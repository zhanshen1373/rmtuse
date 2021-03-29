package com.hd.hse.padinterface;

/**
 * ClassName: PadInterfaceRequest (交互请求参数编码)<br/>
 * date: 2014年9月16日  <br/>
 *
 * @author lxf
 * @version 
 */
public class PadInterfaceRequest {
	/**
	 * PERSONID:TODO(用户编码).
	 */
	public final static String KEYPERSONID="personid";
	
	/**
	 * PERSONID:TODO(登录用户描述).
	 */
	public final static String KEYPERSONDESC="persondesc";
	
	/**
	 * KEYLOGINID:TODO(登录编码).
	 */
	public final static String KEYLOGINID="loginid";
	
	/**
	 * DEPTNUM:TODO(登录用户部门).
	 */
	public final static String KEYDEPTNUM="department";
	
	/**
	 * DEPTNUM:TODO(登录用户部门).
	 */
	public final static String KEYDEPTDESC="department_desc";
	
	/**
	 * TABLE:TODO(用户记录表明+时间戳的Key).
	 */
	public final static String KEYTABLE="table";
	
	/**
	 * KEYDATA:TODO(上传内容的key).
	 */
	public final static String KEYDATA="data";
	
	/**
	 * PADVERSION:TODO(版本号).
	 */
	public final static String KEYPADVERSION="padversion";
	/**
	 * PADAPKURL:TODO(升级地址).
	 */
	public final static String KEYPADAPKURL="padapkurl";
	
	/**
	 * KEYFILENAME:TODO(上传文件名称编码).
	 */
	public final static String KEYFILENAME="filename";
	/**
	 * KEYAPPNAME:TODO(承包商下载照片时,appname).
	 */
	public final static String KEYAPPNAME="appname";
	
	/**
	 * KEYTABLIEID:TODO(表唯一id).
	 */
	public final static String KEYTABLIEID="uniqueid";
	
	/**
	 * KEYRETURNID:TODO(pc端返回信息用到的主键).
	 */
	public final static String KEYRETURNID="id";
	
	/**
	 * KEYRETURNMESSAGE:TODO(pc端返回信息用到的msg).
	 */
	public final static String KEYRETURNMESSAGE="mesg";
	
	/**
	 * KEYZYPSTRID:TODO(作业票ID字符串).
	 */
	public final static String KEYZYPSTRID="zypstr";
	
	/**
	 * KEYNEWSID:TODO(消息ID).
	 */
	public final static String KEYNEWSID="newsid";
	
	/**
	 * KEYWHERE:TODO(条件).
	 */
	public final static String KEYWHERE="where";
	
	/**
	 * KEYMAC:TODO(硬件MAC地址).
	 */
	public final static String KEYMAC="pdamac";
	
	/**
	 * KEYZYPSTRID:TODO(作业票ID字符串).
	 */
	public final static String KEYEXCLUDEZYPSTRID="excludezypstr";
	/**
	 * KEYCLUDEZYPCLASSSTR:TODO(作业票类型串).
	 */
	public final static String KEYCLUDEZYPCLASSSTR="keycludezypclassstr";
	

	/**
	 * KEYPASSWORD:TODO(人员卡人员密码).
	 */
	public final static String KEYPASSWORD="password";
	
	
	/**
	 * KEYPASSWORD:TODO(承包商人员入厂证号).
	 */
	public final static String KEYINNERCARD="innercard";
	/**
	 * KEYPASSWORD:TODO(承包商人员身份证号).
	 */
	public final static String KEYICCARD="iccard";
	
	/**
	 * KEYCREATETIME:TODO(创建时间key).
	 */
	public final static String KEYCREATETIME="createtime";
	
	/**
	 * KEYFUNCODE:TODO(功能编码key).
	 */
	public final static String KEYFUNCODE="funcode";
	
	/**
	 * KEYUPLOADFILE:TODO(上传文件).
	 */
	public final static String KEYUPLOADFILE="uploadfile";
	
	/**
	 * KEYMODULECODE:TODO(模块功能编码).
	 */
	public final static String KEYMODULECODE="modulecode";
	
	/**
	 * KEYNEWSSERVERURL:TODO(消息服务的URLKEY).
	 */
	public final static String KEYNEWSSERVERURL="newsserverurl";
	
	/**
	 * KEYUUID:TODO(全球唯一ID).
	 */
	public final static String KEYUUID="uuid";
}
