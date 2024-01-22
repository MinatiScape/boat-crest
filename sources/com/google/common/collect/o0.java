package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public final class o0<K, V> extends p0<K, V> implements ListMultimap<K, V> {
    public o0(ListMultimap<K, V> listMultimap, Predicate<? super K> predicate) {
        super(listMultimap, predicate);
    }

    @Override // com.google.common.collect.p0, com.google.common.collect.r0
    /* renamed from: d */
    public ListMultimap<K, V> a() {
        return (ListMultimap) super.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.p0, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Collection get(Object obj) {
        return get((o0<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
        return replaceValues((o0<K, V>) obj, iterable);
    }

    @Override // com.google.common.collect.p0, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public List<V> get(K k) {
        return (List) super.get((o0<K, V>) k);
    }

    @Override // com.google.common.collect.p0, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public List<V> removeAll(@NullableDecl Object obj) {
        return (List) super.removeAll(obj);
    }

    @Override // com.google.common.collect.g, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
        return (List) super.replaceValues((o0<K, V>) k, (Iterable) iterable);
    }
}
