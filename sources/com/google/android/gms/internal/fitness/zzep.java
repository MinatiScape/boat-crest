package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.BleDevicesResult;
/* loaded from: classes8.dex */
public abstract class zzep extends zza implements zzem {
    public zzep() {
        super("com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
    }

    public static zzem zzk(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
        if (queryLocalInterface instanceof zzem) {
            return (zzem) queryLocalInterface;
        }
        return new zzeo(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zza
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zza((BleDevicesResult) zzd.zza(parcel, BleDevicesResult.CREATOR));
            return true;
        }
        return false;
    }
}
