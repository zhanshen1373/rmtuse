package com.hd.hse.entity.resultdata;

import com.hd.hse.entity.qstn.RmtQstnList;

import java.util.List;

/**
 * created by yangning on 2017/7/3 16:45.
 * 清单清单列表
 */

public class RmtQstnListResltData {

    /**
     * dictvos : null
     * mainvo : [{"headVO":{"RMT_WORK_QSTN_LIST_M":{"tableName":"rmt_work_qstn_list","qstn_unit_name":"we","ts":1495086885904,"violator_id":null,"owner_name":"李四","qstn_unit_id":null,"created_by_name":"测试用户","ver":1,"inspector_name":"张三","product_flag":2,"df":0,"orgid":808212,"description":null,"updated_by":1000,"updated_dt":"2017-05-02 18:00:33","tenantid":1,"inspect_unit_name":"检查站","created_by":1000,"site":"一厂","inspect_unit_id":null,"pic":null,"inspector_id":null,"isenable":1,"created_dt":"2017-05-02 17:08:05","work_subtask_id":1,"inspect_time":null,"qstn_type":null,"owner_id":null,"tenant_name":"海顿","qstn_list_id":1000000000040,"updated_by_name":"测试用户","violator_name":"张三","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_QSTN_LIST_M":{"tableName":"rmt_work_qstn_list","qstn_unit_name":"we","ts":1495792096954,"violator_id":null,"owner_name":"李四","qstn_unit_id":null,"created_by_name":"测试用户","ver":1,"inspector_name":"张三","product_flag":2,"df":0,"orgid":808212,"description":null,"updated_by":1000,"updated_dt":"2017-05-02 18:00:33","tenantid":1,"inspect_unit_name":"检查站","created_by":1000,"site":"一厂","inspect_unit_id":null,"pic":null,"inspector_id":null,"isenable":1,"created_dt":"2017-05-02 17:08:05","work_subtask_id":1,"inspect_time":null,"qstn_type":null,"owner_id":null,"tenant_name":"海顿","qstn_list_id":1000000000100,"updated_by_name":"测试用户","violator_name":"张三","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_QSTN_LIST_M":{"tableName":"rmt_work_qstn_list","qstn_unit_name":"HD","ts":1499054480912,"violator_id":126655265,"owner_name":"现场负责人","qstn_unit_id":126655265,"created_by_name":"测试用户","ver":1,"inspector_name":null,"product_flag":2,"df":0,"orgid":1,"description":"测试","updated_by":1000,"updated_dt":"2017-07-03 12:01:20","tenantid":1,"inspect_unit_name":null,"created_by":1000,"site":"1","inspect_unit_id":null,"pic":null,"inspector_id":null,"isenable":1,"created_dt":"2017-07-03 12:01:20","work_subtask_id":126655265,"inspect_time":null,"qstn_type":null,"owner_id":126655265,"tenant_name":"海顿","qstn_list_id":1000000000821,"updated_by_name":"系统管理员","violator_name":"违章人员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]
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

        public static class HeadVOBean {

            private RmtQstnList RMT_WORK_QSTN_LIST_M;

            public RmtQstnList getRMT_WORK_QSTN_LIST_M() {
                return RMT_WORK_QSTN_LIST_M;
            }

            public void setRMT_WORK_QSTN_LIST_M(RmtQstnList RMT_WORK_QSTN_LIST_M) {
                this.RMT_WORK_QSTN_LIST_M = RMT_WORK_QSTN_LIST_M;
            }


        }
    }
}
