package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.result.SessionStopResult;
import java.util.Collections;
/* loaded from: classes8.dex */
public final class e1 extends s<SessionStopResult> {
    public final /* synthetic */ String c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e1(zzeb zzebVar, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient);
        this.c = str2;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new SessionStopResult(status, Collections.emptyList());
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzav zzavVar) throws RemoteException {
        ((zzca) zzavVar.getService()).zza(new com.google.android.gms.fitness.request.zzba((String) null, this.c, (zzci) new j1(this, null)));
    }
}
