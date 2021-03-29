/**
 * Project Name:hse-end-phone-app
 * File Name:EndTaskActivity.java
 * Package Name:hd.hse.end.phone.ui.activity
 * Date:2015年6月15日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 */

package hd.hse.end.phone.ui.activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.common.module.branch.ui.model.fragment.PromiseInfoFragment;
import com.hd.hse.constant.IConfigEncoding;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.entity.resultdata.RmtWorkSubtaskResltData;
import com.hd.hse.util.ServiceUtilTools;

import java.util.List;

import hd.hse.end.phone.ui.fragment.EndFragment;
import retrofit2.Call;

/**
 * ClassName:EndTaskActivity ().<br/>
 * Date: 2015年6月15日 <br/>
 *
 * @author zhaofeng
 * @see
 */
public class EndTaskActivity extends FrameworkActivity {

    private boolean isEndTask;
    public static final String ISENDTASK = "isEndTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //判断关闭主任务还是分项任务
        isEndTask=getIntent().getBooleanExtra(ISENDTASK,false);
        special();
        super.onCreate(savedInstanceState);
    }

    private boolean isEndTask() {
        return isEndTask;
    }

    private void setEndTask(boolean endTask) {
        isEndTask = endTask;
    }

    /**
     * special:(个性化设置). <br/>
     * date: 2015年6月8日 <br/>
     *
     * @author zhaofeng
     */
    private void special() {
        // setActionTitle("");
        setPopupItemsDesc(new String[]{IActionBar.ITEM_PHOTOGTAPH,
                IActionBar.ITEM_PHOTOMANAGER});
    }

    @Override
    public List<RmtConfMIntfc> SpecialConfigList(
            List<RmtConfMIntfc> list) {
        // TODO Auto-generated method stub
        List<RmtConfMIntfc> mergeList = ServiceUtilTools.mergeSameList(list, IConfigEncoding.GB_TYPE + "," + IConfigEncoding.QX_TYPE);
        for (RmtConfMIntfc pdaWorkOrderInfoConfig : mergeList) {
            if ((IConfigEncoding.GB_TYPE + "," + IConfigEncoding.QX_TYPE).contains(pdaWorkOrderInfoConfig.getTab_type() + "")) {
                pdaWorkOrderInfoConfig.setTab_name("任务结束");
            }
        }
        return mergeList;
    }

    @Override
    public Fragment configFragmentByPadType(List<RmtConfMIntfc> listConfig, RmtConfMIntfc config) {
        // TODO Auto-generated method stub
        BaseFragment fragment = null;
        switch (config.getTab_type()) {
            case IConfigEncoding.QX_TYPE:
            case IConfigEncoding.GB_TYPE:
                // 结束界面
                fragment = new EndFragment();
                List<RmtConfMIntfc> childlist = (List) config
                        .getChild(RmtConfMIntfc.class.getName());
                if (childlist != null)
                    fragment.setConfigList(childlist);
                ((EndFragment) fragment).setWorkTask(workTask);
                ((EndFragment) fragment).setEndTask(isEndTask());

                break;
            case IConfigEncoding.PROMISE_TYPE:
                // 承诺信息类别
                fragment = new PromiseInfoFragment<SuperEntity>();
                fragment.setPDAWorkOrderInfoConfig(config);
                break;
            default:
                break;
        }
        //lxf设置模块编码
        if (fragment != null)
            fragment.setModuleCode(function);
        return fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (getCurrentFocus() != null
                    && getCurrentFocus().getWindowToken() != null) {

                manager.hideSoftInputFromWindow(getCurrentFocus()
                                .getWindowToken(),

                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }

        return super.onTouchEvent(event);

    }

    @Override
    protected Call<ResultData<List<RmtWorkSubtaskResltData>>> getCall(RmtInterface rmtInterface) {
        if (task != null) {
            return rmtInterface.getTask(LoginUserRecord.getInstance().getUser().getUserid(), task.getWork_task_id(), function);
        } else {
            return rmtInterface.getRmtWorkSubtask(LoginUserRecord.getInstance().getUser().getUserid(), workTask.getWork_subtask_id(), function);
        }
    }
}
