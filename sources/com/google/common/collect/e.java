package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public abstract class e<E> extends h<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    public transient p1<E> backingMap;
    public transient long size;

    /* loaded from: classes10.dex */
    public class a extends e<E>.c<E> {
        public a() {
            super();
        }

        @Override // com.google.common.collect.e.c
        public E b(int i) {
            return e.this.backingMap.i(i);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends e<E>.c<Multiset.Entry<E>> {
        public b() {
            super();
        }

        @Override // com.google.common.collect.e.c
        /* renamed from: c */
        public Multiset.Entry<E> b(int i) {
            return e.this.backingMap.g(i);
        }
    }

    /* loaded from: classes10.dex */
    public abstract class c<T> implements Iterator<T> {
        public int h;
        public int i = -1;
        public int j;

        public c() {
            this.h = e.this.backingMap.e();
            this.j = e.this.backingMap.d;
        }

        public final void a() {
            if (e.this.backingMap.d != this.j) {
                throw new ConcurrentModificationException();
            }
        }

        public abstract T b(int i);

        @Override // java.util.Iterator
        public boolean hasNext() {
            a();
            return this.h >= 0;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T b = b(this.h);
                int i = this.h;
                this.i = i;
                this.h = e.this.backingMap.s(i);
                return b;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            e eVar;
            a();
            u.e(this.i != -1);
            e.this.size -= eVar.backingMap.x(this.i);
            this.h = e.this.backingMap.t(this.h, this.i);
            this.i = -1;
            this.j = e.this.backingMap.d;
        }
    }

    public e(int i) {
        init(i);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int h = e2.h(objectInputStream);
        init(3);
        e2.g(this, objectInputStream, h);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        e2.k(this, objectOutputStream);
    }

    @Override // com.google.common.collect.h, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final int add(@NullableDecl E e, int i) {
        if (i == 0) {
            return count(e);
        }
        Preconditions.checkArgument(i > 0, "occurrences cannot be negative: %s", i);
        int m = this.backingMap.m(e);
        if (m == -1) {
            this.backingMap.u(e, i);
            this.size += i;
            return 0;
        }
        int k = this.backingMap.k(m);
        long j = i;
        long j2 = k + j;
        Preconditions.checkArgument(j2 <= 2147483647L, "too many occurrences: %s", j2);
        this.backingMap.B(m, (int) j2);
        this.size += j;
        return k;
    }

    public void addTo(Multiset<? super E> multiset) {
        Preconditions.checkNotNull(multiset);
        int e = this.backingMap.e();
        while (e >= 0) {
            multiset.add((E) this.backingMap.i(e), this.backingMap.k(e));
            e = this.backingMap.s(e);
        }
    }

    @Override // com.google.common.collect.h, java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.backingMap.a();
        this.size = 0L;
    }

    @Override // com.google.common.collect.Multiset
    public final int count(@NullableDecl Object obj) {
        return this.backingMap.f(obj);
    }

    @Override // com.google.common.collect.h
    public final int distinctElements() {
        return this.backingMap.C();
    }

    @Override // com.google.common.collect.h
    public final Iterator<E> elementIterator() {
        return new a();
    }

    @Override // com.google.common.collect.h
    public final Iterator<Multiset.Entry<E>> entryIterator() {
        return new b();
    }

    public abstract void init(int i);

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public final Iterator<E> iterator() {
        return Multisets.h(this);
    }

    @Override // com.google.common.collect.h, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final int remove(@NullableDecl Object obj, int i) {
        if (i == 0) {
            return count(obj);
        }
        Preconditions.checkArgument(i > 0, "occurrences cannot be negative: %s", i);
        int m = this.backingMap.m(obj);
        if (m == -1) {
            return 0;
        }
        int k = this.backingMap.k(m);
        if (k > i) {
            this.backingMap.B(m, k - i);
        } else {
            this.backingMap.x(m);
            i = k;
        }
        this.size -= i;
        return k;
    }

    @Override // com.google.common.collect.h, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final int setCount(@NullableDecl E e, int i) {
        u.b(i, "count");
        p1<E> p1Var = this.backingMap;
        int v = i == 0 ? p1Var.v(e) : p1Var.u(e, i);
        this.size += i - v;
        return v;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public final int size() {
        return Ints.saturatedCast(this.size);
    }

    @Override // com.google.common.collect.h, com.google.common.collect.Multiset
    public final boolean setCount(@NullableDecl E e, int i, int i2) {
        u.b(i, "oldCount");
        u.b(i2, "newCount");
        int m = this.backingMap.m(e);
        if (m == -1) {
            if (i != 0) {
                return false;
            }
            if (i2 > 0) {
                this.backingMap.u(e, i2);
                this.size += i2;
            }
            return true;
        } else if (this.backingMap.k(m) != i) {
            return false;
        } else {
            if (i2 == 0) {
                this.backingMap.x(m);
                this.size -= i;
            } else {
                this.backingMap.B(m, i2);
                this.size += i2 - i;
            }
            return true;
        }
    }
}
