package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class d1<K, V> extends ImmutableCollection<V> {
    private final ImmutableMap<K, V> map;

    /* loaded from: classes10.dex */
    public class a extends UnmodifiableIterator<V> {
        public final UnmodifiableIterator<Map.Entry<K, V>> h;

        public a() {
            this.h = d1.this.map.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h.hasNext();
        }

        @Override // java.util.Iterator
        public V next() {
            return this.h.next().getValue();
        }
    }

    /* loaded from: classes10.dex */
    public class b extends ImmutableList<V> {
        public final /* synthetic */ ImmutableList val$entryList;

        public b(d1 d1Var, ImmutableList immutableList) {
            this.val$entryList = immutableList;
        }

        @Override // java.util.List
        public V get(int i) {
            return (V) ((Map.Entry) this.val$entryList.get(i)).getValue();
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.val$entryList.size();
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static class c<V> implements Serializable {
        private static final long serialVersionUID = 0;
        public final ImmutableMap<?, V> map;

        public c(ImmutableMap<?, V> immutableMap) {
            this.map = immutableMap;
        }

        public Object readResolve() {
            return this.map.values();
        }
    }

    public d1(ImmutableMap<K, V> immutableMap) {
        this.map = immutableMap;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<V> asList() {
        return new b(this, this.map.entrySet().asList());
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
    public boolean contains(@NullableDecl Object obj) {
        return obj != null && Iterators.contains(iterator(), obj);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.map.size();
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public Object writeReplace() {
        return new c(this.map);
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public UnmodifiableIterator<V> iterator() {
        return new a();
    }
}
