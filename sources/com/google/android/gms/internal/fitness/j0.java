package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataDeleteRequest;
/* loaded from: classes8.dex */
public final class j0 extends f {
    public final /* synthetic */ DataDeleteRequest c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j0(zzde zzdeVar, GoogleApiClient googleApiClient, DataDeleteRequest dataDeleteRequest) {
        super(googleApiClient);
        this.c = dataDeleteRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzad zzadVar) throws RemoteException {
        ((zzbu) zzadVar.getService()).zza(new DataDeleteRequest(this.c, new zzei(this)));
    }
}
