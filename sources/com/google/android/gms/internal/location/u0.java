package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes8.dex */
public final class u0 extends w0 {
    public final /* synthetic */ long c;
    public final /* synthetic */ PendingIntent d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u0(zzw zzwVar, GoogleApiClient googleApiClient, long j, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.c = j;
        this.d = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzf zzfVar = (zzf) anyClient;
        long j = this.c;
        PendingIntent pendingIntent = this.d;
        Preconditions.checkNotNull(pendingIntent);
        Preconditions.checkArgument(j >= 0, "detectionIntervalMillis must be >= 0");
        ((zzo) zzfVar.getService()).zzr(j, true, pendingIntent);
        setResult((u0) Status.RESULT_SUCCESS);
    }
}
