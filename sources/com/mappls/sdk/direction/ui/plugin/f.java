package com.mappls.sdk.direction.ui.plugin;

import androidx.annotation.NonNull;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class f implements Runnable {
    public final /* synthetic */ d h;

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ List h;

        /* renamed from: com.mappls.sdk.direction.ui.plugin.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0619a implements Style.OnStyleLoaded {
            public C0619a() {
            }

            @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
            public final void onStyleLoaded(@NonNull Style style) {
                FeatureCollection fromFeatures = FeatureCollection.fromFeatures(a.this.h);
                GeoJsonSource geoJsonSource = (GeoJsonSource) style.getSourceAs("com.mappls.sdk.directions.DIRECTIONS_SOURCE_ID");
                if (geoJsonSource != null) {
                    geoJsonSource.setGeoJson(fromFeatures);
                }
                d.g(f.this.h, true, style);
            }
        }

        public a(ArrayList arrayList) {
            this.h = arrayList;
        }

        @Override // java.lang.Runnable
        public final void run() {
            MapplsMap mapplsMap;
            mapplsMap = f.this.h.j;
            mapplsMap.getStyle(new C0619a());
        }
    }

    public f(d dVar) {
        this.h = dVar;
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x001d */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.direction.ui.plugin.f.run():void");
    }
}
