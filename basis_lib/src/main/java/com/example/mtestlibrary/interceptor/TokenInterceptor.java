package com.example.mtestlibrary.interceptor;

import com.example.mtestlibrary.base.BaseBean;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author Created by 王佳  on  2018/8/9  下午3:44
 * @version 1.0
 */
public class TokenInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody requestBody = request.body();
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        /**
         * 获取respon body
         */
        try {
            if (!response.isSuccessful()) {
                return response;
            }
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (responseBody != null) {
                if (responseBody.contentType() != null) {
                    if (!contentType.toString().contains("application/json")) {
                        return response;
                    }
                }
            }
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            String bodyString = buffer.clone().readString(charset);
            if (bodyString.contains("<html>")) {
                return response;
            }
            BaseBean baseBean = new Gson().fromJson(bodyString, BaseBean.class);
            /**
             * 判断token失效
             */
            if (baseBean.returnStatus == 1002) {
//                String name = UserManager.getManager(UIUtils.getContext()).getUserName();
//                final String pwd = UserManager.getManager().getUserPassword();
//                RequestUserLogin requestUserLogin = new RequestUserLogin();
//                requestUserLogin.setUserName(name);
//                requestUserLogin.setPassword(pwd);
//                Call<ResponseUserLogin> loginCall = ApiManager.getApiService().userLogin(requestUserLogin);
//                /**
//                 * 使用同步方法
//                 */
//                ResponseUserLogin userLogin = loginCall.execute().body();
//                if (userLogin != null && userLogin.getListData() != null
//                        && userLogin.getListData().size() != 0) {
//                    Logger.e(userLogin.getListData().get(0).getToken());
//                    LoginUtils.saveUserMessage(userLogin.getListData().get(0), pwd);  //重新保存数据
//                }
//                String newToken = UserManager.getManager(App.getContext()).getToken();
                Request newRequest = request.newBuilder().header("token", "")
                        .build();
                return chain.proceed(newRequest);
            }
        } catch (Exception e) {
            Logger.e(e + "");
            return response;
        }
        return response;
    }
}
