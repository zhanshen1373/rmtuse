package rmt.statistics.phone.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;

import java.util.ArrayList;
import java.util.List;

import rmt.statistics.phone.app.R;
import rmt.statistics.phone.app.adapter.MyBaseAdapter;

public class TaskTabulationActivity extends BaseTaskListBusActivity {

    private ListView listView;
    private List<String> data;
    private MyBaseAdapter myBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_tabulation);
        listView = (ListView) findViewById(R.id.statistics_listview);
        myBaseAdapter = new MyBaseAdapter(this, data);
        listView.setAdapter(myBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //需要传过去对应的url
                //传名称
                Intent intent = new Intent(TaskTabulationActivity.this, StatisticsActivity.class);
                intent.putExtra("position",position);
                if (position==0){
                    intent.putExtra("name","今日作业统计");
                }else if (position==1){
                    intent.putExtra("name","作业类型统计");
                }else if(position==2){
                    intent.putExtra("name","作业月度数量统计");
                }
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initView() {
//        super.initView();
        // 设置导航栏信息
        setCustomActionBar(true, eventListener, new String[]{
                IActionBar.TV_TITLE});
        // 设置导航栏标题
        setActionBartitleContent(getTitileName(), false);
        // 设置左侧模块选择抽屉
        setNavContent(getNavContentData(), getNavCurrentKey());
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        data.add("今日作业统计");
        data.add("作业类型统计");
        data.add("作业月度数量统计");

    }

    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub
        return "统计分析";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "rmt-statistics-phone-app";
    }

    @Override
    public Class<?> getToActivityClass() {
        // TODO Auto-generated method stub
        return StatisticsActivity.class;
    }

}
