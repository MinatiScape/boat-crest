package io.reactivex.rxjava3.observers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.ListCompositeDisposable;
import io.reactivex.rxjava3.internal.util.EndConsumerHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public abstract class ResourceObserver<T> implements Observer<T>, Disposable {
    public final AtomicReference<Disposable> h = new AtomicReference<>();
    public final ListCompositeDisposable i = new ListCompositeDisposable();

    public final void add(@NonNull Disposable disposable) {
        Objects.requireNonNull(disposable, "resource is null");
        this.i.add(disposable);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        if (DisposableHelper.dispose(this.h)) {
            this.i.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(this.h.get());
    }

    public void onStart() {
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (EndConsumerHelper.setOnce(this.h, disposable, getClass())) {
            onStart();
        }
    }
}
