package org.zzk.example.data.api.meitu;

import org.zzk.example.bean.ImageListBean;
import org.zzk.example.rxjava.RxSchedulers;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zwl on 16/10/13.
 */
public class MeituApi {

    private MeituService mMeituService;

    @Inject
    public MeituApi(MeituService meituService){
        this.mMeituService = meituService;
    }

    public Observable<ImageListBean> getImages(String title, int page){
        return mMeituService.getImageList(title, "全部", page*20, 20, 1)
                .compose(RxSchedulers.schedulersTransformer);
    }
}
