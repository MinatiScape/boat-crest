package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observers.Subscribers;
/* loaded from: classes13.dex */
public final class OnSubscribeUsing<T, Resource> implements Observable.OnSubscribe<T> {
    public final Func0<Resource> h;
    public final Func1<? super Resource, ? extends Observable<? extends T>> i;
    public final Action1<? super Resource> j;
    public final boolean k;

    /* loaded from: classes13.dex */
    public static final class a<Resource> extends AtomicBoolean implements Action0, Subscription {
        private static final long serialVersionUID = 4262875056400218316L;
        private Action1<? super Resource> dispose;
        private Resource resource;

        public a(Action1<? super Resource> action1, Resource resource) {
            this.dispose = action1;
            this.resource = resource;
            lazySet(false);
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [Resource, rx.functions.Action1<? super Resource>] */
        @Override // rx.functions.Action0
        public void call() {
            if (compareAndSet(false, true)) {
                ?? r0 = (Resource) false;
                try {
                    this.dispose.call((Resource) this.resource);
                } finally {
                    this.resource = null;
                    this.dispose = null;
                }
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            call();
        }
    }

    public OnSubscribeUsing(Func0<Resource> func0, Func1<? super Resource, ? extends Observable<? extends T>> func1, Action1<? super Resource> action1, boolean z) {
        this.h = func0;
        this.i = func1;
        this.j = action1;
        this.k = z;
    }

    public final Throwable a(Action0 action0) {
        try {
            action0.call();
            return null;
        } catch (Throwable th) {
            return th;
        }
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        Observable<? extends T> doAfterTerminate;
        try {
            Resource call = this.h.call();
            a aVar = new a(this.j, call);
            subscriber.add(aVar);
            Observable<? extends T> call2 = this.i.call(call);
            if (this.k) {
                doAfterTerminate = call2.doOnTerminate(aVar);
            } else {
                doAfterTerminate = call2.doAfterTerminate(aVar);
            }
            doAfterTerminate.unsafeSubscribe(Subscribers.wrap(subscriber));
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
