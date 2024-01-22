package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzv;
/* loaded from: classes8.dex */
public final class o0 extends f {
    public final /* synthetic */ PendingIntent c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o0(zzde zzdeVar, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.c = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzad zzadVar) throws RemoteException {
        ((zzbu) zzadVar.getService()).zza(new zzv(this.c, new zzei(this)));
    }
}
