package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataSourcesResult;
/* loaded from: classes8.dex */
public abstract class zzbg extends zza implements zzbh {
    public zzbg() {
        super("com.google.android.gms.fitness.internal.IDataSourcesCallback");
    }

    public static zzbh zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IDataSourcesCallback");
        if (queryLocalInterface instanceof zzbh) {
            return (zzbh) queryLocalInterface;
        }
        return new zzbj(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zza
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zza((DataSourcesResult) zzd.zza(parcel, DataSourcesResult.CREATOR));
            return true;
        }
        return false;
    }
}
