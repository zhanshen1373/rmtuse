package com.hd.hse.entity.workorder;

import java.util.List;

/**
 * 气体检测
 * created by yangning on 2017/4/25 16:22.
 */

public class RmtWorkGasDetect {

    /**
     * dictvos : {"RMT_GAS_TYPE_TREE_M":[{"tableName":null,"columnValues":null,"dataStatus":0,"code":"oxygen","name":"氧气浓度%","dictdataid":1000000000520,"isleaf":0,"children":[{"tableName":null,"lowerlimit":"2.00","upperlimit":"2.00","dataStatus":0,"code":"oxygen_01","name":"氧气%","dictdataid":1000000000523,"isleaf":1,"children":[]}]},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"combus","name":"可燃气体浓度LEL%","dictdataid":1000000000521,"isleaf":0,"children":[{"tableName":null,"lowerlimit":"1.00","upperlimit":"12.00","dataStatus":0,"code":"combus_01","name":"可燃气%","dictdataid":1000000000524,"isleaf":1,"children":[]}]},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"poison","name":"有毒气体浓度%","dictdataid":1000000000522,"isleaf":0,"children":[{"tableName":null,"lowerlimit":"1.00","upperlimit":"12.00","dataStatus":0,"code":"poison_01","name":"硫化氢mg/m3","dictdataid":1000000000525,"isleaf":1,"children":[]},{"tableName":null,"lowerlimit":"1.00","upperlimit":"12.00","dataStatus":0,"code":"poison_02","name":"甲醇mg/m3","dictdataid":1000000000526,"isleaf":1,"children":[]},{"tableName":null,"lowerlimit":"1.00","upperlimit":"12.00","dataStatus":0,"code":"poison_03","name":"氨mg/m3","dictdataid":1000000000528,"isleaf":1,"children":[]},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"poison_04","name":"COmg/m3","dictdataid":1000000000527,"isleaf":1,"children":[]},{"tableName":null,"columnValues":null,"dataStatus":0,"code":"poison_05","name":"有毒气体","dictdataid":1000000000529,"isleaf":1,"children":[]}]}]}
     * mainvo : [{"headVO":{"RMT_WORK_GAS_DETECT_M":{"tableName":"rmt_work_gas_detect","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2017-04-21 09:45:11","updated_by":1000,"updated_dt":"2017-04-21 09:45:41","df":0,"tenantid":1,"ts":1492739141604,"gas_detect_id":1000000000420,"work_subtask_id":1000000000620,"created_by_name":"测试用户","updated_by_name":"测试用户","isenable":1,"product_flag":2,"orgid":808212,"detect_time":"2017-04-24 00:00:00","detect_site":"位置","analyzer_id":null,"analyzer_name":null,"qualified":0,"detect_no":1}},"attachDataMap":null,"bodyVOs":{"RMT_WORK_GAS_DETECT_LINE_M":[{"headVO":{"RMT_WORK_GAS_DETECT_LINE_M":{"tableName":"rmt_work_gas_detect_line","created_by":1000,"gas_type_sub":null,"ts":1492739111595,"gas_name":"氧气浓度%","ver":1,"created_by_name":"测试用户","isenable":1,"product_flag":2,"created_dt":"2017-04-21 09:45:01","gas_type_sub_name":null,"work_subtask_id":1000000000620,"df":0,"gas_value":null,"orgid":808212,"gas_type":"oxygen","gas_detect_id":1000000000420,"updated_by":1000,"tenantid":1,"updated_dt":"2017-04-21 09:45:01","gas_detect_line_id":1000000000200,"updated_by_name":"测试用户","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_GAS_DETECT_LINE_M":{"tableName":"rmt_work_gas_detect_line","created_by":1000,"gas_type_sub":null,"ts":1492739111596,"gas_name":"可燃气体浓度LEL%","ver":1,"created_by_name":"测试用户","isenable":1,"product_flag":2,"created_dt":"2017-04-21 09:45:01","gas_type_sub_name":null,"work_subtask_id":1000000000620,"df":0,"gas_value":null,"orgid":808212,"gas_type":"combustible","gas_detect_id":1000000000420,"updated_by":1000,"tenantid":1,"updated_dt":"2017-04-21 09:45:01","gas_detect_line_id":1000000000201,"updated_by_name":"测试用户","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_GAS_DETECT_LINE_M":{"tableName":"rmt_work_gas_detect_line","created_by":1000,"gas_type_sub":null,"ts":1492739111596,"gas_name":"有毒气体浓度%","ver":1,"created_by_name":"测试用户","isenable":1,"product_flag":2,"created_dt":"2017-04-21 09:45:01","gas_type_sub_name":null,"work_subtask_id":1000000000620,"df":0,"gas_value":null,"orgid":808212,"gas_type":"poisonous","gas_detect_id":1000000000420,"updated_by":1000,"tenantid":1,"updated_dt":"2017-04-21 09:45:01","gas_detect_line_id":1000000000202,"updated_by_name":"测试用户","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]},"attachDataVOs":null}]
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
        private List<RMTGASTYPETREEMBean> RMT_GAS_TYPE_TREE_M;

        public List<RMTGASTYPETREEMBean> getRMT_GAS_TYPE_TREE_M() {
            return RMT_GAS_TYPE_TREE_M;
        }

        public void setRMT_GAS_TYPE_TREE_M(List<RMTGASTYPETREEMBean> RMT_GAS_TYPE_TREE_M) {
            this.RMT_GAS_TYPE_TREE_M = RMT_GAS_TYPE_TREE_M;
        }

        public static class RMTGASTYPETREEMBean {
            /**
             * tableName : null
             * columnValues : null
             * dataStatus : 0
             * code : oxygen
             * name : 氧气浓度%
             * dictdataid : 1000000000520
             * isleaf : 0
             * children : [{"tableName":null,"lowerlimit":"2.00","upperlimit":"2.00","dataStatus":0,"code":"oxygen_01","name":"氧气%","dictdataid":1000000000523,"isleaf":1,"children":[]}]
             */

