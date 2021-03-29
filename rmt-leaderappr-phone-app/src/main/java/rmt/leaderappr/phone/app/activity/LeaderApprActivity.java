package rmt.leaderappr.phone.app.activity;

import com.hd.hse.common.module.phone.adapter.TaskListExpandAdapterextends;
import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskAndItemListBusActivity;
import com.hd.hse.entity.workorder.RmtTaskListQuery;
import com.hd.hse.entity.workorder.WorkTask;

import java.util.List;

import rmt.leaderappr.phone.app.adapter.TaskListExpandAdapter;

/**
 * 领导审批
 * created by yangning on 2018/3/15 14:48.
 */


public class LeaderApprActivity extends BaseTaskAndItemListBusActivity {
    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub

       return super.getTitileName();
//        return "领导审批";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "rmt-leaderappr-phone-app";
    }

    @Override
    protected boolean liBottomButtonIsVisible() {
        return true;
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
    protected TaskListExpandAdapterextends getTaskListAdapter() {
        return new TaskListExpandAdapter(this, getExpandableListView());
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
