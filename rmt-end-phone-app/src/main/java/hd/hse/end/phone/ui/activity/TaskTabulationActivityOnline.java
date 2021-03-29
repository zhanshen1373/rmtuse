package hd.hse.end.phone.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.adapter.TaskListExpandAdapterextends;
import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskAndItemListBusActivity;
import com.hd.hse.constant.IWorkOrderStatus;
import com.hd.hse.entity.workorder.RmtTaskListQuery;
import com.hd.hse.entity.workorder.RmtWorkSubTaskBean;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.entity.workorder.RmtWorkTaskBean;
import com.hd.hse.entity.workorder.WorkTask;

import java.util.List;

import hd.hse.end.phone.ui.adapter.TaskListExpandAdapter;

import static com.hd.hse.common.module.phone.adapter.TaskListExpandAdapterextends.AGREE;
import static com.hd.hse.common.module.phone.adapter.TaskListExpandAdapterextends.DISAGREE;
import static com.hd.hse.common.module.phone.adapter.TaskListExpandAdapterextends.FCTAGREE;
import static com.hd.hse.common.module.phone.adapter.TaskListExpandAdapterextends.FCTDISAGREE;
import static hd.hse.end.phone.ui.activity.EndTaskActivity.ISENDTASK;

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
        return "任务结束";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "hse-end-phone-app";
    }

    @Override
    public Class<?> getToActivityClass() {
        // TODO Auto-generated method stub
        return EndTaskActivity.class;
    }

    @Override
    protected void onWorkListItemLongClick(WorkTask workTask) {
        //super.onWorkListItemLongClick(workTask);
        //注册掉长按点击事件
    }

    @Override
    protected void endSubTask(RmtWorkSubtask rmtWorkSubtask) {
        super.endSubTask(rmtWorkSubtask);
        //跳转到结束分项任务
        String leaderaudit = rmtWorkSubtask.getLeaderaudit();
        if (AGREE.equalsIgnoreCase(leaderaudit) || FCTAGREE.equalsIgnoreCase(leaderaudit)) {
            Intent intent = new Intent(this, getToActivityClass());
            Bundle bundle = new Bundle();
            bundle.putBoolean(ISENDTASK, false);
            bundle.putSerializable(FrameworkActivity.INTENT_WORKTASK_KEY,
                    rmtWorkSubtask);
            intent.putExtras(bundle);
            intent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                    getFunctionCode());

            startActivityForResult(intent, IEventType.WORKORDER_AUDIT);
        } else if (DISAGREE.equalsIgnoreCase(leaderaudit) || FCTDISAGREE.equalsIgnoreCase(leaderaudit)) {
            ToastUtil.toast(getApplicationContext(), "负责人审批为“不同意”，不能结束该任务");
        } else {
            ToastUtil.toast(getApplicationContext(), "负责人尚未审批，不能结束该任务");
        }


    }

    @Override
    protected void endWorkTask(RmtWorkTaskBean rmtWorkTaskBean) {
        super.endWorkTask(rmtWorkTaskBean);
        //跳转到结束主任务
        Intent intent = new Intent(this, getToActivityClass());
        Bundle bundle = new Bundle();
        bundle.putBoolean(ISENDTASK, true);
        bundle.putSerializable(FrameworkActivity.INTENT_TASK_KEY,
                rmtWorkTaskBean);
        intent.putExtras(bundle);
        intent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                getFunctionCode());
        startActivityForResult(intent, IEventType.WORKORDER_AUDIT);
    }

    @Override
    protected TaskListExpandAdapterextends getTaskListAdapter() {
        return new TaskListExpandAdapter(this, getExpandableListView());
    }

    /**
     * 过滤掉分项任务状态不为作业中的数据
     *
     * @param rmtmainvo
     */
    @Override
    protected void filterData(List<RmtTaskListQuery.MainvoBean> rmtmainvo) {
        super.filterData(rmtmainvo);
        for (int i = 0; i < rmtmainvo.size(); i++) {
            RmtTaskListQuery.MainvoBean mainvoBean = rmtmainvo.get(i);
            if (mainvoBean.getBodyVOs() != null && mainvoBean.getBodyVOs().getRMT_WORK_SUBTASK_M() != null) {
                List<RmtWorkSubTaskBean> rmtWorkTaskBeanList = mainvoBean.getBodyVOs().getRMT_WORK_SUBTASK_M();
                for (int j = 0; j < rmtWorkTaskBeanList.size(); j++) {
                    RmtWorkSubTaskBean rmtWorkSubTaskBean = rmtWorkTaskBeanList.get(j);
                    if (rmtWorkSubTaskBean.getHeadVO() != null && rmtWorkSubTaskBean.getHeadVO().getRMT_WORK_SUBTASK_M() != null &&
                            rmtWorkSubTaskBean.getHeadVO().getRMT_WORK_SUBTASK_M().getStatus() != null &&
                            !(rmtWorkSubTaskBean.getHeadVO().getRMT_WORK_SUBTASK_M().getStatus()).equalsIgnoreCase(IWorkOrderStatus.APPR)) {
                        rmtWorkTaskBeanList.remove(j);
                        j--;
                    }
                }
            }
        }
        //过滤掉没有结束主任务按钮，并且没有分项任务的住任务
        for (int i = 0; i < rmtmainvo.size(); i++) {
            RmtTaskListQuery.MainvoBean mainvoBean = rmtmainvo.get(i);


            if (mainvoBean.getHeadVO() == null
                    || mainvoBean.getHeadVO().getRMT_WORK_TASK_M() == null
                    || (mainvoBean.getBodyVOs() == null && !mainvoBean.getHeadVO().getRMT_WORK_TASK_M().isallowclose())) {
                rmtmainvo.remove(i);
                i--;
            }
        }
    }

}
