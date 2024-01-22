package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;
/* loaded from: classes8.dex */
public final class zzce extends zzb implements zzcc {
    public zzce(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzcc
    public final void zza(ListSubscriptionsResult listSubscriptionsResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, listSubscriptionsResult);
        zzb(1, zza);
    }
}
