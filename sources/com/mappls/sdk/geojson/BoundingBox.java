package com.mappls.sdk.geojson;

import androidx.annotation.FloatRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.mappls.sdk.geojson.gson.BoundingBoxTypeAdapter;
import java.io.Serializable;
import java.util.Objects;
@Keep
/* loaded from: classes11.dex */
public class BoundingBox implements Serializable {
    private final Point northeast;
    private final Point southwest;

    public BoundingBox(Point point, Point point2) {
        Objects.requireNonNull(point, "Null southwest");
        this.southwest = point;
        Objects.requireNonNull(point2, "Null northeast");
        this.northeast = point2;
    }

    @Deprecated
    public static BoundingBox fromCoordinates(@FloatRange(from = -180.0d, to = 180.0d) double d, @FloatRange(from = -90.0d, to = 90.0d) double d2, @FloatRange(from = -180.0d, to = 180.0d) double d3, @FloatRange(from = -90.0d, to = 90.0d) double d4) {
        return fromLngLats(d, d2, d3, d4);
    }

    public static BoundingBox fromJson(String str) {
        return (BoundingBox) new GsonBuilder().registerTypeAdapter(BoundingBox.class, new BoundingBoxTypeAdapter()).create().fromJson(str, (Class<Object>) BoundingBox.class);
    }

    public static BoundingBox fromLngLats(@FloatRange(from = -180.0d, to = 180.0d) double d, @FloatRange(from = -90.0d, to = 90.0d) double d2, @FloatRange(from = -180.0d, to = 180.0d) double d3, @FloatRange(from = -90.0d, to = 90.0d) double d4) {
        return new BoundingBox(Point.fromLngLat(d, d2), Point.fromLngLat(d3, d4));
    }

    public static BoundingBox fromPoints(@NonNull Point point, @NonNull Point point2) {
        return new BoundingBox(point, point2);
    }

    public static TypeAdapter<BoundingBox> typeAdapter(Gson gson) {
        return new BoundingBoxTypeAdapter();
    }

    public final double east() {
        return northeast().longitude();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BoundingBox) {
            BoundingBox boundingBox = (BoundingBox) obj;
            return this.southwest.equals(boundingBox.southwest()) && this.northeast.equals(boundingBox.northeast());
        }
        return false;
    }

    public int hashCode() {
        return ((this.southwest.hashCode() ^ 1000003) * 1000003) ^ this.northeast.hashCode();
    }

    public final double north() {
        return northeast().latitude();
    }

    @NonNull
    public Point northeast() {
        return this.northeast;
    }

    public final double south() {
        return southwest().latitude();
    }

    @NonNull
    public Point southwest() {
        return this.southwest;
    }

    public final String toJson() {
        return new GsonBuilder().registerTypeAdapter(BoundingBox.class, new BoundingBoxTypeAdapter()).create().toJson(this, BoundingBox.class);
    }

    public String toString() {
        return "BoundingBox{southwest=" + this.southwest + ", northeast=" + this.northeast + "}";
    }

    public final double west() {
        return southwest().longitude();
    }

    @Deprecated
    public static BoundingBox fromCoordinates(@FloatRange(from = -180.0d, to = 180.0d) double d, @FloatRange(from = -90.0d, to = 90.0d) double d2, double d3, @FloatRange(from = -180.0d, to = 180.0d) double d4, @FloatRange(from = -90.0d, to = 90.0d) double d5, double d6) {
        return fromLngLats(d, d2, d3, d4, d5, d6);
    }

    public static BoundingBox fromLngLats(@FloatRange(from = -180.0d, to = 180.0d) double d, @FloatRange(from = -90.0d, to = 90.0d) double d2, double d3, @FloatRange(from = -180.0d, to = 180.0d) double d4, @FloatRange(from = -90.0d, to = 90.0d) double d5, double d6) {
        return new BoundingBox(Point.fromLngLat(d, d2, d3), Point.fromLngLat(d4, d5, d6));
    }
}
