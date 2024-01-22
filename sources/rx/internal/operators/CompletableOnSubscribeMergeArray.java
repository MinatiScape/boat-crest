package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class CompletableOnSubscribeMergeArray implements Completable.OnSubscribe {
    public final Completable[] h;

    /* loaded from: classes13.dex */
    public class a implements CompletableSubscriber {
        public final /* synthetic */ CompositeSubscription h;
        public final /* synthetic */ AtomicBoolean i;
        public final /* synthetic */ CompletableSubscriber j;
        public final /* synthetic */ AtomicInteger k;

        public a(CompletableOnSubscribeMergeArray completableOnSubscribeMergeArray, CompositeSubscription compositeSubscription, AtomicBoolean atomicBoolean, CompletableSubscriber completableSubscriber, AtomicInteger atomicInteger) {
            this.h = compositeSubscription;
            this.i = atomicBoolean;
            this.j = completableSubscriber;
            this.k = atomicInteger;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            if (this.k.decrementAndGet() == 0 && this.i.compareAndSet(false, true)) {
                this.j.onCompleted();
            }
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            this.h.unsubscribe();
            if (this.i.compareAndSet(false, true)) {
                this.j.onError(th);
            } else {
                RxJavaHooks.onError(th);
            }
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.h.add(subscription);
        }
    }

    public CompletableOnSubscribeMergeArray(Completable[] completableArr) {
        this.h = completableArr;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        boolean z = true;
        AtomicInteger atomicInteger = new AtomicInteger(this.h.length + 1);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        completableSubscriber.onSubscribe(compositeSubscription);
        Completable[] completableArr = this.h;
        int length = completableArr.length;
        boolean z2 = false;
        int i = 0;
        while (i < length) {
            Completable completable = completableArr[i];
            if (compositeSubscription.isUnsubscribed()) {
                return;
            }
            if (completable == null) {
                compositeSubscription.unsubscribe();
                Throwable nullPointerException = new NullPointerException("A completable source is null");
                if (atomicBoolean.compareAndSet(z2, z)) {
                    completableSubscriber.onError(nullPointerException);
                    return;
                }
                RxJavaHooks.onError(nullPointerException);
            }
            completable.unsafeSubscribe(new a(this, compositeSubscription, atomicBoolean, completableSubscriber, atomicInteger));
            i++;
            z = true;
            z2 = false;
        }
        if (atomicInteger.decrementAndGet() == 0 && atomicBoolean.compareAndSet(false, true)) {
            completableSubscriber.onCompleted();
        }
    }
}
