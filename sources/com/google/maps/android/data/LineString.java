package com.google.maps.android.data;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;
/* loaded from: classes10.dex */
public class LineString implements Geometry<List<LatLng>> {

    /* renamed from: a  reason: collision with root package name */
    public final List<LatLng> f11546a;

    public LineString(List<LatLng> list) {
        if (list != null) {
            this.f11546a = list;
            return;
        }
        throw new IllegalArgumentException("Coordinates cannot be null");
    }

    @Override // com.google.maps.android.data.Geometry
    public String getGeometryType() {
        return "LineString";
    }

    public String toString() {
        return "LineString{\n coordinates=" + this.f11546a + "\n}\n";
    }

    @Override // com.google.maps.android.data.Geometry
    public List<LatLng> getGeometryObject() {
        return this.f11546a;
    }
}
