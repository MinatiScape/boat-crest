package io.reactivex.rxjava3.processors;

import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class BehaviorProcessor<T> extends FlowableProcessor<T> {
    public static final Object[] p = new Object[0];
    public static final a[] q = new a[0];
    public static final a[] r = new a[0];
    public final AtomicReference<a<T>[]> i;
    public final ReadWriteLock j;
    public final Lock k;
    public final Lock l;
    public final AtomicReference<Object> m;
    public final AtomicReference<Throwable> n;
    public long o;

    /* loaded from: classes12.dex */
    public static final class a<T> extends AtomicLong implements Subscription, AppendOnlyLinkedArrayList.NonThrowingPredicate<Object> {
        private static final long serialVersionUID = 3293175281126227086L;
        public volatile boolean cancelled;
        public final Subscriber<? super T> downstream;
        public boolean emitting;
        public boolean fastPath;
        public long index;
        public boolean next;
        public AppendOnlyLinkedArrayList<Object> queue;
        public final BehaviorProcessor<T> state;

        public a(Subscriber<? super T> subscriber, BehaviorProcessor<T> behaviorProcessor) {
            this.downstream = subscriber;
            this.state = behaviorProcessor;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.state.f(this);
        }

        public void emitFirst() {
            if (this.cancelled) {
                return;
            }
            synchronized (this) {
                if (this.cancelled) {
                    return;
                }
                if (this.next) {
                    return;
                }
                BehaviorProcessor<T> behaviorProcessor = this.state;
                Lock lock = behaviorProcessor.k;
                lock.lock();
                this.index = behaviorProcessor.o;
                Object obj = behaviorProcessor.m.get();
                lock.unlock();
                this.emitting = obj != null;
                this.next = true;
                if (obj == null || test(obj)) {
                    return;
                }
                emitLoop();
            }
        }

        public void emitLoop() {
            AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList;
            while (!this.cancelled) {
                synchronized (this) {
                    appendOnlyLinkedArrayList = this.queue;
                    if (appendOnlyLinkedArrayList == null) {
                        this.emitting = false;
                        return;
                    }
                    this.queue = null;
                }
                appendOnlyLinkedArrayList.forEachWhile(this);
            }
        }

        public void emitNext(Object obj, long j) {
            if (this.cancelled) {
                return;
            }
            if (!this.fastPath) {
                synchronized (this) {
                    if (this.cancelled) {
                        return;
                    }
                    if (this.index == j) {
                        return;
                    }
                    if (this.emitting) {
                        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.queue;
                        if (appendOnlyLinkedArrayList == null) {
                            appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                            this.queue = appendOnlyLinkedArrayList;
                        }
                        appendOnlyLinkedArrayList.add(obj);
                        return;
                    }
                    this.next = true;
                    this.fastPath = true;
                }
            }
            test(obj);
        }

        public boolean isFull() {
            return get() == 0;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this, j);
            }
        }

        @Override // io.reactivex.rxjava3.internal.util.AppendOnlyLinkedArrayList.NonThrowingPredicate, io.reactivex.rxjava3.functions.Predicate
        public boolean test(Object obj) {
            if (this.cancelled) {
                return true;
            }
            if (NotificationLite.isComplete(obj)) {
                this.downstream.onComplete();
                return true;
            } else if (NotificationLite.isError(obj)) {
                this.downstream.onError(NotificationLite.getError(obj));
                return true;
            } else {
                long j = get();
                if (j != 0) {
                    this.downstream.onNext((Object) NotificationLite.getValue(obj));
                    if (j != Long.MAX_VALUE) {
                        decrementAndGet();
                        return false;
                    }
                    return false;
                }
                cancel();
                this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
                return true;
            }
        }
    }

    public BehaviorProcessor() {
        this.m = new AtomicReference<>();
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.j = reentrantReadWriteLock;
        this.k = reentrantReadWriteLock.readLock();
        this.l = reentrantReadWriteLock.writeLock();
        this.i = new AtomicReference<>(q);
        this.n = new AtomicReference<>();
    }

    @CheckReturnValue
    @NonNull
    public static <T> BehaviorProcessor<T> create() {
        return new BehaviorProcessor<>();
    }

    @CheckReturnValue
    @NonNull
    public static <T> BehaviorProcessor<T> createDefault(T t) {
        Objects.requireNonNull(t, "defaultValue is null");
        return new BehaviorProcessor<>(t);
    }

    public boolean e(a<T> aVar) {
        a<T>[] aVarArr;
        a<T>[] aVarArr2;
        do {
            aVarArr = this.i.get();
            if (aVarArr == r) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.i.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void f(a<T> aVar) {
        a<T>[] aVarArr;
        a[] aVarArr2;
        do {
            aVarArr = this.i.get();
            int length = aVarArr.length;
            if (length == 0) {
                return;
            }
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (aVarArr[i2] == aVar) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                aVarArr2 = q;
            } else {
                a[] aVarArr3 = new a[length - 1];
                System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                aVarArr2 = aVarArr3;
            }
        } while (!this.i.compareAndSet(aVarArr, aVarArr2));
    }

    public void g(Object obj) {
        Lock lock = this.l;
        lock.lock();
        this.o++;
        this.m.lazySet(obj);
        lock.unlock();
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @CheckReturnValue
    @Nullable
    public Throwable getThrowable() {
        Object obj = this.m.get();
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    @CheckReturnValue
    @Nullable
    public T getValue() {
        Object obj = this.m.get();
        if (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) {
            return null;
        }
        return (T) NotificationLite.getValue(obj);
    }

    public a<T>[] h(Object obj) {
        g(obj);
        return this.i.getAndSet(r);
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @CheckReturnValue
    public boolean hasComplete() {
        return NotificationLite.isComplete(this.m.get());
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @CheckReturnValue
    public boolean hasSubscribers() {
        return this.i.get().length != 0;
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @CheckReturnValue
    public boolean hasThrowable() {
        return NotificationLite.isError(this.m.get());
    }

    @CheckReturnValue
    public boolean hasValue() {
        Object obj = this.m.get();
        return (obj == null || NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) ? false : true;
    }

    @CheckReturnValue
    public boolean offer(@NonNull T t) {
        ExceptionHelper.nullCheck(t, "offer called with a null value.");
        a<T>[] aVarArr = this.i.get();
        for (a<T> aVar : aVarArr) {
            if (aVar.isFull()) {
                return false;
            }
        }
        Object next = NotificationLite.next(t);
        g(next);
        for (a<T> aVar2 : aVarArr) {
            aVar2.emitNext(next, this.o);
        }
        return true;
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.n.compareAndSet(null, ExceptionHelper.TERMINATED)) {
            Object complete = NotificationLite.complete();
            for (a<T> aVar : h(complete)) {
                aVar.emitNext(complete, this.o);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(@NonNull Throwable th) {
        ExceptionHelper.nullCheck(th, "onError called with a null Throwable.");
        if (!this.n.compareAndSet(null, th)) {
            RxJavaPlugins.onError(th);
            return;
        }
        Object error = NotificationLite.error(th);
        for (a<T> aVar : h(error)) {
            aVar.emitNext(error, this.o);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(@NonNull T t) {
        ExceptionHelper.nullCheck(t, "onNext called with a null value.");
        if (this.n.get() != null) {
            return;
        }
        Object next = NotificationLite.next(t);
        g(next);
        for (a<T> aVar : this.i.get()) {
            aVar.emitNext(next, this.o);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onSubscribe(@NonNull Subscription subscription) {
        if (this.n.get() != null) {
            subscription.cancel();
        } else {
            subscription.request(Long.MAX_VALUE);
        }
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(@NonNull Subscriber<? super T> subscriber) {
        a<T> aVar = new a<>(subscriber, this);
        subscriber.onSubscribe(aVar);
        if (e(aVar)) {
            if (aVar.cancelled) {
                f(aVar);
                return;
            } else {
                aVar.emitFirst();
                return;
            }
        }
        Throwable th = this.n.get();
        if (th == ExceptionHelper.TERMINATED) {
            subscriber.onComplete();
        } else {
            subscriber.onError(th);
        }
    }

    public BehaviorProcessor(T t) {
        this();
        this.m.lazySet(t);
    }
}