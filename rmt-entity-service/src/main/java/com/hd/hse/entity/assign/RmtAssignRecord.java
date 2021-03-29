package com.hd.hse.entity.assign;

/**
 * created by yangning on 2017/11/2 13:53.
 */

public class RmtAssignRecord {
    /**
     * assignee_id : 1000000000418
     * assignee_name : null
     * assign_time : null
     */

    private long assignee_id;
    private String assignee_name;
    private String relieved_time;

    public long getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(long assignee_id) {
        this.assignee_id = assignee_id;
    }

    public String getAssignee_name() {
        return assignee_name;
    }

    public void setAssignee_name(String assignee_name) {
        this.assignee_name = assignee_name;
    }

    public String getRelieved_time() {
        return relieved_time;
    }

    public void setRelieved_time(String relieved_time) {
        this.relieved_time = relieved_time;
    }
}
