package io.reactivex.rxjava3.internal.util;

import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public interface QueueDrain<T, U> {
    boolean accept(Subscriber<? super U> subscriber, T t);

    boolean cancelled();

    boolean done();

    boolean enter();

    Throwable error();

    int leave(int i);

    long produced(long j);

    long requested();
}
