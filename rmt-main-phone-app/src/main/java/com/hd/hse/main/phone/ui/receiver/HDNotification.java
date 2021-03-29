package com.hd.hse.main.phone.ui.receiver;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.hd.hse.entity.common.PushMessage;
import com.hd.hse.main.phone.R;
import com.hd.hse.main.phone.ui.activity.login.LoginActivity;
import com.hd.hse.main.phone.ui.activity.main.MainActivity;
import com.hd.hse.main.phone.ui.activity.main.MsgListActivity;
import com.hd.hse.main.phone.ui.activity.welcome.StartWelcomeActivity;

/**
 * @num消息的id
 * @author zl
 * 
 */
public class HDNotification {
	private static NotificationManager manager;
	private Notification notification;

	@SuppressLint("NewApi")
	public HDNotification(Context context, PushMessage msg) {
		if (manager == null) {
			manager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
		}
		String msgtitle;
		boolean isTZ=false;
		if (UdpClient.MSGTYPE01.equals(msg.getXxlx())) {
			msgtitle = "通知类";
			isTZ=true;
		} else {
			msgtitle = "操作类";
		}
		Intent intent;
		if(isTZ){
			 intent = new Intent(context, MsgListActivity.class);
			// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 关键的一步，设置启动模式 与manifset 文件设置 一样，而且本地设置 会覆盖manifest文件设置
			// intent.setAction(LoginActivity.ACTION_TRIP_APPROVE);
			 intent.putExtra("MSG","1");
		}
		else{
			 //清除
			 intent = new Intent(context, MainActivity.class);
		}
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);;
		notification = new Notification.Builder(context)
				.setSmallIcon(R.drawable.hd_hse_main_message_logo)
				.setTicker(msgtitle).setContentTitle(msgtitle)
				.setContentText(msg.getXxnr()).setContentIntent(pendingIntent)
				.setNumber(1).build();
		// 点击后自动消失
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		// 发动通知,id由自己指定，每一个Notification对应的唯一标志
		manager.notify(Integer.parseInt(msg.getUd_zyxk_xxjlid()), notification);
	}

	/**
	 * ClearNotifiaction:(退出应用程序时清除消息). <br/>
	 * date: 2015年7月23日 <br/>
	 * 
	 * @author lxf
	 */
	public static void ClearNotifiaction() {
		if (manager == null) {
			return;
		}
		manager.cancelAll();
	}
}
