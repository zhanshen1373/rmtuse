package com.hd.hse.common.module.branch.ui.model.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebViewClient;

import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.constant.BusinessActionNum;
import com.hd.hse.entity.workorder.WorkTask;
import com.hd.hse.service.inter.ICallBack;
import com.hd.hse.service.manager.ManagerInstance;

public class WorkTaskDetailActivity extends Activity implements ICallBack{
	
	private static final String FLAG = "worktask";
	private WorkTask worktask;
	private ProgressDialog dialog;
	private WebView webView;
	private String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hd_hse_module_work_task_detail);
		
		Intent intent = getIntent();
		worktask = (WorkTask) intent.getSerializableExtra(FLAG);
		
		webView = (WebView) findViewById(R.id.wv_work_task_detail_detail);
		
		dialog = new ProgressDialog(this, "正在加载...");
		
		try {
			ManagerInstance.getInstance().getActionInstance().action(BusinessActionNum.QUERY_REPORT_URL, this, worktask);
		} catch (HDException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(String action, int flag, Object... objects) {
		if(dialog != null){
			dialog.show();
		}
	}

	@Override
	public void process(String action, int flag, Object... objects) {
		
	}

	@Override
	public void end(String action, int flag, Object... objects) {
		if(webView != null){
			url = (String) objects[0];
			refreshPage();
		}
	}

	@Override
	public void error(String action, int flag, Object... objects) {
		if(dialog != null && dialog.isShowing()){
			dialog.dismiss();
		}
		ToastUtils.imgToast(this, R.drawable.hd_hse_common_msg_wrong, "加载错误:" + objects[0]);
	}
	
	private void refreshPage(){
		if(dialog != null && !dialog.isShowing()){
			dialog.show();
		}
		
		if(!TextUtils.isEmpty(url) && webView!= null){
			WebSettings settings = webView.getSettings();
			settings.setSupportZoom(true);
			settings.setBuiltInZoomControls(true);
			
			settings.setDefaultTextEncodingName("UTF-8");
			settings.setRenderPriority(RenderPriority.HIGH);
			
			WebViewClient client = new WebViewClient(){
				@Override
				public void onPageFinished(WebView view, String url) {
					super.onPageFinished(view, url);
					if(dialog != null && dialog.isShowing()){
						dialog.dismiss();
					}
				}
				
				@Override
				public void onReceivedError(WebView view, int errorCode,
						String description, String failingUrl) {
					super.onReceivedError(view, errorCode, description, failingUrl);
					if(dialog != null && dialog.isShowing()){
						dialog.dismiss();
					}
				}
			};
			
			webView.setWebViewClient(client);
			webView.loadUrl(url);
		}
	}
}
