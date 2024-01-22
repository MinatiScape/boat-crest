package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataSourcesResult;
/* loaded from: classes8.dex */
public final class zzbj extends zzb implements zzbh {
    public zzbj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataSourcesCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbh
    public final void zza(DataSourcesResult dataSourcesResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, dataSourcesResult);
        zzb(1, zza);
    }
}
