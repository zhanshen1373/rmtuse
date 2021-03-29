package com.hd.hse.entity.virtual;

import java.util.List;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.table.DBTable;
import com.hd.hse.entity.workorder.WorkApplyMeasure;

/**
 * ClassName: measure (虚拟措施 为online 添加)<br/>
 * date: 2015年6月17日  <br/>
 *
 * @author lxf
 * @version 
 */
@DBTable(tableName = "measure")
public class Measure extends SuperEntity {

	/**
	 * serialVersionUID:TODO().
	 */
	private static final long serialVersionUID = -7017466117614317433L;
	
	/**
	 * cstype:TODO(措施类别).
	 */
	private String cstype;
	/**
	 * ud_zyxk_zysqpdascid:TODO(配置页面主键).
	 */
	private String ud_zyxk_zysqpdascid;
	
	/**
	 * ud_zyxk_zysqid:TODO(作业申请ID).
	 */
	private String ud_zyxk_zysqid;
	/**   在线版
	 * listMeasure:TODO(获取措施集合).
	 */
	private List<WorkApplyMeasure> UD_ZYXK_ZYSQ2PRECAUTION;
	
	/**
	 * getCstype:(措施类别). <br/>
	 * date: 2015年6月17日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public String getCstype() {
		return cstype;
	}
	/**
	 * setCstype:(措施类别). <br/>
	 * date: 2015年6月17日 <br/>
	 *
	 * @author lxf
	 * @param cstype
	 */
	public void setCstype(String cstype) {
		this.cstype = cstype;
	}
	/**
	 * getUd_zyxk_zysqpdascid:(配置页面主键). <br/>
	 * date: 2015年6月17日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public String getUd_zyxk_zysqpdascid() {
		return ud_zyxk_zysqpdascid;
	}
	/**
	 * setUd_zyxk_zysqpdascid:(配置页面主键). <br/>
	 * date: 2015年6月17日 <br/>
	 *
	 * @author lxf
	 * @param ud_zyxk_zysqpdascid
	 */
	public void setUd_zyxk_zysqpdascid(String ud_zyxk_zysqpdascid) {
		this.ud_zyxk_zysqpdascid = ud_zyxk_zysqpdascid;
	}
	/**
	 * getUd_zyxk_zysqid:(作业票主键). <br/>
	 * date: 2015年6月17日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public String getUd_zyxk_zysqid() {
		return ud_zyxk_zysqid;
	}
	/**
	 * setUd_zyxk_zysqid:(作业票主键). <br/>
	 * date: 2015年6月17日 <br/>
	 *
	 * @author lxf
	 * @param ud_zyxk_zysqid
	 */
	public void setUd_zyxk_zysqid(String ud_zyxk_zysqid) {
		this.ud_zyxk_zysqid = ud_zyxk_zysqid;
	}
	/**
	 * getUd_zyxk_zysq2precaution:(措施集合). <br/>
	 * date: 2015年6月17日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public List<WorkApplyMeasure> getUd_zyxk_zysq2precaution() {
		return UD_ZYXK_ZYSQ2PRECAUTION;
	}
	/**
	 * setUd_zyxk_zysq2precaution:(措施集合). <br/>
	 * date: 2015年6月17日 <br/>
	 *
	 * @author lxf
	 * @param ud_zyxk_zysq2precaution
	 */
	public void setUd_zyxk_zysq2precaution(
			List<WorkApplyMeasure> ud_zyxk_zysq2precaution) {
		this.UD_ZYXK_ZYSQ2PRECAUTION = ud_zyxk_zysq2precaution;
	}


}
