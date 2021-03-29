package com.hd.hse.entity.workorder;

import java.util.List;

/**
 * created by yangning on 2017/5/10 10:40.
 */

public class Test {

    /**
     * successfull : 1
     * messages : null
     * resultData : {"dictvos":null,"mainvo":[{"headVO":{"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1493794487559,"operator_id":null,"wf_audit_state":"2","wf_current_node":1000000001121,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-03 00:00:00","product_flag":2,"upload_file":null,"df":0,"orgid":808212,"monitor_id":null,"description":"浣滀笟浠诲姟鐢熸垚浣滀笟绁ㄥ嵄瀹虫帾鏂芥祴璇曟暟鎹�1>>0503>>lyz","updated_by":1000,"updated_dt":"2017-05-03 14:50:31","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":1000,"status":"inprg","work_type":"gen,hot,cfd,high,hst,ppl,dig,elec,ray","est_end_time":"2017-05-04 00:00:00","isenable":1,"team":null,"created_dt":"2017-05-03 14:50:31","act_start_time":null,"work_subtask_id":1000000001420,"wf_instance_seq":1,"wf_instance":1000000000000,"monitor_name":null,"wf_current_user":1000,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1493978431931,"operator_id":null,"wf_audit_state":"0","wf_current_node":null,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-05 18:00:26","product_flag":2,"upload_file":0,"df":0,"orgid":808212,"monitor_id":null,"description":"21321","updated_by":1000,"updated_dt":"2017-05-05 18:00:31","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":null,"status":"wappr","work_type":"gen,hot,cfd,high,ppl,dig,hst,ray,elec","est_end_time":"2017-05-06 18:00:29","isenable":1,"team":null,"created_dt":"2017-05-05 18:00:31","act_start_time":null,"work_subtask_id":1000000001422,"wf_instance_seq":null,"wf_instance":null,"monitor_name":null,"wf_current_user":null,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1493797336241,"operator_id":null,"wf_audit_state":"2","wf_current_node":1000000001141,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-03 00:00:00","product_flag":2,"upload_file":null,"df":0,"orgid":808212,"monitor_id":null,"description":"浣滀笟浠诲姟鐢熸垚浣滀笟绁ㄥ嵄瀹虫帾鏂芥祴璇曟暟鎹�2>>0503>>lyz","updated_by":1000,"updated_dt":"2017-05-03 15:42:02","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":1000,"status":"inprg","work_type":"gen,hot,cfd,high,hst,ppl,dig,elec,ray","est_end_time":"2017-05-04 00:00:00","isenable":1,"team":null,"created_dt":"2017-05-03 15:42:02","act_start_time":null,"work_subtask_id":1000000001440,"wf_instance_seq":1,"wf_instance":1000000000000,"monitor_name":null,"wf_current_user":1000,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1494380355894,"operator_id":null,"wf_audit_state":"0","wf_current_node":null,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-01 09:39:01","product_flag":2,"upload_file":0,"df":0,"orgid":808212,"monitor_id":null,"description":"test","updated_by":1000,"updated_dt":"2017-05-10 09:39:15","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":null,"status":"wappr","work_type":"gen","est_end_time":"2017-05-03 09:39:05","isenable":1,"team":null,"created_dt":"2017-05-10 09:39:15","act_start_time":null,"work_subtask_id":1000000001460,"wf_instance_seq":null,"wf_instance":null,"monitor_name":null,"wf_current_user":null,"work_task_id":1000000000280,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]}
     */

    private int successfull;
    private Object messages;
    private ResultDataBean resultData;

    public int getSuccessfull() {
        return successfull;
    }

    public void setSuccessfull(int successfull) {
        this.successfull = successfull;
    }

    public Object getMessages() {
        return messages;
    }

    public void setMessages(Object messages) {
        this.messages = messages;
    }

    public ResultDataBean getResultData() {
        return resultData;
    }

