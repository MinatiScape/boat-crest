package com.mappls.sdk.direction.ui.plugin;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import java.util.HashMap;
/* loaded from: classes11.dex */
public final class i implements Runnable {
    public final /* synthetic */ HashMap h;
    public final /* synthetic */ d i;

    /* loaded from: classes11.dex */
    public class a implements Style.OnStyleLoaded {
        public a() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            style.addImagesAsync(i.this.h);
        }
    }

    public i(d dVar, HashMap hashMap) {
        this.i = dVar;
        this.h = hashMap;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MapplsMap mapplsMap;
        mapplsMap = this.i.j;
        mapplsMap.getStyle(new a());
    }
}
