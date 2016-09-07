package org.zzk.example.data.api;

import org.zzk.example.bean.GankBean;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zwl on 16/9/6.
 */
public interface GankApi {

    @GET("{type}/20/{page}")
    rx.Observable<GankBean> getGankData(@Path("type") String type, @Path("page") int page);
}
