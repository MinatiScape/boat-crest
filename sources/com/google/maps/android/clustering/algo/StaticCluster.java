package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.LinkedHashSet;
/* loaded from: classes10.dex */
public class StaticCluster<T extends ClusterItem> implements Cluster<T> {

    /* renamed from: a  reason: collision with root package name */
    public final LatLng f11537a;
    public final Collection<T> b = new LinkedHashSet();

    public StaticCluster(LatLng latLng) {
        this.f11537a = latLng;
    }

    public boolean add(T t) {
        return this.b.add(t);
    }

    public boolean equals(Object obj) {
        if (obj instanceof StaticCluster) {
            StaticCluster staticCluster = (StaticCluster) obj;
            return staticCluster.f11537a.equals(this.f11537a) && staticCluster.b.equals(this.b);
        }
        return false;
    }

    @Override // com.google.maps.android.clustering.Cluster
    public Collection<T> getItems() {
        return this.b;
    }

    @Override // com.google.maps.android.clustering.Cluster
    public LatLng getPosition() {
        return this.f11537a;
    }

    @Override // com.google.maps.android.clustering.Cluster
    public int getSize() {
        return this.b.size();
    }

    public int hashCode() {
        return this.f11537a.hashCode() + this.b.hashCode();
    }

    public boolean remove(T t) {
        return this.b.remove(t);
    }

    public String toString() {
        return "StaticCluster{mCenter=" + this.f11537a + ", mItems.size=" + this.b.size() + '}';
    }
}
