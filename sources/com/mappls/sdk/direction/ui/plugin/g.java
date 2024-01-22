package com.mappls.sdk.direction.ui.plugin;

import androidx.annotation.NonNull;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class g implements Runnable {
    public final /* synthetic */ List h;
    public final /* synthetic */ d i;

    /* loaded from: classes11.dex */
    public class a implements Style.OnStyleLoaded {
        public a() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            d.g(g.this.i, true, style);
            FeatureCollection fromFeatures = FeatureCollection.fromFeatures(g.this.h);
            GeoJsonSource geoJsonSource = (GeoJsonSource) style.getSourceAs("com.mappls.sdk.directions.DIRECTIONS_SOURCE_ID");
            if (geoJsonSource != null) {
                geoJsonSource.setGeoJson(fromFeatures);
            }
        }
    }

    public g(d dVar, ArrayList arrayList) {
        this.i = dVar;
        this.h = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MapplsMap mapplsMap;
        mapplsMap = this.i.j;
        mapplsMap.getStyle(new a());
    }
}
