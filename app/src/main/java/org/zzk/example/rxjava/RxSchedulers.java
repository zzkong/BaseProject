package org.zzk.example.rxjava;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Rxjava线程调度
 * Created by zwl on 16/8/11.
 */
public class RxSchedulers {

    public static Observable.Transformer schedulersTransformer = new Observable.Transformer(){

        @Override
        public Object call(Object observable) {
            return ((Observable)observable).subscribeOn(Schedulers.newThread())
                    .unsubscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };
}
