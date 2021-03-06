package rmt.question.phone.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUser;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.Base64Util;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.camera.CameraCaptureActivity;
import com.hd.hse.common.module.phone.custom.HorizontalListView;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;
import com.hd.hse.common.module.phone.ui.activity.PhotoPreviewActivity;
import com.hd.hse.entity.common.Image;
import com.hd.hse.entity.qstn.RmtQstnList;
import com.hd.hse.entity.sys.RmtSysUser;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.entity.workorder.TermContent;
import com.hd.hse.entity.workorder.TermType;
import com.hd.hse.system.SystemProperty;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import rmt.question.phone.app.R;
import rmt.question.phone.app.adapter.ImgAdapter;
import rmt.question.phone.app.dialog.PersionSelectDialog;
import rmt.question.phone.app.fragment.QuestionListUploadedFrag;

/**
 * created by yangning on 2017/6/1 17:11.
 * ????????????
 */

public class QuestionRegistrationActivity extends BaseFrameActivity implements View.OnClickListener, IEventListener {

    private static Logger logger = LogUtils
            .getLogger(QuestionRegistrationActivity.class);
    public static final int TERM_TYPE_RESULT_CODE = 15;
    public static final int TERM_CONTENT_RESULT_CODE = 14;
    private ProgressDialog dialog;

    public static final int REQUEST_CODE = 0;


    private RelativeLayout.LayoutParams mLayoutParams;
    private LayoutParams mLvLayoutParams;
    private LayoutParams mReLayoutParams;
    /**
     * ???????????????
     */
    private LinearLayout liAll;
    /**
     * ???????????????
     */
    private boolean isCanUpload = false;
    /**
     * ????????????
     */
    private TextView mJcdeptTxt;
    /**
     * ?????????
     */
    private TextView mJcPersonTxt;
    /**
     * ????????????
     */
    private TextView mJcTimeTxt;
    /**
     * ????????????
     */
    private TextView mWorkOrderTxt;
    /**
     * ????????????
     */
    private EditText edZrgs;
    /**
     * ????????????
     */
    private EditText problemDescEd;
    /**
     * ????????????
     */
    private EditText problemSiteEd;
    /**
     * ???????????????
     */
    private EditText liveFzrEd;
    /**
     * ???????????????????????????
     */
    private Button btLiveFzr;
    /**
     * ????????????
     */
    private EditText wzPersonEd;
    private Button wzPersonBt;

    /**
     * ????????????
     */
    private EditText zrDeptEd;
    private Button zrDeptBt;

    /**
     * ????????????
     */
    private EditText fkMoneyEd;
    /**
     * ????????????
     */
    // private EditText wytkEd;
    /**
     * ????????????
     */
    private CheckBox zgCheckBox;
    /**
     * ??????????????????
     */
    private CheckBox blacklistCheckBox;


    /**
     * ????????????
     */
    private TextView tvTermType;
    /**
     * ????????????
     */
    private TextView tvTermContent;
    /**
     * ??????
     */
    private TextView tvScore;

    /**
     * ???????????????listview
     */
    private HorizontalListView mHorizontalListView;
    private ImgAdapter mImgAdapter;
    private LinearLayout liZg;
    private HorizontalListView hvZg;
    private ImgAdapter mImgAdapterZg;
    /**
     * ????????????
     */
    private ArrayList<Image> imageList = new ArrayList<>();
    /**
     * ?????????
     */
    private ArrayList<Image> imageListZgq = new ArrayList<>();
    /**
     * ?????????
     */
    private ArrayList<Image> imageListZgh = new ArrayList<>();


    private Handler handler;


    public static final String NAME = "name";
    public static final String EDITTEXTISFOCUSABLE = "edittextisfacuable";
    public static final String ISSINGLEPERSON = "issingleperson";
    public static final int SINGLEPERSON = 1;

    public static final int REQUESTCODEONE = 1;
    public static final int REQUESTCODETWO = 2;
    public static final int READCARDEXAMINEACTIVITYCODE = 1545;

    private String[] itemNames = new String[]{"??????"};

    private final String imgtempFolderName = "img";

