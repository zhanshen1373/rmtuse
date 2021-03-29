package com.hayden.hap.common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

/**
 * 日志类
 * Created by liuyang on 2017/2/27.
 */

public class LogUtil {

    public LogUtil(Class clazz) {
        TAG = clazz.getName();
    }

    private String TAG;
    public static boolean APP_DBG = false; // 是否是debug模式

    public static void init(Context context) {
        APP_DBG = isDebugEnabled(context);
    }

    /**
     * 但是当我们没在AndroidManifest.xml中设置其debug属性时:
     * 使用Eclipse运行这种方式打包时其debug属性为true,使用Eclipse导出这种方式打包时其debug属性为法false.
     * 在使用ant打包时，其值就取决于ant的打包参数是release还是debug.
     * 因此在AndroidMainifest.xml中最好不设置android:debuggable属性置，而是由打包方式来决定其值.
     *
     * @param context
     * @return
     * @author SHANHY
     * @date 2015-8-7
     */
    private static boolean isDebugEnabled(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {

        }
        return false;
    }

    public void error(String msg) {
        Log.e(TAG, msg);
    }

    public void error(String msg, Throwable ex) {
        Log.e(TAG, msg, ex);
    }

    public void debug(String msg) {
        if (APP_DBG)
            Log.d(TAG, msg);
    }

}
