package com.hd.hse.dc.business.common.util;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.renderscript.Int2;
import android.util.Log;

import com.hd.hse.business.util.TableDesc;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.result.GsonUtil;

public class GsonDataAdapter extends AbstractDataAdapter {

	private static Logger logger = LogUtils.getLogger(JsonDataAdapter.class);

	private static GsonDataAdapter jsonAnalyze = null;

	private GsonDataAdapter() {

	}

	/**
	 * instance:(实例化) Data: 2014年8月11日 <br/>
	 * 
	 * @author lxf
	 * @return 实例化对象
	 * */
	public static GsonDataAdapter getInstance() {
		if (null == jsonAnalyze) {
			jsonAnalyze = new GsonDataAdapter();
		}
		return jsonAnalyze;

	}

	@SuppressWarnings("unused")
	public HashMap<String, List<String>> resSql(String str, String primaryKey)
			throws HDException {
		// String str =
		// "{'zysq':[{'zysqid':'01','cstable':[{'csid':'cs01','csname':'措施名称','spjl':[{'jlid':'jl01','jldate':'now'}]}]},{'zysqid':'02','cstable':[{'csid':'cs02','csname':'措施名称02','spjl':[{'jlid':'jl02','jldate':'now02'}]}]}]}";
		JSONObject jsonObject;
		List<String> sqllist = new ArrayList<String>();
		HashMap<String, List<String>> hasmap = new HashMap<String, List<String>>();
		try {
			jsonObject = new JSONObject(str);
			try {
				getResJson(jsonObject, "", sqllist, hasmap, primaryKey);
			} catch (Exception e) {

				logger.error("解析JSon报错;", e);
			}

		} catch (JSONException e) {

			logger.error("解析JSon报错;", e);
		}
		return hasmap;

	}

	@Override
	public HashMap<String, List<String>> resSql(String str,
			HashMap<String, TableDesc> hashRelation) throws HDException {
		// str =
		// "{'UD_CBSGL_CL':[{'carnum':'1294','CREATEBY':'MAXADMIN','UD_CBSGL_CLJS':[{'carnum':'1294','UD_CBSGL_CLJSID':'901','NAME':'文林'}]}]}";
		JSONObject jsonObject;
		// 表示返回的对象
		HashMap<String, List<String>> rethash = new HashMap<String, List<String>>();
		HashMap<String, String> ishave = null;
		String primaryValue = null;
		List<String> listsql = null;
		StringBuilder sbsqlInsert = new StringBuilder();
		StringBuilder sbsqlupdate = new StringBuilder();
		StringBuilder sbsqlmainsubdelete = new StringBuilder();
		StringBuilder sbcolum = new StringBuilder();
		StringBuilder sbvalue = new StringBuilder();
		StringBuilder sbzysqid = new StringBuilder();
		try {
			jsonObject = new JSONObject(str);
			@SuppressWarnings("unchecked")
			Iterator<String> iter = jsonObject.keys();
			// 表示主表
			while (iter.hasNext()) {
				// 表示表明
				String keytablename = iter.next();
				JSONArray jsonArray = null;
				JSON_TYPE jsonType = JSON_TYPE.JSON_TYPE_ERROR;
				try {
					// 报错表示不是数组
					// 如果是数组，表示每一行的值
					jsonArray = jsonObject.getJSONArray(keytablename);
					jsonType = JSON_TYPE.JSON_TYPE_ARRAY;
				} catch (Exception e) {

				}
				if (jsonType == JSON_TYPE.JSON_TYPE_ARRAY) {
					// 表示数组,主表筛选的行数
					keytablename = keytablename.toLowerCase();
					int count = jsonArray.length();
					for (int i = 0; i < count; i++) {
						// 表示循环读取每一行的数据
						listsql = new ArrayList<String>();// 用于存取子表数据
						primaryValue = null;// 每循环一次，主键值清空
						ishave = new HashMap<String, String>();

						JSONObject jsoinobject2 = jsonArray.getJSONObject(i);
						@SuppressWarnings("unchecked")
						Iterator<String> iter2 = jsoinobject2.keys();
						while (iter2.hasNext()) {
							// key表示属性或子表
							String key2 = iter2.next();
							JSONArray jsonArray2 = null;
							JSON_TYPE jsonType2 = JSON_TYPE.JSON_TYPE_ERROR;
							try {
								// 报错表示不是数组
								jsonArray2 = jsoinobject2.getJSONArray(key2);
								jsonType2 = JSON_TYPE.JSON_TYPE_ARRAY;
							} catch (Exception e) {

							}
							if (jsonType2 == JSON_TYPE.JSON_TYPE_ARRAY) {
								// 表示取子表值
								for (int j = 0; j < jsonArray2.length(); j++) {
									// 表示是表明
									getSubListSql(key2.toLowerCase(),
											jsonArray2.getJSONObject(j),
											hashRelation, listsql, ishave,
											sbzysqid);
								}
							} else {
								// 表示取属性值
								String value = jsoinobject2.getString(key2);
								// 表示拼写更新语句sql语句
								if (null != hashRelation) {
									TableDesc tb = hashRelation
											.get(keytablename);
									if (null != tb) {
										// 表示当前处理表示主表
										if (key2.equalsIgnoreCase(tb
												.getPrimarykey())
												&& StringUtils
														.isEmpty(primaryValue)) {
											primaryValue = value;
											// 删除主表信息
											sbsqlupdate.append("delete from  ")
													.append(keytablename)
													.append("  where ")
													.append(tb.getPrimarykey())
													.append("='").append(value)
													.append("';");
										}
										if (key2.equalsIgnoreCase("dr")
												&& !StringUtils
														.isEmpty(primaryValue)) {
											// 删除子表数据
											String subtable = getSubTableName(
													keytablename, hashRelation);
											if (!StringUtils.isEmpty(subtable)) {

												StringBuilder sbsql = new StringBuilder();

												sbsql.append("delete from ")
														.append(subtable)
														.append(" where ")
														.append(tb
																.getPrimarykey())
														.append("='")
														.append(primaryValue)
														.append("';");
												// }
												boolean isadd = true;
												if (null != listsql
														&& listsql.size() > 0) {
													for (String string : listsql) {
														if (string
																.contains(sbsql
																		.toString())) {
															isadd = false;
															break;
														}
													}
												}
												if (isadd) {
													sbsqlmainsubdelete
															.append(sbsql
																	.toString());
													sbsql.delete(0,
															sbsql.length());
												}
											}
										}
									}
								}
								// 把column 和value
								sbcolum.append(key2).append(",");
								sbvalue.append("'").append(value).append("',");
							}
						}// end表示一行循环结束。

						// 去掉最后一“,”
						int len = sbcolum.length();
						if (len > 0) {
							sbcolum.delete(len - 1, len);
							len = sbvalue.length();
							sbvalue.delete(len - 1, len);
							// 拼写insert语句
							sbsqlInsert.append("insert into ")
									.append(keytablename).append("(")
									.append(sbcolum.toString())
									.append(")values(").append(sbvalue)
									.append(");");
							sbcolum.delete(0, sbcolum.length());
							sbvalue.delete(0, sbvalue.length());
							if (sbsqlupdate.length() > 0) {
								listsql.add(sbsqlupdate.toString());
								sbsqlupdate.delete(0, sbsqlupdate.length());
							}
							if (sbsqlmainsubdelete.length() > 0) {
								listsql.add(sbsqlmainsubdelete.toString());
								sbsqlmainsubdelete.delete(0,
										sbsqlmainsubdelete.length());
							}
							if (sbsqlInsert.length() > 0) {
								listsql.add(sbsqlInsert.toString());
								sbsqlInsert.delete(0, sbsqlInsert.length());
							}
							// 表示主键部位空，而且没有下一个数据时，添加
							if (!StringUtils.isEmpty(primaryValue)) {
								if (sbzysqid.length() > 0) {
									primaryValue = primaryValue + "@"
											+ sbzysqid.toString();
								}
								rethash.put(primaryValue, listsql);
							}
						}
					}
				} else {
					// 现在这种情况不存在
				}
			}
			// getHashSql("", jsonObject, hashRelation, sqllist, hasmap, null,
			// haveMain);
		} catch (JSONException e) {
			logger.error("解析JSon报错;", e);
		}
		return rethash;
	}

