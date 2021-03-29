package com.hayden.hap.common.login.business;

/**
 * 用于登录时给密码加密传输用的密钥
 * Created by liuyang on 2017/2/27.
 */

public class LoginKey {
    /**
     * tableName : null
     * columnValues : null
     * dataStatus : 0
     * ver : 1
     * created_by : null
     * created_dt : 2017-02-22 15:05:29
     * updated_by : null
     * updated_dt : 2017-02-22 15:05:29
     * df : 0
     * tenantid : null
     * ts : null
     * stokenkey : cebd854aedd04189890d10cd37a94130
     * modulus : 00a090c25fd219f41e39f21f5be9a5a84aec45f7e47cc53e50b9184e1275a4669c280f88c84e6547678cff53af5d206039dd93b022a5fd2a15dd4c4d29b96189685451c1e2d7688c45dc64192b0a42ef58950fc27163f9b4238a03ac700845d2b1b345951b6a330d8cc4103fcde29a0abe75a7860b2eae8315b96a9244ca7263d1
     * publicExponent : 010001
     */

    private Object tableName;
    private Object columnValues;
    private int dataStatus;
    private int ver;
    private Long created_by;
    private String created_dt;
    private Long updated_by;
    private String updated_dt;
    private int df;
    private Long tenantid;
    private Object ts;
    private String stokenkey;
    private String modulus;
    private String publicExponent;
    private String encryptType;


    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
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

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public Long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Long created_by) {
        this.created_by = created_by;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public Long getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(Long updated_by) {
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

    public Long getTenantid() {
        return tenantid;
    }

    public void setTenantid(Long tenantid) {
        this.tenantid = tenantid;
    }

    public Object getTs() {
        return ts;
    }

    public void setTs(Object ts) {
        this.ts = ts;
    }

    public String getStokenkey() {
        return stokenkey;
    }

    public void setStokenkey(String stokenkey) {
        this.stokenkey = stokenkey;
    }

    public String getModulus() {
        return modulus;
    }

    public void setModulus(String modulus) {
        this.modulus = modulus;
    }

    public String getPublicExponent() {
        return publicExponent;
    }

    public void setPublicExponent(String publicExponent) {
        this.publicExponent = publicExponent;
    }
}
