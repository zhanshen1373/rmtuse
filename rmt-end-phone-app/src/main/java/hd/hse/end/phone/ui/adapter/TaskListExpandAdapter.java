package hd.hse.end.phone.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;

import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.module.phone.adapter.TaskListExpandAdapterextends;

/**
 * created by yangning on 2017/7/6 14:32.
 */

public class TaskListExpandAdapter extends TaskListExpandAdapterextends {
    public TaskListExpandAdapter(Context context, ExpandableListView expandableListView) {
        super(context, expandableListView);
    }

    @Override
    protected int getLookDetialIEventType() {
        //结束分项任务
        return IEventType.ACTION_END_SUB_TASK;
    }

    protected int getEndTaskIEventType() {
        return IEventType.ACTION_END_WORK_TASK;
    }

    @Override
    protected String getLookDetiaName() {
        return "结束分项任务";
    }

    @Override
    protected void setRecyclerViewVisibility(View view) {
        view.setVisibility(View.GONE);
    }

    @Override
    protected void setCheckBoxVisibility(CheckBox view) {
        view.setVisibility(View.GONE);
    }

    @Override
    protected void setEndTaskViewVisibility(View view, boolean isAllowClose) {
        if (isAllowClose) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }

    }
}
