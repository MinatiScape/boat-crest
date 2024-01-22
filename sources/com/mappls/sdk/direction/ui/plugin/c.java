package com.mappls.sdk.direction.ui.plugin;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.Style;
/* loaded from: classes11.dex */
public final class c implements Style.OnStyleLoaded {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ a f12607a;

    public c(a aVar) {
        this.f12607a = aVar;
    }

    @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
    public final void onStyleLoaded(@NonNull Style style) {
        if (style.getSource("directions_bearing") == null) {
            a.d(this.f12607a, style);
            return;
        }
        a aVar = this.f12607a;
        aVar.getClass();
        a.e(aVar, false, style);
    }
}
