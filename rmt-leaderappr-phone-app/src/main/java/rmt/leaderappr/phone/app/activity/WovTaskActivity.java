package rmt.leaderappr.phone.app.activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.custom.HSEActionBar;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.constant.IConfigEncoding;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.entity.workorder.RmtWorkSubtask;

import java.util.ArrayList;
import java.util.List;

import rmt.leaderappr.phone.app.fragment.AssignRecordFragment;
import rmt.leaderappr.phone.app.fragment.WovTimeLineFragmentNew;

/**
 * ClassName: WovTaskActivity (任务浏览明细页)<br/>
 * date: 2015年8月7日 <br/>
 *
 * @author lxf
 */
public class WovTaskActivity extends FrameworkActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        special();
        super.onCreate(savedInstanceState);

    }

    @Override
    public void queryData() {
        // 查询数据
        // 初始化数据
        initlizeCompant();
    }

    /**
     * special:(个性化设置). <br/>
     * date: 2015年6月8日 <br/>
     *
     * @author zhaofeng
     */
    private void special() {
        // setActionTitle("");
        // setPopupItemsDesc(new String[] {IActionBar.ITEM_PHOTOMANAGER });
    }


    @Override
    public List<RmtConfMIntfc> SpecialConfigList(
            List<RmtConfMIntfc> list) {

        if (list == null) {
            List<RmtConfMIntfc> mergeList = new ArrayList<RmtConfMIntfc>();

            // 造一个
            RmtConfMIntfc tempPad = new RmtConfMIntfc();
            // 设置事件周类别
            tempPad.setTab_type(IConfigEncoding.TIMELINE_TYPE);
            tempPad.setM_intfc_id(146555151);
            tempPad.setTab_name("当前进度");
            mergeList.add(tempPad);

            //造一个交接班记录
            RmtConfMIntfc tempPad2 = new RmtConfMIntfc();
            // 设置事件周类别
            tempPad2.setTab_type(IConfigEncoding.ASSIGN_RECORD_TYPE);
            tempPad.setM_intfc_id(16555581);
            tempPad2.setTab_name("交接班记录");
            mergeList.add(tempPad2);
            return mergeList;
        }
        return list;
    }


    @Override
    public Fragment configFragmentByPadType(List<RmtConfMIntfc> listConfig, RmtConfMIntfc config) {
        // TODO Auto-generated method stub
        BaseFragment fragment = null;
        switch ((int) config.getTab_type()) {
            case IConfigEncoding.TIMELINE_TYPE:
                // 事件轴的
                fragment = new WovTimeLineFragmentNew();
                ((WovTimeLineFragmentNew) fragment)
                        .setItemWorkTask((RmtWorkSubtask) itemWorkTask);
                ((WovTimeLineFragmentNew) fragment).setWorkTask(workTask);
                break;
            case IConfigEncoding.ASSIGN_RECORD_TYPE:
                fragment = new AssignRecordFragment();
                ((AssignRecordFragment) fragment).setWorkTask(workTask);
                break;
            default:
                break;
        }

        if (fragment != null) {
            fragment.setPDAWorkOrderInfoConfig(config);
            // lxf设置模块编码
            fragment.setModuleCode(function);
        }
        return fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (getCurrentFocus() != null
                    && getCurrentFocus().getWindowToken() != null) {

                manager.hideSoftInputFromWindow(getCurrentFocus()
                                .getWindowToken(),

                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }

        return super.onTouchEvent(event);

    }

    @Override
    public void customFinish() {
        // TODO Auto-generated method stub
        setResult(RESULT_CANCELED);
    }



    @Override
    protected HSEActionBar getHseAB(String[] popupItems, IEventListener actionBarListener) {
        HSEActionBar hseActionBar;
        hseActionBar = new HSEActionBar(this, actionBarListener, new String[]{
                IActionBar.TV_BACK, IActionBar.TV_TITLE, IActionBar.BT_CANCLE});
        return hseActionBar;
    }

}
