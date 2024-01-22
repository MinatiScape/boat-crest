package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.request.zzr;
import com.google.android.gms.fitness.request.zzz;
/* loaded from: classes8.dex */
public final class zzbt extends zzb implements zzbq {
    public zzbt(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitConfigApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzbq
    public final void zza(DataTypeCreateRequest dataTypeCreateRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, dataTypeCreateRequest);
        zza(1, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbq
    public final void zza(zzr zzrVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzrVar);
        zza(2, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbq
    public final void zza(zzz zzzVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzzVar);
        zza(22, zza);
    }
}
