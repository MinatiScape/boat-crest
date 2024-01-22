package com.mappls.sdk.services.api;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.mappls.sdk.services.utils.ApiCallHelper;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
@Keep
/* loaded from: classes11.dex */
public class AnalyticsInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        return chain.proceed(request.newBuilder().headers(request.headers().newBuilder().addUnsafeNonAscii("x-sa", ApiCallHelper.getAnalyticsHeader(MapplsApiConfiguration.getInstance().getLocation(), MapplsApiConfiguration.getInstance().isNavigating())).build()).build());
    }
}
