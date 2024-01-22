package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzao;
import com.google.android.gms.maps.model.LatLng;
/* loaded from: classes10.dex */
public final class x0 extends zzao {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnMapLongClickListener f10104a;

    public x0(GoogleMap googleMap, GoogleMap.OnMapLongClickListener onMapLongClickListener) {
        this.f10104a = onMapLongClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzap
    public final void zzb(LatLng latLng) {
        this.f10104a.onMapLongClick(latLng);
    }
}
