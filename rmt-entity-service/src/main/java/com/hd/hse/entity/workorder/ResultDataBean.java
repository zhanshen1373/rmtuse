package com.hd.hse.entity.workorder;

import com.hd.hse.entity.resultdata.RmtWorkGasDetectReslt;

import java.util.List;

/**
 * created by yangning on 2017/5/11 17:34.
 */


public class ResultDataBean<T>  {
    /**
     * dictvos : null
     * mainvo : [{"headVO":{"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1493794487559,"operator_id":null,"wf_audit_state":"2","wf_current_node":1000000001121,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-03 00:00:00","product_flag":2,"upload_file":null,"df":0,"orgid":808212,"monitor_id":null,"description":"浣滀笟浠诲姟鐢熸垚浣滀笟绁ㄥ嵄瀹虫帾鏂芥祴璇曟暟鎹�1>>0503>>lyz","updated_by":1000,"updated_dt":"2017-05-03 14:50:31","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":1000,"status":"inprg","work_type":"gen,hot,cfd,high,hst,ppl,dig,elec,ray","est_end_time":"2017-05-04 00:00:00","isenable":1,"team":null,"created_dt":"2017-05-03 14:50:31","act_start_time":null,"work_subtask_id":1000000001420,"wf_instance_seq":1,"wf_instance":1000000000000,"monitor_name":null,"wf_current_user":1000,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1493978431931,"operator_id":null,"wf_audit_state":"0","wf_current_node":null,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-05 18:00:26","product_flag":2,"upload_file":0,"df":0,"orgid":808212,"monitor_id":null,"description":"21321","updated_by":1000,"updated_dt":"2017-05-05 18:00:31","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":null,"status":"wappr","work_type":"gen,hot,cfd,high,ppl,dig,hst,ray,elec","est_end_time":"2017-05-06 18:00:29","isenable":1,"team":null,"created_dt":"2017-05-05 18:00:31","act_start_time":null,"work_subtask_id":1000000001422,"wf_instance_seq":null,"wf_instance":null,"monitor_name":null,"wf_current_user":null,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1493797336241,"operator_id":null,"wf_audit_state":"2","wf_current_node":1000000001141,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-03 00:00:00","product_flag":2,"upload_file":null,"df":0,"orgid":808212,"monitor_id":null,"description":"浣滀笟浠诲姟鐢熸垚浣滀笟绁ㄥ嵄瀹虫帾鏂芥祴璇曟暟鎹�2>>0503>>lyz","updated_by":1000,"updated_dt":"2017-05-03 15:42:02","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":1000,"status":"inprg","work_type":"gen,hot,cfd,high,hst,ppl,dig,elec,ray","est_end_time":"2017-05-04 00:00:00","isenable":1,"team":null,"created_dt":"2017-05-03 15:42:02","act_start_time":null,"work_subtask_id":1000000001440,"wf_instance_seq":1,"wf_instance":1000000000000,"monitor_name":null,"wf_current_user":1000,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1494380355894,"operator_id":null,"wf_audit_state":"0","wf_current_node":null,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-01 09:39:01","product_flag":2,"upload_file":0,"df":0,"orgid":808212,"monitor_id":null,"description":"test","updated_by":1000,"updated_dt":"2017-05-10 09:39:15","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":null,"status":"wappr","work_type":"gen","est_end_time":"2017-05-03 09:39:05","isenable":1,"team":null,"created_dt":"2017-05-10 09:39:15","act_start_time":null,"work_subtask_id":1000000001460,"wf_instance_seq":null,"wf_instance":null,"monitor_name":null,"wf_current_user":null,"work_task_id":1000000000280,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]
     */

    private Object dictvos;
    private List<MainvoBean<T>> mainvo;

    public Object getDictvos() {
        return dictvos;
    }

    public void setDictvos(Object dictvos) {
        this.dictvos = dictvos;
    }

    public List<MainvoBean<T>> getMainvo() {
        return mainvo;
    }

    public void setMainvo(List<MainvoBean<T>> mainvo) {
        this.mainvo = mainvo;
    }

