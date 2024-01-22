package com.crrepa.p0;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    public static final Map<Class<?>, Class<?>> f7809a;
    public static final Map<Class<?>, Class<?>> b;

    static {
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        b(hashMap, hashMap2, Boolean.TYPE, Boolean.class);
        b(hashMap, hashMap2, Byte.TYPE, Byte.class);
        b(hashMap, hashMap2, Character.TYPE, Character.class);
        b(hashMap, hashMap2, Double.TYPE, Double.class);
        b(hashMap, hashMap2, Float.TYPE, Float.class);
        b(hashMap, hashMap2, Integer.TYPE, Integer.class);
        b(hashMap, hashMap2, Long.TYPE, Long.class);
        b(hashMap, hashMap2, Short.TYPE, Short.class);
        b(hashMap, hashMap2, Void.TYPE, Void.class);
        f7809a = Collections.unmodifiableMap(hashMap);
        b = Collections.unmodifiableMap(hashMap2);
    }

    public j() {
        throw new UnsupportedOperationException();
    }

    public static <T> Class<T> a(Class<T> cls) {
        Class<T> cls2 = (Class<T>) b.get(a.a(cls));
        return cls2 == null ? cls : cls2;
    }

    public static boolean a(Type type) {
        return f7809a.containsKey(type);
    }

    public static <T> Class<T> b(Class<T> cls) {
        Class<T> cls2 = (Class<T>) f7809a.get(a.a(cls));
        return cls2 == null ? cls : cls2;
    }

    public static void b(Map<Class<?>, Class<?>> map, Map<Class<?>, Class<?>> map2, Class<?> cls, Class<?> cls2) {
        map.put(cls, cls2);
        map2.put(cls2, cls);
    }

    public static boolean b(Type type) {
        return b.containsKey(a.a(type));
    }
}
