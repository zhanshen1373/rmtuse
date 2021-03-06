package com.hd.hse.common.module.phone.ui.module.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.custom.ShowPopWindowDialog;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.adapter.TaskListExpandAdapterextends;
import com.hd.hse.common.module.phone.camera2.CameraActivity;
import com.hd.hse.common.module.phone.camera2.SavePhotoFragment;
import com.hd.hse.common.module.phone.custom.PopWinButton;
import com.hd.hse.common.module.phone.custom.ZYPOperatePopWindow;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;
import com.hd.hse.common.module.phone.ui.activity.FragmentSignActivity;
import com.hd.hse.entity.common.Image;
import com.hd.hse.entity.sign.ImgStringVo;
import com.hd.hse.entity.sys.RmtSignRecord;
import com.hd.hse.entity.workorder.RmtTaskListQuery;
import com.hd.hse.entity.workorder.RmtWorkSubTaskBean;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.entity.workorder.RmtWorkTaskBean;
import com.hd.hse.entity.workorder.WorkOrder;
import com.hd.hse.entity.workorder.WorkTask;
import com.hd.hse.system.SystemProperty;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.hayden.hap.hd_push.sdk.HDPush.context;
import static com.hd.hse.common.module.phone.adapter.TaskListExpandAdapterextends.AGREE;
import static com.hd.hse.common.module.phone.adapter.TaskListExpandAdapterextends.DISAGREE;
import static com.hd.hse.common.module.phone.adapter.TaskListExpandAdapterextends.FCTWAPPR;

/**
 * ClassName: BaseTaskAndItemListActivity (????????????????????????????????????base)<br/>
 * date: 2015???8???7??? <br/>
 *
 * @author lxf
 */
public abstract class BaseTaskAndItemListActivity extends BaseFrameActivity {

    /**
     * ZYPListView(?????????????????????).
     */
    private ExpandableListView mExpandableListView;
    private ZYPOperatePopWindow popWindow;
    public RelativeLayout relativeLayout;
    // popWindow????????????
    public IEventListener iEventLsn;

    private static Logger logger = LogUtils.getLogger(BaseTaskAndItemListActivity.class);
    /**
     * taskListAdapter:TODO(?????????).
     */
    private TaskListExpandAdapterextends taskListAdapter;

    /**
     * dataList:TODO(?????????).
     */
    private List<RmtTaskListQuery.MainvoBean> rmtmainvo = null;
    /**
     * searchStr:TODO(????????????).
     */
    private String searchStr = "";

    /**
     * searchId:TODO(??????id)
     */
    private Long searchId;

    /**
     * ?????????????????????
     */
    private ShowPopWindowDialog mAlertDialog;

    /**
     * ???????????????button??????
     */
    private LinearLayout liBottomButton;
    /**
     * ??????
     */
    private Button btAgree;
    /**
     * ???????????????
     */
    private Button btDisagree;
    /**
     * ?????????????????????
     */
    private Button btSubmitAgree;
    private ProgressDialog mProgressDialog;
    /**
     * ??????????????????
     */
    private int isQzPz = -1;

    /**
     * ??????????????????????????????????????????????????????????????? setDate:(). <br/>
     * date: 2015???6???3??? <br/>
     *
     * @author xuxinwen
     */
    /**
     * ????????????????????????
     */
    private Boolean factory;
    private long work_unit_id = -1;
    private String ids;
    private View allV;
    private String description;
    private String fxdescription;


    public final void setDataList(List<RmtTaskListQuery.MainvoBean> mainvo, String moduleCode) {
        this.rmtmainvo = mainvo;
        // TO???DO
        if (rmtmainvo == null)
            return;
        //????????????
        filterData(rmtmainvo);
        // ????????????
        setDataOrder();

        taskListAdapter.setSearchId(getSearchId());
        taskListAdapter.setDateinfo(rmtmainvo, moduleCode);
    }

    //??????????????????????????????
    public final void setDataList(List<RmtTaskListQuery.MainvoBean> mainvo, RmtWorkSubtask rmtWorkSubtask, String moduleCode) {
        this.rmtmainvo = mainvo;
        // TO???DO
        if (rmtmainvo == null)
            return;
        //????????????
        filterData(rmtmainvo);
        // ????????????
        setDataOrder();
        taskListAdapter.setSearchId(getSearchId());
        taskListAdapter.setDateinfo(rmtmainvo, rmtWorkSubtask, moduleCode);
    }

