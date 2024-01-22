package com.mappls.sdk.maps.module.http;

import com.google.common.net.HttpHeaders;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.auth.MapplsAuthentication;
import com.mappls.sdk.services.api.auth.model.AtlasAuthToken;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes11.dex */
public class a implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    public OkHttpClient f12790a;

    public OkHttpClient a() {
        return this.f12790a;
    }

    public final void b(Request.Builder builder, String str) {
        builder.header("Authorization", String.format("bearer %s", str));
    }

    public void c(OkHttpClient okHttpClient) {
        this.f12790a = okHttpClient;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        newBuilder.header(HttpHeaders.ACCEPT, "application/json");
        synchronized (this.f12790a) {
            if (MapplsAccountManager.getInstance().getAccessToken() == null) {
                retrofit2.Response<AtlasAuthToken> executeCall = MapplsAuthentication.builder().build().executeCall();
                if (executeCall != null && executeCall.body() != null) {
                    MapplsAccountManager.getInstance().setAccessToken(executeCall.body().accessToken);
                }
                if (executeCall.code() != 200) {
                    return new Response.Builder().request(request).code(executeCall.code()).body(executeCall.errorBody()).protocol(Protocol.HTTP_1_0).message(executeCall.message()).headers(executeCall.headers()).build();
                }
            }
            b(newBuilder, MapplsAccountManager.getInstance().getAccessToken());
            Response proceed = chain.proceed(newBuilder.build());
            if (proceed.code() == 401 || proceed.code() == 400) {
                retrofit2.Response<AtlasAuthToken> executeCall2 = MapplsAuthentication.builder().build().executeCall();
                if (executeCall2 != null && executeCall2.body() != null) {
                    MapplsAccountManager.getInstance().setAccessToken(executeCall2.body().accessToken);
                }
                if (executeCall2.code() != 200) {
                    return proceed;
                }
                if (MapplsAccountManager.getInstance().getAccessToken() != null) {
                    proceed.close();
                    b(newBuilder, MapplsAccountManager.getInstance().getAccessToken());
                    return chain.proceed(newBuilder.build());
                }
            }
            return proceed;
        }
    }
}
