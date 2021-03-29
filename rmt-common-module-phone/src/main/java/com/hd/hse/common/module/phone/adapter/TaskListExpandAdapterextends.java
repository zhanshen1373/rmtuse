package com.hd.hse.common.module.phone.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.custom.IconForZYPClass;
import com.hd.hse.constant.IWorkOrderStatus;
import com.hd.hse.constant.IWorkOrderZypClass;
import com.hd.hse.entity.workorder.ItemWorkTask;
import com.hd.hse.entity.workorder.RmtTaskListQuery;
import com.hd.hse.entity.workorder.RmtWorkSubTaskBean;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


/**
 * ClassName: TaskListExpandAdapterextends (可扩展的listview 适配器)<br/>
 * date: 2015年8月6日 <br/>
 *
 * @author Administrator
 */
public class TaskListExpandAdapterextends extends BaseExpandableListAdapter {

    private Logger logger = LogUtils
            .getLogger(TaskListExpandAdapterextends.class);
    private LayoutInflater inflater = null;
    private List<RmtTaskListQuery.MainvoBean> mainvo;
    private Context context;
    private ExpandableListView mExpandableListView;
    private Resources r;

    /**
     * childKey:TODO(取子表数据的KEY).
     */
    private String childKey = ItemWorkTask.class.getName();

    /**
     * worktime:TODO(任务开始时间).
     */
    private String worktime = "zystarttime";
    /**
     * workState:TODO(任务状态).
     */
    private String workState = "status";
    /**
     * workName:TODO(任务名称).
     */
    private String workName = "zyname";
    /**
     * workDept:TODO(任务部门).
     */
    private String workDept = "sddept_desc";
    /**
     * WorkArea:TODO(任务区域).
     */
    private String WorkArea = "zylocation_desc";

    /**
     * workItemName:TODO(分项任务名称).
     */
    private String workItemName = "zymx";
    /**
     * workOrderAll:TODO(取出分项任务下的所有票).
     */
    private String workOrderAll = "zylx";
    /**
     * workItemState:TODO(分项任务的状态).
     */
    private String workItemState = "status";

    /**
     * 同意
     */
    public static final String AGREE = "agree";
    /**
     * 不同意
     */
    public static String DISAGREE = "disagree";
    /**
     * 同意并提交厂级审批(老版)
     * 待厂级审批
     */
    public static String FCTWAPPR = "fctwappr";
    /**
     * 待审批
     */
    public static String WAPPR = "wappr";
    /**
     * 厂级同意
     */
    public static final String FCTAGREE = "fctagree";
    /**
     * 厂级不同意
     */

    public static final String FCTDISAGREE = "fctdisagree";

    /**
     * 待科级审批
     */
    public static final String DEPTWAPPR = "deptwappr";

    /**
     * 待相关方审批
     */
    public static final String XGF_WAPPR = "xgf_wappr";
    /**
     * 待车间审批
     */
    public static final String CJ_WAPPR = "cj_wappr";

    private int pageNum = 2;
    private int tempNum = -1;


    private int t = -1;
    private String moduleCode;
    private String search = "";
    private Long searchid;


    public TaskListExpandAdapterextends(Context context,
                                        ExpandableListView expandableListView) {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        mExpandableListView = expandableListView;
        init();
    }

