package com.hd.hse.dc.business.common.util;


import java.lang.reflect.Type;
import java.util.List;







import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.entity.base.PDAWorkOrderInfoConfig;
import com.hd.hse.entity.workorder.WorkTask;

public class TypeUtil {
	/**
	 * getBusinessType:(获取业务对象类型). <br/>
	 * date: 2015年6月17日 <br/>
	 *
	 * @author lxf
	 * @param sup
	 * @return
	 * @throws Exception 
	 */
	public static Type getBusinessType(SuperEntity sup) throws HDException{
		Type type=null;
		if(sup instanceof PDAWorkOrderInfoConfig){
			type= new TypeToken<List<PDAWorkOrderInfoConfig>>() {}.getType();
		}
		else if(sup instanceof WorkTask){
			type= new TypeToken<List<PDAWorkOrderInfoConfig>>() {}.getType();
		}
		else{
			 throw new HDException("请扩展"+sup.getClass().getName());
		}
		
		return type;
	}

}
