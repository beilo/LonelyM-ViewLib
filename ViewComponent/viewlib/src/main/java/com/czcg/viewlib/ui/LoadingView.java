package com.czcg.viewlib.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.czcg.viewlib.R;
import com.czcg.viewlib.ui.indicators.BallPulseIndicator;

/**
 * Created by leipe on 2017/7/6.
 */

public class LoadingView extends View {
    private static final Indicator DEFAULT_INDICATOR = new BallPulseIndicator();
    private static final int MIN_SHOW_TIME = 500; //ms
    private static final int MIN_DELAY = 500; //ms

    private long mStartTime = -1;
    private boolean mPostedHide = false;
    private boolean mPostedShow = false;
    private boolean mDismissed = false;

    private final Runnable mDelayedHide = new Runnable() {
        @Override
        public void run() {
            mPostedHide = false;
            mStartTime = -1;
            setVisibility(GONE);
        }
    };

    private final Runnable mDelayedShow = new Runnable() {
        @Override
        public void run() {
            mPostedShow = false;
            if (!mDismissed) {
                mStartTime = System.currentTimeMillis(); //启动时间设置为当前时间
                setVisibility(VISIBLE);
            }
        }
    };

    int mMinWidth;
    int mMaxWidth;
    int mMinHeight;
    int mMaxHeight;

    private Indicator mIndicator;
    private int mIndicatorColor;

    private boolean mShouldStartAnimationDrawable;

