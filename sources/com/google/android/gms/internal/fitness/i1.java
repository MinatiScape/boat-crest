package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
/* loaded from: classes8.dex */
public final class i1 extends u {
    public final /* synthetic */ PendingIntent c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i1(zzeb zzebVar, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.c = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzav zzavVar) throws RemoteException {
        ((zzca) zzavVar.getService()).zza(new com.google.android.gms.fitness.request.zzbc(this.c, (zzcn) new zzei(this)));
    }
}
