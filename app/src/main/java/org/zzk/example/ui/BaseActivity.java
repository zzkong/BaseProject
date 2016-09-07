package org.zzk.example.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import org.zzk.example.AppManager;
import org.zzk.example.R;
import org.zzk.example.utils.ResourceUtil;

import butterknife.ButterKnife;

/**
 * Created by zwl on 16/9/5.
 */
public abstract class BaseActivity extends SwipeBackAppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setTranslucentStatus(isApplyStatusBarTranslucency());
        setStatusBarColor(isApplyStatusBarColor());
        initEventAndData();
        AppManager.getAppManager().addActivity(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();

    protected abstract boolean isApplyStatusBarTranslucency();

    protected abstract boolean isApplyStatusBarColor();

    /**
     * set status bar translucency
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    /**
     * use SystemBarTintManager
     */
    public void setStatusBarColor(boolean on) {
        if (on) {
            StatusBarUtil.setColor(this, ResourceUtil.getThemeColor(this), 0);
        }
    }

    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGoforResult(Class<?> clazz, int code){
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, code);
    }

    public void setTitles(int title){
        ((TextView)ButterKnife.findById(this, R.id.text_title)).setText(title);
    }

    public void setTitles(String title){
        ((TextView)ButterKnife.findById(this, R.id.text_title)).setText(title);
    }

    public void backClick(View view){
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        AppManager.getAppManager().finishActivity(this);
    }
}
