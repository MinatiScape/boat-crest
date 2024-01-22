package com.mappls.sdk.services.api.distance.models;

import androidx.annotation.Keep;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.PointAsCoordinatesTypeAdapter;
import com.mappls.sdk.services.api.distance.DistanceMatrixAdapterFactory;
import java.io.Serializable;
@Keep
/* loaded from: classes11.dex */
public class DistanceJsonObject implements Serializable {
    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DistanceMatrixAdapterFactory.create());
        gsonBuilder.registerTypeAdapter(Point.class, new PointAsCoordinatesTypeAdapter());
        return gsonBuilder.create().toJson(this);
    }
}
