package com.hd.hse.common.module.branch.ui.model.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.custom.BottomActionBar;
import com.hd.hse.common.component.phone.custom.MessageDialog;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.branch.ui.model.activity.PaintSignatureActivity;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.camera2.CameraActivity;
import com.hd.hse.common.module.phone.camera2.SavePhotoFragment;
import com.hd.hse.common.module.phone.ui.activity.FragmentSignActivity;
import com.hd.hse.entity.common.Image;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.entity.sign.ImgStringVo;
import com.hd.hse.entity.sys.RmtSignRecord;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.system.SystemProperty;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.hayden.hap.hd_push.sdk.HDPush.context;
import static com.hd.hse.common.module.branch.ui.model.activity.PaintSignatureActivity.PAINTSIGNATURE;
import static com.hd.hse.common.module.branch.ui.model.activity.PaintSignatureActivity.RMTSIGNRECORD;
import static com.hd.hse.common.module.branch.ui.model.activity.PaintSignatureActivity.SIGN_PIC_SUCCESS_CODE;

public abstract class BaseFragment extends Fragment {

    private static Logger logger = LogUtils.getLogger(BaseFragment.class);

    public final static int STYLE_SINGLE_BUTTON = 0;
    public final static int STYLE_MULTI_MENU = 1;
    public final static int STYLE_NO_MENU = 2;

    private FrameLayout vMainContainer;
    private FrameLayout vBottomContainer;

    protected List<RmtConfMIntfc> mConfigs;

    private TextView vSingleButton;
    private BottomActionBar vBottomActionBar;

    private int mBottomStyle = 0;

    protected RmtConfMIntfc mPDAWorkOrderInfoConfig;

    /**
     * moduleCode:TODO(????????????).
     */
    private String moduleCode = "";
    /**
     * ????????????
     */
    protected RmtSignRecord rmtSignRecord;
    /**
     * ?????????????????????????????????
     */
    private String folderPath;
    /**
     * ????????????????????????
     */
    protected boolean upApprRecordState = false;

    /**
     *
     */
    private RmtWorkSubtask rmtWorkSubtask;


    /**
     * ??????code??????,???????????????id????????????????????????????????????
     */
    private String[] orgCodes = {"NX010303", "NX01030301", "NX01030302", "NX01030303", "NX01030304", "NX01030305"};
    private RmtConfMIntfc rcfm;
    private boolean orgidIsInSet;
    private RmtSignRecord qzpz;
    private Image image;

//    "NX010305", "NX010306", "NX010307", "NX010308", "NX010309", "NX010310"

    /**
     * ???????????????????????????????????????
     * Creates a new instance of BaseFragment.
     *
     * @param bottomStyle
     */
    public BaseFragment(int bottomStyle) {
        super();
        mBottomStyle = bottomStyle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View _resultView = inflater.inflate(R.layout.hd_hse_common_module_base_fragment, null);


        vMainContainer = (FrameLayout) _resultView.findViewById(R.id.hd_hse_common_module_container);
        vBottomContainer = (FrameLayout) _resultView.findViewById(R.id.hd_hse_common_module_bottom_container);
        vSingleButton = (TextView) _resultView.findViewById(R.id.hd_hse_common_module_btn);
        vBottomActionBar = (BottomActionBar) _resultView.findViewById(R.id.hd_hse_common_module_bottom_action_bar);

        vSingleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mBottomButtonClickListener == null) {
                    return;
                }

                MessageDialog.Builder builder = new MessageDialog.Builder(getActivity());
                builder.setTitle("??????");
                builder.setMessage("?????????????");
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        // ??????1.???????????????????????? ???????????????  ????????????
                        // ??????2 ????????????????????????  ???????????? ?????????????????????????????????????????????????????????
                        if (rmtWorkSubtask != null) {
                            if (LoginUserRecord.getInstance().getUser().getOrgid() != rmtWorkSubtask.getWork_unit_id()) {
                                //????????????
                                sqCheck(v);
                            } else {
                                List<Long> orgidList = LoginUserRecord.getInstance().getOrgidList();
                                if (orgidList.contains(rmtWorkSubtask.getWork_unit_id())) {
                                    //??????????????????????????????????????????????????????
                                    sqCheck(v);
                                } else {
                                    mBottomButtonClickListener.onClick(v);
                                }
                            }
                        } else {
                            mBottomButtonClickListener.onClick(v);
                        }


