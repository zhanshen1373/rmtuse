package com.hd.hse.common.module.phone.photo.preview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;

public class ZoomImageView extends ImageView implements OnGlobalLayoutListener,
		OnScaleGestureListener, OnTouchListener {

	private boolean mOnce = false;
	private float mInitScale;
	private float mDoubleTapScale;
	private float mMaxScale;
	private Matrix mScaleMatrix;

	private ScaleGestureDetector mScaleGestureDetector;

	/** -------------- 图片自由移动--------------- */
	private int mLastPointerCount;
	private float mLastX;
	private float mLastY;
	private int mTouchSlop;
	private boolean isCanDrag;

	private boolean isCheckLeftAndRight;
	private boolean isCheckTopAndBottom;

	/** ------------ 双击放大与缩小 -------------- */
	private GestureDetector mGestureDetector;
	private boolean isAutoScale; // 防止暴力双击

	public ZoomImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		// init
		mScaleMatrix = new Matrix();
		setScaleType(ScaleType.MATRIX);

		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

		setOnTouchListener(this);
		mScaleGestureDetector = new ScaleGestureDetector(context, this);
		mGestureDetector = new GestureDetector(context,
				new SimpleDoubleTapListener());
	}

	public ZoomImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ZoomImageView(Context context) {
		this(context, null);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		getViewTreeObserver().addOnGlobalLayoutListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onDetachedFromWindow() {
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
			getViewTreeObserver().removeOnGlobalLayoutListener(this);
		} else {
			getViewTreeObserver().removeGlobalOnLayoutListener(this);
		}
		super.onDetachedFromWindow();
	}

	@Override
	public void onGlobalLayout() {
		if (!mOnce) {
			int width = getWidth();
			int height = getHeight();

			Drawable drawable = getDrawable();
			if (drawable == null) {
				return;
			}

			int dw = drawable.getIntrinsicWidth();
			int dh = drawable.getIntrinsicHeight();

			// 计算缩放比
			mInitScale = 1.0f;
			if (dw > width && dh < height) {
				mInitScale = width * 1.0f / dw;
			} else if (dw < width && dh > height) {
				mInitScale = height * 1.0f / dh;
			} else {
				mInitScale = Math.min(width * 1.0f / dw, height * 1.0f / dh);
			}

			mMaxScale = mInitScale * 4;
			mDoubleTapScale = mInitScale * 2;

			// 将图片移动到控件中心
			int dx = width / 2 - dw / 2;
			int dy = height / 2 - dh / 2;

			mScaleMatrix.postTranslate(dx, dy);
			mScaleMatrix.postScale(mInitScale, mInitScale, width / 2,
					height / 2);
			setImageMatrix(mScaleMatrix);

			mOnce = true;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (mGestureDetector.onTouchEvent(event)) {
			return true;
		}

		mScaleGestureDetector.onTouchEvent(event);

		// 触摸中心点的位置
		float x = 0;
		float y = 0;
		int pointerCount = event.getPointerCount();
		for (int i = 0; i < pointerCount; i++) {
			x += event.getX(i);
			y += event.getY(i);
		}

		x /= pointerCount;
		y /= pointerCount;

		if (mLastPointerCount != pointerCount) {
			isCanDrag = false;
			mLastX = x;
			mLastY = y;
		}
		mLastPointerCount = pointerCount;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			handleIntercept();
			break;
		case MotionEvent.ACTION_MOVE:
			handleIntercept();

			float dx = x - mLastX;
			float dy = y - mLastY;

			if (!isCanDrag) {
				isCanDrag = isMoveAction(dx, dy);
			}

			if (isCanDrag) {
				RectF rectF = getMatrixRectF();
				if (getDrawable() != null) {
					isCheckLeftAndRight = isCheckTopAndBottom = true;
					if (rectF.width() < getWidth()) {
						isCheckLeftAndRight = false;
						dx = 0;
					}

					if (rectF.height() < getHeight()) {
						isCheckTopAndBottom = false;
						dy = 0;
					}

					mScaleMatrix.postTranslate(dx, dy);
					checkBorderWhenTranslate();
					setImageMatrix(mScaleMatrix);
				}
			}
			mLastX = x;
			mLastY = y;
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			mLastPointerCount = 0;
			break;
		}

		return true;
	}

	private void checkBorderWhenTranslate() {
		RectF rectF = getMatrixRectF();
		float deltaX = 0;
		float deltaY = 0;

		int width = getWidth();
		int height = getHeight();

		if (rectF.top > 0 && isCheckTopAndBottom) {
			deltaY = -rectF.top;
		}

		if (rectF.bottom < height && isCheckTopAndBottom) {
			deltaY = height - rectF.bottom;
		}

		if (rectF.left > 0 && isCheckLeftAndRight) {
			deltaX = -rectF.left;
		}

		if (rectF.right < width && isCheckLeftAndRight) {
			deltaX = width - rectF.right;
		}

		mScaleMatrix.postTranslate(deltaX, deltaY);
	}

	public float getScale() {
		float[] values = new float[9];
		mScaleMatrix.getValues(values);
		return values[Matrix.MSCALE_X];
	}

	private RectF getMatrixRectF() {
		Matrix matrix = mScaleMatrix;
		RectF rectF = new RectF();
		Drawable d = getDrawable();
		if (null != d) {
			rectF.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
			matrix.mapRect(rectF);
		}
		return rectF;
	}

	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		float scale = getScale();
		float scaleFactor = detector.getScaleFactor();

		if (getDrawable() == null) {
			return true;
		}

		if ((scale < mMaxScale && scaleFactor > 1.0f)
				|| (scale > mInitScale && scaleFactor < 1.0f)) {
			if (scale * scaleFactor < mInitScale) {
				scaleFactor = mInitScale / scale;
			}

			if (scale * scaleFactor > mMaxScale) {
				scaleFactor = mMaxScale / scale;
			}

			mScaleMatrix.postScale(scaleFactor, scaleFactor,
					detector.getFocusX(), detector.getFocusY());
			checkBorderAndCenterWhenScale();
			setImageMatrix(mScaleMatrix);
		}
		return true;
	}

	private void checkBorderAndCenterWhenScale() {
		RectF rectF = getMatrixRectF();
		float deltaX = 0;
		float deltaY = 0;
		int width = getWidth();
		int height = getHeight();

		if (rectF.width() >= width) {
			if (rectF.left > 0) {
				deltaX = -rectF.left;
			}

			if (rectF.right < width) {
				deltaX = width - rectF.right;
			}
		}

		if (rectF.height() >= height) {
			if (rectF.top > 0) {
				deltaY = -rectF.top;
			}

			if (rectF.bottom < height) {
				deltaY = height - rectF.bottom;
			}
		}

		// 图片的宽高小于组件的宽高，让其居中
		if (rectF.width() < width) {
			deltaX = width / 2.0f - rectF.right + rectF.width() / 2.0f;
		}

		if (rectF.height() < height) {
			deltaY = height / 2.0f - rectF.bottom + rectF.height() / 2.0f;
		}

		mScaleMatrix.postTranslate(deltaX, deltaY);
	}

	private boolean isMoveAction(float dx, float dy) {
		return Math.sqrt(dx * dx + dy * dy) > mTouchSlop;
	}

	private class SimpleDoubleTapListener extends
			GestureDetector.SimpleOnGestureListener {
		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			if (isAutoScale) {
				return true;
			}

			float x = e.getX();
			float y = e.getY();
			if (getScale() < mDoubleTapScale) {
				postDelayed(new AutoScaleRunnable(mDoubleTapScale, x, y), 20);
			} else {
				postDelayed(new AutoScaleRunnable(mInitScale, x, y), 20);
			}
			isAutoScale = true;
			return true;
		}
	}

	private class AutoScaleRunnable implements Runnable {

		private float mTargetScale;
		private float x;
		private float y;

		private final float GRAD_BIGGER = 1.07f;
		private final float GRAD_SMALLER = 0.93f;

		private float mTempScale;

		public AutoScaleRunnable(float targetScale, float x, float y) {
			this.mTargetScale = targetScale;
			this.x = x;
			this.y = y;

			if (getScale() < mTargetScale) {
				mTempScale = GRAD_BIGGER;
			} else if (getScale() > mTargetScale) {
				mTempScale = GRAD_SMALLER;
			}
		}

		@Override
		public void run() {
			mScaleMatrix.postScale(mTempScale, mTempScale, x, y);
			checkBorderAndCenterWhenScale();
			setImageMatrix(mScaleMatrix);

			float currentScale = getScale();
			if ((mTempScale > 1.0f && currentScale < mTargetScale)
					|| (mTempScale < 1.0f && currentScale > mTargetScale)) {
				postDelayed(this, 20);
			} else {
				float scale = mTargetScale / currentScale;
				mScaleMatrix.postScale(scale, scale, x, y);
				checkBorderAndCenterWhenScale();
				setImageMatrix(mScaleMatrix);
				isAutoScale = false;
			}
		}
	}

	private void handleIntercept() {
		RectF rectF = getMatrixRectF();
		if (rectF.width() > getWidth() + 0.01f
				|| rectF.height() > getHeight() + 0.01f) {
			getParent().requestDisallowInterceptTouchEvent(true);
		}
	}

	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		return true;
	}

	@Override
	public void onScaleEnd(ScaleGestureDetector detector) {
	}
}
