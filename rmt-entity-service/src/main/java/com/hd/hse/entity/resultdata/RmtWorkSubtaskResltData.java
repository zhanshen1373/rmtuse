package com.hd.hse.entity.resultdata;

import com.hd.hse.entity.conf.RmtConfApprAuth;
import com.hd.hse.entity.conf.RmtConfElecEqpt;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.entity.dict.RmtDict;
import com.hd.hse.entity.workorder.RmtWorkPermit;

import java.util.List;

/**
 * 措施确认，分项任务数据
 * created by yangning on 2017/5/19 10:10.
 */

public class RmtWorkSubtaskResltData {

    /**
     * headvo : {"RMT_CONF_M_INTFC_M":{"tableName":"rmt_conf_m_intfc","work_subtask_id":1000000001700,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2017-04-14 10:05:21","updated_by":1000,"updated_dt":"2017-04-14 10:05:21","df":0,"tenantid":1,"ts":1492135521400,"m_intfc_id":1000000000200,"permit_info_id":1000000000040,"created_by_name":"测试用户","updated_by_name":"测试用户","isenable":1,"product_flag":2,"orgid":808212,"tab_type":1,"tab_name":null,"func_code":"approve","func_name":"现场核查","func_order":1,"work_type":"hot","savebtn":null,"itemize":null,"batch":null,"apply_intfc":null,"combobox":null,"checkbox_dsc":null,"ignore_meas":null,"risk_type":"gen","style":null}}
     * bodyvos : {"RMT_VIR_RISK_M":[{"tableName":null,"columnValues":null,"dataStatus":0,"risk_type":"gen","work_permit_id":1000000001221,"work_subtask_id":1000000001700,"work_task_id":null,"m_intfc_id":1000000000200,"voList":[{"tableName":"rmt_work_permit_meas","meas_id":1000000000581,"appr_node_name":null,"op_status":null,"appr_node_id":null,"meas_description":"0518移动端测试数据_0.28蒸汽_工艺措施1","approver_id":null,"meas_type":"tech_meas","work_permit_meas_id":1000000000185,"work_permit_id":1000000001221,"approver_name":null,"work_subtask_id":1000000001700,"dataStatus":0},{"tableName":"rmt_work_permit_meas","meas_id":1000000000583,"appr_node_name":null,"op_status":null,"appr_node_id":null,"meas_description":"0518移动端测试数据_0.28蒸汽_作业措施1","approver_id":null,"meas_type":"work_meas","work_permit_meas_id":1000000000186,"work_permit_id":1000000001221,"approver_name":null,"work_subtask_id":1000000001700,"dataStatus":0},{"tableName":"rmt_work_permit_meas","meas_id":1000000000585,"appr_node_name":null,"op_status":null,"appr_node_id":null,"meas_description":"0518移动端测试数据_动火作业_工艺措施1","approver_id":null,"meas_type":"tech_meas","work_permit_meas_id":1000000000187,"work_permit_id":1000000001221,"approver_name":null,"work_subtask_id":1000000001700,"dataStatus":0},{"tableName":"rmt_work_permit_meas","meas_id":1000000000587,"appr_node_name":null,"op_status":null,"appr_node_id":null,"meas_description":"0518移动端测试数据_动火作业_作业措施1","approver_id":null,"meas_type":"work_meas","work_permit_meas_id":1000000000188,"work_permit_id":1000000001221,"approver_name":null,"work_subtask_id":1000000001700,"dataStatus":0},{"tableName":"rmt_work_permit_meas","meas_id":1000000000589,"appr_node_name":null,"op_status":null,"appr_node_id":null,"meas_description":"0518移动端测试数据_动火作业_设备0518_工艺措施1","approver_id":null,"meas_type":"tech_meas","work_permit_meas_id":1000000000189,"work_permit_id":1000000001221,"approver_name":null,"work_subtask_id":1000000001700,"dataStatus":0},{"tableName":"rmt_work_permit_meas","meas_id":1000000000591,"appr_node_name":null,"op_status":null,"appr_node_id":null,"meas_description":"0518移动端测试数据_动火作业_设备0518_工艺风险2","approver_id":null,"meas_type":"tech_meas","work_permit_meas_id":1000000000190,"work_permit_id":1000000001221,"approver_name":null,"work_subtask_id":1000000001700,"dataStatus":0}]}],"RMT_CONF_APPR_AUTH_M":[{"tableName":"rmt_conf_appr_auth","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2017-04-17 09:38:41","updated_by":1000,"updated_dt":"2017-05-18 15:14:10","df":0,"tenantid":1,"ts":1495091650560,"appr_auth_id":1000000000102,"appr_plan_id":1000000000123,"m_intfc_id":1000000000200,"created_by_name":"测试用户","updated_by_name":"系统管理员","isenable":1,"product_flag":2,"orgid":808212,"work_type":"hot","work_level":"hot01","related_unit_id":52525275237828,"related_unit_name":"测试单位","status":"appr","appr_node_id":1000000000265,"appr_node_name":"临时用电作业申请人#作业申请人","persongroup_id":"100","persongroup_name":"稀里糊涂","appr_order":2,"display_order":1,"final_check":1,"multiple":1,"handwrite":1,"contractor":1,"mandatory":1,"audit_qual":1,"cmt_info":"25","alias":null}]}
     * dictvos : null
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
         * RMT_CONF_M_INTFC_M : {"tableName":"rmt_conf_m_intfc","work_subtask_id":1000000001700,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2017-04-14 10:05:21","updated_by":1000,"updated_dt":"2017-04-14 10:05:21","df":0,"tenantid":1,"ts":1492135521400,"m_intfc_id":1000000000200,"permit_info_id":1000000000040,"created_by_name":"测试用户","updated_by_name":"测试用户","isenable":1,"product_flag":2,"orgid":808212,"tab_type":1,"tab_name":null,"func_code":"approve","func_name":"现场核查","func_order":1,"work_type":"hot","savebtn":null,"itemize":null,"batch":null,"apply_intfc":null,"combobox":null,"checkbox_dsc":null,"ignore_meas":null,"risk_type":"gen","style":null}
         */

