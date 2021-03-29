package com.hd.hse.entity.common;

import com.hd.hse.common.table.DBTable;

/**
 * 消息记录虚拟对象（本地数据库中无对应表）
 * @author zl
 *
 */
@SuppressWarnings("serial")
@DBTable(tableName = "ud_zyxk_xxjl")
public class PushMessage extends com.hd.hse.common.entity.SuperEntity{
	/**
	 * 记录ID
	 */
	private String ud_zyxk_xxjlid;
	/**
	 * 作业申请ID
	 */
	private String ud_zyxk_zysqid;
	/**
	 * 消息类型
	 */
	private String xxlx;
	/**
	 * 功能编码
	 */
	private String gnbm;
	/**
	 * 消息服务端发送时间
	 */
	private String fssj;
	/**
	 * 消息内容
	 */
	private String xxnr;
	
	public String getUd_zyxk_xxjlid() {
		return ud_zyxk_xxjlid;
	}
	public void setUd_zyxk_xxjlid(String ud_zyxk_xxjlid) {
		this.ud_zyxk_xxjlid = ud_zyxk_xxjlid;
	}
	public String getUd_zyxk_zysqid() {
		return ud_zyxk_zysqid;
	}
	public void setUd_zyxk_zysqid(String ud_zyxk_zysqid) {
		this.ud_zyxk_zysqid = ud_zyxk_zysqid;
	}
	public String getXxlx() {
		return xxlx;
	}
	public void setXxlx(String xxlx) {
		this.xxlx = xxlx;
	}
	public String getGnbm() {
		return gnbm;
	}
	public void setGnbm(String gnbm) {
		this.gnbm = gnbm;
	}
	public String getFssj() {
		return fssj;
	}
	public void setFssj(String fssj) {
		this.fssj = fssj;
	}
	public String getXxnr() {
		return xxnr;
	}
	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}
	
}
