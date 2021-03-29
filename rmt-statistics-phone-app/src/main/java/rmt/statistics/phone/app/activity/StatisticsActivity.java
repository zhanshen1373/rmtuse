package rmt.statistics.phone.app.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.DateTimePickDialogUtil;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;
import com.hd.hse.entity.common.PersonCard;
import com.hd.hse.entity.workorder.StatisticsTodayWorkUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import rmt.statistics.phone.app.R;

import static com.hd.hse.system.SystemProperty.getSystemProperty;

public class StatisticsActivity extends BaseTaskListBusActivity implements View.OnClickListener {

    private LinearLayout topView;
    private LinearLayout startTimeLinear;
    private LinearLayout endTimeLinear;
    private TextView startTimeTxt;
    private TextView endTimeTxt;
    private TextView deptTxt;
    private Button sureButton;
    private WebView webView;
    private boolean needClearHistory = false; // 是否需要清除webview历史的标志
    private LinearLayout deptLinear;
    private String titleName;
    private String decode;
    private List<Long> orgidlist;
    private PersonCard personCard;
    private LinearLayout zyptypeLinear;
    private TextView zyptypeTxt;
    private TextView starttimeTitle;
    private int position;
    private View lineview;
    private View endtiemview;
    private List<String> orgnamelist;
    private List<String> bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        endtiemview = findViewById(R.id.endtime_view);
        lineview = findViewById(R.id.zyptype_view);
        topView = (LinearLayout) findViewById(R.id.topview);
        startTimeLinear = (LinearLayout) findViewById(R.id.starttime_linear);
        endTimeLinear = (LinearLayout) findViewById(R.id.endtime_linear);
        deptLinear = (LinearLayout) findViewById(R.id.dept_linear);
        zyptypeLinear = (LinearLayout) findViewById(R.id.zyptype_linear);
        starttimeTitle = (TextView) findViewById(R.id.starttime_title);
        zyptypeTxt = (TextView) findViewById(R.id.zyptype_text);
        startTimeTxt = (TextView) findViewById(R.id.starttime_text);
        endTimeTxt = (TextView) findViewById(R.id.endtime_text);
        deptTxt = (TextView) findViewById(R.id.dept_text);
        sureButton = (Button) findViewById(R.id.surebutton);
        webView = (WebView) findViewById(R.id.statistics_webview);
        webView.setInitialScale(150);
        WebSettings webSettings = webView.getSettings();
        // 设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        webView.setWebViewClient(new MyWebViewClient());

        if (position == 0) {
            zyptypeLinear.setVisibility(View.GONE);
            lineview.setVisibility(View.GONE);
        } else if (position == 1) {
            zyptypeLinear.setVisibility(View.VISIBLE);
            zyptypeTxt.setText("请选择");
            lineview.setVisibility(View.VISIBLE);
        } else if (position == 2) {
            zyptypeLinear.setVisibility(View.GONE);
            endTimeLinear.setVisibility(View.GONE);
            lineview.setVisibility(View.GONE);
            endtiemview.setVisibility(View.GONE);
            starttimeTitle.setText("年度");
        }

        if (position == 2) {
            startTimeTxt.setText(new SimpleDateFormat("yyyy")
                    .format(new Date()));
        } else {
            startTimeTxt.setText(new SimpleDateFormat("yyyy-MM-dd")
                    .format(new Date()));
        }

