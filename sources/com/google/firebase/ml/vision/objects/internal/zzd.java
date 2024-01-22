package com.google.firebase.ml.vision.objects.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes10.dex */
public final class zzd extends com.google.android.gms.internal.firebase_ml.zzb implements zzc {
    public zzd(IBinder iBinder) {
        super(iBinder, "com.google.firebase.ml.vision.objects.internal.IObjectDetectorCreator");
    }

    @Override // com.google.firebase.ml.vision.objects.internal.zzc
    public final IObjectDetector newObjectDetector(IObjectWrapper iObjectWrapper, ObjectDetectorOptionsParcel objectDetectorOptionsParcel) throws RemoteException {
        IObjectDetector zzaVar;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.firebase_ml.zzd.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        com.google.android.gms.internal.firebase_ml.zzd.zza(obtainAndWriteInterfaceToken, objectDetectorOptionsParcel);
        Parcel zza = zza(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            zzaVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.firebase.ml.vision.objects.internal.IObjectDetector");
            if (queryLocalInterface instanceof IObjectDetector) {
                zzaVar = (IObjectDetector) queryLocalInterface;
            } else {
                zzaVar = new zza(readStrongBinder);
            }
        }
        zza.recycle();
        return zzaVar;
    }
}
