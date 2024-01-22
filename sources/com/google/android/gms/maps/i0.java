package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzr;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzaa;
import com.google.android.gms.maps.model.IndoorBuilding;
/* loaded from: classes10.dex */
public final class i0 extends zzaa {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnIndoorStateChangeListener f10061a;

    public i0(GoogleMap googleMap, GoogleMap.OnIndoorStateChangeListener onIndoorStateChangeListener) {
        this.f10061a = onIndoorStateChangeListener;
    }

    @Override // com.google.android.gms.maps.internal.zzab
    public final void zzb() {
        this.f10061a.onIndoorBuildingFocused();
    }

    @Override // com.google.android.gms.maps.internal.zzab
    public final void zzc(zzr zzrVar) {
        this.f10061a.onIndoorLevelActivated(new IndoorBuilding(zzrVar));
    }
}
