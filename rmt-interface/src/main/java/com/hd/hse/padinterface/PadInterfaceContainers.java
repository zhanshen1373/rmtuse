package com.hd.hse.padinterface;

public class PadInterfaceContainers {


	/**
	 * METHOD_SUCCESS:TODO(执行结果 T).
	 */
	public final static String METHOD_SUCCESS = "T";
	/**
	 * METHOD_FAILD:TODO(执行结果 F).
	 */
	public final static String METHOD_FAILD = "F";
	
	/**
	 * METHOD_BUSINESSFAILD:TODO(业务判断不能处理是).
	 */
	public final static String METHOD_BUSINESSFAILD="BF";
	/**
	 * V_ACTION_INSERT:TODO(数据处理动作 insert).
	 */
	public final static String V_ACTION_INSERT="INSERT";
	/**
	 * V_ACTION_UPDATE:TODO(数据处理动作 update).
	 */
	public final static String V_ACTION_UPDATE="UPDATE";
	
	/**
	 *	 接口动作，PC端系统实现对应业务			
	 */

	/**
	 * METHOD_CBS_BASICDOWN:TODO(下载：承包商基础数据).
	 */
	public final static String METHOD_CBS_BASICDOWN = "CBS_001";
	/**
	 * METHOD_CBS_PERSIONDOWN:TODO(下载：承包商人员及特种作业证).
	 */
	public final static String METHOD_CBS_PERSIONDOWN = "CBS_002";
	/**
	 * METHOD_CBS_CARDOWN:TODO(下载：承包商入厂车辆及驾驶员).
	 */
	public final static String METHOD_CBS_CARDOWN = "CBS_003";

	/**
	 * METHOD_CBS_WORKLOGUP:TODO(上传：承包商工作日志).
	 */
	public final static String METHOD_CBS_WORKLOGUP = "CBS_004";
	
	/******************** 下载：承包商人员图片查看******************** */
	/**
	 * METHOD_CBS_CBSHEADIAMGEURL:TODO(获取承包商人员头像照片路径).
	 */
	public final static String METHOD_CBS_CBSHEADIAMGEURL = "CBS_005";
	
	/**
	 * METHOD_CBS_CBSINANDOUTFACTORYRECORDS:TODO(承包商人员出入厂记录).
	 */
	public final static String METHOD_CBS_CBSINANDOUTFACTORYRECORDS = "CBS_006";
	
	/**
	 * METHOD_CBS_CBSINANDOUTFACTORYRECORDS:TODO(承包商人员违章记录).
	 */
	public final static String METHOD_CBS_CBSRULESRECORDS = "CBS_007";
	
	/**
	 * METHOD_COMMON_DOWNTABLE:TODO(通用：下载表信息).
	 */
	public final static String METHOD_COMMON_DOWNTABLE = "COMMON_001";
	/**
	 * METHOD_COMMON_DOWNFILE:TODO(通用：下载文件).
	 */
	public final static String METHOD_COMMON_DOWNFILE = "COMMON_002";
	/**
	 * METHOD_COMMON_UPFILE:TODO(通用：上传文件).
	 */
	public final static String METHOD_COMMON_UPFILE = "COMMON_003";
	/**
	 * METHOD_COMMON_UPTABLE:TODO(通用：上传表信息).
	 */
	public final static String METHOD_COMMON_UPTABLE = "COMMON_004";
	
	
	/******************** 通用：获得文件大小*********************/
	public final static String METHOD_COMMON_101 = "COMMON_101";
	/**
	 * METHOD_COMMON_CHECKUSER:TODO(通用：登陆验证).
	 */
	public final static String METHOD_COMMON_CHECKUSER = "COMMON_102";
	/**
	 * METHOD_COMMON_VERSIONIFNO:TODO(通用：获取版本信息).
	 */
	public final static String METHOD_COMMON_VERSIONIFNO = "COMMON_103";
	
	/**
	 * METHOD_COMMON_TEST:TODO(校验网络是否连接成功).
	 */
	public final static String METHOD_COMMON_TEST = "COMMON_104";
	
	/**
	 * METHOD_COMMON_TEST:TODO(特殊基础数据更新，目前只更新url).
	 */
	public final static String METHOD_COMMON_SPECIALUPDATE = "COMMON_105";
	
	/**
	 * METHOD_COMMON_TEST:TODO( 获取票下图片路径url).
	 */
	public final static String METHOD_COMMON_GETIMAGEPATH = "COMMON_106";
	
	/**
	 * METHOD_COMMON_GETTASKIMAGEPATH:TODO(获取任务下 图片路径).
	 */
	public final static String METHOD_COMMON_GETTASKIMAGEPATH="COMMON_107";
	
	/**
	 * METHOD_COMMON_GETNEWSSERVERURL:TODO(获取消息服务的URL).
	 */
	public final static String METHOD_COMMON_GETNEWSSERVERURL="COMMON_108";
	/**
	 * METHOD_COMMON_GETNEWSSERVERURL:TODO(获取报表的URL).
	 */
	public final static String METHOD_COMMON_GETREPORTURL="COMMON_109";
	
