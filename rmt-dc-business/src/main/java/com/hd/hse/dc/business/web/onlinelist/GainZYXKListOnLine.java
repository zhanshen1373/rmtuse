package com.hd.hse.dc.business.web.onlinelist;

import java.util.HashMap;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.dc.business.common.result.BaseWebResult;
import com.hd.hse.dc.business.common.result.EntityListWebResult;
import com.hd.hse.dc.business.common.result.GsonEntityListSpecialDealWebResult;
import com.hd.hse.entity.workorder.WorkTask;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;

/**
 * ClassName: GainZYXKListOnLine (获取作业列表的 措施确认、接班、作业结束)<br/>
 * date: 2015年5月28日 <br/>
 * 
 * @author lxf
 * @version
 */
public class GainZYXKListOnLine extends GainDataInfoBaseOnline {

	@Override
	public Object action(String action, Object... args) throws HDException {
		// TODO Auto-generated method stub
		//0 模块、1、搜素内容 2、任务id 
		if(args!=null && args.length>1){
			setSearch(args[1].toString());
		}
		if(args!=null && args.length>2){
			setTaskid(args[2].toString());
		}

		return super.action(action, args);
	}
	@Override
	public BaseWebResult getResultChangeType() {
		// TODO Auto-generated method stub
		try {
			return new EntityListWebResult(getEntityClass(),
					getAnalyzeDataAdapter());
//			return new GsonEntityListSpecialDealWebResult(getEntityClass(),
//					getAnalyzeDataAdapter());
		} catch (HDException e) {
			setWritelog(e.getMessage());
		}
		return null;
	}

	@Override
	public Class<?> getEntityClass() {
		return WorkTask.class;
	}

	@Override
	public String getMethodType() {
		// 功能编码
		return PadInterfaceContainers.METHOD_ZYPONLINE_LIST;
	}
	
