package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbt;
/* loaded from: classes10.dex */
public final class o0 extends zzbt {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.SnapshotReadyCallback f10089a;

    public o0(GoogleMap googleMap, GoogleMap.SnapshotReadyCallback snapshotReadyCallback) {
        this.f10089a = snapshotReadyCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzbu
    public final void zzb(Bitmap bitmap) throws RemoteException {
        this.f10089a.onSnapshotReady(bitmap);
    }

    @Override // com.google.android.gms.maps.internal.zzbu
    public final void zzc(IObjectWrapper iObjectWrapper) throws RemoteException {
        this.f10089a.onSnapshotReady((Bitmap) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
