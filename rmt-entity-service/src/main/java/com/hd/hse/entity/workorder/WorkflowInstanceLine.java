package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.field.DBField;
import com.hd.hse.common.table.DBTable;

@DBTable(tableName="UD_ZYXK_BCLINELINE")
public class WorkflowInstanceLine extends SuperEntity {
	/**
	 * @author liuyang
	 * @date 2017年1月16日
	 */
	private static final long serialVersionUID = 7641542577343745565L;
	/**
     * zdr : JUNHAN
     * ud_zyxk_bclineid : 18322
     * ud_zyxk_bcid : 4235
     * ud_zyxk_gzlslid : 37869
     * zdr_desc : 韩军
     * ud_zyxk_bclinelineid : 20768
     * wgsj : 2016-11-10 17:46:10
     */

	@DBField
    private String zdr;
	@DBField
    private String ud_zyxk_bclineid;
	@DBField
    private String ud_zyxk_bcid;
	@DBField
    private String ud_zyxk_gzlslid;
	@DBField
    private String zdr_desc;
	@DBField
    private String ud_zyxk_bclinelineid;
	@DBField
    private String wgsj;

    public String getZdr() {
        return zdr;
    }

    public void setZdr(String zdr) {
        this.zdr = zdr;
    }

    public String getUd_zyxk_bclineid() {
        return ud_zyxk_bclineid;
    }

    public void setUd_zyxk_bclineid(String ud_zyxk_bclineid) {
        this.ud_zyxk_bclineid = ud_zyxk_bclineid;
    }

    public String getUd_zyxk_bcid() {
        return ud_zyxk_bcid;
    }

    public void setUd_zyxk_bcid(String ud_zyxk_bcid) {
        this.ud_zyxk_bcid = ud_zyxk_bcid;
    }

    public String getUd_zyxk_gzlslid() {
        return ud_zyxk_gzlslid;
    }

    public void setUd_zyxk_gzlslid(String ud_zyxk_gzlslid) {
        this.ud_zyxk_gzlslid = ud_zyxk_gzlslid;
    }

    public String getZdr_desc() {
        return zdr_desc;
    }

    public void setZdr_desc(String zdr_desc) {
        this.zdr_desc = zdr_desc;
    }

    public String getUd_zyxk_bclinelineid() {
        return ud_zyxk_bclinelineid;
    }

    public void setUd_zyxk_bclinelineid(String ud_zyxk_bclinelineid) {
        this.ud_zyxk_bclinelineid = ud_zyxk_bclinelineid;
    }

    public String getWgsj() {
        return wgsj;
    }

    public void setWgsj(String wgsj) {
        this.wgsj = wgsj;
    }
}
