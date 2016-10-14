package org.zzk.example.injector.components;

import org.zzk.example.MyApp;
import org.zzk.example.components.okhttp.OkHttpHelper;
import org.zzk.example.data.api.gank.GankService;
import org.zzk.example.data.api.meitu.MeituService;
import org.zzk.example.injector.modules.ApiModule;
import org.zzk.example.injector.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zwl on 16/9/5.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    OkHttpHelper getOkHttpHelper();

    GankService getGankService();

    MeituService getMeituService();

    void inject(MyApp mApplication);
}
