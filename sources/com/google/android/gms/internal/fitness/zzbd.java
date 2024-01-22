package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DailyTotalResult;
/* loaded from: classes8.dex */
public final class zzbd extends zzb implements zzbb {
    public zzbd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDailyTotalCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbb
    public final void zzc(DailyTotalResult dailyTotalResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, dailyTotalResult);
        zzb(1, zza);
    }
}
