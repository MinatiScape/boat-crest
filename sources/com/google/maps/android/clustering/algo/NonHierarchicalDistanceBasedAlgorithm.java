package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
/* loaded from: classes10.dex */
public class NonHierarchicalDistanceBasedAlgorithm<T extends ClusterItem> extends AbstractAlgorithm<T> {
    public static final SphericalMercatorProjection e = new SphericalMercatorProjection(1.0d);
    public int b = 100;
    public final Collection<QuadItem<T>> c = new LinkedHashSet();
    public final PointQuadTree<QuadItem<T>> d = new PointQuadTree<>(0.0d, 1.0d, 0.0d, 1.0d);

    /* loaded from: classes10.dex */
    public static class QuadItem<T extends ClusterItem> implements PointQuadTree.Item, Cluster<T> {

        /* renamed from: a  reason: collision with root package name */
        public final T f11536a;
        public final Point b;
        public final LatLng c;
        public Set<T> d;

        public boolean equals(Object obj) {
            if (obj instanceof QuadItem) {
                return ((QuadItem) obj).f11536a.equals(this.f11536a);
            }
            return false;
        }

        @Override // com.google.maps.android.quadtree.PointQuadTree.Item
        public Point getPoint() {
            return this.b;
        }

        @Override // com.google.maps.android.clustering.Cluster
        public LatLng getPosition() {
            return this.c;
        }

        @Override // com.google.maps.android.clustering.Cluster
        public int getSize() {
            return 1;
        }

        public int hashCode() {
            return this.f11536a.hashCode();
        }

        public QuadItem(T t) {
            this.f11536a = t;
            LatLng position = t.getPosition();
            this.c = position;
            this.b = NonHierarchicalDistanceBasedAlgorithm.e.toPoint(position);
            this.d = Collections.singleton(t);
        }

        @Override // com.google.maps.android.clustering.Cluster
        public Set<T> getItems() {
            return this.d;
        }
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean addItem(T t) {
        boolean add;
        QuadItem<T> quadItem = new QuadItem<>(t);
        synchronized (this.d) {
            add = this.c.add(quadItem);
            if (add) {
                this.d.add(quadItem);
            }
        }
        return add;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean addItems(Collection<T> collection) {
        boolean z = false;
        for (T t : collection) {
            if (addItem(t)) {
                z = true;
            }
        }
        return z;
    }

    public final Bounds b(Point point, double d) {
        double d2 = d / 2.0d;
        double d3 = point.x;
        double d4 = d3 - d2;
        double d5 = d3 + d2;
        double d6 = point.y;
        return new Bounds(d4, d5, d6 - d2, d6 + d2);
    }

    public final double c(Point point, Point point2) {
        double d = point.x;
        double d2 = point2.x;
        double d3 = (d - d2) * (d - d2);
        double d4 = point.y;
        double d5 = point2.y;
        return d3 + ((d4 - d5) * (d4 - d5));
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void clearItems() {
        synchronized (this.d) {
            this.c.clear();
            this.d.clear();
        }
    }

    public Collection<QuadItem<T>> getClusteringItems(PointQuadTree<QuadItem<T>> pointQuadTree, float f) {
        return this.c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Set<? extends Cluster<T>> getClusters(float f) {
        double pow = (this.b / Math.pow(2.0d, (int) f)) / 256.0d;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        synchronized (this.d) {
            Iterator<QuadItem<T>> it = getClusteringItems(this.d, f).iterator();
            while (it.hasNext()) {
                QuadItem<T> next = it.next();
                if (!hashSet.contains(next)) {
                    Collection<QuadItem<T>> search = this.d.search(b(next.getPoint(), pow));
                    if (search.size() == 1) {
                        hashSet2.add(next);
                        hashSet.add(next);
                        hashMap.put(next, Double.valueOf(0.0d));
                    } else {
                        StaticCluster staticCluster = new StaticCluster(next.f11536a.getPosition());
                        hashSet2.add(staticCluster);
                        for (QuadItem<T> quadItem : search) {
                            Double d = (Double) hashMap.get(quadItem);
                            Iterator<QuadItem<T>> it2 = it;
                            double c = c(quadItem.getPoint(), next.getPoint());
                            if (d != null) {
                                if (d.doubleValue() < c) {
                                    it = it2;
                                } else {
                                    ((StaticCluster) hashMap2.get(quadItem)).remove(quadItem.f11536a);
                                }
                            }
                            hashMap.put(quadItem, Double.valueOf(c));
                            staticCluster.add(quadItem.f11536a);
                            hashMap2.put(quadItem, staticCluster);
                            it = it2;
                        }
                        hashSet.addAll(search);
                        it = it;
                    }
                }
            }
        }
        return hashSet2;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Collection<T> getItems() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        synchronized (this.d) {
            for (QuadItem<T> quadItem : this.c) {
                linkedHashSet.add(quadItem.f11536a);
            }
        }
        return linkedHashSet;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public int getMaxDistanceBetweenClusteredItems() {
        return this.b;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean removeItem(T t) {
        boolean remove;
        QuadItem<T> quadItem = new QuadItem<>(t);
        synchronized (this.d) {
            remove = this.c.remove(quadItem);
            if (remove) {
                this.d.remove(quadItem);
            }
        }
        return remove;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean removeItems(Collection<T> collection) {
        boolean z;
        synchronized (this.d) {
            z = false;
            for (T t : collection) {
                QuadItem<T> quadItem = new QuadItem<>(t);
                if (this.c.remove(quadItem)) {
                    this.d.remove(quadItem);
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void setMaxDistanceBetweenClusteredItems(int i) {
        this.b = i;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean updateItem(T t) {
        boolean removeItem;
        synchronized (this.d) {
            removeItem = removeItem(t);
            if (removeItem) {
                removeItem = addItem(t);
            }
        }
        return removeItem;
    }
}
