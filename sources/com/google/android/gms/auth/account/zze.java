package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.IInterface;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public interface zze extends IInterface {
    void zzd(zzb zzbVar, String str) throws RemoteException;

    void zze(zzb zzbVar, Account account) throws RemoteException;

    void zzf(boolean z) throws RemoteException;
}
