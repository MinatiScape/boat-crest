package rx.internal.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.SerialSubscription;
/* loaded from: classes13.dex */
public final class OnSubscribeJoin<TLeft, TRight, TLeftDuration, TRightDuration, R> implements Observable.OnSubscribe<R> {
    public final Observable<TLeft> h;
    public final Observable<TRight> i;
    public final Func1<TLeft, Observable<TLeftDuration>> j;
    public final Func1<TRight, Observable<TRightDuration>> k;
    public final Func2<TLeft, TRight, R> l;

    /* loaded from: classes13.dex */
    public final class a extends HashMap<Integer, TLeft> {
        private static final long serialVersionUID = 3491669543549085380L;
        public boolean leftDone;
        public int leftId;
        public boolean rightDone;
        public int rightId;
        public final Subscriber<? super R> subscriber;
        public final CompositeSubscription group = new CompositeSubscription();
        public final Map<Integer, TRight> rightMap = new HashMap();

        /* renamed from: rx.internal.operators.OnSubscribeJoin$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public final class C0940a extends Subscriber<TLeft> {

            /* renamed from: rx.internal.operators.OnSubscribeJoin$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes13.dex */
            public final class C0941a extends Subscriber<TLeftDuration> {
                public final int l;
                public boolean m = true;

                public C0941a(int i) {
                    this.l = i;
                }

                @Override // rx.Observer
                public void onCompleted() {
                    if (this.m) {
                        this.m = false;
                        C0940a.this.b(this.l, this);
                    }
                }

                @Override // rx.Observer
                public void onError(Throwable th) {
                    C0940a.this.onError(th);
                }

                @Override // rx.Observer
                public void onNext(TLeftDuration tleftduration) {
                    onCompleted();
                }
            }

            public C0940a() {
            }

            public void b(int i, Subscription subscription) {
                boolean z;
                synchronized (a.this) {
                    z = a.this.leftMap().remove(Integer.valueOf(i)) != null && a.this.leftMap().isEmpty() && a.this.leftDone;
                }
                if (z) {
                    a.this.subscriber.onCompleted();
                    a.this.subscriber.unsubscribe();
                    return;
                }
                a.this.group.remove(subscription);
            }

            @Override // rx.Observer
            public void onCompleted() {
                boolean z;
                synchronized (a.this) {
                    a aVar = a.this;
                    z = true;
                    aVar.leftDone = true;
                    if (!aVar.rightDone && !aVar.leftMap().isEmpty()) {
                        z = false;
                    }
                }
                if (z) {
                    a.this.subscriber.onCompleted();
                    a.this.subscriber.unsubscribe();
                    return;
                }
                a.this.group.remove(this);
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                a.this.subscriber.onError(th);
                a.this.subscriber.unsubscribe();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // rx.Observer
            public void onNext(TLeft tleft) {
                int i;
                a aVar;
                int i2;
                synchronized (a.this) {
                    a aVar2 = a.this;
                    i = aVar2.leftId;
                    aVar2.leftId = i + 1;
                    aVar2.leftMap().put(Integer.valueOf(i), tleft);
                    aVar = a.this;
                    i2 = aVar.rightId;
                }
                try {
                    C0941a c0941a = new C0941a(i);
                    a.this.group.add(c0941a);
                    OnSubscribeJoin.this.j.call(tleft).unsafeSubscribe(c0941a);
                    ArrayList<Object> arrayList = new ArrayList();
                    synchronized (a.this) {
                        for (Map.Entry<Integer, TRight> entry : a.this.rightMap.entrySet()) {
                            if (entry.getKey().intValue() < i2) {
                                arrayList.add(entry.getValue());
                            }
                        }
                    }
                    for (Object obj : arrayList) {
                        a.this.subscriber.onNext(OnSubscribeJoin.this.l.call(tleft, obj));
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }

        /* loaded from: classes13.dex */
        public final class b extends Subscriber<TRight> {

            /* renamed from: rx.internal.operators.OnSubscribeJoin$a$b$a  reason: collision with other inner class name */
            /* loaded from: classes13.dex */
            public final class C0942a extends Subscriber<TRightDuration> {
                public final int l;
                public boolean m = true;

                public C0942a(int i) {
                    this.l = i;
                }

                @Override // rx.Observer
                public void onCompleted() {
                    if (this.m) {
                        this.m = false;
                        b.this.b(this.l, this);
                    }
                }

                @Override // rx.Observer
                public void onError(Throwable th) {
                    b.this.onError(th);
                }

                @Override // rx.Observer
                public void onNext(TRightDuration trightduration) {
                    onCompleted();
                }
            }

            public b() {
            }

            public void b(int i, Subscription subscription) {
                boolean z;
                synchronized (a.this) {
                    z = a.this.rightMap.remove(Integer.valueOf(i)) != null && a.this.rightMap.isEmpty() && a.this.rightDone;
                }
                if (z) {
                    a.this.subscriber.onCompleted();
                    a.this.subscriber.unsubscribe();
                    return;
                }
                a.this.group.remove(subscription);
            }

            @Override // rx.Observer
            public void onCompleted() {
                boolean z;
                synchronized (a.this) {
                    a aVar = a.this;
                    z = true;
                    aVar.rightDone = true;
                    if (!aVar.leftDone && !aVar.rightMap.isEmpty()) {
                        z = false;
                    }
                }
                if (z) {
                    a.this.subscriber.onCompleted();
                    a.this.subscriber.unsubscribe();
                    return;
                }
                a.this.group.remove(this);
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                a.this.subscriber.onError(th);
                a.this.subscriber.unsubscribe();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // rx.Observer
            public void onNext(TRight tright) {
                int i;
                int i2;
                synchronized (a.this) {
                    a aVar = a.this;
                    i = aVar.rightId;
                    aVar.rightId = i + 1;
                    aVar.rightMap.put(Integer.valueOf(i), tright);
                    i2 = a.this.leftId;
                }
                a.this.group.add(new SerialSubscription());
                try {
                    C0942a c0942a = new C0942a(i);
                    a.this.group.add(c0942a);
                    OnSubscribeJoin.this.k.call(tright).unsafeSubscribe(c0942a);
                    ArrayList<Object> arrayList = new ArrayList();
                    synchronized (a.this) {
                        for (Map.Entry<Integer, TLeft> entry : a.this.leftMap().entrySet()) {
                            if (entry.getKey().intValue() < i2) {
                                arrayList.add(entry.getValue());
                            }
                        }
                    }
                    for (Object obj : arrayList) {
                        a.this.subscriber.onNext(OnSubscribeJoin.this.l.call(obj, tright));
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }

        public a(Subscriber<? super R> subscriber) {
            this.subscriber = subscriber;
        }

        public HashMap<Integer, TLeft> leftMap() {
            return this;
        }

        public void run() {
            this.subscriber.add(this.group);
            C0940a c0940a = new C0940a();
            b bVar = new b();
            this.group.add(c0940a);
            this.group.add(bVar);
            OnSubscribeJoin.this.h.unsafeSubscribe(c0940a);
            OnSubscribeJoin.this.i.unsafeSubscribe(bVar);
        }
    }

    public OnSubscribeJoin(Observable<TLeft> observable, Observable<TRight> observable2, Func1<TLeft, Observable<TLeftDuration>> func1, Func1<TRight, Observable<TRightDuration>> func12, Func2<TLeft, TRight, R> func2) {
        this.h = observable;
        this.i = observable2;
        this.j = func1;
        this.k = func12;
        this.l = func2;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        new a(new SerializedSubscriber(subscriber)).run();
    }
}
