package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.geometry.LatLng;
@Keep
/* loaded from: classes11.dex */
public class CircleOptions extends g<Circle> {
    public static final String PROPERTY_CIRCLE_BLUR = "circle-blur";
    public static final String PROPERTY_CIRCLE_COLOR = "circle-color";
    public static final String PROPERTY_CIRCLE_OPACITY = "circle-opacity";
    public static final String PROPERTY_CIRCLE_RADIUS = "circle-radius";
    public static final String PROPERTY_CIRCLE_STROKE_COLOR = "circle-stroke-color";
    public static final String PROPERTY_CIRCLE_STROKE_OPACITY = "circle-stroke-opacity";
    public static final String PROPERTY_CIRCLE_STROKE_WIDTH = "circle-stroke-width";
    private static final String PROPERTY_IS_DRAGGABLE = "is-draggable";
    private Float circleBlur;
    private String circleColor;
    private Float circleOpacity;
    private Float circleRadius;
    private String circleStrokeColor;
    private Float circleStrokeOpacity;
    private Float circleStrokeWidth;
    private JsonElement data;
    private Point geometry;
    private boolean isDraggable;

    @Nullable
    public static CircleOptions fromFeature(@NonNull Feature feature) {
        if (feature.geometry() != null) {
            if (feature.geometry() instanceof Point) {
                CircleOptions circleOptions = new CircleOptions();
                circleOptions.geometry = (Point) feature.geometry();
                if (feature.hasProperty(PROPERTY_CIRCLE_RADIUS)) {
                    circleOptions.circleRadius = Float.valueOf(feature.getProperty(PROPERTY_CIRCLE_RADIUS).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_CIRCLE_COLOR)) {
                    circleOptions.circleColor = feature.getProperty(PROPERTY_CIRCLE_COLOR).getAsString();
                }
                if (feature.hasProperty(PROPERTY_CIRCLE_BLUR)) {
                    circleOptions.circleBlur = Float.valueOf(feature.getProperty(PROPERTY_CIRCLE_BLUR).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_CIRCLE_OPACITY)) {
                    circleOptions.circleOpacity = Float.valueOf(feature.getProperty(PROPERTY_CIRCLE_OPACITY).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_CIRCLE_STROKE_WIDTH)) {
                    circleOptions.circleStrokeWidth = Float.valueOf(feature.getProperty(PROPERTY_CIRCLE_STROKE_WIDTH).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_CIRCLE_STROKE_COLOR)) {
                    circleOptions.circleStrokeColor = feature.getProperty(PROPERTY_CIRCLE_STROKE_COLOR).getAsString();
                }
                if (feature.hasProperty(PROPERTY_CIRCLE_STROKE_OPACITY)) {
                    circleOptions.circleStrokeOpacity = Float.valueOf(feature.getProperty(PROPERTY_CIRCLE_STROKE_OPACITY).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_IS_DRAGGABLE)) {
                    circleOptions.isDraggable = feature.getProperty(PROPERTY_IS_DRAGGABLE).getAsBoolean();
                }
                return circleOptions;
            }
            return null;
        }
        throw new RuntimeException("geometry field is required");
    }

    @Override // com.mappls.sdk.plugin.annotation.g
    public Circle build(long j, AnnotationManager<?, Circle, ?, ?, ?, ?> annotationManager) {
        if (this.geometry != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(PROPERTY_CIRCLE_RADIUS, this.circleRadius);
            jsonObject.addProperty(PROPERTY_CIRCLE_COLOR, this.circleColor);
            jsonObject.addProperty(PROPERTY_CIRCLE_BLUR, this.circleBlur);
            jsonObject.addProperty(PROPERTY_CIRCLE_OPACITY, this.circleOpacity);
            jsonObject.addProperty(PROPERTY_CIRCLE_STROKE_WIDTH, this.circleStrokeWidth);
            jsonObject.addProperty(PROPERTY_CIRCLE_STROKE_COLOR, this.circleStrokeColor);
            jsonObject.addProperty(PROPERTY_CIRCLE_STROKE_OPACITY, this.circleStrokeOpacity);
            Circle circle = new Circle(j, annotationManager, jsonObject, this.geometry);
            circle.setDraggable(this.isDraggable);
            circle.setData(this.data);
            return circle;
        }
        throw new RuntimeException("geometry field is required");
    }

    public CircleOptions circleBlur(Float f) {
        this.circleBlur = f;
        return this;
    }

    public CircleOptions circleColor(String str) {
        this.circleColor = str;
        return this;
    }

    public CircleOptions circleOpacity(Float f) {
        this.circleOpacity = f;
        return this;
    }

    public CircleOptions circleRadius(Float f) {
        this.circleRadius = f;
        return this;
    }

    public CircleOptions circleStrokeColor(String str) {
        this.circleStrokeColor = str;
        return this;
    }

    public CircleOptions circleStrokeOpacity(Float f) {
        this.circleStrokeOpacity = f;
        return this;
    }

    public CircleOptions circleStrokeWidth(Float f) {
        this.circleStrokeWidth = f;
        return this;
    }

    public CircleOptions data(@Nullable JsonElement jsonElement) {
        this.data = jsonElement;
        return this;
    }

    public CircleOptions draggable(boolean z) {
        this.isDraggable = z;
        return this;
    }

    public CircleOptions geometry(Point point) {
        this.geometry = point;
        return this;
    }

    public Float getCircleBlur() {
        return this.circleBlur;
    }

    public String getCircleColor() {
        return this.circleColor;
    }

    public Float getCircleOpacity() {
        return this.circleOpacity;
    }

    public Float getCircleRadius() {
        return this.circleRadius;
    }

    public String getCircleStrokeColor() {
        return this.circleStrokeColor;
    }

    public Float getCircleStrokeOpacity() {
        return this.circleStrokeOpacity;
    }

    public Float getCircleStrokeWidth() {
        return this.circleStrokeWidth;
    }

    @Nullable
    public JsonElement getData() {
        return this.data;
    }

    public Point getGeometry() {
        return this.geometry;
    }

    public LatLng getPosition() {
        if (this.geometry == null) {
            return null;
        }
        return new LatLng(this.geometry.latitude(), this.geometry.longitude());
    }

    public boolean isDraggable() {
        return this.isDraggable;
    }

    public CircleOptions position(LatLng latLng) {
        this.geometry = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
        return this;
    }
}
