package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;

/**
 * 危害子表
 * created by yangning on 2017/4/25 16:51.
 */

public class RmtWorkPermitHarm extends SuperEntity{

    /**
     * tableName : rmt_work_permit_harm
     * harm_type : tech_risk
     * harm_id : 1000000000580
     * appr_node_name : null
     * op_status : null
     * appr_node_id : null
     * approver_id : null
     * work_permit_harm_id : 1000000000286
     * harm_description : 0518移动端测试数据_0.28蒸汽_工艺风险1
     * confirm : null
     * work_permit_id : 1000000001221
     * approver_name : null
     * work_subtask_id : 1000000001700
     * dataStatus : 0
     */

    private String tableName;
    private String harm_type;
    private long harm_id;
    private Object appr_node_name;
    private Object op_status;
    private Object appr_node_id;
    private Object approver_id;
    private long work_permit_harm_id;
    private String harm_description;
    private Integer confirm;
    private long work_permit_id;
    private Object approver_name;
    private long work_subtask_id;
    private int dataStatus;

    public String getGoto_repo_tmp() {
        return goto_repo_tmp;
    }

    public void setGoto_repo_tmp(String goto_repo_tmp) {
        this.goto_repo_tmp = goto_repo_tmp;
    }

    private String goto_repo_tmp;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getHarm_type() {
        return harm_type;
    }

    public void setHarm_type(String harm_type) {
        this.harm_type = harm_type;
    }

    public long getHarm_id() {
        return harm_id;
    }

    public void setHarm_id(long harm_id) {
        this.harm_id = harm_id;
    }

    public Object getAppr_node_name() {
        return appr_node_name;
    }

    public void setAppr_node_name(Object appr_node_name) {
        this.appr_node_name = appr_node_name;
    }

    public Object getOp_status() {
        return op_status;
    }

    public void setOp_status(Object op_status) {
        this.op_status = op_status;
    }

    public Object getAppr_node_id() {
        return appr_node_id;
    }

    public void setAppr_node_id(Object appr_node_id) {
        this.appr_node_id = appr_node_id;
    }

    public Object getApprover_id() {
        return approver_id;
    }

    public void setApprover_id(Object approver_id) {
        this.approver_id = approver_id;
    }

    public long getWork_permit_harm_id() {
        return work_permit_harm_id;
    }

    public void setWork_permit_harm_id(long work_permit_harm_id) {
        this.work_permit_harm_id = work_permit_harm_id;
    }

    public String getHarm_description() {
        return harm_description;
    }

    public void setHarm_description(String harm_description) {
        this.harm_description = harm_description;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public long getWork_permit_id() {
        return work_permit_id;
    }

    public void setWork_permit_id(long work_permit_id) {
        this.work_permit_id = work_permit_id;
    }

    public Object getApprover_name() {
        return approver_name;
    }

    public void setApprover_name(Object approver_name) {
        this.approver_name = approver_name;
    }

    public long getWork_subtask_id() {
        return work_subtask_id;
    }

    public void setWork_subtask_id(long work_subtask_id) {
        this.work_subtask_id = work_subtask_id;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }
}