	/**
	 * getSubListSql:(拼写子表sql语句). <br/>
	 * date: 2014年9月28日 <br/>
	 * 
	 * @author lxf
	 * @param jsonTableName
	 * @param jsonObject
	 * @param hashRelation
	 * @param listsql
	 * @throws HDException
	 */
	private void getSubListSql(String jsonTableName, JSONObject jsonObject,
			HashMap<String, TableDesc> hashRelation, List<String> listsql,
			HashMap<String, String> ishave, StringBuilder zypid)
			throws HDException {
		// 最外层循环放在外边，便于设置参数，子表和孙子表用递归
		@SuppressWarnings("unchecked")
		Iterator<String> iter = jsonObject.keys();
		if (null == ishave) {
			ishave = new HashMap<String, String>();
		}
		StringBuilder sbsqlInsert = new StringBuilder();
		StringBuilder sbsqldelete = new StringBuilder();
		StringBuilder sbcolum = new StringBuilder();
		StringBuilder sbvalue = new StringBuilder();
		String primaryValue = null;
		try {
			while (iter.hasNext()) {
				String key = iter.next();
				// 取出值来
				JSONArray jsonArray = null;
				JSON_TYPE jsonType = JSON_TYPE.JSON_TYPE_ERROR;
				try {
					// 报错表示不是数组
					jsonArray = jsonObject.getJSONArray(key);
					jsonType = JSON_TYPE.JSON_TYPE_ARRAY;
				} catch (Exception e) {

				}
				if (jsonType == JSON_TYPE.JSON_TYPE_ARRAY) {
					// 表示取子表值
					for (int j = 0; j < jsonArray.length(); j++) {
						// 表示是表明
						getSubListSql(key.toLowerCase(),
								jsonArray.getJSONObject(j), hashRelation,
								listsql, ishave, zypid);
					}
				} else {
					String value = jsonObject.getString(key);
					// 表示拼写更新语句sql语句
					if (!StringUtils.isEmpty(jsonTableName)
							&& null != hashRelation) {
						TableDesc tb = hashRelation.get(jsonTableName);
						if (null != tb) {
							// 表示当前处理表示主键
							if (key.equalsIgnoreCase(tb.getPrimarykey())
									&& StringUtils.isEmpty(primaryValue)) {
								primaryValue = value;
							}
							//
							if (jsonTableName.equalsIgnoreCase("ud_zyxk_zysq")) {
								if (key.equalsIgnoreCase(tb.getPrimarykey())) {
									// 删除主表信息
									sbsqldelete.append("delete from  ")
											.append(jsonTableName)
											.append("  where ")
											.append(tb.getPrimarykey())
											.append("='").append(value)
											.append("';");
								}
							} else {
								if (!StringUtils.isEmpty(tb.getForeignkey())
										&& !StringUtils.isEmpty(tb
												.getForeignMainTable())) {
									// 当前处理表存在子表时：（子表删除记录只出现一条）
									if (!ishave.containsKey(jsonTableName
											+ value)
											&& key.equalsIgnoreCase(tb
													.getForeignkey())) {
										ishave.put(jsonTableName + value, "1");
										sbsqldelete.append("delete from ")
												.append(jsonTableName)
												.append(" where ")
												.append(tb.getForeignkey())
												.append("='").append(value)
												.append("';");
									}
								} else {
									// 此方法执行的时子表的方法,所以都存在父表，此else不执行
								}
							}
						}
					}
					// 把column 和value
					sbcolum.append(key).append(",");
					sbvalue.append("'").append(value).append("',");
				}
			}

			// 去掉最后一“,”
			int len = sbcolum.length();
			if (len > 0) {
				sbcolum.delete(len - 1, len);
				len = sbvalue.length();
				sbvalue.delete(len - 1, len);
				// 拼写insert语句
				sbsqlInsert.append("insert into ").append(jsonTableName)
						.append("(").append(sbcolum.toString())
						.append(")values(").append(sbvalue).append(");");
				if (sbsqldelete.length() > 0) {
					listsql.add(sbsqldelete.toString());
					sbsqldelete.delete(0, sbsqldelete.length());
				}
				if (sbsqlInsert.length() > 0) {
					listsql.add(sbsqlInsert.toString());
					sbsqlInsert.delete(0, sbsqlInsert.length());
				}
				if (jsonTableName.equalsIgnoreCase("ud_zyxk_zysq")
						&& !StringUtils.isEmpty(primaryValue)) {
					zypid.append("'").append(primaryValue).append("',");
				}
			}

		} catch (JSONException e) {
			logger.error(e.getMessage(), e);
			throw new HDException("解析JSon报错;");
		}
	}

