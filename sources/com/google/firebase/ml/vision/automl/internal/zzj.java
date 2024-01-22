package com.google.firebase.ml.vision.automl.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.firebase_ml.zza;
import com.google.android.gms.internal.firebase_ml.zzd;
/* loaded from: classes10.dex */
public abstract class zzj extends zza implements zzg {
    public zzj() {
        super("com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabelerCreator");
    }

    public static zzg asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabelerCreator");
        if (queryLocalInterface instanceof zzg) {
            return (zzg) queryLocalInterface;
        }
        return new zzi(iBinder);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zza
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            IOnDeviceAutoMLImageLabeler newOnDeviceAutoMLImageLabeler = newOnDeviceAutoMLImageLabeler(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (OnDeviceAutoMLImageLabelerOptionsParcel) zzd.zza(parcel, OnDeviceAutoMLImageLabelerOptionsParcel.CREATOR));
            parcel2.writeNoException();
            zzd.zza(parcel2, newOnDeviceAutoMLImageLabeler);
            return true;
        }
        return false;
    }
}
