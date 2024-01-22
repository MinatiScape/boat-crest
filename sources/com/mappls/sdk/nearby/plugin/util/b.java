package com.mappls.sdk.nearby.plugin.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.Polygon;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.utils.ColorUtils;
import com.mappls.sdk.nearby.plugin.model.NearbyUIOption;
import com.mappls.sdk.plugin.annotation.FillManager;
import com.mappls.sdk.plugin.annotation.FillOptions;
import com.mappls.sdk.plugin.annotation.OnSymbolClickListener;
import com.mappls.sdk.plugin.annotation.SymbolManager;
import com.mappls.sdk.plugin.annotation.SymbolOptions;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResult;
import com.mappls.sdk.turf.TurfConstants;
import com.mappls.sdk.turf.TurfTransformation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
@SourceDebugExtension({"SMAP\nNearbyMapPlugin.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NearbyMapPlugin.kt\ncom/mappls/sdk/nearby/plugin/util/NearbyMapPlugin\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,104:1\n1855#2,2:105\n*S KotlinDebug\n*F\n+ 1 NearbyMapPlugin.kt\ncom/mappls/sdk/nearby/plugin/util/NearbyMapPlugin\n*L\n76#1:105,2\n*E\n"})
/* loaded from: classes10.dex */
public final class b {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final OnSymbolClickListener f13067a;
    @NotNull
    public final NearbyUIOption b;
    @NotNull
    public FillManager c;
    @NotNull
    public SymbolManager d;
    @NotNull
    public SymbolManager e;

    public b(@NotNull MapView mapView, @NotNull MapplsMap mapplsMap, @NotNull Style style, @NotNull OnSymbolClickListener onSymbolClickListener, @NotNull NearbyUIOption nearbyUIOption) {
        Intrinsics.checkNotNullParameter(mapView, "mapView");
        Intrinsics.checkNotNullParameter(mapplsMap, "mapplsMap");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(onSymbolClickListener, "onSymbolClickListener");
        Intrinsics.checkNotNullParameter(nearbyUIOption, "nearbyUIOption");
        this.f13067a = onSymbolClickListener;
        this.b = nearbyUIOption;
        this.c = new FillManager(mapView, mapplsMap, style);
        this.d = new SymbolManager(mapView, mapplsMap, style);
        SymbolManager symbolManager = new SymbolManager(mapView, mapplsMap, style);
        this.e = symbolManager;
        Boolean bool = Boolean.TRUE;
        symbolManager.setIconAllowOverlap(bool);
        this.e.setIconIgnorePlacement(bool);
        this.d.setIconAllowOverlap(Boolean.FALSE);
        this.d.setIconIgnorePlacement(bool);
        this.d.addClickListener(onSymbolClickListener);
    }

    public final void a() {
        System.out.println((Object) "clear");
        this.c.clearAll();
        this.e.clearAll();
        this.d.removeClickListener(this.f13067a);
        this.d.clearAll();
    }

    public final void a(@NotNull LatLng latLng) {
        Intrinsics.checkNotNullParameter(latLng, "latLng");
        System.out.println((Object) "Location Marker");
        this.e.clearAll();
        this.e.create((SymbolManager) new SymbolOptions().position(latLng).icon("com.mappls.sdk.nearby.plugin.NEARBY_REF_LOCATION_MARKER_ICON"));
    }

    public final void a(@NotNull LatLng latLng, double d) {
        Intrinsics.checkNotNullParameter(latLng, "latLng");
        System.out.println((Object) "addSelectedAreaPolygon");
        this.c.clearAll();
        Polygon circle = TurfTransformation.circle(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()), d, TurfConstants.UNIT_METRES);
        Intrinsics.checkNotNullExpressionValue(circle, "circle(Point.fromLngLat(…urfConstants.UNIT_METRES)");
        FillOptions geometry = new FillOptions().geometry(circle);
        Integer refLocationCircleColor = this.b.refLocationCircleColor();
        Intrinsics.checkNotNullExpressionValue(refLocationCircleColor, "nearbyUIOption.refLocationCircleColor()");
        this.c.create((FillManager) geometry.fillColor(ColorUtils.colorToRgbaString(refLocationCircleColor.intValue())).fillOpacity(this.b.refLocationCircleAlpha()));
    }

    public final void a(@NotNull String mapplsPin) {
        Intrinsics.checkNotNullParameter(mapplsPin, "mapplsPin");
        System.out.println((Object) "Location Marker");
        this.e.clearAll();
        this.e.create((SymbolManager) new SymbolOptions().mapplsPin(mapplsPin).icon("com.mappls.sdk.nearby.plugin.NEARBY_REF_LOCATION_MARKER_ICON"));
    }

    public final void a(@NotNull ArrayList nearbyAtlasResult, @NotNull Map iconMap) {
        Double d;
        Intrinsics.checkNotNullParameter(nearbyAtlasResult, "nearbyAtlasResult");
        Intrinsics.checkNotNullParameter(iconMap, "iconMap");
        System.out.println((Object) "addNearbyMarkers");
        this.d.clearAll();
        ArrayList arrayList = new ArrayList();
        Iterator it = nearbyAtlasResult.iterator();
        while (it.hasNext()) {
            NearbyAtlasResult nearbyAtlasResult2 = (NearbyAtlasResult) it.next();
            SymbolOptions symbolOptions = new SymbolOptions();
            if (nearbyAtlasResult2.latitude == null || (d = nearbyAtlasResult2.longitude) == null) {
                symbolOptions.mapplsPin(nearbyAtlasResult2.mapplsPin);
            } else {
                Intrinsics.checkNotNullExpressionValue(d, "it.longitude");
                double doubleValue = d.doubleValue();
                Double d2 = nearbyAtlasResult2.latitude;
                Intrinsics.checkNotNullExpressionValue(d2, "it.latitude");
                symbolOptions.geometry(Point.fromLngLat(doubleValue, d2.doubleValue()));
            }
            if (nearbyAtlasResult2.keywords.size() > 0 && iconMap.containsKey(nearbyAtlasResult2.keywords.get(0))) {
                symbolOptions.icon((String) iconMap.get(nearbyAtlasResult2.keywords.get(0)));
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("com.mappls.sdk.nearby.plugin.NEARBY_RESULT_DATA", new Gson().toJson(nearbyAtlasResult2));
            symbolOptions.data(jsonObject);
            arrayList.add(symbolOptions);
        }
        this.d.create(arrayList);
    }
}
