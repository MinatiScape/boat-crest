package com.google.maps.android.clustering;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.algo.Algorithm;
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm;
import com.google.maps.android.clustering.algo.PreCachingAlgorithmDecorator;
import com.google.maps.android.clustering.algo.ScreenBasedAlgorithm;
import com.google.maps.android.clustering.algo.ScreenBasedAlgorithmAdapter;
import com.google.maps.android.clustering.view.ClusterRenderer;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.collections.MarkerManager;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes10.dex */
public class ClusterManager<T extends ClusterItem> implements GoogleMap.OnCameraIdleListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {
    public final MarkerManager h;
    public final MarkerManager.Collection i;
    public final MarkerManager.Collection j;
    public ScreenBasedAlgorithm<T> k;
    public ClusterRenderer<T> l;
    public GoogleMap m;
    public CameraPosition n;
    public ClusterManager<T>.b o;
    public final ReadWriteLock p;
    public OnClusterItemClickListener<T> q;
    public OnClusterInfoWindowClickListener<T> r;
    public OnClusterInfoWindowLongClickListener<T> s;
    public OnClusterItemInfoWindowClickListener<T> t;
    public OnClusterItemInfoWindowLongClickListener<T> u;
    public OnClusterClickListener<T> v;

    /* loaded from: classes10.dex */
    public interface OnClusterClickListener<T extends ClusterItem> {
        boolean onClusterClick(Cluster<T> cluster);
    }

    /* loaded from: classes10.dex */
    public interface OnClusterInfoWindowClickListener<T extends ClusterItem> {
        void onClusterInfoWindowClick(Cluster<T> cluster);
    }

    /* loaded from: classes10.dex */
    public interface OnClusterInfoWindowLongClickListener<T extends ClusterItem> {
        void onClusterInfoWindowLongClick(Cluster<T> cluster);
    }

    /* loaded from: classes10.dex */
    public interface OnClusterItemClickListener<T extends ClusterItem> {
        boolean onClusterItemClick(T t);
    }

    /* loaded from: classes10.dex */
    public interface OnClusterItemInfoWindowClickListener<T extends ClusterItem> {
        void onClusterItemInfoWindowClick(T t);
    }

    /* loaded from: classes10.dex */
    public interface OnClusterItemInfoWindowLongClickListener<T extends ClusterItem> {
        void onClusterItemInfoWindowLongClick(T t);
    }

    /* loaded from: classes10.dex */
    public class b extends AsyncTask<Float, Void, Set<? extends Cluster<T>>> {
        public b() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Set<? extends Cluster<T>> doInBackground(Float... fArr) {
            Algorithm<T> algorithm = ClusterManager.this.getAlgorithm();
            algorithm.lock();
            try {
                return algorithm.getClusters(fArr[0].floatValue());
            } finally {
                algorithm.unlock();
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onPostExecute(Set<? extends Cluster<T>> set) {
            ClusterManager.this.l.onClustersChanged(set);
        }
    }

    public ClusterManager(Context context, GoogleMap googleMap) {
        this(context, googleMap, new MarkerManager(googleMap));
    }

    public boolean addItem(T t) {
        Algorithm<T> algorithm = getAlgorithm();
        algorithm.lock();
        try {
            return algorithm.addItem(t);
        } finally {
            algorithm.unlock();
        }
    }

    public boolean addItems(Collection<T> collection) {
        Algorithm<T> algorithm = getAlgorithm();
        algorithm.lock();
        try {
            return algorithm.addItems(collection);
        } finally {
            algorithm.unlock();
        }
    }

    public void clearItems() {
        Algorithm<T> algorithm = getAlgorithm();
        algorithm.lock();
        try {
            algorithm.clearItems();
        } finally {
            algorithm.unlock();
        }
    }

    public void cluster() {
        this.p.writeLock().lock();
        try {
            this.o.cancel(true);
            ClusterManager<T>.b bVar = new b();
            this.o = bVar;
            bVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Float.valueOf(this.m.getCameraPosition().zoom));
        } finally {
            this.p.writeLock().unlock();
        }
    }

    public Algorithm<T> getAlgorithm() {
        return this.k;
    }

    public MarkerManager.Collection getClusterMarkerCollection() {
        return this.j;
    }

    public MarkerManager.Collection getMarkerCollection() {
        return this.i;
    }

    public MarkerManager getMarkerManager() {
        return this.h;
    }

