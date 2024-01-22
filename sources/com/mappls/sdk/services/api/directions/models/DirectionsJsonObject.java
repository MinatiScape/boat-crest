package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Keep;
import com.google.gson.GsonBuilder;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.PointAsCoordinatesTypeAdapter;
import com.mappls.sdk.services.api.directions.DirectionsAdapterFactory;
import com.mappls.sdk.services.api.directions.WalkingOptionsAdapterFactory;
import java.io.Serializable;
@Keep
/* loaded from: classes11.dex */
public class DirectionsJsonObject implements Serializable {
    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        gsonBuilder.registerTypeAdapter(Point.class, new PointAsCoordinatesTypeAdapter());
        gsonBuilder.registerTypeAdapterFactory(WalkingOptionsAdapterFactory.create());
        return gsonBuilder.create().toJson(this);
    }
}
