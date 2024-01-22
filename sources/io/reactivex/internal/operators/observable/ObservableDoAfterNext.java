package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.BasicFuseableObserver;
/* loaded from: classes12.dex */
public final class ObservableDoAfterNext<T> extends io.reactivex.internal.operators.observable.a<T, T> {
    public final Consumer<? super T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> extends BasicFuseableObserver<T, T> {
        public final Consumer<? super T> h;

        public a(Observer<? super T> observer, Consumer<? super T> consumer) {
            super(observer);
            this.h = consumer;
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.downstream.onNext(t);
            if (this.sourceMode == 0) {
                try {
                    this.h.accept(t);
                } catch (Throwable th) {
                    fail(th);
                }
            }
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() throws Exception {
            T poll = this.qd.poll();
            if (poll != null) {
                this.h.accept(poll);
            }
            return poll;
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }
    }

    public ObservableDoAfterNext(ObservableSource<T> observableSource, Consumer<? super T> consumer) {
        super(observableSource);
        this.h = consumer;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new a(observer, this.h));
    }
}
