package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbe;
import com.google.android.gms.maps.model.Polygon;
/* loaded from: classes10.dex */
public final class m0 extends zzbe {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnPolygonClickListener f10070a;

    public m0(GoogleMap googleMap, GoogleMap.OnPolygonClickListener onPolygonClickListener) {
        this.f10070a = onPolygonClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbf
    public final void zzb(zzaa zzaaVar) {
        this.f10070a.onPolygonClick(new Polygon(zzaaVar));
    }
}
