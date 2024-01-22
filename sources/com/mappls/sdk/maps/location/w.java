package com.mappls.sdk.maps.location;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.style.layers.SymbolLayer;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import com.mappls.sdk.maps.utils.ColorUtils;
import java.util.Set;
/* loaded from: classes11.dex */
public final class w implements m {

    /* renamed from: a  reason: collision with root package name */
    public Style f12785a;
    public final f b;
    public final Set<String> c;
    public Feature d;
    public GeoJsonSource e;

    public w(f fVar, e eVar, boolean z) {
        this.b = fVar;
        this.c = fVar.f();
        this.d = eVar.a(this.d, z);
    }

    public final void A(Point point) {
        JsonObject properties = this.d.properties();
        if (properties != null) {
            this.d = Feature.fromGeometry(point, properties);
            x();
        }
    }

    public final void B(float f) {
        this.d.addNumberProperty("mappls-property-accuracy-radius", Float.valueOf(f));
        x();
    }

    public final void C(float f) {
        y("mappls-property-gps-bearing", f);
    }

    public final void D(double d) {
        JsonArray jsonArray = new JsonArray();
        Float valueOf = Float.valueOf(0.0f);
        jsonArray.add(valueOf);
        jsonArray.add(Float.valueOf((float) ((-0.05d) * d)));
        this.d.addProperty("mappls-property-foreground-icon-offset", jsonArray);
        JsonArray jsonArray2 = new JsonArray();
        jsonArray2.add(valueOf);
        jsonArray2.add(Float.valueOf((float) (d * 0.05d)));
        this.d.addProperty("mappls-property-shadow-icon-offset", jsonArray2);
        x();
    }

    @Override // com.mappls.sdk.maps.location.m
    public void a(int i, @Nullable Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6) {
        Style style = this.f12785a;
        if (style == null || !style.isFullyLoaded()) {
            return;
        }
        if (bitmap != null) {
            this.f12785a.addImage("mappls-location-shadow-icon", bitmap);
        } else {
            this.f12785a.removeImage("mappls-location-shadow-icon");
        }
        this.f12785a.addImage("mappls-location-stroke-icon", bitmap2);
        this.f12785a.addImage("mappls-location-background-stale-icon", bitmap3);
        this.f12785a.addImage("mappls-location-bearing-icon", bitmap4);
        this.f12785a.addImage("mappls-location-icon", bitmap5);
        this.f12785a.addImage("mappls-location-stale-icon", bitmap6);
    }

    @Override // com.mappls.sdk.maps.location.m
    public void b(boolean z) {
        z(LocationComponentConstants.PULSING_CIRCLE_LAYER, z);
    }

    @Override // com.mappls.sdk.maps.location.m
    public void c(Expression expression) {
        Style style = this.f12785a;
        if (style == null || !style.isFullyLoaded()) {
            return;
        }
        for (String str : this.c) {
            Layer layer = this.f12785a.getLayer(str);
            if (layer instanceof SymbolLayer) {
                layer.setProperties(PropertyFactory.iconSize(expression));
            }
        }
    }

    @Override // com.mappls.sdk.maps.location.m
    public void d(Float f) {
        y("mappls-property-gps-bearing", f.floatValue());
    }

    @Override // com.mappls.sdk.maps.location.m
    public void e(double d) {
        C((float) d);
    }

    @Override // com.mappls.sdk.maps.location.m
    public void f(Float f) {
        y("mappls-property-compass-bearing", f.floatValue());
    }

    @Override // com.mappls.sdk.maps.location.m
    public void g(int i, boolean z) {
        if (i == 4) {
            z(LocationComponentConstants.SHADOW_LAYER, true);
            z(LocationComponentConstants.FOREGROUND_LAYER, true);
            z(LocationComponentConstants.BACKGROUND_LAYER, true);
            z(LocationComponentConstants.ACCURACY_LAYER, !z);
            z(LocationComponentConstants.BEARING_LAYER, true);
        } else if (i == 8) {
            z(LocationComponentConstants.SHADOW_LAYER, false);
            z(LocationComponentConstants.FOREGROUND_LAYER, true);
            z(LocationComponentConstants.BACKGROUND_LAYER, true);
            z(LocationComponentConstants.ACCURACY_LAYER, false);
            z(LocationComponentConstants.BEARING_LAYER, false);
        } else if (i != 18) {
        } else {
            z(LocationComponentConstants.SHADOW_LAYER, true);
            z(LocationComponentConstants.FOREGROUND_LAYER, true);
            z(LocationComponentConstants.BACKGROUND_LAYER, true);
            z(LocationComponentConstants.ACCURACY_LAYER, !z);
            z(LocationComponentConstants.BEARING_LAYER, false);
        }
    }

    @Override // com.mappls.sdk.maps.location.m
    public void h(double d) {
        D(d);
    }

    @Override // com.mappls.sdk.maps.location.m
    public void hide() {
        for (String str : this.c) {
            z(str, false);
        }
    }

    @Override // com.mappls.sdk.maps.location.m
    public void i(float f, @Nullable Float f2) {
        this.d.addNumberProperty("mappls-property-pulsing-circle-radius", Float.valueOf(f));
        if (f2 != null) {
            this.d.addNumberProperty("mappls-property-pulsing-circle-opacity", f2);
        }
        x();
    }

