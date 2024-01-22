package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.SessionStopResult;
/* loaded from: classes8.dex */
public final class j1 extends zzcl {

    /* renamed from: a  reason: collision with root package name */
    public final BaseImplementation.ResultHolder<SessionStopResult> f8830a;

    public j1(BaseImplementation.ResultHolder<SessionStopResult> resultHolder) {
        this.f8830a = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzci
    public final void zza(SessionStopResult sessionStopResult) {
        this.f8830a.setResult(sessionStopResult);
    }

    public /* synthetic */ j1(BaseImplementation.ResultHolder resultHolder, c1 c1Var) {
        this(resultHolder);
    }
}
