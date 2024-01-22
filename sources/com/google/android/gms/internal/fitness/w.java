package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
/* loaded from: classes8.dex */
public final class w extends t5 {
    public final /* synthetic */ com.google.android.gms.fitness.request.zzad c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(zzco zzcoVar, GoogleApiClient googleApiClient, com.google.android.gms.fitness.request.zzad zzadVar) {
        super(googleApiClient);
        this.c = zzadVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzk zzkVar) throws RemoteException {
        ((zzbo) zzkVar.getService()).zza(new com.google.android.gms.fitness.request.zzbg(this.c, new zzei(this)));
    }
}
