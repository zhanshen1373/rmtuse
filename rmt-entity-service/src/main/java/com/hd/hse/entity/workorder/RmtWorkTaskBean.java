package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;

/**
 * Created by liuting on 2017/5/15.
 * 主任务的类
 */

public class RmtWorkTaskBean extends SuperEntity {

    /**
     * tableName : rmt_work_task
     * ts : 1493970648590
     * owner_name : null
     * work_site_name : 二公司
     * work_unit_id : 1690115
     * created_by_name : 测试用户
     * est_start_time : 2017-05-09 15:50:32
     * project_type : null
     * work_reason : null
     * related_unit_id : null
     * work_site_id : 1690117
     * created_by : 1000
     * act_end_time : null
     * element : bldscaf,rmvscaf,rmvinsd
     * equipment_name : null
     * work_unit_name : 五公司
     * status : null
     * territorial_unit_name : 五公司
     * work_type : gen,hot,cfd
     * equipment_tag_no : null
     * est_end_time : 2017-05-31 15:50:35
     * created_dt : 2017-05-05 15:50:45
     * equipment_id : null
     * medium : null
     * act_start_time : null
     * equipment_type : null
     * owner_id : null
     * territorial_unit_id : 1690120
     * work_name : 11
     * work_task_id : 1000000000260
     * work_content : 23
     * related_unit_name : null
     * dataStatus : 0
     */

    private String tableName;
    private long ts;
    private Object owner_name;
    private String work_site_name;
    private long work_unit_id;
    private String created_by_name;
    private String est_start_time;
    private Object project_type;
    private Object work_reason;
    private String related_unit_id;
    private long work_site_id;
    private long created_by;
    private Object act_end_time;
    private String element;
    private Object equipment_name;
    private String work_unit_name;
    private Object status;
    private String status_name;
    private String territorial_unit_name;
    private String work_type;
    private Object equipment_tag_no;
    private String est_end_time;
    private String created_dt;
    private long equipment_id;
    private Object medium;
    private Object act_start_time;
    private Object equipment_type;
    private long owner_id;
    private long territorial_unit_id;
    private String work_name;
    private long work_task_id;
    private String work_content;
    private Object related_unit_name;
    private int dataStatus;
    private boolean isallowclose;
    private Boolean factory;

    public Boolean getFactory() {
        return factory;
    }

    public void setFactory(Boolean factory) {
        this.factory = factory;
    }

    public boolean isallowclose() {
        return isallowclose;
    }

    public void setIsallowclose(boolean isallowclose) {
        this.isallowclose = isallowclose;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
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

    public Object getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(Object owner_name) {
        this.owner_name = owner_name;
    }

    public String getWork_site_name() {
        return work_site_name;
    }

    public void setWork_site_name(String work_site_name) {
        this.work_site_name = work_site_name;
    }

    public long getWork_unit_id() {
        return work_unit_id;
    }

    public void setWork_unit_id(long work_unit_id) {
        this.work_unit_id = work_unit_id;
    }

    public String getCreated_by_name() {
        return created_by_name;
    }

    public void setCreated_by_name(String created_by_name) {
        this.created_by_name = created_by_name;
    }

    public String getEst_start_time() {
        return est_start_time;
    }

    public void setEst_start_time(String est_start_time) {
        this.est_start_time = est_start_time;
    }

    public Object getProject_type() {
        return project_type;
    }

    public void setProject_type(Object project_type) {
        this.project_type = project_type;
    }

    public Object getWork_reason() {
        return work_reason;
    }

    public void setWork_reason(Object work_reason) {
        this.work_reason = work_reason;
    }

    public String getRelated_unit_id() {
        return related_unit_id;
    }

    public void setRelated_unit_id(String related_unit_id) {
        this.related_unit_id = related_unit_id;
    }

    public long getWork_site_id() {
        return work_site_id;
    }

    public void setWork_site_id(long work_site_id) {
        this.work_site_id = work_site_id;
    }

    public long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(long created_by) {
        this.created_by = created_by;
    }

    public Object getAct_end_time() {
        return act_end_time;
    }

    public void setAct_end_time(Object act_end_time) {
        this.act_end_time = act_end_time;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Object getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(Object equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getWork_unit_name() {
        return work_unit_name;
    }

    public void setWork_unit_name(String work_unit_name) {
        this.work_unit_name = work_unit_name;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
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

    public Object getEquipment_tag_no() {
        return equipment_tag_no;
    }

    public void setEquipment_tag_no(Object equipment_tag_no) {
        this.equipment_tag_no = equipment_tag_no;
    }

    public String getEst_end_time() {
        return est_end_time;
    }

    public void setEst_end_time(String est_end_time) {
        this.est_end_time = est_end_time;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public long getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(long equipment_id) {
        this.equipment_id = equipment_id;
    }

    public Object getMedium() {
        return medium;
    }

    public void setMedium(Object medium) {
        this.medium = medium;
    }

    public Object getAct_start_time() {
        return act_start_time;
    }

    public void setAct_start_time(Object act_start_time) {
        this.act_start_time = act_start_time;
    }

    public Object getEquipment_type() {
        return equipment_type;
    }

    public void setEquipment_type(Object equipment_type) {
        this.equipment_type = equipment_type;
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

    public Object getRelated_unit_name() {
        return related_unit_name;
    }

    public void setRelated_unit_name(Object related_unit_name) {
        this.related_unit_name = related_unit_name;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }
}


