package com.hd.hse.entity.resultdata;

import com.hd.hse.common.entity.SuperEntity;

import java.util.List;

/**
 * created by yangning on 2017/5/26 13:46.
 */

public class RmtWorkGasDetectReslt extends SuperEntity{

    /**
     * tableName : null
     * columnValues : null
     * dataStatus : 0
     * detect_site : 位置
     * work_subtask_id : 1000000001700
     * qualified : 0
     * detect_time : 2017-05-24 17:45:07
     * voList : [{"tableName":"rmt_work_gas_detect_line","created_by":1000,"gas_type_sub":"oxygen_01","ts":1495618770773,"gas_name":null,"ver":1,"created_by_name":"系统管理员","isenable":1,"product_flag":2,"created_dt":"2017-05-24 17:39:30","gas_type_sub_name":null,"work_subtask_id":1000000001700,"df":0,"gas_value":"5.00","orgid":808212,"gas_type":"oxygen","gas_detect_id":1000000001280,"updated_by":1000,"tenantid":1,"updated_dt":"2017-05-24 17:39:30","gas_detect_line_id":1000000000900,"updated_by_name":"系统管理员","dataStatus":0},{"tableName":"rmt_work_gas_detect_line","created_by":1000,"gas_type_sub":"combus_01","ts":1495618770773,"gas_name":null,"ver":1,"created_by_name":"系统管理员","isenable":1,"product_flag":2,"created_dt":"2017-05-24 17:39:30","gas_type_sub_name":null,"work_subtask_id":1000000001700,"df":0,"gas_value":"1.00","orgid":808212,"gas_type":"combus","gas_detect_id":1000000001280,"updated_by":1000,"tenantid":1,"updated_dt":"2017-05-24 17:39:30","gas_detect_line_id":1000000000901,"updated_by_name":"系统管理员","dataStatus":0},{"tableName":"rmt_work_gas_detect_line","created_by":1000,"gas_type_sub":"poison_01","ts":1495618770773,"gas_name":null,"ver":1,"created_by_name":"系统管理员","isenable":1,"product_flag":2,"created_dt":"2017-05-24 17:39:30","gas_type_sub_name":null,"work_subtask_id":1000000001700,"df":0,"gas_value":"7.00","orgid":808212,"gas_type":"poison","gas_detect_id":1000000001280,"updated_by":1000,"tenantid":1,"updated_dt":"2017-05-24 17:39:30","gas_detect_line_id":1000000000902,"updated_by_name":"系统管理员","dataStatus":0}]
     */

    private Object tableName;
    private Object columnValues;
    private int dataStatus;
    private String detect_site;
    private long work_subtask_id;
    private int qualified;
    private String detect_time;
    private List<VoListBean> voList;

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

    public String getDetect_site() {
        return detect_site;
    }

    public void setDetect_site(String detect_site) {
        this.detect_site = detect_site;
    }

    public long getWork_subtask_id() {
        return work_subtask_id;
    }

    public void setWork_subtask_id(long work_subtask_id) {
        this.work_subtask_id = work_subtask_id;
    }

    public int getQualified() {
        return qualified;
    }

    public void setQualified(int qualified) {
        this.qualified = qualified;
    }

    public String getDetect_time() {
        return detect_time;
    }

    public void setDetect_time(String detect_time) {
        this.detect_time = detect_time;
    }

    public List<VoListBean> getVoList() {
        return voList;
    }

    public void setVoList(List<VoListBean> voList) {
        this.voList = voList;
    }

    public static class VoListBean extends SuperEntity{
        /**
         * tableName : rmt_work_gas_detect_line
         * created_by : 1000
         * gas_type_sub : oxygen_01
         * ts : 1495618770773
         * gas_name : null
         * ver : 1
         * created_by_name : 系统管理员
         * isenable : 1
         * product_flag : 2
         * created_dt : 2017-05-24 17:39:30
         * gas_type_sub_name : null
         * work_subtask_id : 1000000001700
         * df : 0
         * gas_value : 5.00
         * orgid : 808212
         * gas_type : oxygen
         * gas_detect_id : 1000000001280
         * updated_by : 1000
         * tenantid : 1
         * updated_dt : 2017-05-24 17:39:30
         * gas_detect_line_id : 1000000000900
         * updated_by_name : 系统管理员
         * dataStatus : 0
         */

        private String tableName;
        private long created_by;
        private String gas_type_sub;
        private long ts;
        private Object gas_name;
        private int ver;
        private String created_by_name;
        private int isenable;
        private int product_flag;
        private String created_dt;
        private String gas_type_sub_name;
        private long work_subtask_id;
        private int df;
        private String gas_value;
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

        public Object getGas_name() {
            return gas_name;
        }

        public void setGas_name(Object gas_name) {
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

        public String getGas_value() {
            return gas_value;
        }

        public void setGas_value(String gas_value) {
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
