package org.zzk.example.injector.components;

import org.zzk.example.injector.PerActivity;
import org.zzk.example.injector.modules.ActivityModule;
import org.zzk.example.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by zwl on 16/9/5.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity splashActivity);

}