    public LoadingView(Context context) {
        super(context);
        init(context, null, 0, 0);

    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, R.style.AVLoadingIndicatorView);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, R.style.AVLoadingIndicatorView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, R.style.AVLoadingIndicatorView);
    }

    @Override
    protected boolean verifyDrawable(@NonNull Drawable who) {
        return who == mIndicator || super.verifyDrawable(who);
    }

    /**
     * 根据显示隐藏判断是会否开启关闭动画
     *
     * @param visibility visibilityId
     */
    @Override
    public void setVisibility(int visibility) {
        if (getVisibility() != visibility) {
            super.setVisibility(visibility);
            if (visibility == GONE || visibility == INVISIBLE) {
                stopAnimation();
            } else {
                startAnimation();
            }
        }
    }

    /**
     * 根据显示隐藏判断是会否开启关闭动画
     *
     * @param changedView 改变的view
     * @param visibility  visibilityId
     */
    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == GONE || visibility == INVISIBLE) {
            stopAnimation();
        } else {
            startAnimation();
        }
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable drawable) {
        if (verifyDrawable(drawable)) {
            final Rect dirty = drawable.getBounds();
            final int scrollX = getScrollX() + getPaddingLeft();
            final int scrollY = getScrollY() + getPaddingTop();
            invalidate(dirty.left + scrollX, dirty.top + scrollY, dirty.right + scrollX, dirty.bottom + scrollY);
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        updateDrawableBounds(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTrack(canvas);
    }

    /**
     * 测量方法重写,进行计算精度,重新赋值
     *
     * @param widthMeasureSpec  widthMeasureSpec
     * @param heightMeasureSpec heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int dw = 0;
        int dh = 0;
        final Drawable drawable = mIndicator;
        if (drawable != null) {
            dw = Math.max(mMinWidth, Math.min(mMaxWidth, drawable.getIntrinsicWidth()));
            dh = Math.max(mMinHeight, Math.min(mMaxHeight, drawable.getIntrinsicHeight()));
        }
        updateDrawableState();
        dw += getPaddingLeft() + getPaddingRight();
        dh += getPaddingTop() + getPaddingBottom();
        final int measuredWidth = resolveSizeAndState(dw, widthMeasureSpec, 0);
        final int measuredHeight = resolveSizeAndState(dh, heightMeasureSpec, 0);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        updateDrawableState();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void drawableHotspotChanged(float x, float y) {
        super.drawableHotspotChanged(x, y);
        if (mIndicator != null) {
            mIndicator.setHotspot(x, y);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
        removeCallbacks();
    }

    @Override
    protected void onDetachedFromWindow() {
        stopAnimation();
        // This should come after stopAnimation(), otherwise an invalidate message remains in the
        // queue, which can prevent the entire view hierarchy from being GC'ed during a rotation
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    /**
     * 移除线程 防止GC
     */
    private void removeCallbacks() {
        removeCallbacks(mDelayedHide);
        removeCallbacks(mDelayedShow);
    }

    /**
     * 更新mIndicator drawable状态
     */
    private void updateDrawableState() {
        final int[] state = getDrawableState();
        if (mIndicator != null && mIndicator.isStateful()) {
            mIndicator.setState(state);
        }
    }

    /**
     * 平移画布并且更新动画开启状态
     *
     * @param canvas 画布
     */
    private void drawTrack(Canvas canvas) {
        final Drawable drawable = mIndicator;
        if (drawable != null) {
            // 保存画布
            final int saveCount = canvas.save();
            // 平移 padding
            canvas.translate(getPaddingLeft(), getPaddingTop());
            drawable.draw(canvas);
            canvas.restoreToCount(saveCount);
            if (mShouldStartAnimationDrawable) {
                ((Animatable) drawable).start();
                mShouldStartAnimationDrawable = false;
            }
        }
    }

    /**
     * 更改 mIndicator 的bounds
     *
     * @param w width
     * @param h height
     */
    private void updateDrawableBounds(int w, int h) {
        w -= getPaddingRight() + getPaddingLeft();
        h -= getPaddingTop() + getPaddingBottom();

        int right = w;
        int bottom = h;
        int top = 0;
        int left = 0;

        if (mIndicator != null) {
            // Maintain aspect ratio. Certain kinds of animated drawables
            // get very confused otherwise.
            final int intrinsicWidth = mIndicator.getIntrinsicWidth();
            final int intrinsicHeight = mIndicator.getIntrinsicHeight();
            final float intrinsicAspect = (float) intrinsicWidth / intrinsicHeight;
            final float boundAspect = (float) w / h;
            if (intrinsicAspect != boundAspect) {
                if (boundAspect > intrinsicAspect) {
                    // New width is larger. Make it smaller to match height.
                    final int width = (int) (h * intrinsicAspect);
                    left = (w - width) / 2;
                    right = left + width;
                } else {
                    // New height is larger. Make it smaller to match width.
                    final int height = (int) (w * (1 / intrinsicAspect));
                    top = (h - height) / 2;
                    bottom = top + height;
                }
            }
            mIndicator.setBounds(left, top, right, bottom);
        }
    }

    /**
     * 初始化参数
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        mMinWidth = 24;
        mMaxWidth = 48;
        mMinHeight = 24;
        mMaxHeight = 48;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AVLoadingIndicatorView, defStyleAttr, defStyleRes);
        mMinWidth = a.getDimensionPixelSize(R.styleable.AVLoadingIndicatorView_minWidth, mMinWidth);
        mMaxWidth = a.getDimensionPixelSize(R.styleable.AVLoadingIndicatorView_maxWidth, mMaxWidth);
        mMinHeight = a.getDimensionPixelSize(R.styleable.AVLoadingIndicatorView_minHeight, mMinHeight);
        mMaxHeight = a.getDimensionPixelSize(R.styleable.AVLoadingIndicatorView_maxHeight, mMaxHeight);
        String indicatorName = a.getString(R.styleable.AVLoadingIndicatorView_indicatorName);
        mIndicatorColor = a.getColor(R.styleable.AVLoadingIndicatorView_indicatorColor, Color.WHITE);
        setIndicator(indicatorName); //indicatorName没值在方法里已经处理了
        if (mIndicator == null) {
            setIndicator(DEFAULT_INDICATOR);
        }
        a.recycle();
    }

    /**
     * 设置具体的loading
     *
     * @param indicator 具体的loading对象
     */
    public void setIndicator(Indicator indicator) {
        if (mIndicator != indicator) {
            if (mIndicator != null) {
                mIndicator.setCallback(null);
                unscheduleDrawable(mIndicator);
            }
            mIndicator = indicator;
            setIndicatorColor(mIndicatorColor);
            if (indicator != null) {
                indicator.setCallback(this);
            }
            postInvalidate();
        }
    }

    /**
     * 根据包名来设置具体的loading
     *
     * @param indicatorName loading对象的包名
     */
    public void setIndicator(String indicatorName) {
        if (TextUtils.isEmpty(indicatorName))
            return;
        StringBuilder drawableClassName = new StringBuilder();
        if (!indicatorName.contains(".")) {
            String className = getClass().getPackage().getName();
            drawableClassName.append(className)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(indicatorName);

        try {
            Class<?> drawableClass = Class.forName(drawableClassName.toString());
            Indicator indicator = (Indicator) drawableClass.newInstance();
            setIndicator(indicator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Indicator getIndicator() {
        return mIndicator;
    }

    /**
     * 设置loading的颜色 ,支持以下格式
     * setIndicatorColor(0xFF00FF00)
     * or
     * setIndicatorColor(Color.BLUE)
     * or
     * setIndicatorColor(Color.parseColor("#FF4081"))
     * or
     * setIndicatorColor(0xFF00FF00)
     * or
     * setIndicatorColor(getResources().getColor(android.R.color.black))
     *
     * @param color
     */
    public void setIndicatorColor(int color) {
        this.mIndicatorColor = color;
        mIndicator.setColor(color);
    }


    public int getIndicatorColor() {
        return mIndicatorColor;
    }

    /**
     * 开启动画
     */
    private void startAnimation() {
        if (getVisibility() != VISIBLE) {
            return;
        }
        if (mIndicator != null) {
            mShouldStartAnimationDrawable = true;
        }
        postInvalidate();
    }

    /**
     * 关闭动画
     */
    private void stopAnimation() {
        if (mIndicator != null) {
            mIndicator.stop();
            mShouldStartAnimationDrawable = false;
        }
        postInvalidate();
    }

    /**
     * 平滑的显示
     */
    public void smoothToShow() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in));
        show();
    }

    /**
     * 平滑的隐藏
     */
    public void smoothToHide() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out));
        hide();
    }

    /**
     * 隐藏
     */
    public void hide() {
        mDismissed = true;
        removeCallbacks(mDelayedShow);
        long diff = System.currentTimeMillis() - mStartTime;
        if (diff >= MIN_SHOW_TIME || mStartTime == -1) {
            // 如果间隔时间大于最大的显示时间 || 根本就没显示
            setVisibility(GONE);
        } else {
            if (!mPostedHide) {
                postDelayed(mDelayedHide, MIN_SHOW_TIME - diff);
                mPostedHide = true;
            }
        }
    }

    /**
     * 显示
     */
    public void show() {
        mStartTime = -1;
        mDismissed = false;
        removeCallbacks(mDelayedHide);
        if (!mPostedShow) {
            postDelayed(mDelayedShow, MIN_DELAY);
            mPostedShow = true;
        }
    }
}
