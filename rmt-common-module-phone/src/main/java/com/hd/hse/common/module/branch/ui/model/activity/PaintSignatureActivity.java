/**
 * Project Name:hse-common-module-phone
 * File Name:PaintSignatureActivity.java
 * Package Name:com.hd.hse.common.module.phone.ui.activity
 * Date:2015年10月23日
 * Copyright (c) 2015, zhaofeng@ushayden.com All Rights Reserved.
 */

package com.hd.hse.common.module.branch.ui.model.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.hayden.hap.common.login.business.LoginUser;
import com.hayden.hap.common.login.business.LoginUserRecord;
import com.hayden.hap.common.utils.ToastUtil;
import com.hd.hse.common.component.phone.custom.PaintView;
import com.hd.hse.common.component.phone.util.Base64Util;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.common.module.phone.adapter.ImgAdapter;
import com.hd.hse.common.module.phone.custom.HorizontalListView;
import com.hd.hse.common.module.phone.ui.activity.PhotoPreviewActivity;
import com.hd.hse.entity.common.Image;
import com.hd.hse.entity.sys.RmtSignRecord;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName:PaintSignatureActivity ().<br/>
 * Date: 2015年10月23日 <br/>
 *
 * @author LiuYang
 * @see
 */
public class PaintSignatureActivity extends Activity implements OnClickListener {

    private PaintView paintView;
    private Button btCancle, btEnture, btClear, btSave;
    /**
     * 现场负责人
     */
    private TextView tvFzr;
    /**
     * 横向listview，手签记录
     */
    private HorizontalListView hlv;
    private ImgAdapter mImgAdapter;
    /**
     * 签字图片
     */
    private ArrayList<Image> imageList = new ArrayList<Image>();
    public static final String PAINTSIGNATURE = "paintSignature";
    private String folderPath = "";
    private Handler handler;


    private static Logger logger = LogUtils
            .getLogger(PaintSignatureActivity.class);
    private ProgressDialog dialog;
    public static final int SIGN_PIC_SUCCESS_CODE = 0x556;
    public static final String RMTSIGNRECORD = "rmtSignRecord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rmt_common_component_paint_signature);

        initData();
        initView();
        getImgs();

    }

    @Override
    protected void onStart() {
        super.onStart();
        paintView.redo();
    }

    @SuppressWarnings("unchecked")
    private void initData() {
        Intent intent = getIntent();
        folderPath = intent.getStringExtra(PAINTSIGNATURE);
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        handler = new Handler();


    }

    private void initView() {
        paintView = (PaintView) findViewById(R.id.paintview);
        btCancle = (Button) findViewById(R.id.rmt_common_component_paint_signature_bt_cancle);
        btClear = (Button) findViewById(R.id.rmt_common_component_paint_signature_bt_clear);
        btEnture = (Button) findViewById(R.id.rmt_common_component_paint_signature_bt_entrue);
        btSave = (Button) findViewById(R.id.rmt_common_component_paint_signature_bt_save);
        tvFzr = (TextView) findViewById(R.id.rmt_common_component_paint_signature_tv_fzr);
        hlv = (HorizontalListView) findViewById(R.id.rmt_common_component_paint_signature_hlv);
        btCancle.setOnClickListener(this);
        btClear.setOnClickListener(this);
        btEnture.setOnClickListener(this);
        btSave.setOnClickListener(this);
        mImgAdapter = new ImgAdapter(this,
                imageList);
        hlv.setAdapter(mImgAdapter);
        hlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 照片浏览
                Intent intent = new Intent(
                        PaintSignatureActivity.this,
                        PhotoPreviewActivity.class);
                intent.putExtra(PhotoPreviewActivity.SELECTEDINDEX, position);
                intent.putExtra(PhotoPreviewActivity.IMAGESET, imageList);
                startActivity(intent);

            }
        });
        LoginUser loginUser = LoginUserRecord.getInstance().getUser();
        tvFzr.setText("现场负责人：" + loginUser.getUserName());

    }

    private void getImgs() {
        imageList.clear();
        File f = new File(folderPath);
        if (f.exists()) {

            File[] childFiles = f.listFiles();
            for (int i = 0; i < childFiles.length; i++) {
                Image img = new Image();
                img.setImagepath(childFiles[i].getAbsolutePath());
                img.setImageName(childFiles[i].getName());
                imageList.add(img);
            }
        }
    }

    /**
     * 保存签名图片 saveImage:(). <br/>
     * date: 2015年10月26日 <br/>
     *
     * @author LiuYang
     */

    private Image saveImage() {
        Bitmap bitmap = paintView.getBitmap();
        // 获取file name...
        Date date = new Date();
        Long time = date.getTime();
        String filePath = folderPath;
        String fileName = time.toString() + ".png";
        // 保存图片
        File file = new File(filePath, fileName);
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, out);
            out.flush();
            out.close();
            Image image = new Image();
            image.setImageName(fileName);
            image.setImagepath(filePath + File.separator + fileName);
            return image;

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    @Override
    public void onClick(View arg0) {
        int id = arg0.getId();
        if (id == R.id.rmt_common_component_paint_signature_bt_cancle) {
            //取消
            finish();
        } else if (id == R.id.rmt_common_component_paint_signature_bt_entrue) {
            //确定,上传图片到服务器
            final RmtSignRecord rmtSignRecord = new RmtSignRecord();
            dialog.setMessage("图片转码中...");
            if (imageList != null && imageList.size() > 0) {
                String imageName = null;
                final List<String> imageStrings = new ArrayList<>();
                for (Image image : imageList) {
                    if (imageName == null)
                        imageName = image.getImageName();
                    else
                        imageName += ("," + image.getImageName());
                }
                rmtSignRecord.setImageName(imageName);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        for (Image image : imageList) {
                            try {
                                imageStrings.add(Base64Util.encodeBase64File(image.getImagepath()));

                            } catch (Exception e) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (dialog != null && dialog.isShowing())
                                            dialog.dismiss();
                                        ToastUtils.toast(getApplicationContext(), "图片转码出错");
                                    }
                                });
                                e.printStackTrace();
                                break;
                            }
                        }
                        rmtSignRecord.setImageList(imageStrings);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //转码成功
                                if (dialog != null && dialog.isShowing())
                                    dialog.dismiss();
                                Intent i = new Intent();
                                i.putExtra(RMTSIGNRECORD, rmtSignRecord);
                                setResult(SIGN_PIC_SUCCESS_CODE, i);
                                finish();
                                //uploadSignPic(rmtSignRecord);
                            }
                        });


                    }
                }.start();

            } else {
                ToastUtils.toast(getApplicationContext(), "您还没有手写签名");
            }
        } else if (id == R.id.rmt_common_component_paint_signature_bt_clear) {
            //清除
            paintView.redo();
        } else if (id == R.id.rmt_common_component_paint_signature_bt_save) {
            //保存
            if (paintView.hasDraw()) {
                Image image = saveImage();
                if (image != null) {
                    imageList.add(image);
                    mImgAdapter.notifyDataSetChanged();
                    paintView.redo();
                } else {
                    ToastUtil.toast(this, "保存手签图片出错");
                }
            } else {
                ToastUtil.toast(this, "您还没有手写签名");
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //sendBroadCast();
        return super.onKeyDown(keyCode, event);
    }

}
