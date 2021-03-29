package com.hd.hse.entity.conf;

/**
 * 气体检测配置
 * created by yangning on 2017/4/25 17:59.
 */

public class RmtConfGasIndex {


    /**
     * tableName : rmt_conf_gas_index
     * columnValues : null
     * dataStatus : 0
     * ver : 1
     * created_by : 1000
     * created_dt : 2017-04-19 09:32:11
     * updated_by : 1000
     * updated_dt : 2017-04-19 15:28:25
     * df : 0
     * tenantid : 1
     * ts : 1492586905073
     * gas_index_id : 1000000000180
     * permit_info_id : 1000000000040
     * created_by_name : 测试用户
     * updated_by_name : 测试用户
     * isenable : 1
     * product_flag : 2
     * orgid : 808212
     * gas_type : oxygen
     * gas_name : 氧气浓度%
     * gas_type_sub : oxygen_01
     * gas_type_sub_name : 氧气%
     * upperlimit : 12.00
     * lowerlimit : 1.00
     * boundary : 1
     */

    private String tableName;
    private Object columnValues;
    private int dataStatus;
    private int ver;
    private int created_by;
    private String created_dt;
    private int updated_by;
    private String updated_dt;
    private int df;
    private int tenantid;
    private long ts;
    private long gas_index_id;
    private long permit_info_id;
    private String created_by_name;
    private String updated_by_name;
    private int isenable;
    private int product_flag;
    private int orgid;
    private String gas_type;
    private String gas_name;
    private String gas_type_sub;
    private String gas_type_sub_name;
    private String upperlimit;
    private String lowerlimit;
    private int boundary;

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

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public int getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(int updated_by) {
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

    public long getGas_index_id() {
        return gas_index_id;
    }

    public void setGas_index_id(long gas_index_id) {
        this.gas_index_id = gas_index_id;
    }

    public long getPermit_info_id() {
        return permit_info_id;
    }

    public void setPermit_info_id(long permit_info_id) {
        this.permit_info_id = permit_info_id;
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

    public int getOrgid() {
        return orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }

    public String getGas_type() {
        return gas_type;
    }

    public void setGas_type(String gas_type) {
        this.gas_type = gas_type;
    }

    public String getGas_name() {
        return gas_name;
    }

    public void setGas_name(String gas_name) {
        this.gas_name = gas_name;
    }

    public String getGas_type_sub() {
        return gas_type_sub;
    }

    public void setGas_type_sub(String gas_type_sub) {
        this.gas_type_sub = gas_type_sub;
    }

    public String getGas_type_sub_name() {
        return gas_type_sub_name;
    }

    public void setGas_type_sub_name(String gas_type_sub_name) {
        this.gas_type_sub_name = gas_type_sub_name;
    }

    public String getUpperlimit() {
        return upperlimit;
    }

    public void setUpperlimit(String upperlimit) {
        this.upperlimit = upperlimit;
    }

    public String getLowerlimit() {
        return lowerlimit;
    }

    public void setLowerlimit(String lowerlimit) {
        this.lowerlimit = lowerlimit;
    }

    public int getBoundary() {
        return boundary;
    }

    public void setBoundary(int boundary) {
        this.boundary = boundary;
    }
}
