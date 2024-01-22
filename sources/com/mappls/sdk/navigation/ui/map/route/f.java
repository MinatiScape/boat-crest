package com.mappls.sdk.navigation.ui.map.route;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.utils.PolylineUtils;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.LineLayer;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.SymbolLayer;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import com.mappls.sdk.maps.utils.BitmapUtils;
import com.mappls.sdk.maps.utils.MathUtils;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.camera.RouteInformation;
import com.mappls.sdk.navigation.routing.NavigationStep;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.services.api.directions.models.LegStep;
import com.mappls.sdk.turf.TurfConstants;
import com.mappls.sdk.turf.TurfMeasurement;
import com.mappls.sdk.turf.TurfMisc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes11.dex */
public class f {
    @ColorInt

    /* renamed from: a  reason: collision with root package name */
    public final int f12976a;
    @ColorInt
    public final int b;
    public final MapView c;
    public final MapplsMap d;
    public List<String> e;
    public GeoJsonSource f;
    public GeoJsonSource g;
    public List<Point> h;

    /* loaded from: classes11.dex */
    public class a implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f12977a;

        public a(String str) {
            this.f12977a = str;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            f.f(f.this, this.f12977a, style);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f12978a;

        public b(String str) {
            this.f12978a = str;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            if (style.getSource("mappls-navigation-arrow-head-source") == null || style.getSource("mappls-navigation-arrow-shaft-source") == null) {
                f.f(f.this, this.f12978a, style);
                return;
            }
            f.this.g = (GeoJsonSource) style.getSource("mappls-navigation-arrow-head-source");
            f.this.f = (GeoJsonSource) style.getSource("mappls-navigation-arrow-shaft-source");
            if (f.this.g == null || f.this.f == null) {
                return;
            }
            f fVar = f.this;
            fVar.m(fVar.h);
            f fVar2 = f.this;
            fVar2.h(fVar2.h);
        }
    }

    public f(MapView mapView, MapplsMap mapplsMap, String str, @StyleRes int i) {
        this(mapView, mapplsMap, str, i, null);
    }

