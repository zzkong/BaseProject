package org.zzk.example.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zzk.example.MyApp;
import org.zzk.example.injector.components.AppComponent;
import org.zzk.example.injector.components.DaggerFragmentComponent;
import org.zzk.example.injector.components.FragmentComponent;
import org.zzk.example.injector.modules.FragmentModule;

import javax.inject.Inject;

/**
 * MvpFragment基Fragment 继承BaseLazyFragment,主要操作在BaseLazyFragment中
 * Created by zwl on 16/9/5.
 */
public abstract class BaseMvpFragment<T extends IPresenter> extends BaseLazyFragment implements IView{

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Inject
    protected T mPresenter;
    protected FragmentComponent mFragmentComponent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //dagger2注解,子类实现initInjector()方法 进行inject()
        setupActivityComponent(MyApp.getAppComponent(), new FragmentModule(this));
        initInjector();

        //绑定Presenter
        mPresenter.attachView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 获取Component实例,方便子类使用
     */
    protected void setupActivityComponent(AppComponent appComponent, FragmentModule fragmentModule){
        mFragmentComponent = DaggerFragmentComponent.builder().appComponent(appComponent)
                .fragmentModule(fragmentModule)
                .build();
    }

    public abstract void initInjector();
}
