package org.zzk.example.ui.splash;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.zzk.example.data.DataManager;
import org.zzk.example.injector.PerActivity;
import org.zzk.example.ui.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by zwl on 16/9/5.
 */
@PerActivity
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public SplashPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    /**
     * 检测是否第一次启动
     */
    @Override
    public void checkIsFirstIn(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("isFirstIn", Context.MODE_PRIVATE);
        Subscription subscription = Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .flatMap(aLong -> mDataManager.getIsFirstIn(preferences))
                .subscribe((Action1<Boolean>) aboolean -> {
                    if (aboolean) {
                        mView.readyGoGuide();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("isFirstIn", false);
                        editor.commit();
                    } else mView.readyGoMain();
                });
        mRxManager.add(subscription);
    }
}