    @Override // com.mappls.sdk.maps.location.m
    public void j(boolean z, int i) {
        this.d.addBooleanProperty("mappls-property-location-stale", Boolean.valueOf(z));
        x();
        if (i != 8) {
            z(LocationComponentConstants.ACCURACY_LAYER, !z);
        }
    }

    @Override // com.mappls.sdk.maps.location.m
    public void k(float f, int i) {
        this.d.addNumberProperty("mappls-property-accuracy-alpha", Float.valueOf(f));
        this.d.addStringProperty("mappls-property-accuracy-color", ColorUtils.colorToRgbaString(i));
        x();
    }

    @Override // com.mappls.sdk.maps.location.m
    public void l(LocationComponentOptions locationComponentOptions) {
        Style style = this.f12785a;
        if (style == null || !style.isFullyLoaded() || this.f12785a.getLayer(LocationComponentConstants.PULSING_CIRCLE_LAYER) == null) {
            return;
        }
        z(LocationComponentConstants.PULSING_CIRCLE_LAYER, true);
        this.f12785a.getLayer(LocationComponentConstants.PULSING_CIRCLE_LAYER).setProperties(PropertyFactory.circleRadius(Expression.get("mappls-property-pulsing-circle-radius")), PropertyFactory.circleColor(locationComponentOptions.pulseColor().intValue()), PropertyFactory.circleStrokeColor(locationComponentOptions.pulseColor().intValue()), PropertyFactory.circleOpacity(Expression.get("mappls-property-pulsing-circle-opacity")));
    }

    @Override // com.mappls.sdk.maps.location.m
    public void m(Style style) {
        this.f12785a = style;
        u();
    }

    @Override // com.mappls.sdk.maps.location.m
    public void n(LatLng latLng) {
        A(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()));
    }

    @Override // com.mappls.sdk.maps.location.m
    public void o() {
        Style style = this.f12785a;
        if (style != null && style.isFullyLoaded()) {
            for (String str : this.c) {
                this.f12785a.removeLayer(str);
            }
        }
        this.c.clear();
    }

    @Override // com.mappls.sdk.maps.location.m
    public void p(Float f) {
        B(f.floatValue());
    }

    @Override // com.mappls.sdk.maps.location.m
    public void q(k kVar) {
        Layer b = this.b.b(LocationComponentConstants.BEARING_LAYER);
        kVar.a(b);
        this.c.add(b.getId());
        w(LocationComponentConstants.FOREGROUND_LAYER, LocationComponentConstants.BEARING_LAYER);
        w(LocationComponentConstants.BACKGROUND_LAYER, LocationComponentConstants.FOREGROUND_LAYER);
        w(LocationComponentConstants.SHADOW_LAYER, LocationComponentConstants.BACKGROUND_LAYER);
        s();
        v();
    }

    @Override // com.mappls.sdk.maps.location.m
    public void r(String str, String str2, String str3, String str4, String str5) {
        this.d.addStringProperty("mappls-property-foreground-icon", str);
        this.d.addStringProperty("mappls-property-background-icon", str3);
        this.d.addStringProperty("mappls-property-foreground-stale-icon", str2);
        this.d.addStringProperty("mappls-property-background-stale-icon", str4);
        this.d.addStringProperty("mappls-property-shadow-icon", str5);
        x();
    }

    public final void s() {
        t(this.b.a(), LocationComponentConstants.BACKGROUND_LAYER);
    }

    public final void t(Layer layer, @NonNull String str) {
        Style style = this.f12785a;
        if (style == null || !style.isFullyLoaded()) {
            return;
        }
        this.f12785a.addLayerBelow(layer, str);
        this.c.add(layer.getId());
    }

    public final void u() {
        Style style = this.f12785a;
        if (style == null || !style.isFullyLoaded()) {
            return;
        }
        GeoJsonSource e = this.b.e(this.d);
        this.e = e;
        this.f12785a.addSource(e);
    }

    public final void v() {
        t(this.b.d(), LocationComponentConstants.ACCURACY_LAYER);
    }

    public final void w(@NonNull String str, @NonNull String str2) {
        t(this.b.b(str), str2);
    }

    public final void x() {
        Style style = this.f12785a;
        if (style == null || !style.isFullyLoaded() || ((GeoJsonSource) this.f12785a.getSourceAs(LocationComponentConstants.LOCATION_SOURCE)) == null) {
            return;
        }
        this.e.setGeoJson(this.d);
    }

    public final void y(@NonNull String str, float f) {
        this.d.addNumberProperty(str, Float.valueOf(f));
        x();
    }

    public final void z(@NonNull String str, boolean z) {
        Layer layer;
        Style style = this.f12785a;
        if (style == null || !style.isFullyLoaded() || (layer = this.f12785a.getLayer(str)) == null) {
            return;
        }
        String str2 = Property.VISIBLE;
        if (layer.getVisibility().value.equals(z ? Property.VISIBLE : "none")) {
            return;
        }
        PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
        if (!z) {
            str2 = "none";
        }
        propertyValueArr[0] = PropertyFactory.visibility(str2);
        layer.setProperties(propertyValueArr);
    }
}
