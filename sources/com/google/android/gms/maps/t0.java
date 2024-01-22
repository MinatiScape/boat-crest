package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzs;
/* loaded from: classes10.dex */
public final class t0 extends zzs {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnCameraMoveListener f10097a;

    public t0(GoogleMap googleMap, GoogleMap.OnCameraMoveListener onCameraMoveListener) {
        this.f10097a = onCameraMoveListener;
    }

    @Override // com.google.android.gms.maps.internal.zzt
    public final void zzb() {
        this.f10097a.onCameraMove();
    }
}
