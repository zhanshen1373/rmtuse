package rmt.question.phone.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hd.hse.entity.workorder.BaseItem;

import java.util.ArrayList;
import java.util.List;

import rmt.question.phone.app.R;

/**
 * created by yangning on 2019/10/31 16:35.
 */
public class TermSelectorAdapter extends BaseAdapter {
    private List<? extends BaseItem> data = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    public TermSelectorAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<? extends BaseItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
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
            convertView = inflater.inflate(R.layout.base_list_item, null);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvContent.setText(data.get(position).getItemContent());
        return convertView;
    }

    class ViewHolder {
        TextView tvContent;
    }
}
