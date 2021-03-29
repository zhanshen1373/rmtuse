package com.hd.hse.entity.conf;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.entity.dict.RmtDict;
import com.hd.hse.entity.resultdata.RmtVirRiskM;
import com.hd.hse.entity.resultdata.RmtWorkGasDetectReslt;
import com.hd.hse.entity.workorder.RmtWorkPermit;

import java.util.HashMap;
import java.util.List;

/**
 * 移动端界面配置(原PDA基础信息配置)
 * created by yangning on 2017/4/25 18:44.
 */

public class RmtConfMIntfc extends SuperEntity {


    /**
     * tableName : rmt_conf_m_intfc
     * work_subtask_id : 1000000001700
     * dataStatus : 0
     * ver : 1
     * created_by : 1000
     * created_dt : 2017-04-14 10:05:21
     * updated_by : 1000
     * updated_dt : 2017-04-14 10:05:21
     * df : 0
     * tenantid : 1
     * ts : 1492135521400
     * m_intfc_id : 1000000000200
     * permit_info_id : 1000000000040
     * created_by_name : 测试用户
     * updated_by_name : 测试用户
     * isenable : 1
     * product_flag : 2
     * orgid : 808212
     * tab_type : 1
     * tab_name : null
     * func_code : approve
     * func_name : 现场核查
     * func_order : 1
     * work_type : hot
     * savebtn : null
     * itemize : null
     * batch : null
     * apply_intfc : null
     * combobox : null
     * checkbox_dsc : null
     * ignore_meas : null
     * risk_type : gen
     * style : null
     */

    private String tableName;
    private long work_subtask_id;
    private int dataStatus;
    private int ver;
    private long created_by;
    private String created_dt;
    private long updated_by;
    private String updated_dt;
    private int df;
    private long tenantid;
    private long ts;
    private long m_intfc_id;
    private long permit_info_id;
    private String created_by_name;
    private String updated_by_name;
    private int isenable;
    private int product_flag;
    private long orgid;
    private int tab_type;
    private String tab_name;
    private String func_code;
    private String func_name;
    private int func_order;
    private String work_type;
    private Object savebtn;
    private Object itemize;
    private Object batch;
    private Object apply_intfc;
    private Object combobox;
    private Object checkbox_dsc;
    private Object ignore_meas;
    private String risk_type;
    private Object style;
    private String pressure; //压力
    private String pressure_later;
    private String medium;   //介质
    private String medium_later;
    private String temperature; //温度
    private String temperature_later;
    private String device; // 装置
    private String work_site_name; //区域
    private String equipment_name; //设备名称
    private String equipment_type_name; //设备类型名称
    private String equipment_type;


    private HashMap<String, String> equTemAndPreeMap;
    private List<RmtConfApprAuth> apprAuthVOList;
    private List<RmtVirRiskM> virRiskMVOList;
    /**
     * 作业票列表
     */
    private List<RmtWorkPermit> permitMVOList;
    /**
     * 用电设备列表
     */
    private List<RmtConfElecEqpt> confElecEqptVOList;

    /**
     * 气体检测信息
     */
    private List<RmtWorkGasDetectReslt> rmtWorkGasDetectResltList;

    /**
     * 字典信息,此字段不上传
     */
    private transient List<RmtDict> dictList;
    /**
     * 是否已经审批，此字段不上传
     */
    private transient boolean isApprove;


    public String getEquipment_type() {
        return equipment_type;
    }

    public void setEquipment_type(String equipment_type) {
        this.equipment_type = equipment_type;
    }

    public String getEquipment_type_name() {
        return equipment_type_name;
    }

