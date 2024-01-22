package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;
/* loaded from: classes8.dex */
public final class zzbp extends zzb implements zzbn {
    public zzbp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoalsReadCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbn
    public final void zza(GoalsResult goalsResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, goalsResult);
        zzb(1, zza);
    }
}
