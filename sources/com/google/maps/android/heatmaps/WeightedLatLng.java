package com.google.maps.android.heatmaps;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
/* loaded from: classes10.dex */
public class WeightedLatLng implements PointQuadTree.Item {
    public static final double DEFAULT_INTENSITY = 1.0d;
    public static final SphericalMercatorProjection c = new SphericalMercatorProjection(1.0d);

    /* renamed from: a  reason: collision with root package name */
    public Point f11564a;
    public double b;

    public WeightedLatLng(LatLng latLng, double d) {
        this.f11564a = c.toPoint(latLng);
        if (d >= 0.0d) {
            this.b = d;
        } else {
            this.b = 1.0d;
        }
    }

    public double getIntensity() {
        return this.b;
    }

    @Override // com.google.maps.android.quadtree.PointQuadTree.Item
    public Point getPoint() {
        return this.f11564a;
    }

    public WeightedLatLng(LatLng latLng) {
        this(latLng, 1.0d);
    }
}
