package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzag;
import com.google.android.gms.maps.model.Marker;
/* loaded from: classes10.dex */
public final class b0 extends zzag {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnInfoWindowLongClickListener f10049a;

    public b0(GoogleMap googleMap, GoogleMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        this.f10049a = onInfoWindowLongClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzah
    public final void zzb(zzx zzxVar) {
        this.f10049a.onInfoWindowLongClick(new Marker(zzxVar));
    }
}
