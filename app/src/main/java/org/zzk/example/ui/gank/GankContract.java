package org.zzk.example.ui.gank;

import org.zzk.example.adapter.commonadapter.CommonAdapter;
import org.zzk.example.bean.GankBean;
import org.zzk.example.loadmore.RecyclerViewFinal;
import org.zzk.example.ui.base.IPresenter;

import java.util.List;

/**
 * Created by zwl on 16/9/6.
 */
public interface GankContract {

    interface View {
        void initAdapter(CommonAdapter commonAdapter);

        void refresh(List<GankBean.Gank> ganks);

        void showError();

        void loadMore(List<GankBean.Gank> ganks);
    }

    interface Presenter extends IPresenter<View> {

        void initAdapter(RecyclerViewFinal recyclerViewFinal);

        void getGankData(String title, int page, boolean isRefresh);
    }
}
