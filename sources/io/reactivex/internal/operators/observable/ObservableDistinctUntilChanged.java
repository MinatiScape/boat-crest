package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Function;
import io.reactivex.internal.observers.BasicFuseableObserver;
/* loaded from: classes12.dex */
public final class ObservableDistinctUntilChanged<T, K> extends io.reactivex.internal.operators.observable.a<T, T> {
    public final Function<? super T, K> h;
    public final BiPredicate<? super K, ? super K> i;

    /* loaded from: classes12.dex */
    public static final class a<T, K> extends BasicFuseableObserver<T, T> {
        public final Function<? super T, K> h;
        public final BiPredicate<? super K, ? super K> i;
        public K j;
        public boolean k;

        public a(Observer<? super T> observer, Function<? super T, K> function, BiPredicate<? super K, ? super K> biPredicate) {
            super(observer);
            this.h = function;
            this.i = biPredicate;
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(t);
                return;
            }
            try {
                K apply = this.h.apply(t);
                if (this.k) {
                    boolean test = this.i.test((K) this.j, apply);
                    this.j = apply;
                    if (test) {
                        return;
                    }
                } else {
                    this.k = true;
                    this.j = apply;
                }
                this.downstream.onNext(t);
            } catch (Throwable th) {
                fail(th);
            }
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() throws Exception {
            while (true) {
                T poll = this.qd.poll();
                if (poll == null) {
                    return null;
                }
                K apply = this.h.apply(poll);
                if (!this.k) {
                    this.k = true;
                    this.j = apply;
                    return poll;
                } else if (!this.i.test((K) this.j, apply)) {
                    this.j = apply;
                    return poll;
                } else {
                    this.j = apply;
                }
            }
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }
    }

    public ObservableDistinctUntilChanged(ObservableSource<T> observableSource, Function<? super T, K> function, BiPredicate<? super K, ? super K> biPredicate) {
        super(observableSource);
        this.h = function;
        this.i = biPredicate;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new a(observer, this.h, this.i));
    }
}
