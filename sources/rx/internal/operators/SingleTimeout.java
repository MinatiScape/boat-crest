package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action0;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class SingleTimeout<T> implements Single.OnSubscribe<T> {
    public final Single.OnSubscribe<T> h;
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;
    public final Single.OnSubscribe<? extends T> l;

    /* loaded from: classes13.dex */
    public static final class a<T> extends SingleSubscriber<T> implements Action0 {
        public final SingleSubscriber<? super T> i;
        public final AtomicBoolean j = new AtomicBoolean();
        public final Single.OnSubscribe<? extends T> k;

        /* renamed from: rx.internal.operators.SingleTimeout$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public static final class C0965a<T> extends SingleSubscriber<T> {
            public final SingleSubscriber<? super T> i;

            public C0965a(SingleSubscriber<? super T> singleSubscriber) {
                this.i = singleSubscriber;
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                this.i.onError(th);
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(T t) {
                this.i.onSuccess(t);
            }
        }

        public a(SingleSubscriber<? super T> singleSubscriber, Single.OnSubscribe<? extends T> onSubscribe) {
            this.i = singleSubscriber;
            this.k = onSubscribe;
        }

        @Override // rx.functions.Action0
        public void call() {
            if (this.j.compareAndSet(false, true)) {
                try {
                    Single.OnSubscribe<? extends T> onSubscribe = this.k;
                    if (onSubscribe == null) {
                        this.i.onError(new TimeoutException());
                    } else {
                        C0965a c0965a = new C0965a(this.i);
                        this.i.add(c0965a);
                        onSubscribe.call(c0965a);
                    }
                } finally {
                    unsubscribe();
                }
            }
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            if (this.j.compareAndSet(false, true)) {
                try {
                    this.i.onError(th);
                    return;
                } finally {
                    unsubscribe();
                }
            }
            RxJavaHooks.onError(th);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            if (this.j.compareAndSet(false, true)) {
                try {
                    this.i.onSuccess(t);
                } finally {
                    unsubscribe();
                }
            }
        }
    }

    public SingleTimeout(Single.OnSubscribe<T> onSubscribe, long j, TimeUnit timeUnit, Scheduler scheduler, Single.OnSubscribe<? extends T> onSubscribe2) {
        this.h = onSubscribe;
        this.i = j;
        this.j = timeUnit;
        this.k = scheduler;
        this.l = onSubscribe2;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        a aVar = new a(singleSubscriber, this.l);
        Scheduler.Worker createWorker = this.k.createWorker();
        aVar.add(createWorker);
        singleSubscriber.add(aVar);
        createWorker.schedule(aVar, this.i, this.j);
        this.h.call(aVar);
    }
}
