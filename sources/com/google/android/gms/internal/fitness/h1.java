package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.SessionReadResult;
/* loaded from: classes8.dex */
public final class h1 extends zzcg {

    /* renamed from: a  reason: collision with root package name */
    public final BaseImplementation.ResultHolder<SessionReadResult> f8827a;

    public h1(BaseImplementation.ResultHolder<SessionReadResult> resultHolder) {
        this.f8827a = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzch
    public final void zza(SessionReadResult sessionReadResult) {
        this.f8827a.setResult(sessionReadResult);
    }

    public /* synthetic */ h1(BaseImplementation.ResultHolder resultHolder, c1 c1Var) {
        this(resultHolder);
    }
}
