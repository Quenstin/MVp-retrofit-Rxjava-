package com.example.mtestlibrary.net;

import com.example.mtestlibrary.api.ApiServer;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitBegin
 * 2018/8/9 14:29
 * zhuguoqing
 * 作用:网络请求
 */
public class RetrofitBegin {
    private Retrofit mRetrofit;
    private static RetrofitBegin mRetrofitBegin;
    //由于要组件化 所以放弃在retrofit里面直接初始化apiurl.class
//    public static ApiUrl apiUrl;


    private RetrofitBegin() {

        OkHttpClient mClient = new OkHttpClient.Builder()
                .connectTimeout(600, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS)
                .writeTimeout(600, TimeUnit.SECONDS)
//根据服务器 增加cookie 和请求头
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.BASEDAPIOMAIN)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(mClient)
                .build();
//        apiUrl=mRetrofit.create(ApiUrl.class);

    }

    public static Retrofit getInstence() {
        if (mRetrofitBegin == null) {
            synchronized (RetrofitBegin.class) {
                if (mRetrofitBegin == null)
                    mRetrofitBegin = new RetrofitBegin();
            }

        }
        return mRetrofitBegin.mRetrofit;
    }
//    public  ApiUrl getAPI(){
//        return apiUrl;
//    }

}
