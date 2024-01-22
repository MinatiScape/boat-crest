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
import com.mappls.sdk.maps.style.layers.LineLayer;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Keep
/* loaded from: classes11.dex */
public class LineManager extends AnnotationManager<LineLayer, Line, LineOptions, OnLineDragListener, OnLineClickListener, OnLineLongClickListener> {
    private static final String PROPERTY_LINE_CAP = "line-cap";
    private static final String PROPERTY_LINE_DASHARRAY = "line-dasharray";
    private static final String PROPERTY_LINE_MITER_LIMIT = "line-miter-limit";
    private static final String PROPERTY_LINE_ROUND_LIMIT = "line-round-limit";
    private static final String PROPERTY_LINE_TRANSLATE = "line-translate";
    private static final String PROPERTY_LINE_TRANSLATE_ANCHOR = "line-translate-anchor";

    @UiThread
    public LineManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style) {
        this(mapView, mapplsMap, style, null, null);
    }

    @UiThread
    public LineManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, GeoJsonOptions geoJsonOptions) {
        this(mapView, mapplsMap, style, null, geoJsonOptions);
    }

    @VisibleForTesting
    public LineManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, @NonNull c<LineLayer> cVar, @Nullable String str, @Nullable GeoJsonOptions geoJsonOptions, d dVar) {
        super(mapView, mapplsMap, style, cVar, dVar, str, geoJsonOptions);
    }

    @UiThread
    public LineManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, @Nullable String str) {
        this(mapView, mapplsMap, style, str, null);
    }

    @UiThread
    public LineManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, @Nullable String str, @Nullable GeoJsonOptions geoJsonOptions) {
        this(mapView, mapplsMap, style, new f(), str, geoJsonOptions, d.b(mapView, mapplsMap));
    }

    @UiThread
    public List<Line> create(@NonNull FeatureCollection featureCollection) {
        List<Feature> features = featureCollection.features();
        ArrayList arrayList = new ArrayList();
        if (features != null) {
            for (Feature feature : features) {
                LineOptions fromFeature = LineOptions.fromFeature(feature);
                if (fromFeature != null) {
                    arrayList.add(fromFeature);
                }
            }
        }
        return create(arrayList);
    }

    @UiThread
    public List<Line> create(@NonNull String str) {
        return create(FeatureCollection.fromJson(str));
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public String getAnnotationIdKey() {
        return "id";
    }

    @Nullable
    public Expression getFilter() {
        return ((LineLayer) this.layer).getFilter();
    }

    public String getLineCap() {
        return ((LineLayer) this.layer).getLineCap().value;
    }

    public Float[] getLineDasharray() {
        return ((LineLayer) this.layer).getLineDasharray().value;
    }

    public Float getLineMiterLimit() {
        return ((LineLayer) this.layer).getLineMiterLimit().value;
    }

    public Float getLineRoundLimit() {
        return ((LineLayer) this.layer).getLineRoundLimit().value;
    }

    public Float[] getLineTranslate() {
        return ((LineLayer) this.layer).getLineTranslate().value;
    }

    public String getLineTranslateAnchor() {
        return ((LineLayer) this.layer).getLineTranslateAnchor().value;
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public void initializeDataDrivenPropertyMap() {
        Map<String, Boolean> map = this.dataDrivenPropertyUsageMap;
        Boolean bool = Boolean.FALSE;
        map.put(LineOptions.PROPERTY_LINE_JOIN, bool);
        this.dataDrivenPropertyUsageMap.put(LineOptions.PROPERTY_LINE_OPACITY, bool);
        this.dataDrivenPropertyUsageMap.put(LineOptions.PROPERTY_LINE_COLOR, bool);
        this.dataDrivenPropertyUsageMap.put(LineOptions.PROPERTY_LINE_WIDTH, bool);
        this.dataDrivenPropertyUsageMap.put(LineOptions.PROPERTY_LINE_GAP_WIDTH, bool);
        this.dataDrivenPropertyUsageMap.put(LineOptions.PROPERTY_LINE_OFFSET, bool);
        this.dataDrivenPropertyUsageMap.put(LineOptions.PROPERTY_LINE_BLUR, bool);
        this.dataDrivenPropertyUsageMap.put(LineOptions.PROPERTY_LINE_PATTERN, bool);
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public void setDataDrivenPropertyIsUsed(@NonNull String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1842775392:
                if (str.equals(LineOptions.PROPERTY_LINE_BLUR)) {
                    c = 0;
                    break;
                }
                break;
            case -1842534557:
                if (str.equals(LineOptions.PROPERTY_LINE_JOIN)) {
                    c = 1;
                    break;
                }
                break;
            case -1763440266:
                if (str.equals(LineOptions.PROPERTY_LINE_GAP_WIDTH)) {
                    c = 2;
                    break;
                }
                break;
            case -1290458038:
                if (str.equals(LineOptions.PROPERTY_LINE_COLOR)) {
                    c = 3;
                    break;
                }
                break;
            case -1272173907:
                if (str.equals(LineOptions.PROPERTY_LINE_WIDTH)) {
                    c = 4;
                    break;
                }
                break;
            case -1101375694:
                if (str.equals(LineOptions.PROPERTY_LINE_OPACITY)) {
                    c = 5;
                    break;
                }
                break;
            case -1014430580:
                if (str.equals(LineOptions.PROPERTY_LINE_OFFSET)) {
                    c = 6;
                    break;
                }
                break;
            case -625259849:
                if (str.equals(LineOptions.PROPERTY_LINE_PATTERN)) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineBlur(Expression.get(LineOptions.PROPERTY_LINE_BLUR)));
                return;
            case 1:
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineJoin(Expression.get(LineOptions.PROPERTY_LINE_JOIN)));
                return;
            case 2:
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineGapWidth(Expression.get(LineOptions.PROPERTY_LINE_GAP_WIDTH)));
                return;
            case 3:
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineColor(Expression.get(LineOptions.PROPERTY_LINE_COLOR)));
                return;
            case 4:
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineWidth(Expression.get(LineOptions.PROPERTY_LINE_WIDTH)));
                return;
            case 5:
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineOpacity(Expression.get(LineOptions.PROPERTY_LINE_OPACITY)));
                return;
            case 6:
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineOffset(Expression.get(LineOptions.PROPERTY_LINE_OFFSET)));
                return;
            case 7:
                ((LineLayer) this.layer).setProperties(PropertyFactory.linePattern(Expression.get(LineOptions.PROPERTY_LINE_PATTERN)));
                return;
            default:
                return;
        }
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public void setFilter(@NonNull Expression expression) {
        this.layerFilter = expression;
        ((LineLayer) this.layer).setFilter(expression);
    }

    public void setLineCap(String str) {
        PropertyValue<String> lineCap = PropertyFactory.lineCap(str);
        this.constantPropertyUsageMap.put(PROPERTY_LINE_CAP, lineCap);
        ((LineLayer) this.layer).setProperties(lineCap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setLineDasharray(Float[] fArr) {
        PropertyValue<Float[]> lineDasharray = PropertyFactory.lineDasharray(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_LINE_DASHARRAY, lineDasharray);
        ((LineLayer) this.layer).setProperties(lineDasharray);
    }

    public void setLineMiterLimit(Float f) {
        PropertyValue<Float> lineMiterLimit = PropertyFactory.lineMiterLimit(f);
        this.constantPropertyUsageMap.put(PROPERTY_LINE_MITER_LIMIT, lineMiterLimit);
        ((LineLayer) this.layer).setProperties(lineMiterLimit);
    }

    public void setLineRoundLimit(Float f) {
        PropertyValue<Float> lineRoundLimit = PropertyFactory.lineRoundLimit(f);
        this.constantPropertyUsageMap.put(PROPERTY_LINE_ROUND_LIMIT, lineRoundLimit);
        ((LineLayer) this.layer).setProperties(lineRoundLimit);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setLineTranslate(Float[] fArr) {
        PropertyValue<Float[]> lineTranslate = PropertyFactory.lineTranslate(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_LINE_TRANSLATE, lineTranslate);
        ((LineLayer) this.layer).setProperties(lineTranslate);
    }

    public void setLineTranslateAnchor(String str) {
        PropertyValue<String> lineTranslateAnchor = PropertyFactory.lineTranslateAnchor(str);
        this.constantPropertyUsageMap.put(PROPERTY_LINE_TRANSLATE_ANCHOR, lineTranslateAnchor);
        ((LineLayer) this.layer).setProperties(lineTranslateAnchor);
    }
}
