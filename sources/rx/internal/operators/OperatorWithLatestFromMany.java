package rx.internal.operators;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.FuncN;
import rx.observers.SerializedSubscriber;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OperatorWithLatestFromMany<T, R> implements Observable.OnSubscribe<R> {
    public final Observable<T> h;
    public final Observable<?>[] i;
    public final Iterable<Observable<?>> j;
    public final FuncN<R> k;

    /* loaded from: classes13.dex */
    public static final class a<T, R> extends Subscriber<T> {
        public static final Object q = new Object();
        public final Subscriber<? super R> l;
        public final FuncN<R> m;
        public final AtomicReferenceArray<Object> n;
        public final AtomicInteger o;
        public boolean p;

        public a(Subscriber<? super R> subscriber, FuncN<R> funcN, int i) {
            this.l = subscriber;
            this.m = funcN;
            AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(i + 1);
            for (int i2 = 0; i2 <= i; i2++) {
                atomicReferenceArray.lazySet(i2, q);
            }
            this.n = atomicReferenceArray;
            this.o = new AtomicInteger(i);
            request(0L);
        }

        public void b(int i) {
            if (this.n.get(i) == q) {
                onCompleted();
            }
        }

        public void c(int i, Throwable th) {
            onError(th);
        }

        public void d(int i, Object obj) {
            if (this.n.getAndSet(i, obj) == q) {
                this.o.decrementAndGet();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.p) {
                return;
            }
            this.p = true;
            unsubscribe();
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.p) {
                RxJavaHooks.onError(th);
                return;
            }
            this.p = true;
            unsubscribe();
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.p) {
                return;
            }
            if (this.o.get() == 0) {
                AtomicReferenceArray<Object> atomicReferenceArray = this.n;
                int length = atomicReferenceArray.length();
                atomicReferenceArray.lazySet(0, t);
                Object[] objArr = new Object[atomicReferenceArray.length()];
                for (int i = 0; i < length; i++) {
                    objArr[i] = atomicReferenceArray.get(i);
                }
                try {
                    this.l.onNext(this.m.call(objArr));
                    return;
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    onError(th);
                    return;
                }
            }
            request(1L);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            super.setProducer(producer);
            this.l.setProducer(producer);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b extends Subscriber<Object> {
        public final a<?, ?> l;
        public final int m;

        public b(a<?, ?> aVar, int i) {
            this.l = aVar;
            this.m = i;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.b(this.m);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.c(this.m, th);
        }

        @Override // rx.Observer
        public void onNext(Object obj) {
            this.l.d(this.m, obj);
        }
    }

    public OperatorWithLatestFromMany(Observable<T> observable, Observable<?>[] observableArr, Iterable<Observable<?>> iterable, FuncN<R> funcN) {
        this.h = observable;
        this.i = observableArr;
        this.j = iterable;
        this.k = funcN;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        int i;
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        Observable<?>[] observableArr = this.i;
        int i2 = 0;
        if (observableArr != null) {
            i = observableArr.length;
        } else {
            observableArr = new Observable[8];
            int i3 = 0;
            for (Observable<?> observable : this.j) {
                if (i3 == observableArr.length) {
                    observableArr = (Observable[]) Arrays.copyOf(observableArr, (i3 >> 2) + i3);
                }
                observableArr[i3] = observable;
                i3++;
            }
            i = i3;
        }
        a aVar = new a(subscriber, this.k, i);
        serializedSubscriber.add(aVar);
        while (i2 < i) {
            if (serializedSubscriber.isUnsubscribed()) {
                return;
            }
            int i4 = i2 + 1;
            b bVar = new b(aVar, i4);
            aVar.add(bVar);
            observableArr[i2].unsafeSubscribe(bVar);
            i2 = i4;
        }
        this.h.unsafeSubscribe(aVar);
    }
}
