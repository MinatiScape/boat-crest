package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.zzar;
/* loaded from: classes8.dex */
public final class zzcb extends zzb implements zzby {
    public zzcb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitSensorsApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzby
    public final void zza(DataSourcesRequest dataSourcesRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, dataSourcesRequest);
        zza(1, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzby
    public final void zza(com.google.android.gms.fitness.request.zzap zzapVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzapVar);
        zza(2, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzby
    public final void zza(zzar zzarVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzarVar);
        zza(3, zza);
    }
}
