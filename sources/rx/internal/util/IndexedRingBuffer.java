package rx.internal.util;

import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.Subscription;
import rx.functions.Func1;
/* loaded from: classes13.dex */
public final class IndexedRingBuffer<E> implements Subscription {
    public static final int l;
    public final a<E> h = new a<>();
    public final b i = new b();
    public final AtomicInteger j = new AtomicInteger();
    public final AtomicInteger k = new AtomicInteger();

    /* loaded from: classes13.dex */
    public static final class a<E> {

        /* renamed from: a  reason: collision with root package name */
        public final AtomicReferenceArray<E> f15683a = new AtomicReferenceArray<>(IndexedRingBuffer.l);
        public final AtomicReference<a<E>> b = new AtomicReference<>();

        public a<E> a() {
            if (this.b.get() != null) {
                return this.b.get();
            }
            a<E> aVar = new a<>();
            return this.b.compareAndSet(null, aVar) ? aVar : this.b.get();
        }
    }

    /* loaded from: classes13.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final AtomicIntegerArray f15684a = new AtomicIntegerArray(IndexedRingBuffer.l);
        public final AtomicReference<b> b = new AtomicReference<>();

        public int a(int i, int i2) {
            return this.f15684a.getAndSet(i, i2);
        }

        public b b() {
            if (this.b.get() != null) {
                return this.b.get();
            }
            b bVar = new b();
            return this.b.compareAndSet(null, bVar) ? bVar : this.b.get();
        }

        public void c(int i, int i2) {
            this.f15684a.set(i, i2);
        }
    }

    static {
        int i = PlatformDependent.isAndroid() ? 8 : 128;
        String property = System.getProperty("rx.indexed-ring-buffer.size");
        if (property != null) {
            try {
                i = Integer.parseInt(property);
            } catch (NumberFormatException e) {
                PrintStream printStream = System.err;
                printStream.println("Failed to set 'rx.indexed-ring-buffer.size' with value " + property + " => " + e.getMessage());
            }
        }
        l = i;
    }

    public static <T> IndexedRingBuffer<T> getInstance() {
        return new IndexedRingBuffer<>();
    }

    public final int a(Func1<? super E, Boolean> func1, int i, int i2) {
        a<E> aVar;
        int i3;
        int i4 = this.j.get();
        a<E> aVar2 = this.h;
        int i5 = l;
        if (i >= i5) {
            a<E> b2 = b(i);
            i3 = i;
            i %= i5;
            aVar = b2;
        } else {
            aVar = aVar2;
            i3 = i;
        }
        loop0: while (aVar != null) {
            while (i < l) {
                if (i3 >= i4 || i3 >= i2) {
                    break loop0;
                }
                Object obj = (E) aVar.f15683a.get(i);
                if (obj != null && !func1.call(obj).booleanValue()) {
                    return i3;
                }
                i++;
                i3++;
            }
            aVar = aVar.b.get();
            i = 0;
        }
        return i3;
    }

    public int add(E e) {
        int c = c();
        int i = l;
        if (c < i) {
            this.h.f15683a.set(c, e);
            return c;
        }
        b(c).f15683a.set(c % i, e);
        return c;
    }

    public final a<E> b(int i) {
        int i2 = l;
        if (i < i2) {
            return this.h;
        }
        int i3 = i / i2;
        a<E> aVar = this.h;
        for (int i4 = 0; i4 < i3; i4++) {
            aVar = aVar.a();
        }
        return aVar;
    }

    public final synchronized int c() {
        int andIncrement;
        int d = d();
        if (d >= 0) {
            int i = l;
            if (d < i) {
                andIncrement = this.i.a(d, -1);
            } else {
                andIncrement = e(d).a(d % i, -1);
            }
            if (andIncrement == this.j.get()) {
                this.j.getAndIncrement();
            }
        } else {
            andIncrement = this.j.getAndIncrement();
        }
        return andIncrement;
    }

    public final synchronized int d() {
        int i;
        int i2;
        do {
            i = this.k.get();
            if (i <= 0) {
                return -1;
            }
            i2 = i - 1;
        } while (!this.k.compareAndSet(i, i2));
        return i2;
    }

    public final b e(int i) {
        int i2 = l;
        if (i < i2) {
            return this.i;
        }
        int i3 = i / i2;
        b bVar = this.i;
        for (int i4 = 0; i4 < i3; i4++) {
            bVar = bVar.b();
        }
        return bVar;
    }

    public final synchronized void f(int i) {
        int andIncrement = this.k.getAndIncrement();
        int i2 = l;
        if (andIncrement < i2) {
            this.i.c(andIncrement, i);
        } else {
            e(andIncrement).c(andIncrement % i2, i);
        }
    }

    public int forEach(Func1<? super E, Boolean> func1) {
        return forEach(func1, 0);
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return false;
    }

    public void releaseToPool() {
        int i = this.j.get();
        int i2 = 0;
        loop0: for (a<E> aVar = this.h; aVar != null; aVar = aVar.b.get()) {
            int i3 = 0;
            while (i3 < l) {
                if (i2 >= i) {
                    break loop0;
                }
                aVar.f15683a.set(i3, null);
                i3++;
                i2++;
            }
        }
        this.j.set(0);
        this.k.set(0);
    }

    public E remove(int i) {
        E andSet;
        int i2 = l;
        if (i < i2) {
            andSet = this.h.f15683a.getAndSet(i, null);
        } else {
            andSet = b(i).f15683a.getAndSet(i % i2, null);
        }
        f(i);
        return andSet;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        releaseToPool();
    }

    public int forEach(Func1<? super E, Boolean> func1, int i) {
        int a2 = a(func1, i, this.j.get());
        if (i > 0 && a2 == this.j.get()) {
            return a(func1, 0, i);
        }
        if (a2 == this.j.get()) {
            return 0;
        }
        return a2;
    }
}
