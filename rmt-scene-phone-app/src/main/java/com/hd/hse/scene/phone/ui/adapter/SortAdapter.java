package com.hd.hse.scene.phone.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.hd.hse.entity.resultdata.EqptType;
import com.hd.hse.entity.resultdata.WorkSite;

import org.hse.scene.phone.app.R;

import java.util.List;


public class SortAdapter<T> extends BaseAdapter implements SectionIndexer {
    private List<T> list = null;
    private Context mContext;


    public SortAdapter(Context mContext, List<T> list) {
        this.mContext = mContext;
        this.list = list;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;

        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.person_select_item, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
//            viewHolder.tvLetter.setText(eampositionmBean.getSortLetters());
            if (list.get(position) instanceof WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) {
                viewHolder.tvLetter.setText(((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) list.get(position)).getSortLetters());
            } else if (list.get(position) instanceof EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) {
                viewHolder.tvLetter.setText(((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) list.get(position)).getSortLetters());

            }


        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }

        if (list.get(position) instanceof WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) {

            if (((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) list.get(position)).getPosition_name() == null || "".equals(((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) list.get(position)).getPosition_name()))
                viewHolder.tvTitle.setText("未命名");
            else
                viewHolder.tvTitle.setText(((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) list.get(position)).getPosition_name());

        } else if (list.get(position) instanceof EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) {

            if (((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) list.get(position)).getDict_data_name() == null || "".equals(((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) list.get(position)).getDict_data_name()))
                viewHolder.tvTitle.setText("未命名");
            else
                viewHolder.tvTitle.setText(((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) list.get(position)).getDict_data_name());

        }

//        if (eampositionmBean.getPosition_name() == null || "".equals(eampositionmBean.getPosition_name()))
//            viewHolder.tvTitle.setText("未命名");
//        else
//            viewHolder.tvTitle.setText(eampositionmBean.getPosition_name());

        return view;

    }


    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {

        if (list.get(position) instanceof WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) {
            return ((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) list.get(position)).getSortLetters().charAt(0);

        } else if (list.get(position) instanceof EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) {
            return ((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) list.get(position)).getSortLetters().charAt(0);

        } else {
            return -1;
        }
//        return ((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) list.get(position)).getSortLetters().charAt(0);


    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = null;
            if (list.get(i) instanceof WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) {
                sortStr = ((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) list.get(i)).getSortLetters();

            } else if (list.get(i) instanceof EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) {
                sortStr = ((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) list.get(i)).getSortLetters();

            }
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}