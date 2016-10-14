package org.zzk.example.ui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Window;

import org.zzk.example.AppManager;
import org.zzk.example.MyApp;
import org.zzk.example.R;
import org.zzk.example.injector.components.ActivityComponent;
import org.zzk.example.injector.components.AppComponent;
import org.zzk.example.injector.components.DaggerActivityComponent;
import org.zzk.example.injector.modules.ActivityModule;
import org.zzk.example.rxjava.RxManager;
import org.zzk.example.utils.LoadingDialog;
import org.zzk.example.utils.StatusBarCompat;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by zwl on 16/9/30.
 */
public abstract class BaseActivity<T extends IPresenter> extends SwipeBackAppCompatActivity {
    @Inject
    public T mPresenter;
    public RxManager mRxManager;
    protected ActivityComponent mActivityComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        setBaseConfig();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setupActivityComponent(MyApp.getAppComponent(), new ActivityModule(this));
        initInjector();
        if (mPresenter != null)
            mPresenter.attachView(this, this);
        initEventAndData();
    }

    private void setBaseConfig() {
        initTheme();
        AppManager.getAppManager().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SetStatusBarColor();
    }

    /**
     * 获取Component实例,方便子类使用
     */
    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule) {
        mActivityComponent = DaggerActivityComponent.builder().appComponent(appComponent)
                .build();
    }

    protected abstract int getLayoutId();

    protected abstract void initInjector();

    protected abstract void initEventAndData();

    private void initTheme() {
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorAccent));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }

    public void showLoaing() {
        LoadingDialog.showLoading(this);
    }

    public void showLoaing(String msg) {
        LoadingDialog.showLoading(this, msg, true);
    }

    public void dismissLoading() {
        LoadingDialog.disDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        mRxManager.clear();
        ButterKnife.unbind(this);
        AppManager.getAppManager().finishActivity(this);
    }
}
