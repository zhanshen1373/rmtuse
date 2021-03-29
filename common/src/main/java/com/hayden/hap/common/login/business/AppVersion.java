package com.hayden.hap.common.login.business;

import java.io.Serializable;

/**
 * Created by owlla on 2017/2/28.
 */

public class AppVersion implements Serializable {
    /**
     * currentVer : null
     * forceUpgradeVer : null
     * upgradeUrl : null
     */

    private String currentVer;
    private String forceUpgradeVer;
    private String upgradeUrl;

    public String getCurrentVer() {
        return currentVer;
    }

    public void setCurrentVer(String currentVer) {
        this.currentVer = currentVer;
    }

    public String getForceUpgradeVer() {
        return forceUpgradeVer;
    }

    public void setForceUpgradeVer(String forceUpgradeVer) {
        this.forceUpgradeVer = forceUpgradeVer;
    }

    public String getUpgradeUrl() {
        return upgradeUrl;
    }

    public void setUpgradeUrl(String upgradeUrl) {
        this.upgradeUrl = upgradeUrl;
    }
}
