package com.hd.hse.common.module.phone.ui.activity;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.StringUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hd.hse.business.task.IMessageWhat;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.AppException;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.ui.event.homepage.AbstractAppModule;
import com.hd.hse.common.module.phone.ui.utils.BitmapUtils;
import com.hd.hse.constant.IConfigEncoding;
import com.hd.hse.entity.common.PositionCard;
import com.hd.hse.service.card.PositionQuerier;
import com.hd.hse.system.SystemProperty;

/**
 * ClassName: LocationSwCard (位置卡刷卡)<br/>
 * date: 2015年1月20日 <br/>
 * 
 * @author wenlin
 * @version
 */

public class LocationSwCard extends ReadCardActivity {

	/**
	 * SER_KEY_TARGETCLASS:TODO(目标刷卡成功后，跳转应用).
	 */
	public final static String SER_KEY_TARGETCLASS = "targetclass";
	/**
	 * imageView:TODO(刷卡动画).
	 */
	private ImageView imageView;

	/**
	 * imageButton:TODO(关闭按钮).
	 */
	private ImageButton imageButton;
	/**
	 * button:TODO(测试按钮).
	 */
	private Button button;

	private Animation translateAnimation;

	private ImageView locationIV;

	private TextView warnTipTV;

	/**
	 * cls:TODO(成功后跳转的界面).
	 */
	private Class<?> cls;
	
	/**
	 * SuccessCardRecord:TODO(记录上次刷卡成功的卡号).
	 */
	private static String SuccessCardRecord;

	/**
	 * bOnTime:TODO(定时刷卡).
	 */
	private boolean bOnTime = false;

	/**
	 * ON_TIME_TAG:TODO(定时消息标识).
	 */
	private final static String ON_TIME_TAG = "ON_TIME_TAG";

	/**
	 * mTimer:TODO(刷卡计时器).
	 */
	public static Timer mTimer;

	/**
	 * mTimerHandler:TODO(定时刷卡处理).
	 */
	private Handler mTimerHandler;
	
	
	//
	// private IQueryRelativeConfig relation;
	//
	// private RelationTableName relationEntity;
	//
	// /**
	// * isReUseLocation:TODO(是否重用位置卡).
	// */
	// public static boolean isReUseLocation = false;
	
	
	/**
	 * moduleCode:TODO(模块功能编码). //lxf 新增
	 */
	private String moduleCode=null;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initScreenSize();
		setContentView(R.layout.hd_hse_common_module_phone_swingcard_location);
		// 屏蔽点击其他区域消失
		setFinishOnTouchOutside(false);
		Intent intent = getIntent();
		cls = (Class<?>) intent.getSerializableExtra(SER_KEY_TARGETCLASS);
		
		moduleCode=intent.getStringExtra(FrameworkActivity.INTENT_FUNCTION_KEY);
		// 定时刷卡
		bOnTime = intent.getBooleanExtra(ON_TIME_TAG, false);
		//lxf  添加，不是定时刷卡弹出界面，此时需要重置位置卡
		if(!bOnTime){
			SuccessCardRecord=null;
		}
		// if (!isReUseLocation || mTimer == null || bOnTime) {
		// if (mTimer == null || bOnTime) {
		// 1.由定时器弹出的界面必须重新创建定时器，原因定时器的回调函数中已经关闭了定时器
		// 2.定时器为空的时候必须创建
		// 3.位置卡不重用时，必须创建
		// 4.第一次进来，isReUseLocation=false，创建，之后不同模块之间跳转就不会再次创建了
		// 5.操作过位置卡之后，返回到主界面再次进入时，isReUseLocation=true，但是mTimer=null，因此还是会创建
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
		mTimer = new Timer();
		// }
		if (mTimerHandler == null)
			mTimerHandler = new OnTimeHandler();

		// 移动的imageview
		imageView = (ImageView) findViewById(R.id.hd_hse_commone_module_phone_iv_image_phone);
		imageView
				.setImageDrawable(BitmapUtils
						.decodeBitmap(
								getResources(),
								R.drawable.hd_hse_common_module_phone_swinglocation_image_phone));
		// 关闭按钮
		imageButton = (ImageButton) findViewById(R.id.hd_hse_common_module_phone_locationswcard_ib_close);
		imageButton.setOnClickListener(new CloseClickListener());

