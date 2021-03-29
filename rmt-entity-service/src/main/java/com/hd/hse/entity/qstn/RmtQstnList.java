package com.hd.hse.entity.qstn;

import com.hd.hse.common.entity.SuperEntity;

import java.util.List;

/**
 * created by yangning on 2017/6/6 10:18.
 */

public class RmtQstnList extends SuperEntity {


    /**
     * tableName : rmt_work_qstn_list
     * columnValues : null
     * dataStatus : 0
     * ver : 1
     * created_by : 1000
     * created_dt : 2017-07-03 12:01:20
     * updated_by : 1000
     * updated_dt : 2017-07-03 12:01:20
     * df : 0
     * tenantid : 1
     * ts : 1499054480912
     * imageList : []//md5编码
     * qstn_list_id : 1000000000821
     * work_subtask_id : 126655265
     * created_by_name : 系统管理员
     * updated_by_name : 系统管理员
     * isenable : 1
     * product_flag : 2
     * orgid : 1
     * description : 测试
     * site : 1
     * qstn_type : null
     * qstn_unit_id : 126655265
     * qstn_unit_name : HD
     * violator_id : 126655265
     * violator_name : 违章人员
     * owner_id : 126655265
     * owner_name : 现场负责人
     * inspector_id : null
     * inspector_name : null
     * inspect_unit_id : null
     * inspect_unit_name : null
     * inspect_time : null
     * pic : 2
     */

    private String tableName;
    private Object columnValues;
    private int dataStatus;
    private int ver;
    private long created_by;
    private String created_dt;
    private long updated_by;
    private String updated_dt;
    private int df;
    private long tenantid;
    private long ts;
    private Long qstn_list_id;
    private long work_subtask_id;
    private String work_subtask_name;
    private String created_by_name;
    private String updated_by_name;
    private int isenable;
    private int product_flag;
    private long orgid;
    private String description;
    private String site;
    private String qstn_type;
    private long qstn_unit_id;
    private String qstn_unit_name;
    private String violator_id;
    private String violator_name;
    private long owner_id;
    private String owner_name;
    private long inspector_id;
    private Long violationterm_id; //违章条款id
    private String term_code;     //违章条款编码
    private String term_type;     //违章条款分类
    private String term_content;  //条款内容
    private Integer score;         //分数

    public Long getViolationterm_id() {
        return violationterm_id;
    }

    public void setViolationterm_id(Long violationterm_id) {
        this.violationterm_id = violationterm_id;
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

    public String getTerm_content() {
        return term_content;
    }

    public void setTerm_content(String term_content) {
        this.term_content = term_content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getWork_subtask_name() {
        return work_subtask_name;
    }

    public void setWork_subtask_name(String work_subtask_name) {
        this.work_subtask_name = work_subtask_name;
    }

    private String inspector_name;
    private long inspect_unit_id;
    private String inspect_unit_name;
    private String inspect_time;
    private int pic;
    private List<String> imageList;
    private String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(long created_by) {
        this.created_by = created_by;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public long getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(long updated_by) {
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

    public long getTenantid() {
        return tenantid;
    }

    public void setTenantid(long tenantid) {
        this.tenantid = tenantid;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public Long getQstn_list_id() {
        return qstn_list_id;
    }

    public void setQstn_list_id(Long qstn_list_id) {
        this.qstn_list_id = qstn_list_id;
    }

    public long getWork_subtask_id() {
        return work_subtask_id;
    }

    public void setWork_subtask_id(long work_subtask_id) {
        this.work_subtask_id = work_subtask_id;
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

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getQstn_type() {
        return qstn_type;
    }

    public void setQstn_type(String qstn_type) {
        this.qstn_type = qstn_type;
    }

    public long getQstn_unit_id() {
        return qstn_unit_id;
    }

    public void setQstn_unit_id(long qstn_unit_id) {
        this.qstn_unit_id = qstn_unit_id;
    }

    public String getQstn_unit_name() {
        return qstn_unit_name;
    }

    public void setQstn_unit_name(String qstn_unit_name) {
        this.qstn_unit_name = qstn_unit_name;
    }

    public String getViolator_id() {
        return violator_id;
    }

    public void setViolator_id(String violator_id) {
        this.violator_id = violator_id;
    }

    public String getViolator_name() {
        return violator_name;
    }

    public void setViolator_name(String violator_name) {
        this.violator_name = violator_name;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public long getInspector_id() {
        return inspector_id;
    }

    public void setInspector_id(long inspector_id) {
        this.inspector_id = inspector_id;
    }

    public String getInspector_name() {
        return inspector_name;
    }

    public void setInspector_name(String inspector_name) {
        this.inspector_name = inspector_name;
    }

    public long getInspect_unit_id() {
        return inspect_unit_id;
    }

    public void setInspect_unit_id(long inspect_unit_id) {
        this.inspect_unit_id = inspect_unit_id;
    }

    public String getInspect_unit_name() {
        return inspect_unit_name;
    }

    public void setInspect_unit_name(String inspect_unit_name) {
        this.inspect_unit_name = inspect_unit_name;
    }

    public String getInspect_time() {
        return inspect_time;
    }

    public void setInspect_time(String inspect_time) {
        this.inspect_time = inspect_time;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
