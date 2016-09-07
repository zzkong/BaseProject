package org.zzk.example.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.zzk.example.R;


/**
 * Created by zzk on 16/6/22.
 */
public class CustomLoadMoreView extends RelativeLayout implements ILoadMoreView {

    private TextView mTvMessage;
    private LinearLayout mLayout;
    private ProgressBar mPbLoading;

    public CustomLoadMoreView(Context context) {
        super(context);
        init(context);
    }

    public CustomLoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomLoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.loading_view_final_footer_custom, this);
        mLayout = (LinearLayout) findViewById(R.id.loading_layout);
        mPbLoading = (ProgressBar) findViewById(R.id.pb_loading);
        mTvMessage = (TextView) findViewById(R.id.tv_loading_msg);
    }

    @Override
    public void showNormal() {
        mPbLoading.setVisibility(View.GONE);
        // mTvMessage.setText(R.string.loading_view_click_loading_more);
        mTvMessage.setText("");
    }

    @Override
    public void hideNormal() {
        mPbLoading.setVisibility(View.GONE);
        // mTvMessage.setText(R.string.loading_view_click_loading_more);
        mTvMessage.setVisibility(GONE);
    }

    @Override
    public void showNoMore() {
        mPbLoading.setVisibility(View.GONE);
        //   mTvMessage.setText(R.string.loading_view_no_more);
        mTvMessage.setText("");
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
        mTvMessage.setText(R.string.loading_view_loading);
    }

    @Override
    public void showFail() {
        mPbLoading.setVisibility(View.GONE);
        mTvMessage.setText(R.string.loading_view_net_error);
    }

    @Override
    public View getFooterView() {
        return this;
    }

}

