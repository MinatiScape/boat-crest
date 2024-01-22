package com.mappls.sdk.geojson.gson;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.geojson.GeometryAdapterFactory;
@Keep
/* loaded from: classes11.dex */
public class GeometryGeoJson {
    public static Geometry fromJson(@NonNull String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        gsonBuilder.registerTypeAdapterFactory(GeometryAdapterFactory.create());
        return (Geometry) gsonBuilder.create().fromJson(str, (Class<Object>) Geometry.class);
    }
}
