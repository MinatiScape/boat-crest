package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EndConsumerHelper;
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

    @Override // io.reactivex.Observer
    public final void onSubscribe(@NonNull Disposable disposable) {
        if (EndConsumerHelper.validate(this.h, disposable, getClass())) {
            this.h = disposable;
            onStart();
        }
    }
}
