package com.hd.hse.common.component.phone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hd.hse.common.component.phone.R;
import com.hd.hse.entity.resultdata.RmtWorkGasDetectReslt;

import java.util.List;

/**
 * Created by liuting on 2017/5/5.
 */

public class ContinueHistoryAdapter extends BaseAdapter {

    private Context context;
    private List<RmtWorkGasDetectReslt.VoListBean> datas;

    public ContinueHistoryAdapter(Context context,
                                  List<RmtWorkGasDetectReslt.VoListBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int arg0) {
        return datas.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(
                    R.layout.hd_hse_gas_detect_history_nd_list_item, null);
            holder = new ViewHolder();
            holder.gasType = (TextView) view.findViewById(R.id.gas_type);
            holder.gasValue = (TextView) view.findViewById(R.id.gas_value);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.gasType.setText((String) datas.get(position).getGas_type_sub_name());
        holder.gasValue.setText((String) datas.get(position).getGas_value());
        return view;
    }

    static class ViewHolder {
        TextView gasType;
        TextView gasValue;
    }

}
