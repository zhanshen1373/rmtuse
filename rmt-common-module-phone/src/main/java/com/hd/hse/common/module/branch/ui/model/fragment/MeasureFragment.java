package com.hd.hse.common.module.branch.ui.model.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.adapter.StepCheckPointListAdapter;
import com.hd.hse.common.module.phone.adapter.SuperStepAdapter;
import com.hd.hse.constant.IConfigEncoding;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.entity.resultdata.RmtVirRiskM;
import com.hd.hse.entity.workorder.RmtWorkPermitMeas;

import java.util.ArrayList;
import java.util.List;

public class MeasureFragment<T extends SuperEntity> extends BaseFragment
        implements IEventListener {

    public static final String MEASURE_DATA = "measure_data";
    private ListView listView;
    private List<T> data;
    private List<String> attrs;
    private SuperStepAdapter<T> adapter;
    private ProgressDialog dialog;
    private Activity context;

    public MeasureFragment() {
//        super(STYLE_MULTI_MENU);
        super(STYLE_SINGLE_BUTTON);
    }


    @Override
    protected View onCreatePageView(LayoutInflater inflater) {
        context = getActivity();
        initConfig();
        listView = new ListView(getActivity());
        adapter = new StepCheckPointListAdapter<T>(getActivity(), data, attrs);
        listView.setAdapter(adapter);

        setBottomButtonText("审核");

        setOnBottomButtonClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {

                String tabName = null;
                try {
                    tabName = getNotApproveTabName(mConfigs, mPDAWorkOrderInfoConfig);
                } catch (HDException e) {
                    e.printStackTrace();
                }
                if (tabName != null && !tabName.equals("")) {
                    ToastUtil.toast(getActivity(), "审核失败!" + tabName + "还未完成审核");
                    return;
                }


//

                List<T> sourceData = adapter.getSourceValues();
                // 措施默认不适用
                if (sourceData != null && sourceData.size() != 0) {
                    for (T t : sourceData) {
                        if (t instanceof RmtWorkPermitMeas
                                && (((RmtWorkPermitMeas) t).getConfirm() == null) ||
                                ((RmtWorkPermitMeas) t).getConfirm() < 0) {
//                            t.setAttribute("confirm", 0);
                        }
                        if (t instanceof RmtWorkPermitMeas && ((RmtWorkPermitMeas) t).getConfirm() == null ||
                                ((RmtWorkPermitMeas) t).getConfirm()==0) {

                            if (t instanceof RmtWorkPermitMeas && ((RmtWorkPermitMeas) t).getIsselectitem()) {
                                t.setAttribute("confirm", 1);
                                t.setAttribute("isselect", 1);
                            } else if (t instanceof RmtWorkPermitMeas && !((RmtWorkPermitMeas) t).getIsselectitem()) {
                                t.setAttribute("confirm", 0);
                                t.setAttribute("isselect", 0);
                            }

                        }

                    }
                    adapter.modifyCurrentValues(sourceData);
                }


                // TODO 本地校验
                /*
                 * 1. 措施全部有结果 2. 措施不能全部为不适用 3. 措施结果为是，()中为必填项 4. 气体检测结果不能为否。
				 */
                boolean isHasResult = true;
                boolean isOnlyOneResult = true;
                boolean isFinishedEdit = true;
                boolean isALLNotChose = true;
                for (T t : sourceData) {
                    Integer status = (Integer) t.getAttribute("confirm");
                    if (status == null) {
                        isHasResult = false;
                        break;
                    } else {
                        if (status != 0 && status != 1 && status != 2) {
                            isHasResult = false;
                            break;
                        } else {
                            if (status == 0 || status == 1) {
                                if (status == 1) {
                                    //如果有选过的
                                    isALLNotChose = false;
                                }
                                isOnlyOneResult = false;
                                if (status == 1) {
                                    String description = (String) t
                                            .getAttribute("meas_description");
                                    description = description.replaceAll(" +",
                                            "");
                                    if (description.contains("()")
                                            || description.contains("（）")) {
                                        isFinishedEdit = false;
                                    }
                                }
                            }
                        }
                    }
                }

                if (isHasResult && !isOnlyOneResult && isFinishedEdit && !isALLNotChose) {
                    uploadRecord(mPDAWorkOrderInfoConfig);
                } else {
                    Message msg = handler.obtainMessage();
                    msg.what = MSG_ERROR;
                    if (!isHasResult) {
                        msg.obj = "必须操作完所有措施项！";
                    } else if (isALLNotChose) {
                        msg.obj = "措施结果不能全部为不适应";
                    } else if (!isFinishedEdit) {
                        msg.obj = "措施项为“是”的措施，必须完成编辑操作";
                    }
                    msg.sendToTarget();
                }


            }


        });

