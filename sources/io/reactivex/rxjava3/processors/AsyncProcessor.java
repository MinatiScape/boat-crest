package io.reactivex.rxjava3.processors;

import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class AsyncProcessor<T> extends FlowableProcessor<T> {
    public static final a[] l = new a[0];
    public static final a[] m = new a[0];
    public final AtomicReference<a<T>[]> i = new AtomicReference<>(l);
    public Throwable j;
    public T k;

    /* loaded from: classes12.dex */
    public static final class a<T> extends DeferredScalarSubscription<T> {
        private static final long serialVersionUID = 5629876084736248016L;
        public final AsyncProcessor<T> parent;

        public a(Subscriber<? super T> subscriber, AsyncProcessor<T> asyncProcessor) {
            super(subscriber);
            this.parent = asyncProcessor;
        }

        @Override // io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription, org.reactivestreams.Subscription
        public void cancel() {
            if (super.tryCancel()) {
                this.parent.f(this);
            }
        }

        public void onComplete() {
            if (isCancelled()) {
                return;
            }
            this.downstream.onComplete();
        }

        public void onError(Throwable th) {
            if (isCancelled()) {
                RxJavaPlugins.onError(th);
            } else {
                this.downstream.onError(th);
            }
        }
    }

    @CheckReturnValue
    @NonNull
    public static <T> AsyncProcessor<T> create() {
        return new AsyncProcessor<>();
    }

    public boolean e(a<T> aVar) {
        a<T>[] aVarArr;
        a<T>[] aVarArr2;
        do {
            aVarArr = this.i.get();
            if (aVarArr == m) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.i.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void f(a<T> aVar) {
        a<T>[] aVarArr;
        a[] aVarArr2;
        do {
            aVarArr = this.i.get();
            int length = aVarArr.length;
            if (length == 0) {
                return;
            }
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (aVarArr[i2] == aVar) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                aVarArr2 = l;
            } else {
                a[] aVarArr3 = new a[length - 1];
                System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                aVarArr2 = aVarArr3;
            }
        } while (!this.i.compareAndSet(aVarArr, aVarArr2));
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @CheckReturnValue
    @Nullable
    public Throwable getThrowable() {
        if (this.i.get() == m) {
            return this.j;
        }
        return null;
    }

    @CheckReturnValue
    @Nullable
    public T getValue() {
        if (this.i.get() == m) {
            return this.k;
        }
        return null;
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @CheckReturnValue
    public boolean hasComplete() {
        return this.i.get() == m && this.j == null;
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @CheckReturnValue
    public boolean hasSubscribers() {
        return this.i.get().length != 0;
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @CheckReturnValue
    public boolean hasThrowable() {
        return this.i.get() == m && this.j != null;
    }

    @CheckReturnValue
    public boolean hasValue() {
        return this.i.get() == m && this.k != null;
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        a<T>[] aVarArr = this.i.get();
        a<T>[] aVarArr2 = m;
        if (aVarArr == aVarArr2) {
            return;
        }
        T t = this.k;
        a<T>[] andSet = this.i.getAndSet(aVarArr2);
        int i = 0;
        if (t == null) {
            int length = andSet.length;
            while (i < length) {
                andSet[i].onComplete();
                i++;
            }
            return;
        }
        int length2 = andSet.length;
        while (i < length2) {
            andSet[i].complete(t);
            i++;
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(@NonNull Throwable th) {
        ExceptionHelper.nullCheck(th, "onError called with a null Throwable.");
        a<T>[] aVarArr = this.i.get();
        a<T>[] aVarArr2 = m;
        if (aVarArr == aVarArr2) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.k = null;
        this.j = th;
        for (a<T> aVar : this.i.getAndSet(aVarArr2)) {
            aVar.onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(@NonNull T t) {
        ExceptionHelper.nullCheck(t, "onNext called with a null value.");
        if (this.i.get() == m) {
            return;
        }
        this.k = t;
    }

    @Override // org.reactivestreams.Subscriber
    public void onSubscribe(@NonNull Subscription subscription) {
        if (this.i.get() == m) {
            subscription.cancel();
        } else {
            subscription.request(Long.MAX_VALUE);
        }
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(@NonNull Subscriber<? super T> subscriber) {
        a<T> aVar = new a<>(subscriber, this);
        subscriber.onSubscribe(aVar);
        if (e(aVar)) {
            if (aVar.isCancelled()) {
                f(aVar);
                return;
            }
            return;
        }
        Throwable th = this.j;
        if (th != null) {
            subscriber.onError(th);
            return;
        }
        T t = this.k;
        if (t != null) {
            aVar.complete(t);
        } else {
            aVar.onComplete();
        }
    }
}