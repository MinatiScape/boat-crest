package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public final class i<E> extends AbstractSet<E> {
    public final Map<?, E> h;
    public final Object i;

    public i(Map<?, E> map, Object obj) {
        this.h = (Map) Preconditions.checkNotNull(map);
        this.i = Preconditions.checkNotNull(obj);
    }

    @NullableDecl
    public final E a() {
        return this.h.get(this.i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    /* renamed from: b */
    public UnmodifiableIterator<E> iterator() {
        E a2 = a();
        if (a2 == null) {
            return ImmutableSet.of().iterator();
        }
        return Iterators.singletonIterator(a2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@NullableDecl Object obj) {
        E a2 = a();
        return a2 != null && a2.equals(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return a() == null ? 0 : 1;
    }
}