        private RmtConfMIntfc RMT_CONF_M_INTFC_M;

        public RmtConfMIntfc getRMT_CONF_M_INTFC_M() {
            return RMT_CONF_M_INTFC_M;
        }

        public void setRMT_CONF_M_INTFC_M(RmtConfMIntfc RMT_CONF_M_INTFC_M) {
            this.RMT_CONF_M_INTFC_M = RMT_CONF_M_INTFC_M;
        }


    }

    public static class BodyvosBean {
        private List<RmtVirRiskM> RMT_VIR_RISK_M;
        private List<RmtConfElecEqpt> RMT_CONF_ELEC_EQPT_M;
        private List<RmtConfApprAuth> RMT_CONF_APPR_AUTH_M;
        private List<RmtWorkPermit> RMT_WORK_PERMIT_M;
        private List<RmtWorkGasDetectReslt> RMT_WORK_GAS_DETECT_M;

        public List<RmtWorkGasDetectReslt> getRMT_WORK_GAS_DETECT_M() {
            return RMT_WORK_GAS_DETECT_M;
        }

        public void setRMT_WORK_GAS_DETECT_M(List<RmtWorkGasDetectReslt> RMT_WORK_GAS_DETECT_M) {
            this.RMT_WORK_GAS_DETECT_M = RMT_WORK_GAS_DETECT_M;
        }

        public List<RmtConfElecEqpt> getRMT_CONF_ELEC_EQPT_M() {
            return RMT_CONF_ELEC_EQPT_M;
        }

        public void setRMT_CONF_ELEC_EQPT_M(List<RmtConfElecEqpt> RMT_CONF_ELEC_EQPT_M) {
            this.RMT_CONF_ELEC_EQPT_M = RMT_CONF_ELEC_EQPT_M;
        }
        public List<RmtWorkPermit> getRMT_WORK_PERMIT_M() {
            return RMT_WORK_PERMIT_M;
        }

        public void setRMT_WORK_PERMIT_M(List<RmtWorkPermit> RMT_WORK_PERMIT_M) {
            this.RMT_WORK_PERMIT_M = RMT_WORK_PERMIT_M;
        }

        public List<RmtVirRiskM> getRMT_VIR_RISK_M() {
            return RMT_VIR_RISK_M;
        }

        public void setRMT_VIR_RISK_M(List<RmtVirRiskM> RMT_VIR_RISK_M) {
            this.RMT_VIR_RISK_M = RMT_VIR_RISK_M;
        }

        public List<RmtConfApprAuth> getRMT_CONF_APPR_AUTH_M() {
            return RMT_CONF_APPR_AUTH_M;
        }

        public void setRMT_CONF_APPR_AUTH_M(List<RmtConfApprAuth> RMT_CONF_APPR_AUTH_M) {
            this.RMT_CONF_APPR_AUTH_M = RMT_CONF_APPR_AUTH_M;
        }
    }

    public static  class DictvosBean{
        private List<RmtDict> RMT_STOP_REASON_M;

        public List<RmtDict> getRMT_STOP_REASON_M() {
            return RMT_STOP_REASON_M;
        }

        public void setRMT_STOP_REASON_M(List<RmtDict> RMT_STOP_REASON_M) {
            this.RMT_STOP_REASON_M = RMT_STOP_REASON_M;
        }
    }
}
