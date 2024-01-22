package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.Objects;
@KeepForSdk
/* loaded from: classes6.dex */
public final class ApiKey<O extends Api.ApiOptions> {

    /* renamed from: a  reason: collision with root package name */
    public final int f8252a;
    public final Api b;
    @Nullable
    public final Api.ApiOptions c;
    @Nullable
    public final String d;

    public ApiKey(Api api, @Nullable Api.ApiOptions apiOptions, @Nullable String str) {
        this.b = api;
        this.c = apiOptions;
        this.d = str;
        this.f8252a = Objects.hashCode(api, apiOptions, str);
    }

    @NonNull
    @KeepForSdk
    public static <O extends Api.ApiOptions> ApiKey<O> getSharedApiKey(@NonNull Api<O> api, @Nullable O o, @Nullable String str) {
        return new ApiKey<>(api, o, str);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof ApiKey) {
            ApiKey apiKey = (ApiKey) obj;
            return Objects.equal(this.b, apiKey.b) && Objects.equal(this.c, apiKey.c) && Objects.equal(this.d, apiKey.d);
        }
        return false;
    }

    public final int hashCode() {
        return this.f8252a;
    }

    @NonNull
    public final String zaa() {
        return this.b.zad();
    }
}
