package com.hd.hse.entity.conf;

import com.hd.hse.common.entity.SuperEntity;

/**
 * 用电设备台账
 * created by yangning on 2017/4/25 18:05.
 */

public class RmtConfElecEqpt extends SuperEntity{


    private int quantity;
    private boolean choiced;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isChoiced() {
        return choiced;
    }

    public void setChoiced(boolean choiced) {
        this.choiced = choiced;
    }

    /**
     * tableName : rmt_conf_elec_eqpt
     * created_by : 1000
     * model : fh
     * equipment_name : fhfhgf
     * ts : 1492086492307
     * ver : 1
     * created_by_name : 测试用户
     * isenable : 0
     * product_flag : 2
     * created_dt : 2017-04-13 20:27:13
     * permit_info_id : 0
     * df : 0
     * orgid : 808212
     * elec_eqpt_id : 1000000000020
     * elec_load : 24.00
     * updated_by : 1000
     * tenantid : 1
     * updated_dt : 2017-04-13 20:28:10
     * updated_by_name : 测试用户
     * dataStatus : 0
     */

    private String tableName;
    private long created_by;
    private String model;
    private String equipment_name;
    private long ts;
    private int ver;
    private String created_by_name;
    private int isenable;
    private int product_flag;
    private String created_dt;
    private long permit_info_id;
    private int df;
    private long orgid;
    private long elec_eqpt_id;
    private String elec_load;
    private long updated_by;
    private long tenantid;
    private String updated_dt;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
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

    public long getPermit_info_id() {
        return permit_info_id;
    }

    public void setPermit_info_id(long permit_info_id) {
        this.permit_info_id = permit_info_id;
    }

    public int getDf() {
        return df;
    }

    public void setDf(int df) {
        this.df = df;
    }

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

    public long getElec_eqpt_id() {
        return elec_eqpt_id;
    }

    public void setElec_eqpt_id(long elec_eqpt_id) {
        this.elec_eqpt_id = elec_eqpt_id;
    }

    public String getElec_load() {
        return elec_load;
    }

    public void setElec_load(String elec_load) {
        this.elec_load = elec_load;
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
