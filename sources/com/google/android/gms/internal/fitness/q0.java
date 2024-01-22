package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DailyTotalResult;
/* loaded from: classes8.dex */
public final class q0 extends d<DailyTotalResult> {
    public final /* synthetic */ DataType c;
    public final /* synthetic */ boolean d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q0(zzde zzdeVar, GoogleApiClient googleApiClient, DataType dataType, boolean z) {
        super(googleApiClient);
        this.c = dataType;
        this.d = z;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DailyTotalResult.zza(status, this.c);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzad zzadVar) throws RemoteException {
        ((zzbu) zzadVar.getService()).zza(new com.google.android.gms.fitness.request.zzf((zzbb) new p0(this), this.c, this.d));
    }
}
