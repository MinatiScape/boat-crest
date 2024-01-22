package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public class p0<K, V> extends g<K, V> implements r0<K, V> {
    public final Multimap<K, V> h;
    public final Predicate<? super K> i;

    /* loaded from: classes10.dex */
    public static class a<K, V> extends ForwardingList<V> {
        public final K h;

        public a(K k) {
            this.h = k;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(V v) {
            add(0, v);
            return true;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            addAll(0, collection);
            return true;
        }

        @Override // com.google.common.collect.ForwardingList, java.util.List
        public void add(int i, V v) {
            Preconditions.checkPositionIndex(i, 0);
            String valueOf = String.valueOf(this.h);
            StringBuilder sb = new StringBuilder(valueOf.length() + 32);
            sb.append("Key does not satisfy predicate: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }

        @Override // com.google.common.collect.ForwardingList, java.util.List
        @CanIgnoreReturnValue
        public boolean addAll(int i, Collection<? extends V> collection) {
            Preconditions.checkNotNull(collection);
            Preconditions.checkPositionIndex(i, 0);
            String valueOf = String.valueOf(this.h);
            StringBuilder sb = new StringBuilder(valueOf.length() + 32);
            sb.append("Key does not satisfy predicate: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }

        @Override // com.google.common.collect.ForwardingList, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public List<V> delegate() {
            return Collections.emptyList();
        }
    }

    /* loaded from: classes10.dex */
    public class c extends ForwardingCollection<Map.Entry<K, V>> {
        public c() {
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                if (p0.this.h.containsKey(entry.getKey()) && p0.this.i.apply((Object) entry.getKey())) {
                    return p0.this.h.remove(entry.getKey(), entry.getValue());
                }
                return false;
            }
            return false;
        }

        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Collection<Map.Entry<K, V>> delegate() {
            return Collections2.filter(p0.this.h.entries(), p0.this.b());
        }
    }

    public p0(Multimap<K, V> multimap, Predicate<? super K> predicate) {
        this.h = (Multimap) Preconditions.checkNotNull(multimap);
        this.i = (Predicate) Preconditions.checkNotNull(predicate);
    }

    public Multimap<K, V> a() {
        return this.h;
    }

    @Override // com.google.common.collect.r0
    public Predicate<? super Map.Entry<K, V>> b() {
        return Maps.y(this.i);
    }

    public Collection<V> c() {
        if (this.h instanceof SetMultimap) {
            return ImmutableSet.of();
        }
        return ImmutableList.of();
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        keySet().clear();
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        if (this.h.containsKey(obj)) {
            return this.i.apply(obj);
        }
        return false;
    }

    @Override // com.google.common.collect.g
    public Map<K, Collection<V>> createAsMap() {
        return Maps.filterKeys(this.h.asMap(), this.i);
    }

    @Override // com.google.common.collect.g
    public Collection<Map.Entry<K, V>> createEntries() {
        return new c();
    }

    @Override // com.google.common.collect.g
    public Set<K> createKeySet() {
        return Sets.filter(this.h.keySet(), this.i);
    }

    @Override // com.google.common.collect.g
    public Multiset<K> createKeys() {
        return Multisets.filter(this.h.keys(), this.i);
    }

    @Override // com.google.common.collect.g
    public Collection<V> createValues() {
        return new s0(this);
    }

    @Override // com.google.common.collect.g
    public Iterator<Map.Entry<K, V>> entryIterator() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection<V> get(K k) {
        if (this.i.apply(k)) {
            return this.h.get(k);
        }
        if (this.h instanceof SetMultimap) {
            return new b(k);
        }
        return new a(k);
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public Collection<V> removeAll(Object obj) {
        return containsKey(obj) ? this.h.removeAll(obj) : c();
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        int i = 0;
        for (Collection<V> collection : asMap().values()) {
            i += collection.size();
        }
        return i;
    }

    /* loaded from: classes10.dex */
    public static class b<K, V> extends ForwardingSet<V> {
        public final K h;

        public b(K k) {
            this.h = k;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(V v) {
            String valueOf = String.valueOf(this.h);
            StringBuilder sb = new StringBuilder(valueOf.length() + 32);
            sb.append("Key does not satisfy predicate: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            Preconditions.checkNotNull(collection);
            String valueOf = String.valueOf(this.h);
            StringBuilder sb = new StringBuilder(valueOf.length() + 32);
            sb.append("Key does not satisfy predicate: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }

        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<V> delegate() {
            return Collections.emptySet();
        }
    }
}
