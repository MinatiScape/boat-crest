package com.google.maps.android.data;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class MultiGeometry implements Geometry {

    /* renamed from: a  reason: collision with root package name */
    public String f11547a = "MultiGeometry";
    public List<Geometry> b;

    public MultiGeometry(List<? extends Geometry> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (Geometry geometry : list) {
                arrayList.add(geometry);
            }
            this.b = arrayList;
            return;
        }
        throw new IllegalArgumentException("Geometries cannot be null");
    }

    @Override // com.google.maps.android.data.Geometry
    public String getGeometryType() {
        return this.f11547a;
    }

    public void setGeometryType(String str) {
        this.f11547a = str;
    }

    public String toString() {
        String str = this.f11547a.equals("MultiPoint") ? "LineStrings=" : "Geometries=";
        if (this.f11547a.equals("MultiLineString")) {
            str = "points=";
        }
        if (this.f11547a.equals("MultiPolygon")) {
            str = "Polygons=";
        }
        StringBuilder sb = new StringBuilder(getGeometryType());
        sb.append("{");
        sb.append("\n " + str);
        sb.append(getGeometryObject());
        sb.append("\n}\n");
        return sb.toString();
    }

    @Override // com.google.maps.android.data.Geometry
    public List<Geometry> getGeometryObject() {
        return this.b;
    }
}
