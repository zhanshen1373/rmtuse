package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.entity.conf.RmtConfElecEqpt;

import java.math.BigDecimal;
import java.util.List;

/**
 * 作业申请or作业台账
 * created by yangning on 2017/4/25 16:12.
 */

public class RmtWorkPermit extends SuperEntity {
    private List<RmtConfElecEqpt> workElecEqptVOList;
    /**
     * tableName : rmt_work_permit
     * ts : 1495521206397
     * owner_name : 张小明
     * est_start_time : null
     * work_reason : 0518移动端测试数据
     * upload_file : 0
     * hst_ts : 0
     * orgid : 808212
     * hst_rated_load : null
     * work_site_id : 1689533
     * hst_object_volume : null
     * tenantid : 1
     * updated_dt : 2017-05-23 14:33:26
     * created_by : 1000
     * work_unit_name : 中石化
     * status : null
     * est_end_time : null
     * act_start_time : null
     * hst_angle : null
     * elec_voltage : null
     * work_name : 0518移动端测试数据
     * work_task_id : 1000000000381
     * work_content : 0518移动端测试数据
     * related_unit_name : 一公司
     * work_permit_no : GEN2017051800010
     * elec_power_access_point : null
     * work_site_name : 中石化
     * elec_total_load : null
     * work_unit_id : 1689533
     * hst_radius : null
     * ver : 1
     * created_by_name : 系统管理员
     * hst_object_weight : null
     * product_flag : 2
     * project_type : prj13
     * df : 0
     * related_unit_id : 1690393
     * hst_object_name : null
     * updated_by : 1000
     * elec_purpose : null
     * act_end_time : null
     * territorial_unit_name : 中石化
     * work_type : gen
     * isenable : 1
     * work_permit_id : 1000000001260
     * created_dt : 2017-05-23 14:33:26
     * work_subtask_id : 1000000001700
     * owner_id : 1693109
     * territorial_unit_id : 1689533
     * hst_height : null
     * hst_lift_weight : null
     * updated_by_name : 系统管理员
     * dataStatus : 0
     */

    private String tableName;
    private long ts;
    private String owner_name;
    private Object est_start_time;
    private String work_reason;
    private int upload_file;
    private long orgid;
    private long work_site_id;
    private long tenantid;
    private String updated_dt;
    private long created_by;
    private String work_unit_name;
    private String status;
    private Object est_end_time;
    private Object act_start_time;
    private Object elec_voltage;
    private String work_name;
    private long work_task_id;
    private String work_content;
    private String related_unit_name;
    private String work_permit_no;
    private Object elec_power_access_point;
    private String work_site_name;
    private Double elec_total_load;
    private long work_unit_id;
    private int ver;
    private String created_by_name;
    private int product_flag;
    private String project_type;
    private int df;
    private long related_unit_id;
    private long updated_by;
    private Object elec_purpose;
    private Object act_end_time;
    private String territorial_unit_name;
    private String work_type;
    private int isenable;
    private long work_permit_id;
    private String created_dt;
    private long work_subtask_id;
    private long owner_id;
    private long territorial_unit_id;

    private String updated_by_name;
    private int dataStatus;
    /**
     * 关闭原因
     */
    private String stop_reason;

    /**
     * 关闭说明
     */
    private String stop_comment;


    private String hst_code;
    private String hst_desc;
    //吊装信息
    private String hst_object_name;
    private String hst_object_volume;
    private BigDecimal hst_object_weight;
    private BigDecimal hst_lift_weight;
    private BigDecimal hst_radius;
    private BigDecimal hst_height;
    private BigDecimal hst_rated_load;
    private BigDecimal hst_angle;
    private int hst_ts;


    public String getHst_code() {
        return hst_code;
    }

    public void setHst_code(String hst_code) {
        this.hst_code = hst_code;
    }

    public String getHst_desc() {
        return hst_desc;
    }

    public void setHst_desc(String hst_desc) {
        this.hst_desc = hst_desc;
    }

    public String getHst_object_name() {
        return hst_object_name;
    }

    public void setHst_object_name(String hst_object_name) {
        this.hst_object_name = hst_object_name;
    }

    public String getHst_object_volume() {
        return hst_object_volume;
    }

    public void setHst_object_volume(String hst_object_volume) {
        this.hst_object_volume = hst_object_volume;
    }

    public BigDecimal getHst_object_weight() {
        return hst_object_weight;
    }

    public void setHst_object_weight(BigDecimal hst_object_weight) {
        this.hst_object_weight = hst_object_weight;
    }

    public BigDecimal getHst_lift_weight() {
        return hst_lift_weight;
    }

    public void setHst_lift_weight(BigDecimal hst_lift_weight) {
        this.hst_lift_weight = hst_lift_weight;
    }

    public BigDecimal getHst_radius() {
        return hst_radius;
    }

    public void setHst_radius(BigDecimal hst_radius) {
        this.hst_radius = hst_radius;
    }

    public BigDecimal getHst_height() {
        return hst_height;
    }

    public void setHst_height(BigDecimal hst_height) {
        this.hst_height = hst_height;
    }

    public BigDecimal getHst_rated_load() {
        return hst_rated_load;
    }

    public void setHst_rated_load(BigDecimal hst_rated_load) {
        this.hst_rated_load = hst_rated_load;
    }

    public BigDecimal getHst_angle() {
        return hst_angle;
    }

