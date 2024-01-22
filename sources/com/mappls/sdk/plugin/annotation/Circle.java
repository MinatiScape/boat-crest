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
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.gestures.MoveDistancesObject;
import com.mappls.sdk.maps.Projection;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.utils.ColorUtils;
@Keep
@UiThread
/* loaded from: classes11.dex */
public class Circle extends Annotation<Point> {
    private final AnnotationManager<?, Circle, ?, ?, ?, ?> annotationManager;

    public Circle(long j, AnnotationManager<?, Circle, ?, ?, ?, ?> annotationManager, JsonObject jsonObject, Point point) {
        super(j, jsonObject, point);
        this.annotationManager = annotationManager;
    }

    public Float getCircleBlur() {
        if (this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_BLUR).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_BLUR).getAsFloat());
    }

    public String getCircleColor() {
        if (this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_COLOR).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_COLOR).getAsString();
    }

    @ColorInt
    public Integer getCircleColorAsInt() {
        if (this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_COLOR).isJsonNull()) {
            return null;
        }
        return Integer.valueOf(ColorUtils.rgbaToColor(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_COLOR).getAsString()));
    }

    public Float getCircleOpacity() {
        if (this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_OPACITY).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_OPACITY).getAsFloat());
    }

    public Float getCircleRadius() {
        if (this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_RADIUS).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_RADIUS).getAsFloat());
    }

    public String getCircleStrokeColor() {
        if (this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_STROKE_COLOR).isJsonNull()) {
            return null;
        }
        return this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_STROKE_COLOR).getAsString();
    }

    @ColorInt
    public Integer getCircleStrokeColorAsInt() {
        if (this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_STROKE_COLOR).isJsonNull()) {
            return null;
        }
        return Integer.valueOf(ColorUtils.rgbaToColor(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_STROKE_COLOR).getAsString()));
    }

    public Float getCircleStrokeOpacity() {
        if (this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_STROKE_OPACITY).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_STROKE_OPACITY).getAsFloat());
    }

    public Float getCircleStrokeWidth() {
        if (this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_STROKE_WIDTH).isJsonNull()) {
            return null;
        }
        return Float.valueOf(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_STROKE_WIDTH).getAsFloat());
    }

    @Override // com.mappls.sdk.plugin.annotation.Annotation
    public String getName() {
        return "Circle";
    }

    @Override // com.mappls.sdk.plugin.annotation.Annotation
    @Nullable
    public Geometry getOffsetGeometry(@NonNull Projection projection, @NonNull MoveDistancesObject moveDistancesObject, float f, float f2) {
        LatLng fromScreenLocation = projection.fromScreenLocation(new PointF(moveDistancesObject.getCurrentX() - f, moveDistancesObject.getCurrentY() - f2));
        if (fromScreenLocation.getLatitude() > 85.05112877980659d || fromScreenLocation.getLatitude() < -85.05112877980659d) {
            return null;
        }
        return Point.fromLngLat(fromScreenLocation.getLongitude(), fromScreenLocation.getLatitude());
    }

    @NonNull
    public LatLng getPosition() {
        return new LatLng(((Point) this.geometry).latitude(), ((Point) this.geometry).longitude());
    }

    public void setCircleBlur(Float f) {
        this.jsonObject.addProperty(CircleOptions.PROPERTY_CIRCLE_BLUR, f);
    }

    public void setCircleColor(@ColorInt int i) {
        this.jsonObject.addProperty(CircleOptions.PROPERTY_CIRCLE_COLOR, ColorUtils.colorToRgbaString(i));
    }

    public void setCircleColor(@NonNull String str) {
        this.jsonObject.addProperty(CircleOptions.PROPERTY_CIRCLE_COLOR, str);
    }

    public void setCircleOpacity(Float f) {
        this.jsonObject.addProperty(CircleOptions.PROPERTY_CIRCLE_OPACITY, f);
    }

    public void setCircleRadius(Float f) {
        this.jsonObject.addProperty(CircleOptions.PROPERTY_CIRCLE_RADIUS, f);
    }

    public void setCircleStrokeColor(@ColorInt int i) {
        this.jsonObject.addProperty(CircleOptions.PROPERTY_CIRCLE_STROKE_COLOR, ColorUtils.colorToRgbaString(i));
    }

    public void setCircleStrokeColor(@NonNull String str) {
        this.jsonObject.addProperty(CircleOptions.PROPERTY_CIRCLE_STROKE_COLOR, str);
    }

    public void setCircleStrokeOpacity(Float f) {
        this.jsonObject.addProperty(CircleOptions.PROPERTY_CIRCLE_STROKE_OPACITY, f);
    }

    public void setCircleStrokeWidth(Float f) {
        this.jsonObject.addProperty(CircleOptions.PROPERTY_CIRCLE_STROKE_WIDTH, f);
    }

    public void setPosition(LatLng latLng) {
        this.geometry = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
    }

    @Override // com.mappls.sdk.plugin.annotation.Annotation
    public void setUsedDataDrivenProperties() {
        if (!(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_RADIUS) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(CircleOptions.PROPERTY_CIRCLE_RADIUS);
        }
        if (!(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_COLOR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(CircleOptions.PROPERTY_CIRCLE_COLOR);
        }
        if (!(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_BLUR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(CircleOptions.PROPERTY_CIRCLE_BLUR);
        }
        if (!(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_OPACITY) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(CircleOptions.PROPERTY_CIRCLE_OPACITY);
        }
        if (!(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_STROKE_WIDTH) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(CircleOptions.PROPERTY_CIRCLE_STROKE_WIDTH);
        }
        if (!(this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_STROKE_COLOR) instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty(CircleOptions.PROPERTY_CIRCLE_STROKE_COLOR);
        }
        if (this.jsonObject.get(CircleOptions.PROPERTY_CIRCLE_STROKE_OPACITY) instanceof JsonNull) {
            return;
        }
        this.annotationManager.enableDataDrivenProperty(CircleOptions.PROPERTY_CIRCLE_STROKE_OPACITY);
    }
}
