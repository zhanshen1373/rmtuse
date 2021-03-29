package com.hayden.hap.common.utils;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp工具类
 * Created by liuyang on 2017/2/22.
 */
public class OkHttpUtil {
    private static OkHttpClient client;

    public OkHttpUtil(int connectTimeOut, int writeTimeOut, int readTimeOut, final String stokenkey) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeOut, TimeUnit.SECONDS)
                .writeTimeout(writeTimeOut, TimeUnit.SECONDS)
                .readTimeout(readTimeOut, TimeUnit.SECONDS);
        if (stokenkey != null && !stokenkey.equals("")) {
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    // 头信息添加stokenkey
                    Request request = chain.request();
                    Request.Builder builder1 = request.newBuilder();
                    Request build = builder1.addHeader("stokenkey", stokenkey).build();
                    return chain.proceed(build);
                }
            });
        }
        builder.addNetworkInterceptor(new StethoInterceptor());
//                .retryOnConnectionFailure(true)
        client = builder.build();
    }

    public OkHttpClient getClient() {
        return client;
    }
}
