package com.google.android.gms.fitness.data;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public final class zzw extends com.google.android.gms.internal.fitness.zzb implements zzv {
    public zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.data.IDataSourceListener");
    }

    @Override // com.google.android.gms.fitness.data.zzv
    public final void zzc(DataPoint dataPoint) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.fitness.zzd.zza(zza, dataPoint);
        zzb(1, zza);
    }
}
