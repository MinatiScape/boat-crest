package com.google.firebase.ml.vision.barcode.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.firebase_ml.zzb;
/* loaded from: classes10.dex */
public final class zzj extends zzb implements zzi {
    public zzj(IBinder iBinder) {
        super(iBinder, "com.google.firebase.ml.vision.barcode.internal.IBarcodeDetectorCreator");
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzi
    public final IBarcodeDetector newBarcodeDetector(BarcodeDetectorOptionsParcel barcodeDetectorOptionsParcel) throws RemoteException {
        IBarcodeDetector zzgVar;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.firebase_ml.zzd.zza(obtainAndWriteInterfaceToken, barcodeDetectorOptionsParcel);
        Parcel zza = zza(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            zzgVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.firebase.ml.vision.barcode.internal.IBarcodeDetector");
            if (queryLocalInterface instanceof IBarcodeDetector) {
                zzgVar = (IBarcodeDetector) queryLocalInterface;
            } else {
                zzgVar = new zzg(readStrongBinder);
            }
        }
        zza.recycle();
        return zzgVar;
    }
}
