package com.hayden.hap.common.base.activity;

import android.app.Activity;
import android.content.Intent;

import com.hayden.hap.common.base.BaseApplication;
import com.hayden.hap.common.dao.GreenDaoManager;
import com.hayden.hap.common.dao.LoginHistoryDao;
import com.hayden.hap.common.dao.dBEntity.LoginHistory;

import java.util.LinkedList;
import java.util.List;

/**
 * activity栈
 * Created by liuyang on 2017/3/2.
 */

public class ActivityManager {

    private List<Activity> activityList = new LinkedList<>();
    private static ActivityManager instance;

    private ActivityManager() {
    }

    // 单例模式中获取唯一的MyApplication实例
    public static ActivityManager getInstance() {
        if (null == instance) {
            instance = new ActivityManager();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        List<LoginHistory> histories = getLoginHistoryDao().loadAll();
        for (LoginHistory history:histories) {
            history.setAuto(false);
            history.setPassword("");
        }
        getLoginHistoryDao().updateInTx(histories);
        Intent intent = new Intent();
        intent.setAction(BaseApplication.getmAppContext().getPackageName() + ".LOGOUT");
        BaseApplication.getmAppContext().sendBroadcast(intent);
    }

    public void reLogin() {
        //activityList.get(activityList.size() - 1).startActivity(new Intent(activityList.get(activityList.size() - 1), LoginActivity.class));
        exit();
    }

    private LoginHistoryDao getLoginHistoryDao() {
        return GreenDaoManager.getInstance().getmDaoSession().getLoginHistoryDao();
    }
}
