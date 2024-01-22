package com.google.android.gms.internal.p002authapi;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;
/* renamed from: com.google.android.gms.internal.auth-api.i  reason: invalid package */
/* loaded from: classes6.dex */
public final class i extends zbd {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ j f8511a;

    public i(j jVar) {
        this.f8511a = jVar;
    }

    @Override // com.google.android.gms.internal.p002authapi.zbd, com.google.android.gms.internal.p002authapi.zbs
    public final void zbb(Status status, Credential credential) {
        this.f8511a.setResult((j) new zbe(status, credential));
    }

    @Override // com.google.android.gms.internal.p002authapi.zbd, com.google.android.gms.internal.p002authapi.zbs
    public final void zbc(Status status) {
        this.f8511a.setResult((j) new zbe(status, null));
    }
}
