package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.Marker;
/* loaded from: classes10.dex */
public final class c0 extends zzae {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnInfoWindowCloseListener f10051a;

    public c0(GoogleMap googleMap, GoogleMap.OnInfoWindowCloseListener onInfoWindowCloseListener) {
        this.f10051a = onInfoWindowCloseListener;
    }

    @Override // com.google.android.gms.maps.internal.zzaf
    public final void zzb(zzx zzxVar) {
        this.f10051a.onInfoWindowClose(new Marker(zzxVar));
    }
}
