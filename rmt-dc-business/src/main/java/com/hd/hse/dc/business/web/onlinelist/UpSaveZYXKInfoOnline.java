package com.hd.hse.dc.business.web.onlinelist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.result.GsonUtil;
import com.hd.hse.dc.business.common.weblistener.AbsWebListener;
import com.hd.hse.entity.base.PDAWorkOrderInfoConfig;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;
import com.hd.hse.padinterface.PadInterfaceResponse;
import com.hd.hse.system.SystemProperty;
import com.hd.hse.utils.UtilToolsMsg;

/**
 * ClassName: UpSaveZYXKInfoOnline (审核、保存时调用的类)<br/>
 * date: 2015年6月1日 <br/>
 * 
 * @author lxf
 * @version
 */
public class UpSaveZYXKInfoOnline extends AbsWebListener {

	private static Logger logger = LogUtils
			.getLogger(UpSaveZYXKInfoOnline.class);
	private HashMap<String, Object> hashmap = new HashMap<String, Object>();
	private String moduleCode = null;

	private boolean isCS = false; // 是否是措施上传
	private String zysqid;
	private String zypClass;

	@Override
	public Object action(String action, Object... args) throws HDException {
		// TODO Auto-generated method stub
		super.action(action, args);
		// 开始上传数据
		try {
			startAction(args);
		} catch (HDException e) {
			logger.error(e);
			throw new HDException(
					e.getMessage().contains("网路") ? e.getMessage()
							: e.getMessage());
		}
		return "";
	}

	/**
	 * startAction:(开始处理动作). <br/>
	 * date: 2015年7月10日 <br/>
	 * 
	 * @author lxf
	 * @param args
	 * @return
	 * @throws HDException
	 */
	public Object startAction(Object... args) throws HDException {
		if (!beforUpDataInfo(args)) {
			// 跑出异常
			throw new HDException("上传验证不通过.");
		}
		Object param = gainDealObject(args);
		if (param == null) {
			return "";
		}
		// 获取上传数据。
		Object upObj = gainUpInfo(param);
		// 格式化上传数据的格式
		String upStr = upDateInfoFormat(param, upObj);
		Object retob = null;
		// 开始上传数据
		try {
			retob = startUpdateInfo(upStr);
			// 上传成功后执行的代码
			afterUpdateInfo(retob, args);
		} catch (HDException e) {
			// 上传数据报错，执行的代码
			errorUpdateInfo(args);
			logger.error(e);
			throw new HDException(
					e.getMessage().contains("网路") ? e.getMessage()
							: e.getMessage());
		}
		dealReturnMsg(retob);
		return "";
	}

	/**
	 * gainDealObject:(获取处理对象). <br/>
	 * date: 2015年7月10日 <br/>
	 * 默认值是获取第二个
	 * 
	 * @author lxf
	 * @param args
	 * @return
	 */
	public Object gainDealObject(Object... args) {
		if (args != null) {
			if (args.length > 1) {
				return args[1];
			} else {
				return args[0];
			}
		}
		return null;

	}

	/**
	 * dealReturnMsg:(处理返回消息). <br/>
	 * date: 2015年6月2日 <br/>
	 * 
	 * @author lxf
	 * @param obj
	 * @throws HDException
	 */
	public void dealReturnMsg(Object pcdata) throws HDException {
		PadInterfaceResponse response = null;
		if (pcdata instanceof PadInterfaceResponse) {
			response = (PadInterfaceResponse) pcdata;
			if (response.getFlag().equalsIgnoreCase(
					PadInterfaceContainers.METHOD_BUSINESSFAILD)) {
				String tempmsg = UtilToolsMsg.resolveMsg(response
						.getExceptionmsg());
				setWritelog("上传失败：" + tempmsg);
				if (tempmsg.equalsIgnoreCase("0")) {
					return;
				}
				throw new HDException(tempmsg);
			}
		}
		if (null != response
				&& (response.getFailedList().size() > 0 || response
						.getExceptionmsg() != "")) {
			StringBuilder msg = new StringBuilder();
			if (response.getFailedList().size() == 0) {
				if (response.getFlag().equalsIgnoreCase(
						PadInterfaceContainers.METHOD_BUSINESSFAILD)) {
					msg.append(
							UtilToolsMsg.resolveMsg(response.getExceptionmsg()))
							.append(";");
				} else {
					if (response.getFlag().equalsIgnoreCase(
							PadInterfaceContainers.METHOD_FAILD)) {
						msg.append(response.getExceptionmsg());
					}
				}
				setWritelog(msg.toString());
				throw new HDException("上传失败:" + msg);
			} else {
				for (Map<String, String> listMap : response.getFailedList()) {
					if (listMap
							.containsKey(PadInterfaceRequest.KEYRETURNMESSAGE)) {

						if (response.getFlag().equalsIgnoreCase(
								PadInterfaceContainers.METHOD_BUSINESSFAILD)) {
							msg.append(
									UtilToolsMsg
											.resolveMsg(listMap
													.get(PadInterfaceRequest.KEYRETURNMESSAGE)
													.toString())).append(";");
						} else {
							msg.append(
									listMap.get(
											PadInterfaceRequest.KEYRETURNMESSAGE)
											.toString()).append(";");
						}
					}
				}
				setWritelog(msg.toString());
				if (response.getSucessfulList().size() == 0) {
					throw new HDException("上传失败:"
							+ response.getFailedList().size() + "条;");

				} else {
					throw new HDException("上传成功:"
							+ response.getSucessfulList().size() + "条;失败:"
							+ response.getFailedList().size() + "条;");
				}
			}
		}
	}

