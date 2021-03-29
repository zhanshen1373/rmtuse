package com.hd.hse.wov.phone.ui.activity.worktask;

import android.content.Intent;
import android.os.Bundle;

import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskAndItemListBusActivity;
import com.hd.hse.entity.workorder.RmtTaskListQuery;
import com.hd.hse.entity.workorder.WorkTask;

import java.util.List;

/**
 * ClassName: TaskTabulationActivityOnline ()<br/>
 * date: 2015年6月12日  <br/>
 *
 * @author Administrator
 */
public class TaskTabulationActivityOnline extends BaseTaskAndItemListBusActivity {
    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub
        return "任务浏览";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "hse-wov-phone-app";
    }

    @Override
    protected boolean liBottomButtonIsVisible() {
        return false;
    }

    @Override
    public Class<?> getToActivityClass() {
        return WovTaskActivity.class;
    }

    @Override
    protected void onWorkListItemLongClick(WorkTask workTask) {
        //super.onWorkListItemLongClick(workTask);
        //注册掉长按点击事件
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent!=null){
            long searchid = intent.getLongExtra("searchid", -1);
            setSearchId(searchid);
        }
        super.onCreate(savedInstanceState);
    }

    /**
     * 过滤掉没有分项任务的主任务
     *
     * @param rmtmainvo
     */
    @Override
    protected void filterData(List<RmtTaskListQuery.MainvoBean> rmtmainvo) {
        super.filterData(rmtmainvo);
        for (int i = 0; i < rmtmainvo.size(); i++) {
            RmtTaskListQuery.MainvoBean mainvoBean = rmtmainvo.get(i);
            if (mainvoBean.getBodyVOs() == null) {
                rmtmainvo.remove(i);
                i--;
            }
        }
    }

}
