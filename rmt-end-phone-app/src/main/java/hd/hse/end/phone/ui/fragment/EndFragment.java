/**
 * Project Name:hse-end-phone-app
 * File Name:EndFragment.java
 * Package Name:hd.hse.end.phone.ui.fragment
 * Date:2015年6月10日
 * Copyright (c) 2015, longgang@ushayden.com All Rights Reserved.
 */

package hd.hse.end.phone.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.common.module.phone.custom.IconForZYPClass;
import com.hd.hse.constant.IWorkOrderStatus;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.entity.dict.RmtDict;
import com.hd.hse.entity.resultdata.RmtWorkEnd;
import com.hd.hse.entity.workorder.RmtWorkPermit;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import org.apache.log4j.Logger;
import org.hse.end.phone.app.R;

import java.util.ArrayList;
import java.util.List;

import hd.hse.end.phone.ui.adapter.EndListAdapter;
import hd.hse.end.phone.ui.custom.LightListView;
import retrofit2.Call;

/**
 * ClassName:EndFragment (任务结束fragment).<br/>
 * Date: 2015年6月10日 <br/>
 *
 * @author wen
 * @see
 */
public class EndFragment extends BaseFragment {

    private static Logger logger = LogUtils.getLogger(EndFragment.class);
    /**
     * mWorkTask:TODO(任务对象).
     */
    private RmtWorkSubtask mWorkTask;


    /**
     * 是否关闭的是住任务
     */
    private boolean isEndTask;
    /**
     * _workOrders:TODO(票的信息).
     */
    private List<RmtWorkPermit> _workOrders;
    /**
     * mAdapter:TODO(适配器).
     */
    private EndListAdapter mAdapter;
    /**
     * mListView:TODO(view).
     */
    private LightListView mListView;

    /**
     * mcontext:TODO(窗体).
     */
    private Context mcontext;

    /**
     * mProgressDialog:TODO(dialog弹出框).
     */
    private ProgressDialog mDialog;

    public EndFragment() {
        super(BaseFragment.STYLE_SINGLE_BUTTON);
    }

    public boolean isEndTask() {
        return isEndTask;
    }

    public void setEndTask(boolean endTask) {
        isEndTask = endTask;
    }

    @Override
    protected View onCreatePageView(LayoutInflater inflater) {
        mcontext = getActivity();
        mListView = new LightListView(mcontext);
        if (_workOrders == null && mConfigs.get(0) != null) {
            _workOrders = mConfigs.get(0).getPermitMVOList();
        }

        setOnBottomButtonClickListener(new BottomButtonClickListener());
        initData();
        return mListView;
    }

