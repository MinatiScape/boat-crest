package io.reactivex.rxjava3.internal.operators.parallel;

import io.reactivex.rxjava3.parallel.ParallelFlowable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class ParallelFromArray<T> extends ParallelFlowable<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Publisher<T>[] f13981a;

    public ParallelFromArray(Publisher<T>[] publisherArr) {
        this.f13981a = publisherArr;
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public int parallelism() {
        return this.f13981a.length;
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public void subscribe(Subscriber<? super T>[] subscriberArr) {
        if (validate(subscriberArr)) {
            int length = subscriberArr.length;
            for (int i = 0; i < length; i++) {
                this.f13981a[i].subscribe(subscriberArr[i]);
            }
        }
    }
}
