package com.hd.hse.common.module.phone.ui.activity;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import static com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity.DESCRIPTION_KEY;
import static com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity.PERMITID_KEY;
import static com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity.SUBTASKID_KEY;

public class WebActivity extends BaseFrameActivity implements IEventListener {
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String ISEDITZYP = "iseditzyp";
    private boolean isEditZyp = false;

    private WebView mWebView;
    private String url;
    private long permitId;
    private long work_subtask_id;
    private String work_subtask_description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hd_hse_common_module_phone_webactivity);
        isEditZyp = getIntent().getBooleanExtra(ISEDITZYP, false);
        permitId = getIntent().getLongExtra(PERMITID_KEY, 0);
        work_subtask_id = getIntent().getLongExtra(SUBTASKID_KEY, 0);
        work_subtask_description= getIntent().getStringExtra(DESCRIPTION_KEY);
        // 初始化ActionBar
        if (isEditZyp)
            setCustomActionBar(false, this, setActionBarItems2());
            //setCustomActionBar
        else
            setCustomActionBar(false, this, setActionBarItems());

//        setCustomMenuBar(new String[]{IActionBar.ITEM_PHOTOMANAGER});


        // 设置导航栏标题
        setActionBartitleContent(getTitileName(), false);
        url = getIntent().getStringExtra(URL);

        mWebView = (WebView) findViewById(R.id.hd_hse_common_module_phone_webactivity_webview);
        initWebview();
    }

    private void initWebview() {
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            //解决https请求手机验证的问题
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                super.onReceivedSslError(view, handler, error);
            }
        });
        //只会webview访问历史记录里的所有记录除了当前访问记录
        mWebView.clearHistory();
        WebSettings webSettings = mWebView.getSettings();
        // 设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        //关闭webview中缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");
        mWebView.loadUrl(url);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (mWebView != null) {
            mWebView.reload();
        }
    }

    private String getTitileName() {
        return getIntent().getStringExtra(TITLE);
    }

    private String[] setActionBarItems() {
        return new String[]{IActionBar.TV_BACK, IActionBar.TV_TITLE};
    }

    private String[] setActionBarItems2() {
        return new String[]{IActionBar.TV_BACK, IActionBar.TV_TITLE, IActionBar.BT_Edit};
    }

//    @Override
//    public void managerImages() {
//        Intent intent = new Intent(this,
//                PhotoManagerActicity.class);
////        intent.putExtra(PhotoManagerActicity.IMAGEENTITY,
////                (Serializable) imageList);
//        startActivity(intent);
//    }

    @Override
    public void eventProcess(int eventType, Object... objects)
            throws HDException {
        switch (eventType) {
            case IEventType.ACTIONBAR_BT_EDIT_CLICK:
                try {
                    Class aClass = Class.forName("rmt.leaderappr.phone.app.activity.PermitActivity");
                    Intent spIntent = new Intent(
                            this,
                            aClass);
                    RmtWorkSubtask rmtWorkSubtask = new RmtWorkSubtask();
                    rmtWorkSubtask.setWork_subtask_id(work_subtask_id);
                    rmtWorkSubtask.setDescription(work_subtask_description);
                    spIntent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                            "approve");
                    spIntent.putExtra(PERMITID_KEY, permitId);
                    spIntent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                            rmtWorkSubtask);
                    startActivity(spIntent);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
