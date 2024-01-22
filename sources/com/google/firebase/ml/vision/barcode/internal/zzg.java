package com.google.firebase.ml.vision.barcode.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.firebase_ml.zzb;
import com.google.android.gms.internal.firebase_ml.zzsb;
/* loaded from: classes10.dex */
public final class zzg extends zzb implements IBarcodeDetector {
    public zzg(IBinder iBinder) {
        super(iBinder, "com.google.firebase.ml.vision.barcode.internal.IBarcodeDetector");
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.IBarcodeDetector
    public final void start() throws RemoteException {
        zzb(1, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.IBarcodeDetector
    public final void stop() throws RemoteException {
        zzb(3, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.IBarcodeDetector
    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper, zzsb zzsbVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.firebase_ml.zzd.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        com.google.android.gms.internal.firebase_ml.zzd.zza(obtainAndWriteInterfaceToken, zzsbVar);
        Parcel zza = zza(2, obtainAndWriteInterfaceToken);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
        zza.recycle();
        return asInterface;
    }
}
