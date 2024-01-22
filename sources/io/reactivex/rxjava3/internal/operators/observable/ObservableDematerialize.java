package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class ObservableDematerialize<T, R> extends io.reactivex.rxjava3.internal.operators.observable.a<T, R> {
    public final Function<? super T, ? extends Notification<R>> h;

    /* loaded from: classes12.dex */
    public static final class a<T, R> implements Observer<T>, Disposable {
        public final Observer<? super R> h;
        public final Function<? super T, ? extends Notification<R>> i;
        public boolean j;
        public Disposable k;

        public a(Observer<? super R> observer, Function<? super T, ? extends Notification<R>> function) {
            this.h = observer;
            this.i = function;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.k.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.k.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.j) {
                return;
            }
            this.j = true;
            this.h.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (this.j) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.j = true;
            this.h.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.j) {
                if (t instanceof Notification) {
                    Notification notification = (Notification) t;
                    if (notification.isOnError()) {
                        RxJavaPlugins.onError(notification.getError());
                        return;
                    }
                    return;
                }
                return;
            }
            try {
                Notification<R> apply = this.i.apply(t);
                Objects.requireNonNull(apply, "The selector returned a null Notification");
                Notification<R> notification2 = apply;
                if (notification2.isOnError()) {
                    this.k.dispose();
                    onError(notification2.getError());
                } else if (notification2.isOnComplete()) {
                    this.k.dispose();
                    onComplete();
                } else {
                    this.h.onNext(notification2.getValue());
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.k.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.k, disposable)) {
                this.k = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableDematerialize(ObservableSource<T> observableSource, Function<? super T, ? extends Notification<R>> function) {
        super(observableSource);
        this.h = function;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super R> observer) {
        this.source.subscribe(new a(observer, this.h));
    }
}
