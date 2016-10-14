package org.zzk.example.data.api.gank;

import org.zzk.example.bean.GankBean;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zwl on 16/10/14.
 */

public interface GankService {
    @GET("{type}/20/{page}")
    rx.Observable<GankBean> getGankData(@Path("type") String type, @Path("page") int page);
}
