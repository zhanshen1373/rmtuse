package com.hd.hse.entity.dict;

import com.hd.hse.common.entity.SuperEntity;

/**
 * created by yangning on 2017/5/31 13:25.
 * 字典类
 */

public class RmtDict extends SuperEntity{


    /**
     * tableName : null
     * columnValues : null
     * dataStatus : 0
     * code : close_01
     * name : 完工关闭
     * dictdataid : 1000000000600
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
    private int isselected;

    public int getIsselected() {
        return isselected;
    }

    public void setIsselected(int isselected) {
        this.isselected = isselected;
    }

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
