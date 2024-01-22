package rx.subjects;

import rx.Observable;
import rx.Subscriber;
import rx.observers.SerializedObserver;
/* loaded from: classes13.dex */
public class SerializedSubject<T, R> extends Subject<T, R> {
    public final SerializedObserver<T> i;
    public final Subject<T, R> j;

    /* loaded from: classes13.dex */
    public class a implements Observable.OnSubscribe<R> {
        public final /* synthetic */ Subject h;

        public a(Subject subject) {
            this.h = subject;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super R> subscriber) {
            this.h.unsafeSubscribe(subscriber);
        }
    }

    public SerializedSubject(Subject<T, R> subject) {
        super(new a(subject));
        this.j = subject;
        this.i = new SerializedObserver<>(subject);
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.j.hasObservers();
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.i.onCompleted();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.i.onError(th);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.i.onNext(t);
    }
}
