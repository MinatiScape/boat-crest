package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.GoalsReadRequest;
/* loaded from: classes8.dex */
public final class zzbv extends zzb implements zzbs {
    public zzbv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitGoalsApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzbs
    public final void zza(GoalsReadRequest goalsReadRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, goalsReadRequest);
        zza(1, zza);
    }
}
