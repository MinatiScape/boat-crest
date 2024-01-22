package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public final class f extends h {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ g f8524a;

    public f(g gVar) {
        this.f8524a = gVar;
    }

    @Override // com.google.android.gms.internal.auth.h, com.google.android.gms.auth.account.zzb
    public final void zzc(boolean z) {
        this.f8524a.setResult((g) new k(z ? Status.RESULT_SUCCESS : zzal.f8550a));
    }
}
