package com.mappls.sdk.maps.style.sources;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.style.expressions.Expression;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes11.dex */
public class CustomGeometrySource extends Source {
    public static final int THREAD_POOL_LIMIT = 4;
    public static final String THREAD_PREFIX = "CustomGeom";
    public static final AtomicInteger f = new AtomicInteger();

    /* renamed from: a  reason: collision with root package name */
    public final Lock f12850a;
    public ThreadPoolExecutor b;
    public GeometryTileProvider c;
    public final Map<c, b> d;
    public final Map<c, AtomicBoolean> e;

    /* loaded from: classes11.dex */
    public class a implements ThreadFactory {
        public final AtomicInteger h = new AtomicInteger();
        public final int i = CustomGeometrySource.f.getAndIncrement();

        public a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        @NonNull
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, String.format(Locale.US, "%s-%d-%d", CustomGeometrySource.THREAD_PREFIX, Integer.valueOf(this.i), Integer.valueOf(this.h.getAndIncrement())));
        }
    }

    /* loaded from: classes11.dex */
    public static class b implements Runnable {
        public final c h;
        public final GeometryTileProvider i;
        public final Map<c, b> j;
        public final Map<c, AtomicBoolean> k;
        @NonNull
        public final WeakReference<CustomGeometrySource> l;
        public final AtomicBoolean m;

        public b(c cVar, GeometryTileProvider geometryTileProvider, Map<c, b> map, Map<c, AtomicBoolean> map2, CustomGeometrySource customGeometrySource, AtomicBoolean atomicBoolean) {
            this.h = cVar;
            this.i = geometryTileProvider;
            this.j = map;
            this.k = map2;
            this.l = new WeakReference<>(customGeometrySource);
            this.m = atomicBoolean;
        }

        public final Boolean a() {
            return Boolean.valueOf(this.m.get());
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            return this.h.equals(((b) obj).h);
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.j) {
                synchronized (this.k) {
                    if (this.k.containsKey(this.h)) {
                        if (!this.j.containsKey(this.h)) {
                            this.j.put(this.h, this);
                        }
                        return;
                    }
                    this.k.put(this.h, this.m);
                    if (!a().booleanValue()) {
                        GeometryTileProvider geometryTileProvider = this.i;
                        c cVar = this.h;
                        FeatureCollection featuresForBounds = geometryTileProvider.getFeaturesForBounds(LatLngBounds.from(cVar.f12851a, cVar.b, cVar.c), this.h.f12851a);
                        CustomGeometrySource customGeometrySource = this.l.get();
                        if (!a().booleanValue() && customGeometrySource != null && featuresForBounds != null) {
                            customGeometrySource.e(this.h, featuresForBounds);
                        }
                    }
                    synchronized (this.j) {
                        synchronized (this.k) {
                            this.k.remove(this.h);
                            if (this.j.containsKey(this.h)) {
                                b bVar = this.j.get(this.h);
                                CustomGeometrySource customGeometrySource2 = this.l.get();
                                if (customGeometrySource2 != null && bVar != null) {
                                    customGeometrySource2.b.execute(bVar);
                                }
                                this.j.remove(this.h);
                            }
                        }
                    }
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public int f12851a;
        public int b;
        public int c;

        public c(int i, int i2, int i3) {
            this.f12851a = i;
            this.b = i2;
            this.c = i3;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && c.class == obj.getClass() && (obj instanceof c)) {
                c cVar = (c) obj;
                return this.f12851a == cVar.f12851a && this.b == cVar.b && this.c == cVar.c;
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(new int[]{this.f12851a, this.b, this.c});
        }
    }

    @UiThread
    public CustomGeometrySource(String str, GeometryTileProvider geometryTileProvider) {
        this(str, new CustomGeometrySourceOptions(), geometryTileProvider);
    }

    @Keep
    @WorkerThread
    private void cancelTile(int i, int i2, int i3) {
        c cVar = new c(i, i2, i3);
        synchronized (this.d) {
            synchronized (this.e) {
                AtomicBoolean atomicBoolean = this.e.get(cVar);
                if (atomicBoolean == null || !atomicBoolean.compareAndSet(false, true)) {
                    if (!this.b.getQueue().remove(new b(cVar, null, null, null, null, null))) {
                        this.d.remove(cVar);
                    }
                }
            }
        }
    }

    @Keep
    @WorkerThread
    private void fetchTile(int i, int i2, int i3) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        c cVar = new c(i, i2, i3);
        b bVar = new b(cVar, this.c, this.d, this.e, this, atomicBoolean);
        synchronized (this.d) {
            synchronized (this.e) {
                if (this.b.getQueue().contains(bVar)) {
                    this.b.remove(bVar);
                    d(bVar);
                } else if (this.e.containsKey(cVar)) {
                    this.d.put(cVar, bVar);
                } else {
                    d(bVar);
                }
            }
        }
    }

    @Keep
    private boolean isCancelled(int i, int i2, int i3) {
        return this.e.get(new c(i, i2, i3)).get();
    }

    @Keep
    private native void nativeInvalidateBounds(LatLngBounds latLngBounds);

    @Keep
    private native void nativeInvalidateTile(int i, int i2, int i3);

    @Keep
    private native void nativeSetTileData(int i, int i2, int i3, FeatureCollection featureCollection);

    @NonNull
    @Keep
    private native Feature[] querySourceFeatures(Object[] objArr);

    @Keep
    private void releaseThreads() {
        this.f12850a.lock();
        try {
            this.b.shutdownNow();
        } finally {
            this.f12850a.unlock();
        }
    }

    @Keep
    private void startThreads() {
        this.f12850a.lock();
        try {
            ThreadPoolExecutor threadPoolExecutor = this.b;
            if (threadPoolExecutor != null && !threadPoolExecutor.isShutdown()) {
                this.b.shutdownNow();
            }
            this.b = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new a());
        } finally {
            this.f12850a.unlock();
        }
    }

    public final void d(@NonNull b bVar) {
        this.f12850a.lock();
        try {
            ThreadPoolExecutor threadPoolExecutor = this.b;
            if (threadPoolExecutor != null && !threadPoolExecutor.isShutdown()) {
                this.b.execute(bVar);
            }
        } finally {
            this.f12850a.unlock();
        }
    }

    public final void e(c cVar, FeatureCollection featureCollection) {
        nativeSetTileData(cVar.f12851a, cVar.b, cVar.c, featureCollection);
    }

    @Keep
    public native void finalize() throws Throwable;

    @Keep
    public native void initialize(String str, Object obj);

    public void invalidateRegion(LatLngBounds latLngBounds) {
        nativeInvalidateBounds(latLngBounds);
    }

    public void invalidateTile(int i, int i2, int i3) {
        nativeInvalidateTile(i, i2, i3);
    }

    @NonNull
    public List<Feature> querySourceFeatures(@Nullable Expression expression) {
        checkThread();
        Feature[] querySourceFeatures = querySourceFeatures(expression != null ? expression.toArray() : null);
        return querySourceFeatures != null ? Arrays.asList(querySourceFeatures) : new ArrayList();
    }

    public void setTileData(int i, int i2, int i3, FeatureCollection featureCollection) {
        nativeSetTileData(i, i2, i3, featureCollection);
    }

    @UiThread
    public CustomGeometrySource(String str, CustomGeometrySourceOptions customGeometrySourceOptions, GeometryTileProvider geometryTileProvider) {
        this.f12850a = new ReentrantLock();
        this.d = new HashMap();
        this.e = new HashMap();
        this.c = geometryTileProvider;
        initialize(str, customGeometrySourceOptions);
    }
}
