package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.AbstractSequentialIterator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.ExecutionError;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.RetainedWith;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public class a<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    public static final Logger D = Logger.getLogger(a.class.getName());
    public static final a0<Object, Object> E = new C0458a();
    public static final Queue<?> F = new b();
    @RetainedWith
    @NullableDecl
    public Set<K> A;
    @RetainedWith
    @NullableDecl
    public Collection<V> B;
    @RetainedWith
    @NullableDecl
    public Set<Map.Entry<K, V>> C;
    public final int h;
    public final int i;
    public final r<K, V>[] j;
    public final int k;
    public final Equivalence<Object> l;
    public final Equivalence<Object> m;
    public final t n;
    public final t o;
    public final long p;
    public final Weigher<K, V> q;
    public final long r;
    public final long s;
    public final long t;
    public final Queue<RemovalNotification<K, V>> u;
    public final RemovalListener<K, V> v;
    public final Ticker w;
    public final f x;
    public final AbstractCache.StatsCounter y;
    @NullableDecl
    public final CacheLoader<? super K, V> z;

    /* renamed from: com.google.common.cache.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0458a implements a0<Object, Object> {
        @Override // com.google.common.cache.a.a0
        public com.google.common.cache.e<Object, Object> a() {
            return null;
        }

        @Override // com.google.common.cache.a.a0
        public void b(Object obj) {
        }

        @Override // com.google.common.cache.a.a0
        public boolean c() {
            return false;
        }

        @Override // com.google.common.cache.a.a0
        public a0<Object, Object> d(ReferenceQueue<Object> referenceQueue, @NullableDecl Object obj, com.google.common.cache.e<Object, Object> eVar) {
            return this;
        }

        @Override // com.google.common.cache.a.a0
        public Object e() {
            return null;
        }

        @Override // com.google.common.cache.a.a0
        public Object get() {
            return null;
        }

        @Override // com.google.common.cache.a.a0
        public int getWeight() {
            return 0;
        }

        @Override // com.google.common.cache.a.a0
        public boolean isActive() {
            return false;
        }
    }

    /* loaded from: classes10.dex */
    public interface a0<K, V> {
        @NullableDecl
        com.google.common.cache.e<K, V> a();

        void b(@NullableDecl V v);

        boolean c();

        a0<K, V> d(ReferenceQueue<V> referenceQueue, @NullableDecl V v, com.google.common.cache.e<K, V> eVar);

        V e() throws ExecutionException;

        @NullableDecl
        V get();

        int getWeight();

        boolean isActive();
    }

    /* loaded from: classes10.dex */
    public class b extends AbstractQueue<Object> {
        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Object> iterator() {
            return ImmutableSet.of().iterator();
        }

        @Override // java.util.Queue
        public boolean offer(Object obj) {
            return true;
        }

        @Override // java.util.Queue
        public Object peek() {
            return null;
        }

        @Override // java.util.Queue
        public Object poll() {
            return null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return 0;
        }
    }

    /* loaded from: classes10.dex */
    public final class b0 extends AbstractCollection<V> {
        public b0() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            a.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return a.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return a.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new z(a.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return a.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return a.K(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <E> E[] toArray(E[] eArr) {
            return (E[]) a.K(this).toArray(eArr);
        }
    }

    /* loaded from: classes10.dex */
    public abstract class c<T> extends AbstractSet<T> {
        public c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            a.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return a.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return a.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return a.K(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <E> E[] toArray(E[] eArr) {
            return (E[]) a.K(this).toArray(eArr);
        }
    }

    /* loaded from: classes10.dex */
    public static final class c0<K, V> extends e0<K, V> {
        public volatile long k;
        @Weak
        public com.google.common.cache.e<K, V> l;
        @Weak
        public com.google.common.cache.e<K, V> m;

        public c0(ReferenceQueue<K> referenceQueue, K k, int i, @NullableDecl com.google.common.cache.e<K, V> eVar) {
            super(referenceQueue, k, i, eVar);
            this.k = Long.MAX_VALUE;
            this.l = a.x();
            this.m = a.x();
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public long getAccessTime() {
            return this.k;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNextInAccessQueue() {
            return this.l;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getPreviousInAccessQueue() {
            return this.m;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public void setAccessTime(long j) {
            this.k = j;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public void setNextInAccessQueue(com.google.common.cache.e<K, V> eVar) {
            this.l = eVar;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public void setPreviousInAccessQueue(com.google.common.cache.e<K, V> eVar) {
            this.m = eVar;
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class d<K, V> implements com.google.common.cache.e<K, V> {
        @Override // com.google.common.cache.e
        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public int getHash() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public K getKey() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNext() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public com.google.common.cache.e<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public com.google.common.cache.e<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public a0<K, V> getValueReference() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public void setAccessTime(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public void setNextInAccessQueue(com.google.common.cache.e<K, V> eVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public void setNextInWriteQueue(com.google.common.cache.e<K, V> eVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public void setPreviousInAccessQueue(com.google.common.cache.e<K, V> eVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public void setPreviousInWriteQueue(com.google.common.cache.e<K, V> eVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public void setValueReference(a0<K, V> a0Var) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public void setWriteTime(long j) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes10.dex */
    public static final class d0<K, V> extends e0<K, V> {
        public volatile long k;
        @Weak
        public com.google.common.cache.e<K, V> l;
        @Weak
        public com.google.common.cache.e<K, V> m;
        public volatile long n;
        @Weak
        public com.google.common.cache.e<K, V> o;
        @Weak
        public com.google.common.cache.e<K, V> p;

        public d0(ReferenceQueue<K> referenceQueue, K k, int i, @NullableDecl com.google.common.cache.e<K, V> eVar) {
            super(referenceQueue, k, i, eVar);
            this.k = Long.MAX_VALUE;
            this.l = a.x();
            this.m = a.x();
            this.n = Long.MAX_VALUE;
            this.o = a.x();
            this.p = a.x();
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public long getAccessTime() {
            return this.k;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNextInAccessQueue() {
            return this.l;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNextInWriteQueue() {
            return this.o;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getPreviousInAccessQueue() {
            return this.m;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getPreviousInWriteQueue() {
            return this.p;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public long getWriteTime() {
            return this.n;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public void setAccessTime(long j) {
            this.k = j;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public void setNextInAccessQueue(com.google.common.cache.e<K, V> eVar) {
            this.l = eVar;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public void setNextInWriteQueue(com.google.common.cache.e<K, V> eVar) {
            this.o = eVar;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public void setPreviousInAccessQueue(com.google.common.cache.e<K, V> eVar) {
            this.m = eVar;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public void setPreviousInWriteQueue(com.google.common.cache.e<K, V> eVar) {
            this.p = eVar;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public void setWriteTime(long j) {
            this.n = j;
        }
    }

    /* loaded from: classes10.dex */
    public static final class e<K, V> extends AbstractQueue<com.google.common.cache.e<K, V>> {
        public final com.google.common.cache.e<K, V> h = new C0459a(this);

        /* renamed from: com.google.common.cache.a$e$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0459a extends d<Object, Object> {
            @Weak
            public com.google.common.cache.e<Object, Object> h = this;
            @Weak
            public com.google.common.cache.e<Object, Object> i = this;

            public C0459a(e eVar) {
            }

            @Override // com.google.common.cache.a.d, com.google.common.cache.e
            public long getAccessTime() {
                return Long.MAX_VALUE;
            }

            @Override // com.google.common.cache.a.d, com.google.common.cache.e
            public com.google.common.cache.e<Object, Object> getNextInAccessQueue() {
                return this.h;
            }

            @Override // com.google.common.cache.a.d, com.google.common.cache.e
            public com.google.common.cache.e<Object, Object> getPreviousInAccessQueue() {
                return this.i;
            }

            @Override // com.google.common.cache.a.d, com.google.common.cache.e
            public void setAccessTime(long j) {
            }

            @Override // com.google.common.cache.a.d, com.google.common.cache.e
            public void setNextInAccessQueue(com.google.common.cache.e<Object, Object> eVar) {
                this.h = eVar;
            }

            @Override // com.google.common.cache.a.d, com.google.common.cache.e
            public void setPreviousInAccessQueue(com.google.common.cache.e<Object, Object> eVar) {
                this.i = eVar;
            }
        }

        /* loaded from: classes10.dex */
        public class b extends AbstractSequentialIterator<com.google.common.cache.e<K, V>> {
            public b(com.google.common.cache.e eVar) {
                super(eVar);
            }

            @Override // com.google.common.collect.AbstractSequentialIterator
            /* renamed from: a */
            public com.google.common.cache.e<K, V> computeNext(com.google.common.cache.e<K, V> eVar) {
                com.google.common.cache.e<K, V> nextInAccessQueue = eVar.getNextInAccessQueue();
                if (nextInAccessQueue == e.this.h) {
                    return null;
                }
                return nextInAccessQueue;
            }
        }

        @Override // java.util.Queue
        /* renamed from: a */
        public boolean offer(com.google.common.cache.e<K, V> eVar) {
            a.d(eVar.getPreviousInAccessQueue(), eVar.getNextInAccessQueue());
            a.d(this.h.getPreviousInAccessQueue(), eVar);
            a.d(eVar, this.h);
            return true;
        }

        @Override // java.util.Queue
        /* renamed from: b */
        public com.google.common.cache.e<K, V> peek() {
            com.google.common.cache.e<K, V> nextInAccessQueue = this.h.getNextInAccessQueue();
            if (nextInAccessQueue == this.h) {
                return null;
            }
            return nextInAccessQueue;
        }

        @Override // java.util.Queue
        /* renamed from: c */
        public com.google.common.cache.e<K, V> poll() {
            com.google.common.cache.e<K, V> nextInAccessQueue = this.h.getNextInAccessQueue();
            if (nextInAccessQueue == this.h) {
                return null;
            }
            remove(nextInAccessQueue);
            return nextInAccessQueue;
        }

        @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            com.google.common.cache.e<K, V> nextInAccessQueue = this.h.getNextInAccessQueue();
            while (true) {
                com.google.common.cache.e<K, V> eVar = this.h;
                if (nextInAccessQueue != eVar) {
                    com.google.common.cache.e<K, V> nextInAccessQueue2 = nextInAccessQueue.getNextInAccessQueue();
                    a.y(nextInAccessQueue);
                    nextInAccessQueue = nextInAccessQueue2;
                } else {
                    eVar.setNextInAccessQueue(eVar);
                    com.google.common.cache.e<K, V> eVar2 = this.h;
                    eVar2.setPreviousInAccessQueue(eVar2);
                    return;
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return ((com.google.common.cache.e) obj).getNextInAccessQueue() != q.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.h.getNextInAccessQueue() == this.h;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<com.google.common.cache.e<K, V>> iterator() {
            return new b(peek());
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            com.google.common.cache.e eVar = (com.google.common.cache.e) obj;
            com.google.common.cache.e<K, V> previousInAccessQueue = eVar.getPreviousInAccessQueue();
            com.google.common.cache.e<K, V> nextInAccessQueue = eVar.getNextInAccessQueue();
            a.d(previousInAccessQueue, nextInAccessQueue);
            a.y(eVar);
            return nextInAccessQueue != q.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i = 0;
            for (com.google.common.cache.e<K, V> nextInAccessQueue = this.h.getNextInAccessQueue(); nextInAccessQueue != this.h; nextInAccessQueue = nextInAccessQueue.getNextInAccessQueue()) {
                i++;
            }
            return i;
        }
    }

    /* loaded from: classes10.dex */
    public static class e0<K, V> extends WeakReference<K> implements com.google.common.cache.e<K, V> {
        public final int h;
        @NullableDecl
        public final com.google.common.cache.e<K, V> i;
        public volatile a0<K, V> j;

        public e0(ReferenceQueue<K> referenceQueue, K k, int i, @NullableDecl com.google.common.cache.e<K, V> eVar) {
            super(k, referenceQueue);
            this.j = a.L();
            this.h = i;
            this.i = eVar;
        }

        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public int getHash() {
            return this.h;
        }

        @Override // com.google.common.cache.e
        public K getKey() {
            return get();
        }

        @Override // com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNext() {
            return this.i;
        }

        public com.google.common.cache.e<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public com.google.common.cache.e<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        public com.google.common.cache.e<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        public com.google.common.cache.e<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public a0<K, V> getValueReference() {
            return this.j;
        }

        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        public void setAccessTime(long j) {
            throw new UnsupportedOperationException();
        }

        public void setNextInAccessQueue(com.google.common.cache.e<K, V> eVar) {
            throw new UnsupportedOperationException();
        }

        public void setNextInWriteQueue(com.google.common.cache.e<K, V> eVar) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInAccessQueue(com.google.common.cache.e<K, V> eVar) {
            throw new UnsupportedOperationException();
        }

        public void setPreviousInWriteQueue(com.google.common.cache.e<K, V> eVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.e
        public void setValueReference(a0<K, V> a0Var) {
            this.j = a0Var;
        }

        public void setWriteTime(long j) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class f {
        private static final /* synthetic */ f[] $VALUES;
        public static final int ACCESS_MASK = 1;
        public static final f STRONG;
        public static final f STRONG_ACCESS;
        public static final f STRONG_ACCESS_WRITE;
        public static final f STRONG_WRITE;
        public static final f WEAK;
        public static final f WEAK_ACCESS;
        public static final f WEAK_ACCESS_WRITE;
        public static final int WEAK_MASK = 4;
        public static final f WEAK_WRITE;
        public static final int WRITE_MASK = 2;
        public static final f[] factories;

        /* renamed from: com.google.common.cache.a$f$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public enum C0460a extends f {
            public C0460a(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> newEntry(r<Object, Object> rVar, Object obj, int i, @NullableDecl com.google.common.cache.e<Object, Object> eVar) {
                return new w(obj, i, eVar);
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends f {
            public b(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> copyEntry(r<Object, Object> rVar, com.google.common.cache.e<Object, Object> eVar, com.google.common.cache.e<Object, Object> eVar2) {
                com.google.common.cache.e<K, V> copyEntry = super.copyEntry(rVar, eVar, eVar2);
                copyAccessEntry(eVar, copyEntry);
                return copyEntry;
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> newEntry(r<Object, Object> rVar, Object obj, int i, @NullableDecl com.google.common.cache.e<Object, Object> eVar) {
                return new u(obj, i, eVar);
            }
        }

        /* loaded from: classes10.dex */
        public enum c extends f {
            public c(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> copyEntry(r<Object, Object> rVar, com.google.common.cache.e<Object, Object> eVar, com.google.common.cache.e<Object, Object> eVar2) {
                com.google.common.cache.e<K, V> copyEntry = super.copyEntry(rVar, eVar, eVar2);
                copyWriteEntry(eVar, copyEntry);
                return copyEntry;
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> newEntry(r<Object, Object> rVar, Object obj, int i, @NullableDecl com.google.common.cache.e<Object, Object> eVar) {
                return new y(obj, i, eVar);
            }
        }

        /* loaded from: classes10.dex */
        public enum d extends f {
            public d(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> copyEntry(r<Object, Object> rVar, com.google.common.cache.e<Object, Object> eVar, com.google.common.cache.e<Object, Object> eVar2) {
                com.google.common.cache.e<K, V> copyEntry = super.copyEntry(rVar, eVar, eVar2);
                copyAccessEntry(eVar, copyEntry);
                copyWriteEntry(eVar, copyEntry);
                return copyEntry;
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> newEntry(r<Object, Object> rVar, Object obj, int i, @NullableDecl com.google.common.cache.e<Object, Object> eVar) {
                return new v(obj, i, eVar);
            }
        }

        /* loaded from: classes10.dex */
        public enum e extends f {
            public e(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> newEntry(r<Object, Object> rVar, Object obj, int i, @NullableDecl com.google.common.cache.e<Object, Object> eVar) {
                return new e0(rVar.keyReferenceQueue, obj, i, eVar);
            }
        }

        /* renamed from: com.google.common.cache.a$f$f  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public enum C0461f extends f {
            public C0461f(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> copyEntry(r<Object, Object> rVar, com.google.common.cache.e<Object, Object> eVar, com.google.common.cache.e<Object, Object> eVar2) {
                com.google.common.cache.e<K, V> copyEntry = super.copyEntry(rVar, eVar, eVar2);
                copyAccessEntry(eVar, copyEntry);
                return copyEntry;
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> newEntry(r<Object, Object> rVar, Object obj, int i, @NullableDecl com.google.common.cache.e<Object, Object> eVar) {
                return new c0(rVar.keyReferenceQueue, obj, i, eVar);
            }
        }

        /* loaded from: classes10.dex */
        public enum g extends f {
            public g(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> copyEntry(r<Object, Object> rVar, com.google.common.cache.e<Object, Object> eVar, com.google.common.cache.e<Object, Object> eVar2) {
                com.google.common.cache.e<K, V> copyEntry = super.copyEntry(rVar, eVar, eVar2);
                copyWriteEntry(eVar, copyEntry);
                return copyEntry;
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> newEntry(r<Object, Object> rVar, Object obj, int i, @NullableDecl com.google.common.cache.e<Object, Object> eVar) {
                return new g0(rVar.keyReferenceQueue, obj, i, eVar);
            }
        }

        /* loaded from: classes10.dex */
        public enum h extends f {
            public h(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> copyEntry(r<Object, Object> rVar, com.google.common.cache.e<Object, Object> eVar, com.google.common.cache.e<Object, Object> eVar2) {
                com.google.common.cache.e<K, V> copyEntry = super.copyEntry(rVar, eVar, eVar2);
                copyAccessEntry(eVar, copyEntry);
                copyWriteEntry(eVar, copyEntry);
                return copyEntry;
            }

            @Override // com.google.common.cache.a.f
            public <K, V> com.google.common.cache.e<Object, Object> newEntry(r<Object, Object> rVar, Object obj, int i, @NullableDecl com.google.common.cache.e<Object, Object> eVar) {
                return new d0(rVar.keyReferenceQueue, obj, i, eVar);
            }
        }

        private static /* synthetic */ f[] $values() {
            return new f[]{STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE};
        }

        static {
            C0460a c0460a = new C0460a("STRONG", 0);
            STRONG = c0460a;
            b bVar = new b("STRONG_ACCESS", 1);
            STRONG_ACCESS = bVar;
            c cVar = new c("STRONG_WRITE", 2);
            STRONG_WRITE = cVar;
            d dVar = new d("STRONG_ACCESS_WRITE", 3);
            STRONG_ACCESS_WRITE = dVar;
            e eVar = new e("WEAK", 4);
            WEAK = eVar;
            C0461f c0461f = new C0461f("WEAK_ACCESS", 5);
            WEAK_ACCESS = c0461f;
            g gVar = new g("WEAK_WRITE", 6);
            WEAK_WRITE = gVar;
            h hVar = new h("WEAK_ACCESS_WRITE", 7);
            WEAK_ACCESS_WRITE = hVar;
            $VALUES = $values();
            factories = new f[]{c0460a, bVar, cVar, dVar, eVar, c0461f, gVar, hVar};
        }

        private f(String str, int i) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static f getFactory(t tVar, boolean z, boolean z2) {
            return factories[(tVar == t.WEAK ? 4 : 0) | (z ? 1 : 0) | (z2 ? 2 : 0)];
        }

        public static f valueOf(String str) {
            return (f) Enum.valueOf(f.class, str);
        }

        public static f[] values() {
            return (f[]) $VALUES.clone();
        }

        public <K, V> void copyAccessEntry(com.google.common.cache.e<K, V> eVar, com.google.common.cache.e<K, V> eVar2) {
            eVar2.setAccessTime(eVar.getAccessTime());
            a.d(eVar.getPreviousInAccessQueue(), eVar2);
            a.d(eVar2, eVar.getNextInAccessQueue());
            a.y(eVar);
        }

        public <K, V> com.google.common.cache.e<K, V> copyEntry(r<K, V> rVar, com.google.common.cache.e<K, V> eVar, com.google.common.cache.e<K, V> eVar2) {
            return newEntry(rVar, eVar.getKey(), eVar.getHash(), eVar2);
        }

        public <K, V> void copyWriteEntry(com.google.common.cache.e<K, V> eVar, com.google.common.cache.e<K, V> eVar2) {
            eVar2.setWriteTime(eVar.getWriteTime());
            a.e(eVar.getPreviousInWriteQueue(), eVar2);
            a.e(eVar2, eVar.getNextInWriteQueue());
            a.z(eVar);
        }

        public abstract <K, V> com.google.common.cache.e<K, V> newEntry(r<K, V> rVar, K k, int i, @NullableDecl com.google.common.cache.e<K, V> eVar);

        public /* synthetic */ f(String str, int i, C0458a c0458a) {
            this(str, i);
        }
    }

    /* loaded from: classes10.dex */
    public static class f0<K, V> extends WeakReference<V> implements a0<K, V> {
        public final com.google.common.cache.e<K, V> h;

        public f0(ReferenceQueue<V> referenceQueue, V v, com.google.common.cache.e<K, V> eVar) {
            super(v, referenceQueue);
            this.h = eVar;
        }

        @Override // com.google.common.cache.a.a0
        public com.google.common.cache.e<K, V> a() {
            return this.h;
        }

        @Override // com.google.common.cache.a.a0
        public void b(V v) {
        }

        @Override // com.google.common.cache.a.a0
        public boolean c() {
            return false;
        }

        @Override // com.google.common.cache.a.a0
        public a0<K, V> d(ReferenceQueue<V> referenceQueue, V v, com.google.common.cache.e<K, V> eVar) {
            return new f0(referenceQueue, v, eVar);
        }

        @Override // com.google.common.cache.a.a0
        public V e() {
            return get();
        }

        @Override // com.google.common.cache.a.a0
        public int getWeight() {
            return 1;
        }

        @Override // com.google.common.cache.a.a0
        public boolean isActive() {
            return true;
        }
    }

    /* loaded from: classes10.dex */
    public final class g extends a<K, V>.i<Map.Entry<K, V>> {
        public g(a aVar) {
            super();
        }

        @Override // java.util.Iterator
        /* renamed from: f */
        public Map.Entry<K, V> next() {
            return c();
        }
    }

    /* loaded from: classes10.dex */
    public static final class g0<K, V> extends e0<K, V> {
        public volatile long k;
        @Weak
        public com.google.common.cache.e<K, V> l;
        @Weak
        public com.google.common.cache.e<K, V> m;

        public g0(ReferenceQueue<K> referenceQueue, K k, int i, @NullableDecl com.google.common.cache.e<K, V> eVar) {
            super(referenceQueue, k, i, eVar);
            this.k = Long.MAX_VALUE;
            this.l = a.x();
            this.m = a.x();
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNextInWriteQueue() {
            return this.l;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getPreviousInWriteQueue() {
            return this.m;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public long getWriteTime() {
            return this.k;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public void setNextInWriteQueue(com.google.common.cache.e<K, V> eVar) {
            this.l = eVar;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public void setPreviousInWriteQueue(com.google.common.cache.e<K, V> eVar) {
            this.m = eVar;
        }

        @Override // com.google.common.cache.a.e0, com.google.common.cache.e
        public void setWriteTime(long j) {
            this.k = j;
        }
    }

    /* loaded from: classes10.dex */
    public final class h extends a<K, V>.c<Map.Entry<K, V>> {
        public h() {
            super();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Map.Entry entry;
            Object key;
            Object obj2;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (obj2 = a.this.get(key)) != null && a.this.m.equivalent(entry.getValue(), obj2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new g(a.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Map.Entry entry;
            Object key;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && a.this.remove(key, entry.getValue());
        }
    }

    /* loaded from: classes10.dex */
    public static final class h0<K, V> extends s<K, V> {
        public final int i;

        public h0(ReferenceQueue<V> referenceQueue, V v, com.google.common.cache.e<K, V> eVar, int i) {
            super(referenceQueue, v, eVar);
            this.i = i;
        }

        @Override // com.google.common.cache.a.s, com.google.common.cache.a.a0
        public a0<K, V> d(ReferenceQueue<V> referenceQueue, V v, com.google.common.cache.e<K, V> eVar) {
            return new h0(referenceQueue, v, eVar, this.i);
        }

        @Override // com.google.common.cache.a.s, com.google.common.cache.a.a0
        public int getWeight() {
            return this.i;
        }
    }

    /* loaded from: classes10.dex */
    public abstract class i<T> implements Iterator<T> {
        public int h;
        public int i = -1;
        @NullableDecl
        public r<K, V> j;
        @NullableDecl
        public AtomicReferenceArray<com.google.common.cache.e<K, V>> k;
        @NullableDecl
        public com.google.common.cache.e<K, V> l;
        @NullableDecl
        public a<K, V>.l0 m;
        @NullableDecl
        public a<K, V>.l0 n;

        public i() {
            this.h = a.this.j.length - 1;
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
                r<K, V>[] rVarArr = a.this.j;
                this.h = i - 1;
                r<K, V> rVar = rVarArr[i];
                this.j = rVar;
                if (rVar.count != 0) {
                    AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.j.table;
                    this.k = atomicReferenceArray;
                    this.i = atomicReferenceArray.length() - 1;
                    if (e()) {
                        return;
                    }
                }
            }
        }

        public boolean b(com.google.common.cache.e<K, V> eVar) {
            boolean z;
            try {
                long read = a.this.w.read();
                K key = eVar.getKey();
                Object p = a.this.p(eVar, read);
                if (p != null) {
                    this.m = new l0(key, p);
                    z = true;
                } else {
                    z = false;
                }
                return z;
            } finally {
                this.j.postReadCleanup();
            }
        }

        public a<K, V>.l0 c() {
            a<K, V>.l0 l0Var = this.m;
            if (l0Var != null) {
                this.n = l0Var;
                a();
                return this.n;
            }
            throw new NoSuchElementException();
        }

        public boolean d() {
            com.google.common.cache.e<K, V> eVar = this.l;
            if (eVar == null) {
                return false;
            }
            while (true) {
                this.l = eVar.getNext();
                com.google.common.cache.e<K, V> eVar2 = this.l;
                if (eVar2 == null) {
                    return false;
                }
                if (b(eVar2)) {
                    return true;
                }
                eVar = this.l;
            }
        }

        public boolean e() {
            while (true) {
                int i = this.i;
                if (i < 0) {
                    return false;
                }
                AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.k;
                this.i = i - 1;
                com.google.common.cache.e<K, V> eVar = atomicReferenceArray.get(i);
                this.l = eVar;
                if (eVar != null && (b(eVar) || d())) {
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
            Preconditions.checkState(this.n != null);
            a.this.remove(this.n.getKey());
            this.n = null;
        }
    }

    /* loaded from: classes10.dex */
    public static final class i0<K, V> extends x<K, V> {
        public final int i;

        public i0(V v, int i) {
            super(v);
            this.i = i;
        }

        @Override // com.google.common.cache.a.x, com.google.common.cache.a.a0
        public int getWeight() {
            return this.i;
        }
    }

    /* loaded from: classes10.dex */
    public final class j extends a<K, V>.i<K> {
        public j(a aVar) {
            super();
        }

        @Override // java.util.Iterator
        public K next() {
            return c().getKey();
        }
    }

    /* loaded from: classes10.dex */
    public static final class j0<K, V> extends f0<K, V> {
        public final int i;

        public j0(ReferenceQueue<V> referenceQueue, V v, com.google.common.cache.e<K, V> eVar, int i) {
            super(referenceQueue, v, eVar);
            this.i = i;
        }

        @Override // com.google.common.cache.a.f0, com.google.common.cache.a.a0
        public a0<K, V> d(ReferenceQueue<V> referenceQueue, V v, com.google.common.cache.e<K, V> eVar) {
            return new j0(referenceQueue, v, eVar, this.i);
        }

        @Override // com.google.common.cache.a.f0, com.google.common.cache.a.a0
        public int getWeight() {
            return this.i;
        }
    }

    /* loaded from: classes10.dex */
    public final class k extends a<K, V>.c<K> {
        public k() {
            super();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return a.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new j(a.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return a.this.remove(obj) != null;
        }
    }

    /* loaded from: classes10.dex */
    public static final class k0<K, V> extends AbstractQueue<com.google.common.cache.e<K, V>> {
        public final com.google.common.cache.e<K, V> h = new C0462a(this);

        /* renamed from: com.google.common.cache.a$k0$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0462a extends d<Object, Object> {
            @Weak
            public com.google.common.cache.e<Object, Object> h = this;
            @Weak
            public com.google.common.cache.e<Object, Object> i = this;

            public C0462a(k0 k0Var) {
            }

            @Override // com.google.common.cache.a.d, com.google.common.cache.e
            public com.google.common.cache.e<Object, Object> getNextInWriteQueue() {
                return this.h;
            }

            @Override // com.google.common.cache.a.d, com.google.common.cache.e
            public com.google.common.cache.e<Object, Object> getPreviousInWriteQueue() {
                return this.i;
            }

            @Override // com.google.common.cache.a.d, com.google.common.cache.e
            public long getWriteTime() {
                return Long.MAX_VALUE;
            }

            @Override // com.google.common.cache.a.d, com.google.common.cache.e
            public void setNextInWriteQueue(com.google.common.cache.e<Object, Object> eVar) {
                this.h = eVar;
            }

            @Override // com.google.common.cache.a.d, com.google.common.cache.e
            public void setPreviousInWriteQueue(com.google.common.cache.e<Object, Object> eVar) {
                this.i = eVar;
            }

            @Override // com.google.common.cache.a.d, com.google.common.cache.e
            public void setWriteTime(long j) {
            }
        }

        /* loaded from: classes10.dex */
        public class b extends AbstractSequentialIterator<com.google.common.cache.e<K, V>> {
            public b(com.google.common.cache.e eVar) {
                super(eVar);
            }

            @Override // com.google.common.collect.AbstractSequentialIterator
            /* renamed from: a */
            public com.google.common.cache.e<K, V> computeNext(com.google.common.cache.e<K, V> eVar) {
                com.google.common.cache.e<K, V> nextInWriteQueue = eVar.getNextInWriteQueue();
                if (nextInWriteQueue == k0.this.h) {
                    return null;
                }
                return nextInWriteQueue;
            }
        }

        @Override // java.util.Queue
        /* renamed from: a */
        public boolean offer(com.google.common.cache.e<K, V> eVar) {
            a.e(eVar.getPreviousInWriteQueue(), eVar.getNextInWriteQueue());
            a.e(this.h.getPreviousInWriteQueue(), eVar);
            a.e(eVar, this.h);
            return true;
        }

        @Override // java.util.Queue
        /* renamed from: b */
        public com.google.common.cache.e<K, V> peek() {
            com.google.common.cache.e<K, V> nextInWriteQueue = this.h.getNextInWriteQueue();
            if (nextInWriteQueue == this.h) {
                return null;
            }
            return nextInWriteQueue;
        }

        @Override // java.util.Queue
        /* renamed from: c */
        public com.google.common.cache.e<K, V> poll() {
            com.google.common.cache.e<K, V> nextInWriteQueue = this.h.getNextInWriteQueue();
            if (nextInWriteQueue == this.h) {
                return null;
            }
            remove(nextInWriteQueue);
            return nextInWriteQueue;
        }

        @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            com.google.common.cache.e<K, V> nextInWriteQueue = this.h.getNextInWriteQueue();
            while (true) {
                com.google.common.cache.e<K, V> eVar = this.h;
                if (nextInWriteQueue != eVar) {
                    com.google.common.cache.e<K, V> nextInWriteQueue2 = nextInWriteQueue.getNextInWriteQueue();
                    a.z(nextInWriteQueue);
                    nextInWriteQueue = nextInWriteQueue2;
                } else {
                    eVar.setNextInWriteQueue(eVar);
                    com.google.common.cache.e<K, V> eVar2 = this.h;
                    eVar2.setPreviousInWriteQueue(eVar2);
                    return;
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return ((com.google.common.cache.e) obj).getNextInWriteQueue() != q.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.h.getNextInWriteQueue() == this.h;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<com.google.common.cache.e<K, V>> iterator() {
            return new b(peek());
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            com.google.common.cache.e eVar = (com.google.common.cache.e) obj;
            com.google.common.cache.e<K, V> previousInWriteQueue = eVar.getPreviousInWriteQueue();
            com.google.common.cache.e<K, V> nextInWriteQueue = eVar.getNextInWriteQueue();
            a.e(previousInWriteQueue, nextInWriteQueue);
            a.z(eVar);
            return nextInWriteQueue != q.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i = 0;
            for (com.google.common.cache.e<K, V> nextInWriteQueue = this.h.getNextInWriteQueue(); nextInWriteQueue != this.h; nextInWriteQueue = nextInWriteQueue.getNextInWriteQueue()) {
                i++;
            }
            return i;
        }
    }

    /* loaded from: classes10.dex */
    public static final class l<K, V> extends p<K, V> implements LoadingCache<K, V> {
        private static final long serialVersionUID = 1;
        @NullableDecl
        public transient LoadingCache<K, V> autoDelegate;

        public l(a<K, V> aVar) {
            super(aVar);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.autoDelegate = (LoadingCache<K, V>) recreateCacheBuilder().build((CacheLoader<? super K, V>) this.loader);
        }

        private Object readResolve() {
            return this.autoDelegate;
        }

        @Override // com.google.common.cache.LoadingCache, com.google.common.base.Function
        public final V apply(K k) {
            return this.autoDelegate.apply(k);
        }

        @Override // com.google.common.cache.LoadingCache
        public V get(K k) throws ExecutionException {
            return this.autoDelegate.get(k);
        }

        @Override // com.google.common.cache.LoadingCache
        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
            return this.autoDelegate.getAll(iterable);
        }

        @Override // com.google.common.cache.LoadingCache
        public V getUnchecked(K k) {
            return this.autoDelegate.getUnchecked(k);
        }

        @Override // com.google.common.cache.LoadingCache
        public void refresh(K k) {
            this.autoDelegate.refresh(k);
        }
    }

    /* loaded from: classes10.dex */
    public final class l0 implements Map.Entry<K, V> {
        public final K h;
        public V i;

        public l0(K k, V v) {
            this.h = k;
            this.i = v;
        }

        @Override // java.util.Map.Entry
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return this.h.equals(entry.getKey()) && this.i.equals(entry.getValue());
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.h;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.i;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.h.hashCode() ^ this.i.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = (V) a.this.put(this.h, v);
            this.i = v;
            return v2;
        }

        public String toString() {
            String valueOf = String.valueOf(getKey());
            String valueOf2 = String.valueOf(getValue());
            StringBuilder sb = new StringBuilder(valueOf.length() + 1 + valueOf2.length());
            sb.append(valueOf);
            sb.append("=");
            sb.append(valueOf2);
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static class m<K, V> implements a0<K, V> {
        public volatile a0<K, V> h;
        public final SettableFuture<V> i;
        public final Stopwatch j;

        /* renamed from: com.google.common.cache.a$m$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0463a implements Function<V, V> {
            public C0463a() {
            }

            @Override // com.google.common.base.Function
            public V apply(V v) {
                m.this.j(v);
                return v;
            }
        }

        public m() {
            this(a.L());
        }

        @Override // com.google.common.cache.a.a0
        public com.google.common.cache.e<K, V> a() {
            return null;
        }

        @Override // com.google.common.cache.a.a0
        public void b(@NullableDecl V v) {
            if (v != null) {
                j(v);
            } else {
                this.h = a.L();
            }
        }

        @Override // com.google.common.cache.a.a0
        public boolean c() {
            return true;
        }

        @Override // com.google.common.cache.a.a0
        public a0<K, V> d(ReferenceQueue<V> referenceQueue, @NullableDecl V v, com.google.common.cache.e<K, V> eVar) {
            return this;
        }

        @Override // com.google.common.cache.a.a0
        public V e() throws ExecutionException {
            return (V) Uninterruptibles.getUninterruptibly(this.i);
        }

        public long f() {
            return this.j.elapsed(TimeUnit.NANOSECONDS);
        }

        public final ListenableFuture<V> g(Throwable th) {
            return Futures.immediateFailedFuture(th);
        }

        @Override // com.google.common.cache.a.a0
        public V get() {
            return this.h.get();
        }

        @Override // com.google.common.cache.a.a0
        public int getWeight() {
            return this.h.getWeight();
        }

        public a0<K, V> h() {
            return this.h;
        }

        public ListenableFuture<V> i(K k, CacheLoader<? super K, V> cacheLoader) {
            try {
                this.j.start();
                V v = this.h.get();
                if (v == null) {
                    V load = cacheLoader.load(k);
                    return j(load) ? this.i : Futures.immediateFuture(load);
                }
                ListenableFuture<V> reload = cacheLoader.reload(k, v);
                if (reload == null) {
                    return Futures.immediateFuture(null);
                }
                return Futures.transform(reload, new C0463a(), MoreExecutors.directExecutor());
            } catch (Throwable th) {
                ListenableFuture<V> g = k(th) ? this.i : g(th);
                if (th instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                return g;
            }
        }

        @Override // com.google.common.cache.a.a0
        public boolean isActive() {
            return this.h.isActive();
        }

        public boolean j(@NullableDecl V v) {
            return this.i.set(v);
        }

        public boolean k(Throwable th) {
            return this.i.setException(th);
        }

        public m(a0<K, V> a0Var) {
            this.i = SettableFuture.create();
            this.j = Stopwatch.createUnstarted();
            this.h = a0Var;
        }
    }

    /* loaded from: classes10.dex */
    public static class n<K, V> extends o<K, V> implements LoadingCache<K, V> {
        private static final long serialVersionUID = 1;

        public n(CacheBuilder<? super K, ? super V> cacheBuilder, CacheLoader<? super K, V> cacheLoader) {
            super(new a(cacheBuilder, (CacheLoader) Preconditions.checkNotNull(cacheLoader)), null);
        }

        @Override // com.google.common.cache.LoadingCache, com.google.common.base.Function
        public final V apply(K k) {
            return getUnchecked(k);
        }

        @Override // com.google.common.cache.LoadingCache
        public V get(K k) throws ExecutionException {
            return this.localCache.q(k);
        }

        @Override // com.google.common.cache.LoadingCache
        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
            return this.localCache.m(iterable);
        }

        @Override // com.google.common.cache.LoadingCache
        public V getUnchecked(K k) {
            try {
                return get(k);
            } catch (ExecutionException e) {
                throw new UncheckedExecutionException(e.getCause());
            }
        }

        @Override // com.google.common.cache.LoadingCache
        public void refresh(K k) {
            this.localCache.G(k);
        }

        @Override // com.google.common.cache.a.o
        public Object writeReplace() {
            return new l(this.localCache);
        }
    }

    /* loaded from: classes10.dex */
    public static class o<K, V> implements Cache<K, V>, Serializable {
        private static final long serialVersionUID = 1;
        public final a<K, V> localCache;

        /* renamed from: com.google.common.cache.a$o$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0464a extends CacheLoader<Object, V> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Callable f10544a;

            public C0464a(o oVar, Callable callable) {
                this.f10544a = callable;
            }

            @Override // com.google.common.cache.CacheLoader
            public V load(Object obj) throws Exception {
                return (V) this.f10544a.call();
            }
        }

        public /* synthetic */ o(a aVar, C0458a c0458a) {
            this(aVar);
        }

        @Override // com.google.common.cache.Cache
        public ConcurrentMap<K, V> asMap() {
            return this.localCache;
        }

        @Override // com.google.common.cache.Cache
        public void cleanUp() {
            this.localCache.b();
        }

        @Override // com.google.common.cache.Cache
        public V get(K k, Callable<? extends V> callable) throws ExecutionException {
            Preconditions.checkNotNull(callable);
            return this.localCache.l(k, new C0464a(this, callable));
        }

        @Override // com.google.common.cache.Cache
        public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
            return this.localCache.n(iterable);
        }

        @Override // com.google.common.cache.Cache
        @NullableDecl
        public V getIfPresent(Object obj) {
            return this.localCache.o(obj);
        }

        @Override // com.google.common.cache.Cache
        public void invalidate(Object obj) {
            Preconditions.checkNotNull(obj);
            this.localCache.remove(obj);
        }

        @Override // com.google.common.cache.Cache
        public void invalidateAll(Iterable<?> iterable) {
            this.localCache.s(iterable);
        }

        @Override // com.google.common.cache.Cache
        public void put(K k, V v) {
            this.localCache.put(k, v);
        }

        @Override // com.google.common.cache.Cache
        public void putAll(Map<? extends K, ? extends V> map) {
            this.localCache.putAll(map);
        }

        @Override // com.google.common.cache.Cache
        public long size() {
            return this.localCache.v();
        }

        @Override // com.google.common.cache.Cache
        public CacheStats stats() {
            AbstractCache.SimpleStatsCounter simpleStatsCounter = new AbstractCache.SimpleStatsCounter();
            simpleStatsCounter.incrementBy(this.localCache.y);
            for (r<K, V> rVar : this.localCache.j) {
                simpleStatsCounter.incrementBy(rVar.statsCounter);
            }
            return simpleStatsCounter.snapshot();
        }

        Object writeReplace() {
            return new p(this.localCache);
        }

        public o(CacheBuilder<? super K, ? super V> cacheBuilder) {
            this(new a(cacheBuilder, null));
        }

        @Override // com.google.common.cache.Cache
        public void invalidateAll() {
            this.localCache.clear();
        }

        private o(a<K, V> aVar) {
            this.localCache = aVar;
        }
    }

    /* loaded from: classes10.dex */
    public static class p<K, V> extends ForwardingCache<K, V> implements Serializable {
        private static final long serialVersionUID = 1;
        public final int concurrencyLevel;
        @NullableDecl
        public transient Cache<K, V> delegate;
        public final long expireAfterAccessNanos;
        public final long expireAfterWriteNanos;
        public final Equivalence<Object> keyEquivalence;
        public final t keyStrength;
        public final CacheLoader<? super K, V> loader;
        public final long maxWeight;
        public final RemovalListener<? super K, ? super V> removalListener;
        @NullableDecl
        public final Ticker ticker;
        public final Equivalence<Object> valueEquivalence;
        public final t valueStrength;
        public final Weigher<K, V> weigher;

        public p(a<K, V> aVar) {
            this(aVar.n, aVar.o, aVar.l, aVar.m, aVar.s, aVar.r, aVar.p, aVar.q, aVar.k, aVar.v, aVar.w, aVar.z);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.delegate = (Cache<K, V>) recreateCacheBuilder().build();
        }

        private Object readResolve() {
            return this.delegate;
        }

        public CacheBuilder<K, V> recreateCacheBuilder() {
            CacheBuilder<K, V> cacheBuilder = (CacheBuilder<K, V>) CacheBuilder.newBuilder().s(this.keyStrength).t(this.valueStrength).q(this.keyEquivalence).u(this.valueEquivalence).concurrencyLevel(this.concurrencyLevel).removalListener((RemovalListener<? super K, ? super V>) this.removalListener);
            cacheBuilder.f10537a = false;
            long j = this.expireAfterWriteNanos;
            if (j > 0) {
                cacheBuilder.expireAfterWrite(j, TimeUnit.NANOSECONDS);
            }
            long j2 = this.expireAfterAccessNanos;
            if (j2 > 0) {
                cacheBuilder.expireAfterAccess(j2, TimeUnit.NANOSECONDS);
            }
            Weigher weigher = this.weigher;
            if (weigher != CacheBuilder.e.INSTANCE) {
                cacheBuilder.weigher(weigher);
                long j3 = this.maxWeight;
                if (j3 != -1) {
                    cacheBuilder.maximumWeight(j3);
                }
            } else {
                long j4 = this.maxWeight;
                if (j4 != -1) {
                    cacheBuilder.maximumSize(j4);
                }
            }
            Ticker ticker = this.ticker;
            if (ticker != null) {
                cacheBuilder.ticker(ticker);
            }
            return cacheBuilder;
        }

        private p(t tVar, t tVar2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long j, long j2, long j3, Weigher<K, V> weigher, int i, RemovalListener<? super K, ? super V> removalListener, Ticker ticker, CacheLoader<? super K, V> cacheLoader) {
            this.keyStrength = tVar;
            this.valueStrength = tVar2;
            this.keyEquivalence = equivalence;
            this.valueEquivalence = equivalence2;
            this.expireAfterWriteNanos = j;
            this.expireAfterAccessNanos = j2;
            this.maxWeight = j3;
            this.weigher = weigher;
            this.concurrencyLevel = i;
            this.removalListener = removalListener;
            this.ticker = (ticker == Ticker.systemTicker() || ticker == CacheBuilder.t) ? null : null;
            this.loader = cacheLoader;
        }

        @Override // com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
        public Cache<K, V> delegate() {
            return this.delegate;
        }
    }

    /* loaded from: classes10.dex */
    public enum q implements com.google.common.cache.e<Object, Object> {
        INSTANCE;

        @Override // com.google.common.cache.e
        public long getAccessTime() {
            return 0L;
        }

        @Override // com.google.common.cache.e
        public int getHash() {
            return 0;
        }

        @Override // com.google.common.cache.e
        public Object getKey() {
            return null;
        }

        @Override // com.google.common.cache.e
        public com.google.common.cache.e<Object, Object> getNext() {
            return null;
        }

        @Override // com.google.common.cache.e
        public com.google.common.cache.e<Object, Object> getNextInAccessQueue() {
            return this;
        }

        @Override // com.google.common.cache.e
        public com.google.common.cache.e<Object, Object> getNextInWriteQueue() {
            return this;
        }

        @Override // com.google.common.cache.e
        public com.google.common.cache.e<Object, Object> getPreviousInAccessQueue() {
            return this;
        }

        @Override // com.google.common.cache.e
        public com.google.common.cache.e<Object, Object> getPreviousInWriteQueue() {
            return this;
        }

        @Override // com.google.common.cache.e
        public a0<Object, Object> getValueReference() {
            return null;
        }

        @Override // com.google.common.cache.e
        public long getWriteTime() {
            return 0L;
        }

        @Override // com.google.common.cache.e
        public void setAccessTime(long j) {
        }

        @Override // com.google.common.cache.e
        public void setNextInAccessQueue(com.google.common.cache.e<Object, Object> eVar) {
        }

        @Override // com.google.common.cache.e
        public void setNextInWriteQueue(com.google.common.cache.e<Object, Object> eVar) {
        }

        @Override // com.google.common.cache.e
        public void setPreviousInAccessQueue(com.google.common.cache.e<Object, Object> eVar) {
        }

        @Override // com.google.common.cache.e
        public void setPreviousInWriteQueue(com.google.common.cache.e<Object, Object> eVar) {
        }

        @Override // com.google.common.cache.e
        public void setValueReference(a0<Object, Object> a0Var) {
        }

        @Override // com.google.common.cache.e
        public void setWriteTime(long j) {
        }
    }

    /* loaded from: classes10.dex */
    public static class s<K, V> extends SoftReference<V> implements a0<K, V> {
        public final com.google.common.cache.e<K, V> h;

        public s(ReferenceQueue<V> referenceQueue, V v, com.google.common.cache.e<K, V> eVar) {
            super(v, referenceQueue);
            this.h = eVar;
        }

        @Override // com.google.common.cache.a.a0
        public com.google.common.cache.e<K, V> a() {
            return this.h;
        }

        @Override // com.google.common.cache.a.a0
        public void b(V v) {
        }

        @Override // com.google.common.cache.a.a0
        public boolean c() {
            return false;
        }

        public a0<K, V> d(ReferenceQueue<V> referenceQueue, V v, com.google.common.cache.e<K, V> eVar) {
            return new s(referenceQueue, v, eVar);
        }

        @Override // com.google.common.cache.a.a0
        public V e() {
            return get();
        }

        public int getWeight() {
            return 1;
        }

        @Override // com.google.common.cache.a.a0
        public boolean isActive() {
            return true;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class t {
        public static final t STRONG = new C0466a("STRONG", 0);
        public static final t SOFT = new b("SOFT", 1);
        public static final t WEAK = new c("WEAK", 2);
        private static final /* synthetic */ t[] $VALUES = $values();

        /* renamed from: com.google.common.cache.a$t$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public enum C0466a extends t {
            public C0466a(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.cache.a.t
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }

            @Override // com.google.common.cache.a.t
            public <K, V> a0<Object, Object> referenceValue(r<Object, Object> rVar, com.google.common.cache.e<Object, Object> eVar, Object obj, int i) {
                if (i == 1) {
                    return new x(obj);
                }
                return new i0(obj, i);
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends t {
            public b(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.cache.a.t
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }

            @Override // com.google.common.cache.a.t
            public <K, V> a0<Object, Object> referenceValue(r<Object, Object> rVar, com.google.common.cache.e<Object, Object> eVar, Object obj, int i) {
                if (i == 1) {
                    return new s(rVar.valueReferenceQueue, obj, eVar);
                }
                return new h0(rVar.valueReferenceQueue, obj, eVar, i);
            }
        }

        /* loaded from: classes10.dex */
        public enum c extends t {
            public c(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.common.cache.a.t
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }

            @Override // com.google.common.cache.a.t
            public <K, V> a0<Object, Object> referenceValue(r<Object, Object> rVar, com.google.common.cache.e<Object, Object> eVar, Object obj, int i) {
                if (i == 1) {
                    return new f0(rVar.valueReferenceQueue, obj, eVar);
                }
                return new j0(rVar.valueReferenceQueue, obj, eVar, i);
            }
        }

        private static /* synthetic */ t[] $values() {
            return new t[]{STRONG, SOFT, WEAK};
        }

        private t(String str, int i) {
        }

        public static t valueOf(String str) {
            return (t) Enum.valueOf(t.class, str);
        }

        public static t[] values() {
            return (t[]) $VALUES.clone();
        }

        public abstract Equivalence<Object> defaultEquivalence();

        public abstract <K, V> a0<K, V> referenceValue(r<K, V> rVar, com.google.common.cache.e<K, V> eVar, V v, int i);

        public /* synthetic */ t(String str, int i, C0458a c0458a) {
            this(str, i);
        }
    }

    /* loaded from: classes10.dex */
    public static final class u<K, V> extends w<K, V> {
        public volatile long l;
        @Weak
        public com.google.common.cache.e<K, V> m;
        @Weak
        public com.google.common.cache.e<K, V> n;

        public u(K k, int i, @NullableDecl com.google.common.cache.e<K, V> eVar) {
            super(k, i, eVar);
            this.l = Long.MAX_VALUE;
            this.m = a.x();
            this.n = a.x();
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public long getAccessTime() {
            return this.l;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNextInAccessQueue() {
            return this.m;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getPreviousInAccessQueue() {
            return this.n;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setAccessTime(long j) {
            this.l = j;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setNextInAccessQueue(com.google.common.cache.e<K, V> eVar) {
            this.m = eVar;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setPreviousInAccessQueue(com.google.common.cache.e<K, V> eVar) {
            this.n = eVar;
        }
    }

    /* loaded from: classes10.dex */
    public static final class v<K, V> extends w<K, V> {
        public volatile long l;
        @Weak
        public com.google.common.cache.e<K, V> m;
        @Weak
        public com.google.common.cache.e<K, V> n;
        public volatile long o;
        @Weak
        public com.google.common.cache.e<K, V> p;
        @Weak
        public com.google.common.cache.e<K, V> q;

        public v(K k, int i, @NullableDecl com.google.common.cache.e<K, V> eVar) {
            super(k, i, eVar);
            this.l = Long.MAX_VALUE;
            this.m = a.x();
            this.n = a.x();
            this.o = Long.MAX_VALUE;
            this.p = a.x();
            this.q = a.x();
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public long getAccessTime() {
            return this.l;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNextInAccessQueue() {
            return this.m;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNextInWriteQueue() {
            return this.p;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getPreviousInAccessQueue() {
            return this.n;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getPreviousInWriteQueue() {
            return this.q;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public long getWriteTime() {
            return this.o;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setAccessTime(long j) {
            this.l = j;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setNextInAccessQueue(com.google.common.cache.e<K, V> eVar) {
            this.m = eVar;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setNextInWriteQueue(com.google.common.cache.e<K, V> eVar) {
            this.p = eVar;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setPreviousInAccessQueue(com.google.common.cache.e<K, V> eVar) {
            this.n = eVar;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setPreviousInWriteQueue(com.google.common.cache.e<K, V> eVar) {
            this.q = eVar;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setWriteTime(long j) {
            this.o = j;
        }
    }

    /* loaded from: classes10.dex */
    public static class w<K, V> extends d<K, V> {
        public final K h;
        public final int i;
        @NullableDecl
        public final com.google.common.cache.e<K, V> j;
        public volatile a0<K, V> k = a.L();

        public w(K k, int i, @NullableDecl com.google.common.cache.e<K, V> eVar) {
            this.h = k;
            this.i = i;
            this.j = eVar;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public int getHash() {
            return this.i;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public K getKey() {
            return this.h;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNext() {
            return this.j;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public a0<K, V> getValueReference() {
            return this.k;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setValueReference(a0<K, V> a0Var) {
            this.k = a0Var;
        }
    }

    /* loaded from: classes10.dex */
    public static class x<K, V> implements a0<K, V> {
        public final V h;

        public x(V v) {
            this.h = v;
        }

        @Override // com.google.common.cache.a.a0
        public com.google.common.cache.e<K, V> a() {
            return null;
        }

        @Override // com.google.common.cache.a.a0
        public void b(V v) {
        }

        @Override // com.google.common.cache.a.a0
        public boolean c() {
            return false;
        }

        @Override // com.google.common.cache.a.a0
        public a0<K, V> d(ReferenceQueue<V> referenceQueue, V v, com.google.common.cache.e<K, V> eVar) {
            return this;
        }

        @Override // com.google.common.cache.a.a0
        public V e() {
            return get();
        }

        @Override // com.google.common.cache.a.a0
        public V get() {
            return this.h;
        }

        @Override // com.google.common.cache.a.a0
        public int getWeight() {
            return 1;
        }

        @Override // com.google.common.cache.a.a0
        public boolean isActive() {
            return true;
        }
    }

    /* loaded from: classes10.dex */
    public static final class y<K, V> extends w<K, V> {
        public volatile long l;
        @Weak
        public com.google.common.cache.e<K, V> m;
        @Weak
        public com.google.common.cache.e<K, V> n;

        public y(K k, int i, @NullableDecl com.google.common.cache.e<K, V> eVar) {
            super(k, i, eVar);
            this.l = Long.MAX_VALUE;
            this.m = a.x();
            this.n = a.x();
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getNextInWriteQueue() {
            return this.m;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public com.google.common.cache.e<K, V> getPreviousInWriteQueue() {
            return this.n;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public long getWriteTime() {
            return this.l;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setNextInWriteQueue(com.google.common.cache.e<K, V> eVar) {
            this.m = eVar;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setPreviousInWriteQueue(com.google.common.cache.e<K, V> eVar) {
            this.n = eVar;
        }

        @Override // com.google.common.cache.a.d, com.google.common.cache.e
        public void setWriteTime(long j) {
            this.l = j;
        }
    }

    /* loaded from: classes10.dex */
    public final class z extends a<K, V>.i<V> {
        public z(a aVar) {
            super();
        }

        @Override // java.util.Iterator
        public V next() {
            return c().getValue();
        }
    }

    public a(CacheBuilder<? super K, ? super V> cacheBuilder, @NullableDecl CacheLoader<? super K, V> cacheLoader) {
        Queue<RemovalNotification<K, V>> concurrentLinkedQueue;
        this.k = Math.min(cacheBuilder.c(), 65536);
        t h2 = cacheBuilder.h();
        this.n = h2;
        this.o = cacheBuilder.o();
        this.l = cacheBuilder.g();
        this.m = cacheBuilder.n();
        long i2 = cacheBuilder.i();
        this.p = i2;
        this.q = (Weigher<K, V>) cacheBuilder.p();
        this.r = cacheBuilder.d();
        this.s = cacheBuilder.e();
        this.t = cacheBuilder.j();
        RemovalListener<K, V> removalListener = (RemovalListener<K, V>) cacheBuilder.k();
        this.v = removalListener;
        if (removalListener == CacheBuilder.d.INSTANCE) {
            concurrentLinkedQueue = h();
        } else {
            concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        }
        this.u = concurrentLinkedQueue;
        this.w = cacheBuilder.m(E());
        this.x = f.getFactory(h2, M(), Q());
        this.y = cacheBuilder.l().get();
        this.z = cacheLoader;
        int min = Math.min(cacheBuilder.f(), 1073741824);
        if (i() && !g()) {
            min = (int) Math.min(min, i2);
        }
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        int i6 = 1;
        while (i6 < this.k && (!i() || i6 * 20 <= this.p)) {
            i5++;
            i6 <<= 1;
        }
        this.i = 32 - i5;
        this.h = i6 - 1;
        this.j = w(i6);
        int i7 = min / i6;
        while (i4 < (i7 * i6 < min ? i7 + 1 : i7)) {
            i4 <<= 1;
        }
        if (i()) {
            long j2 = this.p;
            long j3 = i6;
            long j4 = (j2 / j3) + 1;
            long j5 = j2 % j3;
            while (true) {
                r<K, V>[] rVarArr = this.j;
                if (i3 >= rVarArr.length) {
                    return;
                }
                if (i3 == j5) {
                    j4--;
                }
                rVarArr[i3] = f(i4, j4, cacheBuilder.l().get());
                i3++;
            }
        } else {
            while (true) {
                r<K, V>[] rVarArr2 = this.j;
                if (i3 >= rVarArr2.length) {
                    return;
                }
                rVarArr2[i3] = f(i4, -1L, cacheBuilder.l().get());
                i3++;
            }
        }
    }

    public static int I(int i2) {
        int i3 = i2 + ((i2 << 15) ^ (-12931));
        int i4 = i3 ^ (i3 >>> 10);
        int i5 = i4 + (i4 << 3);
        int i6 = i5 ^ (i5 >>> 6);
        int i7 = i6 + (i6 << 2) + (i6 << 14);
        return i7 ^ (i7 >>> 16);
    }

    public static <E> ArrayList<E> K(Collection<E> collection) {
        ArrayList<E> arrayList = new ArrayList<>(collection.size());
        Iterators.addAll(arrayList, collection.iterator());
        return arrayList;
    }

    public static <K, V> a0<K, V> L() {
        return (a0<K, V>) E;
    }

    public static <K, V> void d(com.google.common.cache.e<K, V> eVar, com.google.common.cache.e<K, V> eVar2) {
        eVar.setNextInAccessQueue(eVar2);
        eVar2.setPreviousInAccessQueue(eVar);
    }

    public static <K, V> void e(com.google.common.cache.e<K, V> eVar, com.google.common.cache.e<K, V> eVar2) {
        eVar.setNextInWriteQueue(eVar2);
        eVar2.setPreviousInWriteQueue(eVar);
    }

    public static <E> Queue<E> h() {
        return (Queue<E>) F;
    }

    public static <K, V> com.google.common.cache.e<K, V> x() {
        return q.INSTANCE;
    }

    public static <K, V> void y(com.google.common.cache.e<K, V> eVar) {
        com.google.common.cache.e<K, V> x2 = x();
        eVar.setNextInAccessQueue(x2);
        eVar.setPreviousInAccessQueue(x2);
    }

    public static <K, V> void z(com.google.common.cache.e<K, V> eVar) {
        com.google.common.cache.e<K, V> x2 = x();
        eVar.setNextInWriteQueue(x2);
        eVar.setPreviousInWriteQueue(x2);
    }

    public void A() {
        while (true) {
            RemovalNotification<K, V> poll = this.u.poll();
            if (poll == null) {
                return;
            }
            try {
                this.v.onRemoval(poll);
            } catch (Throwable th) {
                D.log(Level.WARNING, "Exception thrown by removal listener", th);
            }
        }
    }

    public void B(com.google.common.cache.e<K, V> eVar) {
        int hash = eVar.getHash();
        J(hash).reclaimKey(eVar, hash);
    }

    public void C(a0<K, V> a0Var) {
        com.google.common.cache.e<K, V> a2 = a0Var.a();
        int hash = a2.getHash();
        J(hash).reclaimValue(a2.getKey(), hash, a0Var);
    }

    public boolean D() {
        return j();
    }

    public boolean E() {
        return F() || D();
    }

    public boolean F() {
        return k() || H();
    }

    public void G(K k2) {
        int r2 = r(Preconditions.checkNotNull(k2));
        J(r2).refresh(k2, r2, this.z, false);
    }

    public boolean H() {
        return this.t > 0;
    }

    public r<K, V> J(int i2) {
        return this.j[(i2 >>> this.i) & this.h];
    }

    public boolean M() {
        return N() || D();
    }

    public boolean N() {
        return j() || i();
    }

    public boolean O() {
        return this.n != t.STRONG;
    }

    public boolean P() {
        return this.o != t.STRONG;
    }

    public boolean Q() {
        return R() || F();
    }

    public boolean R() {
        return k();
    }

    public void b() {
        for (r<K, V> rVar : this.j) {
            rVar.cleanUp();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (r<K, V> rVar : this.j) {
            rVar.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        if (obj == null) {
            return false;
        }
        int r2 = r(obj);
        return J(r2).containsKey(obj, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [int] */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [int] */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        boolean z2 = false;
        if (obj == null) {
            return false;
        }
        long read = this.w.read();
        r<K, V>[] rVarArr = this.j;
        long j2 = -1;
        int i2 = 0;
        while (i2 < 3) {
            long j3 = 0;
            int length = rVarArr.length;
            for (int i3 = z2; i3 < length; i3++) {
                r<K, V> rVar = rVarArr[i3];
                int i4 = rVar.count;
                AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = rVar.table;
                for (int i5 = z2; i5 < atomicReferenceArray.length(); i5++) {
                    com.google.common.cache.e<K, V> eVar = atomicReferenceArray.get(i5);
                    while (eVar != null) {
                        r<K, V>[] rVarArr2 = rVarArr;
                        V liveValue = rVar.getLiveValue(eVar, read);
                        long j4 = read;
                        if (liveValue != null && this.m.equivalent(obj, liveValue)) {
                            return true;
                        }
                        eVar = eVar.getNext();
                        rVarArr = rVarArr2;
                        read = j4;
                    }
                }
                j3 += rVar.modCount;
                read = read;
                z2 = false;
            }
            long j5 = read;
            r<K, V>[] rVarArr3 = rVarArr;
            if (j3 == j2) {
                return false;
            }
            i2++;
            j2 = j3;
            rVarArr = rVarArr3;
            read = j5;
            z2 = false;
        }
        return z2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @GwtIncompatible
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.C;
        if (set != null) {
            return set;
        }
        h hVar = new h();
        this.C = hVar;
        return hVar;
    }

    public r<K, V> f(int i2, long j2, AbstractCache.StatsCounter statsCounter) {
        return new r<>(this, i2, j2, statsCounter);
    }

    public boolean g() {
        return this.q != CacheBuilder.e.INSTANCE;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @NullableDecl
    public V get(@NullableDecl Object obj) {
        if (obj == null) {
            return null;
        }
        int r2 = r(obj);
        return J(r2).get(obj, r2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @NullableDecl
    public V getOrDefault(@NullableDecl Object obj, @NullableDecl V v2) {
        V v3 = get(obj);
        return v3 != null ? v3 : v2;
    }

    public boolean i() {
        return this.p >= 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        r<K, V>[] rVarArr = this.j;
        long j2 = 0;
        for (int i2 = 0; i2 < rVarArr.length; i2++) {
            if (rVarArr[i2].count != 0) {
                return false;
            }
            j2 += rVarArr[i2].modCount;
        }
        if (j2 != 0) {
            for (int i3 = 0; i3 < rVarArr.length; i3++) {
                if (rVarArr[i3].count != 0) {
                    return false;
                }
                j2 -= rVarArr[i3].modCount;
            }
            return j2 == 0;
        }
        return true;
    }

    public boolean j() {
        return this.r > 0;
    }

    public boolean k() {
        return this.s > 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.A;
        if (set != null) {
            return set;
        }
        k kVar = new k();
        this.A = kVar;
        return kVar;
    }

    public V l(K k2, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
        int r2 = r(Preconditions.checkNotNull(k2));
        return J(r2).get(k2, r2, cacheLoader);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ImmutableMap<K, V> m(Iterable<? extends K> iterable) throws ExecutionException {
        LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
        LinkedHashSet newLinkedHashSet = Sets.newLinkedHashSet();
        int i2 = 0;
        int i3 = 0;
        for (K k2 : iterable) {
            Object obj = get(k2);
            if (!newLinkedHashMap.containsKey(k2)) {
                newLinkedHashMap.put(k2, obj);
                if (obj == null) {
                    i3++;
                    newLinkedHashSet.add(k2);
                } else {
                    i2++;
                }
            }
        }
        try {
            if (!newLinkedHashSet.isEmpty()) {
                try {
                    Map u2 = u(newLinkedHashSet, this.z);
                    for (Object obj2 : newLinkedHashSet) {
                        Object obj3 = u2.get(obj2);
                        if (obj3 != null) {
                            newLinkedHashMap.put(obj2, obj3);
                        } else {
                            String valueOf = String.valueOf(obj2);
                            StringBuilder sb = new StringBuilder(valueOf.length() + 37);
                            sb.append("loadAll failed to return a value for ");
                            sb.append(valueOf);
                            throw new CacheLoader.InvalidCacheLoadException(sb.toString());
                        }
                    }
                } catch (CacheLoader.UnsupportedLoadingOperationException unused) {
                    for (Object obj4 : newLinkedHashSet) {
                        i3--;
                        newLinkedHashMap.put(obj4, l(obj4, this.z));
                    }
                }
            }
            return ImmutableMap.copyOf((Map) newLinkedHashMap);
        } finally {
            this.y.recordHits(i2);
            this.y.recordMisses(i3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ImmutableMap<K, V> n(Iterable<?> iterable) {
        LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
        int i2 = 0;
        int i3 = 0;
        for (Object obj : iterable) {
            V v2 = get(obj);
            if (v2 == null) {
                i3++;
            } else {
                newLinkedHashMap.put(obj, v2);
                i2++;
            }
        }
        this.y.recordHits(i2);
        this.y.recordMisses(i3);
        return ImmutableMap.copyOf((Map) newLinkedHashMap);
    }

    @NullableDecl
    public V o(Object obj) {
        int r2 = r(Preconditions.checkNotNull(obj));
        V v2 = J(r2).get(obj, r2);
        if (v2 == null) {
            this.y.recordMisses(1);
        } else {
            this.y.recordHits(1);
        }
        return v2;
    }

    @NullableDecl
    public V p(com.google.common.cache.e<K, V> eVar, long j2) {
        V v2;
        if (eVar.getKey() == null || (v2 = eVar.getValueReference().get()) == null || t(eVar, j2)) {
            return null;
        }
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int r2 = r(k2);
        return J(r2).put(k2, r2, v2, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int r2 = r(k2);
        return J(r2).put(k2, r2, v2, true);
    }

    public V q(K k2) throws ExecutionException {
        return l(k2, this.z);
    }

    public int r(@NullableDecl Object obj) {
        return I(this.l.hash(obj));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(@NullableDecl Object obj) {
        if (obj == null) {
            return null;
        }
        int r2 = r(obj);
        return J(r2).remove(obj, r2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K k2, @NullableDecl V v2, V v3) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v3);
        if (v2 == null) {
            return false;
        }
        int r2 = r(k2);
        return J(r2).replace(k2, r2, v2, v3);
    }

    public void s(Iterable<?> iterable) {
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return Ints.saturatedCast(v());
    }

    public boolean t(com.google.common.cache.e<K, V> eVar, long j2) {
        Preconditions.checkNotNull(eVar);
        if (!j() || j2 - eVar.getAccessTime() < this.r) {
            return k() && j2 - eVar.getWriteTime() >= this.s;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00cd  */
    @org.checkerframework.checker.nullness.compatqual.NullableDecl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Map<K, V> u(java.util.Set<? extends K> r7, com.google.common.cache.CacheLoader<? super K, V> r8) throws java.util.concurrent.ExecutionException {
        /*
            r6 = this;
            com.google.common.base.Preconditions.checkNotNull(r8)
            com.google.common.base.Preconditions.checkNotNull(r7)
            com.google.common.base.Stopwatch r0 = com.google.common.base.Stopwatch.createStarted()
            r1 = 1
            r2 = 0
            java.util.Map r7 = r8.loadAll(r7)     // Catch: java.lang.Throwable -> La2 java.lang.Error -> La5 java.lang.Exception -> Lac java.lang.RuntimeException -> Lb3 java.lang.InterruptedException -> Lba com.google.common.cache.CacheLoader.UnsupportedLoadingOperationException -> Lc8
            if (r7 == 0) goto L76
            r0.stop()
            java.util.Set r3 = r7.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L1d:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L3c
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r5 = r4.getKey()
            java.lang.Object r4 = r4.getValue()
            if (r5 == 0) goto L3a
            if (r4 != 0) goto L36
            goto L3a
        L36:
            r6.put(r5, r4)
            goto L1d
        L3a:
            r2 = r1
            goto L1d
        L3c:
            if (r2 != 0) goto L4a
            com.google.common.cache.AbstractCache$StatsCounter r8 = r6.y
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r0 = r0.elapsed(r1)
            r8.recordLoadSuccess(r0)
            return r7
        L4a:
            com.google.common.cache.AbstractCache$StatsCounter r7 = r6.y
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r0 = r0.elapsed(r1)
            r7.recordLoadException(r0)
            com.google.common.cache.CacheLoader$InvalidCacheLoadException r7 = new com.google.common.cache.CacheLoader$InvalidCacheLoadException
            java.lang.String r8 = java.lang.String.valueOf(r8)
            int r0 = r8.length()
            int r0 = r0 + 42
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            r1.append(r8)
            java.lang.String r8 = " returned null keys or values from loadAll"
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r7.<init>(r8)
            throw r7
        L76:
            com.google.common.cache.AbstractCache$StatsCounter r7 = r6.y
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r0 = r0.elapsed(r1)
            r7.recordLoadException(r0)
            com.google.common.cache.CacheLoader$InvalidCacheLoadException r7 = new com.google.common.cache.CacheLoader$InvalidCacheLoadException
            java.lang.String r8 = java.lang.String.valueOf(r8)
            int r0 = r8.length()
            int r0 = r0 + 31
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            r1.append(r8)
            java.lang.String r8 = " returned null map from loadAll"
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r7.<init>(r8)
            throw r7
        La2:
            r7 = move-exception
            r1 = r2
            goto Lcb
        La5:
            r7 = move-exception
            com.google.common.util.concurrent.ExecutionError r8 = new com.google.common.util.concurrent.ExecutionError     // Catch: java.lang.Throwable -> La2
            r8.<init>(r7)     // Catch: java.lang.Throwable -> La2
            throw r8     // Catch: java.lang.Throwable -> La2
        Lac:
            r7 = move-exception
            java.util.concurrent.ExecutionException r8 = new java.util.concurrent.ExecutionException     // Catch: java.lang.Throwable -> La2
            r8.<init>(r7)     // Catch: java.lang.Throwable -> La2
            throw r8     // Catch: java.lang.Throwable -> La2
        Lb3:
            r7 = move-exception
            com.google.common.util.concurrent.UncheckedExecutionException r8 = new com.google.common.util.concurrent.UncheckedExecutionException     // Catch: java.lang.Throwable -> La2
            r8.<init>(r7)     // Catch: java.lang.Throwable -> La2
            throw r8     // Catch: java.lang.Throwable -> La2
        Lba:
            r7 = move-exception
            java.lang.Thread r8 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> La2
            r8.interrupt()     // Catch: java.lang.Throwable -> La2
            java.util.concurrent.ExecutionException r8 = new java.util.concurrent.ExecutionException     // Catch: java.lang.Throwable -> La2
            r8.<init>(r7)     // Catch: java.lang.Throwable -> La2
            throw r8     // Catch: java.lang.Throwable -> La2
        Lc8:
            r7 = move-exception
            throw r7     // Catch: java.lang.Throwable -> Lca
        Lca:
            r7 = move-exception
        Lcb:
            if (r1 != 0) goto Ld8
            com.google.common.cache.AbstractCache$StatsCounter r8 = r6.y
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r0 = r0.elapsed(r1)
            r8.recordLoadException(r0)
        Ld8:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.a.u(java.util.Set, com.google.common.cache.CacheLoader):java.util.Map");
    }

    public long v() {
        r<K, V>[] rVarArr;
        long j2 = 0;
        for (int i2 = 0; i2 < this.j.length; i2++) {
            j2 += Math.max(0, rVarArr[i2].count);
        }
        return j2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.B;
        if (collection != null) {
            return collection;
        }
        b0 b0Var = new b0();
        this.B = b0Var;
        return b0Var;
    }

    public final r<K, V>[] w(int i2) {
        return new r[i2];
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int r2 = r(obj);
        return J(r2).remove(obj, r2, obj2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K k2, V v2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(v2);
        int r2 = r(k2);
        return J(r2).replace(k2, r2, v2);
    }

    /* loaded from: classes10.dex */
    public static class r<K, V> extends ReentrantLock {
        @GuardedBy("this")
        public final Queue<com.google.common.cache.e<K, V>> accessQueue;
        public volatile int count;
        @NullableDecl
        public final ReferenceQueue<K> keyReferenceQueue;
        @Weak
        public final a<K, V> map;
        public final long maxSegmentWeight;
        public int modCount;
        public final AtomicInteger readCount = new AtomicInteger();
        public final Queue<com.google.common.cache.e<K, V>> recencyQueue;
        public final AbstractCache.StatsCounter statsCounter;
        @NullableDecl
        public volatile AtomicReferenceArray<com.google.common.cache.e<K, V>> table;
        public int threshold;
        @GuardedBy("this")
        public long totalWeight;
        @NullableDecl
        public final ReferenceQueue<V> valueReferenceQueue;
        @GuardedBy("this")
        public final Queue<com.google.common.cache.e<K, V>> writeQueue;

        /* renamed from: com.google.common.cache.a$r$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class RunnableC0465a implements Runnable {
            public final /* synthetic */ Object h;
            public final /* synthetic */ int i;
            public final /* synthetic */ m j;
            public final /* synthetic */ ListenableFuture k;

            public RunnableC0465a(Object obj, int i, m mVar, ListenableFuture listenableFuture) {
                this.h = obj;
                this.i = i;
                this.j = mVar;
                this.k = listenableFuture;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                try {
                    r.this.getAndRecordStats(this.h, this.i, this.j, this.k);
                } catch (Throwable th) {
                    a.D.log(Level.WARNING, "Exception thrown during refresh", th);
                    this.j.k(th);
                }
            }
        }

        public r(a<K, V> aVar, int i, long j, AbstractCache.StatsCounter statsCounter) {
            Queue<com.google.common.cache.e<K, V>> h;
            Queue<com.google.common.cache.e<K, V>> h2;
            Queue<com.google.common.cache.e<K, V>> h3;
            this.map = aVar;
            this.maxSegmentWeight = j;
            this.statsCounter = (AbstractCache.StatsCounter) Preconditions.checkNotNull(statsCounter);
            initTable(newEntryArray(i));
            this.keyReferenceQueue = aVar.O() ? new ReferenceQueue<>() : null;
            this.valueReferenceQueue = aVar.P() ? new ReferenceQueue<>() : null;
            if (aVar.N()) {
                h = new ConcurrentLinkedQueue<>();
            } else {
                h = a.h();
            }
            this.recencyQueue = h;
            if (aVar.R()) {
                h2 = new k0<>();
            } else {
                h2 = a.h();
            }
            this.writeQueue = h2;
            if (aVar.N()) {
                h3 = new e<>();
            } else {
                h3 = a.h();
            }
            this.accessQueue = h3;
        }

        public void cleanUp() {
            runLockedCleanup(this.map.w.read());
            runUnlockedCleanup();
        }

        public void clear() {
            RemovalCause removalCause;
            if (this.count != 0) {
                lock();
                try {
                    preWriteCleanup(this.map.w.read());
                    AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.table;
                    for (int i = 0; i < atomicReferenceArray.length(); i++) {
                        for (com.google.common.cache.e<K, V> eVar = atomicReferenceArray.get(i); eVar != null; eVar = eVar.getNext()) {
                            if (eVar.getValueReference().isActive()) {
                                K key = eVar.getKey();
                                V v = eVar.getValueReference().get();
                                if (key != null && v != null) {
                                    removalCause = RemovalCause.EXPLICIT;
                                    enqueueNotification(key, eVar.getHash(), v, eVar.getValueReference().getWeight(), removalCause);
                                }
                                removalCause = RemovalCause.COLLECTED;
                                enqueueNotification(key, eVar.getHash(), v, eVar.getValueReference().getWeight(), removalCause);
                            }
                        }
                    }
                    for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
                        atomicReferenceArray.set(i2, null);
                    }
                    clearReferenceQueues();
                    this.writeQueue.clear();
                    this.accessQueue.clear();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                    postWriteCleanup();
                }
            }
        }

        public void clearKeyReferenceQueue() {
            do {
            } while (this.keyReferenceQueue.poll() != null);
        }

        public void clearReferenceQueues() {
            if (this.map.O()) {
                clearKeyReferenceQueue();
            }
            if (this.map.P()) {
                clearValueReferenceQueue();
            }
        }

        public void clearValueReferenceQueue() {
            do {
            } while (this.valueReferenceQueue.poll() != null);
        }

        public boolean containsKey(Object obj, int i) {
            try {
                if (this.count != 0) {
                    com.google.common.cache.e<K, V> liveEntry = getLiveEntry(obj, i, this.map.w.read());
                    if (liveEntry == null) {
                        return false;
                    }
                    return liveEntry.getValueReference().get() != null;
                }
                return false;
            } finally {
                postReadCleanup();
            }
        }

        @VisibleForTesting
        public boolean containsValue(Object obj) {
            try {
                if (this.count != 0) {
                    long read = this.map.w.read();
                    AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.table;
                    int length = atomicReferenceArray.length();
                    for (int i = 0; i < length; i++) {
                        for (com.google.common.cache.e<K, V> eVar = atomicReferenceArray.get(i); eVar != null; eVar = eVar.getNext()) {
                            V liveValue = getLiveValue(eVar, read);
                            if (liveValue != null && this.map.m.equivalent(obj, liveValue)) {
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

        @GuardedBy("this")
        public com.google.common.cache.e<K, V> copyEntry(com.google.common.cache.e<K, V> eVar, com.google.common.cache.e<K, V> eVar2) {
            if (eVar.getKey() == null) {
                return null;
            }
            a0<K, V> valueReference = eVar.getValueReference();
            V v = valueReference.get();
            if (v == null && valueReference.isActive()) {
                return null;
            }
            com.google.common.cache.e<K, V> copyEntry = this.map.x.copyEntry(this, eVar, eVar2);
            copyEntry.setValueReference(valueReference.d(this.valueReferenceQueue, v, copyEntry));
            return copyEntry;
        }

        @GuardedBy("this")
        public void drainKeyReferenceQueue() {
            int i = 0;
            do {
                Reference<? extends K> poll = this.keyReferenceQueue.poll();
                if (poll == null) {
                    return;
                }
                this.map.B((com.google.common.cache.e) poll);
                i++;
            } while (i != 16);
        }

        @GuardedBy("this")
        public void drainRecencyQueue() {
            while (true) {
                com.google.common.cache.e<K, V> poll = this.recencyQueue.poll();
                if (poll == null) {
                    return;
                }
                if (this.accessQueue.contains(poll)) {
                    this.accessQueue.add(poll);
                }
            }
        }

        @GuardedBy("this")
        public void drainReferenceQueues() {
            if (this.map.O()) {
                drainKeyReferenceQueue();
            }
            if (this.map.P()) {
                drainValueReferenceQueue();
            }
        }

        @GuardedBy("this")
        public void drainValueReferenceQueue() {
            int i = 0;
            do {
                Reference<? extends V> poll = this.valueReferenceQueue.poll();
                if (poll == null) {
                    return;
                }
                this.map.C((a0) poll);
                i++;
            } while (i != 16);
        }

        @GuardedBy("this")
        public void enqueueNotification(@NullableDecl K k, int i, @NullableDecl V v, int i2, RemovalCause removalCause) {
            this.totalWeight -= i2;
            if (removalCause.wasEvicted()) {
                this.statsCounter.recordEviction();
            }
            if (this.map.u != a.F) {
                this.map.u.offer(RemovalNotification.create(k, v, removalCause));
            }
        }

        @GuardedBy("this")
        public void evictEntries(com.google.common.cache.e<K, V> eVar) {
            if (this.map.i()) {
                drainRecencyQueue();
                if (eVar.getValueReference().getWeight() > this.maxSegmentWeight && !removeEntry(eVar, eVar.getHash(), RemovalCause.SIZE)) {
                    throw new AssertionError();
                }
                while (this.totalWeight > this.maxSegmentWeight) {
                    com.google.common.cache.e<K, V> nextEvictable = getNextEvictable();
                    if (!removeEntry(nextEvictable, nextEvictable.getHash(), RemovalCause.SIZE)) {
                        throw new AssertionError();
                    }
                }
            }
        }

        @GuardedBy("this")
        public void expand() {
            AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.table;
            int length = atomicReferenceArray.length();
            if (length >= 1073741824) {
                return;
            }
            int i = this.count;
            AtomicReferenceArray<com.google.common.cache.e<K, V>> newEntryArray = newEntryArray(length << 1);
            this.threshold = (newEntryArray.length() * 3) / 4;
            int length2 = newEntryArray.length() - 1;
            for (int i2 = 0; i2 < length; i2++) {
                com.google.common.cache.e<K, V> eVar = atomicReferenceArray.get(i2);
                if (eVar != null) {
                    com.google.common.cache.e<K, V> next = eVar.getNext();
                    int hash = eVar.getHash() & length2;
                    if (next == null) {
                        newEntryArray.set(hash, eVar);
                    } else {
                        com.google.common.cache.e<K, V> eVar2 = eVar;
                        while (next != null) {
                            int hash2 = next.getHash() & length2;
                            if (hash2 != hash) {
                                eVar2 = next;
                                hash = hash2;
                            }
                            next = next.getNext();
                        }
                        newEntryArray.set(hash, eVar2);
                        while (eVar != eVar2) {
                            int hash3 = eVar.getHash() & length2;
                            com.google.common.cache.e<K, V> copyEntry = copyEntry(eVar, newEntryArray.get(hash3));
                            if (copyEntry != null) {
                                newEntryArray.set(hash3, copyEntry);
                            } else {
                                removeCollectedEntry(eVar);
                                i--;
                            }
                            eVar = eVar.getNext();
                        }
                    }
                }
            }
            this.table = newEntryArray;
            this.count = i;
        }

        @GuardedBy("this")
        public void expireEntries(long j) {
            com.google.common.cache.e<K, V> peek;
            com.google.common.cache.e<K, V> peek2;
            drainRecencyQueue();
            do {
                peek = this.writeQueue.peek();
                if (peek == null || !this.map.t(peek, j)) {
                    do {
                        peek2 = this.accessQueue.peek();
                        if (peek2 == null || !this.map.t(peek2, j)) {
                            return;
                        }
                    } while (removeEntry(peek2, peek2.getHash(), RemovalCause.EXPIRED));
                    throw new AssertionError();
                }
            } while (removeEntry(peek, peek.getHash(), RemovalCause.EXPIRED));
            throw new AssertionError();
        }

        public V get(K k, int i, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            com.google.common.cache.e<K, V> entry;
            Preconditions.checkNotNull(k);
            Preconditions.checkNotNull(cacheLoader);
            try {
                try {
                    if (this.count != 0 && (entry = getEntry(k, i)) != null) {
                        long read = this.map.w.read();
                        V liveValue = getLiveValue(entry, read);
                        if (liveValue != null) {
                            recordRead(entry, read);
                            this.statsCounter.recordHits(1);
                            return scheduleRefresh(entry, k, i, liveValue, read, cacheLoader);
                        }
                        a0<K, V> valueReference = entry.getValueReference();
                        if (valueReference.c()) {
                            return waitForLoadingValue(entry, k, valueReference);
                        }
                    }
                    return lockedGetOrLoad(k, i, cacheLoader);
                } catch (ExecutionException e) {
                    Throwable cause = e.getCause();
                    if (!(cause instanceof Error)) {
                        if (cause instanceof RuntimeException) {
                            throw new UncheckedExecutionException(cause);
                        }
                        throw e;
                    }
                    throw new ExecutionError((Error) cause);
                }
            } finally {
                postReadCleanup();
            }
        }

        public V getAndRecordStats(K k, int i, m<K, V> mVar, ListenableFuture<V> listenableFuture) throws ExecutionException {
            V v;
            try {
                v = (V) Uninterruptibles.getUninterruptibly(listenableFuture);
                try {
                    if (v != null) {
                        this.statsCounter.recordLoadSuccess(mVar.f());
                        storeLoadedValue(k, i, mVar, v);
                        return v;
                    }
                    String valueOf = String.valueOf(k);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 35);
                    sb.append("CacheLoader returned null for key ");
                    sb.append(valueOf);
                    sb.append(".");
                    throw new CacheLoader.InvalidCacheLoadException(sb.toString());
                } catch (Throwable th) {
                    th = th;
                    if (v == null) {
                        this.statsCounter.recordLoadException(mVar.f());
                        removeLoadingValue(k, i, mVar);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                v = null;
            }
        }

        @NullableDecl
        public com.google.common.cache.e<K, V> getEntry(Object obj, int i) {
            for (com.google.common.cache.e<K, V> first = getFirst(i); first != null; first = first.getNext()) {
                if (first.getHash() == i) {
                    K key = first.getKey();
                    if (key == null) {
                        tryDrainReferenceQueues();
                    } else if (this.map.l.equivalent(obj, key)) {
                        return first;
                    }
                }
            }
            return null;
        }

        public com.google.common.cache.e<K, V> getFirst(int i) {
            AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.table;
            return atomicReferenceArray.get(i & (atomicReferenceArray.length() - 1));
        }

        @NullableDecl
        public com.google.common.cache.e<K, V> getLiveEntry(Object obj, int i, long j) {
            com.google.common.cache.e<K, V> entry = getEntry(obj, i);
            if (entry == null) {
                return null;
            }
            if (this.map.t(entry, j)) {
                tryExpireEntries(j);
                return null;
            }
            return entry;
        }

        public V getLiveValue(com.google.common.cache.e<K, V> eVar, long j) {
            if (eVar.getKey() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V v = eVar.getValueReference().get();
            if (v == null) {
                tryDrainReferenceQueues();
                return null;
            } else if (this.map.t(eVar, j)) {
                tryExpireEntries(j);
                return null;
            } else {
                return v;
            }
        }

        @GuardedBy("this")
        public com.google.common.cache.e<K, V> getNextEvictable() {
            for (com.google.common.cache.e<K, V> eVar : this.accessQueue) {
                if (eVar.getValueReference().getWeight() > 0) {
                    return eVar;
                }
            }
            throw new AssertionError();
        }

        public void initTable(AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray) {
            this.threshold = (atomicReferenceArray.length() * 3) / 4;
            if (!this.map.g()) {
                int i = this.threshold;
                if (i == this.maxSegmentWeight) {
                    this.threshold = i + 1;
                }
            }
            this.table = atomicReferenceArray;
        }

        @NullableDecl
        public m<K, V> insertLoadingValueReference(K k, int i, boolean z) {
            lock();
            try {
                long read = this.map.w.read();
                preWriteCleanup(read);
                AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                com.google.common.cache.e<K, V> eVar = atomicReferenceArray.get(length);
                for (com.google.common.cache.e<K, V> eVar2 = eVar; eVar2 != null; eVar2 = eVar2.getNext()) {
                    K key = eVar2.getKey();
                    if (eVar2.getHash() == i && key != null && this.map.l.equivalent(k, key)) {
                        a0<K, V> valueReference = eVar2.getValueReference();
                        if (!valueReference.c() && (!z || read - eVar2.getWriteTime() >= this.map.t)) {
                            this.modCount++;
                            m<K, V> mVar = new m<>(valueReference);
                            eVar2.setValueReference(mVar);
                            return mVar;
                        }
                        return null;
                    }
                }
                this.modCount++;
                m<K, V> mVar2 = new m<>();
                com.google.common.cache.e<K, V> newEntry = newEntry(k, i, eVar);
                newEntry.setValueReference(mVar2);
                atomicReferenceArray.set(length, newEntry);
                return mVar2;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        public ListenableFuture<V> loadAsync(K k, int i, m<K, V> mVar, CacheLoader<? super K, V> cacheLoader) {
            ListenableFuture<V> i2 = mVar.i(k, cacheLoader);
            i2.addListener(new RunnableC0465a(k, i, mVar, i2), MoreExecutors.directExecutor());
            return i2;
        }

        public V loadSync(K k, int i, m<K, V> mVar, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            return getAndRecordStats(k, i, mVar, mVar.i(k, cacheLoader));
        }

        public V lockedGetOrLoad(K k, int i, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            m<K, V> mVar;
            boolean z;
            a0<K, V> a0Var;
            V loadSync;
            lock();
            try {
                long read = this.map.w.read();
                preWriteCleanup(read);
                int i2 = this.count - 1;
                AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                com.google.common.cache.e<K, V> eVar = atomicReferenceArray.get(length);
                com.google.common.cache.e<K, V> eVar2 = eVar;
                while (true) {
                    mVar = null;
                    if (eVar2 == null) {
                        z = true;
                        a0Var = null;
                        break;
                    }
                    K key = eVar2.getKey();
                    if (eVar2.getHash() == i && key != null && this.map.l.equivalent(k, key)) {
                        a0<K, V> valueReference = eVar2.getValueReference();
                        if (valueReference.c()) {
                            z = false;
                        } else {
                            V v = valueReference.get();
                            if (v == null) {
                                enqueueNotification(key, i, v, valueReference.getWeight(), RemovalCause.COLLECTED);
                            } else if (this.map.t(eVar2, read)) {
                                enqueueNotification(key, i, v, valueReference.getWeight(), RemovalCause.EXPIRED);
                            } else {
                                recordLockedRead(eVar2, read);
                                this.statsCounter.recordHits(1);
                                return v;
                            }
                            this.writeQueue.remove(eVar2);
                            this.accessQueue.remove(eVar2);
                            this.count = i2;
                            z = true;
                        }
                        a0Var = valueReference;
                    } else {
                        eVar2 = eVar2.getNext();
                    }
                }
                if (z) {
                    mVar = new m<>();
                    if (eVar2 == null) {
                        eVar2 = newEntry(k, i, eVar);
                        eVar2.setValueReference(mVar);
                        atomicReferenceArray.set(length, eVar2);
                    } else {
                        eVar2.setValueReference(mVar);
                    }
                }
                if (z) {
                    try {
                        synchronized (eVar2) {
                            loadSync = loadSync(k, i, mVar, cacheLoader);
                        }
                        return loadSync;
                    } finally {
                        this.statsCounter.recordMisses(1);
                    }
                }
                return waitForLoadingValue(eVar2, k, a0Var);
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @GuardedBy("this")
        public com.google.common.cache.e<K, V> newEntry(K k, int i, @NullableDecl com.google.common.cache.e<K, V> eVar) {
            return this.map.x.newEntry(this, Preconditions.checkNotNull(k), i, eVar);
        }

        public AtomicReferenceArray<com.google.common.cache.e<K, V>> newEntryArray(int i) {
            return new AtomicReferenceArray<>(i);
        }

        public void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                cleanUp();
            }
        }

        public void postWriteCleanup() {
            runUnlockedCleanup();
        }

        @GuardedBy("this")
        public void preWriteCleanup(long j) {
            runLockedCleanup(j);
        }

        @NullableDecl
        public V put(K k, int i, V v, boolean z) {
            int i2;
            lock();
            try {
                long read = this.map.w.read();
                preWriteCleanup(read);
                if (this.count + 1 > this.threshold) {
                    expand();
                }
                AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                com.google.common.cache.e<K, V> eVar = atomicReferenceArray.get(length);
                com.google.common.cache.e<K, V> eVar2 = eVar;
                while (true) {
                    if (eVar2 != null) {
                        K key = eVar2.getKey();
                        if (eVar2.getHash() == i && key != null && this.map.l.equivalent(k, key)) {
                            a0<K, V> valueReference = eVar2.getValueReference();
                            V v2 = valueReference.get();
                            if (v2 != null) {
                                if (z) {
                                    recordLockedRead(eVar2, read);
                                } else {
                                    this.modCount++;
                                    enqueueNotification(k, i, v2, valueReference.getWeight(), RemovalCause.REPLACED);
                                    setValue(eVar2, k, v, read);
                                    evictEntries(eVar2);
                                }
                                return v2;
                            }
                            this.modCount++;
                            if (valueReference.isActive()) {
                                enqueueNotification(k, i, v2, valueReference.getWeight(), RemovalCause.COLLECTED);
                                setValue(eVar2, k, v, read);
                                i2 = this.count;
                            } else {
                                setValue(eVar2, k, v, read);
                                i2 = this.count + 1;
                            }
                            this.count = i2;
                            evictEntries(eVar2);
                        } else {
                            eVar2 = eVar2.getNext();
                        }
                    } else {
                        this.modCount++;
                        com.google.common.cache.e<K, V> newEntry = newEntry(k, i, eVar);
                        setValue(newEntry, k, v, read);
                        atomicReferenceArray.set(length, newEntry);
                        this.count++;
                        evictEntries(newEntry);
                        break;
                    }
                }
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        public boolean reclaimKey(com.google.common.cache.e<K, V> eVar, int i) {
            lock();
            try {
                AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                com.google.common.cache.e<K, V> eVar2 = atomicReferenceArray.get(length);
                for (com.google.common.cache.e<K, V> eVar3 = eVar2; eVar3 != null; eVar3 = eVar3.getNext()) {
                    if (eVar3 == eVar) {
                        this.modCount++;
                        atomicReferenceArray.set(length, removeValueFromChain(eVar2, eVar3, eVar3.getKey(), i, eVar3.getValueReference().get(), eVar3.getValueReference(), RemovalCause.COLLECTED));
                        this.count--;
                        return true;
                    }
                }
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        public boolean reclaimValue(K k, int i, a0<K, V> a0Var) {
            lock();
            try {
                AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                com.google.common.cache.e<K, V> eVar = atomicReferenceArray.get(length);
                for (com.google.common.cache.e<K, V> eVar2 = eVar; eVar2 != null; eVar2 = eVar2.getNext()) {
                    K key = eVar2.getKey();
                    if (eVar2.getHash() == i && key != null && this.map.l.equivalent(k, key)) {
                        if (eVar2.getValueReference() == a0Var) {
                            this.modCount++;
                            atomicReferenceArray.set(length, removeValueFromChain(eVar, eVar2, key, i, a0Var.get(), a0Var, RemovalCause.COLLECTED));
                            this.count--;
                            return true;
                        }
                        unlock();
                        if (!isHeldByCurrentThread()) {
                            postWriteCleanup();
                        }
                        return false;
                    }
                }
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
                return false;
            } finally {
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
            }
        }

        @GuardedBy("this")
        public void recordLockedRead(com.google.common.cache.e<K, V> eVar, long j) {
            if (this.map.D()) {
                eVar.setAccessTime(j);
            }
            this.accessQueue.add(eVar);
        }

        public void recordRead(com.google.common.cache.e<K, V> eVar, long j) {
            if (this.map.D()) {
                eVar.setAccessTime(j);
            }
            this.recencyQueue.add(eVar);
        }

        @GuardedBy("this")
        public void recordWrite(com.google.common.cache.e<K, V> eVar, int i, long j) {
            drainRecencyQueue();
            this.totalWeight += i;
            if (this.map.D()) {
                eVar.setAccessTime(j);
            }
            if (this.map.F()) {
                eVar.setWriteTime(j);
            }
            this.accessQueue.add(eVar);
            this.writeQueue.add(eVar);
        }

        @NullableDecl
        public V refresh(K k, int i, CacheLoader<? super K, V> cacheLoader, boolean z) {
            m<K, V> insertLoadingValueReference = insertLoadingValueReference(k, i, z);
            if (insertLoadingValueReference == null) {
                return null;
            }
            ListenableFuture<V> loadAsync = loadAsync(k, i, insertLoadingValueReference, cacheLoader);
            if (loadAsync.isDone()) {
                try {
                    return (V) Uninterruptibles.getUninterruptibly(loadAsync);
                } catch (Throwable unused) {
                }
            }
            return null;
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
            r9 = r5.getValueReference();
            r12 = r9.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0040, code lost:
            if (r12 == null) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0042, code lost:
            r2 = com.google.common.cache.RemovalCause.EXPLICIT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0044, code lost:
            r10 = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x004a, code lost:
            if (r9.isActive() == false) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x004c, code lost:
            r2 = com.google.common.cache.RemovalCause.COLLECTED;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x004f, code lost:
            r11.modCount++;
            r0.set(r1, removeValueFromChain(r4, r5, r6, r13, r12, r9, r10));
            r11.count--;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x006b, code lost:
            return r12;
         */
        @org.checkerframework.checker.nullness.compatqual.NullableDecl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public V remove(java.lang.Object r12, int r13) {
            /*
                r11 = this;
                r11.lock()
                com.google.common.cache.a<K, V> r0 = r11.map     // Catch: java.lang.Throwable -> L78
                com.google.common.base.Ticker r0 = r0.w     // Catch: java.lang.Throwable -> L78
                long r0 = r0.read()     // Catch: java.lang.Throwable -> L78
                r11.preWriteCleanup(r0)     // Catch: java.lang.Throwable -> L78
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.cache.e<K, V>> r0 = r11.table     // Catch: java.lang.Throwable -> L78
                int r1 = r0.length()     // Catch: java.lang.Throwable -> L78
                int r1 = r1 + (-1)
                r1 = r1 & r13
                java.lang.Object r2 = r0.get(r1)     // Catch: java.lang.Throwable -> L78
                r4 = r2
                com.google.common.cache.e r4 = (com.google.common.cache.e) r4     // Catch: java.lang.Throwable -> L78
                r5 = r4
            L1f:
                r2 = 0
                if (r5 == 0) goto L6c
                java.lang.Object r6 = r5.getKey()     // Catch: java.lang.Throwable -> L78
                int r3 = r5.getHash()     // Catch: java.lang.Throwable -> L78
                if (r3 != r13) goto L73
                if (r6 == 0) goto L73
                com.google.common.cache.a<K, V> r3 = r11.map     // Catch: java.lang.Throwable -> L78
                com.google.common.base.Equivalence<java.lang.Object> r3 = r3.l     // Catch: java.lang.Throwable -> L78
                boolean r3 = r3.equivalent(r12, r6)     // Catch: java.lang.Throwable -> L78
                if (r3 == 0) goto L73
                com.google.common.cache.a$a0 r9 = r5.getValueReference()     // Catch: java.lang.Throwable -> L78
                java.lang.Object r12 = r9.get()     // Catch: java.lang.Throwable -> L78
                if (r12 == 0) goto L46
                com.google.common.cache.RemovalCause r2 = com.google.common.cache.RemovalCause.EXPLICIT     // Catch: java.lang.Throwable -> L78
            L44:
                r10 = r2
                goto L4f
            L46:
                boolean r3 = r9.isActive()     // Catch: java.lang.Throwable -> L78
                if (r3 == 0) goto L6c
                com.google.common.cache.RemovalCause r2 = com.google.common.cache.RemovalCause.COLLECTED     // Catch: java.lang.Throwable -> L78
                goto L44
            L4f:
                int r2 = r11.modCount     // Catch: java.lang.Throwable -> L78
                int r2 = r2 + 1
                r11.modCount = r2     // Catch: java.lang.Throwable -> L78
                r3 = r11
                r7 = r13
                r8 = r12
                com.google.common.cache.e r13 = r3.removeValueFromChain(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L78
                int r2 = r11.count     // Catch: java.lang.Throwable -> L78
                int r2 = r2 + (-1)
                r0.set(r1, r13)     // Catch: java.lang.Throwable -> L78
                r11.count = r2     // Catch: java.lang.Throwable -> L78
                r11.unlock()
                r11.postWriteCleanup()
                return r12
            L6c:
                r11.unlock()
                r11.postWriteCleanup()
                return r2
            L73:
                com.google.common.cache.e r5 = r5.getNext()     // Catch: java.lang.Throwable -> L78
                goto L1f
            L78:
                r12 = move-exception
                r11.unlock()
                r11.postWriteCleanup()
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.a.r.remove(java.lang.Object, int):java.lang.Object");
        }

        @GuardedBy("this")
        public void removeCollectedEntry(com.google.common.cache.e<K, V> eVar) {
            enqueueNotification(eVar.getKey(), eVar.getHash(), eVar.getValueReference().get(), eVar.getValueReference().getWeight(), RemovalCause.COLLECTED);
            this.writeQueue.remove(eVar);
            this.accessQueue.remove(eVar);
        }

        @VisibleForTesting
        @GuardedBy("this")
        public boolean removeEntry(com.google.common.cache.e<K, V> eVar, int i, RemovalCause removalCause) {
            AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.table;
            int length = (atomicReferenceArray.length() - 1) & i;
            com.google.common.cache.e<K, V> eVar2 = atomicReferenceArray.get(length);
            for (com.google.common.cache.e<K, V> eVar3 = eVar2; eVar3 != null; eVar3 = eVar3.getNext()) {
                if (eVar3 == eVar) {
                    this.modCount++;
                    atomicReferenceArray.set(length, removeValueFromChain(eVar2, eVar3, eVar3.getKey(), i, eVar3.getValueReference().get(), eVar3.getValueReference(), removalCause));
                    this.count--;
                    return true;
                }
            }
            return false;
        }

        @NullableDecl
        @GuardedBy("this")
        public com.google.common.cache.e<K, V> removeEntryFromChain(com.google.common.cache.e<K, V> eVar, com.google.common.cache.e<K, V> eVar2) {
            int i = this.count;
            com.google.common.cache.e<K, V> next = eVar2.getNext();
            while (eVar != eVar2) {
                com.google.common.cache.e<K, V> copyEntry = copyEntry(eVar, next);
                if (copyEntry != null) {
                    next = copyEntry;
                } else {
                    removeCollectedEntry(eVar);
                    i--;
                }
                eVar = eVar.getNext();
            }
            this.count = i;
            return next;
        }

        public boolean removeLoadingValue(K k, int i, m<K, V> mVar) {
            lock();
            try {
                AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                com.google.common.cache.e<K, V> eVar = atomicReferenceArray.get(length);
                com.google.common.cache.e<K, V> eVar2 = eVar;
                while (true) {
                    if (eVar2 == null) {
                        break;
                    }
                    K key = eVar2.getKey();
                    if (eVar2.getHash() == i && key != null && this.map.l.equivalent(k, key)) {
                        if (eVar2.getValueReference() == mVar) {
                            if (mVar.isActive()) {
                                eVar2.setValueReference(mVar.h());
                            } else {
                                atomicReferenceArray.set(length, removeEntryFromChain(eVar, eVar2));
                            }
                            return true;
                        }
                    } else {
                        eVar2 = eVar2.getNext();
                    }
                }
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        @NullableDecl
        @GuardedBy("this")
        public com.google.common.cache.e<K, V> removeValueFromChain(com.google.common.cache.e<K, V> eVar, com.google.common.cache.e<K, V> eVar2, @NullableDecl K k, int i, V v, a0<K, V> a0Var, RemovalCause removalCause) {
            enqueueNotification(k, i, v, a0Var.getWeight(), removalCause);
            this.writeQueue.remove(eVar2);
            this.accessQueue.remove(eVar2);
            if (a0Var.c()) {
                a0Var.b(null);
                return eVar;
            }
            return removeEntryFromChain(eVar, eVar2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x006f, code lost:
            return false;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean replace(K r18, int r19, V r20, V r21) {
            /*
                r17 = this;
                r9 = r17
                r0 = r19
                r17.lock()
                com.google.common.cache.a<K, V> r1 = r9.map     // Catch: java.lang.Throwable -> Lb5
                com.google.common.base.Ticker r1 = r1.w     // Catch: java.lang.Throwable -> Lb5
                long r7 = r1.read()     // Catch: java.lang.Throwable -> Lb5
                r9.preWriteCleanup(r7)     // Catch: java.lang.Throwable -> Lb5
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.cache.e<K, V>> r10 = r9.table     // Catch: java.lang.Throwable -> Lb5
                int r1 = r10.length()     // Catch: java.lang.Throwable -> Lb5
                r11 = 1
                int r1 = r1 - r11
                r12 = r0 & r1
                java.lang.Object r1 = r10.get(r12)     // Catch: java.lang.Throwable -> Lb5
                r2 = r1
                com.google.common.cache.e r2 = (com.google.common.cache.e) r2     // Catch: java.lang.Throwable -> Lb5
                r13 = r2
            L24:
                r14 = 0
                if (r13 == 0) goto L69
                java.lang.Object r4 = r13.getKey()     // Catch: java.lang.Throwable -> Lb5
                int r1 = r13.getHash()     // Catch: java.lang.Throwable -> Lb5
                if (r1 != r0) goto Lab
                if (r4 == 0) goto Lab
                com.google.common.cache.a<K, V> r1 = r9.map     // Catch: java.lang.Throwable -> Lb5
                com.google.common.base.Equivalence<java.lang.Object> r1 = r1.l     // Catch: java.lang.Throwable -> Lb5
                r15 = r18
                boolean r1 = r1.equivalent(r15, r4)     // Catch: java.lang.Throwable -> Lb5
                if (r1 == 0) goto Lad
                com.google.common.cache.a$a0 r16 = r13.getValueReference()     // Catch: java.lang.Throwable -> Lb5
                java.lang.Object r6 = r16.get()     // Catch: java.lang.Throwable -> Lb5
                if (r6 != 0) goto L70
                boolean r1 = r16.isActive()     // Catch: java.lang.Throwable -> Lb5
                if (r1 == 0) goto L69
                int r1 = r9.modCount     // Catch: java.lang.Throwable -> Lb5
                int r1 = r1 + r11
                r9.modCount = r1     // Catch: java.lang.Throwable -> Lb5
                com.google.common.cache.RemovalCause r8 = com.google.common.cache.RemovalCause.COLLECTED     // Catch: java.lang.Throwable -> Lb5
                r1 = r17
                r3 = r13
                r5 = r19
                r7 = r16
                com.google.common.cache.e r0 = r1.removeValueFromChain(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> Lb5
                int r1 = r9.count     // Catch: java.lang.Throwable -> Lb5
                int r1 = r1 - r11
                r10.set(r12, r0)     // Catch: java.lang.Throwable -> Lb5
                r9.count = r1     // Catch: java.lang.Throwable -> Lb5
            L69:
                r17.unlock()
                r17.postWriteCleanup()
                return r14
            L70:
                com.google.common.cache.a<K, V> r1 = r9.map     // Catch: java.lang.Throwable -> Lb5
                com.google.common.base.Equivalence<java.lang.Object> r1 = r1.m     // Catch: java.lang.Throwable -> Lb5
                r3 = r20
                boolean r1 = r1.equivalent(r3, r6)     // Catch: java.lang.Throwable -> Lb5
                if (r1 == 0) goto La7
                int r1 = r9.modCount     // Catch: java.lang.Throwable -> Lb5
                int r1 = r1 + r11
                r9.modCount = r1     // Catch: java.lang.Throwable -> Lb5
                int r5 = r16.getWeight()     // Catch: java.lang.Throwable -> Lb5
                com.google.common.cache.RemovalCause r10 = com.google.common.cache.RemovalCause.REPLACED     // Catch: java.lang.Throwable -> Lb5
                r1 = r17
                r2 = r18
                r3 = r19
                r4 = r6
                r6 = r10
                r1.enqueueNotification(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> Lb5
                r1 = r17
                r2 = r13
                r3 = r18
                r4 = r21
                r5 = r7
                r1.setValue(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> Lb5
                r9.evictEntries(r13)     // Catch: java.lang.Throwable -> Lb5
                r17.unlock()
                r17.postWriteCleanup()
                return r11
            La7:
                r9.recordLockedRead(r13, r7)     // Catch: java.lang.Throwable -> Lb5
                goto L69
            Lab:
                r15 = r18
            Lad:
                r3 = r20
                com.google.common.cache.e r13 = r13.getNext()     // Catch: java.lang.Throwable -> Lb5
                goto L24
            Lb5:
                r0 = move-exception
                r17.unlock()
                r17.postWriteCleanup()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.a.r.replace(java.lang.Object, int, java.lang.Object, java.lang.Object):boolean");
        }

        public void runLockedCleanup(long j) {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                    expireEntries(j);
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }

        public void runUnlockedCleanup() {
            if (isHeldByCurrentThread()) {
                return;
            }
            this.map.A();
        }

        public V scheduleRefresh(com.google.common.cache.e<K, V> eVar, K k, int i, V v, long j, CacheLoader<? super K, V> cacheLoader) {
            V refresh;
            return (!this.map.H() || j - eVar.getWriteTime() <= this.map.t || eVar.getValueReference().c() || (refresh = refresh(k, i, cacheLoader, true)) == null) ? v : refresh;
        }

        @GuardedBy("this")
        public void setValue(com.google.common.cache.e<K, V> eVar, K k, V v, long j) {
            a0<K, V> valueReference = eVar.getValueReference();
            int weigh = this.map.q.weigh(k, v);
            Preconditions.checkState(weigh >= 0, "Weights must be non-negative");
            eVar.setValueReference(this.map.o.referenceValue(this, eVar, v, weigh));
            recordWrite(eVar, weigh, j);
            valueReference.b(v);
        }

        public boolean storeLoadedValue(K k, int i, m<K, V> mVar, V v) {
            lock();
            try {
                long read = this.map.w.read();
                preWriteCleanup(read);
                int i2 = this.count + 1;
                if (i2 > this.threshold) {
                    expand();
                    i2 = this.count + 1;
                }
                int i3 = i2;
                AtomicReferenceArray<com.google.common.cache.e<K, V>> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                com.google.common.cache.e<K, V> eVar = atomicReferenceArray.get(length);
                com.google.common.cache.e<K, V> eVar2 = eVar;
                while (true) {
                    if (eVar2 != null) {
                        K key = eVar2.getKey();
                        if (eVar2.getHash() == i && key != null && this.map.l.equivalent(k, key)) {
                            a0<K, V> valueReference = eVar2.getValueReference();
                            V v2 = valueReference.get();
                            if (mVar != valueReference && (v2 != null || valueReference == a.E)) {
                                enqueueNotification(k, i, v, 0, RemovalCause.REPLACED);
                                return false;
                            }
                            this.modCount++;
                            if (mVar.isActive()) {
                                enqueueNotification(k, i, v2, mVar.getWeight(), v2 == null ? RemovalCause.COLLECTED : RemovalCause.REPLACED);
                                i3--;
                            }
                            setValue(eVar2, k, v, read);
                            this.count = i3;
                            evictEntries(eVar2);
                        } else {
                            eVar2 = eVar2.getNext();
                        }
                    } else {
                        this.modCount++;
                        com.google.common.cache.e<K, V> newEntry = newEntry(k, i, eVar);
                        setValue(newEntry, k, v, read);
                        atomicReferenceArray.set(length, newEntry);
                        this.count = i3;
                        evictEntries(newEntry);
                        break;
                    }
                }
                return true;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        public void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        public void tryExpireEntries(long j) {
            if (tryLock()) {
                try {
                    expireEntries(j);
                } finally {
                    unlock();
                }
            }
        }

        public V waitForLoadingValue(com.google.common.cache.e<K, V> eVar, K k, a0<K, V> a0Var) throws ExecutionException {
            if (a0Var.c()) {
                Preconditions.checkState(!Thread.holdsLock(eVar), "Recursive load of: %s", k);
                try {
                    V e = a0Var.e();
                    if (e != null) {
                        recordRead(eVar, this.map.w.read());
                        return e;
                    }
                    String valueOf = String.valueOf(k);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 35);
                    sb.append("CacheLoader returned null for key ");
                    sb.append(valueOf);
                    sb.append(".");
                    throw new CacheLoader.InvalidCacheLoadException(sb.toString());
                } finally {
                    this.statsCounter.recordMisses(1);
                }
            }
            throw new AssertionError();
        }

        @NullableDecl
        public V get(Object obj, int i) {
            try {
                if (this.count != 0) {
                    long read = this.map.w.read();
                    com.google.common.cache.e<K, V> liveEntry = getLiveEntry(obj, i, read);
                    if (liveEntry == null) {
                        return null;
                    }
                    V v = liveEntry.getValueReference().get();
                    if (v != null) {
                        recordRead(liveEntry, read);
                        return scheduleRefresh(liveEntry, liveEntry.getKey(), i, v, read, this.map.z);
                    }
                    tryDrainReferenceQueues();
                }
                return null;
            } finally {
                postReadCleanup();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
            r10 = r6.getValueReference();
            r9 = r10.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0048, code lost:
            if (r12.map.m.equivalent(r15, r9) == false) goto L22;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x004a, code lost:
            r13 = com.google.common.cache.RemovalCause.EXPLICIT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x004d, code lost:
            if (r9 != null) goto L26;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
            if (r10.isActive() == false) goto L26;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x0055, code lost:
            r13 = com.google.common.cache.RemovalCause.COLLECTED;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0057, code lost:
            r12.modCount++;
            r0.set(r1, removeValueFromChain(r5, r6, r7, r14, r9, r10, r13));
            r12.count--;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x006d, code lost:
            if (r13 != com.google.common.cache.RemovalCause.EXPLICIT) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0070, code lost:
            r2 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0077, code lost:
            return r2;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean remove(java.lang.Object r13, int r14, java.lang.Object r15) {
            /*
                r12 = this;
                r12.lock()
                com.google.common.cache.a<K, V> r0 = r12.map     // Catch: java.lang.Throwable -> L84
                com.google.common.base.Ticker r0 = r0.w     // Catch: java.lang.Throwable -> L84
                long r0 = r0.read()     // Catch: java.lang.Throwable -> L84
                r12.preWriteCleanup(r0)     // Catch: java.lang.Throwable -> L84
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.cache.e<K, V>> r0 = r12.table     // Catch: java.lang.Throwable -> L84
                int r1 = r0.length()     // Catch: java.lang.Throwable -> L84
                r2 = 1
                int r1 = r1 - r2
                r1 = r1 & r14
                java.lang.Object r3 = r0.get(r1)     // Catch: java.lang.Throwable -> L84
                r5 = r3
                com.google.common.cache.e r5 = (com.google.common.cache.e) r5     // Catch: java.lang.Throwable -> L84
                r6 = r5
            L1f:
                r3 = 0
                if (r6 == 0) goto L78
                java.lang.Object r7 = r6.getKey()     // Catch: java.lang.Throwable -> L84
                int r4 = r6.getHash()     // Catch: java.lang.Throwable -> L84
                if (r4 != r14) goto L7f
                if (r7 == 0) goto L7f
                com.google.common.cache.a<K, V> r4 = r12.map     // Catch: java.lang.Throwable -> L84
                com.google.common.base.Equivalence<java.lang.Object> r4 = r4.l     // Catch: java.lang.Throwable -> L84
                boolean r4 = r4.equivalent(r13, r7)     // Catch: java.lang.Throwable -> L84
                if (r4 == 0) goto L7f
                com.google.common.cache.a$a0 r10 = r6.getValueReference()     // Catch: java.lang.Throwable -> L84
                java.lang.Object r9 = r10.get()     // Catch: java.lang.Throwable -> L84
                com.google.common.cache.a<K, V> r13 = r12.map     // Catch: java.lang.Throwable -> L84
                com.google.common.base.Equivalence<java.lang.Object> r13 = r13.m     // Catch: java.lang.Throwable -> L84
                boolean r13 = r13.equivalent(r15, r9)     // Catch: java.lang.Throwable -> L84
                if (r13 == 0) goto L4d
                com.google.common.cache.RemovalCause r13 = com.google.common.cache.RemovalCause.EXPLICIT     // Catch: java.lang.Throwable -> L84
                goto L57
            L4d:
                if (r9 != 0) goto L78
                boolean r13 = r10.isActive()     // Catch: java.lang.Throwable -> L84
                if (r13 == 0) goto L78
                com.google.common.cache.RemovalCause r13 = com.google.common.cache.RemovalCause.COLLECTED     // Catch: java.lang.Throwable -> L84
            L57:
                int r15 = r12.modCount     // Catch: java.lang.Throwable -> L84
                int r15 = r15 + r2
                r12.modCount = r15     // Catch: java.lang.Throwable -> L84
                r4 = r12
                r8 = r14
                r11 = r13
                com.google.common.cache.e r14 = r4.removeValueFromChain(r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L84
                int r15 = r12.count     // Catch: java.lang.Throwable -> L84
                int r15 = r15 - r2
                r0.set(r1, r14)     // Catch: java.lang.Throwable -> L84
                r12.count = r15     // Catch: java.lang.Throwable -> L84
                com.google.common.cache.RemovalCause r14 = com.google.common.cache.RemovalCause.EXPLICIT     // Catch: java.lang.Throwable -> L84
                if (r13 != r14) goto L70
                goto L71
            L70:
                r2 = r3
            L71:
                r12.unlock()
                r12.postWriteCleanup()
                return r2
            L78:
                r12.unlock()
                r12.postWriteCleanup()
                return r3
            L7f:
                com.google.common.cache.e r6 = r6.getNext()     // Catch: java.lang.Throwable -> L84
                goto L1f
            L84:
                r13 = move-exception
                r12.unlock()
                r12.postWriteCleanup()
                throw r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.a.r.remove(java.lang.Object, int, java.lang.Object):boolean");
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0072, code lost:
            return null;
         */
        @org.checkerframework.checker.nullness.compatqual.NullableDecl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public V replace(K r18, int r19, V r20) {
            /*
                r17 = this;
                r9 = r17
                r0 = r19
                r17.lock()
                com.google.common.cache.a<K, V> r1 = r9.map     // Catch: java.lang.Throwable -> La7
                com.google.common.base.Ticker r1 = r1.w     // Catch: java.lang.Throwable -> La7
                long r7 = r1.read()     // Catch: java.lang.Throwable -> La7
                r9.preWriteCleanup(r7)     // Catch: java.lang.Throwable -> La7
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.cache.e<K, V>> r10 = r9.table     // Catch: java.lang.Throwable -> La7
                int r1 = r10.length()     // Catch: java.lang.Throwable -> La7
                int r1 = r1 + (-1)
                r11 = r0 & r1
                java.lang.Object r1 = r10.get(r11)     // Catch: java.lang.Throwable -> La7
                r2 = r1
                com.google.common.cache.e r2 = (com.google.common.cache.e) r2     // Catch: java.lang.Throwable -> La7
                r12 = r2
            L24:
                r13 = 0
                if (r12 == 0) goto L6c
                java.lang.Object r4 = r12.getKey()     // Catch: java.lang.Throwable -> La7
                int r1 = r12.getHash()     // Catch: java.lang.Throwable -> La7
                if (r1 != r0) goto L9f
                if (r4 == 0) goto L9f
                com.google.common.cache.a<K, V> r1 = r9.map     // Catch: java.lang.Throwable -> La7
                com.google.common.base.Equivalence<java.lang.Object> r1 = r1.l     // Catch: java.lang.Throwable -> La7
                r14 = r18
                boolean r1 = r1.equivalent(r14, r4)     // Catch: java.lang.Throwable -> La7
                if (r1 == 0) goto La1
                com.google.common.cache.a$a0 r15 = r12.getValueReference()     // Catch: java.lang.Throwable -> La7
                java.lang.Object r16 = r15.get()     // Catch: java.lang.Throwable -> La7
                if (r16 != 0) goto L73
                boolean r1 = r15.isActive()     // Catch: java.lang.Throwable -> La7
                if (r1 == 0) goto L6c
                int r1 = r9.modCount     // Catch: java.lang.Throwable -> La7
                int r1 = r1 + 1
                r9.modCount = r1     // Catch: java.lang.Throwable -> La7
                com.google.common.cache.RemovalCause r8 = com.google.common.cache.RemovalCause.COLLECTED     // Catch: java.lang.Throwable -> La7
                r1 = r17
                r3 = r12
                r5 = r19
                r6 = r16
                r7 = r15
                com.google.common.cache.e r0 = r1.removeValueFromChain(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> La7
                int r1 = r9.count     // Catch: java.lang.Throwable -> La7
                int r1 = r1 + (-1)
                r10.set(r11, r0)     // Catch: java.lang.Throwable -> La7
                r9.count = r1     // Catch: java.lang.Throwable -> La7
            L6c:
                r17.unlock()
                r17.postWriteCleanup()
                return r13
            L73:
                int r1 = r9.modCount     // Catch: java.lang.Throwable -> La7
                int r1 = r1 + 1
                r9.modCount = r1     // Catch: java.lang.Throwable -> La7
                int r5 = r15.getWeight()     // Catch: java.lang.Throwable -> La7
                com.google.common.cache.RemovalCause r6 = com.google.common.cache.RemovalCause.REPLACED     // Catch: java.lang.Throwable -> La7
                r1 = r17
                r2 = r18
                r3 = r19
                r4 = r16
                r1.enqueueNotification(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> La7
                r1 = r17
                r2 = r12
                r3 = r18
                r4 = r20
                r5 = r7
                r1.setValue(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> La7
                r9.evictEntries(r12)     // Catch: java.lang.Throwable -> La7
                r17.unlock()
                r17.postWriteCleanup()
                return r16
            L9f:
                r14 = r18
            La1:
                com.google.common.cache.e r12 = r12.getNext()     // Catch: java.lang.Throwable -> La7
                goto L24
            La7:
                r0 = move-exception
                r17.unlock()
                r17.postWriteCleanup()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.a.r.replace(java.lang.Object, int, java.lang.Object):java.lang.Object");
        }
    }
}
