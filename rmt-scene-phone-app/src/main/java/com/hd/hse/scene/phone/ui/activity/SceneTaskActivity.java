/**
 * Project Name:hse-scene-phone-app
 * File Name:SceneTaskActivity.java
 * Package Name:com.hd.hse.scene.phone.ui.activity
 * Date:2015年6月8日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 */

package com.hd.hse.scene.phone.ui.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.common.module.branch.ui.model.fragment.MeasureFragment;
import com.hd.hse.common.module.branch.ui.model.fragment.PromiseInfoFragment;
import com.hd.hse.constant.IConfigEncoding;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.scene.phone.ui.fragment.DiaoZhuangFragment;
import com.hd.hse.scene.phone.ui.fragment.GasDetectInfoFragment;
import com.hd.hse.scene.phone.ui.fragment.MeasureHarmFragment;
import com.hd.hse.scene.phone.ui.fragment.TemPressureFragment;
import com.hd.hse.scene.phone.ui.fragment.TempEleFragment;

import java.util.ArrayList;
import java.util.List;

import static com.hd.hse.common.module.branch.ui.model.activity.CancleWorkSubTaskActivity.CANCLESUCESS;

/**
 * ClassName:SceneTaskActivity ().<br/>
 * Date:     2015年6月8日  <br/>
 *
 * @author zhaofeng
 * @see
 */
public class SceneTaskActivity extends FrameworkActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        special();
    }

    /**
     * special:(个性化设置). <br/>
     * date: 2015年6月8日 <br/>
     *
     * @author zhaofeng
     */
    private void special() {
//		setActionTitle("");
        if (function.equals("review"))
            setPopupItemsDesc(new String[]{
                    IActionBar.ITEM_PHOTOGTAPH,
                    IActionBar.ITEM_PHOTOMANAGER,
                    IActionBar.ITEM_CANNEL
                    });
//                先把交接班二维码隐藏了
//        IActionBar.ITEM_JjbQrCode
        else
            setPopupItemsDesc(new String[]{
                    IActionBar.ITEM_PHOTOGTAPH,
                    IActionBar.ITEM_PHOTOMANAGER,
                    IActionBar.ITEM_CANNEL});

    }

    @Override
    public Fragment configFragmentByPadType(List<RmtConfMIntfc> listConfig, RmtConfMIntfc config) {
        // TODO Auto-generated method stub
        BaseFragment fragment = null;
        switch (config.getTab_type()) {
            case IConfigEncoding.HARM_TYPE:
                //危害
                fragment = new MeasureHarmFragment();
                break;
            case IConfigEncoding.MEASURE_TYPE:
                //措施
                fragment = new MeasureFragment<>();// new MeasureHarmFragment();// MeasureFragment<>();
                break;
            case IConfigEncoding.PROMISE_TYPE:
                //承诺信息类别
                fragment = new PromiseInfoFragment<>();
                break;
            case IConfigEncoding.TEMPELE_TYPE:
                //临时用电
                fragment = new TempEleFragment();
                break;
            case IConfigEncoding.Gas_Detect_TYPE:
                //气体检测
                fragment = new GasDetectInfoFragment();
                break;
            case IConfigEncoding.DiaoZhuang_Detail_TYPE:
                //吊装信息
                fragment = new DiaoZhuangFragment();
                break;
            case IConfigEncoding.TEMPERATURE_PRESSURE_TYPE:
                //设备温度压力
                fragment = new TemPressureFragment();
                break;
            default:
                break;
        }
        fragment.setPDAWorkOrderInfoConfig(config);
        fragment.setConfigList(listConfig);
        fragment.setRmtWorkSubtask(workTask);

        //lxf设置模块编码
        fragment.setModuleCode(function);
        return fragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == CANCLESUCESS)
            finish();
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    public interface MyTouchListener {
        public void onTouchEvent(MotionEvent event,View view);
    }

    // 保存MyTouchListener接口的列表
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MyTouchListener>();

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     * @param listener
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     * @param listener
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove( listener );
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners) {
            View view = this.getCurrentFocus();
            if (view != null && view instanceof EditText) {
                int[] location = {0, 0};
                view.getLocationInWindow(location);
                int left = location[0], top = location[1], right = left
                        + view.getWidth(), bootom = top + view.getHeight();
                // 判断焦点位置坐标是否在空间内，如果位置在控件外，则隐藏键盘
                if (ev.getRawX() < left || ev.getRawX() > right
                        || ev.getY() < top || ev.getRawY() > bootom) {
                    listener.onTouchEvent(ev,view);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}

