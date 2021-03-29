package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.field.DBField;
import com.hd.hse.common.table.DBTable;

/**
 * ClassName: UndergroundFacilities (地下设施)<br/>
 * date: 2015年6月11日 <br/>
 * 
 * @author lxf
 * @version
 */
@DBTable(tableName = "UD_ZYXK_DXSS")
public class UndergroundFacilities extends SuperEntity {

	/**
	 * serialVersionUID:TODO().
	 */
	private static final long serialVersionUID = -1855125637811299983L;
	/**
	 * 外键
	 * 
	 * @pdOid 66e28b7a-4309-49e3-ae22-c37d2011d853
	 */
	@DBField(foreign = true)
	private String ud_zyxk_zysqid;
	/**
	 * description:TODO(地下设施描述).
	 */
	// @DBField
	private String dxss;

	/**
	 * getDescription:(获取地下设施描述). <br/>
	 * date: 2015年6月11日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public String getDxss() {
		return dxss;
	}

	/**
	 * setDescription:(设置地下设施描述). <br/>
	 * date: 2015年6月11日 <br/>
	 * 
	 * @author lxf
	 * @param description
	 */
	public void setDxss(String description) {
		this.dxss = description;
	}

	/**
	 * @return the ud_zyxk_zysqid
	 * 
	 * @pdOid 7afe9fd6-1dc8-494d-8006-e97536a26529
	 */
	public String getUd_zyxk_zysqid() {
		return ud_zyxk_zysqid;
	}

	/**
	 * @param ud_zyxk_zysqid
	 *            the ud_zyxk_zysqid to set
	 * @pdOid a92debba-4ca9-477e-b419-75c50ee27220
	 */
	public void setUd_zyxk_zysqid(String ud_zyxk_zysqid) {
		this.ud_zyxk_zysqid = ud_zyxk_zysqid;
	}
}
