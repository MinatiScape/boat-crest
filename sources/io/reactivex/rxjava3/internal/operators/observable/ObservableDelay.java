package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.observers.SerializedObserver;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class ObservableDelay<T> extends io.reactivex.rxjava3.internal.operators.observable.a<T, T> {
    public final long h;
    public final TimeUnit i;
    public final Scheduler j;
    public final boolean k;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final Observer<? super T> h;
        public final long i;
        public final TimeUnit j;
        public final Scheduler.Worker k;
        public final boolean l;
        public Disposable m;

        /* renamed from: io.reactivex.rxjava3.internal.operators.observable.ObservableDelay$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class RunnableC0847a implements Runnable {
            public RunnableC0847a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.h.onComplete();
                } finally {
                    a.this.k.dispose();
                }
            }
        }

        /* loaded from: classes12.dex */
        public final class b implements Runnable {
            public final Throwable h;

            public b(Throwable th) {
                this.h = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.h.onError(this.h);
                } finally {
                    a.this.k.dispose();
                }
            }
        }

        /* loaded from: classes12.dex */
        public final class c implements Runnable {
            public final T h;

            public c(T t) {
                this.h = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.h.onNext((T) this.h);
            }
        }

        public a(Observer<? super T> observer, long j, TimeUnit timeUnit, Scheduler.Worker worker, boolean z) {
            this.h = observer;
            this.i = j;
            this.j = timeUnit;
            this.k = worker;
            this.l = z;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.m.dispose();
            this.k.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.k.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.k.schedule(new RunnableC0847a(), this.i, this.j);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.k.schedule(new b(th), this.l ? this.i : 0L, this.j);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            this.k.schedule(new c(t), this.i, this.j);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.m, disposable)) {
                this.m = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableDelay(ObservableSource<T> observableSource, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        super(observableSource);
        this.h = j;
        this.i = timeUnit;
        this.j = scheduler;
        this.k = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new a(this.k ? observer : new SerializedObserver(observer), this.h, this.i, this.j.createWorker(), this.k));
    }
}