    public f(MapView mapView, MapplsMap mapplsMap, String str, @StyleRes int i, List<Point> list) {
        this.c = mapView;
        this.d = mapplsMap;
        Context context = mapView.getContext();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.NavigationMapRoute);
        this.f12976a = obtainStyledAttributes.getColor(R.styleable.NavigationMapRoute_upcomingManeuverArrowColor, ContextCompat.getColor(context, R.color.white));
        this.b = obtainStyledAttributes.getColor(R.styleable.NavigationMapRoute_upcomingManeuverArrowBorderColor, ContextCompat.getColor(context, R.color.colorGray700));
        obtainStyledAttributes.recycle();
        this.h = null;
        mapplsMap.getStyle(new a(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void f(f fVar, String str, Style style) {
        fVar.getClass();
        if (style.getSource("mappls-navigation-arrow-shaft-source") == null) {
            GeoJsonSource geoJsonSource = new GeoJsonSource("mappls-navigation-arrow-shaft-source", FeatureCollection.fromFeatures(new Feature[0]), new GeoJsonOptions().withMaxZoom(16));
            fVar.f = geoJsonSource;
            style.addSource(geoJsonSource);
        }
        List<Point> list = fVar.h;
        if (list != null) {
            fVar.m(list);
        }
        if (style.getSource("mappls-navigation-arrow-head-source") == null) {
            GeoJsonSource geoJsonSource2 = new GeoJsonSource("mappls-navigation-arrow-head-source", FeatureCollection.fromFeatures(new Feature[0]), new GeoJsonOptions().withMaxZoom(16));
            fVar.g = geoJsonSource2;
            style.addSource(geoJsonSource2);
        }
        List<Point> list2 = fVar.h;
        if (list2 != null) {
            fVar.h(list2);
        }
        Drawable drawable = AppCompatResources.getDrawable(fVar.c.getContext(), R.drawable.ic_arrow_head);
        if (drawable != null) {
            Drawable wrap = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(wrap.mutate(), fVar.f12976a);
            style.addImage("mappls-navigation-arrow-head-icon", BitmapUtils.getBitmapFromDrawable(wrap));
        }
        Drawable drawable2 = AppCompatResources.getDrawable(fVar.c.getContext(), R.drawable.ic_arrow_head_casing);
        if (drawable2 != null) {
            Drawable wrap2 = DrawableCompat.wrap(drawable2);
            DrawableCompat.setTint(wrap2.mutate(), fVar.b);
            style.addImage("mappls-navigation-arrow-head-icon-casing", BitmapUtils.getBitmapFromDrawable(wrap2));
        }
        Layer layer = (LineLayer) style.getLayerAs("mappls-navigation-arrow-shaft-layer");
        if (layer != null) {
            style.removeLayer(layer);
        }
        Layer withProperties = new LineLayer("mappls-navigation-arrow-shaft-layer", "mappls-navigation-arrow-shaft-source").withProperties(PropertyFactory.lineColor(Expression.color(fVar.f12976a)), PropertyFactory.lineWidth(Expression.interpolate(Expression.linear(), Expression.zoom(), Expression.stop(10, Float.valueOf(2.6f)), Expression.stop(22, Float.valueOf(13.0f)))), PropertyFactory.lineCap("round"), PropertyFactory.lineJoin("round"), PropertyFactory.visibility("none"), PropertyFactory.lineOpacity(Expression.step(Expression.zoom(), Float.valueOf(0.0f), Expression.stop(14, Float.valueOf(1.0f)))));
        Layer layer2 = (LineLayer) style.getLayer("mappls-navigation-arrow-shaft-casing-layer");
        if (layer2 != null) {
            style.removeLayer(layer2);
        }
        Layer withProperties2 = new LineLayer("mappls-navigation-arrow-shaft-casing-layer", "mappls-navigation-arrow-shaft-source").withProperties(PropertyFactory.lineColor(Expression.color(fVar.b)), PropertyFactory.lineWidth(Expression.interpolate(Expression.linear(), Expression.zoom(), Expression.stop(10, Float.valueOf(3.4f)), Expression.stop(22, Float.valueOf(17.0f)))), PropertyFactory.lineCap("round"), PropertyFactory.lineJoin("round"), PropertyFactory.visibility("none"), PropertyFactory.lineOpacity(Expression.step(Expression.zoom(), Float.valueOf(0.0f), Expression.stop(14, Float.valueOf(1.0f)))));
        Layer layer3 = (SymbolLayer) style.getLayer("mappls-navigation-arrow-head-layer");
        if (layer3 != null) {
            style.removeLayer(layer3);
        }
        SymbolLayer symbolLayer = new SymbolLayer("mappls-navigation-arrow-head-layer", "mappls-navigation-arrow-head-source");
        Boolean bool = Boolean.TRUE;
        Layer withProperties3 = symbolLayer.withProperties(PropertyFactory.iconImage("mappls-navigation-arrow-head-icon"), PropertyFactory.iconAllowOverlap(bool), PropertyFactory.iconIgnorePlacement(bool), PropertyFactory.iconSize(Expression.interpolate(Expression.linear(), Expression.zoom(), Expression.stop(10, Float.valueOf(0.2f)), Expression.stop(22, Float.valueOf(0.8f)))), PropertyFactory.iconOffset(y.b), PropertyFactory.iconRotationAlignment("map"), PropertyFactory.iconRotate(Expression.get("mappls-navigation-arrow-bearing")), PropertyFactory.visibility("none"), PropertyFactory.iconOpacity(Expression.step(Expression.zoom(), Float.valueOf(0.0f), Expression.stop(14, Float.valueOf(1.0f)))));
        Layer layer4 = (SymbolLayer) style.getLayer("mappls-navigation-arrow-head-casing-layer");
        if (layer4 != null) {
            style.removeLayer(layer4);
        }
        Layer withProperties4 = new SymbolLayer("mappls-navigation-arrow-head-casing-layer", "mappls-navigation-arrow-head-source").withProperties(PropertyFactory.iconImage("mappls-navigation-arrow-head-icon-casing"), PropertyFactory.iconAllowOverlap(bool), PropertyFactory.iconIgnorePlacement(bool), PropertyFactory.iconSize(Expression.interpolate(Expression.linear(), Expression.zoom(), Expression.stop(10, Float.valueOf(0.2f)), Expression.stop(22, Float.valueOf(0.8f)))), PropertyFactory.iconOffset(y.f12996a), PropertyFactory.iconRotationAlignment("map"), PropertyFactory.iconRotate(Expression.get("mappls-navigation-arrow-bearing")), PropertyFactory.visibility("none"), PropertyFactory.iconOpacity(Expression.step(Expression.zoom(), Float.valueOf(0.0f), Expression.stop(14, Float.valueOf(1.0f)))));
        style.addLayerAbove(withProperties2, str);
        style.addLayerAbove(withProperties4, withProperties2.getId());
        style.addLayerAbove(withProperties, withProperties4.getId());
        style.addLayerAbove(withProperties3, withProperties.getId());
        ArrayList arrayList = new ArrayList();
        fVar.e = arrayList;
        arrayList.add(withProperties2.getId());
        fVar.e.add(withProperties.getId());
        fVar.e.add(withProperties4.getId());
        fVar.e.add(withProperties3.getId());
    }

    public String c(String str) {
        if (this.e.isEmpty()) {
            return str;
        }
        List<String> list = this.e;
        return list.get(list.size() - 1);
    }

    public void d() {
        if (this.d.getStyle() == null || !this.d.getStyle().isFullyLoaded()) {
            return;
        }
        this.d.getStyle().removeLayer("mappls-navigation-arrow-shaft-casing-layer");
        this.d.getStyle().removeLayer("mappls-navigation-arrow-shaft-layer");
        this.d.getStyle().removeLayer("mappls-navigation-arrow-head-layer");
        this.d.getStyle().removeLayer("mappls-navigation-arrow-head-casing-layer");
        this.d.getStyle().removeSource("mappls-navigation-arrow-shaft-source");
        this.d.getStyle().removeSource("mappls-navigation-arrow-head-source");
    }

    public void e(RouteInformation routeInformation) {
        if (routeInformation.getAdviseInfo() == null) {
            return;
        }
        List navigationSteps = MapplsNavigationHelper.getInstance().getNavigationSteps();
        int position = routeInformation.getAdviseInfo().getPosition();
        int position2 = routeInformation.getAdviseInfo().getPosition();
        if (position != 0) {
            position2--;
        }
        LegStep legStep = (LegStep) ((NavigationStep) navigationSteps.get(position2)).getExtraInfo();
        int i = position2 + 1;
        LegStep legStep2 = navigationSteps.size() > i ? (LegStep) ((NavigationStep) navigationSteps.get(i)).getExtraInfo() : null;
        if (legStep == null || legStep2 == null) {
            return;
        }
        List<Point> decode = PolylineUtils.decode(legStep2.geometry(), 6);
        List<Point> decode2 = PolylineUtils.decode(legStep.geometry(), 6);
        boolean z = decode == null || decode.size() < 2;
        boolean z2 = decode2.size() < 2;
        if (!z && !z2) {
            this.d.getStyle(new g(this, true));
            Collections.reverse(decode2);
            LineString fromLngLats = LineString.fromLngLats(decode2);
            LineString fromLngLats2 = LineString.fromLngLats(decode);
            LineString lineSliceAlong = TurfMisc.lineSliceAlong(fromLngLats, 0.0d, 20.0d, TurfConstants.UNIT_METERS);
            LineString lineSliceAlong2 = TurfMisc.lineSliceAlong(fromLngLats2, 0.0d, 20.0d, TurfConstants.UNIT_METERS);
            Collections.reverse(lineSliceAlong.coordinates());
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(lineSliceAlong.coordinates());
            arrayList.addAll(lineSliceAlong2.coordinates());
            this.h = arrayList;
            m(arrayList);
            h(this.h);
            return;
        }
        this.d.getStyle(new g(this, false));
    }

    public final void h(List<Point> list) {
        if (list == null) {
            return;
        }
        double bearing = TurfMeasurement.bearing(list.get(list.size() - 2), list.get(list.size() - 1));
        Feature fromGeometry = Feature.fromGeometry(list.get(list.size() - 1));
        fromGeometry.addNumberProperty("mappls-navigation-arrow-bearing", Float.valueOf((float) MathUtils.wrap(bearing, 0.0d, 360.0d)));
        this.g.setGeoJson(fromGeometry);
    }

    public void l(String str) {
        this.d.getStyle(new b(str));
    }

    public final void m(List<Point> list) {
        if (list == null) {
            return;
        }
        this.f.setGeoJson(Feature.fromGeometry(LineString.fromLngLats(list)));
    }
}
