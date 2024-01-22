package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zzai;
/* loaded from: classes8.dex */
public final class zzbr extends zzb implements zzbo {
    public zzbr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitBleApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzbo
    public final void zza(StartBleScanRequest startBleScanRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, startBleScanRequest);
        zza(1, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbo
    public final void zza(com.google.android.gms.fitness.request.zzbg zzbgVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzbgVar);
        zza(2, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbo
    public final void zza(com.google.android.gms.fitness.request.zzd zzdVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdVar);
        zza(3, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbo
    public final void zza(com.google.android.gms.fitness.request.zzbk zzbkVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzbkVar);
        zza(4, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbo
    public final void zza(zzai zzaiVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzaiVar);
        zza(5, zza);
    }
}
