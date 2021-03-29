package com.hd.hse.entity.resultdata;

import com.hd.hse.common.entity.SuperEntity;

import java.util.List;

/**
 * Created by dubojian on 2018/6/8.
 */

public class WorkSite extends SuperEntity {


    /**
     * dictvos : null
     * mainvo : [{"headVO":{"EAM_POSITION_M":{"tableName":"eam_position","positionid":1000000000000,"orgid":1000000000024,"usernames":null,"ver":1,"owner_dept":"NX040105","userids":null,"owner_dept__name":"一联合车间","position_code":"P-01000","position_name":"海顿","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"EAM_POSITION_M":{"tableName":"eam_position","positionid":1000000000001,"orgid":1000000000005,"usernames":null,"ver":1,"owner_dept":"NX0401","userids":null,"owner_dept__name":"炼油厂","position_code":"P-01001","position_name":"测试","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]
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
         * headVO : {"EAM_POSITION_M":{"tableName":"eam_position","positionid":1000000000000,"orgid":1000000000024,"usernames":null,"ver":1,"owner_dept":"NX040105","userids":null,"owner_dept__name":"一联合车间","position_code":"P-01000","position_name":"海顿","dataStatus":0}}
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
             * EAM_POSITION_M : {"tableName":"eam_position","positionid":1000000000000,"orgid":1000000000024,"usernames":null,"ver":1,"owner_dept":"NX040105","userids":null,"owner_dept__name":"一联合车间","position_code":"P-01000","position_name":"海顿","dataStatus":0}
             */

            private EAMPOSITIONMBean EAM_POSITION_M;

            public EAMPOSITIONMBean getEAM_POSITION_M() {
                return EAM_POSITION_M;
            }

            public void setEAM_POSITION_M(EAMPOSITIONMBean EAM_POSITION_M) {
                this.EAM_POSITION_M = EAM_POSITION_M;
            }

            public static class EAMPOSITIONMBean {
                /**
                 * tableName : eam_position
                 * positionid : 1000000000000
                 * orgid : 1000000000024
                 * usernames : null
                 * ver : 1
                 * owner_dept : NX040105
                 * userids : null
                 * owner_dept__name : 一联合车间
                 * position_code : P-01000
                 * position_name : 海顿
                 * dataStatus : 0
                 */

                private String tableName;
                private long positionid;
                private long orgid;
                private Object usernames;
                private int ver;
                private String owner_dept;
                private Object userids;
                private String owner_dept__name;
                private String position_code;
                private String position_name;
                private int dataStatus;
                private String sortLetters;

                public String getSortLetters() {
                    return sortLetters;
                }

                public void setSortLetters(String sortLetters) {
                    this.sortLetters = sortLetters;
                }

                public String getTableName() {
                    return tableName;
                }

                public void setTableName(String tableName) {
                    this.tableName = tableName;
                }

                public long getPositionid() {
                    return positionid;
                }

                public void setPositionid(long positionid) {
                    this.positionid = positionid;
                }

                public long getOrgid() {
                    return orgid;
                }

                public void setOrgid(long orgid) {
                    this.orgid = orgid;
                }

                public Object getUsernames() {
                    return usernames;
                }

                public void setUsernames(Object usernames) {
                    this.usernames = usernames;
                }

                public int getVer() {
                    return ver;
                }

                public void setVer(int ver) {
                    this.ver = ver;
                }

                public String getOwner_dept() {
                    return owner_dept;
                }

                public void setOwner_dept(String owner_dept) {
                    this.owner_dept = owner_dept;
                }

                public Object getUserids() {
                    return userids;
                }

                public void setUserids(Object userids) {
                    this.userids = userids;
                }

                public String getOwner_dept__name() {
                    return owner_dept__name;
                }

                public void setOwner_dept__name(String owner_dept__name) {
                    this.owner_dept__name = owner_dept__name;
                }

                public String getPosition_code() {
                    return position_code;
                }

                public void setPosition_code(String position_code) {
                    this.position_code = position_code;
                }

                public String getPosition_name() {
                    return position_name;
                }

                public void setPosition_name(String position_name) {
                    this.position_name = position_name;
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
