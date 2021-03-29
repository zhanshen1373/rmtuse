package com.hd.hse.entity.workorder;

import java.io.Serializable;

/**
 * created by yangning on 2017/12/19 10:57.
 */

public class RmtJjbData implements Serializable{
    /**
     * 功能编码
     */
    private String funCode;
    /**
     * 二维码生成时间
     */
    private long createTime;
    /**
     * 二维码时效
     */
    private long timeOut;
    /**
     * 分项任务id
     */
    private long work_subtask_id;
    /**
     * 分项任务描述
     */
    private String description;

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public long getWork_subtask_id() {
        return work_subtask_id;
    }

    public void setWork_subtask_id(long work_subtask_id) {
        this.work_subtask_id = work_subtask_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
