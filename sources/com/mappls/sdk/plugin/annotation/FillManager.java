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
import com.mappls.sdk.maps.style.layers.FillLayer;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Keep
/* loaded from: classes11.dex */
public class FillManager extends AnnotationManager<FillLayer, Fill, FillOptions, OnFillDragListener, OnFillClickListener, OnFillLongClickListener> {
    private static final String PROPERTY_FILL_ANTIALIAS = "fill-antialias";
    private static final String PROPERTY_FILL_TRANSLATE = "fill-translate";
    private static final String PROPERTY_FILL_TRANSLATE_ANCHOR = "fill-translate-anchor";

    @UiThread
    public FillManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style) {
        this(mapView, mapplsMap, style, null, null);
    }

    @UiThread
    public FillManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, GeoJsonOptions geoJsonOptions) {
        this(mapView, mapplsMap, style, null, geoJsonOptions);
    }

    @VisibleForTesting
    public FillManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, @NonNull c<FillLayer> cVar, @Nullable String str, @Nullable GeoJsonOptions geoJsonOptions, d dVar) {
        super(mapView, mapplsMap, style, cVar, dVar, str, geoJsonOptions);
    }

    @UiThread
    public FillManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, @Nullable String str) {
        this(mapView, mapplsMap, style, str, null);
    }

    @UiThread
    public FillManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, @Nullable String str, @Nullable GeoJsonOptions geoJsonOptions) {
        this(mapView, mapplsMap, style, new e(), str, geoJsonOptions, d.b(mapView, mapplsMap));
    }

    @UiThread
    public List<Fill> create(@NonNull FeatureCollection featureCollection) {
        List<Feature> features = featureCollection.features();
        ArrayList arrayList = new ArrayList();
        if (features != null) {
            for (Feature feature : features) {
                FillOptions fromFeature = FillOptions.fromFeature(feature);
                if (fromFeature != null) {
                    arrayList.add(fromFeature);
                }
            }
        }
        return create(arrayList);
    }

    @UiThread
    public List<Fill> create(@NonNull String str) {
        return create(FeatureCollection.fromJson(str));
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public String getAnnotationIdKey() {
        return "id";
    }

    public Boolean getFillAntialias() {
        return ((FillLayer) this.layer).getFillAntialias().value;
    }

    public Float[] getFillTranslate() {
        return ((FillLayer) this.layer).getFillTranslate().value;
    }

    public String getFillTranslateAnchor() {
        return ((FillLayer) this.layer).getFillTranslateAnchor().value;
    }

    @Nullable
    public Expression getFilter() {
        return ((FillLayer) this.layer).getFilter();
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public void initializeDataDrivenPropertyMap() {
        Map<String, Boolean> map = this.dataDrivenPropertyUsageMap;
        Boolean bool = Boolean.FALSE;
        map.put(FillOptions.PROPERTY_FILL_OPACITY, bool);
        this.dataDrivenPropertyUsageMap.put(FillOptions.PROPERTY_FILL_COLOR, bool);
        this.dataDrivenPropertyUsageMap.put(FillOptions.PROPERTY_FILL_OUTLINE_COLOR, bool);
        this.dataDrivenPropertyUsageMap.put(FillOptions.PROPERTY_FILL_PATTERN, bool);
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public void setDataDrivenPropertyIsUsed(@NonNull String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1679439207:
                if (str.equals(FillOptions.PROPERTY_FILL_COLOR)) {
                    c = 0;
                    break;
                }
                break;
            case -1250124351:
                if (str.equals(FillOptions.PROPERTY_FILL_OPACITY)) {
                    c = 1;
                    break;
                }
                break;
            case -774008506:
                if (str.equals(FillOptions.PROPERTY_FILL_PATTERN)) {
                    c = 2;
                    break;
                }
                break;
            case 1201248078:
                if (str.equals(FillOptions.PROPERTY_FILL_OUTLINE_COLOR)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((FillLayer) this.layer).setProperties(PropertyFactory.fillColor(Expression.get(FillOptions.PROPERTY_FILL_COLOR)));
                return;
            case 1:
                ((FillLayer) this.layer).setProperties(PropertyFactory.fillOpacity(Expression.get(FillOptions.PROPERTY_FILL_OPACITY)));
                return;
            case 2:
                ((FillLayer) this.layer).setProperties(PropertyFactory.fillPattern(Expression.get(FillOptions.PROPERTY_FILL_PATTERN)));
                return;
            case 3:
                ((FillLayer) this.layer).setProperties(PropertyFactory.fillOutlineColor(Expression.get(FillOptions.PROPERTY_FILL_OUTLINE_COLOR)));
                return;
            default:
                return;
        }
    }

    public void setFillAntialias(Boolean bool) {
        PropertyValue<Boolean> fillAntialias = PropertyFactory.fillAntialias(bool);
        this.constantPropertyUsageMap.put(PROPERTY_FILL_ANTIALIAS, fillAntialias);
        ((FillLayer) this.layer).setProperties(fillAntialias);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setFillTranslate(Float[] fArr) {
        PropertyValue<Float[]> fillTranslate = PropertyFactory.fillTranslate(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_FILL_TRANSLATE, fillTranslate);
        ((FillLayer) this.layer).setProperties(fillTranslate);
    }

    public void setFillTranslateAnchor(String str) {
        PropertyValue<String> fillTranslateAnchor = PropertyFactory.fillTranslateAnchor(str);
        this.constantPropertyUsageMap.put(PROPERTY_FILL_TRANSLATE_ANCHOR, fillTranslateAnchor);
        ((FillLayer) this.layer).setProperties(fillTranslateAnchor);
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public void setFilter(@NonNull Expression expression) {
        this.layerFilter = expression;
        ((FillLayer) this.layer).setFilter(expression);
    }
}
