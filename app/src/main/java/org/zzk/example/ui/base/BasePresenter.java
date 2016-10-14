package org.zzk.example.ui.base;

import android.content.Context;

import org.zzk.example.rxjava.RxManager;

/**
 * Created by zwl on 16/9/30.
 */

public abstract class BasePresenter<T> implements IPresenter<T> {
    public Context mActivity;
    public T mView;
    public RxManager mRxManager = new RxManager();

    @Override
    public void attachView(T view, Context context) {
        this.mView = view;
        this.mActivity = context;
        this.onStart();
    }

    @Override
    public void detachView() {
        this.mView = null;
        mRxManager.clear();
    }

    public void onStart(){}

}