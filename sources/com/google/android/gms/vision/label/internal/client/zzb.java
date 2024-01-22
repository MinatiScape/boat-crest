package com.google.android.gms.vision.label.internal.client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes10.dex */
public final class zzb extends com.google.android.gms.internal.vision.zzb implements INativeImageLabeler {
    public zzb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.label.internal.client.INativeImageLabeler");
    }

    @Override // com.google.android.gms.vision.label.internal.client.INativeImageLabeler
    public final zzf[] zza(IObjectWrapper iObjectWrapper, LabelOptions labelOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.vision.zzd.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        com.google.android.gms.internal.vision.zzd.zza(obtainAndWriteInterfaceToken, labelOptions);
        Parcel zza = zza(1, obtainAndWriteInterfaceToken);
        zzf[] zzfVarArr = (zzf[]) zza.createTypedArray(zzf.CREATOR);
        zza.recycle();
        return zzfVarArr;
    }

    @Override // com.google.android.gms.vision.label.internal.client.INativeImageLabeler
    public final void zzq() throws RemoteException {
        zzb(2, obtainAndWriteInterfaceToken());
    }
}
