package org.zzk.example.ui.gank;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;

import org.zzk.example.R;
import org.zzk.example.adapter.commonadapter.CommonAdapter;
import org.zzk.example.adapter.commonadapter.ViewHolder;
import org.zzk.example.bean.GankBean;
import org.zzk.example.data.DataManager;
import org.zzk.example.injector.PerFragment;
import org.zzk.example.loadmore.RecyclerViewFinal;
import org.zzk.example.ui.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by zwl on 16/9/6.
 */
@PerFragment
public class GankPresenter extends BasePresenter<GankContract.View> implements GankContract.Presenter{

    @Inject
    public GankPresenter(DataManager dataManager, Activity activity) {
        super(dataManager, activity);
    }

    @Override
    public void initAdapter(RecyclerViewFinal recyclerViewFinal) {
        List<GankBean.Gank> ganks = new ArrayList<>();
        recyclerViewFinal.setLoadMoreView(mActivity);
        recyclerViewFinal.setLayoutManager(new LinearLayoutManager(mActivity));
        CommonAdapter commonAdapter = new CommonAdapter<GankBean.Gank>(mActivity, R.layout.item_gank, ganks) {
            @Override
            protected void convert(ViewHolder holder, GankBean.Gank gank, int position) {
                holder.setText(R.id.item_desc, gank.desc);
                holder.setSimpleDrawByUrl(R.id.item_avator, gank.url);
            }
        };
        recyclerViewFinal.setAdapter(commonAdapter);
        recyclerViewFinal.setHasLoadMore(true);
        mView.initAdapter(commonAdapter);
    }

    @Override
    public void getGankData(String title, int page, boolean isRefresh) {
        Subscription subscription = mDataManager.getGankData(title, page)
                .subscribe(ganks -> {
                    if(isRefresh) mView.refresh(ganks);
                    else mView.loadMore(ganks);
                }, throwable -> {
                    mView.showError();
                });
        addSubscribe(subscription);
    }
}
