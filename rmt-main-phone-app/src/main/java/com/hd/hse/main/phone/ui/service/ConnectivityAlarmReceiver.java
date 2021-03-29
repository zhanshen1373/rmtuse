package com.hd.hse.main.phone.ui.service;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * ClassName: ConnectivityAlarmReceiver (当网路发生变化时是执行的代码)<br/>
 * date: 2015年7月21日 <br/>
 * 
 * @author lxf
 * @version
 */
public class ConnectivityAlarmReceiver extends BroadcastReceiver {

	public ConnectivityAlarmReceiver() {
		super();
	}

	@SuppressLint("ShowToast")
	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		if(!Util.isAppRunning(context)){
			return ;
		}
		Intent startSrv = new Intent(context, NotificationService.class);
		if (Util.hasNetwork(context) == false) {
			startSrv.putExtra("TEXT", "请设置网路链接");
			startSrv.putExtra("CMD", "TOAST");
		} else {
			startSrv.putExtra("CMD", "RESET");
		}
		context.startService(startSrv);
	}

}
