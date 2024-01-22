package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzba;
/* loaded from: classes10.dex */
public final class g0 extends zzba {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnMyLocationClickListener f10058a;

    public g0(GoogleMap googleMap, GoogleMap.OnMyLocationClickListener onMyLocationClickListener) {
        this.f10058a = onMyLocationClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbb
    public final void zzb(Location location) {
        this.f10058a.onMyLocationClick(location);
    }
}
