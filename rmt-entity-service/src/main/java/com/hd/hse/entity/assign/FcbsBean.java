package com.hd.hse.entity.assign;

import com.hd.hse.common.entity.SuperEntity;

public class FcbsBean extends SuperEntity {


    /**
     * tableName : sy_org
     * columnValues : null
     * dataStatus : 0
     * ver : 1
     * created_by : 1
     * created_dt : 2017-07-24 17:49:27
     * updated_by : 1
     * updated_dt : 2017-07-24 18:09:45
     * df : 0
     * tenantid : 1
     * ts : null
     * orgid : 2000000000104
     * orgcode : NX010304
     * orgname : 电仪部
     * isenable : 1
     * parentid : 2000000000103
     * orglayer : null
     * isleaf : 0
     * innercode : 000000010000
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
    private Object ts;
    private long orgid;
    private String orgcode;
    private String orgname;
    private int isenable;
    private long parentid;
    private Object orglayer;
    private int isleaf;
    private String innercode;

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

    public Object getTs() {
        return ts;
    }

    public void setTs(Object ts) {
        this.ts = ts;
    }

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public int getIsenable() {
        return isenable;
    }

    public void setIsenable(int isenable) {
        this.isenable = isenable;
    }

    public long getParentid() {
        return parentid;
    }

    public void setParentid(long parentid) {
        this.parentid = parentid;
    }

    public Object getOrglayer() {
        return orglayer;
    }

    public void setOrglayer(Object orglayer) {
        this.orglayer = orglayer;
    }

    public int getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(int isleaf) {
        this.isleaf = isleaf;
    }

    public String getInnercode() {
        return innercode;
    }

    public void setInnercode(String innercode) {
        this.innercode = innercode;
    }
}
