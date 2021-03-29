package com.hd.hse.common.module.phone.camera2;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hd.hse.common.module.phone.R;

public class CameraFragment extends Fragment implements OnClickListener{

	private Camera camera;
	private CameraPreview cameraPreview;
	private CameraOrientationListener orientationListener;
	private TextView shutterTV;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		orientationListener = new CameraOrientationListener(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.hd_hse_module_fragment_camera, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		view.findViewById(R.id.hd_hse_module_fragment_camera_tv_cancel_take_photo).setOnClickListener(this);
		shutterTV = (TextView) view.findViewById(R.id.hd_hse_module_fragment_camera_tv_shutter);
		shutterTV.setOnClickListener(this);
		
		cameraPreview = (CameraPreview) view.findViewById(R.id.hd_hse_module_camera_preview);
	}

	@Override
	public void onResume() {
		super.onResume();
		orientationListener.enable();
		shutterTV.setEnabled(true);
		openCamera();
	}

	@Override
	public void onPause() {
		releaseCamera();
		orientationListener.disable();
		
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		int vid = v.getId();
		if(vid == R.id.hd_hse_module_fragment_camera_tv_cancel_take_photo){
			getActivity().finish();
		}else if(vid == R.id.hd_hse_module_fragment_camera_tv_shutter){
			if(camera != null){
				orientationListener.rememberOrientation();
				v.setEnabled(false);
				camera.takePicture(null, null, pictureCallback);
			}
		}
	}
	
	private Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
		
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			int orientation = orientationListener.getRememberedNormalOrientation();
			Log.i("trace", "orientation:" + orientation);
			((CameraActivity)getActivity()).switchFragment(CameraFragment.this, SavePhotoFragment.newInstance(data, orientation));
		}
	};
	
	public void openCamera(){
		try{
			camera = Camera.open();
			cameraPreview.setCamera(camera);
		}catch(Exception e){
			camera = null;
			cameraPreview.setCamera(camera);
			Toast.makeText(getActivity(), "摄像头开启失败，请检查相关权限", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void releaseCamera(){
		if(camera != null){
			camera.release();
			camera = null;
			cameraPreview.setCamera(null);
		}
	}
	
    private static class CameraOrientationListener extends OrientationEventListener {

        private int mCurrentNormalizedOrientation;
        private int mRememberedNormalOrientation;

        public CameraOrientationListener(Context context) {
            super(context, SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        public void onOrientationChanged(int orientation) {
            if (orientation != ORIENTATION_UNKNOWN) {
                mCurrentNormalizedOrientation = normalize(orientation);
            }
        }

        private int normalize(int degrees) {
            if (degrees > 315 || degrees <= 45) {
                return 90;
            }

            if (degrees > 45 && degrees <= 135) {
                return 180;
            }

            if (degrees > 135 && degrees <= 225) {
                return 270;
            }

            if (degrees > 225 && degrees <= 315) {
                return 0;
            }

            throw new RuntimeException("The physics as we know them are no more. Watch out for anomalies.");
        }

        public void rememberOrientation() {
            mRememberedNormalOrientation = mCurrentNormalizedOrientation;
        }

        public int getRememberedNormalOrientation() {
            return mRememberedNormalOrientation;
        }
    }
}
