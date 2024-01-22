package rx.observers;

import rx.Observer;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;
/* loaded from: classes13.dex */
public final class Observers {

    /* renamed from: a  reason: collision with root package name */
    public static final Observer<Object> f15689a = new a();

    /* loaded from: classes13.dex */
    public static class a implements Observer<Object> {
        @Override // rx.Observer
        public final void onCompleted() {
        }

        @Override // rx.Observer
        public final void onError(Throwable th) {
            throw new OnErrorNotImplementedException(th);
        }

        @Override // rx.Observer
        public final void onNext(Object obj) {
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class b<T> implements Observer<T> {
        public final /* synthetic */ Action1 h;

        public b(Action1 action1) {
            this.h = action1;
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
            this.h.call(t);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class c<T> implements Observer<T> {
        public final /* synthetic */ Action1 h;
        public final /* synthetic */ Action1 i;

        public c(Action1 action1, Action1 action12) {
            this.h = action1;
            this.i = action12;
        }

        @Override // rx.Observer
        public final void onCompleted() {
        }

        @Override // rx.Observer
        public final void onError(Throwable th) {
            this.h.call(th);
        }

        @Override // rx.Observer
        public final void onNext(T t) {
            this.i.call(t);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class d<T> implements Observer<T> {
        public final /* synthetic */ Action0 h;
        public final /* synthetic */ Action1 i;
        public final /* synthetic */ Action1 j;

        public d(Action0 action0, Action1 action1, Action1 action12) {
            this.h = action0;
            this.i = action1;
            this.j = action12;
        }

        @Override // rx.Observer
        public final void onCompleted() {
            this.h.call();
        }

        @Override // rx.Observer
        public final void onError(Throwable th) {
            this.i.call(th);
        }

        @Override // rx.Observer
        public final void onNext(T t) {
            this.j.call(t);
        }
    }

    public Observers() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Observer<T> create(Action1<? super T> action1) {
        if (action1 != null) {
            return new b(action1);
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public static <T> Observer<T> empty() {
        return (Observer<T>) f15689a;
    }

    public static <T> Observer<T> create(Action1<? super T> action1, Action1<Throwable> action12) {
        if (action1 != null) {
            if (action12 != null) {
                return new c(action12, action1);
            }
            throw new IllegalArgumentException("onError can not be null");
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public static <T> Observer<T> create(Action1<? super T> action1, Action1<Throwable> action12, Action0 action0) {
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
