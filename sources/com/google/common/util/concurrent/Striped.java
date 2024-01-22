package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.MapMaker;
import com.google.common.math.IntMath;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public abstract class Striped<L> {

    /* renamed from: a  reason: collision with root package name */
    public static final Supplier<ReadWriteLock> f10802a = new e();
    public static final Supplier<ReadWriteLock> b = new f();

    /* loaded from: classes10.dex */
    public class a implements Supplier<Lock> {
        @Override // com.google.common.base.Supplier
        /* renamed from: a */
        public Lock get() {
            return new i();
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Supplier<Lock> {
        @Override // com.google.common.base.Supplier
        /* renamed from: a */
        public Lock get() {
            return new ReentrantLock(false);
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Supplier<Semaphore> {
        public final /* synthetic */ int h;

        public c(int i) {
            this.h = i;
        }

        @Override // com.google.common.base.Supplier
        /* renamed from: a */
        public Semaphore get() {
            return new j(this.h);
        }
    }

    /* loaded from: classes10.dex */
    public class d implements Supplier<Semaphore> {
        public final /* synthetic */ int h;

        public d(int i) {
            this.h = i;
        }

        @Override // com.google.common.base.Supplier
        /* renamed from: a */
        public Semaphore get() {
            return new Semaphore(this.h, false);
        }
    }

    /* loaded from: classes10.dex */
    public class e implements Supplier<ReadWriteLock> {
        @Override // com.google.common.base.Supplier
        /* renamed from: a */
        public ReadWriteLock get() {
            return new ReentrantReadWriteLock();
        }
    }

    /* loaded from: classes10.dex */
    public class f implements Supplier<ReadWriteLock> {
        @Override // com.google.common.base.Supplier
        /* renamed from: a */
        public ReadWriteLock get() {
            return new o();
        }
    }

    /* loaded from: classes10.dex */
    public static class g<L> extends k<L> {
        public final Object[] d;

        public /* synthetic */ g(int i, Supplier supplier, a aVar) {
            this(i, supplier);
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int i) {
            return (L) this.d[i];
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.d.length;
        }

        public g(int i, Supplier<L> supplier) {
            super(i);
            int i2 = 0;
            Preconditions.checkArgument(i <= 1073741824, "Stripes must be <= 2^30)");
            this.d = new Object[this.c + 1];
            while (true) {
                Object[] objArr = this.d;
                if (i2 >= objArr.length) {
                    return;
                }
                objArr[i2] = supplier.get();
                i2++;
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static class h<L> extends k<L> {
        public final ConcurrentMap<Integer, L> d;
        public final Supplier<L> e;
        public final int f;

        public h(int i, Supplier<L> supplier) {
            super(i);
            int i2 = this.c;
            this.f = i2 == -1 ? Integer.MAX_VALUE : i2 + 1;
            this.e = supplier;
            this.d = new MapMaker().weakValues().makeMap();
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int i) {
            if (this.f != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i, size());
            }
            L l = this.d.get(Integer.valueOf(i));
            if (l != null) {
                return l;
            }
            L l2 = this.e.get();
            return (L) MoreObjects.firstNonNull(this.d.putIfAbsent(Integer.valueOf(i), l2), l2);
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.f;
        }
    }

    /* loaded from: classes10.dex */
    public static class i extends ReentrantLock {
        public long unused1;
        public long unused2;
        public long unused3;

        public i() {
            super(false);
        }
    }

    /* loaded from: classes10.dex */
    public static class j extends Semaphore {
        public long unused1;
        public long unused2;
        public long unused3;

        public j(int i) {
            super(i, false);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class k<L> extends Striped<L> {
        public final int c;

        public k(int i) {
            super(null);
            Preconditions.checkArgument(i > 0, "Stripes must be positive");
            this.c = i > 1073741824 ? -1 : Striped.c(i) - 1;
        }

        @Override // com.google.common.util.concurrent.Striped
        public final int e(Object obj) {
            return Striped.g(obj.hashCode()) & this.c;
        }

        @Override // com.google.common.util.concurrent.Striped
        public final L get(Object obj) {
            return getAt(e(obj));
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static class l<L> extends k<L> {
        public final AtomicReferenceArray<a<? extends L>> d;
        public final Supplier<L> e;
        public final int f;
        public final ReferenceQueue<L> g;

        /* loaded from: classes10.dex */
        public static final class a<L> extends WeakReference<L> {

            /* renamed from: a  reason: collision with root package name */
            public final int f10803a;

            public a(L l, int i, ReferenceQueue<L> referenceQueue) {
                super(l, referenceQueue);
                this.f10803a = i;
            }
        }

        public l(int i, Supplier<L> supplier) {
            super(i);
            this.g = new ReferenceQueue<>();
            int i2 = this.c;
            int i3 = i2 == -1 ? Integer.MAX_VALUE : i2 + 1;
            this.f = i3;
            this.d = new AtomicReferenceArray<>(i3);
            this.e = supplier;
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int i) {
            L l;
            if (this.f != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i, size());
            }
            a<? extends L> aVar = this.d.get(i);
            L l2 = aVar == null ? null : aVar.get();
            if (l2 != null) {
                return l2;
            }
            L l3 = this.e.get();
            a<? extends L> aVar2 = new a<>(l3, i, this.g);
            while (!this.d.compareAndSet(i, aVar, aVar2)) {
                aVar = this.d.get(i);
                if (aVar == null) {
                    l = null;
                    continue;
                } else {
                    l = aVar.get();
                    continue;
                }
                if (l != null) {
                    return l;
                }
            }
            h();
            return l3;
        }

        public final void h() {
            while (true) {
                Reference<? extends L> poll = this.g.poll();
                if (poll == null) {
                    return;
                }
                a<? extends L> aVar = (a) poll;
                this.d.compareAndSet(aVar.f10803a, aVar, null);
            }
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.f;
        }
    }

    /* loaded from: classes10.dex */
    public static final class m extends com.google.common.util.concurrent.h {

        /* renamed from: a  reason: collision with root package name */
        public final Condition f10804a;

        public m(Condition condition, o oVar) {
            this.f10804a = condition;
        }

        @Override // com.google.common.util.concurrent.h
        public Condition a() {
            return this.f10804a;
        }
    }

    /* loaded from: classes10.dex */
    public static final class n extends com.google.common.util.concurrent.j {
        public final Lock h;
        public final o i;

        public n(Lock lock, o oVar) {
            this.h = lock;
            this.i = oVar;
        }

        @Override // com.google.common.util.concurrent.j
        public Lock a() {
            return this.h;
        }

        @Override // java.util.concurrent.locks.Lock
        public Condition newCondition() {
            return new m(this.h.newCondition(), this.i);
        }
    }

    /* loaded from: classes10.dex */
    public static final class o implements ReadWriteLock {
        public final ReadWriteLock h = new ReentrantReadWriteLock();

        @Override // java.util.concurrent.locks.ReadWriteLock
        public Lock readLock() {
            return new n(this.h.readLock(), this);
        }

        @Override // java.util.concurrent.locks.ReadWriteLock
        public Lock writeLock() {
            return new n(this.h.writeLock(), this);
        }
    }

    public /* synthetic */ Striped(a aVar) {
        this();
    }

    public static int c(int i2) {
        return 1 << IntMath.log2(i2, RoundingMode.CEILING);
    }

    public static <L> Striped<L> d(int i2, Supplier<L> supplier) {
        return new g(i2, supplier, null);
    }

    public static <L> Striped<L> f(int i2, Supplier<L> supplier) {
        if (i2 < 1024) {
            return new l(i2, supplier);
        }
        return new h(i2, supplier);
    }

    public static int g(int i2) {
        int i3 = i2 ^ ((i2 >>> 20) ^ (i2 >>> 12));
        return (i3 >>> 4) ^ ((i3 >>> 7) ^ i3);
    }

    public static Striped<Lock> lazyWeakLock(int i2) {
        return f(i2, new b());
    }

    public static Striped<ReadWriteLock> lazyWeakReadWriteLock(int i2) {
        return f(i2, b);
    }

    public static Striped<Semaphore> lazyWeakSemaphore(int i2, int i3) {
        return f(i2, new d(i3));
    }

    public static Striped<Lock> lock(int i2) {
        return d(i2, new a());
    }

    public static Striped<ReadWriteLock> readWriteLock(int i2) {
        return d(i2, f10802a);
    }

    public static Striped<Semaphore> semaphore(int i2, int i3) {
        return d(i2, new c(i3));
    }

    public Iterable<L> bulkGet(Iterable<?> iterable) {
        Object[] array = Iterables.toArray(iterable, Object.class);
        if (array.length == 0) {
            return ImmutableList.of();
        }
        int[] iArr = new int[array.length];
        for (int i2 = 0; i2 < array.length; i2++) {
            iArr[i2] = e(array[i2]);
        }
        Arrays.sort(iArr);
        int i3 = iArr[0];
        array[0] = getAt(i3);
        for (int i4 = 1; i4 < array.length; i4++) {
            int i5 = iArr[i4];
            if (i5 == i3) {
                array[i4] = array[i4 - 1];
            } else {
                array[i4] = getAt(i5);
                i3 = i5;
            }
        }
        return Collections.unmodifiableList(Arrays.asList(array));
    }

    public abstract int e(Object obj);

    public abstract L get(Object obj);

    public abstract L getAt(int i2);

    public abstract int size();

    public Striped() {
    }
}
