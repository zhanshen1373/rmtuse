package com.hayden.hap.common.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.hayden.hap.common.base.activity.BaseActivity;
import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.dao.GreenDaoManager;
import com.hayden.hap.common.dao.LoginHistoryDao;
import com.hayden.hap.common.dao.dBEntity.LoginHistory;
import com.hayden.hap.common.login.business.LoginInterface;
import com.hayden.hap.common.login.business.LoginKey;
import com.hayden.hap.common.login.business.LoginUser;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.PropertiesUtils;
import com.hayden.hap.common.utils.RSAUtils;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.SysUtil;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import test.demo.mylibrary.R;

/**
 * 欢迎页
 * Created by liuyang
 */
public class WelcomeActivity extends BaseActivity {
    private Timer mTimer;
    private int mTime = 0;
    private boolean isFinish;
    protected boolean isLogin;
    private LoginHistory history;
    private TextView flavorTv;

    public Class getMainActivityClass() {
        String mainActivityClass = PropertiesUtils.getProperties("page.properties").getProperty("MAIN_PAGE");
        try {
            return Class.forName(mainActivityClass);
        } catch (ClassNotFoundException e) {
            showToast("请配置主页面地址");
            return null;
        }
    }

    public Class getLoginActivityClass() {
        return LoginActivity.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_welcome);
        flavorTv = (TextView) findViewById(R.id.flavorTv);

    }

    protected void init() {
        boolean isShow = Boolean.parseBoolean(PropertiesUtils.getProperties("flavor.properties").getProperty("SHOW_FLAVOR"));
        if (isShow) {
            flavorTv.setVisibility(View.VISIBLE);
            flavorTv.setText(PropertiesUtils.getProperties("flavor.properties").getProperty("FLAVOR"));
        } else flavorTv.setVisibility(View.GONE);
        mTimer = new Timer();
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mTime++;
                if (mTime >= 2000 && isFinish) {
                    mTimer.cancel();
                    Intent intent;
                    if (isLogin) {
                        intent = getIntentToMainActivity();
                        intent.putExtra(LoginActivity.APPVERSION, LoginUserRecord.getInstance().getUser().getNewAppVer());
                    } else {
                        intent = getIntentToLoginActivity();
                    }
                    startActivity(intent);
                    finish();
                }
            }
        };
        mTimer.schedule(mTimerTask, 0, 1);
        List<LoginHistory> histories = getLoginHistoryDao().queryBuilder().orderDesc(LoginHistoryDao.Properties.LoginTime).list();
        if (histories != null && histories.size() != 0) {
            history = histories.get(0);
            if (history.isAuto()) {
                login(history.getUsercode(), history.getPassword());
            } else {
                isLogin = false;
                isFinish = true;
            }
        } else {
            isLogin = false;
            isFinish = true;
        }
    }

    protected Intent getIntentToMainActivity() {
        return null;
    }

    protected Intent getIntentToLoginActivity() {
        return null;
    }

    private void login(final String userName, final String password) {

        final LoginInterface loginInterface = RetrofitUtil.createInterface(LoginInterface.class);
        // 获取公钥
        Call<ResultData<LoginKey>> getKeyCall = loginInterface.getStokenkey();
        HttpBusiness.action(WelcomeActivity.this, false, getKeyCall, new HttpCallBack<LoginKey>() {
            @Override
            public void success(LoginKey loginKey) {
                String passRSA = encryptPassword(loginKey, password);
                // 登录
                LoginInterface.LoginRequestBody body = new LoginInterface.LoginRequestBody();
                body.username = userName;
                body.password = passRSA;
                body.appVersion = SysUtil.getVersionName(WelcomeActivity.this);
                body.stokenKey = loginKey.getStokenkey();
                body.equipmentCode = SysUtil.getIMEI(WelcomeActivity.this);
                Call<ResultData<LoginUser>> loginCall = loginInterface.login(body);
                HttpBusiness.action(WelcomeActivity.this, false, loginCall, new HttpCallBack<LoginUser>() {
                    @Override
                    public void success(LoginUser loginUser) {
                        // showToast("登录成功");
                        saveUserToSys(loginUser);
                        saveUserInfo(loginUser);
                        RetrofitUtil.getInstance().setStokenkey(loginUser.getStokenKey());
                        isFinish = true;
                        isLogin = true;
                    }

                    @Override
                    public void warning(String msg) {
                        isFinish = true;
                        isLogin = false;
                    }

                    @Override
                    public void error(Throwable t) {
                        isFinish = true;
                        isLogin = false;
                    }
                });
            }

            @Override
            public void warning(String msg) {
                isFinish = true;
                isLogin = false;
            }

            @Override
            public void error(Throwable t) {
                isFinish = true;
                isLogin = false;
            }
        });
    }

    private String encryptPassword(LoginKey key, String password) {
        String modulus = key.getModulus();
        String publicExponent = key.getPublicExponent();
        return RSAUtils.encryptString(RSAUtils.getRSAPublidKey(modulus, publicExponent), password);
    }

    private void saveUserInfo(LoginUser user) {
        LoginUserRecord.getInstance().setUser(user);
        history.setLoginTime(new Date());
        getLoginHistoryDao().insertOrReplace(history);

    }

    protected void saveUserToSys(LoginUser loginUser) {

    }

    public LoginHistoryDao getLoginHistoryDao() {
        return GreenDaoManager.getInstance().getmDaoSession().getLoginHistoryDao();
    }


}
