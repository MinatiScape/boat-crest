package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzz;
/* loaded from: classes8.dex */
public final class g0 extends y5 {
    public g0(zzcw zzcwVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzq zzqVar) throws RemoteException {
        ((zzbq) zzqVar.getService()).zza(new zzz((zzcn) new zzei(this)));
    }
}
