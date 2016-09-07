package org.zzk.example.ui;

import android.app.Activity;

import org.zzk.example.data.DataManager;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zwl on 16/9/5.
 */
public abstract class BasePresenter<T extends IView> implements IPresenter<T>{

    protected Activity mActivity;
    protected T mView;
    protected CompositeSubscription mCompositeSubscription;
    protected DataManager mDataManager;

    public BasePresenter(DataManager dataManager, Activity activity){
        this.mDataManager = dataManager;
        this.mActivity = activity;
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    protected void unSubscribe(){
        if(mCompositeSubscription != null) mCompositeSubscription.unsubscribe();
    }

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