            private String tableName;
            private Object columnValues;
            private int dataStatus;
            private String code;
            private String name;
            private long dictdataid;
            private int isleaf;
            private List<ChildrenBean> children;

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

            public int getIsleaf() {
                return isleaf;
            }

            public void setIsleaf(int isleaf) {
                this.isleaf = isleaf;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean {
                /**
                 * tableName : null
                 * lowerlimit : 2.00
                 * upperlimit : 2.00
                 * dataStatus : 0
                 * code : oxygen_01
                 * name : 氧气%
                 * dictdataid : 1000000000523
                 * isleaf : 1
                 * children : []
                 */

                private String tableName;
                private String lowerlimit;
                private String upperlimit;

                public int getBoundary() {
                    return boundary;
                }

                public void setBoundary(int boundary) {
                    this.boundary = boundary;
                }

                private int boundary;



                private int dataStatus;
                private String code;
                private String name;
                private long dictdataid;
                private int isleaf;
                private List<?> children;

                public String getTableName() {
                    return tableName;
                }

                public void setTableName(String tableName) {
                    this.tableName = tableName;
                }

                public String getLowerlimit() {
                    return lowerlimit;
                }

                public void setLowerlimit(String lowerlimit) {
                    this.lowerlimit = lowerlimit;
                }

                public String getUpperlimit() {
                    return upperlimit;
                }

                public void setUpperlimit(String upperlimit) {
                    this.upperlimit = upperlimit;
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

                public int getIsleaf() {
                    return isleaf;
                }

                public void setIsleaf(int isleaf) {
                    this.isleaf = isleaf;
                }

                public List<?> getChildren() {
                    return children;
                }

                public void setChildren(List<?> children) {
                    this.children = children;
                }
            }
        }
    }

