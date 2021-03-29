/**
 * Project Name:hse-entity-service
 * File Name:CheckApproveRight.java
 * Package Name:com.hd.hse.service.checkright
 * Date:2014年10月13日
 * Copyright (c) 2014, zhaofeng@ushayden.com All Rights Reserved.
 *
 */
package com.hd.hse.service.checkright;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.DaoException;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.constant.IRelativeEncoding;
import com.hd.hse.dao.BaseDao;
import com.hd.hse.dao.result.EntityResult;
import com.hd.hse.entity.base.RelationTableName;
import com.hd.hse.entity.common.PersonCard;
import com.hd.hse.entity.other.Persongroup;
import com.hd.hse.entity.workorder.PersonSpecialTypeWork;
import com.hd.hse.service.config.IQueryRelativeConfig;
import com.hd.hse.service.config.QueryRelativeConfig;

/**
 * ClassName: CheckApproveRight ()<br/>
 * date: 2014年10月13日 <br/>
 * 
 * @author zhaofeng
 * @version
 */
public class CheckApproveRight implements ICheckApproveRight {

	/**
	 * logger:TODO(日志).
	 */
	private final Logger logger = LogUtils.getLogger(CheckApproveRight.class);

	/**
	 * dao:TODO(dao层).
	 */
	BaseDao dao = null;
	/**
	 * sbSql:TODO(字符串连接类).
	 */
	StringBuilder sbSql = null;
	/**
	 * isContractUnit:TODO(是否区分承包商的单位).
	 */
	private boolean isContractUnit = true;
	/**
	 * relativeConfig:TODO(关系检查).
	 */
	public IQueryRelativeConfig relativeConfig = new QueryRelativeConfig();

	/**
	 * setContractnit:(设置是否区分承包商的单位). <br/>
	 * date: 2014年10月13日 <br/>
	 * 
	 * @author zhaofeng
	 * @param isContractnit
	 */
	public final void setContractUnit(boolean isContractUnit) {
		this.isContractUnit = isContractUnit;
	}

	/**
	 * TODO 实现检查刷卡人是否有审批权限
	 * 
	 * @throws HDException
	 * 
	 * @see com.hd.hse.service.checkright.ICheckApproveRight#checkApproveRight(com.hd.hse.common.entity.SuperEntity,
	 *      com.hd.hse.common.entity.SuperEntity,
	 *      com.hd.hse.common.entity.SuperEntity)
	 */
	@Override
	public void checkApproveRight(SuperEntity nodeEntity,
			SuperEntity personCard, SuperEntity workEntity) throws HDException {
		dao = new BaseDao();
		// TODO Auto-generated method stub
		// 根据人员卡号获取人员信息
		// 判断人员部门和作业票部门之间的关系
		// 根据环节点，人员，作业票判断走普通流程还是走承包商流程
		// 1.普通流程走人员组和人员组关系；2.承包商流程判断承包商的入厂时效和资质审核
		if (nodeEntity == null)
			throw new HDException("环节点信息为空,不能判断权限！");
		if (personCard == null)
			throw new HDException("人员信息为空,不能判断权限！");
		if (workEntity == null)
			throw new HDException("作业票信息为空,不能判断权限！");
		personCard = getPersonCard(personCard);// 获取人员信息
		if (isConstractFlow(nodeEntity, personCard, workEntity)) {
			// 走承包商流程
			checkContractFlow(nodeEntity, personCard, workEntity);
		} else {
			// 走非承包商流程
			checkNonContractFlow(nodeEntity, personCard, workEntity);
		}
		setNodeEntityCheckIn(nodeEntity, personCard);// 有权限，给环节点赋值
	}

