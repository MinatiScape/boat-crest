package io.reactivex.internal.operators.flowable;

import org.reactivestreams.Publisher;
/* loaded from: classes12.dex */
public interface FlowablePublishClassic<T> {
    int publishBufferSize();

    Publisher<T> publishSource();
}
