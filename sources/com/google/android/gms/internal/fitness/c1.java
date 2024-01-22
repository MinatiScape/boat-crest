package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.zzay;
/* loaded from: classes8.dex */
public final class c1 extends u {
    public final /* synthetic */ Session c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c1(zzeb zzebVar, GoogleApiClient googleApiClient, Session session) {
        super(googleApiClient);
        this.c = session;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzav zzavVar) throws RemoteException {
        ((zzca) zzavVar.getService()).zza(new zzay(this.c, (zzcn) new zzei(this)));
    }
}
