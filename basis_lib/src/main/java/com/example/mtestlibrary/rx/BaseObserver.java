package com.example.mtestlibrary.rx;


import android.content.Context;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.CallSuper;
import android.util.Log;


import com.blankj.utilcode.util.ToastUtils;
import com.example.mtestlibrary.base.BaseBean;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * zhuguoqing
 * 2018/8/13
 *
 * @param <T>
 */


public abstract class BaseObserver<T> implements Observer<BaseBean<T>> {
    private Context mContext;
    public final static String Thread_Main = "main";
    private final int RESPONSE_FATAL_EOR = -1;    //返回数据失败,严重的错误
    private int errorCode = -1111;
    private String errorMsg = "未知的错误！";

    private Disposable disposable;

    public BaseObserver(Context cxt) {
        this.mContext = cxt;
    }

    protected BaseObserver() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
        showProgressDialog();

    }

    @Override
    public void onNext(BaseBean<T> tBaseEntity) {
       closeProgressDialog();//请求结束的log或者提示
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }

        try {
            if (tBaseEntity.returnStatus == 200) {
                onSuccees(tBaseEntity);
            } else {
                onCodeError(tBaseEntity.returnStatus, tBaseEntity.remarks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable e) {
        Log.d("BaseObserver", "Throwable t:" + e.toString());  //打印出异常信息
       closeProgressDialog();
        //请求异常提示
        try {
            onFailure(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            errorCode = httpException.code();
            errorMsg = httpException.getMessage();
        } else if (e instanceof SocketTimeoutException) {  //VPN open
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "服务器响应超时";
        } else if (e instanceof ConnectException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "网络连接异常，请检查网络";
        } else if (e instanceof UnknownHostException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "无法解析主机，请检查网络连接";
        } else if (e instanceof UnknownServiceException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "未知的服务器错误";
        } else if (e instanceof IOException) {   //飞行模式等
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "没有网络，请检查网络连接";
        } else if (e instanceof NetworkOnMainThreadException) {
            //主线程不能网络请求，这个很容易发现
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "主线程不能网络请求";
        } else if (e instanceof RuntimeException) {
            //很多的错误都是extends RuntimeException
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "运行时错误" + e.toString();
        } else if (e instanceof JSONException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "解析错误" + e.toString();
        }
        onCodeError(errorCode, errorMsg);


    }

    @Override
    public void onComplete() {

    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(BaseBean<T> t) throws Exception;

    protected abstract void onFailure(Throwable e) throws Exception;

    /**
     * 请求异常
     *
     * @param code
     * @param message
     * @throws Exception
     */
    @CallSuper
    private void onCodeError(int code, String message) {
        if (code == RESPONSE_FATAL_EOR) {
            Log.d("BaseObserver", code + "------" + message);
            ToastUtils.showLong(message, code);
        } else {
            disposeEorCode(message, code);
        }
    }

    /**
     * 对通用问题的统一拦截处理,Demo 项目的特定的做法
     *
     * @param code
     */
    private void disposeEorCode(String message, int code) {
        switch (code) {
            case 101:
            case 112:
            case 123:
            case 401:
                //根据错误的code进行页面处理
                //退回到登录页面


                break;
            case 404:
                Log.d("BaseObserver", "404------------" + message);
                break;
        }

        if (mContext != null && Thread.currentThread().getName().equals(Thread_Main)) {
            ToastUtils.showLong(message + "   ------code=" + code);
        }

    }




    public void showProgressDialog() {
        ToastUtils.showLong("加载中");
    }

    private void closeProgressDialog() {
        ToastUtils.showLong("加载end");
    }


}


