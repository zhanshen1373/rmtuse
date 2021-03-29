package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;

/**
 * Created by dubojian on 2017/11/30.
 */

public class TaskUrl extends SuperEntity {


    /**
     * url : http://rqrpt.test-win.proj-nx.hd-intra-local.com/m/rmt_m/RMT_WORK_PERMIT_M/reportCommonShow?rpx=RMT_WORK_PERMIT_M.rpx&uuid=da793e4095e04d16967e9ca5beeb1afabvECTOoHjbFVXJfGPfoGutXqHQRjpP&pcContext=http%3A%2F%2Fm-rmt.test-win.proj-nx.hd-intra-local.com%3A80
     */

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
