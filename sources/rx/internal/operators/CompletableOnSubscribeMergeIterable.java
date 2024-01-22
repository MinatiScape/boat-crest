package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class CompletableOnSubscribeMergeIterable implements Completable.OnSubscribe {
    public final Iterable<? extends Completable> h;

    /* loaded from: classes13.dex */
    public class a implements CompletableSubscriber {
        public final /* synthetic */ CompositeSubscription h;
        public final /* synthetic */ AtomicBoolean i;
        public final /* synthetic */ CompletableSubscriber j;
        public final /* synthetic */ AtomicInteger k;

        public a(CompletableOnSubscribeMergeIterable completableOnSubscribeMergeIterable, CompositeSubscription compositeSubscription, AtomicBoolean atomicBoolean, CompletableSubscriber completableSubscriber, AtomicInteger atomicInteger) {
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

    public CompletableOnSubscribeMergeIterable(Iterable<? extends Completable> iterable) {
        this.h = iterable;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        completableSubscriber.onSubscribe(compositeSubscription);
        try {
            Iterator<? extends Completable> it = this.h.iterator();
            if (it == null) {
                completableSubscriber.onError(new NullPointerException("The source iterator returned is null"));
                return;
            }
            AtomicInteger atomicInteger = new AtomicInteger(1);
            AtomicBoolean atomicBoolean = new AtomicBoolean();
            while (!compositeSubscription.isUnsubscribed()) {
                try {
                    if (!it.hasNext()) {
                        if (atomicInteger.decrementAndGet() == 0 && atomicBoolean.compareAndSet(false, true)) {
                            completableSubscriber.onCompleted();
                            return;
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
                                compositeSubscription.unsubscribe();
                                Throwable nullPointerException = new NullPointerException("A completable source is null");
                                if (atomicBoolean.compareAndSet(false, true)) {
                                    completableSubscriber.onError(nullPointerException);
                                    return;
                                } else {
                                    RxJavaHooks.onError(nullPointerException);
                                    return;
                                }
                            }
                            atomicInteger.getAndIncrement();
                            next.unsafeSubscribe(new a(this, compositeSubscription, atomicBoolean, completableSubscriber, atomicInteger));
                        } catch (Throwable th) {
                            compositeSubscription.unsubscribe();
                            if (atomicBoolean.compareAndSet(false, true)) {
                                completableSubscriber.onError(th);
                                return;
                            } else {
                                RxJavaHooks.onError(th);
                                return;
                            }
                        }
                    }
                } catch (Throwable th2) {
                    compositeSubscription.unsubscribe();
                    if (atomicBoolean.compareAndSet(false, true)) {
                        completableSubscriber.onError(th2);
                        return;
                    } else {
                        RxJavaHooks.onError(th2);
                        return;
                    }
                }
            }
        } catch (Throwable th3) {
            completableSubscriber.onError(th3);
        }
    }
}
