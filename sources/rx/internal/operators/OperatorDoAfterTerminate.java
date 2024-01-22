package rx.internal.operators;

import java.util.Objects;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OperatorDoAfterTerminate<T> implements Observable.Operator<T, T> {
    public final Action0 h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public final /* synthetic */ Subscriber l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.l = subscriber2;
        }

        public void b() {
            try {
                OperatorDoAfterTerminate.this.h.call();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaHooks.onError(th);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            try {
                this.l.onCompleted();
            } finally {
                b();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            try {
                this.l.onError(th);
            } finally {
                b();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.onNext(t);
        }
    }

    public OperatorDoAfterTerminate(Action0 action0) {
        Objects.requireNonNull(action0, "Action can not be null");
        this.h = action0;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        return new a(subscriber, subscriber);
    }
}
