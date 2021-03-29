package rmt.authorize.phone.app.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.entity.resultdata.RmtAuthorizeBsqr;
import com.hd.hse.entity.resultdata.RmtAuthorizeList;
import com.hd.hse.entity.resultdata.RmtAuthorizeListDetail;
import com.hd.hse.entity.resultdata.RmtAuthorizeSureSave;
import com.hd.hse.entity.resultdata.WorkAuthorizeRecover;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import rmt.authorize.phone.app.Adapter.FcAdapter;
import rmt.authorize.phone.app.Adapter.MyAdapter;
import rmt.authorize.phone.app.R;
import rmt.authorize.phone.app.custom.NormalItemView;

public class WorkAuthorizeDetailActivity extends BarActivity {

    private Serializable data;
    private NormalItemView normalItemView1;
    private NormalItemView normalItemView2;
    private NormalItemView normalItemView3;
    private NormalItemView normalItemView5;
    private Calendar ca;
    private boolean sp;
    private Button sureButton;
    private ListView listView;
    private MyAdapter myAdapter;
    private RmtAuthorizeList.MainvoBean mainvoBean;
    private RmtAuthorizeListDetail.MainvoBean.HeadVOBean.RMTWORKGRANTMBean bean;
    private FcAdapter fcAdapter;
    private List<RmtAuthorizeListDetail.MainvoBean.BodyVOsBean.RMTWORKGRANTITEMMBeanX> rmt_work_grant_item_m;
    private List<RmtAuthorizeBsqr.MainvoBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View inflate = View.inflate(this, R.layout.activity_work_authorize_detail, null);
        frameLayout.addView(inflate);
//        setContentView(R.layout.activity_work_authorize_detail);
        normalItemView1 = (NormalItemView) inflate.findViewById(R.id.normal1);
        normalItemView2 = (NormalItemView) inflate.findViewById(R.id.normal2);
        normalItemView3 = (NormalItemView) inflate.findViewById(R.id.normal3);
        normalItemView5 = (NormalItemView) inflate.findViewById(R.id.normal5);
        sureButton = (Button) inflate.findViewById(R.id.authorizelist_sure);
        listView = (ListView) inflate.findViewById(R.id.authorizelist_list);

        myAdapter = new MyAdapter(WorkAuthorizeDetailActivity.this);
        fcAdapter = new FcAdapter(WorkAuthorizeDetailActivity.this);
        listView.setAdapter(myAdapter);
        ca = Calendar.getInstance();

        Click();


        Intent intent = getIntent();
        data = intent.getSerializableExtra("data");
        mainvoBean = (RmtAuthorizeList.MainvoBean) data;

