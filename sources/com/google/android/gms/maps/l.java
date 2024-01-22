package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbo;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
/* loaded from: classes10.dex */
public final class l extends zzbo {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaLongClickListener f10067a;

    public l(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaLongClickListener onStreetViewPanoramaLongClickListener) {
        this.f10067a = onStreetViewPanoramaLongClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbp
    public final void zzb(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        this.f10067a.onStreetViewPanoramaLongClick(streetViewPanoramaOrientation);
    }
}
