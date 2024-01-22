package com.google.maps.android.clustering.algo;

import com.google.maps.android.clustering.ClusterItem;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes10.dex */
public abstract class AbstractAlgorithm<T extends ClusterItem> implements Algorithm<T> {

    /* renamed from: a  reason: collision with root package name */
    public final ReadWriteLock f11535a = new ReentrantReadWriteLock();

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void lock() {
        this.f11535a.writeLock().lock();
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void unlock() {
        this.f11535a.writeLock().unlock();
    }
}