    /**
     * init:(初始化). <br/>
     * date: 2015年8月6日 <br/>
     *
     * @author lxf
     */
    private void init() {
        r = this.context.getResources();
        mExpandableListView.setAdapter(this);
        mExpandableListView.setGroupIndicator(null); // 去掉默认带的箭头
        mExpandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {

                        if (view.getCount() < 10 * (pageNum - 1)) {
                            ToastUtil.toast(context, "没有更多数据了");
                        } else {
                            //加载更多功能的代码
                            if (tempNum != pageNum) {
                                final android.app.ProgressDialog dialog = new android.app.ProgressDialog(context);
                                dialog.setMessage("加载更多");
                                dialog.show();
                                tempNum = pageNum;
                                final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
                                // 获取任务浏览
                                Call<ResultData<RmtTaskListQuery>> call = rmtInterface.getTaskListQuery(LoginUserRecord.getInstance().getUser().getUserid(), moduleCode, search, searchid, pageNum, 10);
                                HttpBusiness.action(context, false, call, new HttpCallBack<RmtTaskListQuery>() {
                                    @Override
                                    public void success(RmtTaskListQuery rmtTaskListQuery) {
                                        dialog.dismiss();
                                        pageNum++;
                                        List<RmtTaskListQuery.MainvoBean> mainvo = rmtTaskListQuery.getMainvo();

                                        if (mainvo != null && mainvo.size() > 0) {
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

                                            TaskListExpandAdapterextends.this.mainvo.addAll(mainvo);
                                            notifyDataSetChanged();
                                        }

                                    }

                                    @Override
                                    public void warning(String msg) {
                                        dialog.dismiss();
                                        ToastUtil.toast(context, msg);
                                    }

                                    @Override
                                    public void error(Throwable t) {
                                        dialog.dismiss();
                                        ToastUtil.toast(context, "获取数据列表失败");
                                    }
                                });

                            }


                        }

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        showExpandGroup();
    }

    private IEventListener ieventListener;

    /**
     * setOnClickListener:(设置事件). <br/>
     * date: 2015年8月7日 <br/>
     *
     * @param onclick
     * @author lxf
     */
    public void setOnIEventListener(IEventListener onclick) {
        ieventListener = onclick;
    }

    /**
     * showExpandGroup:(默认展开第一个). <br/>
     * date: 2015年8月6日 <br/>
     *
     * @author lxf
     */
    private void showExpandGroup() {
        // 遍历所有group,将所有项设置成默认展开
        int groupCount = mExpandableListView.getCount();
        if (groupCount < 1) {
            return;
        }
        for (int i = 0; i < 1; i++) {
            mExpandableListView.expandGroup(i);
        }
    }

    /**
     * setDateinfo:(设置数据源). <br/>
     * date: 2015年8月6日 <br/>
     *
     * @param
     * @author lxf
     */
    public void setDateinfo(List<RmtTaskListQuery.MainvoBean> mainvo, String moduleCode) {
        this.mainvo = mainvo;
        this.notifyDataSetChanged();
        this.moduleCode = moduleCode;
        showExpandGroup();
    }

    //消息推送会执行
    public void setDateinfo(List<RmtTaskListQuery.MainvoBean> mainvo, RmtWorkSubtask rmtWorkSubtask, String moduleCode) {
        this.mainvo = mainvo;
        this.notifyDataSetChanged();
        this.moduleCode = moduleCode;
        for (RmtTaskListQuery.MainvoBean mainvoBean : mainvo) {
            t++;
            RmtTaskListQuery.MainvoBean.BodyVOsBeanX bodyVOs = mainvoBean.getBodyVOs();
            if (bodyVOs != null) {
                List<RmtWorkSubTaskBean> rmt_work_subtask_m = bodyVOs.getRMT_WORK_SUBTASK_M();
                if (rmt_work_subtask_m != null && rmt_work_subtask_m.size() > 0) {
                    for (RmtWorkSubTaskBean rmtWorkSubTaskBean : rmt_work_subtask_m) {
                        RmtWorkSubTaskBean.HeadVOBeanX headVO = rmtWorkSubTaskBean.getHeadVO();
                        if (headVO != null) {
                            RmtWorkSubtask rmt_work_subtask_m1 = headVO.getRMT_WORK_SUBTASK_M();
                            if (rmt_work_subtask_m1 != null) {
                                long work_subtask_id = rmt_work_subtask_m1.getWork_subtask_id();
                                if (rmtWorkSubtask.getWork_subtask_id() == work_subtask_id) {
                                    mExpandableListView.setSelection(t);
                                    mExpandableListView.expandGroup(t);
                                    return;
                                }
                            }
                        }
                    }
                }

            }
        }


    }

    @Override
    public int getGroupCount() {
        if (mainvo == null) {
            return 0;
        }
        // TODO Auto-generated method stub
        return mainvo.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mainvo == null) {
            return 0;
        }
        if (mainvo.size() <= (groupPosition)) {
            return 0;
        }
        if (mainvo.get(groupPosition).getBodyVOs() == null) {
            return 0;
        }
        if (mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M() == null) {
            return 0;
        } else {
//			return datelist.get(groupPosition).getChild(childKey).size();
//            return mainvo.get(groupPosition).getSubTaskList().size();
            return mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().size();

        }
    }

