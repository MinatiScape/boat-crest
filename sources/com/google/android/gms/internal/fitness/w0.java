package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;
/* loaded from: classes8.dex */
public final class w0 extends zzcf {

    /* renamed from: a  reason: collision with root package name */
    public final BaseImplementation.ResultHolder<ListSubscriptionsResult> f8855a;

    public w0(BaseImplementation.ResultHolder<ListSubscriptionsResult> resultHolder) {
        this.f8855a = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzcc
    public final void zza(ListSubscriptionsResult listSubscriptionsResult) {
        this.f8855a.setResult(listSubscriptionsResult);
    }

    public /* synthetic */ w0(BaseImplementation.ResultHolder resultHolder, t0 t0Var) {
        this(resultHolder);
    }
}
