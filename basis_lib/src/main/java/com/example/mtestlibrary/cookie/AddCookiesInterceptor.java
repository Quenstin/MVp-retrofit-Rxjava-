package com.example.mtestlibrary.cookie;


import android.annotation.SuppressLint;

import com.example.mtestlibrary.BaseApp;
import com.example.mtestlibrary.utils.SPUtil;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * AddCookiesInterceptor
 * 2018/8/13 15:22
 * zhuguoqing
 * 作用:将本地的cookie追加到http请求头中
 */
public class AddCookiesInterceptor implements Interceptor {
    @SuppressLint("CheckResult")
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable.just(SPUtil.getString(BaseApp.getContext(),"COOKIES"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //添加cookie
                        builder.addHeader("cookie", s);
                    }


                });
        return chain.proceed(builder.build());
    }

}
