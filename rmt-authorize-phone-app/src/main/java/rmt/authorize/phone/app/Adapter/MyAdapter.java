package rmt.authorize.phone.app.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hd.hse.entity.resultdata.RmtAuthorizeBsqr;

import java.util.List;

import rmt.authorize.phone.app.R;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<RmtAuthorizeBsqr.MainvoBean> list;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<RmtAuthorizeBsqr.MainvoBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(mContext, com.hd.hse.app.phone.res.R.layout.bsqr, null);

            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.status = (TextView) convertView.findViewById(R.id.status);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.picture = (ImageView) convertView.findViewById(R.id.picture);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RmtAuthorizeBsqr.MainvoBean.HeadVOBean.SYUSERMQUERYBean sy_user_m_query = list.get(position).getHeadVO().getSY_USER_M_QUERY();
        holder.name.setText(sy_user_m_query.getUsername());
        holder.status.setVisibility(View.INVISIBLE);
        holder.time.setVisibility(View.INVISIBLE);
        holder.picture.setVisibility(View.VISIBLE);
        holder.picture.setImageResource(R.drawable.hd_cbs_icon_step_check_no_two);
        return convertView;
    }

    static class ViewHolder {
        private TextView name;
        private TextView status;
        private TextView time;
        private ImageView picture;
    }
}
