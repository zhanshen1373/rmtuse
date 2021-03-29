/**
 * Project Name:woa-obr-app
 * File Name:PhotoWallActivity.java
 * Package Name:com.hd.woa.obr.app.activities
 * Date:2015年4月24日
 * Copyright (c) 2015, fulibo@ushayden.com All Rights Reserved.
 */
package com.hd.hse.common.module.phone.photo.preview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.util.ToastUtils;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.entity.common.ApprRecordMVO;
import com.hd.hse.entity.common.Image;
import com.hd.hse.entity.workorder.RmtWorkSubtask;
import com.hd.hse.entity.workorder.RmtWorkTaskBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * ClassName:PhotoWallActivity ().<br/>
 * Date: 2015年4月24日 <br/>
 *
 * @author flb
 * @see
 */
public class AlbumActivity extends Activity implements OnItemClickListener, OnClickListener {

    public static final String IMAGE_FLAG = "image_flag";
    public static final String IMAGE_TASK_FLAG = "image_task_flag";
    private GridView mGridView;
    private ArrayList<Image> lists;
    private PhotoWallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.hd_woa_obr_app_photo_wall_grid_fragment);

        initConfig();
        initData();

        mGridView = (GridView) findViewById(R.id.gv_img);
        adapter = new PhotoWallAdapter(this, lists, mGridView);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(this);

        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    private void initConfig() {
        int screenWidthPixels = ScreenUtil.getScreenWidthPixels(this);
        int screenHeightPixels = ScreenUtil.getScreenHeightPixels(this);
        Constants.cloumnWidth = screenWidthPixels / 3;
        Constants.cloumnHeight = screenWidthPixels / 3;
       /* ImageCache.getInstance(this).setMaxImageSize(screenWidthPixels,
                screenHeightPixels);*/
    }

    private void initData() {
        Intent intent = getIntent();
        RmtWorkSubtask rmtWorkSubtask = (RmtWorkSubtask) intent.getSerializableExtra(IMAGE_FLAG);
        RmtWorkTaskBean workTaskBean = (RmtWorkTaskBean) intent.getSerializableExtra(IMAGE_TASK_FLAG);
        lists = new ArrayList<Image>();
        if (rmtWorkSubtask != null) {
            getImageList("subtask", rmtWorkSubtask.getWork_subtask_id());
        } else if (workTaskBean != null) {
            getImageList("task", workTaskBean.getWork_task_id());
        }

    }

    @Override
    protected void onStop() {
        ImageLoader.getInstance().cancelAllTask();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //ImageCache.getInstance(this).close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Intent intent = new Intent(this, AlbumPreviewActivity.class);
        intent.putExtra(IMAGE_FLAG, lists);
        intent.putExtra(Constants.IMAGE_CURRENT_ITEM, position);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }

    /**
     * @param type (值为task 或者subtask)
     * @param id
     */
    private void getImageList(String type, long id) {
        final ProgressDialog dialog = new ProgressDialog(AlbumActivity.this);
        dialog.setMessage("数据加载中。。。");
        dialog.show();
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<List<ApprRecordMVO>>> call = rmtInterface.getRecordPicList(type, id);
        HttpBusiness.action(AlbumActivity.this, false, call, new HttpCallBack<List<ApprRecordMVO>>() {
            @Override
            public void success(List<ApprRecordMVO> apprRecordMVOList) {
                dialog.dismiss();
                for (ApprRecordMVO apprRecordMVO : apprRecordMVOList) {
                    Image image = new Image();
                    image.setImageName(apprRecordMVO.getImageName());
                    image.setImagepath(apprRecordMVO.getImageId() + "");
                    lists.add(image);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void warning(String msg) {
                dialog.dismiss();
                ToastUtils.toast(getApplicationContext(), msg);
            }

            @Override
            public void error(Throwable t) {
                dialog.dismiss();
                ToastUtils.imgToast(getApplicationContext(), R.drawable.hd_common_message_error, "加载失败，请检查网络设置！");
            }
        });
    }

}
