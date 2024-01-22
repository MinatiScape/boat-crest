package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.DataPolygon;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class KmlPolygon implements DataPolygon<ArrayList<ArrayList<LatLng>>> {
    public static final String GEOMETRY_TYPE = "Polygon";

    /* renamed from: a  reason: collision with root package name */
    public final List<LatLng> f11555a;
    public final List<List<LatLng>> b;

    public KmlPolygon(List<LatLng> list, List<List<LatLng>> list2) {
        if (list != null) {
            this.f11555a = list;
            this.b = list2;
            return;
        }
        throw new IllegalArgumentException("Outer boundary coordinates cannot be null");
    }

    @Override // com.google.maps.android.data.Geometry
    public String getGeometryType() {
        return GEOMETRY_TYPE;
    }

    @Override // com.google.maps.android.data.DataPolygon
    public List<List<LatLng>> getInnerBoundaryCoordinates() {
        return this.b;
    }

    @Override // com.google.maps.android.data.DataPolygon
    public List<LatLng> getOuterBoundaryCoordinates() {
        return this.f11555a;
    }

    public String toString() {
        return GEOMETRY_TYPE + "{\n outer coordinates=" + this.f11555a + ",\n inner coordinates=" + this.b + "\n}\n";
    }

    @Override // com.google.maps.android.data.Geometry
    public List<List<LatLng>> getGeometryObject() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.f11555a);
        List<List<LatLng>> list = this.b;
        if (list != null) {
            arrayList.addAll(list);
        }
        return arrayList;
    }
}
