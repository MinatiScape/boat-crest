package com.google.android.gms.internal.p002authapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
/* renamed from: com.google.android.gms.internal.auth-api.n  reason: invalid package */
/* loaded from: classes6.dex */
public final class n extends zbd {

    /* renamed from: a  reason: collision with root package name */
    public final BaseImplementation.ResultHolder f8512a;

    public n(BaseImplementation.ResultHolder resultHolder) {
        this.f8512a = resultHolder;
    }

    @Override // com.google.android.gms.internal.p002authapi.zbd, com.google.android.gms.internal.p002authapi.zbs
    public final void zbc(Status status) {
        this.f8512a.setResult(status);
    }
}
