package com.google.firebase.ml.vision.barcode.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes10.dex */
public abstract class zzh extends com.google.android.gms.internal.firebase_ml.zza implements zzi {
    public zzh() {
        super("com.google.firebase.ml.vision.barcode.internal.IBarcodeDetectorCreator");
    }

    public static zzi asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.ml.vision.barcode.internal.IBarcodeDetectorCreator");
        if (queryLocalInterface instanceof zzi) {
            return (zzi) queryLocalInterface;
        }
        return new zzj(iBinder);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zza
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            IBarcodeDetector newBarcodeDetector = newBarcodeDetector((BarcodeDetectorOptionsParcel) com.google.android.gms.internal.firebase_ml.zzd.zza(parcel, BarcodeDetectorOptionsParcel.CREATOR));
            parcel2.writeNoException();
            com.google.android.gms.internal.firebase_ml.zzd.zza(parcel2, newBarcodeDetector);
            return true;
        }
        return false;
    }
}
