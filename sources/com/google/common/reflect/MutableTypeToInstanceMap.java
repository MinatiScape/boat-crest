package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ForwardingMapEntry;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes10.dex */
public final class MutableTypeToInstanceMap<B> extends ForwardingMap<TypeToken<? extends B>, B> implements TypeToInstanceMap<B> {
    public final Map<TypeToken<? extends B>, B> h = Maps.newHashMap();

    /* loaded from: classes10.dex */
    public static final class b<K, V> extends ForwardingMapEntry<K, V> {
        public final Map.Entry<K, V> h;

        /* loaded from: classes10.dex */
        public class a extends ForwardingSet<Map.Entry<K, V>> {
            public final /* synthetic */ Set h;

            public a(Set set) {
                this.h = set;
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable
            public Iterator<Map.Entry<K, V>> iterator() {
                return b.e(super.iterator());
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
            public Object[] toArray() {
                return standardToArray();
            }

            @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
            public <T> T[] toArray(T[] tArr) {
                return (T[]) standardToArray(tArr);
            }

            @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
            public Set<Map.Entry<K, V>> delegate() {
                return this.h;
            }
        }

        /* renamed from: com.google.common.reflect.MutableTypeToInstanceMap$b$b  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0515b implements Function<Map.Entry<K, V>, Map.Entry<K, V>> {
            @Override // com.google.common.base.Function
            /* renamed from: a */
            public Map.Entry<K, V> apply(Map.Entry<K, V> entry) {
                return new b(entry);
            }
        }

        public static <K, V> Iterator<Map.Entry<K, V>> e(Iterator<Map.Entry<K, V>> it) {
            return Iterators.transform(it, new C0515b());
        }

        public static <K, V> Set<Map.Entry<K, V>> f(Set<Map.Entry<K, V>> set) {
            return new a(set);
        }

        @Override // com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }

        public b(Map.Entry<K, V> entry) {
            this.h = (Map.Entry) Preconditions.checkNotNull(entry);
        }

        @Override // com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingObject
        public Map.Entry<K, V> delegate() {
            return this.h;
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T extends B, java.lang.Object] */
    @NullableDecl
    public final <T extends B> T d(TypeToken<T> typeToken) {
        return this.h.get(typeToken);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T extends B, java.lang.Object] */
    @NullableDecl
    public final <T extends B> T e(TypeToken<T> typeToken, @NullableDecl T t) {
        return this.h.put(typeToken, t);
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public Set<Map.Entry<TypeToken<? extends B>, B>> entrySet() {
        return b.f(super.entrySet());
    }

    @Override // com.google.common.reflect.TypeToInstanceMap
    @NullableDecl
    public <T extends B> T getInstance(Class<T> cls) {
        return (T) d(TypeToken.of((Class) cls));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((TypeToken<? extends TypeToken<? extends B>>) obj, (TypeToken<? extends B>) obj2);
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public void putAll(Map<? extends TypeToken<? extends B>, ? extends B> map) {
        throw new UnsupportedOperationException("Please use putInstance() instead.");
    }

    @Override // com.google.common.reflect.TypeToInstanceMap
    @CanIgnoreReturnValue
    @NullableDecl
    public <T extends B> T putInstance(Class<T> cls, @NullableDecl T t) {
        return (T) e(TypeToken.of((Class) cls), t);
    }

    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public Map<TypeToken<? extends B>, B> delegate() {
        return this.h;
    }

    @Override // com.google.common.reflect.TypeToInstanceMap
    @NullableDecl
    public <T extends B> T getInstance(TypeToken<T> typeToken) {
        return (T) d(typeToken.rejectTypeVariables());
    }

    @CanIgnoreReturnValue
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public B put(TypeToken<? extends B> typeToken, B b2) {
        throw new UnsupportedOperationException("Please use putInstance() instead.");
    }

    @Override // com.google.common.reflect.TypeToInstanceMap
    @CanIgnoreReturnValue
    @NullableDecl
    public <T extends B> T putInstance(TypeToken<T> typeToken, @NullableDecl T t) {
        return (T) e(typeToken.rejectTypeVariables(), t);
    }
}
