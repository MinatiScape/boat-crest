package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.operators.mixed.FlowableSwitchMapCompletable;
import org.reactivestreams.Publisher;
/* loaded from: classes12.dex */
public final class FlowableSwitchMapCompletablePublisher<T> extends Completable {
    public final Publisher<T> h;
    public final Function<? super T, ? extends CompletableSource> i;
    public final boolean j;

    public FlowableSwitchMapCompletablePublisher(Publisher<T> publisher, Function<? super T, ? extends CompletableSource> function, boolean z) {
        this.h = publisher;
        this.i = function;
        this.j = z;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.h.subscribe(new FlowableSwitchMapCompletable.a(completableObserver, this.i, this.j));
    }
}
