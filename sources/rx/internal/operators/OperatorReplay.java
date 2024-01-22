package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.internal.util.OpenHashSet;
import rx.observables.ConnectableObservable;
import rx.schedulers.Timestamped;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class OperatorReplay<T> extends ConnectableObservable<T> implements Subscription {
    public static final Func0 l = new a();
    public final Observable<? extends T> i;
    public final AtomicReference<l<T>> j;
    public final Func0<? extends k<T>> k;

    /* loaded from: classes13.dex */
    public static class a implements Func0 {
        @Override // rx.functions.Func0, java.util.concurrent.Callable
        public Object call() {
            return new o(16);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class b<R> implements Observable.OnSubscribe<R> {
        public final /* synthetic */ Func0 h;
        public final /* synthetic */ Func1 i;

        /* loaded from: classes13.dex */
        public class a implements Action1<Subscription> {
            public final /* synthetic */ Subscriber h;

            public a(b bVar, Subscriber subscriber) {
                this.h = subscriber;
            }

            @Override // rx.functions.Action1
            /* renamed from: a */
            public void call(Subscription subscription) {
                this.h.add(subscription);
            }
        }

        public b(Func0 func0, Func1 func1) {
            this.h = func0;
            this.i = func1;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super R> subscriber) {
            try {
                ConnectableObservable connectableObservable = (ConnectableObservable) this.h.call();
                ((Observable) this.i.call(connectableObservable)).subscribe((Subscriber) subscriber);
                connectableObservable.connect(new a(this, subscriber));
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, subscriber);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class c implements Observable.OnSubscribe<T> {
        public final /* synthetic */ Observable h;

        /* loaded from: classes13.dex */
        public class a extends Subscriber<T> {
            public final /* synthetic */ Subscriber l;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(c cVar, Subscriber subscriber, Subscriber subscriber2) {
                super(subscriber);
                this.l = subscriber2;
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.l.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                this.l.onError(th);
            }

            @Override // rx.Observer
            public void onNext(T t) {
                this.l.onNext(t);
            }
        }

        public c(Observable observable) {
            this.h = observable;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super T> subscriber) {
            this.h.unsafeSubscribe(new a(this, subscriber, subscriber));
        }
    }

    /* loaded from: classes13.dex */
    public static class d extends ConnectableObservable<T> {
        public final /* synthetic */ ConnectableObservable i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(Observable.OnSubscribe onSubscribe, ConnectableObservable connectableObservable) {
            super(onSubscribe);
            this.i = connectableObservable;
        }

        @Override // rx.observables.ConnectableObservable
        public void connect(Action1<? super Subscription> action1) {
            this.i.connect(action1);
        }
    }

    /* loaded from: classes13.dex */
    public static class e implements Func0<k<T>> {
        public final /* synthetic */ int h;

        public e(int i) {
            this.h = i;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        /* renamed from: a */
        public k<T> call() {
            return new n(this.h);
        }
    }

    /* loaded from: classes13.dex */
    public static class f implements Func0<k<T>> {
        public final /* synthetic */ int h;
        public final /* synthetic */ long i;
        public final /* synthetic */ Scheduler j;

        public f(int i, long j, Scheduler scheduler) {
            this.h = i;
            this.i = j;
            this.j = scheduler;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        /* renamed from: a */
        public k<T> call() {
            return new m(this.h, this.i, this.j);
        }
    }

    /* loaded from: classes13.dex */
    public static class g implements Observable.OnSubscribe<T> {
        public final /* synthetic */ AtomicReference h;
        public final /* synthetic */ Func0 i;

        public g(AtomicReference atomicReference, Func0 func0) {
            this.h = atomicReference;
            this.i = func0;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super T> subscriber) {
            l lVar;
            while (true) {
                lVar = (l) this.h.get();
                if (lVar != null) {
                    break;
                }
                l lVar2 = new l((k) this.i.call());
                lVar2.d();
                if (this.h.compareAndSet(lVar, lVar2)) {
                    lVar = lVar2;
                    break;
                }
            }
            i<T> iVar = new i<>(lVar, subscriber);
            lVar.b(iVar);
            subscriber.add(iVar);
            lVar.l.replay(iVar);
            subscriber.setProducer(iVar);
        }
    }

    /* loaded from: classes13.dex */
    public static class h<T> extends AtomicReference<j> implements k<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        public long index;
        public int size;
        public j tail;

        public h() {
            j jVar = new j(null, 0L);
            this.tail = jVar;
            set(jVar);
        }

        public final void addLast(j jVar) {
            this.tail.set(jVar);
            this.tail = jVar;
            this.size++;
        }

        public final void collect(Collection<? super T> collection) {
            j initialHead = getInitialHead();
            while (true) {
                initialHead = initialHead.get();
                if (initialHead == null) {
                    return;
                }
                Object leaveTransform = leaveTransform(initialHead.value);
                if (NotificationLite.isCompleted(leaveTransform) || NotificationLite.isError(leaveTransform)) {
                    return;
                }
                collection.add((Object) NotificationLite.getValue(leaveTransform));
            }
        }

        @Override // rx.internal.operators.OperatorReplay.k
        public final void complete() {
            Object enterTransform = enterTransform(NotificationLite.completed());
            long j = this.index + 1;
            this.index = j;
            addLast(new j(enterTransform, j));
            truncateFinal();
        }

        public Object enterTransform(Object obj) {
            return obj;
        }

        @Override // rx.internal.operators.OperatorReplay.k
        public final void error(Throwable th) {
            Object enterTransform = enterTransform(NotificationLite.error(th));
            long j = this.index + 1;
            this.index = j;
            addLast(new j(enterTransform, j));
            truncateFinal();
        }

        public j getInitialHead() {
            return get();
        }

        public boolean hasCompleted() {
            Object obj = this.tail.value;
            return obj != null && NotificationLite.isCompleted(leaveTransform(obj));
        }

        public boolean hasError() {
            Object obj = this.tail.value;
            return obj != null && NotificationLite.isError(leaveTransform(obj));
        }

        public Object leaveTransform(Object obj) {
            return obj;
        }

        @Override // rx.internal.operators.OperatorReplay.k
        public final void next(T t) {
            Object enterTransform = enterTransform(NotificationLite.next(t));
            long j = this.index + 1;
            this.index = j;
            addLast(new j(enterTransform, j));
            truncate();
        }

        public final void removeFirst() {
            j jVar = get().get();
            if (jVar != null) {
                this.size--;
                setFirst(jVar);
                return;
            }
            throw new IllegalStateException("Empty list!");
        }

        public final void removeSome(int i) {
            j jVar = get();
            while (i > 0) {
                jVar = jVar.get();
                i--;
                this.size--;
            }
            setFirst(jVar);
        }

        @Override // rx.internal.operators.OperatorReplay.k
        public final void replay(i<T> iVar) {
            Subscriber<? super T> subscriber;
            j jVar;
            synchronized (iVar) {
                if (iVar.emitting) {
                    iVar.missed = true;
                    return;
                }
                iVar.emitting = true;
                while (!iVar.isUnsubscribed()) {
                    j jVar2 = (j) iVar.index();
                    if (jVar2 == null) {
                        jVar2 = getInitialHead();
                        iVar.index = jVar2;
                        iVar.addTotalRequested(jVar2.index);
                    }
                    if (iVar.isUnsubscribed() || (subscriber = iVar.child) == null) {
                        return;
                    }
                    long j = iVar.get();
                    long j2 = 0;
                    while (j2 != j && (jVar = jVar2.get()) != null) {
                        Object leaveTransform = leaveTransform(jVar.value);
                        try {
                            if (NotificationLite.accept(subscriber, leaveTransform)) {
                                iVar.index = null;
                                return;
                            }
                            j2++;
                            if (iVar.isUnsubscribed()) {
                                return;
                            }
                            jVar2 = jVar;
                        } catch (Throwable th) {
                            iVar.index = null;
                            Exceptions.throwIfFatal(th);
                            iVar.unsubscribe();
                            if (NotificationLite.isError(leaveTransform) || NotificationLite.isCompleted(leaveTransform)) {
                                return;
                            }
                            subscriber.onError(OnErrorThrowable.addValueAsLastCause(th, NotificationLite.getValue(leaveTransform)));
                            return;
                        }
                    }
                    if (j2 != 0) {
                        iVar.index = jVar2;
                        if (j != Long.MAX_VALUE) {
                            iVar.produced(j2);
                        }
                    }
                    synchronized (iVar) {
                        if (!iVar.missed) {
                            iVar.emitting = false;
                            return;
                        }
                        iVar.missed = false;
                    }
                }
            }
        }

        public final void setFirst(j jVar) {
            set(jVar);
        }

        public void truncate() {
        }

        public void truncateFinal() {
        }
    }

    /* loaded from: classes13.dex */
    public static final class i<T> extends AtomicLong implements Producer, Subscription {
        public static final long UNSUBSCRIBED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        public Subscriber<? super T> child;
        public boolean emitting;
        public Object index;
        public boolean missed;
        public final l<T> parent;
        public final AtomicLong totalRequested = new AtomicLong();

        public i(l<T> lVar, Subscriber<? super T> subscriber) {
            this.parent = lVar;
            this.child = subscriber;
        }

        public void addTotalRequested(long j) {
            long j2;
            long j3;
            do {
                j2 = this.totalRequested.get();
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!this.totalRequested.compareAndSet(j2, j3));
        }

        public <U> U index() {
            return (U) this.index;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        public long produced(long j) {
            long j2;
            long j3;
            if (j > 0) {
                do {
                    j2 = get();
                    if (j2 == Long.MIN_VALUE) {
                        return Long.MIN_VALUE;
                    }
                    j3 = j2 - j;
                    if (j3 < 0) {
                        throw new IllegalStateException("More produced (" + j + ") than requested (" + j2 + ")");
                    }
                } while (!compareAndSet(j2, j3));
                return j3;
            }
            throw new IllegalArgumentException("Cant produce zero or less");
        }

        @Override // rx.Producer
        public void request(long j) {
            long j2;
            long j3;
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                return;
            }
            do {
                j2 = get();
                if (j2 == Long.MIN_VALUE) {
                    return;
                }
                if (j2 >= 0 && i == 0) {
                    return;
                }
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!compareAndSet(j2, j3));
            addTotalRequested(j);
            this.parent.f(this);
            this.parent.l.replay(this);
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (get() == Long.MIN_VALUE || getAndSet(Long.MIN_VALUE) == Long.MIN_VALUE) {
                return;
            }
            this.parent.g(this);
            this.parent.f(this);
            this.child = null;
        }
    }

    /* loaded from: classes13.dex */
    public static final class j extends AtomicReference<j> {
        private static final long serialVersionUID = 245354315435971818L;
        public final long index;
        public final Object value;

        public j(Object obj, long j) {
            this.value = obj;
            this.index = j;
        }
    }

    /* loaded from: classes13.dex */
    public interface k<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(i<T> iVar);
    }

    /* loaded from: classes13.dex */
    public static final class l<T> extends Subscriber<T> {
        public static final i[] A = new i[0];
        public final k<T> l;
        public boolean m;
        public volatile boolean n;
        public volatile long q;
        public long r;
        public boolean t;
        public boolean u;
        public long v;
        public long w;
        public volatile Producer x;
        public List<i<T>> y;
        public boolean z;
        public final OpenHashSet<i<T>> o = new OpenHashSet<>();
        public i<T>[] p = A;
        public final AtomicBoolean s = new AtomicBoolean();

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public a() {
            }

            @Override // rx.functions.Action0
            public void call() {
                if (l.this.n) {
                    return;
                }
                synchronized (l.this.o) {
                    if (!l.this.n) {
                        l.this.o.terminate();
                        l.this.q++;
                        l.this.n = true;
                    }
                }
            }
        }

        public l(k<T> kVar) {
            this.l = kVar;
            request(0L);
        }

        public boolean b(i<T> iVar) {
            Objects.requireNonNull(iVar);
            if (this.n) {
                return false;
            }
            synchronized (this.o) {
                if (this.n) {
                    return false;
                }
                this.o.add(iVar);
                this.q++;
                return true;
            }
        }

        public i<T>[] c() {
            i<T>[] iVarArr;
            synchronized (this.o) {
                i<T>[] values = this.o.values();
                int length = values.length;
                iVarArr = new i[length];
                System.arraycopy(values, 0, iVarArr, 0, length);
            }
            return iVarArr;
        }

        public void d() {
            add(Subscriptions.create(new a()));
        }

        public void e(long j, long j2) {
            long j3 = this.w;
            Producer producer = this.x;
            long j4 = j - j2;
            if (j4 == 0) {
                if (j3 == 0 || producer == null) {
                    return;
                }
                this.w = 0L;
                producer.request(j3);
                return;
            }
            this.v = j;
            if (producer == null) {
                long j5 = j3 + j4;
                if (j5 < 0) {
                    j5 = Long.MAX_VALUE;
                }
                this.w = j5;
            } else if (j3 != 0) {
                this.w = 0L;
                producer.request(j3 + j4);
            } else {
                producer.request(j4);
            }
        }

        public void f(i<T> iVar) {
            i<T>[] c;
            long j;
            List<i<T>> list;
            boolean z;
            long j2;
            i<T>[] c2;
            if (isUnsubscribed()) {
                return;
            }
            synchronized (this) {
                if (this.t) {
                    if (iVar != null) {
                        List list2 = this.y;
                        if (list2 == null) {
                            list2 = new ArrayList();
                            this.y = list2;
                        }
                        list2.add(iVar);
                    } else {
                        this.z = true;
                    }
                    this.u = true;
                    return;
                }
                this.t = true;
                long j3 = this.v;
                if (iVar != null) {
                    j = Math.max(j3, iVar.totalRequested.get());
                } else {
                    long j4 = j3;
                    for (i<T> iVar2 : c()) {
                        if (iVar2 != null) {
                            j4 = Math.max(j4, iVar2.totalRequested.get());
                        }
                    }
                    j = j4;
                }
                e(j, j3);
                while (!isUnsubscribed()) {
                    synchronized (this) {
                        if (!this.u) {
                            this.t = false;
                            return;
                        }
                        this.u = false;
                        list = this.y;
                        this.y = null;
                        z = this.z;
                        this.z = false;
                    }
                    long j5 = this.v;
                    if (list != null) {
                        j2 = j5;
                        for (i<T> iVar3 : list) {
                            j2 = Math.max(j2, iVar3.totalRequested.get());
                        }
                    } else {
                        j2 = j5;
                    }
                    if (z) {
                        for (i<T> iVar4 : c()) {
                            if (iVar4 != null) {
                                j2 = Math.max(j2, iVar4.totalRequested.get());
                            }
                        }
                    }
                    e(j2, j5);
                }
            }
        }

        public void g(i<T> iVar) {
            if (this.n) {
                return;
            }
            synchronized (this.o) {
                if (this.n) {
                    return;
                }
                this.o.remove(iVar);
                if (this.o.isEmpty()) {
                    this.p = A;
                }
                this.q++;
            }
        }

        public void h() {
            i<T>[] iVarArr = this.p;
            if (this.r != this.q) {
                synchronized (this.o) {
                    iVarArr = this.p;
                    i<T>[] values = this.o.values();
                    int length = values.length;
                    if (iVarArr.length != length) {
                        iVarArr = new i[length];
                        this.p = iVarArr;
                    }
                    System.arraycopy(values, 0, iVarArr, 0, length);
                    this.r = this.q;
                }
            }
            k<T> kVar = this.l;
            for (i<T> iVar : iVarArr) {
                if (iVar != null) {
                    kVar.replay(iVar);
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.m) {
                return;
            }
            this.m = true;
            try {
                this.l.complete();
                h();
            } finally {
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.m) {
                return;
            }
            this.m = true;
            try {
                this.l.error(th);
                h();
            } finally {
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.m) {
                return;
            }
            this.l.next(t);
            h();
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            if (this.x == null) {
                this.x = producer;
                f(null);
                h();
                return;
            }
            throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
        }
    }

    /* loaded from: classes13.dex */
    public static final class m<T> extends h<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        public final int limit;
        public final long maxAgeInMillis;
        public final Scheduler scheduler;

        public m(int i, long j, Scheduler scheduler) {
            this.scheduler = scheduler;
            this.limit = i;
            this.maxAgeInMillis = j;
        }

        @Override // rx.internal.operators.OperatorReplay.h
        public Object enterTransform(Object obj) {
            return new Timestamped(this.scheduler.now(), obj);
        }

        @Override // rx.internal.operators.OperatorReplay.h
        public j getInitialHead() {
            j jVar;
            long now = this.scheduler.now() - this.maxAgeInMillis;
            j jVar2 = get();
            j jVar3 = jVar2.get();
            while (true) {
                j jVar4 = jVar3;
                jVar = jVar2;
                jVar2 = jVar4;
                if (jVar2 == null) {
                    break;
                }
                Object obj = jVar2.value;
                Object leaveTransform = leaveTransform(obj);
                if (NotificationLite.isCompleted(leaveTransform) || NotificationLite.isError(leaveTransform) || ((Timestamped) obj).getTimestampMillis() > now) {
                    break;
                }
                jVar3 = jVar2.get();
            }
            return jVar;
        }

        @Override // rx.internal.operators.OperatorReplay.h
        public Object leaveTransform(Object obj) {
            return ((Timestamped) obj).getValue();
        }

        @Override // rx.internal.operators.OperatorReplay.h
        public void truncate() {
            j jVar;
            long now = this.scheduler.now() - this.maxAgeInMillis;
            j jVar2 = get();
            j jVar3 = jVar2.get();
            int i = 0;
            while (true) {
                j jVar4 = jVar3;
                jVar = jVar2;
                jVar2 = jVar4;
                if (jVar2 != null) {
                    int i2 = this.size;
                    if (i2 > this.limit) {
                        i++;
                        this.size = i2 - 1;
                        jVar3 = jVar2.get();
                    } else if (((Timestamped) jVar2.value).getTimestampMillis() > now) {
                        break;
                    } else {
                        i++;
                        this.size--;
                        jVar3 = jVar2.get();
                    }
                } else {
                    break;
                }
            }
            if (i != 0) {
                setFirst(jVar);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x003c, code lost:
            setFirst(r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x003f, code lost:
            return;
         */
        @Override // rx.internal.operators.OperatorReplay.h
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void truncateFinal() {
            /*
                r10 = this;
                rx.Scheduler r0 = r10.scheduler
                long r0 = r0.now()
                long r2 = r10.maxAgeInMillis
                long r0 = r0 - r2
                java.lang.Object r2 = r10.get()
                rx.internal.operators.OperatorReplay$j r2 = (rx.internal.operators.OperatorReplay.j) r2
                java.lang.Object r3 = r2.get()
                rx.internal.operators.OperatorReplay$j r3 = (rx.internal.operators.OperatorReplay.j) r3
                r4 = 0
            L16:
                r9 = r3
                r3 = r2
                r2 = r9
                if (r2 == 0) goto L3a
                int r5 = r10.size
                r6 = 1
                if (r5 <= r6) goto L3a
                java.lang.Object r5 = r2.value
                rx.schedulers.Timestamped r5 = (rx.schedulers.Timestamped) r5
                long r7 = r5.getTimestampMillis()
                int r5 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                if (r5 > 0) goto L3a
                int r4 = r4 + 1
                int r3 = r10.size
                int r3 = r3 - r6
                r10.size = r3
                java.lang.Object r3 = r2.get()
                rx.internal.operators.OperatorReplay$j r3 = (rx.internal.operators.OperatorReplay.j) r3
                goto L16
            L3a:
                if (r4 == 0) goto L3f
                r10.setFirst(r3)
            L3f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.m.truncateFinal():void");
        }
    }

    /* loaded from: classes13.dex */
    public static final class n<T> extends h<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        public final int limit;

        public n(int i) {
            this.limit = i;
        }

        @Override // rx.internal.operators.OperatorReplay.h
        public void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class o<T> extends ArrayList<Object> implements k<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        public volatile int size;

        public o(int i) {
            super(i);
        }

        @Override // rx.internal.operators.OperatorReplay.k
        public void complete() {
            add(NotificationLite.completed());
            this.size++;
        }

        @Override // rx.internal.operators.OperatorReplay.k
        public void error(Throwable th) {
            add(NotificationLite.error(th));
            this.size++;
        }

        @Override // rx.internal.operators.OperatorReplay.k
        public void next(T t) {
            add(NotificationLite.next(t));
            this.size++;
        }

        @Override // rx.internal.operators.OperatorReplay.k
        public void replay(i<T> iVar) {
            synchronized (iVar) {
                if (iVar.emitting) {
                    iVar.missed = true;
                    return;
                }
                iVar.emitting = true;
                while (!iVar.isUnsubscribed()) {
                    int i = this.size;
                    Integer num = (Integer) iVar.index();
                    int intValue = num != null ? num.intValue() : 0;
                    Subscriber<? super T> subscriber = iVar.child;
                    if (subscriber == null) {
                        return;
                    }
                    long j = iVar.get();
                    long j2 = 0;
                    while (j2 != j && intValue < i) {
                        Object obj = get(intValue);
                        try {
                            if (NotificationLite.accept(subscriber, obj) || iVar.isUnsubscribed()) {
                                return;
                            }
                            intValue++;
                            j2++;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            iVar.unsubscribe();
                            if (NotificationLite.isError(obj) || NotificationLite.isCompleted(obj)) {
                                return;
                            }
                            subscriber.onError(OnErrorThrowable.addValueAsLastCause(th, NotificationLite.getValue(obj)));
                            return;
                        }
                    }
                    if (j2 != 0) {
                        iVar.index = Integer.valueOf(intValue);
                        if (j != Long.MAX_VALUE) {
                            iVar.produced(j2);
                        }
                    }
                    synchronized (iVar) {
                        if (!iVar.missed) {
                            iVar.emitting = false;
                            return;
                        }
                        iVar.missed = false;
                    }
                }
            }
        }
    }

    public OperatorReplay(Observable.OnSubscribe<T> onSubscribe, Observable<? extends T> observable, AtomicReference<l<T>> atomicReference, Func0<? extends k<T>> func0) {
        super(onSubscribe);
        this.i = observable;
        this.j = atomicReference;
        this.k = func0;
    }

    public static <T> ConnectableObservable<T> c(Observable<? extends T> observable, Func0<? extends k<T>> func0) {
        AtomicReference atomicReference = new AtomicReference();
        return new OperatorReplay(new g(atomicReference, func0), observable, atomicReference, func0);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable) {
        return c(observable, l);
    }

    public static <T, U, R> Observable<R> multicastSelector(Func0<? extends ConnectableObservable<U>> func0, Func1<? super Observable<U>, ? extends Observable<R>> func1) {
        return Observable.unsafeCreate(new b(func0, func1));
    }

    public static <T> ConnectableObservable<T> observeOn(ConnectableObservable<T> connectableObservable, Scheduler scheduler) {
        return new d(new c(connectableObservable.observeOn(scheduler)), connectableObservable);
    }

    @Override // rx.observables.ConnectableObservable
    public void connect(Action1<? super Subscription> action1) {
        l<T> lVar;
        while (true) {
            lVar = this.j.get();
            if (lVar != null && !lVar.isUnsubscribed()) {
                break;
            }
            l<T> lVar2 = new l<>(this.k.call());
            lVar2.d();
            if (this.j.compareAndSet(lVar, lVar2)) {
                lVar = lVar2;
                break;
            }
        }
        boolean z = true;
        if (lVar.s.get() || !lVar.s.compareAndSet(false, true)) {
            z = false;
        }
        action1.call(lVar);
        if (z) {
            this.i.unsafeSubscribe(lVar);
        }
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        l<T> lVar = this.j.get();
        return lVar == null || lVar.isUnsubscribed();
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.j.lazySet(null);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable, int i2) {
        if (i2 == Integer.MAX_VALUE) {
            return create(observable);
        }
        return c(observable, new e(i2));
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return create(observable, j2, timeUnit, scheduler, Integer.MAX_VALUE);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable, long j2, TimeUnit timeUnit, Scheduler scheduler, int i2) {
        return c(observable, new f(i2, timeUnit.toMillis(j2), scheduler));
    }
}
