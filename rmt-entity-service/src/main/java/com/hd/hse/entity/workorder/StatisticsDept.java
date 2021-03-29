package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;

import java.util.List;

/**
 * Created by dubojian on 2017/12/4.
 */

public class StatisticsDept extends SuperEntity {


    /**
     * tableName : sy_org
     * columnValues : null
     * dataStatus : 0
     * ver : 1
     * created_by : null
     * created_dt : 2017-12-04 09:46:19
     * updated_by : null
     * updated_dt : 2017-12-04 09:46:19
     * df : 0
     * tenantid : null
     * ts : null
     * orgid : 1000000000000
     * orgcode : NX
     * orgname : 中国石油宁夏石化公司
     * isenable : 1
     * parentid : null
     * orglayer : null
     * isleaf : 0
     * innercode : 000S
     */

    private String tableName;
    private Object columnValues;
    private int dataStatus;
    private int ver;
    private Object created_by;
    private String created_dt;
    private Object updated_by;
    private String updated_dt;
    private int df;
    private Object tenantid;
    private Object ts;
    private long orgid;
    private String orgcode;
    private String orgname;
    private int isenable;
    private Object parentid;
    private Object orglayer;
    private int isleaf;
    private String innercode;
    private List<StatisticsDept> countOrgMVOList;
    //本地选中的属性
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

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

    public Object getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Object created_by) {
        this.created_by = created_by;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public Object getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(Object updated_by) {
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

    public Object getTenantid() {
        return tenantid;
    }

    public void setTenantid(Object tenantid) {
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

    public Object getParentid() {
        return parentid;
    }

    public void setParentid(Object parentid) {
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

    public List<StatisticsDept> getCountOrgMVOList() {
        return countOrgMVOList;
    }

    public void setCountOrgMVOList(List<StatisticsDept> countOrgMVOList) {
        this.countOrgMVOList = countOrgMVOList;
    }


}
