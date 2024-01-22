package com.bumptech.glide.util.pool;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public final class FactoryPools {

    /* renamed from: a  reason: collision with root package name */
    public static final Resetter<Object> f2561a = new a();

    /* loaded from: classes2.dex */
    public interface Factory<T> {
        T create();
    }

    /* loaded from: classes2.dex */
    public interface Poolable {
        @NonNull
        StateVerifier getVerifier();
    }

    /* loaded from: classes2.dex */
    public interface Resetter<T> {
        void reset(@NonNull T t);
    }

    /* loaded from: classes2.dex */
    public class a implements Resetter<Object> {
        @Override // com.bumptech.glide.util.pool.FactoryPools.Resetter
        public void reset(@NonNull Object obj) {
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes2.dex */
    public class b<T> implements Factory<List<T>> {
        @Override // com.bumptech.glide.util.pool.FactoryPools.Factory
        @NonNull
        /* renamed from: a */
        public List<T> create() {
            return new ArrayList();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes2.dex */
    public class c<T> implements Resetter<List<T>> {
        @Override // com.bumptech.glide.util.pool.FactoryPools.Resetter
        /* renamed from: a */
        public void reset(@NonNull List<T> list) {
            list.clear();
        }
    }

    /* loaded from: classes2.dex */
    public static final class d<T> implements Pools.Pool<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Factory<T> f2562a;
        public final Resetter<T> b;
        public final Pools.Pool<T> c;

        public d(@NonNull Pools.Pool<T> pool, @NonNull Factory<T> factory, @NonNull Resetter<T> resetter) {
            this.c = pool;
            this.f2562a = factory;
            this.b = resetter;
        }

        @Override // androidx.core.util.Pools.Pool
        public T acquire() {
            T acquire = this.c.acquire();
            if (acquire == null) {
                acquire = this.f2562a.create();
                if (Log.isLoggable("FactoryPools", 2)) {
                    Log.v("FactoryPools", "Created new " + acquire.getClass());
                }
            }
            if (acquire instanceof Poolable) {
                ((Poolable) acquire).getVerifier().a(false);
            }
            return acquire;
        }

        @Override // androidx.core.util.Pools.Pool
        public boolean release(@NonNull T t) {
            if (t instanceof Poolable) {
                ((Poolable) t).getVerifier().a(true);
            }
            this.b.reset(t);
            return this.c.release(t);
        }
    }

    @NonNull
    public static <T extends Poolable> Pools.Pool<T> a(@NonNull Pools.Pool<T> pool, @NonNull Factory<T> factory) {
        return b(pool, factory, c());
    }

    @NonNull
    public static <T> Pools.Pool<T> b(@NonNull Pools.Pool<T> pool, @NonNull Factory<T> factory, @NonNull Resetter<T> resetter) {
        return new d(pool, factory, resetter);
    }

    @NonNull
    public static <T> Resetter<T> c() {
        return (Resetter<T>) f2561a;
    }

    @NonNull
    public static <T extends Poolable> Pools.Pool<T> simple(int i, @NonNull Factory<T> factory) {
        return a(new Pools.SimplePool(i), factory);
    }

    @NonNull
    public static <T extends Poolable> Pools.Pool<T> threadSafe(int i, @NonNull Factory<T> factory) {
        return a(new Pools.SynchronizedPool(i), factory);
    }

    @NonNull
    public static <T> Pools.Pool<List<T>> threadSafeList() {
        return threadSafeList(20);
    }

    @NonNull
    public static <T> Pools.Pool<List<T>> threadSafeList(int i) {
        return b(new Pools.SynchronizedPool(i), new b(), new c());
    }
}
