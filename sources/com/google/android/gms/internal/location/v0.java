package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
/* loaded from: classes8.dex */
public final class v0 extends w0 {
    public final /* synthetic */ PendingIntent c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v0(zzw zzwVar, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.c = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzf) anyClient).zzp(this.c);
        setResult((v0) Status.RESULT_SUCCESS);
    }
}
