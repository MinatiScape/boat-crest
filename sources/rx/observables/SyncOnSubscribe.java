package rx.observables;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.internal.operators.BackpressureUtils;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public abstract class SyncOnSubscribe<S, T> implements Observable.OnSubscribe<T> {

    /* loaded from: classes13.dex */
    public static class a implements Func2<S, Observer<? super T>, S> {
        public final /* synthetic */ Action2 h;

        public a(Action2 action2) {
            this.h = action2;
        }

        @Override // rx.functions.Func2
        /* renamed from: a */
        public S call(S s, Observer<? super T> observer) {
            this.h.call(s, observer);
            return s;
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements Func2<S, Observer<? super T>, S> {
        public final /* synthetic */ Action2 h;

        public b(Action2 action2) {
            this.h = action2;
        }

        @Override // rx.functions.Func2
        /* renamed from: a */
        public S call(S s, Observer<? super T> observer) {
            this.h.call(s, observer);
            return s;
        }
    }

    /* loaded from: classes13.dex */
    public static class c implements Func2<Void, Observer<? super T>, Void> {
        public final /* synthetic */ Action1 h;

        public c(Action1 action1) {
            this.h = action1;
        }

        @Override // rx.functions.Func2
        /* renamed from: a */
        public Void call(Void r2, Observer<? super T> observer) {
            this.h.call(observer);
            return r2;
        }
    }

    /* loaded from: classes13.dex */
    public static class d implements Func2<Void, Observer<? super T>, Void> {
        public final /* synthetic */ Action1 h;

        public d(Action1 action1) {
            this.h = action1;
        }

        @Override // rx.functions.Func2
        /* renamed from: a */
        public Void call(Void r1, Observer<? super T> observer) {
            this.h.call(observer);
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public static class e implements Action1<Void> {
        public final /* synthetic */ Action0 h;

        public e(Action0 action0) {
            this.h = action0;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Void r1) {
            this.h.call();
        }
    }

    /* loaded from: classes13.dex */
    public static final class f<S, T> extends AtomicLong implements Producer, Subscription, Observer<T> {
        private static final long serialVersionUID = -3736864024352728072L;
        private final Subscriber<? super T> actualSubscriber;
        private boolean hasTerminated;
        private boolean onNextCalled;
        private final SyncOnSubscribe<S, T> parent;
        private S state;

        public f(Subscriber<? super T> subscriber, SyncOnSubscribe<S, T> syncOnSubscribe, S s) {
            this.actualSubscriber = subscriber;
            this.parent = syncOnSubscribe;
            this.state = s;
        }

        private void doUnsubscribe() {
            try {
                this.parent.onUnsubscribe(this.state);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaHooks.onError(th);
            }
        }

        private void fastPath() {
            SyncOnSubscribe<S, T> syncOnSubscribe = this.parent;
            Subscriber<? super T> subscriber = this.actualSubscriber;
            do {
                try {
                    this.onNextCalled = false;
                    nextIteration(syncOnSubscribe);
                } catch (Throwable th) {
                    handleThrownError(subscriber, th);
                    return;
                }
            } while (!tryUnsubscribe());
        }

        private void handleThrownError(Subscriber<? super T> subscriber, Throwable th) {
            if (this.hasTerminated) {
                RxJavaHooks.onError(th);
                return;
            }
            this.hasTerminated = true;
            subscriber.onError(th);
            unsubscribe();
        }

        private void nextIteration(SyncOnSubscribe<S, T> syncOnSubscribe) {
            this.state = syncOnSubscribe.next(this.state, this);
        }

        private void slowPath(long j) {
            SyncOnSubscribe<S, T> syncOnSubscribe = this.parent;
            Subscriber<? super T> subscriber = this.actualSubscriber;
            do {
                long j2 = j;
                do {
                    try {
                        this.onNextCalled = false;
                        nextIteration(syncOnSubscribe);
                        if (tryUnsubscribe()) {
                            return;
                        }
                        if (this.onNextCalled) {
                            j2--;
                        }
                    } catch (Throwable th) {
                        handleThrownError(subscriber, th);
                        return;
                    }
                } while (j2 != 0);
                j = addAndGet(-j);
            } while (j > 0);
            tryUnsubscribe();
        }

        private boolean tryUnsubscribe() {
            if (this.hasTerminated || get() < -1) {
                set(-1L);
                doUnsubscribe();
                return true;
            }
            return false;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() < 0;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.hasTerminated) {
                this.hasTerminated = true;
                if (this.actualSubscriber.isUnsubscribed()) {
                    return;
                }
                this.actualSubscriber.onCompleted();
                return;
            }
            throw new IllegalStateException("Terminal event already emitted.");
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (!this.hasTerminated) {
                this.hasTerminated = true;
                if (this.actualSubscriber.isUnsubscribed()) {
                    return;
                }
                this.actualSubscriber.onError(th);
                return;
            }
            throw new IllegalStateException("Terminal event already emitted.");
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.onNextCalled) {
                this.onNextCalled = true;
                this.actualSubscriber.onNext(t);
                return;
            }
            throw new IllegalStateException("onNext called multiple times!");
        }

        @Override // rx.Producer
        public void request(long j) {
            if (j <= 0 || BackpressureUtils.getAndAddRequest(this, j) != 0) {
                return;
            }
            if (j == Long.MAX_VALUE) {
                fastPath();
            } else {
                slowPath(j);
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            long j;
            do {
                j = get();
                if (compareAndSet(0L, -1L)) {
                    doUnsubscribe();
                    return;
                }
            } while (!compareAndSet(j, -2L));
        }
    }

    public static <S, T> SyncOnSubscribe<S, T> createSingleState(Func0<? extends S> func0, Action2<? super S, ? super Observer<? super T>> action2) {
        return new g(func0, new a(action2));
    }

    public static <S, T> SyncOnSubscribe<S, T> createStateful(Func0<? extends S> func0, Func2<? super S, ? super Observer<? super T>, ? extends S> func2, Action1<? super S> action1) {
        return new g(func0, func2, action1);
    }

    public static <T> SyncOnSubscribe<Void, T> createStateless(Action1<? super Observer<? super T>> action1) {
        return new g(new c(action1));
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public abstract S generateState();

    public abstract S next(S s, Observer<? super T> observer);

    public void onUnsubscribe(S s) {
    }

    public static <S, T> SyncOnSubscribe<S, T> createStateful(Func0<? extends S> func0, Func2<? super S, ? super Observer<? super T>, ? extends S> func2) {
        return new g(func0, func2);
    }

    public final void call(Subscriber<? super T> subscriber) {
        try {
            f fVar = new f(subscriber, this, generateState());
            subscriber.add(fVar);
            subscriber.setProducer(fVar);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
        }
    }

    public static <S, T> SyncOnSubscribe<S, T> createSingleState(Func0<? extends S> func0, Action2<? super S, ? super Observer<? super T>> action2, Action1<? super S> action1) {
        return new g(func0, new b(action2), action1);
    }

    public static <T> SyncOnSubscribe<Void, T> createStateless(Action1<? super Observer<? super T>> action1, Action0 action0) {
        return new g(new d(action1), new e(action0));
    }

    /* loaded from: classes13.dex */
    public static final class g<S, T> extends SyncOnSubscribe<S, T> {
        public final Func0<? extends S> h;
        public final Func2<? super S, ? super Observer<? super T>, ? extends S> i;
        public final Action1<? super S> j;

        public g(Func0<? extends S> func0, Func2<? super S, ? super Observer<? super T>, ? extends S> func2, Action1<? super S> action1) {
            this.h = func0;
            this.i = func2;
            this.j = action1;
        }

        @Override // rx.observables.SyncOnSubscribe, rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            super.call((Subscriber) ((Subscriber) obj));
        }

        @Override // rx.observables.SyncOnSubscribe
        public S generateState() {
            Func0<? extends S> func0 = this.h;
            if (func0 == null) {
                return null;
            }
            return func0.call();
        }

        @Override // rx.observables.SyncOnSubscribe
        public S next(S s, Observer<? super T> observer) {
            return this.i.call(s, observer);
        }

        @Override // rx.observables.SyncOnSubscribe
        public void onUnsubscribe(S s) {
            Action1<? super S> action1 = this.j;
            if (action1 != null) {
                action1.call(s);
            }
        }

        public g(Func0<? extends S> func0, Func2<? super S, ? super Observer<? super T>, ? extends S> func2) {
            this(func0, func2, null);
        }

        public g(Func2<S, Observer<? super T>, S> func2, Action1<? super S> action1) {
            this(null, func2, action1);
        }

        public g(Func2<S, Observer<? super T>, S> func2) {
            this(null, func2, null);
        }
    }
}
