package com.example.mtestlibrary.cookie;


import android.annotation.SuppressLint;

import com.example.mtestlibrary.BaseApp;
import com.example.mtestlibrary.utils.SPUtil;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.Response;


/**
 * ReceivedCookiesInterceptor
 * 2018/8/13 14:13
 * zhuguoqing
 * 作用:将cookie存储到本地
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @SuppressLint("CheckResult")
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("set-cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            io.reactivex.Observable.fromIterable(originalResponse.headers("set-cookie"))
                    .map(new Function<String, String>() {
                        @Override
                        public String apply(String s) throws Exception {
                            String[] cookieArray = s.split(";");
                            return cookieArray[0];
                        }
                    }).subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    cookieBuffer.append(s).append(";");
                }
            });
            SPUtil.add(BaseApp.getContext(),"COOKIES", cookieBuffer.toString());

        }
        return originalResponse;
    }

}
