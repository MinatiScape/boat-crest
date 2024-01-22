package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class r1 {
    public static <T> T[] a(Object[] objArr, int i, int i2, T[] tArr) {
        return (T[]) Arrays.copyOfRange(objArr, i, i2, tArr.getClass());
    }

    public static <T> T[] b(T[] tArr, int i) {
        return (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
    }

    public static <K, V> Map<K, V> c(int i) {
        return v.createWithExpectedSize(i);
    }

    public static <E> Set<E> d(int i) {
        return w.createWithExpectedSize(i);
    }

    public static <K, V> Map<K, V> e(int i) {
        return y.createWithExpectedSize(i);
    }

    public static <E> Set<E> f(int i) {
        return z.createWithExpectedSize(i);
    }

    public static <E> Set<E> g() {
        return w.create();
    }

    public static <K, V> Map<K, V> h() {
        return v.create();
    }

    public static MapMaker i(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }
}
