package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
/* loaded from: classes8.dex */
public abstract class zzcm extends zza implements zzcn {
    public zzcm() {
        super("com.google.android.gms.fitness.internal.IStatusCallback");
    }

    public static zzcn zzj(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IStatusCallback");
        if (queryLocalInterface instanceof zzcn) {
            return (zzcn) queryLocalInterface;
        }
        return new zzcp(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zza
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            onResult((Status) zzd.zza(parcel, Status.CREATOR));
            return true;
        }
        return false;
    }
}
