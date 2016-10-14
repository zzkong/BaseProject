package org.zzk.example.ui.gank;

import android.os.Bundle;

import org.zzk.example.R;
import org.zzk.example.adapter.commonadapter.CommonAdapter;
import org.zzk.example.bean.GankBean;
import org.zzk.example.loadmore.OnLoadMoreListener;
import org.zzk.example.loadmore.RecyclerViewFinal;
import org.zzk.example.ui.base.BaseFragment;

import java.util.List;

import butterknife.Bind;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;

/**
 * gank上拉 下拉页面
 * Created by zwl on 16/9/6.
 */
public class GankFragment extends BaseFragment<GankPresenter> implements GankContract.View, OnLoadMoreListener{
    @Bind(R.id.recycler_view)
    RecyclerViewFinal mRecyclerView;
    @Bind(R.id.refresh_view)
    PtrClassicFrameLayout mRefreshView;

    private String mTitle;
    private int mPage = 1;
    private CommonAdapter mAdapter;

    public static GankFragment newInstance(String title) {
        GankFragment gankFragment = new GankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        gankFragment.setArguments(bundle);
        return gankFragment;
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initEventAndData() {
        mTitle = getArguments().getString("title");
        mRefreshView.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPage = 1;
                mPresenter.getGankData(mTitle, mPage, true);
            }
        });
        mRecyclerView.setOnLoadMoreListener(this);
        mPresenter.initAdapter(mRecyclerView);
    }

    @Override
    protected void lazyLoadData() {
        mPresenter.getGankData(mTitle, mPage, true);
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initAdapter(CommonAdapter commonAdapter) {
        mAdapter = commonAdapter;
    }

    @Override
    public void refresh(List<GankBean.Gank> ganks) {
        mAdapter.clearAddallNotify(ganks);
        mRefreshView.onRefreshComplete();
    }

    @Override
    public void showError() {

    }

    @Override
    public void loadMore(List<GankBean.Gank> ganks) {
        mAdapter.addAllNotify(ganks);
        mRecyclerView.onLoadMoreComplete();
    }

    @Override
    public void loadMore() {
        mPage++;
        mPresenter.getGankData(mTitle, mPage, false);
    }
}