    public void setEquipment_type_name(String equipment_type_name) {
        this.equipment_type_name = equipment_type_name;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getWork_site_name() {
        return work_site_name;
    }

    public void setWork_site_name(String work_site_name) {
        this.work_site_name = work_site_name;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getMedium_later() {
        return medium_later;
    }

    public void setMedium_later(String medium_later) {
        this.medium_later = medium_later;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getPressure_later() {
        return pressure_later;
    }

    public void setPressure_later(String pressure_later) {
        this.pressure_later = pressure_later;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature_later() {
        return temperature_later;
    }

    public void setTemperature_later(String temperaure_later) {
        this.temperature_later = temperaure_later;
    }

    public HashMap<String, String> getEquTemAndPreeMap() {
        return equTemAndPreeMap;
    }

    public void setEquTemAndPreeMap(HashMap<String, String> equTemAndPreeMap) {
        this.equTemAndPreeMap = equTemAndPreeMap;
    }

    public boolean isApprove() {
        return isApprove;
    }

    public void setApprove(boolean approve) {
        isApprove = approve;
    }

    public List<RmtDict> getDictList() {
        return dictList;
    }

    public void setDictList(List<RmtDict> dictList) {
        this.dictList = dictList;
    }

    public List<RmtWorkGasDetectReslt> getRmtWorkGasDetectResltList() {
        return rmtWorkGasDetectResltList;
    }

    public void setRmtWorkGasDetectResltList(List<RmtWorkGasDetectReslt> rmtWorkGasDetectResltList) {
        this.rmtWorkGasDetectResltList = rmtWorkGasDetectResltList;
    }

    public List<RmtWorkPermit> getPermitMVOList() {
        return permitMVOList;
    }

    public void setPermitMVOList(List<RmtWorkPermit> permitMVOList) {
        this.permitMVOList = permitMVOList;
    }

    public List<RmtConfElecEqpt> getConfElecEqptVOList() {
        return confElecEqptVOList;
    }

    public void setConfElecEqptVOList(List<RmtConfElecEqpt> confElecEqptVOList) {
        this.confElecEqptVOList = confElecEqptVOList;
    }

    public List<RmtConfApprAuth> getApprAuthVOList() {
        return apprAuthVOList;
    }

    public void setApprAuthVOList(List<RmtConfApprAuth> apprAuthVOList) {
        this.apprAuthVOList = apprAuthVOList;
    }

    public List<RmtVirRiskM> getVirRiskMVOList() {
        return virRiskMVOList;
    }

    public void setVirRiskMVOList(List<RmtVirRiskM> virRiskMVOList) {
        this.virRiskMVOList = virRiskMVOList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public long getWork_subtask_id() {
        return work_subtask_id;
    }

    public void setWork_subtask_id(long work_subtask_id) {
        this.work_subtask_id = work_subtask_id;
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

    public long getM_intfc_id() {
        return m_intfc_id;
    }

    public void setM_intfc_id(long m_intfc_id) {
        this.m_intfc_id = m_intfc_id;
    }

    public long getPermit_info_id() {
        return permit_info_id;
    }

    public void setPermit_info_id(long permit_info_id) {
        this.permit_info_id = permit_info_id;
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

    public int getTab_type() {
        return tab_type;
    }

    public void setTab_type(int tab_type) {
        this.tab_type = tab_type;
    }

    public String getTab_name() {
        return tab_name;
    }

    public void setTab_name(String tab_name) {
        this.tab_name = tab_name;
    }

    public String getFunc_code() {
        return func_code;
    }

    public void setFunc_code(String func_code) {
        this.func_code = func_code;
    }

    public String getFunc_name() {
        return func_name;
    }

    public void setFunc_name(String func_name) {
        this.func_name = func_name;
    }

    public int getFunc_order() {
        return func_order;
    }

    public void setFunc_order(int func_order) {
        this.func_order = func_order;
    }

    public String getWork_type() {
        return work_type;
    }

    public void setWork_type(String work_type) {
        this.work_type = work_type;
    }

    public Object getSavebtn() {
        return savebtn;
    }

    public void setSavebtn(Object savebtn) {
        this.savebtn = savebtn;
    }

    public Object getItemize() {
        return itemize;
    }

    public void setItemize(Object itemize) {
        this.itemize = itemize;
    }

    public Object getBatch() {
        return batch;
    }

    public void setBatch(Object batch) {
        this.batch = batch;
    }

    public Object getApply_intfc() {
        return apply_intfc;
    }

    public void setApply_intfc(Object apply_intfc) {
        this.apply_intfc = apply_intfc;
    }

    public Object getCombobox() {
        return combobox;
    }

    public void setCombobox(Object combobox) {
        this.combobox = combobox;
    }

    public Object getCheckbox_dsc() {
        return checkbox_dsc;
    }

    public void setCheckbox_dsc(Object checkbox_dsc) {
        this.checkbox_dsc = checkbox_dsc;
    }

    public Object getIgnore_meas() {
        return ignore_meas;
    }

    public void setIgnore_meas(Object ignore_meas) {
        this.ignore_meas = ignore_meas;
    }

    public String getRisk_type() {
        return risk_type;
    }

    public void setRisk_type(String risk_type) {
        this.risk_type = risk_type;
    }

    public Object getStyle() {
        return style;
    }

    public void setStyle(Object style) {
        this.style = style;
    }
}
