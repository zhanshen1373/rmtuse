/**
 * Project Name:incubator
 * File Name:EndListAdapter.java
 * Package Name:com.example.incubator
 * Date:2015��6��10��
 * Copyright (c) 2015, longgang@ushayden.com All Rights Reserved.
 */

package hd.hse.end.phone.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.custom.IconForZYPClass;
import com.hd.hse.common.module.phone.ui.custom.AlertDialogListView;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.entity.dict.RmtDict;
import com.hd.hse.entity.resultdata.RmtWorkEnd;
import com.hd.hse.entity.workorder.RmtWorkPermit;

import org.apache.commons.lang.StringUtils;
import org.hse.end.phone.app.R;

import java.util.List;

/**
 * ClassName:EndListAdapter ().<br/>
 * Date: 2015年6月10日 <br/>
 *
 * @author wen
 * @see
 */
public class EndListAdapter extends BaseAdapter {

    private Context mContext;

    private List<RmtConfMIntfc> mConfigList;
    private List<RmtWorkPermit> workOrders;
    private final String canCloseStatus = "appr";

    private RmtWorkEnd rmtWorkEnd;
    private boolean[] mSelectState;
    private boolean isgzfzr;
    private boolean isgzxkr;
    /**
     * 结束主任务
     */
    private boolean isEndTask;

    public EndListAdapter(Context context,
                          List<RmtConfMIntfc> configList,
                          List<RmtWorkPermit> _workOrders, boolean isEndTask,RmtWorkEnd time) {
        mContext = context;
        mConfigList = configList;
        workOrders = _workOrders;
        mSelectState = new boolean[mConfigList.size()];
        this.isEndTask = isEndTask;
        this.rmtWorkEnd = time;

        init();
    }

    private void init() {
    }

    @Override
    public int getCount() {
        if (mConfigList != null) {
            return mConfigList.size();
        }
        return 0;
    }

    /**
     * 获得选中状态。 getSelectState:(). <br/>
     * date: 2015年6月11日 <br/>
     *
     * @return
     * @author xuxinwen
     */
    public boolean[] getSelectState() {
        return mSelectState;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View _returnView = null;

        ViewHolder _holder = new ViewHolder();

        _returnView = View.inflate(mContext,
                R.layout.hd_hse_end_phone_end_list_item, null);

        _holder.icon = (ImageView) _returnView
                .findViewById(R.id.hd_hse_end_phone_icon_iv);
        _holder.zypType = (TextView) _returnView
                .findViewById(R.id.hd_hse_end_phone_name_tv);
        _holder.endReasonValue = (TextView) _returnView
                .findViewById(R.id.hd_hse_end_phone_end_reason_value_tv);
        _holder.endReasonSelectBtn = (ImageView) _returnView
                .findViewById(R.id.hd_hse_end_phone_end_reason_btn);
        _holder.endDes = (EditText) _returnView
                .findViewById(R.id.hd_hse_end_phone_des_et);
        _holder.selectBtn = _returnView
                .findViewById(R.id.hd_hse_end_phone_select);
        _holder.selectIcon = _returnView
                .findViewById(R.id.hd_hse_end_phone_select_icon);

        _returnView.setTag(_holder);

        RmtConfMIntfc _config = mConfigList.get(position);

        _holder.icon.setImageResource(IconForZYPClass
                .getIconIDByZYPClass(_config.getWork_type()));
        if (isEndTask)
            _holder.zypType.setText("主任务");
        else
            _holder.zypType.setText(IconForZYPClass.getZYPNameByZYPClass(_config
                    .getWork_type()));

        List<RmtDict> _listDomain = _config.getDictList();
        RmtWorkPermit currentWork = getCurrentSuperInfo(_config.getWork_type());

        String curentTime="";
        RmtWorkEnd.DictvosBean dictvos=null;
        if (currentWork != null) {
            // 说明
            _holder.endDes.setText(currentWork.getStop_comment() == null ? ""
                    : currentWork.getStop_comment());
        }
        if (rmtWorkEnd != null) {
            curentTime = rmtWorkEnd.getData_ext().getCuur_time();
            dictvos = rmtWorkEnd.getDictvos();
        }

        if (dictvos != null) {
            List<RmtWorkEnd.DictvosBean.RmtApprNodeBean> rmt_appr_node = dictvos.getRmt_appr_node();
            if (rmt_appr_node != null && rmt_appr_node.size() > 0) {
                for (int i = 0; i < rmt_appr_node.size(); i++) {
                    if (rmt_appr_node.get(i).getName().equals("工作负责人(关闭)")) {
                        if (rmt_appr_node.get(i).getDictdataid() == _config.getApprAuthVOList().get(0).getAppr_node_id()) {
                            isgzfzr = true;
                        } else isgzfzr = false;
                    }
                    if (rmt_appr_node.get(i).getName().equals("工作许可人(关闭)")) {
                        if (rmt_appr_node.get(i).getDictdataid() == _config.getApprAuthVOList().get(0).getAppr_node_id()) {
                            isgzxkr = true;
                        } else isgzxkr = false;
                    }
                }
            }
        }

        if (IconForZYPClass.getZYPNameByZYPClass(_config.getWork_type()).
                equalsIgnoreCase("电气第二种工作票") && (isgzxkr || isgzfzr)) {


            _holder.endDes.setText("全部工作与" + curentTime + "结束，工作人员已全部撤离，材料工具已清理完毕");
        }
        if (IconForZYPClass.getZYPNameByZYPClass(_config.getWork_type()).
                equalsIgnoreCase("电气一种工作票") && isgzfzr) {
            _holder.endDes.setText("全部工作与" + curentTime + "结束，工作人员已全部撤离，材料工具已清理完毕");



        }
        if (IconForZYPClass.getZYPNameByZYPClass(_config.getWork_type()).
                equalsIgnoreCase("电气一种工作票") && isgzxkr) {
            _holder.endDes.setText(Html.fromHtml("全部工作与" + curentTime + "结束，工作人员已全部撤离，材料工具已清理完毕\n" + "临时遮拦、锁具、标签已拆除，常设格栏已恢复。未拆除或未拉开的接地线编号<font color=\"red\">()</font>等" +
                    "共<font color=\"red\">()</font>组、接地刀闸（小车）共<font color=\"red\">()</font>副（台），工作已结束，汇报。"));


        }



        if (_listDomain == null || _listDomain.size() == 0) {
            _holder.endReasonSelectBtn.setVisibility(View.INVISIBLE);
        } else {
            _holder.endReasonSelectBtn
                    .setOnClickListener(new MyClickListener(_config
                            .getDictList(), _holder.endReasonValue, _holder.selectIcon, position));

            if (currentWork != null) {
                RmtDict currentDomain = selectCurrentInfo(
                        currentWork.getStop_reason(), _config.getDictList());
                // 原因
                _holder.endReasonValue.setText(currentDomain == null ? ""
                        : currentDomain.getName());
            }
        }
        // 设置图标监听事件
        _holder.selectBtn.setOnClickListener(new

                SelectListener(position,
                _holder.selectIcon));

        return _returnView;
    }

