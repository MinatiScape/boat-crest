package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class CompletablePeek extends Completable {
    public final CompletableSource h;
    public final Consumer<? super Disposable> i;
    public final Consumer<? super Throwable> j;
    public final Action k;
    public final Action l;
    public final Action m;
    public final Action n;

    /* loaded from: classes12.dex */
    public final class a implements CompletableObserver, Disposable {
        public final CompletableObserver h;
        public Disposable i;

        public a(CompletableObserver completableObserver) {
            this.h = completableObserver;
        }

        public void a() {
            try {
                CompletablePeek.this.m.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            try {
                CompletablePeek.this.n.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
            this.i.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.i.isDisposed();
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            if (this.i == DisposableHelper.DISPOSED) {
                return;
            }
            try {
                CompletablePeek.this.k.run();
                CompletablePeek.this.l.run();
                this.h.onComplete();
                a();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.h.onError(th);
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            if (this.i == DisposableHelper.DISPOSED) {
                RxJavaPlugins.onError(th);
                return;
            }
            try {
                CompletablePeek.this.j.accept(th);
                CompletablePeek.this.l.run();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.h.onError(th);
            a();
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            try {
                CompletablePeek.this.i.accept(disposable);
                if (DisposableHelper.validate(this.i, disposable)) {
                    this.i = disposable;
                    this.h.onSubscribe(this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                disposable.dispose();
                this.i = DisposableHelper.DISPOSED;
                EmptyDisposable.error(th, this.h);
            }
        }
    }

    public CompletablePeek(CompletableSource completableSource, Consumer<? super Disposable> consumer, Consumer<? super Throwable> consumer2, Action action, Action action2, Action action3, Action action4) {
        this.h = completableSource;
        this.i = consumer;
        this.j = consumer2;
        this.k = action;
        this.l = action2;
        this.m = action3;
        this.n = action4;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.h.subscribe(new a(completableObserver));
    }
}
