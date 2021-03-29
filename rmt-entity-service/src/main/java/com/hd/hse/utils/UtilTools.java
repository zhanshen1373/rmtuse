/**
 * Project Name:hse-entity-service
 * File Name:UtilTools.java
 * Package Name:com.hd.hse.service.workorder.queryinfo
 * Date:2014年10月17日
 * Copyright (c) 2014, zhaofeng@ushayden.com All Rights Reserved.
 *
 */
package com.hd.hse.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.entity.other.WorkTaskDetailInfo;
import com.hd.hse.service.workorder.checkrules.AbstractCheckListener;

/**
 * ClassName: UtilTools (工具类)<br/>
 * date: 2014年10月17日 <br/>
 * 
 * @author zhaofeng
 * @version
 */
public class UtilTools {
	protected static Logger logger = LogUtils
			.getLogger(AbstractCheckListener.class);

	/**
	 * ConvertToSqlString:(将拼接起来的字符串，转化成sql类型的). <br/>
	 * date: 2014年10月17日 <br/>
	 * 
	 * @author zhaofeng
	 * @param str
	 * @return
	 */
	public static String convertToSqlString(String str) {
		if (StringUtils.isEmpty(str))
			return "()";
		StringBuilder retsb = new StringBuilder();
		retsb.append("(");
		String[] strSplit = str.split(",");
		for (String strp : strSplit) {
			retsb.append("'").append(strp.split(":")[0]).append("',");
		}
		if (retsb.length() > 1) {
			retsb.delete(retsb.length() - 1, retsb.length());
		}
		retsb.append(")");
		return retsb.toString();
	}

	/**
	 * judgeMapValue:(校验map中是否包含该对象，并且返回). <br/>
	 * date: 2014年10月23日 <br/>
	 * 
	 * @author zhaofeng
	 * @param map
	 * @param classz
	 * @param isMust
	 * @return
	 * @throws HDException
	 */
	public static Object judgeMapValue(Map<String, Object> map,
			Class<?> classz, boolean isMust) throws HDException {
		if (isMust) {
			if (!map.containsKey(classz.getName()))
				throw new HDException("系统错误，请联系管理员！");
			Object object = map.get(classz.getName());
			if (!classz.isInstance(object))
				throw new HDException("系统错误，请联系管理员！");
			return object;
		} else {
			if (!map.containsKey(classz.getName()))
				return null;
			Object object = map.get(classz.getName());
			if (!classz.isInstance(object))
				return null;
			return object;
		}
	}

