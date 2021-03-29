package com.hd.hse.common.module.phone.ui.module.activity;

import java.util.List;

public class LeadSPBean {

    private List<ApprRecordsBean> apprRecords;

    public List<ApprRecordsBean> getApprRecords() {
        return apprRecords;
    }

    public void setApprRecords(List<ApprRecordsBean> apprRecords) {
        this.apprRecords = apprRecords;
    }

    public static class ApprRecordsBean {
        /**
         * tableName : rmt_work_appr_record
         * dataStatus : 0
         * ver : 1
         * created_by : 2.0000000003E12
         * created_dt : 2020-06-04 17:18:43
         * updated_by : 2.0000000003E12
         * updated_dt : 2020-06-04 17:18:43
         * df : 0
         * tenantid : 1
         * ts : 1.591262323196E12
         * sign : 1
         * appr_record_id : 2.00000021314E12
         * work_permit_id : 2.00000002341E12
         * work_subtask_id : 2.000000020863E12
         * created_by_name : 韩杰
         * updated_by_name : 韩杰
         * isenable : 1
         * product_flag : 2
         * orgid : 2.000000000137E12
         * approver_id : 2.0000000003E12
         * approver_name : 韩杰
         * appr_time : 2020-06-04 17:18:42
         * appr_node_name : 领导审批#同意
         * stage : 领导审批
         * op_table_name : rmt_work_permit
         * op_table_id : 2.00000002341E12
         */

        private String tableName;
        private int dataStatus;
        private int ver;
        private double created_by;
        private String created_dt;
        private double updated_by;
        private String updated_dt;
        private int df;
        private int tenantid;
        private double ts;
        private int sign;
        private long appr_record_id;
        private double work_permit_id;
        private double work_subtask_id;
        private String created_by_name;
        private String updated_by_name;
        private int isenable;
        private int product_flag;
        private double orgid;
        private double approver_id;
        private String approver_name;
        private String appr_time;
        private String appr_node_name;
        private String stage;
        private String op_table_name;
        private double op_table_id;

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public int getDataStatus() {
            return dataStatus;
        }

        public void setDataStatus(int dataStatus) {
            this.dataStatus = dataStatus;
        }

        public int getVer() {
            return ver;
        }

        public void setVer(int ver) {
            this.ver = ver;
        }

        public double getCreated_by() {
            return created_by;
        }

        public void setCreated_by(double created_by) {
            this.created_by = created_by;
        }

        public String getCreated_dt() {
            return created_dt;
        }

        public void setCreated_dt(String created_dt) {
            this.created_dt = created_dt;
        }

        public double getUpdated_by() {
            return updated_by;
        }

        public void setUpdated_by(double updated_by) {
            this.updated_by = updated_by;
        }

        public String getUpdated_dt() {
            return updated_dt;
        }

        public void setUpdated_dt(String updated_dt) {
            this.updated_dt = updated_dt;
        }

        public int getDf() {
            return df;
        }

        public void setDf(int df) {
            this.df = df;
        }

        public int getTenantid() {
            return tenantid;
        }

        public void setTenantid(int tenantid) {
            this.tenantid = tenantid;
        }

        public double getTs() {
            return ts;
        }

        public void setTs(double ts) {
            this.ts = ts;
        }

        public int getSign() {
            return sign;
        }

        public void setSign(int sign) {
            this.sign = sign;
        }

        public long getAppr_record_id() {
            return appr_record_id;
        }

        public void setAppr_record_id(long appr_record_id) {
            this.appr_record_id = appr_record_id;
        }

        public double getWork_permit_id() {
            return work_permit_id;
        }

        public void setWork_permit_id(double work_permit_id) {
            this.work_permit_id = work_permit_id;
        }

        public double getWork_subtask_id() {
            return work_subtask_id;
        }

        public void setWork_subtask_id(double work_subtask_id) {
            this.work_subtask_id = work_subtask_id;
        }

        public String getCreated_by_name() {
            return created_by_name;
        }

        public void setCreated_by_name(String created_by_name) {
            this.created_by_name = created_by_name;
        }

        public String getUpdated_by_name() {
            return updated_by_name;
        }

        public void setUpdated_by_name(String updated_by_name) {
            this.updated_by_name = updated_by_name;
        }

        public int getIsenable() {
            return isenable;
        }

        public void setIsenable(int isenable) {
            this.isenable = isenable;
        }

        public int getProduct_flag() {
            return product_flag;
        }

        public void setProduct_flag(int product_flag) {
            this.product_flag = product_flag;
        }

        public double getOrgid() {
            return orgid;
        }

        public void setOrgid(double orgid) {
            this.orgid = orgid;
        }

        public double getApprover_id() {
            return approver_id;
        }

        public void setApprover_id(double approver_id) {
            this.approver_id = approver_id;
        }

        public String getApprover_name() {
            return approver_name;
        }

        public void setApprover_name(String approver_name) {
            this.approver_name = approver_name;
        }

        public String getAppr_time() {
            return appr_time;
        }

        public void setAppr_time(String appr_time) {
            this.appr_time = appr_time;
        }

        public String getAppr_node_name() {
            return appr_node_name;
        }

        public void setAppr_node_name(String appr_node_name) {
            this.appr_node_name = appr_node_name;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getOp_table_name() {
            return op_table_name;
        }

        public void setOp_table_name(String op_table_name) {
            this.op_table_name = op_table_name;
        }

        public double getOp_table_id() {
            return op_table_id;
        }

        public void setOp_table_id(double op_table_id) {
            this.op_table_id = op_table_id;
        }
    }
}
