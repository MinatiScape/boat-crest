package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataUpdateRequest;
/* loaded from: classes8.dex */
public final class m0 extends f {
    public final /* synthetic */ DataUpdateRequest c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m0(zzde zzdeVar, GoogleApiClient googleApiClient, DataUpdateRequest dataUpdateRequest) {
        super(googleApiClient);
        this.c = dataUpdateRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzad zzadVar) throws RemoteException {
        ((zzbu) zzadVar.getService()).zza(new DataUpdateRequest(this.c, new zzei(this)));
    }
}
