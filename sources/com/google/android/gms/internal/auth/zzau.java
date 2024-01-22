package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public final class zzau extends zza {
    public zzau(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferService");
    }

    public final void zzd(zzat zzatVar, zzaq zzaqVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, zzatVar);
        zzc.zzd(zza, zzaqVar);
        zzc(7, zza);
    }

    public final void zze(zzat zzatVar, zzbb zzbbVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, zzatVar);
        zzc.zzd(zza, zzbbVar);
        zzc(8, zza);
    }

    public final void zzf(zzat zzatVar, zzav zzavVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, zzatVar);
        zzc.zzd(zza, zzavVar);
        zzc(9, zza);
    }

    public final void zzg(zzat zzatVar, zzax zzaxVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, zzatVar);
        zzc.zzd(zza, zzaxVar);
        zzc(6, zza);
    }

    public final void zzh(zzat zzatVar, zzaz zzazVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, zzatVar);
        zzc.zzd(zza, zzazVar);
        zzc(5, zza);
    }
}
