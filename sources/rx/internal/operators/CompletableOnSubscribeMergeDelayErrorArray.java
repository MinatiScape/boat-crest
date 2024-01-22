package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class CompletableOnSubscribeMergeDelayErrorArray implements Completable.OnSubscribe {
    public final Completable[] h;

    /* loaded from: classes13.dex */
    public class a implements CompletableSubscriber {
        public final /* synthetic */ CompositeSubscription h;
        public final /* synthetic */ Queue i;
        public final /* synthetic */ AtomicInteger j;
        public final /* synthetic */ CompletableSubscriber k;

        public a(CompletableOnSubscribeMergeDelayErrorArray completableOnSubscribeMergeDelayErrorArray, CompositeSubscription compositeSubscription, Queue queue, AtomicInteger atomicInteger, CompletableSubscriber completableSubscriber) {
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

    public CompletableOnSubscribeMergeDelayErrorArray(Completable[] completableArr) {
        this.h = completableArr;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        Completable[] completableArr;
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        AtomicInteger atomicInteger = new AtomicInteger(this.h.length + 1);
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        completableSubscriber.onSubscribe(compositeSubscription);
        for (Completable completable : this.h) {
            if (compositeSubscription.isUnsubscribed()) {
                return;
            }
            if (completable == null) {
                concurrentLinkedQueue.offer(new NullPointerException("A completable source is null"));
                atomicInteger.decrementAndGet();
            } else {
                completable.unsafeSubscribe(new a(this, compositeSubscription, concurrentLinkedQueue, atomicInteger, completableSubscriber));
            }
        }
        if (atomicInteger.decrementAndGet() == 0) {
            if (concurrentLinkedQueue.isEmpty()) {
                completableSubscriber.onCompleted();
            } else {
                completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(concurrentLinkedQueue));
            }
        }
    }
}
