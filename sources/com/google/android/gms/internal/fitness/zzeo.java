package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.BleDevicesResult;
/* loaded from: classes8.dex */
public final class zzeo extends zzb implements zzem {
    public zzeo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzem
    public final void zza(BleDevicesResult bleDevicesResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, bleDevicesResult);
        zzb(1, zza);
    }
}