	/******************** 下载：作业票列表*********************/
	public final static String METHOD_ZYP_LIST = "ZYP_001";//作业票列表
	/******************** 下载：作业票信息*********************/
	public final static String METHOD_ZYP_DOWN = "ZYP_002";//作业票信息
	/******************** 下载：返回下载成功的信息*********************/
	public final static String METHOD_ZYP_DOWNRETURN = "ZYP_003";//返回下载成功的信息
	/******************** 下载：获得作业票的状态值*********************/
	public final static String METHOD_ZYP_GETSTATUS = "ZYP_004";//获得作业票的状态值
	/******************** 下载：获得作业票的状态值*********************/
	public final static String METHOD_ZYP_GETTZPSTATUS = "ZYP_005";//获得作业票的状态值
	/******************** 下载：获得特种作业票的状态值*********************/
	public final static String METHOD_ZYP_GETLSYDSTATUS = "ZYP_006";//获得临时用电票的状态值
	/******************** 下载：按作业票id获得票的实际结束时间*********************/
	public final static String METHOD_ZYP_GETSJENDTIME = "ZYP_007";//获得动火票
	
	/**
	 * METHOD_ZYP_DOWNQTJC:TODO(同步气体检测).
	 */
	public final static String METHOD_ZYP_DOWNQTJC="ZYP_008";//同步气体检测
	
	/**
	 * METHOD_ZYP_GETLISTP:TODO(获取APPAUDITED 状态的作业票).
	 */
	public final static String METHOD_ZYP_GETLISTSTATUS="ZYP_009";//获取APPAUDITED 状态的作业票
	
	/**
	 * METHOD_ZYP_ONLINECONFIRM:TODO(最终审核人在线确认).
	 */
	public final static String METHOD_ZYP_ONLINECONFIRM="ZYP_010";
	
	/**
	 * METHOD_ZYP_ONLINENULLIFY:TODO(最终审核人在线作废).
	 */
	public final static String METHOD_ZYP_ONLINENULLIFY="ZYP_011";

	
	
	/********************在线：作业票列表 *********************/
	/**
	 * METHOD_ZYPONLINE_LIST:TODO(在线：作业票列表).
	 */
	public final static String METHOD_ZYPONLINE_LIST = "ZYPONLINE_001";//作业票列表
	/**
	 * METHOD_ZYPONLINE_OPPAGE:TODO(在线：操作界面详细信息).
	 */
	public final static String METHOD_ZYPONLINE_OPPAGE = "ZYPONLINE_002";//操作界面信息
	/**
	 * METHOD_ZYPONLINE_CLICKSURE:TODO(在线：点击确定).
	 */
	public final static String METHOD_ZYPONLINE_CLICKSURE="ZYPONLINE_003";
	/**
	 * METHOD_ZYPONLINE_CLICKSURE:TODO(在线：作业票浏览).
	 */
	public final static String METHOD_ZYPONLINE_ZYPBROWSE="ZYPONLINE_004";
	
	/**
	 * METHOD_ZYPONLINE_CLICKSURE:TODO(在线：接班).
	 */
	public final static String METHOD_ZYPONLINE_ZYPTRANFER="ZYPONLINE_005";
	/**
	 * METHOD_ZYPONLINE_CLICKSURE:TODO(在线：申请作业票偏离).
	 */
	public final static String METHOD_ZYPONLINE_ZYPAPPLYDEVIATE="ZYPONLINE_006";
	/**
	 * METHOD_ZYPONLINE_CLICKSURE:TODO(在线：验证措施的气体检测).
	 */
	public final static String METHOD_ZYPONLINE_ZYPCHECKGAS="ZYPONLINE_007";
	/**
	 * METHOD_ZYPONLINE_CLICKSURE:TODO(在线：获取消息列表消息).
	 */
	public final static String METHOD_ZYPONLINE_NEWSLIST="ZYPONLINE_008";
	
	/**
	 * METHOD_ZYPONLINE_DELNEWS:TODO(在线：删除消息信息).
	 */
	public final static String METHOD_ZYPONLINE_DELNEWS="ZYPONLINE_009";
	
	/**
	 * METHOD_ZYPONLINE_ITEMPROGRESS:TODO(在线:分项任务的进度).
	 */
	public final static String METHOD_ZYPONLINE_ITEMPROGRESS="ZYPONLINE_010";
	
	/**
	 * METHOD_ZYOONLINE_QTJC_GET:TODO(在线:获取气体检测的域和历史记录).
	 */
	public final static String METHOD_ZYPONLINE_QTJC_GET = "ZYPONLINE_011";
	
	/**
	 * METHOD_ZYPONLINE_QTJC_POST:TODO(在线:提交气体检测信息).
	 */
	public final static String METHOD_ZYPONLINE_QTJC_POST = "ZYPONLINE_012";
	/**
	 * METHOD_ZYPONLINE_REPORTFORMURL_GET(在线：获取报表URL).
	 */
	public final static String METHOD_ZYPONLINE_REPORTFORMURL_GET = "ZYPONLINE_013";
	/**
	 * METHOD_ZYPONLINE_TEMPELE_SAVE(在线：提交临时用电设备清单列表).
	 * @author liuyang
	 * @date 2016年7月7日
	 */
	public final static String METHOD_ZYPONLINE_TEMPELE_SAVE = "ZYPONLINE_015";
	/**
	 * 获取时间轴数据
	 */
	public final static String METHOD_ZYPONLINE_TIMELINEDATA = "ZYPONLINE_016";
}
