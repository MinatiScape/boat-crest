package com.mappls.sdk.geojson;

import androidx.annotation.Keep;
import com.google.gson.TypeAdapterFactory;
import com.google.maps.android.data.kml.KmlPolygon;
import com.mappls.sdk.geojson.typeadapters.RuntimeTypeAdapterFactory;
@Keep
/* loaded from: classes11.dex */
public abstract class GeometryAdapterFactory implements TypeAdapterFactory {
    private static TypeAdapterFactory geometryTypeFactory;

    public static TypeAdapterFactory create() {
        if (geometryTypeFactory == null) {
            geometryTypeFactory = RuntimeTypeAdapterFactory.of(Geometry.class, "type", true).registerSubtype(GeometryCollection.class, "GeometryCollection").registerSubtype(Point.class, "Point").registerSubtype(MultiPoint.class, "MultiPoint").registerSubtype(LineString.class, "LineString").registerSubtype(MultiLineString.class, "MultiLineString").registerSubtype(Polygon.class, KmlPolygon.GEOMETRY_TYPE).registerSubtype(MultiPolygon.class, "MultiPolygon");
        }
        return geometryTypeFactory;
    }
}