        if (mainvoBean != null) {
            initData();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (data != null) {
                    if (rmt_work_grant_item_m.get(position).getHeadVO().getRMT_WORK_GRANT_ITEM_M().getDataStatus() == 0) {
                        rmt_work_grant_item_m.get(position).getHeadVO().getRMT_WORK_GRANT_ITEM_M().setDataStatus(1);
                    } else if (rmt_work_grant_item_m.get(position).getHeadVO().getRMT_WORK_GRANT_ITEM_M().getDataStatus() == 1) {
                        rmt_work_grant_item_m.get(position).getHeadVO().getRMT_WORK_GRANT_ITEM_M().setDataStatus(0);
                    }
                    fcAdapter.notifyDataSetChanged();
                } else {
                    list.remove(position);
                    myAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void initData() {

        final ProgressDialog dialog = new ProgressDialog(WorkAuthorizeDetailActivity.this);
        dialog.setMessage("正在查询");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);

        Call<ResultData<RmtAuthorizeListDetail>> call = rmtInterface.getAuthorizeListDetail(mainvoBean.getHeadVO().getRMT_WORK_GRANT_M().getGrt_id(), LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(WorkAuthorizeDetailActivity.this, false, call, new HttpCallBack<RmtAuthorizeListDetail>() {
            @Override
            public void success(RmtAuthorizeListDetail resultDataBean) {
                dialog.dismiss();
                if (resultDataBean != null) {
                    List<RmtAuthorizeListDetail.MainvoBean> mainvo = resultDataBean.getMainvo();
                    RmtAuthorizeListDetail.DictvosBean dictvos = resultDataBean.getDictvos();
                    if (dictvos != null) {
                        List<RmtAuthorizeListDetail.DictvosBean.RmtWorkGrantItemstatusBean> rmt_work_grant_itemstatus = dictvos.getRmt_work_grant_itemstatus();
                        fcAdapter.setList(rmt_work_grant_itemstatus);
                    }
                    if (mainvo != null) {
                        RmtAuthorizeListDetail.MainvoBean.HeadVOBean headVO = mainvo.get(0).getHeadVO();
                        RmtAuthorizeListDetail.MainvoBean.BodyVOsBean bodyVOs = mainvo.get(0).getBodyVOs();
                        if (headVO != null) {
                            bean = headVO.getRMT_WORK_GRANT_M();
                            if (bean != null) {
                                normalItemView2.setTitle(bean.getStarttime());
                                normalItemView3.setTitle(bean.getEndtime());
                                normalItemView1.setTitle(bean.getAuth());
                                tv.setText(bean.getAuth() + "的授权");
                            }
                        }
                        if (bodyVOs != null) {
                            rmt_work_grant_item_m = bodyVOs.getRMT_WORK_GRANT_ITEM_M();
                            if (rmt_work_grant_item_m != null && rmt_work_grant_item_m.size() > 0) {
                                listView.setAdapter(fcAdapter);
                                fcAdapter.setData(rmt_work_grant_item_m);
                            }
                        }
                    }

                }
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(WorkAuthorizeDetailActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(WorkAuthorizeDetailActivity.this, "获取数据列表失败");
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (data != null) {
            sureButton.setVisibility(View.GONE);

            normalItemView2.setImage(0);
            normalItemView3.setImage(0);
            normalItemView5.setImage(0);
            toolbar.setTitle("");


        } else {
            normalItemView1.setTitle(LoginUserRecord.getInstance().getUser().getUserName());
            normalItemView2.setImage(R.drawable.go_next);
            normalItemView3.setImage(R.drawable.go_next);
            normalItemView5.setImage(R.drawable.go_next);
            toolbar.setTitle("");
            tv.setText("作业授权");

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle bundle = data.getBundleExtra("bundle");
            list = (List<RmtAuthorizeBsqr.MainvoBean>) bundle.getSerializable("list");
            myAdapter.setData(list);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean h;

    private void Click() {


        normalItemView5.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data == null) {
                    Intent intent = new Intent(WorkAuthorizeDetailActivity.this, BSHRActivity.class);
//                startActivity(intent);
                    startActivityForResult(intent, 1);
                } else {
                    ImageView imageView = (ImageView) normalItemView5.findViewById(R.id.next);
                    if (!h) {
                        imageView.setImageResource(R.drawable.hd_hse_common_msg_right);
                        for (int i = 0; i < rmt_work_grant_item_m.size(); i++) {
                            RmtAuthorizeListDetail.MainvoBean.BodyVOsBean.RMTWORKGRANTITEMMBeanX.HeadVOBean.RMTWORKGRANTITEMMBean rmt_work_grant_item_m = WorkAuthorizeDetailActivity.this.rmt_work_grant_item_m.get(i).getHeadVO().getRMT_WORK_GRANT_ITEM_M();
                            rmt_work_grant_item_m.setDataStatus(1);
                        }
                        h = true;
                        fcAdapter.setData(rmt_work_grant_item_m);
                        return;
                    }
                    if (h) {
                        imageView.setImageResource(R.drawable.workauthorize_nochoice);
                        for (int i = 0; i < rmt_work_grant_item_m.size(); i++) {
                            RmtAuthorizeListDetail.MainvoBean.BodyVOsBean.RMTWORKGRANTITEMMBeanX.HeadVOBean.RMTWORKGRANTITEMMBean rmt_work_grant_item_m = WorkAuthorizeDetailActivity.this.rmt_work_grant_item_m.get(i).getHeadVO().getRMT_WORK_GRANT_ITEM_M();
                            rmt_work_grant_item_m.setDataStatus(0);
                        }
                        h = false;
                        fcAdapter.setData(rmt_work_grant_item_m);
                        return;
                    }

                }

            }
        });


        sureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(normalItemView2.getTitle()) || TextUtils.isEmpty(normalItemView3.getTitle()) || myAdapter.getCount() == 0) {
                    ToastUtil.toast(WorkAuthorizeDetailActivity.this, "必填项不能为空");
                    return;
                } else {
                    RmtAuthorizeSureSave rmtAuthorizeSureSave = new RmtAuthorizeSureSave();
                    rmtAuthorizeSureSave.setAuth(LoginUserRecord.getInstance().getUser().getUserName());
                    rmtAuthorizeSureSave.setStarttime(normalItemView2.getTitle());
                    rmtAuthorizeSureSave.setEndtime(normalItemView3.getTitle());
                    rmtAuthorizeSureSave.setAuthid(LoginUserRecord.getInstance().getUser().getUserid());
                    rmtAuthorizeSureSave.setGrt_node("on_duty_moni");
                    List<RmtAuthorizeSureSave.ItemVOsBean> dl = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        RmtAuthorizeSureSave.ItemVOsBean itemVOsBean = new RmtAuthorizeSureSave.ItemVOsBean();
                        itemVOsBean.setBe_authed(list.get(i).getHeadVO().getSY_USER_M_QUERY().getUsername());
                        itemVOsBean.setBe_authedid(list.get(i).getHeadVO().getSY_USER_M_QUERY().getUserid());
                        dl.add(itemVOsBean);
                    }
                    rmtAuthorizeSureSave.setItemVOs(dl);
                    SureSave(rmtAuthorizeSureSave);
                }

            }
        });

