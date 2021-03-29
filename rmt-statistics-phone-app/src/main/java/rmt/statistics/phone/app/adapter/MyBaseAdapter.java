package rmt.statistics.phone.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import rmt.statistics.phone.app.R;

/**
 * Created by dubojian on 2017/11/30.
 */

public class MyBaseAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> data;

    public MyBaseAdapter(Context mContext, List<String> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold vh = null;
        if (convertView == null) {
            vh = new ViewHold();
            convertView = View.inflate(mContext, R.layout.statistics, null);
            vh.textView = (TextView) convertView.findViewById(R.id.statistics_text);
            convertView.setTag(vh);
        } else {
            vh = (ViewHold) convertView.getTag();
        }

        vh.textView.setText(data.get(position).toString());
        return convertView;
    }

  static  class ViewHold{
      private TextView textView;
  }
}
