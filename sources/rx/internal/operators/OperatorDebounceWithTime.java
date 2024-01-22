package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;
/* loaded from: classes13.dex */
public final class OperatorDebounceWithTime<T> implements Observable.Operator<T, T> {
    public final long h;
    public final TimeUnit i;
    public final Scheduler j;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public final b<T> l;
        public final Subscriber<?> m;
        public final /* synthetic */ SerialSubscription n;
        public final /* synthetic */ Scheduler.Worker o;
        public final /* synthetic */ SerializedSubscriber p;

        /* renamed from: rx.internal.operators.OperatorDebounceWithTime$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0949a implements Action0 {
            public final /* synthetic */ int h;

            public C0949a(int i) {
                this.h = i;
            }

            @Override // rx.functions.Action0
            public void call() {
                a aVar = a.this;
                aVar.l.b(this.h, aVar.p, aVar.m);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, SerialSubscription serialSubscription, Scheduler.Worker worker, SerializedSubscriber serializedSubscriber) {
            super(subscriber);
            this.n = serialSubscription;
            this.o = worker;
            this.p = serializedSubscriber;
            this.l = new b<>();
            this.m = this;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.c(this.p, this);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.p.onError(th);
            unsubscribe();
            this.l.a();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            int d = this.l.d(t);
            SerialSubscription serialSubscription = this.n;
            Scheduler.Worker worker = this.o;
            C0949a c0949a = new C0949a(d);
            OperatorDebounceWithTime operatorDebounceWithTime = OperatorDebounceWithTime.this;
            serialSubscription.set(worker.schedule(c0949a, operatorDebounceWithTime.h, operatorDebounceWithTime.i));
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> {

        /* renamed from: a  reason: collision with root package name */
        public int f15658a;
        public T b;
        public boolean c;
        public boolean d;
        public boolean e;

        public synchronized void a() {
            this.f15658a++;
            this.b = null;
            this.c = false;
        }

        public void b(int i, Subscriber<T> subscriber, Subscriber<?> subscriber2) {
            synchronized (this) {
                if (!this.e && this.c && i == this.f15658a) {
                    T t = this.b;
                    this.b = null;
                    this.c = false;
                    this.e = true;
                    try {
                        subscriber.onNext(t);
                        synchronized (this) {
                            if (!this.d) {
                                this.e = false;
                            } else {
                                subscriber.onCompleted();
                            }
                        }
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber2, t);
                    }
                }
            }
        }

        public void c(Subscriber<T> subscriber, Subscriber<?> subscriber2) {
            synchronized (this) {
                if (this.e) {
                    this.d = true;
                    return;
                }
                T t = this.b;
                boolean z = this.c;
                this.b = null;
                this.c = false;
                this.e = true;
                if (z) {
                    try {
                        subscriber.onNext(t);
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber2, t);
                        return;
                    }
                }
                subscriber.onCompleted();
            }
        }

        public synchronized int d(T t) {
            int i;
            this.b = t;
            this.c = true;
            i = this.f15658a + 1;
            this.f15658a = i;
            return i;
        }
    }

    public OperatorDebounceWithTime(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.h = j;
        this.i = timeUnit;
        this.j = scheduler;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.j.createWorker();
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        SerialSubscription serialSubscription = new SerialSubscription();
        serializedSubscriber.add(createWorker);
        serializedSubscriber.add(serialSubscription);
        return new a(subscriber, serialSubscription, createWorker, serializedSubscriber);
    }
}
