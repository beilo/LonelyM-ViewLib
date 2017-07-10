package com.czcg.viewlib.ui;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leipe on 2017/7/6.
 */

public abstract class Indicator extends Drawable implements Animatable {

    private HashMap<ValueAnimator, ValueAnimator.AnimatorUpdateListener> mUpdateListeners = new HashMap<>();
    private ArrayList<ValueAnimator> mAnimators;
    private int alpha = 255;
    private static final Rect ZERO_BOUNDS_RECT = new Rect();
    private Rect drawBounds = ZERO_BOUNDS_RECT;

    private boolean mHasAnimators;
    private Paint mPaint = new Paint();

    public Indicator() {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    // region 设置 Color Alpha
    public int getColor() {
        return mPaint.getColor();
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    @Override
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    @Override
    public int getAlpha() {
        return alpha;
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    public void setDrawBounds(Rect drawBounds) {
        setDrawBounds(drawBounds.left, drawBounds.top, drawBounds.right, drawBounds.bottom);
    }
    public void setDrawBounds(int left, int top, int right, int bottom) {
        this.drawBounds = new Rect(left, top, right, bottom);
    }

    public void addUpdateListener(ValueAnimator animator, ValueAnimator.AnimatorUpdateListener updateListener){
        mUpdateListeners.put(animator,updateListener);
    }

    // endregion

    @Override
    public void start() {
        ensureAnimator();
        if (mAnimators == null)
            return;
        if (isStarted())
            return;
        startAnimators();
        postInvalidate();
    }

    @Override
    public void stop() {
        stopAnimators();
    }

    @Override
    public boolean isRunning() {
        if (mAnimators == null)
            return false;
        for (ValueAnimator animator : mAnimators) {
            return animator.isRunning();
        }
        return false;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        draw(canvas, mPaint);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        setDrawBounds(bounds);
    }


    public abstract void draw(Canvas canvas, Paint paint); //具体的绘画

    public abstract ArrayList<ValueAnimator> onCreateAnimator(); //创建动画数据集

    /**
     * 判断是否有动画,没有则创建并且改变状态,有不处理
     */
    private void ensureAnimator() {
        if (!mHasAnimators) {
            mAnimators = onCreateAnimator();
            mHasAnimators = true;
        }
    }

    /**
     * 开启动画集所有动画,并且添加自定义监听
     */
    private void startAnimators() {
        for (int i = 0; i < mAnimators.size(); i++) {
            ValueAnimator valueAnimator = mAnimators.get(i);
            ValueAnimator.AnimatorUpdateListener updateListener = mUpdateListeners.get(valueAnimator);
            if (updateListener != null) {
                valueAnimator.addUpdateListener(updateListener);
            }
            valueAnimator.start();
        }
    }

    /**
     * 关闭动画集中所有开启的动画
     */
    private void stopAnimators() {
        if (mAnimators == null)
            return;
        for (ValueAnimator mAnimator : mAnimators) {
            if (mAnimator != null && mAnimator.isStarted()) {
                mAnimator.removeAllUpdateListeners();
                mAnimator.end();
            }
        }
    }

    /**
     * @return 第一个动画是否被启动
     */
    private boolean isStarted() {
        for (ValueAnimator animator : mAnimators) {
            return animator.isStarted();
        }
        return false;
    }


    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    //region 获取属性 drawBounds width height centerX centerY
    public void postInvalidate(){
        invalidateSelf();
    }

    public Rect getDrawBounds() {
        return drawBounds;
    }

    public int getWidth(){
        return drawBounds.width();
    }

    public int getHeight(){
        return drawBounds.height();
    }

    public int centerX(){
        return drawBounds.centerX();
    }

    public int centerY(){
        return drawBounds.centerY();
    }

    public float exactCenterX(){
        return drawBounds.exactCenterX();
    }

    public float exactCenterY(){
        return drawBounds.exactCenterY();
    }
    //endregion
}
