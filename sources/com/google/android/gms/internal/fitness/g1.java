package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;
/* loaded from: classes8.dex */
public final class g1 extends s<SessionReadResult> {
    public final /* synthetic */ SessionReadRequest c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g1(zzeb zzebVar, GoogleApiClient googleApiClient, SessionReadRequest sessionReadRequest) {
        super(googleApiClient);
        this.c = sessionReadRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return SessionReadResult.zze(status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzav zzavVar) throws RemoteException {
        ((zzca) zzavVar.getService()).zza(new SessionReadRequest(this.c, new h1(this, null)));
    }
}
