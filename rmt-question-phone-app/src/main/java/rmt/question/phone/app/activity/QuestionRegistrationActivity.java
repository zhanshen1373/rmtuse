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
 * 问题登记
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
     * 布局父节点
     */
    private LinearLayout liAll;
    /**
     * 是否可上传
     */
    private boolean isCanUpload = false;
    /**
     * 检查单位
     */
    private TextView mJcdeptTxt;
    /**
     * 检查人
     */
    private TextView mJcPersonTxt;
    /**
     * 检查时间
     */
    private TextView mJcTimeTxt;
    /**
     * 分项任务
     */
    private TextView mWorkOrderTxt;
    /**
     * 责任归属
     */
    private EditText edZrgs;
    /**
     * 问题描述
     */
    private EditText problemDescEd;
    /**
     * 问题地点
     */
    private EditText problemSiteEd;
    /**
     * 现场负责人
     */
    private EditText liveFzrEd;
    /**
     * 现场负责人右侧按钮
     */
    private Button btLiveFzr;
    /**
     * 违章人员
     */
    private EditText wzPersonEd;
    private Button wzPersonBt;

    /**
     * 责任单位
     */
    private EditText zrDeptEd;
    private Button zrDeptBt;

    /**
     * 罚款金额
     */
    private EditText fkMoneyEd;
    /**
     * 违约条款
     */
    // private EditText wytkEd;
    /**
     * 是否整改
     */
    private CheckBox zgCheckBox;
    /**
     * 转人员黑名单
     */
    private CheckBox blacklistCheckBox;


    /**
     * 条款分类
     */
    private TextView tvTermType;
    /**
     * 条款内容
     */
    private TextView tvTermContent;
    /**
     * 得分
     */
    private TextView tvScore;

    /**
     * 横向滚动的listview
     */
    private HorizontalListView mHorizontalListView;
    private ImgAdapter mImgAdapter;
    private LinearLayout liZg;
    private HorizontalListView hvZg;
    private ImgAdapter mImgAdapterZg;
    /**
     * 全部照片
     */
    private ArrayList<Image> imageList = new ArrayList<>();
    /**
     * 整改前
     */
    private ArrayList<Image> imageListZgq = new ArrayList<>();
    /**
     * 整改后
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

    private String[] itemNames = new String[]{"上传"};

    private final String imgtempFolderName = "img";

    /**
     * 问题清单实体类
     */
    private RmtQstnList rmtQstnList;
    private final int transcodeSuccess = 0x456;
    private final int transcodeFail = 0x457;
    private final int codeSuccess = 0x458;
    private final int codeFail = 0x459;
    /**
     * 判断activity 是否running，当Activity关闭后，结束运行的线程
     */
    private boolean isRunning = true;
    /**
     * 人员选择的dialog
     */
    private PersionSelectDialog persionSelectDialog;
    /**
     * 标记违章人员是否手填
     */
    private boolean isHandWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //删除临时图片文件夹
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
                        //图片转码成功
                        RmtQstnList rmtQstnList = getRmtQstnList();
                        List<String> imageList = (List<String>) msg.obj;
                        rmtQstnList.setImageList(imageList);
                        //上传
                        upQstnList(rmtQstnList);
                        break;
                    case transcodeFail:
                        //图片转码失败
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        ToastUtils.toast(QuestionRegistrationActivity.this, "图片转码失败");
                        break;
                    case codeSuccess:
                        //图片解码成功
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        liAll.setVisibility(View.VISIBLE);
                        isCanUpload = true;
                        getImgs();
                        break;
                    case codeFail:
                        //图片解码失败
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        ToastUtils.toast(QuestionRegistrationActivity.this, "图片解码失败");
                        break;
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        //从临时文件夹得到图片并显示
        getImgs();
    }

    private void initActionbar() {
        // 初始化ActionBar
        setCustomActionBar(false, this, new String[]{IActionBar.TV_BACK,
                IActionBar.TV_TITLE, IActionBar.BT_UP});
        // 设置导航栏标题
        setActionBartitleContent("问题登记", false);
        // 设置右侧菜单栏
    }

    private void initData() {
       /* //设置baseurl
        RetrofitUtil.getInstance().setBaseUrl("http://192.168.6.204:8155/");*/


        dialog = new ProgressDialog(QuestionRegistrationActivity.this);
        Intent intent = getIntent();
        if (intent.hasExtra(QuestionListActivity.class.getName())) {
            long qstnId = intent.getLongExtra(QuestionListActivity.class.getName(), 0);
            if (qstnId == 0) {
                ToastUtil.toast(QuestionRegistrationActivity.this, "获取qstnId出错");
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
            dialog.setMessage("加载数据中。。。");
            dialog.show();
        }
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // 获取问题清单
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
                    //解析图片并保存
                    if (dialog != null) {
                        dialog.setMessage("图片解码中");
                        dialog.show();
                    }
                    if (isRunning)
                        saveImageToSDInThread(rmtQstnList.getImageList(), rmtQstnList.getImageName());
                } else {
                    ToastUtils.toast(QuestionRegistrationActivity.this, "获取问题清单失败");
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
     * 填充view
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
                                "请删除整改照片后再进行此操作");
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
                                        "请输入违章人员！");
                                blacklistCheckBox.setChecked(false);
                            }
                        }
                    }
                });

        mImgAdapter = new ImgAdapter(this, mLayoutParams, mReLayoutParams,
                imageListZgq);
        mHorizontalListView.setAdapter(mImgAdapter);
        mHorizontalListView.setOnItemClickListener(new MyItemClickListener(
                "整改前", imageListZgq, mImgAdapter));
        mHorizontalListView.setOnItemLongClickListener(new MyLongClickListener(
                mImgAdapter));

        mImgAdapterZg = new ImgAdapter(this, mLayoutParams, mReLayoutParams,
                imageListZgh);
        hvZg.setAdapter(mImgAdapterZg);
        hvZg.setOnItemClickListener(new MyItemClickListener("整改后",
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
     * 设置检查人，检查单位，检查时间
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
     * 计算img的高度
     */
    private int getImgWidth() {
        int screenWidth, imgWidth;
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels; // 屏幕宽度（像素）
        Resources res = getResources();
        // 距左右边缘的距离
        float margin = res
                .getDimension(R.dimen.hse_pc_phone_app_layout_surveillance_padding);
        // img之间的间隔

        float imgMargin = res
                .getDimension(R.dimen.hse_pc_phone_app_fragment_layout_surveillance_img_marginright);
        imgWidth = (int) ((screenWidth - 2 * margin - 3 * imgMargin) / 3.5);
        return imgWidth;
    }

    // 初始化mLayoutParams
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

    // 计算横向listview的LayoutParams
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
                // 拍照
                touchImages(name);
            } else {
                // 照片浏览
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
            //点击分项任务
            Intent intent = new Intent(this, TaskTabulationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY, "qstn");
            startActivityForResult(intent, 0);
        } else if (v.getId() == R.id.hse_pc_phone_app_layout_surveillance_bt_leader) {
            //现场负责人
            //判断分项任务id是否存在
            if (rmtQstnList.getWork_subtask_id() == 0) {
                ToastUtils.toast(getApplication(), "请先选择分项任务再进行此操作");
            } else {
                getUsers("owner", rmtQstnList.getWork_subtask_id(), liveFzrEd);
            }
        } else if (v.getId() == R.id.hse_pc_phone_app_layout_surveillance_bt_illegal_person) {
            //违章人员
            //判断分项任务id是否存在
            if (rmtQstnList.getWork_subtask_id() == 0) {
                ToastUtils.toast(getApplication(), "请先选择分项任务再进行此操作");
            } else {
                getUsers("violator", rmtQstnList.getWork_subtask_id(), wzPersonEd);
            }
        } else if (v.getId() == R.id.hse_pc_phone_app_layout_surveillance_bt_duty_company) {
            //责任单位
            ToastUtil.toast(this, "责任单位");
        } else if (v.getId() == R.id.hse_pc_phone_app_layout_surveillance_tv_termType) {
            //条款分类
            TermTypeSelectorActivity.StartActivity(QuestionRegistrationActivity.this);

        } else if (v.getId() == R.id.hse_pc_phone_app_layout_surveillance_tv_termContent) {
            if (TextUtils.isEmpty(rmtQstnList.getTerm_type())) {
                Toast.makeText(this, "请先选择条款分类", Toast.LENGTH_LONG).show();
            } else {
                //条款内容
                TermContentSelectorActivity.StartActivity(QuestionRegistrationActivity.this ,rmtQstnList.getTerm_type());
            }
        }


    }

    @Override
    public void eventProcess(int eventType, Object... objects) throws HDException {
        switch (eventType) {
            case IEventType.ACTIONBAR_BT_UP_CLICK:
                //上传问题清单,开辟新线程去转码图片
                if (!jugeDataIsCom())
                    return;
                if (dialog != null) {
                    dialog.setMessage("图片转码中");
                    dialog.show();
                }
                getImageListInThread();
                break;
        }

    }

    /**
     * 上传问题清单
     */
    private void upQstnList(RmtQstnList rmtQstnList) {
        //上传问题清单
        if (dialog != null) {
            dialog.setMessage("正在上传问题登记");
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
                ToastUtils.toast(QuestionRegistrationActivity.this, "上传问题清单成功");
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
                ToastUtils.toast(QuestionRegistrationActivity.this, "上传问题清单失败");
            }
        });

    }

    /**
     * 判断上传数据是否完整
     *
     * @return
     */
    private boolean jugeDataIsCom() {
        //判断是否可上传
        if (!isCanUpload) {
            ToastUtils.toast(QuestionRegistrationActivity.this, "获取数据失败，请退出后重试");
            return false;
        }

        //判断必填项是否有数据
        if (problemDescEd.getText().toString() == null || "".equals(problemDescEd.getText().toString())) {
            ToastUtils.toast(QuestionRegistrationActivity.this, "请填写问题描述");
            return false;
        }
        if (mWorkOrderTxt.getText().toString() == null || "".equals(mWorkOrderTxt.getText().toString())) {
            ToastUtils.toast(QuestionRegistrationActivity.this, "请选择分项任务");
            return false;
        }
        return true;
    }


    /**
     * 上传前得到RmtQstnList
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
     * 得到imageList
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
     * 保存图片到sd卡
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
                throw new HDException("错误,图片数量不等于图片名字数量");
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
     * 得到上传图片的名字用“ ，”号分隔
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
                + File.separator + imgtempFolderName);// 文件夹路径,以作业日志id为文件夹名
        image.setImageName(name);
        Intent intent = new Intent(getBaseContext(),
                CameraCaptureActivity.class);
        intent.putExtra(CameraCaptureActivity.ENTITY_ARGS, image);
        startActivity(intent);
    }

    /**
     * 通过递归调用删除一个文件夹及下面的所有文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isFile()) {//表示该文件不是文件夹
            file.delete();
        } else {
            //首先得到当前的路径
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
     * 删除临时文件夹
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
                if (imageList.get(i).getImageName().contains("整改前")) {
                    imageListZgq.add(imageList.get(i));

                } else if (imageList.get(i).getImageName().contains("整改后")) {
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
        //删除临时图片文件夹
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
                        //换了违章分类，违章条款置空
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
     * 问题登记人员
     *
     * @param type     （现场负责人：owner,违章人员:violator）
     * @param sutaskId 分项任务id
     */
    private void getUsers(final String type, long sutaskId, final TextView tv) {
        if (dialog != null) {
            dialog.setMessage("数据加载中...");
            dialog.show();
        }
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // 获取问题清单
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
                    ToastUtils.toast(getApplicationContext(), "未获取到相关人员");
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
                ToastUtils.toast(getApplication(), "获取人员失败");
            }
        });


    }

    /**
     * 显示选择违章人员对话框
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
                    //如果之前是手书或者从未选择过人员
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
     * 显示选择现场负责人对话框
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
     * 显示人员选择对话框
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
