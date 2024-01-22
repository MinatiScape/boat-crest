package com.mappls.sdk.direction.ui.plugin;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.mappls.sdk.direction.ui.plugin.j;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.Style;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
/* loaded from: classes11.dex */
public final class m implements Style.OnStyleLoaded {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Map f12622a;
    public final /* synthetic */ j b;

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ Style h;

        public a(Style style) {
            this.h = style;
        }

        @Override // java.lang.Runnable
        public final void run() {
            for (final String str : m.this.f12622a.keySet()) {
                if (this.h.getImage(str) == null) {
                    m mVar = m.this;
                    j jVar = mVar.b;
                    final Style style = this.h;
                    j.c cVar = new j.c() { // from class: com.mappls.sdk.direction.ui.plugin.l
                        @Override // com.mappls.sdk.direction.ui.plugin.j.c
                        public final void a(Bitmap bitmap) {
                            Style.this.addImageAsync(str, bitmap);
                        }
                    };
                    jVar.getClass();
                    Executors.newSingleThreadExecutor().execute(new n((String) mVar.f12622a.get(str), cVar));
                }
            }
        }
    }

    public m(j jVar, HashMap hashMap) {
        this.b = jVar;
        this.f12622a = hashMap;
    }

    @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
    public final void onStyleLoaded(@NonNull Style style) {
        MapView mapView;
        mapView = this.b.i;
        mapView.post(new a(style));
    }
}
