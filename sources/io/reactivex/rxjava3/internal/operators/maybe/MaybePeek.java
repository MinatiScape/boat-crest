package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class MaybePeek<T> extends io.reactivex.rxjava3.internal.operators.maybe.a<T, T> {
    public final Consumer<? super Disposable> h;
    public final Consumer<? super T> i;
    public final Consumer<? super Throwable> j;
    public final Action k;
    public final Action l;
    public final Action m;

    /* loaded from: classes12.dex */
    public static final class a<T> implements MaybeObserver<T>, Disposable {
        public final MaybeObserver<? super T> h;
        public final MaybePeek<T> i;
        public Disposable j;

        public a(MaybeObserver<? super T> maybeObserver, MaybePeek<T> maybePeek) {
            this.h = maybeObserver;
            this.i = maybePeek;
        }

        public void a() {
            try {
                this.i.l.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }

        public void b(Throwable th) {
            try {
                this.i.j.accept(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.j = DisposableHelper.DISPOSED;
            this.h.onError(th);
            a();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            try {
                this.i.m.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
            this.j.dispose();
            this.j = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.j.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onComplete() {
            Disposable disposable = this.j;
            DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
            if (disposable == disposableHelper) {
                return;
            }
            try {
                this.i.k.run();
                this.j = disposableHelper;
                this.h.onComplete();
                a();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                b(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            if (this.j == DisposableHelper.DISPOSED) {
                RxJavaPlugins.onError(th);
            } else {
                b(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                try {
                    this.i.h.accept(disposable);
                    this.j = disposable;
                    this.h.onSubscribe(this);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    disposable.dispose();
                    this.j = DisposableHelper.DISPOSED;
                    EmptyDisposable.error(th, this.h);
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            Disposable disposable = this.j;
            DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
            if (disposable == disposableHelper) {
                return;
            }
            try {
                this.i.i.accept(t);
                this.j = disposableHelper;
                this.h.onSuccess(t);
                a();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                b(th);
            }
        }
    }

    public MaybePeek(MaybeSource<T> maybeSource, Consumer<? super Disposable> consumer, Consumer<? super T> consumer2, Consumer<? super Throwable> consumer3, Action action, Action action2, Action action3) {
        super(maybeSource);
        this.h = consumer;
        this.i = consumer2;
        this.j = consumer3;
        this.k = action;
        this.l = action2;
        this.m = action3;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new a(maybeObserver, this));
    }
}
