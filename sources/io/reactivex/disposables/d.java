package io.reactivex.disposables;

import io.reactivex.annotations.NonNull;
/* loaded from: classes12.dex */
public final class d extends c<Runnable> {
    private static final long serialVersionUID = -8219729196779211169L;

    public d(Runnable runnable) {
        super(runnable);
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        return "RunnableDisposable(disposed=" + isDisposed() + ", " + get() + ")";
    }

    @Override // io.reactivex.disposables.c
    public void onDisposed(@NonNull Runnable runnable) {
        runnable.run();
    }
}
