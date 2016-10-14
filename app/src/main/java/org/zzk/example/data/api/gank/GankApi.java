package org.zzk.example.data.api.gank;

import org.zzk.example.bean.GankBean;
import org.zzk.example.rxjava.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zwl on 16/9/6.
 */
public class GankApi {

    private GankService mGankService;

    @Inject
    public GankApi(GankService gankService){
        this.mGankService = gankService;
    }


    public Observable<List<GankBean.Gank>> getGankData(String type, int page){
        return mGankService.getGankData(type, page)
                .compose(RxSchedulers.schedulersTransformer)
                .map(new Func1() {
                    @Override
                    public Object call(Object o) {
                        GankBean gankBean = (GankBean) o;
                        return gankBean.results;
                    }
                });
    }


}
