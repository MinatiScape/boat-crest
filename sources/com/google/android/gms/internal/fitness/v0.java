package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Subscription;
/* loaded from: classes8.dex */
public final class v0 extends k {
    public final /* synthetic */ Subscription c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v0(zzdo zzdoVar, GoogleApiClient googleApiClient, Subscription subscription) {
        super(googleApiClient);
        this.c = subscription;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaj zzajVar) throws RemoteException {
        ((zzbw) zzajVar.getService()).zza(new com.google.android.gms.fitness.request.zzbi(this.c, false, (zzcn) new zzei(this)));
    }
}
