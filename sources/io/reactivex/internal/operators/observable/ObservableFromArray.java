package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.BasicQueueDisposable;
/* loaded from: classes12.dex */
public final class ObservableFromArray<T> extends Observable<T> {
    public final T[] h;

    /* loaded from: classes12.dex */
    public static final class a<T> extends BasicQueueDisposable<T> {
        public final Observer<? super T> h;
        public final T[] i;
        public int j;
        public boolean k;
        public volatile boolean l;

        public a(Observer<? super T> observer, T[] tArr) {
            this.h = observer;
            this.i = tArr;
        }

        public void a() {
            T[] tArr = this.i;
            int length = tArr.length;
            for (int i = 0; i < length && !isDisposed(); i++) {
                T t = tArr[i];
                if (t == null) {
                    Observer<? super T> observer = this.h;
                    observer.onError(new NullPointerException("The element at index " + i + " is null"));
                    return;
                }
                this.h.onNext(t);
            }
            if (isDisposed()) {
                return;
            }
            this.h.onComplete();
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            this.j = this.i.length;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.l = true;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.l;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return this.j == this.i.length;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() {
            int i = this.j;
            T[] tArr = this.i;
            if (i != tArr.length) {
                this.j = i + 1;
                return (T) ObjectHelper.requireNonNull(tArr[i], "The array element is null");
            }
            return null;
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 1) != 0) {
                this.k = true;
                return 1;
            }
            return 0;
        }
    }

    public ObservableFromArray(T[] tArr) {
        this.h = tArr;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        a aVar = new a(observer, this.h);
        observer.onSubscribe(aVar);
        if (aVar.k) {
            return;
        }
        aVar.a();
    }
}
