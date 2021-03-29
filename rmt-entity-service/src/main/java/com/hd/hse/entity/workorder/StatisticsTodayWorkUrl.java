package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;

/**
 * Created by dubojian on 2017/12/4.
 */

public class StatisticsTodayWorkUrl extends SuperEntity{


    /**
     * url : http://192.168.6.125:8089/m/rmt_m/RMT_COUNT_WORK_M/reportCommonShow?rpx=RMT_COUNT_WORK_M.rpx&uuid=b0ffd3efe3dc449ba81de8cf6ff56e41VnSrmFjiuzVAgrisbmzioLJcCcUvUw&pcContext=http%3A%2F%2F192.168.6.125%3A8081
     */

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
