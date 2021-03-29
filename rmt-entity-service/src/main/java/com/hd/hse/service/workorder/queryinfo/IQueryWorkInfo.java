/**
 * Project Name:hse-entity-service
 * File Name:IQueryWorkInfo.java
 * Package Name:com.hd.hse.service.workorder.queryinfo
 * Date:2014年10月16日
 * Copyright (c) 2014, zhaofeng@ushayden.com All Rights Reserved.
 *
 */
package com.hd.hse.service.workorder.queryinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.entity.base.GasDetection;
import com.hd.hse.entity.base.HazardNotify;
import com.hd.hse.entity.base.MultitermMeasureAffirm;
import com.hd.hse.entity.base.PDAWorkOrderInfoConfig;
import com.hd.hse.entity.common.Image;
import com.hd.hse.entity.common.PositionCard;
import com.hd.hse.entity.common.SysActionAgeConfig;
import com.hd.hse.entity.workorder.WorkApplyMeasure;
import com.hd.hse.entity.workorder.WorkApprovalPermission;
import com.hd.hse.entity.workorder.WorkDelay;
import com.hd.hse.entity.workorder.WorkGuardEquipment;
import com.hd.hse.entity.workorder.WorkMeasureReview;
import com.hd.hse.entity.workorder.WorkOrder;

/**
 * ClassName: IQueryWorkInfo ()<br/>
 * date: 2014年10月16日 <br/>
 * 
 * @author zhaofeng
 * @version
 */
public interface IQueryWorkInfo extends IQuery{

	/**
	 * 接口说明： 方法中的异步回调函数如果设置为null，则走本地查询； 要走异步需要实现异步回调函数；
	 * 
	 */

	/**
	 * queryWorkInfo:(作业票信息). <br/>
	 * date: 2014年10月16日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param callEventListener
	 *            异步回调函数
	 * @return
	 * @throws HDException
	 */
	public WorkOrder queryWorkInfo(SuperEntity workEntity,
                                   IQueryCallEventListener callEventListener) throws HDException;

	/**
	 * queryWorkInfoConfigInfo:(). <br/>
	 * date: 2014年10月17日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param functionType
	 *            功能点（审批，复查，延期，关闭/取消）
	 * @param callEventListener
	 *            异步回调函数
	 * @return
	 * @throws HDException
	 */
	public List<PDAWorkOrderInfoConfig> queryWorkInfoConfigInfo(
            SuperEntity workEntity, String functionType,
            IQueryCallEventListener callEventListener) throws HDException;

	/**
	 * queryWorkInfoConfigInfo:(). <br/>
	 * date: 2014年11月4日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param functionType
	 *            具体功能
	 * @param contye
	 *            具体类别的
	 * @param callEventListener
	 * @return
	 * @throws HDException
	 */
	public List<PDAWorkOrderInfoConfig> queryWorkInfoConfigInfo(
            SuperEntity workEntity, String functionType, Integer contye,
            IQueryCallEventListener callEventListener) throws HDException;

	/**
	 * queryHarmInfo:(危害信息). <br/>
	 * date: 2014年10月16日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param callEventListener
	 *            异步回调函数
	 * @return
	 * @throws HDException
	 */
	public List<HazardNotify> queryHarmInfo(SuperEntity workEntity,
                                            IQueryCallEventListener callEventListener) throws HDException;

	/**
	 * queryMeasureInfo:(措施信息). <br/>
	 * date: 2014年10月16日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param workConfigEntity
	 *            tab配置信息
	 * @param callEventListener
	 *            异步回调函数
	 * @return
	 * @throws HDException
	 */
	public List<WorkApplyMeasure> queryMeasureInfo(SuperEntity workEntity,
                                                   SuperEntity workConfigEntity,
                                                   IQueryCallEventListener callEventListener) throws HDException;

	/**
	 * queryPpeInfo:(个人防护装备). <br/>
	 * date: 2014年10月16日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param callEventListener
	 *            异步回调函数
	 * @return
	 * @throws HDException
	 */
	public List<WorkGuardEquipment> queryPpeInfo(SuperEntity workEntity,
                                                 IQueryCallEventListener callEventListener) throws HDException;

	/**
	 * queryGasInfo:(气体检测). <br/>
	 * 新增气体查询 date: 2014年10月16日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param callEventListener
	 *            异步回调函数
	 * @return
	 * @throws HDException
	 */
	public GasDetection queryGasInfo(SuperEntity workEntity,
                                     IQueryCallEventListener callEventListener) throws HDException;

