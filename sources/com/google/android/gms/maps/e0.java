package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzay;
/* loaded from: classes10.dex */
public final class e0 extends zzay {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnMyLocationChangeListener f10054a;

    public e0(GoogleMap googleMap, GoogleMap.OnMyLocationChangeListener onMyLocationChangeListener) {
        this.f10054a = onMyLocationChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzaz
    public final void zzb(IObjectWrapper iObjectWrapper) {
        this.f10054a.onMyLocationChange((Location) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
