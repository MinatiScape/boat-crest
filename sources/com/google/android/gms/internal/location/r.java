package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.GeofencingRequest;
/* loaded from: classes8.dex */
public final class r extends u {
    public final /* synthetic */ GeofencingRequest c;
    public final /* synthetic */ PendingIntent d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(zzbv zzbvVar, GoogleApiClient googleApiClient, GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.c = geofencingRequest;
        this.d = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzda) anyClient).zzq(this.c, this.d, zzbv.a(this));
    }
}
