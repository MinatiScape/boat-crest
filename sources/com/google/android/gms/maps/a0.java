package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzac;
import com.google.android.gms.maps.model.Marker;
/* loaded from: classes10.dex */
public final class a0 extends zzac {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnInfoWindowClickListener f10047a;

    public a0(GoogleMap googleMap, GoogleMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        this.f10047a = onInfoWindowClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzad
    public final void zzb(zzx zzxVar) {
        this.f10047a.onInfoWindowClick(new Marker(zzxVar));
    }
}
