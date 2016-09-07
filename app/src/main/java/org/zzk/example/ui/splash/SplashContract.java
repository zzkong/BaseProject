package org.zzk.example.ui.splash;

import android.content.Context;

import org.zzk.example.ui.IPresenter;
import org.zzk.example.ui.IView;

/**
 * Created by zwl on 16/9/5.
 */
public interface SplashContract {

    interface View extends IView{

        void readyGoMain();

        void readyGoGuide();
    }

    interface Presenter extends IPresenter<View>{

        void checkIsFirstIn(Context context);
    }
}