    /**
     * getSearchStr:(??????????????????). <br/>
     * date: 2014???10???13??? <br/>
     *
     * @return
     * @author zhulei
     */
    public final String getSearchStr() {
        return searchStr;
    }

    /**
     * setSearchStr:(??????????????????). <br/>
     * date: 2014???10???13??? <br/>
     *
     * @param searchStr
     * @author zhulei
     */
    public final void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    /**
     * ??????popwindow?????????
     *
     * @param iEventLsn
     */
    public void setiEventLsn(IEventListener iEventLsn) {
        this.iEventLsn = iEventLsn;
        // vZYPList.setOnEventListener(iEventLsn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ???????????????
        initView();
        // ???????????????
        initData();
    }


    protected void initView() {
        setContentView(R.layout.hd_hse_common_module_task_item_list);
        mExpandableListView = (ExpandableListView) findViewById(R.id.hd_hse_common_phone_expanablelistview_taskitem_id);
        liBottomButton = (LinearLayout) findViewById(R.id.hd_hse_common_phone_li_bottom);
        btAgree = (Button) findViewById(R.id.hd_hse_common_phone_bt_agree);
        btDisagree = (Button) findViewById(R.id.hd_hse_common_phone_bt_disagree);
        btSubmitAgree = (Button) findViewById(R.id.hd_hse_common_phone_bt_submit_agree);
        taskListAdapter = getTaskListAdapter();
        taskListAdapter.setOnIEventListener(new EventListenerImpl());
        /*if (liBottomButtonIsVisible()) {
            liBottomButton.setVisibility(View.VISIBLE);
        } else {
            liBottomButton.setVisibility(View.GONE);
        }
        btAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ids = getCheckIds();
                if (ids == null || "".equals(ids)) {
                    ToastUtils.toast(BaseTaskAndItemListActivity.this, "???????????????????????????");
                    return;
                }
                upApprove(ids, AGREE);
            }
        });
        btDisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ids = getCheckIds();
                if (ids == null || "".equals(ids)) {
                    ToastUtils.toast(BaseTaskAndItemListActivity.this, "???????????????????????????");
                    return;
                }
                upApprove(ids, DISAGREE);
            }
        });
        btSubmitAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ids = getCheckIds();
                if (ids == null || "".equals(ids)) {
                    ToastUtils.toast(BaseTaskAndItemListActivity.this, "???????????????????????????");
                    return;
                }
                upApprove(ids, APPR);
            }
        });*/

    }

    private String getCheckIds() {

        String ids = null;
        if (rmtmainvo == null || rmtmainvo.size() <= 0) {
            return null;
        }
        for (RmtTaskListQuery.MainvoBean mainvoBean : rmtmainvo) {
            if (mainvoBean.getBodyVOs() != null
                    && mainvoBean.getBodyVOs().getRMT_WORK_SUBTASK_M() != null
                    && mainvoBean.getBodyVOs().getRMT_WORK_SUBTASK_M().size() > 0) {
                List<RmtWorkSubTaskBean> rmtWorkSubTaskBeanList = mainvoBean.getBodyVOs().getRMT_WORK_SUBTASK_M();
                for (RmtWorkSubTaskBean rmtWorkSubTaskBean : rmtWorkSubTaskBeanList) {
                    if (rmtWorkSubTaskBean.getHeadVO() != null &&
                            rmtWorkSubTaskBean.getHeadVO().getRMT_WORK_SUBTASK_M() != null
                    ) {
                        boolean isSelected = rmtWorkSubTaskBean.getHeadVO().getRMT_WORK_SUBTASK_M().isSelected();
                        long work_subtask_id = rmtWorkSubTaskBean.getHeadVO().getRMT_WORK_SUBTASK_M().getWork_subtask_id();
                        if (isSelected) {
                            ids = ids == null ? ("" + work_subtask_id) : (ids + "," + work_subtask_id);
                            fxdescription = rmtWorkSubTaskBean.getHeadVO().getRMT_WORK_SUBTASK_M().getFxdescription();
                            work_unit_id = rmtWorkSubTaskBean.getHeadVO().getRMT_WORK_SUBTASK_M().getWork_unit_id();
                            description = rmtWorkSubTaskBean.getHeadVO().getRMT_WORK_SUBTASK_M().getDescription();
                        }
                    }

                }
            }

        }
        return ids;
    }


