package rx.internal.operators;

import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.exceptions.AssemblyStackTraceException;
/* loaded from: classes13.dex */
public final class OnSubscribeOnAssemblyCompletable<T> implements Completable.OnSubscribe {
    public static volatile boolean fullStackTrace;
    public final Completable.OnSubscribe h;
    public final String i = OnSubscribeOnAssembly.a();

    /* loaded from: classes13.dex */
    public static final class a implements CompletableSubscriber {
        public final CompletableSubscriber h;
        public final String i;

        public a(CompletableSubscriber completableSubscriber, String str) {
            this.h = completableSubscriber;
            this.i = str;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            this.h.onCompleted();
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            new AssemblyStackTraceException(this.i).attachTo(th);
            this.h.onError(th);
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.h.onSubscribe(subscription);
        }
    }

    public OnSubscribeOnAssemblyCompletable(Completable.OnSubscribe onSubscribe) {
        this.h = onSubscribe;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        this.h.call(new a(completableSubscriber, this.i));
    }
}
