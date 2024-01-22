package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import com.alibaba.fastjson.parser.JSONLexer;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.style.layers.SymbolLayer;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Keep
/* loaded from: classes11.dex */
public class SymbolManager extends AnnotationManager<SymbolLayer, Symbol, SymbolOptions, OnSymbolDragListener, OnSymbolClickListener, OnSymbolLongClickListener> {
    private static final String PROPERTY_ICON_ALLOW_OVERLAP = "icon-allow-overlap";
    private static final String PROPERTY_ICON_IGNORE_PLACEMENT = "icon-ignore-placement";
    private static final String PROPERTY_ICON_KEEP_UPRIGHT = "icon-keep-upright";
    private static final String PROPERTY_ICON_OPTIONAL = "icon-optional";
    private static final String PROPERTY_ICON_PADDING = "icon-padding";
    private static final String PROPERTY_ICON_PITCH_ALIGNMENT = "icon-pitch-alignment";
    private static final String PROPERTY_ICON_ROTATION_ALIGNMENT = "icon-rotation-alignment";
    private static final String PROPERTY_ICON_TEXT_FIT = "icon-text-fit";
    private static final String PROPERTY_ICON_TEXT_FIT_PADDING = "icon-text-fit-padding";
    private static final String PROPERTY_ICON_TRANSLATE = "icon-translate";
    private static final String PROPERTY_ICON_TRANSLATE_ANCHOR = "icon-translate-anchor";
    private static final String PROPERTY_SYMBOL_AVOID_EDGES = "symbol-avoid-edges";
    private static final String PROPERTY_SYMBOL_PLACEMENT = "symbol-placement";
    private static final String PROPERTY_SYMBOL_SPACING = "symbol-spacing";
    private static final String PROPERTY_TEXT_ALLOW_OVERLAP = "text-allow-overlap";
    private static final String PROPERTY_TEXT_IGNORE_PLACEMENT = "text-ignore-placement";
    private static final String PROPERTY_TEXT_KEEP_UPRIGHT = "text-keep-upright";
    private static final String PROPERTY_TEXT_LINE_HEIGHT = "text-line-height";
    private static final String PROPERTY_TEXT_MAX_ANGLE = "text-max-angle";
    private static final String PROPERTY_TEXT_OPTIONAL = "text-optional";
    private static final String PROPERTY_TEXT_PADDING = "text-padding";
    private static final String PROPERTY_TEXT_PITCH_ALIGNMENT = "text-pitch-alignment";
    private static final String PROPERTY_TEXT_ROTATION_ALIGNMENT = "text-rotation-alignment";
    private static final String PROPERTY_TEXT_TRANSLATE = "text-translate";
    private static final String PROPERTY_TEXT_TRANSLATE_ANCHOR = "text-translate-anchor";
    private static final String PROPERTY_TEXT_VARIABLE_ANCHOR = "text-variable-anchor";

