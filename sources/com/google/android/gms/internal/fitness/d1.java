package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.SessionInsertRequest;
/* loaded from: classes8.dex */
public final class d1 extends u {
    public final /* synthetic */ SessionInsertRequest c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d1(zzeb zzebVar, GoogleApiClient googleApiClient, SessionInsertRequest sessionInsertRequest) {
        super(googleApiClient);
        this.c = sessionInsertRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzav zzavVar) throws RemoteException {
        ((zzca) zzavVar.getService()).zza(new SessionInsertRequest(this.c, new zzei(this)));
    }
}
