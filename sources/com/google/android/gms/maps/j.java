package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbi;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
/* loaded from: classes10.dex */
public final class j extends zzbi {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener f10063a;

    public j(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener onStreetViewPanoramaCameraChangeListener) {
        this.f10063a = onStreetViewPanoramaCameraChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbj
    public final void zzb(StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.f10063a.onStreetViewPanoramaCameraChange(streetViewPanoramaCamera);
    }
}
