package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.field.DBField;
import com.hd.hse.common.table.DBTable;

/**
 * ClassName: ItemWorkTask (分项任务)<br/>
 * date: 2015年8月10日 <br/>
 * 
 * @author lxf
 * @version
 */
@DBTable(tableName = "ud_zyxk_bc")
public class ItemWorkTask extends SuperEntity {

	/**
	 * serialVersionUID:TODO().
	 */
	private static final long serialVersionUID = 2319683776677200817L;

	/**
	 * ud_zyxk_bcid:TODO(主键).
	 */
	@DBField(id = true)
	private String ud_zyxk_bcid;
	/**
	 * 任务外键
	 * 
	 * @pdOid f008ef29-bb6b-4e39-b60b-6a3cd20a9318
	 */
	@DBField(foreign = true)
	private String ud_zyxk_worktaskid;

	/**
	 * description:TODO(分项任务描述).
	 */
	private String zymx;
	/**
	 * status:TODO(分项任务状态).
	 */
	private String status;
	/**
	 * zylx:TODO(分项任务类别).
	 */
	private String zylx;
	/**
	 * zystatus:TODO(分项任务状态).
	 */
	private String zystatus;

	/**
	 * jhkssj:TODO(计划开始时间).
	 */
	private String jhkssj;
	/**
	 * jhjssj:TODO(计划结束时间).
	 */
	private String jhjssj;

	/**
	 * getUd_zyxk_bcid:(主键). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public String getUd_zyxk_bcid() {
		return ud_zyxk_bcid;
	}

	/**
	 * setUd_zyxk_bcid:(设置主键). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @param ud_zyxk_bcid
	 */
	public void setUd_zyxk_bcid(String ud_zyxk_bcid) {
		this.ud_zyxk_bcid = ud_zyxk_bcid;
	}

	/**
	 * getUd_zyxk_worktaskid:(外键). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public String getUd_zyxk_worktaskid() {
		return ud_zyxk_worktaskid;
	}

	/**
	 * setUd_zyxk_worktaskid:(设置外键). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @param ud_zyxk_worktaskid
	 */
	public void setUd_zyxk_worktaskid(String ud_zyxk_worktaskid) {
		this.ud_zyxk_worktaskid = ud_zyxk_worktaskid;
	}

	/**
	 * getDescription:(描述). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public String getZymx() {
		return zymx;
	}

	/**
	 * setDescription:(设置描述). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @param description
	 */
	public void setZymx(String zymx) {
		this.zymx = zymx;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * getZylx:(作业类别). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public String getZylx() {
		return zylx;
	}

	/**
	 * setZylx:(作业类别). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @param zylx
	 */
	public void setZylx(String zylx) {
		this.zylx = zylx;
	}

	/**
	 * getJhkssj:(计划开始时间). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public String getJhkssj() {
		return jhkssj;
	}

	/**
	 * setJhkssj:(计划开始时间). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @param jhkssj
	 */
	public void setJhkssj(String jhkssj) {
		this.jhkssj = jhkssj;
	}

	/**
	 * getJhjssj:(计划结束时间). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public String getJhjssj() {
		return jhjssj;
	}

	/**
	 * setJhjssj:(计划结束时间). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @param jhjssj
	 */
	public void setJhjssj(String jhjssj) {
		this.jhjssj = jhjssj;
	}

	public String getZystatus() {
		return zystatus;
	}

	public void setZystatus(String zystatus) {
		this.zystatus = zystatus;
	}

}
