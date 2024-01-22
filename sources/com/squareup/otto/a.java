package com.squareup.otto;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes12.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final ConcurrentMap<Class<?>, Map<Class<?>, Method>> f13710a = new ConcurrentHashMap();
    public static final ConcurrentMap<Class<?>, Map<Class<?>, Set<Method>>> b = new ConcurrentHashMap();

    public static Map<Class<?>, c> a(Object obj) {
        Class<?> cls = obj.getClass();
        HashMap hashMap = new HashMap();
        Map<Class<?>, Method> map = f13710a.get(cls);
        if (map == null) {
            map = new HashMap<>();
            d(cls, map);
        }
        if (!map.isEmpty()) {
            for (Map.Entry<Class<?>, Method> entry : map.entrySet()) {
                hashMap.put(entry.getKey(), new c(obj, entry.getValue()));
            }
        }
        return hashMap;
    }

    public static Map<Class<?>, Set<b>> b(Object obj) {
        Class<?> cls = obj.getClass();
        HashMap hashMap = new HashMap();
        Map<Class<?>, Set<Method>> map = b.get(cls);
        if (map == null) {
            map = new HashMap<>();
            e(cls, map);
        }
        if (!map.isEmpty()) {
            for (Map.Entry<Class<?>, Set<Method>> entry : map.entrySet()) {
                HashSet hashSet = new HashSet();
                for (Method method : entry.getValue()) {
                    hashSet.add(new b(obj, method));
                }
                hashMap.put(entry.getKey(), hashSet);
            }
        }
        return hashMap;
    }

    public static void c(Class<?> cls, Map<Class<?>, Method> map, Map<Class<?>, Set<Method>> map2) {
        Method[] declaredMethods;
        Class<?>[] parameterTypes;
        for (Method method : cls.getDeclaredMethods()) {
            if (!method.isBridge()) {
                if (method.isAnnotationPresent(Subscribe.class)) {
                    Class<?>[] parameterTypes2 = method.getParameterTypes();
                    if (parameterTypes2.length == 1) {
                        Class<?> cls2 = parameterTypes2[0];
                        if (!cls2.isInterface()) {
                            if ((1 & method.getModifiers()) != 0) {
                                Set<Method> set = map2.get(cls2);
                                if (set == null) {
                                    set = new HashSet<>();
                                    map2.put(cls2, set);
                                }
                                set.add(method);
                            } else {
                                throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + cls2 + " but is not 'public'.");
                            }
                        } else {
                            throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + cls2 + " which is an interface.  Subscription must be on a concrete class type.");
                        }
                    } else {
                        throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation but requires " + parameterTypes2.length + " arguments.  Methods must require a single argument.");
                    }
                } else if (method.isAnnotationPresent(Produce.class)) {
                    if (method.getParameterTypes().length == 0) {
                        if (method.getReturnType() != Void.class) {
                            Class<?> returnType = method.getReturnType();
                            if (!returnType.isInterface()) {
                                if (!returnType.equals(Void.TYPE)) {
                                    if ((1 & method.getModifiers()) != 0) {
                                        if (!map.containsKey(returnType)) {
                                            map.put(returnType, method);
                                        } else {
                                            throw new IllegalArgumentException("Producer for type " + returnType + " has already been registered.");
                                        }
                                    } else {
                                        throw new IllegalArgumentException("Method " + method + " has @Produce annotation on " + returnType + " but is not 'public'.");
                                    }
                                } else {
                                    throw new IllegalArgumentException("Method " + method + " has @Produce annotation but has no return type.");
                                }
                            } else {
                                throw new IllegalArgumentException("Method " + method + " has @Produce annotation on " + returnType + " which is an interface.  Producers must return a concrete class type.");
                            }
                        } else {
                            throw new IllegalArgumentException("Method " + method + " has a return type of void.  Must declare a non-void type.");
                        }
                    } else {
                        throw new IllegalArgumentException("Method " + method + "has @Produce annotation but requires " + parameterTypes.length + " arguments.  Methods must require zero arguments.");
                    }
                } else {
                    continue;
                }
            }
        }
        f13710a.put(cls, map);
        b.put(cls, map2);
    }

    public static void d(Class<?> cls, Map<Class<?>, Method> map) {
        c(cls, map, new HashMap());
    }

    public static void e(Class<?> cls, Map<Class<?>, Set<Method>> map) {
        c(cls, new HashMap(), map);
    }
}
