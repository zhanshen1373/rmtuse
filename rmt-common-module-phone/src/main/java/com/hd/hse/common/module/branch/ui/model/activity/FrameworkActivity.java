/**
 * Project Name:hse-common-module-phone
 * File Name:FrameworkActivity.java
 * Package Name:com.hd.hse.common.module.branch.ui.model.activity
 * Date:2015年6月4日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 */

package com.hd.hse.common.module.branch.ui.model.activity;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.custom.HSEActionBar;
import com.hd.hse.common.component.phone.custom.HseActionBarBranchMenu;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.camera2.CameraActivity;
import com.hd.hse.common.module.phone.custom.StepCheckTabView;
import com.hd.hse.common.module.phone.photo.preview.AlbumActivity;
import com.hd.hse.common.module.phone.ui.activity.BaseActivity;
import com.hd.hse.constant.IConfigEncoding;
import com.hd.hse.entity.common.Image;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.entity.resultdata.RmtVirRiskM;
import com.hd.hse.entity.resultdata.RmtWorkSubtaskResltData;
import com.hd.hse.entity.workorder.RmtJjbData;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.entity.workorder.RmtWorkTaskBean;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.hd.hse.common.module.branch.ui.model.activity.CancleWorkSubTaskActivity.SUBTASKID;
import static com.hd.hse.common.module.branch.ui.model.activity.CreateQrCodeActivity.RMTJJBDATA;

/**
 * ClassName:FrameworkActivity ().<br/>
 * Date: 2015年6月4日 <br/>
 *
 * @author zhaofeng
 * @see
 */
public class FrameworkActivity extends BaseActivity {

    /**
     * logger:TODO(日志).
     */
    private static Logger logger = LogUtils.getLogger(FrameworkActivity.class);
    /**
     * INTENT_FUNCTION_KEY:TODO(模块功能-传值过程中的key值).
     */
    public static final String INTENT_FUNCTION_KEY = "intent_function_key";
    public static final String PERMITID_KEY = "permitId_key";
    public static final String SUBTASKID_KEY = "subtaskid_key";
    public static final String DESCRIPTION_KEY = "work_subtask_description_key";

    /**
     * INTENT_WORKTASK_KEY:TODO(作业任务-传值过程中的key值).
     */
    public static final String INTENT_WORKTASK_KEY = "intent_worktask_key";


    /**
     * INTENT_WORKTASK_KEY:TODO(暂停和恢复-传值过程中的key值).
     */
    public static final String INTENT_PAUSEORRESET_KEY="intent_pauseorreset_key";

    /**
     * INTENT_WORKTASK_KEY:TODO(主任务-传值过程中的key值).
     */
    public static final String INTENT_TASK_KEY = "intent_task_key";

    /**
     * INTENT_ITEMWORKTASK_KEY:TODO(作业任务--分项任务的key值).
     */
    public static final String INTENT_ITEMWORKTASK_KEY = "intent_itemworktask_key";

    /**
     * INTENT_NESID_KEY:TODO(消息ID-传值过程中的KEY值).
     */
    public static final String INTENT_NESID_KEY = "intent_nesid_key";
    /**
     * workTask:TODO(作业任务).//lxf 修改公共
     */
    public RmtWorkSubtask workTask;
    /**
     * 主任务
     */
    public RmtWorkTaskBean task;
    /**
     * itemWorkTask:TODO().
     */
    public SuperEntity itemWorkTask;

    /**
     * function:TODO(功能编码).//lxf修改成公共的
     */
    public String function;

