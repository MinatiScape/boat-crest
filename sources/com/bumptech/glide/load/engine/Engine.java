package com.bumptech.glide.load.engine;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pools;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCacheAdapter;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.load.engine.g;
import com.bumptech.glide.load.engine.l;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import java.util.Map;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class Engine implements i, MemoryCache.ResourceRemovedListener, l.a {
    public static final boolean i = Log.isLoggable("Engine", 2);

    /* renamed from: a  reason: collision with root package name */
    public final m f2342a;
    public final k b;
    public final MemoryCache c;
    public final b d;
    public final q e;
    public final c f;
    public final a g;
    public final com.bumptech.glide.load.engine.a h;

    /* loaded from: classes2.dex */
    public class LoadStatus {

        /* renamed from: a  reason: collision with root package name */
        public final h<?> f2343a;
        public final ResourceCallback b;

        public LoadStatus(ResourceCallback resourceCallback, h<?> hVar) {
            this.b = resourceCallback;
            this.f2343a = hVar;
        }

        public void cancel() {
            synchronized (Engine.this) {
                this.f2343a.o(this.b);
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final g.e f2344a;
        public final Pools.Pool<g<?>> b = FactoryPools.threadSafe(150, new C0211a());
        public int c;

        /* renamed from: com.bumptech.glide.load.engine.Engine$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public class C0211a implements FactoryPools.Factory<g<?>> {
            public C0211a() {
            }

            @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
            /* renamed from: a */
            public g<?> create() {
                a aVar = a.this;
                return new g<>(aVar.f2344a, aVar.b);
            }
        }

        public a(g.e eVar) {
            this.f2344a = eVar;
        }

        public <R> g<R> a(GlideContext glideContext, Object obj, j jVar, Key key, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, boolean z3, Options options, g.b<R> bVar) {
            g gVar = (g) Preconditions.checkNotNull(this.b.acquire());
            int i3 = this.c;
            this.c = i3 + 1;
            return gVar.j(glideContext, obj, jVar, key, i, i2, cls, cls2, priority, diskCacheStrategy, map, z, z2, z3, options, bVar, i3);
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final GlideExecutor f2346a;
        public final GlideExecutor b;
        public final GlideExecutor c;
        public final GlideExecutor d;
        public final i e;
        public final l.a f;
        public final Pools.Pool<h<?>> g = FactoryPools.threadSafe(150, new a());

        /* loaded from: classes2.dex */
        public class a implements FactoryPools.Factory<h<?>> {
            public a() {
            }

            @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
            /* renamed from: a */
            public h<?> create() {
                b bVar = b.this;
                return new h<>(bVar.f2346a, bVar.b, bVar.c, bVar.d, bVar.e, bVar.f, bVar.g);
            }
        }

        public b(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, i iVar, l.a aVar) {
            this.f2346a = glideExecutor;
            this.b = glideExecutor2;
            this.c = glideExecutor3;
            this.d = glideExecutor4;
            this.e = iVar;
            this.f = aVar;
        }

        public <R> h<R> a(Key key, boolean z, boolean z2, boolean z3, boolean z4) {
            return ((h) Preconditions.checkNotNull(this.g.acquire())).i(key, z, z2, z3, z4);
        }

        @VisibleForTesting
        public void b() {
            Executors.shutdownAndAwaitTermination(this.f2346a);
            Executors.shutdownAndAwaitTermination(this.b);
            Executors.shutdownAndAwaitTermination(this.c);
            Executors.shutdownAndAwaitTermination(this.d);
        }
    }

    /* loaded from: classes2.dex */
    public static class c implements g.e {

        /* renamed from: a  reason: collision with root package name */
        public final DiskCache.Factory f2348a;
        public volatile DiskCache b;

        public c(DiskCache.Factory factory) {
            this.f2348a = factory;
        }

        @Override // com.bumptech.glide.load.engine.g.e
        public DiskCache a() {
            if (this.b == null) {
                synchronized (this) {
                    if (this.b == null) {
                        this.b = this.f2348a.build();
                    }
                    if (this.b == null) {
                        this.b = new DiskCacheAdapter();
                    }
                }
            }
            return this.b;
        }

        @VisibleForTesting
        public synchronized void b() {
            if (this.b == null) {
                return;
            }
            this.b.clear();
        }
    }

    public Engine(MemoryCache memoryCache, DiskCache.Factory factory, GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, boolean z) {
        this(memoryCache, factory, glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, null, null, null, null, null, null, z);
    }

    public static void e(String str, long j, Key key) {
        Log.v("Engine", str + " in " + LogTime.getElapsedMillis(j) + "ms, key: " + key);
    }

    public final l<?> a(Key key) {
        Resource<?> remove = this.c.remove(key);
        if (remove == null) {
            return null;
        }
        if (remove instanceof l) {
            return (l) remove;
        }
        return new l<>(remove, true, true, key, this);
    }

    @Nullable
    public final l<?> b(Key key) {
        l<?> e = this.h.e(key);
        if (e != null) {
            e.a();
        }
        return e;
    }

    public final l<?> c(Key key) {
        l<?> a2 = a(key);
        if (a2 != null) {
            a2.a();
            this.h.a(key, a2);
        }
        return a2;
    }

    public void clearDiskCache() {
        this.f.a().clear();
    }

    @Nullable
    public final l<?> d(j jVar, boolean z, long j) {
        if (z) {
            l<?> b2 = b(jVar);
            if (b2 != null) {
                if (i) {
                    e("Loaded resource from active resources", j, jVar);
                }
                return b2;
            }
            l<?> c2 = c(jVar);
            if (c2 != null) {
                if (i) {
                    e("Loaded resource from cache", j, jVar);
                }
                return c2;
            }
            return null;
        }
        return null;
    }

    public final <R> LoadStatus f(GlideContext glideContext, Object obj, Key key, int i2, int i3, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, Options options, boolean z3, boolean z4, boolean z5, boolean z6, ResourceCallback resourceCallback, Executor executor, j jVar, long j) {
        h<?> a2 = this.f2342a.a(jVar, z6);
        if (a2 != null) {
            a2.b(resourceCallback, executor);
            if (i) {
                e("Added to existing load", j, jVar);
            }
            return new LoadStatus(resourceCallback, a2);
        }
        h<R> a3 = this.d.a(jVar, z3, z4, z5, z6);
        g<R> a4 = this.g.a(glideContext, obj, jVar, key, i2, i3, cls, cls2, priority, diskCacheStrategy, map, z, z2, z6, options, a3);
        this.f2342a.c(jVar, a3);
        a3.b(resourceCallback, executor);
        a3.p(a4);
        if (i) {
            e("Started new load", j, jVar);
        }
        return new LoadStatus(resourceCallback, a3);
    }

    public <R> LoadStatus load(GlideContext glideContext, Object obj, Key key, int i2, int i3, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, Options options, boolean z3, boolean z4, boolean z5, boolean z6, ResourceCallback resourceCallback, Executor executor) {
        long logTime = i ? LogTime.getLogTime() : 0L;
        j a2 = this.b.a(obj, key, i2, i3, map, cls, cls2, options);
        synchronized (this) {
            l<?> d = d(a2, z3, logTime);
            if (d == null) {
                return f(glideContext, obj, key, i2, i3, cls, cls2, priority, diskCacheStrategy, map, z, z2, options, z3, z4, z5, z6, resourceCallback, executor, a2, logTime);
            }
            resourceCallback.onResourceReady(d, DataSource.MEMORY_CACHE, false);
            return null;
        }
    }

    @Override // com.bumptech.glide.load.engine.i
    public synchronized void onEngineJobCancelled(h<?> hVar, Key key) {
        this.f2342a.d(key, hVar);
    }

    @Override // com.bumptech.glide.load.engine.i
    public synchronized void onEngineJobComplete(h<?> hVar, Key key, l<?> lVar) {
        if (lVar != null) {
            if (lVar.c()) {
                this.h.a(key, lVar);
            }
        }
        this.f2342a.d(key, hVar);
    }

    @Override // com.bumptech.glide.load.engine.l.a
    public void onResourceReleased(Key key, l<?> lVar) {
        this.h.d(key);
        if (lVar.c()) {
            this.c.put(key, lVar);
        } else {
            this.e.a(lVar, false);
        }
    }

    @Override // com.bumptech.glide.load.engine.cache.MemoryCache.ResourceRemovedListener
    public void onResourceRemoved(@NonNull Resource<?> resource) {
        this.e.a(resource, true);
    }

    public void release(Resource<?> resource) {
        if (resource instanceof l) {
            ((l) resource).d();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }

    @VisibleForTesting
    public void shutdown() {
        this.d.b();
        this.f.b();
        this.h.g();
    }

    @VisibleForTesting
    public Engine(MemoryCache memoryCache, DiskCache.Factory factory, GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, m mVar, k kVar, com.bumptech.glide.load.engine.a aVar, b bVar, a aVar2, q qVar, boolean z) {
        this.c = memoryCache;
        c cVar = new c(factory);
        this.f = cVar;
        com.bumptech.glide.load.engine.a aVar3 = aVar == null ? new com.bumptech.glide.load.engine.a(z) : aVar;
        this.h = aVar3;
        aVar3.f(this);
        this.b = kVar == null ? new k() : kVar;
        this.f2342a = mVar == null ? new m() : mVar;
        this.d = bVar == null ? new b(glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, this, this) : bVar;
        this.g = aVar2 == null ? new a(cVar) : aVar2;
        this.e = qVar == null ? new q() : qVar;
        memoryCache.setResourceRemovedListener(this);
    }
}
