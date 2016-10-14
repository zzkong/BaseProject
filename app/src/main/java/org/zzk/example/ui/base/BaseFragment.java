package org.zzk.example.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zzk.example.MyApp;
import org.zzk.example.injector.components.AppComponent;
import org.zzk.example.injector.components.DaggerFragmentComponent;
import org.zzk.example.injector.components.FragmentComponent;
import org.zzk.example.injector.modules.FragmentModule;

import java.lang.reflect.Field;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by zwl on 16/9/30.
 */

public abstract class BaseFragment<T extends IPresenter> extends Fragment {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected Context mActivity;
    //是否可见状态
    private boolean isVisible;
    //View已经初始化完成
    private boolean isPrepared;
    //是否第一次加载完
    private boolean isFirstLoad = true;

    @Inject
    protected T mPresenter;
    protected FragmentComponent mFragmentComponent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isFirstLoad = true;
        //绑定View
        View view = inflater.inflate(getlayoutId(), container, false);
        ButterKnife.bind(this, view);
        isPrepared = true;

        setupActivityComponent(MyApp.getAppComponent(), new FragmentModule(this));
        initInjector();//dagger2注解,子类实现initInjector()方法 进行inject()
        if(mPresenter != null) {
            //绑定Presenter
            mPresenter.attachView(this, mActivity);
        }
        //初始化事件和获取数据, 在此方法中获取数据不是懒加载模式
        initEventAndData();
        //在此方法中获取数据为懒加载模式,如不需要懒加载,请在initEventAndData获取数据,GankFragment有使用实例
        lazyLoad();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 获取Component实例,方便子类使用
     */
    protected void setupActivityComponent(AppComponent appComponent, FragmentModule fragmentModule){
        mFragmentComponent = DaggerFragmentComponent.builder().appComponent(appComponent)
                .fragmentModule(fragmentModule)
                .build();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onAttach(Context context) {
        this.mActivity = context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = android.support.v4.app.Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible = true;
            onVisible();
        }else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            isVisible = true;
            onVisible();
        }else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){
        lazyLoad();
    }

    protected void onInvisible(){}

    protected void lazyLoad(){
        if(!isPrepared || !isVisible || !isFirstLoad) return;
        isFirstLoad = false;
        lazyLoadData();
    }

    protected abstract int getlayoutId();
    protected abstract void initInjector();
    protected abstract void initEventAndData();
    protected abstract void lazyLoadData();
}
