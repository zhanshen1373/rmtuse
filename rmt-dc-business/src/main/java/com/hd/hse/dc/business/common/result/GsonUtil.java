package com.hd.hse.dc.business.common.result;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.dc.business.common.util.TypeUtil;
import com.hd.hse.entity.base.PDAWorkOrderInfoConfig;

/**
 * ClassName: GsonUtil (gson工具类)<br/>
 * date: 2015年6月1日 <br/>
 * 
 * @author lxf
 * @version
 */
public class GsonUtil {
	private static Gson gson = null;

	static {
		gson = new Gson();
	}

	private GsonUtil() {
	}

	/**
	 * objectToJson:(将对象转换成json格式). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param ts
	 * @return
	 */
	public static String objectToJson(Object ts) {
		String jsonStr = null;
		if (gson != null) {
			jsonStr = gson.toJson(ts);
		}
		return jsonStr;
	}

	public static String objctToCustomJson(Object ts) {
		String jsonStr = null;
		if (gson != null) {
			jsonStr = gson.toJson(ts);
		}
		if (ts instanceof SuperEntity) {
			SuperEntity superentity = (SuperEntity) ts;
			String tablename = superentity.getDBTableName();
			jsonStr = "{\"" + tablename + "\":[" + jsonStr + "]}";
		}
		return jsonStr;
	}

	/**
	 * objectToJsonDateSerializer:(将对象转换成json格式(并自定义日期格式)). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param ts
	 * @param dateformat
	 * @return
	 */
	public static String objectToJsonDateSerializer(Object ts,
			final String dateformat) {
		String jsonStr = null;
		gson = new GsonBuilder()
				.registerTypeHierarchyAdapter(Date.class,
						new JsonSerializer<Date>() {
							@Override
							public JsonElement serialize(Date src,
									Type typeOfSrc,
									JsonSerializationContext context) {
								SimpleDateFormat format = new SimpleDateFormat(
										dateformat);
								return new JsonPrimitive(format.format(src));
							}
						}).setDateFormat(dateformat).create();
		if (gson != null) {
			jsonStr = gson.toJson(ts);
		}
		return jsonStr;
	}

	/**
	 * jsonToList:(将json格式转换成list对象 ). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param <T>
	 * @param jsonStr
	 * @return
	 * @throws HDException 
	 */
	public static List<SuperEntity> jsonToList(SuperEntity object, String jsonStr) throws HDException {
		List<SuperEntity> objList = null;
		if (gson != null) {
			Type type = TypeUtil.getBusinessType(object);
			objList =gson.fromJson(jsonStr, type);
		}
		return objList;
	}

	/**
	 * jsonToMap:(将json格式转换成map对象). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param jsonStr
	 * @return
	 */
	public static Map<?, ?> jsonToMap(String jsonStr) {
		Map<?, ?> objMap = null;
		if (gson != null) {
			Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
			}.getType();
			objMap = gson.fromJson(jsonStr, type);
		}
		return objMap;
	}

	/**
	 * jsonToBean:(将json转换成bean对象). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param jsonStr
	 * @param cl
	 * @return
	 */
	public static Object jsonToBean(String jsonStr, Class<?> cl) {
		Object obj = null;
		if (gson != null) {
			obj = gson.fromJson(jsonStr, cl);
		}
		return obj;
	}

	/**
	 * jsonToBeanDateSerializer:(将json转换成带日期格式的bean对象). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param jsonStr
	 * @param cl
	 * @param pattern
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBeanDateSerializer(String jsonStr, Class<T> cl,
			final String pattern) {
		Object obj = null;
		gson = new GsonBuilder()
				.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

					@Override
					public Date deserialize(JsonElement json, Type typeOfT,
							JsonDeserializationContext context)
							throws JsonParseException {
						SimpleDateFormat format = new SimpleDateFormat(pattern);
						String dateStr = json.getAsString();
						try {
							return format.parse(dateStr);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						return null;
					}
				}).setDateFormat(pattern).create();
		if (gson != null) {
			obj = gson.fromJson(jsonStr, cl);
		}
		return (T) obj;
	}

	/**
	 * getJsonValue:(根绝KEY返回值). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static Object getJsonValue(String jsonStr, String key) {
		Object rulsObj = null;
		Map<?, ?> rulsMap = jsonToMap(jsonStr);
		if (rulsMap != null && rulsMap.size() > 0) {
			rulsObj = rulsMap.get(key);
		}
		return rulsObj;
	}

}
