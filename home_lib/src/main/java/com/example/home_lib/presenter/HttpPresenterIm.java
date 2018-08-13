package com.example.home_lib.presenter;


import android.util.Log;

import com.example.home_lib.api.ApiUrl;
import com.example.home_lib.contract.HttpContract;
import com.example.home_lib.model.PrenBean;
import com.example.home_lib.model.TestHttpBean;
import com.example.mtestlibrary.base.BaseBean;
import com.example.mtestlibrary.net.RetrofitBegin;
import com.example.mtestlibrary.rx.BaseObserver;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * HttpPresenterIm
 * 2018/8/13 10:42
 * zhuguoqing
 * 作用:
 */
public class HttpPresenterIm implements HttpContract.HttpPresenter {
    private HttpContract.HttpView view;

    public HttpPresenterIm(HttpContract.HttpView view) {
        this.view = view;
    }

    @Override
    public void loadHttpInfo() {
        view.loadingStar();
        RetrofitBegin.getInstence().create(ApiUrl.class).getTest()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<PrenBean>() {
                    @Override
                    protected void onSuccees(BaseBean<PrenBean> t) throws Exception {
                        Gson gson=new Gson();
                        String s=gson.toJson(t);
                        Log.d("cccc",s);
                       view.showData(t.returnInformation);

                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
        view.loadingDismiss();


    }

    @Override
    public void onDestroy() {

    }
}
