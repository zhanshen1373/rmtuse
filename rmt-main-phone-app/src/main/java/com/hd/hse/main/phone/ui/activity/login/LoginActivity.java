package com.hd.hse.main.phone.ui.activity.login;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.hayden.hap.common.dao.dBEntity.LoginHistory;
import com.hayden.hap.common.login.business.LoginUser;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.component.phone.adapter.OptionsAdapter;
import com.hd.hse.common.component.phone.custom.DrawEditText;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.ui.activity.SystemApplication;
import com.hd.hse.common.module.phone.ui.utils.BitmapUtils;
import com.hd.hse.entity.common.PersonCard;
import com.hd.hse.main.phone.R;
import com.hd.hse.main.phone.ui.receiver.HDNotification;
import com.hd.hse.main.phone.ui.receiver.ReceiverManager;
import com.hd.hse.system.SystemProperty;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends com.hayden.hap.common.login.LoginActivity implements Handler.Callback {
    private static Logger logger = LogUtils.getLogger(LoginActivity.class);
    public static Context context;

    /**
     * lstRcvr:TODO(广播收听).
     */
    private List<BroadcastReceiver> lstRcvr;

    /**
     * lstPdIntent:TODO(定时任务).
     */
    private List<PendingIntent> lstPdIntent;

    private PopupWindow selectPopupWindow = null;
    // 展示所有下拉选项的ListView
    private ListView listView = null;
    // 自定义Adapter
    private OptionsAdapter optionsAdapter = null;
    private Handler handler;
    // 下拉框选项数据源
    private ArrayList<String> datas = new ArrayList<String>();
    ;
    // 下拉框依附组件宽度，也将作为下拉框的宽度
    private int pwidth;
    private boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.context = this;
        // 启动后台任务
        startTasks();
    }

    @Override
    protected void initView() {
        super.initView();
        View logo = findViewById(R.id.hd_hse_login_logo);
        View main = findViewById(R.id.hd_hse_main_applogin_main);
        // 背景图片压缩处理
        setBackground(logo, R.drawable.hd_hse_phone_logo);
        setBackground(main, R.drawable.hd_hse_main_app_login_bg);
        ((DrawEditText) nameEdit).setiTouchOnClick(new useNameTouchClick());
        initWedget();
    }

    /**
     * 初始化界面控件
     */
    private void initWedget() {
        // 初始化Handler,用来处理消息
        handler = new Handler(LoginActivity.this);

    }

    /**
     * 初始化PopupWindow
     */
    private void initPopuWindow() {
        // PopupWindow浮动下拉框布局
        View loginwindow = (View) this.getLayoutInflater().inflate(
                R.layout.hd_hse_main_app_login_options, null);
        listView = (ListView) loginwindow
                .findViewById(R.id.hd_hse_main_app_option_lv01);
        // 设置自定义Adapter
        optionsAdapter = new OptionsAdapter(this, handler, datas);
        optionsAdapter.setiEventLsn(new IEventListener() {
            @Override
            public void eventProcess(int eventType, Object... objects) throws HDException {
                if (IEventType.MEASURE_TYPE_CLICK == eventType) {
                    nameEdit.setText(objects[0].toString());
                    selectPopupWindow.dismiss();
                }
            }
        });
        listView.setAdapter(optionsAdapter);
        selectPopupWindow = new PopupWindow(loginwindow, pwidth,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        selectPopupWindow.setOutsideTouchable(true);
        // 这一句是为了实现弹出PopupWindow后，当点击屏幕其他部分及Back键时PopupWindow会消失，
        selectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        selectPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        selectPopupWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    public void setBackground(View view, int resId) {
        if (view == null) {
            return;
        }
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(BitmapUtils.decodeBitmap(getResources(),
                    resId));
        } else {
            view.setBackground(BitmapUtils.decodeBitmap(getResources(), resId));
        }
    }

    /**
     * TODO 表示开启寻卡功能
     *
     * @see android.app.Fragment#onResume()
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * startTasks:(启动后台任务). <br/>
     * date: 2014年11月5日 <br/>
     *
     * @author lg
     */
    private void startTasks() {
        lstRcvr = ReceiverManager.registerReceivers(this);
        lstPdIntent = ReceiverManager.startTasks(this);
    }

    /**
     * destroyTasks:(后台任务注销). <br/>
     * date: 2014年11月5日 <br/>
     *
     * @author lg
     */
    private void destroyTasks() {
        ReceiverManager.endTasks(this, lstPdIntent);
        ReceiverManager.unRegisterReceivers(this, lstRcvr);
    }

    /**
     * TODO
     *
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        destroyTasks();
        super.onDestroy();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //点击返回键退出整个应用程序
            HDNotification.ClearNotifiaction();
            SystemApplication.getInstance().exitProcess();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void saveUserToSys(LoginUser loginUser) {
        PersonCard loginPsn = new PersonCard();
        loginPsn.setLoginid(loginUser.getUserid() + "");
        loginPsn.setPersonid_desc(loginUser.getUserName());
        loginPsn.setDepartment_desc(loginUser.getOrgName());
        loginPsn.setDepartment(loginUser.getOrgid()+"");
        SystemProperty.getSystemProperty().setLoginPerson(loginPsn);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    private class useNameTouchClick implements DrawEditText.ITouchOnClick {

        @Override
        public void touchOnClick(View view) {
            hideInputKeyboard(view);
            if (selectPopupWindow == null) {
                // 获取下拉框依附的组件宽度
                int width = nameEdit.getWidth();
                pwidth = width;
                // 初始化PopupWindow
                initPopuWindow();
            }
            // 显示PopupWindow窗口
            popupWindwShowing(view);

        }
    }

    public void hideInputKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void popupWindwShowing(View view) {
        initDatas();
        // 将selectPopupWindow作为parent的下拉框显示，并指定selectPopupWindow在Y方向上向上偏移3pix，
        selectPopupWindow.showAsDropDown(view, 0, 0);
    }

    private void initDatas() {
        List<LoginHistory> loginHistories = getLoginHistory();
        if (loginHistories != null && loginHistories.size() > 0) {
            datas.clear();
            for (LoginHistory history : loginHistories) {
                datas.add(history.getUsercode());
            }
            optionsAdapter.notifyDataSetChanged();
        } else
            ToastUtil.toast(getApplicationContext(), "暂无历史登录人");

    }

}
