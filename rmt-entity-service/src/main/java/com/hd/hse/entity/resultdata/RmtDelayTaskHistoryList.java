package com.hd.hse.entity.resultdata;


import com.hd.hse.common.entity.SuperEntity;

import java.util.List;

/**
 * Created by dbj on 2019/7/30.
 */

public class RmtDelayTaskHistoryList {

    /**
     * dictvos : null
     * mainvo : [{"headVO":{"RMT_WORK_QSTN_LIST_M":{"tableName":"rmt_work_qstn_list","qstn_unit_name":"we","ts":1495086885904,"violator_id":null,"owner_name":"李四","qstn_unit_id":null,"created_by_name":"测试用户","ver":1,"inspector_name":"张三","product_flag":2,"df":0,"orgid":808212,"description":null,"updated_by":1000,"updated_dt":"2017-05-02 18:00:33","tenantid":1,"inspect_unit_name":"检查站","created_by":1000,"site":"一厂","inspect_unit_id":null,"pic":null,"inspector_id":null,"isenable":1,"created_dt":"2017-05-02 17:08:05","work_subtask_id":1,"inspect_time":null,"qstn_type":null,"owner_id":null,"tenant_name":"海顿","qstn_list_id":1000000000040,"updated_by_name":"测试用户","violator_name":"张三","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_QSTN_LIST_M":{"tableName":"rmt_work_qstn_list","qstn_unit_name":"we","ts":1495792096954,"violator_id":null,"owner_name":"李四","qstn_unit_id":null,"created_by_name":"测试用户","ver":1,"inspector_name":"张三","product_flag":2,"df":0,"orgid":808212,"description":null,"updated_by":1000,"updated_dt":"2017-05-02 18:00:33","tenantid":1,"inspect_unit_name":"检查站","created_by":1000,"site":"一厂","inspect_unit_id":null,"pic":null,"inspector_id":null,"isenable":1,"created_dt":"2017-05-02 17:08:05","work_subtask_id":1,"inspect_time":null,"qstn_type":null,"owner_id":null,"tenant_name":"海顿","qstn_list_id":1000000000100,"updated_by_name":"测试用户","violator_name":"张三","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_QSTN_LIST_M":{"tableName":"rmt_work_qstn_list","qstn_unit_name":"HD","ts":1499054480912,"violator_id":126655265,"owner_name":"现场负责人","qstn_unit_id":126655265,"created_by_name":"测试用户","ver":1,"inspector_name":null,"product_flag":2,"df":0,"orgid":1,"description":"测试","updated_by":1000,"updated_dt":"2017-07-03 12:01:20","tenantid":1,"inspect_unit_name":null,"created_by":1000,"site":"1","inspect_unit_id":null,"pic":null,"inspector_id":null,"isenable":1,"created_dt":"2017-07-03 12:01:20","work_subtask_id":126655265,"inspect_time":null,"qstn_type":null,"owner_id":126655265,"tenant_name":"海顿","qstn_list_id":1000000000821,"updated_by_name":"系统管理员","violator_name":"违章人员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]
     */

    private DictvosBean dictvos;
    private List<MainvoBean> mainvo;

    public DictvosBean getDictvos() {
        return dictvos;
    }

    public void setDictvos(DictvosBean dictvos) {
        this.dictvos = dictvos;
    }

    public List<MainvoBean> getMainvo() {
        return mainvo;
    }

    public void setMainvo(List<MainvoBean> mainvo) {
        this.mainvo = mainvo;
    }

    public static class MainvoBean extends SuperEntity {
        /**
         * headVO : {"RMT_WORK_QSTN_LIST_M":{"tableName":"rmt_work_qstn_list","qstn_unit_name":"we","ts":1495086885904,"violator_id":null,"owner_name":"李四","qstn_unit_id":null,"created_by_name":"测试用户","ver":1,"inspector_name":"张三","product_flag":2,"df":0,"orgid":808212,"description":null,"updated_by":1000,"updated_dt":"2017-05-02 18:00:33","tenantid":1,"inspect_unit_name":"检查站","created_by":1000,"site":"一厂","inspect_unit_id":null,"pic":null,"inspector_id":null,"isenable":1,"created_dt":"2017-05-02 17:08:05","work_subtask_id":1,"inspect_time":null,"qstn_type":null,"owner_id":null,"tenant_name":"海顿","qstn_list_id":1000000000040,"updated_by_name":"测试用户","violator_name":"张三","dataStatus":0}}
         * attachDataMap : null
         * bodyVOs : null
         * attachDataVOs : null
         */

        private HeadVOBean headVO;
        private Object attachDataMap;
        private Object bodyVOs;
        private Object attachDataVOs;

        public HeadVOBean getHeadVO() {
            return headVO;
        }

        public void setHeadVO(HeadVOBean headVO) {
            this.headVO = headVO;
        }

        public Object getAttachDataMap() {
            return attachDataMap;
        }

        public void setAttachDataMap(Object attachDataMap) {
            this.attachDataMap = attachDataMap;
        }

        public Object getBodyVOs() {
            return bodyVOs;
        }

        public void setBodyVOs(Object bodyVOs) {
            this.bodyVOs = bodyVOs;
        }

        public Object getAttachDataVOs() {
            return attachDataVOs;
        }

        public void setAttachDataVOs(Object attachDataVOs) {
            this.attachDataVOs = attachDataVOs;
        }

        public static class HeadVOBean extends SuperEntity {

            private RmtWdrList RMT_WORK_DELAY_RECORD_M;

            public RmtWdrList getRMT_WORK_DELAY_RECORD_M() {
                return RMT_WORK_DELAY_RECORD_M;
            }