    @Override
    public List<RmtWorkSubTaskBean> getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        if (mainvo == null) {
            return null;
        }
//		RmtWorkTaskBean rmt_work_task_m = null;
        RmtTaskListQuery.MainvoBean mainvoBean = mainvo.get(groupPosition);
        if (mainvoBean != null) {
            RmtTaskListQuery.MainvoBean.BodyVOsBeanX bodyVOs = mainvoBean.getBodyVOs();
            if (bodyVOs != null) {
                List<RmtWorkSubTaskBean> rmt_work_subtask_m = bodyVOs.getRMT_WORK_SUBTASK_M();
                if (rmt_work_subtask_m != null && rmt_work_subtask_m.size() > 0) {
                    return rmt_work_subtask_m;
                }

            }
        }

        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        if (mainvo == null) {
            return null;
        }

        RmtWorkSubtask rmt_work_subtask_m = mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().
                get(childPosition).getHeadVO().getRMT_WORK_SUBTASK_M();


        return rmt_work_subtask_m;
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    private String mainTask;
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        boolean isexist = false;
        int childrenCount = getChildrenCount(groupPosition);
        String descrip = "";
        for (int i = 0; i < childrenCount; i++) {
            SuperEntity tempentity = (SuperEntity) getChild(groupPosition,
                    i);
            String leaderaudit = ((RmtWorkSubtask) tempentity).getLeaderaudit();
            String description = getDescription(leaderaudit);

            if (description.equals("待车间审批") || description.equals("待相关方审批") ||
                    description.equals("待科级审批") || description.equals("待厂级审批")) {
                isexist = true;
                descrip = description;
                break;
            }

        }

