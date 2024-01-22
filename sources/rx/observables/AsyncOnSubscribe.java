package rx.observables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.annotations.Beta;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Action3;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.internal.operators.BufferUntilSubscriber;
import rx.observers.SerializedObserver;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
@Beta
/* loaded from: classes13.dex */
public abstract class AsyncOnSubscribe<S, T> implements Observable.OnSubscribe<T> {

    /* loaded from: classes13.dex */
    public static class a implements Func3<S, Long, Observer<Observable<? extends T>>, S> {
        public final /* synthetic */ Action3 h;

        public a(Action3 action3) {
            this.h = action3;
        }

        @Override // rx.functions.Func3
        /* renamed from: a */
        public S call(S s, Long l, Observer<Observable<? extends T>> observer) {
            this.h.call(s, l, observer);
            return s;
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements Func3<S, Long, Observer<Observable<? extends T>>, S> {
        public final /* synthetic */ Action3 h;

        public b(Action3 action3) {
            this.h = action3;
        }

        @Override // rx.functions.Func3
        /* renamed from: a */
        public S call(S s, Long l, Observer<Observable<? extends T>> observer) {
            this.h.call(s, l, observer);
            return s;
        }
    }

    /* loaded from: classes13.dex */
    public static class c implements Func3<Void, Long, Observer<Observable<? extends T>>, Void> {
        public final /* synthetic */ Action2 h;

        public c(Action2 action2) {
            this.h = action2;
        }

        @Override // rx.functions.Func3
        /* renamed from: a */
        public Void call(Void r2, Long l, Observer<Observable<? extends T>> observer) {
            this.h.call(l, observer);
            return r2;
        }
    }

    /* loaded from: classes13.dex */
    public static class d implements Func3<Void, Long, Observer<Observable<? extends T>>, Void> {
        public final /* synthetic */ Action2 h;

        public d(Action2 action2) {
            this.h = action2;
        }

        @Override // rx.functions.Func3
        /* renamed from: a */
        public Void call(Void r1, Long l, Observer<Observable<? extends T>> observer) {
            this.h.call(l, observer);
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
    public class f extends Subscriber<T> {
        public final /* synthetic */ Subscriber l;
        public final /* synthetic */ i m;

        public f(AsyncOnSubscribe asyncOnSubscribe, Subscriber subscriber, i iVar) {
            this.l = subscriber;
            this.m = iVar;
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

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.m.f(producer);
        }
    }

    /* loaded from: classes13.dex */
    public class g implements Func1<Observable<T>, Observable<T>> {
        public g(AsyncOnSubscribe asyncOnSubscribe) {
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable<T> call(Observable<T> observable) {
            return observable.onBackpressureBuffer();
        }
    }

    /* loaded from: classes13.dex */
    public static final class i<S, T> implements Producer, Subscription, Observer<Observable<? extends T>> {
        public final AsyncOnSubscribe<S, T> i;
        public boolean l;
        public boolean m;
        public S n;
        public final j<Observable<T>> o;
        public boolean p;
        public List<Long> q;
        public Producer r;
        public long s;
        public final CompositeSubscription k = new CompositeSubscription();
        public final SerializedObserver<Observable<? extends T>> j = new SerializedObserver<>(this);
        public final AtomicBoolean h = new AtomicBoolean();

        /* loaded from: classes13.dex */
        public class a extends Subscriber<T> {
            public long l;
            public final /* synthetic */ long m;
            public final /* synthetic */ BufferUntilSubscriber n;

            public a(long j, BufferUntilSubscriber bufferUntilSubscriber) {
                this.m = j;
                this.n = bufferUntilSubscriber;
                this.l = j;
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.n.onCompleted();
                long j = this.l;
                if (j > 0) {
                    i.this.e(j);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                this.n.onError(th);
            }

            @Override // rx.Observer
            public void onNext(T t) {
                this.l--;
                this.n.onNext(t);
            }
        }

        /* loaded from: classes13.dex */
        public class b implements Action0 {
            public final /* synthetic */ Subscriber h;

            public b(Subscriber subscriber) {
                this.h = subscriber;
            }

            @Override // rx.functions.Action0
            public void call() {
                i.this.k.remove(this.h);
            }
        }

        public i(AsyncOnSubscribe<S, T> asyncOnSubscribe, S s, j<Observable<T>> jVar) {
            this.i = asyncOnSubscribe;
            this.n = s;
            this.o = jVar;
        }

        public void a() {
            this.k.unsubscribe();
            try {
                this.i.onUnsubscribe(this.n);
            } catch (Throwable th) {
                b(th);
            }
        }

        public final void b(Throwable th) {
            if (this.l) {
                RxJavaHooks.onError(th);
                return;
            }
            this.l = true;
            this.o.onError(th);
            a();
        }

        public void c(long j) {
            this.n = this.i.next(this.n, j, this.j);
        }

        @Override // rx.Observer
        /* renamed from: d */
        public void onNext(Observable<? extends T> observable) {
            if (!this.m) {
                this.m = true;
                if (this.l) {
                    return;
                }
                g(observable);
                return;
            }
            throw new IllegalStateException("onNext called multiple times!");
        }

        public void e(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i == 0) {
                return;
            }
            if (i >= 0) {
                synchronized (this) {
                    if (this.p) {
                        List list = this.q;
                        if (list == null) {
                            list = new ArrayList();
                            this.q = list;
                        }
                        list.add(Long.valueOf(j));
                        return;
                    }
                    this.p = true;
                    if (h(j)) {
                        return;
                    }
                    while (true) {
                        synchronized (this) {
                            List<Long> list2 = this.q;
                            if (list2 == null) {
                                this.p = false;
                                return;
                            }
                            this.q = null;
                            for (Long l : list2) {
                                if (h(l.longValue())) {
                                    return;
                                }
                            }
                        }
                    }
                }
            } else {
                throw new IllegalStateException("Request can't be negative! " + j);
            }
        }

        public void f(Producer producer) {
            if (this.r == null) {
                this.r = producer;
                return;
            }
            throw new IllegalStateException("setConcatProducer may be called at most once!");
        }

        public final void g(Observable<? extends T> observable) {
            BufferUntilSubscriber create = BufferUntilSubscriber.create();
            a aVar = new a(this.s, create);
            this.k.add(aVar);
            observable.doOnTerminate(new b(aVar)).subscribe((Subscriber<? super Object>) aVar);
            this.o.onNext(create);
        }

        public boolean h(long j) {
            if (isUnsubscribed()) {
                a();
                return true;
            }
            try {
                this.m = false;
                this.s = j;
                c(j);
                if (!this.l && !isUnsubscribed()) {
                    if (this.m) {
                        return false;
                    }
                    b(new IllegalStateException("No events emitted!"));
                    return true;
                }
                a();
                return true;
            } catch (Throwable th) {
                b(th);
                return true;
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.h.get();
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.l) {
                this.l = true;
                this.o.onCompleted();
                return;
            }
            throw new IllegalStateException("Terminal event already emitted.");
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (!this.l) {
                this.l = true;
                this.o.onError(th);
                return;
            }
            throw new IllegalStateException("Terminal event already emitted.");
        }

        @Override // rx.Producer
        public void request(long j) {
            boolean z;
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i == 0) {
                return;
            }
            if (i >= 0) {
                synchronized (this) {
                    z = true;
                    if (this.p) {
                        List list = this.q;
                        if (list == null) {
                            list = new ArrayList();
                            this.q = list;
                        }
                        list.add(Long.valueOf(j));
                    } else {
                        this.p = true;
                        z = false;
                    }
                }
                this.r.request(j);
                if (z || h(j)) {
                    return;
                }
                while (true) {
                    synchronized (this) {
                        List<Long> list2 = this.q;
                        if (list2 == null) {
                            this.p = false;
                            return;
                        }
                        this.q = null;
                        for (Long l : list2) {
                            if (h(l.longValue())) {
                                return;
                            }
                        }
                    }
                }
            } else {
                throw new IllegalStateException("Request can't be negative! " + j);
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (this.h.compareAndSet(false, true)) {
                synchronized (this) {
                    if (this.p) {
                        ArrayList arrayList = new ArrayList();
                        this.q = arrayList;
                        arrayList.add(0L);
                        return;
                    }
                    this.p = true;
                    a();
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class j<T> extends Observable<T> implements Observer<T> {
        public final a<T> i;

        /* loaded from: classes13.dex */
        public static final class a<T> implements Observable.OnSubscribe<T> {
            public Subscriber<? super T> h;

            @Override // rx.functions.Action1
            /* renamed from: a */
            public void call(Subscriber<? super T> subscriber) {
                synchronized (this) {
                    if (this.h == null) {
                        this.h = subscriber;
                    } else {
                        subscriber.onError(new IllegalStateException("There can be only one subscriber"));
                    }
                }
            }
        }

        public j(a<T> aVar) {
            super(aVar);
            this.i = aVar;
        }

        public static <T> j<T> c() {
            return new j<>(new a());
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.i.h.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.i.h.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.i.h.onNext(t);
        }
    }

    public static <S, T> AsyncOnSubscribe<S, T> createSingleState(Func0<? extends S> func0, Action3<? super S, Long, ? super Observer<Observable<? extends T>>> action3) {
        return new h(func0, new a(action3));
    }

    public static <S, T> AsyncOnSubscribe<S, T> createStateful(Func0<? extends S> func0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func3, Action1<? super S> action1) {
        return new h(func0, func3, action1);
    }

    public static <T> AsyncOnSubscribe<Void, T> createStateless(Action2<Long, ? super Observer<Observable<? extends T>>> action2) {
        return new h(new c(action2));
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public abstract S generateState();

    public abstract S next(S s, long j2, Observer<Observable<? extends T>> observer);

    public void onUnsubscribe(S s) {
    }

    public static <S, T> AsyncOnSubscribe<S, T> createStateful(Func0<? extends S> func0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func3) {
        return new h(func0, func3);
    }

    public final void call(Subscriber<? super T> subscriber) {
        try {
            S generateState = generateState();
            j c2 = j.c();
            i iVar = new i(this, generateState, c2);
            f fVar = new f(this, subscriber, iVar);
            c2.onBackpressureBuffer().concatMap(new g(this)).unsafeSubscribe(fVar);
            subscriber.add(fVar);
            subscriber.add(iVar);
            subscriber.setProducer(iVar);
        } catch (Throwable th) {
            subscriber.onError(th);
        }
    }

    public static <S, T> AsyncOnSubscribe<S, T> createSingleState(Func0<? extends S> func0, Action3<? super S, Long, ? super Observer<Observable<? extends T>>> action3, Action1<? super S> action1) {
        return new h(func0, new b(action3), action1);
    }

    public static <T> AsyncOnSubscribe<Void, T> createStateless(Action2<Long, ? super Observer<Observable<? extends T>>> action2, Action0 action0) {
        return new h(new d(action2), new e(action0));
    }

    /* loaded from: classes13.dex */
    public static final class h<S, T> extends AsyncOnSubscribe<S, T> {
        public final Func0<? extends S> h;
        public final Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> i;
        public final Action1<? super S> j;

        public h(Func0<? extends S> func0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func3, Action1<? super S> action1) {
            this.h = func0;
            this.i = func3;
            this.j = action1;
        }

        @Override // rx.observables.AsyncOnSubscribe, rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            super.call((Subscriber) ((Subscriber) obj));
        }

        @Override // rx.observables.AsyncOnSubscribe
        public S generateState() {
            Func0<? extends S> func0 = this.h;
            if (func0 == null) {
                return null;
            }
            return func0.call();
        }

        @Override // rx.observables.AsyncOnSubscribe
        public S next(S s, long j, Observer<Observable<? extends T>> observer) {
            return this.i.call(s, Long.valueOf(j), observer);
        }

        @Override // rx.observables.AsyncOnSubscribe
        public void onUnsubscribe(S s) {
            Action1<? super S> action1 = this.j;
            if (action1 != null) {
                action1.call(s);
            }
        }

        public h(Func0<? extends S> func0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> func3) {
            this(func0, func3, null);
        }

        public h(Func3<S, Long, Observer<Observable<? extends T>>, S> func3, Action1<? super S> action1) {
            this(null, func3, action1);
        }

        public h(Func3<S, Long, Observer<Observable<? extends T>>, S> func3) {
            this(null, func3, null);
        }
    }
}
