package rmt.statistics.phone.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rmt.statistics.phone.app.R;

public class WorkerOrderTypeActivity extends BaseTaskListBusActivity {


    private ListView listView;
    private List<String> worktypedesc;
    private Button sureButton;

    //装编码的集合
    private List<String> worktype;
    //描述的集合
    private List<String> worktypems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_order_type);

        worktypedesc = new ArrayList<>();
        worktype = new ArrayList<>();
        worktypems = new ArrayList<>();

        worktypedesc.add("动火作业"); //hot
        worktypedesc.add("一般作业"); //gen
        worktypedesc.add("受限空间");  //cfd
        worktypedesc.add("高处作业");  //high
        worktypedesc.add("吊装作业"); //hst
        worktypedesc.add("管线作业");//ppl
        worktypedesc.add("挖掘作业"); //dig
        worktypedesc.add("临时用电"); //elec

        // 没有射线作业

        sureButton = (Button) findViewById(R.id.worker_order_type_button);
        listView = (ListView) findViewById(R.id.worker_order_type_listview);

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return worktypedesc.size();
            }

            @Override
            public Object getItem(int position) {
                return worktypedesc.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                ViewHold vh = null;
                if (convertView == null) {
                    vh = new ViewHold();
                    convertView = View.inflate(WorkerOrderTypeActivity.this, R.layout.workerorder_type_item, null);
                    vh.checkBox = (CheckBox) convertView.findViewById(R.id.worker_order_type_item_checkbox);
                    vh.textView = (TextView) convertView.findViewById(R.id.worker_order_type_item_textview);
                    convertView.setTag(vh);
                } else {
                    vh = (ViewHold) convertView.getTag();
                }

                vh.textView.setText(worktypedesc.get(position));

                return convertView;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.worker_order_type_item_checkbox);
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);

                    switch (position) {
                        case 0:
                            worktype.remove("hot");
                            worktypems.remove("动火作业");
                            break;
                        case 1:
                            worktype.remove("gen");
                            worktypems.remove("一般作业");
                            break;
                        case 2:
                            worktype.remove("cfd");
                            worktypems.remove("受限空间");
                            break;
                        case 3:
                            worktype.remove("high");
                            worktypems.remove("高处作业");
                            break;
                        case 4:
                            worktype.remove("hst");
                            worktypems.remove("吊装作业");
                            break;
                        case 5:
                            worktype.remove("ppl");
                            worktypems.remove("管线作业");
                            break;
                        case 6:
                            worktype.remove("dig");
                            worktypems.remove("挖掘作业");
                            break;
                        case 7:
                            worktype.remove("elec");
                            worktypems.remove("临时用电");
                            break;
                        default:
                            break;
                    }

                } else {
                    checkBox.setChecked(true);

                    switch (position) {

                        case 0:
                            worktype.add("hot");
                            worktypems.add("动火作业");
                            break;
                        case 1:
                            worktype.add("gen");
                            worktypems.add("一般作业");
                            break;
                        case 2:
                            worktype.add("cfd");
                            worktypems.add("受限空间");
                            break;
                        case 3:
                            worktype.add("high");
                            worktypems.add("高处作业");
                            break;
                        case 4:
                            worktype.add("hst");
                            worktypems.add("吊装作业");
                            break;
                        case 5:
                            worktype.add("ppl");
                            worktypems.add("管线作业");
                            break;
                        case 6:
                            worktype.add("dig");
                            worktypems.add("挖掘作业");
                            break;
                        case 7:
                            worktype.add("elec");
                            worktypems.add("临时用电");
                            break;
                        default:
                            break;
                    }

                }
            }
        });

        sureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("workerorderbm", (Serializable) worktype);
                intent.putExtra("workerorderms", (Serializable) worktypems);
                setResult(234, intent);
                finish();
            }
        });

    }

    static class ViewHold {
        CheckBox checkBox;
        TextView textView;

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
    public String getTitileName() {
        // TODO Auto-generated method stub
        return "请选择";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "rmt-statistics-phone-app";
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initData() {

    }
}
