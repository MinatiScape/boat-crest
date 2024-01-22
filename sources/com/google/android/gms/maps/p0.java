package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbc;
import com.google.android.gms.maps.model.PointOfInterest;
/* loaded from: classes10.dex */
public final class p0 extends zzbc {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnPoiClickListener f10091a;

    public p0(GoogleMap googleMap, GoogleMap.OnPoiClickListener onPoiClickListener) {
        this.f10091a = onPoiClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbd
    public final void zzb(PointOfInterest pointOfInterest) throws RemoteException {
        this.f10091a.onPoiClick(pointOfInterest);
    }
}
