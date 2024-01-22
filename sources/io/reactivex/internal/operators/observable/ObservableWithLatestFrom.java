package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.observers.SerializedObserver;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableWithLatestFrom<T, U, R> extends io.reactivex.internal.operators.observable.a<T, R> {
    public final BiFunction<? super T, ? super U, ? extends R> h;
    public final ObservableSource<? extends U> i;

    /* loaded from: classes12.dex */
    public static final class a<T, U, R> extends AtomicReference<U> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -312246233408980075L;
        public final BiFunction<? super T, ? super U, ? extends R> combiner;
        public final Observer<? super R> downstream;
        public final AtomicReference<Disposable> upstream = new AtomicReference<>();
        public final AtomicReference<Disposable> other = new AtomicReference<>();

        public a(Observer<? super R> observer, BiFunction<? super T, ? super U, ? extends R> biFunction) {
            this.downstream = observer;
            this.combiner = biFunction;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this.other);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            DisposableHelper.dispose(this.other);
            this.downstream.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            this.downstream.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            U u = get();
            if (u != null) {
                try {
                    this.downstream.onNext(ObjectHelper.requireNonNull(this.combiner.apply(t, u), "The combiner returned a null value"));
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    dispose();
                    this.downstream.onError(th);
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this.upstream, disposable);
        }

        public void otherError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            this.downstream.onError(th);
        }

        public boolean setOther(Disposable disposable) {
            return DisposableHelper.setOnce(this.other, disposable);
        }
    }

    /* loaded from: classes12.dex */
    public final class b implements Observer<U> {
        public final a<T, U, R> h;

        public b(ObservableWithLatestFrom observableWithLatestFrom, a<T, U, R> aVar) {
            this.h = aVar;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.h.otherError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(U u) {
            this.h.lazySet(u);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.h.setOther(disposable);
        }
    }

    public ObservableWithLatestFrom(ObservableSource<T> observableSource, BiFunction<? super T, ? super U, ? extends R> biFunction, ObservableSource<? extends U> observableSource2) {
        super(observableSource);
        this.h = biFunction;
        this.i = observableSource2;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super R> observer) {
        SerializedObserver serializedObserver = new SerializedObserver(observer);
        a aVar = new a(serializedObserver, this.h);
        serializedObserver.onSubscribe(aVar);
        this.i.subscribe(new b(this, aVar));
        this.source.subscribe(aVar);
    }
}