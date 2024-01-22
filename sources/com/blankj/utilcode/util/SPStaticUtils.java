package com.blankj.utilcode.util;

import androidx.annotation.NonNull;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public final class SPStaticUtils {

    /* renamed from: a  reason: collision with root package name */
    public static SPUtils f2284a;

    public static SPUtils a() {
        SPUtils sPUtils = f2284a;
        return sPUtils != null ? sPUtils : SPUtils.getInstance();
    }

    public static void clear() {
        clear(a());
    }

    public static boolean contains(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return contains(str, a());
    }

    public static Map<String, ?> getAll() {
        return getAll(a());
    }

    public static boolean getBoolean(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getBoolean(str, a());
    }

    public static float getFloat(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getFloat(str, a());
    }

    public static int getInt(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getInt(str, a());
    }

    public static long getLong(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getLong(str, a());
    }

    public static String getString(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getString(str, a());
    }

    public static Set<String> getStringSet(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getStringSet(str, a());
    }

    public static void put(@NonNull String str, String str2) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, str2, a());
    }

    public static void remove(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        remove(str, a());
    }

    public static void setDefaultSPUtils(SPUtils sPUtils) {
        f2284a = sPUtils;
    }

    public static void clear(boolean z) {
        clear(z, a());
    }

    public static Map<String, ?> getAll(@NonNull SPUtils sPUtils) {
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getAll();
    }

    public static void clear(@NonNull SPUtils sPUtils) {
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.clear();
    }

    public static boolean contains(@NonNull String str, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.contains(str);
    }

    public static boolean getBoolean(@NonNull String str, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getBoolean(str, z, a());
    }

    public static float getFloat(@NonNull String str, float f) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getFloat(str, f, a());
    }

    public static int getInt(@NonNull String str, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getInt(str, i, a());
    }

    public static long getLong(@NonNull String str, long j) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getLong(str, j, a());
    }

    public static String getString(@NonNull String str, String str2) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getString(str, str2, a());
    }

    public static Set<String> getStringSet(@NonNull String str, Set<String> set) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getStringSet(str, set, a());
    }

    public static void put(@NonNull String str, String str2, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, str2, z, a());
    }

    public static void remove(@NonNull String str, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        remove(str, z, a());
    }

    public static void clear(boolean z, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.clear(z);
    }

    public static boolean getBoolean(@NonNull String str, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getBoolean(str);
    }

    public static float getFloat(@NonNull String str, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getFloat(str);
    }

    public static int getInt(@NonNull String str, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getInt(str);
    }

    public static long getLong(@NonNull String str, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getLong(str);
    }

    public static String getString(@NonNull String str, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getString(str);
    }

    public static Set<String> getStringSet(@NonNull String str, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getStringSet(str);
    }

    public static void put(@NonNull String str, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, i, a());
    }

    public static void remove(@NonNull String str, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.remove(str);
    }

    public static boolean getBoolean(@NonNull String str, boolean z, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getBoolean(str, z);
    }

    public static float getFloat(@NonNull String str, float f, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getFloat(str, f);
    }

    public static int getInt(@NonNull String str, int i, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getInt(str, i);
    }

    public static long getLong(@NonNull String str, long j, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getLong(str, j);
    }

    public static String getString(@NonNull String str, String str2, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getString(str, str2);
    }

    public static Set<String> getStringSet(@NonNull String str, Set<String> set, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return sPUtils.getStringSet(str, set);
    }

    public static void put(@NonNull String str, int i, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, i, z, a());
    }

    public static void remove(@NonNull String str, boolean z, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.remove(str, z);
    }

    public static void put(@NonNull String str, long j) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, j, a());
    }

    public static void put(@NonNull String str, long j, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, j, z, a());
    }

    public static void put(@NonNull String str, float f) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, f, a());
    }

    public static void put(@NonNull String str, float f, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, f, z, a());
    }

    public static void put(@NonNull String str, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, z, a());
    }

    public static void put(@NonNull String str, boolean z, boolean z2) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, z, z2, a());
    }

    public static void put(@NonNull String str, Set<String> set) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, set, a());
    }

    public static void put(@NonNull String str, Set<String> set, boolean z) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, set, z, a());
    }

    public static void put(@NonNull String str, String str2, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.put(str, str2);
    }

    public static void put(@NonNull String str, String str2, boolean z, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.put(str, str2, z);
    }

    public static void put(@NonNull String str, int i, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.put(str, i);
    }

    public static void put(@NonNull String str, int i, boolean z, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.put(str, i, z);
    }

    public static void put(@NonNull String str, long j, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.put(str, j);
    }

    public static void put(@NonNull String str, long j, boolean z, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.put(str, j, z);
    }

    public static void put(@NonNull String str, float f, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.put(str, f);
    }

    public static void put(@NonNull String str, float f, boolean z, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.put(str, f, z);
    }

    public static void put(@NonNull String str, boolean z, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.put(str, z);
    }

    public static void put(@NonNull String str, boolean z, boolean z2, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.put(str, z, z2);
    }

    public static void put(@NonNull String str, Set<String> set, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.put(str, set);
    }

    public static void put(@NonNull String str, Set<String> set, boolean z, @NonNull SPUtils sPUtils) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(sPUtils, "Argument 'spUtils' of type SPUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        sPUtils.put(str, set, z);
    }
}
