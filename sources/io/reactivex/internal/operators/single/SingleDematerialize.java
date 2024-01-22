package io.reactivex.internal.operators.single;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Notification;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.Experimental;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
@Experimental
/* loaded from: classes12.dex */
public final class SingleDematerialize<T, R> extends Maybe<R> {
    public final Single<T> h;
    public final Function<? super T, Notification<R>> i;

    /* loaded from: classes12.dex */
    public static final class a<T, R> implements SingleObserver<T>, Disposable {
        public final MaybeObserver<? super R> h;
        public final Function<? super T, Notification<R>> i;
        public Disposable j;

        public a(MaybeObserver<? super R> maybeObserver, Function<? super T, Notification<R>> function) {
            this.h = maybeObserver;
            this.i = function;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.j.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.j.isDisposed();
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                this.h.onSubscribe(this);
            }
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            try {
                Notification notification = (Notification) ObjectHelper.requireNonNull(this.i.apply(t), "The selector returned a null Notification");
                if (notification.isOnNext()) {
                    this.h.onSuccess((Object) notification.getValue());
                } else if (notification.isOnComplete()) {
                    this.h.onComplete();
                } else {
                    this.h.onError(notification.getError());
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.h.onError(th);
            }
        }
    }

    public SingleDematerialize(Single<T> single, Function<? super T, Notification<R>> function) {
        this.h = single;
        this.i = function;
    }

    @Override // io.reactivex.Maybe
    public void subscribeActual(MaybeObserver<? super R> maybeObserver) {
        this.h.subscribe(new a(maybeObserver, this.i));
    }
}
