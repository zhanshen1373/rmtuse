/**
 * Project Name:hse-common-module-phone
 * File Name:MainApplication.java
 * Package Name:com.hd.hse.common.module.phone.camera
 * Date:2015年3月31日
 * Copyright (c) 2015, fulibo@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.common.module.phone.camera;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.hayden.hap.common.base.BaseApplication;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.entity.common.ReMindBean;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:MainApplication ().<br/>
 * Date:     2015年3月31日  <br/>
 * @author   flb
 * @version 
 * @see     
 */
public class MainApplication extends BaseApplication {

    private static MainApplication mainApplicationinstance;
    public static Logger logger = LogUtils
            .getLogger(MainApplication.class);
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private static final String TAG = "MainApplication";
	private Thread.UncaughtExceptionHandler mDefaultExHandler;
	private CameraManager mCameraManager;
    //使得每次重新进入mainactivity后都能得到推送的数据
    private  List<ReMindBean.ContentBean> remindlist=new ArrayList<>();

    private Thread.UncaughtExceptionHandler mExHandler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            if (mCameraManager != null) {
                Log.e(TAG, "Uncaught exception! Closing down camera safely firsthand");
                mCameraManager.forceCloseCamera();
            }

            //打正式包的时候，可以不加入这个。
//            logger.error(ex.getMessage(), ex);
//            Intent intent=new Intent(MainApplication.this,ErrorActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            MainApplication.this.startActivity(intent);
            mDefaultExHandler.uncaughtException(thread, ex);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplicationinstance = this;
        mDefaultExHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(mExHandler);

//        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//
//            @Override
//            public void onViewInitFinished(boolean arg0) {
//                // TODO Auto-generated method stub
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//                Log.e("app", " onViewInitFinished is " + arg0);
//            }
//
//            @Override
//            public void onCoreInitFinished() {
//                // TODO Auto-generated method stub
//            }
//        };
//        //x5内核初始化接口
//        QbSdk.initX5Environment(getApplicationContext(),  cb);

    }

    public static MainApplication getmainAppContext() {
        return mainApplicationinstance;
    }

    public  List<ReMindBean.ContentBean> getRemindlist() {
        return remindlist;
    }

    public  void setRemindlist(List<ReMindBean.ContentBean> remindlist) {
        this.remindlist = remindlist;
    }

    public void setCameraManager(CameraManager camMan) {
        mCameraManager = camMan;
    }


}

