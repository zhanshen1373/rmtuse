package com.hd.hse.utils;

public class UtilToolsMsg {

	// public static String MSG_NOZYPFOUND = "MSG_NOZYPFOUND";//没找到作业票
	// public static String MSG_NOPERMITION = "MSG_NOPERMITION";//没有操作权限
	// public static String MSG_AUDITFAILD = "MSG_AUDITFAILD";//提交工作流失败

	/**
	 * resolveMsg:(根绝编码解析显示错误信息). <br/>
	 * date: 2015年6月2日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public static String resolveMsg(String code) {

		String msg = "";
		switch (code.replace(";", "")) {
		case "MSG_NOZYPFOUND":
			msg = "没有查询到作业票信息";
			break;
		case "MSG_NOPERMITION":
			msg = "没有操作权限!";
			break;
		case "MSG_AUDITFAILD":
			msg="工作流报错";
			break;
		default:
			msg = "0";
			break;
		}

		return msg;
	}
}
