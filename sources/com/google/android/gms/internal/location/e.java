package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationRequest;
/* loaded from: classes8.dex */
public final class e extends n {
    public final /* synthetic */ ListenerHolder c;
    public final /* synthetic */ LocationRequest d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(zzau zzauVar, GoogleApiClient googleApiClient, ListenerHolder listenerHolder, LocationRequest locationRequest) {
        super(googleApiClient);
        this.c = listenerHolder;
        this.d = locationRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzda) anyClient).zzv(new m(this.c), this.d, zzau.a(this));
    }
}
