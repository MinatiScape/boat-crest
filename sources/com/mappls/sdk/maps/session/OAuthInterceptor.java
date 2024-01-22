package com.mappls.sdk.maps.session;

import com.google.common.net.HttpHeaders;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.auth.MapplsAuthentication;
import com.mappls.sdk.services.api.auth.model.AtlasAuthToken;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes11.dex */
public class OAuthInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    public OkHttpClient f12830a;

    public final void a(Request.Builder builder, String str) {
        builder.header("Authorization", String.format("bearer %s", str));
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder newBuilder = chain.request().newBuilder();
        newBuilder.header(HttpHeaders.ACCEPT, "application/json");
        a(newBuilder, MapplsAccountManager.getInstance().getAccessToken());
        Response proceed = chain.proceed(newBuilder.build());
        if (proceed.code() == 401 || proceed.code() == 400) {
            synchronized (this.f12830a) {
                retrofit2.Response<AtlasAuthToken> executeCall = MapplsAuthentication.builder().build().executeCall();
                if (executeCall != null && executeCall.body() != null) {
                    MapplsAccountManager.getInstance().setAccessToken(executeCall.body().accessToken);
                }
                if (executeCall.code() != 200) {
                    return proceed;
                }
                if (MapplsAccountManager.getInstance().getAccessToken() != null) {
                    proceed.close();
                    a(newBuilder, MapplsAccountManager.getInstance().getAccessToken());
                    return chain.proceed(newBuilder.build());
                }
            }
        }
        return proceed;
    }

    public void setHttpClient(OkHttpClient okHttpClient) {
        this.f12830a = okHttpClient;
    }
}
