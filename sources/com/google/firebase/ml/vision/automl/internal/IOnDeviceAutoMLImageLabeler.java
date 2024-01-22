package com.google.firebase.ml.vision.automl.internal;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.firebase_ml.zzd;
import com.google.android.gms.internal.firebase_ml.zzsb;
/* loaded from: classes10.dex */
public interface IOnDeviceAutoMLImageLabeler extends IInterface {

    /* loaded from: classes10.dex */
    public static abstract class zza extends com.google.android.gms.internal.firebase_ml.zza implements IOnDeviceAutoMLImageLabeler {
        public zza() {
            super("com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabeler");
        }

        @Override // com.google.android.gms.internal.firebase_ml.zza
        public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                zzl[] zza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzsb) zzd.zza(parcel, zzsb.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedArray(zza, 1);
            } else if (i == 2) {
                zzol();
                parcel2.writeNoException();
            } else if (i == 3) {
                close();
                parcel2.writeNoException();
            } else if (i != 4) {
                return false;
            } else {
                boolean zzou = zzou();
                parcel2.writeNoException();
                zzd.writeBoolean(parcel2, zzou);
            }
            return true;
        }
    }

    void close() throws RemoteException;

    zzl[] zza(IObjectWrapper iObjectWrapper, zzsb zzsbVar) throws RemoteException;

    void zzol() throws RemoteException;

    boolean zzou() throws RemoteException;
}
