package rx.observables;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func1;
import rx.internal.operators.BlockingOperatorLatest;
import rx.internal.operators.BlockingOperatorMostRecent;
import rx.internal.operators.BlockingOperatorNext;
import rx.internal.operators.BlockingOperatorToFuture;
import rx.internal.operators.BlockingOperatorToIterator;
import rx.internal.operators.NotificationLite;
import rx.internal.util.BlockingUtils;
import rx.internal.util.UtilityFunctions;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class BlockingObservable<T> {
    public static final Object b = new Object();
    public static final Object c = new Object();
    public static final Object d = new Object();

    /* renamed from: a  reason: collision with root package name */
    public final Observable<? extends T> f15688a;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public final /* synthetic */ CountDownLatch l;
        public final /* synthetic */ AtomicReference m;
        public final /* synthetic */ Action1 n;

        public a(BlockingObservable blockingObservable, CountDownLatch countDownLatch, AtomicReference atomicReference, Action1 action1) {
            this.l = countDownLatch;
            this.m = atomicReference;
            this.n = action1;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.countDown();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.set(th);
            this.l.countDown();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.n.call(t);
        }
    }

    /* loaded from: classes13.dex */
    public class b implements Iterable<T> {
        public b() {
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return BlockingObservable.this.getIterator();
        }
    }

    /* loaded from: classes13.dex */
    public class c extends Subscriber<T> {
        public final /* synthetic */ CountDownLatch l;
        public final /* synthetic */ AtomicReference m;
        public final /* synthetic */ AtomicReference n;

        public c(BlockingObservable blockingObservable, CountDownLatch countDownLatch, AtomicReference atomicReference, AtomicReference atomicReference2) {
            this.l = countDownLatch;
            this.m = atomicReference;
            this.n = atomicReference2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.countDown();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.set(th);
            this.l.countDown();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.n.set(t);
        }
    }

    /* loaded from: classes13.dex */
    public class d extends Subscriber<T> {
        public final /* synthetic */ Throwable[] l;
        public final /* synthetic */ CountDownLatch m;

        public d(BlockingObservable blockingObservable, Throwable[] thArr, CountDownLatch countDownLatch) {
            this.l = thArr;
            this.m = countDownLatch;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.m.countDown();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l[0] = th;
            this.m.countDown();
        }

        @Override // rx.Observer
        public void onNext(T t) {
        }
    }

    /* loaded from: classes13.dex */
    public class e extends Subscriber<T> {
        public final /* synthetic */ BlockingQueue l;

        public e(BlockingObservable blockingObservable, BlockingQueue blockingQueue) {
            this.l = blockingQueue;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.offer(NotificationLite.completed());
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.offer(NotificationLite.error(th));
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.offer(NotificationLite.next(t));
        }
    }

    /* loaded from: classes13.dex */
    public class f extends Subscriber<T> {
        public final /* synthetic */ BlockingQueue l;
        public final /* synthetic */ Producer[] m;

        public f(BlockingObservable blockingObservable, BlockingQueue blockingQueue, Producer[] producerArr) {
            this.l = blockingQueue;
            this.m = producerArr;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.offer(NotificationLite.completed());
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.offer(NotificationLite.error(th));
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.offer(NotificationLite.next(t));
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            this.l.offer(BlockingObservable.b);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.m[0] = producer;
            this.l.offer(BlockingObservable.c);
        }
    }

    /* loaded from: classes13.dex */
    public class g implements Action0 {
        public final /* synthetic */ BlockingQueue h;

        public g(BlockingObservable blockingObservable, BlockingQueue blockingQueue) {
            this.h = blockingQueue;
        }

        @Override // rx.functions.Action0
        public void call() {
            this.h.offer(BlockingObservable.d);
        }
    }

    /* loaded from: classes13.dex */
    public class h implements Action1<Throwable> {
        public h(BlockingObservable blockingObservable) {
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Throwable th) {
            throw new OnErrorNotImplementedException(th);
        }
    }

    /* loaded from: classes13.dex */
    public class i implements Observer<T> {
        public final /* synthetic */ Action1 h;
        public final /* synthetic */ Action1 i;
        public final /* synthetic */ Action0 j;

        public i(BlockingObservable blockingObservable, Action1 action1, Action1 action12, Action0 action0) {
            this.h = action1;
            this.i = action12;
            this.j = action0;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.j.call();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.i.call(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.h.call(t);
        }
    }

    public BlockingObservable(Observable<? extends T> observable) {
        this.f15688a = observable;
    }

    public static <T> BlockingObservable<T> from(Observable<? extends T> observable) {
        return new BlockingObservable<>(observable);
    }

    public final T a(Observable<? extends T> observable) {
        AtomicReference atomicReference = new AtomicReference();
        AtomicReference atomicReference2 = new AtomicReference();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        BlockingUtils.awaitForComplete(countDownLatch, observable.subscribe((Subscriber<? super Object>) new c(this, countDownLatch, atomicReference2, atomicReference)));
        if (atomicReference2.get() != null) {
            Exceptions.propagate((Throwable) atomicReference2.get());
        }
        return (T) atomicReference.get();
    }

    public T first() {
        return a(this.f15688a.first());
    }

    public T firstOrDefault(T t) {
        return a(this.f15688a.map(UtilityFunctions.identity()).firstOrDefault(t));
    }

    public void forEach(Action1<? super T> action1) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicReference atomicReference = new AtomicReference();
        BlockingUtils.awaitForComplete(countDownLatch, this.f15688a.subscribe((Subscriber<? super Object>) new a(this, countDownLatch, atomicReference, action1)));
        if (atomicReference.get() != null) {
            Exceptions.propagate((Throwable) atomicReference.get());
        }
    }

    public Iterator<T> getIterator() {
        return BlockingOperatorToIterator.toIterator(this.f15688a);
    }

    public T last() {
        return a(this.f15688a.last());
    }

    public T lastOrDefault(T t) {
        return a(this.f15688a.map(UtilityFunctions.identity()).lastOrDefault(t));
    }

    public Iterable<T> latest() {
        return BlockingOperatorLatest.latest(this.f15688a);
    }

    public Iterable<T> mostRecent(T t) {
        return BlockingOperatorMostRecent.mostRecent(this.f15688a, t);
    }

    public Iterable<T> next() {
        return BlockingOperatorNext.next(this.f15688a);
    }

    public T single() {
        return a(this.f15688a.single());
    }

    public T singleOrDefault(T t) {
        return a(this.f15688a.map(UtilityFunctions.identity()).singleOrDefault(t));
    }

    public void subscribe() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Throwable[] thArr = {null};
        BlockingUtils.awaitForComplete(countDownLatch, this.f15688a.subscribe((Subscriber<? super Object>) new d(this, thArr, countDownLatch)));
        Throwable th = thArr[0];
        if (th != null) {
            Exceptions.propagate(th);
        }
    }

    public Future<T> toFuture() {
        return BlockingOperatorToFuture.toFuture(this.f15688a);
    }

    public Iterable<T> toIterable() {
        return new b();
    }

    public T first(Func1<? super T, Boolean> func1) {
        return a(this.f15688a.first(func1));
    }

    public T firstOrDefault(T t, Func1<? super T, Boolean> func1) {
        return a(this.f15688a.filter(func1).map(UtilityFunctions.identity()).firstOrDefault(t));
    }

    public T last(Func1<? super T, Boolean> func1) {
        return a(this.f15688a.last(func1));
    }

    public T lastOrDefault(T t, Func1<? super T, Boolean> func1) {
        return a(this.f15688a.filter(func1).map(UtilityFunctions.identity()).lastOrDefault(t));
    }

    public T single(Func1<? super T, Boolean> func1) {
        return a(this.f15688a.single(func1));
    }

    public T singleOrDefault(T t, Func1<? super T, Boolean> func1) {
        return a(this.f15688a.filter(func1).map(UtilityFunctions.identity()).singleOrDefault(t));
    }

    public void subscribe(Observer<? super T> observer) {
        Object poll;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        Subscription subscribe = this.f15688a.subscribe((Subscriber<? super Object>) new e(this, linkedBlockingQueue));
        do {
            try {
                poll = linkedBlockingQueue.poll();
                if (poll == null) {
                    poll = linkedBlockingQueue.take();
                }
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                observer.onError(e2);
                return;
            } finally {
                subscribe.unsubscribe();
            }
        } while (!NotificationLite.accept(observer, poll));
    }

    public void subscribe(Subscriber<? super T> subscriber) {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        Producer[] producerArr = {null};
        f fVar = new f(this, linkedBlockingQueue, producerArr);
        subscriber.add(fVar);
        subscriber.add(Subscriptions.create(new g(this, linkedBlockingQueue)));
        this.f15688a.subscribe((Subscriber<? super Object>) fVar);
        while (!subscriber.isUnsubscribed()) {
            try {
                try {
                    Object poll = linkedBlockingQueue.poll();
                    if (poll == null) {
                        poll = linkedBlockingQueue.take();
                    }
                    if (subscriber.isUnsubscribed() || poll == d) {
                        break;
                    } else if (poll == b) {
                        subscriber.onStart();
                    } else if (poll == c) {
                        subscriber.setProducer(producerArr[0]);
                    } else if (NotificationLite.accept(subscriber, poll)) {
                        return;
                    }
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    subscriber.onError(e2);
                }
            } finally {
                fVar.unsubscribe();
            }
        }
    }

    public void subscribe(Action1<? super T> action1) {
        subscribe(action1, new h(this), Actions.empty());
    }

    public void subscribe(Action1<? super T> action1, Action1<? super Throwable> action12) {
        subscribe(action1, action12, Actions.empty());
    }

    public void subscribe(Action1<? super T> action1, Action1<? super Throwable> action12, Action0 action0) {
        subscribe(new i(this, action1, action12, action0));
    }
}
