package com.hd.hse.main.phone.ui.activity.main;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.custom.PaintView;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.component.phone.util.Base64Util;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;
import com.hd.hse.entity.sign.ImgStringVo;
import com.hd.hse.main.phone.R;

import java.io.IOException;

import retrofit2.Call;

/**
 * created by yangning on 2017/8/25 14:36.
 */

public class SignActivity extends BaseFrameActivity implements IEventListener, View.OnClickListener {
    private RelativeLayout reParent;
    private PaintView paintView;
    private Button btClean;
    private Button btUp;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmt_signactivity_layout);
        initActionbar();
        initView();
        getSign(LoginUserRecord.getInstance().getUser().getUserid());
        /*String path = SystemProperty.getSystemProperty().getRootpath() + File.separator + "img.png";
        final Bitmap bitmap = BitmapFactory.decodeFile(path);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                paintView.paintBitmap(bitmap);
            }
        }, 3000);*/

    }

    private void initView() {
        paintView = (PaintView) findViewById(R.id.rmt_signactivity_layout_paintview);
        btClean = (Button) findViewById(R.id.rmt_signactivity_layout_bt_clear);
        btUp = (Button) findViewById(R.id.rmt_signactivity_layout_bt_up);
        reParent = (RelativeLayout) findViewById(R.id.rmt_signactivity_layout_re_parent);
        btClean.setOnClickListener(this);
        btUp.setOnClickListener(this);

    }

    private void initActionbar() {
        // ?????????ActionBar
        setCustomActionBar(false, this, new String[]{IActionBar.TV_BACK,
                IActionBar.TV_TITLE});
        // ?????????????????????
        setActionBartitleContent("????????????", false);
        // ?????????????????????
    }

    @Override
    public void eventProcess(int eventType, Object... objects) throws HDException {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rmt_signactivity_layout_bt_clear) {
            paintView.redo();
        } else if (id == R.id.rmt_signactivity_layout_bt_up) {
            if (paintView.hasDraw())
                try {
                    ImgStringVo imgStringVo = new ImgStringVo();
                    imgStringVo.setImgStr(Base64Util.bitmapToBase64(paintView.getBitmap()));
                    upSign(imgStringVo, LoginUserRecord.getInstance().getUser().getUserid());
                } catch (IOException e) {
                    ToastUtil.longToast(getApplicationContext(), "??????????????????????????????");
                    e.printStackTrace();
                }
            else
                ToastUtil.longToast(getApplicationContext(), "????????????????????????");
        }
    }

    /**
     * ??????????????????
     */
    private void upSign(ImgStringVo pic, long userid) {
        if (dialog == null) {
            dialog = new ProgressDialog(SignActivity.this);
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.setMessage("????????????????????????");
        if (dialog != null && !dialog.isShowing())
            dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.upSign(pic, userid);
        HttpBusiness.action(SignActivity.this, false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                ToastUtils.toast(getApplicationContext(), "????????????????????????");
                finish();
            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                ToastUtils.toast(getApplicationContext(), msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                ToastUtils.toast(getApplicationContext(), "????????????????????????");
            }
        });
    }

    /**
     * ??????????????????
     */
    private void getSign(long userid) {
        if (dialog == null) {
            dialog = new ProgressDialog(SignActivity.this);
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.setMessage("?????????????????????????????????");
        if (dialog != null && !dialog.isShowing())
            dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        // ????????????????????????
        Call<ResultData<ImgStringVo>> call = rmtInterface.getSign(userid);
        HttpBusiness.action(SignActivity.this, false, call, new HttpCallBack<ImgStringVo>() {
            @Override
            public void success(ImgStringVo imgStringVo) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                if (imgStringVo == null || imgStringVo.getImgStr() == null || imgStringVo.getImgStr().length() == 0) {
                    ToastUtils.toast(getApplicationContext(), "????????????????????????");
                } else {
                    //?????????bitmap
                    final Bitmap bitmap = Base64Util.base64ToBitmap(imgStringVo.getImgStr());


                    paintView.paintBitmap(bitmap);
                }
                reParent.setVisibility(View.VISIBLE);

            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                reParent.setVisibility(View.VISIBLE);
                ToastUtils.toast(getApplicationContext(), msg);
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                reParent.setVisibility(View.VISIBLE);
                ToastUtils.toast(getApplicationContext(), "???????????????????????????");
            }
        });
    }
}
