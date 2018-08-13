package com.example.mtestlibrary;

import android.app.Application;
import android.util.Log;

/**
 * BaseApp
 * 2018/8/10 16:48
 * zhuguoqing
 * 作用:
 */
public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("cccc", "hello");
    }
}
