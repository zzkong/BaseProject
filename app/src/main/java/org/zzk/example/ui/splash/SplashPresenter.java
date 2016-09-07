package org.zzk.example.ui.splash;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.zzk.example.data.DataManager;
import org.zzk.example.injector.PerActivity;
import org.zzk.example.ui.BasePresenter;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by zwl on 16/9/5.
 */
@PerActivity
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter{

    @Inject
    public SplashPresenter(DataManager dataManager, Activity activity) {
        super(dataManager, activity);
    }

    @Override
    public void checkIsFirstIn(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("isFirstIn", Context.MODE_PRIVATE);
        Subscription subscription = mDataManager.getIsFirstIn(preferences)
                .subscribe(aboolean ->{
                    if(aboolean) {
                        mView.readyGoGuide();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("isFirstIn", false);
                        editor.commit();
                    }
                    else mView.readyGoMain();
                }, throwable -> {

                });
        addSubscribe(subscription);
    }
}
