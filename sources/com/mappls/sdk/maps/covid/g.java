package com.mappls.sdk.maps.covid;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.style.layers.FillLayer;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.SymbolLayer;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import com.mappls.sdk.plugin.annotation.FillOptions;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes11.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public MapplsMap f12713a;
    public MapView b;
    public FeatureCollection c = FeatureCollection.fromFeatures(new ArrayList());
    public f d;

    /* loaded from: classes11.dex */
    public class a implements Style.OnStyleLoaded {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            if (style.getLayer("annotation_layer_fill_layer") == null) {
                style.addLayer(new FillLayer("annotation_layer_fill_layer", "WMS_SOURCE_FILL_LAYER"));
            }
            g.this.k((FillLayer) style.getLayer("annotation_layer_fill_layer"));
            if (style.getLayer("annotation_layer_info_window") == null) {
                style.addLayerAbove(new SymbolLayer("annotation_layer_info_window", "WMS_SOURCE_FILL_LAYER").withProperties(PropertyFactory.iconImage("{name}"), PropertyFactory.iconAnchor("bottom"), PropertyFactory.iconAllowOverlap(Boolean.TRUE), PropertyFactory.iconOffset(new Float[]{Float.valueOf(-2.0f), Float.valueOf(-25.0f)})), "annotation_layer_fill_layer");
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Style.OnStyleLoaded {
        public b() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            if (style.getSource("WMS_SOURCE_FILL_LAYER") == null) {
                style.addSource(new GeoJsonSource("WMS_SOURCE_FILL_LAYER", g.this.c));
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ HashMap f12716a;

        public c(g gVar, HashMap hashMap) {
            this.f12716a = hashMap;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            style.addImages(this.f12716a);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Style.OnStyleLoaded {
        public d() {
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            GeoJsonSource geoJsonSource = (GeoJsonSource) style.getSource("WMS_SOURCE_FILL_LAYER");
            if (geoJsonSource == null) {
                g.this.h();
            } else {
                geoJsonSource.setGeoJson(g.this.c);
            }
        }
    }

    public g(MapplsMap mapplsMap, MapView mapView) {
        this.f12713a = mapplsMap;
        this.b = mapView;
    }

    public final void d() {
        this.f12713a.getStyle(new a());
    }

    public final void e() {
        this.f12713a.getStyle(new b());
    }

    public void f() {
        if (this.d != null) {
            this.c = FeatureCollection.fromFeatures(new ArrayList());
            l();
        }
    }

    public f g(FeatureCollection featureCollection, LatLng latLng, CovidAnnotationOption covidAnnotationOption) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(FillOptions.PROPERTY_FILL_COLOR, covidAnnotationOption.b());
        jsonObject.addProperty(FillOptions.PROPERTY_FILL_OPACITY, covidAnnotationOption.c());
        jsonObject.addProperty(FillOptions.PROPERTY_FILL_OUTLINE_COLOR, covidAnnotationOption.d());
        this.d = new f(jsonObject);
        this.c = featureCollection;
        if (featureCollection != null) {
            new i(this, this.b).execute(featureCollection);
            l();
        }
        return this.d;
    }

    public final void h() {
        e();
        d();
    }

    public void i() {
        if (this.d != null) {
            l();
        }
    }

    public void j(HashMap<String, Bitmap> hashMap) {
        MapplsMap mapplsMap = this.f12713a;
        if (mapplsMap != null) {
            mapplsMap.getStyle(new c(this, hashMap));
        }
    }

    public final void k(FillLayer fillLayer) {
        JsonObject a2;
        f fVar = this.d;
        if (fVar == null || (a2 = fVar.a()) == null) {
            return;
        }
        if (!(a2.get(FillOptions.PROPERTY_FILL_COLOR) instanceof JsonNull)) {
            fillLayer.setProperties(PropertyFactory.fillColor(a2.get(FillOptions.PROPERTY_FILL_COLOR).getAsInt()));
        }
        if (!(a2.get(FillOptions.PROPERTY_FILL_OPACITY) instanceof JsonNull)) {
            fillLayer.setProperties(PropertyFactory.fillOpacity(Float.valueOf(a2.get(FillOptions.PROPERTY_FILL_OPACITY).getAsFloat())));
        }
        if (a2.get(FillOptions.PROPERTY_FILL_OUTLINE_COLOR) instanceof JsonNull) {
            return;
        }
        fillLayer.setProperties(PropertyFactory.fillOutlineColor(a2.get(FillOptions.PROPERTY_FILL_OUTLINE_COLOR).getAsInt()));
    }

    public void l() {
        this.f12713a.getStyle(new d());
    }
}
