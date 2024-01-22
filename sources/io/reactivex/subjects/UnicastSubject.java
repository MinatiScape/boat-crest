package io.reactivex.subjects;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class UnicastSubject<T> extends Subject<T> {
    public final SpscLinkedArrayQueue<T> h;
    public final AtomicReference<Observer<? super T>> i;
    public final AtomicReference<Runnable> j;
    public final boolean k;
    public volatile boolean l;
    public volatile boolean m;
    public Throwable n;
    public final AtomicBoolean o;
    public final BasicIntQueueDisposable<T> p;
    public boolean q;

    /* loaded from: classes12.dex */
    public final class a extends BasicIntQueueDisposable<T> {
        private static final long serialVersionUID = 7926949470189395511L;

        public a() {
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            UnicastSubject.this.h.clear();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (UnicastSubject.this.l) {
                return;
            }
            UnicastSubject.this.l = true;
            UnicastSubject.this.d();
            UnicastSubject.this.i.lazySet(null);
            if (UnicastSubject.this.p.getAndIncrement() == 0) {
                UnicastSubject.this.i.lazySet(null);
                UnicastSubject unicastSubject = UnicastSubject.this;
                if (unicastSubject.q) {
                    return;
                }
                unicastSubject.h.clear();
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return UnicastSubject.this.l;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return UnicastSubject.this.h.isEmpty();
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() throws Exception {
            return UnicastSubject.this.h.poll();
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 2) != 0) {
                UnicastSubject.this.q = true;
                return 2;
            }
            return 0;
        }
    }

    public UnicastSubject(int i, boolean z) {
        this.h = new SpscLinkedArrayQueue<>(ObjectHelper.verifyPositive(i, "capacityHint"));
        this.j = new AtomicReference<>();
        this.k = z;
        this.i = new AtomicReference<>();
        this.o = new AtomicBoolean();
        this.p = new a();
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create() {
        return new UnicastSubject<>(Observable.bufferSize(), true);
    }

    public void d() {
        Runnable runnable = this.j.get();
        if (runnable == null || !this.j.compareAndSet(runnable, null)) {
            return;
        }
        runnable.run();
    }

    public void e() {
        if (this.p.getAndIncrement() != 0) {
            return;
        }
        Observer<? super T> observer = this.i.get();
        int i = 1;
        while (observer == null) {
            i = this.p.addAndGet(-i);
            if (i == 0) {
                return;
            }
            observer = this.i.get();
        }
        if (this.q) {
            f(observer);
        } else {
            g(observer);
        }
    }

    public void f(Observer<? super T> observer) {
        SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.h;
        int i = 1;
        boolean z = !this.k;
        while (!this.l) {
            boolean z2 = this.m;
            if (z && z2 && i(spscLinkedArrayQueue, observer)) {
                return;
            }
            observer.onNext(null);
            if (z2) {
                h(observer);
                return;
            }
            i = this.p.addAndGet(-i);
            if (i == 0) {
                return;
            }
        }
        this.i.lazySet(null);
    }

    public void g(Observer<? super T> observer) {
        SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.h;
        boolean z = !this.k;
        boolean z2 = true;
        int i = 1;
        while (!this.l) {
            boolean z3 = this.m;
            Object obj = (T) this.h.poll();
            boolean z4 = obj == null;
            if (z3) {
                if (z && z2) {
                    if (i(spscLinkedArrayQueue, observer)) {
                        return;
                    }
                    z2 = false;
                }
                if (z4) {
                    h(observer);
                    return;
                }
            }
            if (z4) {
                i = this.p.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            } else {
                observer.onNext(obj);
            }
        }
        this.i.lazySet(null);
        spscLinkedArrayQueue.clear();
    }

    @Override // io.reactivex.subjects.Subject
    @Nullable
    public Throwable getThrowable() {
        if (this.m) {
            return this.n;
        }
        return null;
    }

    public void h(Observer<? super T> observer) {
        this.i.lazySet(null);
        Throwable th = this.n;
        if (th != null) {
            observer.onError(th);
        } else {
            observer.onComplete();
        }
    }

    @Override // io.reactivex.subjects.Subject
    public boolean hasComplete() {
        return this.m && this.n == null;
    }

    @Override // io.reactivex.subjects.Subject
    public boolean hasObservers() {
        return this.i.get() != null;
    }

    @Override // io.reactivex.subjects.Subject
    public boolean hasThrowable() {
        return this.m && this.n != null;
    }

    public boolean i(SimpleQueue<T> simpleQueue, Observer<? super T> observer) {
        Throwable th = this.n;
        if (th != null) {
            this.i.lazySet(null);
            simpleQueue.clear();
            observer.onError(th);
            return true;
        }
        return false;
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        if (this.m || this.l) {
            return;
        }
        this.m = true;
        d();
        e();
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.m && !this.l) {
            this.n = th;
            this.m = true;
            d();
            e();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.m || this.l) {
            return;
        }
        this.h.offer(t);
        e();
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        if (this.m || this.l) {
            disposable.dispose();
        }
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        if (!this.o.get() && this.o.compareAndSet(false, true)) {
            observer.onSubscribe(this.p);
            this.i.lazySet(observer);
            if (this.l) {
                this.i.lazySet(null);
                return;
            } else {
                e();
                return;
            }
        }
        EmptyDisposable.error(new IllegalStateException("Only a single observer allowed."), observer);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create(int i) {
        return new UnicastSubject<>(i, true);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create(int i, Runnable runnable) {
        return new UnicastSubject<>(i, runnable, true);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create(int i, Runnable runnable, boolean z) {
        return new UnicastSubject<>(i, runnable, z);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create(boolean z) {
        return new UnicastSubject<>(Observable.bufferSize(), z);
    }

    public UnicastSubject(int i, Runnable runnable, boolean z) {
        this.h = new SpscLinkedArrayQueue<>(ObjectHelper.verifyPositive(i, "capacityHint"));
        this.j = new AtomicReference<>(ObjectHelper.requireNonNull(runnable, "onTerminate"));
        this.k = z;
        this.i = new AtomicReference<>();
        this.o = new AtomicBoolean();
        this.p = new a();
    }
}
