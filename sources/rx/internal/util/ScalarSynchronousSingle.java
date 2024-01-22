package rx.internal.util;

import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.schedulers.EventLoopsScheduler;
/* loaded from: classes13.dex */
public final class ScalarSynchronousSingle<T> extends Single<T> {
    public final T b;

    /* loaded from: classes13.dex */
    public class a implements Single.OnSubscribe<T> {
        public final /* synthetic */ Object h;

        public a(Object obj) {
            this.h = obj;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            singleSubscriber.onSuccess((Object) this.h);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public class b<R> implements Single.OnSubscribe<R> {
        public final /* synthetic */ Func1 h;

        /* loaded from: classes13.dex */
        public class a extends SingleSubscriber<R> {
            public final /* synthetic */ SingleSubscriber i;

            public a(b bVar, SingleSubscriber singleSubscriber) {
                this.i = singleSubscriber;
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                this.i.onError(th);
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(R r) {
                this.i.onSuccess(r);
            }
        }

        public b(Func1 func1) {
            this.h = func1;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SingleSubscriber<? super R> singleSubscriber) {
            Single single = (Single) this.h.call(ScalarSynchronousSingle.this.b);
            if (single instanceof ScalarSynchronousSingle) {
                singleSubscriber.onSuccess((T) ((ScalarSynchronousSingle) single).b);
                return;
            }
            a aVar = new a(this, singleSubscriber);
            singleSubscriber.add(aVar);
            single.subscribe(aVar);
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T> implements Single.OnSubscribe<T> {
        public final EventLoopsScheduler h;
        public final T i;

        public c(EventLoopsScheduler eventLoopsScheduler, T t) {
            this.h = eventLoopsScheduler;
            this.i = t;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            singleSubscriber.add(this.h.scheduleDirect(new e(singleSubscriber, this.i)));
        }
    }

    /* loaded from: classes13.dex */
    public static final class d<T> implements Single.OnSubscribe<T> {
        public final Scheduler h;
        public final T i;

        public d(Scheduler scheduler, T t) {
            this.h = scheduler;
            this.i = t;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            Scheduler.Worker createWorker = this.h.createWorker();
            singleSubscriber.add(createWorker);
            createWorker.schedule(new e(singleSubscriber, this.i));
        }
    }

    /* loaded from: classes13.dex */
    public static final class e<T> implements Action0 {
        public final SingleSubscriber<? super T> h;
        public final T i;

        public e(SingleSubscriber<? super T> singleSubscriber, T t) {
            this.h = singleSubscriber;
            this.i = t;
        }

        @Override // rx.functions.Action0
        public void call() {
            try {
                this.h.onSuccess((T) this.i);
            } catch (Throwable th) {
                this.h.onError(th);
            }
        }
    }

    public ScalarSynchronousSingle(T t) {
        super(new a(t));
        this.b = t;
    }

    public static <T> ScalarSynchronousSingle<T> create(T t) {
        return new ScalarSynchronousSingle<>(t);
    }

    public T get() {
        return this.b;
    }

    public <R> Single<R> scalarFlatMap(Func1<? super T, ? extends Single<? extends R>> func1) {
        return Single.create(new b(func1));
    }

    public Single<T> scalarScheduleOn(Scheduler scheduler) {
        if (scheduler instanceof EventLoopsScheduler) {
            return Single.create(new c((EventLoopsScheduler) scheduler, this.b));
        }
        return Single.create(new d(scheduler, this.b));
    }
}
