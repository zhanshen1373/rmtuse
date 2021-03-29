package com.hd.hse.common.module.phone.camera;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class HudRing extends ImageView implements View.OnTouchListener{
	private float mLastX;
    private float mLastY;

    private final static float IDLE_ALPHA = 0.25f;
    private final static int ANIMATION_DURATION = 120;

    public HudRing(Context context) {
        super(context);
    }

    public HudRing(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HudRing(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOnTouchListener(this);
        setAlpha(IDLE_ALPHA);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mLastX = motionEvent.getRawX();
            mLastY = motionEvent.getRawY();
           /* mOffsetX = motionEvent.getX() - motionEvent.getRawX();
            mOffsetY = motionEvent.getY() - motionEvent.getRawY();*/
            animatePressDown();
        } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            setX(getX() + (motionEvent.getRawX() - mLastX));
            setY(getY() + (motionEvent.getRawY() - mLastY));

            mLastX = motionEvent.getRawX();
            mLastY = motionEvent.getRawY();
        } else if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
            animatePressUp();
        }

        return true;
    }

    public void animatePressDown() {
        animate().alpha(1.0f).setDuration(ANIMATION_DURATION).start();
    }

    public void animatePressUp() {
        animate().alpha(IDLE_ALPHA).rotation(0).setDuration(ANIMATION_DURATION).start();
    }

    public void animateWorking(long duration) {
        animate().rotationBy(45.0f).setDuration(duration).setInterpolator(
                new DecelerateInterpolator()).start();
    }
}
