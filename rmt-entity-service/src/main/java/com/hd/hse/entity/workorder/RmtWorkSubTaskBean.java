package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;

import java.util.List;

/**
 * Created by liuting on 2017/5/15.
 * 子任务的类
 */

public class RmtWorkSubTaskBean extends SuperEntity {

    /**
     * headVO : {"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","created_by":1000,"operator_name":null,"act_end_time":null,"work_unit_name":"五公司","status":"inprg","operator_id":null,"work_site_name":"二公司","territorial_unit_name":"五公司","work_type":"gen,hot","est_end_time":"2017-05-12 11:33:11","work_unit_id":1690115,"est_start_time":"2017-05-10 11:33:09","created_by_name":"系统管理员","team":null,"created_dt":"2017-05-12 11:36:56","act_start_time":null,"work_subtask_id":1000000001580,"monitor_name":null,"monitor_id":null,"description":"123123","work_site_id":1690117,"territorial_unit_id":1690120,"work_task_id":1000000000260,"dataStatus":0}}
     * attachDataMap : null
     * bodyVOs : {"RMT_WORK_PERMIT_M":[{"headVO":{"RMT_WORK_PERMIT_M":{"tableName":"rmt_work_permit","work_permit_no":"GEN2017051200002","act_end_time":null,"work_unit_name":"五公司","owner_name":null,"status":null,"territorial_unit_name":"五公司","work_site_name":"二公司","work_type":"gen","est_end_time":null,"work_unit_id":1690115,"est_start_time":null,"project_type":null,"work_permit_id":1000000001100,"act_start_time":null,"work_subtask_id":1000000001580,"work_reason":null,"related_unit_id":null,"work_site_id":1690117,"owner_id":null,"territorial_unit_id":1690120,"work_task_id":1000000000260,"work_name":"11","work_content":"23","related_unit_name":null,"dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_PERMIT_M":{"tableName":"rmt_work_permit","work_permit_no":"HOT2017051200003","act_end_time":null,"work_unit_name":"五公司","owner_name":null,"status":null,"territorial_unit_name":"五公司","work_site_name":"二公司","work_type":"hot","est_end_time":null,"work_unit_id":1690115,"est_start_time":null,"project_type":null,"work_permit_id":1000000001101,"act_start_time":null,"work_subtask_id":1000000001580,"work_reason":null,"related_unit_id":null,"work_site_id":1690117,"owner_id":null,"territorial_unit_id":1690120,"work_task_id":1000000000260,"work_name":"11","work_content":"23","related_unit_name":null,"dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]}
     * attachDataVOs : null
     */

    private HeadVOBeanX headVO;
    private Object attachDataMap;
    private BodyVOsBean bodyVOs;
    private Object attachDataVOs;

    public HeadVOBeanX getHeadVO() {
        return headVO;
    }

    public void setHeadVO(HeadVOBeanX headVO) {
        this.headVO = headVO;
    }

    public Object getAttachDataMap() {
        return attachDataMap;
    }

    public void setAttachDataMap(Object attachDataMap) {
        this.attachDataMap = attachDataMap;
    }

    public BodyVOsBean getBodyVOs() {
        return bodyVOs;
    }

    public void setBodyVOs(BodyVOsBean bodyVOs) {
        this.bodyVOs = bodyVOs;
    }

    public Object getAttachDataVOs() {
        return attachDataVOs;
    }

    public void setAttachDataVOs(Object attachDataVOs) {
        this.attachDataVOs = attachDataVOs;
    }

    public static class HeadVOBeanX extends SuperEntity{
        /**
         * RMT_WORK_SUBTASK_M : {"tableName":"rmt_work_subtask","created_by":1000,"operator_name":null,"act_end_time":null,"work_unit_name":"五公司","status":"inprg","operator_id":null,"work_site_name":"二公司","territorial_unit_name":"五公司","work_type":"gen,hot","est_end_time":"2017-05-12 11:33:11","work_unit_id":1690115,"est_start_time":"2017-05-10 11:33:09","created_by_name":"系统管理员","team":null,"created_dt":"2017-05-12 11:36:56","act_start_time":null,"work_subtask_id":1000000001580,"monitor_name":null,"monitor_id":null,"description":"123123","work_site_id":1690117,"territorial_unit_id":1690120,"work_task_id":1000000000260,"dataStatus":0}
         */

        private RmtWorkSubtask RMT_WORK_SUBTASK_M;

        public RmtWorkSubtask getRMT_WORK_SUBTASK_M() {
            return RMT_WORK_SUBTASK_M;
        }