//        setOnBottomActionBarEventListener(this);

        return listView;
    }

    /**
     * 属性列表顺序 0. 条目左侧显示文本 1. 条目中间显示文本 2. 条目右侧显示状态 3. 条目左侧PC端选中状态 4. 条目是否被选中的属性
     * 5. 气体检测措施项的特殊属性
     */
    @SuppressWarnings("unchecked")
    private void initConfig() {

        if (mPDAWorkOrderInfoConfig.getVirRiskMVOList() == null ||
                mPDAWorkOrderInfoConfig.getVirRiskMVOList().size() == 0) {
            return;
        }
        List<RmtVirRiskM> rmtVirRiskMList = mPDAWorkOrderInfoConfig.getVirRiskMVOList();
        data = new ArrayList<T>();
        for (RmtVirRiskM rmtVirRiskM : rmtVirRiskMList) {
            data.addAll((List<T>) rmtVirRiskM.getMeasVOList());
        }

        attrs = new ArrayList<String>();
        attrs.add("meas_description");
        attrs.add("confirm");
        attrs.add("isselect");
        attrs.add("isselecteditem");
    }

    @Override
    public void eventProcess(int action, Object... args) throws HDException {
        if (action == IEventType.BOTTOM_BUTTON_CLICK) { // 审核
            //检验相应的危害是否已经审批
            String tabName = getNotApproveTabName(mConfigs, mPDAWorkOrderInfoConfig);
            if (tabName != null && !tabName.equals("")) {
                ToastUtil.toast(getActivity(), "审核失败!" + tabName + "还未完成审核");
                return;
            }


            List<T> sourceData = adapter.getSourceValues();
            // 措施默认不适用
            if (sourceData != null && sourceData.size() != 0) {
                for (T t : sourceData) {
                    if (t instanceof RmtWorkPermitMeas
                            && (((RmtWorkPermitMeas) t).getConfirm() == null
                            || ((RmtWorkPermitMeas) t).getConfirm() < 0)) {
                        t.setAttribute("confirm", 2);
                    }
                }
                adapter.modifyCurrentValues(sourceData);
            }


            // TODO 本地校验
                /*
                 * 1. 措施全部有结果 2. 措施不能全部为不适用 3. 措施结果为是，()中为必填项 4. 气体检测结果不能为否。
				 */
            boolean isHasResult = true;
            boolean isOnlyOneResult = true;
            boolean isFinishedEdit = true;
            for (T t : sourceData) {
                Integer status = (Integer) t.getAttribute("confirm");
                if (status == null) {
                    isHasResult = false;
                    break;
                } else {
                    if (status != 0 && status != 1 && status != 2) {
                        isHasResult = false;
                        break;
                    } else {
                        if (status == 0 || status == 1) {
                            isOnlyOneResult = false;
                            if (status == 1) {
                                String description = (String) t
                                        .getAttribute("meas_description");
                                description = description.replaceAll(" +",
                                        "");
                                if (description.contains("()")
                                        || description.contains("（）")) {
                                    isFinishedEdit = false;
                                }
                            }
                        }
                    }
                }
            }

            if (isHasResult && !isOnlyOneResult && isFinishedEdit) {
                uploadRecord(mPDAWorkOrderInfoConfig);
            } else {
                Message msg = handler.obtainMessage();
                msg.what = MSG_ERROR;
                if (!isHasResult) {
                    msg.obj = "必须操作完所有措施项！";
                } else if (isOnlyOneResult) {
                    msg.obj = "措施结果不能全部为不适应";
                } else if (!isFinishedEdit) {
                    msg.obj = "措施项为“是”的措施，必须完成编辑操作";
                }
                msg.sendToTarget();
            }


        } else {
            List<T> currentSelectedData = adapter.getCurrentSelectedValues();

            Integer status = null;
            if (action == IEventType.BOTTOM_SELECT_CHECKED) { // 选中
                status = 1;
            } else if (action == IEventType.BOTTOM_UNSELECT_CHECKED) { // 未选中
                status = 0;
            } else if (action == IEventType.BOTTOM_CIRCLE_CHECKED) { // 不适用
                status = 2;
            }

            if (currentSelectedData == null || currentSelectedData.size() == 0) {
                return;
            }

            for (T t : currentSelectedData) {
                t.setAttribute("confirm", status);
                t.setModified(true);
            }
            adapter.modifyCurrentValues(currentSelectedData);


        }
    }

    private static final int MSG_ERROR = 0x11;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            int code = msg.what;
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }

            if (code == MSG_ERROR) {
                String errorDesc = (String) msg.obj;
                ToastUtils.imgToast(getActivity(),
                        R.drawable.hd_hse_common_msg_wrong, errorDesc);
            }
        }
    };

    /**
     * 获取措施对应的未审批风险的tabname
     *
     * @param rmtConfMIntfcList
     * @param mRmtConfMIntfc
     * @return 如果为空，相应的风险已经审批
     * @throws HDException
     */
    private String getNotApproveTabName(List<RmtConfMIntfc> rmtConfMIntfcList, RmtConfMIntfc mRmtConfMIntfc) throws HDException {
        String tabName = null;
        if (rmtConfMIntfcList == null)
            throw new HDException("错误，页面配置集合不能为空");
        String mWork_type = mRmtConfMIntfc.getWork_type();

        for (int i = 0; i < rmtConfMIntfcList.size(); i++) {
            RmtConfMIntfc rmtConfMIntfc = rmtConfMIntfcList.get(i);
            if (rmtConfMIntfc.getTab_type() == IConfigEncoding.TEMPERATURE_PRESSURE_TYPE) {
                //rmtConfMIntfcList.remove(i);
                //i--;
                continue;
            }
            String work_type = rmtConfMIntfc.getWork_type();
            int tab_type = rmtConfMIntfc.getTab_type();
            if (mWork_type != null && work_type != null) {
                if (tab_type == IConfigEncoding.HARM_TYPE && mWork_type.equals(work_type)) {
                    if (!rmtConfMIntfc.isApprove()) {
                        tabName = rmtConfMIntfc.getTab_name();
                        if (tabName == null || "".equals(tabName))
                            tabName = "风险";
                    }
                    break;
                }
            } else {
                throw new HDException("错误，work_type不能为空");
            }
        }
        return tabName;
    }

}
