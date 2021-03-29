package com.hd.hse.main.phone.ui.receiver;

import java.util.List;

import com.hd.hse.main.phone.ui.service.NotificationService;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceiverScreenOn extends BroadcastReceiver{
	public static Context context;
	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		return;
//		if(Intent.ACTION_USER_PRESENT.equals(intent.getAction()) && !serverRunning()){
//			Intent intent2 = new Intent(context,NotificationService.class);
//			intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startService(intent2);
//		}
		
	}
	/**
	 * 判断服务是否已启动
	 * @return
	 */
	private boolean serverRunning(){
		boolean isRunning = false;
		ActivityManager aManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceInfos = aManager.getRunningServices(50);
		if(serviceInfos.size()<1){
			return false;
		}
		for(int i=0;i<serviceInfos.size();i++){
			if(NotificationService.class.getName().equals(serviceInfos.get(i).service.getClassName())){
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}
}
