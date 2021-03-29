/**
 * Project Name:hse-entity-service
 * File Name:QueryWorkInfo.java
 * Package Name:com.hd.hse.service.workorder.queryinfo
 * Date:2014年10月16日
 * Copyright (c) 2014, zhaofeng@ushayden.com All Rights Reserved.
 *
 */
package com.hd.hse.service.workorder.queryinfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.DaoException;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.constant.IConfigEncoding;
import com.hd.hse.constant.ITableName;
import com.hd.hse.constant.IWorkOrderStatus;
import com.hd.hse.dao.BaseDao;
import com.hd.hse.dao.SequenceGenerator;
import com.hd.hse.dao.connection.IConnection;
import com.hd.hse.dao.factory.ConnectionSourceManager;
import com.hd.hse.dao.result.EntityListResult;
import com.hd.hse.dao.result.EntityResult;
import com.hd.hse.dao.result.IProcessResultSet;
import com.hd.hse.dao.result.ListListResult;
import com.hd.hse.dao.result.MapResult;
import com.hd.hse.dao.source.IConnectionSource;
import com.hd.hse.entity.base.Domain;
import com.hd.hse.entity.base.GasDetectSub;
import com.hd.hse.entity.base.GasDetection;
import com.hd.hse.entity.base.HazardNotify;
import com.hd.hse.entity.base.MeasureReviewSub;
import com.hd.hse.entity.base.MultitermMeasureAffirm;
import com.hd.hse.entity.base.PDAWorkOrderInfoConfig;
import com.hd.hse.entity.common.Image;
import com.hd.hse.entity.common.PositionCard;
import com.hd.hse.entity.common.SysActionAgeConfig;
import com.hd.hse.entity.workorder.WorkApplyMeasure;
import com.hd.hse.entity.workorder.WorkApprovalPermission;
import com.hd.hse.entity.workorder.WorkApprovalPersonRecord;
import com.hd.hse.entity.workorder.WorkDelay;
import com.hd.hse.entity.workorder.WorkGuardEquipment;
import com.hd.hse.entity.workorder.WorkMeasureReview;
import com.hd.hse.entity.workorder.WorkOrder;
import com.hd.hse.utils.ServiceActivityUtils;
import com.hd.hse.utils.UtilTools;

/**
 * ClassName: QueryWorkInfo ()<br/>
 * date: 2014年10月16日 <br/>
 * 
 * @author zhaofeng
 * @version
 */
public class QueryWorkInfo implements IQueryWorkInfo {

	private final Logger logger = LogUtils.getLogger(QueryWorkInfo.class);
	/**
	 * dao:TODO(数据库交互).
	 */
	BaseDao dao;

	/**
	 * connection:TODO(数据库连接).
	 */
	private IConnection connection;

	/**
	 * Creates a new instance of QueryWorkInfo.
	 * 
	 */
	public QueryWorkInfo() {
		dao = new BaseDao();
	}

	public QueryWorkInfo(IConnection connection) {
		dao = new BaseDao();
		this.connection = connection;
	}

	/**
	 * executeQuery:(查询). <br/>
	 * date: 2014年11月10日 <br/>
	 * 
	 * @author lg
	 * @param sql
	 * @param type
	 * @return
	 * @throws DaoException
	 */
	private Object executeQuery(String sql, IProcessResultSet type)
			throws DaoException {
		if (this.connection == null) {
			return dao.executeQuery(sql, type);
		} else {
			return dao.executeQuery(connection, sql, type);
		}
	}

