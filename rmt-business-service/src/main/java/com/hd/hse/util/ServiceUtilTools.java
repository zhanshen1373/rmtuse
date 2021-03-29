/**
 * Project Name:hse-business-service
 * File Name:UtilTools.java
 * Package Name:com.hd.hse.util
 * Date:2015年6月15日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.util;

import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.entity.conf.RmtConfMIntfc;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:UtilTools ().<br/>
 * Date: 2015年6月15日 <br/>
 * 
 * @author zhaofeng
 * @version
 * @see
 */
public class ServiceUtilTools {
	private static final Logger logger = LogUtils
			.getLogger(ServiceUtilTools.class);

	/**
	 * mergeSameList:(根据type合并list中相同的项为一项，而且将所有相同的项的集合挂在合并后的那一项下面). <br/>
	 * date: 2015年6月15日 <br/>
	 * 
	 * @author zhaofeng
	 * @param allList
	 * @param type
	 * @throws CloneNotSupportedException
	 */
	public static List<RmtConfMIntfc> mergeSameList(
			List<RmtConfMIntfc> allList, String type) {
		if (type == null)
			return allList;
		List<RmtConfMIntfc> childList = new ArrayList<RmtConfMIntfc>();
		List<RmtConfMIntfc> newAllList = new ArrayList<RmtConfMIntfc>();
		RmtConfMIntfc sameConfig = null;
		int index = 0;
		for (RmtConfMIntfc pdaWorkOrderInfoConfig : allList) {
			if (type.contains(pdaWorkOrderInfoConfig.getTab_type()+"")) {
				childList.add(pdaWorkOrderInfoConfig);
				if (sameConfig == null) {
					try {
						sameConfig = (RmtConfMIntfc) pdaWorkOrderInfoConfig
								.clone();
					} catch (CloneNotSupportedException e) {
						logger.error(e);
						// throw new HDException("数据解析失败！", e);
					}
					index = allList.indexOf(pdaWorkOrderInfoConfig);
				}
			} else {
				newAllList.add(pdaWorkOrderInfoConfig);
			}
		}
		if (sameConfig != null) {
			sameConfig.setChild(RmtConfMIntfc.class.getName(),
					(List) childList);
			newAllList.add(index, sameConfig);
		}
		return newAllList;
	}
}
