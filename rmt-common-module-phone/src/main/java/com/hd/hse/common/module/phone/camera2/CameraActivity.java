package com.hd.hse.common.module.phone.camera2;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hd.hse.common.module.phone.R;
import com.hd.hse.entity.sys.RmtSignRecord;


public class CameraActivity extends FragmentActivity {
	
	public static final String FLAG = "worktask";
	public static final String SIGNRECORD="signrecord";
	private Fragment curFragment;

    public com.hd.hse.entity.common.Image image;
    public RmtSignRecord rmtSignRecord;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN){
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} else {
			View decorView = getWindow().getDecorView();
			int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
			decorView.setSystemUiVisibility(uiOptions);
		}
		
		getWindow().setBackgroundDrawable(null);
		setContentView(R.layout.hd_hse_module_activity_camera);
		
		Intent intent = getIntent();
		image = (com.hd.hse.entity.common.Image) intent.getSerializableExtra(FLAG);
		rmtSignRecord= (RmtSignRecord) intent.getSerializableExtra(SIGNRECORD);
		
		if(savedInstanceState == null){
			android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
			android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
			//FragmentTransaction ft = fm.beginTransaction();
			curFragment = new CameraFragment();
			ft.replace(R.id.hd_hse_module_camera_container, curFragment);
			ft.commit();
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		image = (com.hd.hse.entity.common.Image) intent.getSerializableExtra(FLAG);
	}
	
	public void switchFragment(Fragment from, Fragment to){
		FragmentManager fm = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.hd_hse_module_camera_container, to);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			int count = getSupportFragmentManager().getBackStackEntryCount();
			if(count == 0 || count == 1){
				finish();
			}else{
				getSupportFragmentManager().popBackStack();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
