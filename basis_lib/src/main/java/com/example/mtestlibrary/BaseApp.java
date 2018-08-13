package com.example.mtestlibrary;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * BaseApp
 * 2018/8/10 16:48
 * zhuguoqing
 * 作用:
 */
public class BaseApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        Log.d("cccc", "hello");
    }
    public static Context getContext(){
        return mContext;
    }

}
