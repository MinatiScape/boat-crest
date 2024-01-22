package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.observers.SerializedSubscriber;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;
/* loaded from: classes13.dex */
public final class OnSubscribeConcatMap<T, R> implements Observable.OnSubscribe<R> {
    public static final int BOUNDARY = 1;
    public static final int END = 2;
    public static final int IMMEDIATE = 0;
    public final Observable<? extends T> h;
    public final Func1<? super T, ? extends Observable<? extends R>> i;
    public final int j;
    public final int k;

    /* loaded from: classes13.dex */
    public class a implements Producer {
        public final /* synthetic */ d h;

        public a(OnSubscribeConcatMap onSubscribeConcatMap, d dVar) {
            this.h = dVar;
        }

        @Override // rx.Producer
        public void request(long j) {
            this.h.requestMore(j);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T, R> implements Producer {
        public final R h;
        public final d<T, R> i;
        public boolean j;

        public b(R r, d<T, R> dVar) {
            this.h = r;
            this.i = dVar;
        }

        @Override // rx.Producer
        public void request(long j) {
            if (this.j || j <= 0) {
                return;
            }
            this.j = true;
            d<T, R> dVar = this.i;
            dVar.f(this.h);
            dVar.d(1L);
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T, R> extends Subscriber<R> {
        public final d<T, R> l;
        public long m;

        public c(d<T, R> dVar) {
            this.l = dVar;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.d(this.m);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.e(th, this.m);
        }

        @Override // rx.Observer
        public void onNext(R r) {
            this.m++;
            this.l.f(r);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.l.o.setProducer(producer);
        }
    }

    /* loaded from: classes13.dex */
    public static final class d<T, R> extends Subscriber<T> {
        public final Subscriber<? super R> l;
        public final Func1<? super T, ? extends Observable<? extends R>> m;
        public final int n;
        public final Queue<Object> p;
        public final SerialSubscription s;
        public volatile boolean t;
        public volatile boolean u;
        public final ProducerArbiter o = new ProducerArbiter();
        public final AtomicInteger q = new AtomicInteger();
        public final AtomicReference<Throwable> r = new AtomicReference<>();

        public d(Subscriber<? super R> subscriber, Func1<? super T, ? extends Observable<? extends R>> func1, int i, int i2) {
            Queue<Object> spscAtomicArrayQueue;
            this.l = subscriber;
            this.m = func1;
            this.n = i2;
            if (UnsafeAccess.isUnsafeAvailable()) {
                spscAtomicArrayQueue = new SpscArrayQueue<>(i);
            } else {
                spscAtomicArrayQueue = new SpscAtomicArrayQueue<>(i);
            }
            this.p = spscAtomicArrayQueue;
            this.s = new SerialSubscription();
            request(i);
        }

        public void b() {
            if (this.q.getAndIncrement() != 0) {
                return;
            }
            int i = this.n;
            while (!this.l.isUnsubscribed()) {
                if (!this.u) {
                    if (i == 1 && this.r.get() != null) {
                        Throwable terminate = ExceptionsUtils.terminate(this.r);
                        if (ExceptionsUtils.isTerminated(terminate)) {
                            return;
                        }
                        this.l.onError(terminate);
                        return;
                    }
                    boolean z = this.t;
                    Object poll = this.p.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        Throwable terminate2 = ExceptionsUtils.terminate(this.r);
                        if (terminate2 == null) {
                            this.l.onCompleted();
                            return;
                        } else if (ExceptionsUtils.isTerminated(terminate2)) {
                            return;
                        } else {
                            this.l.onError(terminate2);
                            return;
                        }
                    } else if (!z2) {
                        try {
                            Observable<? extends R> call = this.m.call((Object) NotificationLite.getValue(poll));
                            if (call == null) {
                                c(new NullPointerException("The source returned by the mapper was null"));
                                return;
                            } else if (call != Observable.empty()) {
                                if (call instanceof ScalarSynchronousObservable) {
                                    this.u = true;
                                    this.o.setProducer(new b(((ScalarSynchronousObservable) call).get(), this));
                                } else {
                                    c cVar = new c(this);
                                    this.s.set(cVar);
                                    if (cVar.isUnsubscribed()) {
                                        return;
                                    }
                                    this.u = true;
                                    call.unsafeSubscribe(cVar);
                                }
                                request(1L);
                            } else {
                                request(1L);
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            c(th);
                            return;
                        }
                    }
                }
                if (this.q.decrementAndGet() == 0) {
                    return;
                }
            }
        }

        public void c(Throwable th) {
            unsubscribe();
            if (ExceptionsUtils.addThrowable(this.r, th)) {
                Throwable terminate = ExceptionsUtils.terminate(this.r);
                if (ExceptionsUtils.isTerminated(terminate)) {
                    return;
                }
                this.l.onError(terminate);
                return;
            }
            g(th);
        }

        public void d(long j) {
            if (j != 0) {
                this.o.produced(j);
            }
            this.u = false;
            b();
        }

        public void e(Throwable th, long j) {
            if (!ExceptionsUtils.addThrowable(this.r, th)) {
                g(th);
            } else if (this.n == 0) {
                Throwable terminate = ExceptionsUtils.terminate(this.r);
                if (!ExceptionsUtils.isTerminated(terminate)) {
                    this.l.onError(terminate);
                }
                unsubscribe();
            } else {
                if (j != 0) {
                    this.o.produced(j);
                }
                this.u = false;
                b();
            }
        }

        public void f(R r) {
            this.l.onNext(r);
        }

        public void g(Throwable th) {
            RxJavaHooks.onError(th);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.t = true;
            b();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (ExceptionsUtils.addThrowable(this.r, th)) {
                this.t = true;
                if (this.n == 0) {
                    Throwable terminate = ExceptionsUtils.terminate(this.r);
                    if (!ExceptionsUtils.isTerminated(terminate)) {
                        this.l.onError(terminate);
                    }
                    this.s.unsubscribe();
                    return;
                }
                b();
                return;
            }
            g(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.p.offer(NotificationLite.next(t))) {
                unsubscribe();
                onError(new MissingBackpressureException());
                return;
            }
            b();
        }

        public void requestMore(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i > 0) {
                this.o.request(j);
            } else if (i >= 0) {
            } else {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            }
        }
    }

    public OnSubscribeConcatMap(Observable<? extends T> observable, Func1<? super T, ? extends Observable<? extends R>> func1, int i, int i2) {
        this.h = observable;
        this.i = func1;
        this.j = i;
        this.k = i2;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        d dVar = new d(this.k == 0 ? new SerializedSubscriber<>(subscriber) : subscriber, this.i, this.j, this.k);
        subscriber.add(dVar);
        subscriber.add(dVar.s);
        subscriber.setProducer(new a(this, dVar));
        if (subscriber.isUnsubscribed()) {
            return;
        }
        this.h.unsafeSubscribe(dVar);
    }
}
