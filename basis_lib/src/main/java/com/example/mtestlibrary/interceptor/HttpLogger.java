package com.example.mtestlibrary.interceptor;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * HttpLogger
 * 2018/8/13 17:15
 * zhuguoqing
 * 作用:
 */
public class HttpLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        Log.d("HttpLogger------", message);
    }
}