	/**
	 * getPersonCard:(获取刷卡人员信息). <br/>
	 * date: 2014年10月13日 <br/>
	 * 
	 * @author zhaofeng
	 * @param personCard
	 * @return
	 * @throws HDException
	 */
	private SuperEntity getPersonCard(SuperEntity personCard)
			throws HDException {
		if (sbSql == null)
			sbSql = new StringBuilder();
		sbSql.setLength(0);
		// 获取人员信息
		PersonCard psnCard = null;
		if (personCard instanceof PersonCard)
			psnCard = (PersonCard) personCard;

		try {
			String cardNum = psnCard.getPcardnum();// 人员卡卡号
			sbSql.append("select * from ud_zyxk_ryk ");
			// 通过人员卡登录
			if (!StringUtils.isEmpty(cardNum)) {
				sbSql.append(" where pcardnum='").append(cardNum).append("';");
			} else {
				throw new HDException("不存在该卡号的人员！");
			}
			psnCard = (PersonCard) dao.executeQuery(sbSql.toString(),
					new EntityResult(PersonCard.class));
			if (psnCard == null)
				throw new HDException("无效刷卡用户！");
			if (psnCard.getIscan() == 0)
				throw new HDException("读卡人员权限已失效，请联系相关负责人！");
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
			throw new DaoException("判断审批权限失败！");
		}
		return psnCard;
	}

	/**
	 * isConstractFlow:(判断是否满足承包商流程). <br/>
	 * date: 2014年10月13日 <br/>
	 * 
	 * @author zhaofeng
	 * @param nodeEntity
	 * @param personCard
	 * @param workEntity
	 * @return
	 * @throws HDException
	 */
	private boolean isConstractFlow(SuperEntity nodeEntity,
			SuperEntity personCard, SuperEntity workEntity) throws HDException {
		// 判断是否承包商流程
		if (nodeEntity.getAttribute("iscbs") == null)
			throw new HDException("环节点信息的iscbs属性获取失败！");
		if (personCard.getAttribute("iscbs") == null)
			throw new HDException("人员信息的iscbs属性获取失败！");
		if (workEntity.getAttribute("iscbs") == null)
			throw new HDException("作业票信息的iscbs属性获取失败！");
		if ((Integer.parseInt(nodeEntity.getAttribute("iscbs").toString())) == 1
				&& (Integer.parseInt(personCard.getAttribute("iscbs")
						.toString())) == 1
				&& (Integer.parseInt(workEntity.getAttribute("iscbs")
						.toString())) == 1) {
			// 判断是否需要区别单位
			RelationTableName relationEntity = new RelationTableName();
			relationEntity.setSys_type(IRelativeEncoding.CBSSK);
			relationEntity.setSys_fuction(null);
			relationEntity.setSys_value(workEntity.getAttribute("zypclass")==null?"":workEntity.getAttribute("zypclass").toString());
			if (!relativeConfig.isHadRelative(relationEntity)) {
				// 需要区别单位
				if (personCard.getAttribute("department") == null)
					throw new HDException("人员信息的department属性获取失败！");
				if (workEntity.getAttribute("zydept") == null)
					throw new HDException("作业票信息的zydept属性获取失败！");
				if (personCard.getAttribute("department").toString()
						.equals(workEntity.getAttribute("zydept").toString()))
					return true;
				return false;
			}
			return true;// 不需要区别单位
		}
		return false;
	}

