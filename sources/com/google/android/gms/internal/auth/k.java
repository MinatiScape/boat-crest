package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public final class k implements Result {
    public final Status h;

    public k(Status status) {
        this.h = status;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.h;
    }
}
