package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.DataTypeResult;
/* loaded from: classes8.dex */
public final class f0 extends zzbl {

    /* renamed from: a  reason: collision with root package name */
    public final BaseImplementation.ResultHolder<DataTypeResult> f8824a;

    public f0(BaseImplementation.ResultHolder<DataTypeResult> resultHolder) {
        this.f8824a = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzbi
    public final void zzc(DataTypeResult dataTypeResult) {
        this.f8824a.setResult(dataTypeResult);
    }

    public /* synthetic */ f0(BaseImplementation.ResultHolder resultHolder, e0 e0Var) {
        this(resultHolder);
    }
}
