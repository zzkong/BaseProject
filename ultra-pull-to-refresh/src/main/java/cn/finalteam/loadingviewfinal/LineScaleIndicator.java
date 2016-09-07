package cn.finalteam.loadingviewfinal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.nineoldandroids.animation.Animator;

import java.util.List;

/**
 * Created by Jack on 2015/10/19.
 */
public class LineScaleIndicator extends BaseIndicatorController {

    public static final float SCALE = 1.0f;

    float[] scaleYFloats = new float[]{SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE};

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float translateX = getWidth() / 40;
        float translateY = getHeight() / 2;
        int h = 35;
        int h1 = 20;
        int h2 = 15;
        for (int i = 0; i < 13; i++) {
            if (i < 5) {
                canvas.save();
                canvas.translate((2 + i * 2) * translateX - translateX / 2, translateY);
                canvas.scale(SCALE, scaleYFloats[i]);
                paint.setColor(Color.RED);
                RectF rectF = new RectF(-1.5f, -h, 1.5f, h);
                canvas.drawRoundRect(rectF, 1, 1, paint);
                canvas.restore();
                h = h - 5;
            } else if (5 <= i && i < 9) {
                canvas.save();
                canvas.translate((2 + i * 2) * translateX - translateX / 2, translateY);
                canvas.scale(SCALE, scaleYFloats[i]);
                paint.setColor(Color.RED);
                RectF rectF = new RectF(-1.5f, -h1, 1.5f, h1);
                canvas.drawRoundRect(rectF, 1, 1, paint);
                canvas.restore();
                h1 = h1 + 5;
            } else {
                canvas.save();
                canvas.translate((2 + i * 2) * translateX - translateX / 2, translateY);
                canvas.scale(SCALE, scaleYFloats[i]);
                paint.setColor(Color.RED);
                RectF rectF = new RectF(-1.5f, -h2, 1.5f, h2);
                canvas.drawRoundRect(rectF, 1, 1, paint);
                canvas.restore();
                h2 = h2 + 5;
            }
        }
    }

    @Override
    public List<Animator> createAnimation() {
//        List<Animator> animators=new ArrayList<>();
//        long[] delays=new long[]{90,10,40,30,150,200, 10,60,90,10};
//        for (int i = 0; i < 10; i++) {
//            final int index=i;
//            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1, 0.4f, 1);
//            scaleAnim.setDuration(1000);
//            scaleAnim.setRepeatCount(-1);
//            scaleAnim.setStartDelay(delays[i]);
//            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    scaleYFloats[index] = (float) animation.getAnimatedValue();
//                    postInvalidate();
//                }
//            });
//            scaleAnim.start();
//            animators.add(scaleAnim);
//        }
//        return animators;
        return null;
    }

}
