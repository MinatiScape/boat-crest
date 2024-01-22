package com.google.maps.android.clustering.algo;

import androidx.collection.LruCache;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes10.dex */
public class PreCachingAlgorithmDecorator<T extends ClusterItem> extends AbstractAlgorithm<T> {
    public final Algorithm<T> b;
    public final LruCache<Integer, Set<? extends Cluster<T>>> c = new LruCache<>(5);
    public final ReadWriteLock d = new ReentrantReadWriteLock();
    public final Executor e = Executors.newCachedThreadPool();

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public final int h;

        public a(int i) {
            this.h = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep((long) ((Math.random() * 500.0d) + 500.0d));
            } catch (InterruptedException unused) {
            }
            PreCachingAlgorithmDecorator.this.c(this.h);
        }
    }

    public PreCachingAlgorithmDecorator(Algorithm<T> algorithm) {
        this.b = algorithm;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean addItem(T t) {
        boolean addItem = this.b.addItem(t);
        if (addItem) {
            b();
        }
        return addItem;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean addItems(Collection<T> collection) {
        boolean addItems = this.b.addItems(collection);
        if (addItems) {
            b();
        }
        return addItems;
    }

    public final void b() {
        this.c.evictAll();
    }

    public final Set<? extends Cluster<T>> c(int i) {
        this.d.readLock().lock();
        Set<? extends Cluster<T>> set = this.c.get(Integer.valueOf(i));
        this.d.readLock().unlock();
        if (set == null) {
            this.d.writeLock().lock();
            set = this.c.get(Integer.valueOf(i));
            if (set == null) {
                set = this.b.getClusters(i);
                this.c.put(Integer.valueOf(i), set);
            }
            this.d.writeLock().unlock();
        }
        return set;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void clearItems() {
        this.b.clearItems();
        b();
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Set<? extends Cluster<T>> getClusters(float f) {
        int i = (int) f;
        Set<? extends Cluster<T>> c = c(i);
        int i2 = i + 1;
        if (this.c.get(Integer.valueOf(i2)) == null) {
            this.e.execute(new a(i2));
        }
        int i3 = i - 1;
        if (this.c.get(Integer.valueOf(i3)) == null) {
            this.e.execute(new a(i3));
        }
        return c;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Collection<T> getItems() {
        return this.b.getItems();
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public int getMaxDistanceBetweenClusteredItems() {
        return this.b.getMaxDistanceBetweenClusteredItems();
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean removeItem(T t) {
        boolean removeItem = this.b.removeItem(t);
        if (removeItem) {
            b();
        }
        return removeItem;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean removeItems(Collection<T> collection) {
        boolean removeItems = this.b.removeItems(collection);
        if (removeItems) {
            b();
        }
        return removeItems;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void setMaxDistanceBetweenClusteredItems(int i) {
        this.b.setMaxDistanceBetweenClusteredItems(i);
        b();
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean updateItem(T t) {
        boolean updateItem = this.b.updateItem(t);
        if (updateItem) {
            b();
        }
        return updateItem;
    }
}
