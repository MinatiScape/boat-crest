package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.request.zzv;
/* loaded from: classes8.dex */
public final class zzbx extends zzb implements zzbu {
    public zzbx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitHistoryApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzbu
    public final void zza(DataReadRequest dataReadRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, dataReadRequest);
        zza(1, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbu
    public final void zza(com.google.android.gms.fitness.request.zzj zzjVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzjVar);
        zza(2, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbu
    public final void zza(DataDeleteRequest dataDeleteRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, dataDeleteRequest);
        zza(3, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbu
    public final void zza(com.google.android.gms.fitness.request.zzf zzfVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzfVar);
        zza(7, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbu
    public final void zza(DataUpdateRequest dataUpdateRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, dataUpdateRequest);
        zza(9, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbu
    public final void zza(DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, dataUpdateListenerRegistrationRequest);
        zza(10, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbu
    public final void zza(zzv zzvVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzvVar);
        zza(11, zza);
    }
}