        endTimeTxt.setText(new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date()));
        personCard = getSystemProperty()
                .getLoginPerson();
        if (personCard != null) {
            deptTxt.setText(personCard.getDepartment_desc());
        }

        startTimeLinear.setOnClickListener(this);
        endTimeLinear.setOnClickListener(this);
        deptLinear.setOnClickListener(this);
        sureButton.setOnClickListener(this);
        zyptypeLinear.setOnClickListener(this);

    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        titleName = intent.getStringExtra("name");

        // 设置导航栏信息
        setCustomActionBar(false, eventListener, new String[]{
                IActionBar.TV_BACK, IActionBar.TV_TITLE, IActionBar.TV_SXTJ});
        // 设置导航栏标题
        setActionBartitleContent(getTitileName(), false);
        // 设置左侧模块选择抽屉
        setNavContent(getNavContentData(), getNavCurrentKey());
    }

    class MyWebViewClient extends WebViewClient {
        // 重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开。
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            // 如果不需要其他对点击链接事件的处理返回true，否则返回false
            return true;

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            webView.loadUrl(null);

        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (needClearHistory) {
                webView.clearHistory();
                needClearHistory = false;
            }
        }
    }

    /**
     * eventListener:TODO().
     */
    protected IEventListener eventListener = new IEventListener() {

        @Override
        public void eventProcess(int eventType, Object... objects)
                throws HDException {
            // TODO Auto-generated method stub
            if (eventType == IEventType.ACTION_SXTJ) {
                if (topView.getVisibility() == View.VISIBLE) {
                    topView.setVisibility(View.GONE);
                } else {
                    topView.setVisibility(View.VISIBLE);
                }

            }
        }
    };

    @Override
    protected void initData() {


    }

    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub
        //放需要的名称
        return titleName;
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "rmt-statistics-phone-app";
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.starttime_linear) {
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String str = startTimeTxt.getText().toString();
            DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                    StatisticsActivity.this, str);
            dateTimePicKDialog.dateTimePicKDialog(startTimeTxt, format);
        } else if (v.getId() == R.id.endtime_linear) {
            String str = endTimeTxt.getText().toString();
            DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                    StatisticsActivity.this, str);
            dateTimePicKDialog.dateTimePicKDialog(endTimeTxt, str);
        } else if (v.getId() == R.id.dept_linear) {
            Intent intent = new Intent(this, DeptActivity.class);
            intent.putExtra("title", getTitileName());
            startActivityForResult(intent, 123, null);

        } else if (v.getId() == R.id.zyptype_linear) {

            Intent intent = new Intent(this, WorkerOrderTypeActivity.class);
            startActivityForResult(intent, 234, null);

        } else if (v.getId() == R.id.surebutton) {
            //发起get请求
            needClearHistory = true;
            topView.setVisibility(View.GONE);

            if (position == 0) {

                String s = startTimeTxt.getText().toString();
                String s1 = endTimeTxt.getText().toString();
                StringBuffer id = new StringBuffer();
                JSONObject f = new JSONObject();

                //点击了部门
                if (orgidlist != null && orgidlist.size() > 0) {
                    for (int i = 0; i < orgidlist.size(); i++) {
                        if (i == orgidlist.size() - 1) {
                            id.append(orgidlist.get(i));
                            break;
                        }
                        id.append(orgidlist.get(i)).append(",");
                    }
                    try {
                        f.put("starttime", s);
                        f.put("endtime", s1);
                        f.put("orgid", id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {

                    String department = personCard.getDepartment();

                    try {
                        f.put("starttime", s);
                        f.put("endtime", s1);
                        f.put("orgid", department);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                final android.app.ProgressDialog dialog = new android.app.ProgressDialog(StatisticsActivity.this);
                dialog.setMessage("正在查询");
                dialog.show();
                final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
                // 获取统计分析属地部门
                Call<ResultData<StatisticsTodayWorkUrl>> call = rmtInterface.getDeptSchedule(f.toString());
                HttpBusiness.action(StatisticsActivity.this, false, call, new HttpCallBack<StatisticsTodayWorkUrl>() {
                    @Override
                    public void success(StatisticsTodayWorkUrl statisticsTodayWorkUrl) {
                        dialog.dismiss();

                        String url = statisticsTodayWorkUrl.getUrl();
                        try {
                            decode = URLDecoder.decode(url, "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        webView.loadUrl(decode);
                    }

                    @Override
                    public void warning(String msg) {
                        dialog.dismiss();
                        ToastUtil.toast(StatisticsActivity.this, msg);
                    }

                    @Override
                    public void error(Throwable t) {
                        dialog.dismiss();
                        ToastUtil.toast(StatisticsActivity.this, "获取数据列表失败");
                    }
                });
            } else if (position == 1) {

                String s = startTimeTxt.getText().toString();
                String s1 = endTimeTxt.getText().toString();
                JSONObject f = new JSONObject();
                StringBuffer worktypeid = new StringBuffer();

                if (bm != null && bm.size() > 0) {

                    for (int j = 0; j < bm.size(); j++) {
                        if (j == bm.size() - 1) {
                            worktypeid.append(bm.get(j));
                            break;
                        }
                        worktypeid.append(bm.get(j)).append(",");
                    }
                    try {
                        f.put("worktype", worktypeid);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {

                    try {
                        f.put("worktype", "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                //点击了部门
                if (orgidlist != null && orgidlist.size() > 0) {
                    //只会有1条数据
                    long id = orgidlist.get(0);
//                    String name = orgnamelist.get(0);


                    try {
                        f.put("starttime", s);
                        f.put("endtime", s1);
                        f.put("orgid", id);
//                        f.put("orgname", name);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {

                    String department = personCard.getDepartment();
//                    String department_desc = personCard.getDepartment_desc();
                    try {
                        f.put("starttime", s);
                        f.put("endtime", s1);
                        f.put("orgid", department);
//                        f.put("orgname", department_desc);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                final android.app.ProgressDialog dialog = new android.app.ProgressDialog(StatisticsActivity.this);
                dialog.setMessage("正在查询");
                dialog.show();
                final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);

                Call<ResultData<StatisticsTodayWorkUrl>> call = rmtInterface.getWorkerOrderTypeSchedule(f.toString());
                HttpBusiness.action(StatisticsActivity.this, false, call, new HttpCallBack<StatisticsTodayWorkUrl>() {
                    @Override
                    public void success(StatisticsTodayWorkUrl statisticsTodayWorkUrl) {
                        dialog.dismiss();

                        String url = statisticsTodayWorkUrl.getUrl();

//                        String urlbegin = url.replace("%3A", ":");
//                        String urlend = urlbegin.replace("%2F", "/");
                        try {
                            decode = URLDecoder.decode(url, "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        webView.loadUrl(decode);
                    }

                    @Override
                    public void warning(String msg) {
                        dialog.dismiss();
                        ToastUtil.toast(StatisticsActivity.this, msg);
                    }

                    @Override
                    public void error(Throwable t) {
                        dialog.dismiss();
                        ToastUtil.toast(StatisticsActivity.this, "获取数据列表失败");
                    }
                });


            } else if (position == 2) {

                String s = startTimeTxt.getText().toString();
                JSONObject f = new JSONObject();

                //点击了部门
                if (orgidlist != null && orgidlist.size() > 0) {
                    //只会有1条数据
                    long id = orgidlist.get(0);
//                    String name = orgnamelist.get(0);


                    try {
                        f.put("year", s);
                        f.put("orgid", id);
//                        f.put("orgname", name);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {

                    String department = personCard.getDepartment();
//                    String department_desc = personCard.getDepartment_desc();
                    try {
                        f.put("year", s);
                        f.put("orgid", department);
//                        f.put("orgname", department_desc);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                final android.app.ProgressDialog dialog = new android.app.ProgressDialog(StatisticsActivity.this);
                dialog.setMessage("正在查询");
                dialog.show();
                final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);

                Call<ResultData<StatisticsTodayWorkUrl>> call = rmtInterface.getWorkerMonthNumSchedule(f.toString());
                HttpBusiness.action(StatisticsActivity.this, false, call, new HttpCallBack<StatisticsTodayWorkUrl>() {
                    @Override
                    public void success(StatisticsTodayWorkUrl statisticsTodayWorkUrl) {
                        dialog.dismiss();

                        String url = statisticsTodayWorkUrl.getUrl();
                        try {
                            decode = URLDecoder.decode(url, "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        webView.loadUrl(decode);
                    }

                    @Override
                    public void warning(String msg) {
                        dialog.dismiss();
                        ToastUtil.toast(StatisticsActivity.this, msg);
                    }

                    @Override
                    public void error(Throwable t) {
                        dialog.dismiss();
                        ToastUtil.toast(StatisticsActivity.this, "获取数据列表失败");
                    }
                });

            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == 123) {
            Serializable dept = data.getSerializableExtra("dept");
            orgidlist = (List<Long>) dept;

            Serializable name = data.getSerializableExtra("name");
            orgnamelist = (List<String>) name;

            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < orgnamelist.size(); i++) {
                if (i <= 7) {
                    if (i == orgnamelist.size() - 1) {
                        stringBuffer.append(orgnamelist.get(i));
                        break;
                    }
                    stringBuffer.append(orgnamelist.get(i)).append(",");
                } else {
                    stringBuffer.append("...");
                }

            }
            if (orgnamelist != null && orgnamelist.size() > 0) {
                deptTxt.setText(stringBuffer.toString());
            } else {
                deptTxt.setText(personCard.getDepartment_desc());
            }

        } else if (requestCode == 234 && resultCode == 234) {
            //编码
            Serializable workerordertype = data.getSerializableExtra("workerorderbm");
            //描述
            Serializable workerorderms = data.getSerializableExtra("workerorderms");
            bm = (List<String>) workerordertype;
            List<String> ms = (List<String>) workerorderms;

            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < ms.size(); i++) {
                if (i <= 7) {
                    if (i == ms.size() - 1) {
                        stringBuffer.append(ms.get(i));
                        break;
                    }
                    stringBuffer.append(ms.get(i)).append(",");
                } else {
                    stringBuffer.append("...");
                }

            }
            if (bm.size() == 0) {
                zyptypeTxt.setText("");
            } else {
                zyptypeTxt.setText(stringBuffer.toString());
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.getVisibility() == View.VISIBLE
                    && webView.canGoBack()) {
                webView.goBack();

            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
