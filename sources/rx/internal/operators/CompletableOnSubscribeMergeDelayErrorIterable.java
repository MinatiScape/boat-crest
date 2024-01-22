package rx.internal.operators;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.internal.util.atomic.MpscLinkedAtomicQueue;
import rx.internal.util.unsafe.MpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class CompletableOnSubscribeMergeDelayErrorIterable implements Completable.OnSubscribe {
    public final Iterable<? extends Completable> h;

    /* loaded from: classes13.dex */
    public class a implements CompletableSubscriber {
        public final /* synthetic */ CompositeSubscription h;
        public final /* synthetic */ Queue i;
        public final /* synthetic */ AtomicInteger j;
        public final /* synthetic */ CompletableSubscriber k;

        public a(CompletableOnSubscribeMergeDelayErrorIterable completableOnSubscribeMergeDelayErrorIterable, CompositeSubscription compositeSubscription, Queue queue, AtomicInteger atomicInteger, CompletableSubscriber completableSubscriber) {
            this.h = compositeSubscription;
            this.i = queue;
            this.j = atomicInteger;
            this.k = completableSubscriber;
        }

        public void a() {
            if (this.j.decrementAndGet() == 0) {
                if (this.i.isEmpty()) {
                    this.k.onCompleted();
                } else {
                    this.k.onError(CompletableOnSubscribeMerge.collectErrors(this.i));
                }
            }
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            a();
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            this.i.offer(th);
            a();
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.h.add(subscription);
        }
    }

    public CompletableOnSubscribeMergeDelayErrorIterable(Iterable<? extends Completable> iterable) {
        this.h = iterable;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        Queue mpscLinkedAtomicQueue;
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        completableSubscriber.onSubscribe(compositeSubscription);
        try {
            Iterator<? extends Completable> it = this.h.iterator();
            if (it == null) {
                completableSubscriber.onError(new NullPointerException("The source iterator returned is null"));
                return;
            }
            AtomicInteger atomicInteger = new AtomicInteger(1);
            if (UnsafeAccess.isUnsafeAvailable()) {
                mpscLinkedAtomicQueue = new MpscLinkedQueue();
            } else {
                mpscLinkedAtomicQueue = new MpscLinkedAtomicQueue();
            }
            Queue queue = mpscLinkedAtomicQueue;
            while (!compositeSubscription.isUnsubscribed()) {
                try {
                    if (!it.hasNext()) {
                        if (atomicInteger.decrementAndGet() == 0) {
                            if (queue.isEmpty()) {
                                completableSubscriber.onCompleted();
                                return;
                            } else {
                                completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                                return;
                            }
                        }
                        return;
                    } else if (compositeSubscription.isUnsubscribed()) {
                        return;
                    } else {
                        try {
                            Completable next = it.next();
                            if (compositeSubscription.isUnsubscribed()) {
                                return;
                            }
                            if (next == null) {
                                queue.offer(new NullPointerException("A completable source is null"));
                                if (atomicInteger.decrementAndGet() == 0) {
                                    if (queue.isEmpty()) {
                                        completableSubscriber.onCompleted();
                                        return;
                                    } else {
                                        completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                                        return;
                                    }
                                }
                                return;
                            }
                            atomicInteger.getAndIncrement();
                            next.unsafeSubscribe(new a(this, compositeSubscription, queue, atomicInteger, completableSubscriber));
                        } catch (Throwable th) {
                            queue.offer(th);
                            if (atomicInteger.decrementAndGet() == 0) {
                                if (queue.isEmpty()) {
                                    completableSubscriber.onCompleted();
                                    return;
                                } else {
                                    completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                                    return;
                                }
                            }
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    queue.offer(th2);
                    if (atomicInteger.decrementAndGet() == 0) {
                        if (queue.isEmpty()) {
                            completableSubscriber.onCompleted();
                            return;
                        } else {
                            completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                            return;
                        }
                    }
                    return;
                }
            }
        } catch (Throwable th3) {
            completableSubscriber.onError(th3);
        }
    }
}
