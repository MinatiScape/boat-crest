package io.reactivex.rxjava3.disposables;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
/* loaded from: classes12.dex */
public final class b extends e<AutoCloseable> {
    private static final long serialVersionUID = -6646144244598696847L;

    public b(AutoCloseable autoCloseable) {
        super(autoCloseable);
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        return "AutoCloseableDisposable(disposed=" + isDisposed() + ", " + get() + ")";
    }

    @Override // io.reactivex.rxjava3.disposables.e
    public void onDisposed(@NonNull AutoCloseable autoCloseable) {
        try {
            autoCloseable.close();
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }
}