        if (convertView == null) {
            holder = new GroupViewHolder();
            convertView = inflater.inflate(
                    R.layout.hd_hse_component_expandablelist_task_item_one,
                    null);
            // 设置第一级月份
            holder.groupicon = (TextView) convertView
                    .findViewById(R.id.hd_hse_compontent_expandablelist_task_one_ionc_id);
            holder.groupTime = (TextView) convertView
                    .findViewById(R.id.hd_hse_compontent_expandablelist_task_one_time);
            holder.groupState = (TextView) convertView
                    .findViewById(R.id.hd_hse_compontent_expandablelist_task_one_state);
            holder.groupWorkName = (TextView) convertView
                    .findViewById(R.id.hd_hse_compontent_expandablelist_task_one_name);
            holder.groupDep = (TextView) convertView
                    .findViewById(R.id.hd_hse_compontent_expandablelist_task_one_dep);
            holder.groupArea = (TextView) convertView
                    .findViewById(R.id.hd_hse_compontent_expandablelist_task_one_area);
            holder.groupEndTask = (TextView) convertView
                    .findViewById(R.id.hse_common_module_phone_grid_item_end_work_task);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        //判断子view是否为空，或者数量为0
        if (mainvo.get(groupPosition).getBodyVOs() == null || mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M() == null ||
                mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().size() <= 0) {
            holder.groupicon.setVisibility(View.INVISIBLE);
        } else {
            holder.groupicon.setVisibility(View.VISIBLE);
        }

        if (isExpanded) {
            holder.groupicon
                    .setBackgroundResource(R.drawable.he_hse_common_component_opened);
        } else {
            holder.groupicon
                    .setBackgroundResource(R.drawable.he_hse_common_component_closed);
        }
        // 开始赋值

//        getCreated_dt()
        holder.groupTime.setText(mainvo.get(groupPosition).getHeadVO().getRMT_WORK_TASK_M().getEst_start_time());
        if (moduleCode.equals("browse") || moduleCode.equals("leader")) {
            if (mainvo.get(groupPosition).getHeadVO().getRMT_WORK_TASK_M().getStatus().equals("appr")) {
                if (mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().get(0).getHeadVO().getRMT_WORK_SUBTASK_M().getLeaderaudit() != null) {
                    if (mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().get(0).getHeadVO().getRMT_WORK_SUBTASK_M().getLeaderaudit().equals("wappr")) {
                        if (isexist) {
                            holder.groupState.setText(descrip);
                        } else {
                            holder.groupState.setText("待领导审批");
                        }
                    } else {
                        if (isexist) {
                            holder.groupState.setText(descrip);
                        } else {
                            holder.groupState.setText("作业中");
                        }
                    }
                } else {
                    if (isexist) {
                        holder.groupState.setText(descrip);
                    } else {
                        holder.groupState.setText("作业中");
                    }
                }

            } else {
                if (isexist) {
                    holder.groupState.setText(descrip);
                } else {
                    holder.groupState.setText(mainvo.get(groupPosition).getHeadVO().getRMT_WORK_TASK_M().getStatus_name());
                }
            }
        } else {
            if (isexist) {
                holder.groupState.setText(descrip);
            } else {
                holder.groupState.setText(mainvo.get(groupPosition).getHeadVO().getRMT_WORK_TASK_M().getStatus_name());
            }
        }
        holder.groupDep.setText(mainvo.get(groupPosition).getHeadVO().getRMT_WORK_TASK_M().getTerritorial_unit_name());
        holder.groupArea.setText(mainvo.get(groupPosition).getHeadVO().getRMT_WORK_TASK_M().getWork_site_name());
        holder.groupWorkName.setText(mainvo.get(groupPosition).getHeadVO().getRMT_WORK_TASK_M().getWork_name());
        holder.groupEndTask.setOnClickListener(new EndTaskOnClickListener(getEndTaskIEventType(), mainvo.get(groupPosition).getHeadVO().getRMT_WORK_TASK_M()));
        //允许关闭显示，不允许不显示
        setEndTaskViewVisibility(holder.groupEndTask, mainvo.get(groupPosition).getHeadVO().getRMT_WORK_TASK_M().isallowclose());

        mainTask=holder.groupState.getText().toString();
        if (mainvo.get(groupPosition) != null
                && mainvo.get(groupPosition).getBodyVOs() != null
                && mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M() != null) {
            List<RmtWorkSubTaskBean> childList = mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M();
            int flag = 0;// 0黑色，1同意绿色，2不同意红色
            for (RmtWorkSubTaskBean entity : childList) {
                RmtWorkSubtask rmtWorkSubtask = entity.getHeadVO().getRMT_WORK_SUBTASK_M();
                String leaderaudit = rmtWorkSubtask.getLeaderaudit();
                if (AGREE.equalsIgnoreCase(leaderaudit)
                        || FCTWAPPR.equalsIgnoreCase(leaderaudit)
                        || FCTAGREE.equalsIgnoreCase(leaderaudit)) {
                    if (holder.groupState.getText().equals("作业中") || holder.groupState.getText().equals("关闭中")) {
                        flag = 1;
                    }
                } else if (DISAGREE.equalsIgnoreCase(leaderaudit)
                        || FCTDISAGREE.equalsIgnoreCase(leaderaudit)) {
                    flag = 2;
                    break;
                }
            }
            if (flag == 2) {
                changeColor((ViewGroup) convertView, Color.parseColor("#FF0000"));
            } else if (flag == 1) {
                changeColor((ViewGroup) convertView, Color.parseColor("#2CAD3F"));
            } else {
                changeColor((ViewGroup) convertView, Color.BLACK);
            }

        } else {
            changeColor((ViewGroup) convertView, Color.BLACK);
        }


        return convertView;
    }

    /**
     * getValue:(根据属性获取值). <br/>
     * date: 2015年8月7日 <br/>
     *
     * @param temp
     * @param atr
     * @return
     * @author lxf
     */
    private String getValue(SuperEntity temp, String atr) {
        if (temp == null) {
            return "";
        }
        return temp.getAttribute(atr) == null ? "" : temp.getAttribute(atr)
                .toString();
    }

    int rowHeight = 0;
    int headHeight = 0;

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder = null;
        final SuperEntity tempentity = (SuperEntity) getChild(groupPosition,
                childPosition);

