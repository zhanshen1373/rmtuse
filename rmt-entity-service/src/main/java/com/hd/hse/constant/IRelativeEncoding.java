/**
 * Project Name:hse-entity-service
 * File Name:IRelativeEncoding.java
 * Package Name:com.hd.hse.constant
 * Date:2014年11月25日
 * Copyright (c) 2014, zhaofeng@ushayden.com All Rights Reserved.
 *
 */
package com.hd.hse.constant;

/**
 * ClassName: IRelativeEncoding ()<br/>
 * date: 2014年11月25日  <br/>
 *
 * @author zhaofeng
 * @version 
 */
public interface IRelativeEncoding {

	
	/*
	 * 记录所有关系配置信息中的编码
	 */
	
	/**
	 * BIGANDSPECIAL:TODO(大小票关系).
	 */
	public static final String BIGANDSPECIAL="BIGANDSPECIAL";
	/**
	 * DHYD:TODO(动火临时用电关系).
	 */
	public static final String DHYD="DHYD";
	/**
	 * QTJCFY:TODO(气体检测复用关系).
	 */
	public static final String QTJCFY="QTJCFY";
	/**
	 * CBSSK:TODO(承包商刷卡权限不受单位限制).
	 */
	public static final String CBSSK="CBSSK";
	/**
	 * ISASYSCPCQT:TODO(增加同步PC端气体模块).
	 */
	public static final String ISASYSCPCQT="ISASYSCPCQT";
	/**
	 * HCSJPZ:TODO(缓冲时间配置).
	 */
	public static final String HCSJPZ="HCSJPZ";
	/**
	 * PBQXGN:TODO(屏蔽取消功能).
	 */
	public static final String PBQXGN="PBQXGN";
	/**
	 * PBQTJCGN:TODO(屏蔽气体检测功能).
	 */
	public static final String PBQTJCGN="PBQTJCGN";
	/**
	 * SXPCJY:TODO(进行时效频次校验).
	 */
	public static final String SXPCJY="SXPCJY";
	/**
	 * JJBJHRBG:TODO(是否有交接班监护人变更).
	 */
	public static final String JJBJHRBG="JJBJHRBG";
	/**
	 * JJBZYRBG:TODO(是否有作业人变更).
	 */
	public static final String JJBZYRBG="JJBZYRBG";
	/**
	 * PROMPTQT:TODO(关闭时，是否提示气体检测的信息).
	 */
	public static final String PROMPTQT = "PROMPTQT";
	
	/**
	 * PROMPTQT:TODO(是否支持危害都不确认).
	 */
	public static final String ISWHNOSURE = "ISWHNOSURE";
	/**
	 * PROMPTQT:TODO(是否支持个人防护都不确认).
	 */
	public static final String ISPPENOSURE = "ISPPENOSURE";
	
	/**
	 * PCCSNOAPPLY:TODO(PC勾选措施不可选不适用).
	 */
	public static final String PCCSNOAPPLY="PCCSNOAPPLY";
	
	/**
	 * ISDHYD:TODO(动作与电的关系).
	 */
	public static final String ISDHYD="DHYD";
	/**
	 * SHOWMODELSTR:TODO(表示是否进行模块显示的过滤关系).
	 */
	public static final String SHOWMODELSTR = "SHOWMODELSTR";
	
	/**
	 * REUSINGLOCATIONCARD:TODO(是否重用位置).
	 */
	public static final String REUSINGLOCATIONCARD = "REUSINGLOCATIONCARD";
	
	/**
	 * UPDATAHANDLE:TODO(删除上传完后的所有数据).
	 */
	public static final String UPDATAHANDLE="UPDATAHANDLE";

}
