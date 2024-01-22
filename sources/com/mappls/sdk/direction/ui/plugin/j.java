package com.mappls.sdk.direction.ui.plugin;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.SymbolLayer;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes11.dex */
public final class j implements MapView.OnDidFinishLoadingStyleListener {
    public MapplsMap h;
    public MapView i;
    public FeatureCollection k;
    public HashMap l;
    public Handler j = new Handler(Looper.getMainLooper());
    public Runnable m = new a();

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            j.this.g();
            j jVar = j.this;
            j.d(jVar, jVar.l);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Style.OnStyleLoaded {
        public b() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            GeoJsonSource geoJsonSource = (GeoJsonSource) style.getSource("mappls_directions_events_source_id");
            if (geoJsonSource == null) {
                j.c(j.this, style);
            } else if (j.this.k != null) {
                geoJsonSource.setGeoJson(j.this.k);
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface c {
        void a(Bitmap bitmap);
    }

    public j(MapView mapView, MapplsMap mapplsMap) {
        this.h = mapplsMap;
        this.i = mapView;
        mapView.addOnDidFinishLoadingStyleListener(this);
        g();
    }

    public static void c(j jVar, Style style) {
        style.addSource(jVar.k != null ? new GeoJsonSource("mappls_directions_events_source_id", jVar.k) : new GeoJsonSource("mappls_directions_events_source_id"));
        style.addLayerAbove(new SymbolLayer("mappls_directions_events_layer_id", "mappls_directions_events_source_id").withProperties(PropertyFactory.iconImage(Expression.get("mappls_directions_events_image_name"))), "com.mappls.sdk.directions.route_selected");
    }

    public static void d(j jVar, HashMap hashMap) {
        jVar.h.getStyle(new m(jVar, hashMap));
    }

    public final void a() {
        if (this.h.getStyle() == null || !this.h.getStyle().isFullyLoaded()) {
            return;
        }
        this.h.getStyle().removeLayer("mappls_directions_events_layer_id");
        this.h.getStyle().removeSource("mappls_directions_events_source_id");
    }

    public final void a(List<ReportDetails> list) {
        HashMap hashMap;
        String id;
        this.l = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (ReportDetails reportDetails : list) {
            Feature fromGeometry = Feature.fromGeometry(Point.fromLngLat(reportDetails.getLongitude().doubleValue(), reportDetails.getLatitude().doubleValue()));
            if (reportDetails.getChildCategory() == null || reportDetails.getParentCategory() == null) {
                fromGeometry.addStringProperty("mappls_directions_events_image_name", reportDetails.getId());
                hashMap = this.l;
                id = reportDetails.getId();
            } else {
                fromGeometry.addStringProperty("mappls_directions_events_image_name", reportDetails.getParentCategory() + "_" + reportDetails.getChildCategory());
                hashMap = this.l;
                id = reportDetails.getParentCategory() + "_" + reportDetails.getChildCategory();
            }
            hashMap.put(id, reportDetails.getReportIcon("24px"));
            arrayList.add(fromGeometry);
        }
        this.k = FeatureCollection.fromFeatures(arrayList);
        this.j.removeCallbacksAndMessages(null);
        this.j.postDelayed(this.m, 100L);
    }

    public final void b() {
        GeoJsonSource geoJsonSource;
        if (this.h.getStyle() == null || !this.h.getStyle().isFullyLoaded() || (geoJsonSource = (GeoJsonSource) this.h.getStyle().getSourceAs("mappls_directions_events_source_id")) == null) {
            return;
        }
        geoJsonSource.setGeoJson(FeatureCollection.fromFeatures(new ArrayList()));
    }

    public final void g() {
        this.h.getStyle(new b());
    }

    @Override // com.mappls.sdk.maps.MapView.OnDidFinishLoadingStyleListener
    public final void onDidFinishLoadingStyle() {
        this.j.removeCallbacksAndMessages(null);
        this.j.postDelayed(this.m, 100L);
    }
}
