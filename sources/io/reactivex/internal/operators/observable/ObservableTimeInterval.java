package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.schedulers.Timed;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class ObservableTimeInterval<T> extends io.reactivex.internal.operators.observable.a<T, Timed<T>> {
    public final Scheduler h;
    public final TimeUnit i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final Observer<? super Timed<T>> h;
        public final TimeUnit i;
        public final Scheduler j;
        public long k;
        public Disposable l;

        public a(Observer<? super Timed<T>> observer, TimeUnit timeUnit, Scheduler scheduler) {
            this.h = observer;
            this.j = scheduler;
            this.i = timeUnit;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.l.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.l.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.h.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            long now = this.j.now(this.i);
            long j = this.k;
            this.k = now;
            this.h.onNext(new Timed(t, now - j, this.i));
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.l, disposable)) {
                this.l = disposable;
                this.k = this.j.now(this.i);
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableTimeInterval(ObservableSource<T> observableSource, TimeUnit timeUnit, Scheduler scheduler) {
        super(observableSource);
        this.h = scheduler;
        this.i = timeUnit;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Timed<T>> observer) {
        this.source.subscribe(new a(observer, this.i, this.h));
    }
}
