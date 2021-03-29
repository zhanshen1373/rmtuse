package com.hd.hse.common.module.phone.camera2;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

public class CameraPreview extends ViewGroup implements SurfaceHolder.Callback, AutoFocusCallback {

	// for camera preview
	private Context context;
	private Camera camera;
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private Camera.Size previewSize;
	private List<Camera.Size> supportedPreviewSizes;

	// for zoom
	private static final int ZOOM_OUT = 0;
	private static final int ZOOM_IN = 1;
	private static final int ZOOM_DELTA = 1;

	private boolean isSupportedZoom;
	private int maxZoom;
	private int scaleFactor = 1;
	private ScaleGestureDetector scaleDetector;

	// for camera focus
	private static final int FOCUS_SQR_SIZE = 100;
	private static final int FOCUS_MAX_BOUND = 1000;
	private static final int FOCUS_MIN_BOUND = -1000;

	private boolean isFocusing;
	private Camera.Area focusArea;
	private List<Camera.Area> focusAreas;

	private float lastTouchX;
	private float lastTouchY;

	public CameraPreview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		init(context);
	}

	public CameraPreview(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public CameraPreview(Context context) {
		this(context, null);
	}

	private void init(Context context) {
		this.context = context;
		this.surfaceView = new SurfaceView(this.context);
		this.addView(this.surfaceView);
		this.surfaceHolder = this.surfaceView.getHolder();
		this.surfaceHolder.addCallback(this);

		this.setBackgroundColor(Color.BLACK);

		this.focusArea = new Camera.Area(new Rect(), 1000);
		this.focusAreas = new ArrayList<Camera.Area>();
		this.focusAreas.add(focusArea);

		this.scaleDetector = new ScaleGestureDetector(context,
				new ScaleListener());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
		int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);

		setMeasuredDimension(width, height);
		if (supportedPreviewSizes != null && supportedPreviewSizes.size() > 0) {
			previewSize = getOptimalPreviewSize(supportedPreviewSizes, height, width);
		}
	}

	private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
		double ASPECT_TOLERANCE = 0.1;
		double targetRatio = (double) w / h;
		if (sizes == null) {
			return null;
		}

		Camera.Size optimalSize = null;
		double minDiff = Double.MAX_VALUE;
		int targetHeight = h;
		for (Camera.Size size : sizes) {
			double ratio = (double) size.width / size.height;
			if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) {
				continue;
			}

			if (Math.abs(size.height - targetHeight) < minDiff) {
				optimalSize = size;
				minDiff = Math.abs(size.height - targetHeight);
			}
		}

		if (optimalSize == null) {
			minDiff = Double.MAX_VALUE;
			for (Camera.Size size : sizes) {
				if (Math.abs(size.height - targetHeight) < minDiff) {
					optimalSize = size;
					minDiff = Math.abs(size.height - targetHeight);
				}
			}
		}
		return optimalSize;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (changed && getChildCount() > 0) {
			final View child = getChildAt(0);

			int width = r - l;
			int height = b - t;

			int previewWidth = width;
			int previewHeight = height;

			if (previewSize != null) {
				previewWidth = previewSize.height;
				previewHeight = previewSize.width;
			}
			
			// 手机屏幕的宽高比大于采集图像的宽高比
			if (width * previewHeight > height * previewWidth) {
				final int scaledChildWidth = previewWidth * height / previewHeight;
				 child.layout((width - scaledChildWidth) / 2, 0, (width+scaledChildWidth) / 2, height);
			} else {
				final int scaledChildHeight = previewHeight * width / previewWidth;
				child.layout(0, (height - scaledChildHeight) / 2, width, (height + scaledChildHeight) / 2);
			}
		}

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		scaleDetector.onTouchEvent(event);
		
    	final int action = event.getAction();
    	switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			isFocusing = true;
			lastTouchX = event.getX();
			lastTouchY = event.getY();
			break;
			
		case MotionEvent.ACTION_POINTER_DOWN:
			if(camera != null){
				camera.cancelAutoFocus();
			}
			isFocusing = false;
			break;
			
		case MotionEvent.ACTION_UP:
			if(isFocusing && camera != null){
				handleFocus(camera.getParameters());
			}
			break;
		}
    	return true;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try{
			if(camera != null){
				camera.setPreviewDisplay(this.surfaceHolder);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    	if(camera != null){
    		Camera.Parameters parameters = camera.getParameters();
    		parameters.setPreviewSize(previewSize.width, previewSize.height);
    		camera.setParameters(parameters);
    		camera.setDisplayOrientation(90);
    		camera.startPreview();
    	}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if(camera != null){
			camera.stopPreview();
		}
	}

	@Override
	public void onAutoFocus(boolean success, Camera camera) {
		if(success){
			
		}else{
			
		}
	}

	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			scaleFactor = (int) detector.getScaleFactor();
			if(camera != null){
				handleZoom(camera.getParameters());
			}
			return true;
		}
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
		if (this.camera != null) {
			Parameters parameters = this.camera.getParameters();
			supportedPreviewSizes = parameters.getSupportedPreviewSizes();
			isSupportedZoom = parameters.isZoomSupported();
			if (isSupportedZoom) {
				maxZoom = parameters.getMaxZoom();
			}
			requestLayout();
		}
	}
	
	private void handleFocus(Parameters parameters) {
		float x = lastTouchX;
		float y = lastTouchY;
		
		if(!setFocusBound(x, y)){
			return ;
		}
		
		List<String> supportedFocusModes = parameters.getSupportedFocusModes();
		
		if(supportedFocusModes != null && supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)){
			parameters.setFocusAreas(focusAreas);
			parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
			camera.setParameters(parameters);
			camera.autoFocus(this);
		}		
	}
	
	private boolean setFocusBound(float x, float y) {
		int left = (int) (x - FOCUS_SQR_SIZE / 2);
		int right = (int) (x + FOCUS_SQR_SIZE / 2);
		int top = (int) (y - FOCUS_SQR_SIZE / 2);
		int bottom = (int) (y + FOCUS_SQR_SIZE / 2);

		if (FOCUS_MIN_BOUND > left || left > FOCUS_MAX_BOUND)
			return false;
		if (FOCUS_MIN_BOUND > right || right > FOCUS_MAX_BOUND)
			return false;
		if (FOCUS_MIN_BOUND > top || top > FOCUS_MAX_BOUND)
			return false;
		if (FOCUS_MIN_BOUND > bottom || bottom > FOCUS_MAX_BOUND)
			return false;

		focusArea.rect.set(left, top, right, bottom);
		return true;
	}
	
	private void handleZoom(Parameters params) {
		int zoom = params.getZoom();
		if(scaleFactor == ZOOM_IN){
			if(zoom < maxZoom){
				zoom += ZOOM_DELTA;
			}
		}else if(scaleFactor == ZOOM_OUT){
			if(zoom > 0){
				zoom -= ZOOM_DELTA;
			}
		}
		params.setZoom(zoom);
		camera.setParameters(params);
	}
	
	public Camera getCamera(){
		return this.camera;
	}
}
