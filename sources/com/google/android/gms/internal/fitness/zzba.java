package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DailyTotalResult;
/* loaded from: classes8.dex */
public abstract class zzba extends zza implements zzbb {
    public zzba() {
        super("com.google.android.gms.fitness.internal.IDailyTotalCallback");
    }

    public static zzbb zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IDailyTotalCallback");
        if (queryLocalInterface instanceof zzbb) {
            return (zzbb) queryLocalInterface;
        }
        return new zzbd(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zza
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzc((DailyTotalResult) zzd.zza(parcel, DailyTotalResult.CREATOR));
            return true;
        }
        return false;
    }
}
