package com.google.firebase.ml.vision.automl.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.firebase_ml.zzd;
import com.google.android.gms.internal.firebase_ml.zzsb;
/* loaded from: classes10.dex */
public final class zzh extends com.google.android.gms.internal.firebase_ml.zzb implements IOnDeviceAutoMLImageLabeler {
    public zzh(IBinder iBinder) {
        super(iBinder, "com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabeler");
    }

    @Override // com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabeler
    public final void close() throws RemoteException {
        zzb(3, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabeler
    public final zzl[] zza(IObjectWrapper iObjectWrapper, zzsb zzsbVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzd.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        zzd.zza(obtainAndWriteInterfaceToken, zzsbVar);
        Parcel zza = zza(1, obtainAndWriteInterfaceToken);
        zzl[] zzlVarArr = (zzl[]) zza.createTypedArray(zzl.CREATOR);
        zza.recycle();
        return zzlVarArr;
    }

    @Override // com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabeler
    public final void zzol() throws RemoteException {
        zzb(2, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabeler
    public final boolean zzou() throws RemoteException {
        Parcel zza = zza(4, obtainAndWriteInterfaceToken());
        boolean zza2 = zzd.zza(zza);
        zza.recycle();
        return zza2;
    }
}
