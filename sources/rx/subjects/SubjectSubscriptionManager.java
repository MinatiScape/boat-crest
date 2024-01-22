package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.operators.NotificationLite;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class SubjectSubscriptionManager<T> extends AtomicReference<State<T>> implements Observable.OnSubscribe<T> {
    private static final long serialVersionUID = 6035251036011671568L;
    public boolean active;
    public volatile Object latest;
    public Action1<SubjectObserver<T>> onAdded;
    public Action1<SubjectObserver<T>> onStart;
    public Action1<SubjectObserver<T>> onTerminated;

    /* loaded from: classes13.dex */
    public static final class State<T> {
        public static final SubjectObserver[] c;
        public static final State d;
        public static final State e;

        /* renamed from: a  reason: collision with root package name */
        public final boolean f15705a;
        public final SubjectObserver[] b;

        static {
            SubjectObserver[] subjectObserverArr = new SubjectObserver[0];
            c = subjectObserverArr;
            d = new State(true, subjectObserverArr);
            e = new State(false, subjectObserverArr);
        }

        public State(boolean z, SubjectObserver[] subjectObserverArr) {
            this.f15705a = z;
            this.b = subjectObserverArr;
        }

        public State add(SubjectObserver subjectObserver) {
            SubjectObserver[] subjectObserverArr = this.b;
            int length = subjectObserverArr.length;
            SubjectObserver[] subjectObserverArr2 = new SubjectObserver[length + 1];
            System.arraycopy(subjectObserverArr, 0, subjectObserverArr2, 0, length);
            subjectObserverArr2[length] = subjectObserver;
            return new State(this.f15705a, subjectObserverArr2);
        }

        public State remove(SubjectObserver subjectObserver) {
            SubjectObserver[] subjectObserverArr = this.b;
            int length = subjectObserverArr.length;
            if (length == 1 && subjectObserverArr[0] == subjectObserver) {
                return e;
            }
            if (length == 0) {
                return this;
            }
            int i = length - 1;
            SubjectObserver[] subjectObserverArr2 = new SubjectObserver[i];
            int i2 = 0;
            for (SubjectObserver subjectObserver2 : subjectObserverArr) {
                if (subjectObserver2 != subjectObserver) {
                    if (i2 == i) {
                        return this;
                    }
                    subjectObserverArr2[i2] = subjectObserver2;
                    i2++;
                }
            }
            if (i2 == 0) {
                return e;
            }
            if (i2 < i) {
                SubjectObserver[] subjectObserverArr3 = new SubjectObserver[i2];
                System.arraycopy(subjectObserverArr2, 0, subjectObserverArr3, 0, i2);
                subjectObserverArr2 = subjectObserverArr3;
            }
            return new State(this.f15705a, subjectObserverArr2);
        }
    }

    /* loaded from: classes13.dex */
    public static final class SubjectObserver<T> implements Observer<T> {
        public final Subscriber<? super T> h;
        public boolean i = true;
        public boolean j;
        public List<Object> k;
        public boolean l;
        public volatile Object m;

        public SubjectObserver(Subscriber<? super T> subscriber) {
            this.h = subscriber;
        }

        public void a(Object obj) {
            if (obj != null) {
                NotificationLite.accept(this.h, obj);
            }
        }

        public void b(Object obj) {
            synchronized (this) {
                if (this.i && !this.j) {
                    this.i = false;
                    this.j = obj != null;
                    if (obj != null) {
                        c(null, obj);
                    }
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x0038  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void c(java.util.List<java.lang.Object> r5, java.lang.Object r6) {
            /*
                r4 = this;
                r0 = 1
                r1 = r0
            L2:
                r2 = 0
                if (r5 == 0) goto L1a
                java.util.Iterator r5 = r5.iterator()     // Catch: java.lang.Throwable -> L17
            L9:
                boolean r3 = r5.hasNext()     // Catch: java.lang.Throwable -> L17
                if (r3 == 0) goto L1a
                java.lang.Object r3 = r5.next()     // Catch: java.lang.Throwable -> L17
                r4.a(r3)     // Catch: java.lang.Throwable -> L17
                goto L9
            L17:
                r5 = move-exception
                r0 = r2
                goto L36
            L1a:
                if (r1 == 0) goto L20
                r4.a(r6)     // Catch: java.lang.Throwable -> L17
                r1 = r2
            L20:
                monitor-enter(r4)     // Catch: java.lang.Throwable -> L17
                java.util.List<java.lang.Object> r5 = r4.k     // Catch: java.lang.Throwable -> L2e
                r3 = 0
                r4.k = r3     // Catch: java.lang.Throwable -> L2e
                if (r5 != 0) goto L2c
                r4.j = r2     // Catch: java.lang.Throwable -> L2e
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L34
                return
            L2c:
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L2e
                goto L2
            L2e:
                r5 = move-exception
                r0 = r2
            L30:
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L34
                throw r5     // Catch: java.lang.Throwable -> L32
            L32:
                r5 = move-exception
                goto L36
            L34:
                r5 = move-exception
                goto L30
            L36:
                if (r0 != 0) goto L40
                monitor-enter(r4)
                r4.j = r2     // Catch: java.lang.Throwable -> L3d
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L3d
                goto L40
            L3d:
                r5 = move-exception
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L3d
                throw r5
            L40:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.SubjectSubscriptionManager.SubjectObserver.c(java.util.List, java.lang.Object):void");
        }

        public void d(Object obj) {
            if (!this.l) {
                synchronized (this) {
                    this.i = false;
                    if (this.j) {
                        if (this.k == null) {
                            this.k = new ArrayList();
                        }
                        this.k.add(obj);
                        return;
                    }
                    this.l = true;
                }
            }
            NotificationLite.accept(this.h, obj);
        }

        public <I> I index() {
            return (I) this.m;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.h.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.h.onNext(t);
        }

        public void index(Object obj) {
            this.m = obj;
        }
    }

    /* loaded from: classes13.dex */
    public class a implements Action0 {
        public final /* synthetic */ SubjectObserver h;

        public a(SubjectObserver subjectObserver) {
            this.h = subjectObserver;
        }

        @Override // rx.functions.Action0
        public void call() {
            SubjectSubscriptionManager.this.remove(this.h);
        }
    }

    public SubjectSubscriptionManager() {
        super(State.e);
        this.active = true;
        this.onStart = Actions.empty();
        this.onAdded = Actions.empty();
        this.onTerminated = Actions.empty();
    }

    public boolean add(SubjectObserver<T> subjectObserver) {
        State<T> state;
        do {
            state = get();
            if (state.f15705a) {
                this.onTerminated.call(subjectObserver);
                return false;
            }
        } while (!compareAndSet(state, state.add(subjectObserver)));
        this.onAdded.call(subjectObserver);
        return true;
    }

    public void addUnsubscriber(Subscriber<? super T> subscriber, SubjectObserver<T> subjectObserver) {
        subscriber.add(Subscriptions.create(new a(subjectObserver)));
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public Object getLatest() {
        return this.latest;
    }

    public SubjectObserver<T>[] next(Object obj) {
        setLatest(obj);
        return get().b;
    }

    public SubjectObserver<T>[] observers() {
        return get().b;
    }

    public void remove(SubjectObserver<T> subjectObserver) {
        State<T> state;
        State<T> remove;
        do {
            state = get();
            if (state.f15705a || (remove = state.remove(subjectObserver)) == state) {
                return;
            }
        } while (!compareAndSet(state, remove));
    }

    public void setLatest(Object obj) {
        this.latest = obj;
    }

    public SubjectObserver<T>[] terminate(Object obj) {
        setLatest(obj);
        this.active = false;
        if (get().f15705a) {
            return State.c;
        }
        return getAndSet(State.d).b;
    }

    public void call(Subscriber<? super T> subscriber) {
        SubjectObserver<T> subjectObserver = new SubjectObserver<>(subscriber);
        addUnsubscriber(subscriber, subjectObserver);
        this.onStart.call(subjectObserver);
        if (!subscriber.isUnsubscribed() && add(subjectObserver) && subscriber.isUnsubscribed()) {
            remove(subjectObserver);
        }
    }
}
