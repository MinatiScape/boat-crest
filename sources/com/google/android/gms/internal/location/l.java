package com.google.android.gms.internal.location;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
/* loaded from: classes8.dex */
public final class l extends n {
    public final /* synthetic */ Location c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(zzau zzauVar, GoogleApiClient googleApiClient, Location location) {
        super(googleApiClient);
        this.c = location;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzda) anyClient).zzz(this.c, zzau.a(this));
    }
}