	/////////////////////////////////////////////////启用时 要删掉此方法
//	/**
//	 * TODO 重载 获取数据的方法
//	 * @see com.hd.hse.dc.business.common.weblistener.down.GainPCDataListener#getDataInfo(java.lang.Object[])
//	 */
//	@Override
//	public Object getDataInfo(Object... obj) throws HDException {
//		String methodtype = getMethodType();
//		if (StringUtils.isEmpty(methodtype)) {
//			throw new HDException("请注册要调用方法的");
//		}
////		Object retobj = sClient.action(methodtype,
////				(HashMap<String, Object>) initParam());
////		if (retobj instanceof PadInterfaceResponse) {
////			PadInterfaceResponse response = (PadInterfaceResponse) retobj;
////			if(response.getFlag()==PadInterfaceContainers.METHOD_BUSINESSFAILD){
////				setWritelog("PC错误：" +UtilToolsMsg.resolveMsg(response.getExceptionmsg()));
////				throw new HDException(UtilToolsMsg.resolveMsg(response.getExceptionmsg()));
////			}
////			else{
////				setWritelog("PC错误：" + response.getExceptionmsg());
////				throw new HDException("加载失败,请联系管理员");
////			}
////		}
////		if(retobj==null){
////			retobj="[]";
////		}
//		Object	retobj="{'UD_ZYXK_WORKTASK':[{'zyname':'宁夏测试001','ud_zyxk_worktaskid':'zyp-62323','sddept_desc':'一联合车间','zysite':'五道口','zyendtime':'2015-06-11 15:15:00','zydept_desc':'综合办公室','zylocation_desc':'气分装置泵廊中点','zyarea_desc':'炼油厂','zystarttime':'2015-06-11 09:08:14','UD_ZYXK_ZYSQ':[{'statusname':'草稿','zydept_desc':'综合办公室','zyendtime':'2015-06-11 15:15:00','zyarea_desc':'炼油厂','zypclass':'zylx02','zylevel':'SXKJ01','parent_id':'62323','zyname':'宁夏测试001','status':'WAPPR','ud_zyxk_worktaskid':'zyp-62323','sddept_desc':'一联合车间','zysite':'五道口','zypclassname':'*进入受限空间','zylocation_desc':'气分装置泵廊中点','zystarttime':'2015-06-11 09:08:14','ud_zyxk_zysqid':'62328','sx_type':'受限类别都有哪些'},{'statusname':'草稿','zydept_desc':'综合办公室','zyendtime':'2015-06-11 15:15:00','zyarea_desc':'炼油厂','zypclass':'zylx03','parent_id':'62323','zyname':'宁夏测试001','status':'WAPPR','ud_zyxk_worktaskid':'zyp-62323','sddept_desc':'一联合车间','zysite':'五道口','zypclassname':'*挖掘作业','zylocation_desc':'气分装置泵廊中点','zystarttime':'2015-06-11 09:08:14','ud_zyxk_zysqid':'62327'},{'statusname':'草稿','zydept_desc':'综合办公室','zyendtime':'2015-06-11 15:15:00','zyarea_desc':'炼油厂','zypclass':'zylx04','zylevel':'GCZY01','parent_id':'62323','zyname':'宁夏测试001','status':'WAPPR','ud_zyxk_worktaskid':'zyp-62323','sddept_desc':'一联合车间','zysite':'五道口','zypclassname':'*高处作业','zylocation_desc':'气分装置泵廊中点','zystarttime':'2015-06-11 09:08:14','ud_zyxk_zysqid':'62326'},{'statusname':'草稿','zydept_desc':'综合办公室','zyendtime':'2015-06-11 15:15:00','zyarea_desc':'炼油厂','zypclass':'zylx05','parent_id':'62323','zyname':'宁夏测试001','status':'WAPPR','ud_zyxk_worktaskid':'zyp-62323','sddept_desc':'一联合车间','zysite':'五道口','zypclassname':'*吊装作业','zylocation_desc':'气分装置泵廊中点','zystarttime':'2015-06-11 09:08:14','ud_zyxk_zysqid':'62325'},{'statusname':'草稿','zycontent':'用于办理临时用电作业许可证','zydept_desc':'综合办公室','zyendtime':'2015-06-11 15:15:00','zyarea_desc':'炼油厂','zypclass':'zylx07','parent_id':'62323','zyname':'宁夏测试001','status':'WAPPR','ud_zyxk_worktaskid':'zyp-62323','sddept_desc':'一联合车间','zysite':'五道口','zypclassname':'*临时用电','zylocation_desc':'气分装置泵廊中点','dhzy_id':'62324','zystarttime':'2015-06-11 09:08:14','ud_zyxk_zysqid':'62329'},{'statusname':'草稿','zydept_desc':'综合办公室','zyendtime':'2015-06-11 15:15:00','zyarea_desc':'炼油厂','zypclass':'zylx06','parent_id':'62323','zyname':'宁夏测试001','status':'WAPPR','ud_zyxk_worktaskid':'zyp-62323','sddept_desc':'一联合车间','zysite':'五道口','zypclassname':'*管线/设备打开','zylocation_desc':'气分装置泵廊中点','zystarttime':'2015-06-11 09:08:14','ud_zyxk_zysqid':'62330'},{'statusname':'草稿','zydept_desc':'综合办公室','zyendtime':'2015-06-11 15:15:00','zyarea_desc':'炼油厂','zypclass':'zylx99','zyname':'宁夏测试001','status':'WAPPR','ud_zyxk_worktaskid':'zyp-62323','sddept_desc':'一联合车间','zysite':'五道口','zypclassname':'*作业大票','zylocation_desc':'气分装置泵廊中点','zystarttime':'2015-06-11 09:08:14','ud_zyxk_zysqid':'62323'},{'statusname':'草稿','zydept_desc':'综合办公室','zyendtime':'2015-06-11 15:15:00','zyarea_desc':'炼油厂','zypclass':'zylx01','zylevel':'DHZY01','parent_id':'62323','zyname':'宁夏测试001','status':'WAPPR','ud_zyxk_worktaskid':'zyp-62323','sddept_desc':'一联合车间','zysite':'五道口','zypclassname':'*动火作业','zylocation_desc':'气分装置泵廊中点','zystarttime':'2015-06-11 09:08:14','ud_zyxk_zysqid':'62324','dh_yy':'动火原因紧急动火','dh_type':'动火类别动态与','ud_zyxk_qtjc':[{'ud_zyxk_zysqid':'62324','ud_zyxk_qtjcid':'1','jctime':'2015-06-12','ud_zyxk_qtjcline':[{'ud_zyxk_qtjcid':'1','qttype':'氧气','qtvaluestr':'1.01'},{'ud_zyxk_qtjcid':'1','qttype':'二氧化碳','qtvaluestr':'1.01'}]},{'ud_zyxk_zysqid':'62324','ud_zyxk_qtjcidstr':'1.01','jctime':'2015-06-12'}]}]}]}";
//		setWriteDubuglog(retobj.toString());
//		BaseWebResult type = getResultChangeType();
//		if (null == type) {
//			return retobj;
//		} else {
//			return type.processResultSet(retobj);
//		}
//	}
}
