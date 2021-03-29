package rmt.authorize.phone.app.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hd.hse.entity.resultdata.RmtAuthorizeListDetail;

import java.util.List;

import rmt.authorize.phone.app.R;


public class FcAdapter extends BaseAdapter {


    private Context mContext;
    private List<RmtAuthorizeListDetail.MainvoBean.BodyVOsBean.RMTWORKGRANTITEMMBeanX> list;
    private List<RmtAuthorizeListDetail.DictvosBean.RmtWorkGrantItemstatusBean> datas;


    public FcAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<RmtAuthorizeListDetail.MainvoBean.BodyVOsBean.RMTWORKGRANTITEMMBeanX> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setList(List<RmtAuthorizeListDetail.DictvosBean.RmtWorkGrantItemstatusBean> datas) {
        this.datas = datas;
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

        RmtAuthorizeListDetail.MainvoBean.BodyVOsBean.RMTWORKGRANTITEMMBeanX.HeadVOBean.RMTWORKGRANTITEMMBean rmt_work_grant_item_m = list.get(position).getHeadVO().getRMT_WORK_GRANT_ITEM_M();

        holder.name.setText(rmt_work_grant_item_m.getBe_authed());
        String status = rmt_work_grant_item_m.getStatus();
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getCode().equals(status)) {
                holder.status.setText(datas.get(i).getName());
            }
        }
        if (status.equals("appr")){
            holder.time.setVisibility(View.INVISIBLE);
        }else{
            holder.time.setVisibility(View.VISIBLE);
            if (status.equals("recover")){
                holder.time.setText(holder.status.getText() + "时间: " + rmt_work_grant_item_m.getRec_time());
            }else{
                holder.time.setText(holder.status.getText() + "时间: " + rmt_work_grant_item_m.getGrt_item_time());
            }
        }
        if (isEdit) {
            holder.status.setVisibility(View.INVISIBLE);
            holder.time.setVisibility(View.INVISIBLE);
            if (status.equals("appr") || status.equals("receive")) {
                holder.picture.setVisibility(View.VISIBLE);
            } else {
                holder.picture.setVisibility(View.INVISIBLE);
            }
            if (rmt_work_grant_item_m.getDataStatus() == 1) {
                holder.picture.setImageResource(R.drawable.hd_hse_common_msg_right);
            } else if (rmt_work_grant_item_m.getDataStatus() == 0) {
                holder.picture.setImageResource(R.drawable.workauthorize_nochoice);
            }
        } else {
            holder.status.setVisibility(View.VISIBLE);
            holder.picture.setVisibility(View.INVISIBLE);

        }

        return convertView;
    }

    private boolean isEdit;

    public void setEdit(boolean b) {
        isEdit = b;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        private TextView name;
        private TextView status;
        private TextView time;
        private ImageView picture;
    }
}