    public static class MainvoBean<T> {
        /**
         * headVO : {"RMT_WORK_SUBTASK_M":{"tableName":"rmt_work_subtask","ts":1493794487559,"operator_id":null,"wf_audit_state":"2","wf_current_node":1000000001121,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-03 00:00:00","product_flag":2,"upload_file":null,"df":0,"orgid":808212,"monitor_id":null,"description":"浣滀笟浠诲姟鐢熸垚浣滀笟绁ㄥ嵄瀹虫帾鏂芥祴璇曟暟鎹�1>>0503>>lyz","updated_by":1000,"updated_dt":"2017-05-03 14:50:31","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":1000,"status":"inprg","work_type":"gen,hot,cfd,high,hst,ppl,dig,elec,ray","est_end_time":"2017-05-04 00:00:00","isenable":1,"team":null,"created_dt":"2017-05-03 14:50:31","act_start_time":null,"work_subtask_id":1000000001420,"wf_instance_seq":1,"wf_instance":1000000000000,"monitor_name":null,"wf_current_user":1000,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}}
         * attachDataMap : null
         * bodyVOs : null
         * attachDataVOs : null
         */

        private HeadVOBean<T> headVO;
        private Object attachDataMap;
        private BodyBean bodyVOs;
        private Object attachDataVOs;

        public HeadVOBean<T> getHeadVO() {
            return headVO;
        }

        public void setHeadVO(HeadVOBean<T> headVO) {
            this.headVO = headVO;
        }

        public Object getAttachDataMap() {
            return attachDataMap;
        }

        public void setAttachDataMap(Object attachDataMap) {
            this.attachDataMap = attachDataMap;
        }

        public BodyBean getBodyVOs() {
            return bodyVOs;
        }

        public void setBodyVOs(BodyBean bodyVOs) {
            this.bodyVOs = bodyVOs;
        }

        public Object getAttachDataVOs() {
            return attachDataVOs;
        }

        public void setAttachDataVOs(Object attachDataVOs) {
            this.attachDataVOs = attachDataVOs;
        }

        public static class HeadVOBean <T>{
            /**
             * RMT_WORK_SUBTASK_M : {"tableName":"rmt_work_subtask","ts":1493794487559,"operator_id":null,"wf_audit_state":"2","wf_current_node":1000000001121,"created_by_name":"娴嬭瘯鐢ㄦ埛","ver":1,"est_start_time":"2017-05-03 00:00:00","product_flag":2,"upload_file":null,"df":0,"orgid":808212,"monitor_id":null,"description":"浣滀笟浠诲姟鐢熸垚浣滀笟绁ㄥ嵄瀹虫帾鏂芥祴璇曟暟鎹�1>>0503>>lyz","updated_by":1000,"updated_dt":"2017-05-03 14:50:31","tenantid":1,"created_by":1000,"operator_name":null,"act_end_time":null,"wf_create_user":1000,"status":"inprg","work_type":"gen,hot,cfd,high,hst,ppl,dig,elec,ray","est_end_time":"2017-05-04 00:00:00","isenable":1,"team":null,"created_dt":"2017-05-03 14:50:31","act_start_time":null,"work_subtask_id":1000000001420,"wf_instance_seq":1,"wf_instance":1000000000000,"monitor_name":null,"wf_current_user":1000,"work_task_id":1000000000261,"updated_by_name":"娴嬭瘯鐢ㄦ埛","dataStatus":0}
             */

            private T RMT_WORK_SUBTASK_M;

            public T getRMT_WORK_SUBTASK_M() {
                return RMT_WORK_SUBTASK_M;
            }

            public void setRMT_WORK_SUBTASK_M(T RMT_WORK_SUBTASK_M) {
                this.RMT_WORK_SUBTASK_M = RMT_WORK_SUBTASK_M;
            }


        }
        public static class BodyBean{

            private List<RMTWORKGASDETECTMBeanX> RMT_WORK_GAS_DETECT_M;

            public List<RMTWORKGASDETECTMBeanX> getRMT_WORK_GAS_DETECT_M() {
                return RMT_WORK_GAS_DETECT_M;
            }

            public void setRMT_WORK_GAS_DETECT_M(List<RMTWORKGASDETECTMBeanX> RMT_WORK_GAS_DETECT_M) {
                this.RMT_WORK_GAS_DETECT_M = RMT_WORK_GAS_DETECT_M;
            }

