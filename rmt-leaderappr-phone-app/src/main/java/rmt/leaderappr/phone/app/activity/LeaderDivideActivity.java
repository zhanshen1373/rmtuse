package rmt.leaderappr.phone.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskAndItemListBusActivity;

import java.util.ArrayList;
import java.util.List;

import rmt.leaderappr.phone.app.R;

public class LeaderDivideActivity extends BaseTaskAndItemListBusActivity {

    private ListView listView;
    private List<String> list;

    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub
        return "领导审批";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "rmt-leaderappr-phone-app";
    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_task_tabulation_organize);
        listView = (ListView) findViewById(R.id.task_tabulation_organize_listview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LeaderDivideActivity.this, LeaderApprActivity.class);
                if (position == 0) {
                    intent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY, getFunctionCode());
                } else {
                    intent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY, "xgf");
                }
                startActivity(intent);
                finish();
            }
        });
        setCustomActionBar(true, eventListener, new String[]{IActionBar.TV_TITLE});
        setActionBartitleContent(getTitileName(), false);
    }

    private IEventListener eventListener = new IEventListener() {

        @Override
        public void eventProcess(int eventType, Object... objects)
                throws HDException {

        }
    };


    @Override
    protected void initData() {
        list = new ArrayList<>();
        list.add("领导审批");
        list.add("相关方");
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHold hd = null;
                if (convertView == null) {
                    hd = new ViewHold();
                    convertView = View.inflate(LeaderDivideActivity.this, R.layout.singletvandbulebackground, null);
                    hd.tv = (TextView) convertView.findViewById(R.id.singletv);
                    convertView.setTag(hd);
                } else {
                    hd = (ViewHold) convertView.getTag();
                }

                hd.tv.setText(list.get(position));

                return convertView;
            }
        });
    }

    static class ViewHold {
        private TextView tv;
    }
}
