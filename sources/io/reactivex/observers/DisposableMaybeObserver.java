package io.reactivex.observers;

import io.reactivex.MaybeObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public abstract class DisposableMaybeObserver<T> implements MaybeObserver<T>, Disposable {
    public final AtomicReference<Disposable> h = new AtomicReference<>();

    @Override // io.reactivex.disposables.Disposable
    public final void dispose() {
        DisposableHelper.dispose(this.h);
    }

    @Override // io.reactivex.disposables.Disposable
    public final boolean isDisposed() {
        return this.h.get() == DisposableHelper.DISPOSED;
    }

    public void onStart() {
    }

    @Override // io.reactivex.MaybeObserver
    public final void onSubscribe(@NonNull Disposable disposable) {
        if (EndConsumerHelper.setOnce(this.h, disposable, getClass())) {
            onStart();
        }
    }
}