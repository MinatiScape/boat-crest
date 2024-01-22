package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.CircleLayer;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Keep
/* loaded from: classes11.dex */
public class CircleManager extends AnnotationManager<CircleLayer, Circle, CircleOptions, OnCircleDragListener, OnCircleClickListener, OnCircleLongClickListener> {
    private static final String PROPERTY_CIRCLE_PITCH_ALIGNMENT = "circle-pitch-alignment";
    private static final String PROPERTY_CIRCLE_PITCH_SCALE = "circle-pitch-scale";
    private static final String PROPERTY_CIRCLE_TRANSLATE = "circle-translate";
    private static final String PROPERTY_CIRCLE_TRANSLATE_ANCHOR = "circle-translate-anchor";

    @UiThread
    public CircleManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style) {
        this(mapView, mapplsMap, style, null, null);
    }

    @UiThread
    public CircleManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, GeoJsonOptions geoJsonOptions) {
        this(mapView, mapplsMap, style, null, geoJsonOptions);
    }

    @VisibleForTesting
    public CircleManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, @NonNull c<CircleLayer> cVar, @Nullable String str, @Nullable GeoJsonOptions geoJsonOptions, d dVar) {
        super(mapView, mapplsMap, style, cVar, dVar, str, geoJsonOptions);
    }

    @UiThread
    public CircleManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, @Nullable String str) {
        this(mapView, mapplsMap, style, str, null);
    }

    @UiThread
    public CircleManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, @Nullable String str, @Nullable GeoJsonOptions geoJsonOptions) {
        this(mapView, mapplsMap, style, new a(), str, geoJsonOptions, d.b(mapView, mapplsMap));
    }

    @UiThread
    public List<Circle> create(@NonNull FeatureCollection featureCollection) {
        List<Feature> features = featureCollection.features();
        ArrayList arrayList = new ArrayList();
        if (features != null) {
            for (Feature feature : features) {
                CircleOptions fromFeature = CircleOptions.fromFeature(feature);
                if (fromFeature != null) {
                    arrayList.add(fromFeature);
                }
            }
        }
        return create(arrayList);
    }

    @UiThread
    public List<Circle> create(@NonNull String str) {
        return create(FeatureCollection.fromJson(str));
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public String getAnnotationIdKey() {
        return "id";
    }

    public String getCirclePitchAlignment() {
        return ((CircleLayer) this.layer).getCirclePitchAlignment().value;
    }

    public String getCirclePitchScale() {
        return ((CircleLayer) this.layer).getCirclePitchScale().value;
    }

    public Float[] getCircleTranslate() {
        return ((CircleLayer) this.layer).getCircleTranslate().value;
    }

    public String getCircleTranslateAnchor() {
        return ((CircleLayer) this.layer).getCircleTranslateAnchor().value;
    }

    @Nullable
    public Expression getFilter() {
        return ((CircleLayer) this.layer).getFilter();
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public void initializeDataDrivenPropertyMap() {
        Map<String, Boolean> map = this.dataDrivenPropertyUsageMap;
        Boolean bool = Boolean.FALSE;
        map.put(CircleOptions.PROPERTY_CIRCLE_RADIUS, bool);
        this.dataDrivenPropertyUsageMap.put(CircleOptions.PROPERTY_CIRCLE_COLOR, bool);
        this.dataDrivenPropertyUsageMap.put(CircleOptions.PROPERTY_CIRCLE_BLUR, bool);
        this.dataDrivenPropertyUsageMap.put(CircleOptions.PROPERTY_CIRCLE_OPACITY, bool);
        this.dataDrivenPropertyUsageMap.put(CircleOptions.PROPERTY_CIRCLE_STROKE_WIDTH, bool);
        this.dataDrivenPropertyUsageMap.put(CircleOptions.PROPERTY_CIRCLE_STROKE_COLOR, bool);
        this.dataDrivenPropertyUsageMap.put(CircleOptions.PROPERTY_CIRCLE_STROKE_OPACITY, bool);
    }

    public void setCirclePitchAlignment(String str) {
        PropertyValue<String> circlePitchAlignment = PropertyFactory.circlePitchAlignment(str);
        this.constantPropertyUsageMap.put(PROPERTY_CIRCLE_PITCH_ALIGNMENT, circlePitchAlignment);
        ((CircleLayer) this.layer).setProperties(circlePitchAlignment);
    }

    public void setCirclePitchScale(String str) {
        PropertyValue<String> circlePitchScale = PropertyFactory.circlePitchScale(str);
        this.constantPropertyUsageMap.put(PROPERTY_CIRCLE_PITCH_SCALE, circlePitchScale);
        ((CircleLayer) this.layer).setProperties(circlePitchScale);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setCircleTranslate(Float[] fArr) {
        PropertyValue<Float[]> circleTranslate = PropertyFactory.circleTranslate(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_CIRCLE_TRANSLATE, circleTranslate);
        ((CircleLayer) this.layer).setProperties(circleTranslate);
    }

    public void setCircleTranslateAnchor(String str) {
        PropertyValue<String> circleTranslateAnchor = PropertyFactory.circleTranslateAnchor(str);
        this.constantPropertyUsageMap.put(PROPERTY_CIRCLE_TRANSLATE_ANCHOR, circleTranslateAnchor);
        ((CircleLayer) this.layer).setProperties(circleTranslateAnchor);
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public void setDataDrivenPropertyIsUsed(@NonNull String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1290287090:
                if (str.equals(CircleOptions.PROPERTY_CIRCLE_OPACITY)) {
                    c = 0;
                    break;
                }
                break;
            case -939323345:
                if (str.equals(CircleOptions.PROPERTY_CIRCLE_RADIUS)) {
                    c = 1;
                    break;
                }
                break;
            case -585897621:
                if (str.equals(CircleOptions.PROPERTY_CIRCLE_STROKE_COLOR)) {
                    c = 2;
                    break;
                }
                break;
            case -567613490:
                if (str.equals(CircleOptions.PROPERTY_CIRCLE_STROKE_WIDTH)) {
                    c = 3;
                    break;
                }
                break;
            case -113174716:
                if (str.equals(CircleOptions.PROPERTY_CIRCLE_BLUR)) {
                    c = 4;
                    break;
                }
                break;
            case 787555366:
                if (str.equals(CircleOptions.PROPERTY_CIRCLE_COLOR)) {
                    c = 5;
                    break;
                }
                break;
            case 1671319571:
                if (str.equals(CircleOptions.PROPERTY_CIRCLE_STROKE_OPACITY)) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleOpacity(Expression.get(CircleOptions.PROPERTY_CIRCLE_OPACITY)));
                return;
            case 1:
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleRadius(Expression.get(CircleOptions.PROPERTY_CIRCLE_RADIUS)));
                return;
            case 2:
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleStrokeColor(Expression.get(CircleOptions.PROPERTY_CIRCLE_STROKE_COLOR)));
                return;
            case 3:
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleStrokeWidth(Expression.get(CircleOptions.PROPERTY_CIRCLE_STROKE_WIDTH)));
                return;
            case 4:
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleBlur(Expression.get(CircleOptions.PROPERTY_CIRCLE_BLUR)));
                return;
            case 5:
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleColor(Expression.get(CircleOptions.PROPERTY_CIRCLE_COLOR)));
                return;
            case 6:
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleStrokeOpacity(Expression.get(CircleOptions.PROPERTY_CIRCLE_STROKE_OPACITY)));
                return;
            default:
                return;
        }
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public void setFilter(@NonNull Expression expression) {
        this.layerFilter = expression;
        ((CircleLayer) this.layer).setFilter(expression);
    }
}
