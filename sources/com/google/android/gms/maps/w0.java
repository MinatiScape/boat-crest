package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzak;
import com.google.android.gms.maps.model.LatLng;
/* loaded from: classes10.dex */
public final class w0 extends zzak {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnMapClickListener f10102a;

    public w0(GoogleMap googleMap, GoogleMap.OnMapClickListener onMapClickListener) {
        this.f10102a = onMapClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzal
    public final void zzb(LatLng latLng) {
        this.f10102a.onMapClick(latLng);
    }
}
