package rmt.authorize.phone.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;
import com.hd.hse.entity.resultdata.RmtAuthorizeList;
import com.hd.hse.entity.resultdata.RmtBeAuthorizeList;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import rmt.authorize.phone.app.R;

public class WorkAuthorizeActivity extends BaseTaskListBusActivity {


    private List<RmtAuthorizeList.MainvoBean> mainvo;
    private List<RmtBeAuthorizeList.MainvoBean> beauthorizelist;
    private List<RmtBeAuthorizeList.DictvosBean.RmtGrtItemStatusBean> rmt_grt_item_status;


    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub
        return "作业授权";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "rmt-authorize-phone-app";
    }

    @Override
    public Class<?> getToActivityClass() {
        // TODO Auto-generated method stub
        return WorkAuthorizeDetailActivity.class;
    }

    @Override
    protected void setListener() {
        //新增数据
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkAuthorizeActivity.this, WorkAuthorizeDetailActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onItemClick(SuperEntity superEntity) {
        //查看记录
        if (superEntity instanceof RmtAuthorizeList.MainvoBean) {
            RmtAuthorizeList.MainvoBean rmtAuthorizeList = (RmtAuthorizeList.MainvoBean) superEntity;
            Intent intent = new Intent(WorkAuthorizeActivity.this, WorkAuthorizeDetailActivity.class);
            intent.putExtra("data", rmtAuthorizeList);
            startActivity(intent);
        } else if (superEntity instanceof RmtBeAuthorizeList.MainvoBean) {
            RmtBeAuthorizeList.MainvoBean rmtAuthorizeList = (RmtBeAuthorizeList.MainvoBean) superEntity;
            Intent intent = new Intent(WorkAuthorizeActivity.this, WorkAuthorizeMessageActivity.class);
            long grt_item_id = rmtAuthorizeList.getHeadVO().getRMT_WORK_GRANT_ITEM_M().getGrt_item_id();
            RmtWorkSubtask rmtWorkSubtask = new RmtWorkSubtask();
            rmtWorkSubtask.setWork_subtask_id(grt_item_id);
            intent.putExtra(FrameworkActivity.INTENT_WORKTASK_KEY,
                    rmtWorkSubtask);
            startActivity(intent);
        }

    }


    @Override
    public void setCustomActionBar(boolean isDrawerLayout, IEventListener eventLst, String[] actionFlags) {
        actionFlags = new String[]{IActionBar.TV_TITLE};
        super.setCustomActionBar(isDrawerLayout, eventLst, actionFlags);
    }

    @Override
    protected void onResume() {
        super.onResume();

        imageView.setVisibility(View.VISIBLE);
        authorizelistTab.setVisibility(View.VISIBLE);
        authorizelistZysq.setTextColor(getResources().getColor(R.color.hd_hse_common_component_phone_actionbar_background));
        authorizelistSqjl.setTextColor(getResources().getColor(R.color.black));
        requestData();

    }

    @Override
    protected void initData() {


        authorizelistZysq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
                authorizelistZysq.setTextColor(getResources().getColor(R.color.hd_hse_common_component_phone_actionbar_background));
                authorizelistSqjl.setTextColor(getResources().getColor(R.color.black));
                requestData();


            }
        });
        authorizelistSqjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.GONE);
                authorizelistSqjl.setTextColor(getResources().getColor(R.color.hd_hse_common_component_phone_actionbar_background));
                authorizelistZysq.setTextColor(getResources().getColor(R.color.black));
                requestBeAuthorizeData();

            }
        });


    }

    private void requestBeAuthorizeData() {

        final ArrayList<SuperEntity> list = new ArrayList<>();
        final ProgressDialog dialog = new ProgressDialog(WorkAuthorizeActivity.this);
        dialog.setMessage("正在查询");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<RmtBeAuthorizeList>> call = rmtInterface.getBeAuthorizeList(LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(WorkAuthorizeActivity.this, false, call, new HttpCallBack<RmtBeAuthorizeList>() {
            @Override
            public void success(RmtBeAuthorizeList resultDataBean) {
                dialog.dismiss();
                ListAdapter adapter = new BeAuthorizeAdapter();
                if (resultDataBean != null) {
                    beauthorizelist = resultDataBean.getMainvo();
                    RmtBeAuthorizeList.DictvosBean dictvos = resultDataBean.getDictvos();
                    if (dictvos != null) {
                        rmt_grt_item_status = dictvos.getRmt_grt_item_status();
                    }
                    setAdapter(adapter);
                }
                for (int i = 0; i < beauthorizelist.size(); i++) {
                    list.add(beauthorizelist.get(i));
                }
                setData(list);

            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(WorkAuthorizeActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(WorkAuthorizeActivity.this, "获取数据列表失败");
            }
        });

    }

    private void requestData() {

        final ArrayList<SuperEntity> list = new ArrayList<>();
        final ProgressDialog dialog = new ProgressDialog(WorkAuthorizeActivity.this);
        dialog.setMessage("正在查询");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<RmtAuthorizeList>> call = rmtInterface.getAuthorizeList(LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(WorkAuthorizeActivity.this, false, call, new HttpCallBack<RmtAuthorizeList>() {
            @Override
            public void success(RmtAuthorizeList resultDataBean) {
                dialog.dismiss();
                ListAdapter adapter = new Adapter();
                if (resultDataBean != null) {
                    mainvo = resultDataBean.getMainvo();
                    setAdapter(adapter);
                }
                for (int i = 0; i < mainvo.size(); i++) {
                    list.add(mainvo.get(i));
                }
                setData(list);

            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(WorkAuthorizeActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(WorkAuthorizeActivity.this, "获取数据列表失败");
            }
        });


    }

    class BeAuthorizeAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return beauthorizelist.size();
        }

        @Override
        public Object getItem(int position) {
            return beauthorizelist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = new ViewHolder();
            if (convertView == null) {
                convertView = View.inflate(WorkAuthorizeActivity.this, com.hd.hse.app.phone.res.R.layout.authorizelist, null);
                holder.tv1 = (TextView) convertView.findViewById(R.id.authorizelist_name);
                holder.tv2 = (TextView) convertView.findViewById(R.id.authorizelist_content);
                holder.tv3 = (TextView) convertView.findViewById(R.id.authorizelist_time);
                holder.tv4 = (TextView) convertView.findViewById(R.id.authorizelist_status);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            RmtBeAuthorizeList.MainvoBean mainvoBean = beauthorizelist.get(position);
            RmtBeAuthorizeList.MainvoBean.HeadVOBean headVO = mainvoBean.getHeadVO();
            RmtBeAuthorizeList.MainvoBean.HeadVOBean.RMTWORKGRANTITEMMBean rmt_work_grant_item_m = headVO.getRMT_WORK_GRANT_ITEM_M();
            holder.tv4.setVisibility(View.VISIBLE);
            if (rmt_work_grant_item_m != null) {

                RmtBeAuthorizeList.MainvoBean.HeadVOBean.RMTWORKGRANTITEMMBean.GrantvoBean grantvo = rmt_work_grant_item_m.getGrantvo();
                holder.tv1.setText(grantvo.getAuth());
                holder.tv2.setText("关闭作业环节授权与您");
                holder.tv3.setText(grantvo.getStarttime() + "至" + grantvo.getEndtime());
                for (int i = 0; i < rmt_grt_item_status.size(); i++) {
                    if (rmt_grt_item_status.get(i).getCode().equals(rmt_work_grant_item_m.getStatus())) {
                        holder.tv4.setText(rmt_grt_item_status.get(i).getName());
                    }
                }
            }


            return convertView;
        }


    }


    class Adapter extends BaseAdapter {


        @Override
        public int getCount() {
            return mainvo.size();
        }

        @Override
        public Object getItem(int position) {
            return mainvo.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = new ViewHolder();
            if (convertView == null) {
                convertView = View.inflate(WorkAuthorizeActivity.this, com.hd.hse.app.phone.res.R.layout.authorizelist, null);
                holder.tv1 = (TextView) convertView.findViewById(R.id.authorizelist_name);
                holder.tv2 = (TextView) convertView.findViewById(R.id.authorizelist_content);
                holder.tv3 = (TextView) convertView.findViewById(R.id.authorizelist_time);
                holder.tv4 = (TextView) convertView.findViewById(R.id.authorizelist_status);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            RmtAuthorizeList.MainvoBean mainvoBean = mainvo.get(position);
            RmtAuthorizeList.MainvoBean.HeadVOBean.RMTWORKGRANTMBean rmt_work_grant_m = mainvoBean.getHeadVO().getRMT_WORK_GRANT_M();
            holder.tv4.setVisibility(View.GONE);
            if (rmt_work_grant_m != null) {

                holder.tv1.setText(rmt_work_grant_m.getAuth());
                holder.tv2.setText("关闭作业环节授权给" + rmt_work_grant_m.getGrt_num() + "人");
                holder.tv3.setText(rmt_work_grant_m.getStarttime() + "至" + rmt_work_grant_m.getEndtime());
            }


            return convertView;
        }


    }

    static class ViewHolder {
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
    }

}
