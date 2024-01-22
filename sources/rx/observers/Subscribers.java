package rx.observers;

import rx.Observer;
import rx.Subscriber;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;
/* loaded from: classes13.dex */
public final class Subscribers {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class a<T> extends Subscriber<T> {
        public final /* synthetic */ Observer l;

        public a(Observer observer) {
            this.l = observer;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.onNext(t);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class b<T> extends Subscriber<T> {
        public final /* synthetic */ Action1 l;

        public b(Action1 action1) {
            this.l = action1;
        }

        @Override // rx.Observer
        public final void onCompleted() {
        }

        @Override // rx.Observer
        public final void onError(Throwable th) {
            throw new OnErrorNotImplementedException(th);
        }

        @Override // rx.Observer
        public final void onNext(T t) {
            this.l.call(t);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class c<T> extends Subscriber<T> {
        public final /* synthetic */ Action1 l;
        public final /* synthetic */ Action1 m;

        public c(Action1 action1, Action1 action12) {
            this.l = action1;
            this.m = action12;
        }

        @Override // rx.Observer
        public final void onCompleted() {
        }

        @Override // rx.Observer
        public final void onError(Throwable th) {
            this.l.call(th);
        }

        @Override // rx.Observer
        public final void onNext(T t) {
            this.m.call(t);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class d<T> extends Subscriber<T> {
        public final /* synthetic */ Action0 l;
        public final /* synthetic */ Action1 m;
        public final /* synthetic */ Action1 n;

        public d(Action0 action0, Action1 action1, Action1 action12) {
            this.l = action0;
            this.m = action1;
            this.n = action12;
        }

        @Override // rx.Observer
        public final void onCompleted() {
            this.l.call();
        }

        @Override // rx.Observer
        public final void onError(Throwable th) {
            this.m.call(th);
        }

        @Override // rx.Observer
        public final void onNext(T t) {
            this.n.call(t);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class e<T> extends Subscriber<T> {
        public final /* synthetic */ Subscriber l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.l = subscriber2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.onNext(t);
        }
    }

    public Subscribers() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Subscriber<T> create(Action1<? super T> action1) {
        if (action1 != null) {
            return new b(action1);
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public static <T> Subscriber<T> empty() {
        return from(Observers.empty());
    }

    public static <T> Subscriber<T> from(Observer<? super T> observer) {
        return new a(observer);
    }

    public static <T> Subscriber<T> wrap(Subscriber<? super T> subscriber) {
        return new e(subscriber, subscriber);
    }

    public static <T> Subscriber<T> create(Action1<? super T> action1, Action1<Throwable> action12) {
        if (action1 != null) {
            if (action12 != null) {
                return new c(action12, action1);
            }
            throw new IllegalArgumentException("onError can not be null");
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public static <T> Subscriber<T> create(Action1<? super T> action1, Action1<Throwable> action12, Action0 action0) {
        if (action1 != null) {
            if (action12 != null) {
                if (action0 != null) {
                    return new d(action0, action12, action1);
                }
                throw new IllegalArgumentException("onComplete can not be null");
            }
            throw new IllegalArgumentException("onError can not be null");
        }
        throw new IllegalArgumentException("onNext can not be null");
    }
}
