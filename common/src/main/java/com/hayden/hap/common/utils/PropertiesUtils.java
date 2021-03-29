package com.hayden.hap.common.utils;

import android.util.Log;

import com.hayden.hap.common.base.BaseApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 获取网络设置配置文件中属性值工具类
 * Created by sunxm on 2017/3/22.
 */

public class PropertiesUtils {

    private static final String WEEX_BASE_URL_NAME = "WEEX_BASE_URL";

    private static final String BASE_URL_NAME = "BASE_URL";

    private static String BASE_URL;

    public static String getWeexBaseUrl() {
        Properties properties = getProperties("flavor.properties");
        return properties.getProperty(WEEX_BASE_URL_NAME);
    }

    public static String getBaseUrl() {
        /*Properties properties = getProperties("flavor.properties");
        return properties.getProperty(BASE_URL_NAME);*/
        return  BASE_URL;
    }
    public static void setBaseUrl(String url) {
        BASE_URL=url;
    }


    public static Properties getProperties(String propertiesFile) {
        Properties pro = new Properties();
        try {
            InputStream is = BaseApplication.getmAppContext().getAssets().open(propertiesFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            pro.load(bufferedReader);
            // pro.load(is);
        } catch (IOException e) {
            Log.e(PropertiesUtils.class.getName(), e.getMessage());
        }
        return pro;
    }
}
