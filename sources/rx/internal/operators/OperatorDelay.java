package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
/* loaded from: classes13.dex */
public final class OperatorDelay<T> implements Observable.Operator<T, T> {
    public final long h;
    public final TimeUnit i;
    public final Scheduler j;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public boolean l;
        public final /* synthetic */ Scheduler.Worker m;
        public final /* synthetic */ Subscriber n;

        /* renamed from: rx.internal.operators.OperatorDelay$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0950a implements Action0 {
            public C0950a() {
            }

            @Override // rx.functions.Action0
            public void call() {
                a aVar = a.this;
                if (aVar.l) {
                    return;
                }
                aVar.l = true;
                aVar.n.onCompleted();
            }
        }

        /* loaded from: classes13.dex */
        public class b implements Action0 {
            public final /* synthetic */ Throwable h;

            public b(Throwable th) {
                this.h = th;
            }

            @Override // rx.functions.Action0
            public void call() {
                a aVar = a.this;
                if (aVar.l) {
                    return;
                }
                aVar.l = true;
                aVar.n.onError(this.h);
                a.this.m.unsubscribe();
            }
        }

        /* loaded from: classes13.dex */
        public class c implements Action0 {
            public final /* synthetic */ Object h;

            public c(Object obj) {
                this.h = obj;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // rx.functions.Action0
            public void call() {
                a aVar = a.this;
                if (aVar.l) {
                    return;
                }
                aVar.n.onNext(this.h);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, Scheduler.Worker worker, Subscriber subscriber2) {
            super(subscriber);
            this.m = worker;
            this.n = subscriber2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            Scheduler.Worker worker = this.m;
            C0950a c0950a = new C0950a();
            OperatorDelay operatorDelay = OperatorDelay.this;
            worker.schedule(c0950a, operatorDelay.h, operatorDelay.i);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.schedule(new b(th));
        }

        @Override // rx.Observer
        public void onNext(T t) {
            Scheduler.Worker worker = this.m;
            c cVar = new c(t);
            OperatorDelay operatorDelay = OperatorDelay.this;
            worker.schedule(cVar, operatorDelay.h, operatorDelay.i);
        }
    }

    public OperatorDelay(long j, TimeUnit timeUnit, Scheduler scheduler) {
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
        subscriber.add(createWorker);
        return new a(subscriber, createWorker, subscriber);
    }
}
