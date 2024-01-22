package io.reactivex.internal.operators.mixed;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Notification;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.Experimental;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
@Experimental
/* loaded from: classes12.dex */
public final class MaterializeSingleObserver<T> implements SingleObserver<T>, MaybeObserver<T>, CompletableObserver, Disposable {
    public final SingleObserver<? super Notification<T>> h;
    public Disposable i;

    public MaterializeSingleObserver(SingleObserver<? super Notification<T>> singleObserver) {
        this.h = singleObserver;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        this.i.dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.i.isDisposed();
    }

    @Override // io.reactivex.MaybeObserver
    public void onComplete() {
        this.h.onSuccess(Notification.createOnComplete());
    }

    @Override // io.reactivex.SingleObserver
    public void onError(Throwable th) {
        this.h.onSuccess(Notification.createOnError(th));
    }

    @Override // io.reactivex.SingleObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.i, disposable)) {
            this.i = disposable;
            this.h.onSubscribe(this);
        }
    }

    @Override // io.reactivex.SingleObserver
    public void onSuccess(T t) {
        this.h.onSuccess(Notification.createOnNext(t));
    }
}