    public static class MainvoBean {
        /**
         * headVO : {"RMT_WORK_GAS_DETECT_M":{"tableName":"rmt_work_gas_detect","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2017-04-21 09:45:11","updated_by":1000,"updated_dt":"2017-04-21 09:45:41","df":0,"tenantid":1,"ts":1492739141604,"gas_detect_id":1000000000420,"work_subtask_id":1000000000620,"created_by_name":"测试用户","updated_by_name":"测试用户","isenable":1,"product_flag":2,"orgid":808212,"detect_time":"2017-04-24 00:00:00","detect_site":"位置","analyzer_id":null,"analyzer_name":null,"qualified":0,"detect_no":1}}
         * attachDataMap : null
         * bodyVOs : {"RMT_WORK_GAS_DETECT_LINE_M":[{"headVO":{"RMT_WORK_GAS_DETECT_LINE_M":{"tableName":"rmt_work_gas_detect_line","created_by":1000,"gas_type_sub":null,"ts":1492739111595,"gas_name":"氧气浓度%","ver":1,"created_by_name":"测试用户","isenable":1,"product_flag":2,"created_dt":"2017-04-21 09:45:01","gas_type_sub_name":null,"work_subtask_id":1000000000620,"df":0,"gas_value":null,"orgid":808212,"gas_type":"oxygen","gas_detect_id":1000000000420,"updated_by":1000,"tenantid":1,"updated_dt":"2017-04-21 09:45:01","gas_detect_line_id":1000000000200,"updated_by_name":"测试用户","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_GAS_DETECT_LINE_M":{"tableName":"rmt_work_gas_detect_line","created_by":1000,"gas_type_sub":null,"ts":1492739111596,"gas_name":"可燃气体浓度LEL%","ver":1,"created_by_name":"测试用户","isenable":1,"product_flag":2,"created_dt":"2017-04-21 09:45:01","gas_type_sub_name":null,"work_subtask_id":1000000000620,"df":0,"gas_value":null,"orgid":808212,"gas_type":"combustible","gas_detect_id":1000000000420,"updated_by":1000,"tenantid":1,"updated_dt":"2017-04-21 09:45:01","gas_detect_line_id":1000000000201,"updated_by_name":"测试用户","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null},{"headVO":{"RMT_WORK_GAS_DETECT_LINE_M":{"tableName":"rmt_work_gas_detect_line","created_by":1000,"gas_type_sub":null,"ts":1492739111596,"gas_name":"有毒气体浓度%","ver":1,"created_by_name":"测试用户","isenable":1,"product_flag":2,"created_dt":"2017-04-21 09:45:01","gas_type_sub_name":null,"work_subtask_id":1000000000620,"df":0,"gas_value":null,"orgid":808212,"gas_type":"poisonous","gas_detect_id":1000000000420,"updated_by":1000,"tenantid":1,"updated_dt":"2017-04-21 09:45:01","gas_detect_line_id":1000000000202,"updated_by_name":"测试用户","dataStatus":0}},"attachDataMap":null,"bodyVOs":null,"attachDataVOs":null}]}
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
             * RMT_WORK_GAS_DETECT_M : {"tableName":"rmt_work_gas_detect","columnValues":null,"dataStatus":0,"ver":1,"created_by":1000,"created_dt":"2017-04-21 09:45:11","updated_by":1000,"updated_dt":"2017-04-21 09:45:41","df":0,"tenantid":1,"ts":1492739141604,"gas_detect_id":1000000000420,"work_subtask_id":1000000000620,"created_by_name":"测试用户","updated_by_name":"测试用户","isenable":1,"product_flag":2,"orgid":808212,"detect_time":"2017-04-24 00:00:00","detect_site":"位置","analyzer_id":null,"analyzer_name":null,"qualified":0,"detect_no":1}
             */

            private RMTWORKGASDETECTMBean RMT_WORK_GAS_DETECT_M;

            public RMTWORKGASDETECTMBean getRMT_WORK_GAS_DETECT_M() {
                return RMT_WORK_GAS_DETECT_M;
            }

            public void setRMT_WORK_GAS_DETECT_M(RMTWORKGASDETECTMBean RMT_WORK_GAS_DETECT_M) {
                this.RMT_WORK_GAS_DETECT_M = RMT_WORK_GAS_DETECT_M;
            }

