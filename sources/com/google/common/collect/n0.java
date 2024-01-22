package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
@GwtCompatible
/* loaded from: classes10.dex */
public final class n0<K, V> extends m0<K, V> implements t0<K, V> {
    public n0(SetMultimap<K, V> setMultimap, Predicate<? super Map.Entry<K, V>> predicate) {
        super(setMultimap, predicate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.m0, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Collection get(Object obj) {
        return get((n0<K, V>) obj);
    }

    @Override // com.google.common.collect.m0, com.google.common.collect.g
    /* renamed from: h */
    public Set<Map.Entry<K, V>> createEntries() {
        return Sets.filter(a().entries(), b());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
        return replaceValues((n0<K, V>) obj, iterable);
    }

    @Override // com.google.common.collect.m0, com.google.common.collect.r0
    public SetMultimap<K, V> a() {
        return (SetMultimap) this.h;
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap
    public Set<Map.Entry<K, V>> entries() {
        return (Set) super.entries();
    }

    @Override // com.google.common.collect.m0, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Set<V> get(K k) {
        return (Set) super.get((n0<K, V>) k);
    }

    @Override // com.google.common.collect.m0, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Set<V> removeAll(Object obj) {
        return (Set) super.removeAll(obj);
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
        return (Set) super.replaceValues((n0<K, V>) k, (Iterable) iterable);
    }
}
