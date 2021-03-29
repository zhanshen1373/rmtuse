package com.hd.hse.common.module.phone.camera2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.hayden.hap.common.common.bussiess.HttpBusiness;
import com.hayden.hap.common.common.bussiess.HttpCallBack;
import com.hayden.hap.common.common.bussiess.ResultData;
import com.hayden.hap.common.utils.RetrofitUtil;
import com.hd.hse.common.business.web_interface.RmtInterface;
import com.hd.hse.common.component.phone.util.Base64Util;
import com.hd.hse.common.logger.LogUtils;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.branch.ui.model.fragment.BaseFragment;
import com.hd.hse.common.module.phone.R;
import com.hd.hse.entity.common.Image;
import com.hd.hse.entity.sys.RmtSignRecord;
import com.hd.hse.system.SystemProperty;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class SavePhotoFragment extends Fragment implements OnClickListener {

    public static final String IMG_DATA = "img_data";
    public static final String IMG_ROTATION = "img_rotation";

    /**
     * logger:TODO(日志).
     */
    private final static Logger logger = LogUtils.getLogger(SavePhotoFragment.class);

    private ImageView photoIV;
    private ProgressDialog dialog;
    private static Bitmap bitmap;
    //	private BusinessAction businessAction;
    private String path = "";
    private String filePath = "";

    public static Fragment newInstance(byte[] data, int rotation) {
        Fragment fragment = new SavePhotoFragment();
        Bundle args = new Bundle();
        args.putByteArray(IMG_DATA, data);
        args.putInt(IMG_ROTATION, rotation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hd_hse_module_fragment_edit_photo,
                container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int rotation = getArguments().getInt(IMG_ROTATION);
        byte[] data = getArguments().getByteArray(IMG_DATA);

        view.findViewById(
                R.id.hd_hse_module_fragment_camera_tv_restart_take_photo)
                .setOnClickListener(this);
        view.findViewById(R.id.hd_hse_module_fragment_camera_tv_confirm_photo)
                .setOnClickListener(this);
        photoIV = (ImageView) view
                .findViewById(R.id.hd_hse_module_fragment_camera_iv_show_photo);
        bitmap = rotatePicture(getActivity(), rotation, data);
        photoIV.setImageBitmap(bitmap);
    }

    public static Bitmap rotatePicture(Context context, int rotation,
                                       byte[] data) {
        bitmap = ImageCompressUtil.compressImageByPixel(context, data);
        if (rotation != 0) {
            Bitmap oldBitmap = bitmap;
            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);
            bitmap = Bitmap.createBitmap(oldBitmap, 0, 0, oldBitmap.getWidth(),
                    oldBitmap.getHeight(), matrix, false);
            oldBitmap.recycle();
        }
        return bitmap;
    }

    private boolean isFirstUpload = true;
    public static final String RMTSIGNRECORD = "rmtSignRecord";
    public static final String PICTURE = "picture";

    @Override
    public void onClick(View v) {
        int vid = v.getId();
        if (vid == R.id.hd_hse_module_fragment_camera_tv_restart_take_photo) {
            getActivity().getSupportFragmentManager().popBackStack();
        } else if (vid == R.id.hd_hse_module_fragment_camera_tv_confirm_photo) {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("正在上传图片...");
            dialog.show();
            if (bitmap != null) {

                if (isFirstUpload) {
                    final Image image = ((CameraActivity) getActivity()).image;
                    final RmtSignRecord rmtSignRecord = ((CameraActivity) getActivity()).rmtSignRecord;
                    String imageName = image.getImageName();
                    filePath = image.getImagepath();
                    String createTime = SystemProperty.getSystemProperty().getSysSHDateTime();
                    String fileName = imageName + "_" + createTime + ".jpg";
                    image.setImageName(fileName);
                    image.setCreateDate(createTime);

                    final List<String> imageList = new ArrayList<String>();
                    final Handler handler = new Handler();
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            //线程里进行图片编码
                            try {
                                imageList.add(Base64Util.bitmapToBase64(bitmap));
                                if (rmtSignRecord == null) {
                                    image.setImageList(imageList);
                                }
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        //转码成功后上传图片
                                        isFirstUpload = false;
                                        if (rmtSignRecord != null) {

                                            if (dialog != null && dialog.isShowing()) {
                                                dialog.dismiss();
                                            }

                                            SystemProperty.getSystemProperty().setImageList(imageList);
                                            Intent i = new Intent();
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable(RMTSIGNRECORD, rmtSignRecord);
                                            bundle.putSerializable(PICTURE, image);
                                            i.putExtras(bundle);
                                            getActivity().setResult(BaseFragment.Return_Picture, i);
                                            getActivity().finish();


                                        } else {
                                            upLoadPic(image);
                                        }
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (dialog != null && dialog.isShowing()) {
                                            dialog.dismiss();
                                        }
                                        Toast.makeText(getActivity(), "上传图片失败!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }.start();
                } else {
                    upLoadPic(((CameraActivity) getActivity()).image);
                }

            } else {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(getActivity(), "上传图片失败!", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        }
    }


    /**
     * 上传图片
     *
     * @param image
     */
    private void upLoadPic(Image image) {
        final RmtInterface rmtInterface = RetrofitUtil.createInterface(RmtInterface.class);
        Call<ResultData<Object>> call = rmtInterface.uploadPic(image);
        HttpBusiness.action(getActivity(), false, call, new HttpCallBack<Object>() {
            @Override
            public void success(Object o) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                Toast.makeText(getActivity(), "上传图片成功!", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }

            @Override
            public void warning(String msg) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void error(Throwable t) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                Toast.makeText(getActivity(), "上传图片失败!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
