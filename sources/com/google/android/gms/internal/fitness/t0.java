package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzak;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;
/* loaded from: classes8.dex */
public final class t0 extends i<ListSubscriptionsResult> {
    public t0(zzdo zzdoVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return ListSubscriptionsResult.zzd(status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaj zzajVar) throws RemoteException {
        ((zzbw) zzajVar.getService()).zza(new zzak((DataType) null, (zzcc) new w0(this, null)));
    }
}
