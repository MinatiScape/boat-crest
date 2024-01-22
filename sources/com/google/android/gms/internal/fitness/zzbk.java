package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataTypeResult;
/* loaded from: classes8.dex */
public final class zzbk extends zzb implements zzbi {
    public zzbk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataTypeCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbi
    public final void zzc(DataTypeResult dataTypeResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, dataTypeResult);
        zzb(1, zza);
    }
}
