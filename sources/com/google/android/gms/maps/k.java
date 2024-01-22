package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbm;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
/* loaded from: classes10.dex */
public final class k extends zzbm {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaClickListener f10065a;

    public k(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaClickListener onStreetViewPanoramaClickListener) {
        this.f10065a = onStreetViewPanoramaClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbn
    public final void zzb(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        this.f10065a.onStreetViewPanoramaClick(streetViewPanoramaOrientation);
    }
}