	/**
	 * queryHistoryGasInfo:(获取该作业票下的所有气体检测记录). <br/>
	 * 历史数据查询 date: 2014年10月20日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param callEventListener
	 * @return
	 * @throws HDException
	 */
	public ArrayList<Map<String, String>> queryHistoryGasInfo(
            SuperEntity workEntity, IQueryCallEventListener callEventListener)
			throws HDException;

	/**
	 * queryUndoneGasInfo:(获取最近一次未完成的气体检测记录). <br/>
	 * date: 2014年10月23日 <br/>
	 * 判断是否有未操作完成，如果没有 返回 null
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param callEventListener
	 * @return
	 * @throws HDException
	 */
	public GasDetection queryUndoneGasInfo(SuperEntity workEntity,
                                           IQueryCallEventListener callEventListener) throws HDException;

	/**
	 * queryReviewInfo:(措施复查). <br/>
	 * date: 2014年10月16日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param workConfigEntity
	 *            tab配置信息
	 * @param callEventListener
	 *            异步回调函数
	 * @return
	 * @throws HDException
	 */
	public WorkMeasureReview queryReviewInfo(SuperEntity workEntity,
                                             SuperEntity workConfigEntity,
                                             IQueryCallEventListener callEventListener) throws HDException;

	/**
	 * queryDelayInfo:(延期信息). <br/>
	 * date: 2014年10月16日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param zyscNum
	 *            复查编号
	 * @param callEventListener
	 *            异步回调函数
	 * @return 返回null时。作业延期结束时间取作业票的实际结束时间
	 * @throws HDException
	 */
	public WorkDelay queryDelayInfo(SuperEntity workEntity, String zyscNum,
                                    IQueryCallEventListener callEventListener) throws HDException;

	/**
	 * queryApprovalPermission:(审批环节点查询). <br/>
	 * date: 2014年10月17日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param workConfigEntity
	 *            tab配置信息
	 * @param personRecord
	 *            人员记录信息 -针对复查，延期和气体检测这样的重复操作
	 * @param callEventListener
	 *            异步回调函数
	 * @return
	 * @throws HDException
	 */
	public List<WorkApprovalPermission> queryApprovalPermission(
            SuperEntity workEntity, SuperEntity workConfigEntity,
            SuperEntity record,
            IQueryCallEventListener callEventListener) throws HDException;
	/**
	 * querySiteAuditBasicInfo:(查询作业现场核查基础信息《组合信息》). <br/>
	 * date: 2014年10月20日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 * @param functionType
	 *            功能点（审批，复查，延期，关闭/取消）
	 * @param callEventListener
	 * @return
	 * @throws HDException
	 */
	public WorkOrder querySiteAuditBasicInfo(SuperEntity workEntity,
                                             String functionType, IQueryCallEventListener callEventListener)
			throws HDException;

	/**
	 * queryPhoto:(根据来源数据、功能模块查询照片). <br/>
	 * date: 2014年10月23日 <br/>
	 * 
	 * @author lg
	 * @param srcEntity
	 * @param configEntity
	 * @return
	 * @throws HDException
	 */
	public List<Image> queryPhoto(SuperEntity srcEntity,
                                  SuperEntity configEntity) throws HDException;

	/**
	 * querySysActionAgeConfig:(获取动作时效配置信息). <br/>
	 * date: 2014年11月5日 <br/>
	 * 
	 * @author zhaofeng
	 * @param action
	 * @return
	 * @throws HDException
	 */
	public SysActionAgeConfig querySysActionAgeConfig(String action)
			throws HDException;

	/**
	 * queryReportFormUrl:(获取浏览报表的url). <br/>
	 * date: 2014年11月6日 <br/>
	 * 
	 * @author zhaofeng
	 * @param workEntity
	 *            作业许可实体
	 * @return
	 * @throws HDException
	 */
	public String queryReportFormUrl(SuperEntity workEntity) throws HDException;
	/**
	 * queryItemByItemInfo:(获取逐条记录表的信息). <br/>
	 * date: 2014年12月5日 <br/>
	 *
	 * @author zhaofeng
	 * @param multitermMeasureAffirm
	 * @return
	 * @throws HDException
	 */
	public List<MultitermMeasureAffirm> queryItemByItemInfo(MultitermMeasureAffirm multitermMeasureAffirm, IQueryCallEventListener callEventListener) throws HDException;
	/**
	 * queryVirtualCards:(获取虚拟位置集合). <br/>
	 * date: 2015年3月27日 <br/>
	 *
	 * @author zhaofeng
	 * @param callEventListener
	 * @return
	 * @throws HDException
	 */
	public List<PositionCard> queryVirtualCards(IQueryCallEventListener callEventListener) throws HDException;
}
