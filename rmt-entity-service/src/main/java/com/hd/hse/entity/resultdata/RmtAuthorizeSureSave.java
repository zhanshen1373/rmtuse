package com.hd.hse.entity.resultdata;

import java.util.List;

public class RmtAuthorizeSureSave {


    /**
     * endtime : 2020-01-16
     * starttime : 2020-01-16
     * grt_node : on_duty_moni
     * authid : 1000
     * auth : 系统管理员
     * itemVOs : [{"be_authed":"测试人员1","be_authedid":1000000000000},{"be_authed":"系统管理员","be_authedid":1000}]
     */

    private String endtime;
    private String starttime;
    private String grt_node;
    private long authid;
    private String auth;
    private List<ItemVOsBean> itemVOs;

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getGrt_node() {
        return grt_node;
    }

    public void setGrt_node(String grt_node) {
        this.grt_node = grt_node;
    }

    public long getAuthid() {
        return authid;
    }

    public void setAuthid(long authid) {
        this.authid = authid;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public List<ItemVOsBean> getItemVOs() {
        return itemVOs;
    }

    public void setItemVOs(List<ItemVOsBean> itemVOs) {
        this.itemVOs = itemVOs;
    }

    public static class ItemVOsBean {
        /**
         * be_authed : 测试人员1
         * be_authedid : 1000000000000
         */

        private String be_authed;
        private long be_authedid;

        public String getBe_authed() {
            return be_authed;
        }

        public void setBe_authed(String be_authed) {
            this.be_authed = be_authed;
        }

        public long getBe_authedid() {
            return be_authedid;
        }

        public void setBe_authedid(long be_authedid) {
            this.be_authedid = be_authedid;
        }
    }
}