	/**
	 * TODO 创建Json串
	 * 
	 * @see com.hd.hse.business.util.AbstractDataAdapter#create(HashMap,
	 *      List)
	 */
	@Override
	public String create(HashMap<String, List<Map<String, Object>>> dataHm,
			List<TableDesc> relation) throws JSONException {
		String jsonStr;
		// HashMap<表明,HashMap<表明+位移值,HashMap<父表+外键，row>>> 格式
		HashMap<String, HashMap<String, HashMap<String, JSONArray>>> hmJson = new HashMap<String, HashMap<String, HashMap<String, JSONArray>>>();

		JSONObject jsonTable = new JSONObject();
		// 记录行
		// /** 规则 :如果是顶层主表，不做特殊化处理，否则特殊化处理*/
		Iterator<Entry<String, List<Map<String, Object>>>> iterTable = dataHm
				.entrySet().iterator();
		while (iterTable.hasNext()) {
			Entry<String, List<Map<String, Object>>> eTable = iterTable.next();
			// 表名
			String tableName = eTable.getKey();
			TableDesc tabledesc = null;
			if (relation != null) {
				tabledesc = getCurrentTable(relation, tableName);
			}
			// 表数据
			try {
				createParentJson(hmJson, eTable, tabledesc);
			} catch (JSONException e) {
				throw e;
			}
		}
		// 从子表到主表循环遍历，组合JSON对象
		parseJson(hmJson, jsonTable, relation);

		jsonStr = jsonTable.toString();

		return jsonStr;

	}

