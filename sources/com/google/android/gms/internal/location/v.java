package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationSettingsRequest;
/* loaded from: classes8.dex */
public final class v extends w {
    public final /* synthetic */ LocationSettingsRequest c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(zzcc zzccVar, GoogleApiClient googleApiClient, LocationSettingsRequest locationSettingsRequest, String str) {
        super(googleApiClient);
        this.c = locationSettingsRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzda zzdaVar = (zzda) anyClient;
        LocationSettingsRequest locationSettingsRequest = this.c;
        Preconditions.checkArgument(locationSettingsRequest != null, "locationSettingsRequest can't be null");
        ((zzo) zzdaVar.getService()).zzh(locationSettingsRequest, new g0(this), null);
    }
}
