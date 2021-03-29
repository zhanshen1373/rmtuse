package com.hd.hse.wov.phone.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hd.hse.entity.workorder.TaskDetail;
import com.hd.hse.wov.phone.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间轴适配器 Created by liuyang on 2017/1/11.
 */

public class TimeLineAdapter extends
        RecyclerView.Adapter<TimeLineAdapter.MyViewHolder> {
    private Context mContext;
    private List<TaskDetail.MainvoBean> orders;

    public TimeLineAdapter(Context context, List<TaskDetail.MainvoBean> orders) {
        this.mContext = context;
        this.orders = orders;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TimeLineAdapter.MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_recycle_timeline, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (position == orders.size() - 1) {
            holder.timeline.setVisibility(View.GONE);
        } else
            holder.timeline.setVisibility(View.VISIBLE);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setAdapter(new TimeLineItemAdapter(orders
                .get(position)));
//		orders.get(position).getZypclassname()
        holder.workClassText.setText(orders.get(position).getHeadVO().getRMT_WORK_PERMIT_M().getWork_type_name());
//		orders.get(position).getStatus()
        String status = orders.get(position).getHeadVO().getRMT_WORK_PERMIT_M().getStatus();
        if (status == null)
            status = "";
        if (status.equalsIgnoreCase("INPRG")) {
            // 审批中
            holder.workStatTxt.setVisibility(View.VISIBLE);
            holder.workStatTxt.setText("审批中");
            holder.workStatTxt.setBackgroundColor(mContext.getResources()
                    .getColor(R.color.red));
            holder.workStatSignImg.setImageResource(R.drawable.status_inprg);
        } else if (status.equalsIgnoreCase("APPR")) {
            // 作业中
            holder.workStatTxt.setVisibility(View.VISIBLE);
            holder.workStatTxt.setText("作业中");
            holder.workStatTxt.setBackgroundColor(mContext.getResources()
                    .getColor(R.color.green));
            holder.workStatSignImg.setImageResource(R.drawable.status_appr);
        } else if (status.equalsIgnoreCase("CLOSE")) {
            // 已关闭
            holder.workStatTxt.setVisibility(View.VISIBLE);
            holder.workStatTxt.setText("已关闭");
            holder.workStatTxt.setBackgroundColor(mContext.getResources()
                    .getColor(R.color.gray));
            holder.workStatSignImg.setImageResource(R.drawable.status_close);
        } else if (status.equalsIgnoreCase("CANCEL")) {
            //取消
            holder.workStatTxt.setVisibility(View.VISIBLE);
            holder.workStatTxt.setText("取消");
            holder.workStatTxt.setBackgroundColor(mContext.getResources()
                    .getColor(R.color.gray));
            holder.workStatSignImg.setImageResource(R.drawable.status_close);
        } else {
            // 其他
            holder.workStatTxt.setText("");
            holder.workStatTxt.setVisibility(View.GONE);
            holder.workStatSignImg.setImageResource(R.drawable.status_cc);
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        View timeline;
        TextView workClassText;
        ImageView workStatSignImg;
        TextView workStatTxt;
        RecyclerView recyclerView;

        private MyViewHolder(View itemView) {
            super(itemView);
            timeline = itemView.findViewById(R.id.timeline);
            workClassText = (TextView) itemView.findViewById(R.id.work_class);
            workStatSignImg = (ImageView) itemView
                    .findViewById(R.id.work_stat_sign);
            workStatTxt = (TextView) itemView.findViewById(R.id.work_stat);
            recyclerView = (RecyclerView) itemView
                    .findViewById(R.id.item_recycle_view);
        }
    }

    private class TimeLineItemAdapter extends
            RecyclerView.Adapter<TimeLineItemAdapter.MyViewHolder> {
        //		private List<SuperEntity> instances;
        private List<TaskDetail.MainvoBean.BodyVOsBeanX.RMTWORKWFINSTANCEMBeanX> instances;

        private TimeLineItemAdapter(TaskDetail.MainvoBean order) {
            if (instances == null) {
                instances = new ArrayList<>();
            }
//			order.getChild(WorkflowInstance.class.getName())!= null
            if (order != null && order.getBodyVOs().getRMT_WORK_WFINSTANCE_M() != null) {
                instances.clear();
                instances.addAll(order.getBodyVOs().getRMT_WORK_WFINSTANCE_M());
            }
            filterData();
        }

        private void filterData() {
            for (int i = 0; i < instances.size(); i++) {
                String spfield = this.instances.get(i)
                        .getHeadVO().getRMT_WORK_WFINSTANCE_M().getAlias();
                if (spfield == null || "".equals(spfield)) {
                    instances.remove(i);
                    i--;
                }
            }

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(
                    R.layout.item_text_recycle, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
//			this.instances.get(position)
//					.getAttribute("spfield_desc").toString();

            String spfield = this.instances.get(position)
                    .getHeadVO().getRMT_WORK_WFINSTANCE_M().getAlias();
            if (spfield == null || "".equals(spfield))
                return;
            if (spfield.contains("#")) {
                String[] split = spfield.split("#");
                spfield = split[1];
            }
            final String status_name = instances.get(position).getHeadVO().getRMT_WORK_WFINSTANCE_M().getStatus_name();
            holder.statusTextView.setText(status_name);
            holder.textView.setText(spfield);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//					instances.get(position).getChild(WorkflowInstanceLine.class.getName()
//					List<SuperEntity> lines =
                    //数据是空的
                    List<TaskDetail.MainvoBean.BodyVOsBeanX.RMTWORKWFINSTANCEMBeanX.
                            BodyVOsBean.RMTWORKASSIGNLINEMBeanX>
                            rmt_work_assign_line_m = instances.get(position).getBodyVOs().
                            getRMT_WORK_ASSIGN_LINE_M();
                    showDialog(rmt_work_assign_line_m, status_name);
                }
            });


            switch (instances.get(position).getHeadVO().getRMT_WORK_WFINSTANCE_M().getStatus()) {
                case "wappr":
                    // 未确认
                    holder.textView
                            .setBackgroundResource(R.drawable.timeline_bg_unq);
                    break;
                case "inprg":
                    // 待确认
                    holder.textView
                            .setBackgroundResource(R.drawable.timeline_bg_need);
                    break;
                case "appr":
                    // 已确认
                    holder.textView
                            .setBackgroundResource(R.drawable.timeline_bg_done);
                    break;
                default:
                    break;
            }

        }

        @Override
        public int getItemCount() {
            return instances.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            TextView statusTextView;

            private MyViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text_view);
                statusTextView = (TextView) itemView
                        .findViewById(R.id.status_text_view);
            }
        }

        private void showDialog(List<TaskDetail.MainvoBean.BodyVOsBeanX.RMTWORKWFINSTANCEMBeanX.
                BodyVOsBean.RMTWORKASSIGNLINEMBeanX> lines, String dialogname) {
            final Dialog dialog = new Dialog(mContext, R.style.Dialog);
            View layout = LayoutInflater.from(mContext).inflate(
                    R.layout.dialog_timeline, null);

            dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            final ListView listView = (ListView) layout.findViewById(R.id.person_listview);
            final View divider = layout.findViewById(R.id.view);
            ListViewAdapter adapter = new ListViewAdapter(lines, dialogname);
            listView.setAdapter(adapter);
            final View okBtn = layout.findViewById(R.id.ok_btn);
            okBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    dialog.dismiss();
                }
            });
            dialog.setContentView(layout);
            final Window dialogWindow = dialog.getWindow();
            WindowManager m = ((Activity) mContext).getWindowManager();
            final Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
            final WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
            p.height = LayoutParams.MATCH_PARENT; // 高度设置为屏幕的0.6，根据实际情况调整
            p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
            dialogWindow.setAttributes(p);
            dialog.show();
            ViewTreeObserver vto = layout.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //在这里获取View及其子控件的坐标和长宽信息
                    int contentHeight = listView.getHeight() + okBtn.getHeight() + divider.getHeight();
                    int maxHeight = (int) (d.getHeight() * 0.6);
                    if (contentHeight < maxHeight) {
                        p.height = contentHeight;
                    } else {
                        p.height = maxHeight;
                    }
                    dialogWindow.setAttributes(p);
                }
            });
        }

        private class ListViewAdapter extends BaseAdapter {
            private List<TaskDetail.MainvoBean.BodyVOsBeanX.RMTWORKWFINSTANCEMBeanX.
                    BodyVOsBean.RMTWORKASSIGNLINEMBeanX> datas;
            private String dialogname;

            private ListViewAdapter(List<TaskDetail.MainvoBean.BodyVOsBeanX.RMTWORKWFINSTANCEMBeanX.
                    BodyVOsBean.RMTWORKASSIGNLINEMBeanX> datas, String dialogname) {
                this.datas = datas;
                this.dialogname = dialogname;
                if (this.datas == null) {
                    this.datas = new ArrayList<>();
                }
            }

            @Override
            public int getCount() {
                return this.datas.size();
            }

            @Override
            public Object getItem(int arg0) {
                return this.datas.get(arg0);
            }

            @Override
            public long getItemId(int arg0) {
                return arg0;
            }

            @Override
            public View getView(int arg0, View arg1, ViewGroup arg2) {
                View layout = LayoutInflater.from(mContext).inflate(R.layout.item_dialog_timeline, arg2, false);
                TextView nameText = (TextView) layout.findViewById(R.id.name_text);
                TextView statusText = (TextView) layout.findViewById(R.id.status_text);
                TextView timeText = (TextView) layout.findViewById(R.id.time_text);
//				WorkflowInstanceLine data = (WorkflowInstanceLine) this.datas.get(arg0);
                TaskDetail.MainvoBean.BodyVOsBeanX.RMTWORKWFINSTANCEMBeanX.BodyVOsBean.RMTWORKASSIGNLINEMBeanX rmtworkassignlinemBeanX = this.datas.get(arg0);
                TaskDetail.MainvoBean.BodyVOsBeanX.RMTWORKWFINSTANCEMBeanX.BodyVOsBean.RMTWORKASSIGNLINEMBeanX.HeadVOBeanXX headVO = rmtworkassignlinemBeanX.getHeadVO();
                String time = null;
                String relieved_status = null;
                String relieved_status_name = null;
                TaskDetail.MainvoBean.BodyVOsBeanX.RMTWORKWFINSTANCEMBeanX.BodyVOsBean.RMTWORKASSIGNLINEMBeanX.HeadVOBeanXX.RMTWORKASSIGNLINEMBean rmt_work_assign_line_m = null;
                if (headVO != null) {
                    rmt_work_assign_line_m = headVO.getRMT_WORK_ASSIGN_LINE_M();
                    if (rmt_work_assign_line_m != null) {
                        time = rmt_work_assign_line_m.getEnd_time();
                        relieved_status = rmt_work_assign_line_m.getRelieved_status();
                        relieved_status_name = rmt_work_assign_line_m.getRelieved_status_name();
                    }
                }
//				String time = data.getWgsj();
                if (time == null || time.equals("")) {
//					data.getZdr_desc()
                    if (relieved_status == null) {
                        nameText.setText(rmt_work_assign_line_m.getAssignee_name());
                        nameText.setTextColor(mContext.getResources().getColor(R.color.red));
//					"待确认"
                        statusText.setText(dialogname);
                        statusText.setTextColor(mContext.getResources().getColor(R.color.red));
                        timeText.setText("");
                    } else {
                        nameText.setText(rmt_work_assign_line_m.getAssignee_name());
                        nameText.setTextColor(mContext.getResources().getColor(R.color.red));
//					"待确认"
                        statusText.setText(relieved_status_name);
                        statusText.setTextColor(mContext.getResources().getColor(R.color.red));
                        timeText.setText("");
                    }

                } else {
                    if (relieved_status == null) {
                        nameText.setText(rmt_work_assign_line_m.getAssignee_name());
                        nameText.setTextColor(mContext.getResources().getColor(R.color.hd_hse_common_alerttext_black));
                        statusText.setText("已审批");
                        statusText.setTextColor(mContext.getResources().getColor(R.color.green));
                        timeText.setText(time);
                    } else {
                        nameText.setText(rmt_work_assign_line_m.getAssignee_name());
                        nameText.setTextColor(mContext.getResources().getColor(R.color.hd_hse_common_alerttext_black));
                        statusText.setText(relieved_status_name);
                        statusText.setTextColor(mContext.getResources().getColor(R.color.green));
                        timeText.setText(time);
                    }

                }
                return layout;
            }

        }
    }
}
