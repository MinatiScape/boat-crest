package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;
/* loaded from: classes8.dex */
public final class zzcj extends zzb implements zzch {
    public zzcj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.ISessionReadCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzch
    public final void zza(SessionReadResult sessionReadResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, sessionReadResult);
        zzb(1, zza);
    }
}