    public void setResultData(ResultDataBean resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * dictvos : null
         * mainvo : [{"headVO":{"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1493794487559,"operator_id":null,"wf_audit_state":"2","wf_current_node":1000000001121,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-03 00:00:00","product_flag":2,"upload_file":null,"df":0,"orgid":808212,"monitor_id":null,"description":"浣滀笟浠诲姟鐢熸垚浣滀笟绁ㄥ嵄瀹虫帾鏂芥祴璇曟暟鎹�1>>0503>>lyz","updated_by":1000,"updated_dt":"2017-05-03 14:50:31","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":1000,"status":"inprg","work_type":"gen,hot,cfd,high,hst,ppl,dig,elec,ray","est_end_time":"2017-05-04 00:00:00","isenable":1,"team":null,"created_dt":"2017-05-03 14:50:31","act_start_time":null,"work_subtask_id":1000000001420,"wf_instance_seq":1,"wf_instance":1000000000000,"monitor_name":null,"wf_current_user":1000,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1493978431931,"operator_id":null,"wf_audit_state":"0","wf_current_node":null,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-05 18:00:26","product_flag":2,"upload_file":0,"df":0,"orgid":808212,"monitor_id":null,"description":"21321","updated_by":1000,"updated_dt":"2017-05-05 18:00:31","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":null,"status":"wappr","work_type":"gen,hot,cfd,high,ppl,dig,hst,ray,elec","est_end_time":"2017-05-06 18:00:29","isenable":1,"team":null,"created_dt":"2017-05-05 18:00:31","act_start_time":null,"work_subtask_id":1000000001422,"wf_instance_seq":null,"wf_instance":null,"monitor_name":null,"wf_current_user":null,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1493797336241,"operator_id":null,"wf_audit_state":"2","wf_current_node":1000000001141,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-03 00:00:00","product_flag":2,"upload_file":null,"df":0,"orgid":808212,"monitor_id":null,"description":"浣滀笟浠诲姟鐢熸垚浣滀笟绁ㄥ嵄瀹虫帾鏂芥祴璇曟暟鎹�2>>0503>>lyz","updated_by":1000,"updated_dt":"2017-05-03 15:42:02","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":1000,"status":"inprg","work_type":"gen,hot,cfd,high,hst,ppl,dig,elec,ray","est_end_time":"2017-05-04 00:00:00","isenable":1,"team":null,"created_dt":"2017-05-03 15:42:02","act_start_time":null,"work_subtask_id":1000000001440,"wf_instance_seq":1,"wf_instance":1000000000000,"monitor_name":null,"wf_current_user":1000,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1494380355894,"operator_id":null,"wf_audit_state":"0","wf_current_node":null,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-01 09:39:01","product_flag":2,"upload_file":0,"df":0,"orgid":808212,"monitor_id":null,"description":"test","updated_by":1000,"updated_dt":"2017-05-10 09:39:15","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":null,"status":"wappr","work_type":"gen","est_end_time":"2017-05-03 09:39:05","isenable":1,"team":null,"created_dt":"2017-05-10 09:39:15","act_start_time":null,"work_subtask_id":1000000001460,"wf_instance_seq":null,"wf_instance":null,"monitor_name":null,"wf_current_user":null,"work_task_id":1000000000280,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]
         */

        private Object dictvos;
        private List<MainvoBean> mainvo;

        public Object getDictvos() {
            return dictvos;
        }

        public void setDictvos(Object dictvos) {
            this.dictvos = dictvos;
        }

        public List<MainvoBean> getMainvo() {
            return mainvo;
        }

        public void setMainvo(List<MainvoBean> mainvo) {
            this.mainvo = mainvo;
        }

        public static class MainvoBean {
            /**
             * headVO : {"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1493794487559,"operator_id":null,"wf_audit_state":"2","wf_current_node":1000000001121,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-03 00:00:00","product_flag":2,"upload_file":null,"df":0,"orgid":808212,"monitor_id":null,"description":"浣滀笟浠诲姟鐢熸垚浣滀笟绁ㄥ嵄瀹虫帾鏂芥祴璇曟暟鎹�1>>0503>>lyz","updated_by":1000,"updated_dt":"2017-05-03 14:50:31","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":1000,"status":"inprg","work_type":"gen,hot,cfd,high,hst,ppl,dig,elec,ray","est_end_time":"2017-05-04 00:00:00","isenable":1,"team":null,"created_dt":"2017-05-03 14:50:31","act_start_time":null,"work_subtask_id":1000000001420,"wf_instance_seq":1,"wf_instance":1000000000000,"monitor_name":null,"wf_current_user":1000,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}}
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

            public static class HeadVOBean {
                /**
                 * RMT_WORK_SUBTASK_M : {"tableName":"rmt_work_subtask","ts":1493794487559,"operator_id":null,"wf_audit_state":"2","wf_current_node":1000000001121,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-03 00:00:00","product_flag":2,"upload_file":null,"df":0,"orgid":808212,"monitor_id":null,"description":"浣滀笟浠诲姟鐢熸垚浣滀笟绁ㄥ嵄瀹虫帾鏂芥祴璇曟暟鎹�1>>0503>>lyz","updated_by":1000,"updated_dt":"2017-05-03 14:50:31","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":1000,"status":"inprg","work_type":"gen,hot,cfd,high,hst,ppl,dig,elec,ray","est_end_time":"2017-05-04 00:00:00","isenable":1,"team":null,"created_dt":"2017-05-03 14:50:31","act_start_time":null,"work_subtask_id":1000000001420,"wf_instance_seq":1,"wf_instance":1000000000000,"monitor_name":null,"wf_current_user":1000,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}
                 */

                private RMTWORKSUBTASKMBean RMT_WORK_SUBTASK_M;

                public RMTWORKSUBTASKMBean getRMT_WORK_SUBTASK_M() {
                    return RMT_WORK_SUBTASK_M;
                }

                public void setRMT_WORK_SUBTASK_M(RMTWORKSUBTASKMBean RMT_WORK_SUBTASK_M) {
                    this.RMT_WORK_SUBTASK_M = RMT_WORK_SUBTASK_M;
                }

                public static class RMTWORKSUBTASKMBean {
                    /**
                     * tableName : rmt_work_subtask
                     * ts : 1493794487559
                     * operator_id : null
                     * wf_audit_state : 2
                     * wf_current_node : 1000000001121
                     * created_by_name : 娴嬭瘯鐢ㄦ埛
                     * ver : 1
                     * est_start_time : 2017-05-03 00:00:00
                     * product_flag : 2
                     * upload_file : null
                     * df : 0
                     * orgid : 808212
                     * monitor_id : null
                     * description : 浣滀笟浠诲姟鐢熸垚浣滀笟绁ㄥ嵄瀹虫帾鏂芥祴璇曟暟鎹�1>>0503>>lyz
                     * updated_by : 1000
                     * updated_dt : 2017-05-03 14:50:31
                     * tenantid : 1
                     * created_by : 1000
                     * operator_name : null
                     * act_end_time : null
                     * wf_create_user : 1000
                     * status : inprg
                     * work_type : gen,hot,cfd,high,hst,ppl,dig,elec,ray
                     * est_end_time : 2017-05-04 00:00:00
                     * isenable : 1
                     * team : null
                     * created_dt : 2017-05-03 14:50:31
                     * act_start_time : null
                     * work_subtask_id : 1000000001420
                     * wf_instance_seq : 1
                     * wf_instance : 1000000000000
                     * monitor_name : null
                     * wf_current_user : 1000
                     * work_task_id : 1000000000261
                     * updated_by_name : 娴嬭瘯鐢ㄦ埛
                     * dataStatus : 0
                     */

                    private String tableName;
                    private long ts;
                    private Object operator_id;
                    private String wf_audit_state;
                    private long wf_current_node;
                    private String created_by_name;
                    private int ver;
                    private String est_start_time;
                    private int product_flag;
                    private Object upload_file;
                    private int df;
                    private int orgid;
                    private Object monitor_id;
                    private String description;
                    private int updated_by;
                    private String updated_dt;
                    private int tenantid;
                    private int created_by;
                    private Object operator_name;
                    private Object act_end_time;
                    private int wf_create_user;
                    private String status;
                    private String work_type;
                    private String est_end_time;
                    private int isenable;
                    private Object team;
                    private String created_dt;
                    private Object act_start_time;
                    private long work_subtask_id;
                    private int wf_instance_seq;
                    private long wf_instance;
                    private Object monitor_name;
                    private int wf_current_user;
                    private long work_task_id;
                    private String updated_by_name;
                    private int dataStatus;

                    public String getTableName() {
                        return tableName;
                    }

                    public void setTableName(String tableName) {
                        this.tableName = tableName;
                    }

                    public long getTs() {
                        return ts;
                    }

                    public void setTs(long ts) {
                        this.ts = ts;
                    }

                    public Object getOperator_id() {
                        return operator_id;
                    }

                    public void setOperator_id(Object operator_id) {
                        this.operator_id = operator_id;
                    }

                    public String getWf_audit_state() {
                        return wf_audit_state;
                    }

                    public void setWf_audit_state(String wf_audit_state) {
                        this.wf_audit_state = wf_audit_state;
                    }

                    public long getWf_current_node() {
                        return wf_current_node;
                    }

                    public void setWf_current_node(long wf_current_node) {
                        this.wf_current_node = wf_current_node;
                    }

                    public String getCreated_by_name() {
                        return created_by_name;
                    }

                    public void setCreated_by_name(String created_by_name) {
                        this.created_by_name = created_by_name;
                    }

                    public int getVer() {
                        return ver;
                    }

                    public void setVer(int ver) {
                        this.ver = ver;
                    }

                    public String getEst_start_time() {
                        return est_start_time;
                    }

                    public void setEst_start_time(String est_start_time) {
                        this.est_start_time = est_start_time;
                    }

                    public int getProduct_flag() {
                        return product_flag;
                    }

                    public void setProduct_flag(int product_flag) {
                        this.product_flag = product_flag;
                    }

                    public Object getUpload_file() {
                        return upload_file;
                    }

                    public void setUpload_file(Object upload_file) {
                        this.upload_file = upload_file;
                    }

                    public int getDf() {
                        return df;
                    }

                    public void setDf(int df) {
                        this.df = df;
                    }

                    public int getOrgid() {
                        return orgid;
                    }

                    public void setOrgid(int orgid) {
                        this.orgid = orgid;
                    }

                    public Object getMonitor_id() {
                        return monitor_id;
                    }

                    public void setMonitor_id(Object monitor_id) {
                        this.monitor_id = monitor_id;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public int getUpdated_by() {
                        return updated_by;
                    }

                    public void setUpdated_by(int updated_by) {
                        this.updated_by = updated_by;
                    }

                    public String getUpdated_dt() {
                        return updated_dt;
                    }

                    public void setUpdated_dt(String updated_dt) {
                        this.updated_dt = updated_dt;
                    }

                    public int getTenantid() {
                        return tenantid;
                    }

                    public void setTenantid(int tenantid) {
                        this.tenantid = tenantid;
                    }

                    public int getCreated_by() {
                        return created_by;
                    }

                    public void setCreated_by(int created_by) {
                        this.created_by = created_by;
                    }

                    public Object getOperator_name() {
                        return operator_name;
                    }

                    public void setOperator_name(Object operator_name) {
                        this.operator_name = operator_name;
                    }

                    public Object getAct_end_time() {
                        return act_end_time;
                    }

                    public void setAct_end_time(Object act_end_time) {
                        this.act_end_time = act_end_time;
                    }

                    public int getWf_create_user() {
                        return wf_create_user;
                    }

                    public void setWf_create_user(int wf_create_user) {
                        this.wf_create_user = wf_create_user;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public String getWork_type() {
                        return work_type;
                    }

                    public void setWork_type(String work_type) {
                        this.work_type = work_type;
                    }

                    public String getEst_end_time() {
                        return est_end_time;
                    }

                    public void setEst_end_time(String est_end_time) {
                        this.est_end_time = est_end_time;
                    }

                    public int getIsenable() {
                        return isenable;
                    }

                    public void setIsenable(int isenable) {
                        this.isenable = isenable;
                    }

                    public Object getTeam() {
                        return team;
                    }

                    public void setTeam(Object team) {
                        this.team = team;
                    }

                    public String getCreated_dt() {
                        return created_dt;
                    }

                    public void setCreated_dt(String created_dt) {
                        this.created_dt = created_dt;
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

                    public int getWf_instance_seq() {
                        return wf_instance_seq;
                    }

                    public void setWf_instance_seq(int wf_instance_seq) {
                        this.wf_instance_seq = wf_instance_seq;
                    }

                    public long getWf_instance() {
                        return wf_instance;
                    }

                    public void setWf_instance(long wf_instance) {
                        this.wf_instance = wf_instance;
                    }

                    public Object getMonitor_name() {
                        return monitor_name;
                    }

                    public void setMonitor_name(Object monitor_name) {
                        this.monitor_name = monitor_name;
                    }

                    public int getWf_current_user() {
                        return wf_current_user;
                    }

                    public void setWf_current_user(int wf_current_user) {
                        this.wf_current_user = wf_current_user;
                    }

                    public long getWork_task_id() {
                        return work_task_id;
                    }

                    public void setWork_task_id(long work_task_id) {
                        this.work_task_id = work_task_id;
                    }

                    public String getUpdated_by_name() {
                        return updated_by_name;
                    }

                    public void setUpdated_by_name(String updated_by_name) {
                        this.updated_by_name = updated_by_name;
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
