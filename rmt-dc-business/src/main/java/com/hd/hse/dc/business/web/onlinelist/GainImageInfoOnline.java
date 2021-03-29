package com.hd.hse.dc.business.web.onlinelist;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hd.hse.business.task.IMessageWhat;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.result.BaseWebResult;
import com.hd.hse.dc.business.common.result.EntityListWebResult;
import com.hd.hse.dc.business.common.weblistener.down.DataListListener;
import com.hd.hse.entity.common.Image;
import com.hd.hse.padinterface.PadInterfaceContainers;
import com.hd.hse.padinterface.PadInterfaceRequest;
import com.hd.hse.system.SystemProperty;

/**
 * ClassName: GainImageInfoOnline (获取照片管理模块信息)<br/>
 * date: 2015年6月2日  <br/>
 *
 * @author lxf
 * @version 
 */
public class GainImageInfoOnline extends DataListListener {

	private static Logger logger = LogUtils.getLogger(GainImageInfoOnline.class);
	private HashMap<String, Object> hashmap = new HashMap<String, Object>();
	private StringBuilder strid = new StringBuilder();

	@Override
	public Object action(String action, Object... args){
		strid.delete(0, strid.length());
		if (null != args) {
			for (Object ob : args) {
				if (ob instanceof List) {
					@SuppressWarnings("unchecked")
					List<SuperEntity> listEntity = (List<SuperEntity>) ob;
					if (null != listEntity && listEntity.size() > 0) {
						for (SuperEntity supe : listEntity) {
							//strid.append("'");
							strid.append(supe.getAttribute(supe.getPrimaryKey()));
							strid.append(",");
						}
					}
					break;
				} else if (ob instanceof SuperEntity) {
					if (strid.length() > 0) {
						strid.append(",");
					}
					//strid.append("'");
					strid.append(((SuperEntity) ob)
							.getAttribute(((SuperEntity) ob).getPrimaryKey()));
					strid.append(",");
				}
				if (null != strid && strid.length() > 0) {
					strid.delete(strid.length() - 1, strid.length());
				}
			}
		}
		Object obj=null;
		try {
			obj = super.action(action, args);
			this.sendMessage(IMessageWhat.END, 98, 100, "获取成功",obj);
		} catch (HDException e) {
			setWritelog("获取数据报错:", e);
			this.sendMessage(IMessageWhat.ERROR, 9, 9, e.getMessage());
		}
		return obj;
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Image.class;
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
	public String[] getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object initParam() throws HDException {
		hashmap.put(PadInterfaceRequest.KEYPERSONID, SystemProperty
				.getSystemProperty().getLoginPerson().getPersonid());
		String dept = SystemProperty.getSystemProperty().getLoginPerson()
				.getDepartment();
		if (!StringUtils.isEmpty(dept)) {
			hashmap.put(PadInterfaceRequest.KEYDEPTNUM, dept);
		}
		if (strid.length() > 0) {
			hashmap.put(PadInterfaceRequest.KEYZYPSTRID, strid.toString());
		} else {
			hashmap.put(PadInterfaceRequest.KEYZYPSTRID, "");
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
		return PadInterfaceContainers.METHOD_COMMON_GETTASKIMAGEPATH;
	}


}
