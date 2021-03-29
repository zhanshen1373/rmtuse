package com.hd.hse.entity.resultdata;

import java.util.List;

public class RmtAuthorizeTsDetailMessage {


    /**
     * dictvos : {"rmt_work_grant_itemstatus":[{"tableName":null,"columnValues":null,"dataStatus":0,"code":"appr","name":"审批中","dictdataid":1000000001426,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"refuse","name":"拒绝","dictdataid":1000000001427,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"receive","name":"接收","dictdataid":1000000001428,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"recover","name":"收回","dictdataid":1000000001429,"isleaf":null,"children":null}]}
     * mainvo : [{"headVO":{"RMT_WORK_GRANT_ITEM_M":{"tableName":"rmt_work_grant_item","created_by":1000,"ts":1579251864915,"status":"appr","remark":null,"be_authed":"测试人员1","rec_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":null,"grt_id":1000000000001,"created_dt":"2020-01-17 17:02:59","grt_item_time":null,"df":0,"orgid":null,"attach":null,"grt_item_id":1000000000020,"updated_by":1000,"be_authedid":1000000000000,"updated_dt":"2020-01-17 17:03:05","tenantid":1,"grantvo":{"tableName":"rmt_work_grant","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2020-01-16 09:41:16","updated_by":1000,"updated_dt":"2020-01-16 09:41:27","df":0,"tenantid":1,"ts":1579138887412,"grt_id":1000000000001,"auth":"系统管理员","authid":1000,"grt_time":"Thu Jan 16 09:41:16 CST 2020","starttime":"2020-01-16","endtime":"2020-01-16","grt_node":"on_duty_moni","status":null,"remark":null,"attach":null,"created_by_name":"系统管理员","updated_by_name":"系统管理员","isenable":1,"product_flag":null,"orgid":null},"updated_by_name":"系统管理员","dataStatus":0}},"attachDataMap":{},"bodyVOs":null,"attachDataVOs":{}}]
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

    public static class DictvosBean {
        private List<RmtWorkGrantItemstatusBean> rmt_work_grant_itemstatus;

        public List<RmtWorkGrantItemstatusBean> getRmt_work_grant_itemstatus() {
            return rmt_work_grant_itemstatus;
        }

        public void setRmt_work_grant_itemstatus(List<RmtWorkGrantItemstatusBean> rmt_work_grant_itemstatus) {
            this.rmt_work_grant_itemstatus = rmt_work_grant_itemstatus;
        }

        public static class RmtWorkGrantItemstatusBean {
            /**
             * tableName : null
             * columnValues : null
             * dataStatus : 0
             * code : appr
             * name : 审批中
             * dictdataid : 1000000001426
             * isleaf : null
             * children : null
             */

            private Object tableName;
            private Object columnValues;
            private int dataStatus;
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

            public int getDataStatus() {
                return dataStatus;
            }

