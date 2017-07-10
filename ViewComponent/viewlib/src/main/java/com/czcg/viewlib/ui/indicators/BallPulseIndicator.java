package com.czcg.viewlib.ui.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.czcg.viewlib.ui.Indicator;

import java.util.ArrayList;

/** 简单的3个点
 * Created by leipe on 2017/7/7.
 */

public class BallPulseIndicator extends Indicator {
    public static final float SCALE = 1.0f;
    private float[] scaleFloats = new float[]{SCALE,
            SCALE,
            SCALE};

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing = 4;
        float radius = (Math.min(getWidth(), getHeight()) - circleSpacing * 2) / 6;
        float x = getWidth() / 2 - (radius * 2 + circleSpacing);
        float y = getHeight() / 2;
        for (int i = 0; i < 3; i++) {
            canvas.save();
            float translateX = x + (radius * 2) * i + circleSpacing * 1;
            canvas.translate(translateX,y);
            canvas.scale(scaleFloats[i],scaleFloats[i]);
            canvas.drawCircle(0,0,radius,paint);
            canvas.restore();
        }
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimator() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        int[] delays = {120, 240, 360};
        for (int i = 0; i < 3; i++) {
            final int index = i;

            ValueAnimator animator = ValueAnimator.ofFloat(1, 0.3F, 1);
            animator.setDuration(750); //设置间隔时间
            animator.setRepeatCount(-1); //设置循环次数
            animator.setStartDelay(delays[i]); //设置启动延迟时间
            animators.add(animator);

            addUpdateListener(animator, new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    scaleFloats[index] = (float) valueAnimator.getAnimatedValue();
                    postInvalidate();
                }
            });
        }
        return animators;
    }
}
