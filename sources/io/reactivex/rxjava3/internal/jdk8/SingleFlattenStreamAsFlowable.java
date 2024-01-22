package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.jdk8.MaybeFlattenStreamAsFlowable;
import java.util.stream.Stream;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class SingleFlattenStreamAsFlowable<T, R> extends Flowable<R> {
    public final Single<T> i;
    public final Function<? super T, ? extends Stream<? extends R>> j;

    public SingleFlattenStreamAsFlowable(Single<T> single, Function<? super T, ? extends Stream<? extends R>> function) {
        this.i = single;
        this.j = function;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(@NonNull Subscriber<? super R> subscriber) {
        this.i.subscribe(new MaybeFlattenStreamAsFlowable.a(subscriber, this.j));
    }
}