	/**
	 * parseJson:(组织Json). <br/>
	 * date: 2014年9月4日 <br/>
	 * 
	 * @author lxf
	 * @param hmJson
	 * @param jsonTable
	 * @param relation
	 * @throws JSONException
	 */
	private void parseJson(
			HashMap<String, HashMap<String, HashMap<String, JSONArray>>> hmJson,
			JSONObject jsonTable, List<TableDesc> relation)
			throws JSONException {
		/** 记录行 */
		JSONArray jsonRow = new JSONArray();
		for (int i = 0; i < relation.size(); i++) {
			String tableName = relation.get(i).getTableName();
			String parenttableName = relation.get(i).getForeignMainTable();
			/** 在最终的集合中有该表的数据，找到该表关联的主表的id，并根据id找到关联主表的集合，作为属性插入主表集合 */
			if (null != parenttableName && hmJson.containsKey(tableName)
					&& !StringUtils.isEmpty(parenttableName)) {
				// 根据表明获取表下边的所有数据，此时key：表明+主键
				HashMap<String, HashMap<String, JSONArray>> hmTrPrimary = hmJson
						.get(tableName);

				Iterator<Entry<String, HashMap<String, JSONArray>>> iterTrPrimary = hmTrPrimary
						.entrySet().iterator();
				JSONArray currentAllArray = null;
				boolean isRemove = false;
				// 此处使用循环,循环表里的所有行数据
				while (iterTrPrimary.hasNext()) {
					// 此时Key:（父表+外键） 索引值的JSONArray
					HashMap<String, JSONArray> hmTr = iterTrPrimary.next()
							.getValue();
					Iterator<Entry<String, JSONArray>> iterTr = hmTr.entrySet()
							.iterator();
					while (iterTr.hasNext()) {
						Entry<String, JSONArray> entryTr = iterTr.next();
						String keyTr = entryTr.getKey();// keyTr:父表+外键
						if (hmJson.containsKey(parenttableName))// 关联的主表记录是否存在
						{ // 查找主表的记录主键索引的HashMap
							HashMap<String, HashMap<String, JSONArray>> hmParentPrimary = hmJson
									.get(parenttableName); // 找到外键值的JSONArray
							HashMap<String, JSONArray> hmParent = hmParentPrimary
									.get(keyTr);
							Iterator<Entry<String, JSONArray>> iterParentForign = hmParent
									.entrySet().iterator();

							JSONArray objParent = iterParentForign.next()
									.getValue();
							if (objParent == null)
								continue;
							// 表示判断是否已经添加过
							Object attributeObj = null;
							try {
								// ((Map)objParent.getJSONObject(0)).containsKey(tableName);

								attributeObj = objParent.getJSONObject(0).get(
										tableName);
							} catch (Exception e) {
							}
							if (attributeObj != null) {
								objParent.getJSONObject(0)
										.getJSONArray(tableName)
										.put(entryTr.getValue().get(0));
							} else {
								objParent.getJSONObject(0).put(tableName,
										entryTr.getValue());
							}
							isRemove = true;
						}
					}
				}
				// 操作完成的清除
				if (isRemove) {
					hmJson.remove(tableName);
				}
			}
		}

		// 处理最后的JSON数据
		Iterator<Entry<String, HashMap<String, HashMap<String, JSONArray>>>> iterData = hmJson
				.entrySet().iterator();
		while (iterData.hasNext()) {
			// 每个独立的表，都有一个新的记录行对象
			jsonRow = new JSONArray();
			Entry<String, HashMap<String, HashMap<String, JSONArray>>> entry = iterData
					.next();
			String tname = entry.getKey();
			HashMap<String, HashMap<String, JSONArray>> hmRecord = entry
					.getValue();
			Iterator<Entry<String, HashMap<String, JSONArray>>> iterRecord = hmRecord
					.entrySet().iterator();
			while (iterRecord.hasNext()) {
				// 记录行对象进行数据插入
				jsonRow.put((iterRecord.next().getValue().entrySet().iterator()
						.next().getValue().get(0)));
			}
			// 每个独立的表
			jsonTable.put(tname, jsonRow);
		}
	}

	/**
	 * getCurrentTable:(获取当前表信息). <br/>
	 * date: 2014年9月4日 <br/>
	 * 
	 * @author lxf
	 * @param retlation
	 * @param tablename
	 * @return
	 */
	@SuppressWarnings("unused")
	private TableDesc getCurrentTable(List<TableDesc> retlation,
			String tablename) {
		for (TableDesc td : retlation) {
			if (td.getTableName().equalsIgnoreCase(tablename)) {
				return td;
			}
		}
		return null;
	}

