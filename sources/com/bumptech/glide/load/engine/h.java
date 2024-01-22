package com.bumptech.glide.load.engine;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pools;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.load.engine.g;
import com.bumptech.glide.load.engine.l;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes2.dex */
public class h<R> implements g.b<R>, FactoryPools.Poolable {
    public static final c G = new c();
    public GlideException A;
    public boolean B;
    public l<?> C;
    public g<R> D;
    public volatile boolean E;
    public boolean F;
    public final e h;
    public final StateVerifier i;
    public final l.a j;
    public final Pools.Pool<h<?>> k;
    public final c l;
    public final i m;
    public final GlideExecutor n;
    public final GlideExecutor o;
    public final GlideExecutor p;
    public final GlideExecutor q;
    public final AtomicInteger r;
    public Key s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public Resource<?> x;
    public DataSource y;
    public boolean z;

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public final ResourceCallback h;

        public a(ResourceCallback resourceCallback) {
            this.h = resourceCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.h.getLock()) {
                synchronized (h.this) {
                    if (h.this.h.b(this.h)) {
                        h.this.c(this.h);
                    }
                    h.this.f();
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Runnable {
        public final ResourceCallback h;

        public b(ResourceCallback resourceCallback) {
            this.h = resourceCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.h.getLock()) {
                synchronized (h.this) {
                    if (h.this.h.b(this.h)) {
                        h.this.C.a();
                        h.this.d(this.h);
                        h.this.o(this.h);
                    }
                    h.this.f();
                }
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class c {
        public <R> l<R> a(Resource<R> resource, boolean z, Key key, l.a aVar) {
            return new l<>(resource, z, true, key, aVar);
        }
    }

    /* loaded from: classes2.dex */
    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        public final ResourceCallback f2387a;
        public final Executor b;

        public d(ResourceCallback resourceCallback, Executor executor) {
            this.f2387a = resourceCallback;
            this.b = executor;
        }

        public boolean equals(Object obj) {
            if (obj instanceof d) {
                return this.f2387a.equals(((d) obj).f2387a);
            }
            return false;
        }

        public int hashCode() {
            return this.f2387a.hashCode();
        }
    }

    /* loaded from: classes2.dex */
    public static final class e implements Iterable<d> {
        public final List<d> h;

        public e() {
            this(new ArrayList(2));
        }

        public static d d(ResourceCallback resourceCallback) {
            return new d(resourceCallback, Executors.directExecutor());
        }

        public void a(ResourceCallback resourceCallback, Executor executor) {
            this.h.add(new d(resourceCallback, executor));
        }

        public boolean b(ResourceCallback resourceCallback) {
            return this.h.contains(d(resourceCallback));
        }

        public e c() {
            return new e(new ArrayList(this.h));
        }

        public void clear() {
            this.h.clear();
        }

        public void e(ResourceCallback resourceCallback) {
            this.h.remove(d(resourceCallback));
        }

        public boolean isEmpty() {
            return this.h.isEmpty();
        }

        @Override // java.lang.Iterable
        @NonNull
        public Iterator<d> iterator() {
            return this.h.iterator();
        }

        public int size() {
            return this.h.size();
        }

        public e(List<d> list) {
            this.h = list;
        }
    }

    public h(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, i iVar, l.a aVar, Pools.Pool<h<?>> pool) {
        this(glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, iVar, aVar, pool, G);
    }

    @Override // com.bumptech.glide.load.engine.g.b
    public void a(g<?> gVar) {
        g().execute(gVar);
    }

    public synchronized void b(ResourceCallback resourceCallback, Executor executor) {
        this.i.throwIfRecycled();
        this.h.a(resourceCallback, executor);
        boolean z = true;
        if (this.z) {
            h(1);
            executor.execute(new b(resourceCallback));
        } else if (this.B) {
            h(1);
            executor.execute(new a(resourceCallback));
        } else {
            if (this.E) {
                z = false;
            }
            Preconditions.checkArgument(z, "Cannot add callbacks to a cancelled EngineJob");
        }
    }

    @GuardedBy("this")
    public void c(ResourceCallback resourceCallback) {
        try {
            resourceCallback.onLoadFailed(this.A);
        } catch (Throwable th) {
            throw new com.bumptech.glide.load.engine.b(th);
        }
    }

    @GuardedBy("this")
    public void d(ResourceCallback resourceCallback) {
        try {
            resourceCallback.onResourceReady(this.C, this.y, this.F);
        } catch (Throwable th) {
            throw new com.bumptech.glide.load.engine.b(th);
        }
    }

    public void e() {
        if (j()) {
            return;
        }
        this.E = true;
        this.D.a();
        this.m.onEngineJobCancelled(this, this.s);
    }

    public void f() {
        l<?> lVar;
        synchronized (this) {
            this.i.throwIfRecycled();
            Preconditions.checkArgument(j(), "Not yet complete!");
            int decrementAndGet = this.r.decrementAndGet();
            Preconditions.checkArgument(decrementAndGet >= 0, "Can't decrement below 0");
            if (decrementAndGet == 0) {
                lVar = this.C;
                n();
            } else {
                lVar = null;
            }
        }
        if (lVar != null) {
            lVar.d();
        }
    }

    public final GlideExecutor g() {
        if (this.u) {
            return this.p;
        }
        return this.v ? this.q : this.o;
    }

    @Override // com.bumptech.glide.util.pool.FactoryPools.Poolable
    @NonNull
    public StateVerifier getVerifier() {
        return this.i;
    }

    public synchronized void h(int i) {
        l<?> lVar;
        Preconditions.checkArgument(j(), "Not yet complete!");
        if (this.r.getAndAdd(i) == 0 && (lVar = this.C) != null) {
            lVar.a();
        }
    }

    @VisibleForTesting
    public synchronized h<R> i(Key key, boolean z, boolean z2, boolean z3, boolean z4) {
        this.s = key;
        this.t = z;
        this.u = z2;
        this.v = z3;
        this.w = z4;
        return this;
    }

    public final boolean j() {
        return this.B || this.z || this.E;
    }

    public void k() {
        synchronized (this) {
            this.i.throwIfRecycled();
            if (this.E) {
                n();
            } else if (!this.h.isEmpty()) {
                if (!this.B) {
                    this.B = true;
                    Key key = this.s;
                    e c2 = this.h.c();
                    h(c2.size() + 1);
                    this.m.onEngineJobComplete(this, key, null);
                    Iterator<d> it = c2.iterator();
                    while (it.hasNext()) {
                        d next = it.next();
                        next.b.execute(new a(next.f2387a));
                    }
                    f();
                    return;
                }
                throw new IllegalStateException("Already failed once");
            } else {
                throw new IllegalStateException("Received an exception without any callbacks to notify");
            }
        }
    }

    public void l() {
        synchronized (this) {
            this.i.throwIfRecycled();
            if (this.E) {
                this.x.recycle();
                n();
            } else if (!this.h.isEmpty()) {
                if (!this.z) {
                    this.C = this.l.a(this.x, this.t, this.s, this.j);
                    this.z = true;
                    e c2 = this.h.c();
                    h(c2.size() + 1);
                    this.m.onEngineJobComplete(this, this.s, this.C);
                    Iterator<d> it = c2.iterator();
                    while (it.hasNext()) {
                        d next = it.next();
                        next.b.execute(new b(next.f2387a));
                    }
                    f();
                    return;
                }
                throw new IllegalStateException("Already have resource");
            } else {
                throw new IllegalStateException("Received a resource without any callbacks to notify");
            }
        }
    }

    public boolean m() {
        return this.w;
    }

    public final synchronized void n() {
        if (this.s != null) {
            this.h.clear();
            this.s = null;
            this.C = null;
            this.x = null;
            this.B = false;
            this.E = false;
            this.z = false;
            this.F = false;
            this.D.s(false);
            this.D = null;
            this.A = null;
            this.y = null;
            this.k.release(this);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public synchronized void o(ResourceCallback resourceCallback) {
        boolean z;
        this.i.throwIfRecycled();
        this.h.e(resourceCallback);
        if (this.h.isEmpty()) {
            e();
            if (!this.z && !this.B) {
                z = false;
                if (z && this.r.get() == 0) {
                    n();
                }
            }
            z = true;
            if (z) {
                n();
            }
        }
    }

    @Override // com.bumptech.glide.load.engine.g.b
    public void onLoadFailed(GlideException glideException) {
        synchronized (this) {
            this.A = glideException;
        }
        k();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.load.engine.g.b
    public void onResourceReady(Resource<R> resource, DataSource dataSource, boolean z) {
        synchronized (this) {
            this.x = resource;
            this.y = dataSource;
            this.F = z;
        }
        l();
    }

    public synchronized void p(g<R> gVar) {
        this.D = gVar;
        (gVar.z() ? this.n : g()).execute(gVar);
    }

    @VisibleForTesting
    public h(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, i iVar, l.a aVar, Pools.Pool<h<?>> pool, c cVar) {
        this.h = new e();
        this.i = StateVerifier.newInstance();
        this.r = new AtomicInteger();
        this.n = glideExecutor;
        this.o = glideExecutor2;
        this.p = glideExecutor3;
        this.q = glideExecutor4;
        this.m = iVar;
        this.j = aVar;
        this.k = pool;
        this.l = cVar;
    }
}
