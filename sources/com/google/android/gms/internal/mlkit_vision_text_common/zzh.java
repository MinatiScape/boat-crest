package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes6.dex */
public final class zzh extends zza {
    public zzh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
    }

    public final void zzd() throws RemoteException {
        zzc(2, zza());
    }

    public final zzl[] zze(IObjectWrapper iObjectWrapper, zzd zzdVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, iObjectWrapper);
        zzc.zzb(zza, zzdVar);
        Parcel zzb = zzb(1, zza);
        zzl[] zzlVarArr = (zzl[]) zzb.createTypedArray(zzl.CREATOR);
        zzb.recycle();
        return zzlVarArr;
    }
}
