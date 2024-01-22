package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class ObservableDelaySubscriptionOther<T, U> extends Observable<T> {
    public final ObservableSource<? extends T> h;
    public final ObservableSource<U> i;

    /* loaded from: classes12.dex */
    public final class a implements Observer<U> {
        public final SequentialDisposable h;
        public final Observer<? super T> i;
        public boolean j;

        /* renamed from: io.reactivex.rxjava3.internal.operators.observable.ObservableDelaySubscriptionOther$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class C0848a implements Observer<T> {
            public C0848a() {
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onComplete() {
                a.this.i.onComplete();
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onError(Throwable th) {
                a.this.i.onError(th);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onNext(T t) {
                a.this.i.onNext(t);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onSubscribe(Disposable disposable) {
                a.this.h.update(disposable);
            }
        }

        public a(SequentialDisposable sequentialDisposable, Observer<? super T> observer) {
            this.h = sequentialDisposable;
            this.i = observer;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.j) {
                return;
            }
            this.j = true;
            ObservableDelaySubscriptionOther.this.h.subscribe(new C0848a());
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (this.j) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.j = true;
            this.i.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(U u) {
            onComplete();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            this.h.update(disposable);
        }
    }

    public ObservableDelaySubscriptionOther(ObservableSource<? extends T> observableSource, ObservableSource<U> observableSource2) {
        this.h = observableSource;
        this.i = observableSource2;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        observer.onSubscribe(sequentialDisposable);
        this.i.subscribe(new a(sequentialDisposable, observer));
    }
}
