package io.reactivex.internal.operators.single;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class SingleDelay<T> extends Single<T> {
    public final SingleSource<? extends T> h;
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;
    public final boolean l;

    /* loaded from: classes12.dex */
    public final class a implements SingleObserver<T> {
        public final SequentialDisposable h;
        public final SingleObserver<? super T> i;

        /* renamed from: io.reactivex.internal.operators.single.SingleDelay$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class RunnableC0796a implements Runnable {
            public final Throwable h;

            public RunnableC0796a(Throwable th) {
                this.h = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.i.onError(this.h);
            }
        }

        /* loaded from: classes12.dex */
        public final class b implements Runnable {
            public final T h;

            public b(T t) {
                this.h = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.i.onSuccess((T) this.h);
            }
        }

        public a(SequentialDisposable sequentialDisposable, SingleObserver<? super T> singleObserver) {
            this.h = sequentialDisposable;
            this.i = singleObserver;
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            SequentialDisposable sequentialDisposable = this.h;
            Scheduler scheduler = SingleDelay.this.k;
            RunnableC0796a runnableC0796a = new RunnableC0796a(th);
            SingleDelay singleDelay = SingleDelay.this;
            sequentialDisposable.replace(scheduler.scheduleDirect(runnableC0796a, singleDelay.l ? singleDelay.i : 0L, singleDelay.j));
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            this.h.replace(disposable);
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            SequentialDisposable sequentialDisposable = this.h;
            Scheduler scheduler = SingleDelay.this.k;
            b bVar = new b(t);
            SingleDelay singleDelay = SingleDelay.this;
            sequentialDisposable.replace(scheduler.scheduleDirect(bVar, singleDelay.i, singleDelay.j));
        }
    }

    public SingleDelay(SingleSource<? extends T> singleSource, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        this.h = singleSource;
        this.i = j;
        this.j = timeUnit;
        this.k = scheduler;
        this.l = z;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        singleObserver.onSubscribe(sequentialDisposable);
        this.h.subscribe(new a(sequentialDisposable, singleObserver));
    }
}
