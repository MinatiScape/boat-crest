package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.BasicQueueDisposable;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class MaybeFlatMapIterableObservable<T, R> extends Observable<R> {
    public final MaybeSource<T> h;
    public final Function<? super T, ? extends Iterable<? extends R>> i;

    /* loaded from: classes12.dex */
    public static final class a<T, R> extends BasicQueueDisposable<R> implements MaybeObserver<T> {
        public final Observer<? super R> h;
        public final Function<? super T, ? extends Iterable<? extends R>> i;
        public Disposable j;
        public volatile Iterator<? extends R> k;
        public volatile boolean l;
        public boolean m;

        public a(Observer<? super R> observer, Function<? super T, ? extends Iterable<? extends R>> function) {
            this.h = observer;
            this.i = function;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            this.k = null;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.l = true;
            this.j.dispose();
            this.j = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.l;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return this.k == null;
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            this.h.onComplete();
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(Throwable th) {
            this.j = DisposableHelper.DISPOSED;
            this.h.onError(th);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                this.h.onSubscribe(this);
            }
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(T t) {
            Observer<? super R> observer = this.h;
            try {
                Iterator<? extends R> it = this.i.apply(t).iterator();
                if (!it.hasNext()) {
                    observer.onComplete();
                    return;
                }
                this.k = it;
                if (this.m) {
                    observer.onNext(null);
                    observer.onComplete();
                    return;
                }
                while (!this.l) {
                    try {
                        observer.onNext((R) it.next());
                        if (this.l) {
                            return;
                        }
                        try {
                            if (!it.hasNext()) {
                                observer.onComplete();
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            observer.onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        observer.onError(th2);
                        return;
                    }
                }
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                observer.onError(th3);
            }
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public R poll() throws Exception {
            Iterator<? extends R> it = this.k;
            if (it != null) {
                R r = (R) ObjectHelper.requireNonNull(it.next(), "The iterator returned a null value");
                if (!it.hasNext()) {
                    this.k = null;
                }
                return r;
            }
            return null;
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 2) != 0) {
                this.m = true;
                return 2;
            }
            return 0;
        }
    }

    public MaybeFlatMapIterableObservable(MaybeSource<T> maybeSource, Function<? super T, ? extends Iterable<? extends R>> function) {
        this.h = maybeSource;
        this.i = function;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super R> observer) {
        this.h.subscribe(new a(observer, this.i));
    }
}
