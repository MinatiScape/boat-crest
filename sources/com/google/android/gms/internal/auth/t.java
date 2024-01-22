package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public final class t implements ProxyApi.ProxyResult {
    public final Status h;
    public ProxyResponse i;

    public t(ProxyResponse proxyResponse) {
        this.i = proxyResponse;
        this.h = Status.RESULT_SUCCESS;
    }

    public t(Status status) {
        this.h = status;
    }

    @Override // com.google.android.gms.auth.api.proxy.ProxyApi.ProxyResult
    public final ProxyResponse getResponse() {
        return this.i;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.h;
    }
}
