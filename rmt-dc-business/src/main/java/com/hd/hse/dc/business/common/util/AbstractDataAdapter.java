package com.hd.hse.dc.business.common.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.hd.hse.business.util.TableDesc;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;

/**
 * ClassName:AbstractBaseOPData (创建解析字符串).<br/>
 * Date: 2014年8月21日 <br/>
 * 
 * @author lxf
 * @version
 * @see
 **/
public abstract class AbstractDataAdapter {

	public abstract HashMap<String, List<String>> resSql(String str,
			String primarykey) throws HDException;

	/**
	 * ResInsert:(解析成Sql语句) Data: 2014年8月11日 <br/>
	 * 
	 * @author lxf
	 * @param
	 * @return List
	 * @throws HDException
	 * */
	public abstract HashMap<String, List<String>> resSql(String str,
			HashMap<String, TableDesc> hashmap) throws HDException;

	/**
	 * ResInsert:(解析成Object) Data: 2014年8月11日 <br/>
	 * 
	 * @author lxf
	 * @param
	 * @return object
	 * @throws HDException
	 * */
	public abstract List<SuperEntity> toEntityList(String data,
			Class<?> entityClass) throws HDException;

	/**
	 * ResInsert:(解析成Object) Data: 2014年8月11日 <br/>
	 * 
	 * @author lxf
	 * @param
	 * @return Json字符串
	 * @throws JSONException
	 * */
	public abstract String create(
			HashMap<String, List<Map<String, Object>>> dataHm,
			List<TableDesc> relation) throws JSONException;

	/**
	 * entityToJson:(将对象转行成GSON串). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public String objectToJson(Object ts, String... dateformat) {
		return null;
	};
	/**
	 * jsonToList:(将字符串解析成对象集合). <br/>
	 * date: 2015年6月1日 <br/>
	 * 
	 * @author lxf
	 * @param entity
	 * @param jsonStr
	 * @return
	 */
	public List<SuperEntity> jsonToList(Class<?> entity,
			String jsonStr) {
		// TODO Auto-generated method stub
		return null;
	};

}
