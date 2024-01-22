package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.HalfSerializer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableTakeUntil<T, U> extends io.reactivex.internal.operators.observable.a<T, T> {
    public final ObservableSource<? extends U> h;

    /* loaded from: classes12.dex */
    public static final class a<T, U> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 1418547743690811973L;
        public final Observer<? super T> downstream;
        public final AtomicReference<Disposable> upstream = new AtomicReference<>();
        public final a<T, U>.C0792a otherObserver = new C0792a();
        public final AtomicThrowable error = new AtomicThrowable();

        /* renamed from: io.reactivex.internal.operators.observable.ObservableTakeUntil$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class C0792a extends AtomicReference<Disposable> implements Observer<U> {
            private static final long serialVersionUID = -8693423678067375039L;

            public C0792a() {
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                a.this.otherComplete();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                a.this.otherError(th);
            }

            @Override // io.reactivex.Observer
            public void onNext(U u) {
                DisposableHelper.dispose(this);
                a.this.otherComplete();
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }
        }

        public a(Observer<? super T> observer) {
            this.downstream = observer;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this.otherObserver);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            DisposableHelper.dispose(this.otherObserver);
            HalfSerializer.onComplete(this.downstream, this, this.error);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.otherObserver);
            HalfSerializer.onError(this.downstream, th, this, this.error);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            HalfSerializer.onNext(this.downstream, t, this, this.error);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this.upstream, disposable);
        }

        public void otherComplete() {
            DisposableHelper.dispose(this.upstream);
            HalfSerializer.onComplete(this.downstream, this, this.error);
        }

        public void otherError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            HalfSerializer.onError(this.downstream, th, this, this.error);
        }
    }

    public ObservableTakeUntil(ObservableSource<T> observableSource, ObservableSource<? extends U> observableSource2) {
        super(observableSource);
        this.h = observableSource2;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        a aVar = new a(observer);
        observer.onSubscribe(aVar);
        this.h.subscribe(aVar.otherObserver);
        this.source.subscribe(aVar);
    }
}
