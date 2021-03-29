package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.field.DBField;
import com.hd.hse.common.table.DBTable;

/**
 * 临时用电设备清单
 * 
 * @author liuyang
 * 
 * @date 2016年7月5日
 */
@DBTable(tableName = "UD_ZYXK_YDSB")
public class TempEleDevice extends SuperEntity {
	/**
	 * @author liuyang
	 * @date 2016年7月5日
	 */
	private static final long serialVersionUID = 7645929837876159155L;
	/**
	 * 用电设备ID
	 * 
	 * @author liuyang
	 * @date 2016年7月5日
	 */
	@DBField(id = true)
	private String ud_zyxk_ydsbid;
	/**
	 * 用电设备名称
	 * 
	 * @author liuyang
	 * @date 2016年7月5日
	 */
	@DBField
	private String assetname;
	/**
	 * 规格型号
	 * 
	 * @author liuyang
	 * @date 2016年7月5日
	 */
	@DBField
	private String version;
	/**
	 * 功率/负荷
	 * 
	 * @author liuyang
	 * @date 2016年7月5日
	 */
	@DBField
	private String fh;
	@DBField
	private int count;
	private boolean isChoiced;

	public String getUd_zyxk_ydsbid() {
		return ud_zyxk_ydsbid;
	}

	public void setUd_zyxk_ydsbid(String ud_zyxk_ydsbid) {
		this.ud_zyxk_ydsbid = ud_zyxk_ydsbid;
	}

	public String getAssetname() {
		return assetname;
	}

	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFh() {
		return fh;
	}

	public void setFh(String fh) {
		this.fh = fh;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isChoiced() {
		return isChoiced;
	}

	public void setChoiced(boolean idChoiced) {
		this.isChoiced = idChoiced;
	}
}
