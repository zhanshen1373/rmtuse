package com.hd.hse.entity.common;

/**
 * Created by dubojian on 2017/6/22.
 */

public class ReMindBean {


    /**
     * messageBody : 风险措施已生成，请注意审核
     * content : {"gnbm":"RMT_WORK_SUBTASK","messageTitle":"风险措施提醒","xxlx":"notice","xxnr":"风险措施已生成，请注意审核","fssj":"2017-06-22 05:04:45","ud_zyxk_xxjlid":"","messageType":"remind","ud_zyxk_zysqid":""}
     * messageTitle : 风险措施提醒
     * messageType : remind
     */

    private String messageType;
    private String messageTitle;
    private String messageBody;
    private ContentBean content;

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public static class ContentBean {
        /**
         * optionType : RMT_WORK_SUBTASK
         * messageBody : 分项任务描述work_subtask_description
         * sendTime : 2017-06-22 05:04:45
         * subtaskId : Work_subtask_id
         */

        private String mfunccode;  //暂时不用
        private String sendTime;
        private String type;       //暂时不用
        private String dataid;
        private String nextopr;
        private String messageBody;
        private String messageTitle;


        public String getNextopr() {
            return nextopr;
        }

        public void setNextopr(String nextopr) {
            this.nextopr = nextopr;
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

        public String getMfunccode() {
            return mfunccode;
        }

        public void setMfunccode(String mfunccode) {
            this.mfunccode = mfunccode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDataid() {
            return dataid;
        }

        public void setDataid(String dataid) {
            this.dataid = dataid;
        }

        public String getMessageTitle() {
            return messageTitle;
        }

        public void setMessageTitle(String messageTitle) {
            this.messageTitle = messageTitle;
        }
    }
}
