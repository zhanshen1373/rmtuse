package com.hd.hse.dc.business.common.result;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.dc.business.common.util.AbstractDataAdapter;

public class GsonEntityListSpecialDealWebResult extends BaseWebResult {
	private Class<?> clazz;
	private AbstractDataAdapter adapter;
	private static Logger logger = LogUtils.getLogger(GsonEntityListSpecialDealWebResult.class);
	public GsonEntityListSpecialDealWebResult(Class<?> clazz,AbstractDataAdapter adapter) {
		this.clazz = clazz;
		this.adapter=adapter;
	}

	@Override
	public Object changeResultType(Object rs) throws HDException {
		// TODO Auto-generated method stub
		if(rs.toString().equalsIgnoreCase("{}"))
		{
			return new ArrayList<SuperEntity>();
		}
		if(rs.toString().equalsIgnoreCase("[]"))
		{
			return new ArrayList<SuperEntity>();
		}
		if(adapter==null){
		 throw new HDException("请传入解析器对象");	
		}
		StringBuilder sb=new StringBuilder();
		sb.append(rs.toString());
		//进行特殊处理json串
		try {
		    Object obj=newInstance(clazz);
		    if(obj instanceof SuperEntity){
		    	SuperEntity sup= (SuperEntity) obj;
		    	String replaceStr="{"+"\""+sup.getDBTableName().toUpperCase()+"\":";
		    	//String replaceStr2="{'"+sup.getDBTableName().toUpperCase()+"':[";
		    	sb.delete(0,replaceStr.length());
		    	sb.delete(sb.length()-1, sb.length());
		    }
		} catch (Exception e) {
			logger.equals(e);
			 throw new HDException("处理JSON串报错!");
		}
		
		return  adapter.jsonToList(clazz, sb.toString());
	}


}
