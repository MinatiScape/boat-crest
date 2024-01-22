package rx.internal.operators;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.BackpressureOverflow;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.internal.util.BackpressureDrainManager;
/* loaded from: classes13.dex */
public class OperatorOnBackpressureBuffer<T> implements Observable.Operator<T, T> {
    public final Long h;
    public final Action0 i;
    public final BackpressureOverflow.Strategy j;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> implements BackpressureDrainManager.BackpressureQueueCallback {
        public final AtomicLong m;
        public final Subscriber<? super T> n;
        public final BackpressureDrainManager p;
        public final Action0 q;
        public final BackpressureOverflow.Strategy r;
        public final ConcurrentLinkedQueue<Object> l = new ConcurrentLinkedQueue<>();
        public final AtomicBoolean o = new AtomicBoolean(false);

        public a(Subscriber<? super T> subscriber, Long l, Action0 action0, BackpressureOverflow.Strategy strategy) {
            this.n = subscriber;
            this.m = l != null ? new AtomicLong(l.longValue()) : null;
            this.q = action0;
            this.p = new BackpressureDrainManager(this);
            this.r = strategy;
        }

        @Override // rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback
        public boolean accept(Object obj) {
            return NotificationLite.accept(this.n, obj);
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0049 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean b() {
            /*
                r6 = this;
                java.util.concurrent.atomic.AtomicLong r0 = r6.m
                r1 = 1
                if (r0 != 0) goto L6
                return r1
            L6:
                java.util.concurrent.atomic.AtomicLong r0 = r6.m
                long r2 = r0.get()
                r4 = 0
                int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r0 > 0) goto L4a
                r0 = 0
                rx.BackpressureOverflow$Strategy r4 = r6.r     // Catch: rx.exceptions.MissingBackpressureException -> L23
                boolean r4 = r4.mayAttemptDrop()     // Catch: rx.exceptions.MissingBackpressureException -> L23
                if (r4 == 0) goto L34
                java.lang.Object r4 = r6.poll()     // Catch: rx.exceptions.MissingBackpressureException -> L23
                if (r4 == 0) goto L34
                r4 = r1
                goto L35
            L23:
                r4 = move-exception
                java.util.concurrent.atomic.AtomicBoolean r5 = r6.o
                boolean r5 = r5.compareAndSet(r0, r1)
                if (r5 == 0) goto L34
                r6.unsubscribe()
                rx.Subscriber<? super T> r5 = r6.n
                r5.onError(r4)
            L34:
                r4 = r0
            L35:
                rx.functions.Action0 r5 = r6.q
                if (r5 == 0) goto L47
                r5.call()     // Catch: java.lang.Throwable -> L3d
                goto L47
            L3d:
                r1 = move-exception
                rx.exceptions.Exceptions.throwIfFatal(r1)
                rx.internal.util.BackpressureDrainManager r2 = r6.p
                r2.terminateAndDrain(r1)
                return r0
            L47:
                if (r4 != 0) goto L4a
                return r0
            L4a:
                java.util.concurrent.atomic.AtomicLong r0 = r6.m
                r4 = 1
                long r4 = r2 - r4
                boolean r0 = r0.compareAndSet(r2, r4)
                if (r0 == 0) goto L6
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorOnBackpressureBuffer.a.b():boolean");
        }

        public Producer c() {
            return this.p;
        }

        @Override // rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback
        public void complete(Throwable th) {
            if (th != null) {
                this.n.onError(th);
            } else {
                this.n.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.o.get()) {
                return;
            }
            this.p.terminateAndDrain();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.o.get()) {
                return;
            }
            this.p.terminateAndDrain(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (b()) {
                this.l.offer(NotificationLite.next(t));
                this.p.drain();
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }

        @Override // rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback
        public Object peek() {
            return this.l.peek();
        }

        @Override // rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback
        public Object poll() {
            Object poll = this.l.poll();
            AtomicLong atomicLong = this.m;
            if (atomicLong != null && poll != null) {
                atomicLong.incrementAndGet();
            }
            return poll;
        }
    }

    /* loaded from: classes13.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorOnBackpressureBuffer<?> f15667a = new OperatorOnBackpressureBuffer<>();
    }

    public OperatorOnBackpressureBuffer() {
        this.h = null;
        this.i = null;
        this.j = BackpressureOverflow.ON_OVERFLOW_DEFAULT;
    }

    public static <T> OperatorOnBackpressureBuffer<T> instance() {
        return (OperatorOnBackpressureBuffer<T>) b.f15667a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        a aVar = new a(subscriber, this.h, this.i, this.j);
        subscriber.add(aVar);
        subscriber.setProducer(aVar.c());
        return aVar;
    }

    public OperatorOnBackpressureBuffer(long j) {
        this(j, null, BackpressureOverflow.ON_OVERFLOW_DEFAULT);
    }

    public OperatorOnBackpressureBuffer(long j, Action0 action0) {
        this(j, action0, BackpressureOverflow.ON_OVERFLOW_DEFAULT);
    }

    public OperatorOnBackpressureBuffer(long j, Action0 action0, BackpressureOverflow.Strategy strategy) {
        if (j > 0) {
            Objects.requireNonNull(strategy, "The BackpressureOverflow strategy must not be null");
            this.h = Long.valueOf(j);
            this.i = action0;
            this.j = strategy;
            return;
        }
        throw new IllegalArgumentException("Buffer capacity must be > 0");
    }
}
