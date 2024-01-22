package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
/* loaded from: classes8.dex */
public final class n0 extends d<DataReadResult> {
    public final /* synthetic */ DataReadRequest c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n0(zzde zzdeVar, GoogleApiClient googleApiClient, DataReadRequest dataReadRequest) {
        super(googleApiClient);
        this.c = dataReadRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DataReadResult.zza(status, this.c.getDataTypes(), this.c.getDataSources());
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzad zzadVar) throws RemoteException {
        ((zzbu) zzadVar.getService()).zza(new DataReadRequest(this.c, new r0(this, null)));
    }
}
