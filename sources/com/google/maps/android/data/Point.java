package com.google.maps.android.data;

import com.google.android.gms.maps.model.LatLng;
/* loaded from: classes10.dex */
public class Point implements Geometry {

    /* renamed from: a  reason: collision with root package name */
    public final LatLng f11548a;

    public Point(LatLng latLng) {
        if (latLng != null) {
            this.f11548a = latLng;
            return;
        }
        throw new IllegalArgumentException("Coordinates cannot be null");
    }

    @Override // com.google.maps.android.data.Geometry
    public String getGeometryType() {
        return "Point";
    }

    public String toString() {
        return "Point{\n coordinates=" + this.f11548a + "\n}\n";
    }

    @Override // com.google.maps.android.data.Geometry
    public LatLng getGeometryObject() {
        return this.f11548a;
    }
}
