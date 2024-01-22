package com.mappls.sdk.maps.location;

import androidx.annotation.NonNull;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.CircleLayer;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.SymbolLayer;
import com.mappls.sdk.maps.style.layers.TransitionOptions;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes11.dex */
public class f {
    @NonNull
    public Layer a() {
        return new CircleLayer(LocationComponentConstants.ACCURACY_LAYER, LocationComponentConstants.LOCATION_SOURCE).withProperties(PropertyFactory.circleRadius(Expression.get("mappls-property-accuracy-radius")), PropertyFactory.circleColor(Expression.get("mappls-property-accuracy-color")), PropertyFactory.circleOpacity(Expression.get("mappls-property-accuracy-alpha")), PropertyFactory.circleStrokeColor(Expression.get("mappls-property-accuracy-color")), PropertyFactory.circlePitchAlignment("map"));
    }

    @NonNull
    public Layer b(@NonNull String str) {
        SymbolLayer symbolLayer = new SymbolLayer(str, LocationComponentConstants.LOCATION_SOURCE);
        Boolean bool = Boolean.TRUE;
        Expression literal = Expression.literal(str);
        Float valueOf = Float.valueOf(0.0f);
        symbolLayer.setProperties(PropertyFactory.iconAllowOverlap(bool), PropertyFactory.iconIgnorePlacement(bool), PropertyFactory.iconRotationAlignment("map"), PropertyFactory.iconRotate(Expression.match(literal, Expression.literal((Number) valueOf), Expression.stop(LocationComponentConstants.FOREGROUND_LAYER, Expression.get("mappls-property-gps-bearing")), Expression.stop(LocationComponentConstants.BACKGROUND_LAYER, Expression.get("mappls-property-gps-bearing")), Expression.stop(LocationComponentConstants.SHADOW_LAYER, Expression.get("mappls-property-gps-bearing")), Expression.stop(LocationComponentConstants.BEARING_LAYER, Expression.get("mappls-property-compass-bearing")))), PropertyFactory.iconImage(Expression.match(Expression.literal(str), Expression.literal(""), Expression.stop(LocationComponentConstants.FOREGROUND_LAYER, Expression.switchCase(Expression.get("mappls-property-location-stale"), Expression.get("mappls-property-foreground-stale-icon"), Expression.get("mappls-property-foreground-icon"))), Expression.stop(LocationComponentConstants.BACKGROUND_LAYER, Expression.switchCase(Expression.get("mappls-property-location-stale"), Expression.get("mappls-property-background-stale-icon"), Expression.get("mappls-property-background-icon"))), Expression.stop(LocationComponentConstants.SHADOW_LAYER, Expression.literal("mappls-location-shadow-icon")), Expression.stop(LocationComponentConstants.BEARING_LAYER, Expression.get("mappls-property-shadow-icon")))), PropertyFactory.iconOffset(Expression.match(Expression.literal(str), Expression.literal((Object[]) new Float[]{valueOf, valueOf}), Expression.stop(Expression.literal(LocationComponentConstants.FOREGROUND_LAYER), Expression.get("mappls-property-foreground-icon-offset")), Expression.stop(Expression.literal(LocationComponentConstants.SHADOW_LAYER), Expression.get("mappls-property-shadow-icon-offset")))));
        return symbolLayer;
    }

    public Layer c() {
        LocationIndicatorLayer locationIndicatorLayer = new LocationIndicatorLayer(LocationComponentConstants.FOREGROUND_LAYER);
        locationIndicatorLayer.b(new TransitionOptions(0L, 0L));
        locationIndicatorLayer.setProperties(n.i(Float.valueOf(0.9f)), n.g(Float.valueOf(4.0f)));
        return locationIndicatorLayer;
    }

    @NonNull
    public Layer d() {
        return new CircleLayer(LocationComponentConstants.PULSING_CIRCLE_LAYER, LocationComponentConstants.LOCATION_SOURCE).withProperties(PropertyFactory.circlePitchAlignment("map"));
    }

    @NonNull
    public GeoJsonSource e(Feature feature) {
        return new GeoJsonSource(LocationComponentConstants.LOCATION_SOURCE, feature, new GeoJsonOptions().withMaxZoom(16));
    }

    public Set<String> f() {
        return new HashSet();
    }

    public m g() {
        return new b(this);
    }

    public m h(e eVar, boolean z) {
        return new w(this, eVar, z);
    }
}
