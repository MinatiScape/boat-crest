package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.projection.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes10.dex */
public class NonHierarchicalViewBasedAlgorithm<T extends ClusterItem> extends NonHierarchicalDistanceBasedAlgorithm<T> implements ScreenBasedAlgorithm<T> {
    public static final SphericalMercatorProjection i = new SphericalMercatorProjection(1.0d);
    public int f;
    public int g;
    public LatLng h;

    public NonHierarchicalViewBasedAlgorithm(int i2, int i3) {
        this.f = i2;
        this.g = i3;
    }

    public final Bounds d(float f) {
        LatLng latLng = this.h;
        if (latLng == null) {
            return new Bounds(0.0d, 0.0d, 0.0d, 0.0d);
        }
        Point point = i.toPoint(latLng);
        double d = f;
        double pow = ((this.f / Math.pow(2.0d, d)) / 256.0d) / 2.0d;
        double pow2 = ((this.g / Math.pow(2.0d, d)) / 256.0d) / 2.0d;
        double d2 = point.x;
        double d3 = point.y;
        return new Bounds(d2 - pow, d2 + pow, d3 - pow2, d3 + pow2);
    }

    @Override // com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm
    public Collection<NonHierarchicalDistanceBasedAlgorithm.QuadItem<T>> getClusteringItems(PointQuadTree<NonHierarchicalDistanceBasedAlgorithm.QuadItem<T>> pointQuadTree, float f) {
        Bounds d = d(f);
        ArrayList arrayList = new ArrayList();
        double d2 = d.minX;
        if (d2 < 0.0d) {
            arrayList.addAll(pointQuadTree.search(new Bounds(d2 + 1.0d, 1.0d, d.minY, d.maxY)));
            d = new Bounds(0.0d, d.maxX, d.minY, d.maxY);
        }
        double d3 = d.maxX;
        if (d3 > 1.0d) {
            arrayList.addAll(pointQuadTree.search(new Bounds(0.0d, d3 - 1.0d, d.minY, d.maxY)));
            d = new Bounds(d.minX, 1.0d, d.minY, d.maxY);
        }
        arrayList.addAll(pointQuadTree.search(d));
        return arrayList;
    }

    @Override // com.google.maps.android.clustering.algo.ScreenBasedAlgorithm
    public void onCameraChange(CameraPosition cameraPosition) {
        this.h = cameraPosition.target;
    }

    @Override // com.google.maps.android.clustering.algo.ScreenBasedAlgorithm
    public boolean shouldReclusterOnMapMovement() {
        return true;
    }

    public void updateViewSize(int i2, int i3) {
        this.f = i2;
        this.g = i3;
    }
}
