package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public class q<K, V> {

    /* renamed from: a  reason: collision with root package name */
    public final Map<K, V> f10637a;
    @NullableDecl
    public volatile transient Map.Entry<K, V> b;

    /* loaded from: classes10.dex */
    public class a extends AbstractSet<K> {

        /* renamed from: com.google.common.graph.q$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0511a extends UnmodifiableIterator<K> {
            public final /* synthetic */ Iterator h;

            public C0511a(Iterator it) {
                this.h = it;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.h.hasNext();
            }

            @Override // java.util.Iterator
            public K next() {
                Map.Entry entry = (Map.Entry) this.h.next();
                q.this.b = entry;
                return (K) entry.getKey();
            }
        }

        public a() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        /* renamed from: a */
        public UnmodifiableIterator<K> iterator() {
            return new C0511a(q.this.f10637a.entrySet().iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            return q.this.d(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return q.this.f10637a.size();
        }
    }

    public q(Map<K, V> map) {
        this.f10637a = (Map) Preconditions.checkNotNull(map);
    }

    public void c() {
        this.b = null;
    }

    public final boolean d(@NullableDecl Object obj) {
        return f(obj) != null || this.f10637a.containsKey(obj);
    }

    public V e(@NullableDecl Object obj) {
        V f = f(obj);
        return f != null ? f : g(obj);
    }

    public V f(@NullableDecl Object obj) {
        Map.Entry<K, V> entry = this.b;
        if (entry == null || entry.getKey() != obj) {
            return null;
        }
        return entry.getValue();
    }

    public final V g(@NullableDecl Object obj) {
        return this.f10637a.get(obj);
    }

    @CanIgnoreReturnValue
    public final V h(@NullableDecl K k, @NullableDecl V v) {
        c();
        return this.f10637a.put(k, v);
    }

    @CanIgnoreReturnValue
    public final V i(@NullableDecl Object obj) {
        c();
        return this.f10637a.remove(obj);
    }

    public final Set<K> j() {
        return new a();
    }
}
