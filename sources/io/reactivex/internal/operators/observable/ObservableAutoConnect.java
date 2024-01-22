package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public final class ObservableAutoConnect<T> extends Observable<T> {
    public final ConnectableObservable<? extends T> h;
    public final int i;
    public final Consumer<? super Disposable> j;
    public final AtomicInteger k = new AtomicInteger();

    public ObservableAutoConnect(ConnectableObservable<? extends T> connectableObservable, int i, Consumer<? super Disposable> consumer) {
        this.h = connectableObservable;
        this.i = i;
        this.j = consumer;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.h.subscribe((Observer<? super Object>) observer);
        if (this.k.incrementAndGet() == this.i) {
            this.h.connect(this.j);
        }
    }
}
