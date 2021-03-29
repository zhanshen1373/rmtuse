package com.hayden.hap.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * 系统工具类
 * 用来获取一些系统或者设备信息
 * <p>
 * Created by liuyang on 2017/2/15.
 */

public class SysUtil {
    private static LogUtil logger = new LogUtil(SysUtil.class);
    //窗口宽度
    private static int screenWidth;
    //窗口高度
    private static int screenHeight;

    public static int getScreenWidth(Context context) {
        if (screenWidth == 0) {
            DisplayMetrics dm = new DisplayMetrics();
            //取得窗口属性
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
            screenWidth = dm.widthPixels;
        }
        return screenWidth;


    }

    public static int getScreenHeight(Context context) {
        if (screenHeight == 0) {
            DisplayMetrics dm = new DisplayMetrics();
            //取得窗口属性
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
            screenHeight = dm.heightPixels;
        }
        return screenHeight;
    }

    public static String getIMEI(Context context) {
        String imei = ((TelephonyManager) context.getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();
        return imei;
    }

    public static String getVersionName(Context context) {
        String pkName = context.getPackageName();
        try {
            String versionName = context.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            logger.error("未找到版本信息");
            return null;
        }
    }

    /**
     * 收起软键盘
     *
     * @param activity 当前界面
     */
    public static void packUpImm(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
