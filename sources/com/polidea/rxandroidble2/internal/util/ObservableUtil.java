package com.polidea.rxandroidble2.internal.util;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
/* loaded from: classes12.dex */
public class ObservableUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final ObservableTransformer<?, ?> f13521a = new a();

    /* loaded from: classes12.dex */
    public class a implements ObservableTransformer<Object, Object> {
        @Override // io.reactivex.ObservableTransformer
        /* renamed from: a */
        public Observable<Object> apply(Observable<Object> observable) {
            return observable;
        }
    }

    public static <T> ObservableTransformer<T, T> identityTransformer() {
        return (ObservableTransformer<T, T>) f13521a;
    }

    public static <T> Observable<T> justOnNext(T t) {
        return Observable.never().startWith((Observable) t);
    }
}
