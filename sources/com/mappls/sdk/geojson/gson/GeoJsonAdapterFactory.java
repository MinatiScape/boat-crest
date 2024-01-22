package com.mappls.sdk.geojson.gson;

import androidx.annotation.Keep;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.mappls.sdk.geojson.BoundingBox;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.geojson.GeometryCollection;
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.MultiLineString;
import com.mappls.sdk.geojson.MultiPoint;
import com.mappls.sdk.geojson.MultiPolygon;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.Polygon;
@Keep
/* loaded from: classes11.dex */
public abstract class GeoJsonAdapterFactory implements TypeAdapterFactory {

    /* loaded from: classes11.dex */
    public static final class GeoJsonAdapterFactoryIml extends GeoJsonAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class<? super T> rawType = typeToken.getRawType();
            if (BoundingBox.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) BoundingBox.typeAdapter(gson);
            }
            if (Feature.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) Feature.typeAdapter(gson);
            }
            if (FeatureCollection.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) FeatureCollection.typeAdapter(gson);
            }
            if (GeometryCollection.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) GeometryCollection.typeAdapter(gson);
            }
            if (LineString.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) LineString.typeAdapter(gson);
            }
            if (MultiLineString.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) MultiLineString.typeAdapter(gson);
            }
            if (MultiPoint.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) MultiPoint.typeAdapter(gson);
            }
            if (MultiPolygon.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) MultiPolygon.typeAdapter(gson);
            }
            if (Polygon.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) Polygon.typeAdapter(gson);
            }
            if (Point.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) Point.typeAdapter(gson);
            }
            return null;
        }
    }

    public static TypeAdapterFactory create() {
        return new GeoJsonAdapterFactoryIml();
    }
}
