package com.google.android.gms.auth.api;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.ProxyClient;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.auth.zzbo;
import com.google.android.gms.internal.auth.zzbt;
@ShowFirstParty
@KeepForSdk
/* loaded from: classes6.dex */
public final class AuthProxy {
    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Api<AuthProxyOptions> API;
    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final ProxyApi ProxyApi;

    /* renamed from: a  reason: collision with root package name */
    public static final Api.AbstractClientBuilder f8190a;
    @NonNull
    public static final Api.ClientKey zza;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zza = clientKey;
        c cVar = new c();
        f8190a = cVar;
        API = new Api<>("Auth.PROXY_API", cVar, clientKey);
        ProxyApi = new zzbt();
    }

    @NonNull
    @KeepForSdk
    public static ProxyClient getClient(@NonNull Activity activity, @Nullable AuthProxyOptions authProxyOptions) {
        return new zzbo(activity, authProxyOptions);
    }

    @NonNull
    @KeepForSdk
    public static ProxyClient getClient(@NonNull Context context, @Nullable AuthProxyOptions authProxyOptions) {
        return new zzbo(context, authProxyOptions);
    }
}