    /**
     * ?????????????????????
     */
    private RmtQstnList rmtQstnList;
    private final int transcodeSuccess = 0x456;
    private final int transcodeFail = 0x457;
    private final int codeSuccess = 0x458;
    private final int codeFail = 0x459;
    /**
     * ??????activity ??????running??????Activity?????????????????????????????????
     */
    private boolean isRunning = true;
    /**
     * ???????????????dialog
     */
    private PersionSelectDialog persionSelectDialog;
    /**
     * ??????????????????????????????
     */
    private boolean isHandWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //???????????????????????????
        delTempFolder();
        setContentView(R.layout.rmt_pc_phone_app_layout_surveillance);
        initActionbar();
        initHandler();
        initData();

    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void dispatchMessage(Message msg) {
                super.dispatchMessage(msg);
                switch (msg.what) {
                    case transcodeSuccess:
                        //??????????????????
                        RmtQstnList rmtQstnList = getRmtQstnList();
                        List<String> imageList = (List<String>) msg.obj;
                        rmtQstnList.setImageList(imageList);
                        //??????
                        upQstnList(rmtQstnList);
                        break;
                    case transcodeFail:
                        //??????????????????
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        ToastUtils.toast(QuestionRegistrationActivity.this, "??????????????????");
                        break;
                    case codeSuccess:
                        //??????????????????
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        liAll.setVisibility(View.VISIBLE);
                        isCanUpload = true;
                        getImgs();
                        break;
                    case codeFail:
                        //??????????????????
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        ToastUtils.toast(QuestionRegistrationActivity.this, "??????????????????");
                        break;
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        //???????????????????????????????????????
        getImgs();
    }

    private void initActionbar() {
        // ?????????ActionBar
        setCustomActionBar(false, this, new String[]{IActionBar.TV_BACK,
                IActionBar.TV_TITLE, IActionBar.BT_UP});
        // ?????????????????????
        setActionBartitleContent("????????????", false);
        // ?????????????????????
    }

    private void initData() {
       /* //??????baseurl
        RetrofitUtil.getInstance().setBaseUrl("http://192.168.6.204:8155/");*/


        dialog = new ProgressDialog(QuestionRegistrationActivity.this);
        Intent intent = getIntent();
        if (intent.hasExtra(QuestionListActivity.class.getName())) {
            long qstnId = intent.getLongExtra(QuestionListActivity.class.getName(), 0);
            if (qstnId == 0) {
                ToastUtil.toast(QuestionRegistrationActivity.this, "??????qstnId??????");
            } else {
                initView();
                getNetData(qstnId);
            }

        } else {
            rmtQstnList = new RmtQstnList();
            initView();
            setInspectdata();
            isCanUpload = true;
            liAll.setVisibility(View.VISIBLE);
        }
    }

    public void getNetData(long qstnId) {
        if (dialog != null) {
            dialog.setMessage("????????????????????????");
            dialog.show();
        }
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // ??????????????????
        Call<ResultData<RmtQstnList>> call = rmtInterface.getQstn(qstnId);
        HttpBusiness.action(QuestionRegistrationActivity.this, false, call, new HttpCallBack<RmtQstnList>() {
            @Override
            public void success(RmtQstnList rmtQstnList) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (rmtQstnList != null) {
                    QuestionRegistrationActivity.this.rmtQstnList = rmtQstnList;
                    fillView(rmtQstnList);
                    setInspectdata();
                    //?????????????????????
                    if (dialog != null) {
                        dialog.setMessage("???????????????");
                        dialog.show();
                    }
                    if (isRunning)
                        saveImageToSDInThread(rmtQstnList.getImageList(), rmtQstnList.getImageName());
                } else {
                    ToastUtils.toast(QuestionRegistrationActivity.this, "????????????????????????");
                }
            }

            @Override
            public void warning(String msg) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                ToastUtils.toast(QuestionRegistrationActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null) {
                    dialog.dismiss();
                }

            }
        });
    }

    /**
     * ??????view
     */
    private void fillView(RmtQstnList rmtQstnList) {
        mWorkOrderTxt.setText(rmtQstnList.getWork_subtask_name() + "");
        if (rmtQstnList.getQstn_type() != null)
            edZrgs.setText(rmtQstnList.getQstn_type());
        if (rmtQstnList.getDescription() != null)
            problemDescEd.setText(rmtQstnList.getDescription());
        if (rmtQstnList.getSite() != null)
            problemSiteEd.setText(rmtQstnList.getSite());

        if (rmtQstnList.getOwner_name() != null)
            liveFzrEd.setText(rmtQstnList.getOwner_name());
        if (rmtQstnList.getViolator_name() != null)
            wzPersonEd.setText(rmtQstnList.getViolator_name());
        if (rmtQstnList.getQstn_unit_name() != null)
            zrDeptEd.setText(rmtQstnList.getQstn_unit_name());

        if (rmtQstnList.getTerm_type() != null)
            tvTermType.setText(rmtQstnList.getTerm_type());
        if (rmtQstnList.getTerm_content() != null)
            tvTermContent.setText(rmtQstnList.getTerm_content());
        if (rmtQstnList.getScore() != null)
            tvScore.setText(rmtQstnList.getScore().toString());

    }

    private void initView() {
        int width = getImgWidth();
        mLayoutParams = getLayoutParams(width);
        mLvLayoutParams = getLvLayoutParams(width);
        mReLayoutParams = getReLayoutParams(width);
        liAll = (LinearLayout) findViewById(R.id.hse_pc_phone_app_layout_surveillance_li_all);
        mWorkOrderTxt = (TextView) findViewById(R.id.hse_pc_phone_app_layout_surveillance_tv_workorder);
        edZrgs = (EditText) findViewById(R.id.hse_pc_phone_app_layout_surveillance_ed_zrgs);
        mJcdeptTxt = (TextView) findViewById(R.id.hse_pc_phone_app_layout_surveillance_ed_organization);
        mJcPersonTxt = (TextView) findViewById(R.id.hse_pc_phone_app_layout_surveillance_tv_name);
        mJcTimeTxt = (TextView) findViewById(R.id.hse_pc_phone_app_layout_surveillance_tv_time);
        problemDescEd = (EditText) findViewById(R.id.hse_pc_phone_app_layout_surveillance_ed_describe_problem);
        problemSiteEd = (EditText) findViewById(R.id.hse_pc_phone_app_layout_surveillance_ed_site);
        liveFzrEd = (EditText) findViewById(R.id.hse_pc_phone_app_layout_surveillance_ed_leader);
        btLiveFzr = (Button) findViewById(R.id.hse_pc_phone_app_layout_surveillance_bt_leader);
        wzPersonEd = (EditText) findViewById(R.id.hse_pc_phone_app_layout_surveillance_ed_illegal_person);
        wzPersonBt = (Button) findViewById(R.id.hse_pc_phone_app_layout_surveillance_bt_illegal_person);
        zrDeptEd = (EditText) findViewById(R.id.hse_pc_phone_app_layout_surveillance_ed_duty_company);
        zrDeptBt = (Button) findViewById(R.id.hse_pc_phone_app_layout_surveillance_bt_duty_company);
        fkMoneyEd = (EditText) findViewById(R.id.hse_pc_phone_app_layout_surveillance_ed_penalty_money);
        zgCheckBox = (CheckBox) findViewById(R.id.hse_pc_phone_app_layout_surveillance_checkbox_rectify);
        blacklistCheckBox = (CheckBox) findViewById(R.id.hse_pc_phone_app_layout_surveillance_checkbox_blacklist);
        mHorizontalListView = (HorizontalListView) findViewById(R.id.hse_pc_phone_app_layout_surveillance_lv);
        liZg = (LinearLayout) findViewById(R.id.hse_pc_phone_app_layout_surveillance_li_zg);
        hvZg = (HorizontalListView) findViewById(R.id.hse_pc_phone_app_layout_surveillance_lv_zg);
        tvTermType = (TextView) findViewById(R.id.hse_pc_phone_app_layout_surveillance_tv_termType);
        tvTermContent = (TextView) findViewById(R.id.hse_pc_phone_app_layout_surveillance_tv_termContent);
        tvScore = (TextView) findViewById(R.id.hse_pc_phone_app_layout_surveillance_tv_score);

        wzPersonEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isHandWrite = true;
                rmtQstnList.setViolator_id(null);
                rmtQstnList.setViolator_name(wzPersonEd.getText().toString().trim());
            }
        });

        wzPersonBt.setOnClickListener(this);
        mWorkOrderTxt.setOnClickListener(this);
        btLiveFzr.setOnClickListener(this);
        zrDeptBt.setOnClickListener(this);
        tvTermType.setOnClickListener(this);
        tvTermContent.setOnClickListener(this);

        mHorizontalListView.setLayoutParams(mLvLayoutParams);
        hvZg.setLayoutParams(mLvLayoutParams);
        zgCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // TODO Auto-generated method stub
                if (arg1) {
                    liZg.setVisibility(View.VISIBLE);
                } else {
                    if (imageListZgh != null && imageListZgh.size() > 0) {
                        ToastUtils.toast(QuestionRegistrationActivity.this,
                                "??????????????????????????????????????????");
                        zgCheckBox.setChecked(true);
                    } else {
                        liZg.setVisibility(View.GONE);
                    }

                }

            }
        });
        blacklistCheckBox
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton arg0,
                                                 boolean arg1) {
                        if (arg1) {
                            if (wzPersonEd.getText().toString() == null
                                    || wzPersonEd.getText().toString()
                                    .equals("")) {
                                ToastUtils.toast(
                                        QuestionRegistrationActivity.this,
                                        "????????????????????????");
                                blacklistCheckBox.setChecked(false);
                            }
                        }
                    }
                });

        mImgAdapter = new ImgAdapter(this, mLayoutParams, mReLayoutParams,
                imageListZgq);
        mHorizontalListView.setAdapter(mImgAdapter);
        mHorizontalListView.setOnItemClickListener(new MyItemClickListener(
                "?????????", imageListZgq, mImgAdapter));
        mHorizontalListView.setOnItemLongClickListener(new MyLongClickListener(
                mImgAdapter));

        mImgAdapterZg = new ImgAdapter(this, mLayoutParams, mReLayoutParams,
                imageListZgh);
        hvZg.setAdapter(mImgAdapterZg);
        hvZg.setOnItemClickListener(new MyItemClickListener("?????????",
                imageListZgh, mImgAdapterZg));
        hvZg.setOnItemLongClickListener(new MyLongClickListener(mImgAdapterZg));

        fkMoneyEd.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable edt) {
                String temp = edt.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0)
                    return;
                if (temp.length() - posDot - 1 > 2) {
                    edt.delete(posDot + 3, posDot + 4);
                }
            }

            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            }
        });


    }

    /**
     * ?????????????????????????????????????????????
     */
    private void setInspectdata() {
        LoginUser loginUser = LoginUserRecord.getInstance().getUser();
        if (loginUser != null) {
            mJcdeptTxt.setText(loginUser.getOrgName());
            mJcPersonTxt.setText(loginUser.getUserName());
            mJcTimeTxt.setText(getNowTime());
            rmtQstnList.setInspector_name(loginUser.getUserName());
            rmtQstnList.setInspector_id(loginUser.getUserid());
            rmtQstnList.setInspect_unit_id(loginUser.getOrgid());
            rmtQstnList.setInspect_unit_name(loginUser.getOrgName());
            rmtQstnList.setInspect_time(getNowTime());
        }
    }

    /**
     * ??????img?????????
     */
    private int getImgWidth() {
        int screenWidth, imgWidth;
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels; // ????????????????????????
        Resources res = getResources();
        // ????????????????????????
        float margin = res
                .getDimension(R.dimen.hse_pc_phone_app_layout_surveillance_padding);
        // img???????????????

        float imgMargin = res
                .getDimension(R.dimen.hse_pc_phone_app_fragment_layout_surveillance_img_marginright);
        imgWidth = (int) ((screenWidth - 2 * margin - 3 * imgMargin) / 3.5);
        return imgWidth;
    }

    // ?????????mLayoutParams
    private RelativeLayout.LayoutParams getLayoutParams(int width) {

        RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(
                width, width);
        Resources res = getResources();
        // float imgMargin =
        // res.getDimension(R.dimen.hse_pc_phone_app_fragment_layout_surveillance_img_marginright);
        mLayoutParams.setMargins(0, 0, 5, 0);
        return mLayoutParams;
    }

    private LayoutParams getReLayoutParams(int width) {
        // int imgMargin =
        // getResources().getDimensionPixelOffset(R.dimen.hse_pc_phone_app_fragment_layout_surveillance_img_marginright);
        LayoutParams mLayoutParams = new LayoutParams(
                width, width);
        return mLayoutParams;
    }

    // ????????????listview???LayoutParams
    private LayoutParams getLvLayoutParams(int width) {

        LayoutParams mLayoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, width);
        int margin = getResources().getDimensionPixelOffset(
                R.dimen.hse_pc_phone_app_layout_surveillance_padding);
        mLayoutParams.setMargins(margin, margin, margin, margin);
        return mLayoutParams;
    }

    private class MyItemClickListener implements AdapterView.OnItemClickListener {
        private String name;
        private ArrayList<Image> imageList;
        private ImgAdapter mImgAdapter;

        public MyItemClickListener(String name, ArrayList<Image> imageList,
                                   ImgAdapter mImgAdapter) {
            this.name = name;
            this.imageList = imageList;
            this.mImgAdapter = mImgAdapter;
        }

        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            int shotPostion = imageList.size();
            if (shotPostion == arg2) {
                // ??????
                touchImages(name);
            } else {
                // ????????????
                Intent intent = new Intent(QuestionRegistrationActivity.this,
                        PhotoPreviewActivity.class);
                intent.putExtra(PhotoPreviewActivity.SELECTEDINDEX, arg2);
                intent.putExtra(PhotoPreviewActivity.IMAGESET, imageList);
                startActivity(intent);
            }
        }
    }

    private class MyLongClickListener implements AdapterView.OnItemLongClickListener {
        private ImgAdapter mImgAdapter;

        public MyLongClickListener(ImgAdapter mImgAdapter) {
            this.mImgAdapter = mImgAdapter;
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
            boolean isDele = mImgAdapter.isDele();
            if (!isDele) {
                mImgAdapter.setDele(true);
                mImgAdapter.notifyDataSetChanged();
            }
            return false;
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.hse_pc_phone_app_layout_surveillance_tv_workorder) {
            //??????????????????
            Intent intent = new Intent(this, TaskTabulationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY, "qstn");
            startActivityForResult(intent, 0);
        } else if (v.getId() == R.id.hse_pc_phone_app_layout_surveillance_bt_leader) {
            //???????????????
            //??????????????????id????????????
            if (rmtQstnList.getWork_subtask_id() == 0) {
                ToastUtils.toast(getApplication(), "??????????????????????????????????????????");
            } else {
                getUsers("owner", rmtQstnList.getWork_subtask_id(), liveFzrEd);
            }
        } else if (v.getId() == R.id.hse_pc_phone_app_layout_surveillance_bt_illegal_person) {
            //????????????
            //??????????????????id????????????
            if (rmtQstnList.getWork_subtask_id() == 0) {
                ToastUtils.toast(getApplication(), "??????????????????????????????????????????");
            } else {
                getUsers("violator", rmtQstnList.getWork_subtask_id(), wzPersonEd);
            }
        } else if (v.getId() == R.id.hse_pc_phone_app_layout_surveillance_bt_duty_company) {
            //????????????
            ToastUtil.toast(this, "????????????");
        } else if (v.getId() == R.id.hse_pc_phone_app_layout_surveillance_tv_termType) {
            //????????????
            TermTypeSelectorActivity.StartActivity(QuestionRegistrationActivity.this);

        } else if (v.getId() == R.id.hse_pc_phone_app_layout_surveillance_tv_termContent) {
            if (TextUtils.isEmpty(rmtQstnList.getTerm_type())) {
                Toast.makeText(this, "????????????????????????", Toast.LENGTH_LONG).show();
            } else {
                //????????????
                TermContentSelectorActivity.StartActivity(QuestionRegistrationActivity.this ,rmtQstnList.getTerm_type());
            }
        }


    }

    @Override
    public void eventProcess(int eventType, Object... objects) throws HDException {
        switch (eventType) {
            case IEventType.ACTIONBAR_BT_UP_CLICK:
                //??????????????????,??????????????????????????????
                if (!jugeDataIsCom())
                    return;
                if (dialog != null) {
                    dialog.setMessage("???????????????");
                    dialog.show();
                }
                getImageListInThread();
                break;
        }

    }

    /**
     * ??????????????????
     */
    private void upQstnList(RmtQstnList rmtQstnList) {
        //??????????????????
        if (dialog != null) {
            dialog.setMessage("????????????????????????");
            dialog.show();
        }
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.submitQstnList(rmtQstnList);
        HttpBusiness.action(QuestionRegistrationActivity.this, false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object objectResultData) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                ToastUtils.toast(QuestionRegistrationActivity.this, "????????????????????????");
                setResult(QuestionListUploadedFrag.RESULTCODE);
                finish();
            }

            @Override
            public void warning(String msg) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                ToastUtils.toast(QuestionRegistrationActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                ToastUtils.toast(QuestionRegistrationActivity.this, "????????????????????????");
            }
        });

    }

    /**
     * ??????????????????????????????
     *
     * @return
     */
    private boolean jugeDataIsCom() {
        //?????????????????????
        if (!isCanUpload) {
            ToastUtils.toast(QuestionRegistrationActivity.this, "???????????????????????????????????????");
            return false;
        }

        //??????????????????????????????
        if (problemDescEd.getText().toString() == null || "".equals(problemDescEd.getText().toString())) {
            ToastUtils.toast(QuestionRegistrationActivity.this, "?????????????????????");
            return false;
        }
        if (mWorkOrderTxt.getText().toString() == null || "".equals(mWorkOrderTxt.getText().toString())) {
            ToastUtils.toast(QuestionRegistrationActivity.this, "?????????????????????");
            return false;
        }
        return true;
    }


    /**
     * ???????????????RmtQstnList
     *
     * @return
     */
    private RmtQstnList getRmtQstnList() {
        String imageNames = getImageNames();
        rmtQstnList.setImageName(imageNames);
        rmtQstnList.setDescription(problemDescEd.getText().toString().trim());
        rmtQstnList.setSite(problemSiteEd.getText().toString().trim());
        rmtQstnList.setQstn_type(edZrgs.getText().toString().trim());
        return rmtQstnList;
    }

    private void getImageListInThread() {

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    List<String> imageList = getImageList();
                    Message msg = Message.obtain();
                    msg.what = transcodeSuccess;
                    msg.obj = imageList;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = transcodeFail;
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    /**
     * ??????imageList
     *
     * @return
     */
    private List<String> getImageList() throws Exception {
        List<String> imageList = new ArrayList<String>();
        List<Image> all = new ArrayList<Image>();
        all.addAll(imageListZgq);
        all.addAll(imageListZgh);
        for (Image img : all) {
            imageList.add(Base64Util.encodeBase64File(img.getImagepath()));
        }

        return imageList;
    }

    /**
     * ???????????????sd???
     *
     * @param imageList
     * @param imageName
     */
    private void saveImageToSD(List<String> imageList, String imageName) throws Exception {

        if (imageList != null && imageList.size() > 0 && imageName != null && !"".equals(imageName)) {
            String imageNames[] = imageName.split(",");
            if (imageNames.length == imageList.size()) {
                for (int i = 0; i < imageList.size(); i++) {
                    Base64Util.decoderBase64File(imageList.get(i), SystemProperty.getSystemProperty().getRootDBpath()
                            + File.separator + imgtempFolderName + File.separator + imageNames[i]);
                }
            } else {
                throw new HDException("??????,???????????????????????????????????????");
            }
        }
    }

    private void saveImageToSDInThread(final List<String> imageList, final String imageName) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    saveImageToSD(imageList, imageName);
                    Message msg = Message.obtain();
                    msg.what = codeSuccess;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage(), null);
                    Message msg = Message.obtain();
                    msg.what = codeFail;
                    handler.sendMessage(msg);
                }
            }
        }.start();

    }


    /**
     * ????????????????????????????????? ???????????????
     *
     * @return
     */
    private String getImageNames() {
        String imageNames = "";
        List<Image> all = new ArrayList<Image>();
        all.addAll(imageListZgq);
        all.addAll(imageListZgh);
        for (Image img : all) {
            if (imageNames.equals("")) {
                imageNames += img.getImageName();
            } else {
                imageNames += ("," + img.getImageName());
            }
        }
        return imageNames;
    }

    private void touchImages(String name) {
        File f = new File(SystemProperty.getSystemProperty().getRootDBpath()
                + File.separator + imgtempFolderName);
        if (!f.exists())
            f.mkdirs();
        Image image = new Image();
        image.setImagepath(SystemProperty.getSystemProperty().getRootDBpath()
                + File.separator + imgtempFolderName);// ???????????????,???????????????id???????????????
        image.setImageName(name);
        Intent intent = new Intent(getBaseContext(),
                CameraCaptureActivity.class);
        intent.putExtra(CameraCaptureActivity.ENTITY_ARGS, image);
        startActivity(intent);
    }

    /**
     * ???????????????????????????????????????????????????????????????
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isFile()) {//??????????????????????????????
            file.delete();
        } else {
            //???????????????????????????
            File[] childFiles = file.listFiles();
            if (childFiles != null && childFiles.length > 0) {
                for (File f : childFiles) {
                    deleteFile(f);
                }
            }
            //file.delete();
        }
    }

    /**
     * ?????????????????????
     */
    private void delTempFolder() {
        File f = new File(SystemProperty.getSystemProperty().getRootDBpath()
                + File.separator + imgtempFolderName);
        if (f.exists()) {
            deleteFile(f);
        }
    }

    private void getImgs() {
        imageList.clear();
        File f = new File(SystemProperty.getSystemProperty().getRootDBpath()
                + File.separator + imgtempFolderName);
        if (f.exists()) {

            File[] childFiles = f.listFiles();
            for (int i = 0; i < childFiles.length; i++) {
                Image img = new Image();
                img.setImagepath(childFiles[i].getAbsolutePath());
                img.setImageName(childFiles[i].getName());
                imageList.add(img);
            }
            imageListZgh.clear();
            imageListZgq.clear();
            for (int i = 0; i < imageList.size(); i++) {
                if (imageList.get(i).getImageName().contains("?????????")) {
                    imageListZgq.add(imageList.get(i));

                } else if (imageList.get(i).getImageName().contains("?????????")) {
                    imageListZgh.add(imageList.get(i));
                }
            }

            mImgAdapter.setDele(true);
            mImgAdapter.setShotCamraPostion(imageListZgq.size());
            mImgAdapter.notifyDataSetChanged();
            mImgAdapterZg.setDele(true);
            mImgAdapterZg.setShotCamraPostion(imageListZgh.size());
            mImgAdapterZg.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        isRunning = false;
        dialog = null;
        //???????????????????????????
        delTempFolder();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case TaskTabulationActivity.RESULTCODE:
                RmtWorkSubtask rmtWorkSubtask = (RmtWorkSubtask) data.getSerializableExtra(TaskTabulationActivity.RMTWORKSUBTASK);
                mWorkOrderTxt.setText(rmtWorkSubtask.getDescription());
                rmtQstnList.setWork_subtask_id(rmtWorkSubtask.getWork_subtask_id());
                rmtQstnList.setWork_subtask_name(rmtWorkSubtask.getDescription());
                break;

            case TERM_TYPE_RESULT_CODE:
                TermType termType = (TermType) data.getSerializableExtra(TermTypeSelectorActivity.KEY_TERMTYPE);
                if (termType != null) {
                    tvTermType.setText(termType.getName());
                    if (termType.getCode() != null && !termType.getCode().equals(rmtQstnList.getTerm_type())) {
                        //???????????????????????????????????????
                        rmtQstnList.setTerm_type(termType.getCode());
                        rmtQstnList.setTerm_code(null);
                        rmtQstnList.setViolationterm_id(null);
                        rmtQstnList.setTerm_content(null);
                        rmtQstnList.setScore(null);

                        tvTermContent.setText("");
                        tvScore.setText("");
                    }
                }

                break;
            case TERM_CONTENT_RESULT_CODE:
                TermContent termContent = (TermContent) data.getSerializableExtra(TermContentSelectorActivity.KEY_TERMCONTENT);
                if (termContent != null) {
                    rmtQstnList.setTerm_code(termContent.getTerm_code());
                    rmtQstnList.setViolationterm_id(termContent.getViolationterm_id());
                    rmtQstnList.setTerm_content(termContent.getContent());
                    rmtQstnList.setScore(termContent.getScore());
                    tvTermContent.setText(termContent.getContent());
                    tvScore.setText(termContent.getScore().toString());
                }
                break;
        }

    }

    /**
     * ??????????????????
     *
     * @param type     ?????????????????????owner,????????????:violator???
     * @param sutaskId ????????????id
     */
    private void getUsers(final String type, long sutaskId, final TextView tv) {
        if (dialog != null) {
            dialog.setMessage("???????????????...");
            dialog.show();
        }
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // ??????????????????
        Call<ResultData<List<RmtSysUser>>> call = rmtInterface.getUsers(type, sutaskId);
        HttpBusiness.action(QuestionRegistrationActivity.this, false, call, new HttpCallBack<List<RmtSysUser>>() {

            @Override
            public void success(List<RmtSysUser> rmtSysUsers) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (rmtSysUsers != null && rmtSysUsers.size() > 0) {
                    if (type != null && type.equals("owner"))
                        showOwnerDialog(rmtSysUsers, tv);
                    else if (type != null && type.equals("violator"))
                        showViolatorDialog(rmtSysUsers, tv);
                } else {
                    ToastUtils.toast(getApplicationContext(), "????????????????????????");
                }

            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplication(), msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUtils.toast(getApplication(), "??????????????????");
            }
        });


    }

    /**
     * ?????????????????????????????????
     *
     * @param rmtSysUsers
     */
    private void showViolatorDialog(final List<RmtSysUser> rmtSysUsers, final TextView tv) {
        showPersionSelectDialog(rmtSysUsers);
        persionSelectDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                persionSelectDialog.dismiss();

                if (isHandWrite || rmtQstnList.getViolator_id() == null || rmtQstnList.getViolator_id().equals("")) {
                    //????????????????????????????????????????????????
                    tv.setText(rmtSysUsers.get(position).getPerson_name());
                    rmtQstnList.setViolator_id(rmtSysUsers.get(position).getUserid() + "");
                    rmtQstnList.setViolator_name(rmtSysUsers.get(position).getPerson_name());
                } else {
                    rmtQstnList.setViolator_id(rmtQstnList.getViolator_id() + "," + rmtSysUsers.get(position).getUserid());
                    rmtQstnList.setViolator_name(rmtQstnList.getViolator_name() + "," + rmtSysUsers.get(position).getPerson_name());
                    tv.setText(rmtQstnList.getInspector_name());
                }
                isHandWrite = false;


            }
        });
    }

    /**
     * ????????????????????????????????????
     *
     * @param rmtSysUsers
     */
    private void showOwnerDialog(final List<RmtSysUser> rmtSysUsers, final TextView tv) {
        showPersionSelectDialog(rmtSysUsers);
        persionSelectDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                persionSelectDialog.dismiss();
                tv.setText(rmtSysUsers.get(position).getPerson_name());
                rmtQstnList.setOwner_id(rmtSysUsers.get(position).getUserid());
                rmtQstnList.setOwner_name(rmtSysUsers.get(position).getPerson_name());
                zrDeptEd.setText(rmtSysUsers.get(position).getOrgname());
                rmtQstnList.setQstn_unit_id(rmtSysUsers.get(position).getOrgid());
                rmtQstnList.setQstn_unit_name(rmtSysUsers.get(position).getOrgname());

            }
        });
    }

    /**
     * ???????????????????????????
     *
     * @param rmtSysUsers
     */
    private void showPersionSelectDialog(List<RmtSysUser> rmtSysUsers) {
        if (persionSelectDialog == null) {
            persionSelectDialog = new PersionSelectDialog(rmtSysUsers, QuestionRegistrationActivity.this);
        } else {
            persionSelectDialog.setRmtSysUsers(rmtSysUsers);
            persionSelectDialog.show();
        }
    }

}
