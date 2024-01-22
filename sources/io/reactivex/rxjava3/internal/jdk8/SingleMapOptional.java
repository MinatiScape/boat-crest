package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.Optional;
/* loaded from: classes12.dex */
public final class SingleMapOptional<T, R> extends Maybe<R> {
    public final Single<T> h;
    public final Function<? super T, Optional<? extends R>> i;

    /* loaded from: classes12.dex */
    public static final class a<T, R> implements SingleObserver<T>, Disposable {
        public final MaybeObserver<? super R> h;
        public final Function<? super T, Optional<? extends R>> i;
        public Disposable j;

        public a(MaybeObserver<? super R> maybeObserver, Function<? super T, Optional<? extends R>> function) {
            this.h = maybeObserver;
            this.i = function;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            Disposable disposable = this.j;
            this.j = DisposableHelper.DISPOSED;
            disposable.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.j.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                this.h.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            try {
                Optional<? extends R> apply = this.i.apply(t);
                Objects.requireNonNull(apply, "The mapper returned a null item");
                Optional<? extends R> optional = apply;
                if (optional.isPresent()) {
                    this.h.onSuccess((R) optional.get());
                } else {
                    this.h.onComplete();
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.h.onError(th);
            }
        }
    }

    public SingleMapOptional(Single<T> single, Function<? super T, Optional<? extends R>> function) {
        this.h = single;
        this.i = function;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(MaybeObserver<? super R> maybeObserver) {
        this.h.subscribe(new a(maybeObserver, this.i));
    }
}
