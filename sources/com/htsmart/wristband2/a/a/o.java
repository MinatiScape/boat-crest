package com.htsmart.wristband2.a.a;

import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public abstract class o<T, U> implements Observer<T>, Cancellable {
    public final ObservableEmitter<U> b;
    public final m c;
    public final AtomicBoolean h = new AtomicBoolean(false);
    public Disposable i;

    public o(ObservableEmitter<U> observableEmitter, m mVar) {
        this.b = observableEmitter;
        this.c = mVar;
        observableEmitter.setCancellable(this);
    }

    public final void a() {
        Disposable disposable = this.i;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.i.dispose();
    }

    public void a(Throwable th) throws Exception {
        this.h.set(true);
        onError(th);
    }

    @Override // io.reactivex.functions.Cancellable
    public synchronized void cancel() throws Exception {
        this.h.set(true);
        a();
        this.c.c();
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        a();
        this.c.c();
        this.b.onComplete();
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        a();
        this.c.c();
        this.b.tryOnError(th);
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        this.i = disposable;
    }
}
