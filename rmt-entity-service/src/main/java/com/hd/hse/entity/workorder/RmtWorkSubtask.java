package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.entity.resultdata.RmtWorkGasDetectReslt;

import java.util.List;

/**
 * 分项任务
 * created by yangning on 2017/4/25 16:02.
 */

public class RmtWorkSubtask extends SuperEntity {


    /**
     * tableName : rmt_work_subtask
     * created_by : 1000
     * operator_name : null
     * act_end_time : null
     * work_unit_name : 五公司
     * status : inprg
     * operator_id : null
     * work_site_name : 二公司
     * territorial_unit_name : 五公司
     * work_type : gen,hot
     * est_end_time : 2017-05-12 11:33:11
     * work_unit_id : 1690115
     * est_start_time : 2017-05-10 11:33:09
     * created_by_name : 系统管理员
     * team : null
     * created_dt : 2017-05-12 11:36:56
     * act_start_time : null
     * work_subtask_id : 1000000001580
     * monitor_name : null
     * monitor_id : null
     * description : 123123
     * work_site_id : 1690117
     * territorial_unit_id : 1690120
     * work_task_id : 1000000000260
     * dataStatus : 0
     */

    protected String tableName;
    protected long created_by;
    protected Object operator_name;
    protected Object act_end_time;
    protected String work_unit_name;
    protected String status;
    protected String status_name;
    protected long operator_id;
    protected String work_site_name;
    protected String territorial_unit_name;
    protected String work_type;
    protected String est_end_time;
    protected long work_unit_id;
    protected String est_start_time;
    protected String created_by_name;
    protected Object team;
    protected String created_dt;
    protected Object act_start_time;
    protected long work_subtask_id;
    protected Object monitor_name;
    protected long monitor_id;
    protected String description;
    protected long work_site_id;
    protected long territorial_unit_id;
    protected long work_task_id;
    protected int dataStatus;
    protected transient boolean isSelected;
    protected String leaderaudit;
    protected Boolean iscj;
    protected long wf_instance_seq;
    protected long delay_id;
    //分项任务的描述，比如待科级审批
    protected String fxdescription;


    public String getFxdescription() {
        return fxdescription;
    }

    public void setFxdescription(String fxdescription) {
        this.fxdescription = fxdescription;
    }

    public long getDelay_id() {
        return delay_id;
    }

    public void setDelay_id(long delay_id) {
        this.delay_id = delay_id;
    }

    public long getWf_instance_seq() {
        return wf_instance_seq;
    }

    public void setWf_instance_seq(long wf_instance_seq) {
        this.wf_instance_seq = wf_instance_seq;
    }

    /**
     * 气体检测数据
     */
    private List<RmtWorkGasDetectReslt> rmtWorkGasDetectResltList;

    public Boolean getIscj() {
        return iscj;
    }

    public void setIscj(Boolean iscj) {
        this.iscj = iscj;
    }

    public List<RmtWorkGasDetectReslt> getRmtWorkGasDetectResltList() {
        return rmtWorkGasDetectResltList;
    }

    public void setRmtWorkGasDetectResltList(List<RmtWorkGasDetectReslt> rmtWorkGasDetectResltList) {
        this.rmtWorkGasDetectResltList = rmtWorkGasDetectResltList;
    }

    public String getLeaderaudit() {
        return leaderaudit;
    }

    public void setLeaderaudit(String leaderaudit) {
        this.leaderaudit = leaderaudit;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    public long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(long created_by) {
        this.created_by = created_by;
    }

    public Object getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(Object operator_name) {
        this.operator_name = operator_name;
    }

    public Object getAct_end_time() {
        return act_end_time;
    }

    public void setAct_end_time(Object act_end_time) {
        this.act_end_time = act_end_time;
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

    public long getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(long operator_id) {
        this.operator_id = operator_id;
    }

    public String getWork_site_name() {
        return work_site_name;
    }

    public void setWork_site_name(String work_site_name) {
        this.work_site_name = work_site_name;
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

    public String getEst_end_time() {
        return est_end_time;
    }

    public void setEst_end_time(String est_end_time) {
        this.est_end_time = est_end_time;
    }

    public long getWork_unit_id() {
        return work_unit_id;
    }

    public void setWork_unit_id(long work_unit_id) {
        this.work_unit_id = work_unit_id;
    }

    public String getEst_start_time() {
        return est_start_time;
    }

    public void setEst_start_time(String est_start_time) {
        this.est_start_time = est_start_time;
    }

    public String getCreated_by_name() {
        return created_by_name;
    }

    public void setCreated_by_name(String created_by_name) {
        this.created_by_name = created_by_name;
    }

    public Object getTeam() {
        return team;
    }

    public void setTeam(Object team) {
        this.team = team;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public Object getAct_start_time() {
        return act_start_time;
    }

    public void setAct_start_time(Object act_start_time) {
        this.act_start_time = act_start_time;
    }

    public long getWork_subtask_id() {
        return work_subtask_id;
    }

    public void setWork_subtask_id(long work_subtask_id) {
        this.work_subtask_id = work_subtask_id;
    }

    public Object getMonitor_name() {
        return monitor_name;
    }

    public void setMonitor_name(Object monitor_name) {
        this.monitor_name = monitor_name;
    }

    public long getMonitor_id() {
        return monitor_id;
    }

    public void setMonitor_id(long monitor_id) {
        this.monitor_id = monitor_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getWork_site_id() {
        return work_site_id;
    }

    public void setWork_site_id(long work_site_id) {
        this.work_site_id = work_site_id;
    }

    public long getTerritorial_unit_id() {
        return territorial_unit_id;
    }

    public void setTerritorial_unit_id(long territorial_unit_id) {
        this.territorial_unit_id = territorial_unit_id;
    }

    public long getWork_task_id() {
        return work_task_id;
    }

    public void setWork_task_id(long work_task_id) {
        this.work_task_id = work_task_id;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }
}
