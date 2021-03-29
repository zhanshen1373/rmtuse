package com.hd.hse.dc.business.web.onlinelist;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.result.BaseWebResult;
import com.hd.hse.dc.business.common.result.EntityListWebResult;
import com.hd.hse.dc.business.common.weblistener.down.DataListListener;
import com.hd.hse.entity.workorder.WorkTask;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;
import com.hd.hse.padinterface.PadInterfaceResponse;
import com.hd.hse.system.SystemProperty;
import com.hd.hse.utils.UtilToolsMsg;

/**
 * ClassName: GainDataInfoBaseOnline (在线获取数据的基类)<br/>
 * date: 2015年6月3日 <br/>
 * 
 * @author lxf
 * @version
 */
public class GainDataInfoBaseOnline extends DataListListener {

	private static Logger logger = LogUtils
			.getLogger(GainDataInfoBaseOnline.class);
	private HashMap<String, Object> hashmap = new HashMap<String, Object>();

	private String moduleCode = null;

	@Override
	public Object action(String action, Object... args) throws HDException {
		// TODO Auto-generated method stub
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
		} else {
			throw new HDException("请传入模块对象编码");
		}
		return super.action(action, args);
	}

	/**
	 * search:TODO(查询条件).
	 */
	private String search = null;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	private String taskid;
	/**
	 * getTaskid:(获取查询主键). <br/>
	 * date: 2015年6月30日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public String getTaskid() {
		return taskid;
	}

	/**
	 * setTaskid:(设置查询主键). <br/>
	 * date: 2015年6月30日 <br/>
	 *
	 * @author lxf
	 * @param taskid
	 */
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	@Override
	public BaseWebResult getResultChangeType() {
		// TODO Auto-generated method stub
		try {
			return new EntityListWebResult(getEntityClass(),
					getAnalyzeDataAdapter());
		} catch (HDException e) {
			setWritelog(e.getMessage());
		}
		return null;
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return WorkTask.class;
	}

	@Override
	public String[] getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object initParam() {
		hashmap.put(PadInterfaceRequest.KEYPERSONID, SystemProperty
				.getSystemProperty().getLoginPerson().getPersonid());
		// 表示查看本地作业票是，浏览PC端票
		hashmap.put(PadInterfaceRequest.KEYFUNCODE, "true");
		if (search != null) {
			hashmap.put(PadInterfaceRequest.KEYWHERE, search);
		} else {
			hashmap.put(PadInterfaceRequest.KEYWHERE, "");
		}
		if(taskid!=null){
			hashmap.put(PadInterfaceRequest.KEYZYPSTRID, taskid);
		}
		else{
			hashmap.put(PadInterfaceRequest.KEYZYPSTRID, "");
		}
		// 添加模块编码
		hashmap.put(PadInterfaceRequest.KEYMODULECODE, moduleCode);
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
		return PadInterfaceContainers.METHOD_ZYPONLINE_ZYPBROWSE;
	}

	/**
	 * TODO 重载 获取数据的方法
	 * 
	 * @see com.hd.hse.dc.business.common.weblistener.down.GainPCDataListener#getDataInfo(Object[])
	 */
	@Override
	public Object getDataInfo(Object... obj) throws HDException {
		String methodtype = getMethodType();
		if (StringUtils.isEmpty(methodtype)) {
			throw new HDException("请注册要调用方法的");
		}
		Object retobj = sClient.action(methodtype,
				(HashMap<String, Object>) initParam());
		if (retobj instanceof PadInterfaceResponse) {
			PadInterfaceResponse response = (PadInterfaceResponse) retobj;
			if (response.getFlag().equalsIgnoreCase(PadInterfaceContainers.METHOD_BUSINESSFAILD)) {
				String tempmsg = UtilToolsMsg.resolveMsg(response
						.getExceptionmsg());
				setWritelog("PC错误：" + tempmsg);
				if (tempmsg.equalsIgnoreCase("0")) {
					return "";
				}
				throw new HDException(tempmsg);
			} else {
				setWritelog("PC错误：" + response.getExceptionmsg());
				throw new HDException("加载失败,请联系管理员");
			}
		}
		if (retobj == null) {
			retobj = "[]";
		}
		setWriteDubuglog(retobj.toString());
		BaseWebResult type = getResultChangeType();
		if (null == type) {
			return retobj;
		} else {
			return type.processResultSet(retobj);
		}
	}

}
