package com.hd.hse.entity.workorder;

public class RmtTaskOrganizeListQuery {


    /**
     * tableName : null
     * num : 1
     * orgid : 2000000000100
     * territorial_unit_name : 中国石油宁夏石化公司
     * territorial_unit_id : 2000000000100
     * dataStatus : 0
     */

    private Object tableName;
    private int num;
    private long orgid;
    private String territorial_unit_name;
    private long territorial_unit_id;
    private int dataStatus;

    public Object getTableName() {
        return tableName;
    }

    public void setTableName(Object tableName) {
        this.tableName = tableName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

    public String getTerritorial_unit_name() {
        return territorial_unit_name;
    }

    public void setTerritorial_unit_name(String territorial_unit_name) {
        this.territorial_unit_name = territorial_unit_name;
    }

    public long getTerritorial_unit_id() {
        return territorial_unit_id;
    }

    public void setTerritorial_unit_id(long territorial_unit_id) {
        this.territorial_unit_id = territorial_unit_id;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }
}
