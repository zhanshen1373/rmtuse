package rmt.statistics.phone.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;
import com.hd.hse.entity.workorder.StatisticsDept;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import rmt.statistics.phone.app.R;
import rmt.statistics.phone.app.adapter.ListViewAdapter;

public class DeptActivity extends BaseTaskListBusActivity {

    private ListView lv;
    private LinearLayout tab_container;
    private ListViewAdapter adapter;
    private List<StatisticsDept> deptList;
    private List<LinearLayout> linearLayouts;
    private Button button;
    private List<Long> OrgIdList = new ArrayList<>();
    private List<String> OrgNameList = new ArrayList<>();
    private boolean tag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        if (title.equals("作业类型统计") ||
                title.equals("作业月度数量统计")) {
            tag = true;
        } else {
            tag = false;
        }

        lv = (ListView) findViewById(R.id.lv);
        tab_container = (LinearLayout) findViewById(R.id.tab_container);
        button = (Button) findViewById(R.id.dept_button);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //还有下一层
                if (deptList.get(position).getIsleaf() == 0) {

                    addTabView(deptList.get(position).getCountOrgMVOList(), deptList.get(position).getOrgname());

                    deptList = deptList.get(position).getCountOrgMVOList();
                    adapter.setDeptList(deptList);
                    adapter.notifyDataSetChanged();
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OrgIdList.size() == 0) {
                    ToastUtil.toast(DeptActivity.this, "属地单位不能为空");
                    return;
                }
                if (tag) {
                    if (OrgIdList.size() > 1) {
                        ToastUtil.toast(DeptActivity.this, "属地单位数量不能超过1个");
                        return;
                    }
                }
                Intent intent = new Intent();
                intent.putExtra("dept", (Serializable) OrgIdList);
                intent.putExtra("name", (Serializable) OrgNameList);
                setResult(123, intent);
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        // 设置导航栏信息
        setCustomActionBar(false, eventListener, new String[]{
                IActionBar.TV_BACK, IActionBar.TV_TITLE});
        // 设置导航栏标题
        setActionBartitleContent(getTitileName(), false);
        // 设置左侧模块选择抽屉
        setNavContent(getNavContentData(), getNavCurrentKey());

    }

    @Override
    protected void initData() {

        final android.app.ProgressDialog dialog = new android.app.ProgressDialog(DeptActivity.this);
        dialog.setMessage("正在查询");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // 获取统计分析属地部门
        Call<ResultData<List<StatisticsDept>>> call = rmtInterface.getDept();
        HttpBusiness.action(DeptActivity.this, false, call, new HttpCallBack<List<StatisticsDept>>() {
            @Override
            public void success(List<StatisticsDept> statisticsDept) {
                dialog.dismiss();
                deptList = statisticsDept;

                linearLayouts = new ArrayList<>();
                addTabView(deptList, "组织");
                adapter = new ListViewAdapter(deptList, OrgIdList, OrgNameList, DeptActivity.this);
                lv.setAdapter(adapter);


            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(DeptActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(DeptActivity.this, "获取数据列表失败");
            }
        });

    }


    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub
        return "请选择";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "rmt-statistics-phone-app";
    }

    /**
     * 移除tab_container当前view之后的view
     */
    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            /*tab_container.get
            tab_container.removeViewAt();*/
            deptList = (List<StatisticsDept>) v.getTag();
            adapter.setDeptList(deptList);
            adapter.notifyDataSetChanged();
            //移除tab_container当前view之后的view
            boolean flag = false;
            for (int i = 0; i < linearLayouts.size(); i++) {
                LinearLayout linearLayout = linearLayouts.get(i);
                if (flag) {
                    tab_container.removeViewAt(i);
                    linearLayouts.remove(i);
                    i--;
                }
                if (linearLayout == v) {
                    flag = true;
                }

            }
        }
    }


    private void addTabView(List<StatisticsDept> deptList, String tabName) {
        //创建linearLayout
        LinearLayout linearLayout = new LinearLayout(DeptActivity.this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout.setTag(deptList);
        linearLayout.setOnClickListener(new MyOnClickListener());
        //创建TextView
        TextView tv = new TextView(DeptActivity.this);
        tv.setText(tabName);
        tv.setPadding(20, 15, 20, 15);
        linearLayout.addView(tv);
        //创建imageview
        ImageView imageView = new ImageView(DeptActivity.this);
        imageView.setImageResource(R.drawable.go_next);
        //imageView.
        linearLayout.addView(imageView);
        //把linearLayout加入到tab
        tab_container.addView(linearLayout);
        //把linearLayout加入到集合中
        linearLayouts.add(linearLayout);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
