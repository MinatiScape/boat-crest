package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.common.api.internal.IStatusCallback;
/* loaded from: classes6.dex */
public final class zzp extends zza {
    public zzp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.account.data.IGoogleAuthService");
    }

    public final void zzd(IStatusCallback iStatusCallback, zzbw zzbwVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, iStatusCallback);
        zzc.zzd(zza, zzbwVar);
        zzc(2, zza);
    }

    public final void zze(zzm zzmVar, AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, zzmVar);
        zzc.zzd(zza, accountChangeEventsRequest);
        zzc(4, zza);
    }

    public final void zzf(zzo zzoVar, Account account, String str, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, zzoVar);
        zzc.zzd(zza, account);
        zza.writeString(str);
        zzc.zzd(zza, bundle);
        zzc(1, zza);
    }

    public final void zzg(zzk zzkVar, Account account) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, zzkVar);
        zzc.zzd(zza, account);
        zzc(6, zza);
    }

    public final void zzh(zzk zzkVar, String str) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, zzkVar);
        zza.writeString(str);
        zzc(3, zza);
    }
}