            public void setRMT_WORK_DELAY_RECORD_M(RmtWdrList RMT_WORK_DELAY_RECORD_M) {
                this.RMT_WORK_DELAY_RECORD_M = RMT_WORK_DELAY_RECORD_M;
            }

            public static class RmtWdrList extends SuperEntity {

                /**
                 * tableName :  rmt_work_delay_record
                 * work_delay_id : 主键id
                 * work_subtask_id : 分项任务ID
                 * apply_person : 申请人
                 * apply_personid : 申请人id
                 * audit_perosn : 批准人
                 * audit_perosnid : 批准人id
                 * audit_status : 审批状态
                 * delay_time : 延期时间
                 * delay_reason : 延期原因[文本]
                 * remark : 备注[冗余]
                 * attach : 附件[冗余]
                 * tenantid :
                 * created_by : null
                 * created_by_name : null
                 * created_dt : null
                 * updated_by : null
                 * updated_by_name : null
                 * updated_dt : null
                 * isenable : null
                 * product_flag : null
                 * df : null
                 * ts : null
                 * orgid : null
                 * ver : null
                 */

                protected String tableName;
                protected long work_delay_id;
                protected String work_subtask_id;
                protected String apply_person;
                protected String apply_personid;
                protected String audit_perosn;
                protected String audit_perosnid;
                protected String audit_status;
                protected String delay_time;
                protected String delay_reason;
                protected String remark;
                protected String attach;
                protected String tenantid;
                protected Object created_by;
                protected Object created_by_name;
                protected Object created_dt;
                protected Object updated_by;
                protected Object updated_by_name;
                protected Object updated_dt;
                protected Object isenable;
                protected Object product_flag;
                protected Object df;
                protected Object ts;
                protected Object orgid;
                protected Object ver;
                protected String delay_time_str;

                public String getDelay_time_str() {
                    return delay_time_str;
                }

                public void setDelay_time_str(String delay_time_str) {
                    this.delay_time_str = delay_time_str;
                }

                public String getTableName() {
                    return tableName;
                }

                public void setTableName(String tableName) {
                    this.tableName = tableName;
                }

                public long getWork_delay_id() {
                    return work_delay_id;
                }

                public void setWork_delay_id(long work_delay_id) {
                    this.work_delay_id = work_delay_id;
                }

                public String getWork_subtask_id() {
                    return work_subtask_id;
                }

                public void setWork_subtask_id(String work_subtask_id) {
                    this.work_subtask_id = work_subtask_id;
                }

                public String getApply_person() {
                    return apply_person;
                }

                public void setApply_person(String apply_person) {
                    this.apply_person = apply_person;
                }

                public String getApply_personid() {
                    return apply_personid;
                }

                public void setApply_personid(String apply_personid) {
                    this.apply_personid = apply_personid;
                }

                public String getAudit_perosn() {
                    return audit_perosn;
                }

                public void setAudit_perosn(String audit_perosn) {
                    this.audit_perosn = audit_perosn;
                }

                public String getAudit_perosnid() {
                    return audit_perosnid;
                }

                public void setAudit_perosnid(String audit_perosnid) {
                    this.audit_perosnid = audit_perosnid;
                }

                public String getAudit_status() {
                    return audit_status;
                }

                public void setAudit_status(String audit_status) {
                    this.audit_status = audit_status;
                }

                public String getDelay_time() {
                    return delay_time;
                }

                public void setDelay_time(String delay_time) {
                    this.delay_time = delay_time;
                }

                public String getDelay_reason() {
                    return delay_reason;
                }

                public void setDelay_reason(String delay_reason) {
                    this.delay_reason = delay_reason;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getAttach() {
                    return attach;
                }

                public void setAttach(String attach) {
                    this.attach = attach;
                }

                public String getTenantid() {
                    return tenantid;
                }

                public void setTenantid(String tenantid) {
                    this.tenantid = tenantid;
                }

                public Object getCreated_by() {
                    return created_by;
                }

                public void setCreated_by(Object created_by) {
                    this.created_by = created_by;
                }

                public Object getCreated_by_name() {
                    return created_by_name;
                }

                public void setCreated_by_name(Object created_by_name) {
                    this.created_by_name = created_by_name;
                }

                public Object getCreated_dt() {
                    return created_dt;
                }

                public void setCreated_dt(Object created_dt) {
                    this.created_dt = created_dt;
                }

                public Object getUpdated_by() {
                    return updated_by;
                }

                public void setUpdated_by(Object updated_by) {
                    this.updated_by = updated_by;
                }

                public Object getUpdated_by_name() {
                    return updated_by_name;
                }

                public void setUpdated_by_name(Object updated_by_name) {
                    this.updated_by_name = updated_by_name;
                }

                public Object getUpdated_dt() {
                    return updated_dt;
                }

                public void setUpdated_dt(Object updated_dt) {
                    this.updated_dt = updated_dt;
                }

                public Object getIsenable() {
                    return isenable;
                }

                public void setIsenable(Object isenable) {
                    this.isenable = isenable;
                }

                public Object getProduct_flag() {
                    return product_flag;
                }

                public void setProduct_flag(Object product_flag) {
                    this.product_flag = product_flag;
                }

                public Object getDf() {
                    return df;
                }

                public void setDf(Object df) {
                    this.df = df;
                }

                public Object getTs() {
                    return ts;
                }

                public void setTs(Object ts) {
                    this.ts = ts;
                }

                public Object getOrgid() {
                    return orgid;
                }

                public void setOrgid(Object orgid) {
                    this.orgid = orgid;
                }

                public Object getVer() {
                    return ver;
                }

                public void setVer(Object ver) {
                    this.ver = ver;
                }
            }

        }
    }

    public static class DictvosBean extends SuperEntity{

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