            public static class RMTWORKGASDETECTMBeanX {
                /**
                 * headVO : {"RMT_WORK_GAS_DETECT_M":{"tableName":"rmt_work_gas_detect","analyzer_name":null,"gas_detect_id":1000000001960,"detect_site":"xvbb","qualified":0,"detect_time":"2017-12-20 17:52:40","work_subtask_id":1000000003760,"dataStatus":0}}
                 * attachDataMap : null
                 * bodyVOs : {"RMT_WORK_GAS_DETECT_LINE_M":[{"headVO":{"RMT_WORK_GAS_DETECT_LINE_M":{"tableName":"rmt_work_gas_detect_line","gas_value":"28.00","gas_name":"氧气浓度%","gas_detect_id":1000000001960,"gas_detect_line_id":1000000001560,"gas_type_sub_name":"氧气%","work_subtask_id":1000000003760,"dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_GAS_DETECT_LINE_M":{"tableName":"rmt_work_gas_detect_line","gas_value":"45.00","gas_name":"可燃气体浓度LEL%","gas_detect_id":1000000001960,"gas_detect_line_id":1000000001561,"gas_type_sub_name":"可燃气%","work_subtask_id":1000000003760,"dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_GAS_DETECT_LINE_M":{"tableName":"rmt_work_gas_detect_line","gas_value":"88.00","gas_name":"有毒气体浓度%","gas_detect_id":1000000001960,"gas_detect_line_id":1000000001562,"gas_type_sub_name":"硫化氢mg/m3","work_subtask_id":1000000003760,"dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]}
                 * attachDataVOs : null
                 */

                private HeadVOBean headVO;
                private Object attachDataMap;
                private BodyVOsBean bodyVOs;
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

                public static class HeadVOBean {
                    /**
                     * RMT_WORK_GAS_DETECT_M : {"tableName":"rmt_work_gas_detect","analyzer_name":null,"gas_detect_id":1000000001960,"detect_site":"xvbb","qualified":0,"detect_time":"2017-12-20 17:52:40","work_subtask_id":1000000003760,"dataStatus":0}
                     */

                    private RmtWorkGasDetectReslt RMT_WORK_GAS_DETECT_M;

                    public RmtWorkGasDetectReslt getRMT_WORK_GAS_DETECT_M() {
                        return RMT_WORK_GAS_DETECT_M;
                    }

                    public void setRMT_WORK_GAS_DETECT_M(RmtWorkGasDetectReslt RMT_WORK_GAS_DETECT_M) {
                        this.RMT_WORK_GAS_DETECT_M = RMT_WORK_GAS_DETECT_M;
                    }


                }

                public static class BodyVOsBean {
                    private List<RMTWORKGASDETECTLINEMBeanX> RMT_WORK_GAS_DETECT_LINE_M;

                    public List<RMTWORKGASDETECTLINEMBeanX> getRMT_WORK_GAS_DETECT_LINE_M() {
                        return RMT_WORK_GAS_DETECT_LINE_M;
                    }

                    public void setRMT_WORK_GAS_DETECT_LINE_M(List<RMTWORKGASDETECTLINEMBeanX> RMT_WORK_GAS_DETECT_LINE_M) {
                        this.RMT_WORK_GAS_DETECT_LINE_M = RMT_WORK_GAS_DETECT_LINE_M;
                    }

                    public static class RMTWORKGASDETECTLINEMBeanX {
                        /**
                         * headVO : {"RMT_WORK_GAS_DETECT_LINE_M":{"tableName":"rmt_work_gas_detect_line","gas_value":"28.00","gas_name":"氧气浓度%","gas_detect_id":1000000001960,"gas_detect_line_id":1000000001560,"gas_type_sub_name":"氧气%","work_subtask_id":1000000003760,"dataStatus":0}}
                         * attachDataMap : null
                         * bodyVOs : null
                         * attachDataVOs : null
                         */

                        private HeadVOBeanX headVO;
                        private Object attachDataMap;
                        private Object bodyVOs;
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

                        public static class HeadVOBeanX {
                            /**
                             * RMT_WORK_GAS_DETECT_LINE_M : {"tableName":"rmt_work_gas_detect_line","gas_value":"28.00","gas_name":"氧气浓度%","gas_detect_id":1000000001960,"gas_detect_line_id":1000000001560,"gas_type_sub_name":"氧气%","work_subtask_id":1000000003760,"dataStatus":0}
                             */

                            private RmtWorkGasDetectReslt.VoListBean RMT_WORK_GAS_DETECT_LINE_M;

                            public RmtWorkGasDetectReslt.VoListBean getRMT_WORK_GAS_DETECT_LINE_M() {
                                return RMT_WORK_GAS_DETECT_LINE_M;
                            }

                            public void setRMT_WORK_GAS_DETECT_LINE_M(RmtWorkGasDetectReslt.VoListBean RMT_WORK_GAS_DETECT_LINE_M) {
                                this.RMT_WORK_GAS_DETECT_LINE_M = RMT_WORK_GAS_DETECT_LINE_M;
                            }


                        }
                    }
                }
            }
        }
    }

}
