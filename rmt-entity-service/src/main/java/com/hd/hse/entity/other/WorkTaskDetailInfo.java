package com.hd.hse.entity.other;

import com.hd.hse.common.entity.SuperEntity;

/**
 * ClassName: WorkTaskDetailInfo (任务详细显示)<br/>
 * date: 2015年5月28日  <br/>
 *
 * @author lxf
 * @version 
 */
@SuppressWarnings("serial")
public class WorkTaskDetailInfo extends SuperEntity {

	private String  descrption;
	private String  desValue;
	private boolean istitle=false;
	
	
	/**
	 * getDescrption:(获取描述). <br/>
	 * date: 2015年5月28日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public String getDescrption() {
		return descrption;
	}
	/**
	 * setDescrption:(设置描述). <br/>
	 * date: 2015年5月28日 <br/>
	 *
	 * @author lxf
	 * @param descrption
	 */
	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
	/**
	 * getDesValue:(获取描述值). <br/>
	 * date: 2015年5月28日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public String getDesValue() {
		return desValue;
	}
	/**
	 * setDesValue:(设置描述值). <br/>
	 * date: 2015年5月28日 <br/>
	 *
	 * @author lxf
	 * @param desValue
	 */
	public void setDesValue(String desValue) {
		this.desValue = desValue;
	}
	/**
	 * isIstitle:(获取是否标题). <br/>
	 * date: 2015年6月5日 <br/>
	 *
	 * @author lxf
	 * @return
	 */
	public boolean isIstitle() {
		return istitle;
	}
	/**
	 * setIstitle:(设置是否标题). <br/>
	 * date: 2015年6月5日 <br/>
	 *
	 * @author lxf
	 * @param istitle
	 */
	public void setIstitle(boolean istitle) {
		this.istitle = istitle;
	}
	
}
