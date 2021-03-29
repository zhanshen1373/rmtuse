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

public class OtherAdapter extends BaseAdapter {

    private Context mContext;
    private List<RmtAuthorizeBsqr.MainvoBean> list;


    public OtherAdapter(Context mContext) {
        this.mContext = mContext;
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
            convertView = View.inflate(mContext, R.layout.otheradapter, null);
            holder.otheradapterTv = (TextView) convertView.findViewById(R.id.otheradapter_tv);
            holder.otheradapterRb = (ImageView) convertView.findViewById(R.id.otheradapter_rb);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RmtAuthorizeBsqr.MainvoBean mainvoBean = list.get(position);
        RmtAuthorizeBsqr.MainvoBean.HeadVOBean.SYUSERMQUERYBean sy_user_m_query = mainvoBean.getHeadVO().getSY_USER_M_QUERY();
        holder.otheradapterTv.setText(sy_user_m_query.getUsername());
        if (sy_user_m_query.getDataStatus() == 0) {
            holder.otheradapterRb.setImageResource(R.drawable.workauthorize_nochoice);
        } else if (sy_user_m_query.getDataStatus() == 1) {
            holder.otheradapterRb.setImageResource(R.drawable.hd_hse_common_msg_right);
        }


        return convertView;
    }

    public void setData(List<RmtAuthorizeBsqr.MainvoBean> mainvo) {
        this.list = mainvo;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        private TextView otheradapterTv;
        private ImageView otheradapterRb;
    }

}
