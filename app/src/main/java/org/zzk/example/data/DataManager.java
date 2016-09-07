package org.zzk.example.data;

import android.content.SharedPreferences;
import android.util.Log;

import org.zzk.example.bean.GankBean;
import org.zzk.example.data.api.GankApi;
import org.zzk.example.rxjava.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by zwl on 16/9/5.
 */
public class DataManager {

    private GankApi mGankApi;

    @Inject
    public DataManager(GankApi gankApi){
        this.mGankApi = gankApi;
    }

    public Observable<Boolean> getIsFirstIn(final SharedPreferences preferences){
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean isFirstIn = preferences.getBoolean("isFirstIn", true);
                subscriber.onNext(isFirstIn);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<List<GankBean.Gank>> getGankData(String type, int page){
        return mGankApi.getGankData(type, page)
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
