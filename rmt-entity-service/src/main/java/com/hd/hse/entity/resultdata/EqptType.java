package com.hd.hse.entity.resultdata;

import com.hd.hse.common.entity.SuperEntity;

import java.util.List;

/**
 * Created by dubojian on 2018/6/11.
 */

public class EqptType extends SuperEntity {


    /**
     * dictvos : null
     * mainvo : [{"headVO":{"SY_DICT_DATA":{"tableName":"sy_dict_data","dict_data_order":10,"dict_data_isenable":1,"dict_data_name":"安全阀","dict_data_code":"safvle","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"SY_DICT_DATA":{"tableName":"sy_dict_data","dict_data_order":20,"dict_data_isenable":1,"dict_data_name":"动设备","dict_data_code":"rotaeq","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"SY_DICT_DATA":{"tableName":"sy_dict_data","dict_data_order":30,"dict_data_isenable":1,"dict_data_name":"特种设备","dict_data_code":"spcleq","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"SY_DICT_DATA":{"tableName":"sy_dict_data","dict_data_order":40,"dict_data_isenable":1,"dict_data_name":"静设备","dict_data_code":"stateq","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"SY_DICT_DATA":{"tableName":"sy_dict_data","dict_data_order":50,"dict_data_isenable":1,"dict_data_name":"管线","dict_data_code":"piplne","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]
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
         * headVO : {"SY_DICT_DATA":{"tableName":"sy_dict_data","dict_data_order":10,"dict_data_isenable":1,"dict_data_name":"安全阀","dict_data_code":"safvle","dataStatus":0}}
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
             * SY_DICT_DATA : {"tableName":"sy_dict_data","dict_data_order":10,"dict_data_isenable":1,"dict_data_name":"安全阀","dict_data_code":"safvle","dataStatus":0}
             */

            private SYDICTDATABean SY_DICT_DATA;

            public SYDICTDATABean getSY_DICT_DATA() {
                return SY_DICT_DATA;
            }

            public void setSY_DICT_DATA(SYDICTDATABean SY_DICT_DATA) {
                this.SY_DICT_DATA = SY_DICT_DATA;
            }

            public static class SYDICTDATABean {
                /**
                 * tableName : sy_dict_data
                 * dict_data_order : 10
                 * dict_data_isenable : 1
                 * dict_data_name : 安全阀
                 * dict_data_code : safvle
                 * dataStatus : 0
                 */

                private String tableName;
                private int dict_data_order;
                private int dict_data_isenable;
                private String dict_data_name;
                private String dict_data_code;
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

                public int getDict_data_order() {
                    return dict_data_order;
                }

                public void setDict_data_order(int dict_data_order) {
                    this.dict_data_order = dict_data_order;
                }

                public int getDict_data_isenable() {
                    return dict_data_isenable;
                }

                public void setDict_data_isenable(int dict_data_isenable) {
                    this.dict_data_isenable = dict_data_isenable;
                }

                public String getDict_data_name() {
                    return dict_data_name;
                }

                public void setDict_data_name(String dict_data_name) {
                    this.dict_data_name = dict_data_name;
                }

                public String getDict_data_code() {
                    return dict_data_code;
                }

                public void setDict_data_code(String dict_data_code) {
                    this.dict_data_code = dict_data_code;
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
