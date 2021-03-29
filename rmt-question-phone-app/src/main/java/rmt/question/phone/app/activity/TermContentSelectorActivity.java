package rmt.question.phone.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.entity.workorder.TermContent;

import java.util.List;

import retrofit2.Call;

/**
 * created by yangning on 2019/10/31 15:35.
 */
public class TermContentSelectorActivity extends TermSelectorActivity {
    public static final String KEY_TERM_TYPE = "code";
    public static final String KEY_TERMCONTENT = "termcontent";
    private String termType = null;
    private List<TermContent> termContents;

    public static void StartActivity(Activity activity, String termType) {
        Intent intent = new Intent(activity, TermContentSelectorActivity.class);
        intent.putExtra(KEY_TERM_TYPE, termType);
        activity.startActivityForResult(intent, 0);
    }


    @Override
    protected void initView() {
        super.initView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(KEY_TERMCONTENT, termContents.get(position));
                setResult(QuestionRegistrationActivity.TERM_CONTENT_RESULT_CODE, intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        termType = getIntent().getStringExtra(KEY_TERM_TYPE);
        dialog.setMessage("加载中...");
        dialog.show();
        RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<List<TermContent>>> call = rmtInterface.getViolationterm(termType);
        HttpBusiness.action(this, false, call, new HttpCallBack<List<TermContent>>() {
            @Override
            public void success(List<TermContent> termContents) {
                TermContentSelectorActivity.this.termContents=termContents;
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                if (!isDestroyed()) {
                    setdata(termContents);
                }

            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                if (!isDestroyed()) {
                    Toast.makeText(TermContentSelectorActivity.this, msg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (!isDestroyed()) {
                    Toast.makeText(TermContentSelectorActivity.this, "请求数据失败", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected String getActivityTitle() {
        return "条款内容";
    }
}
