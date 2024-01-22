package com.google.android.gms.common.internal.service;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
/* loaded from: classes6.dex */
public final class Common {
    @NonNull
    @KeepForSdk
    public static final Api<Api.ApiOptions.NoOptions> API;
    @NonNull
    @KeepForSdk
    public static final Api.ClientKey<zah> CLIENT_KEY;

    /* renamed from: a  reason: collision with root package name */
    public static final Api.AbstractClientBuilder f8339a;
    public static final zae zaa;

    static {
        Api.ClientKey<zah> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        a aVar = new a();
        f8339a = aVar;
        API = new Api<>("Common.API", aVar, clientKey);
        zaa = new zae();
    }
}
