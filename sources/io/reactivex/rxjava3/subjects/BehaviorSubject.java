package io.reactivex.rxjava3.subjects;

import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes12.dex */
public final class BehaviorSubject<T> extends Subject<T> {
    public static final a[] o = new a[0];
    public static final a[] p = new a[0];
    public final AtomicReference<Object> h;
    public final AtomicReference<a<T>[]> i;
    public final ReadWriteLock j;
    public final Lock k;
    public final Lock l;
    public final AtomicReference<Throwable> m;
    public long n;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Disposable, AppendOnlyLinkedArrayList.NonThrowingPredicate<Object> {
        public final Observer<? super T> h;
        public final BehaviorSubject<T> i;
        public boolean j;
        public boolean k;
        public AppendOnlyLinkedArrayList<Object> l;
        public boolean m;
        public volatile boolean n;
        public long o;

        public a(Observer<? super T> observer, BehaviorSubject<T> behaviorSubject) {
            this.h = observer;
            this.i = behaviorSubject;
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
                BehaviorSubject<T> behaviorSubject = this.i;
                Lock lock = behaviorSubject.k;
                lock.lock();
                this.o = behaviorSubject.n;
                Object obj = behaviorSubject.h.get();
                lock.unlock();
                this.k = obj != null;
                this.j = true;
                if (obj == null || test(obj)) {
                    return;
                }
                b();
            }
        }

        public void b() {
            AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList;
            while (!this.n) {
                synchronized (this) {
                    appendOnlyLinkedArrayList = this.l;
                    if (appendOnlyLinkedArrayList == null) {
                        this.k = false;
                        return;
                    }
                    this.l = null;
                }
                appendOnlyLinkedArrayList.forEachWhile(this);
            }
        }

        public void c(Object obj, long j) {
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
                        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.l;
                        if (appendOnlyLinkedArrayList == null) {
                            appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                            this.l = appendOnlyLinkedArrayList;
                        }
                        appendOnlyLinkedArrayList.add(obj);
                        return;
                    }
                    this.j = true;
                    this.m = true;
                }
            }
            test(obj);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.n) {
                return;
            }
            this.n = true;
            this.i.e(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.n;
        }

        @Override // io.reactivex.rxjava3.internal.util.AppendOnlyLinkedArrayList.NonThrowingPredicate, io.reactivex.rxjava3.functions.Predicate
        public boolean test(Object obj) {
            return this.n || NotificationLite.accept(obj, this.h);
        }
    }

    public BehaviorSubject(T t) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.j = reentrantReadWriteLock;
        this.k = reentrantReadWriteLock.readLock();
        this.l = reentrantReadWriteLock.writeLock();
        this.i = new AtomicReference<>(o);
        this.h = new AtomicReference<>(t);
        this.m = new AtomicReference<>();
    }

    @CheckReturnValue
    @NonNull
    public static <T> BehaviorSubject<T> create() {
        return new BehaviorSubject<>(null);
    }

    @CheckReturnValue
    @NonNull
    public static <T> BehaviorSubject<T> createDefault(T t) {
        Objects.requireNonNull(t, "defaultValue is null");
        return new BehaviorSubject<>(t);
    }

    public boolean d(a<T> aVar) {
        a<T>[] aVarArr;
        a<T>[] aVarArr2;
        do {
            aVarArr = this.i.get();
            if (aVarArr == p) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.i.compareAndSet(aVarArr, aVarArr2));
        return true;
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
                aVarArr2 = o;
            } else {
                a[] aVarArr3 = new a[length - 1];
                System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                aVarArr2 = aVarArr3;
            }
        } while (!this.i.compareAndSet(aVarArr, aVarArr2));
    }

    public void f(Object obj) {
        this.l.lock();
        this.n++;
        this.h.lazySet(obj);
        this.l.unlock();
    }

    public a<T>[] g(Object obj) {
        f(obj);
        return this.i.getAndSet(p);
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    @Nullable
    public Throwable getThrowable() {
        Object obj = this.h.get();
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    @CheckReturnValue
    @Nullable
    public T getValue() {
        Object obj = this.h.get();
        if (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) {
            return null;
        }
        return (T) NotificationLite.getValue(obj);
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasComplete() {
        return NotificationLite.isComplete(this.h.get());
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasObservers() {
        return this.i.get().length != 0;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasThrowable() {
        return NotificationLite.isError(this.h.get());
    }

    @CheckReturnValue
    public boolean hasValue() {
        Object obj = this.h.get();
        return (obj == null || NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) ? false : true;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        if (this.m.compareAndSet(null, ExceptionHelper.TERMINATED)) {
            Object complete = NotificationLite.complete();
            for (a<T> aVar : g(complete)) {
                aVar.c(complete, this.n);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable th) {
        ExceptionHelper.nullCheck(th, "onError called with a null Throwable.");
        if (!this.m.compareAndSet(null, th)) {
            RxJavaPlugins.onError(th);
            return;
        }
        Object error = NotificationLite.error(th);
        for (a<T> aVar : g(error)) {
            aVar.c(error, this.n);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        ExceptionHelper.nullCheck(t, "onNext called with a null value.");
        if (this.m.get() != null) {
            return;
        }
        Object next = NotificationLite.next(t);
        f(next);
        for (a<T> aVar : this.i.get()) {
            aVar.c(next, this.n);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onSubscribe(Disposable disposable) {
        if (this.m.get() != null) {
            disposable.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        a<T> aVar = new a<>(observer, this);
        observer.onSubscribe(aVar);
        if (d(aVar)) {
            if (aVar.n) {
                e(aVar);
                return;
            } else {
                aVar.a();
                return;
            }
        }
        Throwable th = this.m.get();
        if (th == ExceptionHelper.TERMINATED) {
            observer.onComplete();
        } else {
            observer.onError(th);
        }
    }
}
