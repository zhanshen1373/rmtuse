package com.hd.hse.common.module.branch.ui.model.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.hayden.hap.common.utils.ToastUtil;
import com.hayden.hap.plugin.android.qr_code.QrCode;
import com.hd.hse.common.component.phone.constant.IActionBar;
import com.hd.hse.common.component.phone.listener.IEventListener;
import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.ui.activity.BaseFrameActivity;
import com.hd.hse.entity.workorder.RmtJjbData;

/**
 * created by yangning on 2017/12/19 13:39.
 */

public class CreateQrCodeActivity extends BaseFrameActivity implements IEventListener {
    private ImageView imgQrCode;
    public static final String RMTJJBDATA = "rmtJjbData";
    private RmtJjbData rmtJjbData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmt_create_qrcode_activity_layout);
        initView();
        initActionbar();
        initData();
    }

    private void initData() {
        if (getIntent() != null && getIntent().getSerializableExtra(RMTJJBDATA) != null && (getIntent().getSerializableExtra(RMTJJBDATA)) instanceof RmtJjbData) {
            rmtJjbData = (RmtJjbData) getIntent().getSerializableExtra(RMTJJBDATA);
            Bitmap bitmap = QrCode.createQrCode(new Gson().toJson(rmtJjbData), getQrCodeWidth(), getQrCodeWidth());
            imgQrCode.setImageBitmap(bitmap);
        } else {
            ToastUtil.toast(getApplicationContext(), "生成交接班二维码失败");
            finish();
        }
    }

    private void initView() {
        imgQrCode = (ImageView) findViewById(R.id.rmt_create_qrcode_activity_layout_img);
    }

    private void initActionbar() {
        // 初始化ActionBar
        setCustomActionBar(false, this, new String[]{IActionBar.TV_BACK, IActionBar.TV_TITLE});
        // 设置导航栏标题
        setActionBartitleContent("交接班二维码", false);
    }

    @Override
    public void eventProcess(int eventType, Object... objects) throws HDException {

    }


    private int getQrCodeWidth() {
        int qrCodewidth = 0;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        int width = screenWidth < screenHeight ? screenWidth : screenHeight;
        qrCodewidth = width / 3 * 2;
        return qrCodewidth;
    }
}
