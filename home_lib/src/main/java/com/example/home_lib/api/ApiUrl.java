package com.example.home_lib.api;

import com.example.home_lib.model.AppUpdateBean;
import com.example.home_lib.model.PrenBean;
import com.example.home_lib.model.TestLogBean;
import com.example.mtestlibrary.api.ApiServer;
import com.example.mtestlibrary.base.BaseBean;
import com.example.mtestlibrary.base.TestBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Observable<BaseBean<AppUpdateBean>> getUpLoadApp(@Path("version_code") int version_code);

    @GET(ApiServer.APP_TEST)
    Observable<BaseBean<PrenBean>> getTest();
    @POST()
    Observable<TestBean<TestLogBean>> getData(@Query("userName")String name, @Query("password")String pwd);
}