	/**
	 * checkContractFlow:(走承包商刷卡流程). <br/>
	 * date: 2014年10月14日 <br/>
	 * 
	 * @author zhaofeng
	 * @param nodeEntity
	 * @param personCard
	 * @param workEntity
	 * @return
	 * @throws HDException
	 */
	private void checkContractFlow(SuperEntity nodeEntity,
			SuperEntity personCard, SuperEntity workEntity) throws HDException {
		// 走承包商流程
		if (sbSql == null)
			sbSql = new StringBuilder();
		sbSql.setLength(0);
		try {
			// 入场时效检验
			if (personCard.getAttribute("endtime") == null)
				throw new HDException("此刷卡人未配置离厂日期！");
			if (!compareDate(personCard.getAttribute("endtime").toString(),
					null))
				throw new HDException(
						personCard.getAttribute("personid_desc") == null ? ""
								: personCard.getAttribute("personid_desc")
										.toString() + "入厂时间已经到期，不能审核！");
			// 资质证书检验ContractorAptitudeConfig
			if (nodeEntity.getAttribute("iscbszz") == null)
				throw new HDException("获取审批环节点的iscbszz属性失败！");
			if (nodeEntity.getAttribute("ud_zyxk_zyspqxid") == null)
				throw new HDException("获取审批环节点的ud_zyxk_zyspqxid属性失败！");
			if (personCard.getAttribute("personid") == null)
				throw new HDException("获取人员信息的personid属性失败！");
			if ((Integer) nodeEntity.getAttribute("iscbszz") == 1) {
				sbSql.append("select * from ud_cbsgl_rytzzy where certificatetype in (select sszz from ud_zyxk_cbszz where ud_zyxk_zyspqxid='");
				sbSql.append(nodeEntity.getAttribute("ud_zyxk_zyspqxid")
						.toString());
				sbSql.append("') and idcard='")
						.append(personCard.getAttribute("personid").toString())
						.append("';");
				PersonSpecialTypeWork pstw = (PersonSpecialTypeWork) dao
						.executeQuery(sbSql.toString(), new EntityResult(
								PersonSpecialTypeWork.class));
				if (pstw == null)
					throw new HDException("环节点关联证书失败，请联系管理员！");
				if (!compareDate(pstw.getDqdate(), null))
					throw new HDException(pstw.getCertificatetype()
							+ "证书已经到期，不能审核！");
			}
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			throw new DaoException("查询承包商人员资质失败，请联系管理员！");
		}
	}

	/**
	 * checkNonContractFlow:(走非承包商刷卡流程). <br/>
	 * date: 2014年10月14日 <br/>
	 * 
	 * @author zhaofeng
	 * @param nodeEntity
	 * @param personCard
	 * @param workEntity
	 * @return
	 * @throws HDException
	 */
	private void checkNonContractFlow(SuperEntity nodeEntity,
			SuperEntity personCard, SuperEntity workEntity) throws HDException {
		// 走非承包商流程
		boolean flag = false;
		String authorizer = getAuthorizer(nodeEntity, personCard);
		if (checkPersonWork(personCard, workEntity)
				|| !StringUtils.isEmpty(authorizer)) {
			// 走私有组,不满足再走公共组
			if (checkPrivateGroup(nodeEntity, personCard, authorizer))
				flag = true;
		}
		if (!flag) {
			// 直接走公共组
			checkPublicGroup(nodeEntity, personCard,workEntity, authorizer);
		}
	}

	/**
	 * checkPublicGroup:(判断公共组). <br/>
	 * date: 2014年10月14日 <br/>
	 * 
	 * @author zhaofeng
	 * @param nodeEntity
	 * @param personCard
	 * @param authorizer
	 * @return
	 * @throws HDException
	 */
	private void checkPublicGroup(SuperEntity nodeEntity,
			SuperEntity personCard, SuperEntity workEntity, String authorizer) throws HDException {
		if (sbSql == null)
			sbSql = new StringBuilder();
		sbSql.setLength(0);
		try {
			if (personCard.getAttribute("personid") == null)
				throw new HDException("该刷卡人员的编码信息配置错误，请联系管理员！");
			if (nodeEntity.getAttribute("qxrole") == null)
				throw new HDException("该环节点没有配置人员组信息，请联系管理员！");
//			if (personCard.getAttribute("department") == null)
//				throw new HDException("该刷卡人员的部门信息配置错误，请联系管理员！");
			if (workEntity.getAttribute("sddept") == null)
				throw new HDException("作业票的属地部门信息没有配置,请联系管理员！");
			sbSql.append("select PERSONGROUP from PERSONGROUP,UD_SY_DEPT as dept where PERSONGROUP in (");
			sbSql.append(
					"select PERSONGROUP from persongroupteam where RESPPARTYGROUP in ('")
					.append(personCard.getAttribute("personid").toString())
					.append("','").append(authorizer).append("'))");
			sbSql.append(" and  (',' || SSDEPT ||',' like '%,' || dept.DEPTNUM ||',%' and (");
			sbSql.append(
					"  select VREPORTDEPTNUM from UD_SY_DEPT where deptnum='")
					.append(workEntity.getAttribute("sddept").toString())
					.append("') ");
			sbSql.append("  like dept.VREPORTDEPTNUM || '%') and  ',' || '")
					.append(nodeEntity.getAttribute("qxrole").toString())
					.append("' || ',' like '%,'|| PERSONGROUP ||',%';");
			Object object = dao.executeQuery(sbSql.toString(),
					new EntityResult(Persongroup.class));
			if (object == null)
				throw new HDException("该刷卡人未配置刷卡权限！");
		} catch (DaoException e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			throw new HDException("查询共有组组信息失败，请联系管理员！", e);
		}
	}

