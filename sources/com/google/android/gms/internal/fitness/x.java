package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.StartBleScanRequest;
/* loaded from: classes8.dex */
public final class x extends t5 {
    public final /* synthetic */ StartBleScanRequest c;
    public final /* synthetic */ com.google.android.gms.fitness.request.zzad d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(zzco zzcoVar, GoogleApiClient googleApiClient, StartBleScanRequest startBleScanRequest, com.google.android.gms.fitness.request.zzad zzadVar) {
        super(googleApiClient);
        this.c = startBleScanRequest;
        this.d = zzadVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzk zzkVar) throws RemoteException {
        ((zzbo) zzkVar.getService()).zza(new StartBleScanRequest(this.c.getDataTypes(), this.d, this.c.getTimeoutSecs(), new zzei(this)));
    }
}
