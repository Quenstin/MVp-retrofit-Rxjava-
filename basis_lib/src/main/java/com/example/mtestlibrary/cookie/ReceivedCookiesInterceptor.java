package com.example.mtestlibrary.cookie;


import com.example.mtestlibrary.BaseApp;
import com.example.mtestlibrary.utils.SPUtil;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * ReceivedCookiesInterceptor
 * 2018/8/13 14:13
 * zhuguoqing
 * 作用:将cookie存储到本地
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("set-cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            Observable.from(originalResponse.headers("set-cookie"))
                    .map(new Func1<String, String>() {
                        @Override
                        public String call(String s) {
                            String[] cookieArray = s.split(";");
                            return cookieArray[0];
                        }
                    })
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String cookie) {
                            cookieBuffer.append(cookie).append(";");
                        }
                    });
            SPUtil.add(BaseApp.getContext(),"COOKIES", cookieBuffer.toString());
        }
        return originalResponse;
    }

}
