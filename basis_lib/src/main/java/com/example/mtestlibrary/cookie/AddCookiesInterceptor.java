package com.example.mtestlibrary.cookie;


import com.example.mtestlibrary.BaseApp;
import com.example.mtestlibrary.utils.SPUtil;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;

/**
 * AddCookiesInterceptor
 * 2018/8/13 15:22
 * zhuguoqing
 * 作用:将本地的cookie追加到http请求头中
 */
public class AddCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable.just(SPUtil.getString(BaseApp.getContext(),"COOKIES"))
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String cookie) {
                        //添加cookie
                        builder.addHeader("cookie", cookie);
                    }
                });
        return chain.proceed(builder.build());
    }

}
