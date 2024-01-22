package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class MaybeFlatMapBiSelector<T, U, R> extends io.reactivex.internal.operators.maybe.a<T, R> {
    public final Function<? super T, ? extends MaybeSource<? extends U>> h;
    public final BiFunction<? super T, ? super U, ? extends R> i;

    /* loaded from: classes12.dex */
    public static final class a<T, U, R> implements MaybeObserver<T>, Disposable {
        public final Function<? super T, ? extends MaybeSource<? extends U>> h;
        public final C0757a<T, U, R> i;

        /* renamed from: io.reactivex.internal.operators.maybe.MaybeFlatMapBiSelector$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0757a<T, U, R> extends AtomicReference<Disposable> implements MaybeObserver<U> {
            private static final long serialVersionUID = -2897979525538174559L;
            public final MaybeObserver<? super R> downstream;
            public final BiFunction<? super T, ? super U, ? extends R> resultSelector;
            public T value;

            public C0757a(MaybeObserver<? super R> maybeObserver, BiFunction<? super T, ? super U, ? extends R> biFunction) {
                this.downstream = maybeObserver;
                this.resultSelector = biFunction;
            }

            @Override // io.reactivex.MaybeObserver
            public void onComplete() {
                this.downstream.onComplete();
            }

            @Override // io.reactivex.MaybeObserver
            public void onError(Throwable th) {
                this.downstream.onError(th);
            }

            @Override // io.reactivex.MaybeObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            @Override // io.reactivex.MaybeObserver
            public void onSuccess(U u) {
                T t = this.value;
                this.value = null;
                try {
                    this.downstream.onSuccess(ObjectHelper.requireNonNull(this.resultSelector.apply(t, u), "The resultSelector returned a null value"));
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.downstream.onError(th);
                }
            }
        }

        public a(MaybeObserver<? super R> maybeObserver, Function<? super T, ? extends MaybeSource<? extends U>> function, BiFunction<? super T, ? super U, ? extends R> biFunction) {
            this.i = new C0757a<>(maybeObserver, biFunction);
            this.h = function;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.i);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.i.get());
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            this.i.downstream.onComplete();
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(Throwable th) {
            this.i.downstream.onError(th);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this.i, disposable)) {
                this.i.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(T t) {
            try {
                MaybeSource maybeSource = (MaybeSource) ObjectHelper.requireNonNull(this.h.apply(t), "The mapper returned a null MaybeSource");
                if (DisposableHelper.replace(this.i, null)) {
                    C0757a<T, U, R> c0757a = this.i;
                    c0757a.value = t;
                    maybeSource.subscribe(c0757a);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.i.downstream.onError(th);
            }
        }
    }

    public MaybeFlatMapBiSelector(MaybeSource<T> maybeSource, Function<? super T, ? extends MaybeSource<? extends U>> function, BiFunction<? super T, ? super U, ? extends R> biFunction) {
        super(maybeSource);
        this.h = function;
        this.i = biFunction;
    }

    @Override // io.reactivex.Maybe
    public void subscribeActual(MaybeObserver<? super R> maybeObserver) {
        this.source.subscribe(new a(maybeObserver, this.h, this.i));
    }
}
