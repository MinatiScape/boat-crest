package io.reactivex.rxjava3.disposables;

import io.reactivex.rxjava3.annotations.NonNull;
/* loaded from: classes12.dex */
public final class f extends e<Runnable> {
    private static final long serialVersionUID = -8219729196779211169L;

    public f(Runnable runnable) {
        super(runnable);
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        return "RunnableDisposable(disposed=" + isDisposed() + ", " + get() + ")";
    }

    @Override // io.reactivex.rxjava3.disposables.e
    public void onDisposed(@NonNull Runnable runnable) {
        runnable.run();
    }
}
