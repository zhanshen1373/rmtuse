package com.hd.hse.entity.resultdata;

import com.hd.hse.entity.workorder.RmtWorkSubtask;

import java.util.List;

/**
 * Created by dbj on 2019/8/5.
 */

public class RmtDelayTaskMsg {


    /**
     * headvo : {"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","task_worktype_code":null,"worklevel_gx":null,"task_worktype_name":null,"specialenvironment":null,"isreport":null,"cywlqfyxzz":null,"worklevel_dh":null,"worklevel_sx":null,"risksmeasures":null,"material_medium":null,"issjtssxzy":null,"isupgradedh":null,"isdzdh":null,"worklevel_gc":null,"persistent_type":null,"projecttype":null,"worklevel_dz":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2018-12-04 16:50:02","updated_by":1000,"updated_dt":"2018-12-04 16:50:16","df":0,"tenantid":1,"ts":1565094025977,"work_subtask_id":2000000006781,"work_task_id":2000000006944,"description":"1204-02","team_id":2000000001978,"team":"综合班","monitor_id":2000000000578,"monitor_name":"康卫红","operator_id":null,"operator_name":null,"work_type":"gen,high","est_start_time":"2018-12-04 16:48:34","est_end_time":"2018-12-31 16:48:36","act_start_time":null,"act_end_time":null,"status":"appr","wf_audit_state":"2","wf_create_user":1000,"wf_current_node":2000000016803,"wf_current_user":2000000000542,"wf_instance":1000000000085,"wf_instance_seq":1,"upload_file":0,"territorial_unit_id":2000000000106,"work_unit_id":2000000000149,"work_site_id":0,"territorial_unit_name":"化肥一厂","work_unit_name":"化肥土建单位","work_site_name":"常压装置","created_by_name":"测试用户","updated_by_name":"测试用户","isenable":1,"product_flag":2,"orgid":2000000000106,"leaderaudit":"wappr","plan_code":null,"gas_aging_value":null,"ishighlight":0,"stop_reason":null,"stop_comment":null}}
     * bodyvos : {"RMT_WORK_DELAY_RECORD_M":[{"tableName":"rmt_work_delay_record","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2019-08-07 10:21:31","updated_by":1000,"updated_dt":"2019-08-07 10:21:31","df":0,"tenantid":1,"ts":1565144491416,"delay_time_str":null,"work_delay_id":1000000000302,"work_subtask_id":2000000006781,"apply_person":"测试用户","apply_personid":1000,"audit_perosn":null,"audit_perosnid":null,"audit_status":"AUDITING","delay_time":"2019-08-07 10:21:00","delay_reason":"ghb","remark":null,"attach":null,"created_by_name":"系统管理员","updated_by_name":"系统管理员","isenable":1,"product_flag":2,"orgid":1000000000006}]}
     * dictvos : {"RMT_WORK_DELAY_RECORD_AUDIT_STATUS_M":[{"tableName":null,"columnValues":null,"dataStatus":0,"code":"DRAFT","name":"草稿","dictdataid":1000000001120,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"AUDITING","name":"审核中","dictdataid":1000000001121,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"AUDITED","name":"已审核","dictdataid":1000000001122,"isleaf":null,"children":null}]}
     */

    private HeadvoBean headvo;
    private BodyvosBean bodyvos;
    private DictvosBean dictvos;

    public HeadvoBean getHeadvo() {
        return headvo;
    }

    public void setHeadvo(HeadvoBean headvo) {
        this.headvo = headvo;
    }

    public BodyvosBean getBodyvos() {
        return bodyvos;
    }

    public void setBodyvos(BodyvosBean bodyvos) {
        this.bodyvos = bodyvos;
    }

    public DictvosBean getDictvos() {
        return dictvos;
    }

    public void setDictvos(DictvosBean dictvos) {
        this.dictvos = dictvos;
    }

