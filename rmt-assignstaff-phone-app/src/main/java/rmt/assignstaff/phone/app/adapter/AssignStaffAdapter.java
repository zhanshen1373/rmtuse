package rmt.assignstaff.phone.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hd.hse.entity.assign.RmtWorkAssign;

import java.util.HashMap;
import java.util.List;

import test.demo.rmt_assignstaff_phone_app.R;


/**
 * created by yangning on 2017/8/15 16:32.
 */

public class AssignStaffAdapter extends BaseAdapter {
    private List<RmtWorkAssign> data;
    private Context context;
    private LayoutInflater layoutInflater;
    private HashMap<Integer, Boolean> checkMap;

    public AssignStaffAdapter(List<RmtWorkAssign> data, Context context) {
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
     * 全选
     */
    public void selectAll() {
        for (int i = 0; i < data.size(); i++) {
            checkMap.put(i, true);
        }
        notifyDataSetChanged();
    }

    /**
     * 全不选
     */
    public void selectNone() {
        for (int i = 0; i < data.size(); i++) {
            checkMap.put(i, false);
        }
        notifyDataSetChanged();
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
            convertView = layoutInflater.inflate(R.layout.rmt_assignstaff_item_layout, null);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.rmt_assignstaff_item_check);
            viewHolder.tvGw = (TextView) convertView.findViewById(R.id.rmt_assignstaff_item_tv_gw);
            viewHolder.tvNames = (TextView) convertView.findViewById(R.id.rmt_assignstaff_item_tv_czr);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.rmt_assignstaff_item_tv_date);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.checkBox.setChecked(checkMap.get(position));
        viewHolder.tvGw.setText(data.get(position).getAppr_node_name());
        viewHolder.tvNames.setText(data.get(position).getAssignee_name());
        viewHolder.tvDate.setText(data.get(position).getEnd_time());
        //设置数据

        return convertView;
    }

    public class ViewHolder {
        private CheckBox checkBox;
        private TextView tvGw;
        private TextView tvNames;
        private TextView tvDate;

    }
}
