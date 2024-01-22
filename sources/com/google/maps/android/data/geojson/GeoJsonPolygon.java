package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.DataPolygon;
import com.google.maps.android.data.kml.KmlPolygon;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class GeoJsonPolygon implements DataPolygon {

    /* renamed from: a  reason: collision with root package name */
    public final List<? extends List<LatLng>> f11552a;

    public GeoJsonPolygon(List<? extends List<LatLng>> list) {
        if (list != null) {
            this.f11552a = list;
            return;
        }
        throw new IllegalArgumentException("Coordinates cannot be null");
    }

    public List<? extends List<LatLng>> getCoordinates() {
        return this.f11552a;
    }

    @Override // com.google.maps.android.data.Geometry
    public String getGeometryType() {
        return getType();
    }

    public String getType() {
        return KmlPolygon.GEOMETRY_TYPE;
    }

    public String toString() {
        return KmlPolygon.GEOMETRY_TYPE + "{\n coordinates=" + this.f11552a + "\n}\n";
    }

    @Override // com.google.maps.android.data.Geometry
    public List<? extends List<LatLng>> getGeometryObject() {
        return getCoordinates();
    }

    @Override // com.google.maps.android.data.DataPolygon
    public ArrayList<ArrayList<LatLng>> getInnerBoundaryCoordinates() {
        ArrayList<ArrayList<LatLng>> arrayList = new ArrayList<>();
        for (int i = 1; i < getCoordinates().size(); i++) {
            arrayList.add((ArrayList) getCoordinates().get(i));
        }
        return arrayList;
    }

    @Override // com.google.maps.android.data.DataPolygon
    public ArrayList<LatLng> getOuterBoundaryCoordinates() {
        return (ArrayList) getCoordinates().get(0);
    }
}
