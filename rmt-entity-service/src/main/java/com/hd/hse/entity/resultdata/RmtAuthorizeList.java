package com.hd.hse.entity.resultdata;

import com.hd.hse.common.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public class RmtAuthorizeList implements Serializable {

    /**
     * dictvos : null
     * mainvo : [{"headVO":{"RMT_WORK_GRANT_M":{"tableName":"rmt_work_grant","endtime":"2020-01-16","created_by":1000,"starttime":"2020-01-16","grt_num":2,"grt_node":"on_duty_moni","created_by_name":"系统管理员","grt_id":1000000000001,"created_dt":"2020-01-16 09:41:16","authid":1000,"updated_by":1000,"updated_dt":"2020-01-16 09:41:27","tenantid":1,"auth":"系统管理员","updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_GRANT_ITEM_M":[{"headVO":{"RMT_WORK_GRANT_ITEM_M":{"tableName":"rmt_work_grant_item","created_by":1000,"grt_item_time":null,"status":"appr","be_authed":"测试人员1","rec_time":null,"grt_item_id":1000000000020,"be_authedid":1000000000000,"updated_dt":"2020-01-17 17:03:05","created_by_name":"系统管理员","tenantid":1,"created_dt":"2020-01-17 17:02:59","grt_id":1000000000001,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null}]
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

    public static class MainvoBean extends SuperEntity {
        /**
         * headVO : {"RMT_WORK_GRANT_M":{"tableName":"rmt_work_grant","endtime":"2020-01-16","created_by":1000,"starttime":"2020-01-16","grt_num":2,"grt_node":"on_duty_moni","created_by_name":"系统管理员","grt_id":1000000000001,"created_dt":"2020-01-16 09:41:16","authid":1000,"updated_by":1000,"updated_dt":"2020-01-16 09:41:27","tenantid":1,"auth":"系统管理员","updated_by_name":"系统管理员","dataStatus":0}}
         * attachDataMap : null
         * bodyVOs : {"RMT_WORK_GRANT_ITEM_M":[{"headVO":{"RMT_WORK_GRANT_ITEM_M":{"tableName":"rmt_work_grant_item","created_by":1000,"grt_item_time":null,"status":"appr","be_authed":"测试人员1","rec_time":null,"grt_item_id":1000000000020,"be_authedid":1000000000000,"updated_dt":"2020-01-17 17:03:05","created_by_name":"系统管理员","tenantid":1,"created_dt":"2020-01-17 17:02:59","grt_id":1000000000001,"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]}
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

        public static class HeadVOBean implements Serializable {
            /**
             * RMT_WORK_GRANT_M : {"tableName":"rmt_work_grant","endtime":"2020-01-16","created_by":1000,"starttime":"2020-01-16","grt_num":2,"grt_node":"on_duty_moni","created_by_name":"系统管理员","grt_id":1000000000001,"created_dt":"2020-01-16 09:41:16","authid":1000,"updated_by":1000,"updated_dt":"2020-01-16 09:41:27","tenantid":1,"auth":"系统管理员","updated_by_name":"系统管理员","dataStatus":0}
             */

            private RMTWORKGRANTMBean RMT_WORK_GRANT_M;

            public RMTWORKGRANTMBean getRMT_WORK_GRANT_M() {
                return RMT_WORK_GRANT_M;
            }

            public void setRMT_WORK_GRANT_M(RMTWORKGRANTMBean RMT_WORK_GRANT_M) {
                this.RMT_WORK_GRANT_M = RMT_WORK_GRANT_M;
            }

            public static class RMTWORKGRANTMBean implements Serializable {
                /**
                 * tableName : rmt_work_grant
                 * endtime : 2020-01-16
                 * created_by : 1000
                 * starttime : 2020-01-16
                 * grt_num : 2
                 * grt_node : on_duty_moni
                 * created_by_name : 系统管理员
                 * grt_id : 1000000000001
                 * created_dt : 2020-01-16 09:41:16
                 * authid : 1000
                 * updated_by : 1000
                 * updated_dt : 2020-01-16 09:41:27
                 * tenantid : 1
                 * auth : 系统管理员
                 * updated_by_name : 系统管理员
                 * dataStatus : 0
                 */

                private String tableName;
                private String endtime;
                private long created_by;
                private String starttime;
                private int grt_num;
                private String grt_node;
                private String created_by_name;
                private long grt_id;
                private String created_dt;
                private long authid;
                private long updated_by;
                private String updated_dt;
                private long tenantid;
                private String auth;
                private String updated_by_name;
                private int dataStatus;

                public String getTableName() {
                    return tableName;
                }

                public void setTableName(String tableName) {
                    this.tableName = tableName;
                }

                public String getEndtime() {
                    return endtime;
                }

                public void setEndtime(String endtime) {
                    this.endtime = endtime;
                }

                public long getCreated_by() {
                    return created_by;
                }

                public void setCreated_by(long created_by) {
                    this.created_by = created_by;
                }

                public String getStarttime() {
                    return starttime;
                }

                public void setStarttime(String starttime) {
                    this.starttime = starttime;
                }

                public int getGrt_num() {
                    return grt_num;
                }

                public void setGrt_num(int grt_num) {
                    this.grt_num = grt_num;
                }

                public String getGrt_node() {
                    return grt_node;
                }

                public void setGrt_node(String grt_node) {
                    this.grt_node = grt_node;
                }

                public String getCreated_by_name() {
                    return created_by_name;
                }

                public void setCreated_by_name(String created_by_name) {
                    this.created_by_name = created_by_name;
                }

                public long getGrt_id() {
                    return grt_id;
                }

                public void setGrt_id(long grt_id) {
                    this.grt_id = grt_id;
                }

                public String getCreated_dt() {
                    return created_dt;
                }

                public void setCreated_dt(String created_dt) {
                    this.created_dt = created_dt;
                }

                public long getAuthid() {
                    return authid;
                }

                public void setAuthid(long authid) {
                    this.authid = authid;
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

                public long getTenantid() {
                    return tenantid;
                }

                public void setTenantid(long tenantid) {
                    this.tenantid = tenantid;
                }

                public String getAuth() {
                    return auth;
                }

                public void setAuth(String auth) {
                    this.auth = auth;
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

        public static class BodyVOsBean implements Serializable {
            private List<RMTWORKGRANTITEMMBeanX> RMT_WORK_GRANT_ITEM_M;

            public List<RMTWORKGRANTITEMMBeanX> getRMT_WORK_GRANT_ITEM_M() {
                return RMT_WORK_GRANT_ITEM_M;
            }

            public void setRMT_WORK_GRANT_ITEM_M(List<RMTWORKGRANTITEMMBeanX> RMT_WORK_GRANT_ITEM_M) {
                this.RMT_WORK_GRANT_ITEM_M = RMT_WORK_GRANT_ITEM_M;
            }

            public static class RMTWORKGRANTITEMMBeanX implements Serializable {
                /**
                 * headVO : {"RMT_WORK_GRANT_ITEM_M":{"tableName":"rmt_work_grant_item","created_by":1000,"grt_item_time":null,"status":"appr","be_authed":"测试人员1","rec_time":null,"grt_item_id":1000000000020,"be_authedid":1000000000000,"updated_dt":"2020-01-17 17:03:05","created_by_name":"系统管理员","tenantid":1,"created_dt":"2020-01-17 17:02:59","grt_id":1000000000001,"updated_by_name":"系统管理员","dataStatus":0}}
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

                public static class HeadVOBeanX implements Serializable {
                    /**
                     * RMT_WORK_GRANT_ITEM_M : {"tableName":"rmt_work_grant_item","created_by":1000,"grt_item_time":null,"status":"appr","be_authed":"测试人员1","rec_time":null,"grt_item_id":1000000000020,"be_authedid":1000000000000,"updated_dt":"2020-01-17 17:03:05","created_by_name":"系统管理员","tenantid":1,"created_dt":"2020-01-17 17:02:59","grt_id":1000000000001,"updated_by_name":"系统管理员","dataStatus":0}
                     */

                    private RMTWORKGRANTITEMMBean RMT_WORK_GRANT_ITEM_M;

                    public RMTWORKGRANTITEMMBean getRMT_WORK_GRANT_ITEM_M() {
                        return RMT_WORK_GRANT_ITEM_M;
                    }

                    public void setRMT_WORK_GRANT_ITEM_M(RMTWORKGRANTITEMMBean RMT_WORK_GRANT_ITEM_M) {
                        this.RMT_WORK_GRANT_ITEM_M = RMT_WORK_GRANT_ITEM_M;
                    }

                    public static class RMTWORKGRANTITEMMBean implements Serializable {
                        /**
                         * tableName : rmt_work_grant_item
                         * created_by : 1000
                         * grt_item_time : null
                         * status : appr
                         * be_authed : 测试人员1
                         * rec_time : null
                         * grt_item_id : 1000000000020
                         * be_authedid : 1000000000000
                         * updated_dt : 2020-01-17 17:03:05
                         * created_by_name : 系统管理员
                         * tenantid : 1
                         * created_dt : 2020-01-17 17:02:59
                         * grt_id : 1000000000001
                         * updated_by_name : 系统管理员
                         * dataStatus : 0
                         */

                        private String tableName;
                        private long created_by;
                        private Object grt_item_time;
                        private String status;
                        private String be_authed;
                        private Object rec_time;
                        private long grt_item_id;
                        private long be_authedid;
                        private String updated_dt;
                        private String created_by_name;
                        private long tenantid;
                        private String created_dt;
                        private long grt_id;
                        private String updated_by_name;
                        private int dataStatus;

                        public String getTableName() {
                            return tableName;
                        }

                        public void setTableName(String tableName) {
                            this.tableName = tableName;
                        }

                        public long getCreated_by() {
                            return created_by;
                        }

                        public void setCreated_by(long created_by) {
                            this.created_by = created_by;
                        }

                        public Object getGrt_item_time() {
                            return grt_item_time;
                        }

                        public void setGrt_item_time(Object grt_item_time) {
                            this.grt_item_time = grt_item_time;
                        }

                        public String getStatus() {
                            return status;
                        }

                        public void setStatus(String status) {
                            this.status = status;
                        }

                        public String getBe_authed() {
                            return be_authed;
                        }

                        public void setBe_authed(String be_authed) {
                            this.be_authed = be_authed;
                        }

                        public Object getRec_time() {
                            return rec_time;
                        }

                        public void setRec_time(Object rec_time) {
                            this.rec_time = rec_time;
                        }

                        public long getGrt_item_id() {
                            return grt_item_id;
                        }

                        public void setGrt_item_id(long grt_item_id) {
                            this.grt_item_id = grt_item_id;
                        }

                        public long getBe_authedid() {
                            return be_authedid;
                        }

                        public void setBe_authedid(long be_authedid) {
                            this.be_authedid = be_authedid;
                        }

                        public String getUpdated_dt() {
                            return updated_dt;
                        }

                        public void setUpdated_dt(String updated_dt) {
                            this.updated_dt = updated_dt;
                        }

                        public String getCreated_by_name() {
                            return created_by_name;
                        }

                        public void setCreated_by_name(String created_by_name) {
                            this.created_by_name = created_by_name;
                        }

                        public long getTenantid() {
                            return tenantid;
                        }

                        public void setTenantid(long tenantid) {
                            this.tenantid = tenantid;
                        }

                        public String getCreated_dt() {
                            return created_dt;
                        }

                        public void setCreated_dt(String created_dt) {
                            this.created_dt = created_dt;
                        }

                        public long getGrt_id() {
                            return grt_id;
                        }

                        public void setGrt_id(long grt_id) {
                            this.grt_id = grt_id;
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
}
