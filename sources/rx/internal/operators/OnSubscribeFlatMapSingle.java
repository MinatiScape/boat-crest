package rx.internal.operators;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.atomic.MpscLinkedAtomicQueue;
import rx.internal.util.unsafe.MpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class OnSubscribeFlatMapSingle<T, R> implements Observable.OnSubscribe<R> {
    public final Observable<T> h;
    public final Func1<? super T, ? extends Single<? extends R>> i;
    public final boolean j;
    public final int k;

    /* loaded from: classes13.dex */
    public static final class a<T, R> extends Subscriber<T> {
        public final Subscriber<? super R> l;
        public final Func1<? super T, ? extends Single<? extends R>> m;
        public final boolean n;
        public final int o;
        public final Queue<Object> t;
        public volatile boolean v;
        public volatile boolean w;
        public final AtomicInteger p = new AtomicInteger();
        public final AtomicReference<Throwable> s = new AtomicReference<>();
        public final a<T, R>.b u = new b();
        public final CompositeSubscription r = new CompositeSubscription();
        public final AtomicInteger q = new AtomicInteger();

        /* renamed from: rx.internal.operators.OnSubscribeFlatMapSingle$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public final class C0938a extends SingleSubscriber<R> {
            public C0938a() {
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                a.this.c(this, th);
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(R r) {
                a.this.d(this, r);
            }
        }

        /* loaded from: classes13.dex */
        public final class b extends AtomicLong implements Producer, Subscription {
            private static final long serialVersionUID = -887187595446742742L;

            public b() {
            }

            @Override // rx.Subscription
            public boolean isUnsubscribed() {
                return a.this.w;
            }

            public void produced(long j) {
                BackpressureUtils.produced(this, j);
            }

            @Override // rx.Producer
            public void request(long j) {
                if (j > 0) {
                    BackpressureUtils.getAndAddRequest(this, j);
                    a.this.b();
                }
            }

            @Override // rx.Subscription
            public void unsubscribe() {
                a.this.w = true;
                a.this.unsubscribe();
                if (a.this.p.getAndIncrement() == 0) {
                    a.this.t.clear();
                }
            }
        }

        public a(Subscriber<? super R> subscriber, Func1<? super T, ? extends Single<? extends R>> func1, boolean z, int i) {
            this.l = subscriber;
            this.m = func1;
            this.n = z;
            this.o = i;
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.t = new MpscLinkedQueue();
            } else {
                this.t = new MpscLinkedAtomicQueue();
            }
            request(i != Integer.MAX_VALUE ? i : Long.MAX_VALUE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:36:0x0082, code lost:
            if (r13 != 0) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0086, code lost:
            if (r17.w == false) goto L39;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0088, code lost:
            r2.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x008b, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x008e, code lost:
            if (r17.v == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0090, code lost:
            if (r3 == false) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0096, code lost:
            if (r4.get() != 0) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x009c, code lost:
            if (r2.isEmpty() == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00a6, code lost:
            if (r17.s.get() == null) goto L51;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00a8, code lost:
            r1.onError(rx.internal.util.ExceptionsUtils.terminate(r17.s));
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00b2, code lost:
            r1.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00b5, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00be, code lost:
            if (r17.s.get() == null) goto L55;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00c0, code lost:
            r2.clear();
            r1.onError(rx.internal.util.ExceptionsUtils.terminate(r17.s));
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00cc, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00d1, code lost:
            if (r4.get() != 0) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x00d7, code lost:
            if (r2.isEmpty() == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00d9, code lost:
            r1.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00dc, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00df, code lost:
            if (r11 == 0) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00e1, code lost:
            r17.u.produced(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x00e8, code lost:
            if (r17.v != false) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00ef, code lost:
            if (r17.o == Integer.MAX_VALUE) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x00f1, code lost:
            request(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x00f4, code lost:
            r6 = r17.p.addAndGet(-r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:?, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void b() {
            /*
                Method dump skipped, instructions count: 254
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeFlatMapSingle.a.b():void");
        }

        public void c(a<T, R>.C0938a c0938a, Throwable th) {
            if (this.n) {
                ExceptionsUtils.addThrowable(this.s, th);
                this.r.remove(c0938a);
                if (!this.v && this.o != Integer.MAX_VALUE) {
                    request(1L);
                }
            } else {
                this.r.unsubscribe();
                unsubscribe();
                if (!this.s.compareAndSet(null, th)) {
                    RxJavaHooks.onError(th);
                    return;
                }
                this.v = true;
            }
            this.q.decrementAndGet();
            b();
        }

        public void d(a<T, R>.C0938a c0938a, R r) {
            this.t.offer(NotificationLite.next(r));
            this.r.remove(c0938a);
            this.q.decrementAndGet();
            b();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.v = true;
            b();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.n) {
                ExceptionsUtils.addThrowable(this.s, th);
            } else {
                this.r.unsubscribe();
                if (!this.s.compareAndSet(null, th)) {
                    RxJavaHooks.onError(th);
                    return;
                }
            }
            this.v = true;
            b();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                Single<? extends R> call = this.m.call(t);
                if (call != null) {
                    C0938a c0938a = new C0938a();
                    this.r.add(c0938a);
                    this.q.incrementAndGet();
                    call.subscribe(c0938a);
                    return;
                }
                throw new NullPointerException("The mapper returned a null Single");
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(th);
            }
        }
    }

    public OnSubscribeFlatMapSingle(Observable<T> observable, Func1<? super T, ? extends Single<? extends R>> func1, boolean z, int i) {
        Objects.requireNonNull(func1, "mapper is null");
        if (i > 0) {
            this.h = observable;
            this.i = func1;
            this.j = z;
            this.k = i;
            return;
        }
        throw new IllegalArgumentException("maxConcurrency > 0 required but it was " + i);
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        a aVar = new a(subscriber, this.i, this.j, this.k);
        subscriber.add(aVar.r);
        subscriber.add(aVar.u);
        subscriber.setProducer(aVar.u);
        this.h.unsafeSubscribe(aVar);
    }
}