	/**
	 * cloneMap:(将初始化map中的数据，clone到另一个map中去). <br/>
	 * date: 2014年10月24日 <br/>
	 * 
	 * @author zhaofeng
	 * @param map
	 * @param cloneMap
	 * @param classz
	 */
	public static void cloneMap(Map<String, SuperEntity> map,
			Map<String, SuperEntity> cloneMap, Class<?> classz) {
		SuperEntity object = map.get(classz.getName());
		try {
			SuperEntity clone = object.clone();
			cloneMap.put(classz.getName(), clone);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * cloneList:(克隆list结合对象). <br/>
	 * date: 2015年3月4日 <br/>
	 * 
	 * @author lxf
	 * @param list
	 * @param cloneList
	 */
	public static void cloneList(List<SuperEntity> list,
			List<SuperEntity> cloneList) {
		try {
			for (SuperEntity superEntity : list) {
				cloneList.add(superEntity.clone());
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * judgeMapListValue:(校验map中是否包含该对象的list集合，并且返回). <br/>
	 * date: 2014年10月23日 <br/>
	 * 
	 * @author zhaofeng
	 * @param map
	 * @param classz
	 * @param isMust
	 * @return
	 * @throws HDException
	 */
	public static Object judgeMapListValue(Map<String, Object> map,
			Class<?> classz, boolean isMust) throws HDException {
		if (isMust) {
			if (!map.containsKey(classz.getName()))
				throw new HDException("系统错误，请联系管理员！");
			Object object = map.get(classz.getName());
			if (object instanceof List) {
				List list = (List) object;
				if (list.size() == 0)
					throw new HDException("系统错误，请联系管理员！");
				for (int i = 0; i < list.size(); i++) {
					if (!classz.isInstance(list.get(i)))
						throw new HDException("系统错误，请联系管理员！");
				}
			} else {
				throw new HDException("系统错误，请联系管理员！");
			}
			return object;
		} else {
			if (!map.containsKey(classz.getName()))
				return null;
			Object object = map.get(classz.getName());
			if (object instanceof List) {
				List list = (List) object;
				if (list.size() == 0)
					return null;
				for (int i = 0; i < list.size(); i++) {
					if (!classz.isInstance(list.get(i)))
						return null;
				}
			} else {
				return null;
			}
			return object;
		}
	}

	/**
	 * isRangeIn:(判断范围). <br/>
	 * date: 2014年10月30日 <br/>
	 * 
	 * @author zhaofeng
	 * @param maxValue
	 * @param value
	 * @param minValue
	 * @param isbj
	 * @return
	 */
	public static boolean isRangeIn(String maxValue, float value,
			String minValue, boolean isbj) {
		if (isbj) {
			if (StringUtils.isEmpty(maxValue) && !StringUtils.isEmpty(minValue)) {
				// 没有上限
				if (Float.valueOf(minValue) <= value) {
					return true;
				}
			} else if (!StringUtils.isEmpty(maxValue)
					&& StringUtils.isEmpty(minValue)) {
				// 没有下线
				if (value <= Float.valueOf(maxValue)) {
					return true;
				}
			} else if (!StringUtils.isEmpty(maxValue)
					&& !StringUtils.isEmpty(minValue)) {
				// 正常判断，有上下线
				if (value <= Float.valueOf(maxValue)
						&& Float.valueOf(minValue) <= value) {
					return true;
				}
			} else if (StringUtils.isEmpty(maxValue)
					&& StringUtils.isEmpty(minValue)) {
				// 没有上下线
				return true;
			}

		} else {
			if (StringUtils.isEmpty(maxValue) && !StringUtils.isEmpty(minValue)) {
				// 没有上限
				if (Float.valueOf(minValue) < value) {
					return true;
				}
			} else if (!StringUtils.isEmpty(maxValue)
					&& StringUtils.isEmpty(minValue)) {
				// 没有下线
				if (value < Float.valueOf(maxValue)) {
					return true;
				}
			} else if (!StringUtils.isEmpty(maxValue)
					&& !StringUtils.isEmpty(minValue)) {
				// 正常判断，有上下线
				if (value < Float.valueOf(maxValue)
						&& Float.valueOf(minValue) < value) {
					return true;
				}
			} else if (StringUtils.isEmpty(maxValue)
					&& StringUtils.isEmpty(minValue)) {
				// 没有上下线
				return true;
			}
		}
		return false;
	}

	/**
	 * getSysCurrentTime:(得到系统当前时间). <br/>
	 * date: 2014年10月23日 <br/>
	 * 
	 * @author zhaofeng
	 * @return
	 */
	public static String getSysCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		return format.format(new Date());
	}

	/**
	 * dataTimeCompare:(两个时间比较大小). <br/>
	 * date: 2014年11月26日 <br/>
	 * 
	 * @author lxf
	 * @param time1
	 * @param time2
	 * @return time1>time2返回ture 否则返回false
	 * @throws HDException
	 */
	public static boolean dataTimeCompare(String time1, String time2)
			throws HDException {
		boolean ret = false;
		SimpleDateFormat formart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date datetime1 = formart.parse(time1);
			Date datetime2 = formart.parse(time2);
			if (datetime1.compareTo(datetime2) > 0) {
				ret = true;
			}
		} catch (ParseException e) {
			logger.error(e.getMessage() + "time1" + time1 + ";time2:" + time2,
					e);
			throw new HDException("气体检测时间格式不正确，不能审核！", e);
		}
		return ret;
	}

	/**
	 * toAddHours:(给时间增加小时). <br/>
	 * date: 2014年11月28日 <br/>
	 * 
	 * @author zhaofeng
	 * @param date1
	 * @param hour
	 * @return
	 * @throws HDException
	 */
	public static String toAddMinutes(String date1, int minute)
			throws HDException {
		DateFormat formart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		try {
			Date datetime1 = formart.parse(date1);
			Calendar ca = Calendar.getInstance();
			ca.setTime(datetime1);
			ca.add(Calendar.MINUTE, minute);
			return formart.format(ca.getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			throw new HDException("气体检测时间格式不正确，不能审核！", e);
		}
	}

	/**
	 * groupList:(将List集合分组). <br/>
	 * date: 2015年1月19日 <br/>
	 * 
	 * @author zhaofeng
	 * @param list
	 *            已经按照groupProperty属性排好序的集合
	 * @param classz
	 *            list中的子项Class
	 * @param groupProperty
	 *            分组的属性
	 * @param setValueProperty
	 *            组标题要赋值的属性
	 * @return
	 * @throws HDException
	 */
	public static List<?> groupList(List<SuperEntity> list, Class<?> classz,
			String groupProperty, String setValueProperty) throws HDException {
		if (list == null || list.size() == 0)
			return null;
		if (groupProperty == null || setValueProperty == null)
			return null;
		String tempValue = list.get(0).getAttribute(groupProperty) == null ? ""
				: list.get(0).getAttribute(groupProperty).toString();
		if (StringUtils.isEmpty(tempValue))
			return null;
		List<SuperEntity> returnList = new ArrayList<SuperEntity>();
		SuperEntity addEntity = null;
		List<SuperEntity> itemEntity = null;
		try {
			if (classz.isInstance(list.get(0))) {
				for (int i = 0; i < list.size(); i++) {
					tempValue = list.get(i).getAttribute(groupProperty) == null ? ""
							: list.get(i).getAttribute(groupProperty)
									.toString();
					if (StringUtils.isEmpty(tempValue))
						return null;
					if (addEntity == null
							|| !tempValue
									.equals(addEntity
											.getAttribute(setValueProperty) == null ? ""
											: addEntity.getAttribute(
													setValueProperty)
													.toString())) {
						if (addEntity != null && itemEntity != null) {
							addEntity.setChild(classz.getName(), itemEntity);
							returnList.add(addEntity);
						}
						addEntity = (SuperEntity) classz.newInstance();
						itemEntity = new ArrayList<SuperEntity>();
						addEntity.setAttribute(setValueProperty, tempValue);
					}
					itemEntity.add(list.get(i));
				}
				// 增加在以后一个分组
				if (addEntity != null && itemEntity != null) {
					addEntity.setChild(classz.getName(), itemEntity);
					returnList.add(addEntity);
				}
			}
			return returnList;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
			throw new HDException("集合分组失败！", e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
			throw new HDException("集合分组失败！", e);
		}
	}

	/**
	 * mergeList:(合并二维集合，成为一维集合). <br/>
	 * date: 2015年1月19日 <br/>
	 * 
	 * @author zhaofeng
	 * @param list
	 * @param classz
	 * @return
	 */
	public static List<?> mergeList(List<SuperEntity> list, Class<?> classz) {
		if (list == null || list.size() == 0)
			return null;
		List returnList = new ArrayList();
		List<SuperEntity> tempList = null;
		for (int i = 0; i < list.size(); i++) {
			tempList = list.get(0).getChild(classz.getName());
			if (tempList != null) {
				returnList.addAll(tempList);
			}
		}
		return returnList;
	}

	/**
	 * getHostAndPort:(根据URL获取IP和端口号). <br/>
	 * date: 2015年4月27日 <br/>
	 * 
	 * @author lxf
	 * @param url
	 * @return
	 */
	public static String getHostAndPort(String path) {
		String ip = null;
		try {
			URL url = new URL(path);
			int port = url.getPort();
			if (port == -1) {
				ip = "http://" + url.getHost() + File.separator;
			} else {
				ip = "http://" + url.getHost() + ":" + url.getPort()
						+ File.separator;
			}
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e);
		}
		return ip;
	}

	private static String hdtitle = "hdtitle";
	private static String hdtitle1 = "$hdtitle";

	/**
	 * ConvertToListObject:(). <br/>
	 * date: 2015年5月28日 <br/>
	 * 
	 * @author lxf
	 * @param superEntity
	 *            要解析的对象
	 * @param attr
	 *            要转换的属性
	 * @param arrtname
	 *            属性描述
	 * @return 注意：属性字段 遇到hdtitle 会跳过，标记为标题
	 * @throws HDException
	 */
	public static List<SuperEntity> ConvertToListObject(
			SuperEntity superEntity, String[] attrs, String[] arrtnames)
			throws HDException {
		if (superEntity == null || attrs == null || attrs == null) {
			return null;
		}
		if (attrs.length != arrtnames.length) {
			// 表示传入的键值对有问题。
			throw new HDException("传入编码和描述个数不一致");
		}
		int i = 0;
		List<SuperEntity> retList = new ArrayList<SuperEntity>();
		for (String attr : attrs) {
			// 添加属性
			if (attr.equalsIgnoreCase(hdtitle)) {
				WorkTaskDetailInfo work = new WorkTaskDetailInfo();
				if (attr.equalsIgnoreCase(hdtitle1)) {
					Object tempvalue = superEntity.getAttribute(attr);
					if (tempvalue != null) {
						work.setDescrption(tempvalue.toString());
					}
				} else {
					// work.setDesValue(hdtitle);
					work.setDescrption(arrtnames[i]);
				}
				work.setIstitle(true);
				retList.add(work);
				i++;
				continue;
			}
			Object value = superEntity.getAttribute(attr);
			if (value != null) {
				WorkTaskDetailInfo work = new WorkTaskDetailInfo();
				work.setDesValue(value.toString());
				work.setDescrption(arrtnames[i]);
				retList.add(work);
			}
			i++;
			// 获取子类对象信息
			Map<String, List<SuperEntity>> map = superEntity.getChilds();
			if (map != null) {
				Iterator<Map.Entry<String, List<SuperEntity>>> listit = map
						.entrySet().iterator();
				while (listit.hasNext()) {
					Map.Entry<String, List<SuperEntity>> entry = listit.next();
					for (SuperEntity tempSuper : entry.getValue()) {
						retList.addAll(ConvertToListObject(tempSuper, attrs,
								arrtnames));
					}
				}
			}
		}
		return retList;
	}

	/**
	 * ConvertToListObject:(). <br/>
	 * date: 2015年5月28日 <br/>
	 * 
	 * @author lxf
	 * @param superEntity
	 *            要解析的对象
	 * @param attr
	 *            要转换的属性
	 * @param arrtname
	 *            属性描述
	 * @return 注意：属性字段 遇到hdtitle 会跳过，标记为标题
	 * @throws HDException
	 */
	public static List<SuperEntity> ConvertToParentListObject(
			SuperEntity superEntity, String[] attrs, String[] arrtnames)
			throws HDException {
		if (superEntity == null || attrs == null || attrs == null) {
			return null;
		}
		if (attrs.length != arrtnames.length) {
			// 表示传入的键值对有问题。
			throw new HDException("传入编码和描述个数不一致");
		}
		int i = 0;
		List<SuperEntity> retList = new ArrayList<SuperEntity>();
		for (String attr : attrs) {
			// 添加属性
			if (attr.equalsIgnoreCase("hdtitle")
					|| attr.equalsIgnoreCase(hdtitle1)) {
				WorkTaskDetailInfo work = new WorkTaskDetailInfo();
				if (attr.equalsIgnoreCase(hdtitle1)) {
					Object tempvalue = superEntity.getAttribute(attr);
					if (tempvalue != null) {
						work.setDescrption(tempvalue.toString());
					}
				} else {
					// work.setDesValue(hdtitle);
					work.setDescrption(arrtnames[i]);
				}
				work.setIstitle(true);
				retList.add(work);
				i++;
				continue;
			}
			Object value = superEntity.getAttribute(attr);
			if (value != null) {
				WorkTaskDetailInfo work = new WorkTaskDetailInfo();
				work.setDesValue(value.toString());
				work.setDescrption(arrtnames[i]);
				if (StringUtils.isEmpty(arrtnames[i])) {
					work.setIstitle(true);
					work.setDescrption(value.toString());
				}
				retList.add(work);
			}
			i++;
		}
		if (retList!=null && retList.size() > 0) {
			WorkTaskDetailInfo tempwork = (WorkTaskDetailInfo) retList
					.get(retList.size() - 1);
			if (tempwork.isIstitle()) {
				retList.remove(tempwork);
			}
		}
		return retList;
	}

	/**
	 * ConvertToListObject:(). <br/>
	 * date: 2015年5月28日 <br/>
	 * 
	 * @author lxf
	 * @param superEntity
	 *            要解析的对象
	 * @param attr
	 *            要转换的属性集合
	 * @param arrtname
	 *            属性描述集合
	 * @return 注意：属性字段 遇到hdtitle 会跳过，标记为标题
	 * @throws HDException
	 */
	public static List<SuperEntity> TaskConvertToListObject(
			SuperEntity superEntity, List<String[]> attrs,
			List<String[]> arrtnames) throws HDException {
		List<SuperEntity> retList = new ArrayList<SuperEntity>();
		// 一级是任务的转换的
		if (superEntity == null || attrs == null || attrs == null) {
			return null;
		}
		if (attrs.size() != arrtnames.size()) {
			// 表示传入的键值对有问题。
			throw new HDException("传入编码和描述个数不一致");
		}
		List<SuperEntity> listSuper = new ArrayList<SuperEntity>();
		listSuper.add(superEntity);
		int i = 0;
		retList.addAll(TaskConvertToListObject(listSuper, attrs, arrtnames, i));
		// 表示取出任务要显示的信息
		// 二级是作业票的转换
		// 气体检测主表+用电设备+地下设施
		// 气体检测子表
		return retList;
	}

	private static List<SuperEntity> TaskConvertToListObject(
			List<SuperEntity> listSuperEntity, List<String[]> attrs,
			List<String[]> arrtnames, int index) throws HDException {
		List<SuperEntity> retList = new ArrayList<SuperEntity>();
		if (attrs.size() > index) {
			// 获取子类对象信息
			for (SuperEntity superEntity : listSuperEntity) {
				// 表示取出任务要显示的信息
				retList.addAll(ConvertToParentListObject(superEntity,
						attrs.get(index), arrtnames.get(index)));
				Map<String, List<SuperEntity>> map = superEntity.getChilds();
				if (map != null) {
					Iterator<Map.Entry<String, List<SuperEntity>>> listit = map
							.entrySet().iterator();
					while (listit.hasNext()) {
						Map.Entry<String, List<SuperEntity>> entry = listit
								.next();
						index++;
						retList.addAll(TaskConvertToListObject(
								entry.getValue(), attrs, arrtnames, index));
						index--;
					}
				}
			}
		}
		return retList;
	}

	/**
	 * ConvertToListObject:(). <br/>
	 * date: 2015年5月28日 <br/>
	 * 
	 * @author lxf
	 * @param superEntity
	 *            要解析的对象
	 * @param attr
	 *            要转换的属性集合
	 * @param arrtname
	 *            属性描述集合
	 * @return 注意：属性字段 遇到hdtitle 会跳过，标记为标题
	 * @throws HDException
	 */
	public static List<SuperEntity> TaskConvertToListObjectFromAttr(
			SuperEntity superEntity, List<String[]> attrs,
			List<String[]> arrtnames) throws HDException {
		// List<SuperEntity> retList = new ArrayList<SuperEntity>();
		// // 一级是任务的转换的
		// if (superEntity == null || attrs == null || attrs == null) {
		// return null;
		// }
		// if (attrs.size() != arrtnames.size()) {
		// // 表示传入的键值对有问题。
		// throw new HDException("传入编码和描述个数不一致");
		// }
		// int index=0;
		// //表示取出任务信息
		// retList.addAll(ConvertToParentListObject(superEntity,
		// attrs.get(index), arrtnames.get(index)));
		// //表示取出票的信息
		// Map<String, List<SuperEntity>> map = superEntity.getChilds();
		// if (map != null) {
		// Iterator<Map.Entry<String, List<SuperEntity>>> listit = map
		// .entrySet().iterator();
		// while (listit.hasNext()) {
		// Map.Entry<String, List<SuperEntity>> entry = listit.next();
		// index++;
		// retList.addAll(TaskConvertToListObject(entry.getValue(),attrs,arrtnames,index));
		// index--;
		// }
		// }
		// // 表示取出任务要显示的信息
		// // 二级是作业票的转换
		// // 气体检测主表+用电设备+地下设施
		// // 气体检测子表
		// return retList;
		return null;
	}

}
