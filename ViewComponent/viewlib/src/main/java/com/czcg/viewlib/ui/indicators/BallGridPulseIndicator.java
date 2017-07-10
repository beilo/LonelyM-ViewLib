package com.czcg.viewlib.ui.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.czcg.viewlib.ui.Indicator;

import java.util.ArrayList;

/** 九宫格
 * Created by leipe on 2017/7/7.
 */

public class BallGridPulseIndicator extends Indicator {
    private static final int ALPHA = 255;

    private static final float SCALE = 1.0f;

    int[] alphas = new int[]{ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA};

    float[] scaleFloats = new float[]{SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE};

    /**
     * 阅读此处代码推荐在纸上画图
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing = 4; //每两个之间的间隔大小
        // 半径计算公式
        // (view的宽度-4个间隔) / 三个圆 = 直径 / 2 = 半径
        float radius = (getWidth() - circleSpacing * 4) / 6;
        // 左边边框到第一个点中心点的距离 = 宽度/2 - (1个直径+1个间隔)
        float x = getWidth() / 2 - (radius * 2 + circleSpacing);
        // 形状是正方形,所以也用宽度就行了
        float y = getWidth() / 2 - (radius * 2 + circleSpacing);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                canvas.save();
                // x + 直径 * 对应的下标 + 间隔 * 对应的下标
                float translateX = x + (radius * 2 * j) + circleSpacing * j; //此处下标为 j
                float translateY = x + (radius * 2 * i) + circleSpacing * i; //此处下标为 i
                // 平移画布
                canvas.translate(translateX, translateY);
                canvas.scale(scaleFloats[3 * i + j], scaleFloats[3 * i + j]);
                paint.setAlpha(alphas[3 * i + j]);
                canvas.drawCircle(0, 0, radius, paint);
                canvas.restore();
            }
        }
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimator() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        // 下面两排数组没有任何意义,随便瞎搞的
        int[] durations = {720, 1020, 1280, 1420, 1450, 1180, 870, 1450, 1060};
        int[] delays = {-60, 250, -170, 480, 310, 30, 460, 780, 450};
        for (int i = 0; i < 9; i++) {
            final int index = i;
            // 缩放动画
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.5f, 1);
            scaleAnim.setDuration(durations[i]);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            addUpdateListener(scaleAnim, new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });

            // 透明度动画
            ValueAnimator alphaAnim = ValueAnimator.ofInt(255, 210, 122, 255);
            alphaAnim.setDuration(durations[i]);
            alphaAnim.setRepeatCount(-1);
            alphaAnim.setStartDelay(delays[i]);
            addUpdateListener(alphaAnim, new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    alphas[index] = (int) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            animators.add(scaleAnim);
            animators.add(alphaAnim);

        }
        return animators;
    }
}
