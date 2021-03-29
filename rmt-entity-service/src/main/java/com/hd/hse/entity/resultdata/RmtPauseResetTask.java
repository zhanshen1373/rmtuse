package com.hd.hse.entity.resultdata;

import com.hd.hse.entity.workorder.RmtWorkSubtask;

import java.util.List;

/**
 * Created by dbj on 2019/8/5.
 */

public class RmtPauseResetTask {


    /**
     * headvo : {"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","task_worktype_code":null,"worklevel_gx":null,"task_worktype_name":null,"specialenvironment":null,"isreport":null,"cywlqfyxzz":null,"worklevel_dh":null,"worklevel_sx":null,"risksmeasures":null,"material_medium":null,"issjtssxzy":null,"isupgradedh":null,"isdzdh":null,"worklevel_gc":null,"persistent_type":null,"projecttype":null,"worklevel_dz":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2018-12-04 16:50:02","updated_by":1000,"updated_dt":"2018-12-04 16:50:16","df":0,"tenantid":1,"ts":1543913828440,"work_subtask_id":2000000006781,"work_task_id":2000000006944,"description":"1204-02","team_id":2000000001978,"team":"综合班","monitor_id":2000000000578,"monitor_name":"康卫红","operator_id":null,"operator_name":null,"work_type":"gen,high","est_start_time":"2018-12-04 16:48:34","est_end_time":"2018-12-31 16:48:36","act_start_time":null,"act_end_time":null,"status":"appr","wf_audit_state":"2","wf_create_user":1000,"wf_current_node":2000000016803,"wf_current_user":2000000000542,"wf_instance":1000000000085,"wf_instance_seq":1,"upload_file":0,"territorial_unit_id":2000000000106,"work_unit_id":2000000000149,"work_site_id":0,"territorial_unit_name":"化肥一厂","work_unit_name":"化肥土建单位","work_site_name":"常压装置","created_by_name":"测试用户","updated_by_name":"测试用户","isenable":1,"product_flag":2,"orgid":2000000000106,"leaderaudit":"wappr","plan_code":null,"gas_aging_value":null,"ishighlight":0,"stop_reason":null,"stop_comment":null}}
     * bodyvos : {"RMT_WORK_STARTPAUSE_RECORD_M":[{"tableName":"rmt_work_startpause_record","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2019-08-05 21:54:02","updated_by":1000,"updated_dt":"2019-08-05 21:54:02","df":0,"tenantid":1,"ts":1565013242831,"work_startpause_id":1000000000103,"work_subtask_id":2000000006781,"startpause_type":null,"apply_person":null,"apply_personid":null,"audit_perosn":null,"audit_perosnid":null,"audit_status":"draft","apply_time":null,"pause_reason":null,"pause_remark":null,"start_reason":null,"start_remark":null,"attach":null,"created_by_name":"系统管理员","updated_by_name":"系统管理员","isenable":1,"product_flag":2,"orgid":1000000000006,"pause_id":null}]}
     * dictvos : {"RMT_WORK_START_M":[{"tableName":null,"columnValues":null,"dataStatus":0,"code":"violation_corrected","name":"违章已整改","dictdataid":1000000001160,"isleaf":null,"children":null}],"RMT_WORK_PAUSE_M":[{"tableName":null,"columnValues":null,"dataStatus":0,"code":"night_rest","name":"夜间休息","dictdataid":1000000001161,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"operating_violation","name":"作业违章","dictdataid":1000000001162,"isleaf":null,"children":null}],"RMT_WORK_STARTPAUSE_AUDIT_STATUS_M":[{"tableName":null,"columnValues":null,"dataStatus":0,"code":"draft","name":"草稿","dictdataid":1000000001163,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"auditing","name":"审核中","dictdataid":1000000001164,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"audited","name":"已审核","dictdataid":1000000001165,"isleaf":null,"children":null}]}
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
         * RMT_WORK_SUBTASK_M : {"tableName":"rmt_work_subtask","task_worktype_code":null,"worklevel_gx":null,"task_worktype_name":null,"specialenvironment":null,"isreport":null,"cywlqfyxzz":null,"worklevel_dh":null,"worklevel_sx":null,"risksmeasures":null,"material_medium":null,"issjtssxzy":null,"isupgradedh":null,"isdzdh":null,"worklevel_gc":null,"persistent_type":null,"projecttype":null,"worklevel_dz":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2018-12-04 16:50:02","updated_by":1000,"updated_dt":"2018-12-04 16:50:16","df":0,"tenantid":1,"ts":1543913828440,"work_subtask_id":2000000006781,"work_task_id":2000000006944,"description":"1204-02","team_id":2000000001978,"team":"综合班","monitor_id":2000000000578,"monitor_name":"康卫红","operator_id":null,"operator_name":null,"work_type":"gen,high","est_start_time":"2018-12-04 16:48:34","est_end_time":"2018-12-31 16:48:36","act_start_time":null,"act_end_time":null,"status":"appr","wf_audit_state":"2","wf_create_user":1000,"wf_current_node":2000000016803,"wf_current_user":2000000000542,"wf_instance":1000000000085,"wf_instance_seq":1,"upload_file":0,"territorial_unit_id":2000000000106,"work_unit_id":2000000000149,"work_site_id":0,"territorial_unit_name":"化肥一厂","work_unit_name":"化肥土建单位","work_site_name":"常压装置","created_by_name":"测试用户","updated_by_name":"测试用户","isenable":1,"product_flag":2,"orgid":2000000000106,"leaderaudit":"wappr","plan_code":null,"gas_aging_value":null,"ishighlight":0,"stop_reason":null,"stop_comment":null}
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
        private List<RMTWORKSTARTPAUSERECORDMBean> RMT_WORK_STARTPAUSE_RECORD_M;

        public List<RMTWORKSTARTPAUSERECORDMBean> getRMT_WORK_STARTPAUSE_RECORD_M() {
            return RMT_WORK_STARTPAUSE_RECORD_M;
        }

        public void setRMT_WORK_STARTPAUSE_RECORD_M(List<RMTWORKSTARTPAUSERECORDMBean> RMT_WORK_STARTPAUSE_RECORD_M) {
            this.RMT_WORK_STARTPAUSE_RECORD_M = RMT_WORK_STARTPAUSE_RECORD_M;
        }

        public static class RMTWORKSTARTPAUSERECORDMBean {
            /**
             * tableName : rmt_work_startpause_record
             * columnValues : null
             * dataStatus : 0
             * ver : 1
             * created_by : 1000
             * created_dt : 2019-08-05 21:54:02
             * updated_by : 1000
             * updated_dt : 2019-08-05 21:54:02
             * df : 0
             * tenantid : 1
             * ts : 1565013242831
             * work_startpause_id : 1000000000103
             * work_subtask_id : 2000000006781
             * startpause_type : null
             * apply_person : null
             * apply_personid : null
             * audit_perosn : null
             * audit_perosnid : null
             * audit_status : draft
             * apply_time : null
             * pause_reason : null
             * pause_remark : null
             * start_reason : null
             * start_remark : null
             * attach : null
             * created_by_name : 系统管理员
             * updated_by_name : 系统管理员
             * isenable : 1
             * product_flag : 2
             * orgid : 1000000000006
             * pause_id : null
             */

