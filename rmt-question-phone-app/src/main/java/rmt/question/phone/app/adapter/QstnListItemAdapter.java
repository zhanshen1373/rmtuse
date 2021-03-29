package rmt.question.phone.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hd.hse.entity.qstn.RmtQstnList;

import java.util.List;

import rmt.question.phone.app.R;

/**
 * created by yangning on 2017/6/6 10:51.
 */

public class QstnListItemAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<RmtQstnList> rmtQstnLists;
    public QstnListItemAdapter(Context context,List<RmtQstnList> rmtQstnLists){
    this.context=context;
        this.rmtQstnLists=rmtQstnLists;
        this.mLayoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return rmtQstnLists.size();
    }

    @Override
    public Object getItem(int position) {
        return rmtQstnLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(
                    R.layout.rmt_question_list_frag_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.tvContent = (TextView) convertView
                    .findViewById(R.id.hse_ss_phone_app_supervision_list_item_tv_content);
            mViewHolder.tvLocation = (TextView) convertView
                    .findViewById(R.id.hse_ss_phone_app_supervision_list_item_tv_location);
            mViewHolder.tvName = (TextView) convertView
                    .findViewById(R.id.hse_ss_phone_app_supervision_list_item_tv_name);
            mViewHolder.tvTime = (TextView) convertView
                    .findViewById(R.id.hse_ss_phone_app_supervision_list_item_tv_time);
            
            mViewHolder.divider = convertView
                    .findViewById(R.id.hse_ss_phone_app_supervision_list_item_divider);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.tvContent.setText(rmtQstnLists.get(position).getDescription());
        // 地点默认为海顿，地点没有上传
        if (rmtQstnLists.get(position).getQstn_unit_name() == null
                || "".equals(rmtQstnLists.get(position).getSite())) {
            mViewHolder.tvLocation.setText("责任单位 : " + "空");
        } else {
            mViewHolder.tvLocation.setText("责任单位 : "
                    + rmtQstnLists.get(position).getQstn_unit_name());
        }

        mViewHolder.tvName.setText("检查人 : "
                + rmtQstnLists.get(position).getInspector_name());
        mViewHolder.tvTime.setText(rmtQstnLists.get(position).getUpdated_dt());

        return convertView;
    }
    private class ViewHolder {
        private TextView tvContent;
        private TextView tvLocation;
        private TextView tvName;
        private TextView tvTime;
        private View divider;

    }
}
