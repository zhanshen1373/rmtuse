/**
 * Project Name:hse-business-service
 * File Name:BusinessActionNum.java
 * Package Name:com.hd.hse.constant
 * Date:2015年5月29日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.constant;

/**
 * ClassName:BusinessActionNum ().<br/>
 * Date: 2015年5月29日 <br/>
 * 
 * @author zhaofeng
 * @version
 * @see
 */
public class BusinessActionNum {

	/**
	 * QUERY_LOGINED_USER:TODO(查询已经登陆过的历史记录人-(离线和在线都要走的公共方法)).
	 */
	public final static String QUERY_HISTORY_LOGINED_USER = "QUERY_001";
	/**
	 * QUERY_WAITER_WORK:TODO(查询网络配置信息).
	 */
	public final static String QUERY_NETWORK_CONFIG = "QUERY_002";
	/**
	 * QUERY_NOTICE_MESSAGE:TODO(通知类消息查询).
	 */
	public final static String QUERY_NOTICE_MESSAGE = "QUERY_003";
	/**
	 * QUERY_WORKTASK_LIST:TODO(查询作业票列表信息).
	 */
	public final static String QUERY_WORKTASK_LIST = "QUERY_004";
	/**
	 * QUERY_WORKTASK_OPERATE_DATA:TODO(点击任务进入下一界面时，进行的查询).
	 */
	public final static String QUERY_WORKTASK_OPERATE_DATA = "QUERY_005";
	/**
	 * QUERY_WORKTASK_BROWSE_LIST:TODO(作业票浏览编码).
	 */
	public final static String QUERY_WORKTASK_BROWSE_LIST = "QUERY_006";
	/**
	 * QUERY_PUSH_SERVICE_ADDRESS:TODO(获取推送服务器地址).
	 */
	public final static String QUERY_PUSH_SERVICE_ADDRESS = "QUERY_007";
	/**
	 * QUERY_WORKTASK_PHOTO:TODO(照片管理中-查询照片信息).
	 */
	public final static String QUERY_WORKTASK_PHOTO = "QUERY_008";
	/**
	 * QUERY_REPORT_URL:TODO(获取报表URL).
	 */
	public final static String QUERY_REPORT_URL = "QUERY_009";

	/**
	 * QUERY_ITEMPROGRESS_INFO:TODO(获取分项 任务的进度信息).
	 */
	public final static String QUERY_ITEMPROGRESS_INFO = "QUERY_010";
	/**
	 * 
	 * @author liuyang
	 * @date 2017年1月13日
	 */
	public final static String QUERY_TIMELINE_DATA = "QUERY_016";

	/**
	 * CHECK_NETWORK_CONNECTION:TODO(检测网络连接情况).
	 */
	public final static String CHECK_NETWORK_CONNECTION = "CHECK_001";
	/**
	 * CHECK_QTJC_ISHE:TODO(后台气体检测是否合格校验).
	 */
	public final static String CHECK_QTJC_ISHE = "CHECK_002";
	/**
	 * ACTION_USER_LOGIN:TODO(用户登录动作). 包含：1.后台校验是否是有效用户 2.校验成功后，保存或者更新数据到本地
	 */
	public final static String ACTION_USER_LOGIN = "ACTION_001";
	/**
	 * ACTION_HARM_AUDIT:TODO(危害审核).
	 */
	public final static String ACTION_HARM_AUDIT = "ACTION_002";
	/**
	 * ACTION_MEASURE_AUDIT:TODO(措施审核).
	 */
	public final static String ACTION_MEASURE_AUDIT = "ACTION_003";
	/**
	 * ACTION_ACCEPT_WORK_AUDIT:TODO(接班确认).
	 */
	public final static String ACTION_ACCEPT_WORK_AUDIT = "ACTION_004";
	/**
	 * ACTION_WORK_END_AUDIT:TODO(作业结束).
	 */
	public final static String ACTION_WORK_END_AUDIT = "ACTION_005";
	/**
	 * ACTION_APPLY_DEVIATE_AUDIT:TODO(申请偏离操作).
	 */
	public final static String ACTION_APPLY_DEVIATE_AUDIT = "ACTION_006";
	/**
	 * ACTION_NEWS_DELTET:TODO(删除消息信息).
	 */
	public final static String ACTION_NEWS_DELTET = "ACTION_007";
	/**
	 * UPDATE_WORKTASK_PHOTO:TODO(照片上传).
	 */
	public final static String UPLOAD_WORKTASK_PHOTO = "UPLOAD_001";
	/**
	 * UPDATE_NETWORK_CONFIG:TODO(网络配置信息保存).
	 */
	public final static String UPDATE_NETWORK_CONFIG = "UPDATE_001";
	/**
	 * QUERY_QTJC:TODO(获取气体检测域和历史记录).
	 */
	public final static String QUERY_QTJC = "QUERY_011";
	/**
	 * UPLOAD_QTJC:TODO(上传新的气体检测结果).
	 */
	public final static String UPLOAD_QTJC = "UPLOAD_002";
	/**
	 * QUERY_RPTURL:TODO(获取报表URL).
	 */
	public final static String QUERY_RPTURL = "QUERY_012";
	/**
	 * UPLOAD_ELE:TODO(上传新的用电设备结果).
	 */
	public final static String UPLOAD_ELE = "UPLOAD_003";

}
