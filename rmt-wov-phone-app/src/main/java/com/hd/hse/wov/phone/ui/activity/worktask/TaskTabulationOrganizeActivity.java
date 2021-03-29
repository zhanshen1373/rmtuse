package com.hd.hse.wov.phone.ui.activity.worktask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.PhoneEventType;
import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskAndItemListBusActivity;
import com.hd.hse.entity.workorder.RmtTaskListQuery;
import com.hd.hse.entity.workorder.RmtTaskOrganizeListQuery;
import com.hd.hse.entity.workorder.RmtWorkSubTaskBean;
import com.hd.hse.wov.phone.R;

import java.util.List;

import retrofit2.Call;

public class TaskTabulationOrganizeActivity extends BaseTaskAndItemListBusActivity {

    private ListView listView;
    private List<RmtTaskOrganizeListQuery> RmtTaskOrganizeList;



    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_task_tabulation_organize);
        listView = (ListView) findViewById(R.id.task_tabulation_organize_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(TaskTabulationOrganizeActivity.this,TaskTabulationActivityOnline.class);
                long territorial_unit_id = RmtTaskOrganizeList.get(position).getTerritorial_unit_id();
                intent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,getFunctionCode());
                intent.putExtra("searchid",territorial_unit_id);
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

        final android.app.ProgressDialog dialog = new android.app.ProgressDialog(TaskTabulationOrganizeActivity.this);
        dialog.setMessage("正在查询");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // 获取任务浏览
        Call<ResultData<List<RmtTaskOrganizeListQuery>>> call = rmtInterface.getTaskOrganizeListQuery(LoginUserRecord.getInstance().getUser().getUserid(), getFunctionCode());
        HttpBusiness.action(TaskTabulationOrganizeActivity.this, false, call, new HttpCallBack<List<RmtTaskOrganizeListQuery>>() {
            @Override
            public void success(final List<RmtTaskOrganizeListQuery> rmtTaskListQuery) {
                dialog.dismiss();

                if (rmtTaskListQuery != null && rmtTaskListQuery.size() > 0) {
                    RmtTaskOrganizeList=rmtTaskListQuery;
                    listView.setAdapter(new BaseAdapter() {
                        @Override
                        public int getCount() {
                            return RmtTaskOrganizeList.size();
                        }

                        @Override
                        public Object getItem(int position) {
                            return RmtTaskOrganizeList.get(position);
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
                                convertView = View.inflate(TaskTabulationOrganizeActivity.this, R.layout.singletvandbulebackground, null);
                                hd.tv = (TextView) convertView.findViewById(R.id.singletv);
                                convertView.setTag(hd);
                            } else {
                                hd = (ViewHold) convertView.getTag();
                            }
                            RmtTaskOrganizeListQuery rmtTaskOrganizeListQuery = RmtTaskOrganizeList.get(position);
                            hd.tv.setText(rmtTaskOrganizeListQuery.getTerritorial_unit_name()+"("+rmtTaskOrganizeListQuery.getNum()+"项)");

                            return convertView;
                        }
                    });

                }

            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(TaskTabulationOrganizeActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(TaskTabulationOrganizeActivity.this, "获取数据列表失败");
            }
        });
    }

    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub
        return "任务浏览";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "hse-wov-phone-app";
    }

    static class ViewHold {
        private TextView tv;
    }


}
