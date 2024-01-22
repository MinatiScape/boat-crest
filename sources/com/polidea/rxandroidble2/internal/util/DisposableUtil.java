package com.polidea.rxandroidble2.internal.util;

import io.reactivex.ObservableEmitter;
import io.reactivex.SingleEmitter;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
/* loaded from: classes12.dex */
public class DisposableUtil {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes12.dex */
    public class a<T> extends DisposableSingleObserver<T> {
        public final /* synthetic */ SingleEmitter i;

        public a(SingleEmitter singleEmitter) {
            this.i = singleEmitter;
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.i.tryOnError(th);
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            this.i.onSuccess(t);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes12.dex */
    public class b<T> extends DisposableObserver<T> {
        public final /* synthetic */ ObservableEmitter i;

        public b(ObservableEmitter observableEmitter) {
            this.i = observableEmitter;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.i.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.i.tryOnError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.i.onNext(t);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes12.dex */
    public class c<T> extends DisposableSingleObserver<T> {
        public final /* synthetic */ ObservableEmitter i;

        public c(ObservableEmitter observableEmitter) {
            this.i = observableEmitter;
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.i.tryOnError(th);
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            this.i.onNext(t);
            this.i.onComplete();
        }
    }

    public static <T> DisposableObserver<T> disposableObserverFromEmitter(ObservableEmitter<T> observableEmitter) {
        return new b(observableEmitter);
    }

    public static <T> DisposableSingleObserver<T> disposableSingleObserverFromEmitter(SingleEmitter<T> singleEmitter) {
        return new a(singleEmitter);
    }

    public static <T> DisposableSingleObserver<T> disposableSingleObserverFromEmitter(ObservableEmitter<T> observableEmitter) {
        return new c(observableEmitter);
    }
}