	/**
	 * createParentJson:(创建每个表的jsonarray对象). <br/>
	 * date: 2014年9月4日 <br/>
	 * 
	 * @author lxf
	 * @param hmTable
	 * @param eTable
	 * @param currentTable
	 * @throws JSONException
	 */
	private void createParentJson(
			HashMap<String, HashMap<String, HashMap<String, JSONArray>>> hmTable,
			Entry<String, List<Map<String, Object>>> eTable,
			TableDesc currentTable) throws JSONException {
		if (null != currentTable) {
			String kforeign = currentTable.getForeignkey();
			String kprimary = currentTable.getPrimarykey();
			if ((kforeign == null || kforeign.length() == 0)
					&& (kprimary == null || kprimary.length() == 0)) {
				throw new JSONException("接口表主子关系配置无效");
			}
			// 表主键属性
			String[] primary = kprimary.split(",");
			// 表外键属性
			String[] foreign = StringUtils.isEmpty(kforeign) ? null : kforeign
					.split(",");
			// key：表+主键生成的JSON
			HashMap<String, HashMap<String, JSONArray>> hmRowForeign = new HashMap<String, HashMap<String, JSONArray>>();
			// 可以：父表+外键生成的JSON
			HashMap<String, JSONArray> hmRow = null;// new HashMap<String,
													// JSONArray>();
			// 记录
			JSONArray jsonRow = null;// new JSONArray();
			String rowKeyPrimary;
			String rowKeyForeign;
			// 属性
			JSONObject jsonAttrbute = null;
			// 主表名
			String TableName = currentTable.getTableName();
			// 父表名
			String parentTableName = currentTable.getForeignMainTable();
			// 表数据
			List<Map<String, Object>> tableRecords = eTable.getValue();

			for (int j = 0; j < tableRecords.size(); j++) {
				jsonRow = new JSONArray();
				hmRow = new HashMap<String, JSONArray>();
				rowKeyForeign = parentTableName;
				rowKeyPrimary = TableName;
				Map<String, Object> map = tableRecords.get(j);
				Iterator<Entry<String, Object>> iter = map.entrySet()
						.iterator();
				// 属性
				jsonAttrbute = new JSONObject();
				while (iter.hasNext()) {
					Entry<String, Object> e = iter.next();
					String attributename = e.getKey();
					String attributevalue = e.getValue() == null ? "" : e
							.getValue().toString();
					// 生成外键值字符串-[父表名][外键值]
					if (foreign != null)
						for (String colname : foreign) {
							if (colname.equalsIgnoreCase(attributename)) {
								rowKeyForeign += attributevalue;
							}
						}
					// 生成主键值字符串-[子表名][主键值]
					for (String colname : primary) {
						if (colname.equalsIgnoreCase(attributename)) {
							rowKeyPrimary += attributevalue;
						}
					}
					// 注意上传时 列明全部是小写，再次进行转化
					jsonAttrbute.put(attributename.toLowerCase(),
							attributevalue);
				}
				jsonRow.put(jsonAttrbute);

				if (StringUtils.isEmpty(parentTableName)) {
					hmRow.put("MAIN", jsonRow);
				} else {
					hmRow.put(rowKeyForeign, jsonRow);
				}
				hmRowForeign.put(rowKeyPrimary, hmRow);

			}
			// 关系确认
			hmTable.put(TableName, hmRowForeign);
		} else {

		}
	}