            private String tableName;
            private Object columnValues;
            private long dataStatus;
            private long ver;
            private long created_by;
            private String created_dt;
            private long updated_by;
            private String updated_dt;
            private long df;
            private long tenantid;
            private long ts;
            private long work_startpause_id;
            private long work_subtask_id;
            private String startpause_type;
            private String apply_person;
            private Object apply_personid;
            private String audit_perosn;
            private Object audit_perosnid;
            private String audit_status;
            private Object apply_time;
            private String pause_reason;
            private String pause_remark;
            private String start_reason;
            private String start_remark;
            private Object attach;
            private String created_by_name;
            private String updated_by_name;
            private long isenable;
            private long product_flag;
            private long orgid;
            private Object pause_id;


            public String getStartpause_type() {
                return startpause_type;
            }

            public void setStartpause_type(String startpause_type) {
                this.startpause_type = startpause_type;
            }

            public String getApply_person() {
                return apply_person;
            }

            public void setApply_person(String apply_person) {
                this.apply_person = apply_person;
            }

            public String getAudit_perosn() {
                return audit_perosn;
            }

            public void setAudit_perosn(String audit_perosn) {
                this.audit_perosn = audit_perosn;
            }

            public String getPause_reason() {
                return pause_reason;
            }

            public void setPause_reason(String pause_reason) {
                this.pause_reason = pause_reason;
            }

            public String getPause_remark() {
                return pause_remark;
            }

            public void setPause_remark(String pause_remark) {
                this.pause_remark = pause_remark;
            }

            public String getStart_reason() {
                return start_reason;
            }

            public void setStart_reason(String start_reason) {
                this.start_reason = start_reason;
            }

            public String getStart_remark() {
                return start_remark;
            }

            public void setStart_remark(String start_remark) {
                this.start_remark = start_remark;
            }

            public String getTableName() {
                return tableName;
            }

