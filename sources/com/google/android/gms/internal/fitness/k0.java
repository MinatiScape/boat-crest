package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSet;
/* loaded from: classes8.dex */
public final class k0 extends f {
    public final /* synthetic */ DataSet c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k0(zzde zzdeVar, GoogleApiClient googleApiClient, DataSet dataSet, boolean z) {
        super(googleApiClient);
        this.c = dataSet;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzad zzadVar) throws RemoteException {
        ((zzbu) zzadVar.getService()).zza(new com.google.android.gms.fitness.request.zzj(this.c, (zzcn) new zzei(this), false));
    }
}
