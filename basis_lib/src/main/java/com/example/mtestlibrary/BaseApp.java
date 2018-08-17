package com.example.mtestlibrary;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.vondear.rxtool.RxTool;

/**
 * BaseApp
 * 2018/8/10 16:48
 * zhuguoqing
 * 作用:
 */
public class BaseApp extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        RxTool.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
    public static Context getContext(){
        return mContext;
    }

}
