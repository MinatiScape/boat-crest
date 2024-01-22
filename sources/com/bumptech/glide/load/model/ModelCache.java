package com.bumptech.glide.load.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.util.Queue;
/* loaded from: classes2.dex */
public class ModelCache<A, B> {

    /* renamed from: a  reason: collision with root package name */
    public final LruCache<b<A>, B> f2410a;

    /* loaded from: classes2.dex */
    public class a extends LruCache<b<A>, B> {
        public a(ModelCache modelCache, long j) {
            super(j);
        }

        @Override // com.bumptech.glide.util.LruCache
        /* renamed from: b */
        public void onItemEvicted(@NonNull b<A> bVar, @Nullable B b) {
            bVar.c();
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static final class b<A> {
        public static final Queue<b<?>> d = Util.createQueue(0);

        /* renamed from: a  reason: collision with root package name */
        public int f2411a;
        public int b;
        public A c;

        public static <A> b<A> a(A a2, int i, int i2) {
            b<A> bVar;
            Queue<b<?>> queue = d;
            synchronized (queue) {
                bVar = (b<A>) queue.poll();
            }
            if (bVar == null) {
                bVar = new b<>();
            }
            bVar.b(a2, i, i2);
            return bVar;
        }

        public final void b(A a2, int i, int i2) {
            this.c = a2;
            this.b = i;
            this.f2411a = i2;
        }

        public void c() {
            Queue<b<?>> queue = d;
            synchronized (queue) {
                queue.offer(this);
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                return this.b == bVar.b && this.f2411a == bVar.f2411a && this.c.equals(bVar.c);
            }
            return false;
        }

        public int hashCode() {
            return (((this.f2411a * 31) + this.b) * 31) + this.c.hashCode();
        }
    }

    public ModelCache() {
        this(250L);
    }

    public void clear() {
        this.f2410a.clearMemory();
    }

    @Nullable
    public B get(A a2, int i, int i2) {
        b<A> a3 = b.a(a2, i, i2);
        B b2 = this.f2410a.get(a3);
        a3.c();
        return b2;
    }

    public void put(A a2, int i, int i2, B b2) {
        this.f2410a.put(b.a(a2, i, i2), b2);
    }

    public ModelCache(long j) {
        this.f2410a = new a(this, j);
    }
}
