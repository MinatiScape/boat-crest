package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataReadResult;
/* loaded from: classes8.dex */
public final class zzbe extends zzb implements zzbc {
    public zzbe(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataReadCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbc
    public final void zza(DataReadResult dataReadResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, dataReadResult);
        zzb(1, zza);
    }
}
