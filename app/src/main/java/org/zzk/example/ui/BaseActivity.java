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
 * 基类Activity,继承SwipeBackAppCompatActivity 方便实现左滑退出界面
 * Created by zwl on 16/9/5.
 */
public abstract class BaseActivity extends SwipeBackAppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定xml布局文件
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        //设置状态栏
        setTranslucentStatus(isApplyStatusBarTranslucency());
        setStatusBarColor(isApplyStatusBarColor());

        //初始化事件跟获取数据以及一些准备工作, 由于使用了ButterKnife, findViewById和基本的Click事件都不会在这里
        initEventAndData();

        //集中管理Activity
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

   /**
    * Intent跳转,子类直接调用readyGo(T)
    */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 带参的Intent跳转 通过Bundle传递参数
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivityForResult
     */
    protected void readyGoforResult(Class<?> clazz, int code){
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, code);
    }

    /**
     * 设置标题,int形式
     */
    public void setTitles(int title){
        ((TextView)ButterKnife.findById(this, R.id.text_title)).setText(title);
    }

    /**
     * 设置标题,String形式
     */
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
