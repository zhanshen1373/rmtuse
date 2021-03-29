package com.hd.hse.entity.workorder;

import java.util.List;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.field.DBField;
import com.hd.hse.common.table.DBTable;
import com.hd.hse.entity.base.GasDetection;

/**
 * 工作流实例
 * 
 * @author liuyang
 * 
 * @date 2017年1月4日
 */
@DBTable(tableName = "ud_zyxk_gzlsl")
public class WorkflowInstance extends SuperEntity {
	/**
	 * @author liuyang
	 * @date 2017年1月12日
	 */
	private static final long serialVersionUID = 5206429874736654919L;
	/**
	 * vpersonid : ZLGUO,LCLI,JUNHAN
	 * pscode : SP01
	 * istatus : 2
	 * ud_zyxk_zysqpdascid : 7882
	 * ud_zyxk_gzlslid : 37869
	 * vdownnode : 2544
	 * ud_zyxk_zyspqxid : 2554
	 * wttype : zylx99
	 * spfield : YBZYPERSON
	 * spfield_desc : 一般作业人#一般作业人（危害）
	 * ud_zyxk_bcid : 4235
	 * ud_zyxk_zysqid : 24597
	 * vupnode : 21663
	 * tab_order : 1
	 * UD_ZYXK_BCLINELINE : [{"zdr":"JUNHAN","ud_zyxk_bclineid":"18322","ud_zyxk_bcid":"4235","ud_zyxk_gzlslid":"37869","zdr_desc":"韩军","ud_zyxk_bclinelineid":"20768","wgsj":"2016-11-10 17:46:10"},{"zdr":"LCLI","ud_zyxk_bclineid":"18322","ud_zyxk_bcid":"4235","ud_zyxk_gzlslid":"37869","zdr_desc":"李龙昌","ud_zyxk_bclinelineid":"20769","wgsj":"2016-11-11 14:16:32"},{"zdr":"ZLGUO","ud_zyxk_bclineid":"18322","ud_zyxk_bcid":"4235","ud_zyxk_gzlslid":"37869","zdr_desc":"郭兆林","ud_zyxk_bclinelineid":"20770","wgsj":"2016-11-10 17:50:46"}]
	 */

	@DBField
	private String vpersonid;
	@DBField
	private String pscode;
	@DBField
	private String istatus;
	@DBField
	private String ud_zyxk_zysqpdascid;
	@DBField
	private String ud_zyxk_gzlslid;
	@DBField
	private String vdownnode;
	@DBField
	private String ud_zyxk_zyspqxid;
	@DBField
	private String wttype;
	@DBField
	private String spfield;
	@DBField
	private String spfield_desc;
	@DBField
	private String ud_zyxk_bcid;
	@DBField
	private String ud_zyxk_zysqid;
	@DBField
	private String vupnode;
	@DBField
	private String tab_order;
//	private List<WorkflowInstanceLine> UD_ZYXK_BCLINELINE;

	public String getVpersonid() {
		return vpersonid;
	}

	public void setVpersonid(String vpersonid) {
		this.vpersonid = vpersonid;
	}

	public String getPscode() {
		return pscode;
	}

	public void setPscode(String pscode) {
		this.pscode = pscode;
	}

	public String getIstatus() {
		return istatus;
	}

	public void setIstatus(String istatus) {
		this.istatus = istatus;
	}

	public String getUd_zyxk_zysqpdascid() {
		return ud_zyxk_zysqpdascid;
	}

	public void setUd_zyxk_zysqpdascid(String ud_zyxk_zysqpdascid) {
		this.ud_zyxk_zysqpdascid = ud_zyxk_zysqpdascid;
	}

	public String getUd_zyxk_gzlslid() {
		return ud_zyxk_gzlslid;
	}

	public void setUd_zyxk_gzlslid(String ud_zyxk_gzlslid) {
		this.ud_zyxk_gzlslid = ud_zyxk_gzlslid;
	}

	public String getVdownnode() {
		return vdownnode;
	}

	public void setVdownnode(String vdownnode) {
		this.vdownnode = vdownnode;
	}

	public String getUd_zyxk_zyspqxid() {
		return ud_zyxk_zyspqxid;
	}

	public void setUd_zyxk_zyspqxid(String ud_zyxk_zyspqxid) {
		this.ud_zyxk_zyspqxid = ud_zyxk_zyspqxid;
	}

	public String getWttype() {
		return wttype;
	}

	public void setWttype(String wttype) {
		this.wttype = wttype;
	}

	public String getSpfield() {
		return spfield;
	}

	public void setSpfield(String spfield) {
		this.spfield = spfield;
	}

	public String getSpfield_desc() {
		return spfield_desc;
	}

	public void setSpfield_desc(String spfield_desc) {
		this.spfield_desc = spfield_desc;
	}

	public String getUd_zyxk_bcid() {
		return ud_zyxk_bcid;
	}

	public void setUd_zyxk_bcid(String ud_zyxk_bcid) {
		this.ud_zyxk_bcid = ud_zyxk_bcid;
	}

	public String getUd_zyxk_zysqid() {
		return ud_zyxk_zysqid;
	}

	public void setUd_zyxk_zysqid(String ud_zyxk_zysqid) {
		this.ud_zyxk_zysqid = ud_zyxk_zysqid;
	}

	public String getVupnode() {
		return vupnode;
	}

	public void setVupnode(String vupnode) {
		this.vupnode = vupnode;
	}

	public String getTab_order() {
		return tab_order;
	}

	public void setTab_order(String tab_order) {
		this.tab_order = tab_order;
	}
	
	@Override
	public String[] getChildClasses() {
		return new String[] { WorkflowInstanceLine.class.getName() };
	}

//	public List<WorkflowINSTANCELINE> GETUD_ZYXK_BCLINELINE() {
//		RETURN UD_ZYXK_BCLINELINE;
//	}
//
//	PUBLIC VOID SETUD_ZYXK_BCLINELINE(LIST<WORKFLOWINSTANCELINE> UD_ZYXK_BCLINELINE) {
//		THIS.UD_ZYXK_BCLINELINE = UD_ZYXK_BCLINELINE;
//	}

}
