package org.zzk.example.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.zzk.example.MyApp;
import org.zzk.example.injector.components.ActivityComponent;
import org.zzk.example.injector.components.AppComponent;
import org.zzk.example.injector.components.DaggerActivityComponent;
import org.zzk.example.injector.modules.ActivityModule;

import javax.inject.Inject;

/**
 * Created by zwl on 16/9/5.
 */
public abstract class BaseMvpActivity<T extends IPresenter> extends BaseActivity implements IView{

    @Inject
    protected T mPresenter;
    protected ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setupActivityComponent(MyApp.getAppComponent(), new ActivityModule(this));
        initInjector();
        if(mPresenter != null) mPresenter.attachView(this);
        super.onCreate(savedInstanceState);
    }

    public abstract void initInjector();

    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule){
        mActivityComponent = DaggerActivityComponent.builder().appComponent(appComponent)
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null) mPresenter.detachView();
    }
}
