package com.blankj.utilcode.util;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;
import com.blankj.utilcode.constant.CacheConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CacheMemoryUtils implements CacheConstants {
    public static final Map<String, CacheMemoryUtils> c = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public final String f2243a;
    public final LruCache<String, a> b;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public long f2244a;
        public Object b;

        public a(long j, Object obj) {
            this.f2244a = j;
            this.b = obj;
        }
    }

    public CacheMemoryUtils(String str, LruCache<String, a> lruCache) {
        this.f2243a = str;
        this.b = lruCache;
    }

    public static CacheMemoryUtils getInstance() {
        return getInstance(256);
    }

    public void clear() {
        this.b.evictAll();
    }

    public <T> T get(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) get(str, null);
    }

    public int getCacheCount() {
        return this.b.size();
    }

    public void put(@NonNull String str, Object obj) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, obj, -1);
    }

    public Object remove(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        a remove = this.b.remove(str);
        if (remove == null) {
            return null;
        }
        return remove.b;
    }

    public String toString() {
        return this.f2243a + "@" + Integer.toHexString(hashCode());
    }

    public static CacheMemoryUtils getInstance(int i) {
        return getInstance(String.valueOf(i), i);
    }

    public static CacheMemoryUtils getInstance(String str, int i) {
        Map<String, CacheMemoryUtils> map = c;
        CacheMemoryUtils cacheMemoryUtils = map.get(str);
        if (cacheMemoryUtils == null) {
            synchronized (CacheMemoryUtils.class) {
                cacheMemoryUtils = map.get(str);
                if (cacheMemoryUtils == null) {
                    cacheMemoryUtils = new CacheMemoryUtils(str, new LruCache(i));
                    map.put(str, cacheMemoryUtils);
                }
            }
        }
        return cacheMemoryUtils;
    }

    public <T> T get(@NonNull String str, T t) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        a aVar = this.b.get(str);
        if (aVar == null) {
            return t;
        }
        long j = aVar.f2244a;
        if (j != -1 && j < System.currentTimeMillis()) {
            this.b.remove(str);
            return t;
        }
        return (T) aVar.b;
    }

    public void put(@NonNull String str, Object obj, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (obj == null) {
            return;
        }
        this.b.put(str, new a(i < 0 ? -1L : System.currentTimeMillis() + (i * 1000), obj));
    }
}
