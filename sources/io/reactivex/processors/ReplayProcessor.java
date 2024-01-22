package io.reactivex.processors;

import io.reactivex.Scheduler;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class ReplayProcessor<T> extends FlowableProcessor<T> {
    public static final Object[] l = new Object[0];
    public static final c[] m = new c[0];
    public static final c[] n = new c[0];
    public final b<T> i;
    public boolean j;
    public final AtomicReference<c<T>[]> k = new AtomicReference<>(m);

    /* loaded from: classes12.dex */
    public static final class a<T> extends AtomicReference<a<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        public final T value;

        public a(T t) {
            this.value = t;
        }
    }

    /* loaded from: classes12.dex */
    public interface b<T> {
        void a(c<T> cVar);

        void complete();

        void error(Throwable th);

        Throwable getError();

        @Nullable
        T getValue();

        T[] getValues(T[] tArr);

        boolean isDone();

        void next(T t);

        int size();

        void trimHead();
    }

    /* loaded from: classes12.dex */
    public static final class c<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 466549804534799122L;
        public volatile boolean cancelled;
        public final Subscriber<? super T> downstream;
        public long emitted;
        public Object index;
        public final AtomicLong requested = new AtomicLong();
        public final ReplayProcessor<T> state;

        public c(Subscriber<? super T> subscriber, ReplayProcessor<T> replayProcessor) {
            this.downstream = subscriber;
            this.state = replayProcessor;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.state.f(this);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                this.state.i.a(this);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<T> implements b<T> {

        /* renamed from: a  reason: collision with root package name */
        public final int f13936a;
        public final long b;
        public final TimeUnit c;
        public final Scheduler d;
        public int e;
        public volatile f<T> f;
        public f<T> g;
        public Throwable h;
        public volatile boolean i;

        public d(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
            this.f13936a = ObjectHelper.verifyPositive(i, "maxSize");
            this.b = ObjectHelper.verifyPositive(j, "maxAge");
            this.c = (TimeUnit) ObjectHelper.requireNonNull(timeUnit, "unit is null");
            this.d = (Scheduler) ObjectHelper.requireNonNull(scheduler, "scheduler is null");
            f<T> fVar = new f<>(null, 0L);
            this.g = fVar;
            this.f = fVar;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void a(c<T> cVar) {
            int i;
            if (cVar.getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super T> subscriber = cVar.downstream;
            f<T> fVar = (f) cVar.index;
            if (fVar == null) {
                fVar = b();
            }
            long j = cVar.emitted;
            int i2 = 1;
            do {
                long j2 = cVar.requested.get();
                while (true) {
                    i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
                    if (i == 0) {
                        break;
                    } else if (cVar.cancelled) {
                        cVar.index = null;
                        return;
                    } else {
                        boolean z = this.i;
                        f<T> fVar2 = fVar.get();
                        boolean z2 = fVar2 == null;
                        if (z && z2) {
                            cVar.index = null;
                            cVar.cancelled = true;
                            Throwable th = this.h;
                            if (th == null) {
                                subscriber.onComplete();
                                return;
                            } else {
                                subscriber.onError(th);
                                return;
                            }
                        } else if (z2) {
                            break;
                        } else {
                            subscriber.onNext((T) fVar2.value);
                            j++;
                            fVar = fVar2;
                        }
                    }
                }
                if (i == 0) {
                    if (cVar.cancelled) {
                        cVar.index = null;
                        return;
                    } else if (this.i && fVar.get() == null) {
                        cVar.index = null;
                        cVar.cancelled = true;
                        Throwable th2 = this.h;
                        if (th2 == null) {
                            subscriber.onComplete();
                            return;
                        } else {
                            subscriber.onError(th2);
                            return;
                        }
                    }
                }
                cVar.index = fVar;
                cVar.emitted = j;
                i2 = cVar.addAndGet(-i2);
            } while (i2 != 0);
        }

        public f<T> b() {
            f<T> fVar;
            f<T> fVar2 = this.f;
            long now = this.d.now(this.c) - this.b;
            f<T> fVar3 = fVar2.get();
            while (true) {
                f<T> fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (fVar2 == null || fVar2.time > now) {
                    break;
                }
                fVar3 = fVar2.get();
            }
            return fVar;
        }

        public int c(f<T> fVar) {
            int i = 0;
            while (i != Integer.MAX_VALUE && (fVar = fVar.get()) != null) {
                i++;
            }
            return i;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void complete() {
            e();
            this.i = true;
        }

        public void d() {
            int i = this.e;
            if (i > this.f13936a) {
                this.e = i - 1;
                this.f = this.f.get();
            }
            long now = this.d.now(this.c) - this.b;
            f<T> fVar = this.f;
            while (this.e > 1) {
                f<T> fVar2 = fVar.get();
                if (fVar2 == null) {
                    this.f = fVar;
                    return;
                } else if (fVar2.time > now) {
                    this.f = fVar;
                    return;
                } else {
                    this.e--;
                    fVar = fVar2;
                }
            }
            this.f = fVar;
        }

        public void e() {
            long now = this.d.now(this.c) - this.b;
            f<T> fVar = this.f;
            while (true) {
                f<T> fVar2 = fVar.get();
                if (fVar2 == null) {
                    if (fVar.value != null) {
                        this.f = new f<>(null, 0L);
                        return;
                    } else {
                        this.f = fVar;
                        return;
                    }
                } else if (fVar2.time > now) {
                    if (fVar.value != null) {
                        f<T> fVar3 = new f<>(null, 0L);
                        fVar3.lazySet(fVar.get());
                        this.f = fVar3;
                        return;
                    }
                    this.f = fVar;
                    return;
                } else {
                    fVar = fVar2;
                }
            }
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void error(Throwable th) {
            e();
            this.h = th;
            this.i = true;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public Throwable getError() {
            return this.h;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        @Nullable
        public T getValue() {
            f<T> fVar = this.f;
            while (true) {
                f<T> fVar2 = fVar.get();
                if (fVar2 == null) {
                    break;
                }
                fVar = fVar2;
            }
            if (fVar.time < this.d.now(this.c) - this.b) {
                return null;
            }
            return fVar.value;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public T[] getValues(T[] tArr) {
            f<T> b = b();
            int c = c(b);
            if (c == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
            } else {
                if (tArr.length < c) {
                    tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), c));
                }
                for (int i = 0; i != c; i++) {
                    b = b.get();
                    tArr[i] = b.value;
                }
                if (tArr.length > c) {
                    tArr[c] = null;
                }
            }
            return tArr;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public boolean isDone() {
            return this.i;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void next(T t) {
            f<T> fVar = new f<>(t, this.d.now(this.c));
            f<T> fVar2 = this.g;
            this.g = fVar;
            this.e++;
            fVar2.set(fVar);
            d();
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public int size() {
            return c(b());
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void trimHead() {
            if (this.f.value != null) {
                f<T> fVar = new f<>(null, 0L);
                fVar.lazySet(this.f.get());
                this.f = fVar;
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class e<T> implements b<T> {

        /* renamed from: a  reason: collision with root package name */
        public final int f13937a;
        public int b;
        public volatile a<T> c;
        public a<T> d;
        public Throwable e;
        public volatile boolean f;

        public e(int i) {
            this.f13937a = ObjectHelper.verifyPositive(i, "maxSize");
            a<T> aVar = new a<>(null);
            this.d = aVar;
            this.c = aVar;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void a(c<T> cVar) {
            int i;
            if (cVar.getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super T> subscriber = cVar.downstream;
            a<T> aVar = (a) cVar.index;
            if (aVar == null) {
                aVar = this.c;
            }
            long j = cVar.emitted;
            int i2 = 1;
            do {
                long j2 = cVar.requested.get();
                while (true) {
                    i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
                    if (i == 0) {
                        break;
                    } else if (cVar.cancelled) {
                        cVar.index = null;
                        return;
                    } else {
                        boolean z = this.f;
                        a<T> aVar2 = aVar.get();
                        boolean z2 = aVar2 == null;
                        if (z && z2) {
                            cVar.index = null;
                            cVar.cancelled = true;
                            Throwable th = this.e;
                            if (th == null) {
                                subscriber.onComplete();
                                return;
                            } else {
                                subscriber.onError(th);
                                return;
                            }
                        } else if (z2) {
                            break;
                        } else {
                            subscriber.onNext((T) aVar2.value);
                            j++;
                            aVar = aVar2;
                        }
                    }
                }
                if (i == 0) {
                    if (cVar.cancelled) {
                        cVar.index = null;
                        return;
                    } else if (this.f && aVar.get() == null) {
                        cVar.index = null;
                        cVar.cancelled = true;
                        Throwable th2 = this.e;
                        if (th2 == null) {
                            subscriber.onComplete();
                            return;
                        } else {
                            subscriber.onError(th2);
                            return;
                        }
                    }
                }
                cVar.index = aVar;
                cVar.emitted = j;
                i2 = cVar.addAndGet(-i2);
            } while (i2 != 0);
        }

        public void b() {
            int i = this.b;
            if (i > this.f13937a) {
                this.b = i - 1;
                this.c = this.c.get();
            }
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void complete() {
            trimHead();
            this.f = true;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void error(Throwable th) {
            this.e = th;
            trimHead();
            this.f = true;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public Throwable getError() {
            return this.e;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public T getValue() {
            a<T> aVar = this.c;
            while (true) {
                a<T> aVar2 = aVar.get();
                if (aVar2 == null) {
                    return aVar.value;
                }
                aVar = aVar2;
            }
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public T[] getValues(T[] tArr) {
            a<T> aVar = this.c;
            a<T> aVar2 = aVar;
            int i = 0;
            while (true) {
                aVar2 = aVar2.get();
                if (aVar2 == null) {
                    break;
                }
                i++;
            }
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            for (int i2 = 0; i2 < i; i2++) {
                aVar = aVar.get();
                tArr[i2] = aVar.value;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public boolean isDone() {
            return this.f;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void next(T t) {
            a<T> aVar = new a<>(t);
            a<T> aVar2 = this.d;
            this.d = aVar;
            this.b++;
            aVar2.set(aVar);
            b();
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public int size() {
            a<T> aVar = this.c;
            int i = 0;
            while (i != Integer.MAX_VALUE && (aVar = aVar.get()) != null) {
                i++;
            }
            return i;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void trimHead() {
            if (this.c.value != null) {
                a<T> aVar = new a<>(null);
                aVar.lazySet(this.c.get());
                this.c = aVar;
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class f<T> extends AtomicReference<f<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        public final long time;
        public final T value;

        public f(T t, long j) {
            this.value = t;
            this.time = j;
        }
    }

    /* loaded from: classes12.dex */
    public static final class g<T> implements b<T> {

        /* renamed from: a  reason: collision with root package name */
        public final List<T> f13938a;
        public Throwable b;
        public volatile boolean c;
        public volatile int d;

        public g(int i) {
            this.f13938a = new ArrayList(ObjectHelper.verifyPositive(i, "capacityHint"));
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x005b, code lost:
            if (r9 != 0) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x005f, code lost:
            if (r14.cancelled == false) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0061, code lost:
            r14.index = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0063, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0064, code lost:
            r7 = r13.c;
            r8 = r13.d;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0068, code lost:
            if (r7 == false) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x006a, code lost:
            if (r3 != r8) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x006c, code lost:
            r14.index = null;
            r14.cancelled = true;
            r14 = r13.b;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0072, code lost:
            if (r14 != null) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0074, code lost:
            r1.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0078, code lost:
            r1.onError(r14);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x007b, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x007c, code lost:
            r14.index = java.lang.Integer.valueOf(r3);
            r14.emitted = r4;
            r6 = r14.addAndGet(-r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:?, code lost:
            return;
         */
        @Override // io.reactivex.processors.ReplayProcessor.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(io.reactivex.processors.ReplayProcessor.c<T> r14) {
            /*
                r13 = this;
                int r0 = r14.getAndIncrement()
                if (r0 == 0) goto L7
                return
            L7:
                java.util.List<T> r0 = r13.f13938a
                org.reactivestreams.Subscriber<? super T> r1 = r14.downstream
                java.lang.Object r2 = r14.index
                java.lang.Integer r2 = (java.lang.Integer) r2
                r3 = 0
                if (r2 == 0) goto L17
                int r3 = r2.intValue()
                goto L1d
            L17:
                java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
                r14.index = r2
            L1d:
                long r4 = r14.emitted
                r2 = 1
                r6 = r2
            L21:
                java.util.concurrent.atomic.AtomicLong r7 = r14.requested
                long r7 = r7.get()
            L27:
                int r9 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                r10 = 0
                if (r9 == 0) goto L5b
                boolean r11 = r14.cancelled
                if (r11 == 0) goto L33
                r14.index = r10
                return
            L33:
                boolean r11 = r13.c
                int r12 = r13.d
                if (r11 == 0) goto L4b
                if (r3 != r12) goto L4b
                r14.index = r10
                r14.cancelled = r2
                java.lang.Throwable r14 = r13.b
                if (r14 != 0) goto L47
                r1.onComplete()
                goto L4a
            L47:
                r1.onError(r14)
            L4a:
                return
            L4b:
                if (r3 != r12) goto L4e
                goto L5b
            L4e:
                java.lang.Object r9 = r0.get(r3)
                r1.onNext(r9)
                int r3 = r3 + 1
                r9 = 1
                long r4 = r4 + r9
                goto L27
            L5b:
                if (r9 != 0) goto L7c
                boolean r7 = r14.cancelled
                if (r7 == 0) goto L64
                r14.index = r10
                return
            L64:
                boolean r7 = r13.c
                int r8 = r13.d
                if (r7 == 0) goto L7c
                if (r3 != r8) goto L7c
                r14.index = r10
                r14.cancelled = r2
                java.lang.Throwable r14 = r13.b
                if (r14 != 0) goto L78
                r1.onComplete()
                goto L7b
            L78:
                r1.onError(r14)
            L7b:
                return
            L7c:
                java.lang.Integer r7 = java.lang.Integer.valueOf(r3)
                r14.index = r7
                r14.emitted = r4
                int r6 = -r6
                int r6 = r14.addAndGet(r6)
                if (r6 != 0) goto L21
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.processors.ReplayProcessor.g.a(io.reactivex.processors.ReplayProcessor$c):void");
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void complete() {
            this.c = true;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void error(Throwable th) {
            this.b = th;
            this.c = true;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public Throwable getError() {
            return this.b;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        @Nullable
        public T getValue() {
            int i = this.d;
            if (i == 0) {
                return null;
            }
            return this.f13938a.get(i - 1);
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public T[] getValues(T[] tArr) {
            int i = this.d;
            if (i == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
                return tArr;
            }
            List<T> list = this.f13938a;
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            for (int i2 = 0; i2 < i; i2++) {
                tArr[i2] = list.get(i2);
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public boolean isDone() {
            return this.c;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void next(T t) {
            this.f13938a.add(t);
            this.d++;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public int size() {
            return this.d;
        }

        @Override // io.reactivex.processors.ReplayProcessor.b
        public void trimHead() {
        }
    }

    public ReplayProcessor(b<T> bVar) {
        this.i = bVar;
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplayProcessor<T> create() {
        return new ReplayProcessor<>(new g(16));
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplayProcessor<T> createWithSize(int i) {
        return new ReplayProcessor<>(new e(i));
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplayProcessor<T> createWithTime(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return new ReplayProcessor<>(new d(Integer.MAX_VALUE, j, timeUnit, scheduler));
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplayProcessor<T> createWithTimeAndSize(long j, TimeUnit timeUnit, Scheduler scheduler, int i) {
        return new ReplayProcessor<>(new d(i, j, timeUnit, scheduler));
    }

    public void cleanupBuffer() {
        this.i.trimHead();
    }

    public boolean e(c<T> cVar) {
        c<T>[] cVarArr;
        c<T>[] cVarArr2;
        do {
            cVarArr = this.k.get();
            if (cVarArr == n) {
                return false;
            }
            int length = cVarArr.length;
            cVarArr2 = new c[length + 1];
            System.arraycopy(cVarArr, 0, cVarArr2, 0, length);
            cVarArr2[length] = cVar;
        } while (!this.k.compareAndSet(cVarArr, cVarArr2));
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void f(c<T> cVar) {
        c<T>[] cVarArr;
        c[] cVarArr2;
        do {
            cVarArr = this.k.get();
            if (cVarArr == n || cVarArr == m) {
                return;
            }
            int length = cVarArr.length;
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (cVarArr[i2] == cVar) {
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
                cVarArr2 = m;
            } else {
                c[] cVarArr3 = new c[length - 1];
                System.arraycopy(cVarArr, 0, cVarArr3, 0, i);
                System.arraycopy(cVarArr, i + 1, cVarArr3, i, (length - i) - 1);
                cVarArr2 = cVarArr3;
            }
        } while (!this.k.compareAndSet(cVarArr, cVarArr2));
    }

    @Override // io.reactivex.processors.FlowableProcessor
    @Nullable
    public Throwable getThrowable() {
        b<T> bVar = this.i;
        if (bVar.isDone()) {
            return bVar.getError();
        }
        return null;
    }

    public T getValue() {
        return this.i.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] getValues() {
        Object[] objArr = l;
        Object[] values = getValues(objArr);
        return values == objArr ? new Object[0] : values;
    }

    @Override // io.reactivex.processors.FlowableProcessor
    public boolean hasComplete() {
        b<T> bVar = this.i;
        return bVar.isDone() && bVar.getError() == null;
    }

    @Override // io.reactivex.processors.FlowableProcessor
    public boolean hasSubscribers() {
        return this.k.get().length != 0;
    }

    @Override // io.reactivex.processors.FlowableProcessor
    public boolean hasThrowable() {
        b<T> bVar = this.i;
        return bVar.isDone() && bVar.getError() != null;
    }

    public boolean hasValue() {
        return this.i.size() != 0;
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.j) {
            return;
        }
        this.j = true;
        b<T> bVar = this.i;
        bVar.complete();
        for (c<T> cVar : this.k.getAndSet(n)) {
            bVar.a(cVar);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.j) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.j = true;
        b<T> bVar = this.i;
        bVar.error(th);
        for (c<T> cVar : this.k.getAndSet(n)) {
            bVar.a(cVar);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.j) {
            return;
        }
        b<T> bVar = this.i;
        bVar.next(t);
        for (c<T> cVar : this.k.get()) {
            bVar.a(cVar);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (this.j) {
            subscription.cancel();
        } else {
            subscription.request(Long.MAX_VALUE);
        }
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        c<T> cVar = new c<>(subscriber, this);
        subscriber.onSubscribe(cVar);
        if (e(cVar) && cVar.cancelled) {
            f(cVar);
        } else {
            this.i.a(cVar);
        }
    }

    @CheckReturnValue
    @NonNull
    public static <T> ReplayProcessor<T> create(int i) {
        return new ReplayProcessor<>(new g(i));
    }

    public T[] getValues(T[] tArr) {
        return this.i.getValues(tArr);
    }
}
