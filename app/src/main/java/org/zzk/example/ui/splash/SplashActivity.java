package org.zzk.example.ui.splash;

import android.view.View;

import org.zzk.example.R;
import org.zzk.example.ui.BaseMvpActivity;
import org.zzk.example.ui.main.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by zwl on 16/9/5.
 */
public class SplashActivity extends BaseMvpActivity<SplashPresenter> implements SplashContract.View{
    @Override
    public void initInjector() {
        mActivityComponent.inject(SplashActivity.this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initEventAndData() {
        ButterKnife.findById(this, R.id.splash_layout).setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        mPresenter.checkIsFirstIn(this);
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return false;
    }

    @Override
    public void readyGoMain() {
        readyGo(MainActivity.class);
        finish();
    }

    @Override
    public void readyGoGuide() {
        readyGoMain();
    }

    @Override
    public void onBackPressed() {
    }
}
