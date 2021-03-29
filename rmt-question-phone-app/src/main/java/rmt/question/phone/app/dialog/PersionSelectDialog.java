package rmt.question.phone.app.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hd.hse.common.component.phone.custom.SideBar;
import com.hd.hse.common.component.phone.util.CharacterParser;
import com.hd.hse.common.component.phone.util.PinyinComparator;
import com.hd.hse.entity.sys.RmtSysUser;

import java.util.Collections;
import java.util.List;

import rmt.question.phone.app.R;
import rmt.question.phone.app.adapter.SortAdapter;

/**
 * created by yangning on 2017/8/24 14:18.
 */

public class PersionSelectDialog {
    private AlertDialog dialog;
    private List<RmtSysUser> rmtSysUsers;
    private Activity activity;
    private View view;
    private ListView lv;
    private TextView tvDialog;//显示ABCD...
    private SideBar sideBar;//指示器
    private SortAdapter adapter;
    private AdapterView.OnItemClickListener listener;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    public PersionSelectDialog(List<RmtSysUser> rmtSysUsers, Activity activity) {
        this.rmtSysUsers = rmtSysUsers;
        this.activity = activity;
        initView();
        initData();
        initDialog();

    }

    public void setRmtSysUsers(List<RmtSysUser> rmtSysUsers) {
        this.rmtSysUsers = rmtSysUsers;
        filledData(rmtSysUsers);
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
        pinyinComparator = new PinyinComparator();

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
        filledData(rmtSysUsers);
        Collections.sort(rmtSysUsers, pinyinComparator);
        adapter = new SortAdapter(activity, rmtSysUsers);
        lv.setAdapter(adapter);
    }

    /**
     * 为ListView填充数据
     */
    private void filledData(List<RmtSysUser> rmtSysUsers) {
        for (RmtSysUser rmtSysUser : rmtSysUsers) {
            //汉字转换成拼音
            if (rmtSysUser.getPerson_name() == null) {
                rmtSysUser.setSortLetters("#");
            } else {
                String pinyin = characterParser.getSelling(rmtSysUser.getPerson_name());
                String sortString = pinyin.substring(0, 1).toUpperCase();

                // 正则表达式，判断首字母是否是英文字母
                if (sortString.matches("[A-Z]")) {
                    rmtSysUser.setSortLetters(sortString.toUpperCase());
                } else {
                    rmtSysUser.setSortLetters("#");
                }
            }

        }
    }
}
