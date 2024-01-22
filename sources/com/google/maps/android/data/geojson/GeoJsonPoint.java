package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.Point;
/* loaded from: classes10.dex */
public class GeoJsonPoint extends Point {
    public final Double b;

    public GeoJsonPoint(LatLng latLng) {
        this(latLng, null);
    }

    public Double getAltitude() {
        return this.b;
    }

    public LatLng getCoordinates() {
        return getGeometryObject();
    }

    public String getType() {
        return getGeometryType();
    }

    public GeoJsonPoint(LatLng latLng, Double d) {
        super(latLng);
        this.b = d;
    }
}
