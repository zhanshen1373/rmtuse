package com.hd.hse.common.component.phone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hd.hse.common.component.phone.R;
import com.hd.hse.entity.resultdata.RmtWorkGasDetectReslt;

import java.util.List;

/**
 * Created by liuting on 2017/5/5.
 */

public class GasAdapter extends BaseAdapter {

    private ListView mListView;
    private Context mContext;
    private List<RmtWorkGasDetectReslt> rmtWorkGasDetectResltList;
    public GasAdapter(ListView listView, List<RmtWorkGasDetectReslt> rmtWorkGasDetectResltList) {

        mListView = listView;
        this.rmtWorkGasDetectResltList = rmtWorkGasDetectResltList;
        mContext = listView.getContext();
        //mListView.setSelector(R.drawable.blank_selector);
//        mListView.setPadding(0, 5, 0, 5);
//        mListView.setDivider(mContext.getResources().getDrawable(
//                R.drawable.divider_10));
//        mListView.setDividerHeight(10);
        mListView.setAdapter(this);


    }

    @Override
    public int getCount() {
        if (rmtWorkGasDetectResltList != null) {
            return rmtWorkGasDetectResltList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (rmtWorkGasDetectResltList != null) {
            return rmtWorkGasDetectResltList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = View.inflate(mContext,
                    R.layout.hd_hse_gas_detect_activity_history_item, null);

            holder = new ViewHolder();
            holder.addrTxt = (TextView) convertView
                    .findViewById(R.id.gas_history_addr);
            holder.timeTxt = (TextView) convertView
                    .findViewById(R.id.gas_history_time);
            holder.isOkTxt = (TextView) convertView
                    .findViewById(R.id.gas_history_isok);
            holder.concentrationListView = (ListView) convertView
                    .findViewById(R.id.gas_history_concentration_listview);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.addrTxt.setText(rmtWorkGasDetectResltList.get(position).getDetect_site());
        holder.timeTxt.setText(rmtWorkGasDetectResltList.get(position).getDetect_time());
        holder.isOkTxt.setText(rmtWorkGasDetectResltList.get(position).getQualified() == 1 ? "合格" : "不合格");
        if (rmtWorkGasDetectResltList.get(position).getQualified() == 1) {
            holder.isOkTxt.setTextColor(mContext.getResources().getColor(
                    R.color.hd_hse_common_green));
        } else {
            holder.isOkTxt.setTextColor(mContext.getResources().getColor(
                    R.color.hd_hse_common_red));
        }
        holder.concentrationListView.setAdapter(new ContinueHistoryAdapter(
                mContext, rmtWorkGasDetectResltList.get(position).getVoList()));

        return convertView;
    }

    static class ViewHolder {
        TextView addrTxt;
        TextView timeTxt;
        TextView isOkTxt;
        ListView concentrationListView;

    }
}