        if (convertView != null) {
            viewHolder = (ChildViewHolder) convertView.getTag();
        } else {
            viewHolder = new ChildViewHolder();
            convertView = inflater.inflate(
                    R.layout.hd_hse_component_expandablelist_task_item_two,
                    null);
            viewHolder.child_itemname = (TextView) convertView
                    .findViewById(R.id.hd_hse_component_phone_grid_item_itemtext);
            viewHolder.child_allworkorder = (RecyclerView) convertView
                    .findViewById(R.id.hd_hse_component_phone_grid_item_recyclerviewid);
            viewHolder.child_state = (TextView) convertView
                    .findViewById(R.id.hse_common_module_phone_grid_item_state);
            viewHolder.child_lookdetial = (TextView) convertView
                    .findViewById(R.id.hse_common_module_phone_grid_item_look_detail);
            viewHolder.child_checkbox = (CheckBox) convertView
                    .findViewById(R.id.hd_hse_component_phone_grid_item_checkbox);
            viewHolder.child_licheck = (LinearLayout) convertView
                    .findViewById(R.id.hd_hse_component_phone_grid_item_li);
            convertView.setTag(viewHolder);

        }

        if (isShowChexBox()) {
            viewHolder.child_licheck.setVisibility(View.VISIBLE);

        } else {
            viewHolder.child_licheck.setVisibility(View.GONE);
        }

        final String leaderaudit = ((RmtWorkSubtask) tempentity).getLeaderaudit();

        // 根据条件把文字变绿或者变红

        if (TextUtils.isEmpty(leaderaudit) || WAPPR.equalsIgnoreCase(leaderaudit)) {
            viewHolder.child_itemname.setTextColor(Color.BLACK);
        } else if (AGREE.equalsIgnoreCase(leaderaudit)
                || FCTWAPPR.equalsIgnoreCase(leaderaudit)
                || FCTAGREE.equalsIgnoreCase(leaderaudit)) {
            if (mainTask.equals("作业中")||mainTask.equals("关闭中")) {
                viewHolder.child_itemname.setTextColor(Color.parseColor("#2CAD3F"));
            }else{
                viewHolder.child_itemname.setTextColor(Color.BLACK);
            }
        } else if (DISAGREE.equalsIgnoreCase(leaderaudit)
                || FCTDISAGREE.equalsIgnoreCase(leaderaudit)) {
            viewHolder.child_itemname.setTextColor(Color.parseColor("#FF0000"));
        } else {
            viewHolder.child_itemname.setTextColor(Color.BLACK);
        }
        setCheckBoxVisibility(viewHolder.child_checkbox);
        viewHolder.child_checkbox.setChecked((((RmtWorkSubtask) tempentity).isSelected()));

        viewHolder.child_licheck.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v instanceof LinearLayout) {
                    CheckBox checkBox = (CheckBox) ((LinearLayout) v)
                            .getChildAt(0);
                    if (tempentity instanceof RmtWorkSubtask) {
                        // 判断是否含有审批中的作业票，有的话不允许勾选
                        RmtWorkSubTaskBean rmtWorkSubTaskBean = mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().get(childPosition);
                        RmtWorkSubTaskBean.BodyVOsBean bodyVOs = rmtWorkSubTaskBean.getBodyVOs();
                        List<RmtWorkSubTaskBean.BodyVOsBean.RMTWORKPERMITMBeanX> rmt_work_permit_m = null;
                        if (bodyVOs != null) {
                            rmt_work_permit_m = bodyVOs.getRMT_WORK_PERMIT_M();
                        }
                        for (RmtWorkSubTaskBean.BodyVOsBean.RMTWORKPERMITMBeanX beanX : rmt_work_permit_m) {
                            String Work_type = beanX.getHeadVO().getRMT_WORK_PERMIT_M().getWork_type();
                            String status = beanX.getHeadVO().getRMT_WORK_PERMIT_M().getStatus();
                            if (IWorkOrderStatus.INPRG.equalsIgnoreCase(status)
                                    && !IWorkOrderZypClass.ZYPCLASS_LSYDZY.equalsIgnoreCase(Work_type)) {
                                ToastUtils.toast(context, "分项任务中包含审批中的作业票，不可勾选");
                                return;
                            }

                        }


                        ((RmtWorkSubtask) tempentity)
                                .setSelected(!((RmtWorkSubtask) tempentity)
                                        .isSelected());
                        String description = getDescription(leaderaudit);
                        ((RmtWorkSubtask) tempentity).setFxdescription(description);
                        checkBox.setChecked(((RmtWorkSubtask) tempentity)
                                .isSelected());
                    }

                }

            }
        });


        // 设置第二级时间和事件名称
        viewHolder.child_itemname.setText(mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().get(childPosition).getHeadVO().getRMT_WORK_SUBTASK_M().getDescription());

