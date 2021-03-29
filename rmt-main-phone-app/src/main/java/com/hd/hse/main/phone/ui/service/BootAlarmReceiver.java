package com.hd.hse.main.phone.ui.service;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.hd.hse.main.phone.ui.activity.welcome.StartWelcomeActivity;

/**
 * ClassName: BootAlarmReceiver (开机执行的广播接收者)<br/>
 * date: 2015年7月21日  <br/>
 *
 * @author lxf
 * @version 
 */
public class BootAlarmReceiver extends BroadcastReceiver {

	public BootAlarmReceiver(){
		
	}
	@SuppressLint("ShowToast")
	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		Intent startSrv = new Intent(context, NotificationService.class);
		startSrv.putExtra("CMD", "TICK");
		context.startService(startSrv);
	}

}
