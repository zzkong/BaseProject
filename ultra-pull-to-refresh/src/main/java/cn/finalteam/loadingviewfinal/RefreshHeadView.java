package cn.finalteam.loadingviewfinal;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.finalteam.loadingviewfinal.indicator.PtrIndicator;
import cn.finalteam.loadingviewfinal.uptr.R;

/**
 * Created by zzk on 16/7/5.
 */
public class RefreshHeadView extends FrameLayout implements PtrUIHandler {

    private ImageView mDownImg, mLoadImg;
    private TextView mTextView;
    private ProgressBar circleProgress;
    private ObjectAnimator mObjectAnimator;
    public static final int MAX_PROGRESS = 100;
    private AnimationDrawable mAnimationDrawable;
    public static final String PROGRESS_PROPERTY = "progress";
    public static final long MS_IN_FUTURE = DateUtils.MINUTE_IN_MILLIS / 2;

    public RefreshHeadView(Context context) {
        super(context);
        init();
    }

    public RefreshHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_refresh_head, this);
        mDownImg = (ImageView) view.findViewById(R.id.refresh_image);
        mLoadImg = (ImageView) view.findViewById(R.id.load_image);
        mTextView = (TextView) view.findViewById(R.id.refresh_text);
        circleProgress = (ProgressBar) view.findViewById(R.id.circleProgress);

        // 当前进度
        circleProgress.setMax(MAX_PROGRESS);
        resetView();
        mObjectAnimator = ObjectAnimator
                .ofInt(circleProgress, PROGRESS_PROPERTY, circleProgress.getMax())
                .setDuration(400);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        mAnimationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.refresh_anim);
        mLoadImg.setBackgroundDrawable(mAnimationDrawable);
        mAnimationDrawable.start();

    }

    private void resetView() {
        mTextView.setText("刷新完成");
        circleProgress.setProgress(0);
        mLoadImg.setVisibility(GONE);
        circleProgress.setVisibility(GONE);
        mDownImg.setVisibility(GONE);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        resetView();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        if (frame.isPullToRefresh()) {
        } else {
            circleProgress.setVisibility(VISIBLE);
            mDownImg.setVisibility(VISIBLE);
            mTextView.setText("下拉可以刷新...");
        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mTextView.setText("玩命加载中...");
        mLoadImg.setVisibility(VISIBLE);
        circleProgress.setVisibility(GONE);
        mDownImg.setVisibility(GONE);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        circleProgress.setVisibility(GONE);
        resetView();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();
        float a = ptrIndicator.getCurrentPercent() - 0.5f;
        if (a > 1) {
            circleProgress.setProgress(circleProgress.getMax());
        } else if (a >= 0) {
            circleProgress.setProgress((int) (circleProgress.getMax() * a));
        }

        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                //  mTextView.setText("下拉刷新");
            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                mTextView.setText("松开即可刷新...");
            }
        }
    }
}
