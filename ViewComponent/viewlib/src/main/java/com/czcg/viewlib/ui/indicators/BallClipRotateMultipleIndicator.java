package com.czcg.viewlib.ui.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.czcg.viewlib.ui.Indicator;

import java.util.ArrayList;

/**
 * Created by leipe on 2017/7/7.
 */

public class BallClipRotateMultipleIndicator extends Indicator {

    float scaleFloat = 1;
    float degrees = 0;


    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        float circleSpacing = 12;
        float x = getWidth() / 2;
        float y = getHeight() / 2;

        canvas.save();
        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.rotate(degrees);

        // 画大圆弧
        float[] bStartAngles = new float[]{135, -45};
        for (int i = 0; i < 2; i++) {
            float left = -x + circleSpacing;
            float top = -y + circleSpacing;
            float right = x - circleSpacing;
            float bottom = y - circleSpacing;
            RectF rectF = new RectF(left, top, right, bottom);
            canvas.drawArc(rectF, bStartAngles[i], 90, false, paint);
        }

        canvas.restore();
        canvas.translate(x, y);
        canvas.scale(scaleFloat, scaleFloat);
        canvas.rotate(-degrees);
        // 画小圆弧 / 1.8f
        float[] sStartAngles = new float[]{225, 45};
        for (int i = 0; i < 2; i++) {
            float left = -x / 1.8f + circleSpacing;
            float top = -y / 1.8f + circleSpacing;
            float right = x / 1.8f - circleSpacing;
            float bottom = y / 1.8f - circleSpacing;
            RectF rectF = new RectF(left, top, right, bottom);
            canvas.drawArc(rectF, sStartAngles[i], 90, false, paint);
        }
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimator() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();

        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1, 0.6f, 1);
        scaleAnimator.setDuration(1000);
        scaleAnimator.setRepeatCount(-1);

        addUpdateListener(scaleAnimator, new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                scaleFloat = (float) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });

        // 注意这边不能用ofInt,否则valueAnimator.getAnimatedValue()返回Integer会导致强转报错
        ValueAnimator degreesAnimator = ValueAnimator.ofFloat(0, 180, 360);
        degreesAnimator.setDuration(1000);
        degreesAnimator.setRepeatCount(-1);
        addUpdateListener(degreesAnimator, new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                degrees = (float) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });

        animators.add(scaleAnimator);
        animators.add(degreesAnimator);

        return animators;
    }
}
