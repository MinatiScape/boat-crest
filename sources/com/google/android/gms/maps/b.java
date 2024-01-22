package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzc;
/* loaded from: classes10.dex */
public final class b extends zzc {

    /* renamed from: a  reason: collision with root package name */
    public final GoogleMap.CancelableCallback f10048a;

    public b(GoogleMap.CancelableCallback cancelableCallback) {
        this.f10048a = cancelableCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzd
    public final void zzb() {
        this.f10048a.onCancel();
    }

    @Override // com.google.android.gms.maps.internal.zzd
    public final void zzc() {
        this.f10048a.onFinish();
    }
}
