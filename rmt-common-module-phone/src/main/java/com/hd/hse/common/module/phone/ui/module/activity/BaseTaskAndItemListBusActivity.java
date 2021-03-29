package com.hd.hse.common.module.phone.ui.module.activity;

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
import com.hd.hse.common.component.phone.custom.ProgressDialog;
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
import com.hd.hse.entity.sys.AppModule;
import com.hd.hse.entity.workorder.RmtTaskListQuery;
import com.hd.hse.entity.workorder.RmtWorkSubTaskBean;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.entity.workorder.RmtWorkTaskBean;
import com.hd.hse.entity.workorder.WorkTask;
import com.hd.hse.service.inter.ICallBack;
import com.hd.hse.system.SystemProperty;
import com.hd.hse.utils.UtilTools;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * ClassName: BaseTaskAndItemListBusActivity (在线审核列表的基类)<br/>
 * date: 2015年8月7日 <br/>
 *
 * @author lxf
 */
public class BaseTaskAndItemListBusActivity extends BaseTaskAndItemListActivity {

    private static Logger logger = LogUtils
            .getLogger(BaseTaskAndItemListBusActivity.class);

    /**
     * 标记是否已经调用了开启另一个Activity 的方法。
     */
    public boolean hasCalledStartActivity;
    // 作业票查询服务

    public String moduleCode = null;
    private RmtWorkSubtask transformRmtWorkSubtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        moduleCode = getIntent().getStringExtra(
                FrameworkActivity.INTENT_FUNCTION_KEY);

        transformRmtWorkSubtask = (RmtWorkSubtask) getIntent().getSerializableExtra(FrameworkActivity.INTENT_WORKTASK_KEY);


