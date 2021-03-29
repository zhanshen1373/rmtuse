package rmt.leaderappr.phone.app.adapter;

import android.content.Context;
import android.widget.ExpandableListView;

import com.hd.hse.common.module.phone.adapter.TaskListExpandAdapterextends;

/**
 * created by yangning on 2017/7/6 14:32.
 */

public class TaskListExpandAdapter extends TaskListExpandAdapterextends {
    public TaskListExpandAdapter(Context context, ExpandableListView expandableListView) {
        super(context, expandableListView);
    }

    @Override
    protected boolean isShowChexBox() {
        return true;
    }

    @Override
    protected boolean isLeaderAppr() {
        return true;
    }

    @Override
    protected boolean isEditZyp() {
        return true;
    }
}
