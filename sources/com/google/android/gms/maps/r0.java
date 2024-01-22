package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzm;
import com.google.android.gms.maps.model.CameraPosition;
/* loaded from: classes10.dex */
public final class r0 extends zzm {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnCameraChangeListener f10094a;

    public r0(GoogleMap googleMap, GoogleMap.OnCameraChangeListener onCameraChangeListener) {
        this.f10094a = onCameraChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzn
    public final void zzb(CameraPosition cameraPosition) {
        this.f10094a.onCameraChange(cameraPosition);
    }
}
