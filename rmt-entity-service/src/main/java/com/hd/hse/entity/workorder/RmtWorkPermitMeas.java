package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;

/**
 * 措施子表
 * created by yangning on 2017/4/25 17:05.
 */

public class RmtWorkPermitMeas extends SuperEntity {

    /**
     * tableName : rmt_work_permit_meas
     * meas_id : 1000000000581
     * appr_node_name : null
     * op_status : null
     * appr_node_id : null
     * meas_description : 0518移动端测试数据_0.28蒸汽_工艺措施1
     * approver_id : null
     * meas_type : tech_meas
     * work_permit_meas_id : 1000000000185
     * work_permit_id : 1000000001221
     * approver_name : null
     * work_subtask_id : 1000000001700
     * dataStatus : 0
     */

    private String tableName;
    private long meas_id;
    private Object appr_node_name;
    private Object op_status;
    private Object appr_node_id;
    private String meas_description;
    private Object approver_id;
    private String meas_type;
    private long work_permit_meas_id;
    private long work_permit_id;
    private Object approver_name;
    private long work_subtask_id;
    private int dataStatus;
    private Integer confirm;
    private String goto_repo_tmp;

    public String getGoto_repo_tmp() {
        return goto_repo_tmp;
    }

    public void setGoto_repo_tmp(String goto_repo_tmp) {
        this.goto_repo_tmp = goto_repo_tmp;
    }

    /**
     * isselectitem:TODO(当前选中项目).
     */
    private Boolean isselectitem = false;

    private Integer isselect;

    public Integer getIsselect() {
        return isselect;
    }

    public void setIsselect(Integer isselect) {
        this.isselect = isselect;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Boolean getIsselectitem() {
        return isselectitem;
    }

    public void setIsselectitem(Boolean isselectitem) {
        this.isselectitem = isselectitem;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public long getMeas_id() {
        return meas_id;
    }

    public void setMeas_id(long meas_id) {
        this.meas_id = meas_id;
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

    public String getMeas_description() {
        return meas_description;
    }

    public void setMeas_description(String meas_description) {
        this.meas_description = meas_description;
    }

    public Object getApprover_id() {
        return approver_id;
    }

    public void setApprover_id(Object approver_id) {
        this.approver_id = approver_id;
    }

    public String getMeas_type() {
        return meas_type;
    }

    public void setMeas_type(String meas_type) {
        this.meas_type = meas_type;
    }

    public long getWork_permit_meas_id() {
        return work_permit_meas_id;
    }

    public void setWork_permit_meas_id(long work_permit_meas_id) {
        this.work_permit_meas_id = work_permit_meas_id;
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
