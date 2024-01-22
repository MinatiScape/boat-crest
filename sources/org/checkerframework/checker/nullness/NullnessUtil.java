package org.checkerframework.checker.nullness;

import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
/* loaded from: classes13.dex */
public final class NullnessUtil {
    public NullnessUtil() {
        throw new AssertionError("shouldn't be instantiated");
    }

    public static <T> T[] a(T[] tArr) {
        for (T t : tArr) {
            b(t);
        }
        return tArr;
    }

    public static void b(Object obj) {
        Class<?> componentType = obj.getClass().getComponentType();
        if (componentType == null || componentType.isPrimitive()) {
            return;
        }
        a((Object[]) obj);
    }

    @EnsuresNonNull({"#1"})
    public static <T> T castNonNull(T t) {
        return t;
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[] castNonNullDeep(T[] tArr) {
        return (T[]) a(tArr);
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][] castNonNullDeep(T[][] tArr) {
        return (T[][]) ((Object[][]) a(tArr));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][] castNonNullDeep(T[][][] tArr) {
        return (T[][][]) ((Object[][][]) a(tArr));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][][] castNonNullDeep(T[][][][] tArr) {
        return (T[][][][]) ((Object[][][][]) a(tArr));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][][][] castNonNullDeep(T[][][][][] tArr) {
        return (T[][][][][]) ((Object[][][][][]) a(tArr));
    }
}
