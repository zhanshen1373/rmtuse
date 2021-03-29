package com.hd.hse.scene.phone.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hd.hse.common.component.phone.custom.SideBar;
import com.hd.hse.common.component.phone.util.CharacterParser;
import com.hd.hse.entity.resultdata.EqptType;
import com.hd.hse.entity.resultdata.WorkSite;
import com.hd.hse.scene.phone.ui.adapter.SortAdapter;
import com.hd.hse.scene.phone.ui.utils.PinyinComparator;

import org.hse.scene.phone.app.R;

import java.util.Collections;
import java.util.List;


/**
 * created by dubojian on 2018/6/8 14:18.
 */

public class PersionSelectDialog<T> {
    private AlertDialog dialog;
    private List<T> rmtSysUsers;
    private Activity activity;
    private View view;
    private ListView lv;
    private TextView tvDialog;//显示ABCD...
    private SideBar sideBar;//指示器
    private SortAdapter adapter;
    private AdapterView.OnItemClickListener listener;

    private List<T> values;

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator<T> pinyinComparator;

    public PersionSelectDialog(List<T> rmtSysUsers, Activity activity) {
        this.values = rmtSysUsers;
        this.activity = activity;
        initView();
        initData();
        initDialog();

    }

    public void setRmtSysUsers(List<T> rmtSysUsers) {
        this.values = rmtSysUsers;
        filledData(values);
        Collections.sort(rmtSysUsers, pinyinComparator);
        adapter.updateListView(rmtSysUsers);
        lv.setSelection(0);

    }

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view);
        dialog = builder.show();
    }

    /**
     * 设置点击监听
     */
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
        if (lv != null && listener != null) {
            lv.setOnItemClickListener(listener);
        }
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    private void initView() {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator<T>();

        view = LayoutInflater.from(activity).inflate(R.layout.question_select_persion_layout, null);
        lv = (ListView) view.findViewById(R.id.country_lvcountry);
        tvDialog = (TextView) view.findViewById(R.id.dialog);
        sideBar = (SideBar) view.findViewById(R.id.sidrbar);
        sideBar.setTextView(tvDialog);

        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    lv.setSelection(position);
                }
            }
        });
    }

    private void initData() {
        filledData(values);
        Collections.sort(values, pinyinComparator);
        adapter = new SortAdapter(activity, values);
        lv.setAdapter(adapter);
    }

    /**
     * 为ListView填充数据
     */
    private void filledData(List<T> rmtSysUsers) {
        for (T rmtSysUser : rmtSysUsers) {
            //汉字转换成拼音
            if (rmtSysUser instanceof WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) {

                if (((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) rmtSysUser).getPosition_name() == null) {
                    ((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) rmtSysUser).setSortLetters("#");
                } else {
                    String pinyin = characterParser.getSelling(((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) rmtSysUser).getPosition_name());
                    String sortString = pinyin.substring(0, 1).toUpperCase();

                    // 正则表达式，判断首字母是否是英文字母
                    if (sortString.matches("[A-Z]")) {
                        ((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) rmtSysUser).setSortLetters(sortString.toUpperCase());

                    } else {

                        ((WorkSite.MainvoBean.HeadVOBean.EAMPOSITIONMBean) rmtSysUser).setSortLetters("#");

                    }
                }
            }else if (rmtSysUser instanceof EqptType.MainvoBean.HeadVOBean.SYDICTDATABean){

                if (((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) rmtSysUser).getDict_data_name() == null) {
                    ((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) rmtSysUser).setSortLetters("#");
                } else {
                    String pinyin = characterParser.getSelling(((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) rmtSysUser).getDict_data_name());
                    String sortString = pinyin.substring(0, 1).toUpperCase();

                    // 正则表达式，判断首字母是否是英文字母
                    if (sortString.matches("[A-Z]")) {
                        ((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) rmtSysUser).setSortLetters(sortString.toUpperCase());

                    } else {

                        ((EqptType.MainvoBean.HeadVOBean.SYDICTDATABean) rmtSysUser).setSortLetters("#");

                    }
                }

            }


        }
    }
}