        getTitileName();
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {

        final android.app.ProgressDialog dialog = new android.app.ProgressDialog(BaseTaskAndItemListBusActivity.this);
        dialog.setMessage("正在查询");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // 获取任务浏览
        Call<ResultData<RmtTaskListQuery>> call = rmtInterface.getTaskListQuery(LoginUserRecord.getInstance().getUser().getUserid(), getFunctionCode(), getSearchStr(),getSearchId(), 1, 10);
        HttpBusiness.action(BaseTaskAndItemListBusActivity.this, false, call, new HttpCallBack<RmtTaskListQuery>() {
            @Override
            public void success(RmtTaskListQuery rmtTaskListQuery) {
                dialog.dismiss();

                List<RmtTaskListQuery.MainvoBean> mainvo = rmtTaskListQuery.getMainvo();


                //过滤掉没有作业票的数据
                for (RmtTaskListQuery.MainvoBean list : mainvo) {
                    if (list.getBodyVOs() != null && list.getBodyVOs().getRMT_WORK_SUBTASK_M() != null) {
                        List<RmtWorkSubTaskBean> rmt_work_subtask_m = list.getBodyVOs().getRMT_WORK_SUBTASK_M();


                        for (int i = 0; i < rmt_work_subtask_m.size(); i++) {
                            RmtWorkSubTaskBean rmttask = rmt_work_subtask_m.get(i);
                            RmtWorkSubTaskBean.BodyVOsBean bodyVOs = rmttask.getBodyVOs();
                            List<RmtWorkSubTaskBean.BodyVOsBean.RMTWORKPERMITMBeanX> rmt_work_permit_m = null;
                            if (bodyVOs != null) {
                                rmt_work_permit_m = bodyVOs.getRMT_WORK_PERMIT_M();
                            }
                            if (rmt_work_permit_m == null || rmt_work_permit_m.size() == 0) {
                                rmt_work_subtask_m.remove(rmttask);
                                i--;
                            }
                        }
                    }
                }

                if (transformRmtWorkSubtask != null) {
                    //消息推送走的
                    setDataList(mainvo, transformRmtWorkSubtask, getFunctionCode());
                } else {
                    //正常走的
                    setDataList(mainvo, getFunctionCode());
                }
                //处理底部按钮显示方式，并注册监听
                handleBottom();
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(BaseTaskAndItemListBusActivity.this, msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(BaseTaskAndItemListBusActivity.this, "获取数据列表失败");
            }
        });
        super.initData();

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
        if (moduleCode.equals("xgf")) {
            return "相关方";
        } else if (moduleCode.equals("leader")) {
            return "领导审批";
        }
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
    private IEventListener eventListener = new IEventListener() {

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
                //System.out.println("ACTIONBAR_SEARCH_CLICK");
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

    // /**
    // * startCSActivityForResult:(启动界面). <br/>
    // * date: 2015年3月10日 <br/>
    // *
    // * @author lxf
    // * @param workOrder
    // */
    // public void startCSActivityForResult(WorkOrder workOrder) {
    // startNextActivity(workOrder);
    // }

    /**
     * TODO 跳转到作业票审批页签 startNextActivity:(). date: 2015年02月06日
     *
     * @param superEctity 点击记录的实体
     * @throws HDException
     * @author lxf
     */
    private void startNextActivity(SuperEntity superEntity,
                                   SuperEntity itemWorkTask) {
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
            if (itemWorkTask != null) {
                bundle.putSerializable(FrameworkActivity.INTENT_ITEMWORKTASK_KEY, itemWorkTask);
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
     *
     * @see com.hd.hse.common.module.phone.ui.module.activity.BaseTaskListActivity#onWorkListItemClick(com.hd.hse.entity.workorder.WorkTask)
     */
    @Override
    protected void onWorkListItemClick(Object... obj) {
        // TODO Auto-generated method stub
        // 第一个参数是任务，第二个参数是分项任务，
        if (obj != null) {
            SuperEntity tempWorkTask = null;
            if (obj[0] instanceof SuperEntity) {
                tempWorkTask = (SuperEntity) obj[0];
            }
            SuperEntity tempItemWorkTask = null;
            if (obj.length > 1 && obj[1] instanceof SuperEntity) {
                tempItemWorkTask = (SuperEntity) obj[1];
            }
            startNextActivity(tempWorkTask, tempItemWorkTask);
        }

    }

    @Override
    protected void onWorkListItemLongClick(WorkTask workTask) {
        // TODO Auto-generated method stub
        // 获取要显示的列的配置
        List<SuperEntity> data = getShowPopupWinDate(workTask);
        // 长按作业票列表图标
        if (data != null) {
            showWorkOrderPopupWin(data);
        }

    }

    @Override
    protected void endSubTask(RmtWorkSubtask rmtWorkSubtask) {

    }

    @Override
    protected void endWorkTask(RmtWorkTaskBean rmtWorkTaskBean) {

    }

    private List<String[]> attrcode;
    private List<String[]> arrtnames;

    private List<SuperEntity> getShowPopupWinDate(SuperEntity superEntity) {
        List<SuperEntity> retSuper = null;
        try {
            if (arrtnames == null) {
                arrtnames = new ArrayList<String[]>();
                // 任务配置信息名称
                arrtnames
                        .add(getResources()
                                .getStringArray(
                                        R.array.hd_hse_common_component_dytable_worktask_desc_online));
                arrtnames
                        .add(getResources()
                                .getStringArray(
                                        R.array.hd_hse_common_component_dytable_worktask_two_desc_online));
                arrtnames
                        .add(getResources()
                                .getStringArray(
                                        R.array.hd_hse_common_component_dytable_worktask_three_desc_online));
                arrtnames
                        .add(getResources()
                                .getStringArray(
                                        R.array.hd_hse_common_component_dytable_worktask_four_desc_online));

            }
            if (attrcode == null) {
                attrcode = new ArrayList<String[]>();
                attrcode.add(getResources()
                        .getStringArray(
                                R.array.hd_hse_common_component_dytable_worktask_code_online));
                attrcode.add(getResources()
                        .getStringArray(
                                R.array.hd_hse_common_component_dytable_worktask_two_code_online));
                attrcode.add(getResources()
                        .getStringArray(
                                R.array.hd_hse_common_component_dytable_worktask_three_code_online));
                attrcode.add(getResources()
                        .getStringArray(
                                R.array.hd_hse_common_component_dytable_worktask_four_code_online));

            }
            retSuper = UtilTools.TaskConvertToListObject(superEntity, attrcode,
                    arrtnames);
        } catch (HDException e) {
            logger.error(e);
            ToastUtils.toast(getBaseContext(), "程序报错，请联系管理员!");
        }
        return retSuper;
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

    private ProgressDialog mProgressDialog;

    /**
     * ClassName: MyICallBackImpl ()<br/>
     * date: 2015年6月10日 <br/>
     *
     * @author lxf
     * @version BaseTaskListBusActivity
     */
    public class MyICallBackImpl implements ICallBack {

        @Override
        public void start(String action, int flag, Object... objects) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(
                        BaseTaskAndItemListBusActivity.this, "正在查询");
            }

            mProgressDialog.show();
        }

        @Override
        public void process(String action, int flag, Object... objects) {

        }

        @SuppressWarnings("unchecked")
        @Override
        public void end(String action, int flag, Object... objects) {
            // 查询出数据，设置给 ListView
//            if (objects[0] instanceof List) {
//                setDataList((List<SuperEntity>) objects[0]);
//
//            }
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }

        @Override
        public void error(String action, int flag, Object... objects) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
            logger.error(objects);
            if (objects != null && objects[0].toString().contains("没有查询到")) {
//                setDataList(new ArrayList<SuperEntity>());
                ToastUtils.toast(getBaseContext(), objects[0].toString());
            } else if (objects != null && objects[0].toString().contains("网络")) {
                ToastUtils.imgToast(getBaseContext(),
                        R.drawable.hd_common_message_error,
                        objects[0].toString());
            } else {
                ToastUtils.imgToast(getBaseContext(),
                        R.drawable.hd_hse_common_msg_wrong,
                        objects[0].toString());
            }
        }

    }

}