//        String str = getValue(tempentity, workOrderAll);// "大票,动火作业,受限空间,临时用电,挖掘作业,高处作业";
        String work_type = mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().get(childPosition).getHeadVO().getRMT_WORK_SUBTASK_M().getWork_type();
        String[] split = work_type.split(",");
        int length = split.length;

        int rowcount = 6;
        viewHolder.child_allworkorder.setLayoutManager(new GridLayoutManager(
                context, rowcount));

        RmtWorkSubTaskBean rmtWorkSubTaskBean = mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().get(childPosition);
        RmtWorkSubtask rmt_work_subtask_m = rmtWorkSubTaskBean.getHeadVO().getRMT_WORK_SUBTASK_M();
        RmtWorkSubTaskBean.BodyVOsBean bodyVOs = rmtWorkSubTaskBean.getBodyVOs();
        List<RmtWorkSubTaskBean.BodyVOsBean.RMTWORKPERMITMBeanX> rmt_work_permit_m = null;
        if (bodyVOs != null) {
            rmt_work_permit_m = bodyVOs.getRMT_WORK_PERMIT_M();
        }
        if (!isLeaderAppr()) {

            if (moduleCode.equals("browse") || moduleCode.equals("leader")) {
                if (mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().get(childPosition).getHeadVO().getRMT_WORK_SUBTASK_M().getStatus().equals("appr")) {
                    if (mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().get(childPosition).getHeadVO().getRMT_WORK_SUBTASK_M().getLeaderaudit() != null) {
                        if (mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().get(childPosition).getHeadVO().getRMT_WORK_SUBTASK_M().getLeaderaudit().equals("wappr")) {
                            viewHolder.child_state.setText("待领导审批");
                        } else {
                            String description = getDescription(leaderaudit);
                            viewHolder.child_state.setText(description);
//                            viewHolder.child_state.setText("作业中");
                        }
                    } else {
                        String description = getDescription(leaderaudit);
                        viewHolder.child_state.setText(description);
//                        viewHolder.child_state.setText("作业中");
                    }
                } else {
                    viewHolder.child_state.setText(mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().get(childPosition).getHeadVO().getRMT_WORK_SUBTASK_M().getStatus_name());
                }
            } else {
                viewHolder.child_state.setText(mainvo.get(groupPosition).getBodyVOs().getRMT_WORK_SUBTASK_M().get(childPosition).getHeadVO().getRMT_WORK_SUBTASK_M().getStatus_name());

            }
        } else {
            String description = getDescription(leaderaudit);

            viewHolder.child_state.setText(description);

        }
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(
                context, rmt_work_subtask_m, rmt_work_permit_m);
        myRecyclerViewAdapter.setEditZyp(isEditZyp());
        viewHolder.child_allworkorder.setAdapter(myRecyclerViewAdapter);

        if (rowHeight == 0) {
            float tempValue = r
                    .getDimension(R.dimen.hd_hse_common_phone_tasklist_item_height);
            rowHeight = (int) tempValue;
        }
        if (headHeight == 0) {
            float tempValue = r
                    .getDimension(R.dimen.hd_hse_common_phone_tasklist_itemhead_height);
            headHeight = (int) tempValue;
        }
        if (length != 0 && length > rowcount) {
            int row = length / rowcount
                    + (length % rowcount > 0 ? 1 : 0);
            viewHolder.child_allworkorder.setMinimumHeight(rowHeight * row
            );

        } else {
            viewHolder.child_allworkorder.setMinimumHeight(rowHeight
            );
        }
        setRecyclerViewVisibility(viewHolder.child_allworkorder);


        List<RmtWorkSubTaskBean> group = getGroup(groupPosition);
        RmtWorkSubtask rmt_work_subtask_m2 = null;
        if (group != null && group.size() > 0) {

            RmtWorkSubTaskBean rmtWorkSubTaskBean1 = group.get(childPosition);
            RmtWorkSubTaskBean.HeadVOBeanX headVO1 = rmtWorkSubTaskBean1.getHeadVO();

            if (headVO1 != null) {
                rmt_work_subtask_m2 = headVO1.getRMT_WORK_SUBTASK_M();
            }
        }

        viewHolder.child_lookdetial
                .setOnClickListener(new DetailOnClickListener(getLookDetialIEventType()
                        ,
                        rmt_work_subtask_m2, tempentity, groupPosition,
                        childPosition));
        viewHolder.child_lookdetial.setText(getLookDetiaName());
        return convertView;
    }

    /**
     * 根据IEventType回调后进行不同的处理（目前为止，查看任务，结束任务）
     *
     * @return 默认返回IEventType.ACTION_LOOK_ITEMDETAIL（查看任务）
     */
    protected int getLookDetialIEventType() {
        return IEventType.ACTION_LOOK_ITEMDETAIL;
    }

    private String getDescription(String leaderaudit) {
        String state = "";
        if (TextUtils.isEmpty(leaderaudit) || leaderaudit.equals(WAPPR)) {
            state = "待审批";
        } else if (leaderaudit.equals(FCTWAPPR)) {
//                state = "待上级审批";
            state = "待厂级审批";
        } else if (leaderaudit.equals(AGREE)) {
            state = "同意";
        } else if (leaderaudit.equals(DISAGREE)) {
            state = "不同意";
        } else if (leaderaudit.equals(FCTAGREE)) {
            state = "厂级同意";
        } else if (leaderaudit.equals(FCTDISAGREE)) {
            state = "厂级不同意";
        } else if (leaderaudit.equals(DEPTWAPPR)) {
            state = "待科级审批";
        } else if (leaderaudit.equals(XGF_WAPPR)) {
            state = "待相关方审批";
        } else if (leaderaudit.equals(CJ_WAPPR)) {
            state = "待车间审批";
        } else {
            state = leaderaudit;
        }
        return state;
    }

    /**
     * 是否为领导审批，默认返回false
     *
     * @return
     */
    protected boolean isLeaderAppr() {
        return false;
    }

    protected int getEndTaskIEventType() {
        return IEventType.ACTION_END_WORK_TASK;
    }

    /**
     * 修改控件viewHolder.child_lookdetial名字
     *
     * @return 默认返回 查看任务
     */
    protected String getLookDetiaName() {
        return "查看任务";
    }

    /**
     * 设置RecyclerView是否显示，默认显示
     *
     * @param view
     */
    protected void setRecyclerViewVisibility(View view) {
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 设置checkbox是否显示，默认显示(浏览显示，结束不显示)
     *
     * @param view
     */
    protected void setCheckBoxVisibility(CheckBox view) {
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 设置groupEndTask是否显示，默认不显示
     *
     * @param view
     */
    protected void setEndTaskViewVisibility(View view, boolean isAllowClose) {
        view.setVisibility(View.GONE);
    }

    /**
     * 是否编辑作业票
     *
     * @return
     */
    protected boolean isEditZyp() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

    public void setSearch(String searchStr) {
        this.search = searchStr;
    }

    public void setSearchId(Long searchId) {
        this.searchid = searchId;
    }


    /**
     * ClassName: GroupViewHolder (父项类)<br/>
     * date: 2015年8月6日 <br/>
     *
     * @author lxf
     * @version ExpandAdapterTask
     */
    private class GroupViewHolder {
        TextView groupicon;// 图标
        TextView groupTime;// 时间
        TextView groupState;// 状态
        TextView groupWorkName;// 名称
        TextView groupDep;// 部门
        TextView groupArea;// 区域
        TextView groupEndTask;//结束主任务
    }

    /**
     * ClassName: ChildViewHolder (子项类)<br/>
     * date: 2015年8月6日 <br/>
     *
     * @author lxf
     * @version ExpandAdapterTask
     */
    private class ChildViewHolder {
        TextView child_itemname;
        RecyclerView child_allworkorder;
        TextView child_state;
        TextView child_lookdetial;
        CheckBox child_checkbox;
        LinearLayout child_licheck;
    }

    /**
     * onClickListener:TODO(设置点击查看信息的事件).
     */
    public class DetailOnClickListener implements OnClickListener {
        private int action;
        private SuperEntity mainWork;
        private SuperEntity itemWork;
        private int groupPosition;
        private int childPosition;

        public DetailOnClickListener(int action, SuperEntity mainwork,
                                     SuperEntity itemdate, int groupPosition, int childPosition) {
            this.action = action;
            this.mainWork = mainwork;
            this.itemWork = itemdate;
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            if (ieventListener != null) {
                try {
                    ieventListener.eventProcess(action, mainWork, itemWork,
                            groupPosition, childPosition);
                } catch (HDException e) {
                    logger.error(e);
                }
            }
        }

    }

    public class EndTaskOnClickListener implements OnClickListener {
        private int action;
        private SuperEntity mainWork;

        public EndTaskOnClickListener(int action, SuperEntity mainwork) {
            this.action = action;
            this.mainWork = mainwork;
        }

        @Override
        public void onClick(View v) {
            if (ieventListener != null) {
                try {
                    ieventListener.eventProcess(action, mainWork);
                } catch (HDException e) {
                    logger.error(e);
                }
            }
        }

    }


    /**
     * ConvertStrTOList:(将字符串转化成List数组). <br/>
     * date: 2015年8月6日 <br/>
     *
     * @param str
     * @param regularExpression
     * @return
     * @author lxf
     */
    private List<String> getListDateInfo(String str, String regularExpression) {
        List<String> listStr = new ArrayList<String>();
        if (str == null) {
            return listStr;
        }
        if (regularExpression == null || regularExpression.length() == 0) {
            regularExpression = ",";
        }
        String[] strs = str.split(regularExpression);
        for (String string : strs) {
            if (string.length() > 0) {
                listStr.add(IconForZYPClass.getZYPNameByZYPClass(string));
            }
        }
        return listStr;
    }

    /**
     * getListImage:(获取要显示的图片). <br/>
     * date: 2015年8月7日 <br/>
     *
     * @param str
     * @param regularExpression
     * @return
     * @author lxf
     */
    private List<Integer> getListImage(String str, String regularExpression) {
        List<Integer> listStr = new ArrayList<Integer>();
        if (str == null) {
            return listStr;
        }
        if (regularExpression == null || regularExpression.length() == 0) {
            regularExpression = ",";
        }
        String[] strs = str.split(regularExpression);
        for (String string : strs) {
            if (string.length() > 0) {
                listStr.add(IconForZYPClass.getIconIDByZYPClassItem(string));
            }
        }
        return listStr;
    }

    /**
     * getListImage:(获取要显示的图片). <br/>
     * date: 2015年8月7日 <br/>
     *
     * @param str
     * @param regularExpression
     * @return
     * @author lxf
     */
    private List<Integer> getStatusImage(String str, String regularExpression) {
        List<Integer> listStr = new ArrayList<Integer>();
        if (str == null) {
            return listStr;
        }
        if (regularExpression == null || regularExpression.length() == 0) {
            regularExpression = ",";
        }
        String[] strs = str.split(regularExpression);
        for (String string : strs) {
            if (string.length() > 0) {
                try {
                    listStr.add(IconForZYPClass.getIconIDByZYPState(string));
                } catch (HDException e) {
                    logger.error(e);
                }
            }
        }
        return listStr;
    }

    /**
     * 设置ViewGroup 内所有textview的颜色值
     *
     * @param root
     * @param color
     */
    protected void changeColor(ViewGroup root, int color) {
        for (int i = 0; i < root.getChildCount(); i++) {
            View v = root.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTextColor(color);
            } else if (v instanceof ViewGroup) {
                changeColor((ViewGroup) v, color);
            }
        }

    }

    /**
     * 是否显示多选框，默认是false,任务浏览不显示，领导审批显示
     *
     * @return
     */
    protected boolean isShowChexBox() {
        return false;
    }

}

