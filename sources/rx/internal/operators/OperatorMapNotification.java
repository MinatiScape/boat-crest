package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;
/* loaded from: classes13.dex */
public final class OperatorMapNotification<T, R> implements Observable.Operator<R, T> {
    public final Func1<? super T, ? extends R> h;
    public final Func1<? super Throwable, ? extends R> i;
    public final Func0<? extends R> j;

    /* loaded from: classes13.dex */
    public class a implements Producer {
        public final /* synthetic */ b h;

        public a(OperatorMapNotification operatorMapNotification, b bVar) {
            this.h = bVar;
        }

        @Override // rx.Producer
        public void request(long j) {
            this.h.c(j);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T, R> extends Subscriber<T> {
        public final Subscriber<? super R> l;
        public final Func1<? super T, ? extends R> m;
        public final Func1<? super Throwable, ? extends R> n;
        public final Func0<? extends R> o;
        public final AtomicLong p = new AtomicLong();
        public final AtomicLong q = new AtomicLong();
        public final AtomicReference<Producer> r = new AtomicReference<>();
        public long s;
        public R t;

        public b(Subscriber<? super R> subscriber, Func1<? super T, ? extends R> func1, Func1<? super Throwable, ? extends R> func12, Func0<? extends R> func0) {
            this.l = subscriber;
            this.m = func1;
            this.n = func12;
            this.o = func0;
        }

        public void b() {
            long j = this.s;
            if (j == 0 || this.r.get() == null) {
                return;
            }
            BackpressureUtils.produced(this.p, j);
        }

        public void c(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (i == 0) {
            } else {
                while (true) {
                    long j2 = this.p.get();
                    if ((j2 & Long.MIN_VALUE) != 0) {
                        long j3 = Long.MAX_VALUE & j2;
                        if (this.p.compareAndSet(j2, Long.MIN_VALUE | BackpressureUtils.addCap(j3, j))) {
                            if (j3 == 0) {
                                if (!this.l.isUnsubscribed()) {
                                    this.l.onNext((R) this.t);
                                }
                                if (this.l.isUnsubscribed()) {
                                    return;
                                }
                                this.l.onCompleted();
                                return;
                            }
                            return;
                        }
                    } else {
                        if (this.p.compareAndSet(j2, BackpressureUtils.addCap(j2, j))) {
                            AtomicReference<Producer> atomicReference = this.r;
                            Producer producer = atomicReference.get();
                            if (producer != null) {
                                producer.request(j);
                                return;
                            }
                            BackpressureUtils.getAndAddRequest(this.q, j);
                            Producer producer2 = atomicReference.get();
                            if (producer2 != null) {
                                long andSet = this.q.getAndSet(0L);
                                if (andSet != 0) {
                                    producer2.request(andSet);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                    }
                }
            }
        }

        public void d() {
            long j;
            do {
                j = this.p.get();
                if ((j & Long.MIN_VALUE) != 0) {
                    return;
                }
            } while (!this.p.compareAndSet(j, Long.MIN_VALUE | j));
            if (j != 0 || this.r.get() == null) {
                if (!this.l.isUnsubscribed()) {
                    this.l.onNext((R) this.t);
                }
                if (this.l.isUnsubscribed()) {
                    return;
                }
                this.l.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            b();
            try {
                this.t = this.o.call();
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.l);
            }
            d();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            b();
            try {
                this.t = this.n.call(th);
            } catch (Throwable th2) {
                Exceptions.throwOrReport(th2, this.l, th);
            }
            d();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                this.s++;
                this.l.onNext((R) this.m.call(t));
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.l, t);
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            if (this.r.compareAndSet(null, producer)) {
                long andSet = this.q.getAndSet(0L);
                if (andSet != 0) {
                    producer.request(andSet);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Producer already set!");
        }
    }

    public OperatorMapNotification(Func1<? super T, ? extends R> func1, Func1<? super Throwable, ? extends R> func12, Func0<? extends R> func0) {
        this.h = func1;
        this.i = func12;
        this.j = func0;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        b bVar = new b(subscriber, this.h, this.i, this.j);
        subscriber.add(bVar);
        subscriber.setProducer(new a(this, bVar));
        return bVar;
    }
}
