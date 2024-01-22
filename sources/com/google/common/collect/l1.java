package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
import com.google.common.collect.l1.i;
import com.google.common.collect.l1.n;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public class l1<K, V, E extends i<K, V, E>, S extends n<K, V, E, S>> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    public static final long CLEANUP_EXECUTOR_DELAY_SECS = 60;
    public static final int CONTAINS_VALUE_RETRIES = 3;
    public static final int DRAIN_MAX = 16;
    public static final int DRAIN_THRESHOLD = 63;
    public static final int MAXIMUM_CAPACITY = 1073741824;
    public static final int MAX_SEGMENTS = 65536;
    public static final f0<Object, Object, e> UNSET_WEAK_VALUE_REFERENCE = new a();
    private static final long serialVersionUID = 5;
    public final int concurrencyLevel;
    public final transient j<K, V, E, S> entryHelper;
    @NullableDecl
    public transient Set<Map.Entry<K, V>> entrySet;
    public final Equivalence<Object> keyEquivalence;
    @NullableDecl
    public transient Set<K> keySet;
    public final transient int segmentMask;
    public final transient int segmentShift;
    public final transient n<K, V, E, S>[] segments;
    @NullableDecl
    public transient Collection<V> values;

    /* loaded from: classes10.dex */
    public class a implements f0<Object, Object, e> {
        @Override // com.google.common.collect.l1.f0
        /* renamed from: c */
        public f0<Object, Object, e> b(ReferenceQueue<Object> referenceQueue, e eVar) {
            return this;
        }

        @Override // com.google.common.collect.l1.f0
        public void clear() {
        }

        @Override // com.google.common.collect.l1.f0
        /* renamed from: d */
        public e a() {
            return null;
        }

        @Override // com.google.common.collect.l1.f0
        public Object get() {
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public static final class a0<K, V> extends d<K, V, a0<K, V>> {
        @NullableDecl
        public volatile V j;

        /* loaded from: classes10.dex */
        public static final class a<K, V> implements j<K, V, a0<K, V>, b0<K, V>> {

            /* renamed from: a  reason: collision with root package name */
            public static final a<?, ?> f10592a = new a<>();

            public static <K, V> a<K, V> h() {
                return (a<K, V>) f10592a;
            }

            @Override // com.google.common.collect.l1.j
            public p b() {
                return p.STRONG;
            }

            @Override // com.google.common.collect.l1.j
            public p e() {
                return p.WEAK;
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: g */
            public a0<K, V> a(b0<K, V> b0Var, a0<K, V> a0Var, @NullableDecl a0<K, V> a0Var2) {
                if (a0Var.getKey() == null) {
                    return null;
                }
                return a0Var.a(((b0) b0Var).queueForKeys, a0Var2);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: i */
            public a0<K, V> d(b0<K, V> b0Var, K k, int i, @NullableDecl a0<K, V> a0Var) {
                return new a0<>(((b0) b0Var).queueForKeys, k, i, a0Var);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: j */
            public b0<K, V> f(l1<K, V, a0<K, V>, b0<K, V>> l1Var, int i, int i2) {
                return new b0<>(l1Var, i, i2);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: k */
            public void c(b0<K, V> b0Var, a0<K, V> a0Var, V v) {
                a0Var.b(v);
            }
        }

        public a0(ReferenceQueue<K> referenceQueue, K k, int i, @NullableDecl a0<K, V> a0Var) {
            super(referenceQueue, k, i, a0Var);
            this.j = null;
        }

        public a0<K, V> a(ReferenceQueue<K> referenceQueue, a0<K, V> a0Var) {
            a0<K, V> a0Var2 = new a0<>(referenceQueue, getKey(), this.h, a0Var);
            a0Var2.b(this.j);
            return a0Var2;
        }

        public void b(V v) {
            this.j = v;
        }

        @Override // com.google.common.collect.l1.i
        @NullableDecl
        public V getValue() {
            return this.j;
        }
    }

    /* loaded from: classes10.dex */
    public static final class b0<K, V> extends n<K, V, a0<K, V>, b0<K, V>> {
        private final ReferenceQueue<K> queueForKeys;

        public b0(l1<K, V, a0<K, V>, b0<K, V>> l1Var, int i, int i2) {
            super(l1Var, i, i2);
            this.queueForKeys = new ReferenceQueue<>();
        }

        @Override // com.google.common.collect.l1.n
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        @Override // com.google.common.collect.l1.n
        public void maybeClearReferenceQueues() {
            clearReferenceQueue((ReferenceQueue<K>) this.queueForKeys);
        }

        @Override // com.google.common.collect.l1.n
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
        }

        @Override // com.google.common.collect.l1.n
        public b0<K, V> self() {
            return this;
        }

        @Override // com.google.common.collect.l1.n
        public a0<K, V> castForTesting(i<K, V, ?> iVar) {
            return (a0) iVar;
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class c<K, V, E extends i<K, V, E>> implements i<K, V, E> {
        public final K h;
        public final int i;
        @NullableDecl
        public final E j;

        public c(K k, int i, @NullableDecl E e) {
            this.h = k;
            this.i = i;
            this.j = e;
        }

        @Override // com.google.common.collect.l1.i
        public int getHash() {
            return this.i;
        }

        @Override // com.google.common.collect.l1.i
        public K getKey() {
            return this.h;
        }

        @Override // com.google.common.collect.l1.i
        public E getNext() {
            return this.j;
        }
    }

    /* loaded from: classes10.dex */
    public static final class c0<K, V> extends d<K, V, c0<K, V>> implements e0<K, V, c0<K, V>> {
        public volatile f0<K, V, c0<K, V>> j;

        /* loaded from: classes10.dex */
        public static final class a<K, V> implements j<K, V, c0<K, V>, d0<K, V>> {

            /* renamed from: a  reason: collision with root package name */
            public static final a<?, ?> f10593a = new a<>();

            public static <K, V> a<K, V> h() {
                return (a<K, V>) f10593a;
            }

            @Override // com.google.common.collect.l1.j
            public p b() {
                return p.WEAK;
            }

            @Override // com.google.common.collect.l1.j
            public p e() {
                return p.WEAK;
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: g */
            public c0<K, V> a(d0<K, V> d0Var, c0<K, V> c0Var, @NullableDecl c0<K, V> c0Var2) {
                if (c0Var.getKey() == null || n.isCollected(c0Var)) {
                    return null;
                }
                return c0Var.c(((d0) d0Var).queueForKeys, ((d0) d0Var).queueForValues, c0Var2);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: i */
            public c0<K, V> d(d0<K, V> d0Var, K k, int i, @NullableDecl c0<K, V> c0Var) {
                return new c0<>(((d0) d0Var).queueForKeys, k, i, c0Var);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: j */
            public d0<K, V> f(l1<K, V, c0<K, V>, d0<K, V>> l1Var, int i, int i2) {
                return new d0<>(l1Var, i, i2);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: k */
            public void c(d0<K, V> d0Var, c0<K, V> c0Var, V v) {
                c0Var.d(v, ((d0) d0Var).queueForValues);
            }
        }

        public c0(ReferenceQueue<K> referenceQueue, K k, int i, @NullableDecl c0<K, V> c0Var) {
            super(referenceQueue, k, i, c0Var);
            this.j = l1.unsetWeakValueReference();
        }

        public c0<K, V> c(ReferenceQueue<K> referenceQueue, ReferenceQueue<V> referenceQueue2, c0<K, V> c0Var) {
            c0<K, V> c0Var2 = new c0<>(referenceQueue, getKey(), this.h, c0Var);
            c0Var2.j = this.j.b(referenceQueue2, c0Var2);
            return c0Var2;
        }

        public void d(V v, ReferenceQueue<V> referenceQueue) {
            f0<K, V, c0<K, V>> f0Var = this.j;
            this.j = new g0(referenceQueue, v, this);
            f0Var.clear();
        }

        @Override // com.google.common.collect.l1.i
        public V getValue() {
            return this.j.get();
        }

        @Override // com.google.common.collect.l1.e0
        public f0<K, V, c0<K, V>> getValueReference() {
            return this.j;
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class d<K, V, E extends i<K, V, E>> extends WeakReference<K> implements i<K, V, E> {
        public final int h;
        @NullableDecl
        public final E i;

        public d(ReferenceQueue<K> referenceQueue, K k, int i, @NullableDecl E e) {
            super(k, referenceQueue);
            this.h = i;
            this.i = e;
        }

        @Override // com.google.common.collect.l1.i
        public int getHash() {
            return this.h;
        }

        @Override // com.google.common.collect.l1.i
        public K getKey() {
            return get();
        }

        @Override // com.google.common.collect.l1.i
        public E getNext() {
            return this.i;
        }
    }

    /* loaded from: classes10.dex */
    public static final class d0<K, V> extends n<K, V, c0<K, V>, d0<K, V>> {
        private final ReferenceQueue<K> queueForKeys;
        private final ReferenceQueue<V> queueForValues;

        public d0(l1<K, V, c0<K, V>, d0<K, V>> l1Var, int i, int i2) {
            super(l1Var, i, i2);
            this.queueForKeys = new ReferenceQueue<>();
            this.queueForValues = new ReferenceQueue<>();
        }

        @Override // com.google.common.collect.l1.n
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        @Override // com.google.common.collect.l1.n
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            return this.queueForValues;
        }

        @Override // com.google.common.collect.l1.n
        public f0<K, V, c0<K, V>> getWeakValueReferenceForTesting(i<K, V, ?> iVar) {
            return castForTesting((i) iVar).getValueReference();
        }

        @Override // com.google.common.collect.l1.n
        public void maybeClearReferenceQueues() {
            clearReferenceQueue((ReferenceQueue<K>) this.queueForKeys);
        }

        @Override // com.google.common.collect.l1.n
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
            drainValueReferenceQueue(this.queueForValues);
        }

        @Override // com.google.common.collect.l1.n
        public f0<K, V, c0<K, V>> newWeakValueReferenceForTesting(i<K, V, ?> iVar, V v) {
            return new g0(this.queueForValues, v, castForTesting((i) iVar));
        }

        @Override // com.google.common.collect.l1.n
        public d0<K, V> self() {
            return this;
        }

        @Override // com.google.common.collect.l1.n
        public void setWeakValueReferenceForTesting(i<K, V, ?> iVar, f0<K, V, ? extends i<K, V, ?>> f0Var) {
            c0<K, V> castForTesting = castForTesting((i) iVar);
            f0 f0Var2 = castForTesting.j;
            castForTesting.j = f0Var;
            f0Var2.clear();
        }

        @Override // com.google.common.collect.l1.n
        public c0<K, V> castForTesting(i<K, V, ?> iVar) {
            return (c0) iVar;
        }
    }

    /* loaded from: classes10.dex */
    public static final class e implements i<Object, Object, e> {
        public e() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.l1.i
        /* renamed from: a */
        public e getNext() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.l1.i
        public int getHash() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.l1.i
        public Object getKey() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.l1.i
        public Object getValue() {
            throw new AssertionError();
        }
    }

    /* loaded from: classes10.dex */
    public interface e0<K, V, E extends i<K, V, E>> extends i<K, V, E> {
        f0<K, V, E> getValueReference();
    }

    /* loaded from: classes10.dex */
    public final class f extends l1<K, V, E, S>.h<Map.Entry<K, V>> {
        public f(l1 l1Var) {
            super();
        }

        @Override // java.util.Iterator
        /* renamed from: f */
        public Map.Entry<K, V> next() {
            return c();
        }
    }

    /* loaded from: classes10.dex */
    public interface f0<K, V, E extends i<K, V, E>> {
        E a();

        f0<K, V, E> b(ReferenceQueue<V> referenceQueue, E e);

        void clear();

        @NullableDecl
        V get();
    }

    /* loaded from: classes10.dex */
    public final class g extends m<Map.Entry<K, V>> {
        public g() {
            super(null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            l1.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Map.Entry entry;
            Object key;
            Object obj2;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (obj2 = l1.this.get(key)) != null && l1.this.valueEquivalence().equivalent(entry.getValue(), obj2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return l1.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new f(l1.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Map.Entry entry;
            Object key;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && l1.this.remove(key, entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return l1.this.size();
        }
    }

    /* loaded from: classes10.dex */
    public static final class g0<K, V, E extends i<K, V, E>> extends WeakReference<V> implements f0<K, V, E> {
        @Weak
        public final E h;

        public g0(ReferenceQueue<V> referenceQueue, V v, E e) {
            super(v, referenceQueue);
            this.h = e;
        }

        @Override // com.google.common.collect.l1.f0
        public E a() {
            return this.h;
        }

        @Override // com.google.common.collect.l1.f0
        public f0<K, V, E> b(ReferenceQueue<V> referenceQueue, E e) {
            return new g0(referenceQueue, get(), e);
        }
    }

    /* loaded from: classes10.dex */
    public abstract class h<T> implements Iterator<T> {
        public int h;
        public int i = -1;
        @NullableDecl
        public n<K, V, E, S> j;
        @NullableDecl
        public AtomicReferenceArray<E> k;
        @NullableDecl
        public E l;
        @NullableDecl
        public l1<K, V, E, S>.h0 m;
        @NullableDecl
        public l1<K, V, E, S>.h0 n;

        public h() {
            this.h = l1.this.segments.length - 1;
            a();
        }

        public final void a() {
            this.m = null;
            if (d() || e()) {
                return;
            }
            while (true) {
                int i = this.h;
                if (i < 0) {
                    return;
                }
                n<K, V, E, S>[] nVarArr = l1.this.segments;
                this.h = i - 1;
                n<K, V, E, S> nVar = nVarArr[i];
                this.j = nVar;
                if (nVar.count != 0) {
                    AtomicReferenceArray<E> atomicReferenceArray = this.j.table;
                    this.k = atomicReferenceArray;
                    this.i = atomicReferenceArray.length() - 1;
                    if (e()) {
                        return;
                    }
                }
            }
        }

        public boolean b(E e) {
            boolean z;
            try {
                Object key = e.getKey();
                Object liveValue = l1.this.getLiveValue(e);
                if (liveValue != null) {
                    this.m = new h0(key, liveValue);
                    z = true;
                } else {
                    z = false;
                }
                return z;
            } finally {
                this.j.postReadCleanup();
            }
        }

        public l1<K, V, E, S>.h0 c() {
            l1<K, V, E, S>.h0 h0Var = this.m;
            if (h0Var != null) {
                this.n = h0Var;
                a();
                return this.n;
            }
            throw new NoSuchElementException();
        }

        public boolean d() {
            E e = this.l;
            if (e == null) {
                return false;
            }
            while (true) {
                this.l = (E) e.getNext();
                E e2 = this.l;
                if (e2 == null) {
                    return false;
                }
                if (b(e2)) {
                    return true;
                }
                e = this.l;
            }
        }

        public boolean e() {
            while (true) {
                int i = this.i;
                if (i < 0) {
                    return false;
                }
                AtomicReferenceArray<E> atomicReferenceArray = this.k;
                this.i = i - 1;
                E e = atomicReferenceArray.get(i);
                this.l = e;
                if (e != null && (b(e) || d())) {
                    return true;
                }
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.m != null;
        }

        @Override // java.util.Iterator
        public void remove() {
            com.google.common.collect.u.e(this.n != null);
            l1.this.remove(this.n.getKey());
            this.n = null;
        }
    }

    /* loaded from: classes10.dex */
    public final class h0 extends com.google.common.collect.f<K, V> {
        public final K h;
        public V i;

        public h0(K k, V v) {
            this.h = k;
            this.i = v;
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return this.h.equals(entry.getKey()) && this.i.equals(entry.getValue());
            }
            return false;
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public K getKey() {
            return this.h;
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public V getValue() {
            return this.i;
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public int hashCode() {
            return this.h.hashCode() ^ this.i.hashCode();
        }

        @Override // com.google.common.collect.f, java.util.Map.Entry
        public V setValue(V v) {
            V v2 = (V) l1.this.put(this.h, v);
            this.i = v;
            return v2;
        }
    }

    /* loaded from: classes10.dex */
    public interface i<K, V, E extends i<K, V, E>> {
        int getHash();

        K getKey();

        E getNext();

        V getValue();
    }

    /* loaded from: classes10.dex */
    public interface j<K, V, E extends i<K, V, E>, S extends n<K, V, E, S>> {
        E a(S s, E e, @NullableDecl E e2);

        p b();

        void c(S s, E e, V v);

        E d(S s, K k, int i, @NullableDecl E e);

        p e();

        S f(l1<K, V, E, S> l1Var, int i, int i2);
    }

    /* loaded from: classes10.dex */
    public final class k extends l1<K, V, E, S>.h<K> {
        public k(l1 l1Var) {
            super();
        }

        @Override // java.util.Iterator
        public K next() {
            return c().getKey();
        }
    }

    /* loaded from: classes10.dex */
    public final class l extends m<K> {
        public l() {
            super(null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            l1.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return l1.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return l1.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new k(l1.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return l1.this.remove(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return l1.this.size();
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class m<E> extends AbstractSet<E> {
        public m() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return l1.toArrayList(this).toArray();
        }

        public /* synthetic */ m(a aVar) {
            this();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) l1.toArrayList(this).toArray(tArr);
        }
    }

    /* loaded from: classes10.dex */
    public static final class o<K, V> extends b<K, V> {
        private static final long serialVersionUID = 3;

        public o(p pVar, p pVar2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i, ConcurrentMap<K, V> concurrentMap) {
            super(pVar, pVar2, equivalence, equivalence2, i, concurrentMap);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.delegate = readMapMaker(objectInputStream).makeMap();
            readEntries(objectInputStream);
        }

        private Object readResolve() {
            return this.delegate;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            writeMapTo(objectOutputStream);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class p {
        public static final p STRONG = new a("STRONG", 0);
        public static final p WEAK = new b("WEAK", 1);
        private static final /* synthetic */ p[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends p {
            public a(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.collect.l1.p
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends p {
            public b(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.collect.l1.p
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        }

        private static /* synthetic */ p[] $values() {
            return new p[]{STRONG, WEAK};
        }

        private p(String str, int i) {
        }

        public static p valueOf(String str) {
            return (p) Enum.valueOf(p.class, str);
        }

        public static p[] values() {
            return (p[]) $VALUES.clone();
        }

        public abstract Equivalence<Object> defaultEquivalence();

        public /* synthetic */ p(String str, int i, a aVar) {
            this(str, i);
        }
    }

    /* loaded from: classes10.dex */
    public static final class q<K> extends c<K, MapMaker.a, q<K>> {

        /* loaded from: classes10.dex */
        public static final class a<K> implements j<K, MapMaker.a, q<K>, r<K>> {

            /* renamed from: a  reason: collision with root package name */
            public static final a<?> f10594a = new a<>();

            public static <K> a<K> h() {
                return (a<K>) f10594a;
            }

            @Override // com.google.common.collect.l1.j
            public p b() {
                return p.STRONG;
            }

            @Override // com.google.common.collect.l1.j
            public p e() {
                return p.STRONG;
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: g */
            public q<K> a(r<K> rVar, q<K> qVar, @NullableDecl q<K> qVar2) {
                return qVar.a(qVar2);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: i */
            public q<K> d(r<K> rVar, K k, int i, @NullableDecl q<K> qVar) {
                return new q<>(k, i, qVar);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: j */
            public r<K> f(l1<K, MapMaker.a, q<K>, r<K>> l1Var, int i, int i2) {
                return new r<>(l1Var, i, i2);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: k */
            public void c(r<K> rVar, q<K> qVar, MapMaker.a aVar) {
            }
        }

        public q(K k, int i, @NullableDecl q<K> qVar) {
            super(k, i, qVar);
        }

        public q<K> a(q<K> qVar) {
            return new q<>(this.h, this.i, qVar);
        }

        @Override // com.google.common.collect.l1.i
        /* renamed from: b */
        public MapMaker.a getValue() {
            return MapMaker.a.VALUE;
        }
    }

    /* loaded from: classes10.dex */
    public static final class r<K> extends n<K, MapMaker.a, q<K>, r<K>> {
        public r(l1<K, MapMaker.a, q<K>, r<K>> l1Var, int i, int i2) {
            super(l1Var, i, i2);
        }

        @Override // com.google.common.collect.l1.n
        public r<K> self() {
            return this;
        }

        @Override // com.google.common.collect.l1.n
        public q<K> castForTesting(i<K, MapMaker.a, ?> iVar) {
            return (q) iVar;
        }
    }

    /* loaded from: classes10.dex */
    public static final class s<K, V> extends c<K, V, s<K, V>> {
        @NullableDecl
        public volatile V k;

        /* loaded from: classes10.dex */
        public static final class a<K, V> implements j<K, V, s<K, V>, t<K, V>> {

            /* renamed from: a  reason: collision with root package name */
            public static final a<?, ?> f10595a = new a<>();

            public static <K, V> a<K, V> h() {
                return (a<K, V>) f10595a;
            }

            @Override // com.google.common.collect.l1.j
            public p b() {
                return p.STRONG;
            }

            @Override // com.google.common.collect.l1.j
            public p e() {
                return p.STRONG;
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: g */
            public s<K, V> a(t<K, V> tVar, s<K, V> sVar, @NullableDecl s<K, V> sVar2) {
                return sVar.a(sVar2);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: i */
            public s<K, V> d(t<K, V> tVar, K k, int i, @NullableDecl s<K, V> sVar) {
                return new s<>(k, i, sVar);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: j */
            public t<K, V> f(l1<K, V, s<K, V>, t<K, V>> l1Var, int i, int i2) {
                return new t<>(l1Var, i, i2);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: k */
            public void c(t<K, V> tVar, s<K, V> sVar, V v) {
                sVar.b(v);
            }
        }

        public s(K k, int i, @NullableDecl s<K, V> sVar) {
            super(k, i, sVar);
            this.k = null;
        }

        public s<K, V> a(s<K, V> sVar) {
            s<K, V> sVar2 = new s<>(this.h, this.i, sVar);
            sVar2.k = this.k;
            return sVar2;
        }

        public void b(V v) {
            this.k = v;
        }

        @Override // com.google.common.collect.l1.i
        @NullableDecl
        public V getValue() {
            return this.k;
        }
    }

    /* loaded from: classes10.dex */
    public static final class t<K, V> extends n<K, V, s<K, V>, t<K, V>> {
        public t(l1<K, V, s<K, V>, t<K, V>> l1Var, int i, int i2) {
            super(l1Var, i, i2);
        }

        @Override // com.google.common.collect.l1.n
        public t<K, V> self() {
            return this;
        }

        @Override // com.google.common.collect.l1.n
        public s<K, V> castForTesting(i<K, V, ?> iVar) {
            return (s) iVar;
        }
    }

    /* loaded from: classes10.dex */
    public static final class u<K, V> extends c<K, V, u<K, V>> implements e0<K, V, u<K, V>> {
        public volatile f0<K, V, u<K, V>> k;

        /* loaded from: classes10.dex */
        public static final class a<K, V> implements j<K, V, u<K, V>, v<K, V>> {

            /* renamed from: a  reason: collision with root package name */
            public static final a<?, ?> f10596a = new a<>();

            public static <K, V> a<K, V> h() {
                return (a<K, V>) f10596a;
            }

            @Override // com.google.common.collect.l1.j
            public p b() {
                return p.WEAK;
            }

            @Override // com.google.common.collect.l1.j
            public p e() {
                return p.STRONG;
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: g */
            public u<K, V> a(v<K, V> vVar, u<K, V> uVar, @NullableDecl u<K, V> uVar2) {
                if (n.isCollected(uVar)) {
                    return null;
                }
                return uVar.c(((v) vVar).queueForValues, uVar2);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: i */
            public u<K, V> d(v<K, V> vVar, K k, int i, @NullableDecl u<K, V> uVar) {
                return new u<>(k, i, uVar);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: j */
            public v<K, V> f(l1<K, V, u<K, V>, v<K, V>> l1Var, int i, int i2) {
                return new v<>(l1Var, i, i2);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: k */
            public void c(v<K, V> vVar, u<K, V> uVar, V v) {
                uVar.d(v, ((v) vVar).queueForValues);
            }
        }

        public u(K k, int i, @NullableDecl u<K, V> uVar) {
            super(k, i, uVar);
            this.k = l1.unsetWeakValueReference();
        }

        public u<K, V> c(ReferenceQueue<V> referenceQueue, u<K, V> uVar) {
            u<K, V> uVar2 = new u<>(this.h, this.i, uVar);
            uVar2.k = this.k.b(referenceQueue, uVar2);
            return uVar2;
        }

        public void d(V v, ReferenceQueue<V> referenceQueue) {
            f0<K, V, u<K, V>> f0Var = this.k;
            this.k = new g0(referenceQueue, v, this);
            f0Var.clear();
        }

        @Override // com.google.common.collect.l1.i
        public V getValue() {
            return this.k.get();
        }

        @Override // com.google.common.collect.l1.e0
        public f0<K, V, u<K, V>> getValueReference() {
            return this.k;
        }
    }

    /* loaded from: classes10.dex */
    public static final class v<K, V> extends n<K, V, u<K, V>, v<K, V>> {
        private final ReferenceQueue<V> queueForValues;

        public v(l1<K, V, u<K, V>, v<K, V>> l1Var, int i, int i2) {
            super(l1Var, i, i2);
            this.queueForValues = new ReferenceQueue<>();
        }

        @Override // com.google.common.collect.l1.n
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            return this.queueForValues;
        }

        @Override // com.google.common.collect.l1.n
        public f0<K, V, u<K, V>> getWeakValueReferenceForTesting(i<K, V, ?> iVar) {
            return castForTesting((i) iVar).getValueReference();
        }

        @Override // com.google.common.collect.l1.n
        public void maybeClearReferenceQueues() {
            clearReferenceQueue((ReferenceQueue<V>) this.queueForValues);
        }

        @Override // com.google.common.collect.l1.n
        public void maybeDrainReferenceQueues() {
            drainValueReferenceQueue(this.queueForValues);
        }

        @Override // com.google.common.collect.l1.n
        public f0<K, V, u<K, V>> newWeakValueReferenceForTesting(i<K, V, ?> iVar, V v) {
            return new g0(this.queueForValues, v, castForTesting((i) iVar));
        }

        @Override // com.google.common.collect.l1.n
        public v<K, V> self() {
            return this;
        }

        @Override // com.google.common.collect.l1.n
        public void setWeakValueReferenceForTesting(i<K, V, ?> iVar, f0<K, V, ? extends i<K, V, ?>> f0Var) {
            u<K, V> castForTesting = castForTesting((i) iVar);
            f0 f0Var2 = castForTesting.k;
            castForTesting.k = f0Var;
            f0Var2.clear();
        }

        @Override // com.google.common.collect.l1.n
        public u<K, V> castForTesting(i<K, V, ?> iVar) {
            return (u) iVar;
        }
    }

    /* loaded from: classes10.dex */
    public final class w extends l1<K, V, E, S>.h<V> {
        public w(l1 l1Var) {
            super();
        }

        @Override // java.util.Iterator
        public V next() {
            return c().getValue();
        }
    }

    /* loaded from: classes10.dex */
    public final class x extends AbstractCollection<V> {
        public x() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            l1.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return l1.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return l1.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new w(l1.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return l1.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return l1.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) l1.toArrayList(this).toArray(tArr);
        }
    }

    /* loaded from: classes10.dex */
    public static final class y<K> extends d<K, MapMaker.a, y<K>> {

        /* loaded from: classes10.dex */
        public static final class a<K> implements j<K, MapMaker.a, y<K>, z<K>> {

            /* renamed from: a  reason: collision with root package name */
            public static final a<?> f10597a = new a<>();

            public static <K> a<K> h() {
                return (a<K>) f10597a;
            }

            @Override // com.google.common.collect.l1.j
            public p b() {
                return p.STRONG;
            }

            @Override // com.google.common.collect.l1.j
            public p e() {
                return p.WEAK;
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: g */
            public y<K> a(z<K> zVar, y<K> yVar, @NullableDecl y<K> yVar2) {
                if (yVar.getKey() == null) {
                    return null;
                }
                return yVar.a(((z) zVar).queueForKeys, yVar2);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: i */
            public y<K> d(z<K> zVar, K k, int i, @NullableDecl y<K> yVar) {
                return new y<>(((z) zVar).queueForKeys, k, i, yVar);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: j */
            public z<K> f(l1<K, MapMaker.a, y<K>, z<K>> l1Var, int i, int i2) {
                return new z<>(l1Var, i, i2);
            }

            @Override // com.google.common.collect.l1.j
            /* renamed from: k */
            public void c(z<K> zVar, y<K> yVar, MapMaker.a aVar) {
            }
        }

        public y(ReferenceQueue<K> referenceQueue, K k, int i, @NullableDecl y<K> yVar) {
            super(referenceQueue, k, i, yVar);
        }

        public y<K> a(ReferenceQueue<K> referenceQueue, y<K> yVar) {
            return new y<>(referenceQueue, getKey(), this.h, yVar);
        }

        @Override // com.google.common.collect.l1.i
        /* renamed from: b */
        public MapMaker.a getValue() {
            return MapMaker.a.VALUE;
        }
    }

    /* loaded from: classes10.dex */
    public static final class z<K> extends n<K, MapMaker.a, y<K>, z<K>> {
        private final ReferenceQueue<K> queueForKeys;

        public z(l1<K, MapMaker.a, y<K>, z<K>> l1Var, int i, int i2) {
            super(l1Var, i, i2);
            this.queueForKeys = new ReferenceQueue<>();
        }

        @Override // com.google.common.collect.l1.n
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        @Override // com.google.common.collect.l1.n
        public void maybeClearReferenceQueues() {
            clearReferenceQueue((ReferenceQueue<K>) this.queueForKeys);
        }

        @Override // com.google.common.collect.l1.n
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
        }

        @Override // com.google.common.collect.l1.n
        public z<K> self() {
            return this;
        }

        @Override // com.google.common.collect.l1.n
        public y<K> castForTesting(i<K, MapMaker.a, ?> iVar) {
            return (y) iVar;
        }
    }

    private l1(MapMaker mapMaker, j<K, V, E, S> jVar) {
        this.concurrencyLevel = Math.min(mapMaker.a(), 65536);
        this.keyEquivalence = mapMaker.c();
        this.entryHelper = jVar;
        int min = Math.min(mapMaker.b(), 1073741824);
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        int i5 = 1;
        while (i5 < this.concurrencyLevel) {
            i4++;
            i5 <<= 1;
        }
        this.segmentShift = 32 - i4;
        this.segmentMask = i5 - 1;
        this.segments = newSegmentArray(i5);
        int i6 = min / i5;
        while (i3 < (i5 * i6 < min ? i6 + 1 : i6)) {
            i3 <<= 1;
        }
        while (true) {
            n<K, V, E, S>[] nVarArr = this.segments;
            if (i2 >= nVarArr.length) {
                return;
            }
            nVarArr[i2] = createSegment(i3, -1);
            i2++;
        }
    }

    public static <K, V> l1<K, V, ? extends i<K, V, ?>, ?> create(MapMaker mapMaker) {
        p d2 = mapMaker.d();
        p pVar = p.STRONG;
        if (d2 == pVar && mapMaker.e() == pVar) {
            return new l1<>(mapMaker, s.a.h());
        }
        if (mapMaker.d() == pVar && mapMaker.e() == p.WEAK) {
            return new l1<>(mapMaker, u.a.h());
        }
        p d3 = mapMaker.d();
        p pVar2 = p.WEAK;
        if (d3 == pVar2 && mapMaker.e() == pVar) {
            return new l1<>(mapMaker, a0.a.h());
        }
        if (mapMaker.d() == pVar2 && mapMaker.e() == pVar2) {
            return new l1<>(mapMaker, c0.a.h());
        }
        throw new AssertionError();
    }

    public static <K> l1<K, MapMaker.a, ? extends i<K, MapMaker.a, ?>, ?> createWithDummyValues(MapMaker mapMaker) {
        p d2 = mapMaker.d();
        p pVar = p.STRONG;
        if (d2 == pVar && mapMaker.e() == pVar) {
            return new l1<>(mapMaker, q.a.h());
        }
        p d3 = mapMaker.d();
        p pVar2 = p.WEAK;
        if (d3 == pVar2 && mapMaker.e() == pVar) {
            return new l1<>(mapMaker, y.a.h());
        }
        if (mapMaker.e() == pVar2) {
            throw new IllegalArgumentException("Map cannot have both weak and dummy values");
        }
        throw new AssertionError();
    }

    public static int rehash(int i2) {
        int i3 = i2 + ((i2 << 15) ^ (-12931));
        int i4 = i3 ^ (i3 >>> 10);
        int i5 = i4 + (i4 << 3);
        int i6 = i5 ^ (i5 >>> 6);
        int i7 = i6 + (i6 << 2) + (i6 << 14);
        return i7 ^ (i7 >>> 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> ArrayList<E> toArrayList(Collection<E> collection) {
        ArrayList<E> arrayList = new ArrayList<>(collection.size());
        Iterators.addAll(arrayList, collection.iterator());
        return arrayList;
    }

    public static <K, V, E extends i<K, V, E>> f0<K, V, E> unsetWeakValueReference() {
        return (f0<K, V, E>) UNSET_WEAK_VALUE_REFERENCE;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (n<K, V, E, S> nVar : this.segments) {
            nVar.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        if (obj == null) {
            return false;
        }
        int hash = hash(obj);
        return segmentFor(hash).containsKey(obj, hash);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [int] */
    /* JADX WARN: Type inference failed for: r11v0, types: [com.google.common.collect.l1$n] */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.google.common.collect.l1$n<K, V, E extends com.google.common.collect.l1$i<K, V, E>, S extends com.google.common.collect.l1$n<K, V, E, S>>[]] */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        boolean z2 = false;
        if (obj == null) {
            return false;
        }
        n<K, V, E, S>[] nVarArr = this.segments;
        long j2 = -1;
        int i2 = 0;
        while (i2 < 3) {
            long j3 = 0;
            int length = nVarArr.length;
            for (int i3 = z2; i3 < length; i3++) {
                ?? r11 = nVarArr[i3];
                int i4 = r11.count;
                AtomicReferenceArray<E> atomicReferenceArray = r11.table;
                for (int i5 = z2; i5 < atomicReferenceArray.length(); i5++) {
                    for (E e2 = atomicReferenceArray.get(i5); e2 != null; e2 = e2.getNext()) {
                        Object liveValue = r11.getLiveValue(e2);
                        if (liveValue != null && valueEquivalence().equivalent(obj, liveValue)) {
                            return true;
                        }
                    }
                }
                j3 += r11.modCount;
                z2 = false;
            }
            if (j3 == j2) {
                return false;
            }
            i2++;
            j2 = j3;
            z2 = false;
        }
        return z2;
    }

    @VisibleForTesting
    public E copyEntry(E e2, E e3) {
        return segmentFor(e2.getHash()).copyEntry(e2, e3);
    }

    public n<K, V, E, S> createSegment(int i2, int i3) {
        return (S) this.entryHelper.f(this, i2, i3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        g gVar = new g();
        this.entrySet = gVar;
        return gVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(@NullableDecl Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return segmentFor(hash).get(obj, hash);
    }

    public E getEntry(@NullableDecl Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return segmentFor(hash).getEntry(obj, hash);
    }

    public V getLiveValue(E e2) {
        if (e2.getKey() == null) {
            return null;
        }
        return (V) e2.getValue();
    }

    public int hash(Object obj) {
        return rehash(this.keyEquivalence.hash(obj));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        n<K, V, E, S>[] nVarArr = this.segments;
        long j2 = 0;
        for (int i2 = 0; i2 < nVarArr.length; i2++) {
            if (nVarArr[i2].count != 0) {
                return false;
            }
            j2 += nVarArr[i2].modCount;
        }
        if (j2 != 0) {
            for (int i3 = 0; i3 < nVarArr.length; i3++) {
                if (nVarArr[i3].count != 0) {
                    return false;
                }
                j2 -= nVarArr[i3].modCount;
            }
            return j2 == 0;
        }
        return true;
    }

    @VisibleForTesting
    public boolean isLiveForTesting(i<K, V, ?> iVar) {
        return segmentFor(iVar.getHash()).getLiveValueForTesting(iVar) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        l lVar = new l();
        this.keySet = lVar;
        return lVar;
    }

    @VisibleForTesting
    public p keyStrength() {
        return this.entryHelper.e();
    }

    public final n<K, V, E, S>[] newSegmentArray(int i2) {
        return new n[i2];
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public V put(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int hash = hash(k2);
        return segmentFor(hash).put(k2, hash, v2, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public V putIfAbsent(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int hash = hash(k2);
        return segmentFor(hash).put(k2, hash, v2, true);
    }

    public void reclaimKey(E e2) {
        int hash = e2.getHash();
        segmentFor(hash).reclaimKey(e2, hash);
    }

    public void reclaimValue(f0<K, V, E> f0Var) {
        E a2 = f0Var.a();
        int hash = a2.getHash();
        segmentFor(hash).reclaimValue((K) a2.getKey(), hash, f0Var);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public V remove(@NullableDecl Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return segmentFor(hash).remove(obj, hash);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public boolean replace(K k2, @NullableDecl V v2, V v3) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v3);
        if (v2 == null) {
            return false;
        }
        int hash = hash(k2);
        return segmentFor(hash).replace(k2, hash, v2, v3);
    }

    public n<K, V, E, S> segmentFor(int i2) {
        return this.segments[(i2 >>> this.segmentShift) & this.segmentMask];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        long j2 = 0;
        for (n<K, V, E, S> nVar : this.segments) {
            j2 += nVar.count;
        }
        return Ints.saturatedCast(j2);
    }

    @VisibleForTesting
    public Equivalence<Object> valueEquivalence() {
        return this.entryHelper.b().defaultEquivalence();
    }

    @VisibleForTesting
    public p valueStrength() {
        return this.entryHelper.b();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        x xVar = new x();
        this.values = xVar;
        return xVar;
    }

    public Object writeReplace() {
        return new o(this.entryHelper.e(), this.entryHelper.b(), this.keyEquivalence, this.entryHelper.b().defaultEquivalence(), this.concurrencyLevel, this);
    }

    /* loaded from: classes10.dex */
    public static abstract class b<K, V> extends ForwardingConcurrentMap<K, V> implements Serializable {
        private static final long serialVersionUID = 3;
        public final int concurrencyLevel;
        public transient ConcurrentMap<K, V> delegate;
        public final Equivalence<Object> keyEquivalence;
        public final p keyStrength;
        public final Equivalence<Object> valueEquivalence;
        public final p valueStrength;

        public b(p pVar, p pVar2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i, ConcurrentMap<K, V> concurrentMap) {
            this.keyStrength = pVar;
            this.valueStrength = pVar2;
            this.keyEquivalence = equivalence;
            this.valueEquivalence = equivalence2;
            this.concurrencyLevel = i;
            this.delegate = concurrentMap;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void readEntries(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            while (true) {
                Object readObject = objectInputStream.readObject();
                if (readObject == null) {
                    return;
                }
                this.delegate.put(readObject, objectInputStream.readObject());
            }
        }

        public MapMaker readMapMaker(ObjectInputStream objectInputStream) throws IOException {
            return new MapMaker().initialCapacity(objectInputStream.readInt()).g(this.keyStrength).h(this.valueStrength).f(this.keyEquivalence).concurrencyLevel(this.concurrencyLevel);
        }

        public void writeMapTo(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeInt(this.delegate.size());
            for (Map.Entry<K, V> entry : this.delegate.entrySet()) {
                objectOutputStream.writeObject(entry.getKey());
                objectOutputStream.writeObject(entry.getValue());
            }
            objectOutputStream.writeObject(null);
        }

        @Override // com.google.common.collect.ForwardingConcurrentMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public ConcurrentMap<K, V> delegate() {
            return this.delegate;
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public boolean remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int hash = hash(obj);
        return segmentFor(hash).remove(obj, hash, obj2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public V replace(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int hash = hash(k2);
        return segmentFor(hash).replace(k2, hash, v2);
    }

    /* loaded from: classes10.dex */
    public static abstract class n<K, V, E extends i<K, V, E>, S extends n<K, V, E, S>> extends ReentrantLock {
        public volatile int count;
        @Weak
        public final l1<K, V, E, S> map;
        public final int maxSegmentSize;
        public int modCount;
        public final AtomicInteger readCount = new AtomicInteger();
        @NullableDecl
        public volatile AtomicReferenceArray<E> table;
        public int threshold;

        public n(l1<K, V, E, S> l1Var, int i, int i2) {
            this.map = l1Var;
            this.maxSegmentSize = i2;
            initTable(newEntryArray(i));
        }

        public static <K, V, E extends i<K, V, E>> boolean isCollected(E e) {
            return e.getValue() == null;
        }

        public abstract E castForTesting(i<K, V, ?> iVar);

        public void clear() {
            if (this.count != 0) {
                lock();
                try {
                    AtomicReferenceArray<E> atomicReferenceArray = this.table;
                    for (int i = 0; i < atomicReferenceArray.length(); i++) {
                        atomicReferenceArray.set(i, null);
                    }
                    maybeClearReferenceQueues();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                }
            }
        }

        public <T> void clearReferenceQueue(ReferenceQueue<T> referenceQueue) {
            do {
            } while (referenceQueue.poll() != null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @CanIgnoreReturnValue
        public boolean clearValueForTesting(K k, int i, f0<K, V, ? extends i<K, V, ?>> f0Var) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                E e = atomicReferenceArray.get(length);
                for (i iVar = e; iVar != null; iVar = iVar.getNext()) {
                    Object key = iVar.getKey();
                    if (iVar.getHash() == i && key != null && this.map.keyEquivalence.equivalent(k, key)) {
                        if (((e0) iVar).getValueReference() == f0Var) {
                            atomicReferenceArray.set(length, removeFromChain(e, iVar));
                            return true;
                        }
                        return false;
                    }
                }
                return false;
            } finally {
                unlock();
            }
        }

        public boolean containsKey(Object obj, int i) {
            try {
                boolean z = false;
                if (this.count != 0) {
                    E liveEntry = getLiveEntry(obj, i);
                    if (liveEntry != null) {
                        if (liveEntry.getValue() != null) {
                            z = true;
                        }
                    }
                    return z;
                }
                return false;
            } finally {
                postReadCleanup();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @VisibleForTesting
        public boolean containsValue(Object obj) {
            try {
                if (this.count != 0) {
                    AtomicReferenceArray<E> atomicReferenceArray = this.table;
                    int length = atomicReferenceArray.length();
                    for (int i = 0; i < length; i++) {
                        for (E e = atomicReferenceArray.get(i); e != null; e = e.getNext()) {
                            Object liveValue = getLiveValue(e);
                            if (liveValue != null && this.map.valueEquivalence().equivalent(obj, liveValue)) {
                                postReadCleanup();
                                return true;
                            }
                        }
                    }
                }
                return false;
            } finally {
                postReadCleanup();
            }
        }

        public E copyEntry(E e, E e2) {
            return this.map.entryHelper.a(self(), e, e2);
        }

        public E copyForTesting(i<K, V, ?> iVar, @NullableDecl i<K, V, ?> iVar2) {
            return this.map.entryHelper.a(self(), castForTesting(iVar), castForTesting(iVar2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @GuardedBy("this")
        public void drainKeyReferenceQueue(ReferenceQueue<K> referenceQueue) {
            int i = 0;
            do {
                Reference<? extends K> poll = referenceQueue.poll();
                if (poll == null) {
                    return;
                }
                this.map.reclaimKey((i) poll);
                i++;
            } while (i != 16);
        }

        @GuardedBy("this")
        public void drainValueReferenceQueue(ReferenceQueue<V> referenceQueue) {
            int i = 0;
            do {
                Reference<? extends V> poll = referenceQueue.poll();
                if (poll == null) {
                    return;
                }
                this.map.reclaimValue((f0) poll);
                i++;
            } while (i != 16);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @GuardedBy("this")
        public void expand() {
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            int length = atomicReferenceArray.length();
            if (length >= 1073741824) {
                return;
            }
            int i = this.count;
            AtomicReferenceArray<E> atomicReferenceArray2 = (AtomicReferenceArray<E>) newEntryArray(length << 1);
            this.threshold = (atomicReferenceArray2.length() * 3) / 4;
            int length2 = atomicReferenceArray2.length() - 1;
            for (int i2 = 0; i2 < length; i2++) {
                E e = atomicReferenceArray.get(i2);
                if (e != null) {
                    i next = e.getNext();
                    int hash = e.getHash() & length2;
                    if (next == null) {
                        atomicReferenceArray2.set(hash, e);
                    } else {
                        i iVar = e;
                        while (next != null) {
                            int hash2 = next.getHash() & length2;
                            if (hash2 != hash) {
                                iVar = next;
                                hash = hash2;
                            }
                            next = next.getNext();
                        }
                        atomicReferenceArray2.set(hash, iVar);
                        while (e != iVar) {
                            int hash3 = e.getHash() & length2;
                            i copyEntry = copyEntry(e, (i) atomicReferenceArray2.get(hash3));
                            if (copyEntry != null) {
                                atomicReferenceArray2.set(hash3, copyEntry);
                            } else {
                                i--;
                            }
                            e = e.getNext();
                        }
                    }
                }
            }
            this.table = atomicReferenceArray2;
            this.count = i;
        }

        public V get(Object obj, int i) {
            try {
                E liveEntry = getLiveEntry(obj, i);
                if (liveEntry == null) {
                    return null;
                }
                V v = (V) liveEntry.getValue();
                if (v == null) {
                    tryDrainReferenceQueues();
                }
                return v;
            } finally {
                postReadCleanup();
            }
        }

        public E getEntry(Object obj, int i) {
            if (this.count != 0) {
                for (E first = getFirst(i); first != null; first = (E) first.getNext()) {
                    if (first.getHash() == i) {
                        Object key = first.getKey();
                        if (key == null) {
                            tryDrainReferenceQueues();
                        } else if (this.map.keyEquivalence.equivalent(obj, key)) {
                            return first;
                        }
                    }
                }
                return null;
            }
            return null;
        }

        public E getFirst(int i) {
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            return atomicReferenceArray.get(i & (atomicReferenceArray.length() - 1));
        }

        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            throw new AssertionError();
        }

        public E getLiveEntry(Object obj, int i) {
            return getEntry(obj, i);
        }

        @NullableDecl
        public V getLiveValue(E e) {
            if (e.getKey() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V v = (V) e.getValue();
            if (v == null) {
                tryDrainReferenceQueues();
                return null;
            }
            return v;
        }

        @NullableDecl
        public V getLiveValueForTesting(i<K, V, ?> iVar) {
            return getLiveValue(castForTesting(iVar));
        }

        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            throw new AssertionError();
        }

        public f0<K, V, E> getWeakValueReferenceForTesting(i<K, V, ?> iVar) {
            throw new AssertionError();
        }

        public void initTable(AtomicReferenceArray<E> atomicReferenceArray) {
            int length = (atomicReferenceArray.length() * 3) / 4;
            this.threshold = length;
            if (length == this.maxSegmentSize) {
                this.threshold = length + 1;
            }
            this.table = atomicReferenceArray;
        }

        public void maybeClearReferenceQueues() {
        }

        @GuardedBy("this")
        public void maybeDrainReferenceQueues() {
        }

        public AtomicReferenceArray<E> newEntryArray(int i) {
            return new AtomicReferenceArray<>(i);
        }

        public E newEntryForTesting(K k, int i, @NullableDecl i<K, V, ?> iVar) {
            return this.map.entryHelper.d(self(), k, i, castForTesting(iVar));
        }

        public f0<K, V, E> newWeakValueReferenceForTesting(i<K, V, ?> iVar, V v) {
            throw new AssertionError();
        }

        public void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                runCleanup();
            }
        }

        @GuardedBy("this")
        public void preWriteCleanup() {
            runLockedCleanup();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public V put(K k, int i, V v, boolean z) {
            lock();
            try {
                preWriteCleanup();
                int i2 = this.count + 1;
                if (i2 > this.threshold) {
                    expand();
                    i2 = this.count + 1;
                }
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                E e = atomicReferenceArray.get(length);
                for (i iVar = e; iVar != null; iVar = iVar.getNext()) {
                    Object key = iVar.getKey();
                    if (iVar.getHash() == i && key != null && this.map.keyEquivalence.equivalent(k, key)) {
                        V v2 = (V) iVar.getValue();
                        if (v2 == null) {
                            this.modCount++;
                            setValue(iVar, v);
                            this.count = this.count;
                            return null;
                        } else if (z) {
                            return v2;
                        } else {
                            this.modCount++;
                            setValue(iVar, v);
                            return v2;
                        }
                    }
                }
                this.modCount++;
                E d = this.map.entryHelper.d(self(), k, i, e);
                setValue(d, v);
                atomicReferenceArray.set(length, d);
                this.count = i2;
                return null;
            } finally {
                unlock();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @CanIgnoreReturnValue
        public boolean reclaimKey(E e, int i) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                E e2 = atomicReferenceArray.get(length);
                for (i iVar = e2; iVar != null; iVar = iVar.getNext()) {
                    if (iVar == e) {
                        this.modCount++;
                        atomicReferenceArray.set(length, removeFromChain(e2, iVar));
                        this.count--;
                        return true;
                    }
                }
                return false;
            } finally {
                unlock();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @CanIgnoreReturnValue
        public boolean reclaimValue(K k, int i, f0<K, V, E> f0Var) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                E e = atomicReferenceArray.get(length);
                for (i iVar = e; iVar != null; iVar = iVar.getNext()) {
                    Object key = iVar.getKey();
                    if (iVar.getHash() == i && key != null && this.map.keyEquivalence.equivalent(k, key)) {
                        if (((e0) iVar).getValueReference() == f0Var) {
                            this.modCount++;
                            atomicReferenceArray.set(length, removeFromChain(e, iVar));
                            this.count--;
                            return true;
                        }
                        return false;
                    }
                }
                return false;
            } finally {
                unlock();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @CanIgnoreReturnValue
        public V remove(Object obj, int i) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                E e = atomicReferenceArray.get(length);
                for (i iVar = e; iVar != null; iVar = iVar.getNext()) {
                    Object key = iVar.getKey();
                    if (iVar.getHash() == i && key != null && this.map.keyEquivalence.equivalent(obj, key)) {
                        V v = (V) iVar.getValue();
                        if (v == null && !isCollected(iVar)) {
                            return null;
                        }
                        this.modCount++;
                        atomicReferenceArray.set(length, removeFromChain(e, iVar));
                        this.count--;
                        return v;
                    }
                }
                return null;
            } finally {
                unlock();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @GuardedBy("this")
        public boolean removeEntryForTesting(E e) {
            int hash = e.getHash();
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            int length = hash & (atomicReferenceArray.length() - 1);
            E e2 = atomicReferenceArray.get(length);
            for (i iVar = e2; iVar != null; iVar = iVar.getNext()) {
                if (iVar == e) {
                    this.modCount++;
                    atomicReferenceArray.set(length, removeFromChain(e2, iVar));
                    this.count--;
                    return true;
                }
            }
            return false;
        }

        @GuardedBy("this")
        public E removeFromChain(E e, E e2) {
            int i = this.count;
            E e3 = (E) e2.getNext();
            while (e != e2) {
                E copyEntry = copyEntry(e, e3);
                if (copyEntry != null) {
                    e3 = copyEntry;
                } else {
                    i--;
                }
                e = (E) e.getNext();
            }
            this.count = i;
            return e3;
        }

        public E removeFromChainForTesting(i<K, V, ?> iVar, i<K, V, ?> iVar2) {
            return removeFromChain(castForTesting(iVar), castForTesting(iVar2));
        }

        @CanIgnoreReturnValue
        public boolean removeTableEntryForTesting(i<K, V, ?> iVar) {
            return removeEntryForTesting(castForTesting(iVar));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean replace(K k, int i, V v, V v2) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                E e = atomicReferenceArray.get(length);
                for (i iVar = e; iVar != null; iVar = iVar.getNext()) {
                    Object key = iVar.getKey();
                    if (iVar.getHash() == i && key != null && this.map.keyEquivalence.equivalent(k, key)) {
                        Object value = iVar.getValue();
                        if (value == null) {
                            if (isCollected(iVar)) {
                                this.modCount++;
                                atomicReferenceArray.set(length, removeFromChain(e, iVar));
                                this.count--;
                            }
                            return false;
                        } else if (this.map.valueEquivalence().equivalent(v, value)) {
                            this.modCount++;
                            setValue(iVar, v2);
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
                return false;
            } finally {
                unlock();
            }
        }

        public void runCleanup() {
            runLockedCleanup();
        }

        public void runLockedCleanup() {
            if (tryLock()) {
                try {
                    maybeDrainReferenceQueues();
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }

        public abstract S self();

        public void setTableEntryForTesting(int i, i<K, V, ?> iVar) {
            this.table.set(i, castForTesting(iVar));
        }

        public void setValue(E e, V v) {
            this.map.entryHelper.c(self(), e, v);
        }

        public void setValueForTesting(i<K, V, ?> iVar, V v) {
            this.map.entryHelper.c(self(), castForTesting(iVar), v);
        }

        public void setWeakValueReferenceForTesting(i<K, V, ?> iVar, f0<K, V, ? extends i<K, V, ?>> f0Var) {
            throw new AssertionError();
        }

        public void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    maybeDrainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x003d, code lost:
            if (r8.map.valueEquivalence().equivalent(r11, r4.getValue()) == false) goto L20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x003f, code lost:
            r5 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0045, code lost:
            if (isCollected(r4) == false) goto L22;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
            r8.modCount++;
            r0.set(r1, removeFromChain(r3, r4));
            r8.count--;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x005b, code lost:
            return r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x005f, code lost:
            return false;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean remove(java.lang.Object r9, int r10, java.lang.Object r11) {
            /*
                r8 = this;
                r8.lock()
                r8.preWriteCleanup()     // Catch: java.lang.Throwable -> L69
                java.util.concurrent.atomic.AtomicReferenceArray<E extends com.google.common.collect.l1$i<K, V, E>> r0 = r8.table     // Catch: java.lang.Throwable -> L69
                int r1 = r0.length()     // Catch: java.lang.Throwable -> L69
                r2 = 1
                int r1 = r1 - r2
                r1 = r1 & r10
                java.lang.Object r3 = r0.get(r1)     // Catch: java.lang.Throwable -> L69
                com.google.common.collect.l1$i r3 = (com.google.common.collect.l1.i) r3     // Catch: java.lang.Throwable -> L69
                r4 = r3
            L16:
                r5 = 0
                if (r4 == 0) goto L65
                java.lang.Object r6 = r4.getKey()     // Catch: java.lang.Throwable -> L69
                int r7 = r4.getHash()     // Catch: java.lang.Throwable -> L69
                if (r7 != r10) goto L60
                if (r6 == 0) goto L60
                com.google.common.collect.l1<K, V, E extends com.google.common.collect.l1$i<K, V, E>, S extends com.google.common.collect.l1$n<K, V, E, S>> r7 = r8.map     // Catch: java.lang.Throwable -> L69
                com.google.common.base.Equivalence<java.lang.Object> r7 = r7.keyEquivalence     // Catch: java.lang.Throwable -> L69
                boolean r6 = r7.equivalent(r9, r6)     // Catch: java.lang.Throwable -> L69
                if (r6 == 0) goto L60
                java.lang.Object r9 = r4.getValue()     // Catch: java.lang.Throwable -> L69
                com.google.common.collect.l1<K, V, E extends com.google.common.collect.l1$i<K, V, E>, S extends com.google.common.collect.l1$n<K, V, E, S>> r10 = r8.map     // Catch: java.lang.Throwable -> L69
                com.google.common.base.Equivalence r10 = r10.valueEquivalence()     // Catch: java.lang.Throwable -> L69
                boolean r9 = r10.equivalent(r11, r9)     // Catch: java.lang.Throwable -> L69
                if (r9 == 0) goto L41
                r5 = r2
                goto L47
            L41:
                boolean r9 = isCollected(r4)     // Catch: java.lang.Throwable -> L69
                if (r9 == 0) goto L5c
            L47:
                int r9 = r8.modCount     // Catch: java.lang.Throwable -> L69
                int r9 = r9 + r2
                r8.modCount = r9     // Catch: java.lang.Throwable -> L69
                com.google.common.collect.l1$i r9 = r8.removeFromChain(r3, r4)     // Catch: java.lang.Throwable -> L69
                int r10 = r8.count     // Catch: java.lang.Throwable -> L69
                int r10 = r10 - r2
                r0.set(r1, r9)     // Catch: java.lang.Throwable -> L69
                r8.count = r10     // Catch: java.lang.Throwable -> L69
                r8.unlock()
                return r5
            L5c:
                r8.unlock()
                return r5
            L60:
                com.google.common.collect.l1$i r4 = r4.getNext()     // Catch: java.lang.Throwable -> L69
                goto L16
            L65:
                r8.unlock()
                return r5
            L69:
                r9 = move-exception
                r8.unlock()
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.l1.n.remove(java.lang.Object, int, java.lang.Object):boolean");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public V replace(K k, int i, V v) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                E e = atomicReferenceArray.get(length);
                for (i iVar = e; iVar != null; iVar = iVar.getNext()) {
                    Object key = iVar.getKey();
                    if (iVar.getHash() == i && key != null && this.map.keyEquivalence.equivalent(k, key)) {
                        V v2 = (V) iVar.getValue();
                        if (v2 == null) {
                            if (isCollected(iVar)) {
                                this.modCount++;
                                atomicReferenceArray.set(length, removeFromChain(e, iVar));
                                this.count--;
                            }
                            return null;
                        }
                        this.modCount++;
                        setValue(iVar, v);
                        return v2;
                    }
                }
                return null;
            } finally {
                unlock();
            }
        }
    }
}
