package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.zzr;
import com.google.android.gms.fitness.result.DataTypeResult;
/* loaded from: classes8.dex */
public final class d0 extends x5<DataTypeResult> {
    public final /* synthetic */ String c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d0(zzcw zzcwVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.c = str;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DataTypeResult.zzc(status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzq zzqVar) throws RemoteException {
        ((zzbq) zzqVar.getService()).zza(new zzr(this.c, (zzbi) new f0(this, null)));
    }
}
