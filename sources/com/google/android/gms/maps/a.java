package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzas;
import com.google.android.gms.maps.model.Marker;
/* loaded from: classes10.dex */
public final class a extends zzas {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnMarkerClickListener f10046a;

    public a(GoogleMap googleMap, GoogleMap.OnMarkerClickListener onMarkerClickListener) {
        this.f10046a = onMarkerClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzat
    public final boolean zzb(zzx zzxVar) {
        return this.f10046a.onMarkerClick(new Marker(zzxVar));
    }
}
