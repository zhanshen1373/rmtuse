package rmt.question.phone.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.entity.workorder.TermType;

import java.util.List;

import retrofit2.Call;

/**
 * 条款类型选择
 * created by yangning on 2019/10/31 15:20.
 */
public class TermTypeSelectorActivity extends TermSelectorActivity {
    private List<TermType> termTypes;
    public static final String KEY_TERMTYPE = "termType";

    public static void StartActivity(Activity activity) {
        Intent intent = new Intent(activity, TermTypeSelectorActivity.class);

        activity.startActivityForResult(intent, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(KEY_TERMTYPE, termTypes.get(position));
                setResult(QuestionRegistrationActivity.TERM_TYPE_RESULT_CODE, intent);
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        dialog.setMessage("加载中...");
        dialog.show();
        RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<List<TermType>>> call = rmtInterface.getTermType();
        HttpBusiness.action(this, false, call, new HttpCallBack<List<TermType>>() {
            @Override
            public void success(List<TermType> termTypes) {
                TermTypeSelectorActivity.this.termTypes = termTypes;
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                if (!isDestroyed()) {
                    setdata(termTypes);
                }

            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                if (!isDestroyed()) {
                    Toast.makeText(TermTypeSelectorActivity.this, msg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (!isDestroyed()) {
                    Toast.makeText(TermTypeSelectorActivity.this, "请求数据失败", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected String getActivityTitle() {
        return "条款类型";
    }


}
