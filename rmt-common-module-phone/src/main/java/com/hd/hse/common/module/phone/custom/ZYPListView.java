package com.hd.hse.common.module.phone.custom;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
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
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.ui.model.fragment.GasesCheckFragment;
import com.hd.hse.entity.resultdata.RmtWorkGasDetectReslt;
import com.hd.hse.entity.workorder.ResultDataBean;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;

public class ZYPListView extends ListView {

	private static Logger logger = LogUtils.getLogger(GasesCheckFragment.class);

	private final String TAG = this.getClass().getSimpleName();
	private final boolean DEBUG = true;

	private List<SuperEntity> mData;

	private BaseAdapter mAdapter;

	private IEventListener mListener;

	private int pageNum = 2;
	private int tempNum=-1;

	private String funcCode;
	private String searchStr;

	public ZYPListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ZYPListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ZYPListView(Context context) {
		super(context);
		init();
	}

	public void setEventListener(IEventListener listener) {
		mListener = listener;
	}

	private void init() {
		setDivider(new ColorDrawable(0xffeaeaea));
		setDividerHeight(3);

		mAdapter = new Adapter();
		setAdapter(mAdapter);

		this.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (mListener != null) {
					try {
						mListener.eventProcess(IEventType.WORK_LIST_CLICK,
								mData.get(arg2));
					} catch (HDException e) {
						logger.error(e);
					}
				}
			}
		});

		this.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (mListener != null) {
					try {
						mListener.eventProcess(IEventType.WORK_LIST_LONG_CLICK,
								mData.get(arg2));
						return true;
					} catch (HDException e) {
						logger.error(e);
					}
				}
				return false;
			}
		});

		setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {

					if (view.getLastVisiblePosition() == view.getCount() - 1) {

						if (view.getCount() < 10 * (pageNum - 1)) {
							ToastUtil.toast(getContext(), "没有更多数据了");
						} else {
							//加载更多功能的代码
							if (tempNum!=pageNum){
								final ProgressDialog dialog = new ProgressDialog(getContext());
								dialog.setMessage("加载更多");
								dialog.show();
								tempNum=pageNum;
								final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
								// 获取分项任务列表
								Call<ResultData<ResultDataBean<RmtWorkSubtask>>> call = rmtInterface.getRmtWorkSubtaskList(LoginUserRecord.getInstance().getUser().getUserid(), funcCode, searchStr,pageNum,10);
								HttpBusiness.action(getContext(), false, call, new HttpCallBack<ResultDataBean<RmtWorkSubtask>>() {
									@Override
									public void success(ResultDataBean<RmtWorkSubtask> resultDataBean) {
										dialog.dismiss();
										pageNum++;
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
										mData.addAll(rmtWorkSubtasks);
										mAdapter.notifyDataSetChanged();
									}

									@Override
									public void warning(String msg) {
										dialog.dismiss();
										ToastUtil.toast(getContext(), msg);
									}

									@Override
									public void error(Throwable t) {
										dialog.dismiss();
										ToastUtil.toast(getContext(), "获取数据列表失败");
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
	}

	public void setData(List<SuperEntity> data,String funcCode,String searchStr) {
		if (data != null) {
			if (data.size() > 0 && !(data.get(0) instanceof RmtWorkSubtask)) {
				return;
			}
		}
		mData = data;
		this.funcCode=funcCode;
		this.searchStr=searchStr;
		mAdapter.notifyDataSetChanged();
	}

	private class Adapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (mData != null) {
				return mData.size();
			}
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View _resultView = null;
			ViewHolder _holder = null;

			if (convertView == null) {
				_resultView = View.inflate(getContext(),
						R.layout.hd_hse_commmon_module_zyplist_item, null);
				_holder = new ViewHolder();

				_holder.tvDate = (TextView) _resultView
						.findViewById(R.id.hd_hse_common_module_tv_date);
				_holder.tvTime = (TextView) _resultView
						.findViewById(R.id.hd_hse_common_module_tv_time);
				_holder.tvName = (TextView) _resultView
						.findViewById(R.id.hd_hse_common_module_tv_name);
				_holder.tvRegion = (TextView) _resultView
						.findViewById(R.id.hd_hse_common_module_tv_region);
				_holder.divider = (TextView) _resultView
						.findViewById(R.id.hd_hse_common_module_tv_divider);

				_resultView.setTag(_holder);

			} else {
				_resultView = convertView;
				_holder = (ViewHolder) convertView.getTag();
			}

			RmtWorkSubtask rmtWorkSubtask = (RmtWorkSubtask)mData.get(position);

			if ((position % 2) == 0) {
				_holder.divider.setBackgroundColor(0xffeaeaea);
			} else {
				_holder.divider.setBackgroundColor(0xff7cc4ed);
			}

			SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date _date = _sdf.parse(rmtWorkSubtask.getEst_start_time());
				Calendar _calendar = new GregorianCalendar();
				_calendar.setTime(_date);

				/*
				 * 用 Calendar 取月份的时候注意java 中的月份是从0 开始的，所以要加 1
				 */
				_holder.tvDate.setText(_calendar.get(Calendar.MONTH) + 1 + "月"
						+ _calendar.get(Calendar.DAY_OF_MONTH) + "日");

				_holder.tvTime.setText(_calendar.get(Calendar.HOUR_OF_DAY)
						+ ": " + _calendar.get(Calendar.MINUTE));

			} catch (ParseException e) {
				e.printStackTrace();
			}

			_holder.tvName.setText(rmtWorkSubtask.getDescription());
			//地址
			String addr = rmtWorkSubtask.getWork_site_name();
			if (addr == null || addr.equals("null") || addr.equals("")) {
				addr = "";
			}
			_holder.tvRegion.setText("区域：" + addr);

			return _resultView;
		}

	}

	private class ViewHolder {
		public TextView tvDate;
		public TextView tvTime;
		public TextView tvName;
		public TextView tvRegion;
		public TextView divider;
	}

}