    public void setHst_angle(BigDecimal hst_angle) {
        this.hst_angle = hst_angle;
    }

    public int getHst_ts() {
        return hst_ts;
    }

    public void setHst_ts(int hst_ts) {
        this.hst_ts = hst_ts;
    }

    public String getStop_reason() {
        return stop_reason;
    }

    public void setStop_reason(String stop_reason) {
        this.stop_reason = stop_reason;
    }

    public String getStop_comment() {
        return stop_comment;
    }

    public void setStop_comment(String stop_comment) {
        this.stop_comment = stop_comment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public Object getEst_start_time() {
        return est_start_time;
    }

    public void setEst_start_time(Object est_start_time) {
        this.est_start_time = est_start_time;
    }

    public String getWork_reason() {
        return work_reason;
    }

    public void setWork_reason(String work_reason) {
        this.work_reason = work_reason;
    }

    public int getUpload_file() {
        return upload_file;
    }

    public void setUpload_file(int upload_file) {
        this.upload_file = upload_file;
    }


    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }


    public long getWork_site_id() {
        return work_site_id;
    }

    public void setWork_site_id(long work_site_id) {
        this.work_site_id = work_site_id;
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

    public long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(long created_by) {
        this.created_by = created_by;
    }

    public String getWork_unit_name() {
        return work_unit_name;
    }

    public void setWork_unit_name(String work_unit_name) {
        this.work_unit_name = work_unit_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getEst_end_time() {
        return est_end_time;
    }

    public void setEst_end_time(Object est_end_time) {
        this.est_end_time = est_end_time;
    }

    public Object getAct_start_time() {
        return act_start_time;
    }

    public void setAct_start_time(Object act_start_time) {
        this.act_start_time = act_start_time;
    }


    public Object getElec_voltage() {
        return elec_voltage;
    }

    public void setElec_voltage(Object elec_voltage) {
        this.elec_voltage = elec_voltage;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public long getWork_task_id() {
        return work_task_id;
    }

    public void setWork_task_id(long work_task_id) {
        this.work_task_id = work_task_id;
    }

    public String getWork_content() {
        return work_content;
    }

    public void setWork_content(String work_content) {
        this.work_content = work_content;
    }

    public String getRelated_unit_name() {
        return related_unit_name;
    }

    public void setRelated_unit_name(String related_unit_name) {
        this.related_unit_name = related_unit_name;
    }

    public String getWork_permit_no() {
        return work_permit_no;
    }

    public void setWork_permit_no(String work_permit_no) {
        this.work_permit_no = work_permit_no;
    }

    public Object getElec_power_access_point() {
        return elec_power_access_point;
    }

    public void setElec_power_access_point(Object elec_power_access_point) {
        this.elec_power_access_point = elec_power_access_point;
    }

    public String getWork_site_name() {
        return work_site_name;
    }

    public void setWork_site_name(String work_site_name) {
        this.work_site_name = work_site_name;
    }

    public Double getElec_total_load() {
        return elec_total_load;
    }

    public void setElec_total_load(Double elec_total_load) {
        this.elec_total_load = elec_total_load;
    }

    public long getWork_unit_id() {
        return work_unit_id;
    }

    public void setWork_unit_id(long work_unit_id) {
        this.work_unit_id = work_unit_id;
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


    public int getProduct_flag() {
        return product_flag;
    }

    public void setProduct_flag(int product_flag) {
        this.product_flag = product_flag;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public int getDf() {
        return df;
    }

    public void setDf(int df) {
        this.df = df;
    }

    public long getRelated_unit_id() {
        return related_unit_id;
    }

    public void setRelated_unit_id(long related_unit_id) {
        this.related_unit_id = related_unit_id;
    }


    public long getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(long updated_by) {
        this.updated_by = updated_by;
    }

    public Object getElec_purpose() {
        return elec_purpose;
    }

    public void setElec_purpose(Object elec_purpose) {
        this.elec_purpose = elec_purpose;
    }

    public Object getAct_end_time() {
        return act_end_time;
    }

    public void setAct_end_time(Object act_end_time) {
        this.act_end_time = act_end_time;
    }

    public String getTerritorial_unit_name() {
        return territorial_unit_name;
    }

    public void setTerritorial_unit_name(String territorial_unit_name) {
        this.territorial_unit_name = territorial_unit_name;
    }

    public String getWork_type() {
        return work_type;
    }

    public void setWork_type(String work_type) {
        this.work_type = work_type;
    }

    public int getIsenable() {
        return isenable;
    }

    public void setIsenable(int isenable) {
        this.isenable = isenable;
    }

    public long getWork_permit_id() {
        return work_permit_id;
    }

    public void setWork_permit_id(long work_permit_id) {
        this.work_permit_id = work_permit_id;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public long getWork_subtask_id() {
        return work_subtask_id;
    }

    public void setWork_subtask_id(long work_subtask_id) {
        this.work_subtask_id = work_subtask_id;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public long getTerritorial_unit_id() {
        return territorial_unit_id;
    }

    public void setTerritorial_unit_id(long territorial_unit_id) {
        this.territorial_unit_id = territorial_unit_id;
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

    public List<RmtConfElecEqpt> getWorkElecEqptVOList() {
        return workElecEqptVOList;
    }

    public void setWorkElecEqptVOList(List<RmtConfElecEqpt> workElecEqptVOList) {
        this.workElecEqptVOList = workElecEqptVOList;
    }
}
