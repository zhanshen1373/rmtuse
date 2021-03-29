/**
 * Project Name:hse-entity-service
 * File Name:GsonHandler.java
 * Package Name:com.hd.hse.service.branches.tools
 * Date:2015年5月27日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 *
 */

package com.hd.hse.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;

/**
 * ClassName:JsonUtil ().<br/>
 * Date: 2015年5月27日 <br/>
 * 
 * @author zhaofeng
 * @version
 * @see
 */
public class JsonUtil {

	/**
	 * logger:TODO(日志).
	 */
	private static final Logger logger = LogUtils.getLogger(JsonUtil.class);

	/**
	 * getList:(将json转化成对应的集合). <br/>
	 * date: 2015年5月27日 <br/>
	 * 
	 * @author zhaofeng
	 * @param jsonString
	 * @param cls
	 * @return
	 * @throws HDException
	 */
	public static <T> List<T> getList(String jsonString, Class<T[]> cls)
			throws HDException {
		List<T> list = new ArrayList<T>();
		try {
			Gson gson = new Gson();
			T[] arr = gson.fromJson(jsonString, cls);
			list = Arrays.asList(arr);
		} catch (Exception e) {
			logger.error(jsonString, e);
			throw new HDException("数据解析失败！", e);
		}
		return list;
	}

	/**
	 * getObject:(将Json转化成对应de对象). <br/>
	 * date: 2015年5月27日 <br/>
	 * 
	 * @author zhaofeng
	 * @param jsonString
	 * @param cls
	 * @return
	 * @throws HDException
	 */
	public static <T> T getObject(String jsonString, Class<T> cls)
			throws HDException {
		try {
			Gson gson = new Gson();
			T arr = gson.fromJson(jsonString, cls);
			return arr;
		} catch (Exception e) {
			logger.error(jsonString, e);
			throw new HDException("数据解析失败！", e);
		}
	}

	/**
	 * isJson:(判断字符串是否是json格式). <br/>
	 * date: 2015年5月27日 <br/>
	 * 
	 * @author zhaofeng
	 * @param jsonStr
	 * @return
	 */
	public static boolean isJson(String jsonStr) {
		if (StringUtils.isEmpty(jsonStr))
			return false;
		try {
			new JsonParser().parse(jsonStr);
			return true;
		} catch (JsonParseException e) {
			logger.error(jsonStr, e);
			return false;
		}
	}
}
