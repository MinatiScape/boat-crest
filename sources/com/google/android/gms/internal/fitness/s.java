package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
/* loaded from: classes8.dex */
public abstract class s<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzav> {
    public s(GoogleApiClient googleApiClient) {
        super(zzav.API, googleApiClient);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl, com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
    @KeepForSdk
    public /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((s<R>) ((Result) obj));
    }
}