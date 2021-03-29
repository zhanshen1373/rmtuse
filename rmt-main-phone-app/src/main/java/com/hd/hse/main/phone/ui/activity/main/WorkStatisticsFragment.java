package com.hd.hse.main.phone.ui.activity.main;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.custom.ProgressDialog;
import com.hd.hse.entity.workorder.RmtMainPageSchedule;
import com.hd.hse.main.phone.R;

import java.util.List;

import retrofit2.Call;

public class WorkStatisticsFragment extends Fragment {

    private Zdyview zdyview;
    private TextView tv;
    private RmtMainPageSchedule rmtMainPageSchedule;
    private List<RmtMainPageSchedule> list;
    //一般作业数量
    private int ybwork;
    //高危作业数量
    private int gwwork;
    //作业票数量
    private int num;
    private int width;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.hd_hse_main_phone_app_layout_main_workstatisticfg, null);
        zdyview = (Zdyview) inflate.findViewById(R.id.zdy_view);

        rmtMainPageSchedule = new RmtMainPageSchedule();
        rmtMainPageSchedule.setTerritorial_unit_name("单位");
        rmtMainPageSchedule.setConvert_all_num("作业票数量");
        rmtMainPageSchedule.setConvert_gen_num("一般作业");
        rmtMainPageSchedule.setConvert_eight_type_num("高危作业");

        final ProgressDialog dialog = new ProgressDialog(getActivity(),
                "正在查询表单...");
        dialog.show();

        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // 单张票报表的接口
        Call<ResultData<List<RmtMainPageSchedule>>> call = rmtInterface.getMainPageSchedule(LoginUserRecord.getInstance().getUser().getUserid());
        HttpBusiness.action(getActivity(), false, call, new HttpCallBack<List<RmtMainPageSchedule>>() {
            @Override
            public void success(List<RmtMainPageSchedule> rmtMainPageScheduleList) {
                dialog.dismiss();

                list = rmtMainPageScheduleList;
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        RmtMainPageSchedule rmtMainPageSchedule1 = list.get(i);
                        rmtMainPageSchedule1.setTerritorial_unit_name(rmtMainPageSchedule1.getTerritorial_unit_name());
                        rmtMainPageSchedule1.setConvert_all_num(String.valueOf(rmtMainPageSchedule1.getAll_num()));
                        rmtMainPageSchedule1.setConvert_gen_num(String.valueOf(rmtMainPageSchedule1.getGen_num()));
                        rmtMainPageSchedule1.setConvert_eight_type_num(String.valueOf(rmtMainPageSchedule1.getEight_type_num()));
                        ybwork+=rmtMainPageSchedule1.getGen_num();
                        gwwork+=rmtMainPageSchedule1.getEight_type_num();
                        num+=rmtMainPageSchedule1.getAll_num();
                    }
                    list.add(0, rmtMainPageSchedule);
                    RmtMainPageSchedule last=new RmtMainPageSchedule();
                    last.setTerritorial_unit_name("合计");
                    last.setConvert_all_num(String.valueOf(num));
                    last.setConvert_gen_num(String.valueOf(ybwork));
                    last.setConvert_eight_type_num(String.valueOf(gwwork));
                    list.add(list.size(),last);
                }

                if (list != null && list.size() > 0) {
                    //行长度
                    for (int i = 0; i < list.size(); i++) {
                        //列长度
                        LinearLayout row = new LinearLayout(getActivity());
                        row.setOrientation(LinearLayout.HORIZONTAL);
                        row.setLayoutParams(new LinearLayout.LayoutParams(width, 150));
                        for (int t = 0; t < 4; t++) {
                            tv = new TextView(getActivity());
                            RmtMainPageSchedule rmtMainPageSchedule = list.get(i);
                            String content = null;
                            switch (t){
                                case 0:
                                    content=rmtMainPageSchedule.getTerritorial_unit_name();
                                    break;
                                case 1:
                                    content=rmtMainPageSchedule.getConvert_gen_num();
                                    break;
                                case 2:
                                    content=rmtMainPageSchedule.getConvert_eight_type_num();
                                    break;
                                case 3:
                                    content=rmtMainPageSchedule.getConvert_all_num();
                                    break;
                            }
                            tv.setText(content);
                            tv.setTextColor(Color.WHITE);
                            tv.setBackgroundResource(R.drawable.tvbackground);
                            tv.setFocusable(true);
                            tv.setGravity(Gravity.CENTER);
                            tv.setLayoutParams(new LinearLayout.LayoutParams(width / 4, LinearLayout.LayoutParams.MATCH_PARENT));


                            if (i > 0 && t > 0) {
                                tv.setTextColor(Color.BLUE);
                                tv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                                tv.setBackgroundResource(R.drawable.tvnobackground);
                            }
                            row.addView(tv);
                        }
                        zdyview.addView(row);
                        zdyview.initEvent();
                        zdyview.setData(list);
                    }
                }


            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtil.toast(getActivity(), msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtil.toast(getActivity(), "获取数据列表失败");
            }
        });


        zdyview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                width = zdyview.getWidth();
                zdyview.getViewTreeObserver().removeOnGlobalLayoutListener(this);


            }
        });


//        zdyview.setWillNotDraw(false);


        return inflate;
    }
}
