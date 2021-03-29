package com.hd.hse.entity.resultdata;

import com.hd.hse.entity.workorder.RmtWorkPermitHarm;
import com.hd.hse.entity.workorder.RmtWorkPermitMeas;

import java.util.List;

/**
 * created by yangning on 2017/5/24 10:01.
 * 风险、措施
 */

public class RmtVirRiskM {
    /**
     * tableName : null
     * columnValues : null
     * dataStatus : 0
     * risk_type : gen
     * work_permit_id : 1000000001221
     * work_subtask_id : 1000000001700
     * work_task_id : null
     * m_intfc_id : 1000000000200
     * voList : [{"tableName":"rmt_work_permit_meas","meas_id":1000000000581,"appr_node_name":null,"op_status":null,"appr_node_id":null,"meas_description":"0518移动端测试数据_0.28蒸汽_工艺措施1","approver_id":null,"meas_type":"tech_meas","work_permit_meas_id":1000000000185,"work_permit_id":1000000001221,"approver_name":null,"work_subtask_id":1000000001700,"dataStatus":0},{"tableName":"rmt_work_permit_meas","meas_id":1000000000583,"appr_node_name":null,"op_status":null,"appr_node_id":null,"meas_description":"0518移动端测试数据_0.28蒸汽_作业措施1","approver_id":null,"meas_type":"work_meas","work_permit_meas_id":1000000000186,"work_permit_id":1000000001221,"approver_name":null,"work_subtask_id":1000000001700,"dataStatus":0},{"tableName":"rmt_work_permit_meas","meas_id":1000000000585,"appr_node_name":null,"op_status":null,"appr_node_id":null,"meas_description":"0518移动端测试数据_动火作业_工艺措施1","approver_id":null,"meas_type":"tech_meas","work_permit_meas_id":1000000000187,"work_permit_id":1000000001221,"approver_name":null,"work_subtask_id":1000000001700,"dataStatus":0},{"tableName":"rmt_work_permit_meas","meas_id":1000000000587,"appr_node_name":null,"op_status":null,"appr_node_id":null,"meas_description":"0518移动端测试数据_动火作业_作业措施1","approver_id":null,"meas_type":"work_meas","work_permit_meas_id":1000000000188,"work_permit_id":1000000001221,"approver_name":null,"work_subtask_id":1000000001700,"dataStatus":0},{"tableName":"rmt_work_permit_meas","meas_id":1000000000589,"appr_node_name":null,"op_status":null,"appr_node_id":null,"meas_description":"0518移动端测试数据_动火作业_设备0518_工艺措施1","approver_id":null,"meas_type":"tech_meas","work_permit_meas_id":1000000000189,"work_permit_id":1000000001221,"approver_name":null,"work_subtask_id":1000000001700,"dataStatus":0},{"tableName":"rmt_work_permit_meas","meas_id":1000000000591,"appr_node_name":null,"op_status":null,"appr_node_id":null,"meas_description":"0518移动端测试数据_动火作业_设备0518_工艺风险2","approver_id":null,"meas_type":"tech_meas","work_permit_meas_id":1000000000190,"work_permit_id":1000000001221,"approver_name":null,"work_subtask_id":1000000001700,"dataStatus":0}]
     */

    private Object tableName;
    private Object columnValues;
    private int dataStatus;
    private String risk_type;
    private long work_permit_id;
    private long work_subtask_id;
    private Object work_task_id;
    private long m_intfc_id;
    private String confirmed_by;
    private List<RmtWorkPermitMeas> measVOList;
    private List<RmtWorkPermitHarm> harmVOList;

    public Object getTableName() {
        return tableName;
    }

    public void setTableName(Object tableName) {
        this.tableName = tableName;
    }

    public Object getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(Object columnValues) {
        this.columnValues = columnValues;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getRisk_type() {
        return risk_type;
    }

    public void setRisk_type(String risk_type) {
        this.risk_type = risk_type;
    }

    public long getWork_permit_id() {
        return work_permit_id;
    }

    public void setWork_permit_id(long work_permit_id) {
        this.work_permit_id = work_permit_id;
    }

    public long getWork_subtask_id() {
        return work_subtask_id;
    }

    public void setWork_subtask_id(long work_subtask_id) {
        this.work_subtask_id = work_subtask_id;
    }

    public Object getWork_task_id() {
        return work_task_id;
    }

    public void setWork_task_id(Object work_task_id) {
        this.work_task_id = work_task_id;
    }

    public long getM_intfc_id() {
        return m_intfc_id;
    }

    public void setM_intfc_id(long m_intfc_id) {
        this.m_intfc_id = m_intfc_id;
    }

    public List<RmtWorkPermitMeas> getMeasVOList() {
        return measVOList;
    }

    public void setMeasVOList(List<RmtWorkPermitMeas> measVOList) {
        this.measVOList = measVOList;
    }

    public List<RmtWorkPermitHarm> getHarmVOList() {
        return harmVOList;
    }

    public void setHarmVOList(List<RmtWorkPermitHarm> harmVOList) {
        this.harmVOList = harmVOList;
    }
    public String getConfirmed_by() {
        return confirmed_by;
    }

    public void setConfirmed_by(String confirmed_by) {
        this.confirmed_by = confirmed_by;
    }
}
