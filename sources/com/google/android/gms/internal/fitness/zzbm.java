package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;
/* loaded from: classes8.dex */
public abstract class zzbm extends zza implements zzbn {
    public zzbm() {
        super("com.google.android.gms.fitness.internal.IGoalsReadCallback");
    }

    public static zzbn zzf(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoalsReadCallback");
        if (queryLocalInterface instanceof zzbn) {
            return (zzbn) queryLocalInterface;
        }
        return new zzbp(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zza
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zza((GoalsResult) zzd.zza(parcel, GoalsResult.CREATOR));
            return true;
        }
        return false;
    }
}