	/**
	 * TODO 查询作业票信息
	 * 
	 * @see com.hd.hse.service.workorder.queryinfo.IQueryWorkInfo#queryWorkInfo(com.hd.hse.common.entity.SuperEntity,
	 *      com.hd.hse.service.workorder.queryinfo.IQueryCallEventListener)
	 */
	@Override
	public WorkOrder queryWorkInfo(SuperEntity workEntity,
			IQueryCallEventListener callEventListener) throws HDException {
		// TODO Auto-generated method stub
		if (workEntity.getAttribute("ud_zyxk_zysqid") == null)
			throw new HDException("获取作业申请信息的ud_zyxk_zysqid属性失败！");
		if (callEventListener == null) {
			StringBuilder workSbSql = new StringBuilder();
			workSbSql.setLength(0);
			workSbSql
					.append("select ud_zyxk_zysqid,zysqnum,zypclass,ad1.description as zypclassname,parent_id,zyarea,zfpersontime,zfcause,zylocation_desc,zyarea_desc,spstatus,zylocation,");
			workSbSql
					.append("sddept,zysite,zydept,zycontent,s.zylevel,ad.description as zylevel_desc,w.location_desc as location,zystarttime,zyendtime,isqtjc,isnlgl,iskyyq,csnum,cssavenum,");
			workSbSql
					.append("sddept_desc,grfhzb,whshib,zydept_desc,zyname,ifnull(yqcount,0) as yqcount,yyqcount,beforeissuit,afterissuit,ischecked,status,iscbs,");
			workSbSql
					.append("custodytype,dhzy_id,cssavefied,isqualify,checkresult,ischecked,issave,functionpoint,gbtype,gbsm,ad2.description as gbtype_desc,jclocation_desc,");
			workSbSql
					.append("ud_zyxk_zyfxfxid,fxstatus,isrelation,ud_zyxk_jsaid,gjjy,zqx,sjendtime,sjstarttime,s.reasonqx,s.yqcount,s.yyqcount from ud_zyxk_zysq s left join ud_zyxk_wzk w  ");
			workSbSql
					.append("on s.zylocation = w.location left outer join alndomain ad on s.zylevel=ad.value and ad.domainid='UDZYLEVEL' ");
			workSbSql
					.append("left outer join alndomain ad1 on s.zypclass=ad1.value and ad1.domainid='UDZYLX'");
			workSbSql
					.append("left outer join alndomain ad2 on s.gbtype=ad2.value and ad2.domainid='UDZYGBYY'");
			workSbSql
					.append("where ud_zyxk_zysqid ='")
					.append(workEntity.getAttribute("ud_zyxk_zysqid")
							.toString()).append("';");

			// 非异步加载
			try {
				WorkOrder workOrder = (WorkOrder) dao
						.executeQuery(workSbSql.toString(), new EntityResult(
								WorkOrder.class));
				return workOrder;
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				throw new DaoException("查询作业票信息失败！");
			}
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryWorkInfo", new Class<?>[] {
					SuperEntity.class, IQueryCallEventListener.class },
					new Object[] { workEntity, null });
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PDAWorkOrderInfoConfig> queryWorkInfoConfigInfo(
			SuperEntity workEntity, String functionType,
			IQueryCallEventListener callEventListener) throws HDException {
		// TODO Auto-generated method stub
		if (workEntity.getAttribute("zypclass") == null)
			throw new HDException("作业票信息的zypclass属性获取失败！");
		// if(workEntity.getAttribute("cssavefied") == null)
		// throw new HDException("作业票信息的cssavefied属性获取失败！");
		Object csSaveFied = workEntity.getAttribute("cssavefied");
		if (callEventListener == null) {
			StringBuilder configSbSql = new StringBuilder();
			// 主类pscode,contype,zypclass
			configSbSql.setLength(0);
			configSbSql
					.append("select pscode,contype,zypclass,contypedesc,conlevel,batappr,dycode,ifnull(contypelie,0) as contypelie,cbisenable  ");
			configSbSql.append(" from ud_zyxk_zysqpdasc ");
			configSbSql.append(" where zypclass='")
					.append(workEntity.getAttribute("zypclass").toString())
					.append("' and pscode='").append(functionType).append("' ");
			configSbSql
					.append(" and isactive=1 group by pscode ,contype,zypclass order by tab_order asc;");

			StringBuilder childSql = new StringBuilder();
			// 子类
			childSql.setLength(0);
			childSql.append("select sname,pscode,contype,ifnull(contypelie,0) as contypelie,dycode,otherisenable,otherfield,cbisenable,cbtype,zypclass,");
			childSql.append("valuewheretype,cbname,ifnull(cstype,'') as cstype,isorder,issavebtn,conlevel,batappr from ud_zyxk_zysqpdasc ");
			childSql.append(" where zypclass='")
					.append(workEntity.getAttribute("zypclass").toString())
					.append("' and pscode='").append(functionType).append("' ");
			childSql.append(" and isactive=1 order by tab_order asc;");

			// 非异步加载
			try {
				List<PDAWorkOrderInfoConfig> mainConfig = (List<PDAWorkOrderInfoConfig>) executeQuery(
						configSbSql.toString(), new EntityListResult(
								PDAWorkOrderInfoConfig.class));
				List<PDAWorkOrderInfoConfig> childConfig = (List<PDAWorkOrderInfoConfig>) executeQuery(
						childSql.toString(), new EntityListResult(
								PDAWorkOrderInfoConfig.class));
				int count = 0;// 标记措施的数量
				for (int i = 0; i < mainConfig.size(); i++) {
					List<SuperEntity> childList = null;
					for (int j = 0; j < childConfig.size(); j++) {
						if ((int) mainConfig.get(i).getContype() == (int) childConfig
								.get(j).getContype()) {
							if (childList == null)
								childList = new ArrayList<SuperEntity>();
							childList.add(childConfig.get(j));
							count++;
						}
					}
					mainConfig.get(i).setChildCount(count);// 标记子项的数量
					for(SuperEntity superEntity : childList){
						(((PDAWorkOrderInfoConfig)superEntity)).setChildCount(count);
					}
					if (csSaveFied != null
							&& (csSaveFied.toString()).contains(mainConfig
									.get(i).getContype().toString())) {
						mainConfig.get(i).setIsComplete(1);// 该类别已经操作
					} else {
						mainConfig.get(i).setIsComplete(0);// 表示该类别没有操作
					}
					mainConfig.get(i).setChild(
							PDAWorkOrderInfoConfig.class.getName(), childList);
					childList = null;
				}
				if (functionType.equalsIgnoreCase(IConfigEncoding.SP)
						|| functionType.equalsIgnoreCase(IConfigEncoding.FC)) {
					List<SuperEntity> childList = null;
					if (workEntity.getAttribute("isqtjc") != null
							&& Integer.parseInt(workEntity.getAttribute(
									"isqtjc").toString()) == 1) {
						// 如果该作业票有气体检测，增加气体检测导航栏
						// 增加气体检测的导航栏信息
						PDAWorkOrderInfoConfig gasConfig = new PDAWorkOrderInfoConfig();
						if (functionType.equalsIgnoreCase(IConfigEncoding.SP)) {
							gasConfig.setDycode(IConfigEncoding.SP_GAS_NUM);
						} else if (functionType
								.equalsIgnoreCase(IConfigEncoding.FC)) {
							gasConfig.setDycode(IConfigEncoding.FC_GAS_NUM);
						}
						gasConfig.setContype(IConfigEncoding.GAS_TYPE);
						gasConfig.setSname("气体检测");
						gasConfig.setPscode(functionType);
						gasConfig.setContypedesc("气体检测");
						if (csSaveFied != null
								&& csSaveFied
										.toString()
										.contains(
												String.valueOf(IConfigEncoding.GAS_TYPE))) {
							gasConfig.setIsComplete(1);
						} else {
							gasConfig.setIsComplete(0);
						}
						childList = new ArrayList<SuperEntity>();
						childList.add(gasConfig);
						gasConfig.setChild(
								PDAWorkOrderInfoConfig.class.getName(),
								childList);
						mainConfig.add(gasConfig);
					}
					if (functionType.equalsIgnoreCase(IConfigEncoding.SP)) {
						// 增加会签的导航栏信息
						PDAWorkOrderInfoConfig signConfig = new PDAWorkOrderInfoConfig();
						signConfig.setDycode(IConfigEncoding.SP_SIGN_NUM);
						signConfig.setContype(IConfigEncoding.SIGN_TYPE);
						signConfig.setPscode(functionType);
						signConfig.setSname("会签");
						signConfig.setContypedesc("会签");
						if (csSaveFied != null
								&& csSaveFied
										.toString()
										.contains(
												String.valueOf(IConfigEncoding.SIGN_TYPE))) {
							signConfig.setIsComplete(1);
						} else {
							signConfig.setIsComplete(0);
						}
						childList = new ArrayList<SuperEntity>();
						childList.add(signConfig);
						signConfig.setChild(
								PDAWorkOrderInfoConfig.class.getName(),
								childList);
						mainConfig.add(signConfig);
					}
				} else if (functionType.equalsIgnoreCase(IConfigEncoding.YQ)) {
					// 增加延期的导航栏信息
					List<SuperEntity> childList = null;
					PDAWorkOrderInfoConfig YQPDAConfigInfo = new PDAWorkOrderInfoConfig();
					YQPDAConfigInfo.setDycode(IConfigEncoding.YQ_SIGN_NUM);
					YQPDAConfigInfo.setContype(IConfigEncoding.YQ_SIGN_TYPE);
					YQPDAConfigInfo.setSname("延期确认");
					YQPDAConfigInfo.setPscode(IConfigEncoding.YQ);
					YQPDAConfigInfo.setCstype("");
					YQPDAConfigInfo.setCbisenable(0);
					YQPDAConfigInfo.setContypedesc("延期确认");
					if (csSaveFied != null
							&& csSaveFied
									.toString()
									.contains(
											String.valueOf(IConfigEncoding.YQ_SIGN_TYPE))) {
						YQPDAConfigInfo.setIsComplete(1);
					} else {
						YQPDAConfigInfo.setIsComplete(0);
					}
					childList = new ArrayList<SuperEntity>();
					childList.add(YQPDAConfigInfo);
					YQPDAConfigInfo.setChild(
							PDAWorkOrderInfoConfig.class.getName(), childList);
					mainConfig.add(YQPDAConfigInfo);
				}
				return mainConfig;
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				throw new DaoException("查询基础配置信息失败！");
			}
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryWorkInfoConfigInfo",
					new Class<?>[] { SuperEntity.class, String.class,
							IQueryCallEventListener.class }, new Object[] {
							workEntity, functionType, null });
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PDAWorkOrderInfoConfig> queryWorkInfoConfigInfo(
			SuperEntity workEntity, String functionType, Integer contye,
			IQueryCallEventListener callEventListener) throws HDException {
		// TODO Auto-generated method stub
		if (workEntity.getAttribute("zypclass") == null)
			throw new HDException("作业票信息的zypclass属性获取失败！");
		if (callEventListener == null) {

			StringBuilder childSql = new StringBuilder();
			// 子类
			childSql.setLength(0);
			childSql.append("select sname,pscode,contype,ifnull(contypelie,0) as contypelie,dycode,otherisenable,otherfield,cbisenable,cbtype,zypclass,");
			childSql.append("valuewheretype,cbname,ifnull(cstype,'') as cstype,isorder,issavebtn,conlevel,batappr from ud_zyxk_zysqpdasc ");
			childSql.append(" where zypclass='")
					.append(workEntity.getAttribute("zypclass").toString())
					.append("' and pscode='").append(functionType).append("' ");
			if (contye != null) {
				childSql.append(" and contype='")
						.append(String.valueOf(contye)).append("' ");
			}
			childSql.append(" and isactive=1 order by tab_order asc;");

			// 非异步加载
			try {
				List<PDAWorkOrderInfoConfig> configList = (List<PDAWorkOrderInfoConfig>) dao
						.executeQuery(childSql.toString(),
								new EntityListResult(
										PDAWorkOrderInfoConfig.class));
				for (int i = 0; i < configList.size(); i++) {
					configList.get(i).setChildCount(configList.size());
				}
				return configList;
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				throw new DaoException("查询基础配置信息失败！");
			}
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryWorkInfoConfigInfo",
					new Class<?>[] { SuperEntity.class, String.class,
							Integer.class, IQueryCallEventListener.class },
					new Object[] { workEntity, functionType, contye, null });
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HazardNotify> queryHarmInfo(SuperEntity workEntity,
			IQueryCallEventListener callEventListener) throws HDException {
		// TODO Auto-generated method stub
		if (workEntity.getAttribute("ud_zyxk_zysqid") == null)
			throw new HDException("获取作业申请信息的ud_zyxk_zysqid属性失败！");
		if (callEventListener == null) {
			StringBuilder harmSbSql = new StringBuilder();
			harmSbSql.setLength(0);
			harmSbSql
					.append("select whtable.ud_zyxk_zysq2hazardid,whtable.ud_zyxk_zysqid,whtable.hazardid,whtable.description,whtable.ispadselect, ");
//			harmSbSql
//					.append("(case when ifnull(selwh.selenum,'')!='' then 1 else whtable.isselect end) as isselect ");
			harmSbSql
			.append(" whtable.isselect ");
			harmSbSql
					.append(" from ud_zyxk_zysq2hazard as whtable left join (select ifnull(whshib,'') as selenum from ud_zyxk_zysq where ifnull(whshib,'')!='' ");
			harmSbSql
					.append(" and  ud_zyxk_zysqid='")
					.append(workEntity.getAttribute("ud_zyxk_zysqid")
							.toString()).append("') as selwh");
			harmSbSql
					.append(" on ','||selwh.selenum||',' like '%,'|| whtable.hazardid ||',%' ");
			harmSbSql
					.append(" where ifnull(whtable.whtype,0)=0 and  whtable.ud_zyxk_zysqid = '")
					.append(workEntity.getAttribute("ud_zyxk_zysqid")
							.toString()).append("' order by whtable.paixu, whtable.ud_zyxk_zysq2hazardid;");

			// 非异步加载
			try {
				return (List<HazardNotify>) dao.executeQuery(harmSbSql
						.toString(), new EntityListResult(HazardNotify.class));
			} catch (DaoException e) {
				// TODO: handle exception
				logger.error(e.getMessage(), e);
				throw new DaoException("查询作业票危害信息失败！");
			}
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryHarmInfo", new Class<?>[] {
					SuperEntity.class, IQueryCallEventListener.class },
					new Object[] { workEntity, null });
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkApplyMeasure> queryMeasureInfo(SuperEntity workEntity,
			SuperEntity workConfigEntity,
			IQueryCallEventListener callEventListener) throws HDException {
		// TODO Auto-generated method stub
		if (workEntity.getAttribute("ud_zyxk_zysqid") == null)
			throw new HDException("获取作业申请信息的ud_zyxk_zysqid属性失败！");
		if (workConfigEntity.getAttribute("cstype") == null)
			throw new HDException("获取界面配置信息的cstype属性失败！");
		if (callEventListener == null) {
			String csty = workConfigEntity.getAttribute("cstype").toString();
			String cstyStr = UtilTools.convertToSqlString(csty);// 措施项，带‘()’的
			String zysqid = workEntity.getAttribute("ud_zyxk_zysqid")
					.toString();
			StringBuilder measureSbSql = new StringBuilder();
			measureSbSql.setLength(0);

			measureSbSql
					.append("select cs.ud_zyxk_zysqid,cs.precautionid,cs.iszyfxfxadd as issupplement,cs.isnecessary,cs.ud_zyxk_zysq2precautionid,");
			measureSbSql
					.append(" cs.vqtcsfield,cs.description,cs.isselect,cs.value,cs.hazardid,cs.inputtext,cs.cstype,ifnull(wh.description,'') as whdesc,aln.description as cstydesc,");
			measureSbSql
					.append(" ifnull(wh.isselect,0) as wh,ifnull(per.opname,'') as persondesc,cs.checkresult  from ud_zyxk_zysq2precaution cs left join ")
					.append(" ud_zyxk_zysq2hazard wh");
			measureSbSql
					.append(" on cs.hazardid=wh.hazardid and cs.ud_zyxk_zysqid=wh.ud_zyxk_zysqid left join (select * from alndomain  where domainid='UDCSCLASS') aln on cs.cstype=aln.value  left join (select tableid,opid,opcode,opname,sptime from ");
			measureSbSql
					.append(" getstepconfirmpernew where type='SP01' and objectname='UD_ZYXK_ZYSQ' and tableid='")
					.append(workEntity.getAttribute("ud_zyxk_zysqid")
							.toString()).append("') ");
			measureSbSql
					.append("as per  on cs.ud_zyxk_zysqid=per.tableid and  cs.precautionid=per.opid  where ");
			measureSbSql
					.append(" cs.ud_zyxk_zysqid='")
					.append(workEntity.getAttribute("ud_zyxk_zysqid")
							.toString()).append("'");
			if (!StringUtils.isEmpty(csty) && !"QTCS".equals(csty))
				measureSbSql.append(" and cstype in ").append(cstyStr)
						.append(" ");
			measureSbSql
					.append(" order by cs.cstype desc,cs.hazardid,cs.paixu asc,cs.precautionid asc ; ");// cs.isnecessary

			// 非异步加载
			try {
				List<WorkApplyMeasure> measureList = (List<WorkApplyMeasure>) dao
						.executeQuery(measureSbSql.toString(),
								new EntityListResult(WorkApplyMeasure.class));
				if (Integer.valueOf(workConfigEntity.getAttribute("cbisenable")
						.toString()) == 1) {
					// 表示措施分组显示
					if (Integer.valueOf(workConfigEntity.getAttribute(
							"contypelie").toString()) == 1) {
						// 表示四列-危害（危害表）+措施
						return (List<WorkApplyMeasure>) UtilTools.groupList(
								(List) measureList, WorkApplyMeasure.class,
								"whdesc", "description");
					} else {
						// 表示三列-分类名（动态域）+措施
						return (List<WorkApplyMeasure>) UtilTools.groupList(
								(List) measureList, WorkApplyMeasure.class,
								"cstydesc", "description");
					}
				} else {
					return measureList;
				}
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				throw new DaoException("查询作业票措施信息失败！");
			}
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryMeasureInfo", new Class<?>[] {
					SuperEntity.class, SuperEntity.class,
					IQueryCallEventListener.class }, new Object[] { workEntity,
					workConfigEntity, null });
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkGuardEquipment> queryPpeInfo(SuperEntity workEntity,
			IQueryCallEventListener callEventListener) throws HDException {
		// TODO Auto-generated method stub
		if (workEntity.getAttribute("ud_zyxk_zysqid") == null)
			throw new HDException("获取作业申请信息的ud_zyxk_zysqid属性失败！");
		if (callEventListener == null) {
			StringBuilder ppeSbSql = new StringBuilder();
			ppeSbSql.setLength(0);
			ppeSbSql.append("select grfh.ud_zyxk_zysqid,grfh.ud_zyxk_zysq2ud_zyxk_grfhzbid,grfh.grfhzbnum,grfh.description,grfh.ispadselect, ");
//			ppeSbSql.append("(case when ifnull(selgrfh.selenum,'')!='' then 1 else grfh.isselect end) as isselect, ");
			ppeSbSql.append(" grfh.isselect, ");
			ppeSbSql.append(" grfh.value from  ud_zyxk_zysq2ud_zyxk_grfhzb as grfh left join (select ifnull(grfhzb,'') as selenum from ud_zyxk_zysq ");
			ppeSbSql.append(
					" where ifnull(grfhzb,'')!='' and  ud_zyxk_zysqid='")
					.append(workEntity.getAttribute("ud_zyxk_zysqid")
							.toString()).append("') as selgrfh ");
			ppeSbSql.append(" on ','||selgrfh.selenum||',' like '%,'|| grfh.grfhzbnum ||',%' ");
			ppeSbSql.append(" where  grfh.ud_zyxk_zysqid ='")
					.append(workEntity.getAttribute("ud_zyxk_zysqid")
							.toString()).append("' order by grfh.ud_zyxk_zysq2ud_zyxk_grfhzbid; ");

			// 非异步加载
			try {
				return (List<WorkGuardEquipment>) dao.executeQuery(ppeSbSql
						.toString(), new EntityListResult(
						WorkGuardEquipment.class));
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				throw new DaoException("查询作业票个人防护信息失败！");
			}
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryPpeInfo", new Class<?>[] {
					SuperEntity.class, IQueryCallEventListener.class },
					new Object[] { workEntity, null });
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public GasDetection queryGasInfo(SuperEntity workEntity,
			IQueryCallEventListener callEventListener) throws HDException {
		// TODO Auto-generated method stub
		if (workEntity.getAttribute("ud_zyxk_zysqid") == null)
			throw new HDException("获取作业申请信息的ud_zyxk_zysqid属性失败！");
		if (callEventListener == null) {
			// StringBuffer gasSbSql = new StringBuffer();
			// gasSbSql.setLength(0);
			// gasSbSql.append(
			// "select ud_zyxk_qtjcid,ud_zyxk_zysqid,jctime,jclocation_desc,jclocation,ishg,jcdept,jcmethod from ud_zyxk_qtjc where ud_zyxk_zysqid ='")
			// .append(workEntity.getAttribute("ud_zyxk_zysqid")
			// .toString()).append("';");
			StringBuilder domainSbSql = new StringBuilder();
			domainSbSql
					.append("select * from alndomain where domainid='UDJCMETHOD';");
			StringBuilder childSbSql = new StringBuilder();
			childSbSql
					.append("select aln.domainid as domainid,(case when ifnull(line.qttype,'')='' then aln.domainid||'|'||aln.value else line.qttype end) as qttype,line.qtvalue as qtvalue,aln.description as description,");
			childSbSql
					.append(" aln.maxvalue as maxvalue,aln.minvalue as minvalue,aln.isbj as isbj from (select domainid,description,value,maxvalue,minvalue,isbj from");
			childSbSql
					.append(" (select domainid,description,value from alndomain where domainid in (select domainid||value from alndomain where domainid = 'UDQTTYPE' order by value)) as alntable left join ");
			childSbSql
					.append(" (select qtname, main_tab.qtlx,qtpz.maxvalue as maxvalue, qtpz.minvalue as minvalue,qtpz.isbj as isbj from ud_zyxk_qtjcpz qtpz inner join");
			childSbSql
					.append(" (select (aln.domainid || aln.value) as qtlx,pz.qtlx as main_lx,pz.qtname as son_lx from ALNDOMAIN aln inner join  UD_ZYXK_QTJCPZ pz  on pz.QTLX=aln.value");
			childSbSql
					.append(" where aln.domainid='UDQTTYPE' order by value ) as main_tab on qtpz.qtlx=main_tab.main_lx and qtpz.qtname=main_tab.son_lx) as alnpz ");
			childSbSql
					.append(" on alntable.domainid=alnpz.qtlx and alntable.value=alnpz.qtname) as aln left join");
			childSbSql
					.append(" (select qttype, qtvalue from ud_zyxk_qtjcline where ud_zyxk_qtjcid");
			childSbSql
					.append(" in (select ud_zyxk_qtjcid from ud_zyxk_qtjc where ud_zyxk_zysqid = '")
					.append(workEntity.getAttribute("ud_zyxk_zysqid")
							.toString())
					.append("' order by ud_zyxk_qtjcid desc limit 1))");
			childSbSql
					.append("  as line on (aln.domainid||'|'||aln.value)=line.qttype  order by aln.domainid asc,line.qtvalue desc,value asc;");

			// 非异步加载
			try {
				// GasDetection gd = (GasDetection) dao.executeQuery(
				// sbSql.toString(), new EntityResult(GasDetection.class));
				GasDetection gd = null;
				if (gd == null) {
					gd = new GasDetection();
					gd.setUd_zyxk_zysqid(workEntity.getAttribute(
							"ud_zyxk_zysqid").toString());
				}
				List domainList = (List) dao.executeQuery(domainSbSql
						.toString(), new EntityListResult(Domain.class));
				gd.setChild(Domain.class.getName(), domainList);
				List<List<GasDetectSub>> gasLineList = (List<List<GasDetectSub>>) dao
						.executeQuery(childSbSql.toString(),
								new ListListResult(GasDetectSub.class,
										"domainid"));
				gd.setListChild(GasDetectSub.class.getName(),
						(List) gasLineList);
				return gd;
			} catch (DaoException e) {
				// TODO: handle exception
				logger.error(e.getMessage(), e);
				throw new DaoException("查询作业票气体检测信息失败！");
			}
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryGasInfo", new Class<?>[] {
					SuperEntity.class, IQueryCallEventListener.class },
					new Object[] { workEntity, null });
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Map<String, String>> queryHistoryGasInfo(
			SuperEntity workEntity, IQueryCallEventListener callEventListener)
			throws HDException {
		// TODO Auto-generated method stub
		if (workEntity.getAttribute("ud_zyxk_zysqid") == null)
			throw new HDException("获取作业申请信息的ud_zyxk_zysqid属性失败！");
		if (callEventListener == null) {
			// 非异步加载
			StringBuffer historySbSql = new StringBuffer();
			ArrayList<Map<String, String>> mapLists = new ArrayList<Map<String, String>>();
			List<GasDetection> mainList = null;
			Map<String, String> map = null;

			historySbSql.setLength(0);
			historySbSql
					.append("select ud_zyxk_qtjcid, jctime ,jclocation_desc ,ishg,jcdept , jcmethod,writenbypda from ud_zyxk_qtjc  ");
			historySbSql
					.append(" where ud_zyxk_zysqid ='")
					.append(workEntity.getAttribute("ud_zyxk_zysqid")
							.toString()).append("'  order by jctime desc;");
			try {
				mainList = (List<GasDetection>) dao.executeQuery(historySbSql
						.toString(), new EntityListResult(GasDetection.class));
				if (mainList == null || mainList.size() == 0)
					return null;
				PDAWorkOrderInfoConfig workConfigEntity = new PDAWorkOrderInfoConfig();
				workConfigEntity.setDycode(IConfigEncoding.SP_GAS_NUM);
				WorkApprovalPersonRecord personRecord = new WorkApprovalPersonRecord();
				personRecord.setTablename(ITableName.UD_ZYXK_QTJC);
				for (int i = 0; i < mainList.size(); i++) {
					GasDetection mainGasDetection = mainList.get(i);
					if (map == null)
						map = new LinkedHashMap<String, String>();
					map.put("检测时间", mainGasDetection.getJctime());
					map.put("检测位置", mainGasDetection.getJclocation_desc());
					map.put("检测单位", mainGasDetection.getJcdept());
					map.put("检测方式", mainGasDetection.getJcmethod());
					map.put("是否合格",
							(mainGasDetection.getIshg() == null || mainGasDetection
									.getIshg() == "0") ? "否" : "是");
					// personRecord.setTableid(mainList.get(i).getUd_zyxk_qtjcid()
					// .toString());
					historySbSql.setLength(0);
					historySbSql
							.append("select dotable.description as description,line.qtvalue as qtvalue from ud_zyxk_qtjcline as line inner join (select (domainid||'|'||value) as qttype");
					historySbSql
							.append(" ,description from alndomain where domainid in (select domainid||value from alndomain where domainid='UDQTTYPE')) as dotable on line.qttype=dotable.qttype");
					historySbSql
							.append(" where ud_zyxk_qtjcid='")
							.append(mainGasDetection.getUd_zyxk_qtjcid()
									.toString())
							.append("' order by line.qttype asc;");
					List<GasDetectSub> gasLineList = (List<GasDetectSub>) dao
							.executeQuery(historySbSql.toString(),
									new EntityListResult(GasDetectSub.class));
					for (int j = 0; j < gasLineList.size(); j++) {
						map.put(gasLineList.get(j).getDescription(),
								gasLineList.get(j).getQtvalue() == null ? null
										: gasLineList.get(j).getQtvalue()
												.toString());
					}
					// 审批人员信息
					// if (IWorkOrderStatus.GAS_STATUS_FINISH
					// .equals(mainGasDetection.getWritenbypda())) {
					// personRecord.setTableid(mainGasDetection
					// .getUd_zyxk_qtjcid());
					// } else {
					// personRecord.setTableid("UNDO");
					// .getUd_zyxk_qtjcid());
					// }
					personRecord.setTableid(mainGasDetection
							.getUd_zyxk_qtjcid());
					// List<WorkApprovalPermission> approvalList =
					// queryApprovalPermission(
					// workEntity, workConfigEntity, personRecord, null);
					// for (int j = 0; j < approvalList.size(); j++) {
					// map.put(approvalList.get(j).getSpfield_desc(),
					// approvalList.get(j).getPersondesc());
					// }
					List<WorkApprovalPersonRecord> records = queryHisetoryApprovalRecord(personRecord);
					for (int j = 0; j < records.size(); j++) {
						map.put(records.get(j).getValue(), records.get(j)
								.getPerson_name());
					}
					mapLists.add(map);
					map = null;
				}
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				throw new DaoException("查询作业票气体检测历史信息失败！");
			}
			return mapLists;
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryHistoryGasInfo",
					new Class<?>[] { SuperEntity.class,
							IQueryCallEventListener.class }, new Object[] {
							workEntity, null });
		}
		return null;
	}

	private List<WorkApprovalPersonRecord> queryHisetoryApprovalRecord(
			WorkApprovalPersonRecord personRecord) throws HDException {
		if (personRecord == null)
			return null;
		StringBuffer historyApprovalSbSql = new StringBuffer();
		historyApprovalSbSql
				.append("select person_name,value from ud_zyxk_zyspryjl where tablename='")
				.append(personRecord.getTablename()).append("' ");
		historyApprovalSbSql.append(" and ifnull(tableid,'')='")
				.append(personRecord.getTableid()).append("'");
		historyApprovalSbSql.append(" and ifnull(tableid,'')='")
				.append(personRecord.getTableid()).append("'");
		historyApprovalSbSql.append(";");
		try {
			return (List<WorkApprovalPersonRecord>) dao.executeQuery(
					historyApprovalSbSql.toString(), new EntityListResult(
							WorkApprovalPersonRecord.class));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
			throw new HDException("查询历史审批人失败！", e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public GasDetection queryUndoneGasInfo(SuperEntity workEntity,
			IQueryCallEventListener callEventListener) throws HDException {
		// TODO Auto-generated method stub
		if (workEntity.getAttribute("ud_zyxk_zysqid") == null)
			throw new HDException("获取作业申请信息的ud_zyxk_zysqid属性失败！");
		if (callEventListener == null) {
			StringBuilder unDoSbSql = new StringBuilder();
			unDoSbSql.setLength(0);
			// 非异步加载
			try {
				unDoSbSql
						.append("select ud_zyxk_qtjcid, jctime ,jclocation_desc ,ishg,jcdept ,jcmethod from ud_zyxk_qtjc  ");
				unDoSbSql
						.append(" where ud_zyxk_qtjcid in (select ud_zyxk_qtjcid from ud_zyxk_qtjc where ud_zyxk_zysqid='")
						.append(workEntity.getAttribute("ud_zyxk_zysqid")
								.toString())
						.append("' order by jctime desc limit 1)");
				unDoSbSql.append(" and writenbypda='")
						.append(IWorkOrderStatus.GAS_STATUS_PROCEED)
						.append("';");
				GasDetection gd = (GasDetection) dao.executeQuery(unDoSbSql
						.toString(), new EntityResult(GasDetection.class));
				if (gd == null || gd.getUd_zyxk_qtjcid() == null)
					return null;
				unDoSbSql.setLength(0);
				unDoSbSql
						.append("select aln.domainid as domainid,(case when ifnull(line.qttype,'')='' then aln.domainid||'|'||aln.value else line.qttype end) as qtlx,line.qtvalue as qtvalue,aln.description as description,");
				unDoSbSql
						.append(" aln.maxvalue as maxvalue,aln.minvalue as minvalue,aln.isbj as isbj from (select domainid,description,value,maxvalue,minvalue,isbj from");
				unDoSbSql
						.append(" (select domainid,description,value from alndomain where domainid in (select domainid||value from alndomain where domainid = 'UDQTTYPE' order by value)) as alntable left join ");
				unDoSbSql
						.append(" (select qtname, main_tab.qtlx,qtpz.maxvalue as maxvalue, qtpz.minvalue as minvalue,qtpz.isbj as isbj from ud_zyxk_qtjcpz qtpz inner join");
				unDoSbSql
						.append(" (select (aln.domainid || aln.value) as qtlx,pz.qtlx as main_lx,pz.qtname as son_lx from ALNDOMAIN aln inner join  UD_ZYXK_QTJCPZ pz  on pz.QTLX=aln.value");
				unDoSbSql
						.append(" where aln.domainid='UDQTTYPE' order by value ) as main_tab on qtpz.qtlx=main_tab.main_lx and qtpz.qtname=main_tab.son_lx) as alnpz ");
				unDoSbSql
						.append(" on alntable.domainid=alnpz.qtlx and alntable.description=alnpz.qtname) as aln left join");
				unDoSbSql
						.append(" (select qttype, qtvalue from ud_zyxk_qtjcline where ud_zyxk_qtjcid");
				unDoSbSql.append(" ='").append(gd.getUd_zyxk_qtjcid())
						.append("' )");
				unDoSbSql
						.append("  as line on (aln.domainid||'|'||aln.value)=line.qttype order by aln.domainid asc,line.qtvalue desc,value asc;");
				List<List<GasDetectSub>> gasLineList = (List<List<GasDetectSub>>) dao
						.executeQuery(unDoSbSql.toString(), new ListListResult(
								GasDetectSub.class, "domainid"));
				gd.setListChild(GasDetectSub.class.getName(),
						(List) gasLineList);
				return gd;
			} catch (DaoException e) {
				// TODO: handle exception
				logger.error(e.getMessage(), e);
				throw new DaoException("查询作业票气体检测信息失败！");
			}
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryUndoneGasInfo",
					new Class<?>[] { SuperEntity.class,
							IQueryCallEventListener.class }, new Object[] {
							workEntity, null });
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public WorkMeasureReview queryReviewInfo(SuperEntity workEntity,
			SuperEntity workConfigEntity,
			IQueryCallEventListener callEventListener) throws HDException {
		// TODO Auto-generated method stub

		if (workConfigEntity.getAttribute("pscode") == null)
			throw new HDException("获取作业配置信息的pscode属性失败！");
		if (callEventListener == null) {
			if (IConfigEncoding.JJB01.equals(workConfigEntity
					.getAttribute("pscode"))
					|| IConfigEncoding.JJB02.equals(workConfigEntity
							.getAttribute("pscode"))) {
				// 交接班
				WorkMeasureReview tempWmr = null;
				StringBuilder jjbSbSql = new StringBuilder();
				jjbSbSql.append("select * from ud_zyxk_zycsfc where pagetype='")
						.append(workConfigEntity.getAttribute("pscode"))
						.append("' ");
				jjbSbSql.append(" and ud_zyxk_zysqid='")
						.append(workEntity.getAttribute("ud_zyxk_zysqid")
								.toString()).append("' ");
				jjbSbSql.append(" and status!='")
						.append(IWorkOrderStatus.REVIEW_STATUS_FINISH)
						.append("';");
				try {
					tempWmr = (WorkMeasureReview) dao.executeQuery(jjbSbSql
							.toString(), new EntityResult(
							WorkMeasureReview.class));
				} catch (DaoException e) {
					logger.error(e.getMessage(), e);
					tempWmr = null;
				}
				if (tempWmr == null) {
					tempWmr = new WorkMeasureReview();
					tempWmr.setZysqnum(workEntity.getAttribute("zysqnum") == null ? ""
							: workEntity.getAttribute("zysqnum").toString());
					tempWmr.setUd_zyxk_zysqid(workEntity.getAttribute(
							"ud_zyxk_zysqid").toString());
					tempWmr.setStatus(IWorkOrderStatus.REVIEW_STATUS_PROCEED);
					tempWmr.setIsupload(0);
					tempWmr.setDatasource("PDA");
					tempWmr.setPagetype(workConfigEntity.getAttribute("pscode")
							.toString());
				}
				return tempWmr;

			}
			WorkMeasureReview wmReview = getMainMeasureReview(workEntity,
					workConfigEntity, true);
			if (wmReview == null)
				throw new HDException("获取复查信息失败！");
			StringBuilder reViewSbSql = new StringBuilder();
			String csty = workConfigEntity.getAttribute("cstype").toString();
			String zysqid = workEntity.getAttribute("ud_zyxk_zysqid")
					.toString();
			reViewSbSql.setLength(0);
			reViewSbSql
					.append("select cs.zycsfclinenum,cs.precautionid,cs.zycsfcnum,cs.vqtcsfield,cs.description,cs.isselect,cs.value,cs.hazardid,cs.cstype,cs.checkresult,single.opname as persondesc,");
			reViewSbSql
					.append("ifnull(wh.description,'') as whdesc,aln.description as cstydesc, ifnull(wh.isselect,0) as wh  from ud_zyxk_zycsfcline cs left join")
					.append(" (select * from ud_zyxk_zysq2hazard where ud_zyxk_zysqid='")
					.append(zysqid).append("' )wh");
			reViewSbSql
					.append("  on cs.HAZARDID=wh.HAZARDID left join (select * from alndomain  where domainid='UDCSCLASS') aln on cs.cstype=aln.value left join  (select tableid,opid,opcode,opname,sptime from getstepconfirmpernew where ");
			reViewSbSql.append("  type='")
					.append(workConfigEntity.getAttribute("pscode").toString())
					.append("'  and tableid='").append(wmReview.getZycsfcnum())
					.append("') as single  ");
			reViewSbSql
					.append(" on single.opid = cs.precautionid where cs.zycsfcnum ='")
					.append(wmReview.getZycsfcnum()).append("'");
			if (!StringUtils.isEmpty(csty) && !"QTCS".equals(csty))
				reViewSbSql.append(" and cstype in ")
						.append(UtilTools.convertToSqlString(csty)).append(" ");
			reViewSbSql
					.append(" order by cs.cstype desc,cs.hazardid,cs.value desc,cs.paixu asc,cs.precautionid asc ;");
			// 非异步加载
			try {
				List child = (List) dao.executeQuery(reViewSbSql.toString(),
						new EntityListResult(MeasureReviewSub.class));
				// wmReview.setChild(MeasureReviewSub.class.getName(), child);
				if (Integer.valueOf(workConfigEntity.getAttribute("cbisenable")
						.toString()) == 1) {
					// 表示措施分组显示
					if (Integer.valueOf(workConfigEntity.getAttribute(
							"contypelie").toString()) == 1) {
						// 表示四列-危害（危害表）+措施
						wmReview.setChild(MeasureReviewSub.class.getName(),
								(List<SuperEntity>) UtilTools.groupList(child,
										MeasureReviewSub.class, "whdesc",
										"description"));
					} else {
						// 表示三列-分类名（动态域）+措施
						wmReview.setChild(MeasureReviewSub.class.getName(),
								(List<SuperEntity>) UtilTools.groupList(child,
										MeasureReviewSub.class, "cstydesc",
										"description"));
					}
				} else {
					wmReview.setChild(MeasureReviewSub.class.getName(), child);
				}
				return wmReview;
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				throw new DaoException("查询作业票措施复查信息失败！");
			}
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryReviewInfo", new Class<?>[] {
					SuperEntity.class, SuperEntity.class,
					IQueryCallEventListener.class }, new Object[] { workEntity,
					workConfigEntity, null });
		}
		return null;
	}

	@Override
	public WorkDelay queryDelayInfo(SuperEntity workEntity, String zyscNum,
			IQueryCallEventListener callEventListener) throws HDException {
		// TODO Auto-generated method stub

		if (workEntity.getAttribute("ud_zyxk_zysqid") == null)
			throw new HDException("获取作业申请信息的ud_zyxk_zysqid属性失败！");
		if (callEventListener == null) {
			StringBuilder deLaySbSql = new StringBuilder();
			deLaySbSql.setLength(0);
			deLaySbSql
					.append("select yqstarttime,yqendtime,ud_zyxk_zyyqid from ud_zyxk_zyyq where ud_zyxk_zysqid = '")
					.append(workEntity.getAttribute("ud_zyxk_zysqid")
							.toString()).append("' ");
			deLaySbSql.append(" and zycsfcnum='").append(zyscNum)
					.append("' order by ud_zyxk_zyyqid desc limit 1;");

			// 非异步加载
			try {
				WorkDelay workDelay = (WorkDelay) dao.executeQuery(
						deLaySbSql.toString(),
						new EntityResult(WorkDelay.class));
				if (workDelay == null) {
					workDelay = new WorkDelay();
					workDelay.setUd_zyxk_zysqid(workEntity.getAttribute(
							"ud_zyxk_zysqid").toString());
				}
				return workDelay;
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				throw new DaoException("查询环节点信息失败！");
			}
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryDelayInfo", new Class<?>[] {
					SuperEntity.class, String.class,
					IQueryCallEventListener.class }, new Object[] { workEntity,
					zyscNum, null });
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkApprovalPermission> queryApprovalPermission(
			SuperEntity workEntity, SuperEntity workConfigEntity,
			SuperEntity record, IQueryCallEventListener callEventListener)
			throws HDException {
		// TODO Auto-generated method stub
		if (workEntity.getAttribute("sddept") == null)
			throw new HDException("获取作业票信息的sddept属性失败！");
		if (workEntity.getAttribute("ud_zyxk_zysqid") == null)
			throw new HDException("获取作业申请信息的ud_zyxk_zysqid属性失败！");
		if (workConfigEntity.getAttribute("dycode") == null)
			throw new HDException("获取基础配置信息的dycode属性失败！");
		if (callEventListener == null) {
			StringBuilder approvalSbSql = new StringBuilder();
			approvalSbSql.setLength(0);
			if (workConfigEntity.getAttribute("conlevel") != null
					&& Integer.parseInt(workConfigEntity.getAttribute(
							"conlevel").toString()) == 1) {
				// 走逐条环节点加载查询
				approvalSbSql
						.append(" select ud_zyxk_zyspqxid,ifnull(spfield_desc,'没有配置') as spfield_desc,spfield ,spqx.zylocation,'1' as contype,isinput,pdapaixu,ismust,bpermulcard,");
				approvalSbSql
						.append(" wttype,wtlevel,qxperson,spqx.iscbs,spqx.qxrole,spjl.person as personid,spjl.sptime as sptime,ifnull(isend,0) as isend,spqx.iscbszz,spqx.status as status, ");
				approvalSbSql
						.append(" spjl.person_name as persondesc,bmsgremind as ismessage,spjl.person_name as defaultpersondesc,spjl.person as defaultpersonid  from");
				approvalSbSql
						.append(" (select dept_spfa.ud_zyxk_spfaid,dept_spfa.vdept,dept_spfa.vreportdeptnum from (select deptnum, vreportdeptnum from ud_sy_dept as dep where");
				approvalSbSql
						.append(" (select vreportdeptnum from ud_sy_dept where deptnum = '")
						.append(workEntity.getAttribute("sddept").toString())
						.append("') like dep.vreportdeptnum || '%' order by ifnull(vreportdeptnum, '') desc)");
				approvalSbSql
						.append(" as temp_dept left join (select distinct fa.ud_zyxk_spfaid,fa.vdept ,(case when ifnull(fa.vdept,'')='' then (select vreportdeptnum from ud_sy_dept as dep");
				approvalSbSql
						.append(" where  (select vreportdeptnum from ud_sy_dept where deptnum = '")
						.append(workEntity.getAttribute("sddept").toString())
						.append("') like dep.vreportdeptnum || '%' order by ifnull(vreportdeptnum, '') asc limit 1)");
				approvalSbSql
						.append(" else dept.vreportdeptnum end) as vreportdeptnum from ud_sy_dept as dept left join ud_zyxk_spfa as fa");
				approvalSbSql
						.append(" on ','|| fa.vdept ||',' like '%,' || dept.deptnum || ',%' or ifnull(fa.vdept,'')=''  where ifnull(fa.ud_zyxk_spfaid,'')!=''  and fa.visqy=1 )");
				approvalSbSql
						.append(" as dept_spfa on temp_dept.vreportdeptnum=dept_spfa.vreportdeptnum order by dept_spfa.vreportdeptnum desc limit 1) as spfa,");
				approvalSbSql
						.append(" ud_zyxk_zysq as zysq,ud_zyxk_zyspqx  as spqx left join  ( ");
				approvalSbSql
						.append(" select opname as person_name,sptime,spnode,tabcode,opcode as person, tableid,");
				approvalSbSql
						.append(" a.ud_id,a.tableid as ud_zyxk_zysqid,a.opid ,ud_type  from ud_onemeasure_person as a ");
				approvalSbSql
						.append(" where a.ud_id in (select  ud_id from ud_onemeasure_person where ");
				approvalSbSql
						.append(" tabcode='")
						.append(workConfigEntity.getAttribute("dycode")
								.toString()).append("' ");
				approvalSbSql
						.append(" and  ud_type='")
						.append(workConfigEntity.getAttribute("pscode")
								.toString()).append("'  ");
				if (record instanceof MultitermMeasureAffirm) {
					MultitermMeasureAffirm itemRecord = (MultitermMeasureAffirm) record;
					approvalSbSql.append(" and tablename='")
							.append(itemRecord.getTablename())
							.append("' and  tableid='")
							.append(itemRecord.getTableid())
							.append("' and  opid='")
							.append(itemRecord.getOpid()).append("'  ");
				} else {
					approvalSbSql
							.append(" and tablename='' and  tableid='' and  opid=''  ");
				}
				approvalSbSql
						.append(" group by tableid,opid,spnode order by sptime desc limit 1 ) ");
				approvalSbSql
						.append("  ) as spjl on spqx.spfield=spjl.spnode and spqx.zylocation=spjl.tabcode ");
				approvalSbSql
						.append(" where spfa.ud_zyxk_spfaid = spqx.ud_zyxk_spfaid and zysq.ud_zyxk_zysqid in ('")
						.append(workEntity.getAttribute("ud_zyxk_zysqid")
								.toString())
						.append("','@共用id') and ','|| wttype ||',' like '%,' ||zysq.zypclass || ',%' and");
				approvalSbSql
						.append(" (wtlevel=ifnull(zysq.zylevel,'') or  ifnull(wtlevel,'')='')  and spqx.zylocation='")
						.append(workConfigEntity.getAttribute("dycode")
								.toString())
						.append("'  order by isend,pdapaixu,iloadorder ;");

			} else {
				approvalSbSql
						.append(" select ud_zyxk_zyspqxid,ifnull(spfield_desc,'没有配置') as spfield_desc,spfield ,spqx.zylocation,'1' as contype,isinput,pdapaixu,ismust,bpermulcard,");
				approvalSbSql
						.append(" wttype,wtlevel,qxperson,spqx.iscbs,spqx.qxrole,spjl.person as personid,spjl.sptime as sptime,ifnull(isend,0) as isend,spqx.iscbszz,spqx.status as status, ");
				approvalSbSql
						.append(" spjl.person_name as persondesc,bmsgremind as ismessage,spjl.ud_zyxk_zyspryjlid,spjl.person_name as defaultpersondesc,spjl.person as defaultpersonid  from");
				approvalSbSql
						.append(" (select dept_spfa.ud_zyxk_spfaid,dept_spfa.vdept,dept_spfa.vreportdeptnum from (select deptnum, vreportdeptnum from ud_sy_dept as dep where");
				approvalSbSql
						.append(" (select vreportdeptnum from ud_sy_dept where deptnum = '")
						.append(workEntity.getAttribute("sddept").toString())
						.append("') like dep.vreportdeptnum || '%' order by ifnull(vreportdeptnum, '') desc)");
				approvalSbSql
						.append(" as temp_dept left join (select distinct fa.ud_zyxk_spfaid,fa.vdept ,(case when ifnull(fa.vdept,'')='' then (select vreportdeptnum from ud_sy_dept as dep");
				approvalSbSql
						.append(" where  (select vreportdeptnum from ud_sy_dept where deptnum = '")
						.append(workEntity.getAttribute("sddept").toString())
						.append("') like dep.vreportdeptnum || '%' order by ifnull(vreportdeptnum, '') asc limit 1)");
				approvalSbSql
						.append(" else dept.vreportdeptnum end) as vreportdeptnum from ud_sy_dept as dept left join ud_zyxk_spfa as fa");
				approvalSbSql
						.append(" on ','|| fa.vdept ||',' like '%,' || dept.deptnum || ',%' or ifnull(fa.vdept,'')=''  where ifnull(fa.ud_zyxk_spfaid,'')!=''  and fa.visqy=1 )");
				approvalSbSql
						.append(" as dept_spfa on temp_dept.vreportdeptnum=dept_spfa.vreportdeptnum order by dept_spfa.vreportdeptnum desc limit 1) as spfa,");
				approvalSbSql
						.append(" ud_zyxk_zysq as zysq,ud_zyxk_zyspqx  as spqx left join (select ud_zyxk_zyspryjlid,person,sptime,inputall,spnode,dycode,person_name,ifnull(tableid,'0') as tableid,");
				approvalSbSql
						.append(" ud_zyxk_zysqid from ud_zyxk_zyspryjl where ud_zyxk_zysqid in ('")
						.append(workEntity.getAttribute("ud_zyxk_zysqid")
								.toString())
						.append("','@共用id') and dycode='")
						.append(workConfigEntity.getAttribute("dycode")
								.toString()).append("' ");
				WorkApprovalPersonRecord personRecord = null;
				if (record instanceof WorkApprovalPersonRecord) {
					personRecord = (WorkApprovalPersonRecord) record;
				}
				if (personRecord != null
						&& !StringUtils.isEmpty(personRecord.getTablename())
						&& !StringUtils.isEmpty(personRecord.getTableid())
						&& !"UNDO".equals(personRecord.getTableid())) {
					approvalSbSql.append(" and ifnull(tablename,'')='")
							.append(personRecord.getTablename())
							.append("' and ifnull(tableid,'')='")
							.append(personRecord.getTableid()).append("' ");
				} else {
					approvalSbSql.append(" and ifnull(tablename,'')='' ")
							.append(" and ifnull(tableid,'')='' ");
				}
				approvalSbSql
						.append(" ) as spjl on spqx.spfield=spjl.spnode and spqx.zylocation=spjl.dycode");
				approvalSbSql
						.append(" where spfa.ud_zyxk_spfaid = spqx.ud_zyxk_spfaid and zysq.ud_zyxk_zysqid in ('")
						.append(workEntity.getAttribute("ud_zyxk_zysqid")
								.toString())
						.append("','@共用id') and ','|| wttype ||',' like '%,' ||zysq.zypclass || ',%' and");
				approvalSbSql
						.append(" (wtlevel=ifnull(zysq.zylevel,'') or  ifnull(wtlevel,'')='')  and spqx.zylocation='")
						.append(workConfigEntity.getAttribute("dycode")
								.toString())
						.append("'  order by isend,pdapaixu,iloadorder ;");
			}
			// 非异步加载
			try {
				return (List<WorkApprovalPermission>) dao.executeQuery(
						approvalSbSql.toString(), new EntityListResult(
								WorkApprovalPermission.class));
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				throw new DaoException("查询环节点信息失败！");
			}
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler
					.execute(this, "queryApprovalPermission", new Class<?>[] {
							SuperEntity.class, SuperEntity.class,
							SuperEntity.class, IQueryCallEventListener.class },
							new Object[] { workEntity, workConfigEntity,
									record, null });
		}
		return null;
	}

	/**
	 * getMainMeasureReview:(获取复查主记录). <br/>
	 * date: 2014年10月17日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param workConfigEntity
	 * @return
	 * @throws HDException
	 */
	private WorkMeasureReview getMainMeasureReview(SuperEntity workEntity,
			SuperEntity workConfigEntity, boolean isLooper) throws HDException {
		if (workEntity.getAttribute("ud_zyxk_zysqid") == null)
			throw new HDException("获取作业申请信息的ud_zyxk_zysqid属性失败！");
		if (workConfigEntity.getAttribute("pscode") == null)
			throw new HDException("获取作业配置信息的pscode属性失败！");
		WorkMeasureReview wmr = null;
		try {
			StringBuilder measureReviewSbSql = new StringBuilder();
			measureReviewSbSql.setLength(0);
			measureReviewSbSql
					.append("select pagetype,zysqnum,zycsfcnum,ud_zyxk_zysqid,status,csnum,cssavenum from ud_zyxk_zycsfc where pagetype='")
					.append(workConfigEntity.getAttribute("pscode").toString())
					.append("' and  ud_zyxk_zysqid ='");
			measureReviewSbSql
					.append(workEntity.getAttribute("ud_zyxk_zysqid")
							.toString()).append("' and status!='")
					.append(IWorkOrderStatus.REVIEW_STATUS_FINISH)
					.append("'  ");
			measureReviewSbSql.append(" order by rowid desc limit 1 ; ");
			wmr = (WorkMeasureReview) dao.executeQuery(measureReviewSbSql
					.toString(), new EntityResult(WorkMeasureReview.class));
			if (wmr == null) {
				// 表示该功能下的已经彻底完成，复制一份最新的数据
				measureReviewSbSql.setLength(0);
				measureReviewSbSql
						.append("select pagetype,zysqnum,zycsfcnum,ud_zyxk_zysqid,status,csnum,cssavenum from ud_zyxk_zycsfc where pagetype in ('")
						.append(IConfigEncoding.FC).append("','")
						.append(IConfigEncoding.YQ)
						.append("') and ud_zyxk_zysqid ='");
				measureReviewSbSql.append(
						workEntity.getAttribute("ud_zyxk_zysqid").toString())
						.append("' ");
				measureReviewSbSql
						.append(" order by rowid desc limit 1 ; ");
				WorkMeasureReview tempWmr = (WorkMeasureReview) dao
						.executeQuery(measureReviewSbSql.toString(),
								new EntityResult(WorkMeasureReview.class));
				if (tempWmr == null) {
					// 表示没有操作过作业措施复查
					tempWmr = new WorkMeasureReview();
					tempWmr.setZysqnum(workEntity.getAttribute("zysqnum") == null ? ""
							: workEntity.getAttribute("zysqnum").toString());
					tempWmr.setUd_zyxk_zysqid(workEntity.getAttribute(
							"ud_zyxk_zysqid").toString());
					tempWmr.setStatus(IWorkOrderStatus.REVIEW_STATUS_PROCEED);
					tempWmr.setIsupload(0);
					tempWmr.setDatasource("PDA");
					tempWmr.setCssavenum("");
					if (workConfigEntity instanceof PDAWorkOrderInfoConfig) {
						PDAWorkOrderInfoConfig pdaConfig = (PDAWorkOrderInfoConfig) workConfigEntity;
						tempWmr.setCsnum(pdaConfig.getChildCount());
					}
					tempWmr.setPagetype(workConfigEntity.getAttribute("pscode")
							.toString());
					List<MeasureReviewSub> listSubs = new ArrayList<MeasureReviewSub>();
					measureReviewSbSql.setLength(0);
					measureReviewSbSql
							.append("select * from ud_zyxk_zysq2precaution where ud_zyxk_zysqid='")
							.append(workEntity.getAttribute("ud_zyxk_zysqid")
									.toString()).append("';");
					List<WorkApplyMeasure> applyMeasures = (List) dao
							.executeQuery(
									measureReviewSbSql.toString(),
									new EntityListResult(WorkApplyMeasure.class));
					MeasureReviewSub reviewSub = null;
					for (int i = 0; i < applyMeasures.size(); i++) {
						reviewSub = new MeasureReviewSub();
						reviewSub.setCstype(applyMeasures.get(i).getCstype());
						reviewSub.setDescription(applyMeasures.get(i)
								.getDescription());
						reviewSub.setHazardid(applyMeasures.get(i)
								.getHazardid());
						// reviewSub.setIsconfirm(applyMeasures.get(i).getIsconfirm());
						reviewSub.setIsselect(applyMeasures.get(i)
								.getIsselect());
						reviewSub.setValue(applyMeasures.get(i).getValue());
						reviewSub.setVqtcsfield(applyMeasures.get(i)
								.getVqtcsfield());
						reviewSub.setPrecautionid(applyMeasures.get(i)
								.getPrecautionid());
						reviewSub.setPaixu(applyMeasures.get(i).getPaixu());
						reviewSub.setCheckresult(applyMeasures.get(i)
								.getCheckresult());
						listSubs.add(reviewSub);
					}
					tempWmr.setChild(MeasureReviewSub.class.getName(),
							(List) listSubs);
					SequenceGenerator.genPrimaryKeyValue(tempWmr,
							MeasureReviewSub.class);// 创建新的主键
					copyMeasureReview(workEntity, tempWmr);
				} else {
					// 复制最近一条记录
					measureReviewSbSql.setLength(0);
					measureReviewSbSql
							.append("select * from ud_zyxk_zycsfcline where zycsfcnum='")
							.append(tempWmr.getZycsfcnum()).append("';");
					List<MeasureReviewSub> childReview = (List) dao
							.executeQuery(
									measureReviewSbSql.toString(),
									new EntityListResult(MeasureReviewSub.class));
					for (int i = 0; i < childReview.size(); i++) {
						childReview.get(i).setIsupload(0);
						childReview.get(i).setIsconfirm(null);
					}
					tempWmr.setChild(MeasureReviewSub.class.getName(),
							(List) childReview);
					SequenceGenerator.genPrimaryKeyValue(tempWmr,
							MeasureReviewSub.class);// 创建新的主键
					tempWmr.setStatus(IWorkOrderStatus.REVIEW_STATUS_PROCEED);
					tempWmr.setIsupload(0);
					tempWmr.setPagetype(workConfigEntity.getAttribute("pscode")
							.toString());
					tempWmr.setDatasource("PDA");
					tempWmr.setCssavenum("");
					if (workConfigEntity instanceof PDAWorkOrderInfoConfig) {
						PDAWorkOrderInfoConfig pdaConfig = (PDAWorkOrderInfoConfig) workConfigEntity;
						tempWmr.setCsnum(pdaConfig.getChildCount());
					}
					copyMeasureReview(workEntity, tempWmr);    
				}
			}
			if (wmr == null && isLooper)
				wmr = getMainMeasureReview(workEntity, workConfigEntity, false);
			return wmr;
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			throw new DaoException("判断是否进行措施复查失败！");
		}
	}

	/**
	 * copyMeasureReview:(复制一份措施复查的信息). <br/>
	 * date: 2014年11月5日 <br/>
	 * 
	 * @author zhaofeng
	 * @param wmr
	 * @throws DaoException
	 */
	private void copyMeasureReview(SuperEntity workEntity, WorkMeasureReview wmr)
			throws DaoException {
		IConnectionSource connectionSource = null;
		IConnection connection = null;
		try {
			connectionSource = ConnectionSourceManager.getInstance()
					.getJdbcPoolConSource();
			connection = connectionSource.getConnection();
			dao.insertEntity(connection, wmr);
			dao.insertEntities(connection,
					wmr.getChild(MeasureReviewSub.class.getName()));
			// 增加更新作业申请
			dao.executeUpdate(
					connection,
					"update ud_zyxk_zysq set CSNum=0,CSSaveFied='',CSSaveNum='' where ud_zyxk_zysqid='"
							+ wmr.getUd_zyxk_zysqid() + "';");
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
			throw new DaoException("获取复查信息失败！", e);
		} finally {
			if (connectionSource != null) {
				try {
					connectionSource.releaseConnection(connection);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage(), e);
					throw new DaoException(e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * TODO 进入审批中的查询
	 * 
	 * @see com.hd.hse.service.workorder.queryinfo.IQueryWorkInfo#querySiteAuditBasicInfo(com.hd.hse.common.entity.SuperEntity,
	 *      String,
	 *      com.hd.hse.service.workorder.queryinfo.IQueryCallEventListener)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public WorkOrder querySiteAuditBasicInfo(SuperEntity workEntity,
			String functionType, IQueryCallEventListener callEventListener)
			throws HDException {
		// TODO Auto-generated method stub
		if (callEventListener == null) {
			// 非异步加载
			WorkOrder workOrder = queryWorkInfo(workEntity, callEventListener);
			List<PDAWorkOrderInfoConfig> workConfigList = queryWorkInfoConfigInfo(
					workOrder, functionType, callEventListener);
			workOrder.setChild(PDAWorkOrderInfoConfig.class.getName(),
					(List) workConfigList);
			if (!IConfigEncoding.SP.equals(functionType)) {
				// 判断是否是最新复查措施
				StringBuilder measureReviewSbSql = new StringBuilder();
				measureReviewSbSql.setLength(0);
				measureReviewSbSql
						.append("select pagetype,zysqnum,zycsfcnum,ud_zyxk_zysqid,status,csnum from ud_zyxk_zycsfc where pagetype='")
						.append(functionType)
						.append("' and  ud_zyxk_zysqid ='");
				measureReviewSbSql
						.append(workEntity.getAttribute("ud_zyxk_zysqid")
								.toString()).append("' and status!='")
						.append(IWorkOrderStatus.REVIEW_STATUS_FINISH)
						.append("'  ");
				measureReviewSbSql
						.append(" order by zycsfcnum desc limit 1 ; ");
				WorkMeasureReview wmr = (WorkMeasureReview) dao.executeQuery(
						measureReviewSbSql.toString(), new EntityResult(
								WorkMeasureReview.class));
				if (wmr == null) {
					workOrder.setAttribute("CSNum", 0);
					workOrder.setAttribute("CSSaveFied", "");
					workOrder.setAttribute("CSSaveNum", "");
					ServiceActivityUtils.getRefreshWorkConfigInfos(workOrder,
							(List) workConfigList);
				}
				return workOrder;
			}
			if (workConfigList != null && workConfigList.size() > 0) {
				if (workConfigList.get(0).getContype() == IConfigEncoding.HARM_TYPE) {
					// 危害
					List harmList = queryHarmInfo(workEntity, callEventListener);
					workOrder.setChild(HazardNotify.class.getName(), harmList);
				} else if (workConfigList.get(0).getContype() == IConfigEncoding.PPE_TYPE) {
					// 个人防护装备
					List ppeList = queryPpeInfo(workEntity, callEventListener);
					workOrder.setChild(WorkGuardEquipment.class.getName(),
							ppeList);
				}
				// else if (workConfigList.get(0).getContype() ==
				// IConfigEncoding.MEASURE_TYPE) {
				// // 措施
				// List<PDAWorkOrderInfoConfig> configMeasureList = (List)
				// workConfigList
				// .get(0).getChild(
				// PDAWorkOrderInfoConfig.class.getName());
				// List<List<SuperEntity>> measureListList = new
				// ArrayList<List<SuperEntity>>();
				// for (int i = 0; i < configMeasureList.size(); i++) {
				// List measureList = queryMeasureInfo(workEntity,
				// configMeasureList.get(i), callEventListener);
				// measureListList.add(measureList);
				// }
				// workOrder.setListChild(WorkApplyMeasure.class.getName(),
				// measureListList);
				// }
			}
			return workOrder;
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "querySiteAuditBasicInfo",
					new Class<?>[] { SuperEntity.class, String.class,
							IQueryCallEventListener.class }, new Object[] {
							workEntity, functionType, null });
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> queryPhoto(SuperEntity srcEntity,
			SuperEntity configEntity) throws HDException {
		// TODO Auto-generated method stub
		String tablePKField = srcEntity.getPrimaryKey();
		String tableCode = srcEntity.getDBTableName();
		StringBuilder photoSbSql = new StringBuilder();
		photoSbSql.setLength(0);
		photoSbSql.append("select * from hse_sys_image where");
		photoSbSql.append(" tablename = '").append(tableCode).append("'");
		photoSbSql.append(" and tableid = '")
				.append(srcEntity.getAttribute(tablePKField)).append("'");
		if (configEntity != null) {
			String funcode = (String) configEntity.getAttribute("pscode");
			if (!StringUtils.isEmpty(funcode)) {
				photoSbSql.append(" and funcode = '").append(funcode)
						.append("'");
			}
		}
		List<Image> lstImage = (ArrayList<Image>) dao.executeQuery(
				photoSbSql.toString(), new EntityListResult(Image.class));
		return lstImage;
	}

	@Override
	public SysActionAgeConfig querySysActionAgeConfig(String action)
			throws HDException {
		// TODO Auto-generated method stub
		SysActionAgeConfig saConfig = null;
		try {
			StringBuilder sxSbSql = new StringBuilder();
			sxSbSql.append("select * from sys_action_sxpz where actiontype='")
					.append(action).append("';");
			saConfig = (SysActionAgeConfig) dao.executeQuery(
					sxSbSql.toString(), new EntityResult(
							SysActionAgeConfig.class));
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			throw new HDException("获取动作时效失败！", e);
		}
		return saConfig;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String queryReportFormUrl(SuperEntity workEntity) throws HDException {
		// TODO Auto-generated method stub
		if (workEntity.getAttribute("ud_zyxk_zysqid") == null)
			throw new HDException("获取作业申请信息的ud_zyxk_zysqid属性失败！");
		String id = workEntity.getAttribute("ud_zyxk_zysqid").toString();
		String zypclass = "";
		String url = null;
		try {
			StringBuilder urlSbSql = new StringBuilder();
			if (workEntity.getAttribute("zypclass") != null
					&& !StringUtils.isEmpty(workEntity.getAttribute("zypclass")
							.toString())) {
				zypclass = workEntity.getAttribute("zypclass").toString();
				urlSbSql.append("select (forcast.ip||lastdesc.name) as tableurl from ");
				urlSbSql.append(" (select ('http://'||sysurl||'/') as ip from hse_sys_config where syscode in ('inurl','outurl') and enable=1) as forcast ,");
				urlSbSql.append(" (select (description||'")
						.append(id)
						.append("') as name from alndomain aln where aln.value='")
						.append(zypclass).append("'");
				urlSbSql.append(" and domainid='UDRQURL') as lastdesc;");
			} else {
				urlSbSql.append("select (forcast.ip||lastdesc.name) as tableurl from ");
				urlSbSql.append(" (select ('http://'||sysurl||'/') as ip from hse_sys_config where syscode in ('inurl','outurl') and enable=1) as forcast ,");
				urlSbSql.append(" (select (description||zysq.ud_zyxk_zysqid) as name from alndomain aln inner join ud_zyxk_zysq zysq on aln.value=zysq.zypclass");
				urlSbSql.append(
						" where domainid='UDRQURL' and zysq.ud_zyxk_zysqid='")
						.append(workEntity.getAttribute("ud_zyxk_zysqid")
								.toString()).append("') as lastdesc;");
			}
			Map<String, Object> map = (Map) dao.executeQuery(
					urlSbSql.toString(), new MapResult());
			if (map == null || map.size() == 0)
				return null;
			url = map.get("tableurl") == null ? null : map.get("tableurl")
					.toString();
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			throw new HDException("访问服务失败！", e);
		}
		return url;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MultitermMeasureAffirm> queryItemByItemInfo(
			MultitermMeasureAffirm multitermMeasureAffirm,
			IQueryCallEventListener callEventListener) throws HDException {
		// TODO Auto-generated method stub
		if (callEventListener == null) {
			List<MultitermMeasureAffirm> items = null;
			StringBuilder sqlSbBuilder = new StringBuilder();
			sqlSbBuilder.append("select * from ud_onemeasure_person where ");
			sqlSbBuilder.append(" ud_zyxk_zysqid='")
					.append(multitermMeasureAffirm.getUd_zyxk_zysqid())
					.append("' ");
			sqlSbBuilder.append(" and (ud_type='")
					.append(multitermMeasureAffirm.getUd_type())
					.append("' or tablename='")
					.append(multitermMeasureAffirm.getTablename())
					.append("') ");
			sqlSbBuilder.append(" and tableid='")
					.append(multitermMeasureAffirm.getTableid()).append("' ");
			sqlSbBuilder.append(" and opid='")
					.append(multitermMeasureAffirm.getOpid()).append("'; ");
			try {
				items = (List<MultitermMeasureAffirm>) executeQuery(
						sqlSbBuilder.toString(), new EntityListResult(
								MultitermMeasureAffirm.class));
				sqlSbBuilder.setLength(0);
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				throw new HDException("查询逐条信息失败！", e);
			}
			return items;
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryItemByItemInfo",
					new Class<?>[] { MultitermMeasureAffirm.class,
							IQueryCallEventListener.class }, new Object[] {
							multitermMeasureAffirm, null });
		}
		return null;
	}

	@Override
	public List<PositionCard> queryVirtualCards(
			IQueryCallEventListener callEventListener) throws HDException {
		// TODO Auto-generated method stub
		if (callEventListener == null) {
			List<PositionCard> positionCardList = null;
			StringBuilder sqlSbBuilder = new StringBuilder();
			sqlSbBuilder.append("select * from ud_zyxk_wzk where isVirtualCard=1;");
			try {
				positionCardList = (List<PositionCard>) executeQuery(
						sqlSbBuilder.toString(), new EntityListResult(
								PositionCard.class));
				sqlSbBuilder.setLength(0);
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				throw new HDException("查询虚拟位置信息失败！", e);
			}
			return positionCardList;
		} else {
			// 异步加载
			AsyncQueryHandler asyncQueryHandler = new AsyncQueryHandler(
					callEventListener);
			asyncQueryHandler.execute(this, "queryVirtualCards",
					new Class<?>[] { IQueryCallEventListener.class },
					new Object[] { null });
		}
		return null;
	}

}
