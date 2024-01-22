package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzw;
import com.google.android.gms.maps.model.Circle;
/* loaded from: classes10.dex */
public final class l0 extends zzw {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnCircleClickListener f10068a;

    public l0(GoogleMap googleMap, GoogleMap.OnCircleClickListener onCircleClickListener) {
        this.f10068a = onCircleClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzx
    public final void zzb(zzl zzlVar) {
        this.f10068a.onCircleClick(new Circle(zzlVar));
    }
}
