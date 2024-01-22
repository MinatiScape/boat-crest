package io.reactivex.rxjava3.internal.queue;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.internal.fuseable.SimplePlainQueue;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class MpscLinkedQueue<T> implements SimplePlainQueue<T> {
    public final AtomicReference<a<T>> h = new AtomicReference<>();
    public final AtomicReference<a<T>> i = new AtomicReference<>();

    /* loaded from: classes12.dex */
    public static final class a<E> extends AtomicReference<a<E>> {
        private static final long serialVersionUID = 2404266111789071508L;
        private E value;

        public a() {
        }

        public E getAndNullValue() {
            E lpValue = lpValue();
            spValue(null);
            return lpValue;
        }

        public E lpValue() {
            return this.value;
        }

        public a<E> lvNext() {
            return get();
        }

        public void soNext(a<E> aVar) {
            lazySet(aVar);
        }

        public void spValue(E e) {
            this.value = e;
        }

        public a(E e) {
            spValue(e);
        }
    }

    public MpscLinkedQueue() {
        a<T> aVar = new a<>();
        d(aVar);
        e(aVar);
    }

    public a<T> a() {
        return this.i.get();
    }

    public a<T> b() {
        return this.i.get();
    }

    public a<T> c() {
        return this.h.get();
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
    public void clear() {
        while (poll() != null && !isEmpty()) {
        }
    }

    public void d(a<T> aVar) {
        this.i.lazySet(aVar);
    }

    public a<T> e(a<T> aVar) {
        return this.h.getAndSet(aVar);
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
    public boolean isEmpty() {
        return b() == c();
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
    public boolean offer(T t) {
        Objects.requireNonNull(t, "Null is not a valid element");
        a<T> aVar = new a<>(t);
        e(aVar).soNext(aVar);
        return true;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.SimplePlainQueue, io.reactivex.rxjava3.internal.fuseable.SimpleQueue
    @Nullable
    public T poll() {
        a<T> lvNext;
        a<T> a2 = a();
        a<T> lvNext2 = a2.lvNext();
        if (lvNext2 != null) {
            T andNullValue = lvNext2.getAndNullValue();
            d(lvNext2);
            return andNullValue;
        } else if (a2 != c()) {
            do {
                lvNext = a2.lvNext();
            } while (lvNext == null);
            T andNullValue2 = lvNext.getAndNullValue();
            d(lvNext);
            return andNullValue2;
        } else {
            return null;
        }
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
    public boolean offer(T t, T t2) {
        offer(t);
        offer(t2);
        return true;
    }
}
