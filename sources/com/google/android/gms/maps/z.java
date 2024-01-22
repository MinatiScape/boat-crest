package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzau;
import com.google.android.gms.maps.model.Marker;
/* loaded from: classes10.dex */
public final class z extends zzau {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnMarkerDragListener f10105a;

    public z(GoogleMap googleMap, GoogleMap.OnMarkerDragListener onMarkerDragListener) {
        this.f10105a = onMarkerDragListener;
    }

    @Override // com.google.android.gms.maps.internal.zzav
    public final void zzb(zzx zzxVar) {
        this.f10105a.onMarkerDrag(new Marker(zzxVar));
    }

    @Override // com.google.android.gms.maps.internal.zzav
    public final void zzc(zzx zzxVar) {
        this.f10105a.onMarkerDragEnd(new Marker(zzxVar));
    }

    @Override // com.google.android.gms.maps.internal.zzav
    public final void zzd(zzx zzxVar) {
        this.f10105a.onMarkerDragStart(new Marker(zzxVar));
    }
}
