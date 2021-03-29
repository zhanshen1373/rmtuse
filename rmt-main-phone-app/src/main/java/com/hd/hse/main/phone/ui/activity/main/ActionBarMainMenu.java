package com.hd.hse.main.phone.ui.activity.main;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.PaintDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.hd.hse.business.action.BusinessAction;
import com.hd.hse.common.component.phone.adapter.PopMenuAdapter;
import com.hd.hse.common.component.phone.adapter.PopMenuItem;
import com.hd.hse.common.component.phone.custom.MessageDialog;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.ui.activity.SystemApplication;
import com.hd.hse.constant.IActionType;
import com.hd.hse.main.business.main.MainActionListener;
import com.hd.hse.main.phone.R;
import com.hd.hse.main.phone.ui.activity.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * ClassName: ActionBarMainMenu (一级导航栏右侧菜单)<br/>
 * date: 2014年12月30日 <br/>
 *
 * @author wenlin
 */
public class ActionBarMainMenu {

    public Context context;

    protected PopupWindow popupWindow;
    private boolean isVersionUp = false; // 是否需要版本更新
    /**
     * itemNames:TODO(菜单项描述).
     */
    private String[] itemNames;
    private IEventListener listener;

    public ActionBarMainMenu(Context context, IEventListener listener) {
        this.context = context;
        this.listener = listener;
        initPopupWindow();
    }

    private void initPopupWindow() {
        // TODO Auto-generated method stub
        List<PopMenuItem> lstMenuItem = getListViewItem();
        PopMenuAdapter menuAdapter = new PopMenuAdapter(context, lstMenuItem);
        LayoutInflater inflater = LayoutInflater.from(context);
        View poupWindow = inflater
                .inflate(
                        R.layout.hd_hse_common_component_phone_actionbar_listview,
                        null);
        ListView lstView = (ListView) poupWindow
                .findViewById(R.id.hd_hse_common_phone_popmenu_listview);
        lstView.setAdapter(menuAdapter);
        lstView.setOnItemClickListener(itemClick);
        popupWindow = new PopupWindow(poupWindow,
                context.getResources().getDisplayMetrics().widthPixels * 2 / 5,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new PaintDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        adjustInitPopupWindow(lstView);
    }

    /**
     * 预留给子类，让子类可以调整 PopupWindow 的一些设置。
     */
    protected void adjustInitPopupWindow(ListView lstView) {

    }

    private OnItemClickListener itemClick = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> list, View view, int position,
                                long arg3) {
            // TODO Auto-generated method stub
            Intent intent = null;
            PopMenuItem operateItem = (PopMenuItem) list
                    .getItemAtPosition(position);
            String operteName = operateItem.getDescription();
            // 版本升级
            if (itemNames[4].equals(operteName)) {
                if (listener != null) {
                    try {
                        listener.eventProcess(IEventType.ACTION_UPDATE_VERSION, new Object());
                    } catch (HDException e) {
                        e.printStackTrace();
                    }
                }
            } else if (itemNames[5].equals(operteName)) {
                // 退出
                logout();
            } else if (itemNames[6].equals(operteName)) {
                goToSignActivity();
            } else if (itemNames[7].equals(operteName)) {
                if (listener != null) {
                    try {
                        listener.eventProcess(IEventType.ACTION_SYS, new Object());
                    } catch (HDException e) {
                        e.printStackTrace();
                    }
                }
            }

            popupWindow.dismiss();
        }

    };

    /**
     * 跳转到手写签名页面
     */
    private void goToSignActivity() {
        Intent intent = new Intent(context, SignActivity.class);
        context.startActivity(intent);
    }

    /**
     * logout:(安全退出). <br/>
     * date: 2015年1月5日 <br/>
     *
     * @author zhaofeng
     */
    protected void logout() {
        // TODO Auto-generated method stub
        MessageDialog.Builder builder = new MessageDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("您确定要退出?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActionListener actionListener = new MainActionListener();
                BusinessAction action = new BusinessAction(actionListener);
                try {
                    // 后台业务处理
                    action.action(IActionType.MAIN_LOGOUT);
                    //移除通知栏消息
                    NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                    nm.cancelAll();
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    // 前台处理
                    SystemApplication.getInstance().exit();
                } catch (HDException e) {
                    // TODO Auto-generated catch block
                    ToastUtils.toast(context, e.getMessage());
                }
                dialog.dismiss();
                // 设置你的操作事项
            }
        });
        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.createWarm().show();
    }

    protected List<PopMenuItem> getListViewItem() {
        // TODO Auto-generated method stub
        List<PopMenuItem> lstMenuItem = new ArrayList<PopMenuItem>();
        itemNames = context.getResources().getStringArray(
                R.array.hd_hse_main_phone_app_item);
        // 版本更新
        PopMenuItem versionUp = new PopMenuItem();
        versionUp.setDescription(itemNames[4]);
        versionUp
                .setDrawable(R.drawable.hd_hse_main_phone_app_item_icon_upversion);
        versionUp.setRemind(isVersionUp);
        lstMenuItem.add(versionUp);

        //手写签名
        PopMenuItem sign = new PopMenuItem();
        sign.setDescription(itemNames[6]);
        sign.setDrawable(R.drawable.hd_hse_main_phone_app_item_icon_sign);
        lstMenuItem.add(sign);
        // 扫一扫
//        PopMenuItem scan = new PopMenuItem();
//        scan.setDescription(itemNames[7]);
//        scan.setDrawable(R.drawable.hd_hse_main_phone_app_item_icon_scan);
//        lstMenuItem.add(scan);
        // 退出
        PopMenuItem out = new PopMenuItem();
        out.setDescription(itemNames[5]);
        out.setDrawable(R.drawable.hd_hse_main_phone_app_item_icon_goback);
        lstMenuItem.add(out);

        return lstMenuItem;
    }

    /**
     * showAsDropDown:(显示弹出框). <br/>
     * date: 2014年12月29日 <br/>
     *
     * @param view
     * @author wenlin
     */
    public void showAsDropDown(View view) {
        popupWindow.showAsDropDown(view, 0, 0);
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public boolean isVersionUp() {
        return isVersionUp;
    }

    public void setVersionUp(boolean isVersionUp) {
        this.isVersionUp = isVersionUp;
        initPopupWindow();
    }
}