	/**
	 * checkPrivateGroup:(判断私有组). <br/>
	 * date: 2014年10月14日 <br/>
	 * 
	 * @author zhaofeng
	 * @param nodeEntity
	 * @param personCard
	 * @param authorizer
	 * @return
	 * @throws HDException
	 */
	private boolean checkPrivateGroup(SuperEntity nodeEntity,
			SuperEntity personCard, String authorizer) throws HDException {
		if (sbSql == null)
			sbSql = new StringBuilder();
		sbSql.setLength(0);
		try {
			if (personCard.getAttribute("personid") == null)
				throw new HDException("该刷卡人员的编码信息配置错误，请联系管理员！");
			if (nodeEntity.getAttribute("qxrole") == null)
				throw new HDException("该环节点没有配置人员组信息，请联系管理员！");
			sbSql.append("select PERSONGROUP from PERSONGROUP where PERSONGROUP in (select PERSONGROUP from persongroupteam ");
			sbSql.append(" where RESPPARTYGROUP in ('")
					.append(personCard.getAttribute("personid").toString())
					.append("','").append(authorizer).append("'))");
			sbSql.append(" and ifnull(SSDEPT,'')='' and ',' || '")
					.append(nodeEntity.getAttribute("qxrole").toString())
					.append("' || ',' like '%,'|| PERSONGROUP ||',%';");
			Object object = dao.executeQuery(sbSql.toString(),
					new EntityResult(Persongroup.class));
			if (object != null)
				return true;
			return false;
		} catch (DaoException e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			throw new HDException("查询私有组信息失败，请联系管理员！", e);
		}
	}

	/**
	 * checkPersonWork:(判断人员与作业票的部门关系). <br/>
	 * date: 2014年10月13日 <br/>
	 * 
	 * @author zhaofeng
	 * @param personCard
	 * @param workEntity
	 * @return
	 * @throws HDException
	 */
	private boolean checkPersonWork(SuperEntity personCard,
			SuperEntity workEntity) throws HDException {
		// 检查人员和作业票的部门是否有关系-相同或者上下级关系
		if (sbSql == null)
			sbSql = new StringBuilder();
		sbSql.setLength(0);
		try {
			if (workEntity.getAttribute("sddept") == null)
				throw new HDException("作业票的属地部门信息没有配置,请联系管理员！");
			if (personCard.getAttribute("department") == null)
				throw new HDException("该刷卡人员的部门信息配置错误，请联系管理员！");
			sbSql.append("select dept.vreportdeptnum from ud_sy_dept dept left join ");
			sbSql.append(
					"(select vreportdeptnum from ud_sy_dept  where deptnum='")
					.append(workEntity.getAttribute("sddept").toString())
					.append("') as zypdept on ");
			sbSql.append(" dept.vreportdeptnum like '%zypdept.vreportdeptnum%' ");
			sbSql.append(" where dept.deptnum='")
					.append(personCard.getAttribute("department").toString())
					.append("';");
			SuperEntity entity = (SuperEntity) dao.executeQuery(
					sbSql.toString(), new EntityResult(PersonCard.class));
			if (entity != null)
				return true;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
			throw new HDException("查询人员部门和作业票的部门失败,请联系管理员！");
		}
		return false;
	}

