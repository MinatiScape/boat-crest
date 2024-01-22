package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.observers.BasicFuseableObserver;
import java.util.Objects;
import java.util.Optional;
/* loaded from: classes12.dex */
public final class ObservableMapOptional<T, R> extends Observable<R> {
    public final Observable<T> h;
    public final Function<? super T, Optional<? extends R>> i;

    /* loaded from: classes12.dex */
    public static final class a<T, R> extends BasicFuseableObserver<T, R> {
        public final Function<? super T, Optional<? extends R>> h;

        public a(Observer<? super R> observer, Function<? super T, Optional<? extends R>> function) {
            super(observer);
            this.h = function;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(null);
                return;
            }
            try {
                Optional<? extends R> apply = this.h.apply(t);
                Objects.requireNonNull(apply, "The mapper returned a null Optional");
                Optional<? extends R> optional = apply;
                if (optional.isPresent()) {
                    this.downstream.onNext((R) optional.get());
                }
            } catch (Throwable th) {
                fail(th);
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public R poll() throws Throwable {
            Optional<? extends R> optional;
            do {
                T poll = this.qd.poll();
                if (poll == null) {
                    return null;
                }
                Optional<? extends R> apply = this.h.apply(poll);
                Objects.requireNonNull(apply, "The mapper returned a null Optional");
                optional = apply;
            } while (!optional.isPresent());
            return optional.get();
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }
    }

    public ObservableMapOptional(Observable<T> observable, Function<? super T, Optional<? extends R>> function) {
        this.h = observable;
        this.i = function;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super R> observer) {
        this.h.subscribe(new a(observer, this.i));
    }
}
