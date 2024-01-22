package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzq;
/* loaded from: classes10.dex */
public final class u0 extends zzq {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnCameraMoveCanceledListener f10099a;

    public u0(GoogleMap googleMap, GoogleMap.OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        this.f10099a = onCameraMoveCanceledListener;
    }

    @Override // com.google.android.gms.maps.internal.zzr
    public final void zzb() {
        this.f10099a.onCameraMoveCanceled();
    }
}
