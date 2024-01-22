package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
/* loaded from: classes2.dex */
public final class n<Z> implements Resource<Z>, FactoryPools.Poolable {
    public static final Pools.Pool<n<?>> l = FactoryPools.threadSafe(20, new a());
    public final StateVerifier h = StateVerifier.newInstance();
    public Resource<Z> i;
    public boolean j;
    public boolean k;

    /* loaded from: classes2.dex */
    public class a implements FactoryPools.Factory<n<?>> {
        @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
        /* renamed from: a */
        public n<?> create() {
            return new n<>();
        }
    }

    @NonNull
    public static <Z> n<Z> b(Resource<Z> resource) {
        n<Z> nVar = (n) Preconditions.checkNotNull(l.acquire());
        nVar.a(resource);
        return nVar;
    }

    public final void a(Resource<Z> resource) {
        this.k = false;
        this.j = true;
        this.i = resource;
    }

    public final void c() {
        this.i = null;
        l.release(this);
    }

    public synchronized void d() {
        this.h.throwIfRecycled();
        if (this.j) {
            this.j = false;
            if (this.k) {
                recycle();
            }
        } else {
            throw new IllegalStateException("Already unlocked");
        }
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public Z get() {
        return this.i.get();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public Class<Z> getResourceClass() {
        return this.i.getResourceClass();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public int getSize() {
        return this.i.getSize();
    }

    @Override // com.bumptech.glide.util.pool.FactoryPools.Poolable
    @NonNull
    public StateVerifier getVerifier() {
        return this.h;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public synchronized void recycle() {
        this.h.throwIfRecycled();
        this.k = true;
        if (!this.j) {
            this.i.recycle();
            c();
        }
    }
}
