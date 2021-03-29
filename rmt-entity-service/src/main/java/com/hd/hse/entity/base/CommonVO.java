package com.hd.hse.entity.base;

/**
 * Created by liuting on 2017/5/23.
 */

public class CommonVO {

    private Long work_subtask_id;
    private String code;
    private String subcode;
    private String gasvalue;

    public Long getWork_subtask_id() {
        return work_subtask_id;
    }

    public void setWork_subtask_id(Long work_subtask_id) {
        this.work_subtask_id = work_subtask_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getGasvalue() {
        return gasvalue;
    }

    public void setGasvalue(String gasvalue) {
        this.gasvalue = gasvalue;
    }
}
