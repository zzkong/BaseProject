package org.zzk.example.ui.splash;

import android.view.View;

import org.zzk.example.R;
import org.zzk.example.ui.base.BaseActivity;
import org.zzk.example.ui.main.MainActivity;

import butterknife.ButterKnife;

/**
 * 过渡页
 * Created by zwl on 16/9/5.
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View{
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
        SetTranslanteBar();
        // android隐藏底部虚拟键NavigationBar实现全屏
        ButterKnife.findById(this, R.id.splash_layout).setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        mPresenter.checkIsFirstIn(SplashActivity.this);
    }

    @Override
    public void readyGoMain() {
        MainActivity.startActivity(SplashActivity.this);
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
