package rmt.leaderappr.phone.app.activity;

import android.app.Fragment;
import android.os.Bundle;

import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.custom.HSEActionBar;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.constant.IConfigEncoding;
import com.hd.hse.entity.conf.RmtConfMIntfc;
import com.hd.hse.entity.resultdata.RmtWorkSubtaskResltData;

import java.util.List;

import retrofit2.Call;
import rmt.leaderappr.phone.app.fragment.LeaderMeasureFragment;
import rmt.leaderappr.phone.app.fragment.MeasureHarmFragment;

/**
 * created by yangning on 2018/3/20 14:03.
 */

public class PermitActivity extends FrameworkActivity {
    private long permitId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        permitId = getIntent().getLongExtra(PERMITID_KEY, 0);
        special();
        super.onCreate(savedInstanceState);
    }

    /**
     * special:(个性化设置). <br/>
     * date: 2015年6月8日 <br/>
     *
     * @author zhaofeng
     */
    private void special() {
        // setActionTitle("");
        setPopupItemsDesc(new String[]{});
    }

    @Override
    public Fragment configFragmentByPadType(List<RmtConfMIntfc> listConfig, RmtConfMIntfc config) {
        // TODO Auto-generated method stub
        BaseFragment fragment = null;
        switch (config.getTab_type()) {
            case IConfigEncoding.HARM_TYPE:
                //危害
                fragment = new MeasureHarmFragment();
                break;
            case IConfigEncoding.MEASURE_TYPE:
                //措施
                fragment = new LeaderMeasureFragment();// new MeasureHarmFragment();// MeasureFragment<>();
                break;
            /*case IConfigEncoding.PROMISE_TYPE:
                //承诺信息类别
                fragment = new PromiseInfoFragment<>();
                break;
            case IConfigEncoding.TEMPELE_TYPE:
                //临时用电
                fragment = new TempEleFragment();
                break;
            case IConfigEncoding.Gas_Detect_TYPE:
                //气体检测
                fragment = new GasDetectInfoFragment();
                break;
            case IConfigEncoding.DiaoZhuang_Detail_TYPE:
                //吊装信息
                fragment = new DiaoZhuangFragment();
                break;
            case IConfigEncoding.TEMPERATURE_PRESSURE_TYPE:
                //设备温度压力
                fragment = new TemPressureFragment();
                break;*/
            default:
                break;
        }
        fragment.setPDAWorkOrderInfoConfig(config);
        fragment.setConfigList(listConfig);
        //lxf设置模块编码
        fragment.setModuleCode(function);
        return fragment;
    }

    @Override
    protected void setMenuModel() {
        hseAB = getHseAB(popupItems, actionBarListener);
        hseAB.setTitleContent(workTask.getDescription() != null ? workTask.getDescription()
                : "作业任务", false);
    }

    protected HSEActionBar getHseAB(String[] popupItems, IEventListener actionBarListener) {
        HSEActionBar hseActionBar;
        hseActionBar = new HSEActionBar(this, actionBarListener, new String[]{
                IActionBar.TV_BACK, IActionBar.TV_TITLE});
        return hseActionBar;
    }

    @Override
    protected Call<ResultData<List<RmtWorkSubtaskResltData>>> getCall(RmtInterface rmtInterface) {

        return rmtInterface.getPermit(LoginUserRecord.getInstance().getUser().getUserid(), workTask.getWork_subtask_id(), permitId, function);

    }
}
