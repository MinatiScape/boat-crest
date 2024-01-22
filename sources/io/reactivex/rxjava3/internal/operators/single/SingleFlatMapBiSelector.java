package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class SingleFlatMapBiSelector<T, U, R> extends Single<R> {
    public final SingleSource<T> h;
    public final Function<? super T, ? extends SingleSource<? extends U>> i;
    public final BiFunction<? super T, ? super U, ? extends R> j;

    /* loaded from: classes12.dex */
    public static final class a<T, U, R> implements SingleObserver<T>, Disposable {
        public final Function<? super T, ? extends SingleSource<? extends U>> h;
        public final C0864a<T, U, R> i;

        /* renamed from: io.reactivex.rxjava3.internal.operators.single.SingleFlatMapBiSelector$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0864a<T, U, R> extends AtomicReference<Disposable> implements SingleObserver<U> {
            private static final long serialVersionUID = -2897979525538174559L;
            public final SingleObserver<? super R> downstream;
            public final BiFunction<? super T, ? super U, ? extends R> resultSelector;
            public T value;

            public C0864a(SingleObserver<? super R> singleObserver, BiFunction<? super T, ? super U, ? extends R> biFunction) {
                this.downstream = singleObserver;
                this.resultSelector = biFunction;
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                this.downstream.onError(th);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(U u) {
                T t = this.value;
                this.value = null;
                try {
                    R apply = this.resultSelector.apply(t, u);
                    Objects.requireNonNull(apply, "The resultSelector returned a null value");
                    this.downstream.onSuccess(apply);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.downstream.onError(th);
                }
            }
        }

        public a(SingleObserver<? super R> singleObserver, Function<? super T, ? extends SingleSource<? extends U>> function, BiFunction<? super T, ? super U, ? extends R> biFunction) {
            this.i = new C0864a<>(singleObserver, biFunction);
            this.h = function;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.i);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.i.get());
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.i.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this.i, disposable)) {
                this.i.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            try {
                SingleSource<? extends U> apply = this.h.apply(t);
                Objects.requireNonNull(apply, "The mapper returned a null MaybeSource");
                SingleSource<? extends U> singleSource = apply;
                if (DisposableHelper.replace(this.i, null)) {
                    C0864a<T, U, R> c0864a = this.i;
                    c0864a.value = t;
                    singleSource.subscribe(c0864a);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.i.downstream.onError(th);
            }
        }
    }

    public SingleFlatMapBiSelector(SingleSource<T> singleSource, Function<? super T, ? extends SingleSource<? extends U>> function, BiFunction<? super T, ? super U, ? extends R> biFunction) {
        this.h = singleSource;
        this.i = function;
        this.j = biFunction;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.i, this.j));
    }
}
