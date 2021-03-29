package com.hayden.hap.common.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hayden.hap.common.utils.ToastUtil;

/**
 * 所有页面的父级
 * Created by liuyang on 2017/2/14.
 */

public class BaseActivity extends AppCompatActivity {
    protected void showToast(String msg) {
        ToastUtil.toast(this, msg);
    }

    protected void showLongToast(String msg) {
        ToastUtil.longToast(this, msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }
}
