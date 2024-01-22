package rx.internal.operators;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Func1;
import rx.internal.operators.OnSubscribeFromIterable;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OnSubscribeFlattenIterable<T, R> implements Observable.OnSubscribe<R> {
    public final Observable<? extends T> h;
    public final Func1<? super T, ? extends Iterable<? extends R>> i;
    public final int j;

    /* loaded from: classes13.dex */
    public class a implements Producer {
        public final /* synthetic */ b h;

        public a(OnSubscribeFlattenIterable onSubscribeFlattenIterable, b bVar) {
            this.h = bVar;
        }

        @Override // rx.Producer
        public void request(long j) {
            this.h.requestMore(j);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T, R> extends Subscriber<T> {
        public final Subscriber<? super R> l;
        public final Func1<? super T, ? extends Iterable<? extends R>> m;
        public final long n;
        public final Queue<Object> o;
        public volatile boolean s;
        public long t;
        public Iterator<? extends R> u;
        public final AtomicReference<Throwable> p = new AtomicReference<>();
        public final AtomicInteger r = new AtomicInteger();
        public final AtomicLong q = new AtomicLong();

        public b(Subscriber<? super R> subscriber, Func1<? super T, ? extends Iterable<? extends R>> func1, int i) {
            this.l = subscriber;
            this.m = func1;
            if (i == Integer.MAX_VALUE) {
                this.n = Long.MAX_VALUE;
                this.o = new SpscLinkedArrayQueue(RxRingBuffer.SIZE);
            } else {
                this.n = i - (i >> 2);
                if (UnsafeAccess.isUnsafeAvailable()) {
                    this.o = new SpscArrayQueue(i);
                } else {
                    this.o = new SpscAtomicArrayQueue(i);
                }
            }
            request(i);
        }

        public boolean b(boolean z, boolean z2, Subscriber<?> subscriber, Queue<?> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                this.u = null;
                return true;
            } else if (z) {
                if (this.p.get() == null) {
                    if (z2) {
                        subscriber.onCompleted();
                        return true;
                    }
                    return false;
                }
                Throwable terminate = ExceptionsUtils.terminate(this.p);
                unsubscribe();
                queue.clear();
                this.u = null;
                subscriber.onError(terminate);
                return true;
            } else {
                return false;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0066  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x00d8 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:78:0x00cf A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:81:0x0010 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void c() {
            /*
                Method dump skipped, instructions count: 217
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeFlattenIterable.b.c():void");
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.s = true;
            c();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (ExceptionsUtils.addThrowable(this.p, th)) {
                this.s = true;
                c();
                return;
            }
            RxJavaHooks.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.o.offer(NotificationLite.next(t))) {
                unsubscribe();
                onError(new MissingBackpressureException());
                return;
            }
            c();
        }

        public void requestMore(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i > 0) {
                BackpressureUtils.getAndAddRequest(this.q, j);
                c();
            } else if (i >= 0) {
            } else {
                throw new IllegalStateException("n >= 0 required but it was " + j);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T, R> implements Observable.OnSubscribe<R> {
        public final T h;
        public final Func1<? super T, ? extends Iterable<? extends R>> i;

        public c(T t, Func1<? super T, ? extends Iterable<? extends R>> func1) {
            this.h = t;
            this.i = func1;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super R> subscriber) {
            try {
                Iterator<? extends R> it = this.i.call((T) this.h).iterator();
                if (!it.hasNext()) {
                    subscriber.onCompleted();
                } else {
                    subscriber.setProducer(new OnSubscribeFromIterable.a(subscriber, it));
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, subscriber, this.h);
            }
        }
    }

    public OnSubscribeFlattenIterable(Observable<? extends T> observable, Func1<? super T, ? extends Iterable<? extends R>> func1, int i) {
        this.h = observable;
        this.i = func1;
        this.j = i;
    }

    public static <T, R> Observable<R> createFrom(Observable<? extends T> observable, Func1<? super T, ? extends Iterable<? extends R>> func1, int i) {
        if (observable instanceof ScalarSynchronousObservable) {
            return Observable.unsafeCreate(new c(((ScalarSynchronousObservable) observable).get(), func1));
        }
        return Observable.unsafeCreate(new OnSubscribeFlattenIterable(observable, func1, i));
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        b bVar = new b(subscriber, this.i, this.j);
        subscriber.add(bVar);
        subscriber.setProducer(new a(this, bVar));
        this.h.unsafeSubscribe(bVar);
    }
}
