package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.LineString;
import java.util.List;
/* loaded from: classes10.dex */
public class GeoJsonLineString extends LineString {
    public final List<Double> b;

    public GeoJsonLineString(List<LatLng> list) {
        this(list, null);
    }

    public List<Double> getAltitudes() {
        return this.b;
    }

    public List<LatLng> getCoordinates() {
        return getGeometryObject();
    }

    public String getType() {
        return getGeometryType();
    }

    public GeoJsonLineString(List<LatLng> list, List<Double> list2) {
        super(list);
        this.b = list2;
    }
}
