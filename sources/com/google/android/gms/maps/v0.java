package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzo;
/* loaded from: classes10.dex */
public final class v0 extends zzo {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnCameraIdleListener f10100a;

    public v0(GoogleMap googleMap, GoogleMap.OnCameraIdleListener onCameraIdleListener) {
        this.f10100a = onCameraIdleListener;
    }

    @Override // com.google.android.gms.maps.internal.zzp
    public final void zzb() {
        this.f10100a.onCameraIdle();
    }
}
