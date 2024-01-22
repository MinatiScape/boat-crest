package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.internal.schedulers.ImmediateScheduler;
import rx.internal.schedulers.TrampolineScheduler;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;
/* loaded from: classes13.dex */
public final class OperatorObserveOn<T> implements Observable.Operator<T, T> {
    public final Scheduler h;
    public final boolean i;
    public final int j;

    /* loaded from: classes13.dex */
    public static class a implements Observable.Operator<T, T> {
        public final /* synthetic */ int h;

        public a(int i) {
            this.h = i;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
            b bVar = new b(Schedulers.immediate(), subscriber, false, this.h);
            bVar.c();
            return bVar;
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends Subscriber<T> implements Action0 {
        public final Subscriber<? super T> l;
        public final Scheduler.Worker m;
        public final boolean n;
        public final Queue<Object> o;
        public final int p;
        public volatile boolean q;
        public final AtomicLong r = new AtomicLong();
        public final AtomicLong s = new AtomicLong();
        public Throwable t;
        public long u;

        /* loaded from: classes13.dex */
        public class a implements Producer {
            public a() {
            }

            @Override // rx.Producer
            public void request(long j) {
                if (j > 0) {
                    BackpressureUtils.getAndAddRequest(b.this.r, j);
                    b.this.d();
                }
            }
        }

        public b(Scheduler scheduler, Subscriber<? super T> subscriber, boolean z, int i) {
            this.l = subscriber;
            this.m = scheduler.createWorker();
            this.n = z;
            i = i <= 0 ? RxRingBuffer.SIZE : i;
            this.p = i - (i >> 2);
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.o = new SpscArrayQueue(i);
            } else {
                this.o = new SpscAtomicArrayQueue(i);
            }
            request(i);
        }

        public boolean b(boolean z, boolean z2, Subscriber<? super T> subscriber, Queue<Object> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                return true;
            } else if (z) {
                if (this.n) {
                    if (z2) {
                        Throwable th = this.t;
                        try {
                            if (th != null) {
                                subscriber.onError(th);
                            } else {
                                subscriber.onCompleted();
                            }
                            return false;
                        } finally {
                        }
                    }
                    return false;
                }
                Throwable th2 = this.t;
                if (th2 != null) {
                    queue.clear();
                    try {
                        subscriber.onError(th2);
                        return true;
                    } finally {
                    }
                } else if (z2) {
                    try {
                        subscriber.onCompleted();
                        return true;
                    } finally {
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        public void c() {
            Subscriber<? super T> subscriber = this.l;
            subscriber.setProducer(new a());
            subscriber.add(this.m);
            subscriber.add(this);
        }

        @Override // rx.functions.Action0
        public void call() {
            int i;
            long j = this.u;
            Queue<Object> queue = this.o;
            Subscriber<? super T> subscriber = this.l;
            long j2 = 1;
            do {
                long j3 = this.r.get();
                while (true) {
                    i = (j3 > j ? 1 : (j3 == j ? 0 : -1));
                    if (i == 0) {
                        break;
                    }
                    boolean z = this.q;
                    Object poll = queue.poll();
                    boolean z2 = poll == null;
                    if (b(z, z2, subscriber, queue)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    subscriber.onNext((Object) NotificationLite.getValue(poll));
                    j++;
                    if (j == this.p) {
                        j3 = BackpressureUtils.produced(this.r, j);
                        request(j);
                        j = 0;
                    }
                }
                if (i == 0 && b(this.q, queue.isEmpty(), subscriber, queue)) {
                    return;
                }
                this.u = j;
                j2 = this.s.addAndGet(-j2);
            } while (j2 != 0);
        }

        public void d() {
            if (this.s.getAndIncrement() == 0) {
                this.m.schedule(this);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (isUnsubscribed() || this.q) {
                return;
            }
            this.q = true;
            d();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (!isUnsubscribed() && !this.q) {
                this.t = th;
                this.q = true;
                d();
                return;
            }
            RxJavaHooks.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (isUnsubscribed() || this.q) {
                return;
            }
            if (!this.o.offer(NotificationLite.next(t))) {
                onError(new MissingBackpressureException());
            } else {
                d();
            }
        }
    }

    public OperatorObserveOn(Scheduler scheduler, boolean z) {
        this(scheduler, z, RxRingBuffer.SIZE);
    }

    public static <T> Observable.Operator<T, T> rebatch(int i) {
        return new a(i);
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorObserveOn(Scheduler scheduler, boolean z, int i) {
        this.h = scheduler;
        this.i = z;
        this.j = i <= 0 ? RxRingBuffer.SIZE : i;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler scheduler = this.h;
        if ((scheduler instanceof ImmediateScheduler) || (scheduler instanceof TrampolineScheduler)) {
            return subscriber;
        }
        b bVar = new b(scheduler, subscriber, this.i, this.j);
        bVar.c();
        return bVar;
    }
}