	/**
	 * getHashSql:(获取套票sql语句). <br/>
	 * date: 2014年9月4日 <br/>
	 * 
	 * @author lxf
	 * @param jsonTableName
	 * @param jsonObject
	 * @param hashRelation
	 * @param listsql
	 * @param rethash
	 * @throws HDException
	 */
	@SuppressWarnings("unused")
	private void getHashSql(String jsonTableName, JSONObject jsonObject,
			HashMap<String, TableDesc> hashRelation, List<String> listsql,
			HashMap<String, List<String>> rethash,
			HashMap<String, String> ishave, HashMap<String, String> hashHaveMain)
			throws HDException {
		// 最外层循环放在外边，便于设置参数，子表和孙子表用递归
		@SuppressWarnings("unchecked")
		Iterator<String> iter = jsonObject.keys();
		String primaryValue = "";
		if (null == ishave) {
			ishave = new HashMap<String, String>();
		}
		StringBuilder sbsqlupdate = new StringBuilder();
		StringBuilder sbsqlInsert = new StringBuilder();
		StringBuilder sbsqldelete = new StringBuilder();
		StringBuilder sbsqlmainsubdelete = new StringBuilder();
		StringBuilder sbcolum = new StringBuilder();
		StringBuilder sbvalue = new StringBuilder();
		// listsql=new ArrayList<String>();
		try {
			while (iter.hasNext()) {
				String key = iter.next();
				// 取出值来
				JSONArray jsonArray = null;
				JSON_TYPE jsonType = JSON_TYPE.JSON_TYPE_ERROR;
				try {
					// 报错表示不是数组
					jsonArray = jsonObject.getJSONArray(key);
					jsonType = JSON_TYPE.JSON_TYPE_ARRAY;
					// if (!iter.hasNext())
					// jsonTableName = key;// 表示此时是表明
				} catch (Exception e) {

				}
				if (jsonType == JSON_TYPE.JSON_TYPE_ARRAY) {
					// 表示数组
					for (int i = 0; i < jsonArray.length(); i++) {
						// 表示是表明
						getHashSql(key.toLowerCase(),
								jsonArray.getJSONObject(i), hashRelation,
								listsql, rethash, ishave, hashHaveMain);
					}
				} else {
					String value = jsonObject.getString(key);
					// 表示拼写更新语句sql语句
					if (!StringUtils.isEmpty(jsonTableName)
							&& null != hashRelation) {

						TableDesc tb = hashRelation.get(jsonTableName);
						if (null != tb) {
							if (!StringUtils.isEmpty(tb.getForeignkey())
									&& !StringUtils.isEmpty(tb
											.getForeignMainTable())) {
								// 表示当前处理的表示子表 目的是让（子表删除记录只出现一条）
								if (!ishave.containsKey(jsonTableName + value)
										&& key.equalsIgnoreCase(tb
												.getForeignkey())) {
									ishave.put(jsonTableName + value, "1");
									sbsqldelete.append("delete from ")
											.append(jsonTableName)
											.append(" where ")
											.append(tb.getForeignkey())
											.append("='").append(value)
											.append("';");
								}

							} else {
								if (key.equalsIgnoreCase("dr")
										&& !StringUtils.isEmpty(primaryValue)) {
									// 删除子表数据
									String subtable = getSubTableName(
											jsonTableName, hashRelation);
									if (!StringUtils.isEmpty(subtable)) {
										StringBuilder sbsql = new StringBuilder();
										sbsql.append("delete from ")
												.append(subtable)
												.append(" where ")
												.append(tb.getPrimarykey())
												.append("='")
												.append(primaryValue)
												.append("';");
										boolean isadd = true;
										if (null != listsql
												&& listsql.size() > 0) {
											for (String string : listsql) {
												if (string.contains(sbsql
														.toString())) {
													isadd = false;
													break;
												}
											}
										}
										if (isadd) {
											sbsqlmainsubdelete.append(sbsql
													.toString());
										}
									}
								}
								// 表示当前处理表示主表
								if (key.equalsIgnoreCase(tb.getPrimarykey())
										&& StringUtils.isEmpty(primaryValue)) {
									primaryValue = value;
									if (!hashHaveMain.containsKey(primaryValue)) {
										hashHaveMain.put(primaryValue, "1");
										// 表示未执行
									} else {
										// 表示执行过
										listsql = new ArrayList<String>();
									}
									// 删除主表信息
									sbsqlupdate.append("delete from  ")
											.append(jsonTableName)
											.append("  where ")
											.append(tb.getPrimarykey())
											.append("='").append(value)
											.append("';");
								}
							}
						}
					}
					// 把column 和value
					sbcolum.append(key).append(",");
					sbvalue.append("'").append(value).append("',");
				}
			}

			// 去掉最后一“,”
			int len = sbcolum.length();
			if (len > 0) {
				sbcolum.delete(len - 1, len);
				len = sbvalue.length();
				sbvalue.delete(len - 1, len);
				// 拼写insert语句
				sbsqlInsert.append("insert into ").append(jsonTableName)
						.append("(").append(sbcolum.toString())
						.append(")values(").append(sbvalue).append(");");
				if (sbsqlupdate.length() > 0) {
					listsql.add(sbsqlupdate.toString());
					sbsqlupdate.delete(0, sbsqlupdate.length());
				}
				if (sbsqlmainsubdelete.length() > 0) {
					listsql.add(sbsqlmainsubdelete.toString());
					sbsqlmainsubdelete.delete(0, sbsqlmainsubdelete.length());
				}
				if (sbsqldelete.length() > 0) {
					listsql.add(sbsqldelete.toString());
					sbsqldelete.delete(0, sbsqldelete.length());
				}

				if (sbsqlInsert.length() > 0) {
					listsql.add(sbsqlInsert.toString());
					sbsqlInsert.delete(0, sbsqlInsert.length());

				}
				if (!StringUtils.isEmpty(primaryValue)
						&& !rethash.containsKey(primaryValue)
						&& listsql.size() > 0) {
					rethash.put(primaryValue, listsql);

				}
			}

		} catch (JSONException e) {
			logger.error(e.getMessage(), e);
			throw new HDException("解析JSon报错;");
		}

	}

	/**
	 * getResJson:(把json解析成SQL语句). <br/>
	 * date: 2014年9月4日 <br/>
	 * 
	 * @author Administrator
	 * @param jsonObject
	 * @param tablename
	 * @param sqllist
	 * @param hasmap
	 * @param primaryKey
	 * @throws Exception
	 */
	private void getResJson(JSONObject jsonObject, String tablename,
			List<String> sqllist, HashMap<String, List<String>> hasmap,
			String primaryKey) throws Exception {
		try {
			Iterator iter = jsonObject.keys();
			StringBuilder columnStr = new StringBuilder();
			StringBuilder valueStr = new StringBuilder();
			StringBuilder value = new StringBuilder();
			StringBuilder getStr = new StringBuilder();
			String primaryValue = null;
			while (iter.hasNext()) {
				// 表示第一次处理
				String key = (String) iter.next();
				// 取出值来
				JSONArray jsonArray = null;
				JSON_TYPE jsonType = JSON_TYPE.JSON_TYPE_ERROR;
				try {
					jsonArray = jsonObject.getJSONArray(key);
					jsonType = JSON_TYPE.JSON_TYPE_ARRAY;
				} catch (Exception e) {

				}
				if (jsonType == JSON_TYPE.JSON_TYPE_ARRAY) {
					// 下一步是数组
					// JSONArray jsonArray = jsonObject.getJSONArray(key);
					for (int i = 0; i < jsonArray.length(); i++) {
						// 表示是表明
						getResJson(jsonArray.getJSONObject(i), key, sqllist,
								hasmap, "");
					}
				} else {
					// 判断是否存在key
					if (StringUtils.isEmpty(tablename)
							&& key.equalsIgnoreCase(primaryKey)
							&& !StringUtils.isEmpty(primaryValue)) {
						primaryValue = jsonObject.getString(key);
					}

					value.append(jsonObject.getString(key));
					// 把列明，和值分别组织起来
					if (columnStr.length() > 0) {
						columnStr.append(",");
						columnStr.append(key);
						valueStr.append(",'");
						valueStr.append(value);
						valueStr.append("'");

					} else {
						columnStr.append(key);
						valueStr.append("'");
						valueStr.append(value);
						valueStr.append("'");
					}
					value.setLength(0);
				}
			}
			// 防止最后输出空串
			if (columnStr.length() > 0) {
				getStr.append("  insert into ").append(tablename).append("(")
						.append(columnStr).append(")values(").append(valueStr)
						.append(");");

				sqllist.add(getStr.toString());
				if (StringUtils.isEmpty(tablename)
						&& !StringUtils.isEmpty(primaryValue)) {
					if (!hasmap.containsKey(primaryValue)) {
						hasmap.put(primaryValue, sqllist);
						// 表示清空数据
						sqllist.clear();
					}
				}
				columnStr.setLength(0);
				valueStr.setLength(0);
				getStr.setLength(0);
			}

		} catch (HDException e) {
			throw e;
		}
	}

