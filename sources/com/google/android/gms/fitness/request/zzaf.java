package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.fitness.zzb;
/* loaded from: classes6.dex */
public final class zzaf extends zzb implements zzad {
    public zzaf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.request.IBleScanCallback");
    }

    @Override // com.google.android.gms.fitness.request.zzad
    public final void onDeviceFound(BleDevice bleDevice) throws RemoteException {
        Parcel zza = zza();
        com.google.android.gms.internal.fitness.zzd.zza(zza, bleDevice);
        zzb(1, zza);
    }

    @Override // com.google.android.gms.fitness.request.zzad
    public final void onScanStopped() throws RemoteException {
        zzb(2, zza());
    }
}
