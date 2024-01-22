package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
/* loaded from: classes6.dex */
public abstract class zzag extends com.google.android.gms.internal.fitness.zza implements zzad {
    public zzag() {
        super("com.google.android.gms.fitness.request.IBleScanCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zza
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            onDeviceFound((BleDevice) com.google.android.gms.internal.fitness.zzd.zza(parcel, BleDevice.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            onScanStopped();
        }
        return true;
    }
}
