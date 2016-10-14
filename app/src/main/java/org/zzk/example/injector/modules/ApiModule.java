package org.zzk.example.injector.modules;

import org.zzk.example.Constants;
import org.zzk.example.data.api.gank.GankService;
import org.zzk.example.data.api.meitu.MeituService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zwl on 16/9/6.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    public GankService provideGankService(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_GANK_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GankService gankService = retrofit.create(GankService.class);
        return gankService;
    }

    @Provides
    @Singleton
    public MeituService provideMeituService(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_MEITU_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MeituService meituService = retrofit.create(MeituService.class);
        return meituService;
    }
}