    /**
     * newsid:TODO(消息ID).
     */
    private String newsid;
    /**
     * listConfig:TODO(界面信息).
     */
    private List<RmtConfMIntfc> listConfig;
    /**
     * currentPointer:TODO(当前Fragment对应的导航指针).
     */
    private RmtConfMIntfc currentPadConfig;
    /**
     * currentFragment:TODO(标记当前的Fragment).
     */
    private Fragment currentFragment;
    /**
     * fragmentCache:TODO(当前界面的Fragment缓存).
     */
    private Map<String, Fragment> fragmentCache = new HashMap<String, Fragment>();
    /**
     * tabViews:TODO(导航栏组件).
     */
    private StepCheckTabView tabViews;// PageSlidingTabView
    /**
     * hseAB:TODO(标题栏).
     */
    protected HSEActionBar hseAB;
    /**
     * actionTitle:TODO(标题栏标题).
     */
    protected String actionTitle = "作业任务";
    /**
     * hseBBM:TODO(标题栏下拉菜单项).
     */
    private HseActionBarBranchMenu hseBBM;
    /**
     * popupItems:TODO(下拉菜单项显示的内容描述).
     */
    protected String[] popupItems;
    /**
     * 二维码超时时间
     */
    private final long qrCodeTimeout = 30 * 60 * 1000;

