package rmt.authorize.phone.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.entity.resultdata.RmtAuthorizeBsqr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import rmt.authorize.phone.app.Adapter.OtherAdapter;
import rmt.authorize.phone.app.R;

public class BSHRActivity extends BarActivity {


    private EditText bsqrEdit;
    private ListView bsqrListview;
    private OtherAdapter otherAdapter;
    private List<RmtAuthorizeBsqr.MainvoBean> list;
    private boolean whichdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View inflate = View.inflate(this, R.layout.activity_work_bsqr, null);
        frameLayout.addView(inflate);

        bsqrEdit = (EditText) findViewById(R.id.bsqr_edit);
        bsqrListview = (ListView) findViewById(R.id.bsqr_listview);
        otherAdapter = new OtherAdapter(this);
        bsqrListview.setAdapter(otherAdapter);
        initEvent();


        initData();

    }

    private List<RmtAuthorizeBsqr.MainvoBean> mainvo;

    private void initData() {

        final ProgressDialog dialog = new ProgressDialog(BSHRActivity.this);
        dialog.setMessage("正在查询");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);

        Call<ResultData<RmtAuthorizeBsqr>> call = rmtInterface.getAuthorizebsqr(LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(BSHRActivity.this, false, call, new HttpCallBack<RmtAuthorizeBsqr>() {
            @Override
            public void success(RmtAuthorizeBsqr resultDataBean) {
                dialog.dismiss();
                if (resultDataBean != null) {
                    mainvo = resultDataBean.getMainvo();
                    if (mainvo != null && mainvo.size() > 0) {
                        otherAdapter.setData(mainvo);
                        whichdata = true;
                    }
                }
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(BSHRActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(BSHRActivity.this, "获取数据列表失败");
            }
        });

    }

    private void initEvent() {

        bsqrListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (whichdata) {
                    if (mainvo.get(position).getHeadVO().getSY_USER_M_QUERY().getDataStatus() == 0) {
                        mainvo.get(position).getHeadVO().getSY_USER_M_QUERY().setDataStatus(1);
                    } else if (mainvo.get(position).getHeadVO().getSY_USER_M_QUERY().getDataStatus() == 1) {
                        mainvo.get(position).getHeadVO().getSY_USER_M_QUERY().setDataStatus(0);
                    }
                } else {
                    if (list.get(position).getHeadVO().getSY_USER_M_QUERY().getDataStatus() == 0) {
                        list.get(position).getHeadVO().getSY_USER_M_QUERY().setDataStatus(1);
                    } else if (list.get(position).getHeadVO().getSY_USER_M_QUERY().getDataStatus() == 1) {
                        list.get(position).getHeadVO().getSY_USER_M_QUERY().setDataStatus(0);
                    }
                }


                otherAdapter.notifyDataSetChanged();


            }
        });


        bsqrEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!TextUtils.isEmpty(s)) {

                    list = new ArrayList<>();

                    for (int i = 0; i < mainvo.size(); i++) {
                        if (mainvo.get(i).getHeadVO().getSY_USER_M_QUERY().getUsername().equals(s.toString())) {
                            list.add(mainvo.get(i));
                            otherAdapter.setData(list);
                            whichdata = false;
                        }
                    }
                } else {
                    otherAdapter.setData(mainvo);
                    whichdata = true;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle("");
        tv.setText("选择被授权人");
    }


    @Override
    public int Menu() {
        return R.menu.bsqrmenu;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_complete) {
//            ToastUtil.toast(BSHRActivity.this, "完成");

                List<RmtAuthorizeBsqr.MainvoBean> data = new ArrayList<>();
                if (whichdata) {
                    if (mainvo==null||mainvo.size()==0){
                        finish();
                        return super.onOptionsItemSelected(item);
                    }else{
                        for (int i = 0; i < mainvo.size(); i++) {
                            if (mainvo.get(i).getHeadVO().getSY_USER_M_QUERY().getDataStatus() == 1) {
                                data.add(mainvo.get(i));
                            }
                        }
                    }

                } else {
                    if (list==null||list.size()==0){
                        finish();
                        return super.onOptionsItemSelected(item);
                    }else{
                        for (int k = 0; k < list.size(); k++) {
                            if (list.get(k).getHeadVO().getSY_USER_M_QUERY().getDataStatus() == 1) {
                                data.add(list.get(k));
                            }
                        }
                    }

                }
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", (Serializable) data);
//            bundle.putParcelableArrayList("list", data);
                intent.putExtra("bundle", bundle);
                setResult(RESULT_OK, intent);
                finish();



        }
        return super.onOptionsItemSelected(item);
    }
}
