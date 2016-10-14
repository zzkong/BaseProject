package org.zzk.example.ui.splash;

import android.content.Context;

import org.zzk.example.ui.base.IPresenter;

/**
 * Created by zwl on 16/9/5.
 */
public interface SplashContract {

    interface View {

        void readyGoMain();

        void readyGoGuide();
    }

    interface Presenter extends IPresenter<View> {

        void checkIsFirstIn(Context context);
    }
}