    /**
     * selectCurrentInfo:(). <br/>
     * date: 2015年8月4日 <br/>
     *
     * @param type
     * @param date
     * @author Administrator
     */
    private RmtDict selectCurrentInfo(String type, List<RmtDict> date) {
        RmtDict currentDomain = null;
        if (!StringUtils.isEmpty(type)) {
            for (RmtDict domain : date) {
                if (type.equalsIgnoreCase(domain.getCode())) {
                    domain.setIsselected(1);
                    currentDomain = domain;
                    break;
                }
            }
        }
        return currentDomain;
    }

    /**
     * getCurrentSuperInfo:(获取当前票的信息). <br/>
     * date: 2015年8月4日 <br/>
     *
     * @param zypclass
     * @return
     * @author lxf
     */
    private RmtWorkPermit getCurrentSuperInfo(String zypclass) {
        RmtWorkPermit superEntity = null;
        if (workOrders != null) {
            for (SuperEntity sup : workOrders) {
                if (((RmtWorkPermit) sup).getWork_type().equalsIgnoreCase(zypclass)) {
                    superEntity = (RmtWorkPermit) sup;
                    break;
                }
            }
        }
        return superEntity;
    }

    /**
     * ClassName: SelectListener (监听事件)<br/>
     * date: 2015年6月19日 <br/>
     *
     * @author xunxinwen
     * @version EndListAdapter
     */
    private class SelectListener implements OnClickListener {

        private int mPosition;
        private View mIcon;

        public SelectListener(int position, View icon) {
            mPosition = position;
            mIcon = icon;
        }

        // 设置是否显示选中状态
        @Override
        public void onClick(View v) {
            if (mSelectState[mPosition]) {
                mIcon.setVisibility(View.INVISIBLE);
                mSelectState[mPosition] = false;
            } else if (isEndTask) {
                mIcon.setVisibility(View.VISIBLE);
                mSelectState[mPosition] = true;
            } else {
                // 未选中，先要判断是否可勾选
                RmtConfMIntfc _config = mConfigList.get(mPosition);
                RmtWorkPermit currentWork = getCurrentSuperInfo(_config.getWork_type());
                if (currentWork.getStatus() != null && currentWork.getStatus().equalsIgnoreCase(canCloseStatus)) {
                    mIcon.setVisibility(View.VISIBLE);
                    mSelectState[mPosition] = true;
                } else {
                    ToastUtils.toast(mContext, "该作业票状态不是作业中，不可关闭");
                }

            }
        }
    }

    private class MyClickListener implements OnClickListener {
        private List<RmtDict> mDomain;
        private TextView tvValue;
        private View icon;
        private int mPosition;

        public MyClickListener(List<RmtDict> domains, TextView value, View icon, int position) {
            mDomain = domains;
            tvValue = value;
            this.icon = icon;
            mPosition = position;
        }

        @Override
        public void onClick(View v) {
            // 未选中，先要判断是否可勾选
            RmtConfMIntfc _config = mConfigList.get(mPosition);
            RmtWorkPermit currentWork = getCurrentSuperInfo(_config.getWork_type());
            if (isEndTask || (currentWork.getStatus() != null && currentWork.getStatus().equalsIgnoreCase(canCloseStatus))) {
                AlertDialogListView _dialogBuilder = new AlertDialogListView(
                        mContext, mDomain, ListView.CHOICE_MODE_SINGLE);
                _dialogBuilder.setOnClickListener(new IEventListener() {
                    @Override
                    public void eventProcess(int eventType, Object... objects)
                            throws HDException {
                        if (eventType == IEventType.POPUPWINDOW_CHOICE_MULTIPLEORSINGLE) {
                            if (tvValue != null) {
                                tvValue.setText(((RmtDict) objects[0])
                                        .getName());
                                icon.setVisibility(View.VISIBLE);
                                mSelectState[mPosition] = true;
                            }
                        }
                    }
                });
            } else {
                ToastUtils.toast(mContext, "该作业票状态不是作业中，不可选择原因");
            }

        }
    }

    private class ViewHolder {
        ImageView icon;
        TextView zypType;

        TextView endReasonValue;
        ImageView endReasonSelectBtn;

        EditText endDes;

        View selectBtn;
        View selectIcon;
    }

}