            public static class RMTWORKGASDETECTMBean {
                /**
                 * tableName : rmt_work_gas_detect
                 * columnValues : null
                 * dataStatus : 0
                 * ver : 1
                 * created_by : 1000
                 * created_dt : 2017-04-21 09:45:11
                 * updated_by : 1000
                 * updated_dt : 2017-04-21 09:45:41
                 * df : 0
                 * tenantid : 1
                 * ts : 1492739141604
                 * gas_detect_id : 1000000000420
                 * work_subtask_id : 1000000000620
                 * created_by_name : 测试用户
                 * updated_by_name : 测试用户
                 * isenable : 1
                 * product_flag : 2
                 * orgid : 808212
                 * detect_time : 2017-04-24 00:00:00
                 * detect_site : 位置
                 * analyzer_id : null
                 * analyzer_name : null
                 * qualified : 0
                 * detect_no : 1
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
                private long tenantid;
                private long ts;
                private long gas_detect_id;
                private long work_subtask_id;
                private String created_by_name;
                private String updated_by_name;
                private int isenable;
                private int product_flag;
                private long orgid;
                private String detect_time;
                private String detect_site;
                private long analyzer_id;
                private String analyzer_name;
                private int qualified;
                private int detect_no;

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

                public long getTenantid() {
                    return tenantid;
                }

                public void setTenantid(long tenantid) {
                    this.tenantid = tenantid;
                }

                public long getTs() {
                    return ts;
                }

                public void setTs(long ts) {
                    this.ts = ts;
                }

                public long getGas_detect_id() {
                    return gas_detect_id;
                }

                public void setGas_detect_id(long gas_detect_id) {
                    this.gas_detect_id = gas_detect_id;
                }

                public long getWork_subtask_id() {
                    return work_subtask_id;
                }

                public void setWork_subtask_id(long work_subtask_id) {
                    this.work_subtask_id = work_subtask_id;
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

                public int getProduct_flag() {
                    return product_flag;
                }

                public void setProduct_flag(int product_flag) {
                    this.product_flag = product_flag;
                }

                public long getOrgid() {
                    return orgid;
                }

                public void setOrgid(long orgid) {
                    this.orgid = orgid;
                }

                public String getDetect_time() {
                    return detect_time;
                }

                public void setDetect_time(String detect_time) {
                    this.detect_time = detect_time;
                }

                public String getDetect_site() {
                    return detect_site;
                }

                public void setDetect_site(String detect_site) {
                    this.detect_site = detect_site;
                }

                public long getAnalyzer_id() {
                    return analyzer_id;
                }

                public void setAnalyzer_id(long analyzer_id) {
                    this.analyzer_id = analyzer_id;
                }

                public String getAnalyzer_name() {
                    return analyzer_name;
                }

                public void setAnalyzer_name(String analyzer_name) {
                    this.analyzer_name = analyzer_name;
                }

                public int getQualified() {
                    return qualified;
                }

                public void setQualified(int qualified) {
                    this.qualified = qualified;
                }

                public int getDetect_no() {
                    return detect_no;
                }

                public void setDetect_no(int detect_no) {
                    this.detect_no = detect_no;
                }
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
                 * headVO : {"RMT_WORK_GAS_DETECT_LINE_M":{"tableName":"rmt_work_gas_detect_line","created_by":1000,"gas_type_sub":null,"ts":1492739111595,"gas_name":"氧气浓度%","ver":1,"created_by_name":"测试用户","isenable":1,"product_flag":2,"created_dt":"2017-04-21 09:45:01","gas_type_sub_name":null,"work_subtask_id":1000000000620,"df":0,"gas_value":null,"orgid":808212,"gas_type":"oxygen","gas_detect_id":1000000000420,"updated_by":1000,"tenantid":1,"updated_dt":"2017-04-21 09:45:01","gas_detect_line_id":1000000000200,"updated_by_name":"测试用户","dataStatus":0}}
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
                     * RMT_WORK_GAS_DETECT_LINE_M : {"tableName":"rmt_work_gas_detect_line","created_by":1000,"gas_type_sub":null,"ts":1492739111595,"gas_name":"氧气浓度%","ver":1,"created_by_name":"测试用户","isenable":1,"product_flag":2,"created_dt":"2017-04-21 09:45:01","gas_type_sub_name":null,"work_subtask_id":1000000000620,"df":0,"gas_value":null,"orgid":808212,"gas_type":"oxygen","gas_detect_id":1000000000420,"updated_by":1000,"tenantid":1,"updated_dt":"2017-04-21 09:45:01","gas_detect_line_id":1000000000200,"updated_by_name":"测试用户","dataStatus":0}
                     */

                    private RMTWORKGASDETECTLINEMBean RMT_WORK_GAS_DETECT_LINE_M;

                    public RMTWORKGASDETECTLINEMBean getRMT_WORK_GAS_DETECT_LINE_M() {
                        return RMT_WORK_GAS_DETECT_LINE_M;
                    }

                    public void setRMT_WORK_GAS_DETECT_LINE_M(RMTWORKGASDETECTLINEMBean RMT_WORK_GAS_DETECT_LINE_M) {
                        this.RMT_WORK_GAS_DETECT_LINE_M = RMT_WORK_GAS_DETECT_LINE_M;
                    }

