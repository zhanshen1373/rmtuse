package rmt.zsc.phone.app.activity;

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
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListBusActivity;
import com.hd.hse.entity.resultdata.ZscBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import rmt.zsc.phone.app.R;

public class ZscListActivity extends BaseTaskListBusActivity {


    private List<ZscBean.MainvoBean> mainvo;

    @Override
    public String getTitileName() {
        // TODO Auto-generated method stub
        return "知识窗";
    }

    @Override
    public String getNavCurrentKey() {
        // TODO Auto-generated method stub
        return "rmt-zsc-phone-app";
    }

    @Override
    public Class<?> getToActivityClass() {
        // TODO Auto-generated method stub
        return ZscDetailActivity.class;
    }

    @Override
    public void setCustomActionBar(boolean isDrawerLayout, IEventListener eventLst, String[] actionFlags) {
        actionFlags = new String[]{IActionBar.TV_TITLE};
        super.setCustomActionBar(isDrawerLayout, eventLst, actionFlags);
    }

    @Override
    protected void onItemClick(SuperEntity superEntity) {

        ZscBean.MainvoBean mainvoBean= (ZscBean.MainvoBean) superEntity;
        Intent intent = new Intent(this, ZscDetailActivity.class);
        intent.putExtra("data", mainvoBean);
        startActivity(intent);


    }

    @Override
    protected void initData() {


        final ArrayList<SuperEntity> list = new ArrayList<>();
        final ProgressDialog dialog = new ProgressDialog(ZscListActivity.this);
        dialog.setMessage("正在查询");
        dialog.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", "insp");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String s = jsonObject.toString();
//        RetrofitUtil.getInstance().setBaseUrl("http://192.168.6.181:21070/");
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<ZscBean>> call = rmtInterface.getZscList(s);

        HttpBusiness.action(ZscListActivity.this, false, call, new HttpCallBack<ZscBean>() {
            @Override
            public void success(ZscBean zscBean) {
                dialog.dismiss();

                if (zscBean != null) {
                    mainvo = zscBean.getMainvo();

                    if (mainvo != null && mainvo.size() > 0) {
                        for (int i = 0; i < mainvo.size(); i++) {
                            list.add(mainvo.get(i));
                        }
                    }

                }
                ListAdapter adapter = new Adapter();
                //兼容了父类的写法，提供了点击事件
                setAdapter(adapter);
                setData(list);


            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(ZscListActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(ZscListActivity.this, "获取数据列表失败");
            }
        });

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
                convertView = View.inflate(ZscListActivity.this, R.layout.zsclist, null);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.zscFbr = (TextView) convertView.findViewById(R.id.zsc_fbr);
                holder.zscFbdw = (TextView) convertView.findViewById(R.id.zsc_fbdw);
                holder.zscFbtime = (TextView) convertView.findViewById(R.id.zsc_fbtime);
                holder.zscLimitdate = (TextView) convertView.findViewById(R.id.zsc_limitdate);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ZscBean.MainvoBean.HeadVOBean.PHDKNOWLEDGEWINMBean phd_knowledgewin_m = mainvo.get(position).getHeadVO().getPHD_KNOWLEDGEWIN_M();
            if (phd_knowledgewin_m!=null){
                holder.title.setText(phd_knowledgewin_m.getName());
                holder.zscFbr.setText(phd_knowledgewin_m.getRelease_by_name());
                holder.zscFbdw.setText(phd_knowledgewin_m.getRelease_orgname());
                holder.zscFbtime.setText(phd_knowledgewin_m.getRelease_dt());
                holder.zscLimitdate.setText(phd_knowledgewin_m.getStarttime()+"至\n"+phd_knowledgewin_m.getEndtime());
            }


            return convertView;
        }


    }

    static class ViewHolder {
        private TextView title;
        private TextView zscFbr;
        private TextView zscFbdw;
        private TextView zscFbtime;
        private TextView zscLimitdate;

    }


}
