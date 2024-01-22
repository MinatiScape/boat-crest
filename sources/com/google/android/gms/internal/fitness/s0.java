package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzak;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;
/* loaded from: classes8.dex */
public final class s0 extends i<ListSubscriptionsResult> {
    public final /* synthetic */ DataType c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(zzdo zzdoVar, GoogleApiClient googleApiClient, DataType dataType) {
        super(googleApiClient);
        this.c = dataType;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return ListSubscriptionsResult.zzd(status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaj zzajVar) throws RemoteException {
        ((zzbw) zzajVar.getService()).zza(new zzak(this.c, (zzcc) new w0(this, null)));
    }
}
