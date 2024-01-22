package org.reactivestreams;
/* loaded from: classes13.dex */
public interface Subscription {
    void cancel();

    void request(long j);
}
