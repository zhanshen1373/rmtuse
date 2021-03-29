package com.hayden.hap.common.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工具类
 * 单例模式
 * Created by liuyang on 2017/2/22.
 */
public class RetrofitUtil {
    private Context context;
    private static RetrofitUtil ourInstance;
    private static Retrofit retrofit;
    private String stokenkey;
    private String baseUrl = "http://m-rmt.test-linux.proj-env.hd-intra-local.com/";

    private RetrofitUtil() {
    }

    public String getBaseUrl(){
        return baseUrl;
    }

    public void setStokenkey(String stokenkey) {
        this.stokenkey = stokenkey;
        ourInstance.init();
    }

    public String getStokenkey() {
        return stokenkey;
    }

    public void setContext(Context context) {
        this.context = context;
        ourInstance.init();
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        ourInstance.init();
    }

    public static RetrofitUtil getInstance() {
        if (ourInstance == null) {
            synchronized (RetrofitUtil.class) {
                if (ourInstance == null) {
                    ourInstance = new RetrofitUtil();
                }
            }
        }
        return ourInstance;
    }

    private void init() {
        Properties properties = getProperties("flavor.properties");
        int connectTimeOut = Integer.valueOf(properties.getProperty("CONNECT_TIMEOUT"));
        int writeTimeOut = Integer.valueOf(properties.getProperty("WRITE_TIMEOUT"));
        int readTimeOut = Integer.valueOf(properties.getProperty("READ_TIMEOUT"));
        OkHttpUtil okHttpUtil = new OkHttpUtil(connectTimeOut, writeTimeOut, readTimeOut, stokenkey);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpUtil.getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static <T> T createInterface(Class<T> clazz) {
        return getInstance().getRetrofit().create(clazz);
    }

    public Properties
    getProperties(String propertiesFile) {
        Properties pro = new Properties();
        try {
            InputStream is = context.getAssets().open(propertiesFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            pro.load(bufferedReader);
            // pro.load(is);
        } catch (IOException e) {
            Log.e("MyApplication", e.getMessage());
        }
        return pro;
    }

}
