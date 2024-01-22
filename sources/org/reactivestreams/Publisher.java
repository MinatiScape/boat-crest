package org.reactivestreams;
/* loaded from: classes13.dex */
public interface Publisher<T> {
    void subscribe(Subscriber<? super T> subscriber);
}
