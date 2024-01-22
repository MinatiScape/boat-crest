package io.reactivex.rxjava3.internal.jdk8;

import java.util.NoSuchElementException;
/* loaded from: classes12.dex */
public final class ObservableLastStageObserver<T> extends b<T> {
    public final boolean j;
    public final T k;

    public ObservableLastStageObserver(boolean z, T t) {
        this.j = z;
        this.k = t;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        if (isDone()) {
            return;
        }
        T t = this.i;
        clear();
        if (t != null) {
            complete(t);
        } else if (this.j) {
            complete(this.k);
        } else {
            completeExceptionally(new NoSuchElementException());
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        this.i = t;
    }
}
