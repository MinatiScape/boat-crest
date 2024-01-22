package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.Point;
/* loaded from: classes10.dex */
public class KmlPoint extends Point {
    public final Double b;

    public KmlPoint(LatLng latLng) {
        this(latLng, null);
    }

    public Double getAltitude() {
        return this.b;
    }

    public KmlPoint(LatLng latLng, Double d) {
        super(latLng);
        this.b = d;
    }
}