    public static class HeadvoBean {
        /**
         * RMT_WORK_SUBTASK_M : {"tableName":"rmt_work_subtask","task_worktype_code":null,"worklevel_gx":null,"task_worktype_name":null,"specialenvironment":null,"isreport":null,"cywlqfyxzz":null,"worklevel_dh":null,"worklevel_sx":null,"risksmeasures":null,"material_medium":null,"issjtssxzy":null,"isupgradedh":null,"isdzdh":null,"worklevel_gc":null,"persistent_type":null,"projecttype":null,"worklevel_dz":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2018-12-04 16:50:02","updated_by":1000,"updated_dt":"2018-12-04 16:50:16","df":0,"tenantid":1,"ts":1565094025977,"work_subtask_id":2000000006781,"work_task_id":2000000006944,"description":"1204-02","team_id":2000000001978,"team":"综合班","monitor_id":2000000000578,"monitor_name":"康卫红","operator_id":null,"operator_name":null,"work_type":"gen,high","est_start_time":"2018-12-04 16:48:34","est_end_time":"2018-12-31 16:48:36","act_start_time":null,"act_end_time":null,"status":"appr","wf_audit_state":"2","wf_create_user":1000,"wf_current_node":2000000016803,"wf_current_user":2000000000542,"wf_instance":1000000000085,"wf_instance_seq":1,"upload_file":0,"territorial_unit_id":2000000000106,"work_unit_id":2000000000149,"work_site_id":0,"territorial_unit_name":"化肥一厂","work_unit_name":"化肥土建单位","work_site_name":"常压装置","created_by_name":"测试用户","updated_by_name":"测试用户","isenable":1,"product_flag":2,"orgid":2000000000106,"leaderaudit":"wappr","plan_code":null,"gas_aging_value":null,"ishighlight":0,"stop_reason":null,"stop_comment":null}
         */

        private RMTWORKSUBTASKMBean RMT_WORK_SUBTASK_M;

        public RMTWORKSUBTASKMBean getRMT_WORK_SUBTASK_M() {
            return RMT_WORK_SUBTASK_M;
        }

        public void setRMT_WORK_SUBTASK_M(RMTWORKSUBTASKMBean RMT_WORK_SUBTASK_M) {
            this.RMT_WORK_SUBTASK_M = RMT_WORK_SUBTASK_M;
        }

        public static class RMTWORKSUBTASKMBean extends RmtWorkSubtask {

        }
    }

    public static class BodyvosBean {
        private List<RMTWORKDELAYRECORDMBean> RMT_WORK_DELAY_RECORD_M;

        public List<RMTWORKDELAYRECORDMBean> getRMT_WORK_DELAY_RECORD_M() {
            return RMT_WORK_DELAY_RECORD_M;
        }

        public void setRMT_WORK_DELAY_RECORD_M(List<RMTWORKDELAYRECORDMBean> RMT_WORK_DELAY_RECORD_M) {
            this.RMT_WORK_DELAY_RECORD_M = RMT_WORK_DELAY_RECORD_M;
        }

        public static class RMTWORKDELAYRECORDMBean extends RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList {

        }
    }

    public static class DictvosBean {
        private List<RMTWORKDELAYRECORDAUDITSTATUSMBean> RMT_WORK_DELAY_RECORD_AUDIT_STATUS_M;

        public List<RMTWORKDELAYRECORDAUDITSTATUSMBean> getRMT_WORK_DELAY_RECORD_AUDIT_STATUS_M() {
            return RMT_WORK_DELAY_RECORD_AUDIT_STATUS_M;
        }

        public void setRMT_WORK_DELAY_RECORD_AUDIT_STATUS_M(List<RMTWORKDELAYRECORDAUDITSTATUSMBean> RMT_WORK_DELAY_RECORD_AUDIT_STATUS_M) {
            this.RMT_WORK_DELAY_RECORD_AUDIT_STATUS_M = RMT_WORK_DELAY_RECORD_AUDIT_STATUS_M;
        }

        public static class RMTWORKDELAYRECORDAUDITSTATUSMBean {
            /**
             * tableName : null
             * columnValues : null
             * dataStatus : 0
             * code : DRAFT
             * name : 草稿
             * dictdataid : 1000000001120
             * isleaf : null
             * children : null
             */

            private Object tableName;
            private Object columnValues;
            private long dataStatus;
            private String code;
            private String name;
            private long dictdataid;
            private Object isleaf;
            private Object children;

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

            public long getDataStatus() {
                return dataStatus;
            }

            public void setDataStatus(long dataStatus) {
                this.dataStatus = dataStatus;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getDictdataid() {
                return dictdataid;
            }

            public void setDictdataid(long dictdataid) {
                this.dictdataid = dictdataid;
            }

            public Object getIsleaf() {
                return isleaf;
            }

            public void setIsleaf(Object isleaf) {
                this.isleaf = isleaf;
            }

            public Object getChildren() {
                return children;
            }

            public void setChildren(Object children) {
                this.children = children;
            }
        }
    }
}
