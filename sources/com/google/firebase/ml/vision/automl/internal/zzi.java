package com.google.firebase.ml.vision.automl.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.firebase_ml.zzd;
/* loaded from: classes10.dex */
public final class zzi extends com.google.android.gms.internal.firebase_ml.zzb implements zzg {
    public zzi(IBinder iBinder) {
        super(iBinder, "com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabelerCreator");
    }

    @Override // com.google.firebase.ml.vision.automl.internal.zzg
    public final IOnDeviceAutoMLImageLabeler newOnDeviceAutoMLImageLabeler(IObjectWrapper iObjectWrapper, OnDeviceAutoMLImageLabelerOptionsParcel onDeviceAutoMLImageLabelerOptionsParcel) throws RemoteException {
        IOnDeviceAutoMLImageLabeler zzhVar;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzd.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        zzd.zza(obtainAndWriteInterfaceToken, onDeviceAutoMLImageLabelerOptionsParcel);
        Parcel zza = zza(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            zzhVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabeler");
            if (queryLocalInterface instanceof IOnDeviceAutoMLImageLabeler) {
                zzhVar = (IOnDeviceAutoMLImageLabeler) queryLocalInterface;
            } else {
                zzhVar = new zzh(readStrongBinder);
            }
        }
        zza.recycle();
        return zzhVar;
    }
}
