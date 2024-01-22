package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
/* loaded from: classes8.dex */
public final class zzcp extends zzb implements zzcn {
    public zzcp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IStatusCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzcn
    public final void onResult(Status status) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, status);
        zzb(1, zza);
    }
}
