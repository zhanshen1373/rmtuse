package com.hayden.hap.common.dao.dBEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;

/**
 * 登录历史数据
 * Created by liuyang on 2017/3/1.
 */
@Entity
public class LoginHistory {
    @Id
    private String usercode;
    private String password;
    private Date loginTime;
    private boolean isAuto;

    @Generated(hash = 763721049)
    public LoginHistory(String usercode, String password, Date loginTime,
                        boolean isAuto) {
        this.usercode = usercode;
        this.password = password;
        this.loginTime = loginTime;
        this.isAuto = isAuto;
    }

    @Generated(hash = 21907039)
    public LoginHistory() {
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void setAuto(boolean auto) {
        isAuto = auto;
    }

    public boolean getIsAuto() {
        return this.isAuto;
    }

    public void setIsAuto(boolean isAuto) {
        this.isAuto = isAuto;
    }
}
