package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.observers.SerializedSubscriber;
/* loaded from: classes13.dex */
public final class OperatorSampleWithTime<T> implements Observable.Operator<T, T> {
    public final long h;
    public final TimeUnit i;
    public final Scheduler j;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> implements Action0 {
        public static final Object n = new Object();
        public final Subscriber<? super T> l;
        public final AtomicReference<Object> m = new AtomicReference<>(n);

        public a(Subscriber<? super T> subscriber) {
            this.l = subscriber;
        }

        public final void b() {
            AtomicReference<Object> atomicReference = this.m;
            Object obj = n;
            Object andSet = atomicReference.getAndSet(obj);
            if (andSet != obj) {
                try {
                    this.l.onNext(andSet);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }

        @Override // rx.functions.Action0
        public void call() {
            b();
        }

        @Override // rx.Observer
        public void onCompleted() {
            b();
            this.l.onCompleted();
            unsubscribe();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.onError(th);
            unsubscribe();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.m.set(t);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    public OperatorSampleWithTime(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.h = j;
        this.i = timeUnit;
        this.j = scheduler;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        Scheduler.Worker createWorker = this.j.createWorker();
        subscriber.add(createWorker);
        a aVar = new a(serializedSubscriber);
        subscriber.add(aVar);
        long j = this.h;
        createWorker.schedulePeriodically(aVar, j, j, this.i);
        return aVar;
    }
}