                    public static class RMTWORKGASDETECTLINEMBean {
                        /**
                         * tableName : rmt_work_gas_detect_line
                         * created_by : 1000
                         * gas_type_sub : null
                         * ts : 1492739111595
                         * gas_name : 氧气浓度%
                         * ver : 1
                         * created_by_name : 测试用户
                         * isenable : 1
                         * product_flag : 2
                         * created_dt : 2017-04-21 09:45:01
                         * gas_type_sub_name : null
                         * work_subtask_id : 1000000000620
                         * df : 0
                         * gas_value : null
                         * orgid : 808212
                         * gas_type : oxygen
                         * gas_detect_id : 1000000000420
                         * updated_by : 1000
                         * tenantid : 1
                         * updated_dt : 2017-04-21 09:45:01
                         * gas_detect_line_id : 1000000000200
                         * updated_by_name : 测试用户
                         * dataStatus : 0
                         */

                        private String tableName;
                        private long created_by;
                        private String gas_type_sub;
                        private long ts;
                        private String gas_name;
                        private int ver;
                        private String created_by_name;
                        private int isenable;
                        private int product_flag;
                        private String created_dt;
                        private String gas_type_sub_name;
                        private long work_subtask_id;
                        private int df;
                        private double gas_value;
                        private long orgid;
                        private String gas_type;
                        private long gas_detect_id;
                        private long updated_by;
                        private long tenantid;
                        private String updated_dt;
                        private long gas_detect_line_id;
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

                        public String getGas_type_sub() {
                            return gas_type_sub;
                        }

                        public void setGas_type_sub(String gas_type_sub) {
                            this.gas_type_sub = gas_type_sub;
                        }

                        public long getTs() {
                            return ts;
                        }

                        public void setTs(long ts) {
                            this.ts = ts;
                        }

                        public String getGas_name() {
                            return gas_name;
                        }

                        public void setGas_name(String gas_name) {
                            this.gas_name = gas_name;
                        }

                        public int getVer() {
                            return ver;
                        }

                        public void setVer(int ver) {
                            this.ver = ver;
                        }

                        public String getCreated_by_name() {
                            return created_by_name;
                        }

                        public void setCreated_by_name(String created_by_name) {
                            this.created_by_name = created_by_name;
                        }

                        public int getIsenable() {
                            return isenable;
                        }

                        public void setIsenable(int isenable) {
                            this.isenable = isenable;
                        }

                        public int getProduct_flag() {
                            return product_flag;
                        }

                        public void setProduct_flag(int product_flag) {
                            this.product_flag = product_flag;
                        }

                        public String getCreated_dt() {
                            return created_dt;
                        }

                        public void setCreated_dt(String created_dt) {
                            this.created_dt = created_dt;
                        }

                        public String getGas_type_sub_name() {
                            return gas_type_sub_name;
                        }

                        public void setGas_type_sub_name(String gas_type_sub_name) {
                            this.gas_type_sub_name = gas_type_sub_name;
                        }

                        public long getWork_subtask_id() {
                            return work_subtask_id;
                        }

                        public void setWork_subtask_id(long work_subtask_id) {
                            this.work_subtask_id = work_subtask_id;
                        }

                        public int getDf() {
                            return df;
                        }

                        public void setDf(int df) {
                            this.df = df;
                        }

                        public double getGas_value() {
                            return gas_value;
                        }

                        public void setGas_value(double gas_value) {
                            this.gas_value = gas_value;
                        }

                        public long getOrgid() {
                            return orgid;
                        }

                        public void setOrgid(long orgid) {
                            this.orgid = orgid;
                        }

                        public String getGas_type() {
                            return gas_type;
                        }

                        public void setGas_type(String gas_type) {
                            this.gas_type = gas_type;
                        }

                        public long getGas_detect_id() {
                            return gas_detect_id;
                        }

                        public void setGas_detect_id(long gas_detect_id) {
                            this.gas_detect_id = gas_detect_id;
                        }

                        public long getUpdated_by() {
                            return updated_by;
                        }

                        public void setUpdated_by(long updated_by) {
                            this.updated_by = updated_by;
                        }

                        public long getTenantid() {
                            return tenantid;
                        }

                        public void setTenantid(long tenantid) {
                            this.tenantid = tenantid;
                        }

                        public String getUpdated_dt() {
                            return updated_dt;
                        }

                        public void setUpdated_dt(String updated_dt) {
                            this.updated_dt = updated_dt;
                        }

                        public long getGas_detect_line_id() {
                            return gas_detect_line_id;
                        }

                        public void setGas_detect_line_id(long gas_detect_line_id) {
                            this.gas_detect_line_id = gas_detect_line_id;
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
