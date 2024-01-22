package io.reactivex.rxjava3.internal.observers;
/* loaded from: classes12.dex */
public final class BlockingLastObserver<T> extends BlockingBaseObserver<T> {
    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable th) {
        this.h = null;
        this.i = th;
        countDown();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        this.h = t;
    }
}