		locationIV = (ImageView) findViewById(R.id.hd_hse_commone_module_phone_iv_image_location);
		locationIV
				.setImageDrawable(BitmapUtils
						.decodeBitmap(
								getResources(),
								R.drawable.hd_hse_common_module_phone_swinglocation_image_locationcaed));

		warnTipTV = (TextView) findViewById(R.id.hd_hse_common_module_phone_tv_messagetip);
		warnTipTV.setCompoundDrawablesRelativeWithIntrinsicBounds(BitmapUtils
				.decodeBitmap(getResources(),
						R.drawable.hd_hse_common_module_phone_icon_warn), null,
				null, null);

		// 测试按钮
		button = (Button) findViewById(R.id.locationtest);
		if (!IConfigEncoding.ISTEST) {
			button.setVisibility(View.GONE);
		}
		// relation = new QueryRelativeConfig();
		// relationEntity = new RelationTableName();
		// relationEntity.setSys_type(IRelativeEncoding.REUSINGLOCATIONCARD);
		// isReUseLocation = relation.isHadRelative(relationEntity);
		// // 判断其是否存在'重用位置'关系,并且是否刷位置卡
		// if (!bOnTime && isReUseLocation
		// && SystemProperty.getSystemProperty().getPositionCard() != null) {
		// startActivity(cls);
		// }
		// button点击事件
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				startActivity(cls);
			}
		});
		// if (translateAnimation == null) {
		// setAnimationDrawable();
		// if (!translateAnimation.hasStarted())
		// // 启动动画
		// translateAnimation.startNow();
		// }
	}

	/**
	 * initScreenSize:(初始化弹框尺寸). <br/>
	 * date: 2015年1月20日 <br/>
	 * 
	 * @author wenlin
	 */
	@SuppressWarnings("deprecation")
	public void initScreenSize() {
		// 没有导航栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 获取屏幕的尺寸
		Display display = this.getWindowManager().getDefaultDisplay();
		// 获取屏幕各尺寸参数
		LayoutParams mWinLayoutParams = this.getWindow().getAttributes();
		// 弹框宽度为全宽的0.9倍
		mWinLayoutParams.width = (int) (display.getWidth() * 0.9);
		// 弹框高度为屏幕的0.41倍
		mWinLayoutParams.height = (int) (display.getHeight() * 0.41);
	}

	/**
	 * TODO 开始动画
	 * 
	 * @see android.app.Fragment#onStart()
	 */
	public void onStart() {
		super.onStart();
		// if (isReUseLocation
		// || SystemProperty.getSystemProperty().getPositionCard() == null) {
		// if (SystemProperty.getSystemProperty().getPositionCard() == null) {
		if (translateAnimation == null) {
			setAnimationDrawable();
			translateAnimation.startNow();
		} else {
			if (translateAnimation.hasEnded())
				// 启动动画
				translateAnimation.startNow();
		}
		// }
	}

	/**
	 * TODO 刷卡动画初始化 setAnimationDrawable:() date: 2015年01月20日
	 * 
	 * @author wenlin
	 */
	private void setAnimationDrawable() {
		// 动画移动的轨迹
		translateAnimation = new TranslateAnimation(0, -250, 0, 0);
		// 单次动画持续时间
		translateAnimation.setDuration(2000);
		// 从头移动
		translateAnimation.setRepeatMode(Animation.RESTART);
		// 总共移动次数
		translateAnimation.setRepeatCount(100000);
		imageView.setAnimation(translateAnimation);
	}

	/**
	 * TODO 停止动画
	 * 
	 * @see android.app.Fragment#onStop()
	 */
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		// if (translateAnimation != null) {
		// // 动画取消
		// translateAnimation.cancel();
		// }
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	/**
	 * TODO 表示开启寻卡功能
	 * 
	 * @see android.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		actionByCardID(cardnum);
	}

	/**
	 * @see android.app.Fragment#onPause()
	 */
	@Override
	public void onPause() {
		if (translateAnimation != null) {
			// 动画取消
			translateAnimation.cancel();
		}
		super.onPause();
	}

	@Override
	public void actionByCardID(String cardid) {
		// 刷卡，回调本方法
		if (cardid != null && cardid.length() > 0) {
			startActivityByCard(cardid);
		}
	}

	/**
	 * startActivityByCard:(刷卡后取数据并启动新的界面). <br/>
	 * date: 2015年01月20日 <br/>
	 * 
	 * @author wenlin
	 * @param cardNum
	 *            卡号
	 * @throws HDException
	 */
	private void startActivityByCard(String cardNum) {
		boolean iserror = false;
		if (!StringUtils.isEmpty(SuccessCardRecord)
				&& !SuccessCardRecord.equalsIgnoreCase(cardNum)) {
			ToastUtils.imgToast(this, R.drawable.hd_hse_common_msg_wrong,
					"请刷当前操作位置的位置卡");
			return;
		}
		// 位置卡查询
		PositionQuerier querier = new PositionQuerier();
		try {
			// 根据位置卡卡号查询的信息
			PositionCard perPositionCard = (PositionCard) querier
					.query(cardNum);
			if (perPositionCard == null) {
				throw new AppException("无效卡或更新基础数据后再尝试刷卡！");
			}
			// 设置系统属性位置卡信息
			SuccessCardRecord=cardNum;
			SystemProperty.getSystemProperty().setPositionCard(perPositionCard);
			startActivity(cls);

		} catch (AppException e) {
			ToastUtils.imgToast(this, R.drawable.hd_hse_common_msg_wrong,
					e.getMessage());
			iserror = true;
		} catch (HDException e) {
			ToastUtils.imgToast(this, R.drawable.hd_hse_common_msg_wrong,
					e.getMessage());
			iserror = true;
		}
		// 错误处理
		if (iserror && !isend) {
			if (null != myReadCardThread
					&& myReadCardThread.getState() == java.lang.Thread.State.TERMINATED) {
				StartReadCardThread();
			} else {
				if (null != myReadCardThread) {
					myReadCardThread.setStart();
				}
			}
		}
	}

	/**
	 * startActivity:(跳转到指定作业票列表). <br/>
	 * date: 2015年1月20日 <br/>
	 * 
	 * @author wenlin
	 * @param cls
	 */
	private void startActivity(Class<?> cls) {
		// // 销毁本Activity
		// popActivity();
		// 非定时刷卡，则启动下一页面
		if (!bOnTime) {
			Intent intent = new Intent(SystemApplication.getInstance()
					.getLastActivity(), cls);
			// 开启界面  //lxf 新增传入模块编码
			Bundle mBundle = new Bundle();
			mBundle.putString(FrameworkActivity.INTENT_FUNCTION_KEY,moduleCode);
			intent.putExtras(mBundle);
			
			startActivity(intent);
		}
		// 启动定时器
		PositionCard posCard = SystemProperty.getSystemProperty()
				.getPositionCard();
		if (posCard != null && !StringUtils.isEmpty(posCard.getTxtime())) {
			long intervalTime = Long.parseLong(posCard.getTxtime()) * 60 * 1000;
			mTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					Log.i("LocationCard", "mTimerHandler.sendEmptyMessage");
					// 到时间，向UI发消息
					mTimerHandler.sendEmptyMessage(IMessageWhat.START);
				}
			}, intervalTime);
		}
		// 销毁本Activity
		popActivity();
	}

	/**
	 * TODO
	 * 
	 * @see com.hd.hse.common.module.phone.ui.activity.BaseActivity#onKeyDown(int,
	 *      android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (KeyEvent.KEYCODE_BACK == keyCode) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * ClassName: CloseClickListener (关闭事件监听)<br/>
	 * date: 2015年2月4日 <br/>
	 * 
	 * @author lg
	 * @version LocationSwCard
	 */
	private class CloseClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			// 定时刷卡，则回到首页
			if (bOnTime) {
				SystemApplication.getInstance().exitToMain();
				if (mTimer != null) {
					mTimer.cancel();
					mTimer = null;
				}
				SystemProperty.getSystemProperty().setPositionCard(null);
			}
			// 非定时刷卡，则退出刷卡界面
			else {
				popActivity();
			}
		}

	}

	/**
	 * ClassName: OnTimeHandler (定时器消息处理，定时刷卡)<br/>
	 * date: 2015年2月4日 <br/>
	 * 
	 * @author lg
	 * @version LocationSwCard
	 */
	private class OnTimeHandler extends Handler {

		/**
		 * TODO
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == IMessageWhat.START) {
				// 到达规定时间
				Log.i("LocationCard", "handle Message");
				SystemProperty.getSystemProperty().setPositionCard(null);
				if (AbstractAppModule.isSwCard) {
					Intent intent = new Intent(LocationSwCard.this,
							LocationSwCard.class);
					intent.putExtra(ON_TIME_TAG, true);
					startActivity(intent);
				} 
				Log.i("LocationCard", "mTimer cancel");
				mTimer.cancel();
			}
		}

	}

}
