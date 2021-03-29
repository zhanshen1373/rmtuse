package com.hd.hse.common.module.phone.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.custom.IconForZYPClass;
import com.hd.hse.common.module.phone.ui.activity.WebActivity;
import com.hd.hse.entity.workorder.RmtWorkSubTaskBean;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.entity.workorder.TaskUrl;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import retrofit2.Call;

import static com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity.DESCRIPTION_KEY;
import static com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity.PERMITID_KEY;
import static com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity.SUBTASKID_KEY;

public class MyRecyclerViewAdapter extends
        RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private static Logger logger = LogUtils
            .getLogger(MyRecyclerViewAdapter.class);
    private Context mContext;
    //	private List<SuperEntity> mWorkOrders;
    List<RmtWorkSubTaskBean.BodyVOsBean.RMTWORKPERMITMBeanX> mWorkOrders;
    RmtWorkSubtask rmtworksubtaskmBean;

    public boolean isEditZyp() {
        return isEditZyp;
    }

    private String url;
    private boolean isEditZyp = false;

    public void setEditZyp(boolean editZyp) {
        isEditZyp = editZyp;
    }

    public MyRecyclerViewAdapter(Context context, RmtWorkSubtask rwswork, List<RmtWorkSubTaskBean.BodyVOsBean.RMTWORKPERMITMBeanX> list) {
        this.mContext = context;
        this.mWorkOrders = list;
        this.rmtworksubtaskmBean = rwswork;
    }

    @Override
    public int getItemCount() {
        // return this.objs.size();
        return mWorkOrders == null ? 0 : mWorkOrders.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int position) {
        Drawable drawable = mContext.getResources().getDrawable(
                IconForZYPClass.getIconIDByZYPClassItem(mWorkOrders.get(position).getHeadVO().getRMT_WORK_PERMIT_M().getWork_type()));
        ;
        viewHolder.imageView.setImageDrawable(drawable);
        try {
            drawable = mContext.getResources().getDrawable(
                    IconForZYPClass.getIconIDByZYPState(mWorkOrders.get(position).getHeadVO().getRMT_WORK_PERMIT_M().getStatus()));

        } catch (NotFoundException | HDException e) {
            logger.error(e);
            ToastUtils.imgToast(mContext, R.drawable.hd_hse_common_msg_wrong,
                    "获取状态信息错误！");
        }

        viewHolder.superScript.setImageDrawable(drawable);
        viewHolder.textView.setText((IconForZYPClass
                .getZYPNameByZYPClass(mWorkOrders.get(position).getHeadVO().getRMT_WORK_PERMIT_M().getWork_type())));


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                final ProgressDialog dialog = new ProgressDialog(mContext,
                        "正在查询表单...");
                dialog.show();
                long worksubtaskid = mWorkOrders.get(position).getHeadVO().getRMT_WORK_PERMIT_M().getWork_subtask_id();
                long permitid = mWorkOrders.get(position).getHeadVO().getRMT_WORK_PERMIT_M().getWork_permit_id();

                final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
                // 单张票报表的接口
                Call<ResultData<TaskUrl>> call = rmtInterface.getTaskSchedule(worksubtaskid, permitid);
                HttpBusiness.action(mContext, false, call, new HttpCallBack<TaskUrl>() {
                    @Override
                    public void success(TaskUrl rmtTaskListQuery) {
                        dialog.dismiss();

                        try {
                            url = URLDecoder.decode(rmtTaskListQuery.getUrl(), "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(mContext, WebActivity.class);
                        intent.putExtra(WebActivity.TITLE,
                                (IconForZYPClass
                                        .getZYPNameByZYPClass((mWorkOrders
                                                .get(position).getHeadVO().getRMT_WORK_PERMIT_M().getWork_type()
                                        ))));

                        intent.putExtra(WebActivity.ISEDITZYP, isEditZyp);
                        intent.putExtra(PERMITID_KEY, mWorkOrders
                                .get(position).getHeadVO().getRMT_WORK_PERMIT_M().getWork_permit_id());
                        intent.putExtra(SUBTASKID_KEY, mWorkOrders
                                .get(position).getHeadVO().getRMT_WORK_PERMIT_M().getWork_subtask_id());
                        intent.putExtra(DESCRIPTION_KEY, (IconForZYPClass
                                .getZYPNameByZYPClass(mWorkOrders.get(position).getHeadVO().getRMT_WORK_PERMIT_M().getWork_type())));


                        intent.putExtra(WebActivity.URL, url);
                        mContext.startActivity(intent);
                    }

                    @Override
                    public void warning(String msg) {
                        dialog.dismiss();
                        ToastUtil.toast(mContext, msg);
                    }

                    @Override
                    public void error(Throwable t) {
                        dialog.dismiss();
                        ToastUtil.toast(mContext, "获取数据列表失败");
                    }
                });


            }
        });

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.hd_hse_component_expandablelist_task_item_three, arg0,
                false));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView imageView;
        ImageView superScript;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = (ImageView) itemView
                    .findViewById(R.id.hd_hse_component_phone_grid_item_texticon_img);
            superScript = (ImageView) itemView
                    .findViewById(R.id.hd_hse_component_phone_grid_item_texticon_superscript);
            textView = (TextView) itemView
                    .findViewById(R.id.hd_hse_component_phone_grid_item_texticon_text);
        }

    }
}
