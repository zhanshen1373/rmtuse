package com.hd.hse.entity.virtual;

import java.util.List;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.entity.base.HazardNotify;
import com.hd.hse.entity.workorder.WorkApplyMeasure;

/**
 * ClassName: HAZARD (危害虚拟对象  online 使用)<br/>
 * date: 2015年6月17日  <br/>
 *
 * @author lxf
 * @version 
 */
public class Hazard extends SuperEntity {
	/**
	 * serialVersionUID:TODO().
	 */
	private static final long serialVersionUID = -4383243798496924170L;
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
	 * listMeasure:TODO(获取危害集合).
	 */
	private List<HazardNotify> UD_ZYXK_ZYSQ2HAZARD;
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
	public List<HazardNotify> getUd_zyxk_zysq2hazard() {
		return UD_ZYXK_ZYSQ2HAZARD;
	}
	/**
	 * setUd_zyxk_zysq2precaution:(措施集合). <br/>
	 * date: 2015年6月17日 <br/>
	 *
	 * @author lxf
	 * @param ud_zyxk_zysq2precaution
	 */
	public void setUd_zyxk_zyxk_zysq2hazard(
			List<HazardNotify> ud_zyxk_zysq2hazard) {
		this.UD_ZYXK_ZYSQ2HAZARD = ud_zyxk_zysq2hazard;
	}

}
