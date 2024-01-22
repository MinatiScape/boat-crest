package com.mappls.sdk.maps.module.http;

import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.maps.MapplsMapConfiguration;
import com.mappls.sdk.maps.auth.MapplsVectorKey;
import com.mappls.sdk.maps.auth.model.PublicKeyToken;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;
/* loaded from: classes11.dex */
public class b implements Interceptor {
    public static long b;

    /* renamed from: a  reason: collision with root package name */
    public OkHttpClient f12791a;

    public OkHttpClient a() {
        return this.f12791a;
    }

    public void b(OkHttpClient okHttpClient) {
        this.f12791a = okHttpClient;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String httpUrl = request.url().toString();
        if (!MapplsMapConfiguration.getInstance().isUsingRasterStyle()) {
            if (MapplsMapManager.getInstance().getRawPublicKey() == null) {
                synchronized (this.f12791a) {
                    if (MapplsMapManager.getInstance().getRawPublicKey() == null) {
                        retrofit2.Response<PublicKeyToken> executeCall = MapplsVectorKey.builder().build().executeCall();
                        if (executeCall.code() == 200 && executeCall.body() != null) {
                            MapplsMapManager.getInstance().setRawPublicKey(executeCall.body().getPublicKey());
                        } else {
                            return new Response.Builder().request(request).code(executeCall.code()).body(executeCall.errorBody()).protocol(Protocol.HTTP_1_0).message(executeCall.message()).headers(executeCall.headers()).build();
                        }
                    }
                    return chain.proceed(request);
                }
            } else if (httpUrl.contains("vectorTiles") || httpUrl.contains("/vector_tile/") || httpUrl.contains("/map_tile/")) {
                Response proceed = chain.proceed(request);
                if (proceed.code() > 399 && proceed.code() < 500 && ((MapplsMapManager.getInstance().getRawPublicKey() == null || proceed.code() == 422 || proceed.code() == 409) && System.currentTimeMillis() - b > Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS)) {
                    Timber.d("request failed with %d", Integer.valueOf(proceed.code()));
                    b = System.currentTimeMillis();
                    String rawPublicKey = MapplsMapManager.getInstance().getRawPublicKey();
                    synchronized (this.f12791a) {
                        String rawPublicKey2 = MapplsMapManager.getInstance().getRawPublicKey();
                        if (MapplsMapManager.getInstance().getRawPublicKey() == null || rawPublicKey2.equalsIgnoreCase(rawPublicKey)) {
                            retrofit2.Response<PublicKeyToken> executeCall2 = MapplsVectorKey.builder().build().executeCall();
                            if (executeCall2.code() == 200 && executeCall2.body() != null) {
                                MapplsMapManager.getInstance().setRawPublicKey(executeCall2.body().getPublicKey());
                            } else {
                                return new Response.Builder().request(request).code(executeCall2.code()).body(executeCall2.errorBody()).protocol(Protocol.HTTP_1_0).message(executeCall2.message()).headers(executeCall2.headers()).build();
                            }
                        }
                    }
                }
                return proceed;
            }
        }
        return chain.proceed(request);
    }
}
