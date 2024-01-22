package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbk;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
/* loaded from: classes10.dex */
public final class i extends zzbk {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaChangeListener f10060a;

    public i(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaChangeListener onStreetViewPanoramaChangeListener) {
        this.f10060a = onStreetViewPanoramaChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbl
    public final void zzb(StreetViewPanoramaLocation streetViewPanoramaLocation) {
        this.f10060a.onStreetViewPanoramaChange(streetViewPanoramaLocation);
    }
}
