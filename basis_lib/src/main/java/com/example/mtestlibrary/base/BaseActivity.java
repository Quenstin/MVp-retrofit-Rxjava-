package com.example.mtestlibrary.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * BaseActivity
 * 2018/8/9 14:17
 * zhuguoqing
 * 作用: 所有activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;
    protected Context appContext;
    protected Application mApplication;
    private BasePresenter mBasePresenter=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
        appContext = getApplicationContext();
        mApplication = getApplication();
        setContentView(getLayoutResId());
        mBasePresenter=binPresenter();
        initView();
        initData();

    }


    public abstract int getLayoutResId();

    public abstract void initView();

    public abstract void initData();

    protected abstract BasePresenter binPresenter();



    //重写该方法 要重写super.onDestroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBasePresenter!=null){
            mBasePresenter.onDestroy();
            mBasePresenter=null;
            System.gc();
        }
    }
}
