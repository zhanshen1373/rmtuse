/**
 * Project Name:hse-entity-service
 * File Name:ServiceActivityUtils.java
 * Package Name:com.hd.hse.utils
 * Date:2014年10月22日
 * Copyright (c) 2014, zhaofeng@ushayden.com All Rights Reserved.
 *
 */
package com.hd.hse.utils;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.entity.base.PDAWorkOrderInfoConfig;
import com.hd.hse.entity.workorder.WorkOrder;

/**
 * ClassName: ServiceActivityUtils ()<br/>
 * date: 2014年10月22日 <br/>
 * 
 * @author zhaofeng
 * @version
 */
public class ServiceActivityUtils {

	/**
	 * getRefreshWorkConfigInfo:(为当前配置信息打标记). <br/>
	 * date: 2015年3月3日 <br/>
	 *
	 * @author zhaofeng
	 * @param workOrder
	 * @param workConfig
	 * @return
	 * @throws HDException
	 */
	public static SuperEntity getRefreshWorkConfigInfo(WorkOrder workOrder,
			PDAWorkOrderInfoConfig workConfig) throws HDException {
		if (StringUtils.isEmpty(workOrder.getCssavefied()) || workConfig.getContype()==null) {
			workConfig.setIsComplete(0);
		} else if(workOrder.getCssavefied().contains(workConfig.getContype().toString())){
			workConfig.setIsComplete(1);
		}
		return workConfig;
	}
	
	/**
	 * getRefreshWorkConfigInfos:(批量修改). <br/>
	 * date: 2015年3月3日 <br/>
	 *
	 * @author zhaofeng
	 * @param workOrder
	 * @param list
	 * @return
	 * @throws HDException
	 */
	public static List<SuperEntity> getRefreshWorkConfigInfos(WorkOrder workOrder,
			List<SuperEntity> list) throws HDException {
		for (SuperEntity superEntity : list) {
			PDAWorkOrderInfoConfig workConfig = (PDAWorkOrderInfoConfig)superEntity;
			getRefreshWorkConfigInfo(workOrder,workConfig);
		}
		return list;
	}
}