	/**
	 * getSubTableName:(获取子表的表明). <br/>
	 * date: 2014年9月18日 <br/>
	 * 
	 * @author lxf
	 * @param tbname
	 * @param hashRelation
	 * @return
	 */
	private String getSubTableName(String tbname,
			HashMap<String, TableDesc> hashRelation) {

		String retvalue = null;
		if (hashRelation.containsKey(tbname)) {

			Iterator<Entry<String, TableDesc>> iter = hashRelation.entrySet()
					.iterator();
			while (iter.hasNext()) {
				Entry<String, TableDesc> entry = iter.next();
				TableDesc tb = entry.getValue();
				if (null != tb.getForeignMainTable()
						&& tb.getForeignMainTable().equalsIgnoreCase(tbname)) {
					retvalue = tb.getTableName();
					break;
				}

			}
		}

		return retvalue;
	}

	/**
	 * 用于判断JSON格式的枚举
	 * */
	public enum JSON_TYPE {
		/** JSONObject */
		JSON_TYPE_OBJECT,
		/** JSONArray */
		JSON_TYPE_ARRAY,
		/** JSONERROR */
		JSON_TYPE_ERROR,
	}

	/**
	 * 从一个JSON数组得到一个java对象集合
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 * @throws
	 */
	public List<SuperEntity> toEntityList(String jsonString,
			Class<?> entityClass) throws HDException {
		logger.error(jsonString, null);
		// setDataFormat2JAVA();
		// str =
		// "{'UD_CBSGL_CL':[{'carnum':'1294','CREATEBY':'MAXADMIN','UD_CBSGL_CLJS':[{'carnum':'1294','UD_CBSGL_CLJSID':'901','NAME':'文林'}]}]}";
		List<SuperEntity> listMainSupuer = new ArrayList<SuperEntity>();
		try {
			JSONObject jsoinobject = new JSONObject(jsonString);
			Iterator<String> iter = jsoinobject.keys();
			// 表示主表
			while (iter.hasNext()) {
				String key = iter.next();
				JSONArray jsonArray = null;
				JSON_TYPE jsonType = JSON_TYPE.JSON_TYPE_ERROR;
				try {
					// 报错表示不是数组
					jsonArray = jsoinobject.getJSONArray(key);
					jsonType = JSON_TYPE.JSON_TYPE_ARRAY;
				} catch (Exception e) {

				}
				if (jsonType == JSON_TYPE.JSON_TYPE_ARRAY) {
					// 表示数组
					int count = jsonArray.length();
					for (int i = 0; i < count; i++) {
						// key是类的表明
						JSONObject jsoinobject2 = jsonArray.getJSONObject(i);
						SuperEntity retsuperEntity = getSuperEntity(
								entityClass.getName(), key);
						getDTOsub(key, jsoinobject2, entityClass,
								retsuperEntity);
						listMainSupuer.add(retsuperEntity);
					}
				}
			}

		} catch (JSONException e) {
			logger.error("解析JSon报错;", e);
		}
		return listMainSupuer;
	}

