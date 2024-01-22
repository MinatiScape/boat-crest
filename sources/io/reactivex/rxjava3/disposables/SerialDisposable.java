package io.reactivex.rxjava3.disposables;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class SerialDisposable implements Disposable {
    public final AtomicReference<Disposable> h;

    public SerialDisposable() {
        this.h = new AtomicReference<>();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this.h);
    }

    @Nullable
    public Disposable get() {
        Disposable disposable = this.h.get();
        return disposable == DisposableHelper.DISPOSED ? Disposable.disposed() : disposable;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(this.h.get());
    }

    public boolean replace(@Nullable Disposable disposable) {
        return DisposableHelper.replace(this.h, disposable);
    }

    public boolean set(@Nullable Disposable disposable) {
        return DisposableHelper.set(this.h, disposable);
    }

    public SerialDisposable(@Nullable Disposable disposable) {
        this.h = new AtomicReference<>(disposable);
    }
}
