package rx.internal.operators;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.internal.operators.a;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public class OperatorTimeoutWithSelector<T, U, V> extends rx.internal.operators.a<T> {

    /* loaded from: classes13.dex */
    public class a implements a.InterfaceC0966a<T> {
        public final /* synthetic */ Func0 h;

        /* renamed from: rx.internal.operators.OperatorTimeoutWithSelector$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0958a extends Subscriber<U> {
            public final /* synthetic */ a.c l;
            public final /* synthetic */ Long m;

            public C0958a(a aVar, a.c cVar, Long l) {
                this.l = cVar;
                this.m = l;
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.l.b(this.m.longValue());
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                this.l.onError(th);
            }

            @Override // rx.Observer
            public void onNext(U u) {
                this.l.b(this.m.longValue());
            }
        }

        public a(Func0 func0) {
            this.h = func0;
        }

        @Override // rx.functions.Func3
        /* renamed from: a */
        public Subscription call(a.c<T> cVar, Long l, Scheduler.Worker worker) {
            Func0 func0 = this.h;
            if (func0 != null) {
                try {
                    return ((Observable) func0.call()).unsafeSubscribe(new C0958a(this, cVar, l));
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, cVar);
                    return Subscriptions.unsubscribed();
                }
            }
            return Subscriptions.unsubscribed();
        }
    }

    /* loaded from: classes13.dex */
    public class b implements a.b<T> {
        public final /* synthetic */ Func1 h;

        /* loaded from: classes13.dex */
        public class a extends Subscriber<V> {
            public final /* synthetic */ a.c l;
            public final /* synthetic */ Long m;

            public a(b bVar, a.c cVar, Long l) {
                this.l = cVar;
                this.m = l;
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.l.b(this.m.longValue());
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                this.l.onError(th);
            }

            @Override // rx.Observer
            public void onNext(V v) {
                this.l.b(this.m.longValue());
            }
        }

        public b(Func1 func1) {
            this.h = func1;
        }

        @Override // rx.functions.Func4
        /* renamed from: a */
        public Subscription call(a.c<T> cVar, Long l, T t, Scheduler.Worker worker) {
            try {
                return ((Observable) this.h.call(t)).unsafeSubscribe(new a(this, cVar, l));
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, cVar);
                return Subscriptions.unsubscribed();
            }
        }
    }

    public OperatorTimeoutWithSelector(Func0<? extends Observable<U>> func0, Func1<? super T, ? extends Observable<V>> func1, Observable<? extends T> observable) {
        super(new a(func0), new b(func1), observable, Schedulers.immediate());
    }

    @Override // rx.internal.operators.a
    public /* bridge */ /* synthetic */ Subscriber call(Subscriber subscriber) {
        return super.call(subscriber);
    }
}
