package rmt.delay.phone.app.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hd.hse.entity.resultdata.RmtDelayTaskHistoryList;

import java.util.List;

import rmt.delay.phone.app.R;

/**
 * Created by dbj on 2019/7/30.
 */

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<RmtDelayTaskHistoryList.MainvoBean> mainvo;
    private List<RmtDelayTaskHistoryList.DictvosBean.RMTWORKDELAYRECORDAUDITSTATUSMBean> rmt_work_delay_record_audit_status_m;
    private boolean isShr;

    public ListViewAdapter(Context mContext, List<RmtDelayTaskHistoryList.MainvoBean> mainvo, List<RmtDelayTaskHistoryList.DictvosBean.RMTWORKDELAYRECORDAUDITSTATUSMBean> rmt_work_delay_record_audit_status_m,
                           boolean isShr) {
        this.mContext = mContext;
        this.mainvo = mainvo;
        this.rmt_work_delay_record_audit_status_m = rmt_work_delay_record_audit_status_m;
        this.isShr = isShr;
    }

    @Override
    public int getCount() {
        return mainvo != null && mainvo.size() > 0 ? mainvo.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mainvo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.delaytasklist, null);
            holder.tv1 = (TextView) convertView.findViewById(R.id.delaytasklist_zxr);
            holder.tv2 = (TextView) convertView.findViewById(R.id.delaytasklist_yqz);
            holder.tv3 = (TextView) convertView.findViewById(R.id.delaytasklist_spzt);
            holder.tv4 = (TextView) convertView.findViewById(R.id.delaytasklist_yqyy);
            holder.tv5 = (TextView) convertView.findViewById(R.id.delaytasklist_pzr);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.delaytasklist_linear);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RmtDelayTaskHistoryList.MainvoBean mainvoBean = mainvo.get(position);
        if (mainvoBean != null) {
            if (mainvoBean.getHeadVO() != null) {
                RmtDelayTaskHistoryList.MainvoBean.HeadVOBean.RmtWdrList rmt_work_delay_record_m = mainvoBean.getHeadVO().getRMT_WORK_DELAY_RECORD_M();
                if (rmt_work_delay_record_m != null) {
                    holder.tv1.setText(rmt_work_delay_record_m.getApply_person());
                    holder.tv2.setText(rmt_work_delay_record_m.getDelay_time());
                    for (int i = 0; i < rmt_work_delay_record_audit_status_m.size(); i++) {
                        if (rmt_work_delay_record_audit_status_m.get(i).getCode().equals(rmt_work_delay_record_m.
                                getAudit_status())) {
                            holder.tv3.setText(rmt_work_delay_record_audit_status_m.get(i).getName());
                            break;
                        }
                    }
                    holder.tv4.setText(rmt_work_delay_record_m.getDelay_reason());
                    if (isShr){
                        holder.tv5.setText(LoginUserRecord.getInstance().getUser().getUserName());
                    }else{
                        holder.linearLayout.setVisibility(View.GONE);
                    }



                }
            }
        }

        return convertView;
    }

    static class ViewHolder {
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private LinearLayout linearLayout;
    }
}
