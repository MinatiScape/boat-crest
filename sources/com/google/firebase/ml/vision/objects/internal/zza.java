package com.google.firebase.ml.vision.objects.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.firebase_ml.zzsb;
/* loaded from: classes10.dex */
public final class zza extends com.google.android.gms.internal.firebase_ml.zzb implements IObjectDetector {
    public zza(IBinder iBinder) {
        super(iBinder, "com.google.firebase.ml.vision.objects.internal.IObjectDetector");
    }

    @Override // com.google.firebase.ml.vision.objects.internal.IObjectDetector
    public final void start() throws RemoteException {
        zzb(2, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.firebase.ml.vision.objects.internal.IObjectDetector
    public final void stop() throws RemoteException {
        zzb(3, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.firebase.ml.vision.objects.internal.IObjectDetector
    public final zzj[] zzc(IObjectWrapper iObjectWrapper, zzsb zzsbVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.firebase_ml.zzd.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        com.google.android.gms.internal.firebase_ml.zzd.zza(obtainAndWriteInterfaceToken, zzsbVar);
        Parcel zza = zza(1, obtainAndWriteInterfaceToken);
        zzj[] zzjVarArr = (zzj[]) zza.createTypedArray(zzj.CREATOR);
        zza.recycle();
        return zzjVarArr;
    }
}