        public void setRMT_WORK_SUBTASK_M(RmtWorkSubtask RMT_WORK_SUBTASK_M) {
            this.RMT_WORK_SUBTASK_M = RMT_WORK_SUBTASK_M;
        }

    }

    public static class BodyVOsBean extends SuperEntity{
        private List<RMTWORKPERMITMBeanX> RMT_WORK_PERMIT_M;

        public List<RMTWORKPERMITMBeanX> getRMT_WORK_PERMIT_M() {
            return RMT_WORK_PERMIT_M;
        }

        public void setRMT_WORK_PERMIT_M(List<RMTWORKPERMITMBeanX> RMT_WORK_PERMIT_M) {
            this.RMT_WORK_PERMIT_M = RMT_WORK_PERMIT_M;
        }

        public static class RMTWORKPERMITMBeanX extends SuperEntity {
            /**
             * headVO : {"RMT_WORK_PERMIT_M":{"tableName":"rmt_work_permit","work_permit_no":"GEN2017051200002","act_end_time":null,"work_unit_name":"五公司","owner_name":null,"status":null,"territorial_unit_name":"五公司","work_site_name":"二公司","work_type":"gen","est_end_time":null,"work_unit_id":1690115,"est_start_time":null,"project_type":null,"work_permit_id":1000000001100,"act_start_time":null,"work_subtask_id":1000000001580,"work_reason":null,"related_unit_id":null,"work_site_id":1690117,"owner_id":null,"territorial_unit_id":1690120,"work_task_id":1000000000260,"work_name":"11","work_content":"23","related_unit_name":null,"dataStatus":0}}
             * attachDataMap : null
             * bodyVOs : null
             * attachDataVOs : null
             */

            private HeadVOBeanXX headVO;
            private Object attachDataMap;
            private Object bodyVOs;
            private Object attachDataVOs;

            public HeadVOBeanXX getHeadVO() {
                return headVO;
            }

