package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.field.DBField;
import com.hd.hse.common.table.DBTable;

/**
 * ClassName: UseEquipment (用电设备)<br/>
 * date: 2015年6月11日  <br/>
 *
 * @author lxf
 * @version 
 */
@DBTable(tableName = "UD_ZYXK_YDASSET")
public class UseEquipment extends SuperEntity {

	/**
	 * serialVersionUID:TODO().
	 */
	private static final long serialVersionUID = -8763438773398270845L;
	//@DBField(id = true)
	/**
	 * ud_zyxk_ydassetid:TODO(唯一约束).
	 */
	private String  ud_zyxk_ydassetid;
	//@DBField(foreign = true)
	/**
	 * ud_zyxk_zysqid:TODO(外键).
	 */
	private String ud_zyxk_zysqid;
	//@DBField
	/**
	 * assetname:TODO(设备名称).
	 */
	private String assetname;
	//@DBField
	/**
	 * count:TODO(数量).
	 */
	private String count;
	//@DBField
	/**
	 * fh:TODO(负荷).
	 */
	private String fh;
	
	/**
	 * getUd_zyxk_ydassetid:(主键). <br/>
	 * date: 2015年6月11日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public String getUd_zyxk_ydassetid() {
		return ud_zyxk_ydassetid;
	}
	/**
	 * setUd_zyxk_ydassetid:(主键). <br/>
	 * date: 2015年6月11日 <br/>
	 *
	 * @author lxf
	 * @param ud_zyxk_ydassetid
	 */
	public void setUd_zyxk_ydassetid(String ud_zyxk_ydassetid) {
		this.ud_zyxk_ydassetid = ud_zyxk_ydassetid;
	}
	/**
	 * getUd_zyxk_zysqid:(作业申请id). <br/>
	 * date: 2015年6月11日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public String getUd_zyxk_zysqid() {
		return ud_zyxk_zysqid;
	}
	/**
	 * setUd_zyxk_zysqid:(作业申请id). <br/>
	 * date: 2015年6月11日 <br/>
	 *
	 * @author lxf
	 * @param ud_zyxk_zysqid
	 */
	public void setUd_zyxk_zysqid(String ud_zyxk_zysqid) {
		this.ud_zyxk_zysqid = ud_zyxk_zysqid;
	}
	/**
	 * getAssetname:(设备名称). <br/>
	 * date: 2015年6月11日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public String getAssetname() {
		return assetname;
	}
	/**
	 * setAssetname:(设备名称). <br/>
	 * date: 2015年6月11日 <br/>
	 *
	 * @author lxf
	 * @param assetname
	 */
	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}
	/**
	 * getCount:(数量). <br/>
	 * date: 2015年6月11日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public String getCount() {
		return count;
	}
	/**
	 * setCount:(数量). <br/>
	 * date: 2015年6月11日 <br/>
	 *
	 * @author lxf
	 * @param count
	 */
	public void setCount(String count) {
		this.count = count;
	}
	/**
	 * getFh:(负荷). <br/>
	 * date: 2015年6月11日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public String getFh() {
		return fh;
	}
	/**
	 * setFh:(负荷). <br/>
	 * date: 2015年6月11日 <br/>
	 *
	 * @author lxf
	 * @param fh
	 */
	public void setFh(String fh) {
		this.fh = fh;
	}

}