    @UiThread
    public SymbolManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style) {
        this(mapView, mapplsMap, style, null, null);
    }

    @UiThread
    public SymbolManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, GeoJsonOptions geoJsonOptions) {
        this(mapView, mapplsMap, style, null, geoJsonOptions);
    }

    @VisibleForTesting
    public SymbolManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, @NonNull c<SymbolLayer> cVar, @Nullable String str, @Nullable GeoJsonOptions geoJsonOptions, d dVar) {
        super(mapView, mapplsMap, style, cVar, dVar, str, geoJsonOptions);
    }

    @UiThread
    public SymbolManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, @Nullable String str) {
        this(mapView, mapplsMap, style, str, null);
    }

    @UiThread
    public SymbolManager(@NonNull MapView mapView, @NonNull MapplsMap mapplsMap, @NonNull Style style, @Nullable String str, @Nullable GeoJsonOptions geoJsonOptions) {
        this(mapView, mapplsMap, style, new h(), str, geoJsonOptions, d.b(mapView, mapplsMap));
    }

    @UiThread
    public List<Symbol> create(@NonNull FeatureCollection featureCollection) {
        List<Feature> features = featureCollection.features();
        ArrayList arrayList = new ArrayList();
        if (features != null) {
            for (Feature feature : features) {
                SymbolOptions fromFeature = SymbolOptions.fromFeature(feature, this.style);
                if (fromFeature != null) {
                    arrayList.add(fromFeature);
                }
            }
        }
        return create(arrayList);
    }

    @UiThread
    public List<Symbol> create(@NonNull String str) {
        return create(FeatureCollection.fromJson(str));
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public String getAnnotationIdKey() {
        return "id";
    }

    @Nullable
    public Expression getFilter() {
        return ((SymbolLayer) this.layer).getFilter();
    }

    public Boolean getIconAllowOverlap() {
        return ((SymbolLayer) this.layer).getIconAllowOverlap().value;
    }

    public Boolean getIconIgnorePlacement() {
        return ((SymbolLayer) this.layer).getIconIgnorePlacement().value;
    }

    public Boolean getIconKeepUpright() {
        return ((SymbolLayer) this.layer).getIconKeepUpright().value;
    }

    public Boolean getIconOptional() {
        return ((SymbolLayer) this.layer).getIconOptional().value;
    }

    public Float getIconPadding() {
        return ((SymbolLayer) this.layer).getIconPadding().value;
    }

    public String getIconPitchAlignment() {
        return ((SymbolLayer) this.layer).getIconPitchAlignment().value;
    }

    public String getIconRotationAlignment() {
        return ((SymbolLayer) this.layer).getIconRotationAlignment().value;
    }

    public String getIconTextFit() {
        return ((SymbolLayer) this.layer).getIconTextFit().value;
    }

    public Float[] getIconTextFitPadding() {
        return ((SymbolLayer) this.layer).getIconTextFitPadding().value;
    }

    public Float[] getIconTranslate() {
        return ((SymbolLayer) this.layer).getIconTranslate().value;
    }

    public String getIconTranslateAnchor() {
        return ((SymbolLayer) this.layer).getIconTranslateAnchor().value;
    }

    public Boolean getSymbolAvoidEdges() {
        return ((SymbolLayer) this.layer).getSymbolAvoidEdges().value;
    }

    public String getSymbolPlacement() {
        return ((SymbolLayer) this.layer).getSymbolPlacement().value;
    }

    public Float getSymbolSpacing() {
        return ((SymbolLayer) this.layer).getSymbolSpacing().value;
    }

    public Boolean getTextAllowOverlap() {
        return ((SymbolLayer) this.layer).getTextAllowOverlap().value;
    }

    public Boolean getTextIgnorePlacement() {
        return ((SymbolLayer) this.layer).getTextIgnorePlacement().value;
    }

    public Boolean getTextKeepUpright() {
        return ((SymbolLayer) this.layer).getTextKeepUpright().value;
    }

    public Float getTextLineHeight() {
        return ((SymbolLayer) this.layer).getTextLineHeight().value;
    }

    public Float getTextMaxAngle() {
        return ((SymbolLayer) this.layer).getTextMaxAngle().value;
    }

    public Boolean getTextOptional() {
        return ((SymbolLayer) this.layer).getTextOptional().value;
    }

    public Float getTextPadding() {
        return ((SymbolLayer) this.layer).getTextPadding().value;
    }

    public String getTextPitchAlignment() {
        return ((SymbolLayer) this.layer).getTextPitchAlignment().value;
    }

    public String getTextRotationAlignment() {
        return ((SymbolLayer) this.layer).getTextRotationAlignment().value;
    }

    public Float[] getTextTranslate() {
        return ((SymbolLayer) this.layer).getTextTranslate().value;
    }

    public String getTextTranslateAnchor() {
        return ((SymbolLayer) this.layer).getTextTranslateAnchor().value;
    }

    public String[] getTextVariableAnchor() {
        return ((SymbolLayer) this.layer).getTextVariableAnchor().value;
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public void initializeDataDrivenPropertyMap() {
        Map<String, Boolean> map = this.dataDrivenPropertyUsageMap;
        Boolean bool = Boolean.FALSE;
        map.put(SymbolOptions.PROPERTY_SYMBOL_SORT_KEY, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_ICON_SIZE, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_ICON_IMAGE, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_ICON_ROTATE, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_ICON_OFFSET, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_ICON_ANCHOR, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_FIELD, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_FONT, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_SIZE, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_MAX_WIDTH, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_LETTER_SPACING, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_JUSTIFY, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_RADIAL_OFFSET, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_ANCHOR, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_ROTATE, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_TRANSFORM, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_OFFSET, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_ICON_OPACITY, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_ICON_COLOR, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_ICON_HALO_COLOR, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_ICON_HALO_WIDTH, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_ICON_HALO_BLUR, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_OPACITY, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_COLOR, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_HALO_COLOR, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_HALO_WIDTH, bool);
        this.dataDrivenPropertyUsageMap.put(SymbolOptions.PROPERTY_TEXT_HALO_BLUR, bool);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public void setDataDrivenPropertyIsUsed(@NonNull String str) {
        Object obj;
        String str2;
        String str3;
        char c;
        Object obj2;
        str.hashCode();
        int hashCode = str.hashCode();
        String str4 = SymbolOptions.PROPERTY_ICON_HALO_COLOR;
        String str5 = SymbolOptions.PROPERTY_TEXT_RADIAL_OFFSET;
        String str6 = SymbolOptions.PROPERTY_ICON_ROTATE;
        switch (hashCode) {
            case -2146810373:
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                if (str.equals(str2)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -2041493401:
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                boolean equals = str.equals(SymbolOptions.PROPERTY_ICON_OFFSET);
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                if (equals) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1946894033:
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                if (!str.equals(str6)) {
                    str6 = str6;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    c = 65535;
                    break;
                } else {
                    c = 2;
                    str6 = str6;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    break;
                }
            case -1717422239:
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                if (!str.equals(str5)) {
                    str5 = str5;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    c = 65535;
                    break;
                } else {
                    c = 3;
                    str5 = str5;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    break;
                }
            case -1708933018:
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                if (!str.equals(str4)) {
                    str4 = str4;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    c = 65535;
                    break;
                } else {
                    c = 4;
                    str4 = str4;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    break;
                }
            case -1690648887:
                obj2 = SymbolOptions.PROPERTY_ICON_COLOR;
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                if (str.equals(obj)) {
                    c = 5;
                    str3 = obj2;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    break;
                }
                str3 = obj2;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                c = 65535;
                break;
            case -1600683761:
                obj2 = SymbolOptions.PROPERTY_ICON_COLOR;
                if (!str.equals(obj2)) {
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str3 = obj2;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    c = 65535;
                    break;
                } else {
                    c = 6;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str3 = obj2;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    break;
                }
            case -1595213049:
                if (str.equals(SymbolOptions.PROPERTY_ICON_IMAGE)) {
                    c = 7;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case -1436636971:
                if (str.equals(SymbolOptions.PROPERTY_ICON_SIZE)) {
                    c = '\b';
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case -1336352187:
                if (str.equals(SymbolOptions.PROPERTY_SYMBOL_SORT_KEY)) {
                    c = '\t';
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case -1262567732:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_TRANSFORM)) {
                    c = '\n';
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case -1084154641:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_FONT)) {
                    c = 11;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case -1083772767:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_SIZE)) {
                    c = '\f';
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case -888013006:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_HALO_COLOR)) {
                    c = '\r';
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case -886443260:
                if (str.equals(SymbolOptions.PROPERTY_ICON_HALO_BLUR)) {
                    c = 14;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case -869728875:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_HALO_WIDTH)) {
                    c = 15;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case -483024021:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_OPACITY)) {
                    c = 16;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case -465299984:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_JUSTIFY)) {
                    c = 17;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case 317300605:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_MAX_WIDTH)) {
                    c = 18;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case 428355132:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_LETTER_SPACING)) {
                    c = 19;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case 525511352:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_HALO_BLUR)) {
                    c = 20;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case 748171971:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_COLOR)) {
                    c = 21;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case 750756954:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_FIELD)) {
                    c = 22;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case 1419415223:
                if (str.equals(SymbolOptions.PROPERTY_ICON_OPACITY)) {
                    c = 23;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case 1660037973:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_ANCHOR)) {
                    c = 24;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case 1859954313:
                if (str.equals(SymbolOptions.PROPERTY_ICON_ANCHOR)) {
                    c = 25;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            case 2053557555:
                if (str.equals(SymbolOptions.PROPERTY_TEXT_OFFSET)) {
                    c = JSONLexer.EOI;
                    obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                    str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                    str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                    break;
                }
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
            default:
                obj = SymbolOptions.PROPERTY_ICON_HALO_WIDTH;
                str2 = SymbolOptions.PROPERTY_TEXT_ROTATE;
                str3 = SymbolOptions.PROPERTY_ICON_COLOR;
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textRotate(Expression.get(str2)));
                return;
            case 1:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconOffset(Expression.get(SymbolOptions.PROPERTY_ICON_OFFSET)));
                return;
            case 2:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconRotate(Expression.get(str6)));
                return;
            case 3:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textRadialOffset(Expression.get(str5)));
                return;
            case 4:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconHaloColor(Expression.get(str4)));
                return;
            case 5:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconHaloWidth(Expression.get(obj)));
                return;
            case 6:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconColor(Expression.get(str3)));
                return;
            case 7:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconImage(Expression.get(SymbolOptions.PROPERTY_ICON_IMAGE)));
                return;
            case '\b':
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconSize(Expression.get(SymbolOptions.PROPERTY_ICON_SIZE)));
                return;
            case '\t':
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.symbolSortKey(Expression.get(SymbolOptions.PROPERTY_SYMBOL_SORT_KEY)));
                return;
            case '\n':
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textTransform(Expression.get(SymbolOptions.PROPERTY_TEXT_TRANSFORM)));
                return;
            case 11:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textFont(Expression.get(SymbolOptions.PROPERTY_TEXT_FONT)));
                return;
            case '\f':
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textSize(Expression.get(SymbolOptions.PROPERTY_TEXT_SIZE)));
                return;
            case '\r':
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textHaloColor(Expression.get(SymbolOptions.PROPERTY_TEXT_HALO_COLOR)));
                return;
            case 14:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconHaloBlur(Expression.get(SymbolOptions.PROPERTY_ICON_HALO_BLUR)));
                return;
            case 15:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textHaloWidth(Expression.get(SymbolOptions.PROPERTY_TEXT_HALO_WIDTH)));
                return;
            case 16:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textOpacity(Expression.get(SymbolOptions.PROPERTY_TEXT_OPACITY)));
                return;
            case 17:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textJustify(Expression.get(SymbolOptions.PROPERTY_TEXT_JUSTIFY)));
                return;
            case 18:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textMaxWidth(Expression.get(SymbolOptions.PROPERTY_TEXT_MAX_WIDTH)));
                return;
            case 19:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textLetterSpacing(Expression.get(SymbolOptions.PROPERTY_TEXT_LETTER_SPACING)));
                return;
            case 20:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textHaloBlur(Expression.get(SymbolOptions.PROPERTY_TEXT_HALO_BLUR)));
                return;
            case 21:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textColor(Expression.get(SymbolOptions.PROPERTY_TEXT_COLOR)));
                return;
            case 22:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textField(Expression.get(SymbolOptions.PROPERTY_TEXT_FIELD)));
                return;
            case 23:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconOpacity(Expression.get(SymbolOptions.PROPERTY_ICON_OPACITY)));
                return;
            case 24:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textAnchor(Expression.get(SymbolOptions.PROPERTY_TEXT_ANCHOR)));
                return;
            case 25:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconAnchor(Expression.get(SymbolOptions.PROPERTY_ICON_ANCHOR)));
                return;
            case 26:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textOffset(Expression.get(SymbolOptions.PROPERTY_TEXT_OFFSET)));
                return;
            default:
                return;
        }
    }

    @Override // com.mappls.sdk.plugin.annotation.AnnotationManager
    public void setFilter(@NonNull Expression expression) {
        this.layerFilter = expression;
        ((SymbolLayer) this.layer).setFilter(expression);
    }

    public void setIconAllowOverlap(Boolean bool) {
        PropertyValue<Boolean> iconAllowOverlap = PropertyFactory.iconAllowOverlap(bool);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_ALLOW_OVERLAP, iconAllowOverlap);
        ((SymbolLayer) this.layer).setProperties(iconAllowOverlap);
    }

    public void setIconIgnorePlacement(Boolean bool) {
        PropertyValue<Boolean> iconIgnorePlacement = PropertyFactory.iconIgnorePlacement(bool);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_IGNORE_PLACEMENT, iconIgnorePlacement);
        ((SymbolLayer) this.layer).setProperties(iconIgnorePlacement);
    }

    public void setIconKeepUpright(Boolean bool) {
        PropertyValue<Boolean> iconKeepUpright = PropertyFactory.iconKeepUpright(bool);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_KEEP_UPRIGHT, iconKeepUpright);
        ((SymbolLayer) this.layer).setProperties(iconKeepUpright);
    }

    public void setIconOptional(Boolean bool) {
        PropertyValue<Boolean> iconOptional = PropertyFactory.iconOptional(bool);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_OPTIONAL, iconOptional);
        ((SymbolLayer) this.layer).setProperties(iconOptional);
    }

    public void setIconPadding(Float f) {
        PropertyValue<Float> iconPadding = PropertyFactory.iconPadding(f);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_PADDING, iconPadding);
        ((SymbolLayer) this.layer).setProperties(iconPadding);
    }

    public void setIconPitchAlignment(String str) {
        PropertyValue<String> iconPitchAlignment = PropertyFactory.iconPitchAlignment(str);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_PITCH_ALIGNMENT, iconPitchAlignment);
        ((SymbolLayer) this.layer).setProperties(iconPitchAlignment);
    }

    public void setIconRotationAlignment(String str) {
        PropertyValue<String> iconRotationAlignment = PropertyFactory.iconRotationAlignment(str);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_ROTATION_ALIGNMENT, iconRotationAlignment);
        ((SymbolLayer) this.layer).setProperties(iconRotationAlignment);
    }

    public void setIconTextFit(String str) {
        PropertyValue<String> iconTextFit = PropertyFactory.iconTextFit(str);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_TEXT_FIT, iconTextFit);
        ((SymbolLayer) this.layer).setProperties(iconTextFit);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setIconTextFitPadding(Float[] fArr) {
        PropertyValue<Float[]> iconTextFitPadding = PropertyFactory.iconTextFitPadding(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_TEXT_FIT_PADDING, iconTextFitPadding);
        ((SymbolLayer) this.layer).setProperties(iconTextFitPadding);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setIconTranslate(Float[] fArr) {
        PropertyValue<Float[]> iconTranslate = PropertyFactory.iconTranslate(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_TRANSLATE, iconTranslate);
        ((SymbolLayer) this.layer).setProperties(iconTranslate);
    }

    public void setIconTranslateAnchor(String str) {
        PropertyValue<String> iconTranslateAnchor = PropertyFactory.iconTranslateAnchor(str);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_TRANSLATE_ANCHOR, iconTranslateAnchor);
        ((SymbolLayer) this.layer).setProperties(iconTranslateAnchor);
    }

    public void setSymbolAvoidEdges(Boolean bool) {
        PropertyValue<Boolean> symbolAvoidEdges = PropertyFactory.symbolAvoidEdges(bool);
        this.constantPropertyUsageMap.put(PROPERTY_SYMBOL_AVOID_EDGES, symbolAvoidEdges);
        ((SymbolLayer) this.layer).setProperties(symbolAvoidEdges);
    }

    public void setSymbolPlacement(String str) {
        PropertyValue<String> symbolPlacement = PropertyFactory.symbolPlacement(str);
        this.constantPropertyUsageMap.put(PROPERTY_SYMBOL_PLACEMENT, symbolPlacement);
        ((SymbolLayer) this.layer).setProperties(symbolPlacement);
    }

    public void setSymbolSpacing(Float f) {
        PropertyValue<Float> symbolSpacing = PropertyFactory.symbolSpacing(f);
        this.constantPropertyUsageMap.put(PROPERTY_SYMBOL_SPACING, symbolSpacing);
        ((SymbolLayer) this.layer).setProperties(symbolSpacing);
    }

    public void setTextAllowOverlap(Boolean bool) {
        PropertyValue<Boolean> textAllowOverlap = PropertyFactory.textAllowOverlap(bool);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_ALLOW_OVERLAP, textAllowOverlap);
        ((SymbolLayer) this.layer).setProperties(textAllowOverlap);
    }

    public void setTextIgnorePlacement(Boolean bool) {
        PropertyValue<Boolean> textIgnorePlacement = PropertyFactory.textIgnorePlacement(bool);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_IGNORE_PLACEMENT, textIgnorePlacement);
        ((SymbolLayer) this.layer).setProperties(textIgnorePlacement);
    }

    public void setTextKeepUpright(Boolean bool) {
        PropertyValue<Boolean> textKeepUpright = PropertyFactory.textKeepUpright(bool);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_KEEP_UPRIGHT, textKeepUpright);
        ((SymbolLayer) this.layer).setProperties(textKeepUpright);
    }

    public void setTextLineHeight(Float f) {
        PropertyValue<Float> textLineHeight = PropertyFactory.textLineHeight(f);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_LINE_HEIGHT, textLineHeight);
        ((SymbolLayer) this.layer).setProperties(textLineHeight);
    }

    public void setTextMaxAngle(Float f) {
        PropertyValue<Float> textMaxAngle = PropertyFactory.textMaxAngle(f);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_MAX_ANGLE, textMaxAngle);
        ((SymbolLayer) this.layer).setProperties(textMaxAngle);
    }

    public void setTextOptional(Boolean bool) {
        PropertyValue<Boolean> textOptional = PropertyFactory.textOptional(bool);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_OPTIONAL, textOptional);
        ((SymbolLayer) this.layer).setProperties(textOptional);
    }

    public void setTextPadding(Float f) {
        PropertyValue<Float> textPadding = PropertyFactory.textPadding(f);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_PADDING, textPadding);
        ((SymbolLayer) this.layer).setProperties(textPadding);
    }

    public void setTextPitchAlignment(String str) {
        PropertyValue<String> textPitchAlignment = PropertyFactory.textPitchAlignment(str);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_PITCH_ALIGNMENT, textPitchAlignment);
        ((SymbolLayer) this.layer).setProperties(textPitchAlignment);
    }

    public void setTextRotationAlignment(String str) {
        PropertyValue<String> textRotationAlignment = PropertyFactory.textRotationAlignment(str);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_ROTATION_ALIGNMENT, textRotationAlignment);
        ((SymbolLayer) this.layer).setProperties(textRotationAlignment);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setTextTranslate(Float[] fArr) {
        PropertyValue<Float[]> textTranslate = PropertyFactory.textTranslate(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_TRANSLATE, textTranslate);
        ((SymbolLayer) this.layer).setProperties(textTranslate);
    }

    public void setTextTranslateAnchor(String str) {
        PropertyValue<String> textTranslateAnchor = PropertyFactory.textTranslateAnchor(str);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_TRANSLATE_ANCHOR, textTranslateAnchor);
        ((SymbolLayer) this.layer).setProperties(textTranslateAnchor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setTextVariableAnchor(String[] strArr) {
        PropertyValue<String[]> textVariableAnchor = PropertyFactory.textVariableAnchor(strArr);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_VARIABLE_ANCHOR, textVariableAnchor);
        ((SymbolLayer) this.layer).setProperties(textVariableAnchor);
    }
}
