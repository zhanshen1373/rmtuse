package com.hd.hse.entity.common;

/**
 * Created by dubojian on 2017/6/28.
 */

public class NoTiceBean {


    /**
     * optionType : harm
     * messageTitle : 风险措施提醒
     * xxnr : 0626001-分项任务01-hqs,测试专用，
     * sendTime : 2017-06-28 02:56:10
     * messageType : notice
     * subtaskId : 1000000002340
     */


    private String messageType;
    private String messageTitle;
    private String messageBody;
    private String sendTime;
    private String dataid;
    private String mfunccode;  //暂时不用
    private String nextopr;



    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public String getMfunccode() {
        return mfunccode;
    }

    public void setMfunccode(String mfunccode) {
        this.mfunccode = mfunccode;
    }

    public String getNextopr() {
        return nextopr;
    }

    public void setNextopr(String nextopr) {
        this.nextopr = nextopr;
    }

}
