package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzh;
import com.google.android.gms.maps.model.Marker;
/* loaded from: classes10.dex */
public final class d0 extends zzh {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.InfoWindowAdapter f10053a;

    public d0(GoogleMap googleMap, GoogleMap.InfoWindowAdapter infoWindowAdapter) {
        this.f10053a = infoWindowAdapter;
    }

    @Override // com.google.android.gms.maps.internal.zzi
    public final IObjectWrapper zzb(zzx zzxVar) {
        return ObjectWrapper.wrap(this.f10053a.getInfoContents(new Marker(zzxVar)));
    }

    @Override // com.google.android.gms.maps.internal.zzi
    public final IObjectWrapper zzc(zzx zzxVar) {
        return ObjectWrapper.wrap(this.f10053a.getInfoWindow(new Marker(zzxVar)));
    }
}
