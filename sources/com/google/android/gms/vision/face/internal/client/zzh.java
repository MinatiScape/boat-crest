package com.google.android.gms.vision.face.internal.client;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zzp;
/* loaded from: classes10.dex */
public interface zzh extends IInterface {
    FaceParcel[] zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, int i, int i2, int i3, int i4, int i5, int i6, zzp zzpVar) throws RemoteException;

    FaceParcel[] zzc(IObjectWrapper iObjectWrapper, zzp zzpVar) throws RemoteException;

    boolean zzd(int i) throws RemoteException;

    void zzm() throws RemoteException;
}