            public void setDataStatus(int dataStatus) {
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

    public static class MainvoBean {
        /**
         * headVO : {"RMT_WORK_GRANT_ITEM_M":{"tableName":"rmt_work_grant_item","created_by":1000,"ts":1579251864915,"status":"appr","remark":null,"be_authed":"测试人员1","rec_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":null,"grt_id":1000000000001,"created_dt":"2020-01-17 17:02:59","grt_item_time":null,"df":0,"orgid":null,"attach":null,"grt_item_id":1000000000020,"updated_by":1000,"be_authedid":1000000000000,"updated_dt":"2020-01-17 17:03:05","tenantid":1,"grantvo":{"tableName":"rmt_work_grant","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2020-01-16 09:41:16","updated_by":1000,"updated_dt":"2020-01-16 09:41:27","df":0,"tenantid":1,"ts":1579138887412,"grt_id":1000000000001,"auth":"系统管理员","authid":1000,"grt_time":"Thu Jan 16 09:41:16 CST 2020","starttime":"2020-01-16","endtime":"2020-01-16","grt_node":"on_duty_moni","status":null,"remark":null,"attach":null,"created_by_name":"系统管理员","updated_by_name":"系统管理员","isenable":1,"product_flag":null,"orgid":null},"updated_by_name":"系统管理员","dataStatus":0}}
         * attachDataMap : {}
         * bodyVOs : null
         * attachDataVOs : {}
         */

        private HeadVOBean headVO;
        private AttachDataMapBean attachDataMap;
        private Object bodyVOs;
        private AttachDataVOsBean attachDataVOs;

        public HeadVOBean getHeadVO() {
            return headVO;
        }

        public void setHeadVO(HeadVOBean headVO) {
            this.headVO = headVO;
        }

        public AttachDataMapBean getAttachDataMap() {
            return attachDataMap;
        }

        public void setAttachDataMap(AttachDataMapBean attachDataMap) {
            this.attachDataMap = attachDataMap;
        }

        public Object getBodyVOs() {
            return bodyVOs;
        }

        public void setBodyVOs(Object bodyVOs) {
            this.bodyVOs = bodyVOs;
        }

        public AttachDataVOsBean getAttachDataVOs() {
            return attachDataVOs;
        }

        public void setAttachDataVOs(AttachDataVOsBean attachDataVOs) {
            this.attachDataVOs = attachDataVOs;
        }

        public static class HeadVOBean {

            /**
             * RMT_WORK_GRANT_ITEM_M : {"tableName":"rmt_work_grant_item","created_by":1000,"ts":1579251864915,"status":"appr","remark":null,"be_authed":"测试人员1","rec_time":null,"ver":1,"isenable":1,"created_by_name":"系统管理员","product_flag":null,"grt_id":1000000000001,"created_dt":"2020-01-17 17:02:59","grt_item_time":null,"df":0,"orgid":null,"attach":null,"grt_item_id":1000000000020,"updated_by":1000,"be_authedid":1000000000000,"updated_dt":"2020-01-17 17:03:05","tenantid":1,"grantvo":{"tableName":"rmt_work_grant","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2020-01-16 09:41:16","updated_by":1000,"updated_dt":"2020-01-16 09:41:27","df":0,"tenantid":1,"ts":1579138887412,"grt_id":1000000000001,"auth":"系统管理员","authid":1000,"grt_time":"Thu Jan 16 09:41:16 CST 2020","starttime":"2020-01-16","endtime":"2020-01-16","grt_node":"on_duty_moni","status":null,"remark":null,"attach":null,"created_by_name":"系统管理员","updated_by_name":"系统管理员","isenable":1,"product_flag":null,"orgid":null},"updated_by_name":"系统管理员","dataStatus":0}
             */

            private RMTWORKGRANTITEMMBean RMT_WORK_GRANT_ITEM_M;

            public RMTWORKGRANTITEMMBean getRMT_WORK_GRANT_ITEM_M() {
                return RMT_WORK_GRANT_ITEM_M;
            }

            public void setRMT_WORK_GRANT_ITEM_M(RMTWORKGRANTITEMMBean RMT_WORK_GRANT_ITEM_M) {
                this.RMT_WORK_GRANT_ITEM_M = RMT_WORK_GRANT_ITEM_M;
            }

            public static class RMTWORKGRANTITEMMBean {
                /**
                 * tableName : rmt_work_grant_item
                 * created_by : 1000
                 * ts : 1579251864915
                 * status : appr
                 * remark : null
                 * be_authed : 测试人员1
                 * rec_time : null
                 * ver : 1
                 * isenable : 1
                 * created_by_name : 系统管理员
                 * product_flag : null
                 * grt_id : 1000000000001
                 * created_dt : 2020-01-17 17:02:59
                 * grt_item_time : null
                 * df : 0
                 * orgid : null
                 * attach : null
                 * grt_item_id : 1000000000020
                 * updated_by : 1000
                 * be_authedid : 1000000000000
                 * updated_dt : 2020-01-17 17:03:05
                 * tenantid : 1
                 * grantvo : {"tableName":"rmt_work_grant","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2020-01-16 09:41:16","updated_by":1000,"updated_dt":"2020-01-16 09:41:27","df":0,"tenantid":1,"ts":1579138887412,"grt_id":1000000000001,"auth":"系统管理员","authid":1000,"grt_time":"Thu Jan 16 09:41:16 CST 2020","starttime":"2020-01-16","endtime":"2020-01-16","grt_node":"on_duty_moni","status":null,"remark":null,"attach":null,"created_by_name":"系统管理员","updated_by_name":"系统管理员","isenable":1,"product_flag":null,"orgid":null}
                 * updated_by_name : 系统管理员
                 * dataStatus : 0
                 */

                private String tableName;
                private long created_by;
                private long ts;
                private String status;
                private Object remark;
                private String be_authed;
                private Object rec_time;
                private int ver;
                private int isenable;
                private String created_by_name;
                private Object product_flag;
                private long grt_id;
                private String created_dt;
                private Object grt_item_time;
                private int df;
                private Object orgid;
                private Object attach;
                private long grt_item_id;
                private long updated_by;
                private long be_authedid;
                private String updated_dt;
                private long tenantid;
                private GrantvoBean grantvo;
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

                public long getTs() {
                    return ts;
                }

                public void setTs(long ts) {
                    this.ts = ts;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public Object getRemark() {
                    return remark;
                }

                public void setRemark(Object remark) {
                    this.remark = remark;
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

                public int getVer() {
                    return ver;
                }

                public void setVer(int ver) {
                    this.ver = ver;
                }

                public int getIsenable() {
                    return isenable;
                }

                public void setIsenable(int isenable) {
                    this.isenable = isenable;
                }

                public String getCreated_by_name() {
                    return created_by_name;
                }

                public void setCreated_by_name(String created_by_name) {
                    this.created_by_name = created_by_name;
                }

                public Object getProduct_flag() {
                    return product_flag;
                }

                public void setProduct_flag(Object product_flag) {
                    this.product_flag = product_flag;
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

                public Object getGrt_item_time() {
                    return grt_item_time;
                }

                public void setGrt_item_time(Object grt_item_time) {
                    this.grt_item_time = grt_item_time;
                }

                public int getDf() {
                    return df;
                }

                public void setDf(int df) {
                    this.df = df;
                }

                public Object getOrgid() {
                    return orgid;
                }

                public void setOrgid(Object orgid) {
                    this.orgid = orgid;
                }

                public Object getAttach() {
                    return attach;
                }

                public void setAttach(Object attach) {
                    this.attach = attach;
                }

                public long getGrt_item_id() {
                    return grt_item_id;
                }

                public void setGrt_item_id(long grt_item_id) {
                    this.grt_item_id = grt_item_id;
                }

                public long getUpdated_by() {
                    return updated_by;
                }

                public void setUpdated_by(long updated_by) {
                    this.updated_by = updated_by;
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

                public long getTenantid() {
                    return tenantid;
                }

                public void setTenantid(long tenantid) {
                    this.tenantid = tenantid;
                }

                public GrantvoBean getGrantvo() {
                    return grantvo;
                }

                public void setGrantvo(GrantvoBean grantvo) {
                    this.grantvo = grantvo;
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

                public static class GrantvoBean {
                    /**
                     * tableName : rmt_work_grant
                     * columnValues : null
                     * dataStatus : 0
                     * ver : 1
                     * created_by : 1000
                     * created_dt : 2020-01-16 09:41:16
                     * updated_by : 1000
                     * updated_dt : 2020-01-16 09:41:27
                     * df : 0
                     * tenantid : 1
                     * ts : 1579138887412
                     * grt_id : 1000000000001
                     * auth : 系统管理员
                     * authid : 1000
                     * grt_time : Thu Jan 16 09:41:16 CST 2020
                     * starttime : 2020-01-16
                     * endtime : 2020-01-16
                     * grt_node : on_duty_moni
                     * status : null
                     * remark : null
                     * attach : null
                     * created_by_name : 系统管理员
                     * updated_by_name : 系统管理员
                     * isenable : 1
                     * product_flag : null
                     * orgid : null
                     */

                    private String tableName;
                    private Object columnValues;
                    private int dataStatus;
                    private int ver;
                    private long created_by;
                    private String created_dt;
                    private long updated_by;
                    private String updated_dt;
                    private int df;
                    private int tenantid;
                    private long ts;
                    private long grt_id;
                    private String auth;
                    private long authid;
                    private String grt_time;
                    private String starttime;
                    private String endtime;
                    private String grt_node;
                    private Object status;
                    private Object remark;
                    private Object attach;
                    private String created_by_name;
                    private String updated_by_name;
                    private int isenable;
                    private Object product_flag;
                    private Object orgid;

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

                    public long getTs() {
                        return ts;
                    }

                    public void setTs(long ts) {
                        this.ts = ts;
                    }

                    public long getGrt_id() {
                        return grt_id;
                    }

                    public void setGrt_id(long grt_id) {
                        this.grt_id = grt_id;
                    }

                    public String getAuth() {
                        return auth;
                    }

                    public void setAuth(String auth) {
                        this.auth = auth;
                    }

                    public long getAuthid() {
                        return authid;
                    }

                    public void setAuthid(long authid) {
                        this.authid = authid;
                    }

                    public String getGrt_time() {
                        return grt_time;
                    }

                    public void setGrt_time(String grt_time) {
                        this.grt_time = grt_time;
                    }

                    public String getStarttime() {
                        return starttime;
                    }

                    public void setStarttime(String starttime) {
                        this.starttime = starttime;
                    }

                    public String getEndtime() {
                        return endtime;
                    }

                    public void setEndtime(String endtime) {
                        this.endtime = endtime;
                    }

                    public String getGrt_node() {
                        return grt_node;
                    }

                    public void setGrt_node(String grt_node) {
                        this.grt_node = grt_node;
                    }

                    public Object getStatus() {
                        return status;
                    }

                    public void setStatus(Object status) {
                        this.status = status;
                    }

                    public Object getRemark() {
                        return remark;
                    }

                    public void setRemark(Object remark) {
                        this.remark = remark;
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

                    public int getIsenable() {
                        return isenable;
                    }

                    public void setIsenable(int isenable) {
                        this.isenable = isenable;
                    }

                    public Object getProduct_flag() {
                        return product_flag;
                    }

                    public void setProduct_flag(Object product_flag) {
                        this.product_flag = product_flag;
                    }

                    public Object getOrgid() {
                        return orgid;
                    }

                    public void setOrgid(Object orgid) {
                        this.orgid = orgid;
                    }
                }
            }
        }

        public static class AttachDataMapBean {
        }

        public static class AttachDataVOsBean {
        }
    }
}
