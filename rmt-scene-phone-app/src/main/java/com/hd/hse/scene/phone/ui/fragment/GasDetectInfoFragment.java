package com.hd.hse.scene.phone.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.hd.hse.common.component.phone.adapter.GasAdapter;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.entity.workorder.RmtWorkGasDetect;

import org.apache.log4j.Logger;
import org.hse.scene.phone.app.R;

import java.util.List;

/**
 * Created by dubojian on 2017/5/5.
 */

public class GasDetectInfoFragment extends BaseFragment {

    private static Logger logger = LogUtils.getLogger(GasDetectInfoFragment.class);

    private BaseAdapter mAdapter;
    private List<RmtWorkGasDetect.MainvoBean> historyDatas;
    private RmtWorkGasDetect  rmtWorkGasDetect;


    public GasDetectInfoFragment() {
        super(STYLE_NO_MENU);
    }

    @Override
    protected View onCreatePageView(LayoutInflater inflater) {
        View view = inflater.inflate(
                R.layout.hd_hse_scene_phone_gas_fragment, null);
        ListView lv= (ListView) view.findViewById(R.id.hd_hse_scene_phone_gas_fragment_lv);
        //mPDAWorkOrderInfoConfig
        mAdapter=new GasAdapter(lv,mPDAWorkOrderInfoConfig.getRmtWorkGasDetectResltList());
        return view;
    }

}
