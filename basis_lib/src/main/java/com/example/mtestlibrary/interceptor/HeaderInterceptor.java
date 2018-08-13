package com.example.mtestlibrary.interceptor;

import android.content.Context;

import com.example.mtestlibrary.BaseApp;
import com.meituan.android.walle.WalleChannelReader;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * zhuguoqing
 */
public class HeaderInterceptor implements Interceptor {

    public HeaderInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = addHeaders(chain);
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
        return response;
    }


    /**
     * 添加自定义headers
     *
     * @param chain
     * @return
     */
    private Request addHeaders(Chain chain) {
        Context context = BaseApp.getContext();
        Request request = null;
        try {
            request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "Application/json")
                    //接口版本
                    //.addHeader("apiVersion", NetworkConfig.serverInterfaceVerNum)
                    //应用版本
                    //.addHeader("appVersion", PhoneUtils.getVerName(context))
                    //应用渠道（百度，豌豆荚，appstore等）
                    .addHeader("channel", WalleChannelReader.getChannel(context))
                    //网络状态（wifi，3g，4g）
                    //.addHeader("network", PhoneUtils.getNetWork(context))
                    //ip
                    //.addHeader("ip", NetUtil.getLocalIpAddress())
                    //分辨率（48*1080等）
                    //.addHeader("resolution", PhoneUtils.getResolution(context))
                    //设备id（安卓deviceid或ios uuid）
                    //.addHeader("deviceId", PhoneUtils.getDeviceID(context))
                    //设备名称（用户自定义名称）
                    //.addHeader("deviceName", PhoneUtils.getBrandModel(context))
                    //手机机型（Nexus one或 iphone 6s等
                    //.addHeader("deviceModel", PhoneUtils.getBrandModel(context))
                    //手机类型（ios，android等）
                    .addHeader("os", "Android")
                    //操作系统版本（4.4.4或9.0）
                    //.addHeader("osVersion", PhoneUtils.getPhoneVersion(context))
                    //用户标识
                    //.addHeader("token", UserManager.getManager().getToken())
                    .build();
        } catch (Exception e) {
            request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "Application/json")
                    //用户标识
                    //.addHeader("token", UserManager.getManager().getToken())
                    .build();
        }
        return request;
    }

}
