package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
/* loaded from: classes8.dex */
public final class u0 extends k {
    public final /* synthetic */ DataType c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u0(zzdo zzdoVar, GoogleApiClient googleApiClient, DataType dataType) {
        super(googleApiClient);
        this.c = dataType;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaj zzajVar) throws RemoteException {
        ((zzbw) zzajVar.getService()).zza(new com.google.android.gms.fitness.request.zzbm(this.c, (DataSource) null, (zzcn) new zzei(this)));
    }
}
