package com.jakewharton.rxrelay2;

import com.jakewharton.rxrelay2.AppendOnlyLinkedArrayList;
import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import java.lang.reflect.Array;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes11.dex */
public final class BehaviorRelay<T> extends Relay<T> {
    public static final Object[] m = new Object[0];
    public static final a[] n = new a[0];
    public final AtomicReference<T> h;
    public final AtomicReference<a<T>[]> i;
    public final Lock j;
    public final Lock k;
    public long l;

    /* loaded from: classes11.dex */
    public static final class a<T> implements Disposable, AppendOnlyLinkedArrayList.NonThrowingPredicate<T> {
        public final Observer<? super T> h;
        public final BehaviorRelay<T> i;
        public boolean j;
        public boolean k;
        public AppendOnlyLinkedArrayList<T> l;
        public boolean m;
        public volatile boolean n;
        public long o;

        public a(Observer<? super T> observer, BehaviorRelay<T> behaviorRelay) {
            this.h = observer;
            this.i = behaviorRelay;
        }

        public void a() {
            if (this.n) {
                return;
            }
            synchronized (this) {
                if (this.n) {
                    return;
                }
                if (this.j) {
                    return;
                }
                BehaviorRelay<T> behaviorRelay = this.i;
                Lock lock = behaviorRelay.j;
                lock.lock();
                this.o = behaviorRelay.l;
                T t = behaviorRelay.h.get();
                lock.unlock();
                this.k = t != null;
                this.j = true;
                if (t != null) {
                    test(t);
                    b();
                }
            }
        }

        public void b() {
            AppendOnlyLinkedArrayList<T> appendOnlyLinkedArrayList;
            while (!this.n) {
                synchronized (this) {
                    appendOnlyLinkedArrayList = this.l;
                    if (appendOnlyLinkedArrayList == null) {
                        this.k = false;
                        return;
                    }
                    this.l = null;
                }
                appendOnlyLinkedArrayList.c(this);
            }
        }

        public void c(T t, long j) {
            if (this.n) {
                return;
            }
            if (!this.m) {
                synchronized (this) {
                    if (this.n) {
                        return;
                    }
                    if (this.o == j) {
                        return;
                    }
                    if (this.k) {
                        AppendOnlyLinkedArrayList<T> appendOnlyLinkedArrayList = this.l;
                        if (appendOnlyLinkedArrayList == null) {
                            appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                            this.l = appendOnlyLinkedArrayList;
                        }
                        appendOnlyLinkedArrayList.b(t);
                        return;
                    }
                    this.j = true;
                    this.m = true;
                }
            }
            test(t);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.n) {
                return;
            }
            this.n = true;
            this.i.e(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.n;
        }

        @Override // com.jakewharton.rxrelay2.AppendOnlyLinkedArrayList.NonThrowingPredicate, io.reactivex.functions.Predicate
        public boolean test(T t) {
            if (this.n) {
                return false;
            }
            this.h.onNext(t);
            return false;
        }
    }

    public BehaviorRelay() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.j = reentrantReadWriteLock.readLock();
        this.k = reentrantReadWriteLock.writeLock();
        this.i = new AtomicReference<>(n);
        this.h = new AtomicReference<>();
    }

    @CheckReturnValue
    @NonNull
    public static <T> BehaviorRelay<T> create() {
        return new BehaviorRelay<>();
    }

    @CheckReturnValue
    @NonNull
    public static <T> BehaviorRelay<T> createDefault(T t) {
        return new BehaviorRelay<>(t);
    }

    @Override // com.jakewharton.rxrelay2.Relay, io.reactivex.functions.Consumer
    public void accept(T t) {
        Objects.requireNonNull(t, "value == null");
        f(t);
        for (a<T> aVar : this.i.get()) {
            aVar.c(t, this.l);
        }
    }

    public void d(a<T> aVar) {
        a<T>[] aVarArr;
        a<T>[] aVarArr2;
        do {
            aVarArr = this.i.get();
            int length = aVarArr.length;
            aVarArr2 = new a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.i.compareAndSet(aVarArr, aVarArr2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void e(a<T> aVar) {
        a<T>[] aVarArr;
        a[] aVarArr2;
        do {
            aVarArr = this.i.get();
            int length = aVarArr.length;
            if (length == 0) {
                return;
            }
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (aVarArr[i2] == aVar) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                aVarArr2 = n;
            } else {
                a[] aVarArr3 = new a[length - 1];
                System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                aVarArr2 = aVarArr3;
            }
        } while (!this.i.compareAndSet(aVarArr, aVarArr2));
    }

    public void f(T t) {
        this.k.lock();
        this.l++;
        this.h.lazySet(t);
        this.k.unlock();
    }

    @Nullable
    public T getValue() {
        return this.h.get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Object[] getValues() {
        Object[] objArr = m;
        Object[] values = getValues(objArr);
        return values == objArr ? new Object[0] : values;
    }

    @Override // com.jakewharton.rxrelay2.Relay
    public boolean hasObservers() {
        return this.i.get().length != 0;
    }

    public boolean hasValue() {
        return this.h.get() != null;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        a<T> aVar = new a<>(observer, this);
        observer.onSubscribe(aVar);
        d(aVar);
        if (aVar.n) {
            e(aVar);
        } else {
            aVar.a();
        }
    }

    @Deprecated
    public T[] getValues(T[] tArr) {
        T t = this.h.get();
        if (t == null) {
            if (tArr.length != 0) {
                tArr[0] = null;
            }
            return tArr;
        } else if (tArr.length != 0) {
            tArr[0] = t;
            if (tArr.length != 1) {
                tArr[1] = null;
                return tArr;
            }
            return tArr;
        } else {
            T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1));
            tArr2[0] = t;
            return tArr2;
        }
    }

    public BehaviorRelay(T t) {
        this();
        Objects.requireNonNull(t, "defaultValue == null");
        this.h.lazySet(t);
    }
}
