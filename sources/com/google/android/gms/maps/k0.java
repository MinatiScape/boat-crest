package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzo;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.model.GroundOverlay;
/* loaded from: classes10.dex */
public final class k0 extends zzy {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnGroundOverlayClickListener f10066a;

    public k0(GoogleMap googleMap, GoogleMap.OnGroundOverlayClickListener onGroundOverlayClickListener) {
        this.f10066a = onGroundOverlayClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzz
    public final void zzb(zzo zzoVar) {
        this.f10066a.onGroundOverlayClick(new GroundOverlay(zzoVar));
    }
}
