package com.hd.hse.entity.resultdata;

import java.util.List;

public class RmtWorkEnd {


    /**
     * dictvos : {"rmt_appr_node":[{"tableName":null,"columnValues":null,"dataStatus":0,"code":"tear_line_person_sign","name":"拆线人签名","dictdataid":1000000000260,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"prov_elec_guar","name":"供电监护人","dictdataid":1000000000261,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"use_elec_pers","name":"用电人","dictdataid":1000000000262,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"terr_guar","name":"属地监护人","dictdataid":1000000000263,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"high_oper_pers","name":"高处作业人","dictdataid":1000000000264,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"temp_use_elec_appl","name":"临时用电作业申请人","dictdataid":1000000000265,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"temp_use_elec_pers","name":"临时用电","dictdataid":1000000000266,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"hois_oper_pers","name":"吊装作业人","dictdataid":1000000000267,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"majo_pers","name":"主修人","dictdataid":1000000000268,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"oper_guar","name":"作业监护人","dictdataid":1000000000269,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"on_duty_moni","name":"当班班长","dictdataid":1000000000270,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"norm_oper_pers","name":"一般（大票）作业人","dictdataid":1000000000271,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"opre_appl","name":"作业申请人","dictdataid":1000000000272,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"limi_oper_pers","name":"受限作业人","dictdataid":1000000000273,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"use_fire_oper_pers","name":"动火作业作业人","dictdataid":1000000000274,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"pipe_line_oper_pers","name":"管线作业人","dictdataid":1000000000275,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"use_fire_majo","name":"岗位主操","dictdataid":1000000000276,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"brak_grou_oper_pers","name":"破土作业人","dictdataid":1000000000277,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"fix_line_pres","name":"接线人","dictdataid":1000000000620,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"tear_line_guar","name":"拆线人监护","dictdataid":1000000000621,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"elecunit_terr_lead_pers","name":"电仪部属地负责人","dictdataid":1000000001302,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"prdunit_terr_lead_pers","name":"生产单位属地负责人","dictdataid":1000000001303,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"work_lead_pers","name":"工作负责人","dictdataid":1000000001304,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"work_perm_pers","name":"工作许可人","dictdataid":1000000001305,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"elec_oper_pers","name":"电气作业人","dictdataid":1000000001306,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"work_lead_pers_close","name":"工作负责人(关闭)","dictdataid":1000000001360,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"work_perm_pers_close","name":"工作许可人(关闭)","dictdataid":1000000001361,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"on_duty_fzr","name":"值班负责人","dictdataid":1000000001800,"isleaf":null,"children":null},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"prdunit_guar","name":"生产单位监护人","dictdataid":1000000001820,"isleaf":null,"children":null}]}
     * data_ext : {"cuur_time":"2020-04-26 15:07:33"}
     */

    private DictvosBean dictvos;
    private DataExtBean data_ext;

    public DictvosBean getDictvos() {
        return dictvos;
    }

    public void setDictvos(DictvosBean dictvos) {
        this.dictvos = dictvos;
    }

    public DataExtBean getData_ext() {
        return data_ext;
    }

    public void setData_ext(DataExtBean data_ext) {
        this.data_ext = data_ext;
    }

    public static class DictvosBean {
        private List<RmtApprNodeBean> rmt_appr_node;

        public List<RmtApprNodeBean> getRmt_appr_node() {
            return rmt_appr_node;
        }

        public void setRmt_appr_node(List<RmtApprNodeBean> rmt_appr_node) {
            this.rmt_appr_node = rmt_appr_node;
        }

        public static class RmtApprNodeBean {
            /**
             * tableName : null
             * columnValues : null
             * dataStatus : 0
             * code : tear_line_person_sign
             * name : 拆线人签名
             * dictdataid : 1000000000260
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

    public static class DataExtBean {
        /**
         * cuur_time : 2020-04-26 15:07:33
         */

        private String cuur_time;

        public String getCuur_time() {
            return cuur_time;
        }

        public void setCuur_time(String cuur_time) {
            this.cuur_time = cuur_time;
        }
    }
}
