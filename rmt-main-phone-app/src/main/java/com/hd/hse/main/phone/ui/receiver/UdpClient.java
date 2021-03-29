package com.hd.hse.main.phone.ui.receiver;

import android.content.Context;

import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.entity.common.PushMessage;
import com.hd.hse.entity.common.ReMindBean;
import com.hd.hse.main.phone.ui.service.Util;

import org.apache.log4j.Logger;
import org.ddpush.im.v1.client.appuser.Message;
import org.ddpush.im.v1.client.appuser.TCPClientBase;

import java.util.ArrayList;
import java.util.List;

/*
 编写接收客户端需要继承 UDPClientBase 或 TCPClientBase 
 实现三个方法，然后在主线程中调用该类的 start 方法，
 此类会启动两个守护线程来与服务器保持长期连接，进程必须
 有一个自己的主线程，该线程不能是守护线程，因为如果一个
 进程中的线程全部是守护线程的话，jvm 会杀死该进程。
 */
public class UdpClient extends TCPClientBase {
	private static Logger logger = LogUtils.getLogger(UdpClient.class);
	public static List<PushMessage> taskListDatas = new ArrayList<PushMessage>();
	public static String MSGTYPE01 = "TZ01";
	public static String MSGTYPE02 = "CZ01";
	
	private Context context;

	public UdpClient(byte[] uuid, int appid, String serverAddr, int serverPort)
			throws Exception {
		super(uuid, appid, serverAddr, serverPort,10);
	}

	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public boolean hasNetworkConnection() {

		return true;
	}

	@Override
	public void onPushMessage(Message message) {
		if (message.getCmd() == 32 && context != null) {// 0x20 自定义推送信息
			String str = null;
			try {
				try{
					str = new String(message.getData(),5,message.getContentLength(), "UTF-8");
				}catch(Exception e){
					logger.error(message);
					ToastUtils.toast(context, "赋值消息内容报错");
					str = Util.convert(message.getData(),5,message.getContentLength());
				}
				PushMessage pushMessage	=messageChange(str);
				if (pushMessage != null) {
					new HDNotification(context, pushMessage);
				}
			} catch (Exception e) {
				ToastUtils.toast(context, "转化消息报错");
				logger.error(message);
			}
		}

	}

	@Override
	public void trySystemSleep() {
		// TODO Auto-generated method stub

	}

//	/**
//	 * 去除缓存中与服务端重复的消息（若不重复则加入缓存list）
//	 * 
//	 * @param list
//	 */
//	private void removeDouble(PushMessage msg, List<PushMessage> list) {
//		boolean isdouble = false;
//		if (msg != null && list != null) {
//			for (PushMessage pMessage : list) {
//				if (pMessage.getUd_zyxk_xxjlid()
//						.equals(msg.getUd_zyxk_xxjlid())) {
//					isdouble = true;
//					break;
//				}
//			}
//		}
//		if (list != null && !isdouble) {
//			list.add(0, msg);
//		}
//	}

	/**
	 * 消息内容转换至实体中
	 */
	private PushMessage messageChange(String str) {
		String[] strMsg = str.split("#");
		 PushMessage pushMessage = new PushMessage();
		if (strMsg.length > 5) {
			pushMessage.setUd_zyxk_xxjlid(strMsg[0]);
			pushMessage.setUd_zyxk_zysqid(strMsg[1]);
			pushMessage.setXxlx(strMsg[2]);
			pushMessage.setGnbm(strMsg[3]);
			pushMessage.setFssj(strMsg[4]);
			pushMessage.setXxnr(strMsg[5]);
		}
		else{
			ToastUtils.toast(context, "消息格式不正确！");
		}
		return pushMessage;
	}

}