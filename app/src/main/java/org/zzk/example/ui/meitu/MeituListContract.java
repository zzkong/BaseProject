package org.zzk.example.ui.meitu;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.zzk.example.adapter.commonadapter.CommonAdapter;
import org.zzk.example.bean.ImageBean;
import org.zzk.example.ui.base.IPresenter;

import java.util.List;

/**
 * Created by zwl on 16/9/30.
 */

public interface MeituListContract {

    interface View {

        void initAdapter(CommonAdapter commonAdapter, LRecyclerViewAdapter lRecyclerViewAdapter);

        void refresh(List<ImageBean> imageList);

        void loadMore(List<ImageBean> imageList);
    }

    interface Presenter extends IPresenter<View> {

        void initAdapter(LRecyclerView recyclerView);

        void getImageByKey(String title, int page, boolean isRefresh);
    }

}
