package org.zzk.example.views;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ScrollView;

/**
 * Created by zwl on 16/8/29.
 */
public class SwipeBackLayout extends ViewGroup {

    public enum DragEdge {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    private DragEdge mDragEdge = DragEdge.LEFT;

    public void setDragEdge(DragEdge dragEdge){
        this.mDragEdge = dragEdge;
    }

    private static final double AUTO_FINISHED_SPEED_LIMIT = 2000.0;

    private View target;

    private View scrollChild;

    private int verticalDragRange = 0;

    private int horizontalDragRange = 0;

    private int draggingState = 0;

    private int draggingOffset;

    private int triggerRange = 50;

    private boolean isCanTrigger = false;

    private float downX = 0;

    private boolean enablePullToBack = true;

    private static final float BACK_FACTOR = 0;

    private static final int BACK_RANGE = 200;

    private float finishAnchor = 0;

    public void setFinishAnchor(float offset){
        finishAnchor = offset;
    }

    private boolean enableFlingBack = true;

    public void setEnableFlingBack(boolean b){
        enableFlingBack = b;
    }

    private SwipeBackListener mSwipeBackListener;

    @Deprecated
    public void setOnPullToBackListener(SwipeBackListener listener) {
        mSwipeBackListener = listener;
    }

    public void setOnSwipeBackListener(SwipeBackListener listener) {
        mSwipeBackListener = listener;
    }

    public void setTriggerRange(int triggerRange) {
        this.triggerRange = triggerRange;
    }

    public SwipeBackLayout(Context context) {
        this(context, null);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

//        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelperCallBack());
    }

    public void setScrollChild(View view) {
        scrollChild = view;
    }

    public void setEnablePullToBack(boolean b) {
        enablePullToBack = b;
    }

    private void ensureTarget() {
        if (target == null) {
            if (getChildCount() > 1) {
                throw new IllegalStateException("SwipeBackLayout must contains only one direct child");
            }
            target = getChildAt(0);

            if (scrollChild == null && target != null) {
                if (target instanceof ViewGroup) {
                    findScrollView((ViewGroup) target);
                } else {
                    scrollChild = target;
                }

            }
        }
    }