                        dialog.dismiss();

                    }
                });
                builder.setNegativeButton("??????",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.createWarm().show();
            }
        });

        vBottomActionBar.setIEventListener(new IEventListener() {

            @Override
            public void eventProcess(final int eventType, final Object... objects)
                    throws HDException {
                if (mBottomActionBarEventListener == null) {
                    return;
                }
                if (eventType != IEventType.BOTTOM_BUTTON_CLICK) {
                    try {
                        mBottomActionBarEventListener.eventProcess(eventType, objects);
                    } catch (HDException e) {
                        logger.error(e);
                    }
                    return;
                }

                MessageDialog.Builder builder = new MessageDialog.Builder(getActivity());
                builder.setTitle("??????");
                builder.setMessage("?????????????");
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            mBottomActionBarEventListener.eventProcess(eventType, objects);
                        } catch (HDException e) {
                            logger.error(e);
                            ToastUtils.toast(getActivity(), e.getMessage());
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("??????",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.createWarm().show();

            }
        });

        final int _bottomStyle = mBottomStyle;

        switch (_bottomStyle) {
            case STYLE_MULTI_MENU:

                vSingleButton.setVisibility(View.GONE);
                vBottomActionBar.setVisibility(View.VISIBLE);

                break;
            case STYLE_SINGLE_BUTTON:

                vSingleButton.setVisibility(View.VISIBLE);
                vBottomActionBar.setVisibility(View.GONE);

                break;
            case STYLE_NO_MENU:
                vSingleButton.setVisibility(View.GONE);
                vBottomActionBar.setVisibility(View.GONE);
                break;
        }

        /*
         * ???????????? UI ?????????????????????????????????????????????????????? onCreatePageView ?????????
         * ?????????????????????????????????????????????????????????UI??????????????????
         */
        View _pageView = onCreatePageView(inflater);

        if (_pageView != null) {
            vMainContainer.addView(_pageView);
        }

        return _resultView;
    }


    private void sqCheck(final View v) {

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
                    mBottomButtonClickListener.onClick(v);
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

    public void setConfigList(List<RmtConfMIntfc> configList) {
        mConfigs = configList;
    }

    /**
     * ??????????????????????????????????????????????????????UI??????????????????????????????????????????????????????????????????
     *
     * @return
     */
    protected abstract View onCreatePageView(LayoutInflater inflater);

    /**
     * ????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * ???????????????
     */
    private OnClickListener mBottomButtonClickListener;

    /**
     * ??????????????????????????????
     * setOnBottomButtonClickListener:(). <br/>
     * date: 2015???6???4??? <br/>
     *
     * @param l
     * @author xuxinwen
     */
    public void setOnBottomButtonClickListener(OnClickListener l) {
        mBottomButtonClickListener = l;

    }

    /**
     * ??????????????????????????????
     * setBottomButtonText:(). <br/>
     * date: 2015???6???4??? <br/>
     *
     * @param text
     * @author xuxinwen
     */
    public void setBottomButtonText(String text) {
        vSingleButton.setText(text);
    }

    /**
     * ?????????????????????????????????????????????????????????????????????????????????dialog
     */
    private IEventListener mBottomActionBarEventListener;

    /**
     * ????????????????????????????????????????????????????????????BottomActionBar??????????????????
     * ???????????????
     * setOnBottomActionBarEventListener:(). <br/>
     * date: 2015???6???5??? <br/>
     *
     * @param listener
     * @author xuxinwen
     */
    public void setOnBottomActionBarEventListener(IEventListener listener) {
        mBottomActionBarEventListener = listener;

    }

    /**
     * ?????????????????? PDAWorkOrderInfoConfig ?????????????????????????????? Fragment ????????????
     * Activity ??????????????????????????????????????????
     * setPDAWorkOrderInfoConfig:(). <br/>
     * date: 2015???6???8??? <br/>
     *
     * @param config
     * @author xuxinwen
     */
    public void setPDAWorkOrderInfoConfig(RmtConfMIntfc config) {
        mPDAWorkOrderInfoConfig = config;
        //?????????????????????????????????
        if (mPDAWorkOrderInfoConfig.getVirRiskMVOList() != null
                && mPDAWorkOrderInfoConfig.getVirRiskMVOList().size() > 0
                && mPDAWorkOrderInfoConfig.getVirRiskMVOList().get(0) != null
                && !TextUtils.isEmpty(mPDAWorkOrderInfoConfig.getVirRiskMVOList().get(0).getConfirmed_by())) {
            String confirmed_by = mPDAWorkOrderInfoConfig.getVirRiskMVOList().get(0).getConfirmed_by();
            long userid = LoginUserRecord.getInstance().getUser().getUserid();
            if (confirmed_by.contains(userid + "")) {
                mPDAWorkOrderInfoConfig.setApprove(true);
            }
        }

        folderPath = SystemProperty.getSystemProperty().getRootDBpath()
                + File.separator + "paintSignature" + File.separator
                + config.getWork_subtask_id() + File.separator
                + config.getTab_name();
    }

    /**
     * getModuleCode:(????????????). <br/>
     * date: 2015???6???18??? <br/>
     *
     * @return
     * @author lxf
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * setModuleCode:(????????????). <br/>
     * date: 2015???6???18??? <br/>
     *
     * @param moduleCode
     * @author lxf
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    protected void uploadRecord(RmtConfMIntfc rmtConfMIntfc) {
        rcfm = rmtConfMIntfc;
        orgidIsInSet = true;
        String orgCode = LoginUserRecord.getInstance().getUser().getOrgCode();
        for (int i = 0; i < orgCodes.length; i++) {
            if (orgCodes[i].equalsIgnoreCase((orgCode))) {
                orgidIsInSet = false;
                break;
            }
        }
        boolean forcePhoto = false;
        if (rmtConfMIntfc.getApprAuthVOList() != null && rmtConfMIntfc.getApprAuthVOList().size() > 0 && rmtConfMIntfc.getApprAuthVOList().get(0) != null) {
            if (rmtConfMIntfc.getApprAuthVOList().get(0).getForce_photo() == 1) {
                forcePhoto = true;

            }
        }
        if (!upApprRecordState) {

            if (forcePhoto) {
                //???????????????
                touchImages();
            } else {

                if (rmtConfMIntfc.getApprAuthVOList() != null
                        && rmtConfMIntfc.getApprAuthVOList().size() > 0
                        && rmtConfMIntfc.getApprAuthVOList().get(0) != null
                        && rmtConfMIntfc.getApprAuthVOList().get(0).getHandwrite() == 1
                        && orgidIsInSet) {
                    //??????????????????
                    Intent intent = new Intent(getActivity(), PaintSignatureActivity.class);
                    intent.putExtra(PAINTSIGNATURE, folderPath);
                    startActivityForResult(intent, 0);
                } else {
                    saveApprRecord(rmtConfMIntfc);
                }
            }


        } else if (upApprRecordState && rmtSignRecord != null) {
            if (forcePhoto) {
                //???????????????
                touchImages();
            } else {
                uploadSignPic(rmtSignRecord);
            }

        } else {
            if (forcePhoto) {
                //???????????????
                touchImages();
            } else {
                saveApprRecord(rmtConfMIntfc);
            }
        }
    }


    /**
     * ????????????????????????
     *
     * @param rmtSignRecord
     */
    private void upLoadQzPic(RmtSignRecord rmtSignRecord) {
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("??????????????????...");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.uploadQZPic(rmtSignRecord);
        HttpBusiness.action(getActivity(), false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "??????????????????!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "??????????????????!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static final int Take_Picture = 0x001;
    public static final int Return_Picture = 0x002;

    private void touchImages() {
        RmtSignRecord rmtSignRecord = new RmtSignRecord();
        Image image = new Image();

        image.setCreateUser(LoginUserRecord.getInstance().getUser().getUserid());// ?????????
        image.setCreateUsername(LoginUserRecord.getInstance().getUser().getUserName());

        image.setTabType(mPDAWorkOrderInfoConfig.getTab_type());
        image.setImageName(mPDAWorkOrderInfoConfig.getTab_name());
        Intent intent = new Intent(getActivity(), CameraActivity.class);
        intent.putExtra(CameraActivity.FLAG, image);
        intent.putExtra(CameraActivity.SIGNRECORD, rmtSignRecord);
        startActivityForResult(intent, BaseFragment.Take_Picture);
    }


    /**
     * ??????????????????
     *
     * @param rmtConfMIntfc
     */
    protected void saveApprRecord(RmtConfMIntfc rmtConfMIntfc) {
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("??????????????????...");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Map<String, Long>>> call = rmtInterface.approveByTab(rmtConfMIntfc, LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(context, false, call, new HttpCallBack<Map<String, Long>>() {
            @Override
            public void success(Map<String, Long> map) {
                dialog.dismiss();
                mPDAWorkOrderInfoConfig.setApprove(true);
                if (rmtSignRecord == null) {
                    ToastUtils.toast(context, "????????????");
                    if (qzpz != null) {
                        if (map != null && map.containsKey("apprRecordId")) {
                            qzpz.setApprRecordId(map.get("apprRecordId"));
                        }
                        upLoadQzPic(qzpz);
                    }
                } else {
                    if (map != null && map.containsKey("apprRecordId")) {
                        rmtSignRecord.setApprRecordId(map.get("apprRecordId"));
                        upApprRecordState = true;
                        uploadSignPic(rmtSignRecord);
                    } else {
                        upApprRecordState = false;
                        ToastUtils.toast(context, "???????????????apprRecordId???????????????????????????");
                    }
                }
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtils.toast(context, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtils.toast(context, "??????????????????");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SIGN_PIC_SUCCESS_CODE) {
            rmtSignRecord = (RmtSignRecord) data.getSerializableExtra(RMTSIGNRECORD);
            saveApprRecord(mPDAWorkOrderInfoConfig);
        } else if (resultCode == BaseFragment.Return_Picture) {

            List<String> imageList1 = SystemProperty.getSystemProperty().getImageList();
            Bundle extras = data.getExtras();
            qzpz = (RmtSignRecord) extras.getSerializable(SavePhotoFragment.RMTSIGNRECORD);
            image = (Image) extras.getSerializable(SavePhotoFragment.PICTURE);
            qzpz.setImage(image);
            qzpz.setImageList(imageList1);
            qzpz.setImageName(image.getImageName());
            saveApprRecord(rcfm);

        }
    }

    /**
     * ??????????????????
     *
     * @param uploadSignPic
     */
    protected void uploadSignPic(RmtSignRecord uploadSignPic) {
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("????????????????????????");
        if (dialog != null && !dialog.isShowing())
            dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.uploadSignPic(uploadSignPic);
        HttpBusiness.action(getActivity(), false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                ToastUtils.toast(getActivity(), "????????????????????????");
                upLoadQzPic(qzpz);
                upApprRecordState = false;
                rmtSignRecord = null;
                deleteFile(new File(folderPath));
            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                ToastUtils.toast(getActivity(), msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                ToastUtils.toast(getActivity(), "????????????????????????");
            }
        });

    }

    /**
     * ????????????????????? Created by liuyang on 2016???3???24???
     *
     * @param file
     */
    private void deleteFile(File file) {
        if (file.exists()) { // ????????????????????????
            if (file.isFile()) { // ?????????????????????
                file.delete(); // delete()?????? ??????????????? ??????????????????;
            } else if (file.isDirectory()) { // ??????????????????????????????
                File files[] = file.listFiles(); // ?????????????????????????????? files[];
                for (int i = 0; i < files.length; i++) { // ??????????????????????????????
                    this.deleteFile(files[i]); // ??????????????? ???????????????????????????
                }
            }
            file.delete();
        }
    }

    public void setRmtWorkSubtask(RmtWorkSubtask workTask) {
        this.rmtWorkSubtask = workTask;
    }
}
