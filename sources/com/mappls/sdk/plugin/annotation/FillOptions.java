package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.Polygon;
import com.mappls.sdk.maps.geometry.LatLng;
import java.util.ArrayList;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
public class FillOptions extends g<Fill> {
    public static final String PROPERTY_FILL_COLOR = "fill-color";
    public static final String PROPERTY_FILL_OPACITY = "fill-opacity";
    public static final String PROPERTY_FILL_OUTLINE_COLOR = "fill-outline-color";
    public static final String PROPERTY_FILL_PATTERN = "fill-pattern";
    private static final String PROPERTY_IS_DRAGGABLE = "is-draggable";
    private JsonElement data;
    private String fillColor;
    private Float fillOpacity;
    private String fillOutlineColor;
    private String fillPattern;
    private Polygon geometry;
    private boolean isDraggable;

    @Nullable
    public static FillOptions fromFeature(@NonNull Feature feature) {
        if (feature.geometry() != null) {
            if (feature.geometry() instanceof Polygon) {
                FillOptions fillOptions = new FillOptions();
                fillOptions.geometry = (Polygon) feature.geometry();
                if (feature.hasProperty(PROPERTY_FILL_OPACITY)) {
                    fillOptions.fillOpacity = Float.valueOf(feature.getProperty(PROPERTY_FILL_OPACITY).getAsFloat());
                }
                if (feature.hasProperty(PROPERTY_FILL_COLOR)) {
                    fillOptions.fillColor = feature.getProperty(PROPERTY_FILL_COLOR).getAsString();
                }
                if (feature.hasProperty(PROPERTY_FILL_OUTLINE_COLOR)) {
                    fillOptions.fillOutlineColor = feature.getProperty(PROPERTY_FILL_OUTLINE_COLOR).getAsString();
                }
                if (feature.hasProperty(PROPERTY_FILL_PATTERN)) {
                    fillOptions.fillPattern = feature.getProperty(PROPERTY_FILL_PATTERN).getAsString();
                }
                if (feature.hasProperty(PROPERTY_IS_DRAGGABLE)) {
                    fillOptions.isDraggable = feature.getProperty(PROPERTY_IS_DRAGGABLE).getAsBoolean();
                }
                return fillOptions;
            }
            return null;
        }
        throw new RuntimeException("geometry field is required");
    }

    @Override // com.mappls.sdk.plugin.annotation.g
    public Fill build(long j, AnnotationManager<?, Fill, ?, ?, ?, ?> annotationManager) {
        if (this.geometry != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(PROPERTY_FILL_OPACITY, this.fillOpacity);
            jsonObject.addProperty(PROPERTY_FILL_COLOR, this.fillColor);
            jsonObject.addProperty(PROPERTY_FILL_OUTLINE_COLOR, this.fillOutlineColor);
            jsonObject.addProperty(PROPERTY_FILL_PATTERN, this.fillPattern);
            Fill fill = new Fill(j, annotationManager, jsonObject, this.geometry);
            fill.setDraggable(this.isDraggable);
            fill.setData(this.data);
            return fill;
        }
        throw new RuntimeException("geometry field is required");
    }

    public FillOptions data(@Nullable JsonElement jsonElement) {
        this.data = jsonElement;
        return this;
    }

    public FillOptions draggable(boolean z) {
        this.isDraggable = z;
        return this;
    }

    public FillOptions fillColor(String str) {
        this.fillColor = str;
        return this;
    }

    public FillOptions fillOpacity(Float f) {
        this.fillOpacity = f;
        return this;
    }

    public FillOptions fillOutlineColor(String str) {
        this.fillOutlineColor = str;
        return this;
    }

    public FillOptions fillPattern(String str) {
        this.fillPattern = str;
        return this;
    }

    public FillOptions geometry(Polygon polygon) {
        this.geometry = polygon;
        return this;
    }

    @Nullable
    public JsonElement getData() {
        return this.data;
    }

    public String getFillColor() {
        return this.fillColor;
    }

    public Float getFillOpacity() {
        return this.fillOpacity;
    }

    public String getFillOutlineColor() {
        return this.fillOutlineColor;
    }

    public String getFillPattern() {
        return this.fillPattern;
    }

    public Polygon getGeometry() {
        return this.geometry;
    }

    public List<List<LatLng>> getPoints() {
        ArrayList arrayList = new ArrayList();
        Polygon polygon = this.geometry;
        if (polygon != null) {
            for (List<Point> list : polygon.coordinates()) {
                ArrayList arrayList2 = new ArrayList();
                for (Point point : list) {
                    arrayList2.add(new LatLng(point.latitude(), point.longitude()));
                }
                arrayList.add(arrayList2);
            }
        }
        return arrayList;
    }

    public boolean isDraggable() {
        return this.isDraggable;
    }

    public FillOptions points(List<List<LatLng>> list) {
        ArrayList arrayList = new ArrayList();
        for (List<LatLng> list2 : list) {
            ArrayList arrayList2 = new ArrayList();
            for (LatLng latLng : list2) {
                arrayList2.add(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()));
            }
            arrayList.add(arrayList2);
        }
        this.geometry = Polygon.fromLngLats(arrayList);
        return this;
    }
}
