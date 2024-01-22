package com.google.common.eventbus;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Primitives;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public final class c {
    public static final LoadingCache<Class<?>, ImmutableList<Method>> c = CacheBuilder.newBuilder().weakKeys().build(new a());
    public static final LoadingCache<Class<?>, ImmutableSet<Class<?>>> d = CacheBuilder.newBuilder().weakKeys().build(new b());

    /* renamed from: a  reason: collision with root package name */
    public final ConcurrentMap<Class<?>, CopyOnWriteArraySet<com.google.common.eventbus.b>> f10615a = Maps.newConcurrentMap();
    @Weak
    public final EventBus b;

    /* loaded from: classes10.dex */
    public class a extends CacheLoader<Class<?>, ImmutableList<Method>> {
        @Override // com.google.common.cache.CacheLoader
        /* renamed from: a */
        public ImmutableList<Method> load(Class<?> cls) throws Exception {
            return c.e(cls);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends CacheLoader<Class<?>, ImmutableSet<Class<?>>> {
        @Override // com.google.common.cache.CacheLoader
        /* renamed from: a */
        public ImmutableSet<Class<?>> load(Class<?> cls) {
            return ImmutableSet.copyOf((Collection) TypeToken.of((Class) cls).getTypes().rawTypes());
        }
    }

    /* renamed from: com.google.common.eventbus.c$c  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static final class C0501c {

        /* renamed from: a  reason: collision with root package name */
        public final String f10616a;
        public final List<Class<?>> b;

        public C0501c(Method method) {
            this.f10616a = method.getName();
            this.b = Arrays.asList(method.getParameterTypes());
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof C0501c) {
                C0501c c0501c = (C0501c) obj;
                return this.f10616a.equals(c0501c.f10616a) && this.b.equals(c0501c.b);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.f10616a, this.b);
        }
    }

    public c(EventBus eventBus) {
        this.b = (EventBus) Preconditions.checkNotNull(eventBus);
    }

    @VisibleForTesting
    public static ImmutableSet<Class<?>> c(Class<?> cls) {
        try {
            return d.getUnchecked(cls);
        } catch (UncheckedExecutionException e) {
            throw Throwables.propagate(e.getCause());
        }
    }

    public static ImmutableList<Method> d(Class<?> cls) {
        try {
            return c.getUnchecked(cls);
        } catch (UncheckedExecutionException e) {
            Throwables.throwIfUnchecked(e.getCause());
            throw e;
        }
    }

    public static ImmutableList<Method> e(Class<?> cls) {
        Method[] declaredMethods;
        Set<Class> rawTypes = TypeToken.of((Class) cls).getTypes().rawTypes();
        HashMap newHashMap = Maps.newHashMap();
        for (Class cls2 : rawTypes) {
            for (Method method : cls2.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Subscribe.class) && !method.isSynthetic()) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Preconditions.checkArgument(parameterTypes.length == 1, "Method %s has @Subscribe annotation but has %s parameters. Subscriber methods must have exactly 1 parameter.", (Object) method, parameterTypes.length);
                    Preconditions.checkArgument(!parameterTypes[0].isPrimitive(), "@Subscribe method %s's parameter is %s. Subscriber methods cannot accept primitives. Consider changing the parameter to %s.", method, parameterTypes[0].getName(), Primitives.wrap(parameterTypes[0]).getSimpleName());
                    C0501c c0501c = new C0501c(method);
                    if (!newHashMap.containsKey(c0501c)) {
                        newHashMap.put(c0501c, method);
                    }
                }
            }
        }
        return ImmutableList.copyOf(newHashMap.values());
    }

    public final Multimap<Class<?>, com.google.common.eventbus.b> b(Object obj) {
        HashMultimap create = HashMultimap.create();
        UnmodifiableIterator<Method> it = d(obj.getClass()).iterator();
        while (it.hasNext()) {
            Method next = it.next();
            create.put(next.getParameterTypes()[0], com.google.common.eventbus.b.d(this.b, obj, next));
        }
        return create;
    }

    public Iterator<com.google.common.eventbus.b> f(Object obj) {
        ImmutableSet<Class<?>> c2 = c(obj.getClass());
        ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(c2.size());
        UnmodifiableIterator<Class<?>> it = c2.iterator();
        while (it.hasNext()) {
            CopyOnWriteArraySet<com.google.common.eventbus.b> copyOnWriteArraySet = this.f10615a.get(it.next());
            if (copyOnWriteArraySet != null) {
                newArrayListWithCapacity.add(copyOnWriteArraySet.iterator());
            }
        }
        return Iterators.concat(newArrayListWithCapacity.iterator());
    }

    public void g(Object obj) {
        for (Map.Entry<Class<?>, Collection<com.google.common.eventbus.b>> entry : b(obj).asMap().entrySet()) {
            Class<?> key = entry.getKey();
            Collection<com.google.common.eventbus.b> value = entry.getValue();
            CopyOnWriteArraySet<com.google.common.eventbus.b> copyOnWriteArraySet = this.f10615a.get(key);
            if (copyOnWriteArraySet == null) {
                CopyOnWriteArraySet<com.google.common.eventbus.b> copyOnWriteArraySet2 = new CopyOnWriteArraySet<>();
                copyOnWriteArraySet = (CopyOnWriteArraySet) MoreObjects.firstNonNull(this.f10615a.putIfAbsent(key, copyOnWriteArraySet2), copyOnWriteArraySet2);
            }
            copyOnWriteArraySet.addAll(value);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void h(java.lang.Object r5) {
        /*
            r4 = this;
            com.google.common.collect.Multimap r0 = r4.b(r5)
            java.util.Map r0 = r0.asMap()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L10:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L5f
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.Class r2 = (java.lang.Class) r2
            java.lang.Object r1 = r1.getValue()
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.concurrent.ConcurrentMap<java.lang.Class<?>, java.util.concurrent.CopyOnWriteArraySet<com.google.common.eventbus.b>> r3 = r4.f10615a
            java.lang.Object r2 = r3.get(r2)
            java.util.concurrent.CopyOnWriteArraySet r2 = (java.util.concurrent.CopyOnWriteArraySet) r2
            if (r2 == 0) goto L39
            boolean r1 = r2.removeAll(r1)
            if (r1 == 0) goto L39
            goto L10
        L39:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r1 = r5.length()
            int r1 = r1 + 65
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "missing event subscriber for an annotated method. Is "
            r2.append(r1)
            r2.append(r5)
            java.lang.String r5 = " registered?"
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            r0.<init>(r5)
            throw r0
        L5f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.eventbus.c.h(java.lang.Object):void");
    }
}
