package com.hd.hse.common.module.phone.ui.module.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hd.hse.common.component.phone.custom.ShowPopWindowDialog;
import com.hd.hse.common.component.phone.event.IEventType;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.entity.SuperEntity;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.custom.PopWinButton;
import com.hd.hse.common.module.phone.custom.ZYPListView;
import com.hd.hse.common.module.phone.custom.ZYPOperatePopWindow;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;
import com.hd.hse.entity.workorder.WorkOrder;

import java.util.List;

/**
 * ClassName: BaseTaskListActivity (在线 任务列表)<br/>
 * date: 2015年6月10日 <br/>
 *
 * @author xuxinwen
 */
public abstract class BaseTaskListActivity extends BaseFrameActivity {

    // private final String zystarttimeKey = "zystarttime";
    /**
     * ZYPListView(显示列表的控件).
     */
    private ZYPListView vZYPList;
    private ZYPOperatePopWindow popWindow;
    protected ImageView imageView;
    public RelativeLayout relativeLayout;
    protected LinearLayout authorizelistTab;
    protected TextView authorizelistZysq;
    protected TextView authorizelistSqjl;
    // popWindow监听接口
    public IEventListener iEventLsn;

    /**
     * dataList:TODO(数据集).
     */
    private List<SuperEntity> dataList = null;

    private List<SuperEntity> data = null;
    /**
     * searchStr:TODO(查询描述).
     */
    private String searchStr = "";

    /**
     * 作业票详情页面
     */
    private ShowPopWindowDialog mAlertDialog;

    private String funcCode;

    /**
     * getDataList:(获取数据集). <br/>
     * date: 2014年10月13日 <br/>
     *
     * @return
     * @author zhulei
     */
    public final List<SuperEntity> getDataList() {
        return dataList;
    }


    protected void setAdapter(ListAdapter adapter) {
        vZYPList.setAdapter(adapter);
        vZYPList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BaseTaskListActivity.this.onItemClick(data.get(position));
            }
        });
    }

    protected void setData(List<SuperEntity> dataList) {
        this.data = dataList;
    }

    protected void onItemClick(SuperEntity superEntity) {

    }

    /**
     * 提供给使用者在获取到数据后设置数据的方法。 setDate:(). <br/>
     * date: 2015年6月3日 <br/>
     *
     * @param data
     * @author xuxinwen
     */

    public final void setDataList(List<SuperEntity> dataList,String functioncode) {
        this.dataList = dataList;
        this.funcCode=functioncode;
        // TO—DO
        if (dataList == null)
            return;
        // 数据排序
        setDataOrder();
        vZYPList.setData(dataList,funcCode,searchStr);
    }

    /**
     * resId:TODO(详细列表页中的资源文件id).
     */
    private int[] resId = {
            R.array.hd_hse_common_component_dytable_worktask_desc,
            R.array.hd_hse_common_component_dytable_worktask_num};

    /**
     * setResId:(设置详细信息显示的字段和描述). <br/>
     * date: 2014年10月13日 <br/>
     *
     * @param resId
     * @author zhulei
     */
    public final void setResId(int[] resId) {
        this.resId = resId;
    }

    /**
     * getSearchStr:(获取查询描述). <br/>
     * date: 2014年10月13日 <br/>
     *
     * @return
     * @author zhulei
     */
    public final String getSearchStr() {
        return searchStr;
    }

    /**
     * setSearchStr:(设置查询描述). <br/>
     * date: 2014年10月13日 <br/>
     *
     * @param searchStr
     * @author zhulei
     */
    public final void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    // /**
    // * iEventListener:TODO(设置作业票列表的单机事件).
    // */
    // public void setItemClickListener(
    // OnZYPItemClickListener mZYPItemClickListener) {
    // vZYPList.setOnZYPItemClickListener(mZYPItemClickListener);// 实现跳转
    // }
    //

    /**
     * 设置popwindow的监听
     *
     * @param iEventLsn
     */
    public void setiEventLsn(IEventListener iEventLsn) {
        this.iEventLsn = iEventLsn;
        // vZYPList.setOnEventListener(iEventLsn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化控件
        initView();
        // 初始化数据
        initData();
    }

    protected void initView() {
        setContentView(R.layout.hd_hse_common_module_task_list);
        vZYPList = (ZYPListView) findViewById(R.id.hd_hse_common_module_list);
        imageView = (ImageView) findViewById(R.id.authorizelist_add);
        authorizelistTab = (LinearLayout) findViewById(R.id.authorizelist_tab);
        authorizelistZysq = (TextView) findViewById(R.id.authorizelist_zysq);
        authorizelistSqjl = (TextView) findViewById(R.id.authorizelist_sqjl);

        vZYPList.setEventListener(new EventListenerImpl());
    }

    /**
     * initData:(初始化数据). <br/>
     * date: 2014年10月10日 <br/>
     *
     * @author zhulei
     */
    protected void initData() {
        if (dataList == null)
            return;
        // 数据排序
        setDataOrder();
        vZYPList.setData(dataList,null,null);
    }

    /**
     * setDataOrder:(设置数据排序). <br/>
     * date: 2014年10月31日 <br/>
     *
     * @author zhulei
     */
    private void setDataOrder() {

    }

    private PopWinButton[] popWinButton;

    public void initPopWindows(final PopWinButton[] mPopWinButton,
                               IEventListener iEventLsn) {
        // this.mPopWinButton = mPopWinButton;
        popWindow = new ZYPOperatePopWindow(this);
        popWindow.setEventListener(iEventLsn);
        popWinButton = mPopWinButton;
    }

    /**
     * showNavPopWindows:(显示弹出框popwindows). <br/>
     * date: 2015年3月10日 <br/>
     *
     * @author lxf
     */
    public void showNavPopWindows(WorkOrder workOrder, View anchorView,
                                  int pointerHorizontalPosition) {
        if (popWindow != null) {
            popWindow.show(anchorView, 0, pointerHorizontalPosition,
                    popWinButton, workOrder);
        }
    }

    public void popWindowsMiss() {
        if (popWindow != null && popWindow.isShowing()) {
            popWindow.dimiss();
        }
    }

    /**
     * TODO 长按作业票图标显示作业票详细信息 showWorkOrderPopupWin:(). <br/>
     * date: 2015年3月2日 <br/>
     *
     * @param workOrder
     * @author wenlin
     */
    public void showWorkOrderPopupWin(List<SuperEntity> superEntity) {
        if (mAlertDialog == null) {
            mAlertDialog = new ShowPopWindowDialog(this,
                    R.style.workorder_dialog);
        }
        mAlertDialog.setDataList(superEntity);

        mAlertDialog.show();
        WindowManager.LayoutParams params = mAlertDialog.getWindow()
                .getAttributes();
        params.height = 800;
        mAlertDialog.getWindow().setAttributes(params);
    }

    private class EventListenerImpl implements IEventListener {
        @Override
        public void eventProcess(int arg0, Object... arg1) throws HDException {
            switch (arg0) {
                case IEventType.WORK_LIST_CLICK:
                    onWorkListItemClick(arg1[0]);
                    break;

                case IEventType.WORK_LIST_LONG_CLICK:
                    onWorkListItemLongClick(arg1[0]);
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * onWorkListItemClick:(). <br/>
     * date: 2015年6月3日 <br/>
     *
     * @author xuxinwen
     */
    abstract protected void onWorkListItemClick(Object workTask);

    /**
     * onWorkListItemLongClick:(). <br/>
     * date: 2015年6月3日 <br/>
     *
     * @author xuxinwen
     */
    abstract protected void onWorkListItemLongClick(Object workTask);


}
