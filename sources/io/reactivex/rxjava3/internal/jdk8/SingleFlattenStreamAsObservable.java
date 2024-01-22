package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.jdk8.MaybeFlattenStreamAsObservable;
import java.util.stream.Stream;
/* loaded from: classes12.dex */
public final class SingleFlattenStreamAsObservable<T, R> extends Observable<R> {
    public final Single<T> h;
    public final Function<? super T, ? extends Stream<? extends R>> i;

    public SingleFlattenStreamAsObservable(Single<T> single, Function<? super T, ? extends Stream<? extends R>> function) {
        this.h = single;
        this.i = function;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(@NonNull Observer<? super R> observer) {
        this.h.subscribe(new MaybeFlattenStreamAsObservable.a(observer, this.i));
    }
}
