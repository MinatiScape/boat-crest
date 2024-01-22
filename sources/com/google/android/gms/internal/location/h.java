package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;
/* loaded from: classes8.dex */
public final class h extends n {
    public final /* synthetic */ LocationListener c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(zzau zzauVar, GoogleApiClient googleApiClient, LocationListener locationListener) {
        super(googleApiClient);
        this.c = locationListener;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzda) anyClient).zzC(ListenerHolders.createListenerKey(this.c, LocationListener.class.getSimpleName()), true, zzau.a(this));
    }
}
