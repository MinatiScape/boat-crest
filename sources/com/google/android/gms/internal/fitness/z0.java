package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.zzv;
import com.google.android.gms.fitness.request.zzar;
/* loaded from: classes8.dex */
public final class z0 extends p {
    public final /* synthetic */ zzv c;
    public final /* synthetic */ PendingIntent d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z0(zzdx zzdxVar, GoogleApiClient googleApiClient, zzv zzvVar, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.c = zzvVar;
        this.d = pendingIntent;
    }

    @Override // com.google.android.gms.internal.fitness.p
    public final Status b(Status status) {
        return status;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return b(status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzap zzapVar) throws RemoteException {
        ((zzby) zzapVar.getService()).zza(new zzar(this.c, this.d, new zzei(this)));
    }
}
