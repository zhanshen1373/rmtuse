package com.hd.hse.entity.submitdata;

import com.hd.hse.entity.conf.RmtConfMIntfc;

/**
 * created by yangning on 2017/5/23 14:16.
 * 提交措施的body
 */

public class MeasureBody {
    public long userId;
    public RmtConfMIntfc RMT_CONF_M_INTFC_M;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public RmtConfMIntfc getRMT_CONF_M_INTFC_M() {
        return RMT_CONF_M_INTFC_M;
    }

    public void setRMT_CONF_M_INTFC_M(RmtConfMIntfc RMT_CONF_M_INTFC_M) {
        this.RMT_CONF_M_INTFC_M = RMT_CONF_M_INTFC_M;
    }
}
