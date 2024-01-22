package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzu;
/* loaded from: classes10.dex */
public final class s0 extends zzu {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnCameraMoveStartedListener f10095a;

    public s0(GoogleMap googleMap, GoogleMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        this.f10095a = onCameraMoveStartedListener;
    }

    @Override // com.google.android.gms.maps.internal.zzv
    public final void zzb(int i) {
        this.f10095a.onCameraMoveStarted(i);
    }
}
