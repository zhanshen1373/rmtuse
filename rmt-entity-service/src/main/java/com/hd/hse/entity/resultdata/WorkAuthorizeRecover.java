package com.hd.hse.entity.resultdata;

public class WorkAuthorizeRecover {

    /**
     * status : appr
     * be_authed : 测试人员1
     * rec_time : null
     * grt_id : 1000000000001
     * created_dt : 2020-01-17 17:02:59
     * grt_item_time : null
     * grt_item_id : 1000000000020
     * be_authedid : 1000000000000
     */

    private String status;
    private String be_authed;
    private Object rec_time;
    private long grt_id;
    private String created_dt;
    private Object grt_item_time;
    private long grt_item_id;
    private long be_authedid;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBe_authed() {
        return be_authed;
    }

    public void setBe_authed(String be_authed) {
        this.be_authed = be_authed;
    }

    public Object getRec_time() {
        return rec_time;
    }

    public void setRec_time(Object rec_time) {
        this.rec_time = rec_time;
    }

    public long getGrt_id() {
        return grt_id;
    }

    public void setGrt_id(long grt_id) {
        this.grt_id = grt_id;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public Object getGrt_item_time() {
        return grt_item_time;
    }

    public void setGrt_item_time(Object grt_item_time) {
        this.grt_item_time = grt_item_time;
    }

    public long getGrt_item_id() {
        return grt_item_id;
    }

    public void setGrt_item_id(long grt_item_id) {
        this.grt_item_id = grt_item_id;
    }

    public long getBe_authedid() {
        return be_authedid;
    }

    public void setBe_authedid(long be_authedid) {
        this.be_authedid = be_authedid;
    }
}
