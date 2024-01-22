package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.AssemblyStackTraceException;
/* loaded from: classes13.dex */
public final class OnSubscribeOnAssemblySingle<T> implements Single.OnSubscribe<T> {
    public static volatile boolean fullStackTrace;
    public final Single.OnSubscribe<T> h;
    public final String i = OnSubscribeOnAssembly.a();

    /* loaded from: classes13.dex */
    public static final class a<T> extends SingleSubscriber<T> {
        public final SingleSubscriber<? super T> i;
        public final String j;

        public a(SingleSubscriber<? super T> singleSubscriber, String str) {
            this.i = singleSubscriber;
            this.j = str;
            singleSubscriber.add(this);
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            new AssemblyStackTraceException(this.j).attachTo(th);
            this.i.onError(th);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            this.i.onSuccess(t);
        }
    }

    public OnSubscribeOnAssemblySingle(Single.OnSubscribe<T> onSubscribe) {
        this.h = onSubscribe;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        this.h.call(new a(singleSubscriber, this.i));
    }
}
