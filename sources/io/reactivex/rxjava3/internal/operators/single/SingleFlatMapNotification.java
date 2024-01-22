package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class SingleFlatMapNotification<T, R> extends Single<R> {
    public final SingleSource<T> h;
    public final Function<? super T, ? extends SingleSource<? extends R>> i;
    public final Function<? super Throwable, ? extends SingleSource<? extends R>> j;

    /* loaded from: classes12.dex */
    public static final class a<T, R> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = 4375739915521278546L;
        public final SingleObserver<? super R> downstream;
        public final Function<? super Throwable, ? extends SingleSource<? extends R>> onErrorMapper;
        public final Function<? super T, ? extends SingleSource<? extends R>> onSuccessMapper;
        public Disposable upstream;

        /* renamed from: io.reactivex.rxjava3.internal.operators.single.SingleFlatMapNotification$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class C0865a implements SingleObserver<R> {
            public C0865a() {
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                a.this.downstream.onError(th);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(a.this, disposable);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(R r) {
                a.this.downstream.onSuccess(r);
            }
        }

        public a(SingleObserver<? super R> singleObserver, Function<? super T, ? extends SingleSource<? extends R>> function, Function<? super Throwable, ? extends SingleSource<? extends R>> function2) {
            this.downstream = singleObserver;
            this.onSuccessMapper = function;
            this.onErrorMapper = function2;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
            this.upstream.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            try {
                SingleSource<? extends R> apply = this.onErrorMapper.apply(th);
                Objects.requireNonNull(apply, "The onErrorMapper returned a null SingleSource");
                SingleSource<? extends R> singleSource = apply;
                if (isDisposed()) {
                    return;
                }
                singleSource.subscribe(new C0865a());
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            try {
                SingleSource<? extends R> apply = this.onSuccessMapper.apply(t);
                Objects.requireNonNull(apply, "The onSuccessMapper returned a null SingleSource");
                SingleSource<? extends R> singleSource = apply;
                if (isDisposed()) {
                    return;
                }
                singleSource.subscribe(new C0865a());
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }
    }

    public SingleFlatMapNotification(SingleSource<T> singleSource, Function<? super T, ? extends SingleSource<? extends R>> function, Function<? super Throwable, ? extends SingleSource<? extends R>> function2) {
        this.h = singleSource;
        this.i = function;
        this.j = function2;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.i, this.j));
    }
}
