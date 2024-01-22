package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public final class b<T> extends Observable<T> {
    public final Subject<T> h;
    public final AtomicBoolean i = new AtomicBoolean();

    public b(Subject<T> subject) {
        this.h = subject;
    }

    public boolean d() {
        return !this.i.get() && this.i.compareAndSet(false, true);
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.h.subscribe(observer);
        this.i.set(true);
    }
}
