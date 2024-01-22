package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class MaybeEqualSingle<T> extends Single<Boolean> {
    public final MaybeSource<? extends T> h;
    public final MaybeSource<? extends T> i;
    public final BiPredicate<? super T, ? super T> j;

    /* loaded from: classes12.dex */
    public static final class a<T> extends AtomicInteger implements Disposable {
        public final SingleObserver<? super Boolean> downstream;
        public final BiPredicate<? super T, ? super T> isEqual;
        public final b<T> observer1;
        public final b<T> observer2;

        public a(SingleObserver<? super Boolean> singleObserver, BiPredicate<? super T, ? super T> biPredicate) {
            super(2);
            this.downstream = singleObserver;
            this.isEqual = biPredicate;
            this.observer1 = new b<>(this);
            this.observer2 = new b<>(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.observer1.dispose();
            this.observer2.dispose();
        }

        public void done() {
            if (decrementAndGet() == 0) {
                Object obj = this.observer1.value;
                Object obj2 = this.observer2.value;
                if (obj != null && obj2 != null) {
                    try {
                        this.downstream.onSuccess(Boolean.valueOf(this.isEqual.test(obj, obj2)));
                        return;
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.downstream.onError(th);
                        return;
                    }
                }
                this.downstream.onSuccess(Boolean.valueOf(obj == null && obj2 == null));
            }
        }

        public void error(b<T> bVar, Throwable th) {
            if (getAndSet(0) > 0) {
                b<T> bVar2 = this.observer1;
                if (bVar == bVar2) {
                    this.observer2.dispose();
                } else {
                    bVar2.dispose();
                }
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.observer1.get());
        }

        public void subscribe(MaybeSource<? extends T> maybeSource, MaybeSource<? extends T> maybeSource2) {
            maybeSource.subscribe(this.observer1);
            maybeSource2.subscribe(this.observer2);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
        private static final long serialVersionUID = -3031974433025990931L;
        public final a<T> parent;
        public Object value;

        public b(a<T> aVar) {
            this.parent = aVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            this.parent.done();
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(Throwable th) {
            this.parent.error(this, th);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(T t) {
            this.value = t;
            this.parent.done();
        }
    }

    public MaybeEqualSingle(MaybeSource<? extends T> maybeSource, MaybeSource<? extends T> maybeSource2, BiPredicate<? super T, ? super T> biPredicate) {
        this.h = maybeSource;
        this.i = maybeSource2;
        this.j = biPredicate;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        a aVar = new a(singleObserver, this.j);
        singleObserver.onSubscribe(aVar);
        aVar.subscribe(this.h, this.i);
    }
}
