package com.hd.hse.wov.phone.ui.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hd.hse.entity.assign.RmtAssignRecord;
import com.hd.hse.wov.phone.R;

import java.util.List;

/**
 * created by yangning on 2017/11/2 15:56.
 */

public class AssignRecordAdapter extends BaseAdapter {
    private List<RmtAssignRecord> rmtAssignRecords;
    private Context context;
    private LayoutInflater layoutInflater;

    public AssignRecordAdapter(List<RmtAssignRecord> rmtAssignRecords, Context context) {
        this.rmtAssignRecords = rmtAssignRecords;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return rmtAssignRecords.size();
    }

    @Override
    public Object getItem(int position) {
        return rmtAssignRecords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.assign_record_item, null);
            viewHolder = new ViewHolder();
            viewHolder.assignee_name = (TextView) convertView.findViewById(R.id.assign_record_item_assignee_name);
            viewHolder.relieved_time = (TextView) convertView.findViewById(R.id.assign_record_item_relieved_time);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.assignee_name.setText(rmtAssignRecords.get(position).getAssignee_name());
        viewHolder.relieved_time.setText(rmtAssignRecords.get(position).getRelieved_time());
        return convertView;
    }

    public class ViewHolder {
        TextView assignee_name;
        TextView relieved_time;
    }

}