            public void setTableName(String tableName) {
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

            public long getVer() {
                return ver;
            }

            public void setVer(long ver) {
                this.ver = ver;
            }

            public long getCreated_by() {
                return created_by;
            }

            public void setCreated_by(long created_by) {
                this.created_by = created_by;
            }

            public String getCreated_dt() {
                return created_dt;
            }

            public void setCreated_dt(String created_dt) {
                this.created_dt = created_dt;
            }

            public long getUpdated_by() {
                return updated_by;
            }

            public void setUpdated_by(long updated_by) {
                this.updated_by = updated_by;
            }

            public String getUpdated_dt() {
                return updated_dt;
            }

            public void setUpdated_dt(String updated_dt) {
                this.updated_dt = updated_dt;
            }

            public long getDf() {
                return df;
            }

            public void setDf(long df) {
                this.df = df;
            }

            public long getTenantid() {
                return tenantid;
            }

            public void setTenantid(long tenantid) {
                this.tenantid = tenantid;
            }

            public long getTs() {
                return ts;
            }

            public void setTs(long ts) {
                this.ts = ts;
            }

            public long getWork_startpause_id() {
                return work_startpause_id;
            }

            public void setWork_startpause_id(long work_startpause_id) {
                this.work_startpause_id = work_startpause_id;
            }

            public long getWork_subtask_id() {
                return work_subtask_id;
            }

            public void setWork_subtask_id(long work_subtask_id) {
                this.work_subtask_id = work_subtask_id;
            }


            public Object getApply_personid() {
                return apply_personid;
            }

            public void setApply_personid(Object apply_personid) {
                this.apply_personid = apply_personid;
            }

            public Object getAudit_perosnid() {
                return audit_perosnid;
            }

            public void setAudit_perosnid(Object audit_perosnid) {
                this.audit_perosnid = audit_perosnid;
            }

            public String getAudit_status() {
                return audit_status;
            }

            public void setAudit_status(String audit_status) {
                this.audit_status = audit_status;
            }

            public Object getApply_time() {
                return apply_time;
            }

            public void setApply_time(Object apply_time) {
                this.apply_time = apply_time;
            }


            public Object getAttach() {
                return attach;
            }

            public void setAttach(Object attach) {
                this.attach = attach;
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

            public long getIsenable() {
                return isenable;
            }

            public void setIsenable(long isenable) {
                this.isenable = isenable;
            }

            public long getProduct_flag() {
                return product_flag;
            }

            public void setProduct_flag(long product_flag) {
                this.product_flag = product_flag;
            }

            public long getOrgid() {
                return orgid;
            }

            public void setOrgid(long orgid) {
                this.orgid = orgid;
            }

            public Object getPause_id() {
                return pause_id;
            }

            public void setPause_id(Object pause_id) {
                this.pause_id = pause_id;
            }
        }
    }

    public static class DictvosBean {
        private List<RMTWORKSTARTMBean> RMT_WORK_START_M;
        private List<RMTWORKPAUSEMBean> RMT_WORK_PAUSE_M;
        private List<RMTWORKSTARTPAUSEAUDITSTATUSMBean> RMT_WORK_STARTPAUSE_AUDIT_STATUS_M;
        private List<RMT_WORK_STARTPAUSE_TYPE_MBean> RMT_WORK_STARTPAUSE_TYPE_M;

        public List<RMT_WORK_STARTPAUSE_TYPE_MBean> getRMT_WORK_STARTPAUSE_TYPE_M() {
            return RMT_WORK_STARTPAUSE_TYPE_M;
        }

        public void setRMT_WORK_STARTPAUSE_TYPE_M(List<RMT_WORK_STARTPAUSE_TYPE_MBean> RMT_WORK_STARTPAUSE_TYPE_M) {
            this.RMT_WORK_STARTPAUSE_TYPE_M = RMT_WORK_STARTPAUSE_TYPE_M;
        }

        public List<RMTWORKSTARTMBean> getRMT_WORK_START_M() {
            return RMT_WORK_START_M;
        }

        public void setRMT_WORK_START_M(List<RMTWORKSTARTMBean> RMT_WORK_START_M) {
            this.RMT_WORK_START_M = RMT_WORK_START_M;
        }

        public List<RMTWORKPAUSEMBean> getRMT_WORK_PAUSE_M() {
            return RMT_WORK_PAUSE_M;
        }

        public void setRMT_WORK_PAUSE_M(List<RMTWORKPAUSEMBean> RMT_WORK_PAUSE_M) {
            this.RMT_WORK_PAUSE_M = RMT_WORK_PAUSE_M;
        }

        public List<RMTWORKSTARTPAUSEAUDITSTATUSMBean> getRMT_WORK_STARTPAUSE_AUDIT_STATUS_M() {
            return RMT_WORK_STARTPAUSE_AUDIT_STATUS_M;
        }

        public void setRMT_WORK_STARTPAUSE_AUDIT_STATUS_M(List<RMTWORKSTARTPAUSEAUDITSTATUSMBean> RMT_WORK_STARTPAUSE_AUDIT_STATUS_M) {
            this.RMT_WORK_STARTPAUSE_AUDIT_STATUS_M = RMT_WORK_STARTPAUSE_AUDIT_STATUS_M;
        }

        public static class RMTWORKSTARTMBean {
            /**
             * tableName : null
             * columnValues : null
             * dataStatus : 0
             * code : violation_corrected
             * name : 违章已整改
             * dictdataid : 1000000001160
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

        public static class RMTWORKPAUSEMBean {
            /**
             * tableName : null
             * columnValues : null
             * dataStatus : 0
             * code : night_rest
             * name : 夜间休息
             * dictdataid : 1000000001161
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

        public static class RMTWORKSTARTPAUSEAUDITSTATUSMBean {
            /**
             * tableName : null
             * columnValues : null
             * dataStatus : 0
             * code : draft
             * name : 草稿
             * dictdataid : 1000000001163
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

        public static class RMT_WORK_STARTPAUSE_TYPE_MBean {

            /**
             * tableName : null
             * columnValues : null
             * dataStatus : 0
             * code : pause
             * name : 暂停
             * dictdataid : 1000000001166
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
