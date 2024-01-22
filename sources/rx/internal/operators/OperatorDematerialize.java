package rx.internal.operators;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;
/* loaded from: classes13.dex */
public final class OperatorDematerialize<T> implements Observable.Operator<T, Notification<T>> {

    /* loaded from: classes13.dex */
    public class a extends Subscriber<Notification<T>> {
        public boolean l;
        public final /* synthetic */ Subscriber m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(OperatorDematerialize operatorDematerialize, Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.m = subscriber2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.m.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.l) {
                return;
            }
            this.l = true;
            this.m.onError(th);
        }

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Notification) ((Notification) obj));
        }

        public void onNext(Notification<T> notification) {
            int i = b.f15659a[notification.getKind().ordinal()];
            if (i == 1) {
                if (this.l) {
                    return;
                }
                this.m.onNext(notification.getValue());
            } else if (i == 2) {
                onError(notification.getThrowable());
            } else if (i != 3) {
                onError(new IllegalArgumentException("Unsupported notification type: " + notification));
            } else {
                onCompleted();
            }
        }
    }

    /* loaded from: classes13.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f15659a;

        static {
            int[] iArr = new int[Notification.Kind.values().length];
            f15659a = iArr;
            try {
                iArr[Notification.Kind.OnNext.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15659a[Notification.Kind.OnError.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15659a[Notification.Kind.OnCompleted.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorDematerialize<Object> f15660a = new OperatorDematerialize<>();
    }

    public static OperatorDematerialize instance() {
        return c.f15660a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super Notification<T>> call(Subscriber<? super T> subscriber) {
        return new a(this, subscriber, subscriber);
    }
}
