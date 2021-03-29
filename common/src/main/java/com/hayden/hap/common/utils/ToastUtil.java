package com.hayden.hap.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by owlla on 2017/2/28.
 */

public class ToastUtil {
    public static void toast(Context context, String msg) {
        try {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
    }

    public static void longToast(Context context, String msg) {
        try {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        } catch (Exception e) {

        }
    }
}
