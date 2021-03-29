package rmt.leaderappr.phone.app.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.entity.workorder.TaskDetail;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class WovTimeLineFragmentNew extends BaseFragment {
	private static Logger logger = LogUtils
			.getLogger(WovTimeLineFragmentNew.class);
	private Context mContext;
	private TimeLineAdapter mAdapter;
	private List<TaskDetail.MainvoBean> orders;
	/**
	 * itemWorkTask:TODO(分项任务).
	 */
	private RmtWorkSubtask itemWorkTask;

	private RmtWorkSubtask workTask;
	/**
	 * mProgressDialog:TODO(dialog弹出框).
	 */
	private ProgressDialog mProgressDialog;

	public WovTimeLineFragmentNew() {
		super(BaseFragment.STYLE_NO_MENU);
	}

	@Override
	protected View onCreatePageView(LayoutInflater inflater) {
		mContext = getActivity();
		RecyclerView reView = new RecyclerView(mContext);
		reView.setLayoutManager(new LinearLayoutManager(mContext));
		orders = new ArrayList<TaskDetail.MainvoBean>();
		mAdapter = new TimeLineAdapter(mContext, orders);
		reView.setAdapter(mAdapter);
		/* 获取数据源。 */
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(getActivity(), "加载数据");
		}
		mProgressDialog.show();
		final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
		Call<ResultData<TaskDetail>> taskDetail = rmtInterface.getTaskDetail(LoginUserRecord.getInstance().getUser().getUserid(), workTask.getWork_subtask_id());
		HttpBusiness.action(mContext, false, taskDetail, new HttpCallBack<TaskDetail>() {
			@Override
			public void success(TaskDetail taskDetail) {
				mProgressDialog.dismiss();
				List<TaskDetail.MainvoBean> mainvo = taskDetail.getMainvo();
				WovTimeLineFragmentNew.this.orders.clear();
				WovTimeLineFragmentNew.this.orders.addAll(mainvo);
				mAdapter.notifyDataSetChanged();
			}

			@Override
			public void warning(String msg) {
				mProgressDialog.dismiss();
				ToastUtil.toast(mContext, msg);
			}

			@Override
			public void error(Throwable t) {
				mProgressDialog.dismiss();
				ToastUtil.toast(mContext, "获取数据失败");
			}
		});


		return reView;
	}

	/**
	 * setItemWorkTask:(设置分项任务). <br/>
	 * date: 2015年8月10日 <br/>
	 * 
	 * @author lxf
	 * @param itemWork
	 */
	public void setItemWorkTask(RmtWorkSubtask itemWork) {
		itemWorkTask = itemWork;
	}

	public void setWorkTask(RmtWorkSubtask workTask){
         this.workTask=workTask;
	}

}
