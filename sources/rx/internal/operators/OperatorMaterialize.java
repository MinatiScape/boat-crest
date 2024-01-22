package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Notification;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OperatorMaterialize<T> implements Observable.Operator<Notification<T>, T> {

    /* loaded from: classes13.dex */
    public class a implements Producer {
        public final /* synthetic */ c h;

        public a(OperatorMaterialize operatorMaterialize, c cVar) {
            this.h = cVar;
        }

        @Override // rx.Producer
        public void request(long j) {
            if (j > 0) {
                this.h.requestMore(j);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorMaterialize<Object> f15664a = new OperatorMaterialize<>();
    }

    /* loaded from: classes13.dex */
    public static class c<T> extends Subscriber<T> {
        public final Subscriber<? super Notification<T>> l;
        public volatile Notification<T> m;
        public boolean n;
        public boolean o;
        public final AtomicLong p = new AtomicLong();

        public c(Subscriber<? super Notification<T>> subscriber) {
            this.l = subscriber;
        }

        public final void b() {
            long j;
            AtomicLong atomicLong = this.p;
            do {
                j = atomicLong.get();
                if (j == Long.MAX_VALUE) {
                    return;
                }
            } while (!atomicLong.compareAndSet(j, j - 1));
        }

        public final void c() {
            synchronized (this) {
                if (this.n) {
                    this.o = true;
                    return;
                }
                AtomicLong atomicLong = this.p;
                while (!this.l.isUnsubscribed()) {
                    Notification<T> notification = this.m;
                    if (notification != null && atomicLong.get() > 0) {
                        this.m = null;
                        this.l.onNext(notification);
                        if (this.l.isUnsubscribed()) {
                            return;
                        }
                        this.l.onCompleted();
                        return;
                    }
                    synchronized (this) {
                        if (!this.o) {
                            this.n = false;
                            return;
                        }
                    }
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.m = Notification.createOnCompleted();
            c();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m = Notification.createOnError(th);
            RxJavaHooks.onError(th);
            c();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.onNext(Notification.createOnNext(t));
            b();
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(0L);
        }

        public void requestMore(long j) {
            BackpressureUtils.getAndAddRequest(this.p, j);
            request(j);
            c();
        }
    }

    public static <T> OperatorMaterialize<T> instance() {
        return (OperatorMaterialize<T>) b.f15664a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super Notification<T>> subscriber) {
        c cVar = new c(subscriber);
        subscriber.add(cVar);
        subscriber.setProducer(new a(this, cVar));
        return cVar;
    }
}