            public void setHeadVO(HeadVOBeanXX headVO) {
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

            public static class HeadVOBeanXX extends SuperEntity{
                /**
                 * RMT_WORK_PERMIT_M : {"tableName":"rmt_work_permit","work_permit_no":"GEN2017051200002","act_end_time":null,"work_unit_name":"五公司","owner_name":null,"status":null,"territorial_unit_name":"五公司","work_site_name":"二公司","work_type":"gen","est_end_time":null,"work_unit_id":1690115,"est_start_time":null,"project_type":null,"work_permit_id":1000000001100,"act_start_time":null,"work_subtask_id":1000000001580,"work_reason":null,"related_unit_id":null,"work_site_id":1690117,"owner_id":null,"territorial_unit_id":1690120,"work_task_id":1000000000260,"work_name":"11","work_content":"23","related_unit_name":null,"dataStatus":0}
                 */

                private RMTWORKPERMITMBean RMT_WORK_PERMIT_M;

                public RMTWORKPERMITMBean getRMT_WORK_PERMIT_M() {
                    return RMT_WORK_PERMIT_M;
                }

                public void setRMT_WORK_PERMIT_M(RMTWORKPERMITMBean RMT_WORK_PERMIT_M) {
                    this.RMT_WORK_PERMIT_M = RMT_WORK_PERMIT_M;
                }

                public static class RMTWORKPERMITMBean extends SuperEntity {
                    /**
                     * tableName : rmt_work_permit
                     * work_permit_no : GEN2017051200002
                     * act_end_time : null
                     * work_unit_name : 五公司
                     * owner_name : null
                     * status : null
                     * territorial_unit_name : 五公司
                     * work_site_name : 二公司
                     * work_type : gen
                     * est_end_time : null
                     * work_unit_id : 1690115
                     * est_start_time : null
                     * project_type : null
                     * work_permit_id : 1000000001100
                     * act_start_time : null
                     * work_subtask_id : 1000000001580
                     * work_reason : null
                     * related_unit_id : null
                     * work_site_id : 1690117
                     * owner_id : null
                     * territorial_unit_id : 1690120
                     * work_task_id : 1000000000260
                     * work_name : 11
                     * work_content : 23
                     * related_unit_name : null
                     * dataStatus : 0
                     */

                    private String tableName;
                    private String work_permit_no;
                    private Object act_end_time;
                    private String work_unit_name;
                    private Object owner_name;
                    private String status;
                    private String territorial_unit_name;
                    private String work_site_name;
                    private String work_type;
                    private Object est_end_time;
                    private long work_unit_id;
                    private Object est_start_time;
                    private Object project_type;
                    private long work_permit_id;
                    private Object act_start_time;
                    private long work_subtask_id;
                    private Object work_reason;
                    private Object related_unit_id;
                    private long work_site_id;
                    private Object owner_id;
                    private long territorial_unit_id;
                    private long work_task_id;
                    private String work_name;
                    private String work_content;
                    private Object related_unit_name;
                    private int dataStatus;

                    public String getTableName() {
                        return tableName;
                    }

                    public void setTableName(String tableName) {
                        this.tableName = tableName;
                    }

                    public String getWork_permit_no() {
                        return work_permit_no;
                    }

                    public void setWork_permit_no(String work_permit_no) {
                        this.work_permit_no = work_permit_no;
                    }

                    public Object getAct_end_time() {
                        return act_end_time;
                    }

                    public void setAct_end_time(Object act_end_time) {
                        this.act_end_time = act_end_time;
                    }

                    public String getWork_unit_name() {
                        return work_unit_name;
                    }

                    public void setWork_unit_name(String work_unit_name) {
                        this.work_unit_name = work_unit_name;
                    }

                    public Object getOwner_name() {
                        return owner_name;
                    }

                    public void setOwner_name(Object owner_name) {
                        this.owner_name = owner_name;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public String getTerritorial_unit_name() {
                        return territorial_unit_name;
                    }

                    public void setTerritorial_unit_name(String territorial_unit_name) {
                        this.territorial_unit_name = territorial_unit_name;
                    }

                    public String getWork_site_name() {
                        return work_site_name;
                    }

                    public void setWork_site_name(String work_site_name) {
                        this.work_site_name = work_site_name;
                    }

                    public String getWork_type() {
                        return work_type;
                    }

                    public void setWork_type(String work_type) {
                        this.work_type = work_type;
                    }

                    public Object getEst_end_time() {
                        return est_end_time;
                    }

                    public void setEst_end_time(Object est_end_time) {
                        this.est_end_time = est_end_time;
                    }

                    public long getWork_unit_id() {
                        return work_unit_id;
                    }

                    public void setWork_unit_id(long work_unit_id) {
                        this.work_unit_id = work_unit_id;
                    }

                    public Object getEst_start_time() {
                        return est_start_time;
                    }

                    public void setEst_start_time(Object est_start_time) {
                        this.est_start_time = est_start_time;
                    }

                    public Object getProject_type() {
                        return project_type;
                    }

                    public void setProject_type(Object project_type) {
                        this.project_type = project_type;
                    }

                    public long getWork_permit_id() {
                        return work_permit_id;
                    }

                    public void setWork_permit_id(long work_permit_id) {
                        this.work_permit_id = work_permit_id;
                    }

                    public Object getAct_start_time() {
                        return act_start_time;
                    }

                    public void setAct_start_time(Object act_start_time) {
                        this.act_start_time = act_start_time;
                    }

                    public long getWork_subtask_id() {
                        return work_subtask_id;
                    }

                    public void setWork_subtask_id(long work_subtask_id) {
                        this.work_subtask_id = work_subtask_id;
                    }

                    public Object getWork_reason() {
                        return work_reason;
                    }

                    public void setWork_reason(Object work_reason) {
                        this.work_reason = work_reason;
                    }

                    public Object getRelated_unit_id() {
                        return related_unit_id;
                    }

                    public void setRelated_unit_id(Object related_unit_id) {
                        this.related_unit_id = related_unit_id;
                    }

                    public long getWork_site_id() {
                        return work_site_id;
                    }

                    public void setWork_site_id(long work_site_id) {
                        this.work_site_id = work_site_id;
                    }

                    public Object getOwner_id() {
                        return owner_id;
                    }

                    public void setOwner_id(Object owner_id) {
                        this.owner_id = owner_id;
                    }

                    public long getTerritorial_unit_id() {
                        return territorial_unit_id;
                    }

                    public void setTerritorial_unit_id(long territorial_unit_id) {
                        this.territorial_unit_id = territorial_unit_id;
                    }

                    public long getWork_task_id() {
                        return work_task_id;
                    }

                    public void setWork_task_id(long work_task_id) {
                        this.work_task_id = work_task_id;
                    }

                    public String getWork_name() {
                        return work_name;
                    }

                    public void setWork_name(String work_name) {
                        this.work_name = work_name;
                    }

                    public String getWork_content() {
                        return work_content;
                    }

                    public void setWork_content(String work_content) {
                        this.work_content = work_content;
                    }

                    public Object getRelated_unit_name() {
                        return related_unit_name;
                    }

                    public void setRelated_unit_name(Object related_unit_name) {
                        this.related_unit_name = related_unit_name;
                    }

                    public int getDataStatus() {
                        return dataStatus;
                    }

                    public void setDataStatus(int dataStatus) {
                        this.dataStatus = dataStatus;
                    }
                }
            }
        }
    }
}


