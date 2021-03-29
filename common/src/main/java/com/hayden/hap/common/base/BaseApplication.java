package com.hayden.hap.common.base;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.hayden.hap.common.dao.GreenDaoManager;
import com.hayden.hap.common.utils.LogUtil;
import com.hayden.hap.common.utils.RetrofitUtil;

/**
 * Created by liuyang on 2017/4/10.
 */

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    private static BaseApplication instence;

    @Override
    public void onCreate() {
        super.onCreate();
        instence = this;
        // 初始化日志类
        LogUtil.init(BaseApplication.this);
        // initRealm();
        // 初始化Stetho调试框架
        initStetho();
        // 初始化数据库
        GreenDaoManager.getInstance().init(BaseApplication.this);
        // 初始化Weex
//        InitConfig config = new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
//        WXSDKEngine.initialize(this, config);

        //ImageLoaderUtil.initImageLoader(this);
        /*InitConfig config = new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(this, config);
        try {
            WXSDKEngine.registerModule("navigator", MyWXNavigatorModule.class);
            WXSDKEngine.registerModule("hapEvent", MyWXEnventModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }*/
        RetrofitUtil.getInstance().setContext(this);

    }

    public static BaseApplication getmAppContext() {
        return instence;
    }

    /**
     * 初始化Stetho框架<br/>
     * Strtho是facebook的一个用来在chrome中调试android程序的框架
     * 调试地址 chrome://inspect
     */
    private void initStetho() {
        if (LogUtil.APP_DBG)
            Stetho.initializeWithDefaults(this);
    }


}
