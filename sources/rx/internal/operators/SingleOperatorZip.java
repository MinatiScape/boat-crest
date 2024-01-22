package rx.internal.operators;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.functions.FuncN;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class SingleOperatorZip {

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class a<R> implements Single.OnSubscribe<R> {
        public final /* synthetic */ Single[] h;
        public final /* synthetic */ FuncN i;

        /* JADX INFO: Add missing generic type declarations: [T] */
        /* renamed from: rx.internal.operators.SingleOperatorZip$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0962a<T> extends SingleSubscriber<T> {
            public final /* synthetic */ Object[] i;
            public final /* synthetic */ int j;
            public final /* synthetic */ AtomicInteger k;
            public final /* synthetic */ SingleSubscriber l;
            public final /* synthetic */ AtomicBoolean m;

            public C0962a(Object[] objArr, int i, AtomicInteger atomicInteger, SingleSubscriber singleSubscriber, AtomicBoolean atomicBoolean) {
                this.i = objArr;
                this.j = i;
                this.k = atomicInteger;
                this.l = singleSubscriber;
                this.m = atomicBoolean;
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                if (this.m.compareAndSet(false, true)) {
                    this.l.onError(th);
                } else {
                    RxJavaHooks.onError(th);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // rx.SingleSubscriber
            public void onSuccess(T t) {
                this.i[this.j] = t;
                if (this.k.decrementAndGet() == 0) {
                    try {
                        this.l.onSuccess(a.this.i.call(this.i));
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        onError(th);
                    }
                }
            }
        }

        public a(Single[] singleArr, FuncN funcN) {
            this.h = singleArr;
            this.i = funcN;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SingleSubscriber<? super R> singleSubscriber) {
            if (this.h.length == 0) {
                singleSubscriber.onError(new NoSuchElementException("Can't zip 0 Singles."));
                return;
            }
            AtomicInteger atomicInteger = new AtomicInteger(this.h.length);
            AtomicBoolean atomicBoolean = new AtomicBoolean();
            Object[] objArr = new Object[this.h.length];
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            singleSubscriber.add(compositeSubscription);
            for (int i = 0; i < this.h.length && !compositeSubscription.isUnsubscribed() && !atomicBoolean.get(); i++) {
                C0962a c0962a = new C0962a(objArr, i, atomicInteger, singleSubscriber, atomicBoolean);
                compositeSubscription.add(c0962a);
                if (compositeSubscription.isUnsubscribed() || atomicBoolean.get()) {
                    return;
                }
                this.h[i].subscribe(c0962a);
            }
        }
    }

    public SingleOperatorZip() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, R> Single<R> zip(Single<? extends T>[] singleArr, FuncN<? extends R> funcN) {
        return Single.create(new a(singleArr, funcN));
    }
}
