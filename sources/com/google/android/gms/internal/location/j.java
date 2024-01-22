package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;
/* loaded from: classes8.dex */
public final class j extends n {
    public final /* synthetic */ LocationCallback c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(zzau zzauVar, GoogleApiClient googleApiClient, LocationCallback locationCallback) {
        super(googleApiClient);
        this.c = locationCallback;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzda) anyClient).zzB(ListenerHolders.createListenerKey(this.c, LocationCallback.class.getSimpleName()), true, zzau.a(this));
    }
}
