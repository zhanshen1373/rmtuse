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
     * moduleCode:TODO(模块编码).
     */
    private String moduleCode = "";
    /**
     * 手签记录
     */
    protected RmtSignRecord rmtSignRecord;
    /**
     * 保存手签图片文件的路径
     */
    private String folderPath;
    /**
     * 上传审批记录状态
     */
    protected boolean upApprRecordState = false;

    /**
     *
     */
    private RmtWorkSubtask rmtWorkSubtask;


    /**
     * 部门code集合,登录人部门id在这个集合里面不允许手签
     */
    private String[] orgCodes = {"NX010303", "NX01030301", "NX01030302", "NX01030303", "NX01030304", "NX01030305"};
    private RmtConfMIntfc rcfm;
    private boolean orgidIsInSet;
    private RmtSignRecord qzpz;
    private Image image;

//    "NX010305", "NX010306", "NX010307", "NX010308", "NX010309", "NX010310"

    /**
     * 底部样式常量定义在本类中。
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
                builder.setTitle("提示");
                builder.setMessage("确定继续?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        // 情况1.登陆人和作业单位 如果不一样  非承包商
                        // 情况2 登陆人和作业单位  如果一样 作业单位除了安检公司和电仪部都是承包商
                        if (rmtWorkSubtask != null) {
                            if (LoginUserRecord.getInstance().getUser().getOrgid() != rmtWorkSubtask.getWork_unit_id()) {
                                //非承包商
                                sqCheck(v);
                            } else {
                                List<Long> orgidList = LoginUserRecord.getInstance().getOrgidList();
                                if (orgidList.contains(rmtWorkSubtask.getWork_unit_id())) {
                                    //安检公司和电仪部及子部门都是非承包商
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
                builder.setNegativeButton("取消",
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
                builder.setTitle("提示");
                builder.setMessage("确定继续?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
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
                builder.setNegativeButton("取消",
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
         * 获得中间 UI 的方法写在最后，目的是防止，使用者在 onCreatePageView 方法中
         * 调用了设置底部按钮的方法，不会导致底部UI还没有加载。
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
                    ToastUtils.toast(context, "您未上传手写签名");
                    //调起录入签名
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
     * 子类需要实现的方法，返回页面中变化的UI部分，本框架中是上半部分，最下面是一个按钮。
     *
     * @return
     */
    protected abstract View onCreatePageView(LayoutInflater inflater);

    /**
     * 后续添加的字段，目的是实现在每次点击审核的时候要先弹出对话框提示用户是否
     * 真的要保存，所以解决方案是在现有基础上内部先实现一个接口，将弹提示框的部分
     * 写在内部。
     */
    private OnClickListener mBottomButtonClickListener;

    /**
     * 底部按钮的点击事件。
     * setOnBottomButtonClickListener:(). <br/>
     * date: 2015年6月4日 <br/>
     *
     * @param l
     * @author xuxinwen
     */
    public void setOnBottomButtonClickListener(OnClickListener l) {
        mBottomButtonClickListener = l;

    }

    /**
     * 给底部按钮设置文本。
     * setBottomButtonText:(). <br/>
     * date: 2015年6月4日 <br/>
     *
     * @param text
     * @author xuxinwen
     */
    public void setBottomButtonText(String text) {
        vSingleButton.setText(text);
    }

    /**
     * 底部复杂样式中按钮的点击事件。添加原因是增加中间的提示dialog
     */
    private IEventListener mBottomActionBarEventListener;

    /**
     * 底部导航监听事件。事件类型定义需要参考，BottomActionBar内部抛出事件
     * 处的定义。
     * setOnBottomActionBarEventListener:(). <br/>
     * date: 2015年6月5日 <br/>
     *
     * @param listener
     * @author xuxinwen
     */
    public void setOnBottomActionBarEventListener(IEventListener listener) {
        mBottomActionBarEventListener = listener;

    }

    /**
     * 为该界面设置 PDAWorkOrderInfoConfig 实体，这个方法必须在 Fragment 被添加到
     * Activity 之前调用。之后调用不起作用。
     * setPDAWorkOrderInfoConfig:(). <br/>
     * date: 2015年6月8日 <br/>
     *
     * @param config
     * @author xuxinwen
     */
    public void setPDAWorkOrderInfoConfig(RmtConfMIntfc config) {
        mPDAWorkOrderInfoConfig = config;
        //判断是不是已经审批过了
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
     * getModuleCode:(模块编码). <br/>
     * date: 2015年6月18日 <br/>
     *
     * @return
     * @author lxf
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * setModuleCode:(模块编码). <br/>
     * date: 2015年6月18日 <br/>
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
                //有强制拍照
                touchImages();
            } else {

                if (rmtConfMIntfc.getApprAuthVOList() != null
                        && rmtConfMIntfc.getApprAuthVOList().size() > 0
                        && rmtConfMIntfc.getApprAuthVOList().get(0) != null
                        && rmtConfMIntfc.getApprAuthVOList().get(0).getHandwrite() == 1
                        && orgidIsInSet) {
                    //弹出手签界面
                    Intent intent = new Intent(getActivity(), PaintSignatureActivity.class);
                    intent.putExtra(PAINTSIGNATURE, folderPath);
                    startActivityForResult(intent, 0);
                } else {
                    saveApprRecord(rmtConfMIntfc);
                }
            }


        } else if (upApprRecordState && rmtSignRecord != null) {
            if (forcePhoto) {
                //有强制拍照
                touchImages();
            } else {
                uploadSignPic(rmtSignRecord);
            }

        } else {
            if (forcePhoto) {
                //有强制拍照
                touchImages();
            } else {
                saveApprRecord(rmtConfMIntfc);
            }
        }
    }


    /**
     * 上传强制拍照图片
     *
     * @param rmtSignRecord
     */
    private void upLoadQzPic(RmtSignRecord rmtSignRecord) {
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("正在上传照片...");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.uploadQZPic(rmtSignRecord);
        HttpBusiness.action(getActivity(), false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "上传图片成功!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "上传图片失败!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static final int Take_Picture = 0x001;
    public static final int Return_Picture = 0x002;

    private void touchImages() {
        RmtSignRecord rmtSignRecord = new RmtSignRecord();
        Image image = new Image();

        image.setCreateUser(LoginUserRecord.getInstance().getUser().getUserid());// 创建人
        image.setCreateUsername(LoginUserRecord.getInstance().getUser().getUserName());

        image.setTabType(mPDAWorkOrderInfoConfig.getTab_type());
        image.setImageName(mPDAWorkOrderInfoConfig.getTab_name());
        Intent intent = new Intent(getActivity(), CameraActivity.class);
        intent.putExtra(CameraActivity.FLAG, image);
        intent.putExtra(CameraActivity.SIGNRECORD, rmtSignRecord);
        startActivityForResult(intent, BaseFragment.Take_Picture);
    }


    /**
     * 保存审批记录
     *
     * @param rmtConfMIntfc
     */
    protected void saveApprRecord(RmtConfMIntfc rmtConfMIntfc) {
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("正在保存数据...");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Map<String, Long>>> call = rmtInterface.approveByTab(rmtConfMIntfc, LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(context, false, call, new HttpCallBack<Map<String, Long>>() {
            @Override
            public void success(Map<String, Long> map) {
                dialog.dismiss();
                mPDAWorkOrderInfoConfig.setApprove(true);
                if (rmtSignRecord == null) {
                    ToastUtils.toast(context, "保存成功");
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
                        ToastUtils.toast(context, "没有获取到apprRecordId，上传手签记录失败");
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
                ToastUtils.toast(context, "保存数据失败");
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
     * 上传手签记录
     *
     * @param uploadSignPic
     */
    protected void uploadSignPic(RmtSignRecord uploadSignPic) {
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("正在上传手签记录");
        if (dialog != null && !dialog.isShowing())
            dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.uploadSignPic(uploadSignPic);
        HttpBusiness.action(getActivity(), false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                ToastUtils.toast(getActivity(), "上传手签记录成功");
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
                ToastUtils.toast(getActivity(), "上传手签记录失败");
            }
        });

    }

    /**
     * 删除文件的方法 Created by liuyang on 2016年3月24日
     *
     * @param file
     */
    private void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        }
    }

    public void setRmtWorkSubtask(RmtWorkSubtask workTask) {
        this.rmtWorkSubtask = workTask;
    }
}
