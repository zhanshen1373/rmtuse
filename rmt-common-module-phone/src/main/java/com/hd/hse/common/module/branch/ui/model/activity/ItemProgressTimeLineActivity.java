package com.hd.hse.common.module.branch.ui.model.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.hd.hse.common.component.phone.adapter.TimeLineExpanableListAdapter;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.ui.activity.BaseActivity;
import com.hd.hse.constant.BusinessActionNum;
import com.hd.hse.entity.workorder.WorkTask;
import com.hd.hse.service.inter.ICallBack;
import com.hd.hse.service.manager.ManagerInstance;

/**
 * ClassName: ItemProgressTimeLineActivity (分项任务进度)<br/>
 * date: 2015年8月3日 <br/>
 * 
 * @author lxf
 * @version
 */
public class ItemProgressTimeLineActivity extends BaseActivity {

	private List<SuperEntity> datalist;
	private ExpandableListView expandlistView;
	private TimeLineExpanableListAdapter statusAdapter;
	private Context context;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hd_hse_common_module_timeline_itemp_layout);
		context = this;
		expandlistView = (ExpandableListView) findViewById(R.id.hd_hse_common_timeline_expandlistviewid);

		// 获取数据源
		putInitData();
		String childKey = getChildKeyAtr();
		String lookTitle = getLookTitleAtr();
		String showName = getShowNameAtr();
		String showTime = getShowTimeAtr();
		statusAdapter = new TimeLineExpanableListAdapter(context,
				 childKey, lookTitle, showName, showTime);
		expandlistView.setAdapter(statusAdapter);
		// 去掉默认带的箭头
		expandlistView.setGroupIndicator(null);

		// 遍历所有group,将所有项设置成默认展开
		int groupCount = expandlistView.getCount();
		for (int i = 0; i < groupCount; i++) {
			expandlistView.expandGroup(i);
		}
		// 屏蔽展开折叠事件
		expandlistView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return true;
			}
		});
		// Intent intent = getIntent();
		// worktask = (WorkTask) intent.getSerializableExtra(FLAG);
		//
		// webView = (WebView) findViewById(R.id.wv_work_task_detail_detail);
		//
		// dialog = new ProgressDialog(this, "正在加载...");
		//

	}

	/**
	 * putInitData:(设置初始化数据源). <br/>
	 * date: 2015年8月3日 <br/>
	 * 
	 * @author lxf
	 */
	public void putInitData() {
		try {
			dialog = new ProgressDialog(this, "正在加载...");
			ManagerInstance
					.getInstance()
					.getActionInstance()
					.action(BusinessActionNum.QUERY_ITEMPROGRESS_INFO,
							icallback);
		} catch (HDException e) {
			e.printStackTrace();
		}
	}

	public void setDataInfo(List<SuperEntity> listdate) {
		if (statusAdapter != null) {
			statusAdapter.setDataInfo(listdate);
			refreshList();
		}
	}

	/**
	 * refreshList:(刷新列表数据). <br/>
	 * date: 2015年8月3日 <br/>
	 * 
	 * @author lxf
	 */
	public void refreshList() {
		if (statusAdapter != null) {
			statusAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * getChildKeyAtr:(获取子表数据的KEY). <br/>
	 * date: 2015年8月3日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public String getChildKeyAtr() {
		return "";
	}

	/**
	 * getLookTitleAtr:(获取标题字段属性). <br/>
	 * date: 2015年8月3日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public String getLookTitleAtr() {
		return "";
	}

	/**
	 * getShowNameAtr:(获取子项，第一列属性字段). <br/>
	 * date: 2015年8月3日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public String getShowNameAtr() {
		return "";
	}

	/**
	 * getShowTimeAtr:(获取子项，第二列属性字段). <br/>
	 * date: 2015年8月3日 <br/>
	 * 
	 * @author lxf
	 * @return
	 */
	public String getShowTimeAtr() {
		return "";
	}

	/**
	 * icallback:TODO(回调函数).
	 */
	public ICallBack icallback = new ICallBack() {

		@Override
		public void start(String action, int flag, Object... objects) {
			if (dialog != null) {
				dialog.show();
			}
		}

		@Override
		public void process(String action, int flag, Object... objects) {
			// TODO Auto-generated method stub

		}

		@Override
		public void error(String action, int flag, Object... objects) {
			if (dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
			ToastUtils.imgToast(context, R.drawable.hd_hse_common_msg_wrong,
					"加载错误:" + objects[0]);

		}

		@SuppressWarnings("unchecked")
		@Override
		public void end(String action, int flag, Object... objects) {
			if (objects != null && objects[0] instanceof List) {
				datalist = (List<SuperEntity>) objects[0];
				setDataInfo(datalist);
			}
		}
	};

}