    public ClusterRenderer<T> getRenderer() {
        return this.l;
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnCameraIdleListener
    public void onCameraIdle() {
        ClusterRenderer<T> clusterRenderer = this.l;
        if (clusterRenderer instanceof GoogleMap.OnCameraIdleListener) {
            ((GoogleMap.OnCameraIdleListener) clusterRenderer).onCameraIdle();
        }
        this.k.onCameraChange(this.m.getCameraPosition());
        if (this.k.shouldReclusterOnMapMovement()) {
            cluster();
            return;
        }
        CameraPosition cameraPosition = this.n;
        if (cameraPosition == null || cameraPosition.zoom != this.m.getCameraPosition().zoom) {
            this.n = this.m.getCameraPosition();
            cluster();
        }
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
    public void onInfoWindowClick(Marker marker) {
        getMarkerManager().onInfoWindowClick(marker);
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
    public boolean onMarkerClick(Marker marker) {
        return getMarkerManager().onMarkerClick(marker);
    }

    public boolean removeItem(T t) {
        Algorithm<T> algorithm = getAlgorithm();
        algorithm.lock();
        try {
            return algorithm.removeItem(t);
        } finally {
            algorithm.unlock();
        }
    }

    public boolean removeItems(Collection<T> collection) {
        Algorithm<T> algorithm = getAlgorithm();
        algorithm.lock();
        try {
            return algorithm.removeItems(collection);
        } finally {
            algorithm.unlock();
        }
    }

    public void setAlgorithm(Algorithm<T> algorithm) {
        if (algorithm instanceof ScreenBasedAlgorithm) {
            setAlgorithm((ScreenBasedAlgorithm) ((ScreenBasedAlgorithm) algorithm));
        } else {
            setAlgorithm((ScreenBasedAlgorithm) new ScreenBasedAlgorithmAdapter(algorithm));
        }
    }

    public void setAnimation(boolean z) {
        this.l.setAnimation(z);
    }

    public void setOnClusterClickListener(OnClusterClickListener<T> onClusterClickListener) {
        this.v = onClusterClickListener;
        this.l.setOnClusterClickListener(onClusterClickListener);
    }

    public void setOnClusterInfoWindowClickListener(OnClusterInfoWindowClickListener<T> onClusterInfoWindowClickListener) {
        this.r = onClusterInfoWindowClickListener;
        this.l.setOnClusterInfoWindowClickListener(onClusterInfoWindowClickListener);
    }

    public void setOnClusterInfoWindowLongClickListener(OnClusterInfoWindowLongClickListener<T> onClusterInfoWindowLongClickListener) {
        this.s = onClusterInfoWindowLongClickListener;
        this.l.setOnClusterInfoWindowLongClickListener(onClusterInfoWindowLongClickListener);
    }

    public void setOnClusterItemClickListener(OnClusterItemClickListener<T> onClusterItemClickListener) {
        this.q = onClusterItemClickListener;
        this.l.setOnClusterItemClickListener(onClusterItemClickListener);
    }

    public void setOnClusterItemInfoWindowClickListener(OnClusterItemInfoWindowClickListener<T> onClusterItemInfoWindowClickListener) {
        this.t = onClusterItemInfoWindowClickListener;
        this.l.setOnClusterItemInfoWindowClickListener(onClusterItemInfoWindowClickListener);
    }

    public void setOnClusterItemInfoWindowLongClickListener(OnClusterItemInfoWindowLongClickListener<T> onClusterItemInfoWindowLongClickListener) {
        this.u = onClusterItemInfoWindowLongClickListener;
        this.l.setOnClusterItemInfoWindowLongClickListener(onClusterItemInfoWindowLongClickListener);
    }

    public void setRenderer(ClusterRenderer<T> clusterRenderer) {
        this.l.setOnClusterClickListener(null);
        this.l.setOnClusterItemClickListener(null);
        this.j.clear();
        this.i.clear();
        this.l.onRemove();
        this.l = clusterRenderer;
        clusterRenderer.onAdd();
        this.l.setOnClusterClickListener(this.v);
        this.l.setOnClusterInfoWindowClickListener(this.r);
        this.l.setOnClusterInfoWindowLongClickListener(this.s);
        this.l.setOnClusterItemClickListener(this.q);
        this.l.setOnClusterItemInfoWindowClickListener(this.t);
        this.l.setOnClusterItemInfoWindowLongClickListener(this.u);
        cluster();
    }

    public boolean updateItem(T t) {
        Algorithm<T> algorithm = getAlgorithm();
        algorithm.lock();
        try {
            return algorithm.updateItem(t);
        } finally {
            algorithm.unlock();
        }
    }

    public ClusterManager(Context context, GoogleMap googleMap, MarkerManager markerManager) {
        this.p = new ReentrantReadWriteLock();
        this.m = googleMap;
        this.h = markerManager;
        this.j = markerManager.newCollection();
        this.i = markerManager.newCollection();
        this.l = new DefaultClusterRenderer(context, googleMap, this);
        this.k = new ScreenBasedAlgorithmAdapter(new PreCachingAlgorithmDecorator(new NonHierarchicalDistanceBasedAlgorithm()));
        this.o = new b();
        this.l.onAdd();
    }

    public void setAlgorithm(ScreenBasedAlgorithm<T> screenBasedAlgorithm) {
        screenBasedAlgorithm.lock();
        try {
            Algorithm<T> algorithm = getAlgorithm();
            this.k = screenBasedAlgorithm;
            if (algorithm != null) {
                algorithm.lock();
                screenBasedAlgorithm.addItems(algorithm.getItems());
                algorithm.unlock();
            }
            screenBasedAlgorithm.unlock();
            if (this.k.shouldReclusterOnMapMovement()) {
                this.k.onCameraChange(this.m.getCameraPosition());
            }
            cluster();
        } catch (Throwable th) {
            screenBasedAlgorithm.unlock();
            throw th;
        }
    }
}