    /**
     * Find out the scrollable child view from a ViewGroup.
     *
     * @param viewGroup
     */
    private void findScrollView(ViewGroup viewGroup) {
        scrollChild = viewGroup;
        if (viewGroup.getChildCount() > 0) {
            int count = viewGroup.getChildCount();
            View child;
            for (int i = 0; i < count; i++) {
                child = viewGroup.getChildAt(i);
                if (child instanceof AbsListView || child instanceof ScrollView || child instanceof ViewPager || child instanceof WebView) {
                    scrollChild = child;
                    return;
                }
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (getChildCount() == 0) return;

        View child = getChildAt(0);

        int childWidth = width - getPaddingLeft() - getPaddingRight();
        int childHeight = height - getPaddingTop() - getPaddingBottom();
        int childLeft = getPaddingLeft();
        int childTop = getPaddingTop();
        int childRight = childLeft + childWidth;
        int childBottom = childTop + childHeight;
        child.layout(childLeft, childTop, childRight, childBottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() > 1) {
            throw new IllegalStateException("SwipeBackLayout must contains only one direct child.");
        }

        if (getChildCount() > 0) {
            int measureWidth = MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY);
            int measureHeight = MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY);
            getChildAt(0).measure(measureWidth, measureHeight);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        verticalDragRange = h;
        horizontalDragRange = w;

        switch (mDragEdge) {
            case TOP:
            case BOTTOM:
                finishAnchor = finishAnchor > 0 ? finishAnchor : verticalDragRange * BACK_FACTOR;
                break;
            case LEFT:
            case RIGHT:
                finishAnchor = finishAnchor > 0 ? finishAnchor : horizontalDragRange * BACK_FACTOR;
                break;
        }
    }

    private int getDragRange() {
        switch (mDragEdge) {
            case TOP:
            case BOTTOM:
                return verticalDragRange;
            case LEFT:
            case RIGHT:
                return horizontalDragRange;
            default:
                return verticalDragRange;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean handled = false;
        ensureTarget();
        if (isEnabled() && isTrigger(ev)) {
//            handled = viewDragHelper.shouldInterceptTouchEvent(ev);
            handled = true;
        } else {
//            viewDragHelper.cancel();
        }
        return !handled ? super.onInterceptTouchEvent(ev) : handled;
    }

    /**
     * decide whether should trigger drag action or not
     *
     * @param ev
     * @return true if (x,y) point of ACTION_DOWN is in the range of {@link SwipeBackLayout#triggerRange} ,false otherwise
     */
    private boolean isTrigger(MotionEvent ev) {

        if (MotionEvent.ACTION_DOWN == ev.getAction()) {
            switch (mDragEdge) {
                case LEFT:
                    isCanTrigger = ev.getX() < triggerRange;
                    break;
                case TOP:
                    isCanTrigger = ev.getY() < triggerRange;
                    break;
                case RIGHT:
                    isCanTrigger = this.getWidth() - ev.getX() < triggerRange;
                    break;
                case BOTTOM:
                    isCanTrigger = this.getHeight() - ev.getY() < triggerRange;
                    break;
                default:
                    break;
            }
        } else {
            return isCanTrigger;
        }
        return isCanTrigger;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (enablePullToBack && isTrigger(event)) {
//            viewDragHelper.processTouchEvent(event);
            if (isCanFinish(event)) {
                finish();
            }

            return true;
        } else {
            return false;
        }
    }

    private boolean isCanFinish(MotionEvent event) {
        boolean result = false;
        float currentX = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                currentX = event.getRawX();
                break;
            default:
                break;
        }
        result = currentX - downX > BACK_RANGE;
        return result;
    }

    @Override
    public void computeScroll() {
//        if (viewDragHelper.continueSettling(true)) {
//            //ViewCompat.postInvalidateOnAnimation(this);
//        }
    }

    public boolean canChildScrollUp() {
        return ViewCompat.canScrollVertically(scrollChild, -1);
    }

    public boolean canChildScrollDown() {
        return ViewCompat.canScrollVertically(scrollChild, 1);
    }

    private boolean canChildScrollRight() {
        return ViewCompat.canScrollHorizontally(scrollChild, -1);
    }

    private boolean canChildScrollLeft() {
        return ViewCompat.canScrollHorizontally(scrollChild, 1);
    }

    private void finish() {
        Activity act = (Activity) getContext();
        act.finish();
    }

//    private class ViewDragHelperCallBack extends ViewDragHelper.Callback {
//
//        @Override
//        public boolean tryCaptureView(View child, int pointerId) {
//            return child == target && enablePullToBack;
//        }
//
//        @Override
//        public int getViewVerticalDragRange(View child) {
//            return verticalDragRange;
//        }
//
//        @Override
//        public int getViewHorizontalDragRange(View child) {
//            return horizontalDragRange;
//        }
//
//        @Override
//        public int clampViewPositionVertical(View child, int top, int dy) {
//
//            int result = 0;
//
//            if (dragEdge == DragEdge.TOP && !canChildScrollUp() && top > 0) {
//                final int topBound = getPaddingTop();
//                final int bottomBound = verticalDragRange;
//                result = Math.min(Math.max(top, topBound), bottomBound);
//            } else if (dragEdge == DragEdge.BOTTOM && !canChildScrollDown() && top < 0) {
//                final int topBound = -verticalDragRange;
//                final int bottomBound = getPaddingTop();
//                result = Math.min(Math.max(top, topBound), bottomBound);
//            }
//
//            return result;
//        }
//
//        @Override
//        public int clampViewPositionHorizontal(View child, int left, int dx) {
//
//            int result = 0;
//
//            if (dragEdge == DragEdge.LEFT && !canChildScrollRight() && left > 0) {
//                final int leftBound = getPaddingLeft();
//                final int rightBound = horizontalDragRange;
//                result = Math.min(Math.max(left, leftBound), rightBound);
//            } else if (dragEdge == DragEdge.RIGHT && !canChildScrollLeft() && left < 0) {
//                final int leftBound = -horizontalDragRange;
//                final int rightBound = getPaddingLeft();
//                result = Math.min(Math.max(left, leftBound), rightBound);
//            }
//
//            return result;
//        }
//
//        @Override
//        public void onViewDragStateChanged(int state) {
//            if (state == draggingState) return;
//
//            if ((draggingState == ViewDragHelper.STATE_DRAGGING || draggingState == ViewDragHelper.STATE_SETTLING) &&
//                    state == ViewDragHelper.STATE_IDLE) {
//                // the view stopped from moving.
//                if (draggingOffset == getDragRange()) {
//                    finish();
//                }
//            }
//
//            draggingState = state;
//        }
//
//
//        @Override
//        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
//            switch (dragEdge) {
//                case TOP:
//                case BOTTOM:
//                    draggingOffset = Math.abs(top);
//                    break;
//                case LEFT:
//                case RIGHT:
//                    draggingOffset = Math.abs(left);
//                    break;
//                default:
//                    break;
//            }
//
//            //The proportion of the sliding.
//            float fractionAnchor = (float) draggingOffset / finishAnchor;
//            if (fractionAnchor >= 1) fractionAnchor = 1;
//
//            float fractionScreen = (float) draggingOffset / (float) getDragRange();
//            if (fractionScreen >= 1) fractionScreen = 1;
//
//            if (swipeBackListener != null) {
//                swipeBackListener.onViewPositionChanged(fractionAnchor, fractionScreen);
//            }
//        }
//
//        @Override
//        public void onViewReleased(View releasedChild, float xvel, float yvel) {
//            if (draggingOffset == 0) return;
//
//            if (draggingOffset == getDragRange()) return;
//
//            boolean isBack = false;
//
//            if (enableFlingBack && backBySpeed(xvel, yvel)) {
//                isBack = !canChildScrollUp();
//            } else if (draggingOffset >= finishAnchor) {
//                isBack = true;
//            } else if (draggingOffset < finishAnchor) {
//                isBack = false;
//            }
//
//            int finalLeft;
//            int finalTop;
//            switch (dragEdge) {
//                case LEFT:
//                    finalLeft = isBack ? horizontalDragRange : 0;
//                    smoothScrollToX(finalLeft);
//                    break;
//                case RIGHT:
//                    finalLeft = isBack ? -horizontalDragRange : 0;
//                    smoothScrollToX(finalLeft);
//                    break;
//                case TOP:
//                    finalTop = isBack ? verticalDragRange : 0;
//                    smoothScrollToY(finalTop);
//                    break;
//                case BOTTOM:
//                    finalTop = isBack ? -verticalDragRange : 0;
//                    smoothScrollToY(finalTop);
//                    break;
//            }
//
//        }
//    }

    private boolean backBySpeed(float xvel, float yvel) {
        switch (mDragEdge) {
            case TOP:
            case BOTTOM:
                if (Math.abs(yvel) > Math.abs(xvel) && Math.abs(yvel) > AUTO_FINISHED_SPEED_LIMIT) {
                    return mDragEdge == DragEdge.TOP ? !canChildScrollUp() : !canChildScrollDown();
                }
                break;
            case LEFT:
            case RIGHT:
                if (Math.abs(xvel) > Math.abs(yvel) && Math.abs(xvel) > AUTO_FINISHED_SPEED_LIMIT) {
                    return mDragEdge == DragEdge.LEFT ? !canChildScrollLeft() : !canChildScrollRight();
                }
                break;
        }
        return false;
    }

//    private void smoothScrollToX(int finalLeft) {
//        if (viewDragHelper.settleCapturedViewAt(finalLeft, 0)) {
//            ViewCompat.postInvalidateOnAnimation(SwipeBackLayout.this);
//        }
//    }

//    private void smoothScrollToY(int finalTop) {
//        if (viewDragHelper.settleCapturedViewAt(0, finalTop)) {
//            ViewCompat.postInvalidateOnAnimation(SwipeBackLayout.this);
//        }
//    }

    public interface SwipeBackListener {

        /**
         * Return scrolled fraction of the layout.
         *
         * @param fractionAnchor relative to the anchor.
         * @param fractionScreen relative to the screen.
         */
        void onViewPositionChanged(float fractionAnchor, float fractionScreen);

    }
}