        normalItemView2.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sp) {
                    normalItemView2.setImage(R.drawable.go_next);
                    normalItemView2.setTitle("");
                    sp = false;
                } else {

                    final DatePickerDialog datePicker = new DatePickerDialog(WorkAuthorizeDetailActivity.this, DatePickerDialog.THEME_HOLO_LIGHT, null,
                            ca.get(Calendar.YEAR),
                            ca.get(Calendar.MONTH),
                            ca.get(Calendar.DAY_OF_MONTH));
                    datePicker.setButton(DialogInterface.BUTTON_POSITIVE, "确认",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //确定的逻辑代码在监听中实现
                                    DatePicker picker = datePicker.getDatePicker();
                                    int year = picker.getYear();
                                    int monthOfYear = picker.getMonth() + 1;
                                    int dayOfMonth = picker.getDayOfMonth();
                                    DecimalFormat df = new DecimalFormat("00");
                                    String str2 = df.format(monthOfYear);
                                    String str3 = df.format(dayOfMonth);
                                    normalItemView2.setImage(R.drawable.hd_cbs_icon_step_check_no_two);
                                    normalItemView2.setTitle(year + "-" + str2 + "-" + str3);
                                    sp = true;
                                }
                            });
                    datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //取消什么也不用做
                                }
                            });
                    datePicker.show();

                }
            }
        });

        normalItemView3.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sp) {
                    normalItemView3.setImage(R.drawable.go_next);
                    normalItemView3.setTitle("");
                    sp = false;
                } else {

                    final DatePickerDialog datePicker = new DatePickerDialog(WorkAuthorizeDetailActivity.this, DatePickerDialog.THEME_HOLO_LIGHT, null,
                            ca.get(Calendar.YEAR),
                            ca.get(Calendar.MONTH),
                            ca.get(Calendar.DAY_OF_MONTH));
                    datePicker.setButton(DialogInterface.BUTTON_POSITIVE, "确认",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //确定的逻辑代码在监听中实现
                                    DatePicker picker = datePicker.getDatePicker();
                                    int year = picker.getYear();
                                    int monthOfYear = picker.getMonth() + 1;
                                    int dayOfMonth = picker.getDayOfMonth();
                                    DecimalFormat df = new DecimalFormat("00");
                                    String str2 = df.format(monthOfYear);
                                    String str3 = df.format(dayOfMonth);
                                    normalItemView3.setImage(R.drawable.hd_cbs_icon_step_check_no_two);
                                    normalItemView3.setTitle(year + "-" + str2 + "-" + str3);
                                    sp = true;
                                }
                            });
                    datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //取消什么也不用做
                                }
                            });
                    datePicker.show();

                }
            }
        });
    }

    private void SureSave(RmtAuthorizeSureSave rmtAuthorizeSureSave) {


        final ProgressDialog dialog = new ProgressDialog(WorkAuthorizeDetailActivity.this);
        dialog.setMessage("正在保存数据...");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.getbcButton(rmtAuthorizeSureSave, LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(WorkAuthorizeDetailActivity.this, false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object map) {
                dialog.dismiss();
                ToastUtil.toast(WorkAuthorizeDetailActivity.this, "已保存");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtils.toast(WorkAuthorizeDetailActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtils.toast(WorkAuthorizeDetailActivity.this, "保存数据失败");
            }
        });

    }


    @Override
    public int Menu() {
        if (data != null) {
            return R.menu.menu;
        } else {
            return 0;
        }
    }

    private void Recover(List<WorkAuthorizeRecover> list) {


        final ProgressDialog dialog = new ProgressDialog(WorkAuthorizeDetailActivity.this);
        dialog.setMessage("正在保存数据...");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.getRecover(list, LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(WorkAuthorizeDetailActivity.this, false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object map) {
                dialog.dismiss();
                ToastUtil.toast(WorkAuthorizeDetailActivity.this, "已收回");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtils.toast(WorkAuthorizeDetailActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtils.toast(WorkAuthorizeDetailActivity.this, "上传数据失败");
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.action_sh) {
            if (item.getTitle().equals("完成")) {
                item.setTitle("收回");
                ImageView imageView = (ImageView) normalItemView5.findViewById(R.id.next);
                imageView.setVisibility(View.INVISIBLE);
                fcAdapter.setEdit(false);
                final List<WorkAuthorizeRecover> list = new ArrayList<>();
                final List<RmtAuthorizeListDetail.MainvoBean.BodyVOsBean.RMTWORKGRANTITEMMBeanX> datas = new ArrayList<>();
                datas.addAll(rmt_work_grant_item_m);
                for (int k = 0; k < datas.size(); k++) {
                    if (!datas.get(k).getHeadVO().getRMT_WORK_GRANT_ITEM_M().getStatus().equals("appr") &&
                            !datas.get(k).getHeadVO().getRMT_WORK_GRANT_ITEM_M().getStatus().equals("receive")) {
                        datas.remove(k);
                        k--;

                    }
                }

                listView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                    @Override
                    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                        if (datas.size() != 0) {
                            for (int i = 0; i < datas.size(); i++) {
                                RmtAuthorizeListDetail.MainvoBean.BodyVOsBean.RMTWORKGRANTITEMMBeanX.HeadVOBean.RMTWORKGRANTITEMMBean item_m = datas.get(i).getHeadVO().getRMT_WORK_GRANT_ITEM_M();

                                if (item_m.getDataStatus() == 1) {
                                    item_m.setStatus("recover");
                                    WorkAuthorizeRecover workAuthorizeRecover = new WorkAuthorizeRecover();
                                    workAuthorizeRecover.setStatus(item_m.getStatus());
                                    workAuthorizeRecover.setBe_authed(item_m.getBe_authed());
                                    workAuthorizeRecover.setRec_time(item_m.getRec_time());
                                    workAuthorizeRecover.setGrt_id(item_m.getGrt_id());
                                    workAuthorizeRecover.setCreated_dt(item_m.getCreated_dt());
                                    workAuthorizeRecover.setGrt_item_time(item_m.getGrt_item_time());
                                    workAuthorizeRecover.setGrt_item_id(item_m.getGrt_item_id());
                                    workAuthorizeRecover.setBe_authedid(item_m.getBe_authedid());
                                    list.add(workAuthorizeRecover);
                                }
                            }
                        }

                        if (list.size() == 0) {
                            ToastUtil.toast(WorkAuthorizeDetailActivity.this, "没有可收回的数据");
                        }else{
                            Recover(list);
                        }

                    }
                });

            } else if (item.getTitle().equals("收回")) {
                item.setTitle("完成");
                normalItemView5.setImage(R.drawable.workauthorize_nochoice);
                fcAdapter.setEdit(true);
            }

        }
        return super.onOptionsItemSelected(item);
    }


}
