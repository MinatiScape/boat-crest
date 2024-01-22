package io.reactivex.rxjava3.internal.observers;
/* loaded from: classes12.dex */
public final class BlockingFirstObserver<T> extends BlockingBaseObserver<T> {
    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable th) {
        if (this.h == null) {
            this.i = th;
        }
        countDown();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        if (this.h == null) {
            this.h = t;
            this.j.dispose();
            countDown();
        }
    }
}
