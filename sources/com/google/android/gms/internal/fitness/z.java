package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.BleDevice;
/* loaded from: classes8.dex */
public final class z extends t5 {
    public final /* synthetic */ String c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(zzco zzcoVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.c = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzk zzkVar) throws RemoteException {
        ((zzbo) zzkVar.getService()).zza(new com.google.android.gms.fitness.request.zzd(this.c, (BleDevice) null, (zzcn) new zzei(this)));
    }
}
