package com.google.maps.android.projection;

import com.google.android.gms.maps.model.LatLng;
/* loaded from: classes10.dex */
public class SphericalMercatorProjection {

    /* renamed from: a  reason: collision with root package name */
    public final double f11565a;

    public SphericalMercatorProjection(double d) {
        this.f11565a = d;
    }

    public LatLng toLatLng(com.google.maps.android.geometry.Point point) {
        double d = point.x;
        double d2 = this.f11565a;
        return new LatLng(90.0d - Math.toDegrees(Math.atan(Math.exp(((-(0.5d - (point.y / d2))) * 2.0d) * 3.141592653589793d)) * 2.0d), ((d / d2) - 0.5d) * 360.0d);
    }

    public Point toPoint(LatLng latLng) {
        double sin = Math.sin(Math.toRadians(latLng.latitude));
        double d = this.f11565a;
        return new Point(((latLng.longitude / 360.0d) + 0.5d) * d, (((Math.log((sin + 1.0d) / (1.0d - sin)) * 0.5d) / (-6.283185307179586d)) + 0.5d) * d);
    }
}
