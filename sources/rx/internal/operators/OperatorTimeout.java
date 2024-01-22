package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.operators.a;
/* loaded from: classes13.dex */
public final class OperatorTimeout<T> extends rx.internal.operators.a<T> {

    /* loaded from: classes13.dex */
    public class a implements a.InterfaceC0966a<T> {
        public final /* synthetic */ long h;
        public final /* synthetic */ TimeUnit i;

        /* renamed from: rx.internal.operators.OperatorTimeout$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0957a implements Action0 {
            public final /* synthetic */ a.c h;
            public final /* synthetic */ Long i;

            public C0957a(a aVar, a.c cVar, Long l) {
                this.h = cVar;
                this.i = l;
            }

            @Override // rx.functions.Action0
            public void call() {
                this.h.b(this.i.longValue());
            }
        }

        public a(long j, TimeUnit timeUnit) {
            this.h = j;
            this.i = timeUnit;
        }

        @Override // rx.functions.Func3
        /* renamed from: a */
        public Subscription call(a.c<T> cVar, Long l, Scheduler.Worker worker) {
            return worker.schedule(new C0957a(this, cVar, l), this.h, this.i);
        }
    }

    /* loaded from: classes13.dex */
    public class b implements a.b<T> {
        public final /* synthetic */ long h;
        public final /* synthetic */ TimeUnit i;

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public final /* synthetic */ a.c h;
            public final /* synthetic */ Long i;

            public a(b bVar, a.c cVar, Long l) {
                this.h = cVar;
                this.i = l;
            }

            @Override // rx.functions.Action0
            public void call() {
                this.h.b(this.i.longValue());
            }
        }

        public b(long j, TimeUnit timeUnit) {
            this.h = j;
            this.i = timeUnit;
        }

        @Override // rx.functions.Func4
        /* renamed from: a */
        public Subscription call(a.c<T> cVar, Long l, T t, Scheduler.Worker worker) {
            return worker.schedule(new a(this, cVar, l), this.h, this.i);
        }
    }

    public OperatorTimeout(long j, TimeUnit timeUnit, Observable<? extends T> observable, Scheduler scheduler) {
        super(new a(j, timeUnit), new b(j, timeUnit), observable, scheduler);
    }

    @Override // rx.internal.operators.a
    public /* bridge */ /* synthetic */ Subscriber call(Subscriber subscriber) {
        return super.call(subscriber);
    }
}