    private void initData() {
        final ProgressDialog dialog = new ProgressDialog(EndFragment.this.getActivity());
        dialog.setMessage("正在查询");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<RmtWorkEnd>> workEnd = rmtInterface.getWorkEnd(LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(EndFragment.this.getActivity(), false, workEnd, new HttpCallBack<RmtWorkEnd>() {

            @Override
            public void success(RmtWorkEnd rmtWorkEnd) {
                if (rmtWorkEnd != null) {
                    mAdapter = new EndListAdapter(mcontext, mConfigs, _workOrders, isEndTask,rmtWorkEnd);
                    mListView.setAdapter(mAdapter);
                }
                dialog.dismiss();
//                ToastUtil.toast(EndFragment.this.getActivity(), "获取数据成功");
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(EndFragment.this.getActivity(), msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
//                ToastUtil.toast(EndFragment.this.getActivity(), "获取数据失败");
                mAdapter = new EndListAdapter(mcontext, mConfigs, _workOrders, isEndTask,null);
                mListView.setAdapter(mAdapter);
            }

        });

    }

    /**
     * setWorkTask:(). <br/>
     * date: 2015年6月10日 <br/>
     *
     * @param workTask
     * @author xuxinwen
     */
    public void setWorkTask(RmtWorkSubtask workTask) {
        mWorkTask = workTask;
    }

    /**
     * 内部方法，找到该 PDAWorkOrderInfoConfig 实体中被选中的 Domain findSelectDomain:(). <br/>
     * date: 2015年6月12日 <br/>
     *
     * @param config
     * @return
     * @author xuxinwen
     */
    private RmtDict findSelectDomain(RmtConfMIntfc config) {
        // 找出被选的 Domain
        List<RmtDict> _listalndomain = config.getDictList();
        for (RmtDict item : _listalndomain) {
            if (item.getIsselected() == 1) {
                return item;

            }
        }
        return null;
    }

    /**
     * 保存 ClassName: BottomButtonClickListener ()<br/>
     * date: 2015年6月11日 <br/>
     *
     * @author xuxinwen
     * @version EndFragment
     */
    private class BottomButtonClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            // 得到所有的选中状态，遍历被选中的条目。
            boolean[] _selectState = mAdapter.getSelectState();
            boolean _hasSelect = false;
            if (_workOrders == null && mConfigs.get(0) != null) {
                _workOrders = mConfigs.get(0).getPermitMVOList();
            }
            // 为要保存的 PDAWorkOrderInfoConfig 创建集合。
            List<RmtConfMIntfc> _configListToSave = new ArrayList<RmtConfMIntfc>();
            for (int i = (_selectState.length - 1); i >= 0; i--) {
                // 是否选中的判断。
                if (_selectState[i]) {
                    _hasSelect = true;
                    // 该 项被选中
                    RmtConfMIntfc _config = mConfigs.get(i);
                    // 找出该 PDAWorkOrderInfoConfig 被选中 Domain，也就是结束原因。
                    RmtDict _endDomain = findSelectDomain(_config);
                    // 如果没有选择结束原因，不再继续保存操作。
                    if (_endDomain == null) {
                        String _zypName = IconForZYPClass
                                .getZYPNameByZYPClass(_config.getWork_type());
                        ToastUtils
                                .toast(getActivity(), _zypName + " 没有选择结束原因！");
                        return;
                    }
                    String _endReasonDes = null;
                    // 从UI中找出描述信息，从 LightListView 中找出对应的 itemView
                    View _v = mListView.getLightViewChildAt(i);
                    EditText _text = (EditText) _v
                            .findViewById(R.id.hd_hse_end_phone_des_et);
                    _endReasonDes = _text.getText().toString();

                    // 根据 zypClass 去匹配相应的 WorkOrder ，这里的匹配方法是循环遍历，比对。
                    RmtWorkPermit _workOrder = null;
                    // 遍历 WorkOrder 找对应的WorkOrder
                    for (int j = (_workOrders.size() - 1); j >= 0; j--) {
                        _workOrder = (RmtWorkPermit) _workOrders.get(j);
                        // 比对的代码
                        if (_workOrder.getWork_type().equals(
                                _config.getWork_type())) {
                            // 找到对应的 WorkOrder,
                            // 设置中文描述
                            /*_workOrder.setGbtype_desc(_endDomain
									.getDescription());*/
                            // 对应用户在文本框中输入的信息。
                            _workOrder.setStop_comment(_endReasonDes);
                            // 结束原因的编码，存的不是Domain里完整的编码，
                            if (_endDomain.getCode().contains(
                                    IWorkOrderStatus.CLOSE)) {
                                _workOrder.setStatus(IWorkOrderStatus.CLOSE);
                                //_workOrder.setSpstatus(IWorkOrderStatus.CLOSE);
                            } else if (_endDomain.getCode().contains(
                                    IWorkOrderStatus.CAN)) {
                                _workOrder.setStatus(IWorkOrderStatus.CAN);
                                //_workOrder.setSpstatus(IWorkOrderStatus.CAN);
                            }
                            _workOrder.setStop_reason(_endDomain.getCode());
                            break;
                        }
                    }
                    List<RmtWorkPermit> list = new ArrayList<RmtWorkPermit>();
                    list.add(_workOrder);
                    _config.setPermitMVOList(list);
                    _configListToSave.add(_config);
                }
            }
            if (!_hasSelect) {
                ToastUtils.imgToast(getActivity(),
                        R.drawable.hd_common_message_error, "请选择要保存的对象");
                return;
            }
            /* 遍历结束开始调用保存服务。 */

            if (mDialog == null) {
                mDialog = new ProgressDialog(getActivity());
                mDialog.setMessage("保存中...");
            }
            mDialog.show();
            final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
            Call<ResultData<Object>> approveByTab = rmtInterface.approveByTab(_configListToSave, LoginUserRecord.getInstance().getUser().getUserid());
            HttpBusiness.action(getActivity(), false, approveByTab, new HttpCallBack<Object>() {
                @Override
                public void success(Object o) {
                    mDialog.dismiss();
                    ToastUtils.imgToast(getActivity(), R.drawable.hd_hse_phone_message_true, "保存成功");
                    disableItemViews();
                    unSelectItemViews();
                    boolean[] _selectState = mAdapter.getSelectState();
                    for (int i = _selectState.length - 1; i >= 0; i--) {
                        _selectState[i] = false;
                    }
                }

                @Override
                public void warning(String msg) {
                    mDialog.dismiss();
                    ToastUtils.toast(getActivity(), msg);
                }

                @Override
                public void error(Throwable t) {
                    mDialog.dismiss();
                    t.printStackTrace();
                    ToastUtils.toast(getActivity(), "保存数据失败");
                }
            });
        }
    }


    /**
     * unSelectItemViews:(隐藏被选中的图标). <br/>
     * date: 2015年6月19日 <br/>
     *
     * @author lxf
     */
    private void unSelectItemViews() {
        boolean[] _selectState = mAdapter.getSelectState();

        for (int i = (_selectState.length - 1); i >= 0; i--) {
            if (_selectState[i]) {
                // 被选中项position
                View _itemView = mListView.getLightViewChildAt(i);
                _itemView.findViewById(R.id.hd_hse_end_phone_select_icon)
                        .setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * disableItemViews:(禁用控件). <br/>
     * date: 2015年6月19日 <br/>
     *
     * @author xunxinwen
     */
    private void disableItemViews() {
        boolean[] _selectState = mAdapter.getSelectState();
        for (int i = (_selectState.length - 1); i >= 0; i--) {
            // 只把选中，禁用
            if (_selectState[i]) {
                // 被选中项position
                View _itemView = mListView.getLightViewChildAt(i);
                _itemView.findViewById(R.id.hd_hse_end_phone_select)
                        .setEnabled(false);
                _itemView.findViewById(R.id.hd_hse_end_phone_end_reason_btn)
                        .setEnabled(false);
                _itemView.findViewById(R.id.hd_hse_end_phone_des_et)
                        .setEnabled(false);
                _itemView.findViewById(R.id.hd_hse_end_phone_icon_iv).setEnabled(false);
            }
        }
    }
}
