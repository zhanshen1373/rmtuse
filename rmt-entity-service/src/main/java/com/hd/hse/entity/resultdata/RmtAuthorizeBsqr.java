package com.hd.hse.entity.resultdata;

import android.os.Parcel;

import java.io.Serializable;
import java.util.List;

public class RmtAuthorizeBsqr implements Serializable {

    /**
     * dictvos : null
     * mainvo : [{"headVO":{"SY_USER_M_QUERY":{"tableName":"sy_user","username":"超级管理员","orgid":808213,"usercode":"hd","userid":1,"dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"SY_USER_M_QUERY":{"tableName":"sy_user","username":"系统管理员","orgid":1000000000005,"usercode":"sy","orgname":"炼油厂","userid":100,"dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]
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

    public static class MainvoBean implements Serializable {
        /**
         * headVO : {"SY_USER_M_QUERY":{"tableName":"sy_user","username":"超级管理员","orgid":808213,"usercode":"hd","userid":1,"dataStatus":0}}
         * attachDataMap : null
         * bodyVOs : null
         * attachDataVOs : null
         */

        private HeadVOBean headVO;
        private Object attachDataMap;
        private Object bodyVOs;
        private Object attachDataVOs;

        protected MainvoBean(Parcel in) {
        }


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


        public static class HeadVOBean implements Serializable{
            /**
             * SY_USER_M_QUERY : {"tableName":"sy_user","username":"超级管理员","orgid":808213,"usercode":"hd","userid":1,"dataStatus":0}
             */

            private SYUSERMQUERYBean SY_USER_M_QUERY;

            public SYUSERMQUERYBean getSY_USER_M_QUERY() {
                return SY_USER_M_QUERY;
            }

            public void setSY_USER_M_QUERY(SYUSERMQUERYBean SY_USER_M_QUERY) {
                this.SY_USER_M_QUERY = SY_USER_M_QUERY;
            }

            public static class SYUSERMQUERYBean implements Serializable{
                /**
                 * tableName : sy_user
                 * username : 超级管理员
                 * orgid : 808213
                 * usercode : hd
                 * userid : 1
                 * dataStatus : 0
                 */

                private String tableName;
                private String username;
                private long orgid;
                private String usercode;
                private long userid;
                private int dataStatus;

                public String getTableName() {
                    return tableName;
                }

                public void setTableName(String tableName) {
                    this.tableName = tableName;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public long getOrgid() {
                    return orgid;
                }

                public void setOrgid(long orgid) {
                    this.orgid = orgid;
                }

                public String getUsercode() {
                    return usercode;
                }

                public void setUsercode(String usercode) {
                    this.usercode = usercode;
                }

                public long getUserid() {
                    return userid;
                }

                public void setUserid(long userid) {
                    this.userid = userid;
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
