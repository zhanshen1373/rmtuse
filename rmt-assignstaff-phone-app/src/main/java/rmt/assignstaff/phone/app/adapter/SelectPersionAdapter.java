package rmt.assignstaff.phone.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hd.hse.entity.assign.RmtAssignUser;

import java.util.HashMap;
import java.util.List;

import test.demo.rmt_assignstaff_phone_app.R;

/**
 * created by yangning on 2017/8/17 15:38.
 */


public class SelectPersionAdapter extends BaseAdapter {
    private List<RmtAssignUser> data;
    private Context context;
    private LayoutInflater layoutInflater;
    private HashMap<Integer, Boolean> checkMap;

    public SelectPersionAdapter(List<RmtAssignUser> data, Context context) {
        this.data = data;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        checkMap = new HashMap<>();
        initCheckMap();

    }

    public void initCheckMap() {
        for (int i = 0; i < data.size(); i++) {
            checkMap.put(i, false);
        }
    }

    /**
     * 得到点击item的check
     *
     * @param position
     * @return
     */
    public boolean getCurrentCheck(int position) {
        return checkMap.get(position);
    }

    /**
     * 设置点击item的check
     *
     * @param position
     * @param ischeck
     */
    public void setCurrentCheck(int position, boolean ischeck) {
        checkMap.put(position, ischeck);
        notifyDataSetChanged();
    }

    /**
     * 得到checkMap
     *
     * @return checkMap
     */
    public HashMap<Integer, Boolean> getCheckMap() {
        return checkMap;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.rmt_select_people_activity_item, null);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.rmt_select_people_activity_item_check);
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.rmt_select_people_activity_item_tv_id);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.rmt_select_people_activity_item_tv_name);
            viewHolder.tvDept = (TextView) convertView.findViewById(R.id.rmt_select_people_activity_item_tv_dept);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.checkBox.setChecked(checkMap.get(position));
        viewHolder.tvId.setText(data.get(position).getPerson_id() + "");
        viewHolder.tvName.setText(data.get(position).getPerson_name());
        viewHolder.tvDept.setText(data.get(position).getOrgname());
        //设置数据

        return convertView;
    }

    public class ViewHolder {
        private CheckBox checkBox;
        private TextView tvId;
        private TextView tvName;
        private TextView tvDept;

    }
}