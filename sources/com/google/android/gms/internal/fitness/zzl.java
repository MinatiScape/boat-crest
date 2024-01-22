package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.DataSourcesResult;
/* loaded from: classes8.dex */
public final class zzl extends zzbg {

    /* renamed from: a  reason: collision with root package name */
    public final BaseImplementation.ResultHolder<DataSourcesResult> f8868a;

    public zzl(BaseImplementation.ResultHolder<DataSourcesResult> resultHolder) {
        this.f8868a = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzbh
    public final void zza(DataSourcesResult dataSourcesResult) {
        this.f8868a.setResult(dataSourcesResult);
    }
}
