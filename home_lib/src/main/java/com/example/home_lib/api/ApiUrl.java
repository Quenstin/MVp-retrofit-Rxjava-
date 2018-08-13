package com.example.home_lib.api;

import com.example.home_lib.model.AppUpdateBean;
import com.example.home_lib.model.TestBean;
import com.example.home_lib.model.TestHttpBean;
import com.example.mtestlibrary.api.ApiServer;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * ApiUrl
 * 2018/8/10 14:40
 * zhuguoqing
 * 作用:
 */
public interface ApiUrl {
    /**
     * 检测升级app
     */
    @GET(ApiServer.APP_UPDATE)
    Observable<AppUpdateBean> getUpLoadApp(@Path("version_code") int version_code);

    @GET(ApiServer.APP_TEST)
    Observable<TestHttpBean> getTest();
}
