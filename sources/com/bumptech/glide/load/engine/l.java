package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;
/* loaded from: classes2.dex */
public class l<Z> implements Resource<Z> {
    public final boolean h;
    public final boolean i;
    public final Resource<Z> j;
    public final a k;
    public final Key l;
    public int m;
    public boolean n;

    /* loaded from: classes2.dex */
    public interface a {
        void onResourceReleased(Key key, l<?> lVar);
    }

    public l(Resource<Z> resource, boolean z, boolean z2, Key key, a aVar) {
        this.j = (Resource) Preconditions.checkNotNull(resource);
        this.h = z;
        this.i = z2;
        this.l = key;
        this.k = (a) Preconditions.checkNotNull(aVar);
    }

    public synchronized void a() {
        if (!this.n) {
            this.m++;
        } else {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
    }

    public Resource<Z> b() {
        return this.j;
    }

    public boolean c() {
        return this.h;
    }

    public void d() {
        boolean z;
        synchronized (this) {
            int i = this.m;
            if (i > 0) {
                z = true;
                int i2 = i - 1;
                this.m = i2;
                if (i2 != 0) {
                    z = false;
                }
            } else {
                throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
            }
        }
        if (z) {
            this.k.onResourceReleased(this.l, this);
        }
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public Z get() {
        return this.j.get();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public Class<Z> getResourceClass() {
        return this.j.getResourceClass();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public int getSize() {
        return this.j.getSize();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public synchronized void recycle() {
        if (this.m <= 0) {
            if (!this.n) {
                this.n = true;
                if (this.i) {
                    this.j.recycle();
                }
            } else {
                throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
            }
        } else {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        }
    }

    public synchronized String toString() {
        return "EngineResource{isMemoryCacheable=" + this.h + ", listener=" + this.k + ", key=" + this.l + ", acquired=" + this.m + ", isRecycled=" + this.n + ", resource=" + this.j + '}';
    }
}
