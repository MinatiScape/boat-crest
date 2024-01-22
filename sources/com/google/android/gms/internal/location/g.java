package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
/* loaded from: classes8.dex */
public final class g extends n {
    public final /* synthetic */ PendingIntent c;
    public final /* synthetic */ LocationRequest d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(zzau zzauVar, GoogleApiClient googleApiClient, PendingIntent pendingIntent, LocationRequest locationRequest) {
        super(googleApiClient);
        this.c = pendingIntent;
        this.d = locationRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzda) anyClient).zzw(this.c, this.d, zzau.a(this));
    }
}
