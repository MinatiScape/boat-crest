package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import java.util.List;
/* loaded from: classes10.dex */
public interface zzdz extends IInterface {
    @Nullable
    String zzd(zzp zzpVar) throws RemoteException;

    @Nullable
    List<zzkq> zze(zzp zzpVar, boolean z) throws RemoteException;

    List<zzab> zzf(@Nullable String str, @Nullable String str2, zzp zzpVar) throws RemoteException;

    List<zzab> zzg(String str, @Nullable String str2, @Nullable String str3) throws RemoteException;

    List<zzkq> zzh(@Nullable String str, @Nullable String str2, boolean z, zzp zzpVar) throws RemoteException;

    List<zzkq> zzi(String str, @Nullable String str2, @Nullable String str3, boolean z) throws RemoteException;

    void zzj(zzp zzpVar) throws RemoteException;

    void zzk(zzat zzatVar, zzp zzpVar) throws RemoteException;

    void zzl(zzat zzatVar, String str, @Nullable String str2) throws RemoteException;

    void zzm(zzp zzpVar) throws RemoteException;

    void zzn(zzab zzabVar, zzp zzpVar) throws RemoteException;

    void zzo(zzab zzabVar) throws RemoteException;

    void zzp(zzp zzpVar) throws RemoteException;

    void zzq(long j, @Nullable String str, @Nullable String str2, String str3) throws RemoteException;

    void zzr(Bundle bundle, zzp zzpVar) throws RemoteException;

    void zzs(zzp zzpVar) throws RemoteException;

    void zzt(zzkq zzkqVar, zzp zzpVar) throws RemoteException;

    @Nullable
    byte[] zzu(zzat zzatVar, String str) throws RemoteException;
}
