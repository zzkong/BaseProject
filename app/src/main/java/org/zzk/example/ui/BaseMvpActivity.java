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
 * MVP基类Activity
 */
public abstract class BaseMvpActivity<T extends IPresenter> extends BaseActivity implements IView{

    @Inject
    protected T mPresenter;
    protected ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //dagger2注解,子类实现initInjector()方法 进行inject()
        setupActivityComponent(MyApp.getAppComponent(), new ActivityModule(this));
        initInjector();
        
        //绑定Presenter
        if(mPresenter != null) mPresenter.attachView(this);
        super.onCreate(savedInstanceState);
    }

    public abstract void initInjector();

    /**
     * 获取Component实例,方便子类使用
     */
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
