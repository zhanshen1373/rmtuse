package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;

/**
 * 主任务
 * created by yangning on 2017/4/25 15:46.
 */

public class RmtWorkTask extends SuperEntity{
    private String tableName;
    private int equipment_id;
    private int dataStatus;
    private int ver;
    private int created_by;
    private String created_dt;
    private int updated_by;
    private String updated_dt;
    private int df;
    private int tenantid;
    private long ts;
    private long work_task_id;
    private String created_by_name;
    private String updated_by_name;
    private int isenable;
    private int product_flag;
    private int orgid;
    private String work_name;
    private String work_content;
    private int territorial_unit_id;
    private String territorial_unit_name;
    private int work_unit_id;
    private String work_unit_name;
    private int work_site_id;
    private String work_site_name;
    private String equipment_type;
    private String est_start_time;
    private String est_end_time;
    private String act_start_time;
    private String act_end_time;
    private int owner_id;
    private String owner_name;
    private Object equipment_code;
    private String medium;
    private String element;
    private String status;
    private int related_unit_id;
    private String related_unit_name;
    private String project_type;
    private String work_type;
    private String equipment_name;
    private String equipment_tag_no;
    private String pressure;
    private String temperature;
    private int ncs;
    private int nwmr;
    private int nms;
    private int nmcmr;
    private String work_reason;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(int equipment_id) {
        this.equipment_id = equipment_id;
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

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public long getWork_task_id() {
        return work_task_id;
    }

    public void setWork_task_id(long work_task_id) {
        this.work_task_id = work_task_id;
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

    public int getOrgid() {
        return orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public String getWork_content() {
        return work_content;
    }

    public void setWork_content(String work_content) {
        this.work_content = work_content;
    }

    public int getTerritorial_unit_id() {
        return territorial_unit_id;
    }

    public void setTerritorial_unit_id(int territorial_unit_id) {
        this.territorial_unit_id = territorial_unit_id;
    }

    public String getTerritorial_unit_name() {
        return territorial_unit_name;
    }

    public void setTerritorial_unit_name(String territorial_unit_name) {
        this.territorial_unit_name = territorial_unit_name;
    }

    public int getWork_unit_id() {
        return work_unit_id;
    }

    public void setWork_unit_id(int work_unit_id) {
        this.work_unit_id = work_unit_id;
    }

    public String getWork_unit_name() {
        return work_unit_name;
    }

    public void setWork_unit_name(String work_unit_name) {
        this.work_unit_name = work_unit_name;
    }

    public int getWork_site_id() {
        return work_site_id;
    }

    public void setWork_site_id(int work_site_id) {
        this.work_site_id = work_site_id;
    }

    public String getWork_site_name() {
        return work_site_name;
    }

    public void setWork_site_name(String work_site_name) {
        this.work_site_name = work_site_name;
    }

    public String getEquipment_type() {
        return equipment_type;
    }

    public void setEquipment_type(String equipment_type) {
        this.equipment_type = equipment_type;
    }

    public String getEst_start_time() {
        return est_start_time;
    }

    public void setEst_start_time(String est_start_time) {
        this.est_start_time = est_start_time;
    }

    public String getEst_end_time() {
        return est_end_time;
    }

    public void setEst_end_time(String est_end_time) {
        this.est_end_time = est_end_time;
    }

    public String getAct_start_time() {
        return act_start_time;
    }

    public void setAct_start_time(String act_start_time) {
        this.act_start_time = act_start_time;
    }

    public String getAct_end_time() {
        return act_end_time;
    }

    public void setAct_end_time(String act_end_time) {
        this.act_end_time = act_end_time;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public Object getEquipment_code() {
        return equipment_code;
    }

    public void setEquipment_code(Object equipment_code) {
        this.equipment_code = equipment_code;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRelated_unit_id() {
        return related_unit_id;
    }

    public void setRelated_unit_id(int related_unit_id) {
        this.related_unit_id = related_unit_id;
    }

    public String getRelated_unit_name() {
        return related_unit_name;
    }

    public void setRelated_unit_name(String related_unit_name) {
        this.related_unit_name = related_unit_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getWork_type() {
        return work_type;
    }

    public void setWork_type(String work_type) {
        this.work_type = work_type;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getEquipment_tag_no() {
        return equipment_tag_no;
    }

    public void setEquipment_tag_no(String equipment_tag_no) {
        this.equipment_tag_no = equipment_tag_no;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public int getNcs() {
        return ncs;
    }

    public void setNcs(int ncs) {
        this.ncs = ncs;
    }

    public int getNwmr() {
        return nwmr;
    }

    public void setNwmr(int nwmr) {
        this.nwmr = nwmr;
    }

    public int getNms() {
        return nms;
    }

    public void setNms(int nms) {
        this.nms = nms;
    }

    public int getNmcmr() {
        return nmcmr;
    }

    public void setNmcmr(int nmcmr) {
        this.nmcmr = nmcmr;
    }

    public String getWork_reason() {
        return work_reason;
    }

    public void setWork_reason(String work_reason) {
        this.work_reason = work_reason;
    }
}
