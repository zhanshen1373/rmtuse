package com.hd.hse.entity.workorder;

/**
 * 条款内容
 * created by yangning on 2019/10/31 14:34.
 */
public class TermContent extends BaseItem {

    /**
     * tableName : rmt_violationterm
     * content : 条款内容
     * violationterm_id : 1000000000000
     * orgid : null
     * score : 1
     * isenable : 1
     * created_by_name : 系统管理员
     * product_flag : null
     * updated_by_name : 系统管理员
     * term_code : WZTK2019103100001
     * term_type : type_one
     * dataStatus : 0
     * ver : 1
     * created_by : 1000
     * created_dt : 2019-10-31 13:53:34
     * updated_by : 1000
     * updated_dt : 2019-10-31 13:58:08
     * df : 0
     * tenantid : 1
     * ts : 1572501489237
     */

    private String tableName;
    private String content;
    private Long violationterm_id;
    private Object orgid;
    private Integer score;
    private Long isenable;
    private String created_by_name;
    private Object product_flag;
    private String updated_by_name;
    private String term_code;
    private String term_type;
    private Long dataStatus;
    private Long ver;
    private Long created_by;
    private String created_dt;
    private Long updated_by;
    private String updated_dt;
    private Long df;
    private Long tenantid;
    private Long ts;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getViolationterm_id() {
        return violationterm_id;
    }

    public void setViolationterm_id(Long violationterm_id) {
        this.violationterm_id = violationterm_id;
    }

    public Object getOrgid() {
        return orgid;
    }

    public void setOrgid(Object orgid) {
        this.orgid = orgid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getIsenable() {
        return isenable;
    }

    public void setIsenable(Long isenable) {
        this.isenable = isenable;
    }

    public String getCreated_by_name() {
        return created_by_name;
    }

    public void setCreated_by_name(String created_by_name) {
        this.created_by_name = created_by_name;
    }

    public Object getProduct_flag() {
        return product_flag;
    }

    public void setProduct_flag(Object product_flag) {
        this.product_flag = product_flag;
    }

    public String getUpdated_by_name() {
        return updated_by_name;
    }

    public void setUpdated_by_name(String updated_by_name) {
        this.updated_by_name = updated_by_name;
    }

    public String getTerm_code() {
        return term_code;
    }

    public void setTerm_code(String term_code) {
        this.term_code = term_code;
    }

    public String getTerm_type() {
        return term_type;
    }

    public void setTerm_type(String term_type) {
        this.term_type = term_type;
    }

    public Long getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Long dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Long getVer() {
        return ver;
    }

    public void setVer(Long ver) {
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

    public Long getDf() {
        return df;
    }

    public void setDf(Long df) {
        this.df = df;
    }

    public Long getTenantid() {
        return tenantid;
    }

    public void setTenantid(Long tenantid) {
        this.tenantid = tenantid;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    @Override
    public String getItemContent() {
        return content;
    }
}
