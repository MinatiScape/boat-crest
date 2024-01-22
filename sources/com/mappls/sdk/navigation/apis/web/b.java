package com.mappls.sdk.navigation.apis.web;

import com.google.gson.GsonBuilder;
import com.mappls.sdk.services.api.MapplsApiConfiguration;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
/* loaded from: classes11.dex */
public final class b {
    public static b d = new b();

    /* renamed from: a  reason: collision with root package name */
    public a f12883a = null;
    public OkHttpClient.Builder b;
    public Retrofit.Builder c;

    public b() {
        new OkHttpClient.Builder();
        this.b = new OkHttpClient.Builder();
        this.c = null;
    }

    public static b a() {
        return d;
    }

    public final a b() {
        if (this.f12883a == null) {
            OkHttpClient.Builder builder = this.b;
            long j = 60;
            TimeUnit timeUnit = TimeUnit.SECONDS;
            builder.readTimeout(j, timeUnit);
            this.b.connectTimeout(j, timeUnit);
            if (MapplsApiConfiguration.getInstance().getProxyHost() != null) {
                this.b.proxy(new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(MapplsApiConfiguration.getInstance().getProxyHost(), MapplsApiConfiguration.getInstance().getProxyPort())));
            }
            OkHttpClient build = this.b.build();
            if (this.c == null) {
                this.c = new Retrofit.Builder().baseUrl("https://outpost.mappls.com/api/security/").addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()));
            }
            this.f12883a = (a) this.c.client(build).build().create(a.class);
        }
        return this.f12883a;
    }
}
