package rmt.statistics.phone.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hd.hse.entity.workorder.StatisticsDept;

import java.util.List;

import rmt.statistics.phone.app.R;

/**
 * created by yangning on 2017/11/29 16:13.
 */

public class ListViewAdapter extends BaseAdapter {
    private List<StatisticsDept> deptList;
    private List<Long> idList;
    private List<String> describeList;

    public void setDeptList(List<StatisticsDept> deptList) {
        this.deptList = deptList;
    }

    private Context context;
    private LayoutInflater layoutInflater;

    public ListViewAdapter(List<StatisticsDept> deptList, List<Long> idlist, List<String> namelist,Context context) {
        this.deptList = deptList;
        this.context = context;
        this.idList = idlist;
        this.describeList=namelist;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return deptList == null ? 0 : deptList.size();
    }

    @Override
    public Object getItem(int i) {
        return deptList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.lv_item, null);
            viewHolder = new ViewHolder();
            viewHolder.checkBox = (CheckBox) view.findViewById(R.id.checkbox);
            viewHolder.reContainer = (RelativeLayout) view.findViewById(R.id.re_checkbox_container);
            viewHolder.tvName = (TextView) view.findViewById(R.id.tv_name);
            viewHolder.imgGoNext = (ImageView) view.findViewById(R.id.img_go_next);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.checkBox.setChecked(deptList.get(i).isSelected());
        viewHolder.tvName.setText(deptList.get(i).getOrgname());
        int visibility = deptList.get(i).getIsleaf() == 0 ? View.VISIBLE : View.INVISIBLE;
        viewHolder.imgGoNext.setVisibility(visibility);

        viewHolder.reContainer.setOnClickListener(new MyOnClickListener(viewHolder.checkBox, i));

        return view;
    }

    class MyOnClickListener implements View.OnClickListener {
        private CheckBox checkBox;
        private int position;

        MyOnClickListener(CheckBox checkBox, int position) {
            this.checkBox = checkBox;
            this.position = position;
        }

        @Override
        public void onClick(View v) {

            deptList.get(position).setSelected(!deptList.get(position).isSelected());
            checkBox.setChecked(deptList.get(position).isSelected());
            if (deptList.get(position).isSelected()) {
                idList.add(deptList.get(position).getOrgid());
                describeList.add(deptList.get(position).getOrgname());
            } else {
                idList.remove(deptList.get(position).getOrgid());
                describeList.remove(deptList.get(position).getOrgname());
            }

        }
    }

    class ViewHolder {
        private RelativeLayout reContainer;
        private CheckBox checkBox;
        private TextView tvName;
        private ImageView imgGoNext;

    }
}
