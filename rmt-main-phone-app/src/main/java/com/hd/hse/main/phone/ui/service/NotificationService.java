package com.hd.hse.main.phone.ui.service;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.PowerManager.WakeLock;
import android.widget.Toast;

import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.constant.BusinessActionNum;
import com.hd.hse.main.phone.ui.activity.welcome.StartWelcomeActivity;
import com.hd.hse.main.phone.ui.receiver.UdpClient;
import com.hd.hse.service.inter.ICallBack;
import com.hd.hse.service.manager.ManagerInstance;
import com.hd.hse.system.SystemProperty;

public class NotificationService extends Service {
	private static Logger logger = LogUtils
			.getLogger(NotificationService.class);
	private WakeLock wakeLock;
	/**
	 * uuid:TODO(用户编码).
	 */
	private String uuid;
	/**
	 * pushIp:TODO(服务器的IP).
	 */
	private String pushIp;
	/**
	 * pushPort:TODO(服务器的端口号).
	 */
	private int pushPort;
	/**
	 * udpClient:TODO(推送服务对象).
	 */
	public UdpClient udpClient;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// PowerManager pm = (PowerManager)
		// this.getSystemService(Context.POWER_SERVICE);
		// wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
		// "OnlineService");

		// notifyRunning();
	}

	@Override
	public int onStartCommand(Intent param, int flags, int startId) {
		// TODO Auto-generated method stub
		if (param == null) {
			return START_STICKY;
		}
		String cmd = param.getStringExtra("CMD");
		if (cmd == null) {
			cmd = "";
		}
		if (cmd.equals("START")) {
			// 获取服务 消息 ip+端口号+uuid 并写入SharedPrefernce 里
			setService();

		}
		if (cmd.equals("TICK")) {
			if (wakeLock != null && wakeLock.isHeld() == false) {
				wakeLock.acquire();
			}
			Toast.makeText(this, "开启程序", Toast.LENGTH_LONG).show();

			Intent intent = new Intent(this, StartWelcomeActivity.class);// getPackageManager().getLaunchIntentForPackage(packageName);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			this.startActivity(intent);
		}
		if (cmd.equals("RESET")) {
			if (wakeLock != null && wakeLock.isHeld() == false) {
				wakeLock.acquire();
			}
			// 表示读取SharedPre 里边的数据
			getSharedPre();
			resetClient();
		}
		if (cmd.equals("TOAST")) {
			String text = param.getStringExtra("TEXT");
			if (text != null && text.trim().length() != 0) {
				Toast.makeText(this, text, Toast.LENGTH_LONG).show();
			}
		}
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * setService:(设置消息服务IP+Port+UUID). <br/>
	 * date: 2015年7月21日 <br/>
	 * 
	 * @author lxf
	 */
	private void setService() {
		try {
			// 访问查推送服务id地址和端口号
			ManagerInstance
					.getInstance()
					.getActionInstance()
					.action(BusinessActionNum.QUERY_PUSH_SERVICE_ADDRESS,
							iCallBack);
		} catch (HDException e) {
			ToastUtils.toast(this, "初始化出错，请联系管理员！");
		}
	}

	/**
	 * 查询历史登录人回调函数
	 */
	private ICallBack iCallBack = new ICallBack() {

		@Override
		public void start(String action, int flag, Object... objects) {

		}

		@Override
		public void process(String action, int flag, Object... objects) {
		}

		@Override
		public void end(String action, int flag, Object... objects) {
			if (BusinessActionNum.QUERY_PUSH_SERVICE_ADDRESS.equals(action)) {
				// 获取到消息服务的信息
				try {
					if (objects != null && objects[0] instanceof String) {
						String pushStr = objects[0].toString();
						if (!"".equals(pushStr)) {
							if (pushStr.contains(":")) {
								String str[];
								str = pushStr.split(":");
								pushIp = str[0];
								pushPort = Integer.parseInt(str[1]);
							} else {
								pushIp = pushStr;
								pushPort = 8080;
							}
						}
					}
				} catch (Exception e) {
					logger.error(e);
					ToastUtils.toast(getBaseContext(), "设置消息服务地址有问题，请检查");
				}
				// 访问查历史登陆人数据集服务
				if (SystemProperty.getSystemProperty().getLoginPerson() != null) {
					uuid = SystemProperty.getSystemProperty().getLoginPerson()
							.getUuid();
				} else {
					uuid = "TEST_TEST";
				}
				//
				setPkgsInfo(true);
				// 设置消息服务
				resetClient();
			}

		}

		@Override
		public void error(String action, int flag, Object... objects) {
			logger.error("获取历史登陆人或推送服务注册问题");
		}
	};

	/**
	 * setPkgsInfo:(存服务消息基础信息). <br/>
	 * date: 2015年7月21日 <br/>
	 * 
	 * @author lxf
	 */
	protected void setPkgsInfo(boolean isall) {
		// 把信息存入ShardPrefrence中
		if (!isall) {
			if (udpClient == null) {
				return;
			}
		}
		// long sent = myUdpClient.getSentPackets();
		// long received = myUdpClient.getReceivedPackets();
		SharedPreferences account = this.getSharedPreferences(
				Params.DEFAULT_PRE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = account.edit();
		if (isall) {
			editor.putString(Params.SENT_PKGS, "0");
			editor.putString(Params.RECEIVE_PKGS, "0");
			editor.putString(Params.SERVER_IP, pushIp);// ip
			editor.putInt(Params.SERVER_PORT, pushPort);// 端口号
			editor.putString(Params.USER_NAME, uuid);// 唯一ID
		} else {
			editor.putString(Params.SENT_PKGS, "" + 1);
			editor.putString(Params.RECEIVE_PKGS, "" + 1);
		}
		editor.commit();
	}

	/**
	 * getSharedPre:(表示读取). <br/>
	 * date: 2015年7月21日 <br/>
	 * 
	 * @author lxf
	 */
	private void getSharedPre() {
		SharedPreferences account = this.getSharedPreferences(
				Params.DEFAULT_PRE_NAME, Context.MODE_PRIVATE);
		pushIp = account.getString(Params.SERVER_IP, "");
		pushPort = account.getInt(Params.SERVER_PORT, 8080);
		uuid = account.getString(Params.USER_NAME, "");
	}

	/**
	 * resetClient:(初始化设置消息服务). <br/>
	 * date: 2015年7月21日 <br/>
	 * 
	 * @author lxf
	 */
	protected void resetClient() {

		try {
			if (udpClient != null) {
				udpClient.stop();
			}
			if (StringUtils.isEmpty(pushIp) || StringUtils.isEmpty(uuid)) {
				return;
			}
			//Context context = NotificationService.this.getApplicationContext();
			udpClient = new UdpClient(Util.md5Byte(uuid), 1, pushIp, pushPort);
			udpClient.setContext(this);
			udpClient.setHeartbeatInterval(50);
			udpClient.start();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public class Params {
		public static final String DEFAULT_PRE_NAME = "defaultAccount";
		public static final String SERVER_IP = "serverIp";
		public static final String SERVER_PORT = "serverPort";
		public static final String PUSH_PORT = "pushPort";
		public static final String USER_NAME = "userName";
		public static final String SENT_PKGS = "sentPkgs";
		public static final String RECEIVE_PKGS = "receivePkgs";
	}

	// private final IBinder mBinder = new MyBinder();

	//
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		if (udpClient != null) {
			try {
				udpClient.stop();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		super.onDestroy();
	}
	//
	// @Override
	// public IBinder onBind(Intent arg0) {
	//
	// return mBinder;
	//
	// }
	//
	// public class MyBinder extends Binder {
	//
	// public NotificationService getService() {
	//
	// return NotificationService.this;
	//
	// }
	//
	// }
	//

}
