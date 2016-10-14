package org.zzk.example.data;

import android.content.SharedPreferences;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by zwl on 16/9/5.
 */
public class DataManager {

    @Inject
    public DataManager(){

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
}