	/**
	 * getAuthorizer:(获取被授权人信息). <br/>
	 * date: 2014年10月14日 <br/>
	 * 
	 * @author zhaofeng
	 * @param nodeEntity
	 * @param personCard
	 * @return
	 * @throws HDException
	 */
	private String getAuthorizer(SuperEntity nodeEntity, SuperEntity personCard)
			throws HDException {
		if (nodeEntity.getAttribute("qxperson") == null)
			return null;
		if (personCard.getAttribute("personid") == null)
			return null;
		String qxpersons = nodeEntity.getAttribute("qxperson").toString();
		String personid = personCard.getAttribute("personid").toString();
		if (!StringUtils.isEmpty(qxpersons)) {
			// 存在授权环节，进行相应的处理
			String[] personArray = qxpersons.split(";");
			for (int i = 0; i < personArray.length; i++) {
				if (personArray[i].contains(personid)) {
					String authorizer = personArray[i].split("#")[0];
					if (!personid.equals(authorizer)) {
						return authorizer;
					}
				}
			}
		}
		return null;
	}

	/**
	 * setNodeEntityCheckIn:(给审批信息赋值). <br/>
	 * date: 2014年10月22日 <br/>
	 * 
	 * @author zhaofeng
	 * @param nodeEntity
	 * @param personCard
	 */
	private void setNodeEntityCheckIn(SuperEntity nodeEntity,
			SuperEntity personCard) {
		String personid = "";
		String persondescString = "";
		if (nodeEntity.getAttribute("defaultpersonid") != null
				&& !StringUtils.isEmpty(nodeEntity.getAttribute(
						"defaultpersonid").toString())) {
			personid = nodeEntity.getAttribute("defaultpersonid").toString();
		}
		if (nodeEntity.getAttribute("defaultpersondesc") != null
				&& !StringUtils.isEmpty(nodeEntity.getAttribute(
						"defaultpersondesc").toString())) {
			persondescString = nodeEntity.getAttribute("defaultpersondesc")
					.toString();
		}
		if (!StringUtils.isEmpty(personid)) {
			if (personCard.getAttribute("personid") != null
					&& !StringUtils.isEmpty(personCard.getAttribute("personid")
							.toString())) {
				personid += ("," + personCard.getAttribute("personid")
						.toString());
			}
		} else {
			if (personCard.getAttribute("personid") != null
					&& !StringUtils.isEmpty(personCard.getAttribute("personid")
							.toString())) {
				personid += (personCard.getAttribute("personid").toString());
			}
		}

		if (!StringUtils.isEmpty(persondescString)) {
			if (personCard.getAttribute("personid_desc") != null
					&& !StringUtils.isEmpty(personCard.getAttribute(
							"personid_desc").toString())) {
				persondescString += ("," + personCard.getAttribute(
						"personid_desc").toString());
			}
		} else {
			if (personCard.getAttribute("personid_desc") != null
					&& !StringUtils.isEmpty(personCard.getAttribute(
							"personid_desc").toString())) {
				persondescString += (personCard.getAttribute("personid_desc")
						.toString());
			}
		}
		nodeEntity.setAttribute("personid", personid);
		nodeEntity.setAttribute("persondesc", persondescString);
		nodeEntity.setAttribute("departmentdesc",
				personCard.getAttribute("department_desc"));
	}

	/**
	 * compareDate:(比较时间大小). <br/>
	 * date: 2014年10月13日 <br/>
	 * 
	 * @author zhaofeng
	 * @param date1
	 * @param date2
	 * @return
	 * @throws HDException
	 * 人员资质，承包商人员
	 */
	private boolean compareDate(String date1, String date2) throws HDException {
		DateFormat df = null;
		if(date1.length() == 19){
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else{
			df = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			if (date2 == null) {
				if (df.parse(date1).getTime() >= new Date().getTime())
					return true;
			} else {
				if (df.parse(date1).getTime() >= df.parse(date2).getTime())
					return true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new HDException("时间格式不正确！");
		}
		return false;
	}

}
