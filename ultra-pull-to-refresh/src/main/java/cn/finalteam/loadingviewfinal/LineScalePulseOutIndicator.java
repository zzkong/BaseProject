package cn.finalteam.loadingviewfinal;


import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2015/10/19.
 */
public class LineScalePulseOutIndicator extends LineScaleIndicator {

    @Override
    public List<Animator> createAnimation() {
        List<Animator> animators = new ArrayList<>();
        long[] delays = new long[]{500, 400, 300, 200, 100, 100, 200, 300, 400, 500, 100, 200, 300};
        for (int i = 0; i < 13; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1f, 0.3f);
            scaleAnim.setDuration(700);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleYFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            scaleAnim.start();
            animators.add(scaleAnim);
        }
        return animators;
    }

}
