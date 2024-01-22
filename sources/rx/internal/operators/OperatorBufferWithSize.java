package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.MissingBackpressureException;
/* loaded from: classes13.dex */
public final class OperatorBufferWithSize<T> implements Observable.Operator<List<T>, T> {
    public final int h;
    public final int i;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> {
        public final Subscriber<? super List<T>> l;
        public final int m;
        public List<T> n;

        /* renamed from: rx.internal.operators.OperatorBufferWithSize$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0945a implements Producer {
            public C0945a() {
            }

            @Override // rx.Producer
            public void request(long j) {
                int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (i < 0) {
                    throw new IllegalArgumentException("n >= required but it was " + j);
                } else if (i != 0) {
                    a.this.request(BackpressureUtils.multiplyCap(j, a.this.m));
                }
            }
        }

        public a(Subscriber<? super List<T>> subscriber, int i) {
            this.l = subscriber;
            this.m = i;
            request(0L);
        }

        public Producer c() {
            return new C0945a();
        }

        @Override // rx.Observer
        public void onCompleted() {
            List<T> list = this.n;
            if (list != null) {
                this.l.onNext(list);
            }
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.n = null;
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            List list = this.n;
            if (list == null) {
                list = new ArrayList(this.m);
                this.n = list;
            }
            list.add(t);
            if (list.size() == this.m) {
                this.n = null;
                this.l.onNext(list);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends Subscriber<T> {
        public final Subscriber<? super List<T>> l;
        public final int m;
        public final int n;
        public long o;
        public final ArrayDeque<List<T>> p = new ArrayDeque<>();
        public final AtomicLong q = new AtomicLong();
        public long r;

        /* loaded from: classes13.dex */
        public final class a extends AtomicBoolean implements Producer {
            private static final long serialVersionUID = -4015894850868853147L;

            public a() {
            }

            @Override // rx.Producer
            public void request(long j) {
                b bVar = b.this;
                if (!BackpressureUtils.postCompleteRequest(bVar.q, j, bVar.p, bVar.l) || j == 0) {
                    return;
                }
                if (get() || !compareAndSet(false, true)) {
                    bVar.request(BackpressureUtils.multiplyCap(bVar.n, j));
                } else {
                    bVar.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(bVar.n, j - 1), bVar.m));
                }
            }
        }

        public b(Subscriber<? super List<T>> subscriber, int i, int i2) {
            this.l = subscriber;
            this.m = i;
            this.n = i2;
            request(0L);
        }

        public Producer d() {
            return new a();
        }

        @Override // rx.Observer
        public void onCompleted() {
            long j = this.r;
            if (j != 0) {
                if (j > this.q.get()) {
                    Subscriber<? super List<T>> subscriber = this.l;
                    subscriber.onError(new MissingBackpressureException("More produced than requested? " + j));
                    return;
                }
                this.q.addAndGet(-j);
            }
            BackpressureUtils.postCompleteDone(this.q, this.p, this.l);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.p.clear();
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            long j = this.o;
            if (j == 0) {
                this.p.offer(new ArrayList(this.m));
            }
            long j2 = j + 1;
            if (j2 == this.n) {
                this.o = 0L;
            } else {
                this.o = j2;
            }
            Iterator<List<T>> it = this.p.iterator();
            while (it.hasNext()) {
                it.next().add(t);
            }
            List<T> peek = this.p.peek();
            if (peek == null || peek.size() != this.m) {
                return;
            }
            this.p.poll();
            this.r++;
            this.l.onNext(peek);
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T> extends Subscriber<T> {
        public final Subscriber<? super List<T>> l;
        public final int m;
        public final int n;
        public long o;
        public List<T> p;

        /* loaded from: classes13.dex */
        public final class a extends AtomicBoolean implements Producer {
            private static final long serialVersionUID = 3428177408082367154L;

            public a() {
            }

            @Override // rx.Producer
            public void request(long j) {
                int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (i < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + j);
                } else if (i != 0) {
                    c cVar = c.this;
                    if (get() || !compareAndSet(false, true)) {
                        cVar.request(BackpressureUtils.multiplyCap(j, cVar.n));
                    } else {
                        cVar.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(j, cVar.m), BackpressureUtils.multiplyCap(cVar.n - cVar.m, j - 1)));
                    }
                }
            }
        }

        public c(Subscriber<? super List<T>> subscriber, int i, int i2) {
            this.l = subscriber;
            this.m = i;
            this.n = i2;
            request(0L);
        }

        public Producer d() {
            return new a();
        }

        @Override // rx.Observer
        public void onCompleted() {
            List<T> list = this.p;
            if (list != null) {
                this.p = null;
                this.l.onNext(list);
            }
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.p = null;
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            long j = this.o;
            List list = this.p;
            if (j == 0) {
                list = new ArrayList(this.m);
                this.p = list;
            }
            long j2 = j + 1;
            if (j2 == this.n) {
                this.o = 0L;
            } else {
                this.o = j2;
            }
            if (list != null) {
                list.add(t);
                if (list.size() == this.m) {
                    this.p = null;
                    this.l.onNext(list);
                }
            }
        }
    }

    public OperatorBufferWithSize(int i, int i2) {
        if (i <= 0) {
            throw new IllegalArgumentException("count must be greater than 0");
        }
        if (i2 > 0) {
            this.h = i;
            this.i = i2;
            return;
        }
        throw new IllegalArgumentException("skip must be greater than 0");
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> subscriber) {
        int i = this.i;
        int i2 = this.h;
        if (i == i2) {
            a aVar = new a(subscriber, i2);
            subscriber.add(aVar);
            subscriber.setProducer(aVar.c());
            return aVar;
        } else if (i > i2) {
            c cVar = new c(subscriber, i2, i);
            subscriber.add(cVar);
            subscriber.setProducer(cVar.d());
            return cVar;
        } else {
            b bVar = new b(subscriber, i2, i);
            subscriber.add(bVar);
            subscriber.setProducer(bVar.d());
            return bVar;
        }
    }
}
