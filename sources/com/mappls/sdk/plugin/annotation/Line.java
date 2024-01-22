package com.mappls.sdk.plugin.annotation;

import android.graphics.PointF;
import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.gestures.MoveDistancesObject;
import com.mappls.sdk.maps.Projection;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.utils.ColorUtils;
import java.util.ArrayList;
import java.util.List;
@Keep
@UiThread
/* loaded from: classes11.dex */
public class Line extends Annotation<LineString> {
    private final AnnotationManager<?, Line, ?, ?, ?, ?> annotationManager;

    public Line(long j, AnnotationManager<?, Line, ?, ?, ?, ?> annotationManager, JsonObject jsonObject, LineString lineString) {
        super(j, jsonObject, lineString);
        this.annotationManager = annotationManager;
    }

    public Float getLineBlur() {
        if (this.jsonObject.get(LineOptions.PROPERTY_LINE_BLUR).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(LineOptions.PROPERTY_LINE_BLUR).getAsFloat());
    }

    public String getLineColor() {
        if (this.jsonObject.get(LineOptions.PROPERTY_LINE_COLOR).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(LineOptions.PROPERTY_LINE_COLOR).getAsString();
    }

    @ColorInt
    public Integer getLineColorAsInt() {
        if (this.jsonObject.get(LineOptions.PROPERTY_LINE_COLOR).isJsonNull()) {
            return null;
        }
        return Integer.valueOf(ColorUtils.rgbaToColor(this.jsonObject.get(LineOptions.PROPERTY_LINE_COLOR).getAsString()));
    }

    public Float getLineGapWidth() {
        if (this.jsonObject.get(LineOptions.PROPERTY_LINE_GAP_WIDTH).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(LineOptions.PROPERTY_LINE_GAP_WIDTH).getAsFloat());
    }

    public String getLineJoin() {
        if (this.jsonObject.get(LineOptions.PROPERTY_LINE_JOIN).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(LineOptions.PROPERTY_LINE_JOIN).getAsString();
    }

    public Float getLineOffset() {
        if (this.jsonObject.get(LineOptions.PROPERTY_LINE_OFFSET).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(LineOptions.PROPERTY_LINE_OFFSET).getAsFloat());
    }

    public Float getLineOpacity() {
        if (this.jsonObject.get(LineOptions.PROPERTY_LINE_OPACITY).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(LineOptions.PROPERTY_LINE_OPACITY).getAsFloat());
    }

    public String getLinePattern() {
        if (this.jsonObject.get(LineOptions.PROPERTY_LINE_PATTERN).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(LineOptions.PROPERTY_LINE_PATTERN).getAsString();
    }

    public Float getLineWidth() {
        if (this.jsonObject.get(LineOptions.PROPERTY_LINE_WIDTH).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(LineOptions.PROPERTY_LINE_WIDTH).getAsFloat());
    }

    @Override // com.mappls.sdk.plugin.annotation.Annotation
    public String getName() {
        return "Line";
    }

    @Override // com.mappls.sdk.plugin.annotation.Annotation
    @Nullable
    public Geometry getOffsetGeometry(@NonNull Projection projection, @NonNull MoveDistancesObject moveDistancesObject, float f, float f2) {
        List<Point> coordinates = ((LineString) this.geometry).coordinates();
        ArrayList arrayList = new ArrayList(coordinates.size());
        for (Point point : coordinates) {
            PointF screenLocation = projection.toScreenLocation(new LatLng(point.latitude(), point.longitude()));
            screenLocation.x -= moveDistancesObject.getDistanceXSinceLast();
            screenLocation.y -= moveDistancesObject.getDistanceYSinceLast();
            LatLng fromScreenLocation = projection.fromScreenLocation(screenLocation);
            if (fromScreenLocation.getLatitude() > 85.05112877980659d || fromScreenLocation.getLatitude() < -85.05112877980659d) {
                return null;
            }
            arrayList.add(Point.fromLngLat(fromScreenLocation.getLongitude(), fromScreenLocation.getLatitude()));
        }
        return LineString.fromLngLats(arrayList);
    }

    @NonNull
    public List<LatLng> getPoints() {
        ArrayList arrayList = new ArrayList();
        for (Point point : ((LineString) this.geometry).coordinates()) {
            arrayList.add(new LatLng(point.latitude(), point.longitude()));
        }
        return arrayList;
    }

    public void setLineBlur(Float f) {
        this.jsonObject.addProperty(LineOptions.PROPERTY_LINE_BLUR, f);
    }

    public void setLineColor(@ColorInt int i) {
        this.jsonObject.addProperty(LineOptions.PROPERTY_LINE_COLOR, ColorUtils.colorToRgbaString(i));
    }

    public void setLineColor(@NonNull String str) {
        this.jsonObject.addProperty(LineOptions.PROPERTY_LINE_COLOR, str);
    }

    public void setLineGapWidth(Float f) {
        this.jsonObject.addProperty(LineOptions.PROPERTY_LINE_GAP_WIDTH, f);
    }

    public void setLineJoin(String str) {
        this.jsonObject.addProperty(LineOptions.PROPERTY_LINE_JOIN, str);
    }

    public void setLineOffset(Float f) {
        this.jsonObject.addProperty(LineOptions.PROPERTY_LINE_OFFSET, f);
    }

    public void setLineOpacity(Float f) {
        this.jsonObject.addProperty(LineOptions.PROPERTY_LINE_OPACITY, f);
    }

    public void setLinePattern(String str) {
        this.jsonObject.addProperty(LineOptions.PROPERTY_LINE_PATTERN, str);
    }

    public void setLineWidth(Float f) {
        this.jsonObject.addProperty(LineOptions.PROPERTY_LINE_WIDTH, f);
    }

    public void setPoints(List<LatLng> list) {
        ArrayList arrayList = new ArrayList();
        for (LatLng latLng : list) {
            arrayList.add(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()));
        }
        this.geometry = LineString.fromLngLats(arrayList);
    }

    @Override // com.mappls.sdk.plugin.annotation.Annotation
    public void setUsedDataDrivenProperties() {
        if (!(this.jsonObject.get(LineOptions.PROPERTY_LINE_JOIN) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(LineOptions.PROPERTY_LINE_JOIN);
        }
        if (!(this.jsonObject.get(LineOptions.PROPERTY_LINE_OPACITY) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(LineOptions.PROPERTY_LINE_OPACITY);
        }
        if (!(this.jsonObject.get(LineOptions.PROPERTY_LINE_COLOR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(LineOptions.PROPERTY_LINE_COLOR);
        }
        if (!(this.jsonObject.get(LineOptions.PROPERTY_LINE_WIDTH) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(LineOptions.PROPERTY_LINE_WIDTH);
        }
        if (!(this.jsonObject.get(LineOptions.PROPERTY_LINE_GAP_WIDTH) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(LineOptions.PROPERTY_LINE_GAP_WIDTH);
        }
        if (!(this.jsonObject.get(LineOptions.PROPERTY_LINE_OFFSET) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(LineOptions.PROPERTY_LINE_OFFSET);
        }
        if (!(this.jsonObject.get(LineOptions.PROPERTY_LINE_BLUR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(LineOptions.PROPERTY_LINE_BLUR);
        }
        if (this.jsonObject.get(LineOptions.PROPERTY_LINE_PATTERN) instanceof JsonNull) {
            return;
        }
        this.annotationManager.enableDataDrivenProperty(LineOptions.PROPERTY_LINE_PATTERN);
    }
}
