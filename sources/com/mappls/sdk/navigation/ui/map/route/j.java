package com.mappls.sdk.navigation.ui.map.route;

import android.os.Handler;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.SymbolLayer;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import timber.log.Timber;
/* loaded from: classes11.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public final MapView f12981a;
    public final MapplsMap b;
    public GeoJsonSource c;
    public String d;
    public FeatureCollection e;
    public AtomicReference<com.mappls.sdk.navigation.ui.map.route.c> f;
    public s g;

    /* loaded from: classes11.dex */
    public class a implements s {
        public a() {
        }

        @Override // com.mappls.sdk.navigation.ui.map.route.s
        public void a(FeatureCollection featureCollection) {
            j.this.e = featureCollection;
            j jVar = j.this;
            jVar.f(jVar.d);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f12983a;

        public b(String str) {
            this.f12983a = str;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            j.e(j.this, this.f12983a, style);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f12984a;

        public c(String str) {
            this.f12984a = str;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            if (style.getSource("mappls-navigation-route-event-source-id") == null) {
                j.e(j.this, this.f12984a, style);
                return;
            }
            j.this.c = (GeoJsonSource) style.getSource("mappls-navigation-route-event-source-id");
            if (j.this.e == null || j.this.c == null) {
                return;
            }
            j.this.c.setGeoJson(j.this.e);
        }
    }

    public j(MapView mapView, MapplsMap mapplsMap, String str) {
        this(mapView, mapplsMap, str, null);
    }

    public j(MapView mapView, MapplsMap mapplsMap, String str, List<ReportDetails> list) {
        this.f = new AtomicReference<>(null);
        this.g = new a();
        this.f12981a = mapView;
        this.b = mapplsMap;
        this.d = str;
        mapplsMap.getStyle(new b(str));
    }

    public static void e(j jVar, String str, Style style) {
        jVar.getClass();
        if (style.getSource("mappls-navigation-route-event-source-id") == null) {
            GeoJsonSource geoJsonSource = new GeoJsonSource("mappls-navigation-route-event-source-id", FeatureCollection.fromFeatures(new Feature[0]));
            jVar.c = geoJsonSource;
            style.addSource(geoJsonSource);
        }
        FeatureCollection featureCollection = jVar.e;
        if (featureCollection != null) {
            jVar.c.setGeoJson(featureCollection);
        }
        Layer layer = (SymbolLayer) style.getLayerAs("mappls-navigation-route-event-symbol-layer");
        if (layer != null) {
            style.removeLayer(layer);
        }
        Layer withProperties = new SymbolLayer("mappls-navigation-route-event-symbol-layer", "mappls-navigation-route-event-source-id").withProperties(PropertyFactory.iconImage(Expression.get("mappls-navigation-route-event-marker")), PropertyFactory.iconAllowOverlap(Boolean.FALSE));
        withProperties.setMinZoom(13.0f);
        if (style.getLayer(str) != null) {
            style.addLayerAbove(withProperties, str);
        } else {
            style.addLayer(withProperties);
        }
    }

    public void d() {
        if (this.b.getStyle() == null || !this.b.getStyle().isFullyLoaded()) {
            return;
        }
        this.b.getStyle().removeLayer("mappls-navigation-route-event-symbol-layer");
        this.b.getStyle().removeSource("mappls-navigation-route-event-source-id");
    }

    public void f(String str) {
        this.d = str;
        this.b.getStyle(new c(str));
    }

    public void g(List<ReportDetails> list) {
        Timber.tag("addMapEvents").e(new Gson().toJson(list), new Object[0]);
        i(list);
        for (ReportDetails reportDetails : list) {
            l lVar = new l(this);
            String id = (reportDetails.getChildCategory() == null || reportDetails.getParentCategory() == null) ? reportDetails.getId() : reportDetails.getParentCategory() + "_" + reportDetails.getChildCategory();
            if (com.mappls.sdk.navigation.ui.d.a().a(id) == null) {
                Executors.newSingleThreadExecutor().execute(new m(this, reportDetails, id, lVar));
            } else {
                this.b.getStyle(new k(lVar, id, com.mappls.sdk.navigation.ui.d.a().a(id)));
            }
        }
    }

    public final void i(List<ReportDetails> list) {
        com.mappls.sdk.navigation.ui.map.route.c andSet = this.f.getAndSet(new com.mappls.sdk.navigation.ui.map.route.c(list, this.g, new Handler(this.f12981a.getContext().getMainLooper())));
        if (andSet != null) {
            andSet.b();
        }
        com.mappls.sdk.navigation.ui.map.route.c cVar = this.f.get();
        if (cVar != null) {
            cVar.start();
        }
    }
}
