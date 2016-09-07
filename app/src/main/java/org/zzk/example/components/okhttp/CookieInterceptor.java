package org.zzk.example.components.okhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by zwl on 16/8/14.
 */
public class CookieInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
