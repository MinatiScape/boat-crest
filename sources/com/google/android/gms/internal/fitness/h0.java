package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.fitness.result.GoalsResult;
import java.util.Collections;
/* loaded from: classes8.dex */
public final class h0 extends b<GoalsResult> {
    public final /* synthetic */ GoalsReadRequest c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h0(zzdd zzddVar, GoogleApiClient googleApiClient, GoalsReadRequest goalsReadRequest) {
        super(googleApiClient);
        this.c = goalsReadRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new GoalsResult(status, Collections.emptyList());
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzw zzwVar) throws RemoteException {
        ((zzbs) zzwVar.getService()).zza(new GoalsReadRequest(this.c, new i0(this)));
    }
}
