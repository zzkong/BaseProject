package org.zzk.example.injector.modules;

import org.zzk.example.Constants;
import org.zzk.example.data.api.GankApi;

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
    public GankApi provideGankApi(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GankApi gankApi = retrofit.create(GankApi.class);
        return gankApi;
    }
}