	/**
	 * getDTOsub:(递归读取子表数据). <br/>
	 * date: 2014年9月23日 <br/>
	 * 
	 * @author lxf
	 * @param tablename
	 * @param jsonObject
	 * @param entityClass
	 * @param superEntitymain
	 * @throws HDException
	 * @throws JSONException
	 */
	private void getDTOsub(String tablename, JSONObject jsonObject,
			Class<?> entityClass, SuperEntity retSuperEntity)
			throws HDException, JSONException {
		Iterator<String> iter = jsonObject.keys();

		List<SuperEntity> listMainSupuer = new ArrayList<SuperEntity>();
		// 表示主表
		while (iter.hasNext()) {
			String key = iter.next();
			JSONArray jsonArray = null;
			JSON_TYPE jsonType = JSON_TYPE.JSON_TYPE_ERROR;
			try {
				// 报错表示不是数组
				jsonArray = jsonObject.getJSONArray(key);
				jsonType = JSON_TYPE.JSON_TYPE_ARRAY;
			} catch (Exception e) {
			}
			if (jsonType == JSON_TYPE.JSON_TYPE_ARRAY) {
				// 表示数组
				int count = jsonArray.length();
				if (count > 0) {
					// SuperEntity retSuperEntitysub =
					// getSuperEntity(entityClass.getName(), key);
					SuperEntity retSuperEntitysub = null;// getSuperEntity(entityClass.getName(),
															// key);
					listMainSupuer = new ArrayList<SuperEntity>();
					for (int i = 0; i < count; i++) {
						// key是类的表明
						JSONObject jsoinobject2;
						jsoinobject2 = jsonArray.getJSONObject(i);
						retSuperEntitysub = getSuperEntity(
								entityClass.getName(), key);
						getDTOsub(key, jsoinobject2, entityClass,
								retSuperEntitysub);
						listMainSupuer.add(retSuperEntitysub);
					}
					// 表示将子类对象添加进去
					if (null != retSuperEntity && null != retSuperEntitysub) {
						if (listMainSupuer.size() > 0)
							retSuperEntity.setChild(retSuperEntitysub
									.getClass().getName(), listMainSupuer);
					}
				}
			} else {
				Object value = jsonObject.get(key);// .getString(key);
				try {
					// 2015-07-02 由于PC端传过来的数据都是字符串类型， 本地有String和int之分 添加特殊处理
					// if (isNumeric(value.toString())) {
					// // 表示不是小数
					// if (retSuperEntity.getAttribute(key) instanceof integer)
					// {
					// int tempv = (int) value;
					// retSuperEntity.setAttribute(key, tempv);
					// } else {
					// retSuperEntity.setAttribute(key, value);
					// }
					//
					// } else {
					retSuperEntity.setAttribute(key, value);
					// }
				} catch (Exception e) {
					try {
						if (NumberValidationUtils.isWholeNumber(value
								.toString())) {
							try {
								int tempv = Integer.parseInt(value.toString());
								retSuperEntity.setAttribute(key, tempv);
							} catch (Exception ee) {
								Float tempf = new Float(value.toString());
								retSuperEntity.setAttribute(key, tempf);
							}
						} else if (NumberValidationUtils.isDecimal(value
								.toString())) {
							Float tempf = new Float(value.toString());
							retSuperEntity.setAttribute(key, tempf);
						} else {
							logger.error("解析JSON设置属性" + key + "出错.", e);
						}
					} catch (Exception ee) {
						logger.error("解析JSON设置属性" + key + "出错.", ee);
					}

				}
				// }
				// }
			}
		}
	}

	/**
	 * getSuperEntity:(获取实体类). <br/>
	 * date: 2014年9月23日 <br/>
	 * 
	 * @author lxf
	 * @param className
	 * @param tbname
	 * @return
	 * @throws HDException
	 */
	private SuperEntity getSuperEntity(String className, String tbname)
			throws HDException {
		SuperEntity newEntity = null;
		SuperEntity retnewEntity = null;
		String tableName = null;
		try {
			newEntity = newInstance(className);
			tableName = newEntity.getDBTableName();
			if (StringUtils.isEmpty(tableName)) {
				throw new HDException("实体未指定表名：" + className);
			}
			if (tbname.equalsIgnoreCase(tableName)) {
				return newEntity;
			}
			String[] childClasses = newEntity.getChildClasses();
			if (null != childClasses) {
				for (String childname : childClasses) {
					retnewEntity = getSuperEntity(childname, tbname);
					if (null != retnewEntity) {
						break;
					}
				}
			}
			return retnewEntity;
		} catch (SQLException e) {
			throw new HDException(e.getMessage(), e);
		}
	}

	/**
	 * newInstance:(实例化实体类). <br/>
	 * date: 2014年9月23日 <br/>
	 * 
	 * @author lxf
	 * @param className
	 * @return
	 * @throws SQLException
	 */
	private SuperEntity newInstance(String className) throws SQLException {
		try {
			SuperEntity newEntity = (SuperEntity) Class.forName(className)
					.newInstance();
			newEntity.setAdd(false);
			return newEntity;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new SQLException(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new SQLException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new SQLException(e);
		}
	}

	// /-------------------------GSON 解析

	/**
	 * objectToJson:(将对象解析成JSON). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param ts
	 * @param dateformat
	 *            转换日期格式
	 * @return
	 */
	@Override
	public String objectToJson(Object ts, String... dateformat) {
		String json = null;
		if (dateformat == null) {
			json = GsonUtil.objectToJson(ts);
		} else {
			json = GsonUtil.objectToJsonDateSerializer(ts, dateformat[0]);
		}
		return json;
	}

	/**
	 * jsonToList:(将字符串解析成对象集合). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param entity
	 * @param jsonStr
	 * @return
	 */
	@Override
	public List<SuperEntity> jsonToList(Class<?> entity, String jsonStr) {
		try {
			return GsonUtil.jsonToList((SuperEntity) entity.newInstance(),
					jsonStr);
		} catch (HDException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return null;
	}

}
