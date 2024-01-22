package com.mappls.sdk.maps.location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.Point;
/* loaded from: classes11.dex */
public class e {
    @NonNull
    public Feature a(@Nullable Feature feature, boolean z) {
        if (feature != null) {
            return feature;
        }
        Feature fromGeometry = Feature.fromGeometry(Point.fromLngLat(0.0d, 0.0d));
        fromGeometry.addNumberProperty("mappls-property-gps-bearing", Float.valueOf(0.0f));
        fromGeometry.addNumberProperty("mappls-property-compass-bearing", Float.valueOf(0.0f));
        fromGeometry.addBooleanProperty("mappls-property-location-stale", Boolean.valueOf(z));
        return fromGeometry;
    }
}
