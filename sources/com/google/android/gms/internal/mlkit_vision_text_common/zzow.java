package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes6.dex */
public final class zzow extends zza {
    public zzow(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.text.aidls.ITextRecognizer");
    }

    public final zzpg zzd(IObjectWrapper iObjectWrapper, zzou zzouVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, iObjectWrapper);
        zzc.zzb(zza, zzouVar);
        Parcel zzb = zzb(3, zza);
        zzpg zzpgVar = (zzpg) zzc.zza(zzb, zzpg.CREATOR);
        zzb.recycle();
        return zzpgVar;
    }

    public final void zze() throws RemoteException {
        zzc(1, zza());
    }

    public final void zzf() throws RemoteException {
        zzc(2, zza());
    }
}
