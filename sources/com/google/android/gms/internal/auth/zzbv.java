package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.Nonnull;
/* loaded from: classes6.dex */
public final class zzbv implements ProxyApi.SpatulaHeaderResult {
    public final Status h;
    public final String i;

    public zzbv(@Nonnull Status status) {
        this.h = (Status) Preconditions.checkNotNull(status);
        this.i = "";
    }

    @Override // com.google.android.gms.auth.api.proxy.ProxyApi.SpatulaHeaderResult
    public final String getSpatulaHeader() {
        return this.i;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.h;
    }

    public zzbv(@Nonnull String str) {
        this.i = (String) Preconditions.checkNotNull(str);
        this.h = Status.RESULT_SUCCESS;
    }
}
