package rmt.question.phone.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.entity.qstn.RmtQstnList;
import com.hd.hse.entity.resultdata.RmtQstnListResltData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import rmt.question.phone.app.R;
import rmt.question.phone.app.activity.QuestionListActivity;
import rmt.question.phone.app.activity.QuestionRegistrationActivity;
import rmt.question.phone.app.adapter.QstnListItemAdapter;

/**
 * created by yangning on 2017/6/1 17:15.
 * 已上传问题列表展示
 */

public class QuestionListUploadedFrag extends Fragment {
    private View view;
    private Context context;
    private ListView lv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<RmtQstnList> rmtQstnLists;
    private QstnListItemAdapter qstnListItemAdapter;
    public static final int RESULTCODE = 0x62;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rmt_question_list_fragment, null);
        lv = (ListView) view.findViewById(R.id.rmt_question_list_fragment_lv);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.rmt_question_list_fragment_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.hd_hse_common_title_back));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNetData();
            }
        });
        initData();
        swipeRefreshLayout.setRefreshing(true);
        getNetData();
        return view;
    }

    private void initData() {
        rmtQstnLists = new ArrayList<RmtQstnList>();
        qstnListItemAdapter = new QstnListItemAdapter(getContext(), rmtQstnLists);
        lv.setAdapter(qstnListItemAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), QuestionRegistrationActivity.class);
                intent.putExtra(QuestionListActivity.class.getName(), rmtQstnLists.get(position).getQstn_list_id());
                startActivityForResult(intent, 0);
            }
        });
    }

    public void getNetData() {
       /* final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("加载数据中。。。");
        dialog.show();*/
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // 获取问题清单列表
        Call<ResultData<RmtQstnListResltData>> call = rmtInterface.getQstnList();
        HttpBusiness.action(getActivity(), false, call, new HttpCallBack<RmtQstnListResltData>() {
            @Override
            public void success(RmtQstnListResltData rmtQstnListResltData) {
                //dialog.dismiss();
                if (swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
                rmtQstnLists.clear();
                List<RmtQstnListResltData.MainvoBean> mainvo = rmtQstnListResltData.getMainvo();
                if (mainvo != null && mainvo.size() > 0) {
                    for (RmtQstnListResltData.MainvoBean m : mainvo) {
                        if (m.getHeadVO() != null && m.getHeadVO().getRMT_WORK_QSTN_LIST_M() != null) {
                            rmtQstnLists.add(m.getHeadVO().getRMT_WORK_QSTN_LIST_M());
                        }
                    }
                }
                qstnListItemAdapter.notifyDataSetChanged();
                if (rmtQstnLists.size() <= 0)
                    ToastUtils.toast(getActivity(), "没有获取到问题清单");


            }

            @Override
            public void warning(String msg) {
                if (swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
                ToastUtils.toast(getActivity(), msg);
            }

            @Override
            public void error(Throwable t) {
                if (swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
                ToastUtils.toast(getActivity(), "获取问题登记列表失败");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULTCODE) {
            swipeRefreshLayout.setRefreshing(true);
            getNetData();
        }

    }
}
