package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public final class SingleEquals<T> extends Single<Boolean> {
    public final SingleSource<? extends T> h;
    public final SingleSource<? extends T> i;

    /* loaded from: classes12.dex */
    public static class a<T> implements SingleObserver<T> {
        public final int h;
        public final CompositeDisposable i;
        public final Object[] j;
        public final SingleObserver<? super Boolean> k;
        public final AtomicInteger l;

        public a(int i, CompositeDisposable compositeDisposable, Object[] objArr, SingleObserver<? super Boolean> singleObserver, AtomicInteger atomicInteger) {
            this.h = i;
            this.i = compositeDisposable;
            this.j = objArr;
            this.k = singleObserver;
            this.l = atomicInteger;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            int andSet = this.l.getAndSet(-1);
            if (andSet != 0 && andSet != 1) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.i.dispose();
            this.k.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.i.add(disposable);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            this.j[this.h] = t;
            if (this.l.incrementAndGet() == 2) {
                SingleObserver<? super Boolean> singleObserver = this.k;
                Object[] objArr = this.j;
                singleObserver.onSuccess(Boolean.valueOf(Objects.equals(objArr[0], objArr[1])));
            }
        }
    }

    public SingleEquals(SingleSource<? extends T> singleSource, SingleSource<? extends T> singleSource2) {
        this.h = singleSource;
        this.i = singleSource2;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        AtomicInteger atomicInteger = new AtomicInteger();
        Object[] objArr = {null, null};
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        singleObserver.onSubscribe(compositeDisposable);
        this.h.subscribe(new a(0, compositeDisposable, objArr, singleObserver, atomicInteger));
        this.i.subscribe(new a(1, compositeDisposable, objArr, singleObserver, atomicInteger));
    }
}
