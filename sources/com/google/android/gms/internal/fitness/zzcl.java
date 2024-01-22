package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionStopResult;
/* loaded from: classes8.dex */
public abstract class zzcl extends zza implements zzci {
    public zzcl() {
        super("com.google.android.gms.fitness.internal.ISessionStopCallback");
    }

    public static zzci zzi(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionStopCallback");
        if (queryLocalInterface instanceof zzci) {
            return (zzci) queryLocalInterface;
        }
        return new zzck(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zza
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zza((SessionStopResult) zzd.zza(parcel, SessionStopResult.CREATOR));
            return true;
        }
        return false;
    }
}
