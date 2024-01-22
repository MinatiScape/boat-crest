package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzaj;
import com.google.android.gms.maps.internal.zzj;
/* loaded from: classes10.dex */
public final class q0 extends zzj {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LocationSource f10093a;

    public q0(GoogleMap googleMap, LocationSource locationSource) {
        this.f10093a = locationSource;
    }

    @Override // com.google.android.gms.maps.internal.ILocationSourceDelegate
    public final void activate(zzaj zzajVar) {
        this.f10093a.activate(new j0(this, zzajVar));
    }

    @Override // com.google.android.gms.maps.internal.ILocationSourceDelegate
    public final void deactivate() {
        this.f10093a.deactivate();
    }
}
