package com.hayden.hap.common.login.business;

import java.util.List;

/**
 * Created by liuyang on 2017/4/10.
 */

public class LoginUserRecord {
    private static final LoginUserRecord ourInstance = new LoginUserRecord();
    private LoginUser user;
    private List<Long> orgidList;
    private String ob;


    public String getOb() {
        return ob;
    }

    public void setOb(String ob) {
        this.ob = ob;
    }

    public List<Long> getOrgidList() {
        return orgidList;
    }

    public void setOrgidList(List<Long> orgidList) {
        this.orgidList = orgidList;
    }

    public static LoginUserRecord getInstance() {
        return ourInstance;
    }

    private LoginUserRecord() {
    }

    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }
}
