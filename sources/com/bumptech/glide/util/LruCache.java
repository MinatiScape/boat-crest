package com.bumptech.glide.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class LruCache<T, Y> {

    /* renamed from: a  reason: collision with root package name */
    public final Map<T, a<Y>> f2555a = new LinkedHashMap(100, 0.75f, true);
    public final long b;
    public long c;
    public long d;

    /* loaded from: classes2.dex */
    public static final class a<Y> {

        /* renamed from: a  reason: collision with root package name */
        public final Y f2556a;
        public final int b;

        public a(Y y, int i) {
            this.f2556a = y;
            this.b = i;
        }
    }

    public LruCache(long j) {
        this.b = j;
        this.c = j;
    }

    public final void a() {
        trimToSize(this.c);
    }

    public void clearMemory() {
        trimToSize(0L);
    }

    public synchronized boolean contains(@NonNull T t) {
        return this.f2555a.containsKey(t);
    }

    @Nullable
    public synchronized Y get(@NonNull T t) {
        a<Y> aVar;
        aVar = this.f2555a.get(t);
        return aVar != null ? aVar.f2556a : null;
    }

    public synchronized int getCount() {
        return this.f2555a.size();
    }

    public synchronized long getCurrentSize() {
        return this.d;
    }

    public synchronized long getMaxSize() {
        return this.c;
    }

    public int getSize(@Nullable Y y) {
        return 1;
    }

    public void onItemEvicted(@NonNull T t, @Nullable Y y) {
    }

    @Nullable
    public synchronized Y put(@NonNull T t, @Nullable Y y) {
        int size = getSize(y);
        long j = size;
        if (j >= this.c) {
            onItemEvicted(t, y);
            return null;
        }
        if (y != null) {
            this.d += j;
        }
        a<Y> put = this.f2555a.put(t, y == null ? null : new a<>(y, size));
        if (put != null) {
            this.d -= put.b;
            if (!put.f2556a.equals(y)) {
                onItemEvicted(t, put.f2556a);
            }
        }
        a();
        return put != null ? put.f2556a : null;
    }

    @Nullable
    public synchronized Y remove(@NonNull T t) {
        a<Y> remove = this.f2555a.remove(t);
        if (remove == null) {
            return null;
        }
        this.d -= remove.b;
        return remove.f2556a;
    }

    public synchronized void setSizeMultiplier(float f) {
        if (f >= 0.0f) {
            this.c = Math.round(((float) this.b) * f);
            a();
        } else {
            throw new IllegalArgumentException("Multiplier must be >= 0");
        }
    }

    public synchronized void trimToSize(long j) {
        while (this.d > j) {
            Iterator<Map.Entry<T, a<Y>>> it = this.f2555a.entrySet().iterator();
            Map.Entry<T, a<Y>> next = it.next();
            a<Y> value = next.getValue();
            this.d -= value.b;
            T key = next.getKey();
            it.remove();
            onItemEvicted(key, value.f2556a);
        }
    }
}
