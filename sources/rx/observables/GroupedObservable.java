package rx.observables;

import rx.Observable;
import rx.Subscriber;
/* loaded from: classes13.dex */
public class GroupedObservable<K, T> extends Observable<T> {
    public final K i;

    /* loaded from: classes13.dex */
    public static class a implements Observable.OnSubscribe<T> {
        public final /* synthetic */ Observable h;

        public a(Observable observable) {
            this.h = observable;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super T> subscriber) {
            this.h.unsafeSubscribe(subscriber);
        }
    }

    public GroupedObservable(K k, Observable.OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
        this.i = k;
    }

    public static <K, T> GroupedObservable<K, T> create(K k, Observable.OnSubscribe<T> onSubscribe) {
        return new GroupedObservable<>(k, onSubscribe);
    }

    public static <K, T> GroupedObservable<K, T> from(K k, Observable<T> observable) {
        return new GroupedObservable<>(k, new a(observable));
    }

    public K getKey() {
        return this.i;
    }
}
