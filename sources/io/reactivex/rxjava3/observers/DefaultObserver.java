package io.reactivex.rxjava3.observers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.EndConsumerHelper;
/* loaded from: classes12.dex */
public abstract class DefaultObserver<T> implements Observer<T> {
    public Disposable h;

    public final void cancel() {
        Disposable disposable = this.h;
        this.h = DisposableHelper.DISPOSED;
        disposable.dispose();
    }

    public void onStart() {
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(@NonNull Disposable disposable) {
        if (EndConsumerHelper.validate(this.h, disposable, getClass())) {
            this.h = disposable;
            onStart();
        }
    }
}
