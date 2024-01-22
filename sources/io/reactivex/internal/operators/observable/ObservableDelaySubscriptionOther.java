package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class ObservableDelaySubscriptionOther<T, U> extends Observable<T> {
    public final ObservableSource<? extends T> h;
    public final ObservableSource<U> i;

    /* loaded from: classes12.dex */
    public final class a implements Observer<U> {
        public final SequentialDisposable h;
        public final Observer<? super T> i;
        public boolean j;

        /* renamed from: io.reactivex.internal.operators.observable.ObservableDelaySubscriptionOther$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class C0782a implements Observer<T> {
            public C0782a() {
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                a.this.i.onComplete();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                a.this.i.onError(th);
            }

            @Override // io.reactivex.Observer
            public void onNext(T t) {
                a.this.i.onNext(t);
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                a.this.h.update(disposable);
            }
        }

        public a(SequentialDisposable sequentialDisposable, Observer<? super T> observer) {
            this.h = sequentialDisposable;
            this.i = observer;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.j) {
                return;
            }
            this.j = true;
            ObservableDelaySubscriptionOther.this.h.subscribe(new C0782a());
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.j) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.j = true;
            this.i.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(U u) {
            onComplete();
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.h.update(disposable);
        }
    }

    public ObservableDelaySubscriptionOther(ObservableSource<? extends T> observableSource, ObservableSource<U> observableSource2) {
        this.h = observableSource;
        this.i = observableSource2;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        observer.onSubscribe(sequentialDisposable);
        this.i.subscribe(new a(sequentialDisposable, observer));
    }
}
