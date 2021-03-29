package com.hd.hse.dc.business.common.result;

import java.util.ArrayList;

import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.dc.business.common.util.AbstractDataAdapter;

public class EntityListWebResult extends BaseWebResult {

	private Class<?> clazz;
	private AbstractDataAdapter adapter;
	public EntityListWebResult(Class<?> clazz,AbstractDataAdapter adapter) {
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
		return  adapter.toEntityList(rs.toString(), clazz);
	}

}
