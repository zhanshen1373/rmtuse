package com.hd.hse.entity.workorder;

/**
 * 条款分类
 * created by yangning on 2019/10/31 14:52.
 */
public class TermType extends BaseItem{

    /**
     * tableName : null
     * columnValues : null
     * dataStatus : 0
     * code : type_one
     * name : 条款一
     * dictdataid : 1000000001320
     * isleaf : null
     * children : null
     */

    private Object tableName;
    private Object columnValues;
    private Long dataStatus;
    private String code;
    private String name;
    private Long dictdataid;
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

    public Long getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Long dataStatus) {
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

    public Long getDictdataid() {
        return dictdataid;
    }

    public void setDictdataid(Long dictdataid) {
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

    @Override
    public String getItemContent() {
        return name;
    }
}
