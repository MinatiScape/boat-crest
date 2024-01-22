package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.LinkedArrayList;
import rx.subscriptions.SerialSubscription;
/* loaded from: classes13.dex */
public final class CachedObservable<T> extends Observable<T> {

    /* loaded from: classes13.dex */
    public static final class a<T> extends LinkedArrayList implements Observer<T> {
        public static final c<?>[] q = new c[0];
        public final Observable<? extends T> m;
        public final SerialSubscription n;
        public volatile c<?>[] o;
        public boolean p;

        /* renamed from: rx.internal.operators.CachedObservable$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0933a extends Subscriber<T> {
            public C0933a() {
            }

            @Override // rx.Observer
            public void onCompleted() {
                a.this.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                a.this.onError(th);
            }

            @Override // rx.Observer
            public void onNext(T t) {
                a.this.onNext(t);
            }
        }

        public a(Observable<? extends T> observable, int i) {
            super(i);
            this.m = observable;
            this.o = q;
            this.n = new SerialSubscription();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void b(c<T> cVar) {
            synchronized (this.n) {
                c<?>[] cVarArr = this.o;
                int length = cVarArr.length;
                c<?>[] cVarArr2 = new c[length + 1];
                System.arraycopy(cVarArr, 0, cVarArr2, 0, length);
                cVarArr2[length] = cVar;
                this.o = cVarArr2;
            }
        }

        public void c() {
            C0933a c0933a = new C0933a();
            this.n.set(c0933a);
            this.m.unsafeSubscribe(c0933a);
        }

        public void d() {
            for (c<?> cVar : this.o) {
                cVar.replay();
            }
        }

        public void e(c<T> cVar) {
            synchronized (this.n) {
                c<?>[] cVarArr = this.o;
                int length = cVarArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (cVarArr[i2].equals(cVar)) {
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
                    this.o = q;
                    return;
                }
                c<?>[] cVarArr2 = new c[length - 1];
                System.arraycopy(cVarArr, 0, cVarArr2, 0, i);
                System.arraycopy(cVarArr, i + 1, cVarArr2, i, (length - i) - 1);
                this.o = cVarArr2;
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.p) {
                return;
            }
            this.p = true;
            add(NotificationLite.completed());
            this.n.unsubscribe();
            d();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.p) {
                return;
            }
            this.p = true;
            add(NotificationLite.error(th));
            this.n.unsubscribe();
            d();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.p) {
                return;
            }
            add(NotificationLite.next(t));
            d();
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends AtomicBoolean implements Observable.OnSubscribe<T> {
        private static final long serialVersionUID = -2817751667698696782L;
        public final a<T> state;

        public b(a<T> aVar) {
            this.state = aVar;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public void call(Subscriber<? super T> subscriber) {
            c<T> cVar = new c<>(subscriber, this.state);
            this.state.b(cVar);
            subscriber.add(cVar);
            subscriber.setProducer(cVar);
            if (get() || !compareAndSet(false, true)) {
                return;
            }
            this.state.c();
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T> extends AtomicLong implements Producer, Subscription {
        private static final long serialVersionUID = -2557562030197141021L;
        public final Subscriber<? super T> child;
        public Object[] currentBuffer;
        public int currentIndexInBuffer;
        public boolean emitting;
        public int index;
        public boolean missed;
        public final a<T> state;

        public c(Subscriber<? super T> subscriber, a<T> aVar) {
            this.child = subscriber;
            this.state = aVar;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() < 0;
        }

        public long produced(long j) {
            return addAndGet(-j);
        }

        public void replay() {
            boolean z;
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                try {
                    Subscriber<? super T> subscriber = this.child;
                    while (true) {
                        long j = get();
                        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                        if (i < 0) {
                            return;
                        }
                        int size = this.state.size();
                        try {
                            if (size != 0) {
                                Object[] objArr = this.currentBuffer;
                                if (objArr == null) {
                                    objArr = this.state.head();
                                    this.currentBuffer = objArr;
                                }
                                int length = objArr.length - 1;
                                int i2 = this.index;
                                int i3 = this.currentIndexInBuffer;
                                if (i == 0) {
                                    Object obj = objArr[i3];
                                    if (NotificationLite.isCompleted(obj)) {
                                        subscriber.onCompleted();
                                        unsubscribe();
                                        return;
                                    } else if (NotificationLite.isError(obj)) {
                                        subscriber.onError(NotificationLite.getError(obj));
                                        unsubscribe();
                                        return;
                                    }
                                } else if (i > 0) {
                                    int i4 = 0;
                                    while (i2 < size && j > 0) {
                                        if (subscriber.isUnsubscribed()) {
                                            return;
                                        }
                                        if (i3 == length) {
                                            objArr = (Object[]) objArr[length];
                                            i3 = 0;
                                        }
                                        Object obj2 = objArr[i3];
                                        try {
                                            if (NotificationLite.accept(subscriber, obj2)) {
                                                try {
                                                    unsubscribe();
                                                    return;
                                                } catch (Throwable th) {
                                                    th = th;
                                                    z = true;
                                                    try {
                                                        Exceptions.throwIfFatal(th);
                                                        unsubscribe();
                                                        if (NotificationLite.isError(obj2) || NotificationLite.isCompleted(obj2)) {
                                                            return;
                                                        }
                                                        subscriber.onError(OnErrorThrowable.addValueAsLastCause(th, NotificationLite.getValue(obj2)));
                                                        return;
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        if (!z) {
                                                            synchronized (this) {
                                                                this.emitting = false;
                                                            }
                                                        }
                                                        throw th;
                                                    }
                                                }
                                            }
                                            i3++;
                                            i2++;
                                            j--;
                                            i4++;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            z = false;
                                        }
                                    }
                                    if (subscriber.isUnsubscribed()) {
                                        return;
                                    }
                                    this.index = i2;
                                    this.currentIndexInBuffer = i3;
                                    this.currentBuffer = objArr;
                                    produced(i4);
                                }
                            }
                            try {
                                synchronized (this) {
                                    try {
                                        if (!this.missed) {
                                            this.emitting = false;
                                            return;
                                        }
                                        this.missed = false;
                                    } catch (Throwable th4) {
                                        th = th4;
                                        throw th;
                                    }
                                }
                            } catch (Throwable th5) {
                                th = th5;
                            }
                        } catch (Throwable th6) {
                            z = true;
                            th = th6;
                        }
                    }
                } catch (Throwable th7) {
                    th = th7;
                    z = false;
                }
            }
        }

        @Override // rx.Producer
        public void request(long j) {
            long j2;
            long j3;
            do {
                j2 = get();
                if (j2 < 0) {
                    return;
                }
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!compareAndSet(j2, j3));
            replay();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (get() < 0 || getAndSet(-1L) < 0) {
                return;
            }
            this.state.e(this);
        }
    }

    public CachedObservable(Observable.OnSubscribe<T> onSubscribe, a<T> aVar) {
        super(onSubscribe);
    }

    public static <T> CachedObservable<T> from(Observable<? extends T> observable) {
        return from(observable, 16);
    }

    public static <T> CachedObservable<T> from(Observable<? extends T> observable, int i) {
        if (i >= 1) {
            a aVar = new a(observable, i);
            return new CachedObservable<>(new b(aVar), aVar);
        }
        throw new IllegalArgumentException("capacityHint > 0 required");
    }
}
