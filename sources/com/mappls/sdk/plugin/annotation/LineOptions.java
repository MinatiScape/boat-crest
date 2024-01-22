package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.geometry.LatLng;
import java.util.ArrayList;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
public class LineOptions extends g<Line> {
    private static final String PROPERTY_IS_DRAGGABLE = "is-draggable";
    public static final String PROPERTY_LINE_BLUR = "line-blur";
    public static final String PROPERTY_LINE_COLOR = "line-color";
    public static final String PROPERTY_LINE_GAP_WIDTH = "line-gap-width";
    public static final String PROPERTY_LINE_JOIN = "line-join";
    public static final String PROPERTY_LINE_OFFSET = "line-offset";
    public static final String PROPERTY_LINE_OPACITY = "line-opacity";
    public static final String PROPERTY_LINE_PATTERN = "line-pattern";
    public static final String PROPERTY_LINE_WIDTH = "line-width";
    private JsonElement data;
    private LineString geometry;
    private boolean isDraggable;
    private Float lineBlur;
    private String lineColor;
    private Float lineGapWidth;
    private String lineJoin;
    private Float lineOffset;
    private Float lineOpacity;
    private String linePattern;
    private Float lineWidth;

    @Nullable
    public static LineOptions fromFeature(@NonNull Feature feature) {
        if (feature.geometry() != null) {
            if (feature.geometry() instanceof LineString) {
                LineOptions lineOptions = new LineOptions();
                lineOptions.geometry = (LineString) feature.geometry();
                if (feature.hasProperty(PROPERTY_LINE_JOIN)) {
                    lineOptions.lineJoin = feature.getProperty(PROPERTY_LINE_JOIN).getAsString();
                }
                if (feature.hasProperty(PROPERTY_LINE_OPACITY)) {
                    lineOptions.lineOpacity = Float.valueOf(feature.getProperty(PROPERTY_LINE_OPACITY).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_LINE_COLOR)) {
                    lineOptions.lineColor = feature.getProperty(PROPERTY_LINE_COLOR).getAsString();
                }
                if (feature.hasProperty(PROPERTY_LINE_WIDTH)) {
                    lineOptions.lineWidth = Float.valueOf(feature.getProperty(PROPERTY_LINE_WIDTH).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_LINE_GAP_WIDTH)) {
                    lineOptions.lineGapWidth = Float.valueOf(feature.getProperty(PROPERTY_LINE_GAP_WIDTH).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_LINE_OFFSET)) {
                    lineOptions.lineOffset = Float.valueOf(feature.getProperty(PROPERTY_LINE_OFFSET).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_LINE_BLUR)) {
                    lineOptions.lineBlur = Float.valueOf(feature.getProperty(PROPERTY_LINE_BLUR).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_LINE_PATTERN)) {
                    lineOptions.linePattern = feature.getProperty(PROPERTY_LINE_PATTERN).getAsString();
                }
                if (feature.hasProperty(PROPERTY_IS_DRAGGABLE)) {
                    lineOptions.isDraggable = feature.getProperty(PROPERTY_IS_DRAGGABLE).getAsBoolean();
                }
                return lineOptions;
            }
            return null;
        }
        throw new RuntimeException("geometry field is required");
    }

    @Override // com.mappls.sdk.plugin.annotation.g
    public Line build(long j, AnnotationManager<?, Line, ?, ?, ?, ?> annotationManager) {
        if (this.geometry != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(PROPERTY_LINE_JOIN, this.lineJoin);
            jsonObject.addProperty(PROPERTY_LINE_OPACITY, this.lineOpacity);
            jsonObject.addProperty(PROPERTY_LINE_COLOR, this.lineColor);
            jsonObject.addProperty(PROPERTY_LINE_WIDTH, this.lineWidth);
            jsonObject.addProperty(PROPERTY_LINE_GAP_WIDTH, this.lineGapWidth);
            jsonObject.addProperty(PROPERTY_LINE_OFFSET, this.lineOffset);
            jsonObject.addProperty(PROPERTY_LINE_BLUR, this.lineBlur);
            jsonObject.addProperty(PROPERTY_LINE_PATTERN, this.linePattern);
            Line line = new Line(j, annotationManager, jsonObject, this.geometry);
            line.setDraggable(this.isDraggable);
            line.setData(this.data);
            return line;
        }
        throw new RuntimeException("geometry field is required");
    }

    public LineOptions data(@Nullable JsonElement jsonElement) {
        this.data = jsonElement;
        return this;
    }

    public LineOptions draggable(boolean z) {
        this.isDraggable = z;
        return this;
    }

    public LineOptions geometry(LineString lineString) {
        this.geometry = lineString;
        return this;
    }

    @Nullable
    public JsonElement getData() {
        return this.data;
    }

    public LineString getGeometry() {
        return this.geometry;
    }

    public Float getLineBlur() {
        return this.lineBlur;
    }

    public String getLineColor() {
        return this.lineColor;
    }

    public Float getLineGapWidth() {
        return this.lineGapWidth;
    }

    public String getLineJoin() {
        return this.lineJoin;
    }

    public Float getLineOffset() {
        return this.lineOffset;
    }

    public Float getLineOpacity() {
        return this.lineOpacity;
    }

    public String getLinePattern() {
        return this.linePattern;
    }

    public Float getLineWidth() {
        return this.lineWidth;
    }

    public List<LatLng> getPoints() {
        ArrayList arrayList = new ArrayList();
        LineString lineString = this.geometry;
        if (lineString != null) {
            for (Point point : lineString.coordinates()) {
                arrayList.add(new LatLng(point.latitude(), point.longitude()));
            }
        }
        return arrayList;
    }

    public boolean isDraggable() {
        return this.isDraggable;
    }

    public LineOptions lineBlur(Float f) {
        this.lineBlur = f;
        return this;
    }

    public LineOptions lineColor(String str) {
        this.lineColor = str;
        return this;
    }

    public LineOptions lineGapWidth(Float f) {
        this.lineGapWidth = f;
        return this;
    }

    public LineOptions lineJoin(String str) {
        this.lineJoin = str;
        return this;
    }

    public LineOptions lineOffset(Float f) {
        this.lineOffset = f;
        return this;
    }

    public LineOptions lineOpacity(Float f) {
        this.lineOpacity = f;
        return this;
    }

    public LineOptions linePattern(String str) {
        this.linePattern = str;
        return this;
    }

    public LineOptions lineWidth(Float f) {
        this.lineWidth = f;
        return this;
    }

    public LineOptions points(List<LatLng> list) {
        ArrayList arrayList = new ArrayList();
        for (LatLng latLng : list) {
            arrayList.add(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()));
        }
        this.geometry = LineString.fromLngLats(arrayList);
        return this;
    }
}
