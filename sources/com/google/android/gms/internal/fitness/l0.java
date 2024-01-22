package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
/* loaded from: classes8.dex */
public final class l0 extends f {
    public final /* synthetic */ DataUpdateListenerRegistrationRequest c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l0(zzde zzdeVar, GoogleApiClient googleApiClient, DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) {
        super(googleApiClient);
        this.c = dataUpdateListenerRegistrationRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzad zzadVar) throws RemoteException {
        ((zzbu) zzadVar.getService()).zza(new DataUpdateListenerRegistrationRequest(this.c, new zzei(this)));
    }
}