    protected void upApprove(String ids, String result) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("????????????????????????...");
        }
        mProgressDialog.show();

        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        final Call<ResultData<Object>> call = rmtInterface.viewAttachment(ids, result, LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(this, false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                mProgressDialog.dismiss();
                ToastUtil.toast(BaseTaskAndItemListActivity.this, "????????????");
                //????????????
                initData();
            }

            @Override
            public void warning(String msg) {
                mProgressDialog.dismiss();
                ToastUtil.toast(BaseTaskAndItemListActivity.this, msg);
                List<RmtSignRecord> rmtSignRecordList = new ArrayList<>();
                String ob = LoginUserRecord.getInstance().getOb();
                if (isQzPz == 1) {
                    if (ob != null) {
                        Gson g = new Gson();
                        LeadSPBean leadSPBean = g.fromJson(ob, LeadSPBean.class);
                        List<LeadSPBean.ApprRecordsBean> apprRecords = leadSPBean.getApprRecords();
                        RmtSignRecord clone = null;
                        if (apprRecords != null && apprRecords.size() > 0) {
                            for (int i = 0; i < apprRecords.size(); i++) {
                                if (clone != null) {
                                    rmtSignRecord = clone;
                                }
                                rmtSignRecord.setApprRecordId(apprRecords.get(i).getAppr_record_id());
                                rmtSignRecordList.add(rmtSignRecord);
                                try {
                                    clone = (RmtSignRecord) rmtSignRecord.clone();
                                } catch (CloneNotSupportedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (rmtSignRecordList.size() > 0) {
                            upLoadQzPic(rmtSignRecordList);
                        }
                    } else {
                        //????????????
                        initData();
                    }
                } else {
                    //????????????
                    initData();
                }


            }

            @Override
            public void error(Throwable t) {
                mProgressDialog.dismiss();
                ToastUtil.toast(BaseTaskAndItemListActivity.this, "????????????????????????");
            }
        });

    }

    protected ExpandableListView getExpandableListView() {
        return mExpandableListView;
    }

    protected TaskListExpandAdapterextends getTaskListAdapter() {
        return new TaskListExpandAdapterextends(this,
                mExpandableListView);
    }

    /**
     * ??????????????????????????????????????????????????????????????????
     *
     * @return
     */
    protected boolean liBottomButtonIsVisible() {
        return false;
    }

    /**
     * initData:(???????????????). <br/>
     * date: 2014???10???10??? <br/>
     *
     * @author zhulei
     */
    protected void initData() {
        /*
        // ????????????
        filterData(rmtmainvo);
        setDataOrder();
        taskListAdapter.setDateinfo(rmtmainvo);*/
        if (isQzPz == -1) {

            RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
            Call<ResultData<RmtInterface.PZ>> call = rmtInterface.getQzpz();
            HttpBusiness.action(this, false, call, new HttpCallBack<RmtInterface.PZ>() {
                @Override
                public void success(RmtInterface.PZ pz) {

                    isQzPz = pz.force_photo;
                }

                @Override
                public void warning(String msg) {

                    Toast.makeText(BaseTaskAndItemListActivity.this, msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void error(Throwable t) {
                    Toast.makeText(BaseTaskAndItemListActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private void a(final List<Call<ResultData<Object>>> callList) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("??????????????????...");
        dialog.show();

        HttpBusiness.action(this, false, callList.get(0), new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                dialog.dismiss();
                Toast.makeText(BaseTaskAndItemListActivity.this, "??????????????????!", Toast.LENGTH_SHORT).show();
                callList.remove(0);
                if (callList.size() != 0) {
                    a(callList);
                } else {
                    initData();
                }
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                Toast.makeText(BaseTaskAndItemListActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                Toast.makeText(BaseTaskAndItemListActivity.this, "??????????????????!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * ????????????????????????
     *
     * @param rmtSignRecord
     */
    private void upLoadQzPic(List<RmtSignRecord> rmtSignRecord) {
        List<Call<ResultData<Object>>> callArrayList = new ArrayList<>();

        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        for (int w = 0; w < rmtSignRecord.size(); w++) {
            Call<ResultData<Object>> call = rmtInterface.uploadQZPic(rmtSignRecord.get(w));
            callArrayList.add(call);
        }
        a(callArrayList);

//        HttpBusiness.action(this, false, call, new HttpCallBack<Object>() {
//            @Override
//            public void success(Object o) {
//                dialog.dismiss();
//                Toast.makeText(BaseTaskAndItemListActivity.this, "??????????????????!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void warning(String msg) {
//                dialog.dismiss();
//                Toast.makeText(BaseTaskAndItemListActivity.this, msg, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void error(Throwable t) {
//                dialog.dismiss();
//                Toast.makeText(BaseTaskAndItemListActivity.this, "??????????????????!", Toast.LENGTH_SHORT).show();
//            }
//        });

    }


    private void sqCheck(final View v, final String ids) {

        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<ImgStringVo>> call = rmtInterface.getSign(LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(context, false, call, new HttpCallBack<ImgStringVo>() {
            @Override
            public void success(ImgStringVo imgStringVo) {
                if (imgStringVo == null || imgStringVo.getImgStr() == null || imgStringVo.getImgStr().length() == 0) {
                    ToastUtils.toast(context, "????????????????????????");
                    //??????????????????
                    Intent intent = new Intent(context, FragmentSignActivity.class);
                    context.startActivity(intent);
                } else {
                    if (v.getId() == R.id.hd_hse_common_phone_bt_agree) {
//                        if (factory) {
//                            upApprove(ids, FCTAGREE);
//                        } else {
//                            upApprove(ids, AGREE);
//                        }
                        if (name.equals("???")) {
                            upApprove(ids, FCTWAPPR);
                        } else if (name.equals("???")) {
                            upApprove(ids, AGREE);
                        } else {
                            upApprove(ids, AGREE);
                        }
                    } else if (v.getId() == R.id.hd_hse_common_phone_bt_disagree) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(BaseTaskAndItemListActivity.this);
                        builder.setMessage("??????????????????");
                        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
//                                if (factory) {
//                                    upApprove(ids, FCTDISAGREE);
//                                } else {
//                                    upApprove(ids, DISAGREE);
//                                }
                                upApprove(ids, DISAGREE);
                            }
                        }).setNegativeButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

                    } else if (v.getId() == R.id.hd_hse_common_phone_bt_submit_agree) {
                        if (name.equals("???")) {
                            upApprove(ids, FCTWAPPR);
                        } else if (name.equals("???")) {
                            upApprove(ids, AGREE);
                        } else {
                            upApprove(ids, FCTWAPPR);
                        }
                    }
                }
            }

            @Override
            public void warning(String msg) {

                ToastUtils.toast(context, msg);
            }

            @Override
            public void error(Throwable t) {
                ToastUtils.toast(context, t.toString());
            }
        });
    }

    private void jy(View v, final String ids) {


        if (LoginUserRecord.getInstance().getUser().getOrgid() != work_unit_id) {
            sqCheck(v, ids);
        } else {
            List<Long> orgidList = LoginUserRecord.getInstance().getOrgidList();
            if (orgidList != null && orgidList.size() > 0) {
                if (orgidList.contains(work_unit_id)) {
                    sqCheck(v, ids);
                }
            } else {

                if (v.getId() == R.id.hd_hse_common_phone_bt_agree) {
//                        if (factory) {
//                            upApprove(ids, FCTAGREE);
//                        } else {
//                            upApprove(ids, AGREE);
//                        }
                    if (name.equals("???")) {
                        upApprove(ids, FCTWAPPR);
                    } else if (name.equals("???")) {
                        upApprove(ids, AGREE);
                    } else {
                        upApprove(ids, AGREE);
                    }
                } else if (v.getId() == R.id.hd_hse_common_phone_bt_disagree) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BaseTaskAndItemListActivity.this);
                    builder.setMessage("??????????????????");
                    builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
//                                if (factory) {
//                                    upApprove(ids, FCTDISAGREE);
//                                } else {
//                                    upApprove(ids, DISAGREE);
//                                }
                            upApprove(ids, DISAGREE);
                        }
                    }).setNegativeButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();

                } else if (v.getId() == R.id.hd_hse_common_phone_bt_submit_agree) {
                    if (name.equals("???")) {
                        upApprove(ids, FCTWAPPR);
                    } else if (name.equals("???")) {
                        upApprove(ids, AGREE);
                    } else {
                        upApprove(ids, FCTWAPPR);
                    }

                }

            }

        }


    }

    private String name;

    /**
     * ??????????????????????????????????????????????????????????????????
     */
    protected void handleBottom() {
        if (rmtmainvo == null || rmtmainvo.size() == 0)
            return;
        if (liBottomButtonIsVisible()) {
            try {
                factory = rmtmainvo.get(0).getHeadVO().getRMT_WORK_TASK_M().getFactory();
                if (factory == null) {
                    ToastUtils.toast(getApplicationContext(), "??????factory????????????");
                } else {
                    liBottomButton.setVisibility(View.VISIBLE);

//                    if (factory) {
//                        btSubmitAgree.setVisibility(View.GONE);
//                        //final String agree=FCTAGREE;
//                    } else {
//                        btAgree.setVisibility(View.GONE);
//                        btSubmitAgree.setVisibility(View.VISIBLE);
//                    }

                    btSubmitAgree.setVisibility(View.VISIBLE);

                    btAgree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View v) {
                            allV = v;
                            ids = getCheckIds();
                            if (ids == null || "".equals(ids)) {
                                ToastUtils.toast(BaseTaskAndItemListActivity.this, "???????????????????????????");
                                return;
                            }

                            name = "";
                            if (fxdescription.equals("???????????????")) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(BaseTaskAndItemListActivity.this);
                                builder.setMessage("??????????????????????????????")
                                        .setCancelable(true)
                                        .setPositiveButton("???", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (isQzPz == 1) {
                                                    touchImages();
                                                } else {
                                                    jy(v, ids);
                                                }
                                                name = "???";
                                            }
                                        })
                                        .setNegativeButton("???", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (isQzPz == 1) {
                                                    touchImages();
                                                } else {
                                                    jy(v, ids);
                                                }
                                                name = "???";
                                            }
                                        });
                                builder.show();
                            } else {
                                if (isQzPz == 1) {
                                    touchImages();
                                } else {
                                    jy(v, ids);
                                }
                            }


                        }
                    });
                    btDisagree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            allV = v;
                            ids = getCheckIds();
                            if (ids == null || "".equals(ids)) {
                                ToastUtils.toast(BaseTaskAndItemListActivity.this, "???????????????????????????");
                                return;
                            }
                            if (isQzPz == 1) {
                                touchImages();
                            } else {
                                jy(v, ids);
                            }


                        }
                    });
                    btSubmitAgree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View v) {

                            allV = v;
                            ids = getCheckIds();
                            if (ids == null || "".equals(ids)) {
                                ToastUtils.toast(BaseTaskAndItemListActivity.this, "???????????????????????????");
                                return;
                            }


                            name = "";
                            if (fxdescription.equals("???????????????")) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(BaseTaskAndItemListActivity.this);
                                builder.setMessage("??????????????????????????????")
                                        .setCancelable(true)
                                        .setPositiveButton("???", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (isQzPz == 1) {
                                                    touchImages();
                                                } else {
                                                    jy(v, ids);
                                                }
                                                name = "???";
                                            }
                                        })
                                        .setNegativeButton("???", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (isQzPz == 1) {
                                                    touchImages();
                                                } else {
                                                    jy(v, ids);
                                                }
                                                name = "???";
                                            }
                                        });
                                builder.show();
                            } else {
                                if (isQzPz == 1) {
                                    touchImages();
                                } else {
                                    jy(v, ids);
                                }
                            }


                        }
                    });
                }
            } catch (Exception e) {
                ToastUtils.toast(getApplicationContext(), "??????factory????????????");
            }
        } else {
            liBottomButton.setVisibility(View.GONE);
        }
    }

    private void touchImages() {
        RmtSignRecord rmtSignRecord = new RmtSignRecord();
        Image image = new Image();

        image.setCreateUser(LoginUserRecord.getInstance().getUser().getUserid());// ?????????
        image.setCreateUsername(LoginUserRecord.getInstance().getUser().getUserName());

//        image.setTabType(mPDAWorkOrderInfoConfig.getTab_type());
        image.setImageName(description);
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra(CameraActivity.FLAG, image);
        intent.putExtra(CameraActivity.SIGNRECORD, rmtSignRecord);
        startActivityForResult(intent, BaseFragment.Take_Picture);
    }

    private RmtSignRecord rmtSignRecord;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == BaseFragment.Return_Picture) {

            Bundle extras = data.getExtras();
            rmtSignRecord = (RmtSignRecord) extras.getSerializable(SavePhotoFragment.RMTSIGNRECORD);
            Image image = (Image) extras.getSerializable(SavePhotoFragment.PICTURE);
            List<String> imageList1 = SystemProperty.getSystemProperty().getImageList();
            rmtSignRecord.setImage(image);
            rmtSignRecord.setImageList(imageList1);
            rmtSignRecord.setImageName(image.getImageName());

            jy(allV, ids);
        }
    }

    /**
     * ????????????
     *
     * @param rmtmainvo
     */
    protected void filterData(List<RmtTaskListQuery.MainvoBean> rmtmainvo) {

    }

    /**
     * setDataOrder:(??????????????????). <br/>
     * date: 2014???10???31??? <br/>
     *
     * @author zhulei
     */
    private void setDataOrder() {

    }

    private PopWinButton[] popWinButton;

    public void initPopWindows(final PopWinButton[] mPopWinButton,
                               IEventListener iEventLsn) {
        // this.mPopWinButton = mPopWinButton;
        popWindow = new ZYPOperatePopWindow(this);
        popWindow.setEventListener(iEventLsn);
        popWinButton = mPopWinButton;
    }

    /**
     * showNavPopWindows:(???????????????popwindows). <br/>
     * date: 2015???3???10??? <br/>
     *
     * @author lxf
     */
    public void showNavPopWindows(WorkOrder workOrder, View anchorView,
                                  int pointerHorizontalPosition) {
        if (popWindow != null) {
            popWindow.show(anchorView, 0, pointerHorizontalPosition,
                    popWinButton, workOrder);
        }
    }

    public void popWindowsMiss() {
        if (popWindow != null && popWindow.isShowing()) {
            popWindow.dimiss();
        }
    }

    /**
     * TODO ???????????????????????????????????????????????? showWorkOrderPopupWin:(). <br/>
     * date: 2015???3???2??? <br/>
     *
     * @author wenlin
     */
    public void showWorkOrderPopupWin(List<SuperEntity> superEntity) {
        if (mAlertDialog == null) {
            mAlertDialog = new ShowPopWindowDialog(this,
                    R.style.workorder_dialog);
        }
        mAlertDialog.setDataList(superEntity);

        mAlertDialog.show();
        WindowManager.LayoutParams params = mAlertDialog.getWindow()
                .getAttributes();
        params.height = 800;
        mAlertDialog.getWindow().setAttributes(params);
    }

    private class EventListenerImpl implements IEventListener {
        @Override
        public void eventProcess(int arg0, Object... arg1) throws HDException {
            switch (arg0) {
                case IEventType.WORK_LIST_CLICK:
                    onWorkListItemClick((WorkTask) arg1[0]);
                    break;

                case IEventType.WORK_LIST_LONG_CLICK:
                    onWorkListItemLongClick((WorkTask) arg1[0]);
                    break;
                case IEventType.ACTION_LOOK_ITEMDETAIL:
                    onWorkListItemClick(arg1);
                    break;
                case IEventType.ACTION_END_SUB_TASK:
                    //??????????????????
                    endSubTask((RmtWorkSubtask) arg1[0]);
                    break;
                case IEventType.ACTION_END_WORK_TASK:
                    //???????????????
                    endWorkTask((RmtWorkTaskBean) arg1[0]);
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * onWorkListItemClick:(). <br/>
     * date: 2015???6???3??? <br/>
     *
     * @author xuxinwen
     */
    abstract protected void onWorkListItemClick(Object... obj);

    /**
     * onWorkListItemLongClick:(). <br/>
     * date: 2015???6???3??? <br/>
     *
     * @author xuxinwen
     */
    abstract protected void onWorkListItemLongClick(WorkTask workTask);

    /**
     * ??????????????????
     *
     * @param rmtWorkSubtask
     */
    abstract protected void endSubTask(RmtWorkSubtask rmtWorkSubtask);

    /**
     * ???????????????
     */
    abstract protected void endWorkTask(RmtWorkTaskBean rmtWorkTaskBean);
}