	/**
	 * beforUpDataInfo:(上传数据前的处理). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param args
	 * @return
	 * @throws HDException
	 */
	public boolean beforUpDataInfo(Object... args) throws HDException {
		if (args != null) {
			if (args[0] instanceof SuperEntity) {
				SuperEntity sup = (SuperEntity) args[0];
				moduleCode = sup.getAttribute("modelnum") == null ? "" : sup
						.getAttribute("modelnum").toString();
			} else if (args[0] instanceof String) {
				moduleCode = args[0].toString();
			} else {
				throw new HDException("请传入模块编码");
			}
			if (isCS) {
				zysqid = ((PDAWorkOrderInfoConfig) args[1]).getUd_zyxk_zysqid();
				zypClass = ((PDAWorkOrderInfoConfig) args[1]).getZypclass();
			}
		} else {
			throw new HDException("请传入模块对象编码");
		}
		return true;
	}

	/**
	 * startUpdateInfo:(开始上传数据). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param str
	 * @throws HDException
	 */
	@SuppressWarnings("unchecked")
	public Object startUpdateInfo(String upstr) throws HDException {
		// 记录上传信息
		setWriteDubuglog(upstr);
		Object tempobj = initParam();
		HashMap<String, Object> tempdateParam = null;
		if (tempobj instanceof HashMap) {
			tempdateParam = (HashMap<String, Object>) tempobj;
			tempdateParam.put(PadInterfaceRequest.KEYDATA, upstr);
		}
		Object obj = null;
		obj = sClient.action(getMethodType(), tempdateParam == null ? tempobj
				: tempdateParam);
		return obj;
	}

	/**
	 * afterUpdateInfo:(上传数据后执行的方法). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param args
	 */
	public void afterUpdateInfo(Object pcretob, Object... args) {

	}

	/**
	 * errorUpdateInfo:(处理错误信息). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param args
	 */
	public void errorUpdateInfo(Object... args) {

	}

	/**
	 * upDateInfoFormat:(上传数据格式化). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param args
	 * @return
	 */
	public String upDateInfoFormat(Object obj, Object objstr) {
		// 格式化代码格式
		String retStr = null;
		String tabeName = null;
		if (obj != null) {
			if (obj instanceof List<?>) {
				if (((List<?>) obj).get(0) instanceof SuperEntity) {
					SuperEntity tempsuper = (SuperEntity) ((List<?>) obj)
							.get(0);
					tabeName = tempsuper.getDBTableName();
				}
			} else if (obj instanceof SuperEntity) {
				tabeName = ((SuperEntity) obj).getDBTableName();
			}
		}
		if (tabeName != null) {
			if (objstr.toString().startsWith("[")) {
				retStr = "{'" + tabeName.toUpperCase() + "':"
						+ objstr.toString() + "}";
			} else {
				retStr = "{'" + tabeName.toUpperCase() + "':["
						+ objstr.toString() + "]}";
			}

		}
		return retStr == null ? objstr.toString() : retStr;
	}

	/**
	 * gainUpInfo:(获取上传数据). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public Object gainUpInfo(Object ob) {
		return GsonUtil.objectToJson(ob);
	}

	@Override
	public Object initParam() throws HDException {
		hashmap.put(PadInterfaceRequest.KEYPERSONID, SystemProperty
				.getSystemProperty().getLoginPerson().getPersonid());
		// 添加模块编码
		hashmap.put(PadInterfaceRequest.KEYMODULECODE, moduleCode == null ? ""
				: moduleCode);
		if (isCS) {
			hashmap.put(PadInterfaceRequest.KEYZYPSTRID, zysqid);
			hashmap.put(PadInterfaceRequest.KEYCLUDEZYPCLASSSTR, zypClass);
		}
		return hashmap;
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return logger;
	}

	@Override
	public String getMethodType() {
		// TODO Auto-generated method stub
		return PadInterfaceContainers.METHOD_ZYPONLINE_CLICKSURE;
	}

	public boolean isCS() {
		return isCS;
	}

	public void setCS(boolean isCS) {
		this.isCS = isCS;
	}

}