    private boolean isDestroy = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hd_hse_common_module_fragmentwork_layout);
        tabViews = (StepCheckTabView) this
                .findViewById(R.id.hd_hse_common_module_fragmentwork_navigation_bar_id);

        // 初始化数据
        initlizeData();
    }

    @Override
    public void finish() {
        // TODO Auto-generated method stub

        customFinish();
        super.finish();
    }

    /**
     * customFinish:(). <br/>
     * date: 2015年8月13日 <br/>
     *
     * @author lxf
     */
    public void customFinish() {
        // 控制返回是否刷新 lxf 增加
        setResult(RESULT_OK);
    }

    /**
     * initlizeData:(初始化数据). <br/>
     * date: 2015年6月4日 <br/>
     *
     * @author zhaofeng
     */
    private void initlizeData() {
        Intent intent = getIntent();
        workTask = (RmtWorkSubtask) intent.getSerializableExtra(INTENT_WORKTASK_KEY);
        task = (RmtWorkTaskBean) intent.getSerializableExtra(INTENT_TASK_KEY);
        itemWorkTask = (SuperEntity) intent
                .getSerializableExtra(INTENT_ITEMWORKTASK_KEY);
        function = intent.getStringExtra(INTENT_FUNCTION_KEY);
        newsid = intent.getStringExtra(INTENT_NESID_KEY);
        // 异步查询数据
        queryData();
    }

    /**
     * initlizeCompant:(初始化界面). <br/>
     * date: 2015年6月4日 <br/>
     *
     * @author zhaofeng
     */
    public void initlizeCompant() {
        // 初始化前的数据处理
        listConfig = SpecialConfigList(listConfig);
        // 创建对应的Fragment缓存,和当前数据
        cacheFragmentByPdaInfo(listConfig);
        // 设置导航栏信息
        setNavModel();
        // 设置菜单栏信息
        setMenuModel();
        // 初始化界面时，设置初始化Fragment
        setFramentModel(currentFragment);
    }


    /**
     * SpecialConfigList:(初始化前数据处理). <br/>
     * date: 2015年6月15日 <br/>
     *
     * @param list
     * @author zhaofeng
     */
    public List<RmtConfMIntfc> SpecialConfigList(List<RmtConfMIntfc> list) {
        return listConfig;
    }

    /**
     * setMenuModel:(设置菜单栏信息). <br/>
     * date: 2015年6月4日 <br/>
     *
     * @author zhaofeng
     */
    protected void setMenuModel() {
        // 返回图标，标题，更多操作
        hseAB = getHseAB(popupItems, actionBarListener);
        if (popupItems == null) {
            hseAB.setTitleContent(actionTitle == null ? workTask.getDescription()
                    : "作业任务", true, true);
            // 设置数据
            hseAB.setDyTable(workTask);
        } else {
            hseAB.setTitleContent(actionTitle == null ? workTask.getDescription()
                    : "作业任务", true, true);
            // 设置数据
            if (workTask != null) {
                hseAB.setDyTable(workTask);
                if (listConfig.get(0) != null && listConfig.get(0).getRmtWorkGasDetectResltList() != null) {
                    hseAB.setRmtWorkGasDetectResltList(listConfig.get(0).getRmtWorkGasDetectResltList());
                }

            } else if (task != null) {
                hseAB.setDyTable(task);
            }
            // 下拉菜单
            hseBBM = new HseActionBarBranchMenu(getApplicationContext(),
                    popupItemClickListener, popupItems);
        }
    }

    protected HSEActionBar getHseAB(String[] popupItems, IEventListener actionBarListener) {
        HSEActionBar hseActionBar;
        if (popupItems == null) {
            hseActionBar = new HSEActionBar(this, actionBarListener, new String[]{
                    IActionBar.TV_BACK, IActionBar.TV_TITLE});
        } else {
            hseActionBar = new HSEActionBar(this, actionBarListener, new String[]{
                    IActionBar.TV_BACK, IActionBar.TV_TITLE,
                    IActionBar.IBTN_LEVELTWO_MORE});
        }
        return hseActionBar;
    }

    /**
     * setActionTitle:(设置标题栏-标题). <br/>
     * date: 2015年6月8日 <br/>
     *
     * @param actionTitle
     * @author zhaofeng
     */
    public void setActionTitle(String actionTitle) {
        this.actionTitle = actionTitle;
    }

    /**
     * setPopupItemsDesc:(根据功能模块设置显示的下拉菜单项内容). <br/>
     * date: 2015年6月8日 <br/>
     *
     * @param popupItems
     * @author zhaofeng
     */
    public void setPopupItemsDesc(String[] popupItems) {
        this.popupItems = popupItems;
    }

    /**
     * setNavModel:(设置导航栏信息). <br/>
     * date: 2015年6月4日 <br/>
     *
     * @author zhaofeng
     */
    private void setNavModel() {
        tabViews.setDataList(listConfig);
        tabViews.setOnIEventListener(eventLst);
        tabViews.setCurrentItem(currentPadConfig);
    }

    /**
     * eventLst:TODO(导航栏监听事件).
     */
    private IEventListener eventLst = new IEventListener() {

        @Override
        public void eventProcess(int eventType, Object... arg1)
                throws HDException {
            // 切换Fragment
            switchTab(null, (RmtConfMIntfc) arg1[0]);
        }
    };

    // /**
    // * setNaviFinish:(设置导航栏完成标记). <br/>
    // * date: 2015年6月4日 <br/>
    // *
    // * @author zhaofeng
    // * @param entity
    // */
    // public void setNaviFinish(SuperEntity entity) {
    // tabViews.setFinished(entity);
    // }

    /**
     * setFramentModel:(设置显示的Fragment). <br/>
     * date: 2015年6月4日 <br/>
     *
     * @param fragment
     * @author zhaofeng
     */
    private void setFramentModel(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        getFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.hd_hse_common_module_fragmentwork_fragmentlayout_id,
                        fragment).commit();
        currentFragment = fragment;
    }

    /**
     * queryData:(查询该任务对应的所有信息). <br/>
     * date: 2015年6月4日 <br/>
     *
     * @throws HDException
     * @author zhaofeng
     */
    public void queryData() {
        final ProgressDialog dialog = new ProgressDialog(FrameworkActivity.this);
        dialog.setMessage("加载数据中。。。");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // 获取分项任务
        Call<ResultData<List<RmtWorkSubtaskResltData>>> call = getCall(rmtInterface);
        HttpBusiness.action(FrameworkActivity.this, false, call, new HttpCallBack<List<RmtWorkSubtaskResltData>>() {
            @Override
            public void success(List<RmtWorkSubtaskResltData> resultDataBean) {
                dialog.dismiss();
                listConfig = getListConfig(resultDataBean);
                if (listConfig != null && listConfig.size() > 0) {
                    //审批权限数据
                    if (isDestroy) {
                        initlizeCompant();
                    }
                } else {
                    ToastUtil.toast(FrameworkActivity.this, "暂无数据");
                }
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(FrameworkActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(FrameworkActivity.this, "获取数据失败");
            }
        });

    }

    protected Call<ResultData<List<RmtWorkSubtaskResltData>>> getCall(RmtInterface rmtInterface) {
        return rmtInterface.getRmtWorkSubtask(LoginUserRecord.getInstance().getUser().getUserid(), workTask.getWork_subtask_id(), function);
    }

    /**
     * 解析数据
     */
    private List<RmtConfMIntfc> getListConfig(List<RmtWorkSubtaskResltData> rmtWorkSubtaskResltDatas) {
        List<RmtConfMIntfc> listConfig = new ArrayList<RmtConfMIntfc>();
        if (rmtWorkSubtaskResltDatas != null && rmtWorkSubtaskResltDatas.size() > 0)
            for (RmtWorkSubtaskResltData rmtWorkSubtaskResltData : rmtWorkSubtaskResltDatas) {
                if (rmtWorkSubtaskResltData.getHeadvo() != null)
                    //解析headvo数据
                    if (rmtWorkSubtaskResltData.getHeadvo().getRMT_CONF_M_INTFC_M() != null) {
                        //得到RmtConfMIntfc
                        listConfig.add(rmtWorkSubtaskResltData.getHeadvo().getRMT_CONF_M_INTFC_M());
                        if (rmtWorkSubtaskResltData.getBodyvos() != null) {
                            //解析bodayov数据
                            if (rmtWorkSubtaskResltData.getBodyvos().getRMT_VIR_RISK_M() != null) {
                                List<RmtVirRiskM> rmtVirRiskMList = rmtWorkSubtaskResltData.getBodyvos().getRMT_VIR_RISK_M();
                                if (rmtVirRiskMList != null && rmtVirRiskMList.size() > 0) {
                                    rmtWorkSubtaskResltData.getHeadvo().getRMT_CONF_M_INTFC_M().setVirRiskMVOList(rmtVirRiskMList);
                                }
                            }
                            if (rmtWorkSubtaskResltData.getBodyvos().getRMT_CONF_APPR_AUTH_M() != null &&
                                    rmtWorkSubtaskResltData.getBodyvos().getRMT_CONF_APPR_AUTH_M().size() > 0) {
                                //解析审批权限
                                if (rmtWorkSubtaskResltData.getBodyvos().getRMT_CONF_APPR_AUTH_M() != null) {
                                    rmtWorkSubtaskResltData.getHeadvo().getRMT_CONF_M_INTFC_M().setApprAuthVOList
                                            (rmtWorkSubtaskResltData.getBodyvos().getRMT_CONF_APPR_AUTH_M());
                                }
                            }
                            if (rmtWorkSubtaskResltData.getBodyvos().getRMT_WORK_PERMIT_M() != null &&
                                    rmtWorkSubtaskResltData.getBodyvos().getRMT_WORK_PERMIT_M().size() > 0) {
                                //解析作业票
                                rmtWorkSubtaskResltData.getHeadvo().getRMT_CONF_M_INTFC_M().setPermitMVOList
                                        (rmtWorkSubtaskResltData.getBodyvos().getRMT_WORK_PERMIT_M());
                            }
                            if (rmtWorkSubtaskResltData.getBodyvos().getRMT_CONF_ELEC_EQPT_M() != null &&
                                    rmtWorkSubtaskResltData.getBodyvos().getRMT_CONF_ELEC_EQPT_M().size() > 0) {
                                //解析作业票
                                rmtWorkSubtaskResltData.getHeadvo().getRMT_CONF_M_INTFC_M().setConfElecEqptVOList
                                        (rmtWorkSubtaskResltData.getBodyvos().getRMT_CONF_ELEC_EQPT_M());
                            }

                            if (rmtWorkSubtaskResltData.getBodyvos().getRMT_WORK_GAS_DETECT_M() != null &&
                                    rmtWorkSubtaskResltData.getBodyvos().getRMT_WORK_GAS_DETECT_M().size() > 0) {
                                //解析气体检测数据
                                rmtWorkSubtaskResltData.getHeadvo().getRMT_CONF_M_INTFC_M().setRmtWorkGasDetectResltList
                                        (rmtWorkSubtaskResltData.getBodyvos().getRMT_WORK_GAS_DETECT_M());
                            }


                        }

                        if (rmtWorkSubtaskResltData.getDictvos() != null &&
                                rmtWorkSubtaskResltData.getDictvos().getRMT_STOP_REASON_M() != null &&
                                rmtWorkSubtaskResltData.getDictvos().getRMT_STOP_REASON_M().size() > 0) {
                            //解析dictvos数据
                            rmtWorkSubtaskResltData.getHeadvo().getRMT_CONF_M_INTFC_M().setDictList(
                                    rmtWorkSubtaskResltData.getDictvos().getRMT_STOP_REASON_M()
                            );
                        }

                    }

            }


        return listConfig;
    }

    /**
     * switchTab:(切换tab). <br/>
     * date: 2015年6月4日 <br/>
     *
     * @param classz
     * @param config
     * @throws HDException
     * @author zhaofeng
     */
    private void switchTab(Class<?> classz, RmtConfMIntfc config)
            throws HDException {
        Fragment fragment = getFragmentByConfig(classz, config);
        setFramentModel(fragment);
    }

    /**
     * cacheFragmentByPdaInfo:(根据padList集合的数据创建缓存存储对应的Fragment). <br/>
     * date: 2015年6月4日 <br/>
     *
     * @param list
     * @author zhaofeng
     */
    private void cacheFragmentByPdaInfo(List<RmtConfMIntfc> list) {
        Fragment fragment = null;
        for (RmtConfMIntfc padConfig : list) {
            if (currentPadConfig == null)
                currentPadConfig = padConfig;
            fragment = configFragmentByPadType(list, padConfig);
            if (fragment != null) {
                if (currentFragment == null) {
                    currentFragment = fragment;
                }
                fragmentCache.put(padConfig.getM_intfc_id() + "", fragment);
            }
        }
    }


    /**
     * configFragmentByPadType:(外部根据padconfig配置对应的fragment). <br/>
     * date: 2015年6月8日 <br/>
     *
     * @param config
     * @return
     * @author zhaofeng
     */
    public Fragment configFragmentByPadType(List<RmtConfMIntfc> listConfig, RmtConfMIntfc config) {
        return null;
    }

    /**
     * getFragmentByKey:(根据配置信息获取对应的Fragment). <br/>
     * date: 2015年6月4日 <br/>
     *
     * @param classz
     * @param config
     * @return
     * @throws HDException
     * @author zhaofeng
     */
    private Fragment getFragmentByConfig(Class<?> classz,
                                         RmtConfMIntfc config) throws HDException {
        String key = config.getM_intfc_id() + "";
        if (fragmentCache.containsKey(key)) {
            return fragmentCache.get(key);
        } else {
            try {
                Fragment fragment = (Fragment) classz.newInstance();
                fragmentCache.put(key, fragment);
                return fragment;
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                logger.error(e.getMessage(), e);
                throw new HDException("加载模块失败，请联系管理员！", e);

            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                logger.error(e.getMessage(), e);
                throw new HDException("加载模块失败，请联系管理员！", e);
            }

        }
    }

    /**
     * handleShowError:(处理显示错误信息). <br/>
     * date: 2015年6月4日 <br/>
     *
     * @param e
     * @param showMessage
     * @author zhaofeng
     */
    private void handleShowError(HDException e, String showMessage) {
        logger.error(e.getMessage(), e);
        ToastUtils.imgToast(this, R.drawable.hd_common_message_error,
                showMessage);
    }

    /**
     * actionBarListener:TODO(菜单栏监听事件).
     */
    protected IEventListener actionBarListener = new IEventListener() {

        @Override
        public void eventProcess(int arg0, Object... arg1) throws HDException {
            // TODO Auto-generated method stub
            if (arg0 == IEventType.ACTIONBAR_LEVELTWO_MORE_CLICK) {
                if (hseBBM != null)
                    hseBBM.showAsDropDown((View) arg1[0]);
            } else if (arg0 == IEventType.ACTIONBAR_CLICK_POPUP) {

            } else if (arg0 == IEventType.ACTION_SUBTASK_CANCLE) {
                /**
                 * 跳转到任务取消界面
                 */

                Intent intent = new Intent(FrameworkActivity.this, CancleWorkSubTaskActivity.class);
                intent.putExtra(SUBTASKID, workTask.getWork_subtask_id());
                startActivityForResult(intent, 1);

            }
        }
    };

    /**
     * popupItemClickListener:TODO(下拉菜单项的点击监听事件).
     */
    private IEventListener popupItemClickListener = new IEventListener() {

        @Override
        public void eventProcess(int arg0, Object... arg1) throws HDException {
            // TODO Auto-generated method stub
            if (arg0 == IEventType.ACTIONBAR_PHOTOGRAPH_CLICK) {
                touchImages();
            } else if (arg0 == IEventType.MENUBAR_LEVELTWO_PHOTOMANAGER_CLICK) {
                managerImages();
            } else if (arg0 == IEventType.ACTIONBAR_RETURN_CLICK) {
                // 作业票结束
                // 跳转到作业票结束界面
                Intent intent = new Intent();
                intent.setAction("ACTION_WORKTASK_END");
                intent.putExtra(INTENT_WORKTASK_KEY, workTask);
                intent.putExtra(INTENT_FUNCTION_KEY, IConfigEncoding.JS);
                startActivity(intent);
            } else if (arg0 == IEventType.ACTIONBAR_INVAILD_CLICK) {
                Intent intent = new Intent(FrameworkActivity.this, CancleWorkSubTaskActivity.class);
                intent.putExtra(SUBTASKID, workTask.getWork_subtask_id());
                startActivityForResult(intent, 1);
            } else if (arg0 == IEventType.ACTIONBAR_SEETICKET_CLICK) {
                // 作业浏览
                Intent intent = new Intent();
                intent.setAction("ACTION_WORKTASK_ONLINELL");
                intent.putExtra(INTENT_WORKTASK_KEY, workTask);
                intent.putExtra(INTENT_FUNCTION_KEY, IConfigEncoding.LL);
                startActivity(intent);
            } else if (arg0 == IEventType.ACTIONBAR_JJBQRCODE_CLICK) {
                //生成交接班二维码
                RmtJjbData rmtJjbData = new RmtJjbData();
                rmtJjbData.setCreateTime(System.currentTimeMillis());
                rmtJjbData.setTimeOut(qrCodeTimeout);
                rmtJjbData.setDescription(workTask.getDescription());
                rmtJjbData.setFunCode(function);
                rmtJjbData.setWork_subtask_id(workTask.getWork_subtask_id());
                createQrCode(rmtJjbData);
            }
        }
    };

    private void createQrCode(RmtJjbData rmtJjbData) {
        Intent intent = new Intent(FrameworkActivity.this, CreateQrCodeActivity.class);
        intent.putExtra(RMTJJBDATA, rmtJjbData);
        startActivity(intent);
    }

    /**
     * touchImages:(拍照). <br/>
     * date: 2015年6月8日 <br/>
     *
     * @author zhaofeng
     */
    private void touchImages() {

        Image image = new Image();
        if (workTask != null) {
            image.setSubtaskId(workTask.getWork_subtask_id());// 表主键
        }
        if (task != null) {
            image.setTaskId(task.getWork_task_id());
        }
        image.setCreateUser(LoginUserRecord.getInstance().getUser().getUserid());// 创建人
        image.setCreateUsername(LoginUserRecord.getInstance().getUser().getUserName());
        image.setFunCode(function);// 对应功能
        image.setTabType(currentPadConfig.getTab_type());
        image.setImageName(currentPadConfig.getTab_name());
        Intent intent = new Intent(getBaseContext(), CameraActivity.class);
        intent.putExtra(CameraActivity.FLAG, image);
        startActivity(intent);
    }

    /**
     * managerImages:(照片管理). <br/>
     * date: 2015年6月8日 <br/>
     *
     * @author zhaofeng
     */

    private void managerImages() {
        Intent intent = new Intent(getApplicationContext(), AlbumActivity.class);
        if (workTask != null) {
            intent.putExtra(AlbumActivity.IMAGE_FLAG, workTask);
        } else if (task != null) {
            intent.putExtra(AlbumActivity.IMAGE_TASK_FLAG, task);
        } else {
            ToastUtils.toast(getApplication(), "未获取到主任务以及分项任务");
            return;
        }

        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroy = false;
    }

    public void setWorkTask(RmtWorkSubtask workTask) {
        this.workTask = workTask;
    }
}
