package com.hd.hse.entity.submitdata;

import com.hd.hse.entity.conf.RmtConfMIntfc;

/**
 * created by yangning on 2017/5/23 15:06.
 * 提交服务器危害的body
 */

public class HarmBody {
    public long userId;
    public long subtaskId;
    public RmtConfMIntfc rmtConfMIntfc;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(long subtaskId) {
        this.subtaskId = subtaskId;
    }

    public RmtConfMIntfc getRmtConfMIntfc() {
        return rmtConfMIntfc;
    }

    public void setRmtConfMIntfc(RmtConfMIntfc rmtConfMIntfc) {
        this.rmtConfMIntfc = rmtConfMIntfc;
    }


}
