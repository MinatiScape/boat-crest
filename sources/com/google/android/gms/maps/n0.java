package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzad;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbg;
import com.google.android.gms.maps.model.Polyline;
/* loaded from: classes10.dex */
public final class n0 extends zzbg {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnPolylineClickListener f10088a;

    public n0(GoogleMap googleMap, GoogleMap.OnPolylineClickListener onPolylineClickListener) {
        this.f10088a = onPolylineClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbh
    public final void zzb(zzad zzadVar) {
        this.f10088a.onPolylineClick(new Polyline(zzadVar));
    }
}
