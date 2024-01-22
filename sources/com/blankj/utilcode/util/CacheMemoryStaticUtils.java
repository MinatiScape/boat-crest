package com.blankj.utilcode.util;

import androidx.annotation.NonNull;
import java.util.Objects;
/* loaded from: classes.dex */
public final class CacheMemoryStaticUtils {

    /* renamed from: a  reason: collision with root package name */
    public static CacheMemoryUtils f2242a;

    public static CacheMemoryUtils a() {
        CacheMemoryUtils cacheMemoryUtils = f2242a;
        return cacheMemoryUtils != null ? cacheMemoryUtils : CacheMemoryUtils.getInstance();
    }

    public static void clear() {
        clear(a());
    }

    public static <T> T get(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) get(str, a());
    }

    public static int getCacheCount() {
        return getCacheCount(a());
    }

    public static void put(@NonNull String str, Object obj) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, obj, a());
    }

    public static Object remove(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return remove(str, a());
    }

    public static void setDefaultCacheMemoryUtils(CacheMemoryUtils cacheMemoryUtils) {
        f2242a = cacheMemoryUtils;
    }

    public static void clear(@NonNull CacheMemoryUtils cacheMemoryUtils) {
        Objects.requireNonNull(cacheMemoryUtils, "Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        cacheMemoryUtils.clear();
    }

    public static int getCacheCount(@NonNull CacheMemoryUtils cacheMemoryUtils) {
        Objects.requireNonNull(cacheMemoryUtils, "Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return cacheMemoryUtils.getCacheCount();
    }

    public static <T> T get(@NonNull String str, T t) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) get(str, t, a());
    }

    public static void put(@NonNull String str, Object obj, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, obj, i, a());
    }

    public static Object remove(@NonNull String str, @NonNull CacheMemoryUtils cacheMemoryUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(cacheMemoryUtils, "Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return cacheMemoryUtils.remove(str);
    }

    public static <T> T get(@NonNull String str, @NonNull CacheMemoryUtils cacheMemoryUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(cacheMemoryUtils, "Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) cacheMemoryUtils.get(str);
    }

    public static void put(@NonNull String str, Object obj, @NonNull CacheMemoryUtils cacheMemoryUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(cacheMemoryUtils, "Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        cacheMemoryUtils.put(str, obj);
    }

    public static <T> T get(@NonNull String str, T t, @NonNull CacheMemoryUtils cacheMemoryUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(cacheMemoryUtils, "Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) cacheMemoryUtils.get(str, t);
    }

    public static void put(@NonNull String str, Object obj, int i, @NonNull CacheMemoryUtils cacheMemoryUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(cacheMemoryUtils, "Argument 'cacheMemoryUtils' of type CacheMemoryUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        cacheMemoryUtils.put(str, obj, i);
    }
}
