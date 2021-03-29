package com.hd.hse.common.module.phone.ui.module.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.custom.MessageDialog;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.PhoneEventType;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.ui.activity.LocationSwCard;
import com.hd.hse.common.module.phone.ui.activity.SystemApplication;
import com.hd.hse.entity.other.DailogDataBean;
import com.hd.hse.entity.resultdata.RmtWorkGasDetectReslt;
import com.hd.hse.entity.sys.AppModule;
import com.hd.hse.entity.workorder.ResultDataBean;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.entity.workorder.WorkOrder;
import com.hd.hse.system.SystemProperty;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * ClassName: BaseTaskListBusActivity (在线审核列表的基类)<br/>
 * date: 2015年6月10日 <br/>
 *
 * @author lxf
 */
public class BaseTaskListBusActivity extends BaseTaskListActivity {

    private static Logger logger = LogUtils
            .getLogger(BaseTaskListBusActivity.class);

    /**
     * 标记是否已经调用了开启另一个Activity 的方法。
     */
    public boolean hasCalledStartActivity;
    // 作业票查询服务

    public String moduleCode = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        moduleCode = getIntent().getStringExtra(
                FrameworkActivity.INTENT_FUNCTION_KEY);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {

        final ProgressDialog dialog = new ProgressDialog(BaseTaskListBusActivity.this);
        dialog.setMessage("正在查询");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // 获取分项任务列表
        Call<ResultData<ResultDataBean<RmtWorkSubtask>>> call = rmtInterface.getRmtWorkSubtaskList(LoginUserRecord.getInstance().getUser().getUserid(), getFunctionCode(), getSearchStr(),1,10);
        HttpBusiness.action(BaseTaskListBusActivity.this, false, call, new HttpCallBack<ResultDataBean<RmtWorkSubtask>>() {
            @Override
            public void success(ResultDataBean<RmtWorkSubtask> resultDataBean) {
                dialog.dismiss();
                List<ResultDataBean.MainvoBean<RmtWorkSubtask>> mainvoList = resultDataBean.getMainvo();
                List<SuperEntity> rmtWorkSubtasks = new ArrayList<SuperEntity>();
                //rmtWorkGasDetectResltList = new ArrayList<RmtWorkGasDetectReslt>();
                for (ResultDataBean.MainvoBean<RmtWorkSubtask> mainvoBean : mainvoList) {
                    ResultDataBean.MainvoBean.HeadVOBean<RmtWorkSubtask> headVOBean = mainvoBean.getHeadVO();
                    if (headVOBean != null) {
                        //解析气体检测数据
                        RmtWorkSubtask rmtWorkSubtask = headVOBean.getRMT_WORK_SUBTASK_M();
                        if (rmtWorkSubtask != null) {
                            List<RmtWorkGasDetectReslt> rmtWorkGasDetectResltList = new ArrayList<RmtWorkGasDetectReslt>();
                            ResultDataBean.MainvoBean.BodyBean bodyBean = mainvoBean.getBodyVOs();
                            if (bodyBean != null && bodyBean.getRMT_WORK_GAS_DETECT_M() != null
                                    && bodyBean.getRMT_WORK_GAS_DETECT_M().size() > 0) {
                                List<ResultDataBean.MainvoBean.BodyBean.RMTWORKGASDETECTMBeanX> rmt_work_gas_detect_mList =
                                        bodyBean.getRMT_WORK_GAS_DETECT_M();

                                for (
                                        ResultDataBean.MainvoBean.BodyBean.RMTWORKGASDETECTMBeanX beanX :
                                        rmt_work_gas_detect_mList) {
                                    if (beanX.getHeadVO() != null && beanX.getHeadVO().getRMT_WORK_GAS_DETECT_M() != null) {
                                        RmtWorkGasDetectReslt rmtWorkGasDetectReslt = beanX.getHeadVO().getRMT_WORK_GAS_DETECT_M();
                                        rmtWorkGasDetectResltList.add(rmtWorkGasDetectReslt);
                                        if (beanX.getBodyVOs() != null && beanX.getBodyVOs().getRMT_WORK_GAS_DETECT_LINE_M() != null
                                                && beanX.getBodyVOs().getRMT_WORK_GAS_DETECT_LINE_M().size() > 0) {
                                            List<ResultDataBean.MainvoBean.BodyBean.RMTWORKGASDETECTMBeanX.BodyVOsBean.RMTWORKGASDETECTLINEMBeanX>
                                                    beanXes = beanX.getBodyVOs().getRMT_WORK_GAS_DETECT_LINE_M();
                                            List<RmtWorkGasDetectReslt.VoListBean> voListBeanList = new ArrayList<RmtWorkGasDetectReslt.VoListBean>();
                                            for (ResultDataBean.MainvoBean.BodyBean.RMTWORKGASDETECTMBeanX.BodyVOsBean.RMTWORKGASDETECTLINEMBeanX beanX1 : beanXes) {
                                                if (beanX1.getHeadVO() != null && beanX1.getHeadVO().getRMT_WORK_GAS_DETECT_LINE_M() != null
                                                        ) {
                                                    voListBeanList.add(beanX1.getHeadVO().getRMT_WORK_GAS_DETECT_LINE_M());
                                                }
                                            }
                                            rmtWorkGasDetectReslt.setVoList(voListBeanList);

                                        }
                                    }
                                }

                            }
                            rmtWorkSubtask.setRmtWorkGasDetectResltList(rmtWorkGasDetectResltList);
                            rmtWorkSubtasks.add(rmtWorkSubtask);
                        }
                    }
                }
                setDataList(rmtWorkSubtasks,getFunctionCode());
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(BaseTaskListBusActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(BaseTaskListBusActivity.this, "获取数据列表失败");
            }
        });


    }

    @Override
    protected void initView() {
        super.initView();
        // 设置导航栏信息
        setCustomActionBar(true, eventListener, new String[]{
                IActionBar.IBTN_SEARCH, IActionBar.TV_TITLE});
        // 设置导航栏标题
        setActionBartitleContent(getTitileName(), false);
        // 设置左侧模块选择抽屉
        setNavContent(getNavContentData(), getNavCurrentKey());
        //初始化事件
        setListener();
    }

    protected void setListener(){

    }

    @Override
    protected void onResume() {
        super.onResume();
        hasCalledStartActivity = false;
    }

    /**
     * 现场核查界面返回时，刷新作业票列表
     *
     * @see android.app.Activity#onActivityResult(int, int,
     * android.content.Intent)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 是否需要刷新
        if (resultCode == RESULT_OK) {
            initData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * getTitileName:(获取标题名字). <br/>
     * date: 2015年2月6日 <br/>
     *
     * @return
     * @author lxf
     */

    public String getTitileName() {
        return "";
    }

    /**
     * getNavContentData:(获取导航数据). <br/>
     * date: 2015年2月6日 <br/>
     *
     * @return
     * @author lxf
     */
    public List<AppModule> getNavContentData() {
        return SystemProperty.getSystemProperty()
                .getMainAppModulelist("ONLINE");
    }

    /**
     * getNavCurrentKey:(获取导航栏的KEY). <br/>
     * date: 2015年2月6日 <br/>
     *
     * @return
     * @author lxf
     */
    public String getNavCurrentKey() {
        return null;
    }

    /**
     * getToActivityClass:(启动到下一个Activity类). <br/>
     * date: 2015年2月6日 <br/>
     *
     * @return
     * @author lxf
     */
    public Class<?> getToActivityClass() {
        return null;
    }

    /**
     * getFunctionCode:(). <br/>
     * date: 2015年2月6日 <br/>
     *
     * @return
     * @author lxf
     */
    public String getFunctionCode() {
        return moduleCode;
    }

    /**
     * eventListener:TODO().
     */
    protected IEventListener eventListener = new IEventListener() {

        @Override
        public void eventProcess(int eventType, Object... objects)
                throws HDException {
            // TODO Auto-generated method stub
            if (eventType == IEventType.WORK_LIST_CLICK) {
                /*
                 * if (objects[0] instanceof SuperEntity) {
				 * startNextActivity((SuperEntity) objects[0]); }
				 */
            } else if (eventType == IEventType.ACTIONBAR_SEARCH_CLICK) {
                System.out.println("ACTIONBAR_SEARCH_CLICK");
                if (objects[0] instanceof String) {
                    setSearchStr((String) objects[0]);
                    initData();
                }
            } else if (IEventType.ACTIONBAR_CALNEL_CLICK == eventType) {
                setSearchStr("");
                initData();
            } else if (PhoneEventType.ZYPLIST_ITEM_LONG_CLICK == eventType) {
                /*
                 * // 查询作业票信息 WorkOrder mWorkOrder =
				 * quertWorkInfo.querySiteAuditBasicInfo( (SuperEntity)
				 * objects[0], getFunctionCode(), null); // 长按作业票列表图标
				 * showWorkOrderPopupWin(mWorkOrder);
				 */
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            setHomeAction();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * startCSActivityForResult:(启动界面). <br/>
     * date: 2015年3月10日 <br/>
     *
     * @param workOrder
     * @author lxf
     */
    public void startCSActivityForResult(WorkOrder workOrder) {
        startNextActivity(workOrder);
    }

    /**
     * TODO 跳转到作业票审批页签 startNextActivity:(). date: 2015年02月06日
     *
     * @throws HDException
     * @author lxf
     */
    protected void startNextActivity(SuperEntity superEntity) {
        if (hasCalledStartActivity) {
            return;
        }
        hasCalledStartActivity = true;
        if (getToActivityClass() == null) {
            return;
        }
        if (StringUtils.isEmpty(getFunctionCode())) {
            return;
        }
        Intent intent = new Intent(this, getToActivityClass());
        Bundle bundle = new Bundle();
        try {

            Object object = getNextPageNeedDate(superEntity);
            if (object instanceof List) {
                //
            } else if (object instanceof SuperEntity) {
                bundle.putSerializable(FrameworkActivity.INTENT_WORKTASK_KEY,
                        (Serializable) object);
            }
            intent.putExtras(bundle);
            intent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,
                    getFunctionCode());
            startActivityForResult(intent, IEventType.WORKORDER_AUDIT);
        } catch (HDException e) {
            hasCalledStartActivity = false;
            logger.error(e.getMessage());
            ToastUtils.imgToast(this, R.drawable.hd_hse_common_msg_wrong,
                    e.getMessage());
        }
    }

    /**
     * setHomeAction:(设置按返回键事件). <br/>
     * date: 2015年2月6日 <br/>
     *
     * @author lxf
     */
    private void setHomeAction() {
        // 暂时注释掉
        // if (this.getDataList() == null || this.getDataList().size() == 0) {
        // SystemApplication.getInstance().popActivity();
        // return;
        // }
        // 提示是否退出程序
        MessageDialog.Builder builder = new MessageDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定要返回主界面吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SystemApplication.getInstance().popActivity();
                dialog.dismiss();
                // 设置你的操作事项
                if (LocationSwCard.mTimer != null) {
                    LocationSwCard.mTimer.cancel();
                    LocationSwCard.mTimer = null;
                }
                // 清空刷卡位置
                SystemProperty.getSystemProperty().setPositionCard(null);
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

    /**
     * TODO 作业票点击列表事件
     */
    @Override
    protected void onWorkListItemClick(Object workTask) {
        // TODO Auto-generated method stub
        RmtWorkSubtask rmtWorkSubtask= (RmtWorkSubtask) workTask;
        startNextActivity(rmtWorkSubtask);
    }

    @Override
    protected void onWorkListItemLongClick(Object workTask) {
        // TODO Auto-generated method stub
        // 获取要显示的列的配置
        List<SuperEntity> data = getShowPopupWinDate(workTask);
        // 长按作业票列表图标
        if (data != null) {
            showWorkOrderPopupWin(data);
        }

    }

    private List<String[]> attrcode;
    private List<String[]> arrtnames;

    private List<SuperEntity> getShowPopupWinDate(Object t) {
        RmtWorkSubtask workTask= (RmtWorkSubtask) t;
        List<SuperEntity> retSuper = new ArrayList<SuperEntity>();
        SuperEntity s = new DailogDataBean("任务信息", "", true);
        SuperEntity s1 = new DailogDataBean("作业名称:", workTask.getDescription());
        SuperEntity s2 = new DailogDataBean("属地单位:", workTask.getTerritorial_unit_name());
        SuperEntity s3 = new DailogDataBean("作业单位:", workTask.getWork_unit_name());
        SuperEntity s4 = new DailogDataBean("作业内容:", workTask.getDescription());
        SuperEntity s5 = new DailogDataBean("开始时间:", workTask.getEst_start_time());
        SuperEntity s6 = new DailogDataBean("结束时间:", workTask.getEst_end_time());
        retSuper.add(s);
        retSuper.add(s1);
        retSuper.add(s2);
        retSuper.add(s3);
        retSuper.add(s4);
        retSuper.add(s5);
        retSuper.add(s6);
        //得到气体检测数据
        List<SuperEntity> superEntities = getGasCheckData(workTask);
        if (superEntities != null && superEntities.size() > 0)
            retSuper.addAll(superEntities);


        return retSuper;
    }

    private List<SuperEntity> getGasCheckData(RmtWorkSubtask workTask) {
        List<RmtWorkGasDetectReslt> rmtWorkGasDetectResltList = workTask.getRmtWorkGasDetectResltList();
        if (rmtWorkGasDetectResltList != null && rmtWorkGasDetectResltList.size() > 0) {
            List<SuperEntity> retSuper = new ArrayList<SuperEntity>();
            SuperEntity s0 = new DailogDataBean("气体检测信息", "", true);
            retSuper.add(s0);
            for (RmtWorkGasDetectReslt rmtWorkGasDetectReslt : rmtWorkGasDetectResltList) {
                SuperEntity s = new DailogDataBean("检测时间:", rmtWorkGasDetectReslt.getDetect_time());
                SuperEntity s2 = new DailogDataBean("检测地点:", rmtWorkGasDetectReslt.getDetect_site());
                SuperEntity s3 = new DailogDataBean("是否合格:", rmtWorkGasDetectReslt.getQualified() + "");
                retSuper.add(s);
                retSuper.add(s2);
                retSuper.add(s3);
                for (RmtWorkGasDetectReslt.VoListBean voListBean : rmtWorkGasDetectReslt.getVoList()) {
                    SuperEntity s4 = new DailogDataBean("类型:", voListBean.getGas_type_sub_name());
                    SuperEntity s5 = new DailogDataBean("浓度:", voListBean.getGas_value());
                    retSuper.add(s4);
                    retSuper.add(s5);
                }
            }
            return retSuper;
        } else {
            return null;
        }
    }

    /**
     * getNextPageNeedDate:(获取需要传入下一页需要的参数数据). <br/>
     * date: 2015年6月10日 <br/>
     *
     * @param superEntity
     * @return
     * @author lxf
     */
    public Object getNextPageNeedDate(SuperEntity superEntity)
            throws HDException {
        return superEntity;
    }


}
