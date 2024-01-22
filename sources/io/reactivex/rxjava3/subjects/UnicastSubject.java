package io.reactivex.rxjava3.subjects;

import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.functions.ObjectHelper;
import io.reactivex.rxjava3.internal.fuseable.SimpleQueue;
import io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable;
import io.reactivex.rxjava3.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class UnicastSubject<T> extends Subject<T> {
    public final SpscLinkedArrayQueue<T> h;
    public final AtomicReference<Runnable> j;
    public final boolean k;
    public volatile boolean l;
    public volatile boolean m;
    public Throwable n;
    public boolean q;
    public final AtomicReference<Observer<? super T>> i = new AtomicReference<>();
    public final AtomicBoolean o = new AtomicBoolean();
    public final BasicIntQueueDisposable<T> p = new a();

    /* loaded from: classes12.dex */
    public final class a extends BasicIntQueueDisposable<T> {
        private static final long serialVersionUID = 7926949470189395511L;

        public a() {
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public void clear() {
            UnicastSubject.this.h.clear();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
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

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return UnicastSubject.this.l;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return UnicastSubject.this.h.isEmpty();
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() {
            return UnicastSubject.this.h.poll();
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 2) != 0) {
                UnicastSubject.this.q = true;
                return 2;
            }
            return 0;
        }
    }

    public UnicastSubject(int i, Runnable runnable, boolean z) {
        this.h = new SpscLinkedArrayQueue<>(i);
        this.j = new AtomicReference<>(runnable);
        this.k = z;
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create() {
        return new UnicastSubject<>(Observable.bufferSize(), null, true);
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

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
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

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasComplete() {
        return this.m && this.n == null;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasObservers() {
        return this.i.get() != null;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
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

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        if (this.m || this.l) {
            return;
        }
        this.m = true;
        d();
        e();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable th) {
        ExceptionHelper.nullCheck(th, "onError called with a null Throwable.");
        if (!this.m && !this.l) {
            this.n = th;
            this.m = true;
            d();
            e();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        ExceptionHelper.nullCheck(t, "onNext called with a null value.");
        if (this.m || this.l) {
            return;
        }
        this.h.offer(t);
        e();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onSubscribe(Disposable disposable) {
        if (this.m || this.l) {
            disposable.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observable
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
        ObjectHelper.verifyPositive(i, "capacityHint");
        return new UnicastSubject<>(i, null, true);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create(int i, @NonNull Runnable runnable) {
        ObjectHelper.verifyPositive(i, "capacityHint");
        Objects.requireNonNull(runnable, "onTerminate");
        return new UnicastSubject<>(i, runnable, true);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create(int i, @NonNull Runnable runnable, boolean z) {
        ObjectHelper.verifyPositive(i, "capacityHint");
        Objects.requireNonNull(runnable, "onTerminate");
        return new UnicastSubject<>(i, runnable, z);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create(boolean z) {
        return new UnicastSubject<>(Observable.bufferSize(), null, z);
    }
}
